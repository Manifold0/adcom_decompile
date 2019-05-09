// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.concurrent.TimeoutException;
import rx.internal.producers.ProducerArbiter;
import rx.functions.Func4;
import rx.functions.Func3;
import rx.Producer;
import rx.subscriptions.SerialSubscription;
import rx.observers.SerializedSubscriber;
import rx.Subscription;
import rx.Subscriber;
import rx.Scheduler;
import rx.Observable;

class OperatorTimeoutBase<T> implements Operator<T, T>
{
    final FirstTimeoutStub<T> firstTimeoutStub;
    final Observable<? extends T> other;
    final Scheduler scheduler;
    final TimeoutStub<T> timeoutStub;
    
    OperatorTimeoutBase(final FirstTimeoutStub<T> firstTimeoutStub, final TimeoutStub<T> timeoutStub, final Observable<? extends T> other, final Scheduler scheduler) {
        this.firstTimeoutStub = firstTimeoutStub;
        this.timeoutStub = timeoutStub;
        this.other = other;
        this.scheduler = scheduler;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final Scheduler.Worker worker = this.scheduler.createWorker();
        subscriber.add(worker);
        final SerializedSubscriber serializedSubscriber = new SerializedSubscriber<Object>(subscriber);
        final SerialSubscription serialSubscription = new SerialSubscription();
        serializedSubscriber.add(serialSubscription);
        final TimeoutSubscriber timeoutSubscriber = new TimeoutSubscriber((SerializedSubscriber<Object>)serializedSubscriber, (TimeoutStub<Object>)this.timeoutStub, serialSubscription, this.other, worker);
        serializedSubscriber.add(timeoutSubscriber);
        serializedSubscriber.setProducer(timeoutSubscriber.arbiter);
        serialSubscription.set((Subscription)this.firstTimeoutStub.call((TimeoutSubscriber<T>)timeoutSubscriber, 0L, worker));
        return (Subscriber<? super T>)timeoutSubscriber;
    }
    
    interface FirstTimeoutStub<T> extends Func3<TimeoutSubscriber<T>, Long, Scheduler.Worker, Subscription>
    {
    }
    
    interface TimeoutStub<T> extends Func4<TimeoutSubscriber<T>, Long, T, Scheduler.Worker, Subscription>
    {
    }
    
    static final class TimeoutSubscriber<T> extends Subscriber<T>
    {
        long actual;
        final ProducerArbiter arbiter;
        final Scheduler.Worker inner;
        final Observable<? extends T> other;
        final SerialSubscription serial;
        final SerializedSubscriber<T> serializedSubscriber;
        boolean terminated;
        final TimeoutStub<T> timeoutStub;
        
        TimeoutSubscriber(final SerializedSubscriber<T> serializedSubscriber, final TimeoutStub<T> timeoutStub, final SerialSubscription serial, final Observable<? extends T> other, final Scheduler.Worker inner) {
            this.serializedSubscriber = serializedSubscriber;
            this.timeoutStub = timeoutStub;
            this.serial = serial;
            this.other = other;
            this.inner = inner;
            this.arbiter = new ProducerArbiter();
        }
        
        @Override
        public void onCompleted() {
            boolean b = false;
            synchronized (this) {
                if (!this.terminated) {
                    this.terminated = true;
                    b = true;
                }
                // monitorexit(this)
                if (b) {
                    this.serial.unsubscribe();
                    this.serializedSubscriber.onCompleted();
                }
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            boolean b = false;
            synchronized (this) {
                if (!this.terminated) {
                    this.terminated = true;
                    b = true;
                }
                // monitorexit(this)
                if (b) {
                    this.serial.unsubscribe();
                    this.serializedSubscriber.onError(t);
                }
            }
        }
        
        @Override
        public void onNext(final T t) {
            boolean b = false;
            synchronized (this) {
                long actual;
                if (!this.terminated) {
                    actual = this.actual + 1L;
                    this.actual = actual;
                    b = true;
                }
                else {
                    actual = this.actual;
                }
                // monitorexit(this)
                if (b) {
                    this.serializedSubscriber.onNext(t);
                    this.serial.set((Subscription)this.timeoutStub.call(this, actual, t, this.inner));
                }
            }
        }
        
        public void onTimeout(final long n) {
            final boolean b = false;
            // monitorenter(this)
            int n2 = b ? 1 : 0;
            Label_0069: {
                try {
                    if (n == this.actual) {
                        n2 = (b ? 1 : 0);
                        if (!this.terminated) {
                            this.terminated = true;
                            n2 = 1;
                        }
                    }
                    // monitorexit(this)
                    if (n2 != 0) {
                        if (this.other != null) {
                            break Label_0069;
                        }
                        this.serializedSubscriber.onError(new TimeoutException());
                    }
                    return;
                }
                finally {
                }
                // monitorexit(this)
            }
            final Subscriber<T> subscriber = new Subscriber<T>() {
                @Override
                public void onCompleted() {
                    TimeoutSubscriber.this.serializedSubscriber.onCompleted();
                }
                
                @Override
                public void onError(final Throwable t) {
                    TimeoutSubscriber.this.serializedSubscriber.onError(t);
                }
                
                @Override
                public void onNext(final T t) {
                    TimeoutSubscriber.this.serializedSubscriber.onNext(t);
                }
                
                @Override
                public void setProducer(final Producer producer) {
                    TimeoutSubscriber.this.arbiter.setProducer(producer);
                }
            };
            this.other.unsafeSubscribe(subscriber);
            this.serial.set(subscriber);
        }
        
        @Override
        public void setProducer(final Producer producer) {
            this.arbiter.setProducer(producer);
        }
    }
}
