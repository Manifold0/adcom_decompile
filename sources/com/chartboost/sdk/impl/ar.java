package com.chartboost.sdk.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings.Secure;
import com.facebook.places.model.PlaceFields;
import com.tapjoy.TapjoyConstants;
import com.vungle.warren.network.VungleApiClient;
import java.util.UUID;

public class ar {
    /* renamed from: a */
    public static String m3412a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        int i = context.getResources().getConfiguration().uiMode & 15;
        int i2 = context.getResources().getConfiguration().screenLayout & 15;
        if (packageManager.hasSystemFeature("org.chromium.arc.device_management") || ((Build.BRAND != null && Build.BRAND.equals("chromium") && Build.MANUFACTURER.equals("chromium")) || (Build.DEVICE != null && Build.DEVICE.matches(".+_cheets")))) {
            return "chromebook";
        }
        if (packageManager.hasSystemFeature("android.hardware.type.watch") || i == 6) {
            return "watch";
        }
        if (packageManager.hasSystemFeature("android.hardware.type.television") || i == 4) {
            return "tv";
        }
        if ((Build.MANUFACTURER == null || !Build.MANUFACTURER.equalsIgnoreCase(VungleApiClient.MANUFACTURER_AMAZON)) && i2 != 4) {
            return PlaceFields.PHONE;
        }
        return "tablet";
    }

    /* renamed from: b */
    public static String m3413b(Context context) {
        try {
            Object obj = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get("cb.limit.aid");
            if ((obj instanceof Integer) && ((Integer) obj).intValue() == 1) {
                return null;
            }
        } catch (Exception e) {
        }
        String string = Secure.getString(context.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
        if ("9774d56d682e549c".equals(string)) {
            string = null;
        }
        return string == null ? m3414c(context) : string;
    }

    /* renamed from: c */
    private static String m3414c(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("cbPrefs", 0);
        if (sharedPreferences == null) {
            return UUID.randomUUID().toString();
        }
        String string = sharedPreferences.getString("cbUUID", null);
        if (string != null) {
            return string;
        }
        string = UUID.randomUUID().toString();
        Editor edit = sharedPreferences.edit();
        edit.putString("cbUUID", string);
        edit.apply();
        return string;
    }
}
