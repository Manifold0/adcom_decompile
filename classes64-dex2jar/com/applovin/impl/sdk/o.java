// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

public enum o
{
    a("NONE"), 
    b("DIRECT"), 
    c("INDIRECT");
    
    private final String d;
    
    static {
        e = new o[] { o.a, o.b, o.c };
    }
    
    private o(final String d) {
        this.d = d;
    }
    
    public static o a(final String s) {
        if (o.b.toString().equalsIgnoreCase(s)) {
            return o.b;
        }
        if (o.c.toString().equalsIgnoreCase(s)) {
            return o.c;
        }
        return o.a;
    }
    
    @Override
    public String toString() {
        return this.d;
    }
}
