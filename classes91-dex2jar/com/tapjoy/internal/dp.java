// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class dp
{
    final hv a;
    
    public dp(final hv a) {
        this.a = a;
    }
    
    static int a(final int n) {
        if ((n & 0xFFFFFF80) == 0x0) {
            return 1;
        }
        if ((n & 0xFFFFC000) == 0x0) {
            return 2;
        }
        if ((0xFFE00000 & n) == 0x0) {
            return 3;
        }
        if ((0xF0000000 & n) == 0x0) {
            return 4;
        }
        return 5;
    }
    
    static int a(final int n, final dk dk) {
        return n << 3 | dk.e;
    }
    
    static int a(final long n) {
        if ((0xFFFFFFFFFFFFFF80L & n) == 0x0L) {
            return 1;
        }
        if ((0xFFFFFFFFFFFFC000L & n) == 0x0L) {
            return 2;
        }
        if ((0xFFFFFFFFFFE00000L & n) == 0x0L) {
            return 3;
        }
        if ((0xFFFFFFFFF0000000L & n) == 0x0L) {
            return 4;
        }
        if ((0xFFFFFFF800000000L & n) == 0x0L) {
            return 5;
        }
        if ((0xFFFFFC0000000000L & n) == 0x0L) {
            return 6;
        }
        if ((0xFFFE000000000000L & n) == 0x0L) {
            return 7;
        }
        if ((0xFF00000000000000L & n) == 0x0L) {
            return 8;
        }
        if ((Long.MIN_VALUE & n) == 0x0L) {
            return 9;
        }
        return 10;
    }
    
    static int b(final int n) {
        return n << 1 ^ n >> 31;
    }
    
    static long b(final long n) {
        return n << 1 ^ n >> 63;
    }
    
    public final void a(final hx hx) {
        this.a.b(hx);
    }
    
    public final void c(int n) {
        while ((n & 0xFFFFFF80) != 0x0) {
            this.a.e((n & 0x7F) | 0x80);
            n >>>= 7;
        }
        this.a.e(n);
    }
    
    public final void c(long n) {
        while ((0xFFFFFFFFFFFFFF80L & n) != 0x0L) {
            this.a.e(((int)n & 0x7F) | 0x80);
            n >>>= 7;
        }
        this.a.e((int)n);
    }
    
    public final void d(final int n) {
        this.a.d(n);
    }
    
    public final void d(final long n) {
        this.a.f(n);
    }
}
