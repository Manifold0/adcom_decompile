// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.api;

import com.unity3d.services.core.device.VolumeChange;
import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.WebViewApp;
import java.util.TimeZone;
import java.util.Locale;
import java.util.Iterator;
import java.util.List;
import android.hardware.Sensor;
import java.util.Map;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import org.json.JSONException;
import android.content.pm.PackageManager$NameNotFoundException;
import org.json.JSONObject;
import java.util.Collection;
import org.json.JSONArray;
import com.unity3d.services.core.properties.ClientProperties;
import java.io.File;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.device.DeviceError;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.core.device.Device;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.device.IVolumeChangeListener;
import android.util.SparseArray;

public class DeviceInfo
{
    private static SparseArray<IVolumeChangeListener> _volumeChangeListeners;
    
    @WebViewExposed
    public static void getAdvertisingTrackingId(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getAdvertisingTrackingId());
    }
    
    @WebViewExposed
    public static void getAndroidId(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getAndroidId());
    }
    
    @WebViewExposed
    public static void getApiLevel(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getApiLevel());
    }
    
    @WebViewExposed
    public static void getApkDigest(final WebViewCallback webViewCallback) {
        try {
            webViewCallback.invoke(Device.getApkDigest());
        }
        catch (Exception ex) {
            webViewCallback.error(DeviceError.COULDNT_GET_DIGEST, ex.toString());
        }
    }
    
    @WebViewExposed
    public static void getBatteryLevel(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getBatteryLevel());
    }
    
    @WebViewExposed
    public static void getBatteryStatus(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getBatteryStatus());
    }
    
    @WebViewExposed
    public static void getBoard(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getBoard());
    }
    
    @WebViewExposed
    public static void getBootloader(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getBootloader());
    }
    
    @WebViewExposed
    public static void getBrand(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getBrand());
    }
    
    @WebViewExposed
    public static void getBuildId(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getBuildId());
    }
    
    @WebViewExposed
    public static void getBuildVersionIncremental(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getBuildVersionIncremental());
    }
    
    @WebViewExposed
    public static void getCPUCount(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getCPUCount());
    }
    
    @WebViewExposed
    public static void getCertificateFingerprint(final WebViewCallback webViewCallback) {
        final String certificateFingerprint = Device.getCertificateFingerprint();
        if (certificateFingerprint != null) {
            webViewCallback.invoke(certificateFingerprint);
            return;
        }
        webViewCallback.error(DeviceError.COULDNT_GET_FINGERPRINT, new Object[0]);
    }
    
    @WebViewExposed
    public static void getConnectionType(final WebViewCallback webViewCallback) {
        String s;
        if (Device.isUsingWifi()) {
            s = "wifi";
        }
        else if (Device.isActiveNetworkConnected()) {
            s = "cellular";
        }
        else {
            s = "none";
        }
        webViewCallback.invoke(s);
    }
    
    @WebViewExposed
    public static void getDevice(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getDevice());
    }
    
    @WebViewExposed
    public static void getDeviceMaxVolume(final Integer n, final WebViewCallback webViewCallback) {
        final int streamMaxVolume = Device.getStreamMaxVolume(n);
        if (streamMaxVolume > -1) {
            webViewCallback.invoke(streamMaxVolume);
            return;
        }
        switch (streamMaxVolume) {
            default: {
                DeviceLog.error("Unhandled deviceMaxVolume error: " + streamMaxVolume);
            }
            case -1: {
                webViewCallback.error(DeviceError.APPLICATION_CONTEXT_NULL, streamMaxVolume);
            }
            case -2: {
                webViewCallback.error(DeviceError.AUDIOMANAGER_NULL, streamMaxVolume);
            }
        }
    }
    
    @WebViewExposed
    public static void getDeviceVolume(final Integer n, final WebViewCallback webViewCallback) {
        final int streamVolume = Device.getStreamVolume(n);
        if (streamVolume > -1) {
            webViewCallback.invoke(streamVolume);
            return;
        }
        switch (streamVolume) {
            default: {
                DeviceLog.error("Unhandled deviceVolume error: " + streamVolume);
            }
            case -1: {
                webViewCallback.error(DeviceError.APPLICATION_CONTEXT_NULL, streamVolume);
            }
            case -2: {
                webViewCallback.error(DeviceError.AUDIOMANAGER_NULL, streamVolume);
            }
        }
    }
    
    @WebViewExposed
    public static void getElapsedRealtime(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getElapsedRealtime());
    }
    
    private static File getFileForStorageType(final StorageType storageType) {
        switch (storageType) {
            default: {
                DeviceLog.error("Unhandled storagetype: " + storageType);
                return null;
            }
            case INTERNAL: {
                return ClientProperties.getApplicationContext().getCacheDir();
            }
            case EXTERNAL: {
                return ClientProperties.getApplicationContext().getExternalCacheDir();
            }
        }
    }
    
    @WebViewExposed
    public static void getFingerprint(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getFingerprint());
    }
    
    @WebViewExposed
    public static void getFreeMemory(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getFreeMemory());
    }
    
    @WebViewExposed
    public static void getFreeSpace(final String s, final WebViewCallback webViewCallback) {
        final StorageType storageTypeFromString = getStorageTypeFromString(s);
        if (storageTypeFromString == null) {
            webViewCallback.error(DeviceError.INVALID_STORAGETYPE, s);
            return;
        }
        final long freeSpace = Device.getFreeSpace(getFileForStorageType(storageTypeFromString));
        if (freeSpace > -1L) {
            webViewCallback.invoke(freeSpace);
            return;
        }
        webViewCallback.error(DeviceError.COULDNT_GET_STORAGE_LOCATION, freeSpace);
    }
    
    @WebViewExposed
    public static void getGLVersion(final WebViewCallback webViewCallback) {
        final String glVersion = Device.getGLVersion();
        if (glVersion != null) {
            webViewCallback.invoke(glVersion);
            return;
        }
        webViewCallback.error(DeviceError.COULDNT_GET_GL_VERSION, new Object[0]);
    }
    
    @WebViewExposed
    public static void getHardware(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getHardware());
    }
    
    @WebViewExposed
    public static void getHeadset(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.isWiredHeadsetOn());
    }
    
    @WebViewExposed
    public static void getHost(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getHost());
    }
    
    @WebViewExposed
    public static void getInstalledPackages(final boolean b, final WebViewCallback webViewCallback) {
        webViewCallback.invoke(new JSONArray((Collection)Device.getInstalledPackages(b)));
    }
    
    @WebViewExposed
    public static void getLimitAdTrackingFlag(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.isLimitAdTrackingEnabled());
    }
    
    @WebViewExposed
    public static void getManufacturer(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getManufacturer());
    }
    
    @WebViewExposed
    public static void getModel(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getModel());
    }
    
    @WebViewExposed
    public static void getNetworkMetered(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getNetworkMetered());
    }
    
    @WebViewExposed
    public static void getNetworkOperator(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getNetworkOperator());
    }
    
    @WebViewExposed
    public static void getNetworkOperatorName(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getNetworkOperatorName());
    }
    
    @WebViewExposed
    public static void getNetworkType(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getNetworkType());
    }
    
    @WebViewExposed
    public static void getOsVersion(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getOsVersion());
    }
    
    @WebViewExposed
    public static void getPackageInfo(final String s, final WebViewCallback webViewCallback) {
        if (ClientProperties.getApplicationContext() != null) {
            final PackageManager packageManager = ClientProperties.getApplicationContext().getPackageManager();
            PackageInfo packageInfo;
            JSONObject jsonObject;
            try {
                packageInfo = packageManager.getPackageInfo(s, 0);
                final JSONObject jsonObject2;
                jsonObject = (jsonObject2 = new JSONObject());
                final String s2 = "installer";
                final PackageManager packageManager2 = packageManager;
                final String s3 = s;
                final String s4 = packageManager2.getInstallerPackageName(s3);
                jsonObject2.put(s2, (Object)s4);
                final JSONObject jsonObject3 = jsonObject;
                final String s5 = "firstInstallTime";
                final PackageInfo packageInfo2 = packageInfo;
                final long n = packageInfo2.firstInstallTime;
                jsonObject3.put(s5, n);
                final JSONObject jsonObject4 = jsonObject;
                final String s6 = "lastUpdateTime";
                final PackageInfo packageInfo3 = packageInfo;
                final long n2 = packageInfo3.lastUpdateTime;
                jsonObject4.put(s6, n2);
                final JSONObject jsonObject5 = jsonObject;
                final String s7 = "versionCode";
                final PackageInfo packageInfo4 = packageInfo;
                final int n3 = packageInfo4.versionCode;
                jsonObject5.put(s7, n3);
                final JSONObject jsonObject6 = jsonObject;
                final String s8 = "versionName";
                final PackageInfo packageInfo5 = packageInfo;
                final String s9 = packageInfo5.versionName;
                jsonObject6.put(s8, (Object)s9);
                final JSONObject jsonObject7 = jsonObject;
                final String s10 = "packageName";
                final PackageInfo packageInfo6 = packageInfo;
                final String s11 = packageInfo6.packageName;
                jsonObject7.put(s10, (Object)s11);
                final WebViewCallback webViewCallback2 = webViewCallback;
                final int n4 = 1;
                final Object[] array = new Object[n4];
                final int n5 = 0;
                final JSONObject jsonObject8 = jsonObject;
                array[n5] = jsonObject8;
                webViewCallback2.invoke(array);
                return;
            }
            catch (PackageManager$NameNotFoundException ex2) {
                webViewCallback.error(DeviceError.APPLICATION_INFO_NOT_AVAILABLE, s);
                return;
            }
            try {
                final JSONObject jsonObject2 = jsonObject;
                final String s2 = "installer";
                final PackageManager packageManager2 = packageManager;
                final String s3 = s;
                final String s4 = packageManager2.getInstallerPackageName(s3);
                jsonObject2.put(s2, (Object)s4);
                final JSONObject jsonObject3 = jsonObject;
                final String s5 = "firstInstallTime";
                final PackageInfo packageInfo2 = packageInfo;
                final long n = packageInfo2.firstInstallTime;
                jsonObject3.put(s5, n);
                final JSONObject jsonObject4 = jsonObject;
                final String s6 = "lastUpdateTime";
                final PackageInfo packageInfo3 = packageInfo;
                final long n2 = packageInfo3.lastUpdateTime;
                jsonObject4.put(s6, n2);
                final JSONObject jsonObject5 = jsonObject;
                final String s7 = "versionCode";
                final PackageInfo packageInfo4 = packageInfo;
                final int n3 = packageInfo4.versionCode;
                jsonObject5.put(s7, n3);
                final JSONObject jsonObject6 = jsonObject;
                final String s8 = "versionName";
                final PackageInfo packageInfo5 = packageInfo;
                final String s9 = packageInfo5.versionName;
                jsonObject6.put(s8, (Object)s9);
                final JSONObject jsonObject7 = jsonObject;
                final String s10 = "packageName";
                final PackageInfo packageInfo6 = packageInfo;
                final String s11 = packageInfo6.packageName;
                jsonObject7.put(s10, (Object)s11);
                final WebViewCallback webViewCallback2 = webViewCallback;
                final int n4 = 1;
                final Object[] array = new Object[n4];
                final int n5 = 0;
                final JSONObject jsonObject8 = jsonObject;
                array[n5] = jsonObject8;
                webViewCallback2.invoke(array);
                return;
            }
            catch (JSONException ex) {
                webViewCallback.error(DeviceError.JSON_ERROR, ex.getMessage());
                return;
            }
        }
        webViewCallback.error(DeviceError.APPLICATION_CONTEXT_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void getProcessInfo(final WebViewCallback webViewCallback) {
        final JSONObject jsonObject = new JSONObject();
        final Map<String, String> processInfo = Device.getProcessInfo();
        while (true) {
            if (processInfo == null) {
                break Label_0074;
            }
            try {
                if (processInfo.containsKey("stat")) {
                    jsonObject.put("stat", (Object)processInfo.get("stat"));
                }
                if (processInfo.containsKey("uptime")) {
                    jsonObject.put("uptime", (Object)processInfo.get("uptime"));
                }
                webViewCallback.invoke(jsonObject);
            }
            catch (Exception ex) {
                DeviceLog.exception("Error while constructing process info", ex);
                continue;
            }
            break;
        }
    }
    
    @WebViewExposed
    public static void getProduct(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getProduct());
    }
    
    @WebViewExposed
    public static void getRingerMode(final WebViewCallback webViewCallback) {
        final int ringerMode = Device.getRingerMode();
        if (ringerMode > -1) {
            webViewCallback.invoke(ringerMode);
            return;
        }
        switch (ringerMode) {
            default: {
                DeviceLog.error("Unhandled ringerMode error: " + ringerMode);
            }
            case -1: {
                webViewCallback.error(DeviceError.APPLICATION_CONTEXT_NULL, ringerMode);
            }
            case -2: {
                webViewCallback.error(DeviceError.AUDIOMANAGER_NULL, ringerMode);
            }
        }
    }
    
    @WebViewExposed
    public static void getScreenBrightness(final WebViewCallback webViewCallback) {
        final int screenBrightness = Device.getScreenBrightness();
        if (screenBrightness > -1) {
            webViewCallback.invoke(screenBrightness);
            return;
        }
        switch (screenBrightness) {
            default: {
                DeviceLog.error("Unhandled screenBrightness error: " + screenBrightness);
            }
            case -1: {
                webViewCallback.error(DeviceError.APPLICATION_CONTEXT_NULL, screenBrightness);
            }
        }
    }
    
    @WebViewExposed
    public static void getScreenDensity(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getScreenDensity());
    }
    
    @WebViewExposed
    public static void getScreenHeight(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getScreenHeight());
    }
    
    @WebViewExposed
    public static void getScreenLayout(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getScreenLayout());
    }
    
    @WebViewExposed
    public static void getScreenWidth(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getScreenWidth());
    }
    
    @WebViewExposed
    public static void getSensorList(final WebViewCallback webViewCallback) {
        final JSONArray jsonArray = new JSONArray();
        final List<Sensor> sensorList = Device.getSensorList();
        if (sensorList != null) {
            for (final Sensor sensor : sensorList) {
                final JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("name", (Object)sensor.getName());
                    jsonObject.put("type", sensor.getType());
                    jsonObject.put("vendor", (Object)sensor.getVendor());
                    jsonObject.put("maximumRange", (double)sensor.getMaximumRange());
                    jsonObject.put("power", (double)sensor.getPower());
                    jsonObject.put("version", sensor.getVersion());
                    jsonObject.put("resolution", (double)sensor.getResolution());
                    jsonObject.put("minDelay", sensor.getMinDelay());
                    jsonArray.put((Object)jsonObject);
                    continue;
                }
                catch (JSONException ex) {
                    webViewCallback.error(DeviceError.JSON_ERROR, ex.getMessage());
                    return;
                }
                break;
            }
        }
        webViewCallback.invoke(jsonArray);
    }
    
    private static StorageType getStorageTypeFromString(final String s) {
        try {
            return StorageType.valueOf(s);
        }
        catch (IllegalArgumentException ex) {
            DeviceLog.exception("Illegal argument: " + s, ex);
            return null;
        }
    }
    
    @WebViewExposed
    public static void getSupportedAbis(final WebViewCallback webViewCallback) {
        final JSONArray jsonArray = new JSONArray();
        final Iterator<String> iterator = Device.getSupportedAbis().iterator();
        while (iterator.hasNext()) {
            jsonArray.put((Object)iterator.next());
        }
        webViewCallback.invoke(jsonArray);
    }
    
    @WebViewExposed
    public static void getSystemLanguage(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Locale.getDefault().toString());
    }
    
    @WebViewExposed
    public static void getSystemProperty(final String s, final String s2, final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getSystemProperty(s, s2));
    }
    
    @WebViewExposed
    public static void getTimeZone(final Boolean b, final WebViewCallback webViewCallback) {
        webViewCallback.invoke(TimeZone.getDefault().getDisplayName(b, 0, Locale.US));
    }
    
    @WebViewExposed
    public static void getTimeZoneOffset(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 1000);
    }
    
    @WebViewExposed
    public static void getTotalMemory(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getTotalMemory());
    }
    
    @WebViewExposed
    public static void getTotalSpace(final String s, final WebViewCallback webViewCallback) {
        final StorageType storageTypeFromString = getStorageTypeFromString(s);
        if (storageTypeFromString == null) {
            webViewCallback.error(DeviceError.INVALID_STORAGETYPE, s);
            return;
        }
        final long totalSpace = Device.getTotalSpace(getFileForStorageType(storageTypeFromString));
        if (totalSpace > -1L) {
            webViewCallback.invoke(totalSpace);
            return;
        }
        webViewCallback.error(DeviceError.COULDNT_GET_STORAGE_LOCATION, totalSpace);
    }
    
    @WebViewExposed
    public static void getUniqueEventId(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getUniqueEventId());
    }
    
    @WebViewExposed
    public static void getUptime(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getUptime());
    }
    
    @WebViewExposed
    public static void isAdbEnabled(final WebViewCallback webViewCallback) {
        final Boolean adbEnabled = Device.isAdbEnabled();
        if (adbEnabled != null) {
            webViewCallback.invoke(adbEnabled);
            return;
        }
        webViewCallback.error(DeviceError.COULDNT_GET_ADB_STATUS, new Object[0]);
    }
    
    @WebViewExposed
    public static void isAppInstalled(final String s, final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.isAppInstalled(s));
    }
    
    @WebViewExposed
    public static void isRooted(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.isRooted());
    }
    
    @WebViewExposed
    public static void isUSBConnected(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.isUSBConnected());
    }
    
    @WebViewExposed
    public static void registerVolumeChangeListener(final Integer n, final WebViewCallback webViewCallback) {
        if (DeviceInfo._volumeChangeListeners == null) {
            DeviceInfo._volumeChangeListeners = (SparseArray<IVolumeChangeListener>)new SparseArray();
        }
        if (DeviceInfo._volumeChangeListeners.get((int)n) == null) {
            final IVolumeChangeListener volumeChangeListener = new IVolumeChangeListener() {
                private int _streamType = n;
                
                @Override
                public int getStreamType() {
                    return this._streamType;
                }
                
                @Override
                public void onVolumeChanged(final int n) {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.DEVICEINFO, DeviceInfoEvent.VOLUME_CHANGED, this.getStreamType(), n, Device.getStreamMaxVolume(this._streamType));
                }
            };
            DeviceInfo._volumeChangeListeners.append((int)n, (Object)volumeChangeListener);
            VolumeChange.registerListener(volumeChangeListener);
        }
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void unregisterVolumeChangeListener(final Integer n, final WebViewCallback webViewCallback) {
        if (DeviceInfo._volumeChangeListeners != null && DeviceInfo._volumeChangeListeners.get((int)n) != null) {
            VolumeChange.unregisterListener((IVolumeChangeListener)DeviceInfo._volumeChangeListeners.get((int)n));
            DeviceInfo._volumeChangeListeners.remove((int)n);
        }
        webViewCallback.invoke(new Object[0]);
    }
    
    public enum DeviceInfoEvent
    {
        VOLUME_CHANGED;
    }
    
    public enum StorageType
    {
        EXTERNAL, 
        INTERNAL;
    }
}
