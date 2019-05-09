// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.utils;

import com.ironsource.mediationsdk.sdk.GeneralProperties;
import com.ironsource.mediationsdk.config.ConfigFile;
import android.telephony.TelephonyManager;
import android.annotation.SuppressLint;
import java.util.Iterator;
import android.location.Location;
import android.location.LocationManager;
import java.util.Locale;
import java.util.Calendar;
import java.util.TimeZone;
import android.os.StatFs;
import android.os.Environment;
import android.os.Build;
import android.content.Intent;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Build$VERSION;
import java.io.Serializable;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.environment.ApplicationContext;
import android.text.TextUtils;
import com.ironsource.mediationsdk.IronSourceObject;
import java.util.HashMap;
import java.util.Map;
import android.content.Context;

public class GeneralPropertiesWorker implements Runnable
{
    private static final int MAX_MINUTES_OFFSET = 840;
    private static final int MINUTES_OFFSET_STEP = 15;
    private static final int MIN_MINUTES_OFFSET = -720;
    public static final String SDK_VERSION = "sdkVersion";
    private final String ADVERTISING_ID;
    private final String ADVERTISING_ID_IS_LIMIT_TRACKING;
    private final String ADVERTISING_ID_TYPE;
    private final String ANDROID_OS_VERSION;
    private final String APPLICATION_KEY;
    private final String BATTERY_LEVEL;
    private final String BUNDLE_ID;
    private final String CONNECTION_TYPE;
    private final String DEVICE_MODEL;
    private final String DEVICE_OEM;
    private final String DEVICE_OS;
    private final String EXTERNAL_FREE_MEMORY;
    private final String GMT_MINUTES_OFFSET;
    private final String INTERNAL_FREE_MEMORY;
    private final String KEY_IS_ROOT;
    private final String KEY_PLUGIN_FW_VERSION;
    private final String KEY_PLUGIN_TYPE;
    private final String KEY_PLUGIN_VERSION;
    private final String KEY_SESSION_ID;
    private final String LANGUAGE;
    private final String LOCATION_LAT;
    private final String LOCATION_LON;
    private final String MEDIATION_TYPE;
    private final String MOBILE_CARRIER;
    private final String PUBLISHER_APP_VERSION;
    private final String TAG;
    private Context mContext;
    
    private GeneralPropertiesWorker() {
        this.TAG = this.getClass().getSimpleName();
        this.BUNDLE_ID = "bundleId";
        this.ADVERTISING_ID = "advertisingId";
        this.ADVERTISING_ID_IS_LIMIT_TRACKING = "isLimitAdTrackingEnabled";
        this.APPLICATION_KEY = "appKey";
        this.DEVICE_OS = "deviceOS";
        this.ANDROID_OS_VERSION = "osVersion";
        this.CONNECTION_TYPE = "connectionType";
        this.LANGUAGE = "language";
        this.DEVICE_OEM = "deviceOEM";
        this.DEVICE_MODEL = "deviceModel";
        this.MOBILE_CARRIER = "mobileCarrier";
        this.EXTERNAL_FREE_MEMORY = "externalFreeMemory";
        this.INTERNAL_FREE_MEMORY = "internalFreeMemory";
        this.BATTERY_LEVEL = "battery";
        this.LOCATION_LAT = "lat";
        this.LOCATION_LON = "lon";
        this.GMT_MINUTES_OFFSET = "gmtMinutesOffset";
        this.PUBLISHER_APP_VERSION = "appVersion";
        this.KEY_SESSION_ID = "sessionId";
        this.KEY_PLUGIN_TYPE = "pluginType";
        this.KEY_PLUGIN_VERSION = "pluginVersion";
        this.KEY_PLUGIN_FW_VERSION = "plugin_fw_v";
        this.KEY_IS_ROOT = "jb";
        this.ADVERTISING_ID_TYPE = "advertisingIdType";
        this.MEDIATION_TYPE = "mt";
    }
    
