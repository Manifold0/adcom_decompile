// 
// Decompiled by Procyon v0.5.34
// 

package rx;

import rx.internal.operators.OperatorZipIterable;
import rx.internal.operators.OperatorWithLatestFrom;
import rx.internal.operators.OperatorWithLatestFromMany;
import rx.internal.operators.OperatorWindowWithObservableFactory;
import rx.internal.operators.OperatorWindowWithStartEndObservable;
import rx.internal.operators.OperatorWindowWithObservable;
import rx.internal.operators.OperatorWindowWithTime;
import rx.internal.operators.OperatorWindowWithSize;
import rx.internal.operators.OperatorUnsubscribeOn;
import rx.internal.operators.OperatorToObservableSortedList;
import rx.internal.operators.OnSubscribeSingle;
import rx.internal.operators.OnSubscribeToMultimap;
import java.util.Collection;
import rx.internal.operators.OnSubscribeToMap;
import rx.internal.operators.OperatorToObservableList;
import rx.observables.BlockingObservable;
import rx.internal.operators.OperatorTimestamp;
import rx.schedulers.Timestamped;
import rx.internal.operators.OperatorTimeoutWithSelector;
import rx.internal.operators.OperatorTimeout;
import rx.internal.operators.OperatorTimeInterval;
import rx.schedulers.TimeInterval;
import rx.internal.operators.OperatorThrottleFirst;
import rx.internal.operators.OperatorTakeWhile;
import rx.internal.operators.OperatorTakeUntilPredicate;
import rx.internal.operators.OperatorTakeUntil;
import rx.internal.operators.OperatorTakeLastTimed;
import rx.internal.operators.OperatorTakeLast;
import rx.internal.operators.OnSubscribeTakeLastOne;
import rx.internal.operators.OperatorTakeTimed;
import rx.internal.operators.OperatorTake;
import rx.internal.operators.OperatorSwitchIfEmpty;
import rx.internal.operators.OperatorSubscribeOn;
import rx.internal.util.ObserverSubscriber;
import rx.internal.util.ActionSubscriber;
import rx.internal.operators.OperatorSkipWhile;
import rx.internal.operators.OperatorSkipUntil;
import rx.internal.operators.OperatorSkipLastTimed;
import rx.internal.operators.OperatorSkipLast;
import rx.internal.operators.OnSubscribeSkipTimed;
import rx.internal.operators.OperatorSkip;
import rx.internal.operators.OperatorSingle;
import rx.internal.operators.OperatorSerialize;
import rx.internal.operators.OperatorScan;
import rx.internal.operators.OperatorSampleWithObservable;
import rx.internal.operators.OperatorSampleWithTime;
import rx.internal.operators.OperatorReplay;
import rx.internal.operators.OnSubscribeRedo;
import rx.internal.operators.OnSubscribeReduce;
import rx.internal.operators.OnSubscribeReduceSeed;
import rx.observables.ConnectableObservable;
import rx.internal.operators.OperatorPublish;
import rx.internal.operators.OnSubscribeDetach;
import rx.internal.operators.OperatorOnErrorResumeNextViaFunction;
import rx.internal.operators.OperatorOnBackpressureLatest;
import rx.internal.operators.OperatorOnBackpressureDrop;
import rx.internal.operators.OperatorOnBackpressureBuffer;
import rx.internal.operators.OperatorObserveOn;
import rx.internal.operators.OperatorMaterialize;
import rx.internal.operators.OnSubscribeMap;
import rx.internal.operators.OnSubscribeLift;
import rx.internal.operators.OnSubscribeJoin;
import rx.internal.operators.OperatorIgnoreElements;
import rx.internal.operators.OnSubscribeGroupJoin;
import java.util.Map;
import rx.internal.operators.OperatorGroupBy;
import rx.observables.GroupedObservable;
import rx.internal.operators.OperatorMapPair;
import rx.internal.operators.OnSubscribeFilter;
import rx.internal.operators.OperatorAny;
import rx.internal.operators.OperatorElementAt;
import rx.internal.operators.OperatorDoOnUnsubscribe;
import rx.internal.operators.OperatorDoOnSubscribe;
import rx.internal.operators.OperatorDoOnRequest;
import rx.internal.util.ActionNotificationObserver;
import rx.internal.operators.OnSubscribeDoOnEach;
import rx.internal.util.ActionObserver;
import rx.functions.Actions;
import rx.internal.operators.OperatorDoAfterTerminate;
import rx.functions.Action0;
import rx.internal.operators.OperatorDistinctUntilChanged;
import rx.internal.operators.OperatorDistinct;
import rx.internal.operators.OperatorDematerialize;
import rx.internal.operators.OnSubscribeDelaySubscriptionWithSelector;
import rx.internal.operators.OnSubscribeDelaySubscriptionOther;
import rx.internal.operators.OnSubscribeDelaySubscription;
import rx.internal.operators.OperatorDelayWithSelector;
import rx.internal.operators.OperatorDelay;
import rx.internal.operators.OperatorDebounceWithSelector;
import rx.internal.operators.OperatorDebounceWithTime;
import rx.internal.operators.OnSubscribeFlattenIterable;
import rx.internal.operators.OperatorEagerConcatMap;
import rx.internal.operators.OnSubscribeConcatMap;
import rx.internal.operators.OnSubscribeCollect;
import rx.functions.Action2;
import rx.internal.operators.OperatorCast;
import rx.internal.operators.CachedObservable;
import rx.internal.operators.OperatorBufferWithStartEndObservable;
import rx.internal.operators.OperatorBufferWithSingleObservable;
import rx.internal.operators.OperatorBufferWithTime;
import rx.internal.operators.OperatorBufferWithSize;
import rx.internal.operators.OperatorAsObservable;
import rx.internal.operators.OperatorAll;
import java.util.Iterator;
import rx.internal.operators.OperatorZip;
import java.util.ArrayList;
import rx.internal.operators.OnSubscribeUsing;
import rx.internal.operators.OnSubscribeTimerOnce;
import rx.internal.operators.OperatorSwitch;
import rx.subscriptions.Subscriptions;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.Exceptions;
import rx.observers.SafeSubscriber;
import rx.internal.operators.OperatorSequenceEqual;
import rx.internal.util.InternalObservableUtils;
import rx.internal.operators.OnSubscribeRange;
import rx.internal.operators.NeverObservableHolder;
import rx.internal.operators.OperatorMerge;
import rx.internal.operators.OperatorMapNotification;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.operators.OnSubscribeTimerPeriodically;
import rx.schedulers.Schedulers;
import rx.internal.operators.OnSubscribeFromEmitter;
import rx.functions.Action1;
import rx.internal.operators.OnSubscribeFromCallable;
import java.util.concurrent.Callable;
import rx.internal.operators.OnSubscribeFromArray;
import java.util.concurrent.TimeUnit;
import rx.internal.operators.OnSubscribeToObservableFuture;
import java.util.concurrent.Future;
import rx.internal.operators.OnSubscribeFromIterable;
import rx.internal.operators.OnSubscribeThrow;
import rx.internal.operators.EmptyObservableHolder;
import rx.internal.operators.OnSubscribeDefer;
import rx.functions.Func0;
import rx.observables.SyncOnSubscribe;
import rx.annotations.Experimental;
import rx.observables.AsyncOnSubscribe;
import rx.plugins.RxJavaHooks;
import rx.annotations.Beta;
import rx.functions.Func1;
import rx.internal.util.UtilityFunctions;
import rx.internal.util.RxRingBuffer;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.functions.Func5;
import rx.functions.Func6;
import rx.functions.Func7;
import rx.functions.Func8;
import rx.functions.Functions;
import java.util.Arrays;
import rx.functions.Func9;
import java.util.List;
import rx.internal.operators.OnSubscribeCombineLatest;
import rx.functions.FuncN;
import rx.internal.operators.OnSubscribeAmb;

public class Observable<T>
{
    final OnSubscribe<T> onSubscribe;
    
    protected Observable(final OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }
    
    public static <T> Observable<T> amb(final Iterable<? extends Observable<? extends T>> iterable) {
        return create(OnSubscribeAmb.amb(iterable));
    }
    
    public static <T> Observable<T> amb(final Observable<? extends T> observable, final Observable<? extends T> observable2) {
        return create(OnSubscribeAmb.amb(observable, observable2));
    }
    
    public static <T> Observable<T> amb(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3) {
        return create(OnSubscribeAmb.amb(observable, observable2, observable3));
    }
    
    public static <T> Observable<T> amb(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4) {
        return create(OnSubscribeAmb.amb(observable, observable2, observable3, observable4));
    }
    
    public static <T> Observable<T> amb(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5) {
        return create(OnSubscribeAmb.amb(observable, observable2, observable3, observable4, observable5));
    }
    
    public static <T> Observable<T> amb(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6) {
        return create(OnSubscribeAmb.amb(observable, observable2, observable3, observable4, observable5, observable6));
    }
    
    public static <T> Observable<T> amb(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7) {
        return create(OnSubscribeAmb.amb(observable, observable2, observable3, observable4, observable5, observable6, observable7));
    }
    
    public static <T> Observable<T> amb(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7, final Observable<? extends T> observable8) {
        return create(OnSubscribeAmb.amb(observable, observable2, observable3, observable4, observable5, observable6, observable7, observable8));
    }
    
    public static <T> Observable<T> amb(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7, final Observable<? extends T> observable8, final Observable<? extends T> observable9) {
        return create(OnSubscribeAmb.amb(observable, observable2, observable3, observable4, observable5, observable6, observable7, observable8, observable9));
    }
    
    public static <T, R> Observable<R> combineLatest(final Iterable<? extends Observable<? extends T>> iterable, final FuncN<? extends R> funcN) {
        return create((OnSubscribe<R>)new OnSubscribeCombineLatest(iterable, funcN));
    }
    
    public static <T, R> Observable<R> combineLatest(final List<? extends Observable<? extends T>> list, final FuncN<? extends R> funcN) {
        return create((OnSubscribe<R>)new OnSubscribeCombineLatest(list, funcN));
    }
    
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Observable<R> combineLatest(final Observable<? extends T1> observable, final Observable<? extends T2> observable2, final Observable<? extends T3> observable3, final Observable<? extends T4> observable4, final Observable<? extends T5> observable5, final Observable<? extends T6> observable6, final Observable<? extends T7> observable7, final Observable<? extends T8> observable8, final Observable<? extends T9> observable9, final Func9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> func9) {
        return combineLatest((List<? extends Observable<?>>)Arrays.asList(observable, observable2, observable3, observable4, observable5, observable6, observable7, observable8, observable9), Functions.fromFunc((Func9<? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? extends R>)func9));
    }
    
    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Observable<R> combineLatest(final Observable<? extends T1> observable, final Observable<? extends T2> observable2, final Observable<? extends T3> observable3, final Observable<? extends T4> observable4, final Observable<? extends T5> observable5, final Observable<? extends T6> observable6, final Observable<? extends T7> observable7, final Observable<? extends T8> observable8, final Func8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> func8) {
        return combineLatest((List<? extends Observable<?>>)Arrays.asList(observable, observable2, observable3, observable4, observable5, observable6, observable7, observable8), Functions.fromFunc((Func8<? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? extends R>)func8));
    }
    
    public static <T1, T2, T3, T4, T5, T6, T7, R> Observable<R> combineLatest(final Observable<? extends T1> observable, final Observable<? extends T2> observable2, final Observable<? extends T3> observable3, final Observable<? extends T4> observable4, final Observable<? extends T5> observable5, final Observable<? extends T6> observable6, final Observable<? extends T7> observable7, final Func7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> func7) {
        return combineLatest((List<? extends Observable<?>>)Arrays.asList(observable, observable2, observable3, observable4, observable5, observable6, observable7), Functions.fromFunc((Func7<? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? extends R>)func7));
    }
    
    public static <T1, T2, T3, T4, T5, T6, R> Observable<R> combineLatest(final Observable<? extends T1> observable, final Observable<? extends T2> observable2, final Observable<? extends T3> observable3, final Observable<? extends T4> observable4, final Observable<? extends T5> observable5, final Observable<? extends T6> observable6, final Func6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> func6) {
        return combineLatest((List<? extends Observable<?>>)Arrays.asList(observable, observable2, observable3, observable4, observable5, observable6), Functions.fromFunc((Func6<? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? extends R>)func6));
    }
    
    public static <T1, T2, T3, T4, T5, R> Observable<R> combineLatest(final Observable<? extends T1> observable, final Observable<? extends T2> observable2, final Observable<? extends T3> observable3, final Observable<? extends T4> observable4, final Observable<? extends T5> observable5, final Func5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> func5) {
        return combineLatest((List<? extends Observable<?>>)Arrays.asList(observable, observable2, observable3, observable4, observable5), Functions.fromFunc((Func5<? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? extends R>)func5));
    }
    
    public static <T1, T2, T3, T4, R> Observable<R> combineLatest(final Observable<? extends T1> observable, final Observable<? extends T2> observable2, final Observable<? extends T3> observable3, final Observable<? extends T4> observable4, final Func4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> func4) {
        return combineLatest((List<? extends Observable<?>>)Arrays.asList(observable, observable2, observable3, observable4), Functions.fromFunc((Func4<? super Object, ? super Object, ? super Object, ? super Object, ? extends R>)func4));
    }
    
