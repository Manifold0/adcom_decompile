package com.facebook.ads.internal.p025w.p026b;

import java.util.UUID;

/* renamed from: com.facebook.ads.internal.w.b.o */
public class C2585o {
    /* renamed from: a */
    private static final String f6365a = C2585o.class.getSimpleName();
    /* renamed from: b */
    private static volatile boolean f6366b = false;
    /* renamed from: c */
    private static double f6367c;
    /* renamed from: d */
    private static String f6368d;

    /* renamed from: a */
    public static void m6651a() {
        if (!f6366b) {
            synchronized (f6365a) {
                if (!f6366b) {
                    f6366b = true;
                    f6367c = ((double) System.currentTimeMillis()) / 1000.0d;
                    f6368d = UUID.randomUUID().toString();
                }
            }
        }
    }

    /* renamed from: b */
    public static double m6652b() {
        return f6367c;
    }

    /* renamed from: c */
    public static String m6653c() {
        return f6368d;
    }
}
