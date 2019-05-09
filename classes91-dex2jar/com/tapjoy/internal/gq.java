// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledExecutorService;

public final class gq
{
    public static final ScheduledExecutorService a;
    public static final CountDownLatch b;
    public static final CountDownLatch c;
    private static final Runnable d;
    private static String e;
    private static boolean f;
    
    static {
        a = Executors.newScheduledThreadPool(1);
        b = new CountDownLatch(1);
        d = new Runnable() {
            @Override
            public final void run() {
                if (y.c()) {
                    gq.b.countDown();
                    return;
                }
                if (y.a()) {
                    gq.b.countDown();
                    return;
                }
                gq.a.schedule(this, 300L, TimeUnit.SECONDS);
            }
        };
        c = new CountDownLatch(1);
    }
    
    public static void a() {
        gq.a.execute(gq.d);
    }
    
    public static void a(final String e, final boolean f) {
        gq.e = e;
        gq.f = f;
        gq.c.countDown();
    }
    
    public static String b() {
        return gq.e;
    }
    
    public static boolean c() {
        return gq.f;
    }
}
