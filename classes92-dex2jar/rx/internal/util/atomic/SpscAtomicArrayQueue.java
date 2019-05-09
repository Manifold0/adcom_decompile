// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.atomic;

import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

public final class SpscAtomicArrayQueue<E> extends AtomicReferenceArrayQueue<E>
{
    private static final Integer MAX_LOOK_AHEAD_STEP;
    final AtomicLong consumerIndex;
    final int lookAheadStep;
    final AtomicLong producerIndex;
    long producerLookAhead;
    
    static {
        MAX_LOOK_AHEAD_STEP = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    }
    
    public SpscAtomicArrayQueue(final int n) {
        super(n);
        this.producerIndex = new AtomicLong();
        this.consumerIndex = new AtomicLong();
        this.lookAheadStep = Math.min(n / 4, SpscAtomicArrayQueue.MAX_LOOK_AHEAD_STEP);
    }
    
    private long lvConsumerIndex() {
        return this.consumerIndex.get();
    }
    
    private long lvProducerIndex() {
        return this.producerIndex.get();
    }
    
    private void soConsumerIndex(final long n) {
        this.consumerIndex.lazySet(n);
    }
    
    private void soProducerIndex(final long n) {
        this.producerIndex.lazySet(n);
    }
    
    @Override
    public boolean isEmpty() {
        return this.lvProducerIndex() == this.lvConsumerIndex();
    }
    
    @Override
    public boolean offer(final E e) {
        if (e == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        final AtomicReferenceArray<E> buffer = this.buffer;
        final int mask = this.mask;
        final long value = this.producerIndex.get();
        final int calcElementOffset = this.calcElementOffset(value, mask);
        if (value >= this.producerLookAhead) {
            final int lookAheadStep = this.lookAheadStep;
            if (this.lvElement(buffer, this.calcElementOffset(lookAheadStep + value, mask)) == null) {
                this.producerLookAhead = lookAheadStep + value;
            }
            else if (this.lvElement(buffer, calcElementOffset) != null) {
                return false;
            }
        }
        this.soElement(buffer, calcElementOffset, e);
        this.soProducerIndex(1L + value);
        return true;
    }
    
    @Override
    public E peek() {
        return this.lvElement(this.calcElementOffset(this.consumerIndex.get()));
    }
    
    @Override
    public E poll() {
        final long value = this.consumerIndex.get();
        final int calcElementOffset = this.calcElementOffset(value);
        final AtomicReferenceArray<E> buffer = this.buffer;
        final E lvElement = this.lvElement(buffer, calcElementOffset);
        if (lvElement == null) {
            return null;
        }
        this.soElement(buffer, calcElementOffset, null);
        this.soConsumerIndex(1L + value);
        return lvElement;
    }
    
    @Override
    public int size() {
        long n = this.lvConsumerIndex();
        long n2;
        long n3;
        long lvProducerIndex;
        do {
            n2 = n;
            lvProducerIndex = this.lvProducerIndex();
            n3 = (n = this.lvConsumerIndex());
        } while (n2 != n3);
        return (int)(lvProducerIndex - n3);
    }
}
