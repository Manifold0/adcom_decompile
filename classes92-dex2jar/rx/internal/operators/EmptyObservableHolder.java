// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Subscriber;
import rx.Observable;

public enum EmptyObservableHolder implements OnSubscribe<Object>
{
    static final Observable<Object> EMPTY;
    
    INSTANCE;
    
    static {
        EMPTY = Observable.create((Observable.OnSubscribe<Object>)EmptyObservableHolder.INSTANCE);
    }
    
    public static <T> Observable<T> instance() {
        return (Observable<T>)EmptyObservableHolder.EMPTY;
    }
    
    @Override
    public void call(final Subscriber<? super Object> subscriber) {
        subscriber.onCompleted();
    }
}
