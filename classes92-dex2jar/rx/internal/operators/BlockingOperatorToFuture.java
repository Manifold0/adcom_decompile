// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import rx.Subscription;
import rx.Subscriber;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import rx.Observable;

public final class BlockingOperatorToFuture
{
    private BlockingOperatorToFuture() {
        throw new IllegalStateException("No instances!");
    }
    
    public static <T> Future<T> toFuture(final Observable<? extends T> observable) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference atomicReference = new AtomicReference();
        final AtomicReference atomicReference2 = new AtomicReference();
        return new Future<T>() {
            private volatile boolean cancelled;
            final /* synthetic */ Subscription val$s = observable.single().subscribe(new Subscriber<T>(countDownLatch, atomicReference2, atomicReference) {
                final /* synthetic */ AtomicReference val$error;
                final /* synthetic */ CountDownLatch val$finished;
                final /* synthetic */ AtomicReference val$value;
                
                @Override
                public void onCompleted() {
                    this.val$finished.countDown();
                }
                
                @Override
                public void onError(final Throwable t) {
                    this.val$error.compareAndSet(null, t);
                    this.val$finished.countDown();
                }
                
                @Override
                public void onNext(final T t) {
                    this.val$value.set(t);
                }
            });
            
            private T getValue() throws ExecutionException {
                final Throwable t = atomicReference2.get();
                if (t != null) {
                    throw new ExecutionException("Observable onError", t);
                }
                if (this.cancelled) {
                    throw new CancellationException("Subscription unsubscribed");
                }
                return atomicReference.get();
            }
            
            @Override
            public boolean cancel(final boolean b) {
                if (countDownLatch.getCount() > 0L) {
                    this.cancelled = true;
                    this.val$s.unsubscribe();
                    countDownLatch.countDown();
                    return true;
                }
                return false;
            }
            
            @Override
            public T get() throws InterruptedException, ExecutionException {
                countDownLatch.await();
                return this.getValue();
            }
            
            @Override
            public T get(final long n, final TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
                if (countDownLatch.await(n, timeUnit)) {
                    return this.getValue();
                }
                throw new TimeoutException("Timed out after " + timeUnit.toMillis(n) + "ms waiting for underlying Observable.");
            }
            
            @Override
            public boolean isCancelled() {
                return this.cancelled;
            }
            
            @Override
            public boolean isDone() {
                return countDownLatch.getCount() == 0L;
            }
        };
    }
}
