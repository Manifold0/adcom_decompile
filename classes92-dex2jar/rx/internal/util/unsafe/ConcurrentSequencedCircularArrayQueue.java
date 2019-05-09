// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
public abstract class ConcurrentSequencedCircularArrayQueue<E> extends ConcurrentCircularArrayQueue<E>
{
    private static final long ARRAY_BASE;
    private static final int ELEMENT_SHIFT;
    protected final long[] sequenceBuffer;
    
    static {
        if (8 == UnsafeAccess.UNSAFE.arrayIndexScale(long[].class)) {
            ELEMENT_SHIFT = ConcurrentSequencedCircularArrayQueue.SPARSE_SHIFT + 3;
            ARRAY_BASE = UnsafeAccess.UNSAFE.arrayBaseOffset(long[].class) + (32 << ConcurrentSequencedCircularArrayQueue.ELEMENT_SHIFT - ConcurrentSequencedCircularArrayQueue.SPARSE_SHIFT);
            return;
        }
        throw new IllegalStateException("Unexpected long[] element size");
    }
    
    public ConcurrentSequencedCircularArrayQueue(int n) {
        super(n);
        n = (int)(this.mask + 1L);
        this.sequenceBuffer = new long[(n << ConcurrentSequencedCircularArrayQueue.SPARSE_SHIFT) + 64];
        for (long n2 = 0L; n2 < n; ++n2) {
            this.soSequence(this.sequenceBuffer, this.calcSequenceOffset(n2), n2);
        }
    }
    
    protected final long calcSequenceOffset(final long n) {
        return ConcurrentSequencedCircularArrayQueue.ARRAY_BASE + ((this.mask & n) << ConcurrentSequencedCircularArrayQueue.ELEMENT_SHIFT);
    }
    
    protected final long lvSequence(final long[] array, final long n) {
        return UnsafeAccess.UNSAFE.getLongVolatile(array, n);
    }
    
    protected final void soSequence(final long[] array, final long n, final long n2) {
        UnsafeAccess.UNSAFE.putOrderedLong(array, n, n2);
    }
}
