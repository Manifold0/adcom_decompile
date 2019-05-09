// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.producers;

import rx.internal.operators.BackpressureUtils;
import rx.Observer;
import rx.exceptions.Exceptions;
import rx.internal.util.atomic.SpscLinkedAtomicQueue;
import rx.internal.util.unsafe.SpscLinkedQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Queue;
import rx.Subscriber;
import rx.Producer;
import java.util.concurrent.atomic.AtomicLong;

public final class QueuedValueProducer<T> extends AtomicLong implements Producer
{
    static final Object NULL_SENTINEL;
    private static final long serialVersionUID = 7277121710709137047L;
    final Subscriber<? super T> child;
    final Queue<Object> queue;
    final AtomicInteger wip;
    
    static {
        NULL_SENTINEL = new Object();
    }
    
    public QueuedValueProducer(final Subscriber<? super T> subscriber) {
        Object o;
        if (UnsafeAccess.isUnsafeAvailable()) {
            o = new SpscLinkedQueue();
        }
        else {
            o = new SpscLinkedAtomicQueue();
        }
        this(subscriber, (Queue)o);
    }
    
    public QueuedValueProducer(final Subscriber<? super T> child, final Queue<Object> queue) {
        this.child = child;
        this.queue = queue;
        this.wip = new AtomicInteger();
    }
    
    private void drain() {
        Label_0030: {
            if (this.wip.getAndIncrement() == 0) {
                final Subscriber<? super T> child = this.child;
                final Queue<Object> queue = this.queue;
                while (!child.isUnsubscribed()) {
                    this.wip.lazySet(1);
                    long value = this.get();
                    long n = 0L;
                    while (value != 0L) {
                        Object poll = queue.poll();
                        if (poll != null) {
                            try {
                                if (poll == QueuedValueProducer.NULL_SENTINEL) {
                                    child.onNext(null);
                                }
                                else {
                                    child.onNext(poll);
                                }
                                if (!child.isUnsubscribed()) {
                                    --value;
                                    ++n;
                                    continue;
                                }
                                break Label_0030;
                            }
                            catch (Throwable t) {
                                if (poll == QueuedValueProducer.NULL_SENTINEL) {
                                    poll = null;
                                }
                                Exceptions.throwOrReport(t, child, poll);
                                return;
                            }
                            break;
                        }
                        break;
                    }
                    if (n != 0L && this.get() != Long.MAX_VALUE) {
                        this.addAndGet(-n);
                    }
                    if (this.wip.decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }
    
    public boolean offer(final T t) {
        Label_0034: {
            if (t == null) {
                if (this.queue.offer(QueuedValueProducer.NULL_SENTINEL)) {
                    break Label_0034;
                }
            }
            else if (this.queue.offer(t)) {
                break Label_0034;
            }
            return false;
        }
        this.drain();
        return true;
    }
    
    @Override
    public void request(final long n) {
        if (n < 0L) {
            throw new IllegalArgumentException("n >= 0 required");
        }
        if (n > 0L) {
            BackpressureUtils.getAndAddRequest(this, n);
            this.drain();
        }
    }
}
