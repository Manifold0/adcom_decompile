// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.exceptions.Exceptions;
import rx.plugins.RxJavaHooks;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.NoSuchElementException;
import rx.SingleSubscriber;
import rx.functions.FuncN;
import rx.Single;

public final class SingleOperatorZip
{
    private SingleOperatorZip() {
        throw new IllegalStateException("No instances!");
    }
    
    public static <T, R> Single<R> zip(final Single<? extends T>[] array, final FuncN<? extends R> funcN) {
        return Single.create((Single.OnSubscribe<R>)new Single.OnSubscribe<R>() {
            @Override
            public void call(final SingleSubscriber<? super R> singleSubscriber) {
                if (array.length == 0) {
                    singleSubscriber.onError(new NoSuchElementException("Can't zip 0 Singles."));
                }
                else {
                    final AtomicInteger atomicInteger = new AtomicInteger(array.length);
                    final AtomicBoolean atomicBoolean = new AtomicBoolean();
                    final Object[] array = new Object[array.length];
                    final CompositeSubscription compositeSubscription = new CompositeSubscription();
                    singleSubscriber.add(compositeSubscription);
                    for (int n = 0; n < array.length && !compositeSubscription.isUnsubscribed() && !atomicBoolean.get(); ++n) {
                        final SingleSubscriber<T> singleSubscriber2 = new SingleSubscriber<T>() {
                            @Override
                            public void onError(final Throwable t) {
                                if (atomicBoolean.compareAndSet(false, true)) {
                                    singleSubscriber.onError(t);
                                    return;
                                }
                                RxJavaHooks.onError(t);
                            }
                            
                            @Override
                            public void onSuccess(final T t) {
                                array[n] = t;
                                if (atomicInteger.decrementAndGet() != 0) {
                                    return;
                                }
                                try {
                                    singleSubscriber.onSuccess(funcN.call(array));
                                }
                                catch (Throwable t2) {
                                    Exceptions.throwIfFatal(t2);
                                    this.onError(t2);
                                }
                            }
                        };
                        compositeSubscription.add(singleSubscriber2);
                        if (compositeSubscription.isUnsubscribed() || atomicBoolean.get()) {
                            break;
                        }
                        array[n].subscribe(singleSubscriber2);
                    }
                }
            }
        });
    }
}
