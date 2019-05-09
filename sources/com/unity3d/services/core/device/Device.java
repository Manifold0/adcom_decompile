package com.unity3d.services.core.device;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ConfigurationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import com.adjust.sdk.Constants;
import com.facebook.places.model.PlaceFields;
import com.ironsource.sdk.constants.LocationConst;
import com.tapjoy.TapjoyConstants;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.properties.ClientProperties;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Device {

    public enum MemoryInfoType {
        TOTAL_MEMORY,
        FREE_MEMORY
    }

    public static int getApiLevel() {
        return VERSION.SDK_INT;
    }

    public static String getOsVersion() {
        return VERSION.RELEASE;
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getModel() {
        return Build.MODEL;
    }

    public static int getScreenLayout() {
        if (ClientProperties.getApplicationContext() != null) {
            return ClientProperties.getApplicationContext().getResources().getConfiguration().screenLayout;
        }
        return -1;
    }

    @SuppressLint({"DefaultLocale"})
    public static String getAndroidId() {
        String androidID = null;
        try {
            androidID = Secure.getString(ClientProperties.getApplicationContext().getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
        } catch (Exception e) {
            DeviceLog.exception("Problems fetching androidId", e);
        }
        return androidID;
    }

    public static String getAdvertisingTrackingId() {
        return AdvertisingId.getAdvertisingTrackingId();
    }

    public static boolean isLimitAdTrackingEnabled() {
        return AdvertisingId.getLimitedAdTracking();
    }

    public static boolean isUsingWifi() {
        boolean z = true;
        if (ClientProperties.getApplicationContext() == null) {
            return false;
        }
        ConnectivityManager mConnectivity = (ConnectivityManager) ClientProperties.getApplicationContext().getSystemService("connectivity");
        if (mConnectivity == null) {
            return false;
        }
        TelephonyManager mTelephony = (TelephonyManager) ClientProperties.getApplicationContext().getSystemService(PlaceFields.PHONE);
        NetworkInfo info = mConnectivity.getActiveNetworkInfo();
        if (info == null || !mConnectivity.getBackgroundDataSetting() || !mConnectivity.getActiveNetworkInfo().isConnected() || mTelephony == null) {
            return false;
        }
        if (!(info.getType() == 1 && info.isConnected())) {
            z = false;
        }
        return z;
    }

    public static int getNetworkType() {
        if (ClientProperties.getApplicationContext() != null) {
            return ((TelephonyManager) ClientProperties.getApplicationContext().getSystemService(PlaceFields.PHONE)).getNetworkType();
        }
        return -1;
    }

    public static boolean getNetworkMetered() {
        if (ClientProperties.getApplicationContext() == null || VERSION.SDK_INT < 16) {
            return false;
        }
        ConnectivityManager mConnectivity = (ConnectivityManager) ClientProperties.getApplicationContext().getSystemService("connectivity");
        if (mConnectivity == null) {
            return false;
        }
        return mConnectivity.isActiveNetworkMetered();
    }

    public static String getNetworkOperator() {
        if (ClientProperties.getApplicationContext() != null) {
            return ((TelephonyManager) ClientProperties.getApplicationContext().getSystemService(PlaceFields.PHONE)).getNetworkOperator();
        }
        return "";
    }

    public static String getNetworkOperatorName() {
        if (ClientProperties.getApplicationContext() != null) {
            return ((TelephonyManager) ClientProperties.getApplicationContext().getSystemService(PlaceFields.PHONE)).getNetworkOperatorName();
        }
        return "";
    }

    public static int getScreenDensity() {
        if (ClientProperties.getApplicationContext() != null) {
            return ClientProperties.getApplicationContext().getResources().getDisplayMetrics().densityDpi;
        }
        return -1;
    }

    public static int getScreenWidth() {
        if (ClientProperties.getApplicationContext() != null) {
            return ClientProperties.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        }
        return -1;
    }

    public static int getScreenHeight() {
        if (ClientProperties.getApplicationContext() != null) {
            return ClientProperties.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        }
        return -1;
    }

    public static boolean isActiveNetworkConnected() {
        if (ClientProperties.getApplicationContext() == null) {
            return false;
        }
        ConnectivityManager cm = (ConnectivityManager) ClientProperties.getApplicationContext().getSystemService("connectivity");
        if (cm == null) {
            return false;
        }
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null || !activeNetwork.isConnected()) {
            return false;
        }
        return true;
    }

    public static boolean isAppInstalled(String pkgname) {
        if (ClientProperties.getApplicationContext() == null) {
            return false;
        }
        try {
            PackageInfo pkgInfo = ClientProperties.getApplicationContext().getPackageManager().getPackageInfo(pkgname, 0);
            if (pkgInfo == null || pkgInfo.packageName == null || !pkgname.equals(pkgInfo.packageName)) {
                return false;
            }
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static List<Map<String, Object>> getInstalledPackages(boolean hash) {
        List<Map<String, Object>> returnList = new ArrayList();
        if (ClientProperties.getApplicationContext() != null) {
            PackageManager pm = ClientProperties.getApplicationContext().getPackageManager();
            for (PackageInfo pkg : pm.getInstalledPackages(0)) {
                HashMap<String, Object> packageEntry = new HashMap();
                if (hash) {
                    packageEntry.put("name", Utilities.Sha256(pkg.packageName));
                } else {
                    packageEntry.put("name", pkg.packageName);
                }
                if (pkg.firstInstallTime > 0) {
                    packageEntry.put(LocationConst.TIME, Long.valueOf(pkg.firstInstallTime));
                }
                String installer = pm.getInstallerPackageName(pkg.packageName);
                if (!(installer == null || installer.isEmpty())) {
                    packageEntry.put(TapjoyConstants.TJC_INSTALLER, installer);
                }
                returnList.add(packageEntry);
            }
        }
        return returnList;
    }

    public static String getUniqueEventId() {
        return UUID.randomUUID().toString();
    }

    public static boolean isWiredHeadsetOn() {
        if (ClientProperties.getApplicationContext() != null) {
            return ((AudioManager) ClientProperties.getApplicationContext().getSystemService("audio")).isWiredHeadsetOn();
        }
        return false;
    }

    public static String getSystemProperty(String propertyName, String defaultValue) {
        if (defaultValue != null) {
            return System.getProperty(propertyName, defaultValue);
        }
        return System.getProperty(propertyName);
    }

    public static int getRingerMode() {
        if (ClientProperties.getApplicationContext() == null) {
            return -1;
        }
        AudioManager am = (AudioManager) ClientProperties.getApplicationContext().getSystemService("audio");
        if (am != null) {
            return am.getRingerMode();
        }
        return -2;
    }

    public static int getStreamVolume(int streamType) {
        if (ClientProperties.getApplicationContext() == null) {
            return -1;
        }
        AudioManager am = (AudioManager) ClientProperties.getApplicationContext().getSystemService("audio");
        if (am != null) {
            return am.getStreamVolume(streamType);
        }
        return -2;
    }

    public static int getStreamMaxVolume(int streamType) {
        if (ClientProperties.getApplicationContext() == null) {
            return -1;
        }
        AudioManager am = (AudioManager) ClientProperties.getApplicationContext().getSystemService("audio");
        if (am != null) {
            return am.getStreamMaxVolume(streamType);
        }
        return -2;
    }

    public static int getScreenBrightness() {
        if (ClientProperties.getApplicationContext() != null) {
            return System.getInt(ClientProperties.getApplicationContext().getContentResolver(), "screen_brightness", -1);
        }
        return -1;
    }

    public static long getFreeSpace(File file) {
        if (file == null || !file.exists()) {
            return -1;
        }
        return (long) Math.round((float) (file.getFreeSpace() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID));
    }

    public static long getTotalSpace(File file) {
        if (file == null || !file.exists()) {
            return -1;
        }
        return (long) Math.round((float) (file.getTotalSpace() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID));
    }

    public static float getBatteryLevel() {
        if (ClientProperties.getApplicationContext() != null) {
            Intent i = ClientProperties.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (i != null) {
                return ((float) i.getIntExtra("level", -1)) / ((float) i.getIntExtra("scale", -1));
            }
        }
        return -1.0f;
    }

    public static int getBatteryStatus() {
        if (ClientProperties.getApplicationContext() == null) {
            return -1;
        }
        Intent i = ClientProperties.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (i != null) {
            return i.getIntExtra("status", -1);
        }
        return -1;
    }

    public static long getTotalMemory() {
        return getMemoryInfo(MemoryInfoType.TOTAL_MEMORY);
    }

    public static long getFreeMemory() {
        return getMemoryInfo(MemoryInfoType.FREE_MEMORY);
    }

    private static long getMemoryInfo(MemoryInfoType infoType) {
        IOException e;
        Throwable th;
        int lineNumber = -1;
        switch (infoType) {
            case TOTAL_MEMORY:
                lineNumber = 1;
                break;
            case FREE_MEMORY:
                lineNumber = 2;
                break;
        }
        RandomAccessFile reader = null;
        String line = null;
        try {
            RandomAccessFile reader2 = new RandomAccessFile("/proc/meminfo", "r");
            int i = 0;
            while (i < lineNumber) {
                try {
                    line = reader2.readLine();
                    i++;
                } catch (IOException e2) {
                    e = e2;
                    reader = reader2;
                } catch (Throwable th2) {
                    th = th2;
                    reader = reader2;
                }
            }
            long memoryValueFromString = getMemoryValueFromString(line);
            try {
                reader2.close();
            } catch (IOException e3) {
                DeviceLog.exception("Error closing RandomAccessFile", e3);
            }
            reader = reader2;
            return memoryValueFromString;
        } catch (IOException e4) {
            e3 = e4;
            try {
                DeviceLog.exception("Error while reading memory info: " + infoType, e3);
                try {
                    reader.close();
                } catch (IOException e32) {
                    DeviceLog.exception("Error closing RandomAccessFile", e32);
                }
                return -1;
            } catch (Throwable th3) {
                th = th3;
                try {
                    reader.close();
                } catch (IOException e322) {
                    DeviceLog.exception("Error closing RandomAccessFile", e322);
                }
                throw th;
            }
        }
    }

    private static long getMemoryValueFromString(String memVal) {
        if (memVal == null) {
            return -1;
        }
        Matcher m = Pattern.compile("(\\d+)").matcher(memVal);
        String value = "";
        while (m.find()) {
            value = m.group(1);
        }
        return Long.parseLong(value);
    }

    public static boolean isRooted() {
        try {
            return searchPathForBinary("su");
        } catch (Exception e) {
            DeviceLog.exception("Rooted check failed", e);
            return false;
        }
    }

    public static Boolean isAdbEnabled() {
        if (getApiLevel() < 17) {
            return oldAdbStatus();
        }
        return newAdbStatus();
    }

    private static Boolean oldAdbStatus() {
        boolean z = true;
        Boolean status = null;
        try {
            if (1 != Secure.getInt(ClientProperties.getApplicationContext().getContentResolver(), "adb_enabled", 0)) {
                z = false;
            }
            status = Boolean.valueOf(z);
        } catch (Exception e) {
            DeviceLog.exception("Problems fetching adb enabled status", e);
        }
        return status;
    }

    @TargetApi(17)
    private static Boolean newAdbStatus() {
        boolean z = true;
        Boolean status = null;
        try {
            if (1 != Global.getInt(ClientProperties.getApplicationContext().getContentResolver(), "adb_enabled", 0)) {
                z = false;
            }
            status = Boolean.valueOf(z);
        } catch (Exception e) {
            DeviceLog.exception("Problems fetching adb enabled status", e);
        }
        return status;
    }

    private static boolean searchPathForBinary(String binary) {
        for (String path : System.getenv("PATH").split(":")) {
            File pathDir = new File(path);
            if (pathDir.exists() && pathDir.isDirectory()) {
                File[] pathDirFiles = pathDir.listFiles();
                if (pathDirFiles != null) {
                    for (File fileInPath : pathDirFiles) {
                        if (fileInPath.getName().equals(binary)) {
                            return true;
                        }
                    }
                    continue;
                } else {
                    continue;
                }
            }
        }
        return false;
    }

    public static String getGLVersion() {
        if (ClientProperties.getApplicationContext() != null) {
            ActivityManager activityManager = (ActivityManager) ClientProperties.getApplicationContext().getSystemService("activity");
            if (activityManager != null) {
                ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
                if (configurationInfo != null) {
                    return configurationInfo.getGlEsVersion();
                }
            }
        }
        return null;
    }

    public static String getApkDigest() throws Exception {
        Throwable th;
        InputStream inputStream = null;
        try {
            InputStream inputStream2 = new FileInputStream(new File(ClientProperties.getApplicationContext().getPackageCodePath()));
            try {
                String apkDigest = Utilities.Sha256(inputStream2);
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException e) {
                    }
                }
                return apkDigest;
            } catch (Throwable th2) {
                th = th2;
                inputStream = inputStream2;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public static String getCertificateFingerprint() {
        String fingerprint = null;
        try {
            Signature[] signatures = ClientProperties.getApplicationContext().getPackageManager().getPackageInfo(ClientProperties.getApplicationContext().getPackageName(), 64).signatures;
            if (signatures != null && signatures.length >= 1) {
                fingerprint = Utilities.toHexString(MessageDigest.getInstance(Constants.SHA1).digest(((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatures[0].toByteArray()))).getEncoded()));
            }
        } catch (Exception e) {
            DeviceLog.exception("Exception when signing certificate fingerprint", e);
        }
        return fingerprint;
    }

    public static String getBoard() {
        return Build.BOARD;
    }

    public static String getBootloader() {
        return Build.BOOTLOADER;
    }

    public static String getBrand() {
        return Build.BRAND;
    }

    public static String getDevice() {
        return Build.DEVICE;
    }

    public static String getHardware() {
        return Build.HARDWARE;
    }

    public static String getHost() {
        return Build.HOST;
    }

    public static String getProduct() {
        return Build.PRODUCT;
    }

    public static String getFingerprint() {
        return Build.FINGERPRINT;
    }

    public static ArrayList<String> getSupportedAbis() {
        if (getApiLevel() < 21) {
            return getOldAbiList();
        }
        return getNewAbiList();
    }

    public static List<Sensor> getSensorList() {
        if (ClientProperties.getApplicationContext() != null) {
            return ((SensorManager) ClientProperties.getApplicationContext().getSystemService("sensor")).getSensorList(-1);
        }
        return null;
    }

    public static boolean isUSBConnected() {
        if (ClientProperties.getApplicationContext() == null) {
            return false;
        }
        Intent intent = ClientProperties.getApplicationContext().registerReceiver(null, new IntentFilter("android.hardware.usb.action.USB_STATE"));
        if (intent != null) {
            return intent.getBooleanExtra("connected", false);
        }
        return false;
    }

    public static long getCPUCount() {
        return (long) Runtime.getRuntime().availableProcessors();
    }

    public static long getUptime() {
        return SystemClock.uptimeMillis();
    }

    public static long getElapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }

    public static String getBuildId() {
        return Build.ID;
    }

    public static String getBuildVersionIncremental() {
        return VERSION.INCREMENTAL;
    }

    private static ArrayList<String> getOldAbiList() {
        ArrayList<String> abiList = new ArrayList();
        abiList.add(Build.CPU_ABI);
        abiList.add(Build.CPU_ABI2);
        return abiList;
    }

    @TargetApi(21)
    private static ArrayList<String> getNewAbiList() {
        ArrayList<String> abiList = new ArrayList();
        abiList.addAll(Arrays.asList(Build.SUPPORTED_ABIS));
        return abiList;
    }

    public static Map<String, String> getProcessInfo() {
        IOException e;
        Throwable th;
        HashMap<String, String> retData = new HashMap();
        RandomAccessFile reader = null;
        try {
            RandomAccessFile reader2 = new RandomAccessFile("/proc/self/stat", "r");
            try {
                retData.put("stat", reader2.readLine());
                try {
                    reader2.close();
                    reader = reader2;
                } catch (IOException e2) {
                    DeviceLog.exception("Error closing RandomAccessFile", e2);
                    reader = reader2;
                }
            } catch (IOException e3) {
                e2 = e3;
                reader = reader2;
                try {
                    DeviceLog.exception("Error while reading processor info: ", e2);
                    try {
                        reader.close();
                    } catch (IOException e22) {
                        DeviceLog.exception("Error closing RandomAccessFile", e22);
                    }
                    return retData;
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        reader.close();
                    } catch (IOException e222) {
                        DeviceLog.exception("Error closing RandomAccessFile", e222);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                reader = reader2;
                reader.close();
                throw th;
            }
        } catch (IOException e4) {
            e222 = e4;
            DeviceLog.exception("Error while reading processor info: ", e222);
            reader.close();
            return retData;
        }
        return retData;
    }
}
