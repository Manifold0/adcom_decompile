// 
// Decompiled by Procyon v0.5.34
// 

package rx.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import rx.functions.Action0;
import rx.Subscription;

public final class BooleanSubscription implements Subscription
{
    static final Action0 EMPTY_ACTION;
    final AtomicReference<Action0> actionRef;
    
    static {
        EMPTY_ACTION = new Action0() {
            @Override
            public void call() {
            }
        };
    }
    
    public BooleanSubscription() {
        this.actionRef = new AtomicReference<Action0>();
    }
    
    private BooleanSubscription(final Action0 action0) {
        this.actionRef = new AtomicReference<Action0>(action0);
    }
    
    public static BooleanSubscription create() {
        return new BooleanSubscription();
    }
    
    public static BooleanSubscription create(final Action0 action0) {
        return new BooleanSubscription(action0);
    }
    
    @Override
    public boolean isUnsubscribed() {
        return this.actionRef.get() == BooleanSubscription.EMPTY_ACTION;
    }
    
    @Override
    public void unsubscribe() {
        if (this.actionRef.get() != BooleanSubscription.EMPTY_ACTION) {
            final Action0 action0 = this.actionRef.getAndSet(BooleanSubscription.EMPTY_ACTION);
            if (action0 != null && action0 != BooleanSubscription.EMPTY_ACTION) {
                action0.call();
            }
        }
    }
}
