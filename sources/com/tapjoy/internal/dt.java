package com.tapjoy.internal;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

final class dt extends AbstractList implements Serializable, RandomAccess {
    /* renamed from: a */
    List f7379a;
    /* renamed from: b */
    private final List f7380b;

    dt(List list) {
        this.f7380b = list;
        this.f7379a = list;
    }

    public final Object get(int index) {
        return this.f7379a.get(index);
    }

    public final int size() {
        return this.f7379a.size();
    }

    public final Object set(int index, Object element) {
        if (this.f7379a == this.f7380b) {
            this.f7379a = new ArrayList(this.f7380b);
        }
        return this.f7379a.set(index, element);
    }

    public final void add(int index, Object element) {
        if (this.f7379a == this.f7380b) {
            this.f7379a = new ArrayList(this.f7380b);
        }
        this.f7379a.add(index, element);
    }

    public final Object remove(int index) {
        if (this.f7379a == this.f7380b) {
            this.f7379a = new ArrayList(this.f7380b);
        }
        return this.f7379a.remove(index);
    }
}
