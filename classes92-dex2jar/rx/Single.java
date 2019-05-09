// 
// Decompiled by Procyon v0.5.34
// 

package rx;

import rx.singles.BlockingSingle;
import rx.internal.operators.OperatorTimeout;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.CancellationException;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.Subscriptions;
import rx.observers.SafeSubscriber;
import rx.exceptions.OnErrorNotImplementedException;
import rx.internal.operators.OperatorOnErrorResumeNextViaFunction;
import rx.internal.operators.SingleOperatorOnErrorResumeNext;
import rx.internal.operators.OperatorObserveOn;
import rx.internal.operators.SingleOnSubscribeMap;
import rx.internal.operators.CompletableFlatMapSingleToCompletable;
import rx.internal.operators.OperatorDoOnUnsubscribe;
import rx.internal.operators.OperatorDoOnSubscribe;
import rx.functions.Actions;
import rx.annotations.Experimental;
import rx.internal.operators.SingleDoOnEvent;
import rx.internal.operators.SingleDoAfterTerminate;
import rx.functions.Action0;
import rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther;
import rx.internal.operators.OperatorDelay;
import rx.schedulers.Schedulers;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.functions.Func5;
import rx.functions.Func6;
import rx.functions.Func7;
import rx.functions.Func8;
import rx.functions.Func9;
import rx.internal.operators.SingleOperatorZip;
import rx.functions.FuncN;
import rx.internal.operators.SingleOnSubscribeUsing;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.internal.util.UtilityFunctions;
import rx.internal.util.ScalarSynchronousSingle;
import java.util.Iterator;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import rx.internal.operators.OnSubscribeToObservableFuture;
import java.util.concurrent.Future;
import rx.annotations.Beta;
import rx.exceptions.Exceptions;
import java.util.concurrent.Callable;
import rx.internal.producers.SingleDelayedProducer;
import rx.plugins.RxJavaHooks;

public class Single<T>
{
    final Observable.OnSubscribe<T> onSubscribe;
    
    private Single(final Observable.OnSubscribe<T> onSubscribe) {
        this.onSubscribe = RxJavaHooks.onCreate(onSubscribe);
    }
    
    protected Single(final OnSubscribe<T> onSubscribe) {
        this.onSubscribe = new Observable.OnSubscribe<T>() {
            final /* synthetic */ Single.OnSubscribe val$g = RxJavaHooks.onCreate((Single.OnSubscribe<Object>)onSubscribe);
            
            @Override
            public void call(final Subscriber<? super T> subscriber) {
                final SingleDelayedProducer producer = new SingleDelayedProducer((Subscriber<? super T>)subscriber);
                subscriber.setProducer(producer);
                final SingleSubscriber<T> singleSubscriber = new SingleSubscriber<T>() {
                    @Override
                    public void onError(final Throwable t) {
                        subscriber.onError(t);
                    }
                    
                    @Override
                    public void onSuccess(final T value) {
                        producer.setValue(value);
                    }
                };
                subscriber.add(singleSubscriber);
                this.val$g.call(singleSubscriber);
            }
        };
    }
    
    private static <T> Observable<T> asObservable(final Single<T> single) {
        return Observable.create(single.onSubscribe);
    }
    
    public static <T> Observable<T> concat(final Single<? extends T> single, final Single<? extends T> single2) {
        return Observable.concat((Observable<? extends T>)asObservable((Single<? extends T>)single), (Observable<? extends T>)asObservable((Single<? extends T>)single2));
    }
    
    public static <T> Observable<T> concat(final Single<? extends T> single, final Single<? extends T> single2, final Single<? extends T> single3) {
        return Observable.concat((Observable<? extends T>)asObservable((Single<? extends T>)single), (Observable<? extends T>)asObservable((Single<? extends T>)single2), (Observable<? extends T>)asObservable((Single<? extends T>)single3));
    }
    
    public static <T> Observable<T> concat(final Single<? extends T> single, final Single<? extends T> single2, final Single<? extends T> single3, final Single<? extends T> single4) {
        return Observable.concat((Observable<? extends T>)asObservable((Single<? extends T>)single), (Observable<? extends T>)asObservable((Single<? extends T>)single2), (Observable<? extends T>)asObservable((Single<? extends T>)single3), (Observable<? extends T>)asObservable((Single<? extends T>)single4));
    }
    
    public static <T> Observable<T> concat(final Single<? extends T> single, final Single<? extends T> single2, final Single<? extends T> single3, final Single<? extends T> single4, final Single<? extends T> single5) {
        return Observable.concat((Observable<? extends T>)asObservable((Single<? extends T>)single), (Observable<? extends T>)asObservable((Single<? extends T>)single2), (Observable<? extends T>)asObservable((Single<? extends T>)single3), (Observable<? extends T>)asObservable((Single<? extends T>)single4), (Observable<? extends T>)asObservable((Single<? extends T>)single5));
    }
    
