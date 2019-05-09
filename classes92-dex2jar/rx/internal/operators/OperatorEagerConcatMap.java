// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.Iterator;
import rx.subscriptions.Subscriptions;
import rx.functions.Action0;
import rx.Observer;
import rx.exceptions.Exceptions;
import java.util.Collection;
import rx.Subscription;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Producer;
import java.util.concurrent.atomic.AtomicLong;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import java.util.Queue;
import rx.Subscriber;
import rx.functions.Func1;
import rx.Observable;

public final class OperatorEagerConcatMap<T, R> implements Operator<R, T>
{
    final int bufferSize;
    final Func1<? super T, ? extends Observable<? extends R>> mapper;
    private final int maxConcurrent;
    
    public OperatorEagerConcatMap(final Func1<? super T, ? extends Observable<? extends R>> mapper, final int bufferSize, final int maxConcurrent) {
        this.mapper = mapper;
        this.bufferSize = bufferSize;
        this.maxConcurrent = maxConcurrent;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super R> subscriber) {
        final EagerOuterSubscriber eagerOuterSubscriber = new EagerOuterSubscriber(this.mapper, this.bufferSize, this.maxConcurrent, subscriber);
        eagerOuterSubscriber.init();
        return (Subscriber<? super T>)eagerOuterSubscriber;
    }
    
    static final class EagerInnerSubscriber<T> extends Subscriber<T>
    {
        volatile boolean done;
        Throwable error;
        final NotificationLite<T> nl;
        final EagerOuterSubscriber<?, T> parent;
        final Queue<Object> queue;
        
        public EagerInnerSubscriber(final EagerOuterSubscriber<?, T> parent, final int n) {
            this.parent = parent;
            Object queue;
            if (UnsafeAccess.isUnsafeAvailable()) {
                queue = new SpscArrayQueue<Object>(n);
            }
            else {
                queue = new SpscAtomicArrayQueue<Object>(n);
            }
            this.queue = (Queue<Object>)queue;
            this.nl = NotificationLite.instance();
            this.request(n);
        }
        
        @Override
        public void onCompleted() {
            this.done = true;
            this.parent.drain();
        }
        
        @Override
        public void onError(final Throwable error) {
            this.error = error;
            this.done = true;
            this.parent.drain();
        }
        
        @Override
        public void onNext(final T t) {
            this.queue.offer(this.nl.next(t));
            this.parent.drain();
        }
        
        void requestMore(final long n) {
            this.request(n);
        }
    }
    
    static final class EagerOuterProducer extends AtomicLong implements Producer
    {
        private static final long serialVersionUID = -657299606803478389L;
        final EagerOuterSubscriber<?, ?> parent;
        
        public EagerOuterProducer(final EagerOuterSubscriber<?, ?> parent) {
            this.parent = parent;
        }
        
        @Override
        public void request(final long n) {
            if (n < 0L) {
                throw new IllegalStateException("n >= 0 required but it was " + n);
            }
            if (n > 0L) {
                BackpressureUtils.getAndAddRequest(this, n);
                this.parent.drain();
            }
        }
    }
    
    static final class EagerOuterSubscriber<T, R> extends Subscriber<T>
    {
        final Subscriber<? super R> actual;
        final int bufferSize;
        volatile boolean cancelled;
        volatile boolean done;
        Throwable error;
        final Func1<? super T, ? extends Observable<? extends R>> mapper;
        private EagerOuterProducer sharedProducer;
        final Queue<EagerInnerSubscriber<R>> subscribers;
        final AtomicInteger wip;
        
        public EagerOuterSubscriber(final Func1<? super T, ? extends Observable<? extends R>> mapper, final int bufferSize, final int n, final Subscriber<? super R> actual) {
            this.mapper = mapper;
            this.bufferSize = bufferSize;
            this.actual = actual;
            this.subscribers = new LinkedList<EagerInnerSubscriber<R>>();
            this.wip = new AtomicInteger();
            long n2;
            if (n == Integer.MAX_VALUE) {
                n2 = Long.MAX_VALUE;
            }
            else {
                n2 = n;
            }
            this.request(n2);
        }
        
        void cleanup() {
            Object o = this.subscribers;
            synchronized (o) {
                final ArrayList<Subscription> list = new ArrayList<Subscription>(this.subscribers);
                this.subscribers.clear();
                // monitorexit(o)
                o = list.iterator();
                while (((Iterator)o).hasNext()) {
                    ((Iterator<Subscription>)o).next().unsubscribe();
                }
            }
        }
        
