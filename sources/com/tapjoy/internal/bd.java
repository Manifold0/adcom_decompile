package com.tapjoy.internal;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

public final class bd extends AbstractSet implements Serializable, Set {
    /* renamed from: a */
    transient WeakHashMap f7223a;

    public bd() {
        this(new WeakHashMap());
    }

    private bd(WeakHashMap weakHashMap) {
        this.f7223a = weakHashMap;
    }

    public final boolean add(Object object) {
        return this.f7223a.put(object, this) == null;
    }

    public final void clear() {
        this.f7223a.clear();
    }

    public final Object clone() {
        try {
            return (bd) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean contains(Object object) {
        return this.f7223a.containsKey(object);
    }

    public final boolean isEmpty() {
        return this.f7223a.isEmpty();
    }

    public final Iterator iterator() {
        return this.f7223a.keySet().iterator();
    }

    public final boolean remove(Object object) {
        return this.f7223a.remove(object) != null;
    }

    public final int size() {
        return this.f7223a.size();
    }
}
