// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.a;

public enum h
{
    a(-1), 
    b(100), 
    c(300), 
    d(301), 
    e(302), 
    f(303), 
    g(400), 
    h(401), 
    i(402), 
    j(405), 
    k(600), 
    l(603), 
    m(604);
    
    private final int n;
    
    static {
        o = new h[] { h.a, h.b, h.c, h.d, h.e, h.f, h.g, h.h, h.i, h.j, h.k, h.l, h.m };
    }
    
    private h(final int n2) {
        this.n = n2;
    }
    
    public int a() {
        return this.n;
    }
}