    public static <T1, T2, T3, R> Observable<R> combineLatest(final Observable<? extends T1> observable, final Observable<? extends T2> observable2, final Observable<? extends T3> observable3, final Func3<? super T1, ? super T2, ? super T3, ? extends R> func3) {
        return combineLatest((List<? extends Observable<?>>)Arrays.asList(observable, observable2, observable3), Functions.fromFunc((Func3<? super Object, ? super Object, ? super Object, ? extends R>)func3));
    }
    
    public static <T1, T2, R> Observable<R> combineLatest(final Observable<? extends T1> observable, final Observable<? extends T2> observable2, final Func2<? super T1, ? super T2, ? extends R> func2) {
        return combineLatest((List<? extends Observable<?>>)Arrays.asList(observable, observable2), Functions.fromFunc((Func2<? super Object, ? super Object, ? extends R>)func2));
    }
    
    public static <T, R> Observable<R> combineLatestDelayError(final Iterable<? extends Observable<? extends T>> iterable, final FuncN<? extends R> funcN) {
        return create((OnSubscribe<R>)new OnSubscribeCombineLatest(null, iterable, funcN, RxRingBuffer.SIZE, true));
    }
    
    public static <T> Observable<T> concat(final Iterable<? extends Observable<? extends T>> iterable) {
        return concat(from(iterable));
    }
    
    public static <T> Observable<T> concat(final Observable<? extends Observable<? extends T>> observable) {
        return observable.concatMap((Func1<? super Observable<? extends T>, ? extends Observable<? extends T>>)UtilityFunctions.identity());
    }
    
    public static <T> Observable<T> concat(final Observable<? extends T> observable, final Observable<? extends T> observable2) {
        return concat((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2));
    }
    
    public static <T> Observable<T> concat(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3) {
        return concat((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3));
    }
    
    public static <T> Observable<T> concat(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4) {
        return concat((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3, (Observable<? extends T>)observable4));
    }
    
    public static <T> Observable<T> concat(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5) {
        return concat((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3, (Observable<? extends T>)observable4, (Observable<? extends T>)observable5));
    }
    
    public static <T> Observable<T> concat(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6) {
        return concat((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3, (Observable<? extends T>)observable4, (Observable<? extends T>)observable5, (Observable<? extends T>)observable6));
    }
    
    public static <T> Observable<T> concat(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7) {
        return concat((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3, (Observable<? extends T>)observable4, (Observable<? extends T>)observable5, (Observable<? extends T>)observable6, (Observable<? extends T>)observable7));
    }
    
    public static <T> Observable<T> concat(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7, final Observable<? extends T> observable8) {
        return concat((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3, (Observable<? extends T>)observable4, (Observable<? extends T>)observable5, (Observable<? extends T>)observable6, (Observable<? extends T>)observable7, (Observable<? extends T>)observable8));
    }
    
    public static <T> Observable<T> concat(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7, final Observable<? extends T> observable8, final Observable<? extends T> observable9) {
        return concat((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3, (Observable<? extends T>)observable4, (Observable<? extends T>)observable5, (Observable<? extends T>)observable6, (Observable<? extends T>)observable7, (Observable<? extends T>)observable8, (Observable<? extends T>)observable9));
    }
    
    @Beta
    public static <T> Observable<T> concatDelayError(final Iterable<? extends Observable<? extends T>> iterable) {
        return concatDelayError(from(iterable));
    }
    
    @Beta
    public static <T> Observable<T> concatDelayError(final Observable<? extends Observable<? extends T>> observable) {
        return observable.concatMapDelayError((Func1<? super Observable<? extends T>, ? extends Observable<? extends T>>)UtilityFunctions.identity());
    }
    
    @Beta
    public static <T> Observable<T> concatDelayError(final Observable<? extends T> observable, final Observable<? extends T> observable2) {
        return concatDelayError((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2));
    }
    
    @Beta
    public static <T> Observable<T> concatDelayError(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3) {
        return concatDelayError((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3));
    }
    
    @Beta
    public static <T> Observable<T> concatDelayError(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4) {
        return concatDelayError((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3, (Observable<? extends T>)observable4));
    }
    
    @Beta
    public static <T> Observable<T> concatDelayError(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5) {
        return concatDelayError((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3, (Observable<? extends T>)observable4, (Observable<? extends T>)observable5));
    }
    
    @Beta
    public static <T> Observable<T> concatDelayError(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6) {
        return concatDelayError((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3, (Observable<? extends T>)observable4, (Observable<? extends T>)observable5, (Observable<? extends T>)observable6));
    }
    
    @Beta
    public static <T> Observable<T> concatDelayError(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7) {
        return concatDelayError((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3, (Observable<? extends T>)observable4, (Observable<? extends T>)observable5, (Observable<? extends T>)observable6, (Observable<? extends T>)observable7));
    }
    
    @Beta
    public static <T> Observable<T> concatDelayError(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7, final Observable<? extends T> observable8) {
        return concatDelayError((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3, (Observable<? extends T>)observable4, (Observable<? extends T>)observable5, (Observable<? extends T>)observable6, (Observable<? extends T>)observable7, (Observable<? extends T>)observable8));
    }
    
    @Beta
    public static <T> Observable<T> concatDelayError(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7, final Observable<? extends T> observable8, final Observable<? extends T> observable9) {
        return concatDelayError((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3, (Observable<? extends T>)observable4, (Observable<? extends T>)observable5, (Observable<? extends T>)observable6, (Observable<? extends T>)observable7, (Observable<? extends T>)observable8, (Observable<? extends T>)observable9));
    }
    
    @Beta
    public static <T> Observable<T> concatEager(final Iterable<? extends Observable<? extends T>> iterable) {
        return from((Iterable<?>)iterable).concatMapEager((Func1<? super Object, ? extends Observable<? extends T>>)UtilityFunctions.identity());
    }
    
    @Beta
    public static <T> Observable<T> concatEager(final Iterable<? extends Observable<? extends T>> iterable, final int n) {
        return from((Iterable<?>)iterable).concatMapEager((Func1<? super Object, ? extends Observable<? extends T>>)UtilityFunctions.identity(), n);
    }
    
    @Beta
    public static <T> Observable<T> concatEager(final Observable<? extends Observable<? extends T>> observable) {
        return observable.concatMapEager((Func1<? super Observable<? extends T>, ? extends Observable<? extends T>>)UtilityFunctions.identity());
    }
    
    @Beta
    public static <T> Observable<T> concatEager(final Observable<? extends Observable<? extends T>> observable, final int n) {
        return observable.concatMapEager((Func1<? super Observable<? extends T>, ? extends Observable<? extends T>>)UtilityFunctions.identity(), n);
    }
    
    @Beta
    public static <T> Observable<T> concatEager(final Observable<? extends T> observable, final Observable<? extends T> observable2) {
        return concatEager((Iterable<? extends Observable<? extends T>>)Arrays.asList(observable, observable2));
    }
    
    @Beta
    public static <T> Observable<T> concatEager(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3) {
        return concatEager((Iterable<? extends Observable<? extends T>>)Arrays.asList(observable, observable2, observable3));
    }
    
    @Beta
    public static <T> Observable<T> concatEager(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4) {
        return concatEager((Iterable<? extends Observable<? extends T>>)Arrays.asList(observable, observable2, observable3, observable4));
    }
    
    @Beta
    public static <T> Observable<T> concatEager(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5) {
        return concatEager((Iterable<? extends Observable<? extends T>>)Arrays.asList(observable, observable2, observable3, observable4, observable5));
    }
    
    @Beta
    public static <T> Observable<T> concatEager(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6) {
        return concatEager((Iterable<? extends Observable<? extends T>>)Arrays.asList(observable, observable2, observable3, observable4, observable5, observable6));
    }
    
    @Beta
    public static <T> Observable<T> concatEager(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7) {
        return concatEager((Iterable<? extends Observable<? extends T>>)Arrays.asList(observable, observable2, observable3, observable4, observable5, observable6, observable7));
    }
    
    @Beta
    public static <T> Observable<T> concatEager(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7, final Observable<? extends T> observable8) {
        return concatEager((Iterable<? extends Observable<? extends T>>)Arrays.asList(observable, observable2, observable3, observable4, observable5, observable6, observable7, observable8));
    }
    
    @Beta
    public static <T> Observable<T> concatEager(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7, final Observable<? extends T> observable8, final Observable<? extends T> observable9) {
        return concatEager((Iterable<? extends Observable<? extends T>>)Arrays.asList(observable, observable2, observable3, observable4, observable5, observable6, observable7, observable8, observable9));
    }
    
    public static <T> Observable<T> create(final OnSubscribe<T> onSubscribe) {
        return new Observable<T>(RxJavaHooks.onCreate(onSubscribe));
    }
    
    @Experimental
    public static <S, T> Observable<T> create(final AsyncOnSubscribe<S, T> asyncOnSubscribe) {
        return create((OnSubscribe<T>)asyncOnSubscribe);
    }
    
    public static <S, T> Observable<T> create(final SyncOnSubscribe<S, T> syncOnSubscribe) {
        return create((OnSubscribe<T>)syncOnSubscribe);
    }
    
    public static <T> Observable<T> defer(final Func0<Observable<T>> func0) {
        return create((OnSubscribe<T>)new OnSubscribeDefer(func0));
    }
    
    public static <T> Observable<T> empty() {
        return EmptyObservableHolder.instance();
    }
    
    public static <T> Observable<T> error(final Throwable t) {
        return create((OnSubscribe<T>)new OnSubscribeThrow(t));
    }
    
    public static <T> Observable<T> from(final Iterable<? extends T> iterable) {
        return create((OnSubscribe<T>)new OnSubscribeFromIterable(iterable));
    }
    
    public static <T> Observable<T> from(final Future<? extends T> future) {
        return create(OnSubscribeToObservableFuture.toObservableFuture(future));
    }
    
    public static <T> Observable<T> from(final Future<? extends T> future, final long n, final TimeUnit timeUnit) {
        return create(OnSubscribeToObservableFuture.toObservableFuture(future, n, timeUnit));
    }
    
    public static <T> Observable<T> from(final Future<? extends T> future, final Scheduler scheduler) {
        return create(OnSubscribeToObservableFuture.toObservableFuture(future)).subscribeOn(scheduler);
    }
    
    public static <T> Observable<T> from(final T[] array) {
        final int length = array.length;
        if (length == 0) {
            return empty();
        }
        if (length == 1) {
            return just(array[0]);
        }
        return create((OnSubscribe<T>)new OnSubscribeFromArray(array));
    }
    
    public static <T> Observable<T> fromCallable(final Callable<? extends T> callable) {
        return create((OnSubscribe<T>)new OnSubscribeFromCallable(callable));
    }
    
    @Experimental
    public static <T> Observable<T> fromEmitter(final Action1<AsyncEmitter<T>> action1, final AsyncEmitter.BackpressureMode backpressureMode) {
        return create((OnSubscribe<T>)new OnSubscribeFromEmitter((Action1<AsyncEmitter<Object>>)action1, backpressureMode));
    }
    
    public static Observable<Long> interval(final long n, final long n2, final TimeUnit timeUnit) {
        return interval(n, n2, timeUnit, Schedulers.computation());
    }
    
    public static Observable<Long> interval(final long n, final long n2, final TimeUnit timeUnit, final Scheduler scheduler) {
        return create((OnSubscribe<Long>)new OnSubscribeTimerPeriodically(n, n2, timeUnit, scheduler));
    }
    
    public static Observable<Long> interval(final long n, final TimeUnit timeUnit) {
        return interval(n, n, timeUnit, Schedulers.computation());
    }
    
    public static Observable<Long> interval(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return interval(n, n, timeUnit, scheduler);
    }
    
    public static <T> Observable<T> just(final T t) {
        return ScalarSynchronousObservable.create(t);
    }
    
    public static <T> Observable<T> just(final T t, final T t2) {
        return from(new Object[] { t, t2 });
    }
    
    public static <T> Observable<T> just(final T t, final T t2, final T t3) {
        return from(new Object[] { t, t2, t3 });
    }
    
    public static <T> Observable<T> just(final T t, final T t2, final T t3, final T t4) {
        return from(new Object[] { t, t2, t3, t4 });
    }
    
    public static <T> Observable<T> just(final T t, final T t2, final T t3, final T t4, final T t5) {
        return from(new Object[] { t, t2, t3, t4, t5 });
    }
    
    public static <T> Observable<T> just(final T t, final T t2, final T t3, final T t4, final T t5, final T t6) {
        return from(new Object[] { t, t2, t3, t4, t5, t6 });
    }
    
    public static <T> Observable<T> just(final T t, final T t2, final T t3, final T t4, final T t5, final T t6, final T t7) {
        return from(new Object[] { t, t2, t3, t4, t5, t6, t7 });
    }
    
    public static <T> Observable<T> just(final T t, final T t2, final T t3, final T t4, final T t5, final T t6, final T t7, final T t8) {
        return from(new Object[] { t, t2, t3, t4, t5, t6, t7, t8 });
    }
    
    public static <T> Observable<T> just(final T t, final T t2, final T t3, final T t4, final T t5, final T t6, final T t7, final T t8, final T t9) {
        return from(new Object[] { t, t2, t3, t4, t5, t6, t7, t8, t9 });
    }
    
    public static <T> Observable<T> just(final T t, final T t2, final T t3, final T t4, final T t5, final T t6, final T t7, final T t8, final T t9, final T t10) {
        return from(new Object[] { t, t2, t3, t4, t5, t6, t7, t8, t9, t10 });
    }
    
