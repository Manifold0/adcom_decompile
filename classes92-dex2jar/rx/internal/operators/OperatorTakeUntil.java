// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Subscription;
import rx.observers.SerializedSubscriber;
import rx.Subscriber;
import rx.Observable;

public final class OperatorTakeUntil<T, E> implements Operator<T, T>
{
    private final Observable<? extends E> other;
    
    public OperatorTakeUntil(final Observable<? extends E> other) {
        this.other = other;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final SerializedSubscriber serializedSubscriber = new SerializedSubscriber((Subscriber<? super Object>)subscriber, false);
        final Subscriber<T> subscriber2 = new Subscriber<T>(serializedSubscriber, false) {
            @Override
            public void onCompleted() {
                try {
                    serializedSubscriber.onCompleted();
                }
                finally {
                    serializedSubscriber.unsubscribe();
                }
            }
            
            @Override
            public void onError(final Throwable t) {
                try {
                    serializedSubscriber.onError(t);
                }
                finally {
                    serializedSubscriber.unsubscribe();
                }
            }
            
            @Override
            public void onNext(final T t) {
                serializedSubscriber.onNext(t);
            }
        };
        final Subscriber<E> subscriber3 = new Subscriber<E>() {
            @Override
            public void onCompleted() {
                subscriber2.onCompleted();
            }
            
            @Override
            public void onError(final Throwable t) {
                subscriber2.onError(t);
            }
            
            @Override
            public void onNext(final E e) {
                this.onCompleted();
            }
            
            @Override
            public void onStart() {
                this.request(Long.MAX_VALUE);
            }
        };
        serializedSubscriber.add(subscriber2);
        serializedSubscriber.add(subscriber3);
        subscriber.add(serializedSubscriber);
        this.other.unsafeSubscribe(subscriber3);
        return subscriber2;
    }
}
