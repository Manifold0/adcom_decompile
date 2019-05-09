package com.chartboost.sdk.Libraries;

import android.util.Log;

public final class CBLogging {
    /* renamed from: a */
    public static Level f2675a = Level.INTEGRATION;

    public enum Level {
        NONE,
        INTEGRATION,
        ALL
    }

    /* renamed from: a */
    public static void m3097a(String str, String str2) {
        if (f2675a == Level.ALL) {
            Log.d(str, str2);
        }
    }

    /* renamed from: b */
    public static void m3099b(String str, String str2) {
        if (f2675a == Level.ALL) {
            Log.e(str, str2);
        }
    }

    /* renamed from: a */
    public static void m3098a(String str, String str2, Throwable th) {
        if (f2675a == Level.ALL) {
            Log.e(str, str2, th);
        }
    }

    /* renamed from: c */
    public static void m3101c(String str, String str2) {
        if (f2675a == Level.ALL) {
            Log.v(str, str2);
        }
    }

    /* renamed from: b */
    public static void m3100b(String str, String str2, Throwable th) {
        if (f2675a == Level.ALL) {
            Log.v(str, str2, th);
        }
    }

    /* renamed from: d */
    public static void m3103d(String str, String str2) {
        if (f2675a == Level.ALL) {
            Log.w(str, str2);
        }
    }

    /* renamed from: c */
    public static void m3102c(String str, String str2, Throwable th) {
        if (f2675a == Level.ALL) {
            Log.w(str, str2, th);
        }
    }

    /* renamed from: e */
    public static void m3104e(String str, String str2) {
        if (f2675a == Level.ALL) {
            Log.i(str, str2);
        }
    }
}
