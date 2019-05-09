// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.subscriptions;

import rx.Subscription;

public enum Unsubscribed implements Subscription
{
    INSTANCE;
    
    @Override
    public boolean isUnsubscribed() {
        return true;
    }
    
    @Override
    public void unsubscribe() {
    }
}