    public static <T> Observable<T> concat(final Single<? extends T> single, final Single<? extends T> single2, final Single<? extends T> single3, final Single<? extends T> single4, final Single<? extends T> single5, final Single<? extends T> single6) {
        return Observable.concat((Observable<? extends T>)asObservable((Single<? extends T>)single), (Observable<? extends T>)asObservable((Single<? extends T>)single2), (Observable<? extends T>)asObservable((Single<? extends T>)single3), (Observable<? extends T>)asObservable((Single<? extends T>)single4), (Observable<? extends T>)asObservable((Single<? extends T>)single5), (Observable<? extends T>)asObservable((Single<? extends T>)single6));
    }
    
    public static <T> Observable<T> concat(final Single<? extends T> single, final Single<? extends T> single2, final Single<? extends T> single3, final Single<? extends T> single4, final Single<? extends T> single5, final Single<? extends T> single6, final Single<? extends T> single7) {
        return Observable.concat((Observable<? extends T>)asObservable((Single<? extends T>)single), (Observable<? extends T>)asObservable((Single<? extends T>)single2), (Observable<? extends T>)asObservable((Single<? extends T>)single3), (Observable<? extends T>)asObservable((Single<? extends T>)single4), (Observable<? extends T>)asObservable((Single<? extends T>)single5), (Observable<? extends T>)asObservable((Single<? extends T>)single6), (Observable<? extends T>)asObservable((Single<? extends T>)single7));
    }
    
    public static <T> Observable<T> concat(final Single<? extends T> single, final Single<? extends T> single2, final Single<? extends T> single3, final Single<? extends T> single4, final Single<? extends T> single5, final Single<? extends T> single6, final Single<? extends T> single7, final Single<? extends T> single8) {
        return Observable.concat((Observable<? extends T>)asObservable((Single<? extends T>)single), (Observable<? extends T>)asObservable((Single<? extends T>)single2), (Observable<? extends T>)asObservable((Single<? extends T>)single3), (Observable<? extends T>)asObservable((Single<? extends T>)single4), (Observable<? extends T>)asObservable((Single<? extends T>)single5), (Observable<? extends T>)asObservable((Single<? extends T>)single6), (Observable<? extends T>)asObservable((Single<? extends T>)single7), (Observable<? extends T>)asObservable((Single<? extends T>)single8));
    }
    
    public static <T> Observable<T> concat(final Single<? extends T> single, final Single<? extends T> single2, final Single<? extends T> single3, final Single<? extends T> single4, final Single<? extends T> single5, final Single<? extends T> single6, final Single<? extends T> single7, final Single<? extends T> single8, final Single<? extends T> single9) {
        return Observable.concat((Observable<? extends T>)asObservable((Single<? extends T>)single), (Observable<? extends T>)asObservable((Single<? extends T>)single2), (Observable<? extends T>)asObservable((Single<? extends T>)single3), (Observable<? extends T>)asObservable((Single<? extends T>)single4), (Observable<? extends T>)asObservable((Single<? extends T>)single5), (Observable<? extends T>)asObservable((Single<? extends T>)single6), (Observable<? extends T>)asObservable((Single<? extends T>)single7), (Observable<? extends T>)asObservable((Single<? extends T>)single8), (Observable<? extends T>)asObservable((Single<? extends T>)single9));
    }
    
    public static <T> Single<T> create(final OnSubscribe<T> onSubscribe) {
        return new Single<T>(onSubscribe);
    }
    
    @Beta
    public static <T> Single<T> defer(final Callable<Single<T>> callable) {
        return create((OnSubscribe<T>)new OnSubscribe<T>() {
            @Override
            public void call(final SingleSubscriber<? super T> singleSubscriber) {
                try {
                    callable.call().subscribe((SingleSubscriber<? super Object>)singleSubscriber);
                }
                catch (Throwable t) {
                    Exceptions.throwIfFatal(t);
                    singleSubscriber.onError(t);
                }
            }
        });
    }
    
    public static <T> Single<T> error(final Throwable t) {
        return create((OnSubscribe<T>)new OnSubscribe<T>() {
            @Override
            public void call(final SingleSubscriber<? super T> singleSubscriber) {
                singleSubscriber.onError(t);
            }
        });
    }
    
    public static <T> Single<T> from(final Future<? extends T> future) {
        return new Single<T>(OnSubscribeToObservableFuture.toObservableFuture(future));
    }
    
    public static <T> Single<T> from(final Future<? extends T> future, final long n, final TimeUnit timeUnit) {
        return new Single<T>(OnSubscribeToObservableFuture.toObservableFuture(future, n, timeUnit));
    }
    
    public static <T> Single<T> from(final Future<? extends T> future, final Scheduler scheduler) {
        return new Single<T>(OnSubscribeToObservableFuture.toObservableFuture(future)).subscribeOn(scheduler);
    }
    
