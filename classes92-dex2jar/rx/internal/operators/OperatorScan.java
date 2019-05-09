// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.internal.util.atomic.SpscLinkedAtomicQueue;
import rx.internal.util.unsafe.SpscLinkedQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Queue;
import rx.Subscription;
import rx.Producer;
import rx.Observer;
import rx.exceptions.Exceptions;
import rx.Subscriber;
import rx.functions.Func0;
import rx.functions.Func2;
import rx.Observable;

public final class OperatorScan<R, T> implements Operator<R, T>
{
    private static final Object NO_INITIAL_VALUE;
    final Func2<R, ? super T, R> accumulator;
    private final Func0<R> initialValueFactory;
    
    static {
        NO_INITIAL_VALUE = new Object();
    }
    
    public OperatorScan(final R r, final Func2<R, ? super T, R> func2) {
        this(new Func0<R>() {
            @Override
            public R call() {
                return r;
            }
        }, func2);
    }
    
    public OperatorScan(final Func0<R> initialValueFactory, final Func2<R, ? super T, R> accumulator) {
        this.initialValueFactory = initialValueFactory;
        this.accumulator = accumulator;
    }
    
    public OperatorScan(final Func2<R, ? super T, R> func2) {
        this(OperatorScan.NO_INITIAL_VALUE, (Func2<Object, ? super T, Object>)func2);
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super R> subscriber) {
        final R call = this.initialValueFactory.call();
        if (call == OperatorScan.NO_INITIAL_VALUE) {
            return new Subscriber<T>(subscriber) {
                boolean once;
                R value;
                
                @Override
                public void onCompleted() {
                    subscriber.onCompleted();
                }
                
                @Override
                public void onError(final Throwable t) {
                    subscriber.onError(t);
                }
                
                @Override
                public void onNext(T call) {
                    if (!this.once) {
                        this.once = true;
                    }
                    else {
                        final R value = this.value;
                        try {
                            call = (T)OperatorScan.this.accumulator.call(value, (Object)call);
                        }
                        catch (Throwable t) {
                            Exceptions.throwOrReport(t, subscriber, call);
                            return;
                        }
                    }
                    this.value = (R)call;
                    subscriber.onNext(call);
                }
            };
        }
        final InitialProducer producer = new InitialProducer(call, (Subscriber<? super Object>)subscriber);
        final Subscriber<T> subscriber2 = new Subscriber<T>() {
            private R value = call;
            
            @Override
            public void onCompleted() {
                producer.onCompleted();
            }
            
            @Override
            public void onError(final Throwable t) {
                producer.onError(t);
            }
            
            @Override
            public void onNext(final T t) {
                final R value = this.value;
                try {
                    final R call = OperatorScan.this.accumulator.call(value, (Object)t);
                    this.value = call;
                    producer.onNext(call);
                }
                catch (Throwable t2) {
                    Exceptions.throwOrReport(t2, this, t);
                }
            }
            
            @Override
            public void setProducer(final Producer producer) {
                producer.setProducer(producer);
            }
        };
        subscriber.add(subscriber2);
        subscriber.setProducer(producer);
        return subscriber2;
    }
    
    static final class InitialProducer<R> implements Producer, Observer<R>
    {
        final Subscriber<? super R> child;
        volatile boolean done;
        boolean emitting;
        Throwable error;
        boolean missed;
        long missedRequested;
        volatile Producer producer;
        final Queue<Object> queue;
        final AtomicLong requested;
        
        public InitialProducer(final R r, final Subscriber<? super R> child) {
            this.child = child;
            Object queue;
            if (UnsafeAccess.isUnsafeAvailable()) {
                queue = new SpscLinkedQueue<Object>();
            }
            else {
                queue = new SpscLinkedAtomicQueue<Object>();
            }
            (this.queue = (Queue<Object>)queue).offer(NotificationLite.instance().next(r));
            this.requested = new AtomicLong();
        }
        
