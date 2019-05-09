package com.adjust.sdk;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.facebook.places.model.PlaceFields;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.tapjoy.TapjoyConstants;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

class DeviceInfo {
    String abi;
    String androidId;
    String apiLevel;
    String appInstallTime;
    String appUpdateTime;
    String appVersion;
    String buildName;
    String clientSdk;
    String country;
    String deviceManufacturer;
    String deviceName;
    String deviceType;
    String displayHeight;
    String displayWidth;
    String fbAttributionId;
    String hardwareName;
    Boolean isTrackingEnabled;
    String language;
    String macSha1;
    String macShortMd5;
    private boolean nonGoogleIdsRead = false;
    String osName;
    String osVersion;
    String packageName;
    String playAdId;
    Map<String, String> pluginKeys;
    String screenDensity;
    String screenFormat;
    String screenSize;
    String vmInstructionSet;

    DeviceInfo(Context context, String sdkPrefix) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        Locale locale = Util.getLocale(configuration);
        int screenLayout = configuration.screenLayout;
        ContentResolver contentResolver = context.getContentResolver();
        reloadDeviceIds(context);
        this.packageName = getPackageName(context);
        this.appVersion = getAppVersion(context);
        this.deviceType = getDeviceType(screenLayout);
        this.deviceName = getDeviceName();
        this.deviceManufacturer = getDeviceManufacturer();
        this.osName = getOsName();
        this.osVersion = getOsVersion();
        this.apiLevel = getApiLevel();
        this.language = getLanguage(locale);
        this.country = getCountry(locale);
        this.screenSize = getScreenSize(screenLayout);
        this.screenFormat = getScreenFormat(screenLayout);
        this.screenDensity = getScreenDensity(displayMetrics);
        this.displayWidth = getDisplayWidth(displayMetrics);
        this.displayHeight = getDisplayHeight(displayMetrics);
        this.clientSdk = getClientSdk(sdkPrefix);
        this.fbAttributionId = getFacebookAttributionId(context);
        this.pluginKeys = Util.getPluginKeys(context);
        this.hardwareName = getHardwareName();
        this.abi = getABI();
        this.buildName = getBuildName();
        this.vmInstructionSet = getVmInstructionSet();
        this.appInstallTime = getAppInstallTime(context);
        this.appUpdateTime = getAppUpdateTime(context);
    }

    void reloadDeviceIds(Context context) {
        this.isTrackingEnabled = Util.isPlayTrackingEnabled(context);
        this.playAdId = Util.getPlayAdId(context);
        if (this.playAdId == null && !this.nonGoogleIdsRead) {
            if (!Util.checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                AdjustFactory.getLogger().warn("Missing permission: ACCESS_WIFI_STATE", new Object[0]);
            }
            String macAddress = Util.getMacAddress(context);
            this.macSha1 = getMacSha1(macAddress);
            this.macShortMd5 = getMacShortMd5(macAddress);
            this.androidId = Util.getAndroidId(context);
            this.nonGoogleIdsRead = true;
        }
    }

    private String getMacAddress(Context context, boolean isGooglePlayServicesAvailable) {
        if (isGooglePlayServicesAvailable) {
            return null;
        }
        if (!Util.checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
            AdjustFactory.getLogger().warn("Missing permission: ACCESS_WIFI_STATE", new Object[0]);
        }
        return Util.getMacAddress(context);
    }

    private String getPackageName(Context context) {
        return context.getPackageName();
    }

    private String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            return null;
        }
    }

    private String getDeviceType(int screenLayout) {
        switch (screenLayout & 15) {
            case 1:
            case 2:
                return PlaceFields.PHONE;
            case 3:
            case 4:
                return "tablet";
            default:
                return null;
        }
    }

    private String getDeviceName() {
        return Build.MODEL;
    }

    private String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    private String getOsName() {
        return TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE;
    }

    private String getOsVersion() {
        return VERSION.RELEASE;
    }

    private String getApiLevel() {
        return "" + VERSION.SDK_INT;
    }

    private String getLanguage(Locale locale) {
        return locale.getLanguage();
    }

    private String getCountry(Locale locale) {
        return locale.getCountry();
    }

    private String getBuildName() {
        return Build.ID;
    }

    private String getHardwareName() {
        return Build.DISPLAY;
    }

    private String getScreenSize(int screenLayout) {
        switch (screenLayout & 15) {
            case 1:
                return Constants.SMALL;
            case 2:
                return "normal";
            case 3:
                return Constants.LARGE;
            case 4:
                return Constants.XLARGE;
            default:
                return null;
        }
    }

    private String getScreenFormat(int screenLayout) {
        switch (screenLayout & 48) {
            case 16:
                return "normal";
            case 32:
                return Constants.LONG;
            default:
                return null;
        }
    }

    private String getScreenDensity(DisplayMetrics displayMetrics) {
        int density = displayMetrics.densityDpi;
        if (density == 0) {
            return null;
        }
        if (density < IronSourceConstants.USING_CACHE_FOR_INIT_EVENT) {
            return Constants.LOW;
        }
        if (density > 200) {
            return Constants.HIGH;
        }
        return Constants.MEDIUM;
    }

    private String getDisplayWidth(DisplayMetrics displayMetrics) {
        return String.valueOf(displayMetrics.widthPixels);
    }

    private String getDisplayHeight(DisplayMetrics displayMetrics) {
        return String.valueOf(displayMetrics.heightPixels);
    }

    private String getClientSdk(String sdkPrefix) {
        if (sdkPrefix == null) {
            return Constants.CLIENT_SDK;
        }
        return Util.formatString("%s@%s", sdkPrefix, Constants.CLIENT_SDK);
    }

    private String getMacSha1(String macAddress) {
        if (macAddress == null) {
            return null;
        }
        return Util.sha1(macAddress);
    }

    private String getMacShortMd5(String macAddress) {
        if (macAddress == null) {
            return null;
        }
        return Util.md5(macAddress.replaceAll(":", ""));
    }

    private String getFacebookAttributionId(Context context) {
        try {
            String columnName = "aid";
            Cursor cursor = context.getContentResolver().query(Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider"), new String[]{"aid"}, null, null, null);
            if (cursor == null) {
                return null;
            }
            if (cursor.moveToFirst()) {
                String attributionId = cursor.getString(cursor.getColumnIndex("aid"));
                cursor.close();
                return attributionId;
            }
            cursor.close();
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private String getABI() {
        String[] SupportedABIS = Util.getSupportedAbis();
        if (SupportedABIS == null || SupportedABIS.length == 0) {
            return Util.getCpuAbi();
        }
        return SupportedABIS[0];
    }

    private String getVmInstructionSet() {
        return Util.getVmInstructionSet();
    }

    private String getAppInstallTime(Context context) {
        try {
            return Util.dateFormatter.format(new Date(context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).firstInstallTime));
        } catch (Exception e) {
            return null;
        }
    }

    private String getAppUpdateTime(Context context) {
        try {
            return Util.dateFormatter.format(new Date(context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).lastUpdateTime));
        } catch (Exception e) {
            return null;
        }
    }
}
