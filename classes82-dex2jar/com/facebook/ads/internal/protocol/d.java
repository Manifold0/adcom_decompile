// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.protocol;

import java.io.Serializable;

public enum d implements Serializable
{
    @Deprecated
    a(320, 50), 
    b(0, 0), 
    c(-1, 50), 
    d(-1, 90), 
    e(-1, 250);
    
    private final int f;
    private final int g;
    
    private d(final int f, final int g) {
        this.f = f;
        this.g = g;
    }
    
    public static d a(final int n, final int n2) {
        if (d.b.g == n2 && d.b.f == n) {
            return d.b;
        }
        if (d.a.g == n2 && d.a.f == n) {
            return d.a;
        }
        if (d.c.g == n2 && d.c.f == n) {
            return d.c;
        }
        if (d.d.g == n2 && d.d.f == n) {
            return d.d;
        }
        if (d.e.g == n2 && d.e.f == n) {
            return d.e;
        }
        return null;
    }
    
    public int a() {
        return this.f;
    }
    
    public int b() {
        return this.g;
    }
}