    private <R> Observable<R> mapNotification(final Func1<? super T, ? extends R> func1, final Func1<? super Throwable, ? extends R> func2, final Func0<? extends R> func3) {
        return this.lift((Operator<? extends R, ? super T>)new OperatorMapNotification((Func1<? super Object, ?>)func1, func2, func3));
    }
    
    public static <T> Observable<T> merge(final Iterable<? extends Observable<? extends T>> iterable) {
        return merge(from(iterable));
    }
    
    public static <T> Observable<T> merge(final Iterable<? extends Observable<? extends T>> iterable, final int n) {
        return merge(from(iterable), n);
    }
    
    public static <T> Observable<T> merge(final Observable<? extends Observable<? extends T>> observable) {
        if (((ScalarSynchronousObservable<Object>)observable).getClass() == ScalarSynchronousObservable.class) {
            return ((ScalarSynchronousObservable<Object>)observable).scalarFlatMap((Func1<? super Object, ? extends Observable<? extends T>>)UtilityFunctions.identity());
        }
        return observable.lift((Operator<? extends T, ? super Object>)OperatorMerge.instance(false));
    }
    
    public static <T> Observable<T> merge(final Observable<? extends Observable<? extends T>> observable, final int n) {
        if (((ScalarSynchronousObservable<Object>)observable).getClass() == ScalarSynchronousObservable.class) {
            return ((ScalarSynchronousObservable<Object>)observable).scalarFlatMap((Func1<? super Object, ? extends Observable<? extends T>>)UtilityFunctions.identity());
        }
        return observable.lift((Operator<? extends T, ? super Object>)OperatorMerge.instance(false, n));
    }
    
    public static <T> Observable<T> merge(final Observable<? extends T> observable, final Observable<? extends T> observable2) {
        return merge((Observable<? extends T>[])new Observable[] { observable, observable2 });
    }
    
    public static <T> Observable<T> merge(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3) {
        return merge((Observable<? extends T>[])new Observable[] { observable, observable2, observable3 });
    }
    
    public static <T> Observable<T> merge(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4) {
        return merge((Observable<? extends T>[])new Observable[] { observable, observable2, observable3, observable4 });
    }
    
    public static <T> Observable<T> merge(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5) {
        return merge((Observable<? extends T>[])new Observable[] { observable, observable2, observable3, observable4, observable5 });
    }
    
    public static <T> Observable<T> merge(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6) {
        return merge((Observable<? extends T>[])new Observable[] { observable, observable2, observable3, observable4, observable5, observable6 });
    }
    
    public static <T> Observable<T> merge(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7) {
        return merge((Observable<? extends T>[])new Observable[] { observable, observable2, observable3, observable4, observable5, observable6, observable7 });
    }
    
    public static <T> Observable<T> merge(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7, final Observable<? extends T> observable8) {
        return merge((Observable<? extends T>[])new Observable[] { observable, observable2, observable3, observable4, observable5, observable6, observable7, observable8 });
    }
    
    public static <T> Observable<T> merge(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7, final Observable<? extends T> observable8, final Observable<? extends T> observable9) {
        return merge((Observable<? extends T>[])new Observable[] { observable, observable2, observable3, observable4, observable5, observable6, observable7, observable8, observable9 });
    }
    
    public static <T> Observable<T> merge(final Observable<? extends T>[] array) {
        return merge((Observable<? extends Observable<? extends T>>)from((Observable<? extends T>[])array));
    }
    
    public static <T> Observable<T> merge(final Observable<? extends T>[] array, final int n) {
        return merge((Observable<? extends Observable<? extends T>>)from((Observable<? extends T>[])array), n);
    }
    
    public static <T> Observable<T> mergeDelayError(final Iterable<? extends Observable<? extends T>> iterable) {
        return mergeDelayError(from(iterable));
    }
    
    public static <T> Observable<T> mergeDelayError(final Iterable<? extends Observable<? extends T>> iterable, final int n) {
        return mergeDelayError(from(iterable), n);
    }
    
    public static <T> Observable<T> mergeDelayError(final Observable<? extends Observable<? extends T>> observable) {
        return observable.lift((Operator<? extends T, ? super Observable<? extends T>>)OperatorMerge.instance(true));
    }
    
    @Beta
    public static <T> Observable<T> mergeDelayError(final Observable<? extends Observable<? extends T>> observable, final int n) {
        return observable.lift((Operator<? extends T, ? super Observable<? extends T>>)OperatorMerge.instance(true, n));
    }
    
    public static <T> Observable<T> mergeDelayError(final Observable<? extends T> observable, final Observable<? extends T> observable2) {
        return mergeDelayError((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2));
    }
    
    public static <T> Observable<T> mergeDelayError(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3) {
        return mergeDelayError((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3));
    }
    
    public static <T> Observable<T> mergeDelayError(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4) {
        return mergeDelayError((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3, (Observable<? extends T>)observable4));
    }
    
    public static <T> Observable<T> mergeDelayError(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5) {
        return mergeDelayError((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3, (Observable<? extends T>)observable4, (Observable<? extends T>)observable5));
    }
    
    public static <T> Observable<T> mergeDelayError(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6) {
        return mergeDelayError((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3, (Observable<? extends T>)observable4, (Observable<? extends T>)observable5, (Observable<? extends T>)observable6));
    }
    
    public static <T> Observable<T> mergeDelayError(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7) {
        return mergeDelayError((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3, (Observable<? extends T>)observable4, (Observable<? extends T>)observable5, (Observable<? extends T>)observable6, (Observable<? extends T>)observable7));
    }
    
    public static <T> Observable<T> mergeDelayError(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7, final Observable<? extends T> observable8) {
        return mergeDelayError((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3, (Observable<? extends T>)observable4, (Observable<? extends T>)observable5, (Observable<? extends T>)observable6, (Observable<? extends T>)observable7, (Observable<? extends T>)observable8));
    }
    
    public static <T> Observable<T> mergeDelayError(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7, final Observable<? extends T> observable8, final Observable<? extends T> observable9) {
        return mergeDelayError((Observable<? extends Observable<? extends T>>)just((Observable<? extends T>)observable, (Observable<? extends T>)observable2, (Observable<? extends T>)observable3, (Observable<? extends T>)observable4, (Observable<? extends T>)observable5, (Observable<? extends T>)observable6, (Observable<? extends T>)observable7, (Observable<? extends T>)observable8, (Observable<? extends T>)observable9));
    }
    
    public static <T> Observable<T> never() {
        return NeverObservableHolder.instance();
    }
    
    public static Observable<Integer> range(final int n, final int n2) {
        if (n2 < 0) {
            throw new IllegalArgumentException("Count can not be negative");
        }
        if (n2 == 0) {
            return empty();
        }
        if (n > Integer.MAX_VALUE - n2 + 1) {
            throw new IllegalArgumentException("start + count can not exceed Integer.MAX_VALUE");
        }
        if (n2 == 1) {
            return just(n);
        }
        return create((OnSubscribe<Integer>)new OnSubscribeRange(n, n2 - 1 + n));
    }
    
    public static Observable<Integer> range(final int n, final int n2, final Scheduler scheduler) {
        return range(n, n2).subscribeOn(scheduler);
    }
    
    public static <T> Observable<Boolean> sequenceEqual(final Observable<? extends T> observable, final Observable<? extends T> observable2) {
        return sequenceEqual((Observable<?>)observable, (Observable<?>)observable2, (Func2<? super Object, ? super Object, Boolean>)InternalObservableUtils.OBJECT_EQUALS);
    }
    
    public static <T> Observable<Boolean> sequenceEqual(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Func2<? super T, ? super T, Boolean> func2) {
        return OperatorSequenceEqual.sequenceEqual((Observable<?>)observable, (Observable<?>)observable2, (Func2<? super Object, ? super Object, Boolean>)func2);
    }
    
