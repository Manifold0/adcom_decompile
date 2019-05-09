package com.tapjoy.internal;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

final class dr extends AbstractList implements Serializable, RandomAccess {
    /* renamed from: a */
    private final ArrayList f7378a;

    dr(List list) {
        this.f7378a = new ArrayList(list);
    }

    public final int size() {
        return this.f7378a.size();
    }

    public final Object get(int i) {
        return this.f7378a.get(i);
    }

    public final Object[] toArray() {
        return this.f7378a.toArray();
    }
}
