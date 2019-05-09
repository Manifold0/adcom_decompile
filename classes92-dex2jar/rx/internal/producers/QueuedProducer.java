// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.producers;

import rx.internal.operators.BackpressureUtils;
import rx.exceptions.MissingBackpressureException;
import rx.exceptions.Exceptions;
import rx.internal.util.atomic.SpscLinkedAtomicQueue;
import rx.internal.util.unsafe.SpscLinkedQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Queue;
import rx.Subscriber;
import rx.Observer;
import rx.Producer;
import java.util.concurrent.atomic.AtomicLong;

public final class QueuedProducer<T> extends AtomicLong implements Producer, Observer<T>
{
    static final Object NULL_SENTINEL;
    private static final long serialVersionUID = 7277121710709137047L;
    final Subscriber<? super T> child;
    volatile boolean done;
    Throwable error;
    final Queue<Object> queue;
    final AtomicInteger wip;
    
    static {
        NULL_SENTINEL = new Object();
    }
    
    public QueuedProducer(final Subscriber<? super T> subscriber) {
        Object o;
        if (UnsafeAccess.isUnsafeAvailable()) {
            o = new SpscLinkedQueue();
        }
        else {
            o = new SpscLinkedAtomicQueue();
        }
        this(subscriber, (Queue)o);
    }
    
    public QueuedProducer(final Subscriber<? super T> child, final Queue<Object> queue) {
        this.child = child;
        this.queue = queue;
        this.wip = new AtomicInteger();
    }
    
    private boolean checkTerminated(final boolean b, final boolean b2) {
        if (this.child.isUnsubscribed()) {
            return true;
        }
        if (b) {
            final Throwable error = this.error;
            if (error != null) {
                this.queue.clear();
                this.child.onError(error);
                return true;
            }
            if (b2) {
                this.child.onCompleted();
                return true;
            }
        }
        return false;
    }
    
    private void drain() {
        Label_0040: {
            if (this.wip.getAndIncrement() == 0) {
                final Subscriber<? super T> child = this.child;
                final Queue<Object> queue = this.queue;
                while (!this.checkTerminated(this.done, queue.isEmpty())) {
                    this.wip.lazySet(1);
                    long value;
                    long n;
                    for (value = this.get(), n = 0L; value != 0L; --value, ++n) {
                        final boolean done = this.done;
                        Object poll = queue.poll();
                        if (this.checkTerminated(done, poll == null)) {
                            break Label_0040;
                        }
                        if (poll == null) {
                            break;
                        }
                        try {
                            if (poll == QueuedProducer.NULL_SENTINEL) {
                                child.onNext(null);
                            }
                            else {
                                child.onNext(poll);
                            }
                        }
                        catch (Throwable t) {
                            if (poll == QueuedProducer.NULL_SENTINEL) {
                                poll = null;
                            }
                            Exceptions.throwOrReport(t, child, poll);
                            return;
                        }
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
                if (this.queue.offer(QueuedProducer.NULL_SENTINEL)) {
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
        if (!this.offer(t)) {
            this.onError(new MissingBackpressureException());
        }
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
