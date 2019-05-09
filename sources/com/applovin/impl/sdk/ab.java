package com.applovin.impl.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.view.Display;
import android.view.WindowManager;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.tonyodev.fetch.FetchService;

public class ab {
    /* renamed from: a */
    static void m2196a() {
        try {
            if (VERSION.SDK_INT >= 9) {
                StrictMode.setThreadPolicy(new Builder().permitAll().build());
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public static boolean m2197a(Context context) {
        boolean z = false;
        try {
            z = gd.m2945a(context.getPackageManager().getActivityInfo(new ComponentName(context, AppLovinInterstitialActivity.class.getCanonicalName()), 0).configChanges, 1024);
        } catch (Throwable th) {
        }
        return z;
    }

    /* renamed from: a */
    public static boolean m2198a(Class<?> cls, Context context) {
        return context.getPackageManager().resolveActivity(new Intent(context, cls), 0) != null;
    }

    /* renamed from: a */
    public static boolean m2199a(String str, Context context) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    /* renamed from: b */
    public static boolean m2200b() {
        return VERSION.SDK_INT >= 11;
    }

    /* renamed from: b */
    public static boolean m2201b(Context context) {
        boolean z = false;
        try {
            z = gd.m2945a(context.getPackageManager().getActivityInfo(new ComponentName(context, AppLovinInterstitialActivity.class.getCanonicalName()), 0).configChanges, 128);
        } catch (Throwable th) {
        }
        return z;
    }

    /* renamed from: c */
    public static Point m2202c(Context context) {
        Point point = new Point();
        point.x = FetchService.QUERY_SINGLE;
        point.y = FetchService.ACTION_LOGGING;
        try {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            if (VERSION.SDK_INT >= 13) {
                defaultDisplay.getSize(point);
            } else {
                point.x = defaultDisplay.getWidth();
                point.y = defaultDisplay.getHeight();
            }
        } catch (Throwable th) {
        }
        return point;
    }

    /* renamed from: c */
    public static boolean m2203c() {
        return VERSION.SDK_INT >= 14;
    }

    /* renamed from: d */
    public static boolean m2204d() {
        return VERSION.SDK_INT >= 16;
    }

    /* renamed from: e */
    public static boolean m2205e() {
        return VERSION.SDK_INT >= 17;
    }

    /* renamed from: f */
    public static boolean m2206f() {
        return VERSION.SDK_INT >= 19;
    }

    /* renamed from: g */
    public static boolean m2207g() {
        return VERSION.SDK_INT >= 21;
    }

    /* renamed from: h */
    public static boolean m2208h() {
        return VERSION.SDK_INT >= 23;
    }
}
