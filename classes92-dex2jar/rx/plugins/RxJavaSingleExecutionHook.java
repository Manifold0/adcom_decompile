// 
// Decompiled by Procyon v0.5.34
// 

package rx.plugins;

import rx.Subscription;
import rx.Observable;
import rx.Single;

public abstract class RxJavaSingleExecutionHook
{
    @Deprecated
    public <T> Single.OnSubscribe<T> onCreate(final Single.OnSubscribe<T> onSubscribe) {
        return onSubscribe;
    }
    
    @Deprecated
    public <T, R> Observable.Operator<? extends R, ? super T> onLift(final Observable.Operator<? extends R, ? super T> operator) {
        return operator;
    }
    
    @Deprecated
    public <T> Throwable onSubscribeError(final Throwable t) {
        return t;
    }
    
    @Deprecated
    public <T> Subscription onSubscribeReturn(final Subscription subscription) {
        return subscription;
    }
    
    @Deprecated
    public <T> Observable.OnSubscribe<T> onSubscribeStart(final Single<? extends T> single, final Observable.OnSubscribe<T> onSubscribe) {
        return onSubscribe;
    }
}
