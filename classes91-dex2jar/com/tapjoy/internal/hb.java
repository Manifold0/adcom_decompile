// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

final class hb extends gr implements ft
{
    public static final bn a;
    private final String b;
    private final String c;
    private final int d;
    private final String e;
    
    static {
        a = new bn() {};
    }
    
    hb(final String b, final String c, final int d, final String e) {
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    @Override
    public final String a() {
        return this.b;
    }
    
    @Override
    public final String b() {
        return this.c;
    }
    
    @Override
    public final int c() {
        return this.d;
    }
    
    @Override
    public final String d() {
        return this.e;
    }
}
