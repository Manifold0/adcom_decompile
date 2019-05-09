// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.List;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class gk implements ck
{
    public final gc a;
    Set b;
    private final Map c;
    private final Map d;
    
    public gk(final gc a) {
        this.a = a;
        this.c = Collections.synchronizedMap(new HashMap<Object, Object>());
        this.d = cx.a();
        this.b = null;
    }
    
    private void a(final cf cf, hm.a a) {
        if (!(cf instanceof hm)) {
            throw new IllegalStateException(cf.getClass().getName());
        }
        Object b = null;
        final boolean d;
        Label_0052: {
            if (a.b == null) {
                break Label_0052;
            }
            b = a.b;
            synchronized (this) {
                if (b instanceof Collection) {
                    b = new HashSet(cv.a((Iterable)b));
                }
                else {
                    b = cy.a(((Iterable)b).iterator());
                }
                this.b = (Set)b;
                // monitorexit(this)
                b = cf;
                final String c = ((hm)b).c;
                d = ((hm)b).d;
                this.d.remove(c);
                if (!d) {
                    this.c.put(c, a.a);
                }
                a = (hm.a)a.a;
                b = this.a.p;
                if (a instanceof gi) {
                    fz.a("No content for \"{}\"", new Object[] { c });
                    ((gd)b).a(c);
                    return;
                }
            }
        }
        final String s;
        fz.a("New content for \"{}\" is ready", new Object[] { s });
        if (d) {
            ((gj)a).a((gd)b, new ez());
            return;
        }
        ((gd)b).b(s);
    }
    
    @Override
    public final void a(final cf cf) {
        this.a(cf, new hm.a(new gi(), null));
    }
}
