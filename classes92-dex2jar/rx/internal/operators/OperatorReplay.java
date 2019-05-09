// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.schedulers.Timestamped;
import java.util.Iterator;
import java.util.ArrayList;
import rx.subscriptions.Subscriptions;
import rx.functions.Action0;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.internal.util.OpenHashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import rx.exceptions.OnErrorThrowable;
import java.util.Collection;
import rx.Observer;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.Producer;
import rx.Subscription;
import rx.Subscriber;
import rx.Scheduler;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import java.util.concurrent.atomic.AtomicReference;
import rx.functions.Func0;
import rx.observables.ConnectableObservable;

public final class OperatorReplay<T> extends ConnectableObservable<T>
{
    static final Func0 DEFAULT_UNBOUNDED_FACTORY;
    final Func0<? extends ReplayBuffer<T>> bufferFactory;
    final AtomicReference<ReplaySubscriber<T>> current;
    final Observable<? extends T> source;
    
    static {
        DEFAULT_UNBOUNDED_FACTORY = new Func0() {
            @Override
            public Object call() {
                return new UnboundedReplayBuffer(16);
            }
        };
    }
    
    private OperatorReplay(final OnSubscribe<T> onSubscribe, final Observable<? extends T> source, final AtomicReference<ReplaySubscriber<T>> current, final Func0<? extends ReplayBuffer<T>> bufferFactory) {
        super(onSubscribe);
        this.source = source;
        this.current = current;
        this.bufferFactory = bufferFactory;
    }
    
    public static <T> ConnectableObservable<T> create(final Observable<? extends T> observable) {
        return create(observable, (Func0<? extends ReplayBuffer<T>>)OperatorReplay.DEFAULT_UNBOUNDED_FACTORY);
    }
    
    public static <T> ConnectableObservable<T> create(final Observable<? extends T> observable, final int n) {
        if (n == Integer.MAX_VALUE) {
            return create(observable);
        }
        return create(observable, (Func0<? extends ReplayBuffer<T>>)new Func0<ReplayBuffer<T>>() {
            @Override
            public ReplayBuffer<T> call() {
                return new SizeBoundReplayBuffer<T>(n);
            }
        });
    }
    
    public static <T> ConnectableObservable<T> create(final Observable<? extends T> observable, final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return create(observable, n, timeUnit, scheduler, Integer.MAX_VALUE);
    }
    
    public static <T> ConnectableObservable<T> create(final Observable<? extends T> observable, final long n, final TimeUnit timeUnit, final Scheduler scheduler, final int n2) {
        return create(observable, (Func0<? extends ReplayBuffer<T>>)new Func0<ReplayBuffer<T>>() {
            final /* synthetic */ long val$maxAgeInMillis = timeUnit.toMillis(n);
            
            @Override
            public ReplayBuffer<T> call() {
                return new SizeAndTimeBoundReplayBuffer<T>(n2, this.val$maxAgeInMillis, scheduler);
            }
        });
    }
    
    static <T> ConnectableObservable<T> create(final Observable<? extends T> observable, final Func0<? extends ReplayBuffer<T>> func0) {
        final AtomicReference<ReplaySubscriber<T>> atomicReference = (AtomicReference<ReplaySubscriber<T>>)new AtomicReference<ReplaySubscriber<Object>>();
        return new OperatorReplay<T>((OnSubscribe<Object>)new OnSubscribe<T>() {
            @Override
            public void call(final Subscriber<? super T> subscriber) {
                ReplaySubscriber replaySubscriber;
                ReplaySubscriber replaySubscriber2;
                do {
                    replaySubscriber = atomicReference.get();
                    if ((replaySubscriber2 = replaySubscriber) != null) {
                        break;
                    }
                    replaySubscriber2 = new ReplaySubscriber((ReplayBuffer)func0.call());
                    replaySubscriber2.init();
                } while (!atomicReference.compareAndSet(replaySubscriber, replaySubscriber2));
                final InnerProducer producer = new InnerProducer(replaySubscriber2, (Subscriber<? super Object>)subscriber);
                replaySubscriber2.add((InnerProducer<Object>)producer);
                subscriber.add(producer);
                ((ReplaySubscriber)replaySubscriber2).buffer.replay((InnerProducer<T>)producer);
                subscriber.setProducer(producer);
            }
        }, observable, (AtomicReference<ReplaySubscriber<Object>>)atomicReference, (Func0<? extends ReplayBuffer<Object>>)func0);
    }
    
