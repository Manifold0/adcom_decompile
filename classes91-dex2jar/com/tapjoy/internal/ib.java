// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

final class ib
{
    final byte[] a;
    int b;
    int c;
    boolean d;
    boolean e;
    ib f;
    ib g;
    
    ib() {
        this.a = new byte[8192];
        this.e = true;
        this.d = false;
    }
    
    ib(final ib ib) {
        this(ib.a, ib.b, ib.c);
        ib.d = true;
    }
    
    ib(final byte[] a, final int b, final int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.e = false;
        this.d = true;
    }
    
    public final ib a() {
        ib f;
        if (this.f != this) {
            f = this.f;
        }
        else {
            f = null;
        }
        this.g.f = this.f;
        this.f.g = this.g;
        this.f = null;
        this.g = null;
        return f;
    }
    
    public final ib a(final ib ib) {
        ib.g = this;
        ib.f = this.f;
        this.f.g = ib;
        return this.f = ib;
    }
    
    public final void a(final ib ib, final int n) {
        if (!ib.e) {
            throw new IllegalArgumentException();
        }
        if (ib.c + n > 8192) {
            if (ib.d) {
                throw new IllegalArgumentException();
            }
            if (ib.c + n - ib.b > 8192) {
                throw new IllegalArgumentException();
            }
            System.arraycopy(ib.a, ib.b, ib.a, 0, ib.c - ib.b);
            ib.c -= ib.b;
            ib.b = 0;
        }
        System.arraycopy(this.a, this.b, ib.a, ib.c, n);
        ib.c += n;
        this.b += n;
    }
}