    public static <T> Single<T> fromCallable(final Callable<? extends T> callable) {
        return create((OnSubscribe<T>)new OnSubscribe<T>() {
            @Override
            public void call(final SingleSubscriber<? super T> singleSubscriber) {
                try {
                    singleSubscriber.onSuccess(callable.call());
                }
                catch (Throwable t) {
                    Exceptions.throwIfFatal(t);
                    singleSubscriber.onError(t);
                }
            }
        });
    }
    
    static <T> Single<? extends T>[] iterableToArray(final Iterable<? extends Single<? extends T>> iterable) {
        if (iterable instanceof Collection) {
            final Collection<Single> collection = (Collection<Single>)iterable;
            return collection.toArray(new Single[collection.size()]);
        }
        final Single[] array = new Single[8];
        int n = 0;
        final Iterator<Single<? extends T>> iterator = iterable.iterator();
        Single<? extends T>[] array2 = (Single<? extends T>[])array;
        while (iterator.hasNext()) {
            final Single<? extends T> single = iterator.next();
            Single<? extends T>[] array3 = array2;
            if (n == array2.length) {
                array3 = (Single<? extends T>[])new Single[(n >> 2) + n];
                System.arraycopy(array2, 0, array3, 0, n);
            }
            array3[n] = single;
            ++n;
            array2 = array3;
        }
        if (array2.length == n) {
            return array2;
        }
        final Single[] array4 = new Single[n];
        System.arraycopy(array2, 0, array4, 0, n);
        return (Single<? extends T>[])array4;
    }
    
    public static <T> Single<T> just(final T t) {
        return ScalarSynchronousSingle.create(t);
    }
    
    public static <T> Observable<T> merge(final Single<? extends T> single, final Single<? extends T> single2) {
        return Observable.merge((Observable<? extends T>)asObservable((Single<? extends T>)single), (Observable<? extends T>)asObservable((Single<? extends T>)single2));
    }
    
    public static <T> Observable<T> merge(final Single<? extends T> single, final Single<? extends T> single2, final Single<? extends T> single3) {
        return Observable.merge((Observable<? extends T>)asObservable((Single<? extends T>)single), (Observable<? extends T>)asObservable((Single<? extends T>)single2), (Observable<? extends T>)asObservable((Single<? extends T>)single3));
    }
    
    public static <T> Observable<T> merge(final Single<? extends T> single, final Single<? extends T> single2, final Single<? extends T> single3, final Single<? extends T> single4) {
        return Observable.merge((Observable<? extends T>)asObservable((Single<? extends T>)single), (Observable<? extends T>)asObservable((Single<? extends T>)single2), (Observable<? extends T>)asObservable((Single<? extends T>)single3), (Observable<? extends T>)asObservable((Single<? extends T>)single4));
    }
    
    public static <T> Observable<T> merge(final Single<? extends T> single, final Single<? extends T> single2, final Single<? extends T> single3, final Single<? extends T> single4, final Single<? extends T> single5) {
        return Observable.merge((Observable<? extends T>)asObservable((Single<? extends T>)single), (Observable<? extends T>)asObservable((Single<? extends T>)single2), (Observable<? extends T>)asObservable((Single<? extends T>)single3), (Observable<? extends T>)asObservable((Single<? extends T>)single4), (Observable<? extends T>)asObservable((Single<? extends T>)single5));
    }
    
    public static <T> Observable<T> merge(final Single<? extends T> single, final Single<? extends T> single2, final Single<? extends T> single3, final Single<? extends T> single4, final Single<? extends T> single5, final Single<? extends T> single6) {
        return Observable.merge((Observable<? extends T>)asObservable((Single<? extends T>)single), (Observable<? extends T>)asObservable((Single<? extends T>)single2), (Observable<? extends T>)asObservable((Single<? extends T>)single3), (Observable<? extends T>)asObservable((Single<? extends T>)single4), (Observable<? extends T>)asObservable((Single<? extends T>)single5), (Observable<? extends T>)asObservable((Single<? extends T>)single6));
    }
    
    public static <T> Observable<T> merge(final Single<? extends T> single, final Single<? extends T> single2, final Single<? extends T> single3, final Single<? extends T> single4, final Single<? extends T> single5, final Single<? extends T> single6, final Single<? extends T> single7) {
        return Observable.merge((Observable<? extends T>)asObservable((Single<? extends T>)single), (Observable<? extends T>)asObservable((Single<? extends T>)single2), (Observable<? extends T>)asObservable((Single<? extends T>)single3), (Observable<? extends T>)asObservable((Single<? extends T>)single4), (Observable<? extends T>)asObservable((Single<? extends T>)single5), (Observable<? extends T>)asObservable((Single<? extends T>)single6), (Observable<? extends T>)asObservable((Single<? extends T>)single7));
    }
    
