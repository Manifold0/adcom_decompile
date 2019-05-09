// 
// Decompiled by Procyon v0.5.34
// 

package rx;

import rx.annotations.Experimental;

@Experimental
public interface AsyncEmitter<T> extends Observer<T>
{
    long requested();
    
    void setCancellation(final Cancellable p0);
    
    void setSubscription(final Subscription p0);
    
    public enum BackpressureMode
    {
        BUFFER, 
        DROP, 
        ERROR, 
        LATEST, 
        NONE;
    }
    
    public interface Cancellable
    {
        void cancel() throws Exception;
    }
}
