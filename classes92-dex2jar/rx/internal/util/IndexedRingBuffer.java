// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import rx.functions.Func1;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Subscription;

public final class IndexedRingBuffer<E> implements Subscription
{
    private static final ObjectPool<IndexedRingBuffer<?>> POOL;
    static final int SIZE;
    private final ElementSection<E> elements;
    final AtomicInteger index;
    private final IndexSection removed;
    final AtomicInteger removedIndex;
    
    static {
        POOL = new ObjectPool<IndexedRingBuffer<?>>() {
            @Override
            protected IndexedRingBuffer<?> createObject() {
                return new IndexedRingBuffer<Object>();
            }
        };
        int n = 128;
        if (PlatformDependent.isAndroid()) {
            n = 8;
        }
        final String property = System.getProperty("rx.indexed-ring-buffer.size");
        int int1 = n;
        while (true) {
            if (property == null) {
                break Label_0040;
            }
            try {
                int1 = Integer.parseInt(property);
                SIZE = int1;
            }
            catch (NumberFormatException ex) {
                System.err.println("Failed to set 'rx.indexed-ring-buffer.size' with value " + property + " => " + ex.getMessage());
                int1 = n;
                continue;
            }
            break;
        }
    }
    
    IndexedRingBuffer() {
        this.elements = new ElementSection<E>();
        this.removed = new IndexSection();
        this.index = new AtomicInteger();
        this.removedIndex = new AtomicInteger();
    }
    
    private int forEach(final Func1<? super E, Boolean> func1, int n, final int n2) {
        final int value = this.index.get();
        final int n3 = n;
        ElementSection<E> elementSection = this.elements;
        int n4 = n3;
        int i = n;
        if (n >= IndexedRingBuffer.SIZE) {
            elementSection = this.getElementSection(n);
            i = n % IndexedRingBuffer.SIZE;
            n4 = n3;
        }
    Label_0085:
        while (true) {
            n = n4;
            if (elementSection == null) {
                break;
            }
            while (i < IndexedRingBuffer.SIZE) {
                if ((n = n4) >= value) {
                    break Label_0085;
                }
                if (n4 >= n2) {
                    n = n4;
                    break Label_0085;
                }
                final E value2 = elementSection.array.get(i);
                if (value2 != null && !func1.call(value2)) {
                    return n4;
                }
                ++i;
                ++n4;
            }
            elementSection = elementSection.next.get();
            i = 0;
        }
        return n;
    }
    
    private ElementSection<E> getElementSection(int n) {
        ElementSection<E> elements;
        if (n < IndexedRingBuffer.SIZE) {
            elements = this.elements;
        }
        else {
            final int n2 = n / IndexedRingBuffer.SIZE;
            ElementSection<E> elementSection = this.elements;
            n = 0;
            while (true) {
                elements = elementSection;
                if (n >= n2) {
                    break;
                }
                elementSection = elementSection.getNext();
                ++n;
            }
        }
        return elements;
    }
    
    private int getIndexForAdd() {
        synchronized (this) {
            final int indexFromPreviouslyRemoved = this.getIndexFromPreviouslyRemoved();
            int andIncrement;
            if (indexFromPreviouslyRemoved >= 0) {
                int n;
                if (indexFromPreviouslyRemoved < IndexedRingBuffer.SIZE) {
                    n = this.removed.getAndSet(indexFromPreviouslyRemoved, -1);
                }
                else {
                    n = this.getIndexSection(indexFromPreviouslyRemoved).getAndSet(indexFromPreviouslyRemoved % IndexedRingBuffer.SIZE, -1);
                }
                andIncrement = n;
                if (n == this.index.get()) {
                    this.index.getAndIncrement();
                    andIncrement = n;
                }
            }
            else {
                andIncrement = this.index.getAndIncrement();
            }
            return andIncrement;
        }
    }
    
    private int getIndexFromPreviouslyRemoved() {
        synchronized (this) {
            int value;
            do {
                value = this.removedIndex.get();
                if (value > 0) {
                    continue;
                }
                return -1;
            } while (!this.removedIndex.compareAndSet(value, value - 1));
            return value - 1;
        }
    }
    
