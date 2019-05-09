package com.ironsource.sdk.utils;

import android.text.TextUtils;
import android.util.Log;
import com.ironsource.sdk.data.SSAEnums.DebugMode;

public class Logger {
    private static boolean enableLogging;

    public static void enableLogging(int mode) {
        if (DebugMode.MODE_0.getValue() == mode) {
            enableLogging = false;
        } else {
            enableLogging = true;
        }
    }

    /* renamed from: i */
    public static void m1212i(String tag, String message) {
        if (enableLogging) {
            Log.i(tag, message);
        }
    }

    /* renamed from: i */
    public static void m1213i(String tag, String message, Throwable tr) {
        if (enableLogging && !TextUtils.isEmpty(message)) {
            Log.i(tag, message, tr);
        }
    }

    /* renamed from: e */
    public static void m1210e(String tag, String message) {
        if (enableLogging) {
            Log.e(tag, message);
        }
    }

    /* renamed from: e */
    public static void m1211e(String tag, String message, Throwable tr) {
        if (enableLogging) {
            Log.e(tag, message, tr);
        }
    }

    /* renamed from: w */
    public static void m1216w(String tag, String message) {
        if (enableLogging) {
            Log.w(tag, message);
        }
    }

    /* renamed from: w */
    public static void m1217w(String tag, String message, Throwable tr) {
        if (enableLogging) {
            Log.w(tag, message, tr);
        }
    }

    /* renamed from: d */
    public static void m1208d(String tag, String message) {
        if (enableLogging) {
            Log.d(tag, message);
        }
    }

    /* renamed from: d */
    public static void m1209d(String tag, String message, Throwable tr) {
        if (enableLogging) {
            Log.d(tag, message, tr);
        }
    }

    /* renamed from: v */
    public static void m1214v(String tag, String message) {
        if (enableLogging) {
            Log.v(tag, message);
        }
    }

    /* renamed from: v */
    public static void m1215v(String tag, String message, Throwable tr) {
        if (enableLogging) {
            Log.v(tag, message, tr);
        }
    }
}
