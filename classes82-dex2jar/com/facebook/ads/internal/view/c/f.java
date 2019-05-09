// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.c;

public enum f
{
    a(0), 
    b(1), 
    c(2);
    
    private int d;
    
    private f(final int d) {
        this.d = d;
    }
    
    public static f a(final int n) {
        final f[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final f f = values[i];
            if (f.d == n) {
                return f;
            }
        }
        return f.b;
    }
    
    public int a() {
        return this.d;
    }
}