    private IndexSection getIndexSection(int n) {
        IndexSection removed;
        if (n < IndexedRingBuffer.SIZE) {
            removed = this.removed;
        }
        else {
            final int n2 = n / IndexedRingBuffer.SIZE;
            IndexSection indexSection = this.removed;
            n = 0;
            while (true) {
                removed = indexSection;
                if (n >= n2) {
                    break;
                }
                indexSection = indexSection.getNext();
                ++n;
            }
        }
        return removed;
    }
    
    public static <T> IndexedRingBuffer<T> getInstance() {
        return (IndexedRingBuffer<T>)IndexedRingBuffer.POOL.borrowObject();
    }
    
    private void pushRemovedIndex(final int n) {
        synchronized (this) {
            final int andIncrement = this.removedIndex.getAndIncrement();
            if (andIncrement < IndexedRingBuffer.SIZE) {
                this.removed.set(andIncrement, n);
            }
            else {
                this.getIndexSection(andIncrement).set(andIncrement % IndexedRingBuffer.SIZE, n);
            }
        }
    }
    
    public int add(final E e) {
        final int indexForAdd = this.getIndexForAdd();
        if (indexForAdd < IndexedRingBuffer.SIZE) {
            this.elements.array.set(indexForAdd, e);
            return indexForAdd;
        }
        this.getElementSection(indexForAdd).array.set(indexForAdd % IndexedRingBuffer.SIZE, e);
        return indexForAdd;
    }
    
    public int forEach(final Func1<? super E, Boolean> func1) {
        return this.forEach(func1, 0);
    }
    
    public int forEach(final Func1<? super E, Boolean> func1, int forEach) {
        final int forEach2 = this.forEach(func1, forEach, this.index.get());
        if (forEach > 0 && forEach2 == this.index.get()) {
            forEach = this.forEach(func1, 0, forEach);
        }
        else if ((forEach = forEach2) == this.index.get()) {
            return 0;
        }
        return forEach;
    }
    
    @Override
    public boolean isUnsubscribed() {
        return false;
    }
    
    public void releaseToPool() {
        final int value = this.index.get();
        int n = 0;
    Label_0035:
        for (ElementSection<E> elements = this.elements; elements != null; elements = elements.next.get()) {
            for (int i = 0; i < IndexedRingBuffer.SIZE; ++i, ++n) {
                if (n >= value) {
                    break Label_0035;
                }
                elements.array.set(i, null);
            }
        }
        this.index.set(0);
        this.removedIndex.set(0);
        IndexedRingBuffer.POOL.returnObject(this);
    }
    
    public E remove(final int n) {
        E e;
        if (n < IndexedRingBuffer.SIZE) {
            e = this.elements.array.getAndSet(n, null);
        }
        else {
            e = this.getElementSection(n).array.getAndSet(n % IndexedRingBuffer.SIZE, null);
        }
        this.pushRemovedIndex(n);
        return e;
    }
    
    @Override
    public void unsubscribe() {
        this.releaseToPool();
    }
    
    static final class ElementSection<E>
    {
        final AtomicReferenceArray<E> array;
        final AtomicReference<ElementSection<E>> next;
        
        ElementSection() {
            this.array = new AtomicReferenceArray<E>(IndexedRingBuffer.SIZE);
            this.next = new AtomicReference<ElementSection<E>>();
        }
        
        ElementSection<E> getNext() {
            if (this.next.get() != null) {
                return this.next.get();
            }
            final ElementSection elementSection = new ElementSection();
            if (this.next.compareAndSet(null, elementSection)) {
                return elementSection;
            }
            return this.next.get();
        }
    }
    
    static class IndexSection
    {
        private final AtomicReference<IndexSection> _next;
        private final AtomicIntegerArray unsafeArray;
        
        IndexSection() {
            this.unsafeArray = new AtomicIntegerArray(IndexedRingBuffer.SIZE);
            this._next = new AtomicReference<IndexSection>();
        }
        
        public int getAndSet(final int n, final int n2) {
            return this.unsafeArray.getAndSet(n, n2);
        }
        
        IndexSection getNext() {
            if (this._next.get() != null) {
                return this._next.get();
            }
            final IndexSection indexSection = new IndexSection();
            if (this._next.compareAndSet(null, indexSection)) {
                return indexSection;
            }
            return this._next.get();
        }
        
        public void set(final int n, final int n2) {
            this.unsafeArray.set(n, n2);
        }
    }
}
