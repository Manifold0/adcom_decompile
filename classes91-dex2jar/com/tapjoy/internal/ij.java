// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class ij
{
    public static ij a;
    public String b;
    public Throwable c;
    private Object[] d;
    
    static {
        ij.a = new ij(null);
    }
    
    public ij(final String s) {
        this(s, null, null);
    }
    
    public ij(final String b, final Object[] d, final Throwable c) {
        this.b = b;
        this.c = c;
        if (c == null) {
            this.d = d;
            return;
        }
        if (d == null || d.length == 0) {
            throw new IllegalStateException("non-sensical empty or null argument array");
        }
        final int n = d.length - 1;
        final Object[] d2 = new Object[n];
        System.arraycopy(d, 0, d2, 0, n);
        this.d = d2;
    }
}
