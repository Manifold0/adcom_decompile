package com.chartboost.sdk.impl;

import android.util.Log;
import java.io.File;

public class aq {
    /* renamed from: a */
    private static String f3065a = "CBTrace";
    /* renamed from: b */
    private static final boolean f3066b = m3411a();

    /* renamed from: a */
    private static boolean m3411a() {
        boolean z = false;
        try {
            if (Log.isLoggable(f3065a, 4) && C1454s.m3627a().m3633c().equals("mounted")) {
                File b = C1454s.m3627a().m3632b();
                if (b != null) {
                    z = new File(b, ".chartboost/log_trace").exists();
                }
            }
        } catch (Throwable th) {
        }
        return z;
    }

    /* renamed from: a */
    public static void m3409a(String str, String str2) {
        if (f3066b) {
            Log.i(f3065a, str + ": " + str2);
        }
    }

    /* renamed from: a */
    public static void m3410a(String str, boolean z) {
        if (f3066b) {
            Log.i(f3065a, str + ": " + z);
        }
    }

    /* renamed from: a */
    public static void m3408a(String str, Object obj) {
        if (!f3066b) {
            return;
        }
        if (obj != null) {
            Log.i(f3065a, str + ": " + obj.getClass().getName() + " " + obj.hashCode());
        } else {
            Log.i(f3065a, str + ": null");
        }
    }

    /* renamed from: a */
    public static void m3407a(String str) {
        if (f3066b) {
            Log.i(f3065a, str);
        }
    }
}
