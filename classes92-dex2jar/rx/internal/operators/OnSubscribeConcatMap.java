// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.plugins.RxJavaHooks;
import rx.exceptions.MissingBackpressureException;
import rx.internal.util.ScalarSynchronousObservable;
import rx.exceptions.Exceptions;
import rx.internal.util.ExceptionsUtils;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Queue;
import rx.subscriptions.SerialSubscription;
import java.util.concurrent.atomic.AtomicReference;
import rx.internal.producers.ProducerArbiter;
import rx.Producer;
import rx.Subscription;
import rx.observers.SerializedSubscriber;
import rx.Subscriber;
import rx.functions.Func1;
import rx.Observable;

public final class OnSubscribeConcatMap<T, R> implements OnSubscribe<R>
{
    public static final int BOUNDARY = 1;
    public static final int END = 2;
    public static final int IMMEDIATE = 0;
    final int delayErrorMode;
    final Func1<? super T, ? extends Observable<? extends R>> mapper;
    final int prefetch;
    final Observable<? extends T> source;
    
    public OnSubscribeConcatMap(final Observable<? extends T> source, final Func1<? super T, ? extends Observable<? extends R>> mapper, final int prefetch, final int delayErrorMode) {
        this.source = source;
        this.mapper = mapper;
        this.prefetch = prefetch;
        this.delayErrorMode = delayErrorMode;
    }
    
    @Override
    public void call(final Subscriber<? super R> subscriber) {
        SerializedSubscriber serializedSubscriber;
        if (this.delayErrorMode == 0) {
            serializedSubscriber = new SerializedSubscriber<Object>((Subscriber<? super Object>)subscriber);
        }
        else {
            serializedSubscriber = (SerializedSubscriber)subscriber;
        }
        final ConcatMapSubscriber concatMapSubscriber = new ConcatMapSubscriber<Object, Object>(serializedSubscriber, (Func1<? super Object, ? extends Observable<?>>)this.mapper, this.prefetch, this.delayErrorMode);
        subscriber.add(concatMapSubscriber);
        subscriber.add(concatMapSubscriber.inner);
        subscriber.setProducer(new Producer() {
            @Override
            public void request(final long n) {
                concatMapSubscriber.requestMore(n);
            }
        });
        if (!subscriber.isUnsubscribed()) {
            this.source.unsafeSubscribe((Subscriber<? super T>)concatMapSubscriber);
        }
    }
    
    static final class ConcatMapInnerScalarProducer<T, R> implements Producer
    {
        boolean once;
        final ConcatMapSubscriber<T, R> parent;
        final R value;
        
        public ConcatMapInnerScalarProducer(final R value, final ConcatMapSubscriber<T, R> parent) {
            this.value = value;
            this.parent = parent;
        }
        
        @Override
        public void request(final long n) {
            if (!this.once && n > 0L) {
                this.once = true;
                final ConcatMapSubscriber<T, R> parent = this.parent;
                parent.innerNext(this.value);
                parent.innerCompleted(1L);
            }
        }
    }
    
    static final class ConcatMapInnerSubscriber<T, R> extends Subscriber<R>
    {
        final ConcatMapSubscriber<T, R> parent;
        long produced;
        
        public ConcatMapInnerSubscriber(final ConcatMapSubscriber<T, R> parent) {
            this.parent = parent;
        }
        
        @Override
        public void onCompleted() {
            this.parent.innerCompleted(this.produced);
        }
        
        @Override
        public void onError(final Throwable t) {
            this.parent.innerError(t, this.produced);
        }
        
        @Override
        public void onNext(final R r) {
            ++this.produced;
            this.parent.innerNext(r);
        }
        
        @Override
        public void setProducer(final Producer producer) {
            this.parent.arbiter.setProducer(producer);
        }
    }
    
    static final class ConcatMapSubscriber<T, R> extends Subscriber<T>
    {
        volatile boolean active;
        final Subscriber<? super R> actual;
        final ProducerArbiter arbiter;
        final int delayErrorMode;
        volatile boolean done;
        final AtomicReference<Throwable> error;
        final SerialSubscription inner;
        final Func1<? super T, ? extends Observable<? extends R>> mapper;
        final Queue<Object> queue;
        final AtomicInteger wip;
        
        public ConcatMapSubscriber(final Subscriber<? super R> actual, final Func1<? super T, ? extends Observable<? extends R>> mapper, final int n, final int delayErrorMode) {
            this.actual = actual;
            this.mapper = mapper;
            this.delayErrorMode = delayErrorMode;
            this.arbiter = new ProducerArbiter();
            this.wip = new AtomicInteger();
            this.error = new AtomicReference<Throwable>();
            Object queue;
            if (UnsafeAccess.isUnsafeAvailable()) {
                queue = new SpscArrayQueue<Object>(n);
            }
            else {
                queue = new SpscAtomicArrayQueue<Object>(n);
            }
            this.queue = (Queue<Object>)queue;
            this.inner = new SerialSubscription();
            this.request(n);
        }
        
