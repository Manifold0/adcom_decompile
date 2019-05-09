// 
// Decompiled by Procyon v0.5.34
// 

package rx;

public interface Observer<T>
{
    void onCompleted();
    
    void onError(final Throwable p0);
    
    void onNext(final T p0);
}
