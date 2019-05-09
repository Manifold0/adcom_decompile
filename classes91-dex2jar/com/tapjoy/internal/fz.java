// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.os.Looper;

public final class fz
{
    public static boolean a;
    
    public static void a(final String s) {
        if (fz.a) {
            ac.a(4, "Tapjoy", s, null);
        }
    }
    
    public static void a(final String s, final String s2, final String s3) {
        if (fz.a) {
            ac.a("Tapjoy", "{}: {} {}", s, s2, s3);
        }
    }
    
    public static void a(final String s, final Object... array) {
        if (fz.a) {
            ac.a(4, "Tapjoy", s, array);
        }
    }
    
    public static boolean a(final Object o, final String s) {
        if (o == null) {
            if (fz.a) {
                b(s);
            }
            return false;
        }
        return true;
    }
    
    public static boolean a(final boolean b, final String s) {
        if (fz.a && !b) {
            b(s);
            throw new IllegalStateException(s);
        }
        return b;
    }
    
    public static void b(final String s) {
        if (fz.a) {
            ac.a(6, "Tapjoy", s, null);
        }
    }
    
    public static void b(final String s, final Object... array) {
        if (fz.a) {
            ac.a("Tapjoy", s, array);
        }
    }
    
    public static boolean c(final String s) {
        return a(Looper.myLooper() == Looper.getMainLooper(), s + ": Must be called on the main/ui thread");
    }
}
