// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.EOFException;

final class ia implements hw
{
    public final hu a;
    public final if b;
    boolean c;
    
    ia(final if b) {
        this.a = new hu();
        if (b == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.b = b;
    }
    
    @Override
    public final void a(final long n) {
        if (n < 0L) {
            throw new IllegalArgumentException("byteCount < 0: " + n);
        }
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            while (this.a.b < n) {
                if (this.b.b(this.a, 8192L) == -1L) {
                    final int n2 = 0;
                    if (n2 == 0) {
                        throw new EOFException();
                    }
                    return;
                }
            }
            final int n2 = 1;
            continue;
        }
    }
    
    @Override
    public final long b(final hu hu, long min) {
        if (hu == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (min < 0L) {
            throw new IllegalArgumentException("byteCount < 0: " + min);
        }
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        if (this.a.b == 0L && this.b.b(this.a, 8192L) == -1L) {
            return -1L;
        }
        min = Math.min(min, this.a.b);
        return this.a.b(hu, min);
    }
    
    @Override
    public final hx b(final long n) {
        this.a(n);
        return this.a.b(n);
    }
    
    @Override
    public final boolean b() {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        return this.a.b() && this.b.b(this.a, 8192L) == -1L;
    }
    
    @Override
    public final byte c() {
        this.a(1L);
        return this.a.c();
    }
    
    @Override
    public final String c(final long n) {
        this.a(n);
        return this.a.c(n);
    }
    
    @Override
    public final void close() {
        if (this.c) {
            return;
        }
        this.c = true;
        this.b.close();
        final hu a = this.a;
        try {
            a.d(a.b);
        }
        catch (EOFException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    @Override
    public final void d(long n) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        while (n > 0L) {
            if (this.a.b == 0L && this.b.b(this.a, 8192L) == -1L) {
                throw new EOFException();
            }
            final long min = Math.min(n, this.a.b);
            this.a.d(min);
            n -= min;
        }
    }
    
    @Override
    public final int e() {
        this.a(4L);
        return ih.a(this.a.d());
    }
    
    @Override
    public final long f() {
        this.a(8L);
        return this.a.f();
    }
    
    @Override
    public final String toString() {
        return "buffer(" + this.b + ")";
    }
}
