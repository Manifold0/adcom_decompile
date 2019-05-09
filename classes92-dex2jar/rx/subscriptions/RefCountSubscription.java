// 
// Decompiled by Procyon v0.5.34
// 

package rx.subscriptions;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;

public final class RefCountSubscription implements Subscription
{
    static final State EMPTY_STATE;
    private final Subscription actual;
    final AtomicReference<State> state;
    
    static {
        EMPTY_STATE = new State(false, 0);
    }
    
    public RefCountSubscription(final Subscription actual) {
        this.state = new AtomicReference<State>(RefCountSubscription.EMPTY_STATE);
        if (actual == null) {
            throw new IllegalArgumentException("s");
        }
        this.actual = actual;
    }
    
    private void unsubscribeActualIfApplicable(final State state) {
        if (state.isUnsubscribed && state.children == 0) {
            this.actual.unsubscribe();
        }
    }
    
    public Subscription get() {
        final AtomicReference<State> state = this.state;
        State state2;
        do {
            state2 = state.get();
            if (state2.isUnsubscribed) {
                return Subscriptions.unsubscribed();
            }
        } while (!state.compareAndSet(state2, state2.addChild()));
        return new InnerSubscription(this);
    }
    
    @Override
    public boolean isUnsubscribed() {
        return this.state.get().isUnsubscribed;
    }
    
    @Override
    public void unsubscribe() {
        final AtomicReference<State> state = this.state;
        State state2;
        State unsubscribe;
        do {
            state2 = state.get();
            if (state2.isUnsubscribed) {
                return;
            }
            unsubscribe = state2.unsubscribe();
        } while (!state.compareAndSet(state2, unsubscribe));
        this.unsubscribeActualIfApplicable(unsubscribe);
    }
    
    void unsubscribeAChild() {
        final AtomicReference<State> state = this.state;
        State state2;
        State removeChild;
        do {
            state2 = state.get();
            removeChild = state2.removeChild();
        } while (!state.compareAndSet(state2, removeChild));
        this.unsubscribeActualIfApplicable(removeChild);
    }
    
    static final class InnerSubscription extends AtomicInteger implements Subscription
    {
        private static final long serialVersionUID = 7005765588239987643L;
        final RefCountSubscription parent;
        
        public InnerSubscription(final RefCountSubscription parent) {
            this.parent = parent;
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.get() != 0;
        }
        
        @Override
        public void unsubscribe() {
            if (this.compareAndSet(0, 1)) {
                this.parent.unsubscribeAChild();
            }
        }
    }
    
    static final class State
    {
        final int children;
        final boolean isUnsubscribed;
        
        State(final boolean isUnsubscribed, final int children) {
            this.isUnsubscribed = isUnsubscribed;
            this.children = children;
        }
        
        State addChild() {
            return new State(this.isUnsubscribed, this.children + 1);
        }
        
        State removeChild() {
            return new State(this.isUnsubscribed, this.children - 1);
        }
        
        State unsubscribe() {
            return new State(true, this.children);
        }
    }
}
