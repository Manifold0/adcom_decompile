// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Collection;
import java.lang.ref.ReferenceQueue;
import java.util.Set;
import java.util.Iterator;
import java.util.HashMap;
import java.util.AbstractMap;

public final class be extends AbstractMap
{
    private final HashMap a;
    private final cc b;
    
    public be() {
        this.a = new HashMap();
        this.b = new cc();
    }
    
    public static be a() {
        return new be();
    }
    
    private static Object a(final cb cb) {
        if (cb != null) {
            return cb.get();
        }
        return null;
    }
    
    private void b() {
        while (true) {
            final cb a = this.b.a();
            if (a == null) {
                break;
            }
            this.a.remove(a.a);
        }
    }
    
    @Override
    public final void clear() {
        this.a.clear();
        while (this.b.a() != null) {}
    }
    
    @Override
    public final boolean containsKey(final Object o) {
        this.b();
        return this.a.containsKey(o);
    }
    
    @Override
    public final boolean containsValue(final Object o) {
        this.b();
        final Iterator<cb> iterator = this.a.values().iterator();
        while (iterator.hasNext()) {
            if (o.equals(iterator.next().get())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public final Set entrySet() {
        this.b();
        throw new UnsupportedOperationException();
    }
    
    @Override
    public final boolean equals(final Object o) {
        this.b();
        throw new UnsupportedOperationException();
    }
    
    @Override
    public final Object get(final Object o) {
        this.b();
        return a(this.a.get(o));
    }
    
    @Override
    public final int hashCode() {
        this.b();
        throw new UnsupportedOperationException();
    }
    
    @Override
    public final Set keySet() {
        this.b();
        return this.a.keySet();
    }
    
    @Override
    public final Object put(final Object o, final Object o2) {
        this.b();
        return a(this.a.put(o, new cb(o, o2, this.b)));
    }
    
    @Override
    public final Object remove(final Object o) {
        this.b();
        return a(this.a.remove(o));
    }
    
    @Override
    public final int size() {
        this.b();
        return this.a.size();
    }
    
    @Override
    public final String toString() {
        this.b();
        throw new UnsupportedOperationException();
    }
    
    @Override
    public final Collection values() {
        this.b();
        throw new UnsupportedOperationException();
    }
}
