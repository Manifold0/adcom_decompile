package com.ironsource.environment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.System;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.WindowManager;
import com.facebook.places.model.PlaceFields;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import org.json.JSONObject;

public class DeviceStatus {
    private static final String CACHED_UUID_KEY = "cachedUUID";
    private static final String DEVICE_OS = "android";
    private static final String GOOGLE_PLAY_SERVICES_CLASS_NAME = "com.google.android.gms.ads.identifier.AdvertisingIdClient";
    private static final String GOOGLE_PLAY_SERVICES_GET_AID_INFO_METHOD_NAME = "getAdvertisingIdInfo";
    private static final String GOOGLE_PLAY_SERVICES_GET_AID_METHOD_NAME = "getId";
    private static final String GOOGLE_PLAY_SERVICES_IS_LIMITED_AD_TRACKING_METHOD_NAME = "isLimitAdTrackingEnabled";
    private static final String MEDIATION_SHARED_PREFS = "Mediation_Shared_Preferences";
    public static final String UUID_ENABLED = "uuidEnabled";
    private static String uniqueId = null;

    public static long getDeviceLocalTime() {
        return Calendar.getInstance(TimeZone.getDefault()).getTime().getTime();
    }

    public static int getDeviceTimeZoneOffsetInMinutes() {
        return -(TimeZone.getDefault().getOffset(getDeviceLocalTime()) / 60000);
    }

