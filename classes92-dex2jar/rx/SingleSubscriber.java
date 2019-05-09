// 
// Decompiled by Procyon v0.5.34
// 

package rx;

import rx.internal.util.SubscriptionList;

public abstract class SingleSubscriber<T> implements Subscription
{
    private final SubscriptionList cs;
    
    public SingleSubscriber() {
        this.cs = new SubscriptionList();
    }
    
    public final void add(final Subscription subscription) {
        this.cs.add(subscription);
    }
    
    @Override
    public final boolean isUnsubscribed() {
        return this.cs.isUnsubscribed();
    }
    
    public abstract void onError(final Throwable p0);
    
    public abstract void onSuccess(final T p0);
    
    @Override
    public final void unsubscribe() {
        this.cs.unsubscribe();
    }
}
