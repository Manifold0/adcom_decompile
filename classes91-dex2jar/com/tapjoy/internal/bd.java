// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Iterator;
import java.util.WeakHashMap;
import java.util.Set;
import java.io.Serializable;
import java.util.AbstractSet;

public final class bd extends AbstractSet implements Serializable, Set
{
    transient WeakHashMap a;
    
    public bd() {
        this(new WeakHashMap());
    }
    
    private bd(final WeakHashMap a) {
        this.a = a;
    }
    
    @Override
    public final boolean add(final Object o) {
        return this.a.put(o, this) == null;
    }
    
    @Override
    public final void clear() {
        this.a.clear();
    }
    
    public final Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    @Override
    public final boolean contains(final Object o) {
        return this.a.containsKey(o);
    }
    
    @Override
    public final boolean isEmpty() {
        return this.a.isEmpty();
    }
    
    @Override
    public final Iterator iterator() {
        return this.a.keySet().iterator();
    }
    
    @Override
    public final boolean remove(final Object o) {
        return this.a.remove(o) != null;
    }
    
    @Override
    public final int size() {
        return this.a.size();
    }
}
