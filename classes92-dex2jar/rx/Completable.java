// 
// Decompiled by Procyon v0.5.34
// 

package rx;

import rx.internal.operators.CompletableOnSubscribeTimeout;
import rx.observers.SafeSubscriber;
import rx.observers.SafeCompletableSubscriber;
import rx.functions.Func2;
import rx.subscriptions.SerialSubscription;
import rx.internal.util.UtilityFunctions;
import rx.internal.util.SubscriptionList;
import java.util.concurrent.TimeoutException;
import java.util.Collection;
import rx.exceptions.CompositeException;
import java.util.Arrays;
import rx.functions.Actions;
import java.util.concurrent.CountDownLatch;
import rx.functions.Func1;
import rx.exceptions.Exceptions;
import rx.subscriptions.MultipleAssignmentSubscription;
import rx.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import rx.internal.operators.CompletableOnSubscribeMergeDelayErrorArray;
import rx.internal.operators.CompletableOnSubscribeMergeDelayErrorIterable;
import rx.internal.operators.CompletableOnSubscribeMerge;
import rx.internal.operators.CompletableOnSubscribeMergeArray;
import rx.internal.operators.CompletableOnSubscribeMergeIterable;
import java.util.concurrent.Future;
import rx.annotations.Experimental;
import rx.internal.operators.CompletableFromEmitter;
import rx.functions.Action1;
import java.util.concurrent.Callable;
import rx.subscriptions.BooleanSubscription;
import rx.functions.Action0;
import rx.functions.Func0;
import rx.internal.operators.CompletableOnSubscribeConcatArray;
import rx.internal.operators.CompletableOnSubscribeConcat;
import rx.internal.operators.CompletableOnSubscribeConcatIterable;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.subscriptions.CompositeSubscription;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.Subscriptions;
import rx.annotations.Beta;

@Beta
public class Completable
{
    static final Completable COMPLETE;
    static final Completable NEVER;
    private final OnSubscribe onSubscribe;
    
    static {
        COMPLETE = new Completable((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(final CompletableSubscriber completableSubscriber) {
                completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
                completableSubscriber.onCompleted();
            }
        }, false);
        NEVER = new Completable((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(final CompletableSubscriber completableSubscriber) {
                completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
            }
        }, false);
    }
    
    protected Completable(final OnSubscribe onSubscribe) {
        this.onSubscribe = RxJavaHooks.onCreate(onSubscribe);
    }
    
    protected Completable(final OnSubscribe onSubscribe, final boolean b) {
        Object onCreate = onSubscribe;
        if (b) {
            onCreate = RxJavaHooks.onCreate(onSubscribe);
        }
        this.onSubscribe = (OnSubscribe)onCreate;
    }
    
    public static Completable amb(final Iterable<? extends Completable> iterable) {
        requireNonNull(iterable);
        return create((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(final CompletableSubscriber completableSubscriber) {
                NullPointerException ex = null;
            Label_0259:
                while (true) {
                    final CompositeSubscription compositeSubscription = new CompositeSubscription();
                    completableSubscriber.onSubscribe(compositeSubscription);
                    Iterator iterator;
                    try {
                        iterator = iterable.iterator();
                        if (iterator == null) {
                            completableSubscriber.onError(new NullPointerException("The iterator returned is null"));
                            return;
                        }
                    }
                    catch (Throwable t) {
                        completableSubscriber.onError(t);
                        return;
                    }
                    int n = 1;
                    final AtomicBoolean atomicBoolean = new AtomicBoolean();
                    final CompletableSubscriber completableSubscriber2 = new CompletableSubscriber() {
                        @Override
                        public void onCompleted() {
                            if (atomicBoolean.compareAndSet(false, true)) {
                                compositeSubscription.unsubscribe();
                                completableSubscriber.onCompleted();
                            }
                        }
                        
                        @Override
                        public void onError(final Throwable t) {
                            if (atomicBoolean.compareAndSet(false, true)) {
                                compositeSubscription.unsubscribe();
                                completableSubscriber.onError(t);
                                return;
                            }
                            RxJavaHooks.onError(t);
                        }
                        
                        @Override
                        public void onSubscribe(final Subscription subscription) {
                            compositeSubscription.add(subscription);
                        }
                    };
                    while (!atomicBoolean.get() && !compositeSubscription.isUnsubscribed()) {
                        try {
                            if (!iterator.hasNext()) {
                                if (n != 0) {
                                    completableSubscriber.onCompleted();
                                }
                                return;
                            }
                        }
                        catch (Throwable t2) {
                            if (atomicBoolean.compareAndSet(false, true)) {
                                compositeSubscription.unsubscribe();
                                completableSubscriber.onError(t2);
                                return;
                            }
                            RxJavaHooks.onError(t2);
                            return;
                        }
                        n = 0;
                        if (atomicBoolean.get() || compositeSubscription.isUnsubscribed()) {
                            return;
                        }
                        Completable completable = null;
                        Label_0265: {
                            try {
                                completable = iterator.next();
                                if (completable != null) {
                                    break Label_0265;
                                }
                                ex = new NullPointerException("One of the sources is null");
                                if (atomicBoolean.compareAndSet(false, true)) {
                                    compositeSubscription.unsubscribe();
                                    completableSubscriber.onError(ex);
                                    return;
                                }
                            }
                            catch (Throwable t3) {
                                if (atomicBoolean.compareAndSet(false, true)) {
                                    compositeSubscription.unsubscribe();
                                    completableSubscriber.onError(t3);
                                    return;
                                }
                                RxJavaHooks.onError(t3);
                                return;
                            }
                            break Label_0259;
                        }
                        if (atomicBoolean.get() || compositeSubscription.isUnsubscribed()) {
                            return;
                        }
                        completable.unsafeSubscribe(completableSubscriber2);
                    }
                    return;
                }
                RxJavaHooks.onError(ex);
            }
        });
    }
    
