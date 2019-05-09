package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.chartboost.sdk.C1405g;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.C1375d;
import com.chartboost.sdk.Libraries.C1377e;
import com.chartboost.sdk.Libraries.C1382i;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C1390e;
import com.chartboost.sdk.Tracking.C1391a;
import com.facebook.places.model.PlaceFields;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

public class ap {
    /* renamed from: a */
    final C1375d f3040a;
    /* renamed from: b */
    final ai f3041b;
    /* renamed from: c */
    final AtomicReference<C1390e> f3042c;
    /* renamed from: d */
    final SharedPreferences f3043d;
    /* renamed from: e */
    final C1382i f3044e;
    /* renamed from: f */
    final String f3045f;
    /* renamed from: g */
    final String f3046g;
    /* renamed from: h */
    final String f3047h;
    /* renamed from: i */
    final String f3048i;
    /* renamed from: j */
    String f3049j;
    /* renamed from: k */
    String f3050k;
    /* renamed from: l */
    final String f3051l;
    /* renamed from: m */
    final Integer f3052m;
    /* renamed from: n */
    final Integer f3053n;
    /* renamed from: o */
    final Integer f3054o;
    /* renamed from: p */
    final Integer f3055p;
    /* renamed from: q */
    final String f3056q;
    /* renamed from: r */
    final Float f3057r;
    /* renamed from: s */
    final String f3058s;
    /* renamed from: t */
    final String f3059t;
    /* renamed from: u */
    final String f3060u;
    /* renamed from: v */
    final JSONObject f3061v;
    /* renamed from: w */
    final boolean f3062w;
    /* renamed from: x */
    final String f3063x;
    /* renamed from: y */
    final Integer f3064y;

    public ap(Context context, String str, C1375d c1375d, ai aiVar, AtomicReference<C1390e> atomicReference, SharedPreferences sharedPreferences, C1382i c1382i) {
        JSONObject jSONObject;
        int width;
        int height;
        int i;
        int i2;
        this.f3040a = c1375d;
        this.f3041b = aiVar;
        this.f3042c = atomicReference;
        this.f3043d = sharedPreferences;
        this.f3044e = c1382i;
        this.f3058s = str;
        if ("sdk".equals(Build.PRODUCT) || "google_sdk".equals(Build.PRODUCT) || (Build.MANUFACTURER != null && Build.MANUFACTURER.contains("Genymotion"))) {
            this.f3045f = "Android Simulator";
        } else {
            this.f3045f = Build.MODEL;
        }
        this.f3059t = Build.MANUFACTURER + " " + Build.MODEL;
        this.f3060u = ar.m3412a(context);
        this.f3046g = "Android " + VERSION.RELEASE;
        this.f3047h = Locale.getDefault().getCountry();
        this.f3048i = Locale.getDefault().getLanguage();
        this.f3051l = "7.3.0";
        this.f3057r = Float.valueOf(context.getResources().getDisplayMetrics().density);
        try {
            String packageName = context.getPackageName();
            this.f3049j = context.getPackageManager().getPackageInfo(packageName, 128).versionName;
            this.f3050k = packageName;
        } catch (Throwable e) {
            CBLogging.m3098a("RequestBody", "Exception raised getting package mager object", e);
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
        if (telephonyManager == null || telephonyManager.getPhoneType() == 0 || telephonyManager.getSimState() != 5) {
            jSONObject = new JSONObject();
        } else {
            String simOperator;
            String str2 = null;
            try {
                simOperator = telephonyManager.getSimOperator();
            } catch (Exception e2) {
                C1391a.m3206a(Chartboost.class, "Unable to retrieve sim operator information", e2);
                simOperator = str2;
            }
            Object obj = null;
            Object obj2 = null;
            if (!(simOperator == null || TextUtils.isEmpty(simOperator))) {
                obj = simOperator.substring(0, 3);
                obj2 = simOperator.substring(3);
            }
            jSONObject = C1377e.m3130a(C1377e.m3128a("carrier-name", telephonyManager.getNetworkOperatorName()), C1377e.m3128a("mobile-country-code", obj), C1377e.m3128a("mobile-network-code", obj2), C1377e.m3128a("iso-country-code", telephonyManager.getNetworkCountryIso()), C1377e.m3128a("phone-type", Integer.valueOf(telephonyManager.getPhoneType())));
        }
        this.f3061v = jSONObject;
        this.f3062w = CBUtility.m3117c();
        this.f3063x = CBUtility.m3118d();
        this.f3064y = ai.m3372d(context);
        try {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                Rect rect = new Rect();
                activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                width = rect.width();
                height = rect.height();
                i = width;
            } else {
                height = 0;
                i = 0;
            }
            width = height;
            i2 = i;
        } catch (Throwable e3) {
            Throwable th = e3;
            height = 0;
            CBLogging.m3100b("RequestBody", "Exception getting activity size", th);
            width = 0;
            i2 = height;
        }
        DisplayMetrics displayMetrics = (DisplayMetrics) C1405g.m3317a().m3318a(new DisplayMetrics());
        displayMetrics.setTo(context.getResources().getDisplayMetrics());
        if (VERSION.SDK_INT >= 17) {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager != null) {
                windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
            }
        }
        int i3 = displayMetrics.widthPixels;
        i = displayMetrics.heightPixels;
        this.f3054o = Integer.valueOf(i3);
        this.f3055p = Integer.valueOf(i);
        this.f3056q = "" + displayMetrics.densityDpi;
        if (i2 <= 0 || i2 > i3) {
            i2 = i3;
        }
        if (width <= 0 || width > i) {
            width = i;
        }
        this.f3052m = Integer.valueOf(i2);
        this.f3053n = Integer.valueOf(width);
    }
}
