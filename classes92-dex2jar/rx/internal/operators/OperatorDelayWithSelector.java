// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.exceptions.Exceptions;
import rx.Observer;
import rx.observers.Subscribers;
import rx.subjects.PublishSubject;
import rx.observers.SerializedSubscriber;
import rx.Subscriber;
import rx.functions.Func1;
import rx.Observable;

public final class OperatorDelayWithSelector<T, V> implements Operator<T, T>
{
    final Func1<? super T, ? extends Observable<V>> itemDelay;
    final Observable<? extends T> source;
    
    public OperatorDelayWithSelector(final Observable<? extends T> source, final Func1<? super T, ? extends Observable<V>> itemDelay) {
        this.source = source;
        this.itemDelay = itemDelay;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final SerializedSubscriber<Object> serializedSubscriber = new SerializedSubscriber<Object>((Subscriber<? super Object>)subscriber);
        final PublishSubject<Object> create = PublishSubject.create();
        subscriber.add(Observable.merge((Observable<? extends Observable<?>>)create).unsafeSubscribe(Subscribers.from(serializedSubscriber)));
        return new Subscriber<T>(subscriber) {
            @Override
            public void onCompleted() {
                create.onCompleted();
            }
            
            @Override
            public void onError(final Throwable t) {
                serializedSubscriber.onError(t);
            }
            
            @Override
            public void onNext(final T t) {
                try {
                    create.onNext(((Observable)OperatorDelayWithSelector.this.itemDelay.call((Object)t)).take(1).defaultIfEmpty(null).map((Func1<? super Object, ?>)new Func1<V, T>() {
                        @Override
                        public T call(final V v) {
                            return t;
                        }
                    }));
                }
                catch (Throwable t2) {
                    Exceptions.throwOrReport(t2, this);
                }
            }
        };
    }
}
