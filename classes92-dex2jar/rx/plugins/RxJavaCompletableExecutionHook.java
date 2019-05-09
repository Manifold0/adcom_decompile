// 
// Decompiled by Procyon v0.5.34
// 

package rx.plugins;

import rx.Completable;
import rx.annotations.Experimental;

@Experimental
public abstract class RxJavaCompletableExecutionHook
{
    @Deprecated
    public Completable.OnSubscribe onCreate(final Completable.OnSubscribe onSubscribe) {
        return onSubscribe;
    }
    
    @Deprecated
    public Completable.Operator onLift(final Completable.Operator operator) {
        return operator;
    }
    
    @Deprecated
    public Throwable onSubscribeError(final Throwable t) {
        return t;
    }
    
    @Deprecated
    public Completable.OnSubscribe onSubscribeStart(final Completable completable, final Completable.OnSubscribe onSubscribe) {
        return onSubscribe;
    }
}
