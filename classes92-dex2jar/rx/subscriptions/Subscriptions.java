// 
// Decompiled by Procyon v0.5.34
// 

package rx.subscriptions;

import java.util.concurrent.Future;
import rx.Subscription;
import rx.functions.Action0;

public final class Subscriptions
{
    private static final Unsubscribed UNSUBSCRIBED;
    
    static {
        UNSUBSCRIBED = new Unsubscribed();
    }
    
    private Subscriptions() {
        throw new IllegalStateException("No instances!");
    }
    
    public static Subscription create(final Action0 action0) {
        return BooleanSubscription.create(action0);
    }
    
    public static Subscription empty() {
        return BooleanSubscription.create();
    }
    
    public static Subscription from(final Future<?> future) {
        return new FutureSubscription(future);
    }
    
    public static CompositeSubscription from(final Subscription... array) {
        return new CompositeSubscription(array);
    }
    
    public static Subscription unsubscribed() {
        return Subscriptions.UNSUBSCRIBED;
    }
    
    static final class FutureSubscription implements Subscription
    {
        final Future<?> f;
        
        public FutureSubscription(final Future<?> f) {
            this.f = f;
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.f.isCancelled();
        }
        
        @Override
        public void unsubscribe() {
            this.f.cancel(true);
        }
    }
    
    static final class Unsubscribed implements Subscription
    {
        @Override
        public boolean isUnsubscribed() {
            return true;
        }
        
        @Override
        public void unsubscribe() {
        }
    }
}