    public GeneralPropertiesWorker(final Context context) {
        this.TAG = this.getClass().getSimpleName();
        this.BUNDLE_ID = "bundleId";
        this.ADVERTISING_ID = "advertisingId";
        this.ADVERTISING_ID_IS_LIMIT_TRACKING = "isLimitAdTrackingEnabled";
        this.APPLICATION_KEY = "appKey";
        this.DEVICE_OS = "deviceOS";
        this.ANDROID_OS_VERSION = "osVersion";
        this.CONNECTION_TYPE = "connectionType";
        this.LANGUAGE = "language";
        this.DEVICE_OEM = "deviceOEM";
        this.DEVICE_MODEL = "deviceModel";
        this.MOBILE_CARRIER = "mobileCarrier";
        this.EXTERNAL_FREE_MEMORY = "externalFreeMemory";
        this.INTERNAL_FREE_MEMORY = "internalFreeMemory";
        this.BATTERY_LEVEL = "battery";
        this.LOCATION_LAT = "lat";
        this.LOCATION_LON = "lon";
        this.GMT_MINUTES_OFFSET = "gmtMinutesOffset";
        this.PUBLISHER_APP_VERSION = "appVersion";
        this.KEY_SESSION_ID = "sessionId";
        this.KEY_PLUGIN_TYPE = "pluginType";
        this.KEY_PLUGIN_VERSION = "pluginVersion";
        this.KEY_PLUGIN_FW_VERSION = "plugin_fw_v";
        this.KEY_IS_ROOT = "jb";
        this.ADVERTISING_ID_TYPE = "advertisingIdType";
        this.MEDIATION_TYPE = "mt";
        this.mContext = context.getApplicationContext();
    }
    
