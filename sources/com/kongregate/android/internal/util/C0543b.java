package com.kongregate.android.internal.util;

import android.util.Log;
import java.lang.reflect.Method;

/* renamed from: com.kongregate.android.internal.util.b */
public class C0543b {
    /* renamed from: a */
    private static boolean f704a;
    /* renamed from: b */
    private static Method f705b;

    /* renamed from: a */
    public static boolean m616a() {
        if (!f704a) {
            Class b = C0543b.m617b();
            if (b != null) {
                try {
                    f705b = b.getDeclaredMethod("log", new Class[]{Integer.TYPE, String.class, String.class});
                } catch (Throwable e) {
                    Log.w("KONG", "Exception initializing CrashltyicsHelper:", e);
                }
            }
            f704a = true;
            if (f705b == null) {
                return false;
            }
            return true;
        } else if (f705b != null) {
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: a */
    public static void m611a(int i, String str, String str2) {
        if (C0543b.m616a()) {
            try {
                f705b.invoke(null, new Object[]{Integer.valueOf(i), str, str2});
                return;
            } catch (Exception e) {
                Log.w("KONG", "exception logging to crashlytics");
                return;
            }
        }
        Log.w("KONG", "Crashlytics not available. Be sure to initialize before using.");
    }

    /* renamed from: a */
    public static void m615a(String str, boolean z) {
        if (C0543b.m616a()) {
            C0566n.m776a(C0543b.m617b(), "setBool", new Class[]{String.class, Boolean.TYPE}, str, Boolean.valueOf(z));
            return;
        }
        Log.w("KONG", "Crashlytics not available - setBool");
    }

    /* renamed from: a */
    public static void m614a(String str, String str2) {
        if (C0543b.m616a()) {
            C0566n.m776a(C0543b.m617b(), "setString", new Class[]{String.class, String.class}, str, str2);
            return;
        }
        Log.w("KONG", "Crashlytics not available - setString");
    }

    /* renamed from: a */
    public static void m612a(String str, float f) {
        if (C0543b.m616a()) {
            C0566n.m776a(C0543b.m617b(), "setFloat", new Class[]{String.class, Float.TYPE}, str, Float.valueOf(f));
            return;
        }
        Log.w("KONG", "Crashlytics not available - setFloat");
    }

    /* renamed from: a */
    public static void m613a(String str, int i) {
        if (C0543b.m616a()) {
            C0566n.m776a(C0543b.m617b(), "setInt", new Class[]{String.class, Integer.TYPE}, str, Integer.valueOf(i));
            return;
        }
        Log.w("KONG", "Crashlytics not available - setInt");
    }

    /* renamed from: b */
    private static Class m617b() {
        try {
            return Class.forName("com.crashlytics.android.Crashlytics");
        } catch (Throwable e) {
            Log.w("KONG", "Exception initializing CrashltyicsHelper:", e);
            return null;
        }
    }
}
