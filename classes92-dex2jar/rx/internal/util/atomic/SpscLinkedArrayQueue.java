// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.atomic;

import java.util.Iterator;
import java.util.Collection;
import rx.internal.util.unsafe.Pow2;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.Queue;

public final class SpscLinkedArrayQueue<T> implements Queue<T>
{
    private static final Object HAS_NEXT;
    static final int MAX_LOOK_AHEAD_STEP;
    AtomicReferenceArray<Object> consumerBuffer;
    final AtomicLong consumerIndex;
    int consumerMask;
    AtomicReferenceArray<Object> producerBuffer;
    final AtomicLong producerIndex;
    long producerLookAhead;
    int producerLookAheadStep;
    int producerMask;
    
    static {
        MAX_LOOK_AHEAD_STEP = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
        HAS_NEXT = new Object();
    }
    
    public SpscLinkedArrayQueue(int roundToPowerOfTwo) {
        roundToPowerOfTwo = Pow2.roundToPowerOfTwo(roundToPowerOfTwo);
        final int n = roundToPowerOfTwo - 1;
        final AtomicReferenceArray<Object> atomicReferenceArray = new AtomicReferenceArray<Object>(roundToPowerOfTwo + 1);
        this.producerBuffer = atomicReferenceArray;
        this.producerMask = n;
        this.adjustLookAheadStep(roundToPowerOfTwo);
        this.consumerBuffer = atomicReferenceArray;
        this.consumerMask = n;
        this.producerLookAhead = n - 1;
        this.producerIndex = new AtomicLong();
        this.consumerIndex = new AtomicLong();
    }
    
    private void adjustLookAheadStep(final int n) {
        this.producerLookAheadStep = Math.min(n / 4, SpscLinkedArrayQueue.MAX_LOOK_AHEAD_STEP);
    }
    
    private static int calcDirectOffset(final int n) {
        return n;
    }
    
    private static int calcWrappedOffset(final long n, final int n2) {
        return calcDirectOffset((int)n & n2);
    }
    
    private long lpConsumerIndex() {
        return this.consumerIndex.get();
    }
    
    private long lpProducerIndex() {
        return this.producerIndex.get();
    }
    
    private long lvConsumerIndex() {
        return this.consumerIndex.get();
    }
    
    private static <E> Object lvElement(final AtomicReferenceArray<Object> atomicReferenceArray, final int n) {
        return atomicReferenceArray.get(n);
    }
    
    private AtomicReferenceArray<Object> lvNext(final AtomicReferenceArray<Object> atomicReferenceArray) {
        return (AtomicReferenceArray<Object>)lvElement(atomicReferenceArray, calcDirectOffset(atomicReferenceArray.length() - 1));
    }
    
    private long lvProducerIndex() {
        return this.producerIndex.get();
    }
    
    private T newBufferPeek(final AtomicReferenceArray<Object> consumerBuffer, final long n, final int n2) {
        this.consumerBuffer = consumerBuffer;
        return (T)lvElement(consumerBuffer, calcWrappedOffset(n, n2));
    }
    
    private T newBufferPoll(final AtomicReferenceArray<Object> consumerBuffer, final long n, int calcWrappedOffset) {
        this.consumerBuffer = consumerBuffer;
        calcWrappedOffset = calcWrappedOffset(n, calcWrappedOffset);
        final Object lvElement = lvElement(consumerBuffer, calcWrappedOffset);
        if (lvElement == null) {
            return null;
        }
        soElement(consumerBuffer, calcWrappedOffset, null);
        this.soConsumerIndex(1L + n);
        return (T)lvElement;
    }
    
    private void resize(final AtomicReferenceArray<Object> atomicReferenceArray, final long n, final int n2, final T t, final long n3) {
        final AtomicReferenceArray producerBuffer = new AtomicReferenceArray(atomicReferenceArray.length());
        this.producerBuffer = (AtomicReferenceArray<Object>)producerBuffer;
        this.producerLookAhead = n + n3 - 1L;
        soElement(producerBuffer, n2, t);
        this.soNext(atomicReferenceArray, producerBuffer);
        soElement(atomicReferenceArray, n2, SpscLinkedArrayQueue.HAS_NEXT);
        this.soProducerIndex(n + 1L);
    }
    
    private void soConsumerIndex(final long n) {
        this.consumerIndex.lazySet(n);
    }
    
    private static void soElement(final AtomicReferenceArray<Object> atomicReferenceArray, final int n, final Object o) {
        atomicReferenceArray.lazySet(n, o);
    }
    
    private void soNext(final AtomicReferenceArray<Object> atomicReferenceArray, final AtomicReferenceArray<Object> atomicReferenceArray2) {
        soElement(atomicReferenceArray, calcDirectOffset(atomicReferenceArray.length() - 1), atomicReferenceArray2);
    }
    
