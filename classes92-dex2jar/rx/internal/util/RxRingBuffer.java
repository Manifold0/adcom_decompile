// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util;

import rx.exceptions.MissingBackpressureException;
import rx.Observer;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.internal.util.unsafe.SpmcArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import java.util.Queue;
import rx.internal.operators.NotificationLite;
import rx.Subscription;

public class RxRingBuffer implements Subscription
{
    private static final NotificationLite<Object> ON;
    public static final int SIZE;
    public static final ObjectPool<Queue<Object>> SPMC_POOL;
    public static final ObjectPool<Queue<Object>> SPSC_POOL;
    private final ObjectPool<Queue<Object>> pool;
    private Queue<Object> queue;
    private final int size;
    public volatile Object terminalState;
    
    static {
        ON = NotificationLite.instance();
        int n = 128;
        if (PlatformDependent.isAndroid()) {
            n = 16;
        }
        final String property = System.getProperty("rx.ring-buffer.size");
        int int1 = n;
        while (true) {
            if (property == null) {
                break Label_0036;
            }
            try {
                int1 = Integer.parseInt(property);
                SIZE = int1;
                SPSC_POOL = new ObjectPool<Queue<Object>>() {
                    @Override
                    protected SpscArrayQueue<Object> createObject() {
                        return new SpscArrayQueue<Object>(RxRingBuffer.SIZE);
                    }
                };
                SPMC_POOL = new ObjectPool<Queue<Object>>() {
                    @Override
                    protected SpmcArrayQueue<Object> createObject() {
                        return new SpmcArrayQueue<Object>(RxRingBuffer.SIZE);
                    }
                };
            }
            catch (NumberFormatException ex) {
                System.err.println("Failed to set 'rx.buffer.size' with value " + property + " => " + ex.getMessage());
                int1 = n;
                continue;
            }
            break;
        }
    }
    
    RxRingBuffer() {
        this(new SynchronizedQueue<Object>(RxRingBuffer.SIZE), RxRingBuffer.SIZE);
    }
    
    private RxRingBuffer(final Queue<Object> queue, final int size) {
        this.queue = queue;
        this.pool = null;
        this.size = size;
    }
    
    private RxRingBuffer(final ObjectPool<Queue<Object>> pool, final int size) {
        this.pool = pool;
        this.queue = pool.borrowObject();
        this.size = size;
    }
    
    public static RxRingBuffer getSpmcInstance() {
        if (UnsafeAccess.isUnsafeAvailable()) {
            return new RxRingBuffer(RxRingBuffer.SPMC_POOL, RxRingBuffer.SIZE);
        }
        return new RxRingBuffer();
    }
    
    public static RxRingBuffer getSpscInstance() {
        if (UnsafeAccess.isUnsafeAvailable()) {
            return new RxRingBuffer(RxRingBuffer.SPSC_POOL, RxRingBuffer.SIZE);
        }
        return new RxRingBuffer();
    }
    
    public boolean accept(final Object o, final Observer observer) {
        return RxRingBuffer.ON.accept(observer, o);
    }
    
    public Throwable asError(final Object o) {
        return RxRingBuffer.ON.getError(o);
    }
    
    public int available() {
        return this.size - this.count();
    }
    
    public int capacity() {
        return this.size;
    }
    
    public int count() {
        final Queue<Object> queue = this.queue;
        if (queue == null) {
            return 0;
        }
        return queue.size();
    }
    
    public Object getValue(final Object o) {
        return RxRingBuffer.ON.getValue(o);
    }
    
    public boolean isCompleted(final Object o) {
        return RxRingBuffer.ON.isCompleted(o);
    }
    
    public boolean isEmpty() {
        final Queue<Object> queue = this.queue;
        return queue == null || queue.isEmpty();
    }
    
    public boolean isError(final Object o) {
        return RxRingBuffer.ON.isError(o);
    }
    
    @Override
    public boolean isUnsubscribed() {
        return this.queue == null;
    }
    
    public void onCompleted() {
        if (this.terminalState == null) {
            this.terminalState = RxRingBuffer.ON.completed();
        }
    }
    
    public void onError(final Throwable t) {
        if (this.terminalState == null) {
            this.terminalState = RxRingBuffer.ON.error(t);
        }
    }
    
    public void onNext(final Object o) throws MissingBackpressureException {
        int n = 0;
        boolean b = false;
        synchronized (this) {
            final Queue<Object> queue = this.queue;
            if (queue != null) {
                b = !queue.offer(RxRingBuffer.ON.next(o));
            }
            else {
                n = 1;
            }
            // monitorexit(this)
            if (n != 0) {
                throw new IllegalStateException("This instance has been unsubscribed and the queue is no longer usable.");
            }
        }
        if (b) {
            throw new MissingBackpressureException();
        }
    }
    
    public Object peek() {
        synchronized (this) {
            final Queue<Object> queue = this.queue;
            if (queue == null) {
                return null;
            }
            final Object peek = queue.peek();
            final Object terminalState = this.terminalState;
            Object o;
            if ((o = peek) == null) {
                o = peek;
                if (terminalState != null) {
                    o = peek;
                    if (queue.peek() == null) {
                        o = terminalState;
                    }
                }
            }
            return o;
        }
    }
    
    public Object poll() {
        synchronized (this) {
            final Queue<Object> queue = this.queue;
            if (queue == null) {
                return null;
            }
            final Object poll = queue.poll();
            final Object terminalState = this.terminalState;
            Object o;
            if ((o = poll) == null) {
                o = poll;
                if (terminalState != null) {
                    o = poll;
                    if (queue.peek() == null) {
                        o = terminalState;
                        this.terminalState = null;
                    }
                }
            }
            return o;
        }
    }
    
    public void release() {
        synchronized (this) {
            final Queue<Object> queue = this.queue;
            final ObjectPool<Queue<Object>> pool = this.pool;
            if (pool != null && queue != null) {
                queue.clear();
                this.queue = null;
                pool.returnObject(queue);
            }
        }
    }
    
    @Override
    public void unsubscribe() {
        this.release();
    }
}
