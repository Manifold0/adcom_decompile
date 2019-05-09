// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.integration;

import android.text.TextUtils;
import com.ironsource.mediationsdk.IronSourceObject;
import android.content.pm.PackageManager;
import android.util.Pair;
import java.util.ArrayList;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import android.os.Build$VERSION;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.ironsource.mediationsdk.IntegrationData;
import android.app.Activity;

public class IntegrationHelper
{
    private static final String BANNER_COMPATIBILITY_VERSION = "4.3";
    private static final String SDK_COMPATIBILITY_VERSION = "4.1";
    private static final String TAG = "IntegrationHelper";
    
    private static IntegrationData getIntegrationData(final Activity activity, final String s) {
        try {
            final IntegrationData integrationData = (IntegrationData)Class.forName(s).getMethod("getIntegrationData", Activity.class).invoke(null, activity);
            Log.i("IntegrationHelper", "Adapter " + integrationData.version + " - VERIFIED");
            return integrationData;
        }
        catch (ClassNotFoundException ex) {
            Log.e("IntegrationHelper", "Adapter - MISSING");
        }
        catch (Exception ex2) {
            Log.e("IntegrationHelper", "Adapter version - NOT VERIFIED");
            goto Label_0078;
        }
    }
    
    private static boolean isActivitiesValid(final Activity activity, final String[] array) {
        boolean b;
        if (array == null) {
            b = true;
        }
        else {
            Log.i("IntegrationHelper", "*** Activities ***");
            boolean b2 = true;
            final int length = array.length;
            int n = 0;
            while (true) {
                b = b2;
                if (n >= length) {
                    break;
                }
                final String s = array[n];
                try {
                    if (activity.getPackageManager().queryIntentActivities(new Intent((Context)activity, (Class)Class.forName(s)), 65536).size() > 0) {
                        Log.i("IntegrationHelper", s + " - VERIFIED");
                    }
                    else {
                        b2 = false;
                        Log.e("IntegrationHelper", s + " - MISSING");
                    }
                }
                catch (ClassNotFoundException ex) {
                    b2 = false;
                    Log.e("IntegrationHelper", s + " - MISSING");
                }
                ++n;
            }
        }
        return b;
    }
    
    private static boolean isAdapterValid(final Activity activity, final String s) {
        String string;
        IntegrationData integrationData;
        try {
            if (s.equalsIgnoreCase("SupersonicAds")) {
                Log.i("IntegrationHelper", "--------------- IronSource  --------------");
            }
            else {
                Log.i("IntegrationHelper", "--------------- " + s + " --------------");
            }
            string = "com.ironsource.adapters." + s.toLowerCase() + "." + s + "Adapter";
            integrationData = getIntegrationData(activity, string);
            if (integrationData == null) {
                return false;
            }
        }
        catch (Exception ex) {
            Log.e("IntegrationHelper", "isAdapterValid " + s, (Throwable)ex);
            return false;
        }
        if (!verifyBannerAdapterVersion(integrationData)) {
            return false;
        }
        if (!s.equalsIgnoreCase("SupersonicAds") && !isAdapterVersionValid(integrationData)) {
            return false;
        }
        int n = 1;
        if (!s.equalsIgnoreCase("SupersonicAds")) {
            validateSDKVersion(string);
        }
        if (!isActivitiesValid(activity, integrationData.activities)) {
            n = 0;
        }
        if (!isExternalLibsValid(integrationData.externalLibs)) {
            n = 0;
        }
        if (!isServicesValid(activity, integrationData.services)) {
            n = 0;
        }
        boolean b = n != 0;
        if (integrationData.validateWriteExternalStorage) {
            b = (n != 0);
            if (Build$VERSION.SDK_INT <= 18) {
                if (activity.getPackageManager().checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", activity.getPackageName()) == 0) {
                    Log.i("IntegrationHelper", "android.permission.WRITE_EXTERNAL_STORAGE - VERIFIED");
                    return n != 0;
                }
                Log.e("IntegrationHelper", "android.permission.WRITE_EXTERNAL_STORAGE - MISSING");
                b = false;
            }
        }
        return b;
    }
    
    private static boolean isAdapterVersionValid(final IntegrationData integrationData) {
        if (integrationData.version.startsWith("4.1") || integrationData.version.startsWith("4.3")) {
            Log.i("IntegrationHelper", "Adapter - VERIFIED");
            return true;
        }
        Log.e("IntegrationHelper", integrationData.name + " adapter " + integrationData.version + " is incompatible with SDK version " + IronSourceUtils.getSDKVersion() + ", please update your adapter to version " + "4.1" + ".*");
        return false;
    }
    
    private static boolean isExternalLibsValid(ArrayList<Pair<String, String>> iterator) {
        boolean b;
        if (iterator == null) {
            b = true;
        }
        else {
            Log.i("IntegrationHelper", "*** External Libraries ***");
            boolean b2 = true;
            iterator = ((ArrayList<Pair<String, String>>)iterator).iterator();
            while (true) {
                b = b2;
                if (!iterator.hasNext()) {
                    break;
                }
                final Pair<String, String> pair = iterator.next();
                try {
                    Class.forName((String)pair.first);
                    Log.i("IntegrationHelper", (String)pair.second + " - VERIFIED");
                }
                catch (ClassNotFoundException ex) {
                    b2 = false;
                    Log.e("IntegrationHelper", (String)pair.second + " - MISSING");
                }
            }
        }
        return b;
    }
    
