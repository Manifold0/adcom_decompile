package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.C1390e;
import com.chartboost.sdk.Tracking.C1391a;
import com.chartboost.sdk.impl.C1454s;
import com.facebook.internal.AnalyticsEvents;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.b */
public final class C1392b {
    /* renamed from: a */
    static void m3233a(String str) {
        if (C1410i.f2927d == null) {
            CBLogging.m3099b("CBConfig", "Set a valid CBFramework first");
        } else if (TextUtils.isEmpty(str)) {
            CBLogging.m3099b("CBConfig", "Invalid Version String");
        } else {
            C1410i.f2925b = str;
        }
    }

    /* renamed from: a */
    public static boolean m3237a(AtomicReference<C1390e> atomicReference, JSONObject jSONObject, SharedPreferences sharedPreferences) {
        try {
            atomicReference.set(new C1390e(jSONObject));
            return true;
        } catch (Exception e) {
            C1391a.m3206a(C1392b.class, "updateConfig", e);
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m3234a() {
        if (C1392b.m3238b() && C1392b.m3240c()) {
            return true;
        }
        return false;
    }

    /* renamed from: c */
    private static boolean m3240c() {
        C1409h a = C1409h.m3324a();
        if (a == null) {
            return false;
        }
        if (a.f2918q.f2841d != null) {
            return true;
        }
        try {
            throw new Exception("Chartboost Weak Activity reference is null");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    static boolean m3235a(Activity activity) {
        if (activity != null) {
            return true;
        }
        try {
            throw new Exception("Invalid activity context: Host Activity object is null, Please send a valid activity object");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    static boolean m3238b() {
        try {
            if (C1409h.m3324a() == null) {
                throw new Exception("SDK Initialization error. SDK seems to be not initialized properly, check for any integration issues");
            } else if (C1410i.f2936m == null) {
                throw new Exception("SDK Initialization error. Activity context seems to be not initialized properly, host activity or application context is being sent as null");
            } else if (TextUtils.isEmpty(C1410i.f2934k)) {
                throw new Exception("SDK Initialization error. AppId is missing");
            } else if (!TextUtils.isEmpty(C1410i.f2935l)) {
                return true;
            } else {
                throw new Exception("SDK Initialization error. AppSignature is missing");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m3236a(Context context) {
        if (context == null) {
            try {
                throw new RuntimeException("Invalid activity context passed during intitalization");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        int checkSelfPermission;
        int checkSelfPermission2;
        int checkSelfPermission3;
        int checkSelfPermission4;
        int i;
        boolean z;
        if (C1454s.m3627a().m3630a(23)) {
            checkSelfPermission = context.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
            checkSelfPermission2 = context.checkSelfPermission("android.permission.ACCESS_NETWORK_STATE");
            checkSelfPermission3 = context.checkSelfPermission("android.permission.INTERNET");
            checkSelfPermission4 = context.checkSelfPermission("android.permission.READ_PHONE_STATE");
            i = checkSelfPermission;
            checkSelfPermission = checkSelfPermission2;
            checkSelfPermission2 = checkSelfPermission3;
            checkSelfPermission3 = checkSelfPermission4;
            checkSelfPermission4 = context.checkSelfPermission("android.permission.ACCESS_WIFI_STATE");
        } else {
            checkSelfPermission = context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
            checkSelfPermission2 = context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE");
            checkSelfPermission3 = context.checkCallingOrSelfPermission("android.permission.INTERNET");
            checkSelfPermission4 = context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE");
            i = checkSelfPermission;
            checkSelfPermission = checkSelfPermission2;
            checkSelfPermission2 = checkSelfPermission3;
            checkSelfPermission3 = checkSelfPermission4;
            checkSelfPermission4 = context.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE");
        }
        if (i != 0) {
            z = true;
        } else {
            z = false;
        }
        C1410i.f2937n = z;
        if (checkSelfPermission2 != 0) {
            z = true;
        } else {
            z = false;
        }
        C1410i.f2938o = z;
        if (checkSelfPermission != 0) {
            z = true;
        } else {
            z = false;
        }
        C1410i.f2939p = z;
        if (checkSelfPermission3 != 0) {
            z = true;
        } else {
            z = false;
        }
        C1410i.f2940q = z;
        if (checkSelfPermission4 != 0) {
            z = true;
        } else {
            z = false;
        }
        C1410i.f2941r = z;
        if (C1410i.f2938o) {
            throw new RuntimeException("Please add the permission : android.permission.INTERNET in your android manifest.xml");
        } else if (!C1410i.f2939p) {
            return true;
        } else {
            throw new RuntimeException("Please add the permission : android.permission.ACCESS_NETWORK_STATE in your android manifest.xml");
        }
    }

    /* renamed from: b */
    public static boolean m3239b(Context context) {
        List queryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent(context, CBImpressionActivity.class), 0);
        if (queryIntentActivities.isEmpty()) {
            return false;
        }
        ActivityInfo activityInfo = ((ResolveInfo) queryIntentActivities.get(0)).activityInfo;
        boolean z = ((activityInfo.flags & 512) == 0 || (activityInfo.flags & 32) == 0 || (activityInfo.configChanges & 128) == 0 || (activityInfo.configChanges & 32) == 0 || (activityInfo.configChanges & 1024) == 0) ? false : true;
        return z;
    }

    /* renamed from: a */
    public static String m3232a(C1390e c1390e) {
        if (c1390e.f2817y) {
            return AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_WEB;
        }
        return "native";
    }
}
