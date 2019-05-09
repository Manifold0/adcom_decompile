// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.RandomAccess;
import java.io.Serializable;
import java.util.AbstractList;

final class dr extends AbstractList implements Serializable, RandomAccess
{
    private final ArrayList a;
    
    dr(final List list) {
        this.a = new ArrayList(list);
    }
    
    @Override
    public final Object get(final int n) {
        return this.a.get(n);
    }
    
    @Override
    public final int size() {
        return this.a.size();
    }
    
    @Override
    public final Object[] toArray() {
        return this.a.toArray();
    }
}
