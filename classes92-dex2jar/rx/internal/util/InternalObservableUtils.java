// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util;

import java.util.List;
import rx.exceptions.OnErrorNotImplementedException;
import java.util.concurrent.TimeUnit;
import rx.observables.ConnectableObservable;
import rx.functions.Func0;
import rx.Scheduler;
import rx.Notification;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Action2;
import rx.internal.operators.OperatorAny;
import rx.Observable;
import rx.functions.Action1;

public enum InternalObservableUtils
{
    public static final PlusOneFunc2 COUNTER;
    static final NotificationErrorExtractor ERROR_EXTRACTOR;
    public static final Action1<Throwable> ERROR_NOT_IMPLEMENTED;
    public static final Observable.Operator<Boolean, Object> IS_EMPTY;
    public static final PlusOneLongFunc2 LONG_COUNTER;
    public static final ObjectEqualsFunc2 OBJECT_EQUALS;
    static final ReturnsVoidFunc1 RETURNS_VOID;
    public static final ToArrayFunc1 TO_ARRAY;
    
    static {
        LONG_COUNTER = new PlusOneLongFunc2();
        OBJECT_EQUALS = new ObjectEqualsFunc2();
        TO_ARRAY = new ToArrayFunc1();
        RETURNS_VOID = new ReturnsVoidFunc1();
        COUNTER = new PlusOneFunc2();
        ERROR_EXTRACTOR = new NotificationErrorExtractor();
        ERROR_NOT_IMPLEMENTED = new ErrorNotImplementedAction();
        IS_EMPTY = new OperatorAny<Object>(UtilityFunctions.alwaysTrue(), true);
    }
    
    public static <T, R> Func2<R, T, R> createCollectorCaller(final Action2<R, ? super T> action2) {
        return new CollectorCaller<T, R>(action2);
    }
    
    public static Func1<Observable<? extends Notification<?>>, Observable<?>> createRepeatDematerializer(final Func1<? super Observable<? extends Void>, ? extends Observable<?>> func1) {
        return new RepeatNotificationDematerializer(func1);
    }
    
    public static <T, R> Func1<Observable<T>, Observable<R>> createReplaySelectorAndObserveOn(final Func1<? super Observable<T>, ? extends Observable<R>> func1, final Scheduler scheduler) {
        return new SelectorAndObserveOn<T, R>(func1, scheduler);
    }
    
    public static <T> Func0<ConnectableObservable<T>> createReplaySupplier(final Observable<T> observable) {
        return new ReplaySupplierNoParams<T>(observable);
    }
    
    public static <T> Func0<ConnectableObservable<T>> createReplaySupplier(final Observable<T> observable, final int n) {
        return new ReplaySupplierBuffer<T>(observable, n);
    }
    
    public static <T> Func0<ConnectableObservable<T>> createReplaySupplier(final Observable<T> observable, final int n, final long n2, final TimeUnit timeUnit, final Scheduler scheduler) {
        return new ReplaySupplierTime<T>(observable, n, n2, timeUnit, scheduler);
    }
    
    public static <T> Func0<ConnectableObservable<T>> createReplaySupplier(final Observable<T> observable, final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return new ReplaySupplierBufferTime<T>(observable, n, timeUnit, scheduler);
    }
    
    public static Func1<Observable<? extends Notification<?>>, Observable<?>> createRetryDematerializer(final Func1<? super Observable<? extends Throwable>, ? extends Observable<?>> func1) {
        return new RetryNotificationDematerializer(func1);
    }
    
    public static Func1<Object, Boolean> equalsWith(final Object o) {
        return new EqualsWithFunc1(o);
    }
    
    public static Func1<Object, Boolean> isInstanceOf(final Class<?> clazz) {
        return new IsInstanceOfFunc1(clazz);
    }
    
    static final class CollectorCaller<T, R> implements Func2<R, T, R>
    {
        final Action2<R, ? super T> collector;
        
        public CollectorCaller(final Action2<R, ? super T> collector) {
            this.collector = collector;
        }
        
        @Override
        public R call(final R r, final T t) {
            this.collector.call(r, (Object)t);
            return r;
        }
    }
    
    static final class EqualsWithFunc1 implements Func1<Object, Boolean>
    {
        final Object other;
        
        public EqualsWithFunc1(final Object other) {
            this.other = other;
        }
        
        @Override
        public Boolean call(final Object o) {
            return o == this.other || (o != null && o.equals(this.other));
        }
    }
    
    static final class ErrorNotImplementedAction implements Action1<Throwable>
    {
        @Override
        public void call(final Throwable t) {
            throw new OnErrorNotImplementedException(t);
        }
    }
    
    static final class IsInstanceOfFunc1 implements Func1<Object, Boolean>
    {
        final Class<?> clazz;
        
        public IsInstanceOfFunc1(final Class<?> clazz) {
            this.clazz = clazz;
        }
        
        @Override
        public Boolean call(final Object o) {
            return this.clazz.isInstance(o);
        }
    }
    
    static final class NotificationErrorExtractor implements Func1<Notification<?>, Throwable>
    {
        @Override
        public Throwable call(final Notification<?> notification) {
            return notification.getThrowable();
        }
    }
    
