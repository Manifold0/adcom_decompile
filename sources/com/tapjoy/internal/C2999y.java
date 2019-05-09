package com.tapjoy.internal;

import android.os.SystemClock;

/* renamed from: com.tapjoy.internal.y */
public final class C2999y {
    /* renamed from: a */
    private static String f8240a = "pool.ntp.org";
    /* renamed from: b */
    private static long f8241b = 20000;
    /* renamed from: c */
    private static volatile boolean f8242c = false;
    /* renamed from: d */
    private static volatile String f8243d;
    /* renamed from: e */
    private static volatile long f8244e;
    /* renamed from: f */
    private static volatile long f8245f;
    /* renamed from: g */
    private static volatile long f8246g;
    /* renamed from: h */
    private static volatile long f8247h;
    /* renamed from: i */
    private static volatile long f8248i;

    static {
        C2999y.m8231a(false, "System", System.currentTimeMillis(), SystemClock.elapsedRealtime(), Long.MAX_VALUE);
    }

    /* renamed from: a */
    private static synchronized void m8231a(boolean z, String str, long j, long j2, long j3) {
        synchronized (C2999y.class) {
            f8242c = z;
            f8243d = str;
            f8244e = j;
            f8245f = j2;
            f8246g = j3;
            f8247h = f8244e - f8245f;
            f8248i = (SystemClock.elapsedRealtime() + f8247h) - System.currentTimeMillis();
        }
    }

    /* renamed from: a */
    public static boolean m8232a() {
        String str = f8240a;
        long j = f8241b;
        fn fnVar = new fn();
        if (!fnVar.m7772a(str, (int) j)) {
            return false;
        }
        C2999y.m8231a(true, "SNTP", fnVar.f7792a, fnVar.f7793b, fnVar.f7794c / 2);
        return true;
    }

    /* renamed from: b */
    public static long m8233b() {
        return SystemClock.elapsedRealtime() + f8247h;
    }

    /* renamed from: a */
    public static long m8230a(long j) {
        return f8247h + j;
    }

    /* renamed from: c */
    public static boolean m8234c() {
        return f8242c;
    }
}
