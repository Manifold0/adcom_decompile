// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class fx
{
    public static String a(String s) {
        if (s != null && s.length() != 0) {
            final String trim = s.trim();
            if (trim != null) {
                s = trim;
                if (trim.length() != 0) {
                    return s;
                }
            }
            return null;
        }
        s = null;
        return s;
    }
    
    public static String a(String trim, final String s, final String s2) {
        if (trim == null) {
            fz.a(s, s2, "must not be null");
            return null;
        }
        if (trim.length() == 0) {
            fz.a(s, s2, "must not be empty");
            return null;
        }
        trim = trim.trim();
        if (trim.length() == 0) {
            fz.a(s, s2, "must not be blank");
            return null;
        }
        return trim;
    }
    
    public static String b(String trim) {
        if (trim != null && trim.length() != 0) {
            trim = trim.trim();
            if (trim.length() != 0) {
                return trim;
            }
        }
        return null;
    }
}
