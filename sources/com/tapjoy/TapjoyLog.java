package com.tapjoy;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.WebView;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.internal.fz;
import com.tapjoy.internal.gc;

public class TapjoyLog {
    /* renamed from: a */
    private static final String f7098a = TapjoyLog.class.getSimpleName();
    /* renamed from: b */
    private static int f7099b = 6;
    /* renamed from: c */
    private static int f7100c = 4;
    /* renamed from: d */
    private static int f7101d = 2;
    /* renamed from: e */
    private static boolean f7102e = false;
    /* renamed from: f */
    private static int f7103f = f7099b;

    /* renamed from: com.tapjoy.TapjoyLog$1 */
    static class C28211 implements Runnable {
        C28211() {
        }

        public final void run() {
            TapjoyLog.m7126d(TapjoyLog.f7098a, "Enabling WebView debugging");
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    public static void setDebugEnabled(boolean enable) {
        boolean z;
        f7102e = enable;
        gc a = gc.m7831a();
        if (fz.f7823a != enable) {
            fz.f7823a = enable;
            if (enable) {
                fz.m7811a("The debug mode has been enabled");
            } else {
                fz.m7811a("The debug mode has been disabled");
            }
            z = true;
        } else {
            z = false;
        }
        if (z && enable && a.f7859k) {
            a.f7857i.m7821a();
        }
        if (f7102e) {
            m7125a(TapjoyConstants.LOG_LEVEL_DEBUG_ON, false);
        } else {
            m7125a(TapjoyConstants.LOG_LEVEL_DEBUG_OFF, false);
        }
    }

    public static void setInternalLogging(boolean isInternalLogging) {
        if (isInternalLogging) {
            m7125a(TapjoyConstants.LOG_LEVEL_INTERNAL, true);
        }
    }

    @TargetApi(19)
    /* renamed from: a */
    static void m7125a(String str, boolean z) {
        if (z || TapjoyAppSettings.getInstance() == null || TapjoyAppSettings.getInstance().f7003a == null) {
            if (str.equals(TapjoyConstants.LOG_LEVEL_INTERNAL)) {
                f7103f = f7101d;
                if (VERSION.SDK_INT >= 19) {
                    new Handler(Looper.getMainLooper()).post(new C28211());
                }
            } else if (str.equals(TapjoyConstants.LOG_LEVEL_DEBUG_ON)) {
                f7103f = f7100c;
            } else if (str.equals(TapjoyConstants.LOG_LEVEL_DEBUG_OFF)) {
                f7103f = f7099b;
            } else {
                m7126d(f7098a, "unrecognized loggingLevel: " + str);
                f7103f = f7099b;
            }
            m7126d(f7098a, "logThreshold=" + f7103f);
            return;
        }
        m7126d(f7098a, "setLoggingLevel -- log setting already persisted");
    }

    public static boolean isLoggingEnabled() {
        return f7102e;
    }

    /* renamed from: i */
    public static void m7129i(String tag, String msg) {
        m7124a(4, tag, msg);
    }

    /* renamed from: e */
    public static void m7128e(String tag, String msg) {
        m7127e(tag, new TapjoyErrorMessage(ErrorType.INTERNAL_ERROR, msg));
    }

    /* renamed from: e */
    public static void m7127e(String tag, TapjoyErrorMessage error) {
        if (error == null) {
            return;
        }
        if (f7103f == f7101d || error.getType() != ErrorType.INTERNAL_ERROR) {
            m7124a(6, tag, error.toString());
        }
    }

    /* renamed from: w */
    public static void m7131w(String tag, String msg) {
        m7124a(5, tag, msg);
    }

    /* renamed from: d */
    public static void m7126d(String tag, String msg) {
        m7124a(3, tag, msg);
    }

    /* renamed from: v */
    public static void m7130v(String tag, String msg) {
        m7124a(2, tag, msg);
    }

    /* renamed from: a */
    private static void m7124a(int i, String str, String str2) {
        String str3 = f7098a + ":" + str;
        if (f7103f > i) {
            return;
        }
        if (str2.length() > 4096) {
            for (int i2 = 0; i2 <= str2.length() / 4096; i2++) {
                int i3 = i2 * 4096;
                int i4 = (i2 + 1) * 4096;
                if (i4 > str2.length()) {
                    i4 = str2.length();
                }
                Log.println(i, str3, str2.substring(i3, i4));
            }
            return;
        }
        Log.println(i, str3, str2);
    }
}
