package com.tapjoy.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public final class ba implements bc {
    /* renamed from: a */
    private final List f7222a;

    public ba(List list) {
        this.f7222a = list;
    }

    public final boolean add(Object object) {
        return this.f7222a.add(object);
    }

    public final boolean addAll(Collection collection) {
        return this.f7222a.addAll(collection);
    }

    public final void clear() {
        this.f7222a.clear();
    }

    public final boolean contains(Object object) {
        return this.f7222a.contains(object);
    }

    public final boolean containsAll(Collection collection) {
        return this.f7222a.containsAll(collection);
    }

    public final boolean equals(Object object) {
        return this.f7222a.equals(object);
    }

    /* renamed from: a */
    public final Object mo6182a(int i) {
        return this.f7222a.get(i);
    }

    public final int hashCode() {
        return this.f7222a.hashCode();
    }

    public final boolean isEmpty() {
        return this.f7222a.isEmpty();
    }

    public final Iterator iterator() {
        return this.f7222a.iterator();
    }

    public final boolean remove(Object object) {
        return this.f7222a.remove(object);
    }

    public final boolean removeAll(Collection collection) {
        return this.f7222a.removeAll(collection);
    }

    public final boolean retainAll(Collection collection) {
        return this.f7222a.retainAll(collection);
    }

    public final int size() {
        return this.f7222a.size();
    }

    public final Object[] toArray() {
        return this.f7222a.toArray();
    }

    public final Object[] toArray(Object[] array) {
        return this.f7222a.toArray(array);
    }

    public final boolean offer(Object e) {
        return this.f7222a.add(e);
    }

    public final Object remove() {
        Object poll = poll();
        if (poll != null) {
            return poll;
        }
        throw new NoSuchElementException();
    }

    public final Object poll() {
        return this.f7222a.isEmpty() ? null : this.f7222a.remove(0);
    }

    public final Object element() {
        Object peek = peek();
        if (peek != null) {
            return peek;
        }
        throw new NoSuchElementException();
    }

    public final Object peek() {
        return this.f7222a.isEmpty() ? null : this.f7222a.get(0);
    }

    /* renamed from: b */
    public final void mo6183b(int i) {
        bb.m7192a(this.f7222a, i);
    }
}
