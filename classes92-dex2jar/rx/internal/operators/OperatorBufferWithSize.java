// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Iterator;
import java.util.Queue;
import rx.exceptions.MissingBackpressureException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.ArrayDeque;
import java.util.ArrayList;
import rx.Producer;
import rx.Subscription;
import rx.Subscriber;
import java.util.List;
import rx.Observable;

public final class OperatorBufferWithSize<T> implements Operator<List<T>, T>
{
    final int count;
    final int skip;
    
    public OperatorBufferWithSize(final int count, final int skip) {
        if (count <= 0) {
            throw new IllegalArgumentException("count must be greater than 0");
        }
        if (skip <= 0) {
            throw new IllegalArgumentException("skip must be greater than 0");
        }
        this.count = count;
        this.skip = skip;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super List<T>> subscriber) {
        if (this.skip == this.count) {
            final BufferExact<Object> bufferExact = new BufferExact<Object>((Subscriber<? super List<Object>>)subscriber, this.count);
            subscriber.add(bufferExact);
            subscriber.setProducer(bufferExact.createProducer());
            return bufferExact;
        }
        if (this.skip > this.count) {
            final BufferSkip<Object> bufferSkip = new BufferSkip<Object>((Subscriber<? super List<Object>>)subscriber, this.count, this.skip);
            subscriber.add(bufferSkip);
            subscriber.setProducer(bufferSkip.createProducer());
            return bufferSkip;
        }
        final BufferOverlap<Object> bufferOverlap = new BufferOverlap<Object>((Subscriber<? super List<Object>>)subscriber, this.count, this.skip);
        subscriber.add(bufferOverlap);
        subscriber.setProducer(bufferOverlap.createProducer());
        return bufferOverlap;
    }
    
    static final class BufferExact<T> extends Subscriber<T>
    {
        final Subscriber<? super List<T>> actual;
        List<T> buffer;
        final int count;
        
        public BufferExact(final Subscriber<? super List<T>> actual, final int count) {
            this.actual = actual;
            this.count = count;
            this.request(0L);
        }
        
        Producer createProducer() {
            return new Producer() {
                @Override
                public void request(long multiplyCap) {
                    if (multiplyCap < 0L) {
                        throw new IllegalArgumentException("n >= required but it was " + multiplyCap);
                    }
                    if (multiplyCap != 0L) {
                        multiplyCap = BackpressureUtils.multiplyCap(multiplyCap, BufferExact.this.count);
                        Subscriber.this.request(multiplyCap);
                    }
                }
            };
        }
        
        @Override
        public void onCompleted() {
            final List<T> buffer = this.buffer;
            if (buffer != null) {
                this.actual.onNext(buffer);
            }
            this.actual.onCompleted();
        }
        
        @Override
        public void onError(final Throwable t) {
            this.buffer = null;
            this.actual.onError(t);
        }
        
        @Override
        public void onNext(final T t) {
            List<T> buffer;
            if ((buffer = this.buffer) == null) {
                buffer = new ArrayList<T>(this.count);
                this.buffer = buffer;
            }
            buffer.add(t);
            if (buffer.size() == this.count) {
                this.buffer = null;
                this.actual.onNext(buffer);
            }
        }
    }
    
    static final class BufferOverlap<T> extends Subscriber<T>
    {
        final Subscriber<? super List<T>> actual;
        final int count;
        long index;
        long produced;
        final ArrayDeque<List<T>> queue;
        final AtomicLong requested;
        final int skip;
        
        public BufferOverlap(final Subscriber<? super List<T>> actual, final int count, final int skip) {
            this.actual = actual;
            this.count = count;
            this.skip = skip;
            this.queue = new ArrayDeque<List<T>>();
            this.requested = new AtomicLong();
            this.request(0L);
        }
        
        Producer createProducer() {
            return new BufferOverlapProducer();
        }
        
