package com.tapjoy.internal;

import android.os.Handler;
import android.os.Looper;

/* renamed from: com.tapjoy.internal.x */
public final class C2998x {
    /* renamed from: a */
    private static Handler f8239a;

    /* renamed from: a */
    public static synchronized Handler m8228a() {
        Handler handler;
        synchronized (C2998x.class) {
            if (f8239a == null) {
                f8239a = new Handler(Looper.getMainLooper());
            }
            handler = f8239a;
        }
        return handler;
    }

    /* renamed from: a */
    public static bf m8229a(final Handler handler) {
        return new bf() {
            /* renamed from: a */
            public final boolean mo6287a(Runnable runnable) {
                return handler.post(runnable);
            }
        };
    }
}
