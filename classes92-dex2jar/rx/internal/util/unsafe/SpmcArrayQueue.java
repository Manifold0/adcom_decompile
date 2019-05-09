// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
public final class SpmcArrayQueue<E> extends SpmcArrayQueueL3Pad<E>
{
    public SpmcArrayQueue(final int n) {
        super(n);
    }
    
    @Override
    public boolean isEmpty() {
        return this.lvConsumerIndex() == this.lvProducerIndex();
    }
    
    @Override
    public boolean offer(final E e) {
        if (e == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        final E[] buffer = this.buffer;
        final long mask = this.mask;
        final long lvProducerIndex = this.lvProducerIndex();
        final long calcElementOffset = this.calcElementOffset(lvProducerIndex);
        if (this.lvElement(buffer, calcElementOffset) != null) {
            if (lvProducerIndex - this.lvConsumerIndex() > mask) {
                return false;
            }
            while (this.lvElement(buffer, calcElementOffset) != null) {}
        }
        this.spElement(buffer, calcElementOffset, e);
        this.soTail(1L + lvProducerIndex);
        return true;
    }
    
    @Override
    public E peek() {
        final long lvProducerIndexCache = this.lvProducerIndexCache();
        E lvElement;
        do {
            final long lvConsumerIndex = this.lvConsumerIndex();
            if (lvConsumerIndex >= lvProducerIndexCache) {
                final long lvProducerIndex = this.lvProducerIndex();
                if (lvConsumerIndex >= lvProducerIndex) {
                    return null;
                }
                this.svProducerIndexCache(lvProducerIndex);
            }
            lvElement = this.lvElement(this.calcElementOffset(lvConsumerIndex));
        } while (lvElement == null);
        return lvElement;
    }
    
    @Override
    public E poll() {
        final long lvProducerIndexCache = this.lvProducerIndexCache();
        long lvConsumerIndex;
        do {
            lvConsumerIndex = this.lvConsumerIndex();
            if (lvConsumerIndex >= lvProducerIndexCache) {
                final long lvProducerIndex = this.lvProducerIndex();
                if (lvConsumerIndex >= lvProducerIndex) {
                    return null;
                }
                this.svProducerIndexCache(lvProducerIndex);
            }
        } while (!this.casHead(lvConsumerIndex, 1L + lvConsumerIndex));
        final long calcElementOffset = this.calcElementOffset(lvConsumerIndex);
        final E[] buffer = this.buffer;
        final E lpElement = this.lpElement(buffer, calcElementOffset);
        this.soElement(buffer, calcElementOffset, null);
        return lpElement;
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
