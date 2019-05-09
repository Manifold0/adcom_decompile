// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.atomic;

import java.util.Iterator;
import rx.internal.util.unsafe.Pow2;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.AbstractQueue;

abstract class AtomicReferenceArrayQueue<E> extends AbstractQueue<E>
{
    protected final AtomicReferenceArray<E> buffer;
    protected final int mask;
    
    public AtomicReferenceArrayQueue(int roundToPowerOfTwo) {
        roundToPowerOfTwo = Pow2.roundToPowerOfTwo(roundToPowerOfTwo);
        this.mask = roundToPowerOfTwo - 1;
        this.buffer = new AtomicReferenceArray<E>(roundToPowerOfTwo);
    }
    
    protected final int calcElementOffset(final long n) {
        return (int)n & this.mask;
    }
    
    protected final int calcElementOffset(final long n, final int n2) {
        return (int)n & n2;
    }
    
    @Override
    public void clear() {
        while (this.poll() != null || !this.isEmpty()) {}
    }
    
    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }
    
    protected final E lpElement(final int n) {
        return this.buffer.get(n);
    }
    
    protected final E lpElement(final AtomicReferenceArray<E> atomicReferenceArray, final int n) {
        return atomicReferenceArray.get(n);
    }
    
    protected final E lvElement(final int n) {
        return this.lvElement(this.buffer, n);
    }
    
    protected final E lvElement(final AtomicReferenceArray<E> atomicReferenceArray, final int n) {
        return atomicReferenceArray.get(n);
    }
    
    protected final void soElement(final int n, final E e) {
        this.buffer.lazySet(n, e);
    }
    
    protected final void soElement(final AtomicReferenceArray<E> atomicReferenceArray, final int n, final E e) {
        atomicReferenceArray.lazySet(n, e);
    }
    
    protected final void spElement(final int n, final E e) {
        this.buffer.lazySet(n, e);
    }
    
    protected final void spElement(final AtomicReferenceArray<E> atomicReferenceArray, final int n, final E e) {
        atomicReferenceArray.lazySet(n, e);
    }
    
    protected final void svElement(final AtomicReferenceArray<E> atomicReferenceArray, final int n, final E e) {
        atomicReferenceArray.set(n, e);
    }
}
