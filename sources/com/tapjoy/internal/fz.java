package com.tapjoy.internal;

import android.os.Looper;

public final class fz {
    /* renamed from: a */
    public static boolean f7823a;

    /* renamed from: a */
    public static void m7811a(String str) {
        if (f7823a) {
            ac.m7143a(4, "Tapjoy", str, null);
        }
    }

    /* renamed from: a */
    public static void m7813a(String str, Object... objArr) {
        if (f7823a) {
            ac.m7144a(4, "Tapjoy", str, objArr);
        }
    }

    /* renamed from: b */
    public static void m7816b(String str) {
        if (f7823a) {
            ac.m7143a(6, "Tapjoy", str, null);
        }
    }

    /* renamed from: b */
    public static void m7817b(String str, Object... objArr) {
        if (f7823a) {
            ac.m7145a("Tapjoy", str, objArr);
        }
    }

    /* renamed from: a */
    public static void m7812a(String str, String str2, String str3) {
        if (f7823a) {
            ac.m7145a("Tapjoy", "{}: {} {}", str, str2, str3);
        }
    }

    /* renamed from: a */
    public static boolean m7814a(Object obj, String str) {
        if (obj != null) {
            return true;
        }
        if (f7823a) {
            m7816b(str);
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m7815a(boolean z, String str) {
        if (!f7823a || z) {
            return z;
        }
        m7816b(str);
        throw new IllegalStateException(str);
    }

    /* renamed from: c */
    public static boolean m7818c(String str) {
        return m7815a(Looper.myLooper() == Looper.getMainLooper(), str + ": Must be called on the main/ui thread");
    }
}