    private Map<String, Object> collectInformation() {
        final HashMap<String, Double> hashMap = new HashMap<String, Double>();
        hashMap.put("sessionId", (Double)IronSourceObject.getInstance().getSessionId());
        final String bundleId = this.getBundleId();
        if (!TextUtils.isEmpty((CharSequence)bundleId)) {
            hashMap.put("bundleId", (Double)bundleId);
            final String publisherApplicationVersion = ApplicationContext.getPublisherApplicationVersion(this.mContext, bundleId);
            if (!TextUtils.isEmpty((CharSequence)publisherApplicationVersion)) {
                hashMap.put("appVersion", (Double)publisherApplicationVersion);
            }
        }
        hashMap.put("appKey", (Double)this.getApplicationKey());
        final String s = "";
        final String s2 = "";
        final boolean b = false;
        Serializable s3 = s;
        while (true) {
            try {
                final String[] advertisingIdInfo = DeviceStatus.getAdvertisingIdInfo(this.mContext);
                Serializable orGenerateOnceUniqueIdentifier = s;
                boolean booleanValue = b;
                if (advertisingIdInfo != null) {
                    orGenerateOnceUniqueIdentifier = s;
                    booleanValue = b;
                    s3 = s;
                    if (advertisingIdInfo.length == 2) {
                        orGenerateOnceUniqueIdentifier = s;
                        s3 = s;
                        if (!TextUtils.isEmpty((CharSequence)advertisingIdInfo[0])) {
                            orGenerateOnceUniqueIdentifier = advertisingIdInfo[0];
                        }
                        s3 = orGenerateOnceUniqueIdentifier;
                        booleanValue = Boolean.valueOf(advertisingIdInfo[1]);
                    }
                }
                if (!TextUtils.isEmpty((CharSequence)orGenerateOnceUniqueIdentifier)) {
                    s3 = "GAID";
                }
                else {
                    final String s4 = (String)(orGenerateOnceUniqueIdentifier = DeviceStatus.getOrGenerateOnceUniqueIdentifier(this.mContext));
                    s3 = s2;
                    if (!TextUtils.isEmpty((CharSequence)s4)) {
                        s3 = "UUID";
                        orGenerateOnceUniqueIdentifier = s4;
                    }
                }
                if (!TextUtils.isEmpty((CharSequence)orGenerateOnceUniqueIdentifier)) {
                    hashMap.put("advertisingId", (Double)orGenerateOnceUniqueIdentifier);
                    hashMap.put("advertisingIdType", (Double)s3);
                    hashMap.put("isLimitAdTrackingEnabled", (Double)(Object)Boolean.valueOf(booleanValue));
                }
                hashMap.put("deviceOS", (Double)this.getDeviceOS());
                if (!TextUtils.isEmpty((CharSequence)this.getAndroidVersion())) {
                    hashMap.put("osVersion", (Double)this.getAndroidVersion());
                }
                final String connectionType = IronSourceUtils.getConnectionType(this.mContext);
                if (!TextUtils.isEmpty((CharSequence)connectionType)) {
                    hashMap.put("connectionType", (Double)connectionType);
                }
                hashMap.put("sdkVersion", (Double)this.getSDKVersion());
                final String language = this.getLanguage();
                if (!TextUtils.isEmpty((CharSequence)language)) {
                    hashMap.put("language", (Double)language);
                }
                final String deviceOEM = this.getDeviceOEM();
                if (!TextUtils.isEmpty((CharSequence)deviceOEM)) {
                    hashMap.put("deviceOEM", (Double)deviceOEM);
                }
                final String deviceModel = this.getDeviceModel();
                if (!TextUtils.isEmpty((CharSequence)deviceModel)) {
                    hashMap.put("deviceModel", (Double)deviceModel);
                }
                final String mobileCarrier = this.getMobileCarrier();
                if (!TextUtils.isEmpty((CharSequence)mobileCarrier)) {
                    hashMap.put("mobileCarrier", (Double)mobileCarrier);
                }
                hashMap.put("internalFreeMemory", Double.valueOf(this.getInternalStorageFreeSize()));
                hashMap.put("externalFreeMemory", Double.valueOf(this.getExternalStorageFreeSize()));
                hashMap.put("battery", Double.valueOf(Integer.valueOf(this.getBatteryLevel())));
                if (IronSourceUtils.getBooleanFromSharedPrefs(this.mContext, "GeneralProperties.ALLOW_LOCATION_SHARED_PREFS_KEY", false)) {
                    final double[] lastKnownLocation = this.getLastKnownLocation();
                    if (lastKnownLocation != null && lastKnownLocation.length == 2) {
                        hashMap.put("lat", lastKnownLocation[0]);
                        hashMap.put("lon", lastKnownLocation[1]);
                    }
                }
                final int gmtMinutesOffset = this.getGmtMinutesOffset();
                if (this.validateGmtMinutesOffset(gmtMinutesOffset)) {
                    hashMap.put("gmtMinutesOffset", Integer.valueOf(gmtMinutesOffset));
                }
                final String pluginType = this.getPluginType();
                if (!TextUtils.isEmpty((CharSequence)pluginType)) {
                    hashMap.put("pluginType", (Double)pluginType);
                }
                final String pluginVersion = this.getPluginVersion();
                if (!TextUtils.isEmpty((CharSequence)pluginVersion)) {
                    hashMap.put("pluginVersion", (Double)pluginVersion);
                }
                final String pluginFrameworkVersion = this.getPluginFrameworkVersion();
                if (!TextUtils.isEmpty((CharSequence)pluginFrameworkVersion)) {
                    hashMap.put("plugin_fw_v", (Double)pluginFrameworkVersion);
                }
                final String value = String.valueOf(DeviceStatus.isRootedDevice());
                if (!TextUtils.isEmpty((CharSequence)value)) {
                    hashMap.put("jb", (Double)value);
                }
                final String mediationType = this.getMediationType();
                if (!TextUtils.isEmpty((CharSequence)mediationType)) {
                    hashMap.put("mt", (Double)mediationType);
                }
                return (Map<String, Object>)hashMap;
            }
            catch (Exception ex) {
                final Serializable orGenerateOnceUniqueIdentifier = s3;
                final boolean booleanValue = b;
                continue;
            }
            break;
        }
    }
    
