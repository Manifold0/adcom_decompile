// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.atomic;

import java.util.Iterator;
import java.util.Collection;
import rx.internal.util.unsafe.Pow2;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReferenceArray;

public final class SpscExactAtomicArrayQueue<T> extends AtomicReferenceArray<T> implements Queue<T>
{
    private static final long serialVersionUID = 6210984603741293445L;
    final int capacitySkip;
    final AtomicLong consumerIndex;
    final int mask;
    final AtomicLong producerIndex;
    
    public SpscExactAtomicArrayQueue(final int n) {
        super(Pow2.roundToPowerOfTwo(n));
        final int length = this.length();
        this.mask = length - 1;
        this.capacitySkip = length - n;
        this.producerIndex = new AtomicLong();
        this.consumerIndex = new AtomicLong();
    }
    
    @Override
    public boolean add(final T t) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean addAll(final Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void clear() {
        while (this.poll() != null || !this.isEmpty()) {}
    }
    
    @Override
    public boolean contains(final Object o) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean containsAll(final Collection<?> collection) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public T element() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean isEmpty() {
        return this.producerIndex == this.consumerIndex;
    }
    
    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean offer(final T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        final long value = this.producerIndex.get();
        final int mask = this.mask;
        if (this.get((int)(this.capacitySkip + value) & mask) != null) {
            return false;
        }
        final int n = (int)value;
        this.producerIndex.lazySet(1L + value);
        this.lazySet(n & mask, t);
        return true;
    }
    
    @Override
    public T peek() {
        return this.get((int)this.consumerIndex.get() & this.mask);
    }
    
    @Override
    public T poll() {
        final long value = this.consumerIndex.get();
        final int n = (int)value & this.mask;
        final T value2 = this.get(n);
        if (value2 == null) {
            return null;
        }
        this.consumerIndex.lazySet(1L + value);
        this.lazySet(n, null);
        return value2;
    }
    
    @Override
    public T remove() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean remove(final Object o) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean removeAll(final Collection<?> collection) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean retainAll(final Collection<?> collection) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public int size() {
        long value = this.consumerIndex.get();
        long value2;
        long value3;
        while (true) {
            value2 = this.producerIndex.get();
            value3 = this.consumerIndex.get();
            if (value == value3) {
                break;
            }
            value = value3;
        }
        return (int)(value2 - value3);
    }
    
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public <E> E[] toArray(final E[] array) {
        throw new UnsupportedOperationException();
    }
}
