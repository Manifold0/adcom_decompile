// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Collection;
import java.util.List;

public final class ba implements bc
{
    private final List a;
    
    public ba(final List a) {
        this.a = a;
    }
    
    @Override
    public final Object a(final int n) {
        return this.a.get(n);
    }
    
    @Override
    public final boolean add(final Object o) {
        return this.a.add(o);
    }
    
    @Override
    public final boolean addAll(final Collection collection) {
        return this.a.addAll(collection);
    }
    
    @Override
    public final void b(final int n) {
        bb.a(this.a, n);
    }
    
    @Override
    public final void clear() {
        this.a.clear();
    }
    
    @Override
    public final boolean contains(final Object o) {
        return this.a.contains(o);
    }
    
    @Override
    public final boolean containsAll(final Collection collection) {
        return this.a.containsAll(collection);
    }
    
    @Override
    public final Object element() {
        final Object peek = this.peek();
        if (peek != null) {
            return peek;
        }
        throw new NoSuchElementException();
    }
    
    @Override
    public final boolean equals(final Object o) {
        return this.a.equals(o);
    }
    
    @Override
    public final int hashCode() {
        return this.a.hashCode();
    }
    
    @Override
    public final boolean isEmpty() {
        return this.a.isEmpty();
    }
    
    @Override
    public final Iterator iterator() {
        return this.a.iterator();
    }
    
    @Override
    public final boolean offer(final Object o) {
        return this.a.add(o);
    }
    
    @Override
    public final Object peek() {
        if (this.a.isEmpty()) {
            return null;
        }
        return this.a.get(0);
    }
    
    @Override
    public final Object poll() {
        if (this.a.isEmpty()) {
            return null;
        }
        return this.a.remove(0);
    }
    
    @Override
    public final Object remove() {
        final Object poll = this.poll();
        if (poll != null) {
            return poll;
        }
        throw new NoSuchElementException();
    }
    
    @Override
    public final boolean remove(final Object o) {
        return this.a.remove(o);
    }
    
    @Override
    public final boolean removeAll(final Collection collection) {
        return this.a.removeAll(collection);
    }
    
    @Override
    public final boolean retainAll(final Collection collection) {
        return this.a.retainAll(collection);
    }
    
    @Override
    public final int size() {
        return this.a.size();
    }
    
    @Override
    public final Object[] toArray() {
        return this.a.toArray();
    }
    
    @Override
    public final Object[] toArray(final Object[] array) {
        return this.a.toArray(array);
    }
}
