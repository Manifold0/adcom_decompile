// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util;

import rx.functions.Action1;
import rx.functions.Action0;
import rx.Subscriber;

public final class ActionSubscriber<T> extends Subscriber<T>
{
    final Action0 onCompleted;
    final Action1<Throwable> onError;
    final Action1<? super T> onNext;
    
    public ActionSubscriber(final Action1<? super T> onNext, final Action1<Throwable> onError, final Action0 onCompleted) {
        this.onNext = onNext;
        this.onError = onError;
        this.onCompleted = onCompleted;
    }
    
    @Override
    public void onCompleted() {
        this.onCompleted.call();
    }
    
    @Override
    public void onError(final Throwable t) {
        this.onError.call(t);
    }
    
    @Override
    public void onNext(final T t) {
        this.onNext.call((Object)t);
    }
}
