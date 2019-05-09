// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

import java.util.Iterator;
import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
public abstract class ConcurrentCircularArrayQueue<E> extends ConcurrentCircularArrayQueueL0Pad<E>
{
    protected static final int BUFFER_PAD = 32;
    private static final long REF_ARRAY_BASE;
    private static final int REF_ELEMENT_SHIFT;
    protected static final int SPARSE_SHIFT;
    protected final E[] buffer;
    protected final long mask;
    
    static {
        SPARSE_SHIFT = Integer.getInteger("sparse.shift", 0);
        final int arrayIndexScale = UnsafeAccess.UNSAFE.arrayIndexScale(Object[].class);
        if (4 == arrayIndexScale) {
            REF_ELEMENT_SHIFT = ConcurrentCircularArrayQueue.SPARSE_SHIFT + 2;
        }
        else {
            if (8 != arrayIndexScale) {
                throw new IllegalStateException("Unknown pointer size");
            }
            REF_ELEMENT_SHIFT = ConcurrentCircularArrayQueue.SPARSE_SHIFT + 3;
        }
        REF_ARRAY_BASE = UnsafeAccess.UNSAFE.arrayBaseOffset(Object[].class) + (32 << ConcurrentCircularArrayQueue.REF_ELEMENT_SHIFT - ConcurrentCircularArrayQueue.SPARSE_SHIFT);
    }
    
    public ConcurrentCircularArrayQueue(int roundToPowerOfTwo) {
        roundToPowerOfTwo = Pow2.roundToPowerOfTwo(roundToPowerOfTwo);
        this.mask = roundToPowerOfTwo - 1;
        this.buffer = (E[])new Object[(roundToPowerOfTwo << ConcurrentCircularArrayQueue.SPARSE_SHIFT) + 64];
    }
    
    protected final long calcElementOffset(final long n) {
        return this.calcElementOffset(n, this.mask);
    }
    
    protected final long calcElementOffset(final long n, final long n2) {
        return ConcurrentCircularArrayQueue.REF_ARRAY_BASE + ((n & n2) << ConcurrentCircularArrayQueue.REF_ELEMENT_SHIFT);
    }
    
    @Override
    public void clear() {
        while (this.poll() != null || !this.isEmpty()) {}
    }
    
    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }
    
    protected final E lpElement(final long n) {
        return this.lpElement(this.buffer, n);
    }
    
    protected final E lpElement(final E[] array, final long n) {
        return (E)UnsafeAccess.UNSAFE.getObject(array, n);
    }
    
    protected final E lvElement(final long n) {
        return this.lvElement(this.buffer, n);
    }
    
    protected final E lvElement(final E[] array, final long n) {
        return (E)UnsafeAccess.UNSAFE.getObjectVolatile(array, n);
    }
    
    protected final void soElement(final long n, final E e) {
        this.soElement(this.buffer, n, e);
    }
    
    protected final void soElement(final E[] array, final long n, final E e) {
        UnsafeAccess.UNSAFE.putOrderedObject(array, n, e);
    }
    
    protected final void spElement(final long n, final E e) {
        this.spElement(this.buffer, n, e);
    }
    
    protected final void spElement(final E[] array, final long n, final E e) {
        UnsafeAccess.UNSAFE.putObject(array, n, e);
    }
}
