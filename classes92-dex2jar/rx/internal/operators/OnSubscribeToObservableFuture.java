// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.Producer;
import rx.internal.producers.SingleProducer;
import rx.subscriptions.Subscriptions;
import rx.functions.Action0;
import rx.Subscriber;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import java.util.concurrent.Future;

public final class OnSubscribeToObservableFuture
{
    private OnSubscribeToObservableFuture() {
        throw new IllegalStateException("No instances!");
    }
    
    public static <T> Observable.OnSubscribe<T> toObservableFuture(final Future<? extends T> future) {
        return new ToObservableFuture<T>(future);
    }
    
    public static <T> Observable.OnSubscribe<T> toObservableFuture(final Future<? extends T> future, final long n, final TimeUnit timeUnit) {
        return new ToObservableFuture<T>(future, n, timeUnit);
    }
    
    static class ToObservableFuture<T> implements OnSubscribe<T>
    {
        final Future<? extends T> that;
        private final long time;
        private final TimeUnit unit;
        
        public ToObservableFuture(final Future<? extends T> that) {
            this.that = that;
            this.time = 0L;
            this.unit = null;
        }
        
        public ToObservableFuture(final Future<? extends T> that, final long time, final TimeUnit unit) {
            this.that = that;
            this.time = time;
            this.unit = unit;
        }
        
        @Override
        public void call(final Subscriber<? super T> subscriber) {
            while (true) {
                subscriber.add(Subscriptions.create(new Action0() {
                    @Override
                    public void call() {
                        ToObservableFuture.this.that.cancel(true);
                    }
                }));
                while (true) {
                    try {
                        if (subscriber.isUnsubscribed()) {
                            return;
                        }
                        if (this.unit == null) {
                            final T t = (T)this.that.get();
                            subscriber.setProducer(new SingleProducer<Object>((Subscriber<? super Object>)subscriber, t));
                            return;
                        }
                    }
                    catch (Throwable t2) {
                        if (!subscriber.isUnsubscribed()) {
                            Exceptions.throwOrReport(t2, subscriber);
                            return;
                        }
                        break;
                    }
                    final T t = (T)this.that.get(this.time, this.unit);
                    continue;
                }
            }
        }
    }
}
