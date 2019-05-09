package com.facebook.ads.internal.p025w.p026b;

import android.support.annotation.VisibleForTesting;
import android.util.Log;
import java.util.Locale;

/* renamed from: com.facebook.ads.internal.w.b.v */
public class C2597v {
    @VisibleForTesting
    /* renamed from: a */
    public static C2596a f6395a;

    /* renamed from: com.facebook.ads.internal.w.b.v$a */
    public interface C2596a {
        /* renamed from: a */
        long m6665a();
    }

    /* renamed from: a */
    public static long m6666a() {
        return f6395a != null ? f6395a.m6665a() : System.currentTimeMillis();
    }

    /* renamed from: a */
    public static String m6667a(double d) {
        try {
            return String.format(Locale.US, "%.3f", new Object[]{Double.valueOf(d)});
        } catch (Throwable e) {
            Log.e(C2597v.class.getSimpleName(), "Can't format time.", e);
            return "1.234";
        }
    }

    /* renamed from: a */
    public static String m6668a(long j) {
        return Long.toString(j);
    }

    @Deprecated
    /* renamed from: b */
    public static String m6669b(long j) {
        return C2597v.m6667a(((double) j) / 1000.0d);
    }
}
