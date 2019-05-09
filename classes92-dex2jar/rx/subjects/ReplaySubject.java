// 
// Decompiled by Procyon v0.5.34
// 

package rx.subjects;

import java.lang.reflect.Array;
import rx.plugins.RxJavaHooks;
import java.util.List;
import rx.exceptions.Exceptions;
import rx.Observer;
import java.util.concurrent.atomic.AtomicReference;
import java.util.ArrayList;
import java.io.Serializable;
import rx.internal.operators.BackpressureUtils;
import java.util.concurrent.atomic.AtomicLong;
import rx.Subscriber;
import rx.Subscription;
import rx.Producer;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Scheduler;
import java.util.concurrent.TimeUnit;
import rx.schedulers.Schedulers;
import rx.Observable;

public final class ReplaySubject<T> extends Subject<T, T>
{
    private static final Object[] EMPTY_ARRAY;
    final ReplayState<T> state;
    
    static {
        EMPTY_ARRAY = new Object[0];
    }
    
    ReplaySubject(final ReplayState<T> state) {
        super(state);
        this.state = state;
    }
    
    public static <T> ReplaySubject<T> create() {
        return create(16);
    }
    
    public static <T> ReplaySubject<T> create(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("capacity > 0 required but it was " + n);
        }
        return new ReplaySubject<T>(new ReplayState<T>(new ReplayUnboundedBuffer<T>(n)));
    }
    
    static <T> ReplaySubject<T> createUnbounded() {
        return new ReplaySubject<T>(new ReplayState<T>(new ReplaySizeBoundBuffer<T>(Integer.MAX_VALUE)));
    }
    
    static <T> ReplaySubject<T> createUnboundedTime() {
        return new ReplaySubject<T>(new ReplayState<T>(new ReplaySizeAndTimeBoundBuffer<T>(Integer.MAX_VALUE, Long.MAX_VALUE, Schedulers.immediate())));
    }
    
    public static <T> ReplaySubject<T> createWithSize(final int n) {
        return new ReplaySubject<T>(new ReplayState<T>(new ReplaySizeBoundBuffer<T>(n)));
    }
    
    public static <T> ReplaySubject<T> createWithTime(final long n, final TimeUnit timeUnit, final Scheduler scheduler) {
        return createWithTimeAndSize(n, timeUnit, Integer.MAX_VALUE, scheduler);
    }
    
    public static <T> ReplaySubject<T> createWithTimeAndSize(final long n, final TimeUnit timeUnit, final int n2, final Scheduler scheduler) {
        return new ReplaySubject<T>(new ReplayState<T>(new ReplaySizeAndTimeBoundBuffer<T>(n2, timeUnit.toMillis(n), scheduler)));
    }
    
    public Throwable getThrowable() {
        if (this.state.isTerminated()) {
            return this.state.buffer.error();
        }
        return null;
    }
    
    public T getValue() {
        return this.state.buffer.last();
    }
    
    public Object[] getValues() {
        Object[] values;
        if ((values = this.getValues(ReplaySubject.EMPTY_ARRAY)) == ReplaySubject.EMPTY_ARRAY) {
            values = new Object[0];
        }
        return values;
    }
    
    public T[] getValues(final T[] array) {
        return this.state.buffer.toArray(array);
    }
    
    public boolean hasAnyValue() {
        return !this.state.buffer.isEmpty();
    }
    
    public boolean hasCompleted() {
        return this.state.isTerminated() && this.state.buffer.error() == null;
    }
    
    @Override
    public boolean hasObservers() {
        return ((ReplayProducer[])this.state.get()).length != 0;
    }
    
    public boolean hasThrowable() {
        return this.state.isTerminated() && this.state.buffer.error() != null;
    }
    
    public boolean hasValue() {
        return this.hasAnyValue();
    }
    
    @Override
    public void onCompleted() {
        this.state.onCompleted();
    }
    
    @Override
    public void onError(final Throwable t) {
        this.state.onError(t);
    }
    
    @Override
    public void onNext(final T t) {
        this.state.onNext(t);
    }
    
    public int size() {
        return this.state.buffer.size();
    }
    
    int subscriberCount() {
        return ((ReplayProducer[])this.state.get()).length;
    }
    
    interface ReplayBuffer<T>
    {
        void complete();
        
        void drain(final ReplayProducer<T> p0);
        
        Throwable error();
        
        void error(final Throwable p0);
        
        boolean isComplete();
        
        boolean isEmpty();
        
        T last();
        
        void next(final T p0);
        
        int size();
        
        T[] toArray(final T[] p0);
    }
    
    static final class ReplayProducer<T> extends AtomicInteger implements Producer, Subscription
    {
        private static final long serialVersionUID = -5006209596735204567L;
        final Subscriber<? super T> actual;
        int index;
        Object node;
        final AtomicLong requested;
        final ReplayState<T> state;
        int tailIndex;
        
        public ReplayProducer(final Subscriber<? super T> actual, final ReplayState<T> state) {
            this.actual = actual;
            this.requested = new AtomicLong();
            this.state = state;
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.actual.isUnsubscribed();
        }
        
        @Override
        public void request(final long n) {
            if (n > 0L) {
                BackpressureUtils.getAndAddRequest(this.requested, n);
                this.state.buffer.drain(this);
            }
            else if (n < 0L) {
                throw new IllegalArgumentException("n >= required but it was " + n);
            }
        }
        
        @Override
        public void unsubscribe() {
            this.state.remove(this);
        }
    }
    
    static final class ReplaySizeAndTimeBoundBuffer<T> implements ReplayBuffer<T>
    {
        volatile boolean done;
        Throwable error;
        volatile TimedNode<T> head;
        final int limit;
        final long maxAgeMillis;
        final Scheduler scheduler;
        int size;
        TimedNode<T> tail;
        
        public ReplaySizeAndTimeBoundBuffer(final int limit, final long maxAgeMillis, final Scheduler scheduler) {
            this.limit = limit;
            final TimedNode<T> timedNode = new TimedNode<T>(null, 0L);
            this.tail = timedNode;
            this.head = timedNode;
            this.maxAgeMillis = maxAgeMillis;
            this.scheduler = scheduler;
        }
        
        @Override
        public void complete() {
            this.evictFinal();
            this.done = true;
        }
        
        @Override
        public void drain(final ReplayProducer<T> replayProducer) {
            if (replayProducer.getAndIncrement() != 0) {
                return;
            }
            final Subscriber<? super T> actual = replayProducer.actual;
            int addAndGet = 1;
            do {
                final long value = replayProducer.requested.get();
                final long n = 0L;
                final TimedNode timedNode = (TimedNode)replayProducer.node;
                long n2 = n;
                Serializable latestHead;
                if ((latestHead = timedNode) == null) {
                    latestHead = this.latestHead();
                    n2 = n;
                }
                while (n2 != value) {
                    if (actual.isUnsubscribed()) {
                        replayProducer.node = null;
                        return;
                    }
                    final boolean done = this.done;
                    final TimedNode timedNode2 = ((AtomicReference<TimedNode>)latestHead).get();
                    boolean b;
                    if (timedNode2 == null) {
                        b = true;
                    }
                    else {
                        b = false;
                    }
                    if (done && b) {
                        replayProducer.node = null;
                        final Throwable error = this.error;
                        if (error != null) {
                            actual.onError(error);
                            return;
                        }
                        actual.onCompleted();
                        return;
                    }
                    else {
                        if (b) {
                            break;
                        }
                        actual.onNext(timedNode2.value);
                        ++n2;
                        latestHead = timedNode2;
                    }
                }
                if (n2 == value) {
                    if (actual.isUnsubscribed()) {
                        replayProducer.node = null;
                        return;
                    }
                    final boolean done2 = this.done;
                    boolean b2;
                    if (((AtomicReference<TimedNode>)latestHead).get() == null) {
                        b2 = true;
                    }
                    else {
                        b2 = false;
                    }
                    if (done2 && b2) {
                        replayProducer.node = null;
                        final Throwable error2 = this.error;
                        if (error2 != null) {
                            actual.onError(error2);
                            return;
                        }
                        actual.onCompleted();
                        return;
                    }
                }
                if (n2 != 0L && value != Long.MAX_VALUE) {
                    BackpressureUtils.produced(replayProducer.requested, n2);
                }
                replayProducer.node = latestHead;
            } while ((addAndGet = replayProducer.addAndGet(-addAndGet)) != 0);
        }
        
        @Override
        public Throwable error() {
            return this.error;
        }
        
        @Override
        public void error(final Throwable error) {
            this.evictFinal();
            this.error = error;
            this.done = true;
        }
        
        void evictFinal() {
            final long now = this.scheduler.now();
            final long maxAgeMillis = this.maxAgeMillis;
            TimedNode<T> head;
            final TimedNode<T> timedNode = head = this.head;
            while (true) {
                final TimedNode<T> timedNode2 = head.get();
                if (timedNode2 == null || timedNode2.timestamp > now - maxAgeMillis) {
                    break;
                }
                head = timedNode2;
            }
            if (timedNode != head) {
                this.head = head;
            }
        }
        
        @Override
        public boolean isComplete() {
            return this.done;
        }
        
        @Override
        public boolean isEmpty() {
            return this.latestHead().get() == null;
        }
        
        @Override
        public T last() {
            TimedNode<T> latestHead = this.latestHead();
            while (true) {
                final TimedNode<T> timedNode = latestHead.get();
                if (timedNode == null) {
                    break;
                }
                latestHead = timedNode;
            }
            return latestHead.value;
        }
        
        TimedNode<T> latestHead() {
            final long now = this.scheduler.now();
            final long maxAgeMillis = this.maxAgeMillis;
            Serializable head = this.head;
            while (true) {
                final TimedNode timedNode = ((AtomicReference<TimedNode>)head).get();
                if (timedNode == null || timedNode.timestamp > now - maxAgeMillis) {
                    break;
                }
                head = timedNode;
            }
            return (TimedNode<T>)head;
        }
        
        @Override
        public void next(final T t) {
            final long now = this.scheduler.now();
            final TimedNode tail = new TimedNode<T>((T)t, now);
            this.tail.set(tail);
            this.tail = (TimedNode<T>)tail;
            final long maxAgeMillis = this.maxAgeMillis;
            int size = this.size;
            TimedNode<?> head;
            final TimedNode<?> timedNode = head = this.head;
            if (size == this.limit) {
                head = head.get();
            }
            else {
                ++size;
            }
            while (true) {
                final TimedNode<?> timedNode2 = head.get();
                if (timedNode2 == null || timedNode2.timestamp > now - maxAgeMillis) {
                    break;
                }
                head = timedNode2;
                --size;
            }
            this.size = size;
            if (head != timedNode) {
                this.head = (TimedNode<T>)head;
            }
        }
        
        @Override
        public int size() {
            int n = 0;
            for (TimedNode timedNode = this.latestHead().get(); timedNode != null && n != Integer.MAX_VALUE; timedNode = timedNode.get(), ++n) {}
            return n;
        }
        
        @Override
        public T[] toArray(final T[] array) {
            final ArrayList<Object> list = new ArrayList<Object>();
            for (TimedNode timedNode = this.latestHead().get(); timedNode != null; timedNode = timedNode.get()) {
                list.add(timedNode.value);
            }
            return list.toArray(array);
        }
        
        static final class TimedNode<T> extends AtomicReference<TimedNode<T>>
        {
            private static final long serialVersionUID = 3713592843205853725L;
            final long timestamp;
            final T value;
            
            public TimedNode(final T value, final long timestamp) {
                this.value = value;
                this.timestamp = timestamp;
            }
        }
    }
    
    static final class ReplaySizeBoundBuffer<T> implements ReplayBuffer<T>
    {
        volatile boolean done;
        Throwable error;
        volatile Node<T> head;
        final int limit;
        int size;
        Node<T> tail;
        
        public ReplaySizeBoundBuffer(final int limit) {
            this.limit = limit;
            final Node<T> node = new Node<T>(null);
            this.tail = node;
            this.head = node;
        }
        
        @Override
        public void complete() {
            this.done = true;
        }
        
        @Override
        public void drain(final ReplayProducer<T> replayProducer) {
            if (replayProducer.getAndIncrement() != 0) {
                return;
            }
            final Subscriber<? super T> actual = replayProducer.actual;
            int addAndGet = 1;
            do {
                final long value = replayProducer.requested.get();
                final long n = 0L;
                final Node node = (Node)replayProducer.node;
                long n2 = n;
                Serializable head;
                if ((head = node) == null) {
                    head = this.head;
                    n2 = n;
                }
                while (n2 != value) {
                    if (actual.isUnsubscribed()) {
                        replayProducer.node = null;
                        return;
                    }
                    final boolean done = this.done;
                    final Node node2 = ((AtomicReference<Node>)head).get();
                    boolean b;
                    if (node2 == null) {
                        b = true;
                    }
                    else {
                        b = false;
                    }
                    if (done && b) {
                        replayProducer.node = null;
                        final Throwable error = this.error;
                        if (error != null) {
                            actual.onError(error);
                            return;
                        }
                        actual.onCompleted();
                        return;
                    }
                    else {
                        if (b) {
                            break;
                        }
                        actual.onNext(node2.value);
                        ++n2;
                        head = node2;
                    }
                }
                if (n2 == value) {
                    if (actual.isUnsubscribed()) {
                        replayProducer.node = null;
                        return;
                    }
                    final boolean done2 = this.done;
                    boolean b2;
                    if (((AtomicReference<Node>)head).get() == null) {
                        b2 = true;
                    }
                    else {
                        b2 = false;
                    }
                    if (done2 && b2) {
                        replayProducer.node = null;
                        final Throwable error2 = this.error;
                        if (error2 != null) {
                            actual.onError(error2);
                            return;
                        }
                        actual.onCompleted();
                        return;
                    }
                }
                if (n2 != 0L && value != Long.MAX_VALUE) {
                    BackpressureUtils.produced(replayProducer.requested, n2);
                }
                replayProducer.node = head;
            } while ((addAndGet = replayProducer.addAndGet(-addAndGet)) != 0);
        }
        
        @Override
        public Throwable error() {
            return this.error;
        }
        
        @Override
        public void error(final Throwable error) {
            this.error = error;
            this.done = true;
        }
        
        @Override
        public boolean isComplete() {
            return this.done;
        }
        
        @Override
        public boolean isEmpty() {
            return this.head.get() == null;
        }
        
        @Override
        public T last() {
            Node<T> head = this.head;
            while (true) {
                final Node<T> node = head.get();
                if (node == null) {
                    break;
                }
                head = node;
            }
            return head.value;
        }
        
        @Override
        public void next(final T t) {
            final Node<T> tail = new Node<T>(t);
            this.tail.set((Node<T>)tail);
            this.tail = tail;
            final int size = this.size;
            if (size == this.limit) {
                this.head = (Node<T>)this.head.get();
                return;
            }
            this.size = size + 1;
        }
        
        @Override
        public int size() {
            int n = 0;
            for (Node node = this.head.get(); node != null && n != Integer.MAX_VALUE; node = node.get(), ++n) {}
            return n;
        }
        
        @Override
        public T[] toArray(final T[] array) {
            final ArrayList<Object> list = new ArrayList<Object>();
            for (Node node = this.head.get(); node != null; node = node.get()) {
                list.add(node.value);
            }
            return list.toArray(array);
        }
        
        static final class Node<T> extends AtomicReference<Node<T>>
        {
            private static final long serialVersionUID = 3713592843205853725L;
            final T value;
            
            public Node(final T value) {
                this.value = value;
            }
        }
    }
    
    static final class ReplayState<T> extends AtomicReference<ReplayProducer<T>[]> implements OnSubscribe<T>, Observer<T>
    {
        static final ReplayProducer[] EMPTY;
        static final ReplayProducer[] TERMINATED;
        private static final long serialVersionUID = 5952362471246910544L;
        final ReplayBuffer<T> buffer;
        
        static {
            EMPTY = new ReplayProducer[0];
            TERMINATED = new ReplayProducer[0];
        }
        
        public ReplayState(final ReplayBuffer<T> buffer) {
            this.buffer = buffer;
            this.lazySet(ReplayState.EMPTY);
        }
        
        boolean add(final ReplayProducer<T> replayProducer) {
            ReplayProducer[] array;
            ReplayProducer[] array2;
            do {
                array = (ReplayProducer[])this.get();
                if (array == ReplayState.TERMINATED) {
                    return false;
                }
                final int length = array.length;
                array2 = new ReplayProducer[length + 1];
                System.arraycopy(array, 0, array2, 0, length);
                array2[length] = replayProducer;
            } while (!this.compareAndSet(array, array2));
            return true;
        }
        
        @Override
        public void call(final Subscriber<? super T> subscriber) {
            final ReplayProducer producer = new ReplayProducer(subscriber, (ReplayState)this);
            subscriber.add(producer);
            subscriber.setProducer(producer);
            if (this.add((ReplayProducer<T>)producer) && producer.isUnsubscribed()) {
                this.remove((ReplayProducer<T>)producer);
                return;
            }
            this.buffer.drain((ReplayProducer<T>)producer);
        }
        
        boolean isTerminated() {
            return this.get() == ReplayState.TERMINATED;
        }
        
        @Override
        public void onCompleted() {
            final ReplayBuffer<T> buffer = this.buffer;
            buffer.complete();
            final ReplayProducer[] array = this.getAndSet(ReplayState.TERMINATED);
            for (int length = array.length, i = 0; i < length; ++i) {
                buffer.drain(array[i]);
            }
        }
        
        @Override
        public void onError(Throwable t) {
            final ReplayBuffer<T> buffer = this.buffer;
            buffer.error(t);
            t = null;
            final ReplayProducer[] array = this.getAndSet(ReplayState.TERMINATED);
            final int length = array.length;
            int i = 0;
        Label_0054_Outer:
            while (i < length) {
                final ReplayProducer replayProducer = array[i];
                while (true) {
                    try {
                        buffer.drain(replayProducer);
                        ++i;
                        continue Label_0054_Outer;
                    }
                    catch (Throwable t2) {
                        Object o = t;
                        if (t == null) {
                            o = new ArrayList<Throwable>();
                        }
                        ((List<Throwable>)o).add(t2);
                        t = (Throwable)o;
                        continue;
                    }
                    break;
                }
                break;
            }
            Exceptions.throwIfAny((List<? extends Throwable>)t);
        }
        
        @Override
        public void onNext(final T t) {
            final ReplayBuffer<T> buffer = this.buffer;
            buffer.next(t);
            final ReplayProducer[] array = this.get();
            for (int length = array.length, i = 0; i < length; ++i) {
                buffer.drain(array[i]);
            }
        }
        
        void remove(final ReplayProducer<T> replayProducer) {
            while (true) {
                final ReplayProducer[] array = this.get();
                if (array == ReplayState.TERMINATED || array == ReplayState.EMPTY) {
                    break;
                }
                final int length = array.length;
                final int n = -1;
                int n2 = 0;
                int n3;
                while (true) {
                    n3 = n;
                    if (n2 >= length) {
                        break;
                    }
                    if (array[n2] == replayProducer) {
                        n3 = n2;
                        break;
                    }
                    ++n2;
                }
                if (n3 < 0) {
                    break;
                }
                ReplayProducer[] empty;
                if (length == 1) {
                    empty = ReplayState.EMPTY;
                }
                else {
                    empty = new ReplayProducer[length - 1];
                    System.arraycopy(array, 0, empty, 0, n3);
                    System.arraycopy(array, n3 + 1, empty, n3, length - n3 - 1);
                }
                if (this.compareAndSet(array, empty)) {
                    return;
                }
            }
        }
    }
    
    static final class ReplayUnboundedBuffer<T> implements ReplayBuffer<T>
    {
        final int capacity;
        volatile boolean done;
        Throwable error;
        final Object[] head;
        volatile int size;
        Object[] tail;
        int tailIndex;
        
        public ReplayUnboundedBuffer(final int capacity) {
            this.capacity = capacity;
            final Object[] array = new Object[capacity + 1];
            this.head = array;
            this.tail = array;
        }
        
        @Override
        public void complete() {
            this.done = true;
        }
        
        @Override
        public void drain(final ReplayProducer<T> replayProducer) {
            if (replayProducer.getAndIncrement() != 0) {
                return;
            }
            int addAndGet = 1;
            final Subscriber<? super T> actual = replayProducer.actual;
            final int capacity = this.capacity;
            do {
                final long value = replayProducer.requested.get();
                long n = 0L;
                Object[] head;
                if ((head = (Object[])replayProducer.node) == null) {
                    head = this.head;
                }
                int tailIndex = replayProducer.tailIndex;
                int index = replayProducer.index;
                while (n != value) {
                    if (actual.isUnsubscribed()) {
                        replayProducer.node = null;
                        return;
                    }
                    final boolean done = this.done;
                    boolean b;
                    if (index == this.size) {
                        b = true;
                    }
                    else {
                        b = false;
                    }
                    if (done && b) {
                        replayProducer.node = null;
                        final Throwable error = this.error;
                        if (error != null) {
                            actual.onError(error);
                            return;
                        }
                        actual.onCompleted();
                        return;
                    }
                    else {
                        if (b) {
                            break;
                        }
                        Object[] array = head;
                        int n2;
                        if ((n2 = tailIndex) == capacity) {
                            array = (Object[])head[tailIndex];
                            n2 = 0;
                        }
                        actual.onNext(array[n2]);
                        ++n;
                        tailIndex = n2 + 1;
                        ++index;
                        head = array;
                    }
                }
                if (n == value) {
                    if (actual.isUnsubscribed()) {
                        replayProducer.node = null;
                        return;
                    }
                    final boolean done2 = this.done;
                    boolean b2;
                    if (index == this.size) {
                        b2 = true;
                    }
                    else {
                        b2 = false;
                    }
                    if (done2 && b2) {
                        replayProducer.node = null;
                        final Throwable error2 = this.error;
                        if (error2 != null) {
                            actual.onError(error2);
                            return;
                        }
                        actual.onCompleted();
                        return;
                    }
                }
                if (n != 0L && value != Long.MAX_VALUE) {
                    BackpressureUtils.produced(replayProducer.requested, n);
                }
                replayProducer.index = index;
                replayProducer.tailIndex = tailIndex;
                replayProducer.node = head;
            } while ((addAndGet = replayProducer.addAndGet(-addAndGet)) != 0);
        }
        
        @Override
        public Throwable error() {
            return this.error;
        }
        
        @Override
        public void error(final Throwable error) {
            if (this.done) {
                RxJavaHooks.onError(error);
                return;
            }
            this.error = error;
            this.done = true;
        }
        
        @Override
        public boolean isComplete() {
            return this.done;
        }
        
        @Override
        public boolean isEmpty() {
            return this.size == 0;
        }
        
        @Override
        public T last() {
            int i = this.size;
            if (i == 0) {
                return null;
            }
            Object[] head = this.head;
            for (int capacity = this.capacity; i >= capacity; i -= capacity) {
                head = (Object[])head[capacity];
            }
            return (T)head[i - 1];
        }
        
        @Override
        public void next(final T t) {
            if (this.done) {
                return;
            }
            final int tailIndex = this.tailIndex;
            final Object[] tail = this.tail;
            if (tailIndex == tail.length - 1) {
                final Object[] tail2 = new Object[tail.length];
                tail2[0] = t;
                this.tailIndex = 1;
                tail[tailIndex] = tail2;
                this.tail = tail2;
            }
            else {
                tail[tailIndex] = t;
                this.tailIndex = tailIndex + 1;
            }
            ++this.size;
        }
        
        @Override
        public int size() {
            return this.size;
        }
        
        @Override
        public T[] toArray(final T[] array) {
            final int size = this.size;
            Object[] array2 = array;
            if (array.length < size) {
                array2 = (Object[])Array.newInstance(array.getClass().getComponentType(), size);
            }
            Object[] head;
            int capacity;
            int n;
            for (head = this.head, capacity = this.capacity, n = 0; n + capacity < size; n += capacity, head = (Object[])head[capacity]) {
                System.arraycopy(head, 0, array2, n, capacity);
            }
            System.arraycopy(head, 0, array2, n, size - n);
            if (array2.length > size) {
                array2[size] = null;
            }
            return (T[])array2;
        }
    }
}
