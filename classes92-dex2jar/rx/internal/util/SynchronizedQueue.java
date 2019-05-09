// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util;

import java.util.Iterator;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class SynchronizedQueue<T> implements Queue<T>, Cloneable
{
    private final Queue<T> list;
    private final int size;
    
    public SynchronizedQueue() {
        this.list = new LinkedList<T>();
        this.size = -1;
    }
    
    public SynchronizedQueue(final int size) {
        this.list = new LinkedList<T>();
        this.size = size;
    }
    
    @Override
    public boolean add(final T t) {
        synchronized (this) {
            return this.list.add(t);
        }
    }
    
    @Override
    public boolean addAll(final Collection<? extends T> collection) {
        synchronized (this) {
            return this.list.addAll((Collection<?>)collection);
        }
    }
    
    @Override
    public void clear() {
        synchronized (this) {
            this.list.clear();
        }
    }
    
    public Object clone() {
        synchronized (this) {
            final SynchronizedQueue synchronizedQueue = new SynchronizedQueue(this.size);
            synchronizedQueue.addAll(this.list);
            return synchronizedQueue;
        }
    }
    
    @Override
    public boolean contains(final Object o) {
        synchronized (this) {
            return this.list.contains(o);
        }
    }
    
    @Override
    public boolean containsAll(final Collection<?> collection) {
        synchronized (this) {
            return this.list.containsAll(collection);
        }
    }
    
    @Override
    public T element() {
        synchronized (this) {
            return this.list.element();
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = false;
        boolean b2;
        if (this == o) {
            b2 = true;
        }
        else {
            b2 = b;
            if (o != null) {
                b2 = b;
                if (this.getClass() == o.getClass()) {
                    return this.list.equals(((SynchronizedQueue)o).list);
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return this.list.hashCode();
    }
    
    @Override
    public boolean isEmpty() {
        synchronized (this) {
            return this.list.isEmpty();
        }
    }
    
    @Override
    public Iterator<T> iterator() {
        synchronized (this) {
            return this.list.iterator();
        }
    }
    
    @Override
    public boolean offer(final T t) {
        synchronized (this) {
            return (this.size <= -1 || this.list.size() + 1 <= this.size) && this.list.offer(t);
        }
    }
    
    @Override
    public T peek() {
        synchronized (this) {
            return this.list.peek();
        }
    }
    
    @Override
    public T poll() {
        synchronized (this) {
            return this.list.poll();
        }
    }
    
    @Override
    public T remove() {
        synchronized (this) {
            return this.list.remove();
        }
    }
    
    @Override
    public boolean remove(final Object o) {
        synchronized (this) {
            return this.list.remove(o);
        }
    }
    
    @Override
    public boolean removeAll(final Collection<?> collection) {
        synchronized (this) {
            return this.list.removeAll(collection);
        }
    }
    
    @Override
    public boolean retainAll(final Collection<?> collection) {
        synchronized (this) {
            return this.list.retainAll(collection);
        }
    }
    
    @Override
    public int size() {
        synchronized (this) {
            return this.list.size();
        }
    }
    
    @Override
    public Object[] toArray() {
        synchronized (this) {
            return this.list.toArray();
        }
    }
    
    @Override
    public <R> R[] toArray(final R[] array) {
        synchronized (this) {
            return this.list.toArray(array);
        }
    }
    
    @Override
    public String toString() {
        synchronized (this) {
            return this.list.toString();
        }
    }
}
