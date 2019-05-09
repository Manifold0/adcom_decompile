// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.u;

import com.facebook.ads.internal.protocol.e;
import java.util.Locale;
import com.facebook.ads.internal.protocol.c;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class a
{
    private static Map<String, Long> a;
    private static Map<String, Long> b;
    private static Map<String, String> c;
    
    static {
        com.facebook.ads.internal.u.a.a = new ConcurrentHashMap<String, Long>();
        com.facebook.ads.internal.u.a.b = new ConcurrentHashMap<String, Long>();
        com.facebook.ads.internal.u.a.c = new ConcurrentHashMap<String, String>();
    }
    
    public static void a(final long n, final b b) {
        com.facebook.ads.internal.u.a.a.put(d(b), n);
    }
    
    public static void a(final String s, final b b) {
        com.facebook.ads.internal.u.a.c.put(d(b), s);
    }
    
    public static boolean a(final b b) {
        final String d = d(b);
        if (!com.facebook.ads.internal.u.a.b.containsKey(d)) {
            return false;
        }
        final long longValue = com.facebook.ads.internal.u.a.b.get(d);
        final c c = b.c();
        long longValue2 = 0L;
        if (com.facebook.ads.internal.u.a.a.containsKey(d)) {
            longValue2 = com.facebook.ads.internal.u.a.a.get(d);
        }
        else {
            switch (a$1.a[c.ordinal()]) {
                default: {
                    longValue2 = -1000L;
                    break;
                }
                case 1: {
                    longValue2 = 15000L;
                    break;
                }
                case 2:
                case 3: {
                    longValue2 = -1000L;
                    break;
                }
            }
        }
        return System.currentTimeMillis() - longValue < longValue2;
    }
    
    public static void b(final b b) {
        com.facebook.ads.internal.u.a.b.put(d(b), System.currentTimeMillis());
    }
    
    public static String c(final b b) {
        return com.facebook.ads.internal.u.a.c.get(d(b));
    }
    
    private static String d(final b b) {
        int b2 = 0;
        final Locale us = Locale.US;
        final String b3 = b.b();
        final c c = b.c();
        final e a = b.a();
        int a2;
        if (b.d() == null) {
            a2 = 0;
        }
        else {
            a2 = b.d().a();
        }
        if (b.d() != null) {
            b2 = b.d().b();
        }
        return String.format(us, "%s:%s:%s:%d:%d:%d", b3, c, a, a2, b2, b.e());
    }
}