        void drain() {
            if (this.wip.getAndIncrement() != 0) {
                return;
            }
            int n = 1;
            final EagerOuterProducer sharedProducer = this.sharedProducer;
            final Subscriber<? super R> actual = this.actual;
            final NotificationLite<Object> instance = NotificationLite.instance();
        Label_0156_Outer:
            while (!this.cancelled) {
                Object o;
                EagerInnerSubscriber eagerInnerSubscriber = null;
                int addAndGet = 0;
                while (true) {
                    final boolean done = this.done;
                    o = this.subscribers;
                Label_0122:
                    while (true) {
                        synchronized (o) {
                            eagerInnerSubscriber = this.subscribers.peek();
                            // monitorexit(o)
                            if (eagerInnerSubscriber == null) {
                                addAndGet = 1;
                                if (!done) {
                                    break;
                                }
                                o = this.error;
                                if (o != null) {
                                    this.cleanup();
                                    actual.onError((Throwable)o);
                                    return;
                                }
                                break Label_0122;
                            }
                        }
                        addAndGet = 0;
                        continue Label_0156_Outer;
                    }
                    if (addAndGet != 0) {
                        actual.onCompleted();
                        return;
                    }
                    break;
                }
            Label_0251_Outer:
                while (true) {
                    long n2 = 0L;
                    Queue<Object> queue = null;
                    while (true) {
                        final long value;
                        final int n3;
                        Label_0290: {
                            if (addAndGet != 0) {
                                break Label_0290;
                            }
                            value = sharedProducer.get();
                            n2 = 0L;
                            queue = eagerInnerSubscriber.queue;
                            n3 = 0;
                            final boolean done2 = eagerInnerSubscriber.done;
                            o = queue.peek();
                            if (o == null) {
                                addAndGet = 1;
                            }
                            else {
                                addAndGet = 0;
                            }
                            if (!done2) {
                                break Label_0290;
                            }
                            final Throwable error = eagerInnerSubscriber.error;
                            if (error != null) {
                                this.cleanup();
                                actual.onError(error);
                                return;
                            }
                            if (addAndGet == 0) {
                                break Label_0290;
                            }
                            synchronized (this.subscribers) {
                                this.subscribers.poll();
                                // monitorexit(this.subscribers)
                                eagerInnerSubscriber.unsubscribe();
                                final int n4 = 1;
                                this.request(1L);
                                if (n2 != 0L) {
                                    if (value != Long.MAX_VALUE) {
                                        BackpressureUtils.produced(sharedProducer, n2);
                                    }
                                    if (n4 == 0) {
                                        eagerInnerSubscriber.requestMore(n2);
                                    }
                                }
                                if (n4 != 0) {
                                    continue Label_0156_Outer;
                                }
                                addAndGet = this.wip.addAndGet(-n);
                                if ((n = addAndGet) == 0) {
                                    return;
                                }
                                continue Label_0156_Outer;
                            }
                        }
                        int n4 = n3;
                        if (addAndGet != 0) {
                            continue;
                        }
                        n4 = n3;
                        if (value == n2) {
                            continue;
                        }
                        break;
                    }
                    queue.poll();
                    try {
                        actual.onNext(instance.getValue(o));
                        ++n2;
                        continue Label_0251_Outer;
                    }
                    catch (Throwable t) {
                        Exceptions.throwOrReport(t, actual, o);
                        return;
                    }
                    break;
                }
            }
            this.cleanup();
        }
        
        void init() {
            this.sharedProducer = new EagerOuterProducer((EagerOuterSubscriber<?, ?>)this);
            this.add(Subscriptions.create(new Action0() {
                @Override
                public void call() {
                    EagerOuterSubscriber.this.cancelled = true;
                    if (EagerOuterSubscriber.this.wip.getAndIncrement() == 0) {
                        EagerOuterSubscriber.this.cleanup();
                    }
                }
            }));
            this.actual.add(this);
            this.actual.setProducer(this.sharedProducer);
        }
        
        @Override
        public void onCompleted() {
            this.done = true;
            this.drain();
        }
        
        @Override
        public void onError(final Throwable error) {
            this.error = error;
            this.done = true;
            this.drain();
        }
        
        @Override
        public void onNext(final T t) {
            EagerInnerSubscriber eagerInnerSubscriber;
            while (true) {
                try {
                    final Observable observable = (Observable)this.mapper.call((Object)t);
                    if (this.cancelled) {
                        return;
                    }
                }
                catch (Throwable t2) {
                    Exceptions.throwOrReport(t2, this.actual, t);
                    return;
                }
                eagerInnerSubscriber = new EagerInnerSubscriber((EagerOuterSubscriber<?, Object>)this, this.bufferSize);
                synchronized (this.subscribers) {
                    if (this.cancelled) {
                        return;
                    }
                }
                this.subscribers.add((EagerInnerSubscriber<R>)eagerInnerSubscriber);
                // monitorexit(t)
                if (!this.cancelled) {
                    break;
                }
                return;
            }
            final Observable observable2;
            observable2.unsafeSubscribe(eagerInnerSubscriber);
            this.drain();
        }
    }
}
