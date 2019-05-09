// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.t;

public enum k
{
    a(-1, 100, 1), 
    b(-1, 120, 2), 
    c(-1, 300, 3), 
    d(-1, 400, 4), 
    e(-1, 50, 5);
    
    private final int f;
    private final int g;
    private final int h;
    
    private k(final int f, final int g, final int h) {
        this.f = f;
        this.g = g;
        this.h = h;
    }
    
    public int a() {
        return this.f;
    }
    
    public int b() {
        return this.g;
    }
    
    public int c() {
        return this.h;
    }
}
