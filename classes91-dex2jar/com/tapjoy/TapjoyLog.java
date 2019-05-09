// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import com.tapjoy.internal.fz;
import com.tapjoy.internal.gc;
import android.annotation.TargetApi;
import android.webkit.WebView;
import android.os.Handler;
import android.os.Looper;
import android.os.Build$VERSION;
import android.util.Log;

public class TapjoyLog
{
    private static final String a;
    private static int b;
    private static int c;
    private static int d;
    private static boolean e;
    private static int f;
    
    static {
        a = TapjoyLog.class.getSimpleName();
        TapjoyLog.b = 6;
        TapjoyLog.c = 4;
        TapjoyLog.d = 2;
        TapjoyLog.e = false;
        TapjoyLog.f = TapjoyLog.b;
    }
    
    private static void a(final int n, String string, final String s) {
        string = TapjoyLog.a + ":" + string;
        if (TapjoyLog.f <= n) {
            if (s.length() > 4096) {
                for (int i = 0; i <= s.length() / 4096; ++i) {
                    int length;
                    if ((length = (i + 1) * 4096) > s.length()) {
                        length = s.length();
                    }
                    Log.println(n, string, s.substring(i * 4096, length));
                }
            }
            else {
                Log.println(n, string, s);
            }
        }
    }
    
    @TargetApi(19)
    static void a(final String s, final boolean b) {
        if (!b && TapjoyAppSettings.getInstance() != null && TapjoyAppSettings.getInstance().a != null) {
            d(TapjoyLog.a, "setLoggingLevel -- log setting already persisted");
            return;
        }
        if (s.equals("internal")) {
            TapjoyLog.f = TapjoyLog.d;
            if (Build$VERSION.SDK_INT >= 19) {
                new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
                    @Override
                    public final void run() {
                        TapjoyLog.d(TapjoyLog.a, "Enabling WebView debugging");
                        WebView.setWebContentsDebuggingEnabled(true);
                    }
                });
            }
        }
        else if (s.equals("debug_on")) {
            TapjoyLog.f = TapjoyLog.c;
        }
        else if (s.equals("debug_off")) {
            TapjoyLog.f = TapjoyLog.b;
        }
        else {
            d(TapjoyLog.a, "unrecognized loggingLevel: " + s);
            TapjoyLog.f = TapjoyLog.b;
        }
        d(TapjoyLog.a, "logThreshold=" + TapjoyLog.f);
    }
    
    public static void d(final String s, final String s2) {
        a(3, s, s2);
    }
    
    public static void e(final String s, final TapjoyErrorMessage tapjoyErrorMessage) {
        if (tapjoyErrorMessage != null && (TapjoyLog.f == TapjoyLog.d || tapjoyErrorMessage.getType() != TapjoyErrorMessage.ErrorType.INTERNAL_ERROR)) {
            a(6, s, tapjoyErrorMessage.toString());
        }
    }
    
    public static void e(final String s, final String s2) {
        e(s, new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTERNAL_ERROR, s2));
    }
    
    public static void i(final String s, final String s2) {
        a(4, s, s2);
    }
    
    public static boolean isLoggingEnabled() {
        return TapjoyLog.e;
    }
    
    public static void setDebugEnabled(final boolean b) {
        TapjoyLog.e = b;
        final gc a = gc.a();
        boolean b2;
        if (fz.a != b) {
            fz.a = b;
            if (b) {
                fz.a("The debug mode has been enabled");
            }
            else {
                fz.a("The debug mode has been disabled");
            }
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (b2 && b && a.k) {
            a.i.a();
        }
        if (TapjoyLog.e) {
            a("debug_on", false);
            return;
        }
        a("debug_off", false);
    }
    
    public static void setInternalLogging(final boolean b) {
        if (b) {
            a("internal", true);
        }
    }
    
    public static void v(final String s, final String s2) {
        a(2, s, s2);
    }
    
    public static void w(final String s, final String s2) {
        a(5, s, s2);
    }
}
