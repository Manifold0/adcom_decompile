// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android;

import java.security.NoSuchAlgorithmException;
import android.content.pm.PackageInfo;
import java.util.UUID;
import java.security.MessageDigest;
import android.text.TextUtils;
import android.provider.Settings$Secure;
import android.os.Bundle;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import net.hockeyapp.android.utils.HockeyLog;
import android.os.Environment;
import java.io.File;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Build$VERSION;
import android.content.Context;

public class Constants
{
    public static String ANDROID_BUILD;
    public static String ANDROID_VERSION;
    public static String APP_PACKAGE;
    public static String APP_VERSION;
    public static String APP_VERSION_NAME;
    public static final String BASE_URL = "https://sdk.hockeyapp.net/";
    private static final String BUNDLE_BUILD_NUMBER = "buildNumber";
    public static String CRASH_IDENTIFIER;
    public static String DEVICE_IDENTIFIER;
    public static final String FILES_DIRECTORY_NAME = "HockeyApp";
    public static String FILES_PATH;
    public static String PHONE_MANUFACTURER;
    public static String PHONE_MODEL;
    public static final String SDK_NAME = "HockeySDK";
    public static final String SDK_USER_AGENT = "HockeySDK/Android 4.1.2";
    public static final String SDK_VERSION = "4.1.2";
    public static final int UPDATE_PERMISSIONS_REQUEST = 1;
    
    static {
        Constants.FILES_PATH = null;
        Constants.APP_VERSION = null;
        Constants.APP_VERSION_NAME = null;
        Constants.APP_PACKAGE = null;
        Constants.ANDROID_VERSION = null;
        Constants.ANDROID_BUILD = null;
        Constants.PHONE_MODEL = null;
        Constants.PHONE_MANUFACTURER = null;
        Constants.CRASH_IDENTIFIER = null;
        Constants.DEVICE_IDENTIFIER = null;
    }
    
    private static String bytesToHex(final byte[] array) {
        final char[] charArray = "0123456789ABCDEF".toCharArray();
        final char[] array2 = new char[array.length * 2];
        for (int i = 0; i < array.length; ++i) {
            final int n = array[i] & 0xFF;
            array2[i * 2] = charArray[n >>> 4];
            array2[i * 2 + 1] = charArray[n & 0xF];
        }
        return new String(array2).replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");
    }
    
    @SuppressLint({ "InlinedApi" })
    private static String createSalt(Context context) {
        Label_0120: {
            if (Build$VERSION.SDK_INT < 21) {
                break Label_0120;
            }
            context = (Context)Build.SUPPORTED_ABIS[0];
            while (true) {
                final String string = "HA" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + ((String)context).length() % 10 + Build.PRODUCT.length() % 10;
                context = (Context)"";
                try {
                    context = (Context)Build.class.getField("SERIAL").get(null).toString();
                    return string + ":" + (String)context;
                    context = (Context)Build.CPU_ABI;
                }
                catch (Throwable t) {
                    return string + ":" + (String)context;
                }
            }
        }
    }
    
    public static File getHockeyAppStorageDir() {
        final File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "HockeyApp");
        int n;
        if (file.exists() || file.mkdirs()) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n == 0) {
            HockeyLog.warn("Couldn't create HockeyApp Storage dir");
        }
        return file;
    }
    
    private static int loadBuildNumber(final Context context, final PackageManager packageManager) {
        int int1 = 0;
        try {
            final Bundle metaData = packageManager.getApplicationInfo(context.getPackageName(), 128).metaData;
            if (metaData != null) {
                int1 = metaData.getInt("buildNumber", 0);
            }
            return int1;
        }
        catch (PackageManager$NameNotFoundException ex) {
            HockeyLog.error("Exception thrown when accessing the application info:");
            ex.printStackTrace();
            return 0;
        }
    }
    
    private static void loadCrashIdentifier(final Context context) {
        final String string = Settings$Secure.getString(context.getContentResolver(), "android_id");
        if (TextUtils.isEmpty((CharSequence)Constants.APP_PACKAGE) || TextUtils.isEmpty((CharSequence)string)) {
            return;
        }
        final String string2 = Constants.APP_PACKAGE + ":" + string + ":" + createSalt(context);
        try {
            final MessageDigest instance = MessageDigest.getInstance("SHA-1");
            final byte[] bytes = string2.getBytes("UTF-8");
            instance.update(bytes, 0, bytes.length);
            Constants.CRASH_IDENTIFIER = bytesToHex(instance.digest());
        }
        catch (Throwable t) {
            HockeyLog.error("Couldn't create CrashIdentifier with Exception:" + t.toString());
        }
    }
    
    private static void loadDeviceIdentifier(final Context context) {
        final String string = Settings$Secure.getString(context.getContentResolver(), "android_id");
        if (string != null) {
            String device_IDENTIFIER = tryHashStringSha256(context, string);
            if (device_IDENTIFIER == null) {
                device_IDENTIFIER = UUID.randomUUID().toString();
            }
            Constants.DEVICE_IDENTIFIER = device_IDENTIFIER;
        }
    }
    
    private static void loadFilesPath(final Context context) {
        if (context == null) {
            return;
        }
        try {
            final File filesDir = context.getFilesDir();
            if (filesDir != null) {
                Constants.FILES_PATH = filesDir.getAbsolutePath();
            }
        }
        catch (Exception ex) {
            HockeyLog.error("Exception thrown when accessing the files dir:");
            ex.printStackTrace();
        }
    }
    
    public static void loadFromContext(final Context context) {
        Constants.ANDROID_VERSION = Build$VERSION.RELEASE;
        Constants.ANDROID_BUILD = Build.DISPLAY;
        Constants.PHONE_MODEL = Build.MODEL;
        Constants.PHONE_MANUFACTURER = Build.MANUFACTURER;
        loadFilesPath(context);
        loadPackageData(context);
        loadCrashIdentifier(context);
        loadDeviceIdentifier(context);
    }
    
    private static void loadPackageData(final Context context) {
        if (context == null) {
            return;
        }
        try {
            final PackageManager packageManager = context.getPackageManager();
            final PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            Constants.APP_PACKAGE = packageInfo.packageName;
            Constants.APP_VERSION = "" + packageInfo.versionCode;
            Constants.APP_VERSION_NAME = packageInfo.versionName;
            final int loadBuildNumber = loadBuildNumber(context, packageManager);
            if (loadBuildNumber != 0 && loadBuildNumber > packageInfo.versionCode) {
                Constants.APP_VERSION = "" + loadBuildNumber;
            }
        }
        catch (PackageManager$NameNotFoundException ex) {
            HockeyLog.error("Exception thrown when accessing the package info:");
            ex.printStackTrace();
        }
    }
    
    private static String tryHashStringSha256(final Context context, final String s) {
        final String salt = createSalt(context);
        try {
            final MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.reset();
            instance.update(s.getBytes());
            instance.update(salt.getBytes());
            return bytesToHex(instance.digest());
        }
        catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }
}
