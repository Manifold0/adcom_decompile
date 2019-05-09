// 
// Decompiled by Procyon v0.5.34
// 

package rx;

import rx.annotations.Experimental;

@Experimental
public interface CompletableEmitter
{
    void onCompleted();
    
    void onError(final Throwable p0);
    
    void setCancellation(final AsyncEmitter.Cancellable p0);
    
    void setSubscription(final Subscription p0);
}