    static final class ObjectEqualsFunc2 implements Func2<Object, Object, Boolean>
    {
        @Override
        public Boolean call(final Object o, final Object o2) {
            return o == o2 || (o != null && o.equals(o2));
        }
    }
    
    static final class PlusOneFunc2 implements Func2<Integer, Object, Integer>
    {
        @Override
        public Integer call(final Integer n, final Object o) {
            return n + 1;
        }
    }
    
    static final class PlusOneLongFunc2 implements Func2<Long, Object, Long>
    {
        @Override
        public Long call(final Long n, final Object o) {
            return n + 1L;
        }
    }
    
    static final class RepeatNotificationDematerializer implements Func1<Observable<? extends Notification<?>>, Observable<?>>
    {
        final Func1<? super Observable<? extends Void>, ? extends Observable<?>> notificationHandler;
        
        public RepeatNotificationDematerializer(final Func1<? super Observable<? extends Void>, ? extends Observable<?>> notificationHandler) {
            this.notificationHandler = notificationHandler;
        }
        
        @Override
        public Observable<?> call(final Observable<? extends Notification<?>> observable) {
            return (Observable<?>)this.notificationHandler.call((Object)observable.map((Func1<? super Notification<?>, ?>)InternalObservableUtils.RETURNS_VOID));
        }
    }
    
    static final class ReplaySupplierBuffer<T> implements Func0<ConnectableObservable<T>>
    {
        private final int bufferSize;
        private final Observable<T> source;
        
        ReplaySupplierBuffer(final Observable<T> source, final int bufferSize) {
            this.source = source;
            this.bufferSize = bufferSize;
        }
        
        @Override
        public ConnectableObservable<T> call() {
            return this.source.replay(this.bufferSize);
        }
    }
    
    static final class ReplaySupplierBufferTime<T> implements Func0<ConnectableObservable<T>>
    {
        private final Scheduler scheduler;
        private final Observable<T> source;
        private final long time;
        private final TimeUnit unit;
        
        ReplaySupplierBufferTime(final Observable<T> source, final long time, final TimeUnit unit, final Scheduler scheduler) {
            this.unit = unit;
            this.source = source;
            this.time = time;
            this.scheduler = scheduler;
        }
        
        @Override
        public ConnectableObservable<T> call() {
            return this.source.replay(this.time, this.unit, this.scheduler);
        }
    }
    
    static final class ReplaySupplierNoParams<T> implements Func0<ConnectableObservable<T>>
    {
        private final Observable<T> source;
        
        ReplaySupplierNoParams(final Observable<T> source) {
            this.source = source;
        }
        
        @Override
        public ConnectableObservable<T> call() {
            return this.source.replay();
        }
    }
    
    static final class ReplaySupplierTime<T> implements Func0<ConnectableObservable<T>>
    {
        private final int bufferSize;
        private final Scheduler scheduler;
        private final Observable<T> source;
        private final long time;
        private final TimeUnit unit;
        
        ReplaySupplierTime(final Observable<T> source, final int bufferSize, final long time, final TimeUnit unit, final Scheduler scheduler) {
            this.time = time;
            this.unit = unit;
            this.scheduler = scheduler;
            this.bufferSize = bufferSize;
            this.source = source;
        }
        
        @Override
        public ConnectableObservable<T> call() {
            return this.source.replay(this.bufferSize, this.time, this.unit, this.scheduler);
        }
    }
    
    static final class RetryNotificationDematerializer implements Func1<Observable<? extends Notification<?>>, Observable<?>>
    {
        final Func1<? super Observable<? extends Throwable>, ? extends Observable<?>> notificationHandler;
        
        public RetryNotificationDematerializer(final Func1<? super Observable<? extends Throwable>, ? extends Observable<?>> notificationHandler) {
            this.notificationHandler = notificationHandler;
        }
        
        @Override
        public Observable<?> call(final Observable<? extends Notification<?>> observable) {
            return (Observable<?>)this.notificationHandler.call((Object)observable.map((Func1<? super Notification<?>, ?>)InternalObservableUtils.ERROR_EXTRACTOR));
        }
    }
    
    static final class ReturnsVoidFunc1 implements Func1<Object, Void>
    {
        @Override
        public Void call(final Object o) {
            return null;
        }
    }
    
    static final class SelectorAndObserveOn<T, R> implements Func1<Observable<T>, Observable<R>>
    {
        final Scheduler scheduler;
        final Func1<? super Observable<T>, ? extends Observable<R>> selector;
        
        public SelectorAndObserveOn(final Func1<? super Observable<T>, ? extends Observable<R>> selector, final Scheduler scheduler) {
            this.selector = selector;
            this.scheduler = scheduler;
        }
        
        @Override
        public Observable<R> call(final Observable<T> observable) {
            return ((Observable)this.selector.call(observable)).observeOn(this.scheduler);
        }
    }
    
    static final class ToArrayFunc1 implements Func1<List<? extends Observable<?>>, Observable<?>[]>
    {
        @Override
        public Observable<?>[] call(final List<? extends Observable<?>> list) {
            return list.toArray(new Observable[list.size()]);
        }
    }
}
