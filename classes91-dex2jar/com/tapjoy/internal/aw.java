// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Iterator;
import java.util.LinkedHashMap;

public final class aw extends av
{
    private final LinkedHashMap a;
    private int b;
    
    public aw() {
        this.a = new LinkedHashMap(0, 0.75f, true);
        this.b = 10;
    }
    
    private void a() {
        int n = this.a.size() - this.b;
        if (n > 0) {
            final Iterator iterator = this.a.entrySet().iterator();
            while (n > 0 && iterator.hasNext()) {
                --n;
                iterator.next();
                iterator.remove();
            }
        }
    }
    
    @Override
    protected final at a(final Object o, final boolean b) {
        ar ar2;
        final ar ar = ar2 = this.a.get(o);
        if (ar == null) {
            ar2 = ar;
            if (b) {
                ar2 = new ar(o);
                this.a.put(o, ar2);
                this.a();
            }
        }
        return ar2;
    }
    
    @Override
    public final void a(final Object o, final Object o2) {
        super.a(o, o2);
        this.a();
    }
}
