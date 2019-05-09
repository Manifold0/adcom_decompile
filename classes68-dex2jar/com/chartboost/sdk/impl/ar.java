// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import java.util.UUID;
import android.provider.Settings$Secure;
import android.content.pm.PackageManager;
import android.os.Build;
import android.content.Context;

public class ar
{
    public static String a(final Context context) {
        final PackageManager packageManager = context.getPackageManager();
        final int n = context.getResources().getConfiguration().uiMode & 0xF;
        final int screenLayout = context.getResources().getConfiguration().screenLayout;
        if (packageManager.hasSystemFeature("org.chromium.arc.device_management") || (Build.BRAND != null && Build.BRAND.equals("chromium") && Build.MANUFACTURER.equals("chromium")) || (Build.DEVICE != null && Build.DEVICE.matches(".+_cheets"))) {
            return "chromebook";
        }
        if (packageManager.hasSystemFeature("android.hardware.type.watch") || n == 6) {
            return "watch";
        }
        if (packageManager.hasSystemFeature("android.hardware.type.television") || n == 4) {
            return "tv";
        }
        if ((Build.MANUFACTURER != null && Build.MANUFACTURER.equalsIgnoreCase("Amazon")) || (screenLayout & 0xF) == 0x4) {
            return "tablet";
        }
        return "phone";
    }
    
    public static String b(final Context context) {
        while (true) {
            try {
                final Object value = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get("cb.limit.aid");
                if (value instanceof Integer && (int)value == 1) {
                    return null;
                }
            }
            catch (Exception ex) {}
            String string;
            if ("9774d56d682e549c".equals(string = Settings$Secure.getString(context.getContentResolver(), "android_id"))) {
                string = null;
            }
            String s;
            if ((s = string) == null) {
                break;
            }
            return s;
        }
        return c(context);
    }
    
    private static String c(final Context context) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences("cbPrefs", 0);
        String s;
        if (sharedPreferences == null) {
            s = UUID.randomUUID().toString();
        }
        else if ((s = sharedPreferences.getString("cbUUID", (String)null)) == null) {
            final String string = UUID.randomUUID().toString();
            final SharedPreferences$Editor edit = sharedPreferences.edit();
            edit.putString("cbUUID", string);
            edit.apply();
            return string;
        }
        return s;
    }
}
