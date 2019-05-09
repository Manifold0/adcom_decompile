// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class ct
{
    public static String a(final String s) {
        String s2 = s;
        if (s == null) {
            s2 = "";
        }
        return s2;
    }
    
    public static String b(final String s) {
        String s2 = s;
        if (c(s)) {
            s2 = null;
        }
        return s2;
    }
    
    public static boolean c(final String s) {
        return s == null || s.length() == 0;
    }
}
