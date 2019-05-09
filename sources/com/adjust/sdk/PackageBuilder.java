package com.adjust.sdk;

import android.content.ContentResolver;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.facebook.places.model.PlaceFields;
import com.facebook.share.internal.ShareConstants;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.kongregate.p000o.p005b.C0603c;
import com.tapjoy.TapjoyConstants;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

class PackageBuilder {
    private static ILogger logger = AdjustFactory.getLogger();
    private ActivityStateCopy activityStateCopy;
    private AdjustConfig adjustConfig;
    AdjustAttribution attribution;
    long clickTimeInMilliseconds = -1;
    long clicktTimeInSeconds = -1;
    private long createdAt;
    String deeplink;
    private DeviceInfo deviceInfo;
    Map<String, String> extraParameters;
    long installBeginTimeInSeconds = -1;
    String rawReferrer;
    String referrer;
    String reftag;
    private SessionParameters sessionParameters;

    private class ActivityStateCopy {
        int eventCount = -1;
        long lastInterval = -1;
        String pushToken = null;
        int sessionCount = -1;
        long sessionLength = -1;
        int subsessionCount = -1;
        long timeSpent = -1;
        String uuid = null;

        ActivityStateCopy(ActivityState activityState) {
            if (activityState != null) {
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

    public PackageBuilder(AdjustConfig adjustConfig, DeviceInfo deviceInfo, ActivityState activityState, SessionParameters sessionParameters, long createdAt) {
        this.adjustConfig = adjustConfig;
        this.deviceInfo = deviceInfo;
        this.activityStateCopy = new ActivityStateCopy(activityState);
        this.sessionParameters = sessionParameters;
        this.createdAt = createdAt;
    }

    public ActivityPackage buildSessionPackage(boolean isInDelay) {
        Map<String, String> parameters = getAttributableParameters(isInDelay);
        ActivityPackage sessionPackage = getDefaultActivityPackage(ActivityKind.SESSION);
        sessionPackage.setPath("/session");
        sessionPackage.setSuffix("");
        sessionPackage.setParameters(parameters);
        return sessionPackage;
    }

    public ActivityPackage buildEventPackage(AdjustEvent event, boolean isInDelay) {
        Map<String, String> parameters = getDefaultParameters();
        addLong(parameters, "event_count", (long) this.activityStateCopy.eventCount);
        addString(parameters, "event_token", event.eventToken);
        addDouble(parameters, "revenue", event.revenue);
        addString(parameters, "currency", event.currency);
        if (!isInDelay) {
            addMapJson(parameters, Constants.CALLBACK_PARAMETERS, Util.mergeParameters(this.sessionParameters.callbackParameters, event.callbackParameters, "Callback"));
            addMapJson(parameters, Constants.PARTNER_PARAMETERS, Util.mergeParameters(this.sessionParameters.partnerParameters, event.partnerParameters, "Partner"));
        }
        ActivityPackage eventPackage = getDefaultActivityPackage(ActivityKind.EVENT);
        eventPackage.setPath("/event");
        eventPackage.setSuffix(getEventSuffix(event));
        eventPackage.setParameters(parameters);
        if (isInDelay) {
            eventPackage.setCallbackParameters(event.callbackParameters);
            eventPackage.setPartnerParameters(event.partnerParameters);
        }
        return eventPackage;
    }

    public ActivityPackage buildClickPackage(String source) {
        Map<String, String> parameters = getAttributableParameters(false);
        addString(parameters, ShareConstants.FEED_SOURCE_PARAM, source);
        addDateInMilliseconds(parameters, "click_time", this.clickTimeInMilliseconds);
        addString(parameters, Constants.REFTAG, this.reftag);
        addMapJson(parameters, NativeProtocol.WEB_DIALOG_PARAMS, this.extraParameters);
        addString(parameters, "referrer", this.referrer);
        addString(parameters, "raw_referrer", this.rawReferrer);
        addString(parameters, Constants.DEEPLINK, this.deeplink);
        addDateInSeconds(parameters, "click_time", this.clicktTimeInSeconds);
        addDateInSeconds(parameters, "install_begin_time", this.installBeginTimeInSeconds);
        injectAttribution(parameters);
        ActivityPackage clickPackage = getDefaultActivityPackage(ActivityKind.CLICK);
        clickPackage.setPath("/sdk_click");
        clickPackage.setSuffix("");
        clickPackage.setClickTimeInMilliseconds(this.clickTimeInMilliseconds);
        clickPackage.setClickTimeInSeconds(this.clicktTimeInSeconds);
        clickPackage.setInstallBeginTimeInSeconds(this.installBeginTimeInSeconds);
        clickPackage.setParameters(parameters);
        return clickPackage;
    }

    public ActivityPackage buildInfoPackage(String source) {
        Map<String, String> parameters = getIdsParameters();
        addString(parameters, ShareConstants.FEED_SOURCE_PARAM, source);
        ActivityPackage clickPackage = getDefaultActivityPackage(ActivityKind.INFO);
        clickPackage.setPath("/sdk_info");
        clickPackage.setSuffix("");
        clickPackage.setParameters(parameters);
        return clickPackage;
    }

    public ActivityPackage buildAttributionPackage() {
        Map<String, String> parameters = getIdsParameters();
        ActivityPackage attributionPackage = getDefaultActivityPackage(ActivityKind.ATTRIBUTION);
        attributionPackage.setPath("attribution");
        attributionPackage.setSuffix("");
        attributionPackage.setParameters(parameters);
        return attributionPackage;
    }

    private ActivityPackage getDefaultActivityPackage(ActivityKind activityKind) {
        ActivityPackage activityPackage = new ActivityPackage(activityKind);
        activityPackage.setClientSdk(this.deviceInfo.clientSdk);
        return activityPackage;
    }

    private Map<String, String> getAttributableParameters(boolean isInDelay) {
        Map<String, String> parameters = getDefaultParameters();
        addDuration(parameters, "last_interval", this.activityStateCopy.lastInterval);
        addString(parameters, "default_tracker", this.adjustConfig.defaultTracker);
        addString(parameters, "installed_at", this.deviceInfo.appInstallTime);
        addString(parameters, C0603c.f894k, this.deviceInfo.appUpdateTime);
        if (!isInDelay) {
            addMapJson(parameters, Constants.CALLBACK_PARAMETERS, this.sessionParameters.callbackParameters);
            addMapJson(parameters, Constants.PARTNER_PARAMETERS, this.sessionParameters.partnerParameters);
        }
        return parameters;
    }

    private Map<String, String> getDefaultParameters() {
        Map<String, String> parameters = new HashMap();
        injectDeviceInfo(parameters);
        injectConfig(parameters);
        injectActivityState(parameters);
        injectCommonParameters(parameters);
        checkDeviceIds(parameters);
        return parameters;
    }

    private Map<String, String> getIdsParameters() {
        Map<String, String> parameters = new HashMap();
        injectDeviceInfoIds(parameters);
        injectConfig(parameters);
        injectCommonParameters(parameters);
        checkDeviceIds(parameters);
        return parameters;
    }

    private void injectDeviceInfo(Map<String, String> parameters) {
        injectDeviceInfoIds(parameters);
        addString(parameters, "fb_id", this.deviceInfo.fbAttributionId);
        addString(parameters, "package_name", this.deviceInfo.packageName);
        addString(parameters, TapjoyConstants.TJC_APP_VERSION_NAME, this.deviceInfo.appVersion);
        addString(parameters, TapjoyConstants.TJC_DEVICE_TYPE_NAME, this.deviceInfo.deviceType);
        addString(parameters, TapjoyConstants.TJC_DEVICE_NAME, this.deviceInfo.deviceName);
        addString(parameters, TapjoyConstants.TJC_DEVICE_MANUFACTURER, this.deviceInfo.deviceManufacturer);
        addString(parameters, "os_name", this.deviceInfo.osName);
        addString(parameters, TapjoyConstants.TJC_DEVICE_OS_VERSION_NAME, this.deviceInfo.osVersion);
        addString(parameters, "api_level", this.deviceInfo.apiLevel);
        addString(parameters, "language", this.deviceInfo.language);
        addString(parameters, "country", this.deviceInfo.country);
        addString(parameters, "screen_size", this.deviceInfo.screenSize);
        addString(parameters, "screen_format", this.deviceInfo.screenFormat);
        addString(parameters, TapjoyConstants.TJC_DEVICE_SCREEN_DENSITY, this.deviceInfo.screenDensity);
        addString(parameters, "display_width", this.deviceInfo.displayWidth);
        addString(parameters, "display_height", this.deviceInfo.displayHeight);
        addString(parameters, "hardware_name", this.deviceInfo.hardwareName);
        addString(parameters, "cpu_type", this.deviceInfo.abi);
        addString(parameters, "os_build", this.deviceInfo.buildName);
        addString(parameters, "vm_isa", this.deviceInfo.vmInstructionSet);
        addString(parameters, RequestParameters.NETWORK_MCC, Util.getMcc(this.adjustConfig.context));
        addString(parameters, RequestParameters.NETWORK_MNC, Util.getMnc(this.adjustConfig.context));
        addLong(parameters, "connectivity_type", (long) Util.getConnectivityType(this.adjustConfig.context));
        addLong(parameters, "network_type", (long) Util.getNetworkType(this.adjustConfig.context));
        fillPluginKeys(parameters);
    }

    private void injectDeviceInfoIds(Map<String, String> parameters) {
        this.deviceInfo.reloadDeviceIds(this.adjustConfig.context);
        addBoolean(parameters, "tracking_enabled", this.deviceInfo.isTrackingEnabled);
        addString(parameters, "gps_adid", this.deviceInfo.playAdId);
        if (this.deviceInfo.playAdId == null) {
            addString(parameters, "mac_sha1", this.deviceInfo.macSha1);
            addString(parameters, "mac_md5", this.deviceInfo.macShortMd5);
            addString(parameters, TapjoyConstants.TJC_ANDROID_ID, this.deviceInfo.androidId);
        }
    }

    private void injectConfig(Map<String, String> parameters) {
        addString(parameters, "app_token", this.adjustConfig.appToken);
        addString(parameters, "environment", this.adjustConfig.environment);
        addBoolean(parameters, "device_known", this.adjustConfig.deviceKnown);
        addBoolean(parameters, "event_buffering_enabled", Boolean.valueOf(this.adjustConfig.eventBufferingEnabled));
        addString(parameters, "push_token", this.activityStateCopy.pushToken);
        ContentResolver contentResolver = this.adjustConfig.context.getContentResolver();
        addString(parameters, "fire_adid", Util.getFireAdvertisingId(contentResolver));
        addBoolean(parameters, "fire_tracking_enabled", Util.getFireTrackingEnabled(contentResolver));
        addString(parameters, "secret_id", this.adjustConfig.secretId);
        addString(parameters, "app_secret", this.adjustConfig.appSecret);
        if (this.adjustConfig.readMobileEquipmentIdentity) {
            TelephonyManager telephonyManager = (TelephonyManager) this.adjustConfig.context.getSystemService(PlaceFields.PHONE);
            addString(parameters, "device_ids", Util.getTelephonyIds(telephonyManager));
            addString(parameters, "imeis", Util.getIMEIs(telephonyManager));
            addString(parameters, "meids", Util.getMEIDs(telephonyManager));
        }
    }

    private void injectActivityState(Map<String, String> parameters) {
        addString(parameters, "android_uuid", this.activityStateCopy.uuid);
        addLong(parameters, "session_count", (long) this.activityStateCopy.sessionCount);
        addLong(parameters, "subsession_count", (long) this.activityStateCopy.subsessionCount);
        addDuration(parameters, "session_length", this.activityStateCopy.sessionLength);
        addDuration(parameters, "time_spent", this.activityStateCopy.timeSpent);
    }

    private void injectCommonParameters(Map<String, String> parameters) {
        addDateInMilliseconds(parameters, "created_at", this.createdAt);
        addBoolean(parameters, "attribution_deeplink", Boolean.valueOf(true));
        addBoolean(parameters, "needs_response_details", Boolean.valueOf(true));
    }

    private void injectAttribution(Map<String, String> parameters) {
        if (this.attribution != null) {
            addString(parameters, "tracker", this.attribution.trackerName);
            addString(parameters, "campaign", this.attribution.campaign);
            addString(parameters, "adgroup", this.attribution.adgroup);
            addString(parameters, "creative", this.attribution.creative);
        }
    }

    private void checkDeviceIds(Map<String, String> parameters) {
        if (!parameters.containsKey("mac_sha1") && !parameters.containsKey("mac_md5") && !parameters.containsKey(TapjoyConstants.TJC_ANDROID_ID) && !parameters.containsKey("gps_adid")) {
            logger.error("Missing device id's. Please check if Proguard is correctly set with Adjust SDK", new Object[0]);
        }
    }

    private void fillPluginKeys(Map<String, String> parameters) {
        if (this.deviceInfo.pluginKeys != null) {
            for (Entry<String, String> entry : this.deviceInfo.pluginKeys.entrySet()) {
                addString(parameters, (String) entry.getKey(), (String) entry.getValue());
            }
        }
    }

    private String getEventSuffix(AdjustEvent event) {
        if (event.revenue == null) {
            return Util.formatString("'%s'", event.eventToken);
        }
        return Util.formatString("(%.5f %s, '%s')", event.revenue, event.currency, event.eventToken);
    }

    public static void addString(Map<String, String> parameters, String key, String value) {
        if (!TextUtils.isEmpty(value)) {
            parameters.put(key, value);
        }
    }

    public static void addLong(Map<String, String> parameters, String key, long value) {
        if (value >= 0) {
            addString(parameters, key, Long.toString(value));
        }
    }

    public static void addDateInMilliseconds(Map<String, String> parameters, String key, long value) {
        if (value > 0) {
            addDate(parameters, key, new Date(value));
        }
    }

    public static void addDateInSeconds(Map<String, String> parameters, String key, long value) {
        if (value > 0) {
            addDate(parameters, key, new Date(1000 * value));
        }
    }

    public static void addDate(Map<String, String> parameters, String key, Date value) {
        if (value != null) {
            addString(parameters, key, Util.dateFormatter.format(value));
        }
    }

    public static void addDuration(Map<String, String> parameters, String key, long durationInMilliSeconds) {
        if (durationInMilliSeconds >= 0) {
            addLong(parameters, key, (500 + durationInMilliSeconds) / 1000);
        }
    }

    public static void addMapJson(Map<String, String> parameters, String key, Map<String, String> map) {
        if (map != null && map.size() != 0) {
            addString(parameters, key, new JSONObject(map).toString());
        }
    }

    public static void addBoolean(Map<String, String> parameters, String key, Boolean value) {
        if (value != null) {
            addLong(parameters, key, (long) (value.booleanValue() ? 1 : 0));
        }
    }

    public static void addDouble(Map<String, String> parameters, String key, Double value) {
        if (value != null) {
            addString(parameters, key, Util.formatString("%.5f", value));
        }
    }
}
