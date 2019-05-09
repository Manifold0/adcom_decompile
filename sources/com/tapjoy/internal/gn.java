package com.tapjoy.internal;

import android.os.SystemClock;

public abstract class gn {
    /* renamed from: a */
    protected static C2954a f7713a;
    /* renamed from: b */
    private static gn f7714b;

    /* renamed from: com.tapjoy.internal.gn$a */
    public static class C2954a {
        /* renamed from: a */
        public final String f7957a;
        /* renamed from: b */
        public final String f7958b;
        /* renamed from: c */
        public final long f7959c = SystemClock.elapsedRealtime();
        /* renamed from: d */
        public final el f7960d = new el(60000);

        public C2954a(String str, String str2) {
            this.f7957a = str;
            this.f7958b = str2;
        }
    }

    /* renamed from: a */
    public abstract void mo6276a(C2954a c2954a);

    /* renamed from: b */
    public abstract boolean mo6277b();

    /* renamed from: a */
    protected static void m7698a(gn gnVar) {
        synchronized (gn.class) {
            f7714b = gnVar;
            C2954a c2954a = f7713a;
            if (c2954a != null) {
                f7713a = null;
                gnVar.mo6276a(c2954a);
            }
        }
    }

    /* renamed from: a */
    public static void m7699a(String str, String str2) {
        synchronized (gn.class) {
            C2954a c2954a = new C2954a(str, str2);
            if (f7714b != null) {
                f7713a = null;
                f7714b.mo6276a(c2954a);
            } else {
                f7713a = c2954a;
            }
        }
    }

    /* renamed from: c */
    public static boolean m7700c() {
        if (f7714b != null && f7714b.mo6277b()) {
            return true;
        }
        C2954a c2954a = f7713a;
        if (c2954a == null || c2954a.f7960d.m7649a()) {
            return false;
        }
        return true;
    }
}
