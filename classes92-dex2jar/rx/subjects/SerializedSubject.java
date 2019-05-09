// 
// Decompiled by Procyon v0.5.34
// 

package rx.subjects;

import rx.Observer;
import rx.Subscriber;
import rx.Observable;
import rx.observers.SerializedObserver;

public class SerializedSubject<T, R> extends Subject<T, R>
{
    private final Subject<T, R> actual;
    private final SerializedObserver<T> observer;
    
    public SerializedSubject(final Subject<T, R> actual) {
        super(new OnSubscribe<R>() {
            @Override
            public void call(final Subscriber<? super R> subscriber) {
                actual.unsafeSubscribe(subscriber);
            }
        });
        this.actual = actual;
        this.observer = new SerializedObserver<T>(actual);
    }
    
    @Override
    public boolean hasObservers() {
        return this.actual.hasObservers();
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
        this.observer.onNext(t);
    }
}