    private static boolean isServicesValid(final Activity activity, final String[] array) {
        boolean b;
        if (array == null) {
            b = true;
        }
        else {
            final PackageManager packageManager = activity.getPackageManager();
            Log.i("IntegrationHelper", "*** Services ***");
            boolean b2 = true;
            final int length = array.length;
            int n = 0;
            while (true) {
                b = b2;
                if (n >= length) {
                    break;
                }
                final String s = array[n];
                try {
                    if (packageManager.queryIntentServices(new Intent((Context)activity, (Class)Class.forName(s)), 65536).size() > 0) {
                        Log.i("IntegrationHelper", s + " - VERIFIED");
                    }
                    else {
                        b2 = false;
                        Log.e("IntegrationHelper", s + " - MISSING");
                    }
                }
                catch (ClassNotFoundException ex) {
                    b2 = false;
                    Log.e("IntegrationHelper", s + " - MISSING");
                }
                ++n;
            }
        }
        return b;
    }
    
    private static void validateGooglePlayServices(final Activity activity) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Log.w("IntegrationHelper", "--------------- Google Play Services --------------");
                    if (!activity.getPackageManager().getApplicationInfo(activity.getPackageName(), 128).metaData.containsKey("com.google.android.gms.version")) {
                        Log.e("IntegrationHelper", "Google Play Services - MISSING");
                        return;
                    }
                    Log.i("IntegrationHelper", "Google Play Services - VERIFIED");
                    final String advertiserId = IronSourceObject.getInstance().getAdvertiserId((Context)activity);
                    if (!TextUtils.isEmpty((CharSequence)advertiserId)) {
                        Log.i("IntegrationHelper", "GAID is: " + advertiserId + " (use this for test devices)");
                    }
                }
                catch (Exception ex) {
                    Log.e("IntegrationHelper", "Google Play Services - MISSING");
                }
            }
        }.start();
    }
    
    public static void validateIntegration(final Activity activity) {
        int i = 0;
        Log.i("IntegrationHelper", "Verifying Integration:");
        validatePermissions(activity);
        for (String[] array = { "SupersonicAds", "AdColony", "AppLovin", "Chartboost", "HyprMX", "UnityAds", "Vungle", "InMobi", "Facebook", "Fyber", "MediaBrix", "Tapjoy", "AdMob", "MoPub", "Maio" }; i < array.length; ++i) {
            final String s = array[i];
            if (isAdapterValid(activity, s)) {
                if (s.equalsIgnoreCase("SupersonicAds")) {
                    Log.i("IntegrationHelper", ">>>> IronSource - VERIFIED");
                }
                else {
                    Log.i("IntegrationHelper", ">>>> " + s + " - VERIFIED");
                }
            }
            else if (s.equalsIgnoreCase("SupersonicAds")) {
                Log.e("IntegrationHelper", ">>>> IronSource - NOT VERIFIED");
            }
            else {
                Log.e("IntegrationHelper", ">>>> " + s + " - NOT VERIFIED");
            }
        }
        validateGooglePlayServices(activity);
    }
    
    private static void validatePermissions(final Activity activity) {
        Log.i("IntegrationHelper", "*** Permissions ***");
        final PackageManager packageManager = activity.getPackageManager();
        if (packageManager.checkPermission("android.permission.INTERNET", activity.getPackageName()) == 0) {
            Log.i("IntegrationHelper", "android.permission.INTERNET - VERIFIED");
        }
        else {
            Log.e("IntegrationHelper", "android.permission.INTERNET - MISSING");
        }
        if (packageManager.checkPermission("android.permission.ACCESS_NETWORK_STATE", activity.getPackageName()) == 0) {
            Log.i("IntegrationHelper", "android.permission.ACCESS_NETWORK_STATE - VERIFIED");
            return;
        }
        Log.e("IntegrationHelper", "android.permission.ACCESS_NETWORK_STATE - MISSING");
    }
    
    private static void validateSDKVersion(String s) {
        try {
            s = (String)Class.forName(s).getMethod("getAdapterSDKVersion", (Class<?>[])new Class[0]).invoke(null, new Object[0]);
            Log.i("IntegrationHelper", "SDK Version - " + s);
        }
        catch (Exception ex) {
            Log.w("validateSDKVersion", "Unable to get SDK version");
        }
    }
    
    private static boolean verifyBannerAdapterVersion(final IntegrationData integrationData) {
        if ((integrationData.name.equalsIgnoreCase("AppLovin") || integrationData.name.equalsIgnoreCase("AdMob") || integrationData.name.equalsIgnoreCase("Facebook") || integrationData.name.equalsIgnoreCase("InMobi") || integrationData.name.equalsIgnoreCase("Fyber")) && !integrationData.version.startsWith("4.3")) {
            Log.e("IntegrationHelper", integrationData.name + " adapter " + integrationData.version + " is incompatible for showing banners with SDK version " + IronSourceUtils.getSDKVersion() + ", please update your adapter to version " + "4.3" + ".*");
            return false;
        }
        return true;
    }
}
