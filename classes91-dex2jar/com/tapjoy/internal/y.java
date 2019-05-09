// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.os.SystemClock;

public final class y
{
    private static String a;
    private static long b;
    private static volatile boolean c;
    private static volatile String d;
    private static volatile long e;
    private static volatile long f;
    private static volatile long g;
    private static volatile long h;
    private static volatile long i;
    
    static {
        y.a = "pool.ntp.org";
        y.b = 20000L;
        a(y.c = false, "System", System.currentTimeMillis(), SystemClock.elapsedRealtime(), Long.MAX_VALUE);
    }
    
    public static long a(final long n) {
        return y.h + n;
    }
    
    private static void a(final boolean c, final String d, final long e, final long f, final long g) {
        synchronized (y.class) {
            y.c = c;
            y.d = d;
            y.e = e;
            y.f = f;
            y.g = g;
            y.h = y.e - y.f;
            y.i = SystemClock.elapsedRealtime() + y.h - System.currentTimeMillis();
        }
    }
    
    public static boolean a() {
        final String a = y.a;
        final long b = y.b;
        final fn fn = new fn();
        if (fn.a(a, (int)b)) {
            a(true, "SNTP", fn.a, fn.b, fn.c / 2L);
            return true;
        }
        return false;
    }
    
    public static long b() {
        return SystemClock.elapsedRealtime() + y.h;
    }
    
    public static boolean c() {
        return y.c;
    }
}
