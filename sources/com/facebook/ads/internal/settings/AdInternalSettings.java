package com.facebook.ads.internal.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.p025w.p071f.C2616a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

public class AdInternalSettings {
    /* renamed from: a */
    public static Bundle f4776a = new Bundle();
    /* renamed from: b */
    public static boolean f4777b = false;
    /* renamed from: c */
    public static boolean f4778c = false;
    /* renamed from: d */
    public static boolean f4779d = false;
    /* renamed from: e */
    public static int f4780e = 0;
    /* renamed from: f */
    public static int f4781f = 0;
    /* renamed from: g */
    private static final String f4782g = AdInternalSettings.class.getSimpleName();
    /* renamed from: h */
    private static final Collection<String> f4783h = new HashSet();
    /* renamed from: i */
    private static volatile boolean f4784i = false;

    static {
        f4783h.add("sdk");
        f4783h.add("google_sdk");
        f4783h.add("vbox86p");
        f4783h.add("vbox86tp");
    }

    /* renamed from: a */
    private static ArrayList<String> m5228a() {
        ArrayList<String> stringArrayList = f4776a.getStringArrayList("LIST_TEST_DEVICES_KEY");
        if (stringArrayList != null) {
            return stringArrayList;
        }
        stringArrayList = new ArrayList();
        f4776a.putStringArrayList("LIST_TEST_DEVICES_KEY", stringArrayList);
        return stringArrayList;
    }

    public static void addTestDevice(String str) {
        m5228a().add(str);
    }

    public static void addTestDevices(Collection<String> collection) {
        m5228a().addAll(collection);
    }

    public static void clearTestDevices() {
        m5228a().clear();
    }

    public static String getMediationService() {
        return f4776a.getString("STR_MEDIATION_SERVICE_KEY", null);
    }

    public static String getUrlPrefix() {
        return f4776a.getString("STR_URL_PREFIX_KEY", null);
    }

    public static boolean isDebugBuild() {
        return f4776a.getBoolean("BOOL_DEBUG_BUILD_KEY", false);
    }

    public static boolean isExplicitTestMode() {
        return f4776a.getBoolean("BOOL_EXPLICIT_TEST_MODE_KEY", false);
    }

    public static boolean isTestMode(Context context) {
        if (f4776a.getBoolean("BOOL_DEBUG_BUILD_KEY", false) || isExplicitTestMode() || f4783h.contains(Build.PRODUCT)) {
            return true;
        }
        String string = f4776a.getString("STR_DEVICE_ID_HASH_KEY", null);
        if (string == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(C2616a.m6730a("FBAdPrefs", context), 0);
            string = sharedPreferences.getString("deviceIdHash", null);
            if (TextUtils.isEmpty(string)) {
                string = UUID.randomUUID().toString();
                sharedPreferences.edit().putString("deviceIdHash", string).apply();
            }
            f4776a.putString("STR_DEVICE_ID_HASH_KEY", string);
        }
        if (m5228a().contains(string)) {
            return true;
        }
        if (!f4784i) {
            f4784i = true;
            Log.d(f4782g, "Test mode device hash: " + string);
            Log.d(f4782g, "When testing your app with Facebook's ad units you must specify the device hashed ID to ensure the delivery of test ads, add the following code before loading an ad: AdSettings.addTestDevice(\"" + string + "\");");
        }
        return false;
    }

    public static boolean isVideoAutoplay() {
        return f4776a.getBoolean("BOOL_VIDEO_AUTOPLAY_KEY");
    }

    public static boolean isVideoAutoplayOnMobile() {
        return f4776a.getBoolean("BOOL_AUTOPLAY_ON_MOBILE_KEY", false);
    }

    public static boolean isVisibleAnimation() {
        return f4776a.getBoolean("BOOL_VISIBLE_ANIMATION_KEY", false);
    }

    public static void setDebugBuild(boolean z) {
        f4776a.putBoolean("BOOL_DEBUG_BUILD_KEY", z);
    }

    public static void setMediationService(String str) {
        f4776a.putString("STR_MEDIATION_SERVICE_KEY", str);
    }

    public static void setTestMode(boolean z) {
        f4776a.putBoolean("BOOL_EXPLICIT_TEST_MODE_KEY", z);
    }

    public static void setUrlPrefix(String str) {
        f4776a.putString("STR_URL_PREFIX_KEY", str);
    }

    public static void setVideoAutoplay(boolean z) {
        f4776a.putBoolean("BOOL_VIDEO_AUTOPLAY_KEY", z);
    }

    public static void setVideoAutoplayOnMobile(boolean z) {
        f4776a.putBoolean("BOOL_AUTOPLAY_ON_MOBILE_KEY", z);
    }

    public static void setVisibleAnimation(boolean z) {
        f4776a.putBoolean("BOOL_VISIBLE_ANIMATION_KEY", z);
    }
}
