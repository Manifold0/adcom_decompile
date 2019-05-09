package com.facebook.ads.internal.p025w.p026b;

import android.support.annotation.Nullable;
import android.text.TextUtils;

/* renamed from: com.facebook.ads.internal.w.b.a */
public class C2565a {
    /* renamed from: a */
    private static boolean f6302a = false;
    /* renamed from: b */
    private static boolean f6303b = false;

    @Nullable
    /* renamed from: a */
    public static synchronized String m6615a(String str) {
        String property;
        synchronized (C2565a.class) {
            property = !C2565a.m6616a() ? null : System.getProperty("fb.e2e." + str);
        }
        return property;
    }

    /* renamed from: a */
    public static synchronized boolean m6616a() {
        boolean z;
        synchronized (C2565a.class) {
            if (!f6303b) {
                f6302a = "true".equals(System.getProperty("fb.running_e2e"));
                f6303b = true;
            }
            z = f6302a;
        }
        return z;
    }

    /* renamed from: b */
    public static synchronized boolean m6617b(String str) {
        boolean z;
        synchronized (C2565a.class) {
            z = !TextUtils.isEmpty(C2565a.m6615a(str));
        }
        return z;
    }
}
