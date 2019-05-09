// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.b;

final class j
{
    static <T> T a(final T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }
    
    static void a(final boolean b, final String s) {
        if (!b) {
            throw new IllegalArgumentException(s);
        }
    }
}
