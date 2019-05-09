// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
public class MpmcArrayQueue<E> extends MpmcArrayQueueConsumerField<E>
{
    long p30;
    long p31;
    long p32;
    long p33;
    long p34;
    long p35;
    long p36;
    long p37;
    long p40;
    long p41;
    long p42;
    long p43;
    long p44;
    long p45;
    long p46;
    
    public MpmcArrayQueue(final int n) {
        super(Math.max(2, n));
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
        final long n = this.mask + 1L;
        final long[] sequenceBuffer = this.sequenceBuffer;
        long lvConsumerIndex = Long.MAX_VALUE;
        while (true) {
            final long lvProducerIndex = this.lvProducerIndex();
            final long calcSequenceOffset = this.calcSequenceOffset(lvProducerIndex);
            final long n2 = this.lvSequence(sequenceBuffer, calcSequenceOffset) - lvProducerIndex;
            if (n2 == 0L) {
                if (this.casProducerIndex(lvProducerIndex, 1L + lvProducerIndex)) {
                    this.spElement(this.calcElementOffset(lvProducerIndex), e);
                    this.soSequence(sequenceBuffer, calcSequenceOffset, 1L + lvProducerIndex);
                    return true;
                }
                continue;
            }
            else {
                if (n2 < 0L && lvProducerIndex - n <= lvConsumerIndex && lvProducerIndex - n <= (lvConsumerIndex = this.lvConsumerIndex())) {
                    return false;
                }
                continue;
            }
        }
    }
    
    @Override
    public E peek() {
        E lpElement;
        long lvConsumerIndex;
        do {
            lvConsumerIndex = this.lvConsumerIndex();
            lpElement = this.lpElement(this.calcElementOffset(lvConsumerIndex));
        } while (lpElement == null && lvConsumerIndex != this.lvProducerIndex());
        return lpElement;
    }
    
    @Override
    public E poll() {
        final long[] sequenceBuffer = this.sequenceBuffer;
        long lvProducerIndex = -1L;
        while (true) {
            final long lvConsumerIndex = this.lvConsumerIndex();
            final long calcSequenceOffset = this.calcSequenceOffset(lvConsumerIndex);
            final long n = this.lvSequence(sequenceBuffer, calcSequenceOffset) - (1L + lvConsumerIndex);
            if (n == 0L) {
                if (this.casConsumerIndex(lvConsumerIndex, 1L + lvConsumerIndex)) {
                    final long calcElementOffset = this.calcElementOffset(lvConsumerIndex);
                    final E lpElement = this.lpElement(calcElementOffset);
                    this.spElement(calcElementOffset, null);
                    this.soSequence(sequenceBuffer, calcSequenceOffset, this.mask + lvConsumerIndex + 1L);
                    return lpElement;
                }
                continue;
            }
            else {
                if (n < 0L && lvConsumerIndex >= lvProducerIndex && lvConsumerIndex == (lvProducerIndex = this.lvProducerIndex())) {
                    return null;
                }
                continue;
            }
        }
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
