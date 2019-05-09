// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
public final class SpscArrayQueue<E> extends SpscArrayQueueL3Pad<E>
{
    public SpscArrayQueue(final int n) {
        super(n);
    }
    
    private long lvConsumerIndex() {
        return UnsafeAccess.UNSAFE.getLongVolatile(this, SpscArrayQueue.C_INDEX_OFFSET);
    }
    
    private long lvProducerIndex() {
        return UnsafeAccess.UNSAFE.getLongVolatile(this, SpscArrayQueue.P_INDEX_OFFSET);
    }
    
    private void soConsumerIndex(final long n) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, SpscArrayQueue.C_INDEX_OFFSET, n);
    }
    
    private void soProducerIndex(final long n) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, SpscArrayQueue.P_INDEX_OFFSET, n);
    }
    
    @Override
    public boolean isEmpty() {
        return this.lvProducerIndex() == this.lvConsumerIndex();
    }
    
    @Override
    public boolean offer(final E e) {
        if (e == null) {
            throw new NullPointerException("null elements not allowed");
        }
        final E[] buffer = this.buffer;
        final long producerIndex = this.producerIndex;
        final long calcElementOffset = this.calcElementOffset(producerIndex);
        if (this.lvElement(buffer, calcElementOffset) != null) {
            return false;
        }
        this.soElement(buffer, calcElementOffset, e);
        this.soProducerIndex(1L + producerIndex);
        return true;
    }
    
    @Override
    public E peek() {
        return this.lvElement(this.calcElementOffset(this.consumerIndex));
    }
    
    @Override
    public E poll() {
        final long consumerIndex = this.consumerIndex;
        final long calcElementOffset = this.calcElementOffset(consumerIndex);
        final E[] buffer = this.buffer;
        final E lvElement = this.lvElement(buffer, calcElementOffset);
        if (lvElement == null) {
            return null;
        }
        this.soElement(buffer, calcElementOffset, null);
        this.soConsumerIndex(1L + consumerIndex);
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