        boolean checkTerminated(final boolean b, final boolean b2, final Subscriber<? super R> subscriber) {
            if (subscriber.isUnsubscribed()) {
                return true;
            }
            if (b) {
                final Throwable error = this.error;
                if (error != null) {
                    subscriber.onError(error);
                    return true;
                }
                if (b2) {
                    subscriber.onCompleted();
                    return true;
                }
            }
            return false;
        }
        
        void emit() {
            synchronized (this) {
                if (this.emitting) {
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                // monitorexit(this)
                this.emitLoop();
            }
        }
        
        void emitLoop() {
            final Subscriber<? super R> child = this.child;
            final Queue<Object> queue = this.queue;
            final NotificationLite<Object> instance = NotificationLite.instance();
            final AtomicLong requested = this.requested;
            long value = requested.get();
        Label_0049:
            while (!this.checkTerminated(this.done, queue.isEmpty(), child)) {
                long n = 0L;
                long produced = 0L;
            Label_0081_Outer:
                while (true) {
                    while (true) {
                        Label_0151: {
                            Label_0099: {
                                if (n == value) {
                                    break Label_0099;
                                }
                                final boolean done = this.done;
                                final Object poll = queue.poll();
                                if (poll != null) {
                                    break Label_0151;
                                }
                                final boolean b = true;
                                if (this.checkTerminated(done, b, child)) {
                                    break Label_0049;
                                }
                                if (!b) {
                                    final Object value2 = instance.getValue(poll);
                                    try {
                                        child.onNext((Object)value2);
                                        ++n;
                                        continue Label_0081_Outer;
                                    }
                                    catch (Throwable t) {
                                        Exceptions.throwOrReport(t, child, value2);
                                        return;
                                    }
                                    break;
                                }
                            }
                            produced = value;
                            if (n != 0L) {
                                produced = value;
                                if (value != Long.MAX_VALUE) {
                                    produced = BackpressureUtils.produced(requested, n);
                                }
                            }
                            synchronized (this) {
                                if (!this.missed) {
                                    this.emitting = false;
                                    return;
                                }
                                break;
                            }
                        }
                        final boolean b = false;
                        continue;
                    }
                }
                this.missed = false;
                // monitorexit(this)
                value = produced;
            }
        }
        
        @Override
        public void onCompleted() {
            this.done = true;
            this.emit();
        }
        
        @Override
        public void onError(final Throwable error) {
            this.error = error;
            this.done = true;
            this.emit();
        }
        
        @Override
        public void onNext(final R r) {
            this.queue.offer(NotificationLite.instance().next(r));
            this.emit();
        }
        
        @Override
        public void request(final long n) {
            if (n < 0L) {
                throw new IllegalArgumentException("n >= required but it was " + n);
            }
            if (n == 0L) {
                return;
            }
            BackpressureUtils.getAndAddRequest(this.requested, n);
            Label_0095: {
                Producer producer;
                if ((producer = this.producer) != null) {
                    break Label_0095;
                }
                synchronized (this.requested) {
                    producer = this.producer;
                    if (producer == null) {
                        this.missedRequested = BackpressureUtils.addCap(this.missedRequested, n);
                    }
                    // monitorexit(this.requested)
                    if (producer != null) {
                        producer.request(n);
                    }
                    this.emit();
                }
            }
        }
        
        public void setProducer(final Producer producer) {
            if (producer == null) {
                throw new NullPointerException();
            }
            synchronized (this.requested) {
                if (this.producer != null) {
                    throw new IllegalStateException("Can't set more than one Producer!");
                }
            }
            long missedRequested;
            final long n = missedRequested = this.missedRequested;
            if (n != Long.MAX_VALUE) {
                missedRequested = n - 1L;
            }
            this.missedRequested = 0L;
            final Producer producer2;
            this.producer = producer2;
            // monitorexit(atomicLong)
            if (missedRequested > 0L) {
                producer2.request(missedRequested);
            }
            this.emit();
        }
    }
}