    static <T> Subscription subscribe(final Subscriber<? super T> t, final Observable<T> observable) {
        if (t == null) {
            throw new IllegalArgumentException("subscriber can not be null");
        }
        if (observable.onSubscribe == null) {
            throw new IllegalStateException("onSubscribe function can not be null.");
        }
        ((Subscriber)t).onStart();
        Subscriber<? super Object> subscriber = (Subscriber<? super Object>)t;
        if (!(t instanceof SafeSubscriber)) {
            subscriber = new SafeSubscriber<Object>((Subscriber<? super Object>)t);
        }
        try {
            RxJavaHooks.onObservableStart(observable, observable.onSubscribe).call(subscriber);
            return RxJavaHooks.onObservableReturn(subscriber);
        }
        catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            if (subscriber.isUnsubscribed()) {
                RxJavaHooks.onError(RxJavaHooks.onObservableError(t));
            }
            else {
                try {
                    subscriber.onError(RxJavaHooks.onObservableError(t));
                }
                catch (Throwable t2) {
                    Exceptions.throwIfFatal(t2);
                    final OnErrorFailedException ex = new OnErrorFailedException("Error occurred attempting to subscribe [" + t.getMessage() + "] and then again while trying to pass to onError.", t2);
                    RxJavaHooks.onObservableError(ex);
                    throw ex;
                }
            }
            return Subscriptions.unsubscribed();
        }
    }
    
    public static <T> Observable<T> switchOnNext(final Observable<? extends Observable<? extends T>> observable) {
        return observable.lift((Operator<? extends T, ? super Observable<? extends T>>)OperatorSwitch.instance(false));
    }
    
    @Beta
    public static <T> Observable<T> switchOnNextDelayError(final Observable<? extends Observable<? extends T>> observable) {
        return observable.lift((Operator<? extends T, ? super Observable<? extends T>>)OperatorSwitch.instance(true));
    }
    
    @Deprecated
    public static Observable<Long> timer(final long n, final long n2, final TimeUnit timeUnit) {
        return interval(n, n2, timeUnit, Schedulers.computation());
    }
    
    @Deprecated
    public static Observable<Long> timer(final long n, final long n2, final TimeUnit timeUnit, final Scheduler scheduler) {
        return interval(n, n2, timeUnit, scheduler);
    }
    
    public static Observable<Long> timer(final long n, final TimeUnit timeUnit) {
        return timer(n, timeUnit, Schedulers.computation());
    }
    
    public static Observable<Long> timer(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return create((OnSubscribe<Long>)new OnSubscribeTimerOnce(n, timeUnit, scheduler));
    }
    
    public static <T, Resource> Observable<T> using(final Func0<Resource> func0, final Func1<? super Resource, ? extends Observable<? extends T>> func2, final Action1<? super Resource> action1) {
        return using(func0, func2, action1, false);
    }
    
    @Beta
    public static <T, Resource> Observable<T> using(final Func0<Resource> func0, final Func1<? super Resource, ? extends Observable<? extends T>> func2, final Action1<? super Resource> action1, final boolean b) {
        return create((OnSubscribe<T>)new OnSubscribeUsing((Func0<Object>)func0, (Func1<? super Object, ? extends Observable<?>>)func2, (Action1<? super Object>)action1, b));
    }
    
    public static <R> Observable<R> zip(final Iterable<? extends Observable<?>> iterable, final FuncN<? extends R> funcN) {
        final ArrayList<Observable<?>> list = new ArrayList<Observable<?>>();
        final Iterator<? extends Observable<?>> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            list.add((Observable<?>)iterator.next());
        }
        return (Observable<R>)just(list.toArray(new Observable[list.size()])).lift((Operator<?, ? super Observable[]>)new OperatorZip(funcN));
    }
    
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Observable<R> zip(final Observable<? extends T1> observable, final Observable<? extends T2> observable2, final Observable<? extends T3> observable3, final Observable<? extends T4> observable4, final Observable<? extends T5> observable5, final Observable<? extends T6> observable6, final Observable<? extends T7> observable7, final Observable<? extends T8> observable8, final Observable<? extends T9> observable9, final Func9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> func9) {
        return just(new Observable[] { observable, observable2, observable3, observable4, observable5, observable6, observable7, observable8, observable9 }).lift((Operator<? extends R, ? super Observable[]>)new OperatorZip(func9));
    }
    
    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Observable<R> zip(final Observable<? extends T1> observable, final Observable<? extends T2> observable2, final Observable<? extends T3> observable3, final Observable<? extends T4> observable4, final Observable<? extends T5> observable5, final Observable<? extends T6> observable6, final Observable<? extends T7> observable7, final Observable<? extends T8> observable8, final Func8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> func8) {
        return just(new Observable[] { observable, observable2, observable3, observable4, observable5, observable6, observable7, observable8 }).lift((Operator<? extends R, ? super Observable[]>)new OperatorZip(func8));
    }
    
    public static <T1, T2, T3, T4, T5, T6, T7, R> Observable<R> zip(final Observable<? extends T1> observable, final Observable<? extends T2> observable2, final Observable<? extends T3> observable3, final Observable<? extends T4> observable4, final Observable<? extends T5> observable5, final Observable<? extends T6> observable6, final Observable<? extends T7> observable7, final Func7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> func7) {
        return just(new Observable[] { observable, observable2, observable3, observable4, observable5, observable6, observable7 }).lift((Operator<? extends R, ? super Observable[]>)new OperatorZip(func7));
    }
    
    public static <T1, T2, T3, T4, T5, T6, R> Observable<R> zip(final Observable<? extends T1> observable, final Observable<? extends T2> observable2, final Observable<? extends T3> observable3, final Observable<? extends T4> observable4, final Observable<? extends T5> observable5, final Observable<? extends T6> observable6, final Func6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> func6) {
        return just(new Observable[] { observable, observable2, observable3, observable4, observable5, observable6 }).lift((Operator<? extends R, ? super Observable[]>)new OperatorZip(func6));
    }
    
    public static <T1, T2, T3, T4, T5, R> Observable<R> zip(final Observable<? extends T1> observable, final Observable<? extends T2> observable2, final Observable<? extends T3> observable3, final Observable<? extends T4> observable4, final Observable<? extends T5> observable5, final Func5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> func5) {
        return just(new Observable[] { observable, observable2, observable3, observable4, observable5 }).lift((Operator<? extends R, ? super Observable[]>)new OperatorZip(func5));
    }
    
    public static <T1, T2, T3, T4, R> Observable<R> zip(final Observable<? extends T1> observable, final Observable<? extends T2> observable2, final Observable<? extends T3> observable3, final Observable<? extends T4> observable4, final Func4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> func4) {
        return just(new Observable[] { observable, observable2, observable3, observable4 }).lift((Operator<? extends R, ? super Observable[]>)new OperatorZip(func4));
    }
    
    public static <T1, T2, T3, R> Observable<R> zip(final Observable<? extends T1> observable, final Observable<? extends T2> observable2, final Observable<? extends T3> observable3, final Func3<? super T1, ? super T2, ? super T3, ? extends R> func3) {
        return just(new Observable[] { observable, observable2, observable3 }).lift((Operator<? extends R, ? super Observable[]>)new OperatorZip(func3));
    }
    
    public static <T1, T2, R> Observable<R> zip(final Observable<? extends T1> observable, final Observable<? extends T2> observable2, final Func2<? super T1, ? super T2, ? extends R> func2) {
        return just(new Observable[] { observable, observable2 }).lift((Operator<? extends R, ? super Observable[]>)new OperatorZip(func2));
    }
    
    public static <R> Observable<R> zip(final Observable<? extends Observable<?>> observable, final FuncN<? extends R> funcN) {
        return observable.toList().map((Func1<? super List<? extends Observable<?>>, ?>)InternalObservableUtils.TO_ARRAY).lift((Operator<? extends R, ? super Object>)new OperatorZip(funcN));
    }
    
    @Experimental
    public static <R> Observable<R> zip(final Observable<?>[] array, final FuncN<? extends R> funcN) {
        return just(array).lift((Operator<? extends R, ? super Observable[]>)new OperatorZip(funcN));
    }
    
    public final Observable<Boolean> all(final Func1<? super T, Boolean> func1) {
        return this.lift((Operator<? extends Boolean, ? super T>)new OperatorAll((Func1<? super Object, Boolean>)func1));
    }
    
    public final Observable<T> ambWith(final Observable<? extends T> observable) {
        return amb((Observable<? extends T>)this, observable);
    }
    
    public final Observable<T> asObservable() {
        return this.lift((Operator<? extends T, ? super T>)OperatorAsObservable.instance());
    }
    
    public final Observable<List<T>> buffer(final int n) {
        return this.buffer(n, n);
    }
    
    public final Observable<List<T>> buffer(final int n, final int n2) {
        return this.lift((Operator<? extends List<T>, ? super T>)new OperatorBufferWithSize(n, n2));
    }
    
    public final Observable<List<T>> buffer(final long n, final long n2, final TimeUnit timeUnit) {
        return this.buffer(n, n2, timeUnit, Schedulers.computation());
    }
    
    public final Observable<List<T>> buffer(final long n, final long n2, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.lift((Operator<? extends List<T>, ? super T>)new OperatorBufferWithTime(n, n2, timeUnit, Integer.MAX_VALUE, scheduler));
    }
    
    public final Observable<List<T>> buffer(final long n, final TimeUnit timeUnit) {
        return this.buffer(n, timeUnit, Integer.MAX_VALUE, Schedulers.computation());
    }
    
    public final Observable<List<T>> buffer(final long n, final TimeUnit timeUnit, final int n2) {
        return this.lift((Operator<? extends List<T>, ? super T>)new OperatorBufferWithTime(n, n, timeUnit, n2, Schedulers.computation()));
    }
    
    public final Observable<List<T>> buffer(final long n, final TimeUnit timeUnit, final int n2, final Scheduler scheduler) {
        return this.lift((Operator<? extends List<T>, ? super T>)new OperatorBufferWithTime(n, n, timeUnit, n2, scheduler));
    }
    
    public final Observable<List<T>> buffer(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.buffer(n, n, timeUnit, scheduler);
    }
    
    public final <B> Observable<List<T>> buffer(final Observable<B> observable) {
        return this.buffer(observable, 16);
    }
    
    public final <B> Observable<List<T>> buffer(final Observable<B> observable, final int n) {
        return this.lift((Operator<? extends List<T>, ? super T>)new OperatorBufferWithSingleObservable(observable, n));
    }
    
    public final <TOpening, TClosing> Observable<List<T>> buffer(final Observable<? extends TOpening> observable, final Func1<? super TOpening, ? extends Observable<? extends TClosing>> func1) {
        return this.lift((Operator<? extends List<T>, ? super T>)new OperatorBufferWithStartEndObservable(observable, (Func1<? super Object, ? extends Observable<?>>)func1));
    }
    
    public final <TClosing> Observable<List<T>> buffer(final Func0<? extends Observable<? extends TClosing>> func0) {
        return this.lift((Operator<? extends List<T>, ? super T>)new OperatorBufferWithSingleObservable(func0, 16));
    }
    
    public final Observable<T> cache() {
        return (Observable<T>)CachedObservable.from((Observable<?>)this);
    }
    
    @Deprecated
    public final Observable<T> cache(final int n) {
        return this.cacheWithInitialCapacity(n);
    }
    
    public final Observable<T> cacheWithInitialCapacity(final int n) {
        return (Observable<T>)CachedObservable.from((Observable<?>)this, n);
    }
    
    public final <R> Observable<R> cast(final Class<R> clazz) {
        return this.lift((Operator<? extends R, ? super T>)new OperatorCast((Class<Object>)clazz));
    }
    
    public final <R> Observable<R> collect(final Func0<R> func0, final Action2<R, ? super T> action2) {
        return create((OnSubscribe<R>)new OnSubscribeCollect((Observable<Object>)this, (Func0<Object>)func0, (Action2<Object, ? super Object>)action2));
    }
    
    public <R> Observable<R> compose(final Transformer<? super T, ? extends R> transformer) {
        return transformer.call(this);
    }
    
    public final <R> Observable<R> concatMap(final Func1<? super T, ? extends Observable<? extends R>> func1) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable)this).scalarFlatMap(func1);
        }
        return create((OnSubscribe<R>)new OnSubscribeConcatMap(this, (Func1<? super Object, ? extends Observable<?>>)func1, 2, 0));
    }
    
    @Beta
    public final <R> Observable<R> concatMapDelayError(final Func1<? super T, ? extends Observable<? extends R>> func1) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable)this).scalarFlatMap(func1);
        }
        return create((OnSubscribe<R>)new OnSubscribeConcatMap(this, (Func1<? super Object, ? extends Observable<?>>)func1, 2, 2));
    }
    
    @Beta
    public final <R> Observable<R> concatMapEager(final Func1<? super T, ? extends Observable<? extends R>> func1) {
        return this.concatMapEager(func1, RxRingBuffer.SIZE);
    }
    
    @Beta
    public final <R> Observable<R> concatMapEager(final Func1<? super T, ? extends Observable<? extends R>> func1, final int n) {
        if (n < 1) {
            throw new IllegalArgumentException("capacityHint > 0 required but it was " + n);
        }
        return this.lift((Operator<? extends R, ? super T>)new OperatorEagerConcatMap((Func1<? super Object, ? extends Observable<?>>)func1, n, Integer.MAX_VALUE));
    }
    
    @Beta
    public final <R> Observable<R> concatMapEager(final Func1<? super T, ? extends Observable<? extends R>> func1, final int n, final int n2) {
        if (n < 1) {
            throw new IllegalArgumentException("capacityHint > 0 required but it was " + n);
        }
        if (n2 < 1) {
            throw new IllegalArgumentException("maxConcurrent > 0 required but it was " + n);
        }
        return this.lift((Operator<? extends R, ? super T>)new OperatorEagerConcatMap((Func1<? super Object, ? extends Observable<?>>)func1, n, n2));
    }
    
    public final <R> Observable<R> concatMapIterable(final Func1<? super T, ? extends Iterable<? extends R>> func1) {
        return OnSubscribeFlattenIterable.createFrom((Observable<?>)this, (Func1<? super Object, ? extends Iterable<? extends R>>)func1, RxRingBuffer.SIZE);
    }
    
    public final Observable<T> concatWith(final Observable<? extends T> observable) {
        return concat((Observable<? extends T>)this, observable);
    }
    
    public final Observable<Boolean> contains(final Object o) {
        return this.exists(InternalObservableUtils.equalsWith(o));
    }
    
    public final Observable<Integer> count() {
        return this.reduce(0, InternalObservableUtils.COUNTER);
    }
    
    public final Observable<Long> countLong() {
        return this.reduce(0L, InternalObservableUtils.LONG_COUNTER);
    }
    
    public final Observable<T> debounce(final long n, final TimeUnit timeUnit) {
        return this.debounce(n, timeUnit, Schedulers.computation());
    }
    
    public final Observable<T> debounce(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorDebounceWithTime(n, timeUnit, scheduler));
    }
    
    public final <U> Observable<T> debounce(final Func1<? super T, ? extends Observable<U>> func1) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorDebounceWithSelector((Func1<? super Object, ? extends Observable<Object>>)func1));
    }
    
    public final Observable<T> defaultIfEmpty(final T t) {
        return this.switchIfEmpty(just(t));
    }
    
    public final Observable<T> delay(final long n, final TimeUnit timeUnit) {
        return this.delay(n, timeUnit, Schedulers.computation());
    }
    
    public final Observable<T> delay(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorDelay(n, timeUnit, scheduler));
    }
    
    public final <U, V> Observable<T> delay(final Func0<? extends Observable<U>> func0, final Func1<? super T, ? extends Observable<V>> func2) {
        return this.delaySubscription((Func0<? extends Observable<Object>>)func0).lift((Operator<? extends T, ? super T>)new OperatorDelayWithSelector(this, (Func1<? super Object, ? extends Observable<Object>>)func2));
    }
    
    public final <U> Observable<T> delay(final Func1<? super T, ? extends Observable<U>> func1) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorDelayWithSelector(this, (Func1<? super Object, ? extends Observable<Object>>)func1));
    }
    
    public final Observable<T> delaySubscription(final long n, final TimeUnit timeUnit) {
        return this.delaySubscription(n, timeUnit, Schedulers.computation());
    }
    
    public final Observable<T> delaySubscription(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return create((OnSubscribe<T>)new OnSubscribeDelaySubscription(this, n, timeUnit, scheduler));
    }
    
    @Beta
    public final <U> Observable<T> delaySubscription(final Observable<U> observable) {
        if (observable == null) {
            throw new NullPointerException();
        }
        return create((OnSubscribe<T>)new OnSubscribeDelaySubscriptionOther(this, (Observable<Object>)observable));
    }
    
    public final <U> Observable<T> delaySubscription(final Func0<? extends Observable<U>> func0) {
        return create((OnSubscribe<T>)new OnSubscribeDelaySubscriptionWithSelector(this, (Func0<? extends Observable<Object>>)func0));
    }
    
    public final <T2> Observable<T2> dematerialize() {
        return this.lift((Operator<? extends T2, ? super T>)OperatorDematerialize.instance());
    }
    
    public final Observable<T> distinct() {
        return this.lift((Operator<? extends T, ? super T>)OperatorDistinct.instance());
    }
    
    public final <U> Observable<T> distinct(final Func1<? super T, ? extends U> func1) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorDistinct((Func1<? super Object, ?>)func1));
    }
    
    public final Observable<T> distinctUntilChanged() {
        return this.lift((Operator<? extends T, ? super T>)OperatorDistinctUntilChanged.instance());
    }
    
    public final <U> Observable<T> distinctUntilChanged(final Func1<? super T, ? extends U> func1) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorDistinctUntilChanged((Func1<? super Object, ?>)func1));
    }
    
    @Beta
    public final Observable<T> distinctUntilChanged(final Func2<? super T, ? super T, Boolean> func2) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorDistinctUntilChanged((Func2<? super Object, ? super Object, Boolean>)func2));
    }
    
    public final Observable<T> doAfterTerminate(final Action0 action0) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorDoAfterTerminate(action0));
    }
    
    public final Observable<T> doOnCompleted(final Action0 action0) {
        return create((OnSubscribe<T>)new OnSubscribeDoOnEach((Observable<Object>)this, new ActionObserver<Object>(Actions.empty(), (Action1<Throwable>)Actions.empty(), action0)));
    }
    
    public final Observable<T> doOnEach(final Observer<? super T> observer) {
        return create((OnSubscribe<T>)new OnSubscribeDoOnEach((Observable<Object>)this, (Observer<? super Object>)observer));
    }
    
    public final Observable<T> doOnEach(final Action1<Notification<? super T>> action1) {
        return create((OnSubscribe<T>)new OnSubscribeDoOnEach((Observable<Object>)this, new ActionNotificationObserver<Object>((Action1<Notification<? super Object>>)action1)));
    }
    
    public final Observable<T> doOnError(final Action1<Throwable> action1) {
        return create((OnSubscribe<T>)new OnSubscribeDoOnEach((Observable<Object>)this, new ActionObserver<Object>(Actions.empty(), action1, Actions.empty())));
    }
    
    public final Observable<T> doOnNext(final Action1<? super T> action1) {
        return create((OnSubscribe<T>)new OnSubscribeDoOnEach((Observable<Object>)this, new ActionObserver<Object>((Action1<? super Object>)action1, (Action1<Throwable>)Actions.empty(), Actions.empty())));
    }
    
    public final Observable<T> doOnRequest(final Action1<Long> action1) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorDoOnRequest(action1));
    }
    
    public final Observable<T> doOnSubscribe(final Action0 action0) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorDoOnSubscribe(action0));
    }
    
    public final Observable<T> doOnTerminate(final Action0 action0) {
        return create((OnSubscribe<T>)new OnSubscribeDoOnEach((Observable<Object>)this, new ActionObserver<Object>(Actions.empty(), Actions.toAction1(action0), action0)));
    }
    
    public final Observable<T> doOnUnsubscribe(final Action0 action0) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorDoOnUnsubscribe(action0));
    }
    
    public final Observable<T> elementAt(final int n) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorElementAt(n));
    }
    
    public final Observable<T> elementAtOrDefault(final int n, final T t) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorElementAt(n, t));
    }
    
    public final Observable<Boolean> exists(final Func1<? super T, Boolean> func1) {
        return this.lift((Operator<? extends Boolean, ? super T>)new OperatorAny((Func1<? super Object, Boolean>)func1, false));
    }
    
    public final Observable<T> filter(final Func1<? super T, Boolean> func1) {
        return create((OnSubscribe<T>)new OnSubscribeFilter((Observable<Object>)this, (Func1<? super Object, Boolean>)func1));
    }
    
    @Deprecated
    public final Observable<T> finallyDo(final Action0 action0) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorDoAfterTerminate(action0));
    }
    
    public final Observable<T> first() {
        return this.take(1).single();
    }
    
    public final Observable<T> first(final Func1<? super T, Boolean> func1) {
        return this.takeFirst(func1).single();
    }
    
    public final Observable<T> firstOrDefault(final T t) {
        return this.take(1).singleOrDefault(t);
    }
    
    public final Observable<T> firstOrDefault(final T t, final Func1<? super T, Boolean> func1) {
        return this.takeFirst(func1).singleOrDefault(t);
    }
    
    public final <R> Observable<R> flatMap(final Func1<? super T, ? extends Observable<? extends R>> func1) {
        if (this.getClass() == ScalarSynchronousObservable.class) {
            return ((ScalarSynchronousObservable)this).scalarFlatMap(func1);
        }
        return merge(this.map(func1));
    }
    
    public final <R> Observable<R> flatMap(final Func1<? super T, ? extends Observable<? extends R>> func1, final int n) {
        if (this.getClass() == ScalarSynchronousObservable.class) {
            return ((ScalarSynchronousObservable)this).scalarFlatMap(func1);
        }
        return merge(this.map(func1), n);
    }
    
    public final <R> Observable<R> flatMap(final Func1<? super T, ? extends Observable<? extends R>> func1, final Func1<? super Throwable, ? extends Observable<? extends R>> func2, final Func0<? extends Observable<? extends R>> func3) {
        return merge(this.mapNotification(func1, func2, func3));
    }
    
    public final <R> Observable<R> flatMap(final Func1<? super T, ? extends Observable<? extends R>> func1, final Func1<? super Throwable, ? extends Observable<? extends R>> func2, final Func0<? extends Observable<? extends R>> func3, final int n) {
        return merge(this.mapNotification(func1, func2, func3), n);
    }
    
    public final <U, R> Observable<R> flatMap(final Func1<? super T, ? extends Observable<? extends U>> func1, final Func2<? super T, ? super U, ? extends R> func2) {
        return merge(this.lift((Operator<? extends Observable<? extends R>, ? super T>)new OperatorMapPair((Func1<? super Object, ? extends Observable<?>>)func1, (Func2<? super Object, ? super Object, ?>)func2)));
    }
    
    public final <U, R> Observable<R> flatMap(final Func1<? super T, ? extends Observable<? extends U>> func1, final Func2<? super T, ? super U, ? extends R> func2, final int n) {
        return merge(this.lift((Operator<? extends Observable<? extends R>, ? super T>)new OperatorMapPair((Func1<? super Object, ? extends Observable<?>>)func1, (Func2<? super Object, ? super Object, ?>)func2)), n);
    }
    
    public final <R> Observable<R> flatMapIterable(final Func1<? super T, ? extends Iterable<? extends R>> func1) {
        return this.flatMapIterable(func1, RxRingBuffer.SIZE);
    }
    
    public final <R> Observable<R> flatMapIterable(final Func1<? super T, ? extends Iterable<? extends R>> func1, final int n) {
        return OnSubscribeFlattenIterable.createFrom((Observable<?>)this, (Func1<? super Object, ? extends Iterable<? extends R>>)func1, n);
    }
    
    public final <U, R> Observable<R> flatMapIterable(final Func1<? super T, ? extends Iterable<? extends U>> func1, final Func2<? super T, ? super U, ? extends R> func2) {
        return (Observable<R>)this.flatMap((Func1<? super T, ? extends Observable<?>>)OperatorMapPair.convertSelector((Func1<? super T, ? extends Iterable<?>>)func1), (Func2<? super T, ? super Object, ?>)func2);
    }
    
    public final <U, R> Observable<R> flatMapIterable(final Func1<? super T, ? extends Iterable<? extends U>> func1, final Func2<? super T, ? super U, ? extends R> func2, final int n) {
        return (Observable<R>)this.flatMap((Func1<? super T, ? extends Observable<?>>)OperatorMapPair.convertSelector((Func1<? super T, ? extends Iterable<?>>)func1), (Func2<? super T, ? super Object, ?>)func2, n);
    }
    
    public final void forEach(final Action1<? super T> action1) {
        this.subscribe(action1);
    }
    
    public final void forEach(final Action1<? super T> action1, final Action1<Throwable> action2) {
        this.subscribe(action1, action2);
    }
    
    public final void forEach(final Action1<? super T> action1, final Action1<Throwable> action2, final Action0 action3) {
        this.subscribe(action1, action2, action3);
    }
    
    public final <K> Observable<GroupedObservable<K, T>> groupBy(final Func1<? super T, ? extends K> func1) {
        return this.lift((Operator<? extends GroupedObservable<K, T>, ? super T>)new OperatorGroupBy((Func1<? super Object, ?>)func1));
    }
    
    public final <K, R> Observable<GroupedObservable<K, R>> groupBy(final Func1<? super T, ? extends K> func1, final Func1<? super T, ? extends R> func2) {
        return this.lift((Operator<? extends GroupedObservable<K, R>, ? super T>)new OperatorGroupBy((Func1<? super Object, ?>)func1, (Func1<? super Object, ?>)func2));
    }
    
    @Experimental
    public final <K, R> Observable<GroupedObservable<K, R>> groupBy(final Func1<? super T, ? extends K> func1, final Func1<? super T, ? extends R> func2, final Func1<Action1<K>, Map<K, Object>> func3) {
        if (func3 == null) {
            throw new NullPointerException("evictingMapFactory cannot be null");
        }
        return this.lift((Operator<? extends GroupedObservable<K, R>, ? super T>)new OperatorGroupBy((Func1<? super Object, ?>)func1, (Func1<? super Object, ?>)func2, (Func1<Action1<Object>, Map<Object, Object>>)func3));
    }
    
    public final <T2, D1, D2, R> Observable<R> groupJoin(final Observable<T2> observable, final Func1<? super T, ? extends Observable<D1>> func1, final Func1<? super T2, ? extends Observable<D2>> func2, final Func2<? super T, ? super Observable<T2>, ? extends R> func3) {
        return create((OnSubscribe<R>)new OnSubscribeGroupJoin((Observable<Object>)this, (Observable<Object>)observable, (Func1<? super Object, ? extends Observable<Object>>)func1, (Func1<? super Object, ? extends Observable<Object>>)func2, (Func2<? super Object, ? super Observable<Object>, ?>)func3));
    }
    
    public final Observable<T> ignoreElements() {
        return this.lift((Operator<? extends T, ? super T>)OperatorIgnoreElements.instance());
    }
    
    public final Observable<Boolean> isEmpty() {
        return this.lift((Operator<? extends Boolean, ? super T>)InternalObservableUtils.IS_EMPTY);
    }
    
    public final <TRight, TLeftDuration, TRightDuration, R> Observable<R> join(final Observable<TRight> observable, final Func1<T, Observable<TLeftDuration>> func1, final Func1<TRight, Observable<TRightDuration>> func2, final Func2<T, TRight, R> func3) {
        return create((OnSubscribe<R>)new OnSubscribeJoin((Observable<Object>)this, (Observable<Object>)observable, (Func1<Object, Observable<Object>>)func1, (Func1<Object, Observable<Object>>)func2, (Func2<Object, Object, Object>)func3));
    }
    
    public final Observable<T> last() {
        return this.takeLast(1).single();
    }
    
    public final Observable<T> last(final Func1<? super T, Boolean> func1) {
        return this.filter(func1).takeLast(1).single();
    }
    
    public final Observable<T> lastOrDefault(final T t) {
        return this.takeLast(1).singleOrDefault(t);
    }
    
    public final Observable<T> lastOrDefault(final T t, final Func1<? super T, Boolean> func1) {
        return this.filter(func1).takeLast(1).singleOrDefault(t);
    }
    
    public final <R> Observable<R> lift(final Operator<? extends R, ? super T> operator) {
        return create((OnSubscribe<R>)new OnSubscribeLift((OnSubscribe<Object>)this.onSubscribe, (Operator<?, ? super Object>)operator));
    }
    
    public final Observable<T> limit(final int n) {
        return this.take(n);
    }
    
    public final <R> Observable<R> map(final Func1<? super T, ? extends R> func1) {
        return create((OnSubscribe<R>)new OnSubscribeMap((Observable<Object>)this, (Func1<? super Object, ?>)func1));
    }
    
    public final Observable<Notification<T>> materialize() {
        return this.lift((Operator<? extends Notification<T>, ? super T>)OperatorMaterialize.instance());
    }
    
    public final Observable<T> mergeWith(final Observable<? extends T> observable) {
        return merge((Observable<? extends T>)this, observable);
    }
    
    public final Observable<Observable<T>> nest() {
        return just(this);
    }
    
    public final Observable<T> observeOn(final Scheduler scheduler) {
        return this.observeOn(scheduler, RxRingBuffer.SIZE);
    }
    
    public final Observable<T> observeOn(final Scheduler scheduler, final int n) {
        return this.observeOn(scheduler, false, n);
    }
    
    public final Observable<T> observeOn(final Scheduler scheduler, final boolean b) {
        return this.observeOn(scheduler, b, RxRingBuffer.SIZE);
    }
    
    public final Observable<T> observeOn(final Scheduler scheduler, final boolean b, final int n) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable)this).scalarScheduleOn(scheduler);
        }
        return this.lift((Operator<? extends T, ? super T>)new OperatorObserveOn(scheduler, b, n));
    }
    
    public final <R> Observable<R> ofType(final Class<R> clazz) {
        return (Observable<R>)this.filter(InternalObservableUtils.isInstanceOf(clazz)).cast((Class<Object>)clazz);
    }
    
    public final Observable<T> onBackpressureBuffer() {
        return this.lift((Operator<? extends T, ? super T>)OperatorOnBackpressureBuffer.instance());
    }
    
    public final Observable<T> onBackpressureBuffer(final long n) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorOnBackpressureBuffer(n));
    }
    
    public final Observable<T> onBackpressureBuffer(final long n, final Action0 action0) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorOnBackpressureBuffer(n, action0));
    }
    
    @Beta
    public final Observable<T> onBackpressureBuffer(final long n, final Action0 action0, final BackpressureOverflow.Strategy strategy) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorOnBackpressureBuffer(n, action0, strategy));
    }
    
    public final Observable<T> onBackpressureDrop() {
        return this.lift((Operator<? extends T, ? super T>)OperatorOnBackpressureDrop.instance());
    }
    
    public final Observable<T> onBackpressureDrop(final Action1<? super T> action1) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorOnBackpressureDrop((Action1<? super Object>)action1));
    }
    
    public final Observable<T> onBackpressureLatest() {
        return this.lift((Operator<? extends T, ? super T>)OperatorOnBackpressureLatest.instance());
    }
    
    public final Observable<T> onErrorResumeNext(final Observable<? extends T> observable) {
        return this.lift((Operator<? extends T, ? super T>)OperatorOnErrorResumeNextViaFunction.withOther((Observable<?>)observable));
    }
    
    public final Observable<T> onErrorResumeNext(final Func1<? super Throwable, ? extends Observable<? extends T>> func1) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorOnErrorResumeNextViaFunction(func1));
    }
    
    public final Observable<T> onErrorReturn(final Func1<? super Throwable, ? extends T> func1) {
        return this.lift((Operator<? extends T, ? super T>)OperatorOnErrorResumeNextViaFunction.withSingle((Func1<? super Throwable, ?>)func1));
    }
    
    public final Observable<T> onExceptionResumeNext(final Observable<? extends T> observable) {
        return this.lift((Operator<? extends T, ? super T>)OperatorOnErrorResumeNextViaFunction.withException((Observable<?>)observable));
    }
    
    @Experimental
    public final Observable<T> onTerminateDetach() {
        return create((OnSubscribe<T>)new OnSubscribeDetach((Observable<Object>)this));
    }
    
    public final <R> Observable<R> publish(final Func1<? super Observable<T>, ? extends Observable<R>> func1) {
        return OperatorPublish.create((Observable<?>)this, (Func1<? super Observable<Object>, ? extends Observable<R>>)func1);
    }
    
    public final ConnectableObservable<T> publish() {
        return OperatorPublish.create((Observable<? extends T>)this);
    }
    
    @Experimental
    public final Observable<T> rebatchRequests(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n > 0 required but it was " + n);
        }
        return this.lift((Operator<? extends T, ? super T>)OperatorObserveOn.rebatch(n));
    }
    
    public final <R> Observable<R> reduce(final R r, final Func2<R, ? super T, R> func2) {
        return create((OnSubscribe<R>)new OnSubscribeReduceSeed((Observable<Object>)this, r, (Func2<Object, ? super Object, Object>)func2));
    }
    
    public final Observable<T> reduce(final Func2<T, T, T> func2) {
        return create((OnSubscribe<T>)new OnSubscribeReduce((Observable<Object>)this, (Func2<Object, Object, Object>)func2));
    }
    
    public final Observable<T> repeat() {
        return OnSubscribeRedo.repeat(this);
    }
    
    public final Observable<T> repeat(final long n) {
        return OnSubscribeRedo.repeat(this, n);
    }
    
    public final Observable<T> repeat(final long n, final Scheduler scheduler) {
        return OnSubscribeRedo.repeat(this, n, scheduler);
    }
    
    public final Observable<T> repeat(final Scheduler scheduler) {
        return OnSubscribeRedo.repeat(this, scheduler);
    }
    
    public final Observable<T> repeatWhen(final Func1<? super Observable<? extends Void>, ? extends Observable<?>> func1) {
        return OnSubscribeRedo.repeat(this, (Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>>)InternalObservableUtils.createRepeatDematerializer(func1));
    }
    
    public final Observable<T> repeatWhen(final Func1<? super Observable<? extends Void>, ? extends Observable<?>> func1, final Scheduler scheduler) {
        return OnSubscribeRedo.repeat(this, (Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>>)InternalObservableUtils.createRepeatDematerializer(func1), scheduler);
    }
    
    public final <R> Observable<R> replay(final Func1<? super Observable<T>, ? extends Observable<R>> func1) {
        return OperatorReplay.multicastSelector((Func0<? extends ConnectableObservable<Object>>)InternalObservableUtils.createReplaySupplier((Observable<Object>)this), (Func1<? super Observable<Object>, ? extends Observable<R>>)func1);
    }
    
    public final <R> Observable<R> replay(final Func1<? super Observable<T>, ? extends Observable<R>> func1, final int n) {
        return OperatorReplay.multicastSelector((Func0<? extends ConnectableObservable<Object>>)InternalObservableUtils.createReplaySupplier((Observable<Object>)this, n), (Func1<? super Observable<Object>, ? extends Observable<R>>)func1);
    }
    
    public final <R> Observable<R> replay(final Func1<? super Observable<T>, ? extends Observable<R>> func1, final int n, final long n2, final TimeUnit timeUnit) {
        return this.replay(func1, n, n2, timeUnit, Schedulers.computation());
    }
    
    public final <R> Observable<R> replay(final Func1<? super Observable<T>, ? extends Observable<R>> func1, final int n, final long n2, final TimeUnit timeUnit, final Scheduler scheduler) {
        if (n < 0) {
            throw new IllegalArgumentException("bufferSize < 0");
        }
        return OperatorReplay.multicastSelector((Func0<? extends ConnectableObservable<Object>>)InternalObservableUtils.createReplaySupplier((Observable<Object>)this, n, n2, timeUnit, scheduler), (Func1<? super Observable<Object>, ? extends Observable<R>>)func1);
    }
    
    public final <R> Observable<R> replay(final Func1<? super Observable<T>, ? extends Observable<R>> func1, final int n, final Scheduler scheduler) {
        return OperatorReplay.multicastSelector((Func0<? extends ConnectableObservable<Object>>)InternalObservableUtils.createReplaySupplier((Observable<Object>)this, n), (Func1<? super Observable<Object>, ? extends Observable<R>>)InternalObservableUtils.createReplaySelectorAndObserveOn((Func1<? super Observable<Object>, ? extends Observable<Object>>)func1, scheduler));
    }
    
    public final <R> Observable<R> replay(final Func1<? super Observable<T>, ? extends Observable<R>> func1, final long n, final TimeUnit timeUnit) {
        return this.replay(func1, n, timeUnit, Schedulers.computation());
    }
    
    public final <R> Observable<R> replay(final Func1<? super Observable<T>, ? extends Observable<R>> func1, final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return OperatorReplay.multicastSelector((Func0<? extends ConnectableObservable<Object>>)InternalObservableUtils.createReplaySupplier((Observable<Object>)this, n, timeUnit, scheduler), (Func1<? super Observable<Object>, ? extends Observable<R>>)func1);
    }
    
    public final <R> Observable<R> replay(final Func1<? super Observable<T>, ? extends Observable<R>> func1, final Scheduler scheduler) {
        return OperatorReplay.multicastSelector((Func0<? extends ConnectableObservable<Object>>)InternalObservableUtils.createReplaySupplier((Observable<Object>)this), (Func1<? super Observable<Object>, ? extends Observable<R>>)InternalObservableUtils.createReplaySelectorAndObserveOn((Func1<? super Observable<Object>, ? extends Observable<Object>>)func1, scheduler));
    }
    
    public final ConnectableObservable<T> replay() {
        return OperatorReplay.create((Observable<? extends T>)this);
    }
    
    public final ConnectableObservable<T> replay(final int n) {
        return OperatorReplay.create((Observable<? extends T>)this, n);
    }
    
    public final ConnectableObservable<T> replay(final int n, final long n2, final TimeUnit timeUnit) {
        return this.replay(n, n2, timeUnit, Schedulers.computation());
    }
    
    public final ConnectableObservable<T> replay(final int n, final long n2, final TimeUnit timeUnit, final Scheduler scheduler) {
        if (n < 0) {
            throw new IllegalArgumentException("bufferSize < 0");
        }
        return OperatorReplay.create((Observable<? extends T>)this, n2, timeUnit, scheduler, n);
    }
    
    public final ConnectableObservable<T> replay(final int n, final Scheduler scheduler) {
        return OperatorReplay.observeOn(this.replay(n), scheduler);
    }
    
    public final ConnectableObservable<T> replay(final long n, final TimeUnit timeUnit) {
        return this.replay(n, timeUnit, Schedulers.computation());
    }
    
    public final ConnectableObservable<T> replay(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return OperatorReplay.create((Observable<? extends T>)this, n, timeUnit, scheduler);
    }
    
    public final ConnectableObservable<T> replay(final Scheduler scheduler) {
        return OperatorReplay.observeOn(this.replay(), scheduler);
    }
    
    public final Observable<T> retry() {
        return OnSubscribeRedo.retry(this);
    }
    
    public final Observable<T> retry(final long n) {
        return OnSubscribeRedo.retry(this, n);
    }
    
    public final Observable<T> retry(final Func2<Integer, Throwable, Boolean> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   rx/Observable.nest:()Lrx/Observable;
        //     4: new             Lrx/internal/operators/OperatorRetryWithPredicate;
        //     7: dup            
        //     8: aload_1        
        //     9: invokespecial   rx/internal/operators/OperatorRetryWithPredicate.<init>:(Lrx/functions/Func2;)V
        //    12: invokevirtual   rx/Observable.lift:(Lrx/Observable$Operator;)Lrx/Observable;
        //    15: areturn        
        //    Signature:
        //  (Lrx/functions/Func2<Ljava/lang/Integer;Ljava/lang/Throwable;Ljava/lang/Boolean;>;)Lrx/Observable<TT;>;
        // 
        // The error that occurred was:
        // 
        // java.lang.UnsupportedOperationException: The requested operation is not supported.
        //     at com.strobel.util.ContractUtils.unsupported(ContractUtils.java:27)
        //     at com.strobel.assembler.metadata.TypeReference.getRawType(TypeReference.java:276)
        //     at com.strobel.assembler.metadata.TypeReference.getRawType(TypeReference.java:271)
        //     at com.strobel.assembler.metadata.TypeReference.makeGenericType(TypeReference.java:150)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:187)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.ParameterizedType.accept(ParameterizedType.java:103)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitMethod(TypeSubstitutionVisitor.java:276)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2591)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:881)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.invalidateDependentExpressions(TypeAnalysis.java:759)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1011)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2463)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:778)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1656)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypesForVariables(TypeAnalysis.java:586)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:397)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:109)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    public final Observable<T> retryWhen(final Func1<? super Observable<? extends Throwable>, ? extends Observable<?>> func1) {
        return OnSubscribeRedo.retry(this, (Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>>)InternalObservableUtils.createRetryDematerializer(func1));
    }
    
    public final Observable<T> retryWhen(final Func1<? super Observable<? extends Throwable>, ? extends Observable<?>> func1, final Scheduler scheduler) {
        return OnSubscribeRedo.retry(this, (Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>>)InternalObservableUtils.createRetryDematerializer(func1), scheduler);
    }
    
    public final Observable<T> sample(final long n, final TimeUnit timeUnit) {
        return this.sample(n, timeUnit, Schedulers.computation());
    }
    
    public final Observable<T> sample(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorSampleWithTime(n, timeUnit, scheduler));
    }
    
    public final <U> Observable<T> sample(final Observable<U> observable) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorSampleWithObservable((Observable<Object>)observable));
    }
    
    public final <R> Observable<R> scan(final R r, final Func2<R, ? super T, R> func2) {
        return this.lift((Operator<? extends R, ? super T>)new OperatorScan(r, (Func2<Object, ? super Object, Object>)func2));
    }
    
    public final Observable<T> scan(final Func2<T, T, T> func2) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorScan((Func2<Object, ? super Object, Object>)func2));
    }
    
    public final Observable<T> serialize() {
        return this.lift((Operator<? extends T, ? super T>)OperatorSerialize.instance());
    }
    
    public final Observable<T> share() {
        return this.publish().refCount();
    }
    
    public final Observable<T> single() {
        return this.lift((Operator<? extends T, ? super T>)OperatorSingle.instance());
    }
    
    public final Observable<T> single(final Func1<? super T, Boolean> func1) {
        return this.filter(func1).single();
    }
    
    public final Observable<T> singleOrDefault(final T t) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorSingle(t));
    }
    
    public final Observable<T> singleOrDefault(final T t, final Func1<? super T, Boolean> func1) {
        return this.filter(func1).singleOrDefault(t);
    }
    
    public final Observable<T> skip(final int n) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorSkip(n));
    }
    
    public final Observable<T> skip(final long n, final TimeUnit timeUnit) {
        return this.skip(n, timeUnit, Schedulers.computation());
    }
    
    public final Observable<T> skip(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return create((OnSubscribe<T>)new OnSubscribeSkipTimed((Observable<Object>)this, n, timeUnit, scheduler));
    }
    
    public final Observable<T> skipLast(final int n) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorSkipLast(n));
    }
    
    public final Observable<T> skipLast(final long n, final TimeUnit timeUnit) {
        return this.skipLast(n, timeUnit, Schedulers.computation());
    }
    
    public final Observable<T> skipLast(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorSkipLastTimed(n, timeUnit, scheduler));
    }
    
    public final <U> Observable<T> skipUntil(final Observable<U> observable) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorSkipUntil((Observable<Object>)observable));
    }
    
    public final Observable<T> skipWhile(final Func1<? super T, Boolean> func1) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorSkipWhile(OperatorSkipWhile.toPredicate2((Func1<? super Object, Boolean>)func1)));
    }
    
    @Experimental
    public final Observable<T> sorted() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   rx/Observable.toSortedList:()Lrx/Observable;
        //     4: invokestatic    rx/internal/util/UtilityFunctions.identity:()Lrx/functions/Func1;
        //     7: invokevirtual   rx/Observable.flatMapIterable:(Lrx/functions/Func1;)Lrx/Observable;
        //    10: areturn        
        //    Signature:
        //  ()Lrx/Observable<TT;>;
        // 
        // The error that occurred was:
        // 
        // java.lang.UnsupportedOperationException: The requested operation is not supported.
        //     at com.strobel.util.ContractUtils.unsupported(ContractUtils.java:27)
        //     at com.strobel.assembler.metadata.TypeReference.getRawType(TypeReference.java:276)
        //     at com.strobel.assembler.metadata.TypeReference.getRawType(TypeReference.java:271)
        //     at com.strobel.assembler.metadata.TypeReference.makeGenericType(TypeReference.java:150)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:187)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.ParameterizedType.accept(ParameterizedType.java:103)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:173)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.ParameterizedType.accept(ParameterizedType.java:103)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:173)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.ParameterizedType.accept(ParameterizedType.java:103)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitMethod(TypeSubstitutionVisitor.java:276)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2591)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2463)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:778)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1656)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    @Experimental
    public final Observable<T> sorted(final Func2<? super T, ? super T, Integer> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: aload_1        
        //     2: invokevirtual   rx/Observable.toSortedList:(Lrx/functions/Func2;)Lrx/Observable;
        //     5: invokestatic    rx/internal/util/UtilityFunctions.identity:()Lrx/functions/Func1;
        //     8: invokevirtual   rx/Observable.flatMapIterable:(Lrx/functions/Func1;)Lrx/Observable;
        //    11: areturn        
        //    Signature:
        //  (Lrx/functions/Func2<-TT;-TT;Ljava/lang/Integer;>;)Lrx/Observable<TT;>;
        // 
        // The error that occurred was:
        // 
        // java.lang.UnsupportedOperationException: The requested operation is not supported.
        //     at com.strobel.util.ContractUtils.unsupported(ContractUtils.java:27)
        //     at com.strobel.assembler.metadata.TypeReference.getRawType(TypeReference.java:276)
        //     at com.strobel.assembler.metadata.TypeReference.getRawType(TypeReference.java:271)
        //     at com.strobel.assembler.metadata.TypeReference.makeGenericType(TypeReference.java:150)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:187)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.ParameterizedType.accept(ParameterizedType.java:103)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:173)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.ParameterizedType.accept(ParameterizedType.java:103)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:173)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.ParameterizedType.accept(ParameterizedType.java:103)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitMethod(TypeSubstitutionVisitor.java:276)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2591)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2463)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:778)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1656)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    public final Observable<T> startWith(final Iterable<T> iterable) {
        return concat(from((Iterable<? extends T>)iterable), (Observable<? extends T>)this);
    }
    
    public final Observable<T> startWith(final T t) {
        return concat((Observable<? extends T>)just((T)t), (Observable<? extends T>)this);
    }
    
    public final Observable<T> startWith(final T t, final T t2) {
        return concat((Observable<? extends T>)just(t, (T)t2), (Observable<? extends T>)this);
    }
    
    public final Observable<T> startWith(final T t, final T t2, final T t3) {
        return concat((Observable<? extends T>)just(t, t2, (T)t3), (Observable<? extends T>)this);
    }
    
    public final Observable<T> startWith(final T t, final T t2, final T t3, final T t4) {
        return concat((Observable<? extends T>)just(t, t2, t3, (T)t4), (Observable<? extends T>)this);
    }
    
    public final Observable<T> startWith(final T t, final T t2, final T t3, final T t4, final T t5) {
        return concat((Observable<? extends T>)just(t, t2, t3, t4, (T)t5), (Observable<? extends T>)this);
    }
    
    public final Observable<T> startWith(final T t, final T t2, final T t3, final T t4, final T t5, final T t6) {
        return concat((Observable<? extends T>)just(t, t2, t3, t4, t5, (T)t6), (Observable<? extends T>)this);
    }
    
    public final Observable<T> startWith(final T t, final T t2, final T t3, final T t4, final T t5, final T t6, final T t7) {
        return concat((Observable<? extends T>)just(t, t2, t3, t4, t5, t6, (T)t7), (Observable<? extends T>)this);
    }
    
    public final Observable<T> startWith(final T t, final T t2, final T t3, final T t4, final T t5, final T t6, final T t7, final T t8) {
        return concat((Observable<? extends T>)just(t, t2, t3, t4, t5, t6, t7, (T)t8), (Observable<? extends T>)this);
    }
    
    public final Observable<T> startWith(final T t, final T t2, final T t3, final T t4, final T t5, final T t6, final T t7, final T t8, final T t9) {
        return concat((Observable<? extends T>)just(t, t2, t3, t4, t5, t6, t7, t8, (T)t9), (Observable<? extends T>)this);
    }
    
    public final Observable<T> startWith(final Observable<T> observable) {
        return concat((Observable<? extends T>)observable, (Observable<? extends T>)this);
    }
    
    public final Subscription subscribe() {
        return this.subscribe(new ActionSubscriber<Object>(Actions.empty(), InternalObservableUtils.ERROR_NOT_IMPLEMENTED, Actions.empty()));
    }
    
    public final Subscription subscribe(final Observer<? super T> observer) {
        if (observer instanceof Subscriber) {
            return this.subscribe((Subscriber<? super T>)observer);
        }
        if (observer == null) {
            throw new NullPointerException("observer is null");
        }
        return this.subscribe(new ObserverSubscriber<Object>(observer));
    }
    
    public final Subscription subscribe(final Subscriber<? super T> subscriber) {
        return subscribe((Subscriber<? super Object>)subscriber, (Observable<Object>)this);
    }
    
    public final Subscription subscribe(final Action1<? super T> action1) {
        if (action1 == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }
        return this.subscribe(new ActionSubscriber<Object>(action1, InternalObservableUtils.ERROR_NOT_IMPLEMENTED, Actions.empty()));
    }
    
    public final Subscription subscribe(final Action1<? super T> action1, final Action1<Throwable> action2) {
        if (action1 == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }
        if (action2 == null) {
            throw new IllegalArgumentException("onError can not be null");
        }
        return this.subscribe(new ActionSubscriber<Object>(action1, action2, Actions.empty()));
    }
    
    public final Subscription subscribe(final Action1<? super T> action1, final Action1<Throwable> action2, final Action0 action3) {
        if (action1 == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }
        if (action2 == null) {
            throw new IllegalArgumentException("onError can not be null");
        }
        if (action3 == null) {
            throw new IllegalArgumentException("onComplete can not be null");
        }
        return this.subscribe(new ActionSubscriber<Object>(action1, action2, action3));
    }
    
    public final Observable<T> subscribeOn(final Scheduler scheduler) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable)this).scalarScheduleOn(scheduler);
        }
        return create((OnSubscribe<T>)new OperatorSubscribeOn((Observable<Object>)this, scheduler));
    }
    
    public final Observable<T> switchIfEmpty(final Observable<? extends T> observable) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorSwitchIfEmpty(observable));
    }
    
    public final <R> Observable<R> switchMap(final Func1<? super T, ? extends Observable<? extends R>> func1) {
        return switchOnNext(this.map(func1));
    }
    
    @Beta
    public final <R> Observable<R> switchMapDelayError(final Func1<? super T, ? extends Observable<? extends R>> func1) {
        return switchOnNextDelayError(this.map(func1));
    }
    
    public final Observable<T> take(final int n) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorTake(n));
    }
    
    public final Observable<T> take(final long n, final TimeUnit timeUnit) {
        return this.take(n, timeUnit, Schedulers.computation());
    }
    
    public final Observable<T> take(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorTakeTimed(n, timeUnit, scheduler));
    }
    
    public final Observable<T> takeFirst(final Func1<? super T, Boolean> func1) {
        return this.filter(func1).take(1);
    }
    
    public final Observable<T> takeLast(final int n) {
        if (n == 0) {
            return this.ignoreElements();
        }
        if (n == 1) {
            return create((OnSubscribe<T>)new OnSubscribeTakeLastOne((Observable<Object>)this));
        }
        return this.lift((Operator<? extends T, ? super T>)new OperatorTakeLast(n));
    }
    
    public final Observable<T> takeLast(final int n, final long n2, final TimeUnit timeUnit) {
        return this.takeLast(n, n2, timeUnit, Schedulers.computation());
    }
    
    public final Observable<T> takeLast(final int n, final long n2, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorTakeLastTimed(n, n2, timeUnit, scheduler));
    }
    
    public final Observable<T> takeLast(final long n, final TimeUnit timeUnit) {
        return this.takeLast(n, timeUnit, Schedulers.computation());
    }
    
    public final Observable<T> takeLast(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorTakeLastTimed(n, timeUnit, scheduler));
    }
    
    public final Observable<List<T>> takeLastBuffer(final int n) {
        return this.takeLast(n).toList();
    }
    
    public final Observable<List<T>> takeLastBuffer(final int n, final long n2, final TimeUnit timeUnit) {
        return this.takeLast(n, n2, timeUnit).toList();
    }
    
    public final Observable<List<T>> takeLastBuffer(final int n, final long n2, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.takeLast(n, n2, timeUnit, scheduler).toList();
    }
    
    public final Observable<List<T>> takeLastBuffer(final long n, final TimeUnit timeUnit) {
        return this.takeLast(n, timeUnit).toList();
    }
    
    public final Observable<List<T>> takeLastBuffer(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.takeLast(n, timeUnit, scheduler).toList();
    }
    
    public final <E> Observable<T> takeUntil(final Observable<? extends E> observable) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorTakeUntil(observable));
    }
    
    public final Observable<T> takeUntil(final Func1<? super T, Boolean> func1) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorTakeUntilPredicate((Func1<? super Object, Boolean>)func1));
    }
    
    public final Observable<T> takeWhile(final Func1<? super T, Boolean> func1) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorTakeWhile((Func1<? super Object, Boolean>)func1));
    }
    
    public final Observable<T> throttleFirst(final long n, final TimeUnit timeUnit) {
        return this.throttleFirst(n, timeUnit, Schedulers.computation());
    }
    
    public final Observable<T> throttleFirst(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorThrottleFirst(n, timeUnit, scheduler));
    }
    
    public final Observable<T> throttleLast(final long n, final TimeUnit timeUnit) {
        return this.sample(n, timeUnit);
    }
    
    public final Observable<T> throttleLast(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.sample(n, timeUnit, scheduler);
    }
    
    public final Observable<T> throttleWithTimeout(final long n, final TimeUnit timeUnit) {
        return this.debounce(n, timeUnit);
    }
    
    public final Observable<T> throttleWithTimeout(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.debounce(n, timeUnit, scheduler);
    }
    
    public final Observable<TimeInterval<T>> timeInterval() {
        return this.timeInterval(Schedulers.computation());
    }
    
    public final Observable<TimeInterval<T>> timeInterval(final Scheduler scheduler) {
        return this.lift((Operator<? extends TimeInterval<T>, ? super T>)new OperatorTimeInterval(scheduler));
    }
    
    public final Observable<T> timeout(final long n, final TimeUnit timeUnit) {
        return this.timeout(n, timeUnit, null, Schedulers.computation());
    }
    
    public final Observable<T> timeout(final long n, final TimeUnit timeUnit, final Observable<? extends T> observable) {
        return this.timeout(n, timeUnit, observable, Schedulers.computation());
    }
    
    public final Observable<T> timeout(final long n, final TimeUnit timeUnit, final Observable<? extends T> observable, final Scheduler scheduler) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorTimeout(n, timeUnit, observable, scheduler));
    }
    
    public final Observable<T> timeout(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.timeout(n, timeUnit, null, scheduler);
    }
    
    public final <U, V> Observable<T> timeout(final Func0<? extends Observable<U>> func0, final Func1<? super T, ? extends Observable<V>> func2) {
        return this.timeout((Func0<? extends Observable<Object>>)func0, (Func1<? super T, ? extends Observable<Object>>)func2, (Observable<? extends T>)null);
    }
    
    public final <U, V> Observable<T> timeout(final Func0<? extends Observable<U>> func0, final Func1<? super T, ? extends Observable<V>> func2, final Observable<? extends T> observable) {
        if (func2 == null) {
            throw new NullPointerException("timeoutSelector is null");
        }
        return this.lift((Operator<? extends T, ? super T>)new OperatorTimeoutWithSelector((Func0<? extends Observable<Object>>)func0, (Func1<? super Object, ? extends Observable<Object>>)func2, observable));
    }
    
    public final <V> Observable<T> timeout(final Func1<? super T, ? extends Observable<V>> func1) {
        return this.timeout((Func0<? extends Observable<Object>>)null, (Func1<? super T, ? extends Observable<Object>>)func1, (Observable<? extends T>)null);
    }
    
    public final <V> Observable<T> timeout(final Func1<? super T, ? extends Observable<V>> func1, final Observable<? extends T> observable) {
        return this.timeout((Func0<? extends Observable<Object>>)null, (Func1<? super T, ? extends Observable<Object>>)func1, observable);
    }
    
    public final Observable<Timestamped<T>> timestamp() {
        return this.timestamp(Schedulers.computation());
    }
    
    public final Observable<Timestamped<T>> timestamp(final Scheduler scheduler) {
        return this.lift((Operator<? extends Timestamped<T>, ? super T>)new OperatorTimestamp(scheduler));
    }
    
    @Experimental
    public final <R> R to(final Func1<? super Observable<T>, R> func1) {
        return func1.call(this);
    }
    
    public final BlockingObservable<T> toBlocking() {
        return BlockingObservable.from((Observable<? extends T>)this);
    }
    
    @Beta
    public Completable toCompletable() {
        return Completable.fromObservable(this);
    }
    
    public final Observable<List<T>> toList() {
        return this.lift((Operator<? extends List<T>, ? super T>)OperatorToObservableList.instance());
    }
    
    public final <K> Observable<Map<K, T>> toMap(final Func1<? super T, ? extends K> func1) {
        return create((OnSubscribe<Map<K, T>>)new OnSubscribeToMap((Observable<Object>)this, (Func1<? super Object, ?>)func1, (Func1<? super Object, ?>)UtilityFunctions.identity()));
    }
    
    public final <K, V> Observable<Map<K, V>> toMap(final Func1<? super T, ? extends K> func1, final Func1<? super T, ? extends V> func2) {
        return create((OnSubscribe<Map<K, V>>)new OnSubscribeToMap((Observable<Object>)this, (Func1<? super Object, ?>)func1, (Func1<? super Object, ?>)func2));
    }
    
    public final <K, V> Observable<Map<K, V>> toMap(final Func1<? super T, ? extends K> func1, final Func1<? super T, ? extends V> func2, final Func0<? extends Map<K, V>> func3) {
        return create((OnSubscribe<Map<K, V>>)new OnSubscribeToMap((Observable<Object>)this, (Func1<? super Object, ?>)func1, (Func1<? super Object, ?>)func2, (Func0<? extends Map<Object, Object>>)func3));
    }
    
    public final <K> Observable<Map<K, Collection<T>>> toMultimap(final Func1<? super T, ? extends K> func1) {
        return create((OnSubscribe<Map<K, Collection<T>>>)new OnSubscribeToMultimap((Observable<Object>)this, (Func1<? super Object, ?>)func1, (Func1<? super Object, ?>)UtilityFunctions.identity()));
    }
    
    public final <K, V> Observable<Map<K, Collection<V>>> toMultimap(final Func1<? super T, ? extends K> func1, final Func1<? super T, ? extends V> func2) {
        return create((OnSubscribe<Map<K, Collection<V>>>)new OnSubscribeToMultimap((Observable<Object>)this, (Func1<? super Object, ?>)func1, (Func1<? super Object, ?>)func2));
    }
    
    public final <K, V> Observable<Map<K, Collection<V>>> toMultimap(final Func1<? super T, ? extends K> func1, final Func1<? super T, ? extends V> func2, final Func0<? extends Map<K, Collection<V>>> func3) {
        return create((OnSubscribe<Map<K, Collection<V>>>)new OnSubscribeToMultimap((Observable<Object>)this, (Func1<? super Object, ?>)func1, (Func1<? super Object, ?>)func2, (Func0<? extends Map<Object, Collection<Object>>>)func3));
    }
    
    public final <K, V> Observable<Map<K, Collection<V>>> toMultimap(final Func1<? super T, ? extends K> func1, final Func1<? super T, ? extends V> func2, final Func0<? extends Map<K, Collection<V>>> func3, final Func1<? super K, ? extends Collection<V>> func4) {
        return create((OnSubscribe<Map<K, Collection<V>>>)new OnSubscribeToMultimap((Observable<Object>)this, (Func1<? super Object, ?>)func1, (Func1<? super Object, ?>)func2, (Func0<? extends Map<Object, Collection<Object>>>)func3, (Func1<? super Object, ? extends Collection<Object>>)func4));
    }
    
    public Single<T> toSingle() {
        return new Single<T>((Single.OnSubscribe<T>)OnSubscribeSingle.create((Observable<Object>)this));
    }
    
    public final Observable<List<T>> toSortedList() {
        return this.lift((Operator<? extends List<T>, ? super T>)new OperatorToObservableSortedList(10));
    }
    
    @Beta
    public final Observable<List<T>> toSortedList(final int n) {
        return this.lift((Operator<? extends List<T>, ? super T>)new OperatorToObservableSortedList(n));
    }
    
    public final Observable<List<T>> toSortedList(final Func2<? super T, ? super T, Integer> func2) {
        return this.lift((Operator<? extends List<T>, ? super T>)new OperatorToObservableSortedList((Func2<? super Object, ? super Object, Integer>)func2, 10));
    }
    
    @Beta
    public final Observable<List<T>> toSortedList(final Func2<? super T, ? super T, Integer> func2, final int n) {
        return this.lift((Operator<? extends List<T>, ? super T>)new OperatorToObservableSortedList((Func2<? super Object, ? super Object, Integer>)func2, n));
    }
    
    public final Subscription unsafeSubscribe(final Subscriber<? super T> subscriber) {
        try {
            subscriber.onStart();
            RxJavaHooks.onObservableStart(this, this.onSubscribe).call(subscriber);
            return RxJavaHooks.onObservableReturn(subscriber);
        }
        catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            try {
                subscriber.onError(RxJavaHooks.onObservableError(t));
                return Subscriptions.unsubscribed();
            }
            catch (Throwable t2) {
                Exceptions.throwIfFatal(t2);
                final OnErrorFailedException ex = new OnErrorFailedException("Error occurred attempting to subscribe [" + t.getMessage() + "] and then again while trying to pass to onError.", t2);
                RxJavaHooks.onObservableError(ex);
                throw ex;
            }
        }
    }
    
    public final Observable<T> unsubscribeOn(final Scheduler scheduler) {
        return this.lift((Operator<? extends T, ? super T>)new OperatorUnsubscribeOn(scheduler));
    }
    
    public final Observable<Observable<T>> window(final int n) {
        return this.window(n, n);
    }
    
    public final Observable<Observable<T>> window(final int n, final int n2) {
        if (n <= 0) {
            throw new IllegalArgumentException("count > 0 required but it was " + n);
        }
        if (n2 <= 0) {
            throw new IllegalArgumentException("skip > 0 required but it was " + n2);
        }
        return this.lift((Operator<? extends Observable<T>, ? super T>)new OperatorWindowWithSize(n, n2));
    }
    
    public final Observable<Observable<T>> window(final long n, final long n2, final TimeUnit timeUnit) {
        return this.window(n, n2, timeUnit, Integer.MAX_VALUE, Schedulers.computation());
    }
    
    public final Observable<Observable<T>> window(final long n, final long n2, final TimeUnit timeUnit, final int n3, final Scheduler scheduler) {
        return this.lift((Operator<? extends Observable<T>, ? super T>)new OperatorWindowWithTime(n, n2, timeUnit, n3, scheduler));
    }
    
    public final Observable<Observable<T>> window(final long n, final long n2, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.window(n, n2, timeUnit, Integer.MAX_VALUE, scheduler);
    }
    
    public final Observable<Observable<T>> window(final long n, final TimeUnit timeUnit) {
        return this.window(n, n, timeUnit, Schedulers.computation());
    }
    
    public final Observable<Observable<T>> window(final long n, final TimeUnit timeUnit, final int n2) {
        return this.window(n, timeUnit, n2, Schedulers.computation());
    }
    
    public final Observable<Observable<T>> window(final long n, final TimeUnit timeUnit, final int n2, final Scheduler scheduler) {
        return this.window(n, n, timeUnit, n2, scheduler);
    }
    
    public final Observable<Observable<T>> window(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return this.window(n, timeUnit, Integer.MAX_VALUE, scheduler);
    }
    
    public final <U> Observable<Observable<T>> window(final Observable<U> observable) {
        return this.lift((Operator<? extends Observable<T>, ? super T>)new OperatorWindowWithObservable((Observable<Object>)observable));
    }
    
    public final <TOpening, TClosing> Observable<Observable<T>> window(final Observable<? extends TOpening> observable, final Func1<? super TOpening, ? extends Observable<? extends TClosing>> func1) {
        return this.lift((Operator<? extends Observable<T>, ? super T>)new OperatorWindowWithStartEndObservable(observable, (Func1<? super Object, ? extends Observable<?>>)func1));
    }
    
    public final <TClosing> Observable<Observable<T>> window(final Func0<? extends Observable<? extends TClosing>> func0) {
        return this.lift((Operator<? extends Observable<T>, ? super T>)new OperatorWindowWithObservableFactory(func0));
    }
    
    @Experimental
    public final <R> Observable<R> withLatestFrom(final Iterable<Observable<?>> iterable, final FuncN<R> funcN) {
        return create((OnSubscribe<R>)new OperatorWithLatestFromMany((Observable<Object>)this, null, iterable, (FuncN<Object>)funcN));
    }
    
    @Experimental
    public final <T1, T2, T3, T4, T5, T6, T7, T8, R> Observable<R> withLatestFrom(final Observable<T1> observable, final Observable<T2> observable2, final Observable<T3> observable3, final Observable<T4> observable4, final Observable<T5> observable5, final Observable<T6> observable6, final Observable<T7> observable7, final Observable<T8> observable8, final Func9<? super T, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, R> func9) {
        return create((OnSubscribe<R>)new OperatorWithLatestFromMany((Observable<Object>)this, new Observable[] { observable, observable2, observable3, observable4, observable5, observable6, observable7, observable8 }, null, Functions.fromFunc((Func9<? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ?>)func9)));
    }
    
    @Experimental
    public final <T1, T2, T3, T4, T5, T6, T7, R> Observable<R> withLatestFrom(final Observable<T1> observable, final Observable<T2> observable2, final Observable<T3> observable3, final Observable<T4> observable4, final Observable<T5> observable5, final Observable<T6> observable6, final Observable<T7> observable7, final Func8<? super T, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, R> func8) {
        return create((OnSubscribe<R>)new OperatorWithLatestFromMany((Observable<Object>)this, new Observable[] { observable, observable2, observable3, observable4, observable5, observable6, observable7 }, null, Functions.fromFunc((Func8<? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ?>)func8)));
    }
    
    @Experimental
    public final <T1, T2, T3, T4, T5, T6, R> Observable<R> withLatestFrom(final Observable<T1> observable, final Observable<T2> observable2, final Observable<T3> observable3, final Observable<T4> observable4, final Observable<T5> observable5, final Observable<T6> observable6, final Func7<? super T, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, R> func7) {
        return create((OnSubscribe<R>)new OperatorWithLatestFromMany((Observable<Object>)this, new Observable[] { observable, observable2, observable3, observable4, observable5, observable6 }, null, Functions.fromFunc((Func7<? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ?>)func7)));
    }
    
    @Experimental
    public final <T1, T2, T3, T4, T5, R> Observable<R> withLatestFrom(final Observable<T1> observable, final Observable<T2> observable2, final Observable<T3> observable3, final Observable<T4> observable4, final Observable<T5> observable5, final Func6<? super T, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, R> func6) {
        return create((OnSubscribe<R>)new OperatorWithLatestFromMany((Observable<Object>)this, new Observable[] { observable, observable2, observable3, observable4, observable5 }, null, Functions.fromFunc((Func6<? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ?>)func6)));
    }
    
    @Experimental
    public final <T1, T2, T3, T4, R> Observable<R> withLatestFrom(final Observable<T1> observable, final Observable<T2> observable2, final Observable<T3> observable3, final Observable<T4> observable4, final Func5<? super T, ? super T1, ? super T2, ? super T3, ? super T4, R> func5) {
        return create((OnSubscribe<R>)new OperatorWithLatestFromMany((Observable<Object>)this, new Observable[] { observable, observable2, observable3, observable4 }, null, Functions.fromFunc((Func5<? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ?>)func5)));
    }
    
    @Experimental
    public final <T1, T2, T3, R> Observable<R> withLatestFrom(final Observable<T1> observable, final Observable<T2> observable2, final Observable<T3> observable3, final Func4<? super T, ? super T1, ? super T2, ? super T3, R> func4) {
        return create((OnSubscribe<R>)new OperatorWithLatestFromMany((Observable<Object>)this, new Observable[] { observable, observable2, observable3 }, null, Functions.fromFunc((Func4<? super Object, ? super Object, ? super Object, ? super Object, ?>)func4)));
    }
    
    @Experimental
    public final <T1, T2, R> Observable<R> withLatestFrom(final Observable<T1> observable, final Observable<T2> observable2, final Func3<? super T, ? super T1, ? super T2, R> func3) {
        return create((OnSubscribe<R>)new OperatorWithLatestFromMany((Observable<Object>)this, new Observable[] { observable, observable2 }, null, Functions.fromFunc((Func3<? super Object, ? super Object, ? super Object, ?>)func3)));
    }
    
    @Experimental
    public final <U, R> Observable<R> withLatestFrom(final Observable<? extends U> observable, final Func2<? super T, ? super U, ? extends R> func2) {
        return this.lift((Operator<? extends R, ? super T>)new OperatorWithLatestFrom(observable, (Func2<? super Object, ? super Object, ?>)func2));
    }
    
    @Experimental
    public final <R> Observable<R> withLatestFrom(final Observable<?>[] array, final FuncN<R> funcN) {
        return create((OnSubscribe<R>)new OperatorWithLatestFromMany((Observable<Object>)this, array, null, (FuncN<Object>)funcN));
    }
    
    public final <T2, R> Observable<R> zipWith(final Iterable<? extends T2> iterable, final Func2<? super T, ? super T2, ? extends R> func2) {
        return this.lift((Operator<? extends R, ? super T>)new OperatorZipIterable(iterable, (Func2<? super Object, ? super Object, ?>)func2));
    }
    
    public final <T2, R> Observable<R> zipWith(final Observable<? extends T2> observable, final Func2<? super T, ? super T2, ? extends R> func2) {
        return zip((Observable<?>)this, (Observable<?>)observable, (Func2<? super Object, ? super Object, ? extends R>)func2);
    }
    
    public interface OnSubscribe<T> extends Action1<Subscriber<? super T>>
    {
    }
    
    static final class OnSubscribeExtend<T> implements OnSubscribe<T>
    {
        final Observable<T> parent;
        
        OnSubscribeExtend(final Observable<T> parent) {
            this.parent = parent;
        }
        
        @Override
        public void call(final Subscriber<? super T> subscriber) {
            subscriber.add(Observable.subscribe(subscriber, this.parent));
        }
    }
    
    public interface Operator<R, T> extends Func1<Subscriber<? super R>, Subscriber<? super T>>
    {
    }
    
    public interface Transformer<T, R> extends Func1<Observable<T>, Observable<R>>
    {
    }
}
