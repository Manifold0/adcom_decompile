// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.functions.Func2;
import rx.subjects.SerializedSubject;
import rx.Producer;
import rx.subjects.Subject;
import rx.functions.Action0;
import rx.internal.producers.ProducerArbiter;
import rx.observers.Subscribers;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.SerialSubscription;
import rx.Subscription;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import rx.Scheduler;
import rx.Notification;
import rx.functions.Func1;
import rx.Observable;

public final class OnSubscribeRedo<T> implements OnSubscribe<T>
{
    static final Func1<Observable<? extends Notification<?>>, Observable<?>> REDO_INFINITE;
    private final Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> controlHandlerFunction;
    private final Scheduler scheduler;
    final Observable<T> source;
    final boolean stopOnComplete;
    final boolean stopOnError;
    
    static {
        REDO_INFINITE = new Func1<Observable<? extends Notification<?>>, Observable<?>>() {
            @Override
            public Observable<?> call(final Observable<? extends Notification<?>> observable) {
                return observable.map((Func1<? super Notification<?>, ?>)new Func1<Notification<?>, Notification<?>>() {
                    @Override
                    public Notification<?> call(final Notification<?> notification) {
                        return Notification.createOnNext((Object)null);
                    }
                });
            }
        };
    }
    
    private OnSubscribeRedo(final Observable<T> source, final Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> controlHandlerFunction, final boolean stopOnComplete, final boolean stopOnError, final Scheduler scheduler) {
        this.source = source;
        this.controlHandlerFunction = controlHandlerFunction;
        this.stopOnComplete = stopOnComplete;
        this.stopOnError = stopOnError;
        this.scheduler = scheduler;
    }
    
    public static <T> Observable<T> redo(final Observable<T> observable, final Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> func1, final Scheduler scheduler) {
        return Observable.create((Observable.OnSubscribe<T>)new OnSubscribeRedo((Observable<Object>)observable, func1, false, false, scheduler));
    }
    
    public static <T> Observable<T> repeat(final Observable<T> observable) {
        return repeat(observable, Schedulers.trampoline());
    }
    
    public static <T> Observable<T> repeat(final Observable<T> observable, final long n) {
        return repeat(observable, n, Schedulers.trampoline());
    }
    
    public static <T> Observable<T> repeat(final Observable<T> observable, final long n, final Scheduler scheduler) {
        if (n == 0L) {
            return Observable.empty();
        }
        if (n < 0L) {
            throw new IllegalArgumentException("count >= 0 expected");
        }
        return repeat(observable, new RedoFinite(n - 1L), scheduler);
    }
    
    public static <T> Observable<T> repeat(final Observable<T> observable, final Scheduler scheduler) {
        return repeat(observable, OnSubscribeRedo.REDO_INFINITE, scheduler);
    }
    
    public static <T> Observable<T> repeat(final Observable<T> observable, final Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> func1) {
        return Observable.create((Observable.OnSubscribe<T>)new OnSubscribeRedo((Observable<Object>)observable, func1, false, true, Schedulers.trampoline()));
    }
    
    public static <T> Observable<T> repeat(final Observable<T> observable, final Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> func1, final Scheduler scheduler) {
        return Observable.create((Observable.OnSubscribe<T>)new OnSubscribeRedo((Observable<Object>)observable, func1, false, true, scheduler));
    }
    
    public static <T> Observable<T> retry(final Observable<T> observable) {
        return retry(observable, OnSubscribeRedo.REDO_INFINITE);
    }
    
    public static <T> Observable<T> retry(final Observable<T> observable, final long n) {
        if (n < 0L) {
            throw new IllegalArgumentException("count >= 0 expected");
        }
        if (n == 0L) {
            return observable;
        }
        return retry(observable, new RedoFinite(n));
    }
    
    public static <T> Observable<T> retry(final Observable<T> observable, final Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> func1) {
        return Observable.create((Observable.OnSubscribe<T>)new OnSubscribeRedo((Observable<Object>)observable, func1, true, false, Schedulers.trampoline()));
    }
    