    public static String[] getAdvertisingIdInfo(Context c) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> mAdvertisingIdClientClass = Class.forName(GOOGLE_PLAY_SERVICES_CLASS_NAME);
        Object mInfoClass = mAdvertisingIdClientClass.getMethod(GOOGLE_PLAY_SERVICES_GET_AID_INFO_METHOD_NAME, new Class[]{Context.class}).invoke(mAdvertisingIdClientClass, new Object[]{c});
        Method getIdMethod = mInfoClass.getClass().getMethod(GOOGLE_PLAY_SERVICES_GET_AID_METHOD_NAME, new Class[0]);
        Method isLimitAdTrackingEnabledMethod = mInfoClass.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]);
        String advertisingId = getIdMethod.invoke(mInfoClass, new Object[0]).toString();
        boolean isLimitedTrackingEnabled = ((Boolean) isLimitAdTrackingEnabledMethod.invoke(mInfoClass, new Object[0])).booleanValue();
        return new String[]{advertisingId, "" + isLimitedTrackingEnabled};
    }

    public static String getDeviceLanguage(Context c) throws Exception {
        return c.getResources().getConfiguration().locale.getLanguage();
    }

    private static long getFreeStorageInBytes(File f) {
        long res;
        StatFs stat = new StatFs(f.getPath());
        if (VERSION.SDK_INT < 19) {
            res = ((long) stat.getAvailableBlocks()) * ((long) stat.getBlockSize());
        } else {
            res = stat.getAvailableBlocksLong() * stat.getBlockSizeLong();
        }
        return res / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
    }

    public static boolean isExternalMemoryAvailableWritable() {
        return "mounted".equals(Environment.getExternalStorageState()) && Environment.isExternalStorageRemovable();
    }

    public static String getMobileCarrier(Context c) {
        return ((TelephonyManager) c.getSystemService(PlaceFields.PHONE)).getNetworkOperatorName();
    }

    public static String getAndroidOsVersion() {
        return VERSION.RELEASE;
    }

    public static int getAndroidAPIVersion() {
        return VERSION.SDK_INT;
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static String getDeviceOEM() {
        return Build.MANUFACTURER;
    }

    public static String getDeviceOs() {
        return "android";
    }

    public static boolean isRootedDevice() {
        return findBinary("su");
    }

    private static boolean findBinary(String binaryName) {
        try {
            for (String path : new String[]{"/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"}) {
                if (new File(path + binaryName).exists()) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isRTL(Context context) {
        Configuration config = context.getResources().getConfiguration();
        if (VERSION.SDK_INT < 17 || config.getLayoutDirection() != 1) {
            return false;
        }
        return true;
    }

    public static int getApplicationRotation(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
    }

    public static float getSystemVolumePercent(Context context) {
        AudioManager audio = (AudioManager) context.getSystemService("audio");
        return ((float) audio.getStreamVolume(3)) / ((float) audio.getStreamMaxVolume(3));
    }

    public static int getDeviceWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getDeviceHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int getActivityRequestedOrientation(Context activity) {
        return activity instanceof Activity ? ((Activity) activity).getRequestedOrientation() : -1;
    }

    public static int getDeviceDefaultOrientation(Context context) {
        int rotation = getApplicationRotation(context);
        int orientation = getDeviceOrientation(context);
        if ((rotation == 0 || rotation == 2) && orientation == 2) {
            return 2;
        }
        if ((rotation == 1 || rotation == 3) && orientation == 1) {
            return 2;
        }
        return 1;
    }

    public static int getDeviceOrientation(Context context) {
        return context.getResources().getConfiguration().orientation;
    }

    public static float getDeviceDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    public static List<ApplicationInfo> getInstalledApplications(Context context) {
        return context.getPackageManager().getInstalledApplications(0);
    }

    public static boolean isDeviceOrientationLocked(Context context) {
        return System.getInt(context.getContentResolver(), "accelerometer_rotation", 0) != 1;
    }

    public static File getExternalCacheDir(Context context) {
        return context.getExternalCacheDir();
    }

    public static String getInternalCacheDirPath(Context context) {
        File internalFile = context.getCacheDir();
        if (internalFile != null) {
            return internalFile.getPath();
        }
        return null;
    }

    public static long getAvailableInternalMemorySizeInMegaBytes() {
        return getFreeStorageInBytes(Environment.getDataDirectory());
    }

    public static long getAvailableMemorySizeInMegaBytes(String path) {
        return getFreeStorageInBytes(new File(path));
    }

    public static long getAvailableExternalMemorySizeInMegaBytes() {
        if (isExternalMemoryAvailableWritable()) {
            return getFreeStorageInBytes(Environment.getExternalStorageDirectory());
        }
        return 0;
    }

    @TargetApi(19)
    public static boolean isImmersiveSupported(Activity activity) {
        int uiOptions = activity.getWindow().getDecorView().getSystemUiVisibility();
        return (uiOptions | 4096) == uiOptions || (uiOptions | 2048) == uiOptions;
    }

    public static int getBatteryLevel(Context context) {
        int scale = 0;
        try {
            int level;
            Intent batteryIntent = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (batteryIntent != null) {
                level = batteryIntent.getIntExtra("level", -1);
            } else {
                level = 0;
            }
            if (batteryIntent != null) {
                scale = batteryIntent.getIntExtra("scale", -1);
            }
            if (level == -1 || scale == -1) {
                return -1;
            }
            return (int) ((((float) level) / ((float) scale)) * 100.0f);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static synchronized String getOrGenerateOnceUniqueIdentifier(Context context) {
        String str;
        synchronized (DeviceStatus.class) {
            if (TextUtils.isEmpty(uniqueId)) {
                try {
                    SharedPreferences preferences = context.getSharedPreferences(MEDIATION_SHARED_PREFS, 0);
                    if (preferences.getBoolean(UUID_ENABLED, true)) {
                        String id = preferences.getString(CACHED_UUID_KEY, "");
                        if (TextUtils.isEmpty(id)) {
                            uniqueId = UUID.randomUUID().toString();
                            Editor editor = preferences.edit();
                            editor.putString(CACHED_UUID_KEY, uniqueId);
                            editor.apply();
                        } else {
                            uniqueId = id;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                str = uniqueId;
            } else {
                str = uniqueId;
            }
        }
        return str;
    }

    private static boolean isSystemPackage(ResolveInfo resolveInfo) {
        return (resolveInfo.activityInfo.applicationInfo.flags & 1) != 0;
    }

    public static JSONObject getAppsInstallTime(Context ctx, boolean includeSystemPackages) {
        Intent intent = new Intent("android.intent.action.MAIN", null);
        intent.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> pkgAppsList = ctx.getPackageManager().queryIntentActivities(intent, 0);
        JSONObject packagesInstalledPerDate = new JSONObject();
        PackageManager manager = ctx.getPackageManager();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        for (int i = 0; i < pkgAppsList.size(); i++) {
            ResolveInfo resolveInfo = (ResolveInfo) pkgAppsList.get(i);
            if (!includeSystemPackages) {
                try {
                    if (isSystemPackage(resolveInfo)) {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            String installTime = sdf.format(new Date(manager.getPackageInfo(resolveInfo.activityInfo.packageName, 4096).firstInstallTime));
            packagesInstalledPerDate.put(installTime, packagesInstalledPerDate.optInt(installTime, 0) + 1);
        }
        return packagesInstalledPerDate;
    }
}
