// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Observer;
import rx.exceptions.Exceptions;
import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;
import rx.observers.SerializedSubscriber;
import rx.Subscriber;
import rx.functions.Func2;
import rx.Observable;

public final class OperatorWithLatestFrom<T, U, R> implements Operator<R, T>
{
    static final Object EMPTY;
    final Observable<? extends U> other;
    final Func2<? super T, ? super U, ? extends R> resultSelector;
    
    static {
        EMPTY = new Object();
    }
    
    public OperatorWithLatestFrom(final Observable<? extends U> other, final Func2<? super T, ? super U, ? extends R> resultSelector) {
        this.other = other;
        this.resultSelector = resultSelector;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super R> subscriber) {
        final SerializedSubscriber serializedSubscriber = new SerializedSubscriber((Subscriber<? super Object>)subscriber, false);
        subscriber.add(serializedSubscriber);
        final AtomicReference atomicReference = new AtomicReference((V)OperatorWithLatestFrom.EMPTY);
        final Subscriber<T> subscriber2 = new Subscriber<T>(serializedSubscriber, true) {
            @Override
            public void onCompleted() {
                serializedSubscriber.onCompleted();
                serializedSubscriber.unsubscribe();
            }
            
            @Override
            public void onError(final Throwable t) {
                serializedSubscriber.onError(t);
                serializedSubscriber.unsubscribe();
            }
            
            @Override
            public void onNext(final T t) {
                final Object value = atomicReference.get();
                if (value == OperatorWithLatestFrom.EMPTY) {
                    return;
                }
                try {
                    serializedSubscriber.onNext(OperatorWithLatestFrom.this.resultSelector.call((Object)t, (Object)value));
                }
                catch (Throwable t2) {
                    Exceptions.throwOrReport(t2, this);
                }
            }
        };
        final Subscriber<U> subscriber3 = new Subscriber<U>() {
            @Override
            public void onCompleted() {
                if (atomicReference.get() == OperatorWithLatestFrom.EMPTY) {
                    serializedSubscriber.onCompleted();
                    serializedSubscriber.unsubscribe();
                }
            }
            
            @Override
            public void onError(final Throwable t) {
                serializedSubscriber.onError(t);
                serializedSubscriber.unsubscribe();
            }
            
            @Override
            public void onNext(final U u) {
                atomicReference.set(u);
            }
        };
        serializedSubscriber.add(subscriber2);
        serializedSubscriber.add(subscriber3);
        this.other.unsafeSubscribe(subscriber3);
        return subscriber2;
    }
}