    public static <T, U, R> Observable<R> multicastSelector(final Func0<? extends ConnectableObservable<U>> func0, final Func1<? super Observable<U>, ? extends Observable<R>> func2) {
        return Observable.create((OnSubscribe<R>)new OnSubscribe<R>() {
            @Override
            public void call(final Subscriber<? super R> subscriber) {
                try {
                    final ConnectableObservable connectableObservable = func0.call();
                    ((Observable)func2.call(connectableObservable)).subscribe(subscriber);
                    connectableObservable.connect(new Action1<Subscription>() {
                        @Override
                        public void call(final Subscription subscription) {
                            subscriber.add(subscription);
                        }
                    });
                }
                catch (Throwable t) {
                    Exceptions.throwOrReport(t, subscriber);
                }
            }
        });
    }
    
    public static <T> ConnectableObservable<T> observeOn(final ConnectableObservable<T> connectableObservable, final Scheduler scheduler) {
        return new ConnectableObservable<T>(new OnSubscribe<T>() {
            final /* synthetic */ Observable val$observable = connectableObservable.observeOn(scheduler);
            
            @Override
            public void call(final Subscriber<? super T> subscriber) {
                this.val$observable.unsafeSubscribe(new Subscriber<T>(subscriber) {
                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        subscriber.onError(t);
                    }
                    
                    @Override
                    public void onNext(final T t) {
                        subscriber.onNext(t);
                    }
                });
            }
        }) {
            @Override
            public void connect(final Action1<? super Subscription> action1) {
                connectableObservable.connect(action1);
            }
        };
    }
    
    @Override
    public void connect(final Action1<? super Subscription> action1) {
        int n = 1;
        ReplaySubscriber<T> replaySubscriber;
        ReplaySubscriber<T> replaySubscriber2;
        do {
            replaySubscriber = this.current.get();
            if (replaySubscriber != null) {
                replaySubscriber2 = replaySubscriber;
                if (!replaySubscriber.isUnsubscribed()) {
                    break;
                }
            }
            replaySubscriber2 = new ReplaySubscriber<T>((ReplayBuffer<T>)this.bufferFactory.call());
            replaySubscriber2.init();
        } while (!this.current.compareAndSet(replaySubscriber, replaySubscriber2));
        if (replaySubscriber2.shouldConnect.get() || !replaySubscriber2.shouldConnect.compareAndSet(false, true)) {
            n = 0;
        }
        action1.call(replaySubscriber2);
        if (n != 0) {
            this.source.unsafeSubscribe(replaySubscriber2);
        }
    }
    
    static class BoundedReplayBuffer<T> extends AtomicReference<Node> implements ReplayBuffer<T>
    {
        private static final long serialVersionUID = 2346567790059478686L;
        long index;
        final NotificationLite<T> nl;
        int size;
        Node tail;
        
        public BoundedReplayBuffer() {
            this.nl = NotificationLite.instance();
            this.set(this.tail = new Node(null, 0L));
        }
        
        final void addLast(final Node tail) {
            this.tail.set(tail);
            this.tail = tail;
            ++this.size;
        }
        
        final void collect(final Collection<? super T> collection) {
            Node initialHead = this.getInitialHead();
            while (true) {
                initialHead = (Node)initialHead.get();
                if (initialHead == null) {
                    break;
                }
                final Object leaveTransform = this.leaveTransform(initialHead.value);
                if (this.nl.isCompleted(leaveTransform) || this.nl.isError(leaveTransform)) {
                    break;
                }
                collection.add(this.nl.getValue(leaveTransform));
            }
        }
        
        @Override
        public final void complete() {
            final Object enterTransform = this.enterTransform(this.nl.completed());
            final long index = this.index + 1L;
            this.index = index;
            this.addLast(new Node(enterTransform, index));
            this.truncateFinal();
        }
        
        Object enterTransform(final Object o) {
            return o;
        }
        
        @Override
        public final void error(final Throwable t) {
            final Object enterTransform = this.enterTransform(this.nl.error(t));
            final long index = this.index + 1L;
            this.index = index;
            this.addLast(new Node(enterTransform, index));
            this.truncateFinal();
        }
        
        Node getInitialHead() {
            return this.get();
        }
        
        boolean hasCompleted() {
            return this.tail.value != null && this.nl.isCompleted(this.leaveTransform(this.tail.value));
        }
        
        boolean hasError() {
            return this.tail.value != null && this.nl.isError(this.leaveTransform(this.tail.value));
        }
        
        Object leaveTransform(final Object o) {
            return o;
        }
        
        @Override
        public final void next(final T t) {
            final Object enterTransform = this.enterTransform(this.nl.next(t));
            final long index = this.index + 1L;
            this.index = index;
            this.addLast(new Node(enterTransform, index));
            this.truncate();
        }
        
        final void removeFirst() {
            final Node first = this.get().get();
            if (first == null) {
                throw new IllegalStateException("Empty list!");
            }
            --this.size;
            this.setFirst(first);
        }
        
        final void removeSome(int i) {
            Node first = this.get();
            while (i > 0) {
                first = (Node)first.get();
                --i;
                --this.size;
            }
            this.setFirst(first);
        }
        
        @Override
        public final void replay(final InnerProducer<T> innerProducer) {
            while (true) {
                // monitorexit(innerProducer)
            Label_0096_Outer:
                while (true) {
                    long value = 0L;
                    long n = 0L;
                Label_0232:
                    while (true) {
                        Node node;
                        synchronized (innerProducer) {
                            if (innerProducer.emitting) {
                                innerProducer.missed = true;
                                return;
                            }
                            innerProducer.emitting = true;
                            // monitorexit(innerProducer)
                            if (innerProducer.isUnsubscribed()) {
                                return;
                            }
                            node = innerProducer.index();
                            Node initialHead;
                            if ((initialHead = node) == null) {
                                initialHead = this.getInitialHead();
                                innerProducer.index = initialHead;
                                innerProducer.addTotalRequested(initialHead.index);
                            }
                            if (innerProducer.isUnsubscribed()) {
                                return;
                            }
                            final Subscriber<? super T> child = (Subscriber<? super T>)innerProducer.child;
                            if (child == null) {
                                return;
                            }
                            value = innerProducer.get();
                            n = 0L;
                            if (n == value) {
                                break Label_0232;
                            }
                            node = (Node)initialHead.get();
                            if (node == null) {
                                break Label_0232;
                            }
                            final Object leaveTransform = this.leaveTransform(node.value);
                            try {
                                if (this.nl.accept((Observer<? super T>)child, leaveTransform)) {
                                    innerProducer.index = null;
                                    return;
                                }
                            }
                            catch (Throwable node) {
                                innerProducer.index = null;
                                Exceptions.throwIfFatal((Throwable)node);
                                innerProducer.unsubscribe();
                                if (!this.nl.isError(leaveTransform) && !this.nl.isCompleted(leaveTransform)) {
                                    child.onError(OnErrorThrowable.addValueAsLastCause((Throwable)node, this.nl.getValue(leaveTransform)));
                                }
                                return;
                            }
                        }
                        ++n;
                        Node initialHead = node;
                        if (innerProducer.isUnsubscribed()) {
                            return;
                        }
                        continue;
                    }
                    if (n != 0L) {
                        final Throwable index;
                        innerProducer.index = index;
                        if (value != Long.MAX_VALUE) {
                            innerProducer.produced(n);
                        }
                    }
                    synchronized (innerProducer) {
                        if (!innerProducer.missed) {
                            innerProducer.emitting = false;
                            return;
                        }
                    }
                    innerProducer.missed = false;
                    continue Label_0096_Outer;
                }
            }
        }
        
        final void setFirst(final Node node) {
            this.set(node);
        }
        
        void truncate() {
        }
        
        void truncateFinal() {
        }
    }
    
    static final class InnerProducer<T> extends AtomicLong implements Producer, Subscription
    {
        static final long UNSUBSCRIBED = Long.MIN_VALUE;
        private static final long serialVersionUID = -4453897557930727610L;
        Subscriber<? super T> child;
        boolean emitting;
        Object index;
        boolean missed;
        final ReplaySubscriber<T> parent;
        final AtomicLong totalRequested;
        
        public InnerProducer(final ReplaySubscriber<T> parent, final Subscriber<? super T> child) {
            this.parent = parent;
            this.child = child;
            this.totalRequested = new AtomicLong();
        }
        
        void addTotalRequested(final long n) {
            long value;
            long n2;
            do {
                value = this.totalRequested.get();
                if ((n2 = value + n) < 0L) {
                    n2 = Long.MAX_VALUE;
                }
            } while (!this.totalRequested.compareAndSet(value, n2));
        }
        
         <U> U index() {
            return (U)this.index;
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.get() == Long.MIN_VALUE;
        }
        
        public long produced(final long n) {
            if (n <= 0L) {
                throw new IllegalArgumentException("Cant produce zero or less");
            }
            long value;
            long n2;
            do {
                value = this.get();
                if (value == Long.MIN_VALUE) {
                    return Long.MIN_VALUE;
                }
                n2 = value - n;
                if (n2 < 0L) {
                    throw new IllegalStateException("More produced (" + n + ") than requested (" + value + ")");
                }
            } while (!this.compareAndSet(value, n2));
            return n2;
        }
        
        @Override
        public void request(final long n) {
            if (n >= 0L) {
                long value;
                long n2;
                do {
                    value = this.get();
                    if (value == Long.MIN_VALUE || (value >= 0L && n == 0L)) {
                        return;
                    }
                    if ((n2 = value + n) >= 0L) {
                        continue;
                    }
                    n2 = Long.MAX_VALUE;
                } while (!this.compareAndSet(value, n2));
                this.addTotalRequested(n);
                this.parent.manageRequests(this);
                this.parent.buffer.replay(this);
            }
        }
        
        @Override
        public void unsubscribe() {
            if (this.get() != Long.MIN_VALUE && this.getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
                this.parent.manageRequests(this);
                this.child = null;
            }
        }
    }
    
    static final class Node extends AtomicReference<Node>
    {
        private static final long serialVersionUID = 245354315435971818L;
        final long index;
        final Object value;
        
        public Node(final Object value, final long index) {
            this.value = value;
            this.index = index;
        }
    }
    
    interface ReplayBuffer<T>
    {
        void complete();
        
        void error(final Throwable p0);
        
        void next(final T p0);
        
        void replay(final InnerProducer<T> p0);
    }
    
    static final class ReplaySubscriber<T> extends Subscriber<T> implements Subscription
    {
        static final InnerProducer[] EMPTY;
        static final InnerProducer[] TERMINATED;
        final ReplayBuffer<T> buffer;
        boolean coordinateAll;
        List<InnerProducer<T>> coordinationQueue;
        boolean done;
        boolean emitting;
        long maxChildRequested;
        long maxUpstreamRequested;
        boolean missed;
        final NotificationLite<T> nl;
        volatile Producer producer;
        final OpenHashSet<InnerProducer<T>> producers;
        InnerProducer<T>[] producersCache;
        long producersCacheVersion;
        volatile long producersVersion;
        final AtomicBoolean shouldConnect;
        volatile boolean terminated;
        
        static {
            EMPTY = new InnerProducer[0];
            TERMINATED = new InnerProducer[0];
        }
        
        public ReplaySubscriber(final ReplayBuffer<T> buffer) {
            this.buffer = buffer;
            this.nl = NotificationLite.instance();
            this.producers = new OpenHashSet<InnerProducer<T>>();
            this.producersCache = (InnerProducer<T>[])ReplaySubscriber.EMPTY;
            this.shouldConnect = new AtomicBoolean();
            this.request(0L);
        }
        
        boolean add(final InnerProducer<T> innerProducer) {
            if (innerProducer == null) {
                throw new NullPointerException();
            }
            if (this.terminated) {
                return false;
            }
            synchronized (this.producers) {
                if (this.terminated) {
                    return false;
                }
            }
            final InnerProducer<T> innerProducer2;
            this.producers.add(innerProducer2);
            ++this.producersVersion;
            // monitorexit(set)
            return true;
        }
        
        InnerProducer<T>[] copyProducers() {
            synchronized (this.producers) {
                final InnerProducer<T>[] values = this.producers.values();
                final int length = values.length;
                final InnerProducer[] array = new InnerProducer[length];
                System.arraycopy(values, 0, array, 0, length);
                return (InnerProducer<T>[])array;
            }
        }
        
        void init() {
            this.add(Subscriptions.create(new Action0() {
                @Override
                public void call() {
                    if (!ReplaySubscriber.this.terminated) {
                        synchronized (ReplaySubscriber.this.producers) {
                            if (!ReplaySubscriber.this.terminated) {
                                ReplaySubscriber.this.producers.terminate();
                                final ReplaySubscriber this$0 = ReplaySubscriber.this;
                                ++this$0.producersVersion;
                                ReplaySubscriber.this.terminated = true;
                            }
                        }
                    }
                }
            }));
        }
        
        void makeRequest(long n, long n2) {
            final long maxUpstreamRequested = this.maxUpstreamRequested;
            final Producer producer = this.producer;
            n2 = n - n2;
            if (n2 != 0L) {
                this.maxChildRequested = n;
                if (producer == null) {
                    n2 = (n = maxUpstreamRequested + n2);
                    if (n2 < 0L) {
                        n = Long.MAX_VALUE;
                    }
                    this.maxUpstreamRequested = n;
                    return;
                }
                if (maxUpstreamRequested == 0L) {
                    producer.request(n2);
                    return;
                }
                this.maxUpstreamRequested = 0L;
                producer.request(maxUpstreamRequested + n2);
            }
            else if (maxUpstreamRequested != 0L && producer != null) {
                this.maxUpstreamRequested = 0L;
                producer.request(maxUpstreamRequested);
            }
        }
        
        void manageRequests(final InnerProducer<T> innerProducer) {
            if (!this.isUnsubscribed()) {
                while (true) {
                    while (true) {
                        synchronized (this) {
                            if (!this.emitting) {
                                break;
                            }
                            if (innerProducer != null) {
                                List<InnerProducer<T>> coordinationQueue;
                                if ((coordinationQueue = this.coordinationQueue) == null) {
                                    coordinationQueue = new ArrayList<InnerProducer<T>>();
                                    this.coordinationQueue = coordinationQueue;
                                }
                                coordinationQueue.add(innerProducer);
                                this.missed = true;
                                return;
                            }
                        }
                        this.coordinateAll = true;
                        continue;
                    }
                }
                this.emitting = true;
                // monitorexit(this)
                final long maxChildRequested = this.maxChildRequested;
            Label_0112:
                while (true) {
                    Label_0149: {
                        final InnerProducer innerProducer2;
                        if (innerProducer2 == null) {
                            break Label_0149;
                        }
                        final long max = Math.max(maxChildRequested, innerProducer2.totalRequested.get());
                        this.makeRequest(max, maxChildRequested);
                        while (!this.isUnsubscribed()) {
                            Label_0212: {
                                synchronized (this) {
                                    if (!this.missed) {
                                        this.emitting = false;
                                        return;
                                    }
                                    break Label_0212;
                                }
                                break Label_0149;
                            }
                            this.missed = false;
                            final List<InnerProducer<T>> coordinationQueue2 = this.coordinationQueue;
                            this.coordinationQueue = null;
                            final boolean coordinateAll = this.coordinateAll;
                            this.coordinateAll = false;
                            // monitorexit(this)
                            final long maxChildRequested2 = this.maxChildRequested;
                            long n;
                            long max2 = n = maxChildRequested2;
                            if (coordinationQueue2 != null) {
                                final Iterator<InnerProducer<T>> iterator = coordinationQueue2.iterator();
                                while (true) {
                                    n = max2;
                                    if (!iterator.hasNext()) {
                                        break;
                                    }
                                    max2 = Math.max(max2, ((InnerProducer)(InnerProducer)iterator.next()).totalRequested.get());
                                }
                            }
                            long n2 = n;
                            if (coordinateAll) {
                                final InnerProducer<T>[] copyProducers = this.copyProducers();
                                final int length = copyProducers.length;
                                int n3 = 0;
                                while (true) {
                                    n2 = n;
                                    if (n3 >= length) {
                                        break;
                                    }
                                    final InnerProducer<T> innerProducer3 = copyProducers[n3];
                                    long max3 = n;
                                    if (innerProducer3 != null) {
                                        max3 = Math.max(n, innerProducer3.totalRequested.get());
                                    }
                                    ++n3;
                                    n = max3;
                                }
                            }
                            this.makeRequest(n2, maxChildRequested2);
                        }
                        return;
                    }
                    long n4 = maxChildRequested;
                    final InnerProducer<T>[] copyProducers2 = this.copyProducers();
                    final int length2 = copyProducers2.length;
                    int n5 = 0;
                    while (true) {
                        final long max = n4;
                        if (n5 >= length2) {
                            continue Label_0112;
                        }
                        final InnerProducer<T> innerProducer4 = copyProducers2[n5];
                        long max4 = n4;
                        if (innerProducer4 != null) {
                            max4 = Math.max(n4, innerProducer4.totalRequested.get());
                        }
                        ++n5;
                        n4 = max4;
                    }
                    break;
                }
            }
        }
        
        @Override
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            try {
                this.buffer.complete();
                this.replay();
            }
            finally {
                this.unsubscribe();
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            if (this.done) {
                return;
            }
            this.done = true;
            try {
                this.buffer.error(t);
                this.replay();
            }
            finally {
                this.unsubscribe();
            }
        }
        
        @Override
        public void onNext(final T t) {
            if (!this.done) {
                this.buffer.next(t);
                this.replay();
            }
        }
        
        void remove(final InnerProducer<T> innerProducer) {
            if (this.terminated) {
                return;
            }
            synchronized (this.producers) {
                if (this.terminated) {
                    return;
                }
            }
            final InnerProducer<T> innerProducer2;
            this.producers.remove(innerProducer2);
            if (this.producers.isEmpty()) {
                this.producersCache = (InnerProducer<T>[])ReplaySubscriber.EMPTY;
            }
            ++this.producersVersion;
        }
        // monitorexit(set)
        
        void replay() {
            InnerProducer<T>[] producersCache = this.producersCache;
            while (true) {
                if (this.producersCacheVersion != this.producersVersion) {
                    Object producers = this.producers;
                    synchronized (producers) {
                        final InnerProducer<T>[] producersCache2 = this.producersCache;
                        final InnerProducer<T>[] values = this.producers.values();
                        final int length = values.length;
                        producersCache = producersCache2;
                        if (producersCache2.length != length) {
                            producersCache = (InnerProducer<T>[])new InnerProducer[length];
                            this.producersCache = producersCache;
                        }
                        System.arraycopy(values, 0, producersCache, 0, length);
                        this.producersCacheVersion = this.producersVersion;
                        // monitorexit(producers)
                        final ReplayBuffer<T> buffer = this.buffer;
                        for (int length2 = producersCache.length, i = 0; i < length2; ++i) {
                            producers = producersCache[i];
                            if (producers != null) {
                                buffer.replay((InnerProducer<T>)producers);
                            }
                        }
                    }
                    return;
                }
                continue;
            }
        }
        
        @Override
        public void setProducer(final Producer producer) {
            if (this.producer != null) {
                throw new IllegalStateException("Only a single producer can be set on a Subscriber.");
            }
            this.producer = producer;
            this.manageRequests(null);
            this.replay();
        }
    }
    
    static final class SizeAndTimeBoundReplayBuffer<T> extends BoundedReplayBuffer<T>
    {
        private static final long serialVersionUID = 3457957419649567404L;
        final int limit;
        final long maxAgeInMillis;
        final Scheduler scheduler;
        
        public SizeAndTimeBoundReplayBuffer(final int limit, final long maxAgeInMillis, final Scheduler scheduler) {
            this.scheduler = scheduler;
            this.limit = limit;
            this.maxAgeInMillis = maxAgeInMillis;
        }
        
        @Override
        Object enterTransform(final Object o) {
            return new Timestamped(this.scheduler.now(), o);
        }
        
        @Override
        Node getInitialHead() {
            final long now = this.scheduler.now();
            final long maxAgeInMillis = this.maxAgeInMillis;
            Node node = this.get();
            for (Node node2 = node.get(); node2 != null && ((Timestamped)node2.value).getTimestampMillis() <= now - maxAgeInMillis; node2 = (Node)node2.get()) {
                node = node2;
            }
            return node;
        }
        
        @Override
        Object leaveTransform(final Object o) {
            return ((Timestamped)o).getValue();
        }
        
        @Override
        void truncate() {
            final long now = this.scheduler.now();
            final long maxAgeInMillis = this.maxAgeInMillis;
            Node first = this.get();
            Node node = first.get();
            int n = 0;
            while (node != null) {
                if (this.size > this.limit) {
                    ++n;
                    --this.size;
                    first = node;
                    node = (Node)node.get();
                }
                else {
                    if (((Timestamped)node.value).getTimestampMillis() > now - maxAgeInMillis) {
                        break;
                    }
                    ++n;
                    --this.size;
                    first = node;
                    node = (Node)node.get();
                }
            }
            if (n != 0) {
                this.setFirst(first);
            }
        }
        
        @Override
        void truncateFinal() {
            final long now = this.scheduler.now();
            final long maxAgeInMillis = this.maxAgeInMillis;
            Node first = this.get();
            Node node = first.get();
            int n = 0;
            while (node != null && this.size > 1 && ((Timestamped)node.value).getTimestampMillis() <= now - maxAgeInMillis) {
                ++n;
                --this.size;
                first = node;
                node = (Node)node.get();
            }
            if (n != 0) {
                this.setFirst(first);
            }
        }
    }
    
    static final class SizeBoundReplayBuffer<T> extends BoundedReplayBuffer<T>
    {
        private static final long serialVersionUID = -5898283885385201806L;
        final int limit;
        
        public SizeBoundReplayBuffer(final int limit) {
            this.limit = limit;
        }
        
        @Override
        void truncate() {
            if (this.size > this.limit) {
                this.removeFirst();
            }
        }
    }
    
    static final class UnboundedReplayBuffer<T> extends ArrayList<Object> implements ReplayBuffer<T>
    {
        private static final long serialVersionUID = 7063189396499112664L;
        final NotificationLite<T> nl;
        volatile int size;
        
        public UnboundedReplayBuffer(final int n) {
            super(n);
            this.nl = NotificationLite.instance();
        }
        
        @Override
        public void complete() {
            this.add(this.nl.completed());
            ++this.size;
        }
        
        @Override
        public void error(final Throwable t) {
            this.add(this.nl.error(t));
            ++this.size;
        }
        
        @Override
        public void next(final T t) {
            this.add(this.nl.next(t));
            ++this.size;
        }
        
        @Override
        public void replay(final InnerProducer<T> innerProducer) {
        Label_0266:
            while (true) {
                // monitorenter(innerProducer)
                while (true) {
                    int intValue = 0;
                    long value = 0L;
                    long n2 = 0L;
                Label_0076_Outer:
                    while (true) {
                        while (true) {
                            Observer<T> child;
                            Object value2;
                            try {
                                if (innerProducer.emitting) {
                                    innerProducer.missed = true;
                                    return;
                                }
                                innerProducer.emitting = true;
                                // monitorexit(innerProducer)
                                if (innerProducer.isUnsubscribed()) {
                                    break;
                                }
                                final int size = this.size;
                                final Integer n = innerProducer.index();
                                if (n == null) {
                                    break Label_0076_Outer;
                                }
                                intValue = n;
                                child = (Observer<T>)innerProducer.child;
                                if (child != null) {
                                    value = innerProducer.get();
                                    n2 = 0L;
                                    while (n2 != value && intValue < size) {
                                        value2 = this.get(intValue);
                                        final UnboundedReplayBuffer unboundedReplayBuffer = this;
                                        final NotificationLite<T> notificationLite = unboundedReplayBuffer.nl;
                                        final Object o = child;
                                        final Object o2 = value2;
                                        final boolean accept = notificationLite.accept((Observer<? super T>)o, o2);
                                        final boolean accept2 = accept;
                                        if (accept2) {
                                            break Label_0266;
                                        }
                                        final InnerProducer innerProducer2 = (InnerProducer)innerProducer;
                                        final boolean b = innerProducer2.isUnsubscribed();
                                        if (b) {
                                            break Label_0266;
                                        }
                                        final int n3 = intValue;
                                        final int n4 = 1;
                                        intValue = n3 + n4;
                                        final long n5 = n2;
                                        final long n6 = 1L;
                                        final long n7 = n2 = n5 + n6;
                                    }
                                    break Label_0076_Outer;
                                }
                                break;
                            }
                            finally {
                                final Subscriber subscriber;
                                child = (Observer<T>)subscriber;
                            }
                            // monitorexit(innerProducer)
                            try {
                                final UnboundedReplayBuffer unboundedReplayBuffer = this;
                                final NotificationLite<T> notificationLite = unboundedReplayBuffer.nl;
                                final Object o = child;
                                final Object o2 = value2;
                                final boolean accept2;
                                final boolean accept = accept2 = notificationLite.accept((Observer<? super T>)o, o2);
                                if (accept2) {
                                    break;
                                }
                                final InnerProducer innerProducer2 = (InnerProducer)innerProducer;
                                final boolean b = innerProducer2.isUnsubscribed();
                                if (!b) {
                                    final int n3 = intValue;
                                    final int n4 = 1;
                                    intValue = n3 + n4;
                                    final long n5 = n2;
                                    final long n6 = 1L;
                                    n2 = n5 + n6;
                                    continue;
                                }
                                break;
                                intValue = 0;
                                continue Label_0076_Outer;
                            }
                            catch (Throwable t) {
                                Exceptions.throwIfFatal(t);
                                innerProducer.unsubscribe();
                                if (!this.nl.isError(value2) && !this.nl.isCompleted(value2)) {
                                    child.onError(OnErrorThrowable.addValueAsLastCause(t, this.nl.getValue(value2)));
                                    return;
                                }
                                break;
                            }
                            break;
                        }
                        break;
                    }
                    if (n2 != 0L) {
                        innerProducer.index = intValue;
                        if (value != Long.MAX_VALUE) {
                            innerProducer.produced(n2);
                        }
                    }
                    synchronized (innerProducer) {
                        if (!innerProducer.missed) {
                            innerProducer.emitting = false;
                            return;
                        }
                    }
                    innerProducer.missed = false;
                    continue;
                }
            }
            // monitorexit(innerProducer)
        }
    }
}
