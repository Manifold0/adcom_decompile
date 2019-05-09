// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters.b;

public enum p
{
    a, 
    b, 
    c;
    
    public static p a(final String s) {
        try {
            return valueOf(s);
        }
        catch (IllegalArgumentException ex) {
            return p.c;
        }
    }
}
