// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class fl
{
    public static final fl a;
    public final long b;
    public final long c;
    public final double d;
    public long e;
    private final long f;
    
    static {
        a = new fl(0L, 0L, 0L, 0.0);
    }
    
    public fl(final long n, final long b, final long c, final double d) {
        this.f = n;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = n;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final fl fl = (fl)o;
            if (this.f != fl.f || this.b != fl.b || this.c != fl.c || this.d != fl.d || this.e != fl.e) {
                return false;
            }
        }
        return true;
    }
}
