// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.protocol;

public enum e
{
    a(0), 
    b(4), 
    c(5), 
    d(6), 
    e(7), 
    f(100), 
    g(101), 
    h(102), 
    i(103), 
    j(200), 
    k(500), 
    @Deprecated
    l(201), 
    m(400), 
    n(300);
    
    private final int o;
    
    private e(final int o) {
        this.o = o;
    }
    
    public int a() {
        return this.o;
    }
}
