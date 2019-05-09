// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import rx.subscriptions.Subscriptions;
import rx.functions.Action0;
import rx.subscriptions.BooleanSubscription;
import rx.Subscription;
import rx.Scheduler;

public final class ImmediateScheduler extends Scheduler
{
    public static final ImmediateScheduler INSTANCE;
    
    static {
        INSTANCE = new ImmediateScheduler();
    }
    
    private ImmediateScheduler() {
    }
    
    @Override
    public Worker createWorker() {
        return new InnerImmediateScheduler();
    }
    
    final class InnerImmediateScheduler extends Worker implements Subscription
    {
        final BooleanSubscription innerSubscription;
        
        InnerImmediateScheduler() {
            this.innerSubscription = new BooleanSubscription();
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.innerSubscription.isUnsubscribed();
        }
        
        @Override
        public Subscription schedule(final Action0 action0) {
            action0.call();
            return Subscriptions.unsubscribed();
        }
        
        @Override
        public Subscription schedule(final Action0 action0, final long n, final TimeUnit timeUnit) {
            return this.schedule(new SleepingAction(action0, this, ImmediateScheduler.this.now() + timeUnit.toMillis(n)));
        }
        
        @Override
        public void unsubscribe() {
            this.innerSubscription.unsubscribe();
        }
    }
}
