// 
// Decompiled by Procyon v0.5.34
// 

package rx;

import rx.annotations.Experimental;

@Experimental
public interface CompletableSubscriber
{
    void onCompleted();
    
    void onError(final Throwable p0);
    
    void onSubscribe(final Subscription p0);
}
