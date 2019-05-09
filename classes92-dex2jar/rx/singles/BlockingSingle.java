// 
// Decompiled by Procyon v0.5.34
// 

package rx.singles;

import rx.exceptions.Exceptions;
import rx.internal.util.BlockingUtils;
import rx.SingleSubscriber;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import rx.internal.operators.BlockingOperatorToFuture;
import java.util.concurrent.Future;
import rx.Single;
import rx.annotations.Beta;

@Beta
public final class BlockingSingle<T>
{
    private final Single<? extends T> single;
    
    private BlockingSingle(final Single<? extends T> single) {
        this.single = single;
    }
    
    public static <T> BlockingSingle<T> from(final Single<? extends T> single) {
        return new BlockingSingle<T>(single);
    }
    
    public Future<T> toFuture() {
        return BlockingOperatorToFuture.toFuture(this.single.toObservable());
    }
    
    public T value() {
        final AtomicReference<T> atomicReference = new AtomicReference<T>();
        final AtomicReference<Throwable> atomicReference2 = new AtomicReference<Throwable>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        BlockingUtils.awaitForComplete(countDownLatch, this.single.subscribe(new SingleSubscriber<T>() {
            @Override
            public void onError(final Throwable t) {
                atomicReference2.set(t);
                countDownLatch.countDown();
            }
            
            @Override
            public void onSuccess(final T t) {
                atomicReference.set(t);
                countDownLatch.countDown();
            }
        }));
        final Throwable t = atomicReference2.get();
        if (t != null) {
            throw Exceptions.propagate(t);
        }
        return atomicReference.get();
    }
}
