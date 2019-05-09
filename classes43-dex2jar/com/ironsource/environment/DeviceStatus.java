// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.environment;

import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.annotation.TargetApi;
import android.provider.Settings$System;
import android.media.AudioManager;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import java.util.UUID;
import android.text.TextUtils;
import android.telephony.TelephonyManager;
import android.content.pm.ApplicationInfo;
import android.os.StatFs;
import android.os.Build;
import java.util.Calendar;
import java.util.TimeZone;
import android.content.res.Resources;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Environment;
import java.util.List;
import java.util.Date;
import android.content.pm.ResolveInfo;
import java.text.SimpleDateFormat;
import java.util.Locale;
import android.net.Uri;
import android.content.Intent;
import org.json.JSONObject;
import android.view.WindowManager;
import android.os.Build$VERSION;
import java.lang.reflect.InvocationTargetException;
import android.app.Activity;
import android.content.Context;
import java.io.File;

public class DeviceStatus
{
    private static final String CACHED_UUID_KEY = "cachedUUID";
    private static final String DEVICE_OS = "android";
    private static final String GOOGLE_PLAY_SERVICES_CLASS_NAME = "com.google.android.gms.ads.identifier.AdvertisingIdClient";
    private static final String GOOGLE_PLAY_SERVICES_GET_AID_INFO_METHOD_NAME = "getAdvertisingIdInfo";
    private static final String GOOGLE_PLAY_SERVICES_GET_AID_METHOD_NAME = "getId";
    private static final String GOOGLE_PLAY_SERVICES_IS_LIMITED_AD_TRACKING_METHOD_NAME = "isLimitAdTrackingEnabled";
    private static final String MEDIATION_SHARED_PREFS = "Mediation_Shared_Preferences";
    public static final String UUID_ENABLED = "uuidEnabled";
    private static String uniqueId;
    
    static {
        DeviceStatus.uniqueId = null;
    }
    
