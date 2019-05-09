// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import java.util.UUID;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import android.os.Handler;
import android.os.Looper;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.regex.Pattern;
import java.security.MessageDigest;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v4.app.JobIntentService;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.firebase.messaging.FirebaseMessaging;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.net.Uri;
import android.content.res.Resources;
import java.util.Locale;
import android.os.Build$VERSION;
import android.support.v4.app.NotificationManagerCompat;
import android.content.Context;

class OSUtils
{
    static final int UNINITIALIZABLE_STATUS = -999;
    
    static boolean areNotificationsEnabled(final Context context) {
        try {
            return NotificationManagerCompat.from(OneSignal.appContext).areNotificationsEnabled();
        }
        catch (Throwable t) {
            return true;
        }
    }
    
    private Integer checkAndroidSupportLibrary(final Context context) {
        final boolean hasWakefulBroadcastReceiver = hasWakefulBroadcastReceiver();
        final boolean hasNotificationManagerCompat = hasNotificationManagerCompat();
        if (!hasWakefulBroadcastReceiver && !hasNotificationManagerCompat) {
            OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "Could not find the Android Support Library. Please make sure it has been correctly added to your project.");
            return -3;
        }
        if (!hasWakefulBroadcastReceiver || !hasNotificationManagerCompat) {
            OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "The included Android Support Library is to old or incomplete. Please update to the 26.0.0 revision or newer.");
            return -5;
        }
        if (Build$VERSION.SDK_INT >= 26 && getTargetSdkVersion(context) >= 26 && !hasJobIntentService()) {
            OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "The included Android Support Library is to old or incomplete. Please update to the 26.0.0 revision or newer.");
            return -5;
        }
        return null;
    }
    
    static String getCorrectedLanguage() {
        final String language = Locale.getDefault().getLanguage();
        String s;
        if (language.equals("iw")) {
            s = "he";
        }
        else {
            if (language.equals("in")) {
                return "id";
            }
            if (language.equals("ji")) {
                return "yi";
            }
            s = language;
            if (language.equals("zh")) {
                return language + "-" + Locale.getDefault().getCountry();
            }
        }
        return s;
    }
    
    static String getManifestMeta(final Context context, final String s) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString(s);
        }
        catch (Throwable t) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "", t);
            return null;
        }
    }
    
    static String getResourceString(final Context context, final String s, String string) {
        final Resources resources = context.getResources();
        final int identifier = resources.getIdentifier(s, "string", context.getPackageName());
        if (identifier != 0) {
            string = resources.getString(identifier);
        }
        return string;
    }
    
    static Uri getSoundUri(final Context context, final String s) {
        final Resources resources = context.getResources();
        final String packageName = context.getPackageName();
        if (isValidResourceName(s)) {
            final int identifier = resources.getIdentifier(s, "raw", packageName);
            if (identifier != 0) {
                return Uri.parse("android.resource://" + packageName + "/" + identifier);
            }
        }
        final int identifier2 = resources.getIdentifier("onesignal_default_sound", "raw", packageName);
        if (identifier2 != 0) {
            return Uri.parse("android.resource://" + packageName + "/" + identifier2);
        }
        return null;
    }
    
    static int getTargetSdkVersion(final Context context) {
        final String packageName = context.getPackageName();
        final PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationInfo(packageName, 0).targetSdkVersion;
        }
        catch (PackageManager$NameNotFoundException ex) {
            ex.printStackTrace();
            return 15;
        }
    }
    
    static boolean hasFCMLibrary() {
        boolean b = false;
        if (FirebaseMessaging.class != null) {
            b = true;
        }
        return b;
    }
    
    private static boolean hasGCMLibrary() {
        boolean b = false;
        if (GoogleCloudMessaging.class != null) {
            b = true;
        }
        return b;
    }
    
    private static boolean hasJobIntentService() {
        boolean b = false;
        if (JobIntentService.class != null) {
            b = true;
        }
        return b;
    }
    
    private static boolean hasNotificationManagerCompat() {
        boolean b = false;
        if (NotificationManagerCompat.class != null) {
            b = true;
        }
        return b;
    }
    
    private static boolean hasWakefulBroadcastReceiver() {
        boolean b = false;
        if (WakefulBroadcastReceiver.class != null) {
            b = true;
        }
        return b;
    }
    
    static String hexDigest(String s, final String s2) throws Throwable {
        final MessageDigest instance = MessageDigest.getInstance(s2);
        instance.update(s.getBytes("UTF-8"));
        final byte[] digest = instance.digest();
        final StringBuilder sb = new StringBuilder();
        for (int length = digest.length, i = 0; i < length; ++i) {
            for (s = Integer.toHexString(digest[i] & 0xFF); s.length() < 2; s = "0" + s) {}
            sb.append(s);
        }
        return sb.toString();
    }
    
    static boolean isValidEmail(final String s) {
        return s != null && Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$").matcher(s).matches();
    }
    
    static boolean isValidResourceName(final String s) {
        return s != null && !s.matches("^[0-9]");
    }
    
    static long[] parseVibrationPattern(final JSONObject jsonObject) {
        long[] array2;
        try {
            final Object opt = jsonObject.opt("vib_pt");
            JSONArray jsonArray;
            if (opt instanceof String) {
                jsonArray = new JSONArray((String)opt);
            }
            else {
                jsonArray = (JSONArray)opt;
            }
            final long[] array = new long[jsonArray.length()];
            int n = 0;
            while (true) {
                array2 = array;
                if (n >= jsonArray.length()) {
                    break;
                }
                array[n] = jsonArray.optLong(n);
                ++n;
            }
        }
        catch (JSONException ex) {
            array2 = null;
        }
        return array2;
    }
    
    static void runOnMainUIThread(final Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
            return;
        }
        new Handler(Looper.getMainLooper()).post(runnable);
    }
    
    static void sleep(final int n) {
        final long n2 = n;
        try {
            Thread.sleep(n2);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    Integer checkForGooglePushLibrary() {
        final boolean hasFCMLibrary = hasFCMLibrary();
        final boolean hasGCMLibrary = hasGCMLibrary();
        if (!hasFCMLibrary && !hasGCMLibrary) {
            OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "The Firebase FCM library is missing! Please make sure to include it in your project.");
            return -4;
        }
        if (hasGCMLibrary && !hasFCMLibrary) {
            OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "GCM Library detected, please upgrade to Firebase FCM library as GCM is deprecated!");
        }
        if (hasGCMLibrary && hasFCMLibrary) {
            OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Both GCM & FCM Libraries detected! Please remove the deprecated GCM library.");
        }
        return null;
    }
    
    String getCarrierName() {
        try {
            String networkOperatorName = ((TelephonyManager)OneSignal.appContext.getSystemService("phone")).getNetworkOperatorName();
            if ("".equals(networkOperatorName)) {
                networkOperatorName = null;
            }
            return networkOperatorName;
        }
        catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }
    
    int getDeviceType() {
        try {
            Class.forName("com.amazon.device.messaging.ADM");
            return 2;
        }
        catch (ClassNotFoundException ex) {
            return 1;
        }
    }
    
    Integer getNetType() {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)OneSignal.appContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return null;
        }
        final int type = activeNetworkInfo.getType();
        if (type == 1 || type == 9) {
            return 0;
        }
        return 1;
    }
    
    int initializationChecker(final Context context, final int n, final String s) {
        final boolean b = true;
        try {
            UUID.fromString(s);
            if ("b2f7f966-d8cc-11e4-bed1-df8f05be55ba".equals(s) || "5eb5a37e-b458-11e3-ac11-000c2940e62c".equals(s)) {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal Example AppID detected, please update to your app's id found on OneSignal.com");
            }
            int n2 = b ? 1 : 0;
            if (n == 1) {
                final Integer checkForGooglePushLibrary = this.checkForGooglePushLibrary();
                n2 = (b ? 1 : 0);
                if (checkForGooglePushLibrary != null) {
                    n2 = checkForGooglePushLibrary;
                }
            }
            final Integer checkAndroidSupportLibrary = this.checkAndroidSupportLibrary(context);
            if (checkAndroidSupportLibrary != null) {
                n2 = checkAndroidSupportLibrary;
            }
            return n2;
        }
        catch (Throwable t) {
            OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "OneSignal AppId format is invalid.\nExample: 'b2f7f966-d8cc-11e4-bed1-df8f05be55ba'\n", t);
            return -999;
        }
    }
}
