// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.Collection;
import java.util.ArrayList;
import rx.exceptions.CompositeException;
import java.util.Queue;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;
import rx.Producer;
import java.util.concurrent.atomic.AtomicInteger;
import rx.plugins.RxJavaHooks;
import java.util.Iterator;
import java.util.List;
import rx.Subscriber;
import rx.internal.util.RxRingBuffer;
import rx.functions.FuncN;
import rx.Observable;

public final class OnSubscribeCombineLatest<T, R> implements OnSubscribe<R>
{
    final int bufferSize;
    final FuncN<? extends R> combiner;
    final boolean delayError;
    final Observable<? extends T>[] sources;
    final Iterable<? extends Observable<? extends T>> sourcesIterable;
    
    public OnSubscribeCombineLatest(final Iterable<? extends Observable<? extends T>> iterable, final FuncN<? extends R> funcN) {
        this(null, iterable, funcN, RxRingBuffer.SIZE, false);
    }
    
    public OnSubscribeCombineLatest(final Observable<? extends T>[] sources, final Iterable<? extends Observable<? extends T>> sourcesIterable, final FuncN<? extends R> combiner, final int bufferSize, final boolean delayError) {
        this.sources = sources;
        this.sourcesIterable = sourcesIterable;
        this.combiner = combiner;
        this.bufferSize = bufferSize;
        this.delayError = delayError;
    }
    
    @Override
    public void call(final Subscriber<? super R> subscriber) {
        Observable<? extends T>[] sources = this.sources;
        int n = 0;
        int n2;
        if (sources == null) {
            if (this.sourcesIterable instanceof List) {
                final List list = (List)this.sourcesIterable;
                sources = list.toArray(new Observable[list.size()]);
                n2 = sources.length;
            }
            else {
                Observable<? extends T>[] array = (Observable<? extends T>[])new Observable[8];
                final Iterator<? extends Observable<? extends T>> iterator = this.sourcesIterable.iterator();
                while (true) {
                    n2 = n;
                    sources = array;
                    if (!iterator.hasNext()) {
                        break;
                    }
                    final Observable<? extends T> observable = (Observable<? extends T>)iterator.next();
                    Observable<? extends T>[] array2 = array;
                    if (n == array.length) {
                        array2 = (Observable<? extends T>[])new Observable[(n >> 2) + n];
                        System.arraycopy(array, 0, array2, 0, n);
                    }
                    array2[n] = observable;
                    ++n;
                    array = array2;
                }
            }
        }
        else {
            n2 = sources.length;
        }
        if (n2 == 0) {
            subscriber.onCompleted();
            return;
        }
        new LatestCoordinator((Subscriber<? super Object>)subscriber, this.combiner, n2, this.bufferSize, this.delayError).subscribe((Observable[])sources);
    }
    
    static final class CombinerSubscriber<T, R> extends Subscriber<T>
    {
        boolean done;
        final int index;
        final NotificationLite<T> nl;
        final LatestCoordinator<T, R> parent;
        
        public CombinerSubscriber(final LatestCoordinator<T, R> parent, final int index) {
            this.parent = parent;
            this.index = index;
            this.nl = NotificationLite.instance();
            this.request(parent.bufferSize);
        }
        
        @Override
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.parent.combine(null, this.index);
        }
        
        @Override
        public void onError(final Throwable t) {
            if (this.done) {
                RxJavaHooks.onError(t);
                return;
            }
            this.parent.onError(t);
            this.done = true;
            this.parent.combine(null, this.index);
        }
        
        @Override
        public void onNext(final T t) {
            if (this.done) {
                return;
            }
            this.parent.combine(this.nl.next(t), this.index);
        }
        
