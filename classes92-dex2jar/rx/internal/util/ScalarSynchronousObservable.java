// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util;

import rx.Observer;
import rx.exceptions.Exceptions;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.schedulers.EventLoopsScheduler;
import rx.Scheduler;
import rx.observers.Subscribers;
import rx.functions.Func1;
import rx.internal.producers.SingleProducer;
import rx.Producer;
import rx.Subscriber;
import rx.plugins.RxJavaHooks;
import rx.Observable;

public final class ScalarSynchronousObservable<T> extends Observable<T>
{
    static final boolean STRONG_MODE;
    final T t;
    
    static {
        STRONG_MODE = Boolean.valueOf(System.getProperty("rx.just.strong-mode", "false"));
    }
    
    protected ScalarSynchronousObservable(final T t) {
        super(RxJavaHooks.onCreate(new JustOnSubscribe<T>(t)));
        this.t = t;
    }
    
    public static <T> ScalarSynchronousObservable<T> create(final T t) {
        return new ScalarSynchronousObservable<T>(t);
    }
    
    static <T> Producer createProducer(final Subscriber<? super T> subscriber, final T t) {
        if (ScalarSynchronousObservable.STRONG_MODE) {
            return new SingleProducer<Object>(subscriber, t);
        }
        return new WeakSingleProducer<Object>(subscriber, t);
    }
    
    public T get() {
        return this.t;
    }
    
    public <R> Observable<R> scalarFlatMap(final Func1<? super T, ? extends Observable<? extends R>> func1) {
        return Observable.create((OnSubscribe<R>)new OnSubscribe<R>() {
            @Override
            public void call(final Subscriber<? super R> subscriber) {
                final Observable<Object> observable = func1.call(ScalarSynchronousObservable.this.t);
                if (observable instanceof ScalarSynchronousObservable) {
                    subscriber.setProducer(ScalarSynchronousObservable.createProducer((Subscriber<? super T>)subscriber, (T)((ScalarSynchronousObservable<Object>)observable).t));
                    return;
                }
                observable.unsafeSubscribe(Subscribers.wrap((Subscriber<? super Object>)subscriber));
            }
        });
    }
    
    public Observable<T> scalarScheduleOn(final Scheduler scheduler) {
        Func1<Action0, Subscription> func1;
        if (scheduler instanceof EventLoopsScheduler) {
            func1 = new Func1<Action0, Subscription>() {
                final /* synthetic */ EventLoopsScheduler val$els = (EventLoopsScheduler)scheduler;
                
                @Override
                public Subscription call(final Action0 action0) {
                    return this.val$els.scheduleDirect(action0);
                }
            };
        }
        else {
            func1 = new Func1<Action0, Subscription>() {
                @Override
                public Subscription call(final Action0 action0) {
                    final Scheduler.Worker worker = scheduler.createWorker();
                    worker.schedule(new Action0() {
                        @Override
                        public void call() {
                            try {
                                action0.call();
                            }
                            finally {
                                worker.unsubscribe();
                            }
                        }
                    });
                    return worker;
                }
            };
        }
        return Observable.create((OnSubscribe<T>)new ScalarAsyncOnSubscribe(this.t, func1));
    }
    
    static final class JustOnSubscribe<T> implements OnSubscribe<T>
    {
        final T value;
        
        JustOnSubscribe(final T value) {
            this.value = value;
        }
        
        @Override
        public void call(final Subscriber<? super T> subscriber) {
            subscriber.setProducer(ScalarSynchronousObservable.createProducer(subscriber, this.value));
        }
    }
    
    static final class ScalarAsyncOnSubscribe<T> implements OnSubscribe<T>
    {
        final Func1<Action0, Subscription> onSchedule;
        final T value;
        
        ScalarAsyncOnSubscribe(final T value, final Func1<Action0, Subscription> onSchedule) {
            this.value = value;
            this.onSchedule = onSchedule;
        }
        
        @Override
        public void call(final Subscriber<? super T> subscriber) {
            subscriber.setProducer(new ScalarAsyncProducer<Object>(subscriber, this.value, this.onSchedule));
        }
    }
    
    static final class ScalarAsyncProducer<T> extends AtomicBoolean implements Producer, Action0
    {
        private static final long serialVersionUID = -2466317989629281651L;
        final Subscriber<? super T> actual;
        final Func1<Action0, Subscription> onSchedule;
        final T value;
        
        public ScalarAsyncProducer(final Subscriber<? super T> actual, final T value, final Func1<Action0, Subscription> onSchedule) {
            this.actual = actual;
            this.value = value;
            this.onSchedule = onSchedule;
        }
        
        @Override
        public void call() {
            final Subscriber<? super T> actual = this.actual;
            if (!actual.isUnsubscribed()) {
                final T value = this.value;
                try {
                    actual.onNext(value);
                    if (!actual.isUnsubscribed()) {
                        actual.onCompleted();
                    }
                }
                catch (Throwable t) {
                    Exceptions.throwOrReport(t, actual, value);
                }
            }
        }
        
        @Override
        public void request(final long n) {
            if (n < 0L) {
                throw new IllegalArgumentException("n >= 0 required but it was " + n);
            }
            if (n != 0L && this.compareAndSet(false, true)) {
                this.actual.add(this.onSchedule.call(this));
            }
        }
        
        @Override
        public String toString() {
            return "ScalarAsyncProducer[" + this.value + ", " + this.get() + "]";
        }
    }
    
    static final class WeakSingleProducer<T> implements Producer
    {
        final Subscriber<? super T> actual;
        boolean once;
        final T value;
        
        public WeakSingleProducer(final Subscriber<? super T> actual, final T value) {
            this.actual = actual;
            this.value = value;
        }
        
        @Override
        public void request(final long n) {
            if (!this.once) {
                if (n < 0L) {
                    throw new IllegalStateException("n >= required but it was " + n);
                }
                if (n != 0L) {
                    this.once = true;
                    final Subscriber<? super T> actual = this.actual;
                    if (!actual.isUnsubscribed()) {
                        final T value = this.value;
                        try {
                            actual.onNext(value);
                            if (!actual.isUnsubscribed()) {
                                actual.onCompleted();
                            }
                        }
                        catch (Throwable t) {
                            Exceptions.throwOrReport(t, actual, value);
                        }
                    }
                }
            }
        }
    }
}
