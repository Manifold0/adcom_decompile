// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import java.io.File;
import android.util.Log;

public class aq
{
    private static String a;
    private static final boolean b;
    
    static {
        aq.a = "CBTrace";
        b = a();
    }
    
    public static void a(final String s) {
        if (aq.b) {
            Log.i(aq.a, s);
        }
    }
    
    public static void a(final String s, final Object o) {
        if (aq.b) {
            if (o == null) {
                Log.i(aq.a, s + ": null");
                return;
            }
            Log.i(aq.a, s + ": " + o.getClass().getName() + " " + o.hashCode());
        }
    }
    
    public static void a(final String s, final String s2) {
        if (aq.b) {
            Log.i(aq.a, s + ": " + s2);
        }
    }
    
    public static void a(final String s, final boolean b) {
        if (aq.b) {
            Log.i(aq.a, s + ": " + b);
        }
    }
    
    private static boolean a() {
        boolean exists;
        final boolean b = exists = false;
        try {
            if (Log.isLoggable(aq.a, 4)) {
                exists = b;
                if (s.a().c().equals("mounted")) {
                    final File b2 = s.a().b();
                    exists = b;
                    if (b2 != null) {
                        exists = new File(b2, ".chartboost/log_trace").exists();
                    }
                }
            }
            return exists;
        }
        catch (Throwable t) {
            return false;
        }
    }
}
