// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import android.util.Log;

class p
{
    private static String a(final String s) {
        return "Moat" + s;
    }
    
    static void a(final int n, final String s, final Object o, final String s2) {
        if (w.a().b) {
            if (o != null) {
                Log.println(n, a(s), String.format("id = %s, message = %s", o.hashCode(), s2));
                return;
            }
            Log.println(n, a(s), String.format("message = %s", s2));
        }
    }
    
    static void a(final String s, final Object o, final String s2, final Throwable t) {
        if (w.a().b) {
            Log.e(a(s), String.format("id = %s, message = %s", o.hashCode(), s2), t);
        }
    }
    
    static void a(final String s, final String s2) {
        if (!w.a().b && ((k)MoatAnalytics.getInstance()).a) {
            int n = 2;
            if (s.equals("[ERROR] ")) {
                n = 6;
            }
            Log.println(n, "MoatAnalytics", s + s2);
        }
    }
    
    static void b(final int n, String value, final Object o, final String s) {
        if (w.a().c) {
            final String a = a((String)value);
            if (o == null) {
                value = "null";
            }
            else {
                value = o.hashCode();
            }
            Log.println(n, a, String.format("id = %s, message = %s", value, s));
        }
    }
}
