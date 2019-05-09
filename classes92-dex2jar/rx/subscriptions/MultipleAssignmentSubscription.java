// 
// Decompiled by Procyon v0.5.34
// 

package rx.subscriptions;

import rx.internal.subscriptions.SequentialSubscription;
import rx.Subscription;

public final class MultipleAssignmentSubscription implements Subscription
{
    final SequentialSubscription state;
    
    public MultipleAssignmentSubscription() {
        this.state = new SequentialSubscription();
    }
    
    public Subscription get() {
        return this.state.current();
    }
    
    @Override
    public boolean isUnsubscribed() {
        return this.state.isUnsubscribed();
    }
    
    public void set(final Subscription subscription) {
        if (subscription == null) {
            throw new IllegalArgumentException("Subscription can not be null");
        }
        this.state.replace(subscription);
    }
    
    @Override
    public void unsubscribe() {
        this.state.unsubscribe();
    }
}
