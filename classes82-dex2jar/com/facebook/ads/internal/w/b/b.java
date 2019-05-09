// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import org.json.JSONArray;

public enum b
{
    a(0), 
    b(1), 
    c(2), 
    d(3), 
    e(4), 
    f(5), 
    g(6), 
    h(7), 
    i(8), 
    j(9), 
    k(10), 
    l(11), 
    m(16), 
    n(17);
    
    public static final b[] o;
    private static final String q;
    private final int p;
    
    static {
        int n2 = 0;
        o = new b[] { b.d, b.e, b.f, b.h, b.l, b.m, b.n };
        final JSONArray jsonArray = new JSONArray();
        for (b[] o2 = b.o; n2 < o2.length; ++n2) {
            jsonArray.put(o2[n2].a());
        }
        q = jsonArray.toString();
    }
    
    private b(final int p4) {
        this.p = p4;
    }
    
    public static String b() {
        return b.q;
    }
    
    public int a() {
        return this.p;
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.p);
    }
}
