// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.h;

import java.util.Collections;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;

public class b
{
    public static final b a;
    public final int b;
    private final ArrayList<c> c;
    
    static {
        a = new b(new ArrayList<c>(0));
    }
    
    public b(final Collection<c> collection) {
        this.b = collection.size();
        this.c = new ArrayList<c>(this.b);
        final Iterator<c> iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.c.add(iterator.next());
        }
    }
    
    public c a(final int n) {
        if (n < 0 || n >= this.b) {
            return null;
        }
        return this.c.get(n);
    }
    
    public Collection<c> a() {
        return Collections.unmodifiableCollection((Collection<? extends c>)this.c);
    }
}
