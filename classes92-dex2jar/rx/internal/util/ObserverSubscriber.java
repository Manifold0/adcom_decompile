// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util;

import rx.Observer;
import rx.Subscriber;

public final class ObserverSubscriber<T> extends Subscriber<T>
{
    final Observer<? super T> observer;
    
    public ObserverSubscriber(final Observer<? super T> observer) {
        this.observer = observer;
    }
    
    @Override
    public void onCompleted() {
        this.observer.onCompleted();
    }
    
    @Override
    public void onError(final Throwable t) {
        this.observer.onError(t);
    }
    
    @Override
    public void onNext(final T t) {
        this.observer.onNext((Object)t);
    }
}