    public static <T> Observable<T> merge(final Single<? extends T> single, final Single<? extends T> single2, final Single<? extends T> single3, final Single<? extends T> single4, final Single<? extends T> single5, final Single<? extends T> single6, final Single<? extends T> single7, final Single<? extends T> single8) {
        return Observable.merge((Observable<? extends T>)asObservable((Single<? extends T>)single), (Observable<? extends T>)asObservable((Single<? extends T>)single2), (Observable<? extends T>)asObservable((Single<? extends T>)single3), (Observable<? extends T>)asObservable((Single<? extends T>)single4), (Observable<? extends T>)asObservable((Single<? extends T>)single5), (Observable<? extends T>)asObservable((Single<? extends T>)single6), (Observable<? extends T>)asObservable((Single<? extends T>)single7), (Observable<? extends T>)asObservable((Single<? extends T>)single8));
    }
    
    public static <T> Observable<T> merge(final Single<? extends T> single, final Single<? extends T> single2, final Single<? extends T> single3, final Single<? extends T> single4, final Single<? extends T> single5, final Single<? extends T> single6, final Single<? extends T> single7, final Single<? extends T> single8, final Single<? extends T> single9) {
        return Observable.merge((Observable<? extends T>)asObservable((Single<? extends T>)single), (Observable<? extends T>)asObservable((Single<? extends T>)single2), (Observable<? extends T>)asObservable((Single<? extends T>)single3), (Observable<? extends T>)asObservable((Single<? extends T>)single4), (Observable<? extends T>)asObservable((Single<? extends T>)single5), (Observable<? extends T>)asObservable((Single<? extends T>)single6), (Observable<? extends T>)asObservable((Single<? extends T>)single7), (Observable<? extends T>)asObservable((Single<? extends T>)single8), (Observable<? extends T>)asObservable((Single<? extends T>)single9));
    }
    