    public static <T> Observable<T> retry(final Observable<T> observable, final Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> func1, final Scheduler scheduler) {
        return Observable.create((Observable.OnSubscribe<T>)new OnSubscribeRedo((Observable<Object>)observable, func1, true, false, scheduler));
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        final AtomicLong atomicLong = new AtomicLong();
        final Scheduler.Worker worker = this.scheduler.createWorker();
        subscriber.add(worker);
        final SerialSubscription serialSubscription = new SerialSubscription();
        subscriber.add(serialSubscription);
        final SerializedSubject<Object, Object> serialized = BehaviorSubject.create().toSerialized();
        serialized.subscribe(Subscribers.empty());
        final ProducerArbiter producerArbiter = new ProducerArbiter();
        final Action0 action0 = new Action0() {
            @Override
            public void call() {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                final Subscriber<T> subscriber = new Subscriber<T>() {
                    boolean done;
                    
                    private void decrementConsumerCapacity() {
                        long value;
                        do {
                            value = atomicLong.get();
                        } while (value != Long.MAX_VALUE && !atomicLong.compareAndSet(value, value - 1L));
                    }
                    
                    @Override
                    public void onCompleted() {
                        if (!this.done) {
                            this.done = true;
                            this.unsubscribe();
                            serialized.onNext(Notification.createOnCompleted());
                        }
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        if (!this.done) {
                            this.done = true;
                            this.unsubscribe();
                            serialized.onNext(Notification.createOnError(t));
                        }
                    }
                    
                    @Override
                    public void onNext(final T t) {
                        if (!this.done) {
                            subscriber.onNext(t);
                            this.decrementConsumerCapacity();
                            producerArbiter.produced(1L);
                        }
                    }
                    
                    @Override
                    public void setProducer(final Producer producer) {
                        producerArbiter.setProducer(producer);
                    }
                };
                serialSubscription.set(subscriber);
                OnSubscribeRedo.this.source.unsafeSubscribe(subscriber);
            }
        };
        worker.schedule(new Action0() {
            final /* synthetic */ Observable val$restarts = (Observable)OnSubscribeRedo.this.controlHandlerFunction.call((Object)serialized.lift((Observable.Operator<?, ? super Object>)new Operator<Notification<?>, Notification<?>>(this) {
                @Override
                public Subscriber<? super Notification<?>> call(final Subscriber<? super Notification<?>> subscriber) {
                    return new Subscriber<Notification<?>>(this, subscriber, subscriber) {
                        final /* synthetic */ Subscriber val$filteredTerminals;
                        
                        @Override
                        public void onCompleted() {
                            this.val$filteredTerminals.onCompleted();
                        }
                        
                        @Override
                        public void onError(final Throwable t) {
                            this.val$filteredTerminals.onError(t);
                        }
                        
                        @Override
                        public void onNext(final Notification<?> notification) {
                            if (notification.isOnCompleted() && OnSubscribeRedo.this.stopOnComplete) {
                                this.val$filteredTerminals.onCompleted();
                                return;
                            }
                            if (notification.isOnError() && OnSubscribeRedo.this.stopOnError) {
                                this.val$filteredTerminals.onError(notification.getThrowable());
                                return;
                            }
                            this.val$filteredTerminals.onNext(notification);
                        }
                        
                        @Override
                        public void setProducer(final Producer producer) {
                            producer.request(Long.MAX_VALUE);
                        }
                    };
                }
            }));
            
            @Override
            public void call() {
                this.val$restarts.unsafeSubscribe(new Subscriber<Object>(subscriber) {
                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        subscriber.onError(t);
                    }
                    
                    @Override
                    public void onNext(final Object o) {
                        if (!subscriber.isUnsubscribed()) {
                            if (atomicLong.get() <= 0L) {
                                atomicBoolean.compareAndSet(false, true);
                                return;
                            }
                            worker.schedule(action0);
                        }
                    }
                    
                    @Override
                    public void setProducer(final Producer producer) {
                        producer.request(Long.MAX_VALUE);
                    }
                });
            }
        });
        subscriber.setProducer(new Producer() {
            @Override
            public void request(final long n) {
                if (n > 0L) {
                    BackpressureUtils.getAndAddRequest(atomicLong, n);
                    producerArbiter.request(n);
                    if (atomicBoolean.compareAndSet(true, false)) {
                        worker.schedule(action0);
                    }
                }
            }
        });
    }
    
    public static final class RedoFinite implements Func1<Observable<? extends Notification<?>>, Observable<?>>
    {
        final long count;
        
        public RedoFinite(final long count) {
            this.count = count;
        }
        
        @Override
        public Observable<?> call(final Observable<? extends Notification<?>> observable) {
            return observable.map((Func1<? super Notification<?>, ?>)new Func1<Notification<?>, Notification<?>>() {
                int num;
                
                @Override
                public Notification<?> call(final Notification<?> notification) {
                    if (RedoFinite.this.count != 0L) {
                        ++this.num;
                        if (this.num <= RedoFinite.this.count) {
                            return Notification.createOnNext((Object)this.num);
                        }
                    }
                    return notification;
                }
            }).dematerialize();
        }
    }
    
    public static final class RetryWithPredicate implements Func1<Observable<? extends Notification<?>>, Observable<? extends Notification<?>>>
    {
        final Func2<Integer, Throwable, Boolean> predicate;
        
        public RetryWithPredicate(final Func2<Integer, Throwable, Boolean> predicate) {
            this.predicate = predicate;
        }
        
        @Override
        public Observable<? extends Notification<?>> call(final Observable<? extends Notification<?>> observable) {
            return observable.scan((Notification<?>)Notification.createOnNext(0), (Func2<? extends Notification<?>, ? super Notification<?>, ? extends Notification<?>>)new Func2<Notification<Integer>, Notification<?>, Notification<Integer>>() {
                @Override
                public Notification<Integer> call(final Notification<Integer> notification, final Notification<?> notification2) {
                    final int intValue = notification.getValue();
                    Notification<?> onNext = notification2;
                    if (RetryWithPredicate.this.predicate.call(intValue, notification2.getThrowable())) {
                        onNext = Notification.createOnNext((Object)(intValue + 1));
                    }
                    return (Notification<Integer>)onNext;
                }
            });
        }
    }
}
