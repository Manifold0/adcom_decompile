package com.tapjoy.internal;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public final class be extends AbstractMap {
    /* renamed from: a */
    private final HashMap f7224a = new HashMap();
    /* renamed from: b */
    private final cc f7225b = new cc();

    public final int size() {
        m7195b();
        return this.f7224a.size();
    }

    public final void clear() {
        this.f7224a.clear();
        do {
        } while (this.f7225b.m7314a() != null);
    }

    public final boolean containsKey(Object key) {
        m7195b();
        return this.f7224a.containsKey(key);
    }

    public final boolean containsValue(Object value) {
        m7195b();
        for (cb cbVar : this.f7224a.values()) {
            if (value.equals(cbVar.get())) {
                return true;
            }
        }
        return false;
    }

    public final Object get(Object key) {
        m7195b();
        return m7194a((cb) this.f7224a.get(key));
    }

    public final Object put(Object key, Object value) {
        m7195b();
        return m7194a((cb) this.f7224a.put(key, new cb(key, value, this.f7225b)));
    }

    public final Object remove(Object key) {
        m7195b();
        return m7194a((cb) this.f7224a.remove(key));
    }

    /* renamed from: a */
    private static Object m7194a(cb cbVar) {
        return cbVar != null ? cbVar.get() : null;
    }

    public final Set entrySet() {
        m7195b();
        throw new UnsupportedOperationException();
    }

    public final Set keySet() {
        m7195b();
        return this.f7224a.keySet();
    }

    public final Collection values() {
        m7195b();
        throw new UnsupportedOperationException();
    }

    public final boolean equals(Object object) {
        m7195b();
        throw new UnsupportedOperationException();
    }

    public final int hashCode() {
        m7195b();
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        m7195b();
        throw new UnsupportedOperationException();
    }

    /* renamed from: b */
    private void m7195b() {
        while (true) {
            cb a = this.f7225b.m7314a();
            if (a != null) {
                this.f7224a.remove(a.f7278a);
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    public static be m7193a() {
        return new be();
    }
}