    private static boolean findBinary(final String s) {
        int n = 0;
        final boolean b = false;
        try {
            final String[] array = { "/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/" };
            final int length = array.length;
            boolean b2;
            while (true) {
                b2 = b;
                if (n >= length) {
                    break;
                }
                if (new File(array[n] + s).exists()) {
                    b2 = true;
                    break;
                }
                ++n;
            }
            return b2;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public static int getActivityRequestedOrientation(final Context context) {
        if (context instanceof Activity) {
            return ((Activity)context).getRequestedOrientation();
        }
        return -1;
    }
    
    public static String[] getAdvertisingIdInfo(final Context context) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Class<?> forName = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
        final Object invoke = forName.getMethod("getAdvertisingIdInfo", Context.class).invoke(forName, context);
        return new String[] { invoke.getClass().getMethod("getId", (Class<?>[])new Class[0]).invoke(invoke, new Object[0]).toString(), "" + (boolean)invoke.getClass().getMethod("isLimitAdTrackingEnabled", (Class<?>[])new Class[0]).invoke(invoke, new Object[0]) };
    }
    
    public static int getAndroidAPIVersion() {
        return Build$VERSION.SDK_INT;
    }
    
    public static String getAndroidOsVersion() {
        return Build$VERSION.RELEASE;
    }
    
    public static int getApplicationRotation(final Context context) {
        return ((WindowManager)context.getSystemService("window")).getDefaultDisplay().getRotation();
    }
    
    public static JSONObject getAppsInstallTime(Context packageManager, final boolean b) {
        final Intent intent = new Intent("android.intent.action.MAIN", (Uri)null);
        intent.addCategory("android.intent.category.LAUNCHER");
        final List queryIntentActivities = packageManager.getPackageManager().queryIntentActivities(intent, 0);
        final JSONObject jsonObject = new JSONObject();
        packageManager = (Context)packageManager.getPackageManager();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        for (int i = 0; i < queryIntentActivities.size(); ++i) {
            final ResolveInfo resolveInfo = queryIntentActivities.get(i);
            Label_0095: {
                if (b) {
                    break Label_0095;
                }
                try {
                    if (!isSystemPackage(resolveInfo)) {
                        final String format = simpleDateFormat.format(new Date(((PackageManager)packageManager).getPackageInfo(resolveInfo.activityInfo.packageName, 4096).firstInstallTime));
                        jsonObject.put(format, jsonObject.optInt(format, 0) + 1);
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return jsonObject;
    }
    
    public static long getAvailableExternalMemorySizeInMegaBytes() {
        long freeStorageInBytes = 0L;
        if (isExternalMemoryAvailableWritable()) {
            freeStorageInBytes = getFreeStorageInBytes(Environment.getExternalStorageDirectory());
        }
        return freeStorageInBytes;
    }
    
    public static long getAvailableInternalMemorySizeInMegaBytes() {
        return getFreeStorageInBytes(Environment.getDataDirectory());
    }
    
    public static long getAvailableMemorySizeInMegaBytes(final String s) {
        return getFreeStorageInBytes(new File(s));
    }
    
    public static int getBatteryLevel(final Context context) {
        int intExtra = 0;
        final int n = -1;
        try {
            final Intent registerReceiver = context.registerReceiver((BroadcastReceiver)null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra2;
            if (registerReceiver != null) {
                intExtra2 = registerReceiver.getIntExtra("level", -1);
            }
            else {
                intExtra2 = 0;
            }
            if (registerReceiver != null) {
                intExtra = registerReceiver.getIntExtra("scale", -1);
            }
            int n2 = n;
            if (intExtra2 != -1) {
                n2 = n;
                if (intExtra != -1) {
                    n2 = (int)(intExtra2 / (float)intExtra * 100.0f);
                }
            }
            return n2;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    public static int getDeviceDefaultOrientation(final Context context) {
        final int applicationRotation = getApplicationRotation(context);
        final int deviceOrientation = getDeviceOrientation(context);
        if (((applicationRotation == 0 || applicationRotation == 2) && deviceOrientation == 2) || ((applicationRotation == 1 || applicationRotation == 3) && deviceOrientation == 1)) {
            return 2;
        }
        return 1;
    }
    
    public static float getDeviceDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }
    
    public static int getDeviceHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
    
    public static String getDeviceLanguage(final Context context) throws Exception {
        return context.getResources().getConfiguration().locale.getLanguage();
    }
    
    public static long getDeviceLocalTime() {
        return Calendar.getInstance(TimeZone.getDefault()).getTime().getTime();
    }
    
    public static String getDeviceModel() {
        return Build.MODEL;
    }
    
    public static String getDeviceOEM() {
        return Build.MANUFACTURER;
    }
    
    public static int getDeviceOrientation(final Context context) {
        return context.getResources().getConfiguration().orientation;
    }
    
    public static String getDeviceOs() {
        return "android";
    }
    
    public static int getDeviceTimeZoneOffsetInMinutes() {
        return -(TimeZone.getDefault().getOffset(getDeviceLocalTime()) / 60000);
    }
    
    public static int getDeviceWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    
    public static File getExternalCacheDir(final Context context) {
        return context.getExternalCacheDir();
    }
    
    private static long getFreeStorageInBytes(final File file) {
        final StatFs statFs = new StatFs(file.getPath());
        long n;
        if (Build$VERSION.SDK_INT < 19) {
            n = statFs.getAvailableBlocks() * (long)statFs.getBlockSize();
        }
        else {
            n = statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
        }
        return n / 1048576L;
    }
    
    public static List<ApplicationInfo> getInstalledApplications(final Context context) {
        return (List<ApplicationInfo>)context.getPackageManager().getInstalledApplications(0);
    }
    
    public static String getInternalCacheDirPath(final Context context) {
        final String s = null;
        final File cacheDir = context.getCacheDir();
        String path = s;
        if (cacheDir != null) {
            path = cacheDir.getPath();
        }
        return path;
    }
    
    public static String getMobileCarrier(final Context context) {
        return ((TelephonyManager)context.getSystemService("phone")).getNetworkOperatorName();
    }
    
    public static String getOrGenerateOnceUniqueIdentifier(final Context context) {
        synchronized (DeviceStatus.class) {
            String s;
            if (!TextUtils.isEmpty((CharSequence)DeviceStatus.uniqueId)) {
                s = DeviceStatus.uniqueId;
            }
            else {
                while (true) {
                    while (true) {
                        try {
                            final SharedPreferences sharedPreferences = context.getSharedPreferences("Mediation_Shared_Preferences", 0);
                            if (sharedPreferences.getBoolean("uuidEnabled", true)) {
                                final String string = sharedPreferences.getString("cachedUUID", "");
                                if (TextUtils.isEmpty((CharSequence)string)) {
                                    DeviceStatus.uniqueId = UUID.randomUUID().toString();
                                    final SharedPreferences$Editor edit = sharedPreferences.edit();
                                    edit.putString("cachedUUID", DeviceStatus.uniqueId);
                                    edit.apply();
                                }
                                else {
                                    DeviceStatus.uniqueId = string;
                                }
                            }
                            s = DeviceStatus.uniqueId;
                            break;
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                            continue;
                        }
                        continue;
                    }
                }
            }
            return s;
        }
    }
    
    public static float getSystemVolumePercent(final Context context) {
        final AudioManager audioManager = (AudioManager)context.getSystemService("audio");
        return audioManager.getStreamVolume(3) / (float)audioManager.getStreamMaxVolume(3);
    }
    
    public static boolean isDeviceOrientationLocked(final Context context) {
        return Settings$System.getInt(context.getContentResolver(), "accelerometer_rotation", 0) != 1;
    }
    
    public static boolean isExternalMemoryAvailableWritable() {
        return "mounted".equals(Environment.getExternalStorageState()) && Environment.isExternalStorageRemovable();
    }
    
    @TargetApi(19)
    public static boolean isImmersiveSupported(final Activity activity) {
        final int systemUiVisibility = activity.getWindow().getDecorView().getSystemUiVisibility();
        return (systemUiVisibility | 0x1000) == systemUiVisibility || (systemUiVisibility | 0x800) == systemUiVisibility;
    }
    
    public static boolean isRTL(final Context context) {
        final Configuration configuration = context.getResources().getConfiguration();
        return Build$VERSION.SDK_INT >= 17 && configuration.getLayoutDirection() == 1;
    }
    
    public static boolean isRootedDevice() {
        return findBinary("su");
    }
    
    private static boolean isSystemPackage(final ResolveInfo resolveInfo) {
        return (resolveInfo.activityInfo.applicationInfo.flags & 0x1) != 0x0;
    }
}
