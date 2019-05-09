// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

final class n
{
    private static boolean a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    
    static {
        n.a = false;
    }
    
    n() {
        this.b = !j.c;
        this.c = false;
        this.d = false;
        this.e = true;
    }
    
    static void a() {
        n.a = true;
    }
    
    static void b() {
        n.a = false;
    }
    
    static boolean c() {
        return n.a;
    }
    
    final void a(final boolean c) {
        this.c = c;
    }
    
    final void b(final boolean e) {
        this.e = e;
    }
    
    final void c(final boolean d) {
        this.d = d;
    }
    
    final void d() {
        this.b = true;
    }
    
    final boolean e() {
        return this.e;
    }
    
    final boolean f() {
        return n.a && this.c && this.b && !this.e && !this.d;
    }
    
    final boolean g() {
        return this.d;
    }
    
    @Override
    public final String toString() {
        return super.toString();
    }
}
