// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Subscription;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.observers.SerializedSubscriber;
import rx.Subscriber;
import rx.Observable;

public final class OperatorSkipUntil<T, U> implements Operator<T, T>
{
    final Observable<U> other;
    
    public OperatorSkipUntil(final Observable<U> other) {
        this.other = other;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final SerializedSubscriber serializedSubscriber = new SerializedSubscriber((Subscriber<? super T>)subscriber);
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        final Subscriber<U> subscriber2 = new Subscriber<U>() {
            @Override
            public void onCompleted() {
                this.unsubscribe();
            }
            
            @Override
            public void onError(final Throwable t) {
                serializedSubscriber.onError(t);
                serializedSubscriber.unsubscribe();
            }
            
            @Override
            public void onNext(final U u) {
                atomicBoolean.set(true);
                this.unsubscribe();
            }
        };
        subscriber.add(subscriber2);
        this.other.unsafeSubscribe(subscriber2);
        return new Subscriber<T>(subscriber) {
            @Override
            public void onCompleted() {
                serializedSubscriber.onCompleted();
                this.unsubscribe();
            }
            
            @Override
            public void onError(final Throwable t) {
                serializedSubscriber.onError(t);
                this.unsubscribe();
            }
            
            @Override
            public void onNext(final T t) {
                if (atomicBoolean.get()) {
                    serializedSubscriber.onNext(t);
                    return;
                }
                this.request(1L);
            }
        };
    }
}
