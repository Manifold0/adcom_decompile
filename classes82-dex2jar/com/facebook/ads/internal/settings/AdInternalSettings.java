// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.settings;

import android.content.SharedPreferences;
import android.util.Log;
import java.util.UUID;
import android.text.TextUtils;
import com.facebook.ads.internal.w.f.a;
import android.os.Build;
import android.content.Context;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collection;
import android.os.Bundle;

public class AdInternalSettings
{
    public static Bundle a;
    public static boolean b;
    public static boolean c;
    public static boolean d;
    public static int e;
    public static int f;
    private static final String g;
    private static final Collection<String> h;
    private static volatile boolean i;
    
    static {
        g = AdInternalSettings.class.getSimpleName();
        AdInternalSettings.a = new Bundle();
        (h = new HashSet<String>()).add("sdk");
        AdInternalSettings.h.add("google_sdk");
        AdInternalSettings.h.add("vbox86p");
        AdInternalSettings.h.add("vbox86tp");
        AdInternalSettings.b = false;
        AdInternalSettings.c = false;
        AdInternalSettings.d = false;
        AdInternalSettings.e = 0;
        AdInternalSettings.f = 0;
        AdInternalSettings.i = false;
    }
    
    private static ArrayList<String> a() {
        ArrayList<String> stringArrayList;
        if ((stringArrayList = (ArrayList<String>)AdInternalSettings.a.getStringArrayList("LIST_TEST_DEVICES_KEY")) == null) {
            stringArrayList = new ArrayList<String>();
            AdInternalSettings.a.putStringArrayList("LIST_TEST_DEVICES_KEY", (ArrayList)stringArrayList);
        }
        return stringArrayList;
    }
    
    public static void addTestDevice(final String s) {
        a().add(s);
    }
    
    public static void addTestDevices(final Collection<String> collection) {
        a().addAll(collection);
    }
    
    public static void clearTestDevices() {
        a().clear();
    }
    
    public static String getMediationService() {
        return AdInternalSettings.a.getString("STR_MEDIATION_SERVICE_KEY", (String)null);
    }
    
    public static String getUrlPrefix() {
        return AdInternalSettings.a.getString("STR_URL_PREFIX_KEY", (String)null);
    }
    
    public static boolean isDebugBuild() {
        return AdInternalSettings.a.getBoolean("BOOL_DEBUG_BUILD_KEY", false);
    }
    
    public static boolean isExplicitTestMode() {
        return AdInternalSettings.a.getBoolean("BOOL_EXPLICIT_TEST_MODE_KEY", false);
    }
    
    public static boolean isTestMode(final Context context) {
        if (AdInternalSettings.a.getBoolean("BOOL_DEBUG_BUILD_KEY", false) || isExplicitTestMode() || AdInternalSettings.h.contains(Build.PRODUCT)) {
            return true;
        }
        String string;
        if ((string = AdInternalSettings.a.getString("STR_DEVICE_ID_HASH_KEY", (String)null)) == null) {
            final SharedPreferences sharedPreferences = context.getSharedPreferences(com.facebook.ads.internal.w.f.a.a("FBAdPrefs", context), 0);
            String s;
            if (TextUtils.isEmpty((CharSequence)(s = sharedPreferences.getString("deviceIdHash", (String)null)))) {
                s = UUID.randomUUID().toString();
                sharedPreferences.edit().putString("deviceIdHash", s).apply();
            }
            AdInternalSettings.a.putString("STR_DEVICE_ID_HASH_KEY", s);
            string = s;
        }
        if (a().contains(string)) {
            return true;
        }
        if (!AdInternalSettings.i) {
            AdInternalSettings.i = true;
            Log.d(AdInternalSettings.g, "Test mode device hash: " + string);
            Log.d(AdInternalSettings.g, "When testing your app with Facebook's ad units you must specify the device hashed ID to ensure the delivery of test ads, add the following code before loading an ad: AdSettings.addTestDevice(\"" + string + "\");");
        }
        return false;
    }
    
    public static boolean isVideoAutoplay() {
        return AdInternalSettings.a.getBoolean("BOOL_VIDEO_AUTOPLAY_KEY");
    }
    
    public static boolean isVideoAutoplayOnMobile() {
        return AdInternalSettings.a.getBoolean("BOOL_AUTOPLAY_ON_MOBILE_KEY", false);
    }
    
    public static boolean isVisibleAnimation() {
        return AdInternalSettings.a.getBoolean("BOOL_VISIBLE_ANIMATION_KEY", false);
    }
    
    public static void setDebugBuild(final boolean b) {
        AdInternalSettings.a.putBoolean("BOOL_DEBUG_BUILD_KEY", b);
    }
    
    public static void setMediationService(final String s) {
        AdInternalSettings.a.putString("STR_MEDIATION_SERVICE_KEY", s);
    }
    
    public static void setTestMode(final boolean b) {
        AdInternalSettings.a.putBoolean("BOOL_EXPLICIT_TEST_MODE_KEY", b);
    }
    
    public static void setUrlPrefix(final String s) {
        AdInternalSettings.a.putString("STR_URL_PREFIX_KEY", s);
    }
    
    public static void setVideoAutoplay(final boolean b) {
        AdInternalSettings.a.putBoolean("BOOL_VIDEO_AUTOPLAY_KEY", b);
    }
    
    public static void setVideoAutoplayOnMobile(final boolean b) {
        AdInternalSettings.a.putBoolean("BOOL_AUTOPLAY_ON_MOBILE_KEY", b);
    }
    
    public static void setVisibleAnimation(final boolean b) {
        AdInternalSettings.a.putBoolean("BOOL_VISIBLE_ANIMATION_KEY", b);
    }
}
