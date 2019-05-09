// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.a;

import com.kongregate.android.internal.util.j;
import java.util.Locale;

public enum f
{
    a, 
    b, 
    c, 
    d, 
    e, 
    f, 
    g, 
    h, 
    i, 
    j, 
    k, 
    l, 
    m, 
    n, 
    o, 
    p, 
    q, 
    r, 
    s, 
    t, 
    u, 
    v, 
    w, 
    x, 
    y, 
    z;
    
    public static f a(final String s) {
        try {
            return valueOf(s.toUpperCase(Locale.US));
        }
        catch (IllegalArgumentException ex) {
            com.kongregate.android.internal.util.j.c("Unrecognized game callback field name: " + s);
            return null;
        }
    }
    
    public String a() {
        return this.toString().toLowerCase(Locale.US);
    }
}
