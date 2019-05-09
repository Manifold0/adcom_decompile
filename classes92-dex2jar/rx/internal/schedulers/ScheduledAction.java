// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.schedulers;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.plugins.RxJavaHooks;
import rx.exceptions.OnErrorNotImplementedException;
import java.util.concurrent.Future;
import rx.subscriptions.CompositeSubscription;
import rx.internal.util.SubscriptionList;
import rx.functions.Action0;
import rx.Subscription;
import java.util.concurrent.atomic.AtomicReference;

public final class ScheduledAction extends AtomicReference<Thread> implements Runnable, Subscription
{
    private static final long serialVersionUID = -3962399486978279857L;
    final Action0 action;
    final SubscriptionList cancel;
    
    public ScheduledAction(final Action0 action) {
        this.action = action;
        this.cancel = new SubscriptionList();
    }
    
    public ScheduledAction(final Action0 action, final SubscriptionList list) {
        this.action = action;
        this.cancel = new SubscriptionList(new Remover2(this, list));
    }
    
    public ScheduledAction(final Action0 action, final CompositeSubscription compositeSubscription) {
        this.action = action;
        this.cancel = new SubscriptionList(new Remover(this, compositeSubscription));
    }
    
    public void add(final Future<?> future) {
        this.cancel.add(new FutureCompleter(future));
    }
    
    public void add(final Subscription subscription) {
        this.cancel.add(subscription);
    }
    
    public void addParent(final SubscriptionList list) {
        this.cancel.add(new Remover2(this, list));
    }
    
    public void addParent(final CompositeSubscription compositeSubscription) {
        this.cancel.add(new Remover(this, compositeSubscription));
    }
    
    @Override
    public boolean isUnsubscribed() {
        return this.cancel.isUnsubscribed();
    }
    
    @Override
    public void run() {
        try {
            this.lazySet(Thread.currentThread());
            this.action.call();
        }
        catch (OnErrorNotImplementedException ex) {
            this.signalError(new IllegalStateException("Exception thrown on Scheduler.Worker thread. Add `onError` handling.", ex));
        }
        catch (Throwable t) {
            this.signalError(new IllegalStateException("Fatal Exception thrown on Scheduler.Worker thread.", t));
        }
        finally {
            this.unsubscribe();
        }
    }
    
    void signalError(final Throwable t) {
        RxJavaHooks.onError(t);
        final Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, t);
    }
    
    @Override
    public void unsubscribe() {
        if (!this.cancel.isUnsubscribed()) {
            this.cancel.unsubscribe();
        }
    }
    
    final class FutureCompleter implements Subscription
    {
        private final Future<?> f;
        
        FutureCompleter(final Future<?> f) {
            this.f = f;
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.f.isCancelled();
        }
        
        @Override
        public void unsubscribe() {
            if (ScheduledAction.this.get() != Thread.currentThread()) {
                this.f.cancel(true);
                return;
            }
            this.f.cancel(false);
        }
    }
    
    static final class Remover extends AtomicBoolean implements Subscription
    {
        private static final long serialVersionUID = 247232374289553518L;
        final CompositeSubscription parent;
        final ScheduledAction s;
        
        public Remover(final ScheduledAction s, final CompositeSubscription parent) {
            this.s = s;
            this.parent = parent;
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.s.isUnsubscribed();
        }
        
        @Override
        public void unsubscribe() {
            if (this.compareAndSet(false, true)) {
                this.parent.remove(this.s);
            }
        }
    }
    
    static final class Remover2 extends AtomicBoolean implements Subscription
    {
        private static final long serialVersionUID = 247232374289553518L;
        final SubscriptionList parent;
        final ScheduledAction s;
        
        public Remover2(final ScheduledAction s, final SubscriptionList parent) {
            this.s = s;
            this.parent = parent;
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.s.isUnsubscribed();
        }
        
        @Override
        public void unsubscribe() {
            if (this.compareAndSet(false, true)) {
                this.parent.remove(this.s);
            }
        }
    }
}