    public static Completable amb(final Completable... array) {
        requireNonNull(array);
        if (array.length == 0) {
            return complete();
        }
        if (array.length == 1) {
            return array[0];
        }
        return create((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(final CompletableSubscriber completableSubscriber) {
                final CompositeSubscription compositeSubscription = new CompositeSubscription();
                completableSubscriber.onSubscribe(compositeSubscription);
                final AtomicBoolean atomicBoolean = new AtomicBoolean();
                final CompletableSubscriber completableSubscriber2 = new CompletableSubscriber() {
                    @Override
                    public void onCompleted() {
                        if (atomicBoolean.compareAndSet(false, true)) {
                            compositeSubscription.unsubscribe();
                            completableSubscriber.onCompleted();
                        }
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        if (atomicBoolean.compareAndSet(false, true)) {
                            compositeSubscription.unsubscribe();
                            completableSubscriber.onError(t);
                            return;
                        }
                        RxJavaHooks.onError(t);
                    }
                    
                    @Override
                    public void onSubscribe(final Subscription subscription) {
                        compositeSubscription.add(subscription);
                    }
                };
                final Completable[] val$sources = array;
                final int length = val$sources.length;
                int i = 0;
                while (i < length) {
                    final Completable completable = val$sources[i];
                    if (compositeSubscription.isUnsubscribed()) {
                        break;
                    }
                    if (completable == null) {
                        final NullPointerException ex = new NullPointerException("One of the sources is null");
                        if (atomicBoolean.compareAndSet(false, true)) {
                            compositeSubscription.unsubscribe();
                            completableSubscriber.onError(ex);
                            return;
                        }
                        RxJavaHooks.onError(ex);
                    }
                    else {
                        if (atomicBoolean.get() || compositeSubscription.isUnsubscribed()) {
                            break;
                        }
                        completable.unsafeSubscribe(completableSubscriber2);
                        ++i;
                    }
                }
            }
        });
    }
    
    public static Completable complete() {
        final OnSubscribe onCreate = RxJavaHooks.onCreate(Completable.COMPLETE.onSubscribe);
        if (onCreate == Completable.COMPLETE.onSubscribe) {
            return Completable.COMPLETE;
        }
        return new Completable(onCreate, false);
    }
    
    public static Completable concat(final Iterable<? extends Completable> iterable) {
        requireNonNull(iterable);
        return create((OnSubscribe)new CompletableOnSubscribeConcatIterable(iterable));
    }
    
    public static Completable concat(final Observable<? extends Completable> observable) {
        return concat(observable, 2);
    }
    
    public static Completable concat(final Observable<? extends Completable> observable, final int n) {
        requireNonNull(observable);
        if (n < 1) {
            throw new IllegalArgumentException("prefetch > 0 required but it was " + n);
        }
        return create((OnSubscribe)new CompletableOnSubscribeConcat(observable, n));
    }
    
    public static Completable concat(final Completable... array) {
        requireNonNull(array);
        if (array.length == 0) {
            return complete();
        }
        if (array.length == 1) {
            return array[0];
        }
        return create((OnSubscribe)new CompletableOnSubscribeConcatArray(array));
    }
    
    public static Completable create(final OnSubscribe onSubscribe) {
        requireNonNull(onSubscribe);
        try {
            return new Completable(onSubscribe);
        }
        catch (NullPointerException ex) {
            throw ex;
        }
        catch (Throwable t) {
            RxJavaHooks.onError(t);
            throw toNpe(t);
        }
    }
    
    public static Completable defer(final Func0<? extends Completable> func0) {
        requireNonNull(func0);
        return create((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(final CompletableSubscriber completableSubscriber) {
                Completable completable;
                try {
                    completable = func0.call();
                    if (completable == null) {
                        completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
                        completableSubscriber.onError(new NullPointerException("The completable returned is null"));
                        return;
                    }
                }
                catch (Throwable t) {
                    completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
                    completableSubscriber.onError(t);
                    return;
                }
                completable.unsafeSubscribe(completableSubscriber);
            }
        });
    }
    