        public void requestMore(final long n) {
            this.request(n);
        }
    }
    
    static final class LatestCoordinator<T, R> extends AtomicInteger implements Producer, Subscription
    {
        static final Object MISSING;
        private static final long serialVersionUID = 8567835998786448817L;
        int active;
        final Subscriber<? super R> actual;
        final int bufferSize;
        volatile boolean cancelled;
        final FuncN<? extends R> combiner;
        int complete;
        final boolean delayError;
        volatile boolean done;
        final AtomicReference<Throwable> error;
        final Object[] latest;
        final SpscLinkedArrayQueue<Object> queue;
        final AtomicLong requested;
        final CombinerSubscriber<T, R>[] subscribers;
        
        static {
            MISSING = new Object();
        }
        
        public LatestCoordinator(final Subscriber<? super R> actual, final FuncN<? extends R> combiner, final int n, final int bufferSize, final boolean delayError) {
            this.actual = actual;
            this.combiner = combiner;
            this.bufferSize = bufferSize;
            this.delayError = delayError;
            Arrays.fill(this.latest = new Object[n], LatestCoordinator.MISSING);
            this.subscribers = (CombinerSubscriber<T, R>[])new CombinerSubscriber[n];
            this.queue = new SpscLinkedArrayQueue<Object>(bufferSize);
            this.requested = new AtomicLong();
            this.error = new AtomicReference<Throwable>();
        }
        
        void cancel(final Queue<?> queue) {
            queue.clear();
            final CombinerSubscriber<T, R>[] subscribers = this.subscribers;
            for (int length = subscribers.length, i = 0; i < length; ++i) {
                subscribers[i].unsubscribe();
            }
        }
        
        boolean checkTerminated(final boolean b, final boolean b2, final Subscriber<?> subscriber, final Queue<?> queue, final boolean b3) {
            if (this.cancelled) {
                this.cancel(queue);
                return true;
            }
            if (b) {
                if (b3) {
                    if (b2) {
                        final Throwable t = this.error.get();
                        if (t != null) {
                            subscriber.onError(t);
                            return true;
                        }
                        subscriber.onCompleted();
                        return true;
                    }
                }
                else {
                    final Throwable t2 = this.error.get();
                    if (t2 != null) {
                        this.cancel(queue);
                        subscriber.onError(t2);
                        return true;
                    }
                    if (b2) {
                        subscriber.onCompleted();
                        return true;
                    }
                }
            }
            return false;
        }
        
        void combine(final Object o, int complete) {
        Label_0105_Outer:
            while (true) {
                final int n = 0;
                final CombinerSubscriber<T, R> combinerSubscriber = this.subscribers[complete];
                while (true) {
                    Label_0243: {
                        while (true) {
                        Label_0134_Outer:
                            while (true) {
                                int length = 0;
                                Object o2;
                                int active = 0;
                                int n2;
                                int complete2;
                                final Throwable t;
                                Block_10_Outer:Block_14_Outer:Block_12_Outer:
                                while (true) {
                                    synchronized (this) {
                                        length = this.latest.length;
                                        o2 = this.latest[complete];
                                        n2 = (active = this.active);
                                        if (o2 == LatestCoordinator.MISSING) {
                                            active = n2 + 1;
                                            this.active = active;
                                        }
                                        complete2 = this.complete;
                                        if (o == null) {
                                            complete = complete2 + 1;
                                            this.complete = complete;
                                            break Block_10_Outer;
                                        }
                                        this.latest[complete] = combinerSubscriber.nl.getValue(o);
                                        complete = complete2;
                                        break Block_10_Outer;
                                        // iftrue(Label_0243:, complete == length)
                                        // monitorexit(this)
                                        // iftrue(Label_0227:, active != 0 || t == null)
                                        // iftrue(Label_0105:, o2 != LatestCoordinator.MISSING)
                                        // iftrue(Label_0105:, t != null)
                                        // iftrue(Label_0182:, t == null || active == 0)
                                        // iftrue(Label_0219:, complete != 0)
                                        Block_11: {
                                            while (true) {
                                                Block_9: {
                                                    break Block_9;
                                                    combinerSubscriber.requestMore(1L);
                                                    return;
                                                    complete = n;
                                                    break Block_11;
                                                }
                                                complete = n;
                                                continue Block_14_Outer;
                                            }
                                            while (true) {
                                                while (true) {
                                                    this.queue.offer(combinerSubscriber, this.latest.clone());
                                                    continue Block_10_Outer;
                                                    continue Block_12_Outer;
                                                }
                                                continue Label_0105_Outer;
                                            }
                                        }
                                        break Label_0243;
                                    }
                                    active = 0;
                                    continue Label_0134_Outer;
                                    Label_0182: {
                                        if (t == null && this.error.get() != null && (o2 == LatestCoordinator.MISSING || !this.delayError)) {
                                            this.done = true;
                                            continue Label_0105_Outer;
                                        }
                                    }
                                    continue Label_0105_Outer;
                                    Label_0219:
                                    this.done = true;
                                    continue Label_0105_Outer;
                                }
                                if (active == length) {
                                    active = 1;
                                    continue Label_0105_Outer;
                                }
                                break;
                            }
                            continue;
                        }
                    }
                    complete = 1;
                    continue;
                }
            }
            Label_0227: {
                this.drain();
            }
        }
        
        void drain() {
            Label_0007: {
                if (this.getAndIncrement() == 0) {
                    final SpscLinkedArrayQueue<Object> queue = this.queue;
                    final Subscriber<? super R> actual = this.actual;
                    final boolean delayError = this.delayError;
                    final AtomicLong requested = this.requested;
                    int addAndGet = 1;
                    while (!this.checkTerminated(this.done, queue.isEmpty(), actual, queue, delayError)) {
                        final long value = requested.get();
                        long n = 0L;
                        while (n != value) {
                            final boolean done = this.done;
                            final CombinerSubscriber combinerSubscriber = queue.peek();
                            final boolean b = combinerSubscriber == null;
                            if (this.checkTerminated(done, b, actual, queue, delayError)) {
                                break Label_0007;
                            }
                            if (b) {
                                break;
                            }
                            queue.poll();
                            final Object[] array = queue.poll();
                            if (array == null) {
                                this.cancelled = true;
                                this.cancel(queue);
                                actual.onError(new IllegalStateException("Broken queue?! Sender received but not the array."));
                                return;
                            }
                            try {
                                actual.onNext((Object)this.combiner.call(array));
                                combinerSubscriber.requestMore(1L);
                                ++n;
                            }
                            catch (Throwable t) {
                                this.cancelled = true;
                                this.cancel(queue);
                                actual.onError(t);
                                return;
                            }
                        }
                        if (n != 0L && value != Long.MAX_VALUE) {
                            BackpressureUtils.produced(requested, n);
                        }
                        if ((addAndGet = this.addAndGet(-addAndGet)) == 0) {
                            return;
                        }
                    }
                }
            }
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.cancelled;
        }
        
        void onError(final Throwable t) {
            final AtomicReference<Throwable> error = this.error;
            Throwable t2;
            Throwable t3;
            do {
                t2 = error.get();
                if (t2 != null) {
                    if (t2 instanceof CompositeException) {
                        final ArrayList list = new ArrayList<Throwable>(((CompositeException)t2).getExceptions());
                        list.add(t);
                        t3 = new CompositeException((Collection<? extends Throwable>)list);
                    }
                    else {
                        t3 = new CompositeException(Arrays.asList(t2, t));
                    }
                }
                else {
                    t3 = t;
                }
            } while (!error.compareAndSet(t2, t3));
        }
        
        @Override
        public void request(final long n) {
            if (n < 0L) {
                throw new IllegalArgumentException("n >= required but it was " + n);
            }
            if (n != 0L) {
                BackpressureUtils.getAndAddRequest(this.requested, n);
                this.drain();
            }
        }
        
        public void subscribe(final Observable<? extends T>[] array) {
            final CombinerSubscriber<T, R>[] subscribers = this.subscribers;
            final int length = subscribers.length;
            for (int i = 0; i < length; ++i) {
                subscribers[i] = (CombinerSubscriber<T, R>)new CombinerSubscriber((LatestCoordinator<Object, Object>)this, i);
            }
            this.lazySet(0);
            this.actual.add(this);
            this.actual.setProducer(this);
            for (int n = 0; n < length && !this.cancelled; ++n) {
                array[n].subscribe((Subscriber<? super T>)subscribers[n]);
            }
        }
        
        @Override
        public void unsubscribe() {
            if (!this.cancelled) {
                this.cancelled = true;
                if (this.getAndIncrement() == 0) {
                    this.cancel(this.queue);
                }
            }
        }
    }
}
