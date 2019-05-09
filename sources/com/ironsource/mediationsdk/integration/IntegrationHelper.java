package com.ironsource.mediationsdk.integration;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.ironsource.mediationsdk.IntegrationData;
import com.ironsource.mediationsdk.IronSourceObject;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class IntegrationHelper {
    private static final String BANNER_COMPATIBILITY_VERSION = "4.3";
    private static final String SDK_COMPATIBILITY_VERSION = "4.1";
    private static final String TAG = "IntegrationHelper";

    public static void validateIntegration(Activity activity) {
        int i = 0;
        Log.i(TAG, "Verifying Integration:");
        validatePermissions(activity);
        String[] adapters = new String[]{IronSourceConstants.IRONSOURCE_CONFIG_NAME, "AdColony", "AppLovin", "Chartboost", "HyprMX", "UnityAds", "Vungle", "InMobi", "Facebook", "Fyber", "MediaBrix", "Tapjoy", "AdMob", "MoPub", "Maio"};
        int length = adapters.length;
        while (i < length) {
            String adapter = adapters[i];
            if (isAdapterValid(activity, adapter)) {
                if (adapter.equalsIgnoreCase(IronSourceConstants.IRONSOURCE_CONFIG_NAME)) {
                    Log.i(TAG, ">>>> IronSource - VERIFIED");
                } else {
                    Log.i(TAG, ">>>> " + adapter + " - VERIFIED");
                }
            } else if (adapter.equalsIgnoreCase(IronSourceConstants.IRONSOURCE_CONFIG_NAME)) {
                Log.e(TAG, ">>>> IronSource - NOT VERIFIED");
            } else {
                Log.e(TAG, ">>>> " + adapter + " - NOT VERIFIED");
            }
            i++;
        }
        validateGooglePlayServices(activity);
    }

    private static boolean isAdapterValid(Activity activity, String adapterName) {
        try {
            if (adapterName.equalsIgnoreCase(IronSourceConstants.IRONSOURCE_CONFIG_NAME)) {
                Log.i(TAG, "--------------- IronSource  --------------");
            } else {
                Log.i(TAG, "--------------- " + adapterName + " --------------");
            }
            String className = "com.ironsource.adapters." + adapterName.toLowerCase() + "." + adapterName + "Adapter";
            IntegrationData data = getIntegrationData(activity, className);
            if (data == null) {
                return false;
            }
            if (!verifyBannerAdapterVersion(data)) {
                return false;
            }
            if (!adapterName.equalsIgnoreCase(IronSourceConstants.IRONSOURCE_CONFIG_NAME) && !isAdapterVersionValid(data)) {
                return false;
            }
            boolean ret = true;
            if (!adapterName.equalsIgnoreCase(IronSourceConstants.IRONSOURCE_CONFIG_NAME)) {
                validateSDKVersion(className);
            }
            if (!isActivitiesValid(activity, data.activities)) {
                ret = false;
            }
            if (!isExternalLibsValid(data.externalLibs)) {
                ret = false;
            }
            if (!isServicesValid(activity, data.services)) {
                ret = false;
            }
            if (!data.validateWriteExternalStorage || VERSION.SDK_INT > 18) {
                return ret;
            }
            if (activity.getPackageManager().checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", activity.getPackageName()) == 0) {
                Log.i(TAG, "android.permission.WRITE_EXTERNAL_STORAGE - VERIFIED");
                return ret;
            }
            Log.e(TAG, "android.permission.WRITE_EXTERNAL_STORAGE - MISSING");
            return false;
        } catch (Exception e) {
            Log.e(TAG, "isAdapterValid " + adapterName, e);
            return false;
        }
    }

    private static boolean isServicesValid(Activity activity, String[] services) {
        if (services == null) {
            return true;
        }
        PackageManager packageManager = activity.getPackageManager();
        Log.i(TAG, "*** Services ***");
        boolean ret = true;
        for (String service : services) {
            try {
                if (packageManager.queryIntentServices(new Intent(activity, Class.forName(service)), 65536).size() > 0) {
                    Log.i(TAG, service + " - VERIFIED");
                } else {
                    ret = false;
                    Log.e(TAG, service + " - MISSING");
                }
            } catch (ClassNotFoundException e) {
                ret = false;
                Log.e(TAG, service + " - MISSING");
            }
        }
        return ret;
    }

    private static boolean isExternalLibsValid(ArrayList<Pair<String, String>> externalLibs) {
        if (externalLibs == null) {
            return true;
        }
        Log.i(TAG, "*** External Libraries ***");
        boolean ret = true;
        Iterator it = externalLibs.iterator();
        while (it.hasNext()) {
            Pair<String, String> externalLibrary = (Pair) it.next();
            try {
                Class c = Class.forName((String) externalLibrary.first);
                Log.i(TAG, ((String) externalLibrary.second) + " - VERIFIED");
            } catch (ClassNotFoundException e) {
                ret = false;
                Log.e(TAG, ((String) externalLibrary.second) + " - MISSING");
            }
        }
        return ret;
    }

    private static boolean isActivitiesValid(Activity activity, String[] activities) {
        if (activities == null) {
            return true;
        }
        Log.i(TAG, "*** Activities ***");
        boolean ret = true;
        for (String act : activities) {
            try {
                if (activity.getPackageManager().queryIntentActivities(new Intent(activity, Class.forName(act)), 65536).size() > 0) {
                    Log.i(TAG, act + " - VERIFIED");
                } else {
                    ret = false;
                    Log.e(TAG, act + " - MISSING");
                }
            } catch (ClassNotFoundException e) {
                ret = false;
                Log.e(TAG, act + " - MISSING");
            }
        }
        return ret;
    }

    private static void validatePermissions(Activity activity) {
        Log.i(TAG, "*** Permissions ***");
        PackageManager pm = activity.getPackageManager();
        if (pm.checkPermission("android.permission.INTERNET", activity.getPackageName()) == 0) {
            Log.i(TAG, "android.permission.INTERNET - VERIFIED");
        } else {
            Log.e(TAG, "android.permission.INTERNET - MISSING");
        }
        if (pm.checkPermission("android.permission.ACCESS_NETWORK_STATE", activity.getPackageName()) == 0) {
            Log.i(TAG, "android.permission.ACCESS_NETWORK_STATE - VERIFIED");
        } else {
            Log.e(TAG, "android.permission.ACCESS_NETWORK_STATE - MISSING");
        }
    }

    private static boolean verifyBannerAdapterVersion(IntegrationData data) {
        if ((!data.name.equalsIgnoreCase("AppLovin") && !data.name.equalsIgnoreCase("AdMob") && !data.name.equalsIgnoreCase("Facebook") && !data.name.equalsIgnoreCase("InMobi") && !data.name.equalsIgnoreCase("Fyber")) || data.version.startsWith(BANNER_COMPATIBILITY_VERSION)) {
            return true;
        }
        Log.e(TAG, data.name + " adapter " + data.version + " is incompatible for showing banners with SDK version " + IronSourceUtils.getSDKVersion() + ", please update your adapter to version " + BANNER_COMPATIBILITY_VERSION + ".*");
        return false;
    }

    private static boolean isAdapterVersionValid(IntegrationData data) {
        if (data.version.startsWith(SDK_COMPATIBILITY_VERSION) || data.version.startsWith(BANNER_COMPATIBILITY_VERSION)) {
            Log.i(TAG, "Adapter - VERIFIED");
            return true;
        }
        Log.e(TAG, data.name + " adapter " + data.version + " is incompatible with SDK version " + IronSourceUtils.getSDKVersion() + ", please update your adapter to version " + SDK_COMPATIBILITY_VERSION + ".*");
        return false;
    }

    private static IntegrationData getIntegrationData(Activity activity, String className) {
        try {
            IntegrationData ret = (IntegrationData) Class.forName(className).getMethod("getIntegrationData", new Class[]{Activity.class}).invoke(null, new Object[]{activity});
            Log.i(TAG, "Adapter " + ret.version + " - VERIFIED");
            return ret;
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "Adapter - MISSING");
        } catch (Exception e2) {
            Log.e(TAG, "Adapter version - NOT VERIFIED");
        }
        return null;
    }

    private static void validateGooglePlayServices(final Activity activity) {
        String mGooglePlayServicesMetaData = "com.google.android.gms.version";
        String mGooglePlayServices = "Google Play Services";
        new Thread() {
            public void run() {
                try {
                    Log.w(IntegrationHelper.TAG, "--------------- Google Play Services --------------");
                    if (activity.getPackageManager().getApplicationInfo(activity.getPackageName(), 128).metaData.containsKey("com.google.android.gms.version")) {
                        Log.i(IntegrationHelper.TAG, "Google Play Services - VERIFIED");
                        String gaid = IronSourceObject.getInstance().getAdvertiserId(activity);
                        if (!TextUtils.isEmpty(gaid)) {
                            Log.i(IntegrationHelper.TAG, "GAID is: " + gaid + " (use this for test devices)");
                            return;
                        }
                        return;
                    }
                    Log.e(IntegrationHelper.TAG, "Google Play Services - MISSING");
                } catch (Exception e) {
                    Log.e(IntegrationHelper.TAG, "Google Play Services - MISSING");
                }
            }
        }.start();
    }

    private static void validateSDKVersion(String className) {
        try {
            Log.i(TAG, "SDK Version - " + ((String) Class.forName(className).getMethod("getAdapterSDKVersion", new Class[0]).invoke(null, new Object[0])));
        } catch (Exception e) {
            Log.w("validateSDKVersion", "Unable to get SDK version");
        }
    }
}
