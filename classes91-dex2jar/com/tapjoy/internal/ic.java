// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

final class ic
{
    static ib a;
    static long b;
    
    private ic() {
    }
    
    static ib a() {
        synchronized (ic.class) {
            if (ic.a != null) {
                final ib a = ic.a;
                ic.a = a.f;
                a.f = null;
                ic.b -= 8192L;
                return a;
            }
            // monitorexit(ic.class)
            return new ib();
        }
    }
    
    static void a(final ib ib) {
        if (ib.f != null || ib.g != null) {
            throw new IllegalArgumentException();
        }
        if (ib.d) {
            return;
        }
        synchronized (ic.class) {
            if (ic.b + 8192L > 65536L) {
                return;
            }
        }
        ic.b += 8192L;
        final ib a;
        a.f = ic.a;
        a.c = 0;
        a.b = 0;
        ic.a = a;
    }
    // monitorexit(ic.class)
}