        void drain() {
            if (this.wip.getAndIncrement() == 0) {
                final int delayErrorMode = this.delayErrorMode;
                while (!this.actual.isUnsubscribed()) {
                    if (!this.active) {
                        if (delayErrorMode == 1 && this.error.get() != null) {
                            final Throwable terminate = ExceptionsUtils.terminate(this.error);
                            if (!ExceptionsUtils.isTerminated(terminate)) {
                                this.actual.onError(terminate);
                                return;
                            }
                            break;
                        }
                        else {
                            final boolean done = this.done;
                            final Object poll = this.queue.poll();
                            boolean b;
                            if (poll == null) {
                                b = true;
                            }
                            else {
                                b = false;
                            }
                            if (done && b) {
                                final Throwable terminate2 = ExceptionsUtils.terminate(this.error);
                                if (terminate2 == null) {
                                    this.actual.onCompleted();
                                    return;
                                }
                                if (!ExceptionsUtils.isTerminated(terminate2)) {
                                    this.actual.onError(terminate2);
                                    return;
                                }
                                break;
                            }
                            else if (!b) {
                                Observable observable;
                                try {
                                    observable = (Observable)this.mapper.call((Object)NotificationLite.instance().getValue(poll));
                                    if (observable == null) {
                                        this.drainError(new NullPointerException("The source returned by the mapper was null"));
                                        return;
                                    }
                                }
                                catch (Throwable t) {
                                    Exceptions.throwIfFatal(t);
                                    this.drainError(t);
                                    return;
                                }
                                if (observable == Observable.empty()) {
                                    this.request(1L);
                                    continue;
                                }
                                if (observable instanceof ScalarSynchronousObservable) {
                                    final ScalarSynchronousObservable<Object> scalarSynchronousObservable = (ScalarSynchronousObservable<Object>)observable;
                                    this.active = true;
                                    this.arbiter.setProducer(new ConcatMapInnerScalarProducer<Object, Object>(scalarSynchronousObservable.get(), (ConcatMapSubscriber<Object, Object>)this));
                                }
                                else {
                                    final ConcatMapInnerSubscriber concatMapInnerSubscriber = new ConcatMapInnerSubscriber((ConcatMapSubscriber)this);
                                    this.inner.set(concatMapInnerSubscriber);
                                    if (concatMapInnerSubscriber.isUnsubscribed()) {
                                        break;
                                    }
                                    this.active = true;
                                    observable.unsafeSubscribe(concatMapInnerSubscriber);
                                }
                                this.request(1L);
                            }
                        }
                    }
                    if (this.wip.decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
        
        void drainError(Throwable terminate) {
            this.unsubscribe();
            if (ExceptionsUtils.addThrowable(this.error, terminate)) {
                terminate = ExceptionsUtils.terminate(this.error);
                if (!ExceptionsUtils.isTerminated(terminate)) {
                    this.actual.onError(terminate);
                }
                return;
            }
            this.pluginError(terminate);
        }
        
        void innerCompleted(final long n) {
            if (n != 0L) {
                this.arbiter.produced(n);
            }
            this.active = false;
            this.drain();
        }
        
        void innerError(Throwable terminate, final long n) {
            if (!ExceptionsUtils.addThrowable(this.error, terminate)) {
                this.pluginError(terminate);
                return;
            }
            if (this.delayErrorMode == 0) {
                terminate = ExceptionsUtils.terminate(this.error);
                if (!ExceptionsUtils.isTerminated(terminate)) {
                    this.actual.onError(terminate);
                }
                this.unsubscribe();
                return;
            }
            if (n != 0L) {
                this.arbiter.produced(n);
            }
            this.active = false;
            this.drain();
        }
        
        void innerNext(final R r) {
            this.actual.onNext((Object)r);
        }
        
        @Override
        public void onCompleted() {
            this.done = true;
            this.drain();
        }
        
        @Override
        public void onError(Throwable terminate) {
            if (!ExceptionsUtils.addThrowable(this.error, terminate)) {
                this.pluginError(terminate);
                return;
            }
            this.done = true;
            if (this.delayErrorMode == 0) {
                terminate = ExceptionsUtils.terminate(this.error);
                if (!ExceptionsUtils.isTerminated(terminate)) {
                    this.actual.onError(terminate);
                }
                this.inner.unsubscribe();
                return;
            }
            this.drain();
        }
        
        @Override
        public void onNext(final T t) {
            if (!this.queue.offer(NotificationLite.instance().next(t))) {
                this.unsubscribe();
                this.onError(new MissingBackpressureException());
                return;
            }
            this.drain();
        }
        
        void pluginError(final Throwable t) {
            RxJavaHooks.onError(t);
        }
        
        void requestMore(final long n) {
            if (n > 0L) {
                this.arbiter.request(n);
            }
            else if (n < 0L) {
                throw new IllegalArgumentException("n >= 0 required but it was " + n);
            }
        }
    }
}
