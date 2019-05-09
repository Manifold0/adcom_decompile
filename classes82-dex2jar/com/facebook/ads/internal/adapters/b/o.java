// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters.b;

import java.io.Serializable;

public class o implements Serializable
{
    private static final long serialVersionUID = 1L;
    private final String a;
    private final m b;
    private final e c;
    private final i d;
    private final b e;
    private final d f;
    private final String g;
    
    private o(final String g, final String a, final m b, final e c, final i d, final b e, final d f) {
        this.g = g;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    public static o a(final k k) {
        final l l = k.d().get(0);
        return new o(k.e(), k.c(), k.a(), l.a(), l.b(), k.b(), l.c());
    }
    
    public static o a(final q q) {
        return new o(q.e(), q.a(), q.f(), q.g(), q.h(), q.i(), q.j());
    }
    
    public String a() {
        return this.g;
    }
    
    public m b() {
        return this.b;
    }
    
    public e c() {
        return this.c;
    }
    
    public i d() {
        return this.d;
    }
    
    public b e() {
        return this.e;
    }
    
    public d f() {
        return this.f;
    }
    
    public String g() {
        return this.a;
    }
}
