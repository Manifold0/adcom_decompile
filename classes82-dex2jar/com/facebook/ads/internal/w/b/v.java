// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import android.util.Log;
import java.util.Locale;
import android.support.annotation.VisibleForTesting;

public class v
{
    @VisibleForTesting
    public static a a;
    
    public static long a() {
        if (v.a != null) {
            return v.a.a();
        }
        return System.currentTimeMillis();
    }
    
    public static String a(final double n) {
        try {
            return String.format(Locale.US, "%.3f", n);
        }
        catch (Exception ex) {
            Log.e(v.class.getSimpleName(), "Can't format time.", (Throwable)ex);
            return "1.234";
        }
    }
    
    public static String a(final long n) {
        return Long.toString(n);
    }
    
    @Deprecated
    public static String b(final long n) {
        return a(n / 1000.0);
    }
    
    public interface a
    {
        long a();
    }
}
