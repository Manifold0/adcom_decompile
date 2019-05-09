// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.exceptions.Exceptions;
import rx.plugins.RxJavaHooks;
import java.util.NoSuchElementException;
import rx.Producer;
import rx.Subscription;
import rx.Subscriber;
import rx.functions.Func2;
import rx.Observable;

public final class OnSubscribeReduce<T> implements OnSubscribe<T>
{
    final Func2<T, T, T> reducer;
    final Observable<T> source;
    
    public OnSubscribeReduce(final Observable<T> source, final Func2<T, T, T> reducer) {
        this.source = source;
        this.reducer = reducer;
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        final ReduceSubscriber reduceSubscriber = (ReduceSubscriber)new ReduceSubscriber<Object>((Subscriber<? super T>)subscriber, (Func2<T, T, T>)this.reducer);
        subscriber.add(reduceSubscriber);
        subscriber.setProducer(new Producer() {
            @Override
            public void request(final long n) {
                reduceSubscriber.downstreamRequest(n);
            }
        });
        this.source.unsafeSubscribe(reduceSubscriber);
    }
    
    static final class ReduceSubscriber<T> extends Subscriber<T>
    {
        static final Object EMPTY;
        final Subscriber<? super T> actual;
        boolean done;
        final Func2<T, T, T> reducer;
        T value;
        
        static {
            EMPTY = new Object();
        }
        
        public ReduceSubscriber(final Subscriber<? super T> actual, final Func2<T, T, T> reducer) {
            this.actual = actual;
            this.reducer = reducer;
            this.value = (T)ReduceSubscriber.EMPTY;
            this.request(0L);
        }
        
        void downstreamRequest(final long n) {
            if (n < 0L) {
                throw new IllegalArgumentException("n >= 0 required but it was " + n);
            }
            if (n != 0L) {
                this.request(Long.MAX_VALUE);
            }
        }
        
        @Override
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            final T value = this.value;
            if (value != ReduceSubscriber.EMPTY) {
                this.actual.onNext((Object)value);
                this.actual.onCompleted();
                return;
            }
            this.actual.onError(new NoSuchElementException());
        }
        
        @Override
        public void onError(final Throwable t) {
            if (!this.done) {
                this.done = true;
                this.actual.onError(t);
                return;
            }
            RxJavaHooks.onError(t);
        }
        
        @Override
        public void onNext(final T value) {
            if (this.done) {
                return;
            }
            final T value2 = this.value;
            if (value2 == ReduceSubscriber.EMPTY) {
                this.value = value;
                return;
            }
            try {
                this.value = this.reducer.call(value2, value);
            }
            catch (Throwable t) {
                Exceptions.throwIfFatal(t);
                this.unsubscribe();
                this.onError(t);
            }
        }
    }
}