        @Override
        public void onCompleted() {
            final long produced = this.produced;
            if (produced != 0L) {
                if (produced > this.requested.get()) {
                    this.actual.onError(new MissingBackpressureException("More produced than requested? " + produced));
                    return;
                }
                this.requested.addAndGet(-produced);
            }
            BackpressureUtils.postCompleteDone(this.requested, this.queue, this.actual);
        }
        
        @Override
        public void onError(final Throwable t) {
            this.queue.clear();
            this.actual.onError(t);
        }
        
        @Override
        public void onNext(final T t) {
            final long index = this.index;
            if (index == 0L) {
                this.queue.offer(new ArrayList<T>(this.count));
            }
            final long index2 = index + 1L;
            if (index2 == this.skip) {
                this.index = 0L;
            }
            else {
                this.index = index2;
            }
            final Iterator<List<T>> iterator = this.queue.iterator();
            while (iterator.hasNext()) {
                iterator.next().add(t);
            }
            final List<T> list = this.queue.peek();
            if (list != null && list.size() == this.count) {
                this.queue.poll();
                ++this.produced;
                this.actual.onNext((Object)list);
            }
        }
        
        final class BufferOverlapProducer extends AtomicBoolean implements Producer
        {
            private static final long serialVersionUID = -4015894850868853147L;
            
            @Override
            public void request(final long n) {
                final BufferOverlap this$0 = BufferOverlap.this;
                if (BackpressureUtils.postCompleteRequest(this$0.requested, n, this$0.queue, this$0.actual) && n != 0L) {
                    if (this.get() || !this.compareAndSet(false, true)) {
                        this$0.request(BackpressureUtils.multiplyCap(this$0.skip, n));
                        return;
                    }
                    this$0.request(BackpressureUtils.addCap(BackpressureUtils.multiplyCap(this$0.skip, n - 1L), this$0.count));
                }
            }
        }
    }
    
    static final class BufferSkip<T> extends Subscriber<T>
    {
        final Subscriber<? super List<T>> actual;
        List<T> buffer;
        final int count;
        long index;
        final int skip;
        
        public BufferSkip(final Subscriber<? super List<T>> actual, final int count, final int skip) {
            this.actual = actual;
            this.count = count;
            this.skip = skip;
            this.request(0L);
        }
        
        Producer createProducer() {
            return new BufferSkipProducer();
        }
        
        @Override
        public void onCompleted() {
            final List<T> buffer = this.buffer;
            if (buffer != null) {
                this.buffer = null;
                this.actual.onNext(buffer);
            }
            this.actual.onCompleted();
        }
        
        @Override
        public void onError(final Throwable t) {
            this.buffer = null;
            this.actual.onError(t);
        }
        
        @Override
        public void onNext(final T t) {
            final long index = this.index;
            List<T> buffer = this.buffer;
            if (index == 0L) {
                buffer = new ArrayList<T>(this.count);
                this.buffer = buffer;
            }
            final long index2 = index + 1L;
            if (index2 == this.skip) {
                this.index = 0L;
            }
            else {
                this.index = index2;
            }
            if (buffer != null) {
                buffer.add(t);
                if (buffer.size() == this.count) {
                    this.buffer = null;
                    this.actual.onNext(buffer);
                }
            }
        }
        
        final class BufferSkipProducer extends AtomicBoolean implements Producer
        {
            private static final long serialVersionUID = 3428177408082367154L;
            
            @Override
            public void request(final long n) {
                if (n < 0L) {
                    throw new IllegalArgumentException("n >= 0 required but it was " + n);
                }
                if (n != 0L) {
                    final BufferSkip this$0 = BufferSkip.this;
                    if (this.get() || !this.compareAndSet(false, true)) {
                        this$0.request(BackpressureUtils.multiplyCap(n, this$0.skip));
                        return;
                    }
                    this$0.request(BackpressureUtils.addCap(BackpressureUtils.multiplyCap(n, this$0.count), BackpressureUtils.multiplyCap(this$0.skip - this$0.count, n - 1L)));
                }
            }
        }
    }
}
