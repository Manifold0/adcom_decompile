// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;
import java.io.Serializable;
import java.util.AbstractList;

final class dt extends AbstractList implements Serializable, RandomAccess
{
    List a;
    private final List b;
    
    dt(final List list) {
        this.b = list;
        this.a = list;
    }
    
    @Override
    public final void add(final int n, final Object o) {
        if (this.a == this.b) {
            this.a = new ArrayList(this.b);
        }
        this.a.add(n, o);
    }
    
    @Override
    public final Object get(final int n) {
        return this.a.get(n);
    }
    
    @Override
    public final Object remove(final int n) {
        if (this.a == this.b) {
            this.a = new ArrayList(this.b);
        }
        return this.a.remove(n);
    }
    
    @Override
    public final Object set(final int n, final Object o) {
        if (this.a == this.b) {
            this.a = new ArrayList(this.b);
        }
        return this.a.set(n, o);
    }
    
    @Override
    public final int size() {
        return this.a.size();
    }
}
