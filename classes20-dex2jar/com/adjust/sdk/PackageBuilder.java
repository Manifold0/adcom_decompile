// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import android.content.ContentResolver;
import android.telephony.TelephonyManager;
import java.util.HashMap;
import java.util.Iterator;
import android.text.TextUtils;
import org.json.JSONObject;
import java.util.Date;
import java.util.Map;

class PackageBuilder
{
    private static ILogger logger;
    private ActivityStateCopy activityStateCopy;
    private AdjustConfig adjustConfig;
    AdjustAttribution attribution;
    long clickTimeInMilliseconds;
    long clicktTimeInSeconds;
    private long createdAt;
    String deeplink;
    private DeviceInfo deviceInfo;
    Map<String, String> extraParameters;
    long installBeginTimeInSeconds;
    String rawReferrer;
    String referrer;
    String reftag;
    private SessionParameters sessionParameters;
    
    static {
        PackageBuilder.logger = AdjustFactory.getLogger();
    }
    
    public PackageBuilder(final AdjustConfig adjustConfig, final DeviceInfo deviceInfo, final ActivityState activityState, final SessionParameters sessionParameters, final long createdAt) {
        this.clickTimeInMilliseconds = -1L;
        this.clicktTimeInSeconds = -1L;
        this.installBeginTimeInSeconds = -1L;
        this.adjustConfig = adjustConfig;
        this.deviceInfo = deviceInfo;
        this.activityStateCopy = new ActivityStateCopy(activityState);
        this.sessionParameters = sessionParameters;
        this.createdAt = createdAt;
    }
    
    public static void addBoolean(final Map<String, String> map, final String s, final Boolean b) {
        if (b == null) {
            return;
        }
        boolean b2;
        if (b) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        addLong(map, s, b2 ? 1 : 0);
    }
    
    public static void addDate(final Map<String, String> map, final String s, final Date date) {
        if (date == null) {
            return;
        }
        addString(map, s, Util.dateFormatter.format(date));
    }
    
    public static void addDateInMilliseconds(final Map<String, String> map, final String s, final long n) {
        if (n <= 0L) {
            return;
        }
        addDate(map, s, new Date(n));
    }
    
    public static void addDateInSeconds(final Map<String, String> map, final String s, final long n) {
        if (n <= 0L) {
            return;
        }
        addDate(map, s, new Date(1000L * n));
    }
    
    public static void addDouble(final Map<String, String> map, final String s, final Double n) {
        if (n == null) {
            return;
        }
        addString(map, s, Util.formatString("%.5f", n));
    }
    
    public static void addDuration(final Map<String, String> map, final String s, final long n) {
        if (n < 0L) {
            return;
        }
        addLong(map, s, (500L + n) / 1000L);
    }
    
    public static void addLong(final Map<String, String> map, final String s, final long n) {
        if (n < 0L) {
            return;
        }
        addString(map, s, Long.toString(n));
    }
    
    public static void addMapJson(final Map<String, String> map, final String s, final Map<String, String> map2) {
        if (map2 != null && map2.size() != 0) {
            addString(map, s, new JSONObject((Map)map2).toString());
        }
    }
    
    public static void addString(final Map<String, String> map, final String s, final String s2) {
        if (TextUtils.isEmpty((CharSequence)s2)) {
            return;
        }
        map.put(s, s2);
    }
    
    private void checkDeviceIds(final Map<String, String> map) {
        if (!map.containsKey("mac_sha1") && !map.containsKey("mac_md5") && !map.containsKey("android_id") && !map.containsKey("gps_adid")) {
            PackageBuilder.logger.error("Missing device id's. Please check if Proguard is correctly set with Adjust SDK", new Object[0]);
        }
    }
    
    private void fillPluginKeys(final Map<String, String> map) {
        if (this.deviceInfo.pluginKeys != null) {
            for (final Map.Entry<String, String> entry : this.deviceInfo.pluginKeys.entrySet()) {
                addString(map, entry.getKey(), entry.getValue());
            }
        }
    }
    
