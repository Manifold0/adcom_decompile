package com.ironsource.mediationsdk.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.places.model.PlaceFields;
import com.ironsource.environment.ApplicationContext;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.mediationsdk.IronSourceObject;
import com.ironsource.mediationsdk.config.ConfigFile;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.sdk.GeneralProperties;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class GeneralPropertiesWorker implements Runnable {
    private static final int MAX_MINUTES_OFFSET = 840;
    private static final int MINUTES_OFFSET_STEP = 15;
    private static final int MIN_MINUTES_OFFSET = -720;
    public static final String SDK_VERSION = "sdkVersion";
    private final String ADVERTISING_ID = "advertisingId";
    private final String ADVERTISING_ID_IS_LIMIT_TRACKING = RequestParameters.isLAT;
    private final String ADVERTISING_ID_TYPE = "advertisingIdType";
    private final String ANDROID_OS_VERSION = "osVersion";
    private final String APPLICATION_KEY = ServerResponseWrapper.APP_KEY_FIELD;
    private final String BATTERY_LEVEL = "battery";
    private final String BUNDLE_ID = RequestParameters.PACKAGE_NAME;
    private final String CONNECTION_TYPE = RequestParameters.CONNECTION_TYPE;
    private final String DEVICE_MODEL = RequestParameters.DEVICE_MODEL;
    private final String DEVICE_OEM = RequestParameters.DEVICE_OEM;
    private final String DEVICE_OS = "deviceOS";
    private final String EXTERNAL_FREE_MEMORY = "externalFreeMemory";
    private final String GMT_MINUTES_OFFSET = "gmtMinutesOffset";
    private final String INTERNAL_FREE_MEMORY = "internalFreeMemory";
    private final String KEY_IS_ROOT = "jb";
    private final String KEY_PLUGIN_FW_VERSION = "plugin_fw_v";
    private final String KEY_PLUGIN_TYPE = "pluginType";
    private final String KEY_PLUGIN_VERSION = "pluginVersion";
    private final String KEY_SESSION_ID = "sessionId";
    private final String LANGUAGE = "language";
    private final String LOCATION_LAT = "lat";
    private final String LOCATION_LON = "lon";
    private final String MEDIATION_TYPE = "mt";
    private final String MOBILE_CARRIER = RequestParameters.MOBILE_CARRIER;
    private final String PUBLISHER_APP_VERSION = RequestParameters.APPLICATION_VERSION_NAME;
    private final String TAG = getClass().getSimpleName();
    private Context mContext;

    private GeneralPropertiesWorker() {
    }

    public GeneralPropertiesWorker(Context ctx) {
        this.mContext = ctx.getApplicationContext();
    }

    public void run() {
        try {
            GeneralProperties.getProperties().putKeys(collectInformation());
            IronSourceUtils.saveGeneralProperties(this.mContext, GeneralProperties.getProperties().toJSON());
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, "Thread name = " + getClass().getSimpleName(), e);
        }
    }

    private Map<String, Object> collectInformation() {
        Map<String, Object> result = new HashMap();
        result.put("sessionId", IronSourceObject.getInstance().getSessionId());
        String strVal = getBundleId();
        if (!TextUtils.isEmpty(strVal)) {
            result.put(RequestParameters.PACKAGE_NAME, strVal);
            String publAppVersion = ApplicationContext.getPublisherApplicationVersion(this.mContext, strVal);
            if (!TextUtils.isEmpty(publAppVersion)) {
                result.put(RequestParameters.APPLICATION_VERSION_NAME, publAppVersion);
            }
        }
        result.put(ServerResponseWrapper.APP_KEY_FIELD, getApplicationKey());
        String advertisingId = "";
        String advertisingIdType = "";
        boolean isLimitAdTrackingEnabled = false;
        try {
            String[] advertisingIdInfo = DeviceStatus.getAdvertisingIdInfo(this.mContext);
            if (advertisingIdInfo != null && advertisingIdInfo.length == 2) {
                if (!TextUtils.isEmpty(advertisingIdInfo[0])) {
                    advertisingId = advertisingIdInfo[0];
                }
                isLimitAdTrackingEnabled = Boolean.valueOf(advertisingIdInfo[1]).booleanValue();
            }
        } catch (Exception e) {
        }
        if (TextUtils.isEmpty(advertisingId)) {
            advertisingId = DeviceStatus.getOrGenerateOnceUniqueIdentifier(this.mContext);
            if (!TextUtils.isEmpty(advertisingId)) {
                advertisingIdType = IronSourceConstants.TYPE_UUID;
            }
        } else {
            advertisingIdType = IronSourceConstants.TYPE_GAID;
        }
        if (!TextUtils.isEmpty(advertisingId)) {
            result.put("advertisingId", advertisingId);
            result.put("advertisingIdType", advertisingIdType);
            result.put(RequestParameters.isLAT, Boolean.valueOf(isLimitAdTrackingEnabled));
        }
        result.put("deviceOS", getDeviceOS());
        if (!TextUtils.isEmpty(getAndroidVersion())) {
            result.put("osVersion", getAndroidVersion());
        }
        strVal = IronSourceUtils.getConnectionType(this.mContext);
        if (!TextUtils.isEmpty(strVal)) {
            result.put(RequestParameters.CONNECTION_TYPE, strVal);
        }
        result.put(SDK_VERSION, getSDKVersion());
        strVal = getLanguage();
        if (!TextUtils.isEmpty(strVal)) {
            result.put("language", strVal);
        }
        strVal = getDeviceOEM();
        if (!TextUtils.isEmpty(strVal)) {
            result.put(RequestParameters.DEVICE_OEM, strVal);
        }
        strVal = getDeviceModel();
        if (!TextUtils.isEmpty(strVal)) {
            result.put(RequestParameters.DEVICE_MODEL, strVal);
        }
        strVal = getMobileCarrier();
        if (!TextUtils.isEmpty(strVal)) {
            result.put(RequestParameters.MOBILE_CARRIER, strVal);
        }
        result.put("internalFreeMemory", Long.valueOf(getInternalStorageFreeSize()));
        result.put("externalFreeMemory", Long.valueOf(getExternalStorageFreeSize()));
        result.put("battery", Integer.valueOf(getBatteryLevel()));
        if (IronSourceUtils.getBooleanFromSharedPrefs(this.mContext, GeneralProperties.ALLOW_LOCATION_SHARED_PREFS_KEY, false)) {
            double[] lastKnownLocation = getLastKnownLocation();
            if (lastKnownLocation != null && lastKnownLocation.length == 2) {
                result.put("lat", Double.valueOf(lastKnownLocation[0]));
                result.put("lon", Double.valueOf(lastKnownLocation[1]));
            }
        }
        int gmtMinutesOffset = getGmtMinutesOffset();
        if (validateGmtMinutesOffset(gmtMinutesOffset)) {
            result.put("gmtMinutesOffset", Integer.valueOf(gmtMinutesOffset));
        }
        strVal = getPluginType();
        if (!TextUtils.isEmpty(strVal)) {
            result.put("pluginType", strVal);
        }
        strVal = getPluginVersion();
        if (!TextUtils.isEmpty(strVal)) {
            result.put("pluginVersion", strVal);
        }
        strVal = getPluginFrameworkVersion();
        if (!TextUtils.isEmpty(strVal)) {
            result.put("plugin_fw_v", strVal);
        }
        strVal = String.valueOf(DeviceStatus.isRootedDevice());
        if (!TextUtils.isEmpty(strVal)) {
            result.put("jb", strVal);
        }
        strVal = getMediationType();
        if (!TextUtils.isEmpty(strVal)) {
            result.put("mt", strVal);
        }
        return result;
    }

    private String getPluginType() {
        String result = "";
        try {
            result = ConfigFile.getConfigFile().getPluginType();
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, "getPluginType()", e);
        }
        return result;
    }

    private String getPluginVersion() {
        String result = "";
        try {
            result = ConfigFile.getConfigFile().getPluginVersion();
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, "getPluginVersion()", e);
        }
        return result;
    }

    private String getPluginFrameworkVersion() {
        String result = "";
        try {
            result = ConfigFile.getConfigFile().getPluginFrameworkVersion();
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, "getPluginFrameworkVersion()", e);
        }
        return result;
    }

    private String getBundleId() {
        try {
            return this.mContext.getPackageName();
        } catch (Exception e) {
            return "";
        }
    }

    private String getApplicationKey() {
        return IronSourceObject.getInstance().getIronSourceAppKey();
    }

    private String getDeviceOS() {
        return Constants.JAVASCRIPT_INTERFACE_NAME;
    }

    private String getAndroidVersion() {
        try {
            return "" + VERSION.SDK_INT + "(" + VERSION.RELEASE + ")";
        } catch (Exception e) {
            return "";
        }
    }

    private String getSDKVersion() {
        return IronSourceUtils.getSDKVersion();
    }

    private String getLanguage() {
        try {
            return Locale.getDefault().getLanguage();
        } catch (Exception e) {
            return "";
        }
    }

    private String getDeviceOEM() {
        try {
            return Build.MANUFACTURER;
        } catch (Exception e) {
            return "";
        }
    }

    private String getDeviceModel() {
        try {
            return Build.MODEL;
        } catch (Exception e) {
            return "";
        }
    }

    private String getMobileCarrier() {
        String ret = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService(PlaceFields.PHONE);
            if (telephonyManager == null) {
                return ret;
            }
            String operatorName = telephonyManager.getNetworkOperatorName();
            if (operatorName.equals("")) {
                return ret;
            }
            return operatorName;
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, this.TAG + ":getMobileCarrier()", e);
            return ret;
        }
    }

    private boolean isExternalStorageAbvailable() {
        try {
            return Environment.getExternalStorageState().equals("mounted");
        } catch (Exception e) {
            return false;
        }
    }

    private long getInternalStorageFreeSize() {
        try {
            StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
            return (((long) stat.getAvailableBlocks()) * ((long) stat.getBlockSize())) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        } catch (Exception e) {
            return -1;
        }
    }

    private long getExternalStorageFreeSize() {
        if (!isExternalStorageAbvailable()) {
            return -1;
        }
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return (((long) stat.getAvailableBlocks()) * ((long) stat.getBlockSize())) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
    }

    private int getBatteryLevel() {
        int scale = 0;
        try {
            int level;
            Intent batteryIntent = this.mContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
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
            IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, this.TAG + ":getBatteryLevel()", e);
            return -1;
        }
    }

    @SuppressLint({"MissingPermission"})
    private double[] getLastKnownLocation() {
        double[] result = new double[0];
        long bestLocationTime = Long.MIN_VALUE;
        try {
            if (!locationPermissionGranted()) {
                return result;
            }
            LocationManager locationManager = (LocationManager) this.mContext.getApplicationContext().getSystemService(PlaceFields.LOCATION);
            Location bestLocation = null;
            for (String provider : locationManager.getAllProviders()) {
                Location location = locationManager.getLastKnownLocation(provider);
                if (location != null && location.getTime() > bestLocationTime) {
                    bestLocation = location;
                    bestLocationTime = bestLocation.getTime();
                }
            }
            if (bestLocation == null) {
                return result;
            }
            double lat = bestLocation.getLatitude();
            double lon = bestLocation.getLongitude();
            return new double[]{lat, lon};
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, this.TAG + ":getLastLocation()", e);
            return new double[0];
        }
    }

    private boolean locationPermissionGranted() {
        try {
            if (this.mContext.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private int getGmtMinutesOffset() {
        int result = 0;
        try {
            TimeZone tz = TimeZone.getDefault();
            result = (tz.getOffset(GregorianCalendar.getInstance(tz).getTimeInMillis()) / 1000) / 60;
            return Math.round((float) (result / 15)) * 15;
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, this.TAG + ":getGmtMinutesOffset()", e);
            return result;
        }
    }

    private boolean validateGmtMinutesOffset(int offset) {
        return offset <= MAX_MINUTES_OFFSET && offset >= MIN_MINUTES_OFFSET && offset % 15 == 0;
    }

    private String getMediationType() {
        return IronSourceObject.getInstance().getMediationType();
    }
}
