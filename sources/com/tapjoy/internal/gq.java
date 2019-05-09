package com.tapjoy.internal;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class gq {
    /* renamed from: a */
    public static final ScheduledExecutorService f7972a = Executors.newScheduledThreadPool(1);
    /* renamed from: b */
    public static final CountDownLatch f7973b = new CountDownLatch(1);
    /* renamed from: c */
    public static final CountDownLatch f7974c = new CountDownLatch(1);
    /* renamed from: d */
    private static final Runnable f7975d = new C29581();
    /* renamed from: e */
    private static String f7976e;
    /* renamed from: f */
    private static boolean f7977f;

    /* renamed from: com.tapjoy.internal.gq$1 */
    static class C29581 implements Runnable {
        C29581() {
        }

        public final void run() {
            if (C2999y.m8234c()) {
                gq.f7973b.countDown();
            } else if (C2999y.m8232a()) {
                gq.f7973b.countDown();
            } else {
                gq.f7972a.schedule(this, 300, TimeUnit.SECONDS);
            }
        }
    }

    /* renamed from: a */
    public static void m7983a() {
        f7972a.execute(f7975d);
    }

    /* renamed from: a */
    public static void m7984a(String str, boolean z) {
        f7976e = str;
        f7977f = z;
        f7974c.countDown();
    }

    /* renamed from: b */
    public static String m7985b() {
        return f7976e;
    }

    /* renamed from: c */
    public static boolean m7986c() {
        return f7977f;
    }
}