    private String getAndroidVersion() {
        try {
            return "" + Build$VERSION.SDK_INT + "(" + Build$VERSION.RELEASE + ")";
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    private String getApplicationKey() {
        return IronSourceObject.getInstance().getIronSourceAppKey();
    }
    
    private int getBatteryLevel() {
        int intExtra = 0;
        final int n = -1;
        try {
            final Intent registerReceiver = this.mContext.registerReceiver((BroadcastReceiver)null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
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
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, this.TAG + ":getBatteryLevel()", ex);
            return -1;
        }
    }
    
    private String getBundleId() {
        try {
            return this.mContext.getPackageName();
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    private String getDeviceModel() {
        try {
            return Build.MODEL;
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    private String getDeviceOEM() {
        try {
            return Build.MANUFACTURER;
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    private String getDeviceOS() {
        return "Android";
    }
    
    private long getExternalStorageFreeSize() {
        if (this.isExternalStorageAbvailable()) {
            final StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return statFs.getAvailableBlocks() * (long)statFs.getBlockSize() / 1048576L;
        }
        return -1L;
    }
    
    private int getGmtMinutesOffset() {
        int n = 0;
        try {
            final TimeZone default1 = TimeZone.getDefault();
            n = n;
            return Math.round((float)((n = default1.getOffset(Calendar.getInstance(default1).getTimeInMillis()) / 1000 / 60) / 15)) * 15;
        }
        catch (Exception ex) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, this.TAG + ":getGmtMinutesOffset()", ex);
            return n;
        }
    }
    
    private long getInternalStorageFreeSize() {
        try {
            final StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return statFs.getAvailableBlocks() * (long)statFs.getBlockSize() / 1048576L;
        }
        catch (Exception ex) {
            return -1L;
        }
    }
    
    private String getLanguage() {
        try {
            return Locale.getDefault().getLanguage();
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    @SuppressLint({ "MissingPermission" })
    private double[] getLastKnownLocation() {
        final double[] array = new double[0];
        long time = Long.MIN_VALUE;
        double[] array2 = array;
        try {
            if (this.locationPermissionGranted()) {
                final LocationManager locationManager = (LocationManager)this.mContext.getApplicationContext().getSystemService("location");
                Location location = null;
                final Iterator iterator = locationManager.getAllProviders().iterator();
                while (iterator.hasNext()) {
                    final Location lastKnownLocation = locationManager.getLastKnownLocation((String)iterator.next());
                    if (lastKnownLocation != null && lastKnownLocation.getTime() > time) {
                        location = lastKnownLocation;
                        time = location.getTime();
                    }
                }
                array2 = array;
                if (location != null) {
                    array2 = new double[] { location.getLatitude(), location.getLongitude() };
                }
            }
            return array2;
        }
        catch (Exception ex) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, this.TAG + ":getLastLocation()", ex);
            return new double[0];
        }
    }
    
    private String getMediationType() {
        return IronSourceObject.getInstance().getMediationType();
    }
    
    private String getMobileCarrier() {
        final String s = "";
        try {
            final TelephonyManager telephonyManager = (TelephonyManager)this.mContext.getSystemService("phone");
            String s2 = s;
            if (telephonyManager != null) {
                final String networkOperatorName = telephonyManager.getNetworkOperatorName();
                final boolean equals = networkOperatorName.equals("");
                s2 = s;
                if (!equals) {
                    s2 = networkOperatorName;
                }
            }
            return s2;
        }
        catch (Exception ex) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, this.TAG + ":getMobileCarrier()", ex);
            return "";
        }
    }
    
    private String getPluginFrameworkVersion() {
        try {
            return ConfigFile.getConfigFile().getPluginFrameworkVersion();
        }
        catch (Exception ex) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "getPluginFrameworkVersion()", ex);
            return "";
        }
    }
    
    private String getPluginType() {
        try {
            return ConfigFile.getConfigFile().getPluginType();
        }
        catch (Exception ex) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "getPluginType()", ex);
            return "";
        }
    }
    
    private String getPluginVersion() {
        try {
            return ConfigFile.getConfigFile().getPluginVersion();
        }
        catch (Exception ex) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "getPluginVersion()", ex);
            return "";
        }
    }
    
    private String getSDKVersion() {
        return IronSourceUtils.getSDKVersion();
    }
    
    private boolean isExternalStorageAbvailable() {
        try {
            return Environment.getExternalStorageState().equals("mounted");
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    private boolean locationPermissionGranted() {
        boolean b = false;
        try {
            if (this.mContext.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
                b = true;
            }
            return b;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    private boolean validateGmtMinutesOffset(final int n) {
        return n <= 840 && n >= -720 && n % 15 == 0;
    }
    
    @Override
    public void run() {
        try {
            GeneralProperties.getProperties().putKeys(this.collectInformation());
            IronSourceUtils.saveGeneralProperties(this.mContext, GeneralProperties.getProperties().toJSON());
        }
        catch (Exception ex) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "Thread name = " + this.getClass().getSimpleName(), ex);
        }
    }
}
