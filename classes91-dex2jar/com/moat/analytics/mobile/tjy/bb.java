// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import java.util.Map;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.lang.ref.WeakReference;

class bb
{
    final /* synthetic */ ay a;
    private final WeakReference[] b;
    private final LinkedList c;
    private final Method d;
    
    private bb(final ay a, final Method d, final Object... array) {
        int i = 0;
        this.a = a;
        this.c = new LinkedList();
        Object[] a2 = array;
        if (array == null) {
            a2 = ay.a;
        }
        final WeakReference[] b = new WeakReference[a2.length];
        for (int length = a2.length, n = 0; i < length; ++i, ++n) {
            final Object o = a2[i];
            if (o instanceof Map || o instanceof Integer) {
                this.c.add(o);
            }
            b[n] = new WeakReference(o);
        }
        this.b = b;
        this.d = d;
    }
}
