// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Subscription;
import java.util.concurrent.atomic.AtomicReference;
import rx.observers.SerializedSubscriber;
import rx.Subscriber;
import rx.Observable;

public final class OperatorSampleWithObservable<T, U> implements Operator<T, T>
{
    static final Object EMPTY_TOKEN;
    final Observable<U> sampler;
    
    static {
        EMPTY_TOKEN = new Object();
    }
    
    public OperatorSampleWithObservable(final Observable<U> sampler) {
        this.sampler = sampler;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final SerializedSubscriber serializedSubscriber = new SerializedSubscriber((Subscriber<? super T>)subscriber);
        final AtomicReference atomicReference = new AtomicReference((V)OperatorSampleWithObservable.EMPTY_TOKEN);
        final AtomicReference<OperatorSampleWithObservable$2> atomicReference2 = new AtomicReference<OperatorSampleWithObservable$2>();
        final Subscriber<U> subscriber2 = new Subscriber<U>() {
            @Override
            public void onCompleted() {
                this.onNext(null);
                serializedSubscriber.onCompleted();
                atomicReference2.get().unsubscribe();
            }
            
            @Override
            public void onError(final Throwable t) {
                serializedSubscriber.onError(t);
                atomicReference2.get().unsubscribe();
            }
            
            @Override
            public void onNext(final U u) {
                final Object andSet = atomicReference.getAndSet(OperatorSampleWithObservable.EMPTY_TOKEN);
                if (andSet != OperatorSampleWithObservable.EMPTY_TOKEN) {
                    serializedSubscriber.onNext(andSet);
                }
            }
        };
        final Subscriber<T> subscriber3 = new Subscriber<T>() {
            @Override
            public void onCompleted() {
                subscriber2.onNext(null);
                serializedSubscriber.onCompleted();
                subscriber2.unsubscribe();
            }
            
            @Override
            public void onError(final Throwable t) {
                serializedSubscriber.onError(t);
                subscriber2.unsubscribe();
            }
            
            @Override
            public void onNext(final T t) {
                atomicReference.set(t);
            }
        };
        atomicReference2.lazySet(subscriber3);
        subscriber.add(subscriber3);
        subscriber.add(subscriber2);
        this.sampler.unsafeSubscribe(subscriber2);
        return subscriber3;
    }
}