    private Map<String, String> getAttributableParameters(final boolean b) {
        final Map<String, String> defaultParameters = this.getDefaultParameters();
        addDuration(defaultParameters, "last_interval", this.activityStateCopy.lastInterval);
        addString(defaultParameters, "default_tracker", this.adjustConfig.defaultTracker);
        addString(defaultParameters, "installed_at", this.deviceInfo.appInstallTime);
        addString(defaultParameters, "updated_at", this.deviceInfo.appUpdateTime);
        if (!b) {
            addMapJson(defaultParameters, "callback_params", this.sessionParameters.callbackParameters);
            addMapJson(defaultParameters, "partner_params", this.sessionParameters.partnerParameters);
        }
        return defaultParameters;
    }
    
    private ActivityPackage getDefaultActivityPackage(final ActivityKind activityKind) {
        final ActivityPackage activityPackage = new ActivityPackage(activityKind);
        activityPackage.setClientSdk(this.deviceInfo.clientSdk);
        return activityPackage;
    }
    
    private Map<String, String> getDefaultParameters() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        this.injectDeviceInfo(hashMap);
        this.injectConfig(hashMap);
        this.injectActivityState(hashMap);
        this.injectCommonParameters(hashMap);
        this.checkDeviceIds(hashMap);
        return hashMap;
    }
    
    private String getEventSuffix(final AdjustEvent adjustEvent) {
        if (adjustEvent.revenue == null) {
            return Util.formatString("'%s'", adjustEvent.eventToken);
        }
        return Util.formatString("(%.5f %s, '%s')", adjustEvent.revenue, adjustEvent.currency, adjustEvent.eventToken);
    }
    
    private Map<String, String> getIdsParameters() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        this.injectDeviceInfoIds(hashMap);
        this.injectConfig(hashMap);
        this.injectCommonParameters(hashMap);
        this.checkDeviceIds(hashMap);
        return hashMap;
    }
    
    private void injectActivityState(final Map<String, String> map) {
        addString(map, "android_uuid", this.activityStateCopy.uuid);
        addLong(map, "session_count", this.activityStateCopy.sessionCount);
        addLong(map, "subsession_count", this.activityStateCopy.subsessionCount);
        addDuration(map, "session_length", this.activityStateCopy.sessionLength);
        addDuration(map, "time_spent", this.activityStateCopy.timeSpent);
    }
    
    private void injectAttribution(final Map<String, String> map) {
        if (this.attribution == null) {
            return;
        }
        addString(map, "tracker", this.attribution.trackerName);
        addString(map, "campaign", this.attribution.campaign);
        addString(map, "adgroup", this.attribution.adgroup);
        addString(map, "creative", this.attribution.creative);
    }
    
    private void injectCommonParameters(final Map<String, String> map) {
        addDateInMilliseconds(map, "created_at", this.createdAt);
        addBoolean(map, "attribution_deeplink", true);
        addBoolean(map, "needs_response_details", true);
    }
    
    private void injectConfig(final Map<String, String> map) {
        addString(map, "app_token", this.adjustConfig.appToken);
        addString(map, "environment", this.adjustConfig.environment);
        addBoolean(map, "device_known", this.adjustConfig.deviceKnown);
        addBoolean(map, "event_buffering_enabled", this.adjustConfig.eventBufferingEnabled);
        addString(map, "push_token", this.activityStateCopy.pushToken);
        final ContentResolver contentResolver = this.adjustConfig.context.getContentResolver();
        addString(map, "fire_adid", Util.getFireAdvertisingId(contentResolver));
        addBoolean(map, "fire_tracking_enabled", Util.getFireTrackingEnabled(contentResolver));
        addString(map, "secret_id", this.adjustConfig.secretId);
        addString(map, "app_secret", this.adjustConfig.appSecret);
        if (this.adjustConfig.readMobileEquipmentIdentity) {
            final TelephonyManager telephonyManager = (TelephonyManager)this.adjustConfig.context.getSystemService("phone");
            addString(map, "device_ids", Util.getTelephonyIds(telephonyManager));
            addString(map, "imeis", Util.getIMEIs(telephonyManager));
            addString(map, "meids", Util.getMEIDs(telephonyManager));
        }
    }
    
    private void injectDeviceInfo(final Map<String, String> map) {
        this.injectDeviceInfoIds(map);
        addString(map, "fb_id", this.deviceInfo.fbAttributionId);
        addString(map, "package_name", this.deviceInfo.packageName);
        addString(map, "app_version", this.deviceInfo.appVersion);
        addString(map, "device_type", this.deviceInfo.deviceType);
        addString(map, "device_name", this.deviceInfo.deviceName);
        addString(map, "device_manufacturer", this.deviceInfo.deviceManufacturer);
        addString(map, "os_name", this.deviceInfo.osName);
        addString(map, "os_version", this.deviceInfo.osVersion);
        addString(map, "api_level", this.deviceInfo.apiLevel);
        addString(map, "language", this.deviceInfo.language);
        addString(map, "country", this.deviceInfo.country);
        addString(map, "screen_size", this.deviceInfo.screenSize);
        addString(map, "screen_format", this.deviceInfo.screenFormat);
        addString(map, "screen_density", this.deviceInfo.screenDensity);
        addString(map, "display_width", this.deviceInfo.displayWidth);
        addString(map, "display_height", this.deviceInfo.displayHeight);
        addString(map, "hardware_name", this.deviceInfo.hardwareName);
        addString(map, "cpu_type", this.deviceInfo.abi);
        addString(map, "os_build", this.deviceInfo.buildName);
        addString(map, "vm_isa", this.deviceInfo.vmInstructionSet);
        addString(map, "mcc", Util.getMcc(this.adjustConfig.context));
        addString(map, "mnc", Util.getMnc(this.adjustConfig.context));
        addLong(map, "connectivity_type", Util.getConnectivityType(this.adjustConfig.context));
        addLong(map, "network_type", Util.getNetworkType(this.adjustConfig.context));
        this.fillPluginKeys(map);
    }
    
    private void injectDeviceInfoIds(final Map<String, String> map) {
        this.deviceInfo.reloadDeviceIds(this.adjustConfig.context);
        addBoolean(map, "tracking_enabled", this.deviceInfo.isTrackingEnabled);
        addString(map, "gps_adid", this.deviceInfo.playAdId);
        if (this.deviceInfo.playAdId == null) {
            addString(map, "mac_sha1", this.deviceInfo.macSha1);
            addString(map, "mac_md5", this.deviceInfo.macShortMd5);
            addString(map, "android_id", this.deviceInfo.androidId);
        }
    }
    
    public ActivityPackage buildAttributionPackage() {
        final Map<String, String> idsParameters = this.getIdsParameters();
        final ActivityPackage defaultActivityPackage = this.getDefaultActivityPackage(ActivityKind.ATTRIBUTION);
        defaultActivityPackage.setPath("attribution");
        defaultActivityPackage.setSuffix("");
        defaultActivityPackage.setParameters(idsParameters);
        return defaultActivityPackage;
    }
    
    public ActivityPackage buildClickPackage(final String s) {
        final Map<String, String> attributableParameters = this.getAttributableParameters(false);
        addString(attributableParameters, "source", s);
        addDateInMilliseconds(attributableParameters, "click_time", this.clickTimeInMilliseconds);
        addString(attributableParameters, "reftag", this.reftag);
        addMapJson(attributableParameters, "params", this.extraParameters);
        addString(attributableParameters, "referrer", this.referrer);
        addString(attributableParameters, "raw_referrer", this.rawReferrer);
        addString(attributableParameters, "deeplink", this.deeplink);
        addDateInSeconds(attributableParameters, "click_time", this.clicktTimeInSeconds);
        addDateInSeconds(attributableParameters, "install_begin_time", this.installBeginTimeInSeconds);
        this.injectAttribution(attributableParameters);
        final ActivityPackage defaultActivityPackage = this.getDefaultActivityPackage(ActivityKind.CLICK);
        defaultActivityPackage.setPath("/sdk_click");
        defaultActivityPackage.setSuffix("");
        defaultActivityPackage.setClickTimeInMilliseconds(this.clickTimeInMilliseconds);
        defaultActivityPackage.setClickTimeInSeconds(this.clicktTimeInSeconds);
        defaultActivityPackage.setInstallBeginTimeInSeconds(this.installBeginTimeInSeconds);
        defaultActivityPackage.setParameters(attributableParameters);
        return defaultActivityPackage;
    }
    
    public ActivityPackage buildEventPackage(final AdjustEvent adjustEvent, final boolean b) {
        final Map<String, String> defaultParameters = this.getDefaultParameters();
        addLong(defaultParameters, "event_count", this.activityStateCopy.eventCount);
        addString(defaultParameters, "event_token", adjustEvent.eventToken);
        addDouble(defaultParameters, "revenue", adjustEvent.revenue);
        addString(defaultParameters, "currency", adjustEvent.currency);
        if (!b) {
            addMapJson(defaultParameters, "callback_params", Util.mergeParameters(this.sessionParameters.callbackParameters, adjustEvent.callbackParameters, "Callback"));
            addMapJson(defaultParameters, "partner_params", Util.mergeParameters(this.sessionParameters.partnerParameters, adjustEvent.partnerParameters, "Partner"));
        }
        final ActivityPackage defaultActivityPackage = this.getDefaultActivityPackage(ActivityKind.EVENT);
        defaultActivityPackage.setPath("/event");
        defaultActivityPackage.setSuffix(this.getEventSuffix(adjustEvent));
        defaultActivityPackage.setParameters(defaultParameters);
        if (b) {
            defaultActivityPackage.setCallbackParameters(adjustEvent.callbackParameters);
            defaultActivityPackage.setPartnerParameters(adjustEvent.partnerParameters);
        }
        return defaultActivityPackage;
    }
    
    public ActivityPackage buildInfoPackage(final String s) {
        final Map<String, String> idsParameters = this.getIdsParameters();
        addString(idsParameters, "source", s);
        final ActivityPackage defaultActivityPackage = this.getDefaultActivityPackage(ActivityKind.INFO);
        defaultActivityPackage.setPath("/sdk_info");
        defaultActivityPackage.setSuffix("");
        defaultActivityPackage.setParameters(idsParameters);
        return defaultActivityPackage;
    }
    
    public ActivityPackage buildSessionPackage(final boolean b) {
        final Map<String, String> attributableParameters = this.getAttributableParameters(b);
        final ActivityPackage defaultActivityPackage = this.getDefaultActivityPackage(ActivityKind.SESSION);
        defaultActivityPackage.setPath("/session");
        defaultActivityPackage.setSuffix("");
        defaultActivityPackage.setParameters(attributableParameters);
        return defaultActivityPackage;
    }
    
    private class ActivityStateCopy
    {
        int eventCount;
        long lastInterval;
        String pushToken;
        int sessionCount;
        long sessionLength;
        int subsessionCount;
        long timeSpent;
        String uuid;
        
        ActivityStateCopy(final ActivityState activityState) {
            this.lastInterval = -1L;
            this.eventCount = -1;
            this.uuid = null;
            this.sessionCount = -1;
            this.subsessionCount = -1;
            this.sessionLength = -1L;
            this.timeSpent = -1L;
            this.pushToken = null;
            if (activityState == null) {
                return;
            }
            this.lastInterval = activityState.lastInterval;
            this.eventCount = activityState.eventCount;
            this.uuid = activityState.uuid;
            this.sessionCount = activityState.sessionCount;
            this.subsessionCount = activityState.subsessionCount;
            this.sessionLength = activityState.sessionLength;
            this.timeSpent = activityState.timeSpent;
            this.pushToken = activityState.pushToken;
        }
    }
}