    public static <T> Single<T> merge(final Single<? extends Single<? extends T>> single) {
        if (single instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle<Object>)single).scalarFlatMap((Func1<? super Object, ? extends Single<? extends T>>)UtilityFunctions.identity());
        }
        return create((OnSubscribe<T>)new OnSubscribe<T>() {
            @Override
            public void call(final SingleSubscriber<? super T> singleSubscriber) {
                final SingleSubscriber<Single<? extends T>> singleSubscriber2 = new SingleSubscriber<Single<? extends T>>() {
                    @Override
                    public void onError(final Throwable t) {
                        singleSubscriber.onError(t);
                    }
                    
                    @Override
                    public void onSuccess(final Single<? extends T> single) {
                        single.subscribe(singleSubscriber);
                    }
                };
                singleSubscriber.add(singleSubscriber2);
                single.subscribe(singleSubscriber2);
            }
        });
    }
    
    @Beta
    public static <T, Resource> Single<T> using(final Func0<Resource> func0, final Func1<? super Resource, ? extends Single<? extends T>> func2, final Action1<? super Resource> action1) {
        return using(func0, func2, action1, false);
    }
    
    @Beta
    public static <T, Resource> Single<T> using(final Func0<Resource> func0, final Func1<? super Resource, ? extends Single<? extends T>> func2, final Action1<? super Resource> action1, final boolean b) {
        if (func0 == null) {
            throw new NullPointerException("resourceFactory is null");
        }
        if (func2 == null) {
            throw new NullPointerException("singleFactory is null");
        }
        if (action1 == null) {
            throw new NullPointerException("disposeAction is null");
        }
        return create((OnSubscribe<T>)new SingleOnSubscribeUsing((Func0<Object>)func0, (Func1<? super Object, ? extends Single<?>>)func2, (Action1<? super Object>)action1, b));
    }
    
    public static <R> Single<R> zip(final Iterable<? extends Single<?>> iterable, final FuncN<? extends R> funcN) {
        return SingleOperatorZip.zip(iterableToArray(iterable), funcN);
    }
    
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Single<R> zip(final Single<? extends T1> single, final Single<? extends T2> single2, final Single<? extends T3> single3, final Single<? extends T4> single4, final Single<? extends T5> single5, final Single<? extends T6> single6, final Single<? extends T7> single7, final Single<? extends T8> single8, final Single<? extends T9> single9, final Func9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> func9) {
        return SingleOperatorZip.zip((Single<?>[])new Single[] { single, single2, single3, single4, single5, single6, single7, single8, single9 }, (FuncN<? extends R>)new FuncN<R>() {
            @Override
            public R call(final Object... array) {
                return func9.call(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7], array[8]);
            }
        });
    }
    
    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Single<R> zip(final Single<? extends T1> single, final Single<? extends T2> single2, final Single<? extends T3> single3, final Single<? extends T4> single4, final Single<? extends T5> single5, final Single<? extends T6> single6, final Single<? extends T7> single7, final Single<? extends T8> single8, final Func8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> func8) {
        return SingleOperatorZip.zip((Single<?>[])new Single[] { single, single2, single3, single4, single5, single6, single7, single8 }, (FuncN<? extends R>)new FuncN<R>() {
            @Override
            public R call(final Object... array) {
                return func8.call(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7]);
            }
        });
    }
    
    public static <T1, T2, T3, T4, T5, T6, T7, R> Single<R> zip(final Single<? extends T1> single, final Single<? extends T2> single2, final Single<? extends T3> single3, final Single<? extends T4> single4, final Single<? extends T5> single5, final Single<? extends T6> single6, final Single<? extends T7> single7, final Func7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> func7) {
        return SingleOperatorZip.zip((Single<?>[])new Single[] { single, single2, single3, single4, single5, single6, single7 }, (FuncN<? extends R>)new FuncN<R>() {
            @Override
            public R call(final Object... array) {
                return func7.call(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
            }
        });
    }
    
    public static <T1, T2, T3, T4, T5, T6, R> Single<R> zip(final Single<? extends T1> single, final Single<? extends T2> single2, final Single<? extends T3> single3, final Single<? extends T4> single4, final Single<? extends T5> single5, final Single<? extends T6> single6, final Func6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> func6) {
        return SingleOperatorZip.zip((Single<?>[])new Single[] { single, single2, single3, single4, single5, single6 }, (FuncN<? extends R>)new FuncN<R>() {
            @Override
            public R call(final Object... array) {
                return func6.call(array[0], array[1], array[2], array[3], array[4], array[5]);
            }
        });
    }
    
    public static <T1, T2, T3, T4, T5, R> Single<R> zip(final Single<? extends T1> single, final Single<? extends T2> single2, final Single<? extends T3> single3, final Single<? extends T4> single4, final Single<? extends T5> single5, final Func5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> func5) {
        return SingleOperatorZip.zip((Single<?>[])new Single[] { single, single2, single3, single4, single5 }, (FuncN<? extends R>)new FuncN<R>() {
            @Override
            public R call(final Object... array) {
                return func5.call(array[0], array[1], array[2], array[3], array[4]);
            }
        });
    }
    
    public static <T1, T2, T3, T4, R> Single<R> zip(final Single<? extends T1> single, final Single<? extends T2> single2, final Single<? extends T3> single3, final Single<? extends T4> single4, final Func4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> func4) {
        return SingleOperatorZip.zip((Single<?>[])new Single[] { single, single2, single3, single4 }, (FuncN<? extends R>)new FuncN<R>() {
            @Override
            public R call(final Object... array) {
                return func4.call(array[0], array[1], array[2], array[3]);
            }
        });
    }
    
    public static <T1, T2, T3, R> Single<R> zip(final Single<? extends T1> single, final Single<? extends T2> single2, final Single<? extends T3> single3, final Func3<? super T1, ? super T2, ? super T3, ? extends R> func3) {
        return SingleOperatorZip.zip((Single<?>[])new Single[] { single, single2, single3 }, (FuncN<? extends R>)new FuncN<R>() {
            @Override
            public R call(final Object... array) {
                return func3.call(array[0], array[1], array[2]);
            }
        });
    }
    
    public static <T1, T2, R> Single<R> zip(final Single<? extends T1> single, final Single<? extends T2> single2, final Func2<? super T1, ? super T2, ? extends R> func2) {
        return SingleOperatorZip.zip((Single<?>[])new Single[] { single, single2 }, (FuncN<? extends R>)new FuncN<R>() {
            @Override
            public R call(final Object... array) {
                return func2.call(array[0], array[1]);
            }
        });
    }
    
    public <R> Single<R> compose(final Transformer<? super T, ? extends R> transformer) {
        return transformer.call(this);
    }
    
    public final Observable<T> concatWith(final Single<? extends T> single) {
        return concat((Single<? extends T>)this, single);
    }
    
    @Beta
    public final Single<T> delay(final long n, final TimeUnit timeUnit) {
        return this.delay(n, timeUnit, Schedulers.computation());
    }
    
    @Beta
    public final Single<T> delay(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.lift((Observable.Operator<? extends T, ? super T>)new OperatorDelay(n, timeUnit, scheduler));
    }
    
    @Beta
    public final Single<T> delaySubscription(final Observable<?> observable) {
        if (observable == null) {
            throw new NullPointerException();
        }
        return create((OnSubscribe<T>)new SingleOnSubscribeDelaySubscriptionOther(this, observable));
    }
    
    @Beta
    public final Single<T> doAfterTerminate(final Action0 action0) {
        return create((OnSubscribe<T>)new SingleDoAfterTerminate((Single<Object>)this, action0));
    }
    
    @Experimental
    public final Single<T> doOnEach(final Action1<Notification<? extends T>> action1) {
        if (action1 == null) {
            throw new IllegalArgumentException("onNotification is null");
        }
        return create((OnSubscribe<T>)new SingleDoOnEvent((Single<Object>)this, (Action1<? super Object>)new Action1<T>() {
            @Override
            public void call(final T t) {
                action1.call(Notification.createOnNext(t));
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(final Throwable t) {
                action1.call(Notification.createOnError(t));
            }
        }));
    }
    
    @Beta
    public final Single<T> doOnError(final Action1<Throwable> action1) {
        if (action1 == null) {
            throw new IllegalArgumentException("onError is null");
        }
        return create((OnSubscribe<T>)new SingleDoOnEvent((Single<Object>)this, Actions.empty(), new Action1<Throwable>() {
            @Override
            public void call(final Throwable t) {
                action1.call(t);
            }
        }));
    }
    
    @Beta
    public final Single<T> doOnSubscribe(final Action0 action0) {
        return this.lift((Observable.Operator<? extends T, ? super T>)new OperatorDoOnSubscribe(action0));
    }
    
    @Experimental
    public final Single<T> doOnSuccess(final Action1<? super T> action1) {
        if (action1 == null) {
            throw new IllegalArgumentException("onSuccess is null");
        }
        return create((OnSubscribe<T>)new SingleDoOnEvent((Single<Object>)this, (Action1<? super Object>)new Action1<T>() {
            @Override
            public void call(final T t) {
                action1.call(t);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(final Throwable t) {
            }
        }));
    }
    
    @Beta
    public final Single<T> doOnUnsubscribe(final Action0 action0) {
        return this.lift((Observable.Operator<? extends T, ? super T>)new OperatorDoOnUnsubscribe(action0));
    }
    
    public final <R> Single<R> flatMap(final Func1<? super T, ? extends Single<? extends R>> func1) {
        if (this instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle)this).scalarFlatMap(func1);
        }
        return merge(this.map(func1));
    }
    
    @Beta
    public final Completable flatMapCompletable(final Func1<? super T, ? extends Completable> func1) {
        return Completable.create((Completable.OnSubscribe)new CompletableFlatMapSingleToCompletable((Single<Object>)this, (Func1<? super Object, ? extends Completable>)func1));
    }
    
    public final <R> Observable<R> flatMapObservable(final Func1<? super T, ? extends Observable<? extends R>> func1) {
        return Observable.merge((Observable<? extends Observable<? extends R>>)asObservable(this.map((Func1<? super T, ? extends Observable<? extends T>>)func1)));
    }
    
    @Beta
    public final <R> Single<R> lift(final Observable.Operator<? extends R, ? super T> operator) {
        return new Single<R>(new Observable.OnSubscribe<R>() {
            @Override
            public void call(final Subscriber<? super R> subscriber) {
                try {
                    final Subscriber<? super Object> subscriber2 = RxJavaHooks.onSingleLift((Operator<Object, Object>)operator).call((Subscriber<? super Object>)subscriber);
                    try {
                        subscriber2.onStart();
                        Single.this.onSubscribe.call(subscriber2);
                    }
                    catch (Throwable t) {
                        Exceptions.throwOrReport(t, subscriber2);
                    }
                }
                catch (Throwable t2) {
                    Exceptions.throwOrReport(t2, subscriber);
                }
            }
        });
    }
    
    public final <R> Single<R> map(final Func1<? super T, ? extends R> func1) {
        return create((OnSubscribe<R>)new SingleOnSubscribeMap((Single<Object>)this, (Func1<? super Object, ?>)func1));
    }
    
    public final Observable<T> mergeWith(final Single<? extends T> single) {
        return merge((Single<? extends T>)this, single);
    }
    
    public final Single<T> observeOn(final Scheduler scheduler) {
        if (this instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle)this).scalarScheduleOn(scheduler);
        }
        return this.lift((Observable.Operator<? extends T, ? super T>)new OperatorObserveOn(scheduler, false));
    }
    
    @Beta
    public final Single<T> onErrorResumeNext(final Single<? extends T> single) {
        return new Single<T>((OnSubscribe<T>)SingleOperatorOnErrorResumeNext.withOther((Single<?>)this, (Single<?>)single));
    }
    
    @Beta
    public final Single<T> onErrorResumeNext(final Func1<Throwable, ? extends Single<? extends T>> func1) {
        return new Single<T>((OnSubscribe<T>)SingleOperatorOnErrorResumeNext.withFunction((Single<?>)this, (Func1<Throwable, ? extends Single<?>>)func1));
    }
    
    public final Single<T> onErrorReturn(final Func1<Throwable, ? extends T> func1) {
        return this.lift((Observable.Operator<? extends T, ? super T>)OperatorOnErrorResumeNextViaFunction.withSingle((Func1<? super Throwable, ?>)func1));
    }
    
    public final Single<T> retry() {
        return this.toObservable().retry().toSingle();
    }
    
    public final Single<T> retry(final long n) {
        return this.toObservable().retry(n).toSingle();
    }
    
    public final Single<T> retry(final Func2<Integer, Throwable, Boolean> func2) {
        return this.toObservable().retry(func2).toSingle();
    }
    
    public final Single<T> retryWhen(final Func1<Observable<? extends Throwable>, ? extends Observable<?>> func1) {
        return this.toObservable().retryWhen(func1).toSingle();
    }
    
    public final Subscription subscribe() {
        return this.subscribe(new Subscriber<T>() {
            @Override
            public final void onCompleted() {
            }
            
            @Override
            public final void onError(final Throwable t) {
                throw new OnErrorNotImplementedException(t);
            }
            
            @Override
            public final void onNext(final T t) {
            }
        });
    }
    
    public final Subscription subscribe(final Observer<? super T> observer) {
        if (observer == null) {
            throw new NullPointerException("observer is null");
        }
        return this.subscribe(new SingleSubscriber<T>() {
            @Override
            public void onError(final Throwable t) {
                observer.onError(t);
            }
            
            @Override
            public void onSuccess(final T t) {
                observer.onNext(t);
                observer.onCompleted();
            }
        });
    }
    
    public final Subscription subscribe(final SingleSubscriber<? super T> singleSubscriber) {
        final Subscriber<T> subscriber = new Subscriber<T>() {
            @Override
            public void onCompleted() {
            }
            
            @Override
            public void onError(final Throwable t) {
                singleSubscriber.onError(t);
            }
            
            @Override
            public void onNext(final T t) {
                singleSubscriber.onSuccess(t);
            }
        };
        singleSubscriber.add(subscriber);
        this.subscribe(subscriber);
        return subscriber;
    }
    
    public final Subscription subscribe(final Subscriber<? super T> t) {
        if (t == null) {
            throw new IllegalArgumentException("observer can not be null");
        }
        if (this.onSubscribe == null) {
            throw new IllegalStateException("onSubscribe function can not be null.");
        }
        ((Subscriber)t).onStart();
        Subscriber<? super Object> subscriber = (Subscriber<? super Object>)t;
        if (!(t instanceof SafeSubscriber)) {
            subscriber = new SafeSubscriber<Object>((Subscriber<? super Object>)t);
        }
        try {
            RxJavaHooks.onSingleStart(this, this.onSubscribe).call(subscriber);
            return RxJavaHooks.onSingleReturn(subscriber);
        }
        catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            try {
                subscriber.onError(RxJavaHooks.onSingleError(t));
                return Subscriptions.empty();
            }
            catch (Throwable t2) {
                Exceptions.throwIfFatal(t2);
                final RuntimeException ex = new RuntimeException("Error occurred attempting to subscribe [" + t.getMessage() + "] and then again while trying to pass to onError.", t2);
                RxJavaHooks.onSingleError(ex);
                throw ex;
            }
        }
    }
    
    public final Subscription subscribe(final Action1<? super T> action1) {
        if (action1 == null) {
            throw new IllegalArgumentException("onSuccess can not be null");
        }
        return this.subscribe(new Subscriber<T>() {
            @Override
            public final void onCompleted() {
            }
            
            @Override
            public final void onError(final Throwable t) {
                throw new OnErrorNotImplementedException(t);
            }
            
            @Override
            public final void onNext(final T t) {
                action1.call(t);
            }
        });
    }
    
    public final Subscription subscribe(final Action1<? super T> action1, final Action1<Throwable> action2) {
        if (action1 == null) {
            throw new IllegalArgumentException("onSuccess can not be null");
        }
        if (action2 == null) {
            throw new IllegalArgumentException("onError can not be null");
        }
        return this.subscribe(new Subscriber<T>() {
            @Override
            public final void onCompleted() {
            }
            
            @Override
            public final void onError(final Throwable t) {
                action2.call(t);
            }
            
            @Override
            public final void onNext(final T t) {
                action1.call(t);
            }
        });
    }
    
    public final Single<T> subscribeOn(final Scheduler scheduler) {
        if (this instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle)this).scalarScheduleOn(scheduler);
        }
        return create((OnSubscribe<T>)new OnSubscribe<T>() {
            @Override
            public void call(final SingleSubscriber<? super T> singleSubscriber) {
                final Scheduler.Worker worker = scheduler.createWorker();
                singleSubscriber.add(worker);
                worker.schedule(new Action0() {
                    @Override
                    public void call() {
                        final SingleSubscriber<T> singleSubscriber = new SingleSubscriber<T>() {
                            @Override
                            public void onError(final Throwable t) {
                                try {
                                    singleSubscriber.onError(t);
                                }
                                finally {
                                    worker.unsubscribe();
                                }
                            }
                            
                            @Override
                            public void onSuccess(final T t) {
                                try {
                                    singleSubscriber.onSuccess(t);
                                }
                                finally {
                                    worker.unsubscribe();
                                }
                            }
                        };
                        singleSubscriber.add(singleSubscriber);
                        Single.this.subscribe(singleSubscriber);
                    }
                });
            }
        });
    }
    
    public final Single<T> takeUntil(final Completable completable) {
        return this.lift((Observable.Operator<? extends T, ? super T>)new Observable.Operator<T, T>() {
            @Override
            public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
                final SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber, false);
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
                final CompletableSubscriber completableSubscriber = new CompletableSubscriber() {
                    @Override
                    public void onCompleted() {
                        this.onError(new CancellationException("Stream was canceled before emitting a terminal event."));
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        subscriber2.onError(t);
                    }
                    
                    @Override
                    public void onSubscribe(final Subscription subscription) {
                        serializedSubscriber.add(subscription);
                    }
                };
                serializedSubscriber.add(subscriber2);
                subscriber.add(serializedSubscriber);
                completable.unsafeSubscribe(completableSubscriber);
                return subscriber2;
            }
        });
    }
    
    public final <E> Single<T> takeUntil(final Observable<? extends E> observable) {
        return this.lift((Observable.Operator<? extends T, ? super T>)new Observable.Operator<T, T>() {
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
                        this.onError(new CancellationException("Stream was canceled before emitting a terminal event."));
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        subscriber2.onError(t);
                    }
                    
                    @Override
                    public void onNext(final E e) {
                        this.onError(new CancellationException("Stream was canceled before emitting a terminal event."));
                    }
                };
                serializedSubscriber.add(subscriber2);
                serializedSubscriber.add(subscriber3);
                subscriber.add(serializedSubscriber);
                observable.unsafeSubscribe(subscriber3);
                return subscriber2;
            }
        });
    }
    
    public final <E> Single<T> takeUntil(final Single<? extends E> single) {
        return this.lift((Observable.Operator<? extends T, ? super T>)new Observable.Operator<T, T>() {
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
                final SingleSubscriber<E> singleSubscriber = new SingleSubscriber<E>() {
                    @Override
                    public void onError(final Throwable t) {
                        subscriber2.onError(t);
                    }
                    
                    @Override
                    public void onSuccess(final E e) {
                        this.onError(new CancellationException("Stream was canceled before emitting a terminal event."));
                    }
                };
                serializedSubscriber.add(subscriber2);
                serializedSubscriber.add(singleSubscriber);
                subscriber.add(serializedSubscriber);
                single.subscribe(singleSubscriber);
                return subscriber2;
            }
        });
    }
    
    public final Single<T> timeout(final long n, final TimeUnit timeUnit) {
        return this.timeout(n, timeUnit, null, Schedulers.computation());
    }
    
    public final Single<T> timeout(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.timeout(n, timeUnit, null, scheduler);
    }
    
    public final Single<T> timeout(final long n, final TimeUnit timeUnit, final Single<? extends T> single) {
        return this.timeout(n, timeUnit, single, Schedulers.computation());
    }
    
    public final Single<T> timeout(final long n, final TimeUnit timeUnit, final Single<? extends T> single, final Scheduler scheduler) {
        Single<Object> error = (Single<Object>)single;
        if (single == null) {
            error = error(new TimeoutException());
        }
        return (Single<T>)this.lift((Observable.Operator<?, ? super T>)new OperatorTimeout(n, timeUnit, asObservable(error), scheduler));
    }
    
    @Experimental
    public final <R> R to(final Func1<? super Single<T>, R> func1) {
        return func1.call(this);
    }
    
    @Beta
    public final BlockingSingle<T> toBlocking() {
        return BlockingSingle.from((Single<? extends T>)this);
    }
    
    @Beta
    public final Completable toCompletable() {
        return Completable.fromSingle(this);
    }
    
    public final Observable<T> toObservable() {
        return asObservable(this);
    }
    
    public final Subscription unsafeSubscribe(final Subscriber<? super T> subscriber) {
        try {
            subscriber.onStart();
            RxJavaHooks.onSingleStart(this, this.onSubscribe).call(subscriber);
            return RxJavaHooks.onSingleReturn(subscriber);
        }
        catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            try {
                subscriber.onError(RxJavaHooks.onSingleError(t));
                return Subscriptions.unsubscribed();
            }
            catch (Throwable t2) {
                Exceptions.throwIfFatal(t2);
                final RuntimeException ex = new RuntimeException("Error occurred attempting to subscribe [" + t.getMessage() + "] and then again while trying to pass to onError.", t2);
                RxJavaHooks.onSingleError(ex);
                throw ex;
            }
        }
    }
    
    public final <T2, R> Single<R> zipWith(final Single<? extends T2> single, final Func2<? super T, ? super T2, ? extends R> func2) {
        return zip((Single<?>)this, (Single<?>)single, (Func2<? super Object, ? super Object, ? extends R>)func2);
    }
    
    public interface OnSubscribe<T> extends Action1<SingleSubscriber<? super T>>
    {
    }
    
    public interface Transformer<T, R> extends Func1<Single<T>, Single<R>>
    {
    }
}