    static void deliverUncaughtException(final Throwable t) {
        final Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, t);
    }
    
    public static Completable error(final Throwable t) {
        requireNonNull(t);
        return create((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(final CompletableSubscriber completableSubscriber) {
                completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
                completableSubscriber.onError(t);
            }
        });
    }
    
    public static Completable error(final Func0<? extends Throwable> func0) {
        requireNonNull(func0);
        return create((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(final CompletableSubscriber completableSubscriber) {
                completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
                while (true) {
                    try {
                        final Throwable t = func0.call();
                        Throwable t2 = t;
                        if (t == null) {
                            t2 = new NullPointerException("The error supplied is null");
                        }
                        completableSubscriber.onError(t2);
                    }
                    catch (Throwable t) {
                        continue;
                    }
                    break;
                }
            }
        });
    }
    
    public static Completable fromAction(final Action0 action0) {
        requireNonNull(action0);
        return create((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(final CompletableSubscriber completableSubscriber) {
                final BooleanSubscription booleanSubscription = new BooleanSubscription();
                completableSubscriber.onSubscribe(booleanSubscription);
                try {
                    action0.call();
                    if (!booleanSubscription.isUnsubscribed()) {
                        completableSubscriber.onCompleted();
                    }
                }
                catch (Throwable t) {
                    if (!booleanSubscription.isUnsubscribed()) {
                        completableSubscriber.onError(t);
                    }
                }
            }
        });
    }
    
    public static Completable fromCallable(final Callable<?> callable) {
        requireNonNull(callable);
        return create((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(final CompletableSubscriber completableSubscriber) {
                final BooleanSubscription booleanSubscription = new BooleanSubscription();
                completableSubscriber.onSubscribe(booleanSubscription);
                try {
                    callable.call();
                    if (!booleanSubscription.isUnsubscribed()) {
                        completableSubscriber.onCompleted();
                    }
                }
                catch (Throwable t) {
                    if (!booleanSubscription.isUnsubscribed()) {
                        completableSubscriber.onError(t);
                    }
                }
            }
        });
    }
    
    @Experimental
    public static Completable fromEmitter(final Action1<CompletableEmitter> action1) {
        return create((OnSubscribe)new CompletableFromEmitter(action1));
    }
    
    public static Completable fromFuture(final Future<?> future) {
        requireNonNull(future);
        return fromObservable(Observable.from(future));
    }
    
    public static Completable fromObservable(final Observable<?> observable) {
        requireNonNull(observable);
        return create((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(final CompletableSubscriber completableSubscriber) {
                final Subscriber<Object> subscriber = new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        completableSubscriber.onCompleted();
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        completableSubscriber.onError(t);
                    }
                    
                    @Override
                    public void onNext(final Object o) {
                    }
                };
                completableSubscriber.onSubscribe(subscriber);
                observable.unsafeSubscribe(subscriber);
            }
        });
    }
    
    public static Completable fromSingle(final Single<?> single) {
        requireNonNull(single);
        return create((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(final CompletableSubscriber completableSubscriber) {
                final SingleSubscriber<Object> singleSubscriber = new SingleSubscriber<Object>() {
                    @Override
                    public void onError(final Throwable t) {
                        completableSubscriber.onError(t);
                    }
                    
                    @Override
                    public void onSuccess(final Object o) {
                        completableSubscriber.onCompleted();
                    }
                };
                completableSubscriber.onSubscribe(singleSubscriber);
                single.subscribe(singleSubscriber);
            }
        });
    }
    
    public static Completable merge(final Iterable<? extends Completable> iterable) {
        requireNonNull(iterable);
        return create((OnSubscribe)new CompletableOnSubscribeMergeIterable(iterable));
    }
    
    public static Completable merge(final Observable<? extends Completable> observable) {
        return merge0(observable, Integer.MAX_VALUE, false);
    }
    
    public static Completable merge(final Observable<? extends Completable> observable, final int n) {
        return merge0(observable, n, false);
    }
    
    public static Completable merge(final Completable... array) {
        requireNonNull(array);
        if (array.length == 0) {
            return complete();
        }
        if (array.length == 1) {
            return array[0];
        }
        return create((OnSubscribe)new CompletableOnSubscribeMergeArray(array));
    }
    
    protected static Completable merge0(final Observable<? extends Completable> observable, final int n, final boolean b) {
        requireNonNull(observable);
        if (n < 1) {
            throw new IllegalArgumentException("maxConcurrency > 0 required but it was " + n);
        }
        return create((OnSubscribe)new CompletableOnSubscribeMerge(observable, n, b));
    }
    
    public static Completable mergeDelayError(final Iterable<? extends Completable> iterable) {
        requireNonNull(iterable);
        return create((OnSubscribe)new CompletableOnSubscribeMergeDelayErrorIterable(iterable));
    }
    
    public static Completable mergeDelayError(final Observable<? extends Completable> observable) {
        return merge0(observable, Integer.MAX_VALUE, true);
    }
    
    public static Completable mergeDelayError(final Observable<? extends Completable> observable, final int n) {
        return merge0(observable, n, true);
    }
    
    public static Completable mergeDelayError(final Completable... array) {
        requireNonNull(array);
        return create((OnSubscribe)new CompletableOnSubscribeMergeDelayErrorArray(array));
    }
    
    public static Completable never() {
        final OnSubscribe onCreate = RxJavaHooks.onCreate(Completable.NEVER.onSubscribe);
        if (onCreate == Completable.NEVER.onSubscribe) {
            return Completable.NEVER;
        }
        return new Completable(onCreate, false);
    }
    
    static <T> T requireNonNull(final T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }
    
    public static Completable timer(final long n, final TimeUnit timeUnit) {
        return timer(n, timeUnit, Schedulers.computation());
    }
    
    public static Completable timer(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        requireNonNull(timeUnit);
        requireNonNull(scheduler);
        return create((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(final CompletableSubscriber completableSubscriber) {
                final MultipleAssignmentSubscription multipleAssignmentSubscription = new MultipleAssignmentSubscription();
                completableSubscriber.onSubscribe(multipleAssignmentSubscription);
                if (!multipleAssignmentSubscription.isUnsubscribed()) {
                    final Scheduler.Worker worker = scheduler.createWorker();
                    multipleAssignmentSubscription.set(worker);
                    worker.schedule(new Action0() {
                        @Override
                        public void call() {
                            try {
                                completableSubscriber.onCompleted();
                            }
                            finally {
                                worker.unsubscribe();
                            }
                        }
                    }, n, timeUnit);
                }
            }
        });
    }
    
    static NullPointerException toNpe(final Throwable t) {
        final NullPointerException ex = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
        ex.initCause(t);
        return ex;
    }
    
    private <T> void unsafeSubscribe(final Subscriber<T> subscriber, final boolean b) {
        requireNonNull(subscriber);
        Label_0013: {
            if (!b) {
                break Label_0013;
            }
            try {
                subscriber.onStart();
                this.unsafeSubscribe(new CompletableSubscriber() {
                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        subscriber.onError(t);
                    }
                    
                    @Override
                    public void onSubscribe(final Subscription subscription) {
                        subscriber.add(subscription);
                    }
                });
                RxJavaHooks.onObservableReturn(subscriber);
            }
            catch (NullPointerException ex) {
                throw ex;
            }
            catch (Throwable t) {
                Exceptions.throwIfFatal(t);
                final Throwable onObservableError = RxJavaHooks.onObservableError(t);
                RxJavaHooks.onError(onObservableError);
                throw toNpe(onObservableError);
            }
        }
    }
    
    public static <R> Completable using(final Func0<R> func0, final Func1<? super R, ? extends Completable> func2, final Action1<? super R> action1) {
        return using(func0, func2, action1, true);
    }
    
    public static <R> Completable using(final Func0<R> func0, final Func1<? super R, ? extends Completable> func2, final Action1<? super R> action1, final boolean b) {
        requireNonNull(func0);
        requireNonNull(func2);
        requireNonNull(action1);
        return create((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(final CompletableSubscriber p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     1: getfield        rx/Completable$13.val$resourceFunc0:Lrx/functions/Func0;
                //     4: invokeinterface rx/functions/Func0.call:()Ljava/lang/Object;
                //     9: astore_2       
                //    10: aload_0        
                //    11: getfield        rx/Completable$13.val$completableFunc1:Lrx/functions/Func1;
                //    14: aload_2        
                //    15: invokeinterface rx/functions/Func1.call:(Ljava/lang/Object;)Ljava/lang/Object;
                //    20: checkcast       Lrx/Completable;
                //    23: astore_3       
                //    24: aload_3        
                //    25: ifnonnull       211
                //    28: aload_0        
                //    29: getfield        rx/Completable$13.val$disposer:Lrx/functions/Action1;
                //    32: aload_2        
                //    33: invokeinterface rx/functions/Action1.call:(Ljava/lang/Object;)V
                //    38: aload_1        
                //    39: invokestatic    rx/subscriptions/Subscriptions.unsubscribed:()Lrx/Subscription;
                //    42: invokeinterface rx/CompletableSubscriber.onSubscribe:(Lrx/Subscription;)V
                //    47: aload_1        
                //    48: new             Ljava/lang/NullPointerException;
                //    51: dup            
                //    52: ldc             "The completable supplied is null"
                //    54: invokespecial   java/lang/NullPointerException.<init>:(Ljava/lang/String;)V
                //    57: invokeinterface rx/CompletableSubscriber.onError:(Ljava/lang/Throwable;)V
                //    62: return         
                //    63: astore_2       
                //    64: aload_1        
                //    65: invokestatic    rx/subscriptions/Subscriptions.unsubscribed:()Lrx/Subscription;
                //    68: invokeinterface rx/CompletableSubscriber.onSubscribe:(Lrx/Subscription;)V
                //    73: aload_1        
                //    74: aload_2        
                //    75: invokeinterface rx/CompletableSubscriber.onError:(Ljava/lang/Throwable;)V
                //    80: return         
                //    81: astore_3       
                //    82: aload_0        
                //    83: getfield        rx/Completable$13.val$disposer:Lrx/functions/Action1;
                //    86: aload_2        
                //    87: invokeinterface rx/functions/Action1.call:(Ljava/lang/Object;)V
                //    92: aload_3        
                //    93: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
                //    96: aload_1        
                //    97: invokestatic    rx/subscriptions/Subscriptions.unsubscribed:()Lrx/Subscription;
                //   100: invokeinterface rx/CompletableSubscriber.onSubscribe:(Lrx/Subscription;)V
                //   105: aload_1        
                //   106: aload_3        
                //   107: invokeinterface rx/CompletableSubscriber.onError:(Ljava/lang/Throwable;)V
                //   112: return         
                //   113: astore_2       
                //   114: aload_3        
                //   115: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
                //   118: aload_2        
                //   119: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
                //   122: aload_1        
                //   123: invokestatic    rx/subscriptions/Subscriptions.unsubscribed:()Lrx/Subscription;
                //   126: invokeinterface rx/CompletableSubscriber.onSubscribe:(Lrx/Subscription;)V
                //   131: aload_1        
                //   132: new             Lrx/exceptions/CompositeException;
                //   135: dup            
                //   136: iconst_2       
                //   137: anewarray       Ljava/lang/Throwable;
                //   140: dup            
                //   141: iconst_0       
                //   142: aload_3        
                //   143: aastore        
                //   144: dup            
                //   145: iconst_1       
                //   146: aload_2        
                //   147: aastore        
                //   148: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
                //   151: invokespecial   rx/exceptions/CompositeException.<init>:(Ljava/util/Collection;)V
                //   154: invokeinterface rx/CompletableSubscriber.onError:(Ljava/lang/Throwable;)V
                //   159: return         
                //   160: astore_2       
                //   161: aload_2        
                //   162: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
                //   165: aload_1        
                //   166: invokestatic    rx/subscriptions/Subscriptions.unsubscribed:()Lrx/Subscription;
                //   169: invokeinterface rx/CompletableSubscriber.onSubscribe:(Lrx/Subscription;)V
                //   174: aload_1        
                //   175: new             Lrx/exceptions/CompositeException;
                //   178: dup            
                //   179: iconst_2       
                //   180: anewarray       Ljava/lang/Throwable;
                //   183: dup            
                //   184: iconst_0       
                //   185: new             Ljava/lang/NullPointerException;
                //   188: dup            
                //   189: ldc             "The completable supplied is null"
                //   191: invokespecial   java/lang/NullPointerException.<init>:(Ljava/lang/String;)V
                //   194: aastore        
                //   195: dup            
                //   196: iconst_1       
                //   197: aload_2        
                //   198: aastore        
                //   199: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
                //   202: invokespecial   rx/exceptions/CompositeException.<init>:(Ljava/util/Collection;)V
                //   205: invokeinterface rx/CompletableSubscriber.onError:(Ljava/lang/Throwable;)V
                //   210: return         
                //   211: aload_3        
                //   212: new             Lrx/Completable$13$1;
                //   215: dup            
                //   216: aload_0        
                //   217: new             Ljava/util/concurrent/atomic/AtomicBoolean;
                //   220: dup            
                //   221: invokespecial   java/util/concurrent/atomic/AtomicBoolean.<init>:()V
                //   224: aload_2        
                //   225: aload_1        
                //   226: invokespecial   rx/Completable$13$1.<init>:(Lrx/Completable$13;Ljava/util/concurrent/atomic/AtomicBoolean;Ljava/lang/Object;Lrx/CompletableSubscriber;)V
                //   229: invokevirtual   rx/Completable.unsafeSubscribe:(Lrx/CompletableSubscriber;)V
                //   232: return         
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  0      10     63     81     Ljava/lang/Throwable;
                //  10     24     81     160    Ljava/lang/Throwable;
                //  28     38     160    211    Ljava/lang/Throwable;
                //  82     92     113    160    Ljava/lang/Throwable;
                // 
                // The error that occurred was:
                // 
                // java.lang.IndexOutOfBoundsException: Index: 111, Size: 111
                //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
                //     at java.util.ArrayList.get(ArrayList.java:429)
                //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
                //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1164)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
                //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
                //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
                //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
                //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
                //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
                // 
                throw new IllegalStateException("An error occurred while decompiling this method.");
            }
        });
    }
    
    public final Completable ambWith(final Completable completable) {
        requireNonNull(completable);
        return amb(this, completable);
    }
    
    public final Completable andThen(final Completable completable) {
        return this.concatWith(completable);
    }
    
    public final <T> Observable<T> andThen(final Observable<T> observable) {
        requireNonNull(observable);
        return observable.delaySubscription(this.toObservable());
    }
    
    public final <T> Single<T> andThen(final Single<T> single) {
        requireNonNull(single);
        return single.delaySubscription(this.toObservable());
    }
    
    public final void await() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Throwable[] array = { null };
        this.unsafeSubscribe(new CompletableSubscriber() {
            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
            
            @Override
            public void onError(final Throwable t) {
                array[0] = t;
                countDownLatch.countDown();
            }
            
            @Override
            public void onSubscribe(final Subscription subscription) {
            }
        });
        if (countDownLatch.getCount() == 0L) {
            if (array[0] != null) {
                Exceptions.propagate(array[0]);
            }
        }
        else {
            try {
                countDownLatch.await();
                if (array[0] != null) {
                    Exceptions.propagate(array[0]);
                }
            }
            catch (InterruptedException ex) {
                throw Exceptions.propagate(ex);
            }
        }
    }
    
    public final boolean await(final long n, final TimeUnit timeUnit) {
        final boolean b = true;
        requireNonNull(timeUnit);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Throwable[] array = { null };
        this.unsafeSubscribe(new CompletableSubscriber() {
            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
            
            @Override
            public void onError(final Throwable t) {
                array[0] = t;
                countDownLatch.countDown();
            }
            
            @Override
            public void onSubscribe(final Subscription subscription) {
            }
        });
        boolean b2;
        if (countDownLatch.getCount() == 0L) {
            b2 = b;
            if (array[0] != null) {
                Exceptions.propagate(array[0]);
                b2 = b;
            }
        }
        else {
            try {
                final boolean await = countDownLatch.await(n, timeUnit);
                if (b2 = await) {
                    b2 = await;
                    if (array[0] != null) {
                        Exceptions.propagate(array[0]);
                        return await;
                    }
                }
            }
            catch (InterruptedException ex) {
                throw Exceptions.propagate(ex);
            }
        }
        return b2;
    }
    
    public final Completable compose(final Transformer transformer) {
        return this.to((Func1<? super Completable, Completable>)transformer);
    }
    
    public final Completable concatWith(final Completable completable) {
        requireNonNull(completable);
        return concat(this, completable);
    }
    
    public final Completable delay(final long n, final TimeUnit timeUnit) {
        return this.delay(n, timeUnit, Schedulers.computation(), false);
    }
    
    public final Completable delay(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.delay(n, timeUnit, scheduler, false);
    }
    
    public final Completable delay(final long n, final TimeUnit timeUnit, final Scheduler scheduler, final boolean b) {
        requireNonNull(timeUnit);
        requireNonNull(scheduler);
        return create((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(final CompletableSubscriber completableSubscriber) {
                final CompositeSubscription compositeSubscription = new CompositeSubscription();
                final Scheduler.Worker worker = scheduler.createWorker();
                compositeSubscription.add(worker);
                Completable.this.unsafeSubscribe(new CompletableSubscriber() {
                    @Override
                    public void onCompleted() {
                        compositeSubscription.add(worker.schedule(new Action0() {
                            @Override
                            public void call() {
                                try {
                                    completableSubscriber.onCompleted();
                                }
                                finally {
                                    worker.unsubscribe();
                                }
                            }
                        }, n, timeUnit));
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        if (b) {
                            compositeSubscription.add(worker.schedule(new Action0() {
                                @Override
                                public void call() {
                                    try {
                                        completableSubscriber.onError(t);
                                    }
                                    finally {
                                        worker.unsubscribe();
                                    }
                                }
                            }, n, timeUnit));
                            return;
                        }
                        completableSubscriber.onError(t);
                    }
                    
                    @Override
                    public void onSubscribe(final Subscription subscription) {
                        compositeSubscription.add(subscription);
                        completableSubscriber.onSubscribe(compositeSubscription);
                    }
                });
            }
        });
    }
    
    public final Completable doAfterTerminate(final Action0 action0) {
        return this.doOnLifecycle(Actions.empty(), Actions.empty(), Actions.empty(), action0, Actions.empty());
    }
    
    public final Completable doOnCompleted(final Action0 action0) {
        return this.doOnLifecycle(Actions.empty(), Actions.empty(), action0, Actions.empty(), Actions.empty());
    }
    
    public final Completable doOnEach(final Action1<Notification<Object>> action1) {
        if (action1 == null) {
            throw new IllegalArgumentException("onNotification is null");
        }
        return this.doOnLifecycle(Actions.empty(), new Action1<Throwable>() {
            @Override
            public void call(final Throwable t) {
                action1.call(Notification.createOnError(t));
            }
        }, new Action0() {
            @Override
            public void call() {
                action1.call(Notification.createOnCompleted());
            }
        }, Actions.empty(), Actions.empty());
    }
    
    public final Completable doOnError(final Action1<? super Throwable> action1) {
        return this.doOnLifecycle(Actions.empty(), action1, Actions.empty(), Actions.empty(), Actions.empty());
    }
    
    protected final Completable doOnLifecycle(final Action1<? super Subscription> action1, final Action1<? super Throwable> action2, final Action0 action3, final Action0 action4, final Action0 action5) {
        requireNonNull(action1);
        requireNonNull(action2);
        requireNonNull(action3);
        requireNonNull(action4);
        requireNonNull(action5);
        return create((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(final CompletableSubscriber completableSubscriber) {
                Completable.this.unsafeSubscribe(new CompletableSubscriber() {
                    final /* synthetic */ Completable$19 this$1;
                    
                    @Override
                    public void onCompleted() {
                        try {
                            action3.call();
                            completableSubscriber.onCompleted();
                            final CompletableSubscriber completableSubscriber = this;
                            final OnSubscribe onSubscribe = completableSubscriber.this$1;
                            final Action0 action0 = action4;
                            action0.call();
                            return;
                        }
                        catch (Throwable t) {
                            completableSubscriber.onError(t);
                            return;
                        }
                        try {
                            final CompletableSubscriber completableSubscriber = this;
                            final OnSubscribe onSubscribe = completableSubscriber.this$1;
                            final Action0 action0 = action4;
                            action0.call();
                        }
                        catch (Throwable t2) {
                            RxJavaHooks.onError(t2);
                        }
                    }
                    
                    @Override
                    public void onError(Throwable t) {
                        while (true) {
                            try {
                                action2.call(t);
                                completableSubscriber.onError(t);
                            }
                            catch (Throwable t2) {
                                t = new CompositeException(Arrays.asList(t, t2));
                                continue;
                            }
                            break;
                        }
                    }
                    
                    @Override
                    public void onSubscribe(final Subscription subscription) {
                        try {
                            action1.call(subscription);
                            completableSubscriber.onSubscribe(Subscriptions.create(new Action0() {
                                @Override
                                public void call() {
                                    while (true) {
                                        try {
                                            action5.call();
                                            subscription.unsubscribe();
                                        }
                                        catch (Throwable t) {
                                            RxJavaHooks.onError(t);
                                            continue;
                                        }
                                        break;
                                    }
                                }
                            }));
                        }
                        catch (Throwable t) {
                            subscription.unsubscribe();
                            completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
                            completableSubscriber.onError(t);
                        }
                    }
                });
            }
        });
    }
    
    public final Completable doOnSubscribe(final Action1<? super Subscription> action1) {
        return this.doOnLifecycle(action1, Actions.empty(), Actions.empty(), Actions.empty(), Actions.empty());
    }
    
    public final Completable doOnTerminate(final Action0 action0) {
        return this.doOnLifecycle(Actions.empty(), new Action1<Throwable>() {
            @Override
            public void call(final Throwable t) {
                action0.call();
            }
        }, action0, Actions.empty(), Actions.empty());
    }
    
    public final Completable doOnUnsubscribe(final Action0 action0) {
        return this.doOnLifecycle(Actions.empty(), Actions.empty(), Actions.empty(), Actions.empty(), action0);
    }
    
    public final Throwable get() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Throwable[] array = { null };
        this.unsafeSubscribe(new CompletableSubscriber() {
            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
            
            @Override
            public void onError(final Throwable t) {
                array[0] = t;
                countDownLatch.countDown();
            }
            
            @Override
            public void onSubscribe(final Subscription subscription) {
            }
        });
        if (countDownLatch.getCount() == 0L) {
            return array[0];
        }
        try {
            countDownLatch.await();
            return array[0];
        }
        catch (InterruptedException ex) {
            throw Exceptions.propagate(ex);
        }
    }
    
    public final Throwable get(final long n, final TimeUnit timeUnit) {
        requireNonNull(timeUnit);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Throwable[] array = { null };
        this.unsafeSubscribe(new CompletableSubscriber() {
            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
            
            @Override
            public void onError(final Throwable t) {
                array[0] = t;
                countDownLatch.countDown();
            }
            
            @Override
            public void onSubscribe(final Subscription subscription) {
            }
        });
        if (countDownLatch.getCount() == 0L) {
            return array[0];
        }
        try {
            if (countDownLatch.await(n, timeUnit)) {
                return array[0];
            }
        }
        catch (InterruptedException ex) {
            throw Exceptions.propagate(ex);
        }
        Exceptions.propagate(new TimeoutException());
        return null;
    }
    
    public final Completable lift(final Operator operator) {
        requireNonNull(operator);
        return create((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(CompletableSubscriber completableSubscriber) {
                try {
                    completableSubscriber = RxJavaHooks.onCompletableLift(operator).call(completableSubscriber);
                    Completable.this.unsafeSubscribe(completableSubscriber);
                }
                catch (NullPointerException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    throw Completable.toNpe(t);
                }
            }
        });
    }
    
    public final Completable mergeWith(final Completable completable) {
        requireNonNull(completable);
        return merge(this, completable);
    }
    
    public final Completable observeOn(final Scheduler scheduler) {
        requireNonNull(scheduler);
        return create((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(final CompletableSubscriber completableSubscriber) {
                final SubscriptionList list = new SubscriptionList();
                final Scheduler.Worker worker = scheduler.createWorker();
                list.add(worker);
                completableSubscriber.onSubscribe(list);
                Completable.this.unsafeSubscribe(new CompletableSubscriber() {
                    @Override
                    public void onCompleted() {
                        worker.schedule(new Action0() {
                            @Override
                            public void call() {
                                try {
                                    completableSubscriber.onCompleted();
                                }
                                finally {
                                    list.unsubscribe();
                                }
                            }
                        });
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        worker.schedule(new Action0() {
                            @Override
                            public void call() {
                                try {
                                    completableSubscriber.onError(t);
                                }
                                finally {
                                    list.unsubscribe();
                                }
                            }
                        });
                    }
                    
                    @Override
                    public void onSubscribe(final Subscription subscription) {
                        list.add(subscription);
                    }
                });
            }
        });
    }
    
    public final Completable onErrorComplete() {
        return this.onErrorComplete(UtilityFunctions.alwaysTrue());
    }
    
    public final Completable onErrorComplete(final Func1<? super Throwable, Boolean> func1) {
        requireNonNull(func1);
        return create((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(final CompletableSubscriber completableSubscriber) {
                Completable.this.unsafeSubscribe(new CompletableSubscriber() {
                    @Override
                    public void onCompleted() {
                        completableSubscriber.onCompleted();
                    }
                    
                    @Override
                    public void onError(Throwable t) {
                        while (true) {
                            try {
                                final int booleanValue = ((boolean)func1.call(t)) ? 1 : 0;
                                if (booleanValue != 0) {
                                    completableSubscriber.onCompleted();
                                    return;
                                }
                            }
                            catch (Throwable t2) {
                                Exceptions.throwIfFatal(t2);
                                t = new CompositeException(Arrays.asList(t, t2));
                                final int booleanValue = 0;
                                continue;
                            }
                            break;
                        }
                        completableSubscriber.onError(t);
                    }
                    
                    @Override
                    public void onSubscribe(final Subscription subscription) {
                        completableSubscriber.onSubscribe(subscription);
                    }
                });
            }
        });
    }
    
    public final Completable onErrorResumeNext(final Func1<? super Throwable, ? extends Completable> func1) {
        requireNonNull(func1);
        return create((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(final CompletableSubscriber completableSubscriber) {
                Completable.this.unsafeSubscribe(new CompletableSubscriber() {
                    final /* synthetic */ SerialSubscription val$sd = new SerialSubscription();
                    
                    @Override
                    public void onCompleted() {
                        completableSubscriber.onCompleted();
                    }
                    
                    @Override
                    public void onError(Throwable t) {
                        Completable completable;
                        try {
                            completable = func1.call(t);
                            if (completable == null) {
                                t = new CompositeException(Arrays.asList(t, new NullPointerException("The completable returned is null")));
                                completableSubscriber.onError(t);
                                return;
                            }
                        }
                        catch (Throwable t2) {
                            completableSubscriber.onError(new CompositeException(Arrays.asList(t, t2)));
                            return;
                        }
                        completable.unsafeSubscribe(new CompletableSubscriber() {
                            @Override
                            public void onCompleted() {
                                completableSubscriber.onCompleted();
                            }
                            
                            @Override
                            public void onError(final Throwable t) {
                                completableSubscriber.onError(t);
                            }
                            
                            @Override
                            public void onSubscribe(final Subscription subscription) {
                                CompletableSubscriber.this.val$sd.set(subscription);
                            }
                        });
                    }
                    
                    @Override
                    public void onSubscribe(final Subscription subscription) {
                        this.val$sd.set(subscription);
                    }
                });
            }
        });
    }
    
    public final Completable repeat() {
        return fromObservable(this.toObservable().repeat());
    }
    
    public final Completable repeat(final long n) {
        return fromObservable(this.toObservable().repeat(n));
    }
    
    public final Completable repeatWhen(final Func1<? super Observable<? extends Void>, ? extends Observable<?>> func1) {
        requireNonNull(func1);
        return fromObservable(this.toObservable().repeatWhen(func1));
    }
    
    public final Completable retry() {
        return fromObservable(this.toObservable().retry());
    }
    
    public final Completable retry(final long n) {
        return fromObservable(this.toObservable().retry(n));
    }
    
    public final Completable retry(final Func2<Integer, Throwable, Boolean> func2) {
        return fromObservable(this.toObservable().retry(func2));
    }
    
    public final Completable retryWhen(final Func1<? super Observable<? extends Throwable>, ? extends Observable<?>> func1) {
        return fromObservable(this.toObservable().retryWhen(func1));
    }
    
    public final Completable startWith(final Completable completable) {
        requireNonNull(completable);
        return concat(completable, this);
    }
    
    public final <T> Observable<T> startWith(final Observable<T> observable) {
        requireNonNull(observable);
        return this.toObservable().startWith(observable);
    }
    
    public final Subscription subscribe() {
        final MultipleAssignmentSubscription multipleAssignmentSubscription = new MultipleAssignmentSubscription();
        this.unsafeSubscribe(new CompletableSubscriber() {
            @Override
            public void onCompleted() {
                multipleAssignmentSubscription.unsubscribe();
            }
            
            @Override
            public void onError(final Throwable t) {
                RxJavaHooks.onError(t);
                multipleAssignmentSubscription.unsubscribe();
                Completable.deliverUncaughtException(t);
            }
            
            @Override
            public void onSubscribe(final Subscription subscription) {
                multipleAssignmentSubscription.set(subscription);
            }
        });
        return multipleAssignmentSubscription;
    }
    
    public final Subscription subscribe(final Action0 action0) {
        requireNonNull(action0);
        final MultipleAssignmentSubscription multipleAssignmentSubscription = new MultipleAssignmentSubscription();
        this.unsafeSubscribe(new CompletableSubscriber() {
            boolean done;
            
            @Override
            public void onCompleted() {
                if (this.done) {
                    return;
                }
                this.done = true;
                try {
                    action0.call();
                }
                catch (Throwable t) {
                    RxJavaHooks.onError(t);
                    Completable.deliverUncaughtException(t);
                }
                finally {
                    multipleAssignmentSubscription.unsubscribe();
                }
            }
            
            @Override
            public void onError(final Throwable t) {
                RxJavaHooks.onError(t);
                multipleAssignmentSubscription.unsubscribe();
                Completable.deliverUncaughtException(t);
            }
            
            @Override
            public void onSubscribe(final Subscription subscription) {
                multipleAssignmentSubscription.set(subscription);
            }
        });
        return multipleAssignmentSubscription;
    }
    
    public final Subscription subscribe(final Action0 action0, final Action1<? super Throwable> action2) {
        requireNonNull(action0);
        requireNonNull(action2);
        final MultipleAssignmentSubscription multipleAssignmentSubscription = new MultipleAssignmentSubscription();
        this.unsafeSubscribe(new CompletableSubscriber() {
            boolean done;
            
            void callOnError(Throwable t) {
                CompositeException ex;
                try {
                    action2.call(t);
                    multipleAssignmentSubscription.unsubscribe();
                    return;
                }
                catch (Throwable t2) {
                    final CompositeException ex2;
                    ex = (ex2 = new CompositeException(Arrays.asList(t, t2)));
                    RxJavaHooks.onError(ex2);
                    final CompositeException ex3 = ex;
                    Completable.deliverUncaughtException(ex3);
                    final CompletableSubscriber completableSubscriber = this;
                    final MultipleAssignmentSubscription multipleAssignmentSubscription = multipleAssignmentSubscription;
                    multipleAssignmentSubscription.unsubscribe();
                    return;
                }
                finally {
                    final Throwable t3;
                    t = t3;
                }
                while (true) {
                    try {
                        final CompositeException ex2 = ex;
                        RxJavaHooks.onError(ex2);
                        final CompositeException ex3 = ex;
                        Completable.deliverUncaughtException(ex3);
                        final CompletableSubscriber completableSubscriber = this;
                        final MultipleAssignmentSubscription multipleAssignmentSubscription = multipleAssignmentSubscription;
                        multipleAssignmentSubscription.unsubscribe();
                        return;
                        multipleAssignmentSubscription.unsubscribe();
                        throw t;
                    }
                    finally {
                        continue;
                    }
                    break;
                }
            }
            
            @Override
            public void onCompleted() {
                if (this.done) {
                    return;
                }
                this.done = true;
                try {
                    action0.call();
                    multipleAssignmentSubscription.unsubscribe();
                }
                catch (Throwable t) {
                    this.callOnError(t);
                }
            }
            
            @Override
            public void onError(final Throwable t) {
                if (!this.done) {
                    this.done = true;
                    this.callOnError(t);
                    return;
                }
                RxJavaHooks.onError(t);
                Completable.deliverUncaughtException(t);
            }
            
            @Override
            public void onSubscribe(final Subscription subscription) {
                multipleAssignmentSubscription.set(subscription);
            }
        });
        return multipleAssignmentSubscription;
    }
    
    public final void subscribe(final CompletableSubscriber completableSubscriber) {
        CompletableSubscriber completableSubscriber2 = completableSubscriber;
        if (!(completableSubscriber instanceof SafeCompletableSubscriber)) {
            completableSubscriber2 = new SafeCompletableSubscriber(completableSubscriber);
        }
        this.unsafeSubscribe(completableSubscriber2);
    }
    
    public final <T> void subscribe(final Subscriber<T> subscriber) {
        subscriber.onStart();
        Subscriber<T> subscriber2 = subscriber;
        if (!(subscriber instanceof SafeSubscriber)) {
            subscriber2 = new SafeSubscriber<T>(subscriber);
        }
        this.unsafeSubscribe(subscriber2, false);
    }
    
    public final Completable subscribeOn(final Scheduler scheduler) {
        requireNonNull(scheduler);
        return create((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(final CompletableSubscriber completableSubscriber) {
                final Scheduler.Worker worker = scheduler.createWorker();
                worker.schedule(new Action0() {
                    @Override
                    public void call() {
                        try {
                            Completable.this.unsafeSubscribe(completableSubscriber);
                        }
                        finally {
                            worker.unsubscribe();
                        }
                    }
                });
            }
        });
    }
    
    public final Completable timeout(final long n, final TimeUnit timeUnit) {
        return this.timeout0(n, timeUnit, Schedulers.computation(), null);
    }
    
    public final Completable timeout(final long n, final TimeUnit timeUnit, final Completable completable) {
        requireNonNull(completable);
        return this.timeout0(n, timeUnit, Schedulers.computation(), completable);
    }
    
    public final Completable timeout(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.timeout0(n, timeUnit, scheduler, null);
    }
    
    public final Completable timeout(final long n, final TimeUnit timeUnit, final Scheduler scheduler, final Completable completable) {
        requireNonNull(completable);
        return this.timeout0(n, timeUnit, scheduler, completable);
    }
    
    public final Completable timeout0(final long n, final TimeUnit timeUnit, final Scheduler scheduler, final Completable completable) {
        requireNonNull(timeUnit);
        requireNonNull(scheduler);
        return create((OnSubscribe)new CompletableOnSubscribeTimeout(this, n, timeUnit, scheduler, completable));
    }
    
    public final <R> R to(final Func1<? super Completable, R> func1) {
        return func1.call(this);
    }
    
    public final <T> Observable<T> toObservable() {
        return Observable.create((Observable.OnSubscribe<T>)new Observable.OnSubscribe<T>() {
            @Override
            public void call(final Subscriber<? super T> subscriber) {
                Completable.this.unsafeSubscribe(subscriber);
            }
        });
    }
    
    public final <T> Single<T> toSingle(final Func0<? extends T> func0) {
        requireNonNull(func0);
        return Single.create((Single.OnSubscribe<T>)new Single.OnSubscribe<T>() {
            @Override
            public void call(final SingleSubscriber<? super T> singleSubscriber) {
                Completable.this.unsafeSubscribe(new CompletableSubscriber() {
                    @Override
                    public void onCompleted() {
                        Object call;
                        try {
                            call = func0.call();
                            if (call == null) {
                                singleSubscriber.onError(new NullPointerException("The value supplied is null"));
                                return;
                            }
                        }
                        catch (Throwable t) {
                            singleSubscriber.onError(t);
                            return;
                        }
                        singleSubscriber.onSuccess(call);
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        singleSubscriber.onError(t);
                    }
                    
                    @Override
                    public void onSubscribe(final Subscription subscription) {
                        singleSubscriber.add(subscription);
                    }
                });
            }
        });
    }
    
    public final <T> Single<T> toSingleDefault(final T t) {
        requireNonNull(t);
        return this.toSingle((Func0<? extends T>)new Func0<T>() {
            @Override
            public T call() {
                return t;
            }
        });
    }
    
    public final void unsafeSubscribe(final CompletableSubscriber completableSubscriber) {
        requireNonNull(completableSubscriber);
        try {
            RxJavaHooks.onCompletableStart(this, this.onSubscribe).call(completableSubscriber);
        }
        catch (NullPointerException ex) {
            throw ex;
        }
        catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            final Throwable onCompletableError = RxJavaHooks.onCompletableError(t);
            RxJavaHooks.onError(onCompletableError);
            throw toNpe(onCompletableError);
        }
    }
    
    public final <T> void unsafeSubscribe(final Subscriber<T> subscriber) {
        this.unsafeSubscribe(subscriber, true);
    }
    
    public final Completable unsubscribeOn(final Scheduler scheduler) {
        requireNonNull(scheduler);
        return create((OnSubscribe)new OnSubscribe() {
            @Override
            public void call(final CompletableSubscriber completableSubscriber) {
                Completable.this.unsafeSubscribe(new CompletableSubscriber() {
                    @Override
                    public void onCompleted() {
                        completableSubscriber.onCompleted();
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        completableSubscriber.onError(t);
                    }
                    
                    @Override
                    public void onSubscribe(final Subscription subscription) {
                        completableSubscriber.onSubscribe(Subscriptions.create(new Action0() {
                            @Override
                            public void call() {
                                final Scheduler.Worker worker = scheduler.createWorker();
                                worker.schedule(new Action0() {
                                    @Override
                                    public void call() {
                                        try {
                                            subscription.unsubscribe();
                                        }
                                        finally {
                                            worker.unsubscribe();
                                        }
                                    }
                                });
                            }
                        }));
                    }
                });
            }
        });
    }
    
    public interface OnSubscribe extends Action1<CompletableSubscriber>
    {
    }
    
    public interface Operator extends Func1<CompletableSubscriber, CompletableSubscriber>
    {
    }
    
    public interface Transformer extends Func1<Completable, Completable>
    {
    }
}