    private void soProducerIndex(final long n) {
        this.producerIndex.lazySet(n);
    }
    
    private boolean writeToQueue(final AtomicReferenceArray<Object> atomicReferenceArray, final T t, final long n, final int n2) {
        soElement(atomicReferenceArray, n2, t);
        this.soProducerIndex(1L + n);
        return true;
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
        return this.lvProducerIndex() == this.lvConsumerIndex();
    }
    
    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean offer(final T t) {
        final AtomicReferenceArray<Object> producerBuffer = this.producerBuffer;
        final long lpProducerIndex = this.lpProducerIndex();
        final int producerMask = this.producerMask;
        final int calcWrappedOffset = calcWrappedOffset(lpProducerIndex, producerMask);
        if (lpProducerIndex < this.producerLookAhead) {
            return this.writeToQueue(producerBuffer, t, lpProducerIndex, calcWrappedOffset);
        }
        final int producerLookAheadStep = this.producerLookAheadStep;
        if (lvElement(producerBuffer, calcWrappedOffset(producerLookAheadStep + lpProducerIndex, producerMask)) == null) {
            this.producerLookAhead = producerLookAheadStep + lpProducerIndex - 1L;
            return this.writeToQueue(producerBuffer, t, lpProducerIndex, calcWrappedOffset);
        }
        if (lvElement(producerBuffer, calcWrappedOffset(1L + lpProducerIndex, producerMask)) == null) {
            return this.writeToQueue(producerBuffer, t, lpProducerIndex, calcWrappedOffset);
        }
        this.resize(producerBuffer, lpProducerIndex, calcWrappedOffset, t, producerMask);
        return true;
    }
    
    public boolean offer(final T t, final T t2) {
        final AtomicReferenceArray<Object> producerBuffer = this.producerBuffer;
        final long lvProducerIndex = this.lvProducerIndex();
        final int producerMask = this.producerMask;
        if (lvElement(producerBuffer, calcWrappedOffset(lvProducerIndex + 2L, producerMask)) == null) {
            final int calcWrappedOffset = calcWrappedOffset(lvProducerIndex, producerMask);
            soElement(producerBuffer, calcWrappedOffset + 1, t2);
            soElement(producerBuffer, calcWrappedOffset, t);
            this.soProducerIndex(lvProducerIndex + 2L);
        }
        else {
            final AtomicReferenceArray producerBuffer2 = new AtomicReferenceArray<Object>(producerBuffer.length());
            this.producerBuffer = (AtomicReferenceArray<Object>)producerBuffer2;
            final int calcWrappedOffset2 = calcWrappedOffset(lvProducerIndex, producerMask);
            soElement((AtomicReferenceArray<Object>)producerBuffer2, calcWrappedOffset2 + 1, t2);
            soElement((AtomicReferenceArray<Object>)producerBuffer2, calcWrappedOffset2, t);
            this.soNext(producerBuffer, (AtomicReferenceArray<Object>)producerBuffer2);
            soElement(producerBuffer, calcWrappedOffset2, SpscLinkedArrayQueue.HAS_NEXT);
            this.soProducerIndex(lvProducerIndex + 2L);
        }
        return true;
    }
    
    @Override
    public T peek() {
        final AtomicReferenceArray<Object> consumerBuffer = this.consumerBuffer;
        final long lpConsumerIndex = this.lpConsumerIndex();
        final int consumerMask = this.consumerMask;
        Object o;
        if ((o = lvElement(consumerBuffer, calcWrappedOffset(lpConsumerIndex, consumerMask))) == SpscLinkedArrayQueue.HAS_NEXT) {
            o = this.newBufferPeek(this.lvNext(consumerBuffer), lpConsumerIndex, consumerMask);
        }
        return (T)o;
    }
    
    @Override
    public T poll() {
        final AtomicReferenceArray<Object> consumerBuffer = this.consumerBuffer;
        final long lpConsumerIndex = this.lpConsumerIndex();
        final int consumerMask = this.consumerMask;
        final int calcWrappedOffset = calcWrappedOffset(lpConsumerIndex, consumerMask);
        final Object lvElement = lvElement(consumerBuffer, calcWrappedOffset);
        boolean b;
        if (lvElement == SpscLinkedArrayQueue.HAS_NEXT) {
            b = true;
        }
        else {
            b = false;
        }
        if (lvElement != null && !b) {
            soElement(consumerBuffer, calcWrappedOffset, null);
            this.soConsumerIndex(1L + lpConsumerIndex);
            return (T)lvElement;
        }
        if (b) {
            return this.newBufferPoll(this.lvNext(consumerBuffer), lpConsumerIndex, consumerMask);
        }
        return null;
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
    
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public <E> E[] toArray(final E[] array) {
        throw new UnsupportedOperationException();
    }
}
