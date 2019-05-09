// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.utils;

import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import java.util.ArrayList;
import com.ironsource.mediationsdk.IronSource;
import java.util.List;
import java.util.Iterator;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.model.OfferwallPlacement;
import com.ironsource.mediationsdk.model.BannerPlacement;
import com.ironsource.mediationsdk.model.InterstitialPlacement;
import com.ironsource.mediationsdk.model.Placement;
import org.json.JSONArray;
import com.ironsource.mediationsdk.model.ApplicationConfigurations;
import com.ironsource.mediationsdk.model.ServerSegmetData;
import com.ironsource.mediationsdk.model.ApplicationLogger;
import com.ironsource.mediationsdk.model.OfferwallConfigurations;
import com.ironsource.mediationsdk.model.BannerConfigurations;
import com.ironsource.mediationsdk.model.InterstitialConfigurations;
import com.ironsource.mediationsdk.model.RewardedVideoConfigurations;
import com.ironsource.mediationsdk.model.ApplicationEvents;
import com.ironsource.mediationsdk.model.PlacementCappingType;
import com.ironsource.mediationsdk.model.PlacementAvailabilitySettings;
import org.json.JSONException;
import android.text.TextUtils;
import org.json.JSONObject;
import com.ironsource.mediationsdk.model.ProviderSettingsHolder;
import com.ironsource.mediationsdk.model.ProviderOrder;
import android.content.Context;
import com.ironsource.mediationsdk.model.Configurations;

public class ServerResponseWrapper
{
    public static final String APP_KEY_FIELD = "appKey";
    public static final String RESPONSE_FIELD = "response";
    public static final String USER_ID_FIELD = "userId";
    private final String AB_TESTING;
    private final String ADAPTER_TIMEOUT_IN_MILLIS_FIELD;
    private final String ADAPTER_TIMEOUT_IN_SECS_FIELD;
    private final String AD_SOURCE_NAME_FIELD;
    private final String AD_UNITS_FIELD;
    private final String ALLOW_LOCATION;
    private final String APPLICATION_FIELD;
    private final String BACKFILL_FIELD;
    private final String BACKUP_THRESHOLD_FIELD;
    private final String BN_FIELD;
    private final String CONFIGURATIONS_FIELD;
    private final String CONSOLE_FIELD;
    private final int DEFAULT_ADAPTERS_SMARTLOAD_AMOUNT;
    private final int DEFAULT_ADAPTERS_SMARTLOAD_TIMEOUT;
    private final String DEFAULT_BANNER_LOAD_REFRESH_INTERVAL;
    private final int DEFAULT_BANNER_SMARTLOAD_TIMEOUT;
    private final int DEFAULT_LOG_LEVEL;
    private final int DEFAULT_MAX_EVENTS_PER_BATCH;
    private final String ERROR_KEY;
    private final String EVENTS_FIELD;
    private final String INTEGRATION_FIELD;
    private final String IS_FIELD;
    private final String IS_MULTIPLE_INSTANCES_FIELD;
    private final String LOGGERS_FIELD;
    private final String MAX_EVENTS_PER_BATCH;
    private final String MAX_NUM_OF_ADAPTERS_TO_LOAD_ON_START_FIELD;
    private final String MAX_NUM_OF_EVENTS_FIELD;
    private final String OPT_OUT_EVENTS_FIELD;
    private final String OW_FIELD;
    private final String PLACEMENTS_FIELD;
    private final String PLACEMENT_ID_FIELD;
    private final String PLACEMENT_NAME_FIELD;
    private final String PLACEMENT_SETTINGS_CAPPING_FIELD;
    private final String PLACEMENT_SETTINGS_CAPPING_UNIT_FIELD;
    private final String PLACEMENT_SETTINGS_CAPPING_VALUE_FIELD;
    private final String PLACEMENT_SETTINGS_DELIVERY_FIELD;
    private final String PLACEMENT_SETTINGS_ENABLED_FIELD;
    private final String PLACEMENT_SETTINGS_PACING_FIELD;
    private final String PLACEMENT_SETTINGS_PACING_VALUE_FIELD;
    private final String PREMIUM_FIELD;
    private final String PROVIDER_LOAD_NAME_FIELD;
    private final String PROVIDER_ORDER_FIELD;
    private final String PROVIDER_SETTINGS_FIELD;
    private final String PUBLISHER_FIELD;
    private final String RV_FIELD;
    private final String SEGMENT_FIELD;
    private final String SEND_EVENTS_TOGGLE_FIELD;
    private final String SEND_ULTRA_EVENTS_FIELD;
    private final String SERVER_EVENTS_TYPE;
    private final String SERVER_EVENTS_URL_FIELD;
    private final String SERVER_FIELD;
    private final String SUB_PROVIDER_ID_FIELD;
    private final String UUID_ENABLED_FIELD;
    private final String VIRTUAL_ITEM_COUNT_FIELD;
    private final String VIRTUAL_ITEM_NAME_FIELD;
    private String mAppKey;
    private Configurations mConfigurations;
    private Context mContext;
    private ProviderOrder mProviderOrder;
    private ProviderSettingsHolder mProviderSettingsHolder;
    private JSONObject mResponse;
    private String mUserId;
    
    public ServerResponseWrapper(final Context mContext, final String s, final String s2, final String s3) {
        this.ERROR_KEY = "error";
        this.DEFAULT_LOG_LEVEL = 3;
        this.DEFAULT_ADAPTERS_SMARTLOAD_AMOUNT = 2;
        this.DEFAULT_ADAPTERS_SMARTLOAD_TIMEOUT = 60;
        this.DEFAULT_BANNER_SMARTLOAD_TIMEOUT = 10000;
        this.DEFAULT_MAX_EVENTS_PER_BATCH = 5000;
        this.PROVIDER_ORDER_FIELD = "providerOrder";
        this.PROVIDER_SETTINGS_FIELD = "providerSettings";
        this.CONFIGURATIONS_FIELD = "configurations";
        this.AD_UNITS_FIELD = "adUnits";
        this.PROVIDER_LOAD_NAME_FIELD = "providerLoadName";
        this.APPLICATION_FIELD = "application";
        this.RV_FIELD = "rewardedVideo";
        this.IS_FIELD = "interstitial";
        this.OW_FIELD = "offerwall";
        this.BN_FIELD = "banner";
        this.INTEGRATION_FIELD = "integration";
        this.LOGGERS_FIELD = "loggers";
        this.SEGMENT_FIELD = "segment";
        this.EVENTS_FIELD = "events";
        this.MAX_NUM_OF_ADAPTERS_TO_LOAD_ON_START_FIELD = "maxNumOfAdaptersToLoadOnStart";
        this.ADAPTER_TIMEOUT_IN_SECS_FIELD = "adapterTimeOutInSeconds";
        this.ADAPTER_TIMEOUT_IN_MILLIS_FIELD = "atim";
        this.DEFAULT_BANNER_LOAD_REFRESH_INTERVAL = "bannerInterval";
        this.SERVER_FIELD = "server";
        this.PUBLISHER_FIELD = "publisher";
        this.CONSOLE_FIELD = "console";
        this.SEND_ULTRA_EVENTS_FIELD = "sendUltraEvents";
        this.SEND_EVENTS_TOGGLE_FIELD = "sendEventsToggle";
        this.SERVER_EVENTS_URL_FIELD = "serverEventsURL";
        this.SERVER_EVENTS_TYPE = "serverEventsType";
        this.BACKUP_THRESHOLD_FIELD = "backupThreshold";
        this.MAX_NUM_OF_EVENTS_FIELD = "maxNumberOfEvents";
        this.MAX_EVENTS_PER_BATCH = "maxEventsPerBatch";
        this.OPT_OUT_EVENTS_FIELD = "optOut";
        this.ALLOW_LOCATION = "allowLocation";
        this.PLACEMENTS_FIELD = "placements";
        this.PLACEMENT_ID_FIELD = "placementId";
        this.PLACEMENT_NAME_FIELD = "placementName";
        this.PLACEMENT_SETTINGS_DELIVERY_FIELD = "delivery";
        this.PLACEMENT_SETTINGS_CAPPING_FIELD = "capping";
        this.PLACEMENT_SETTINGS_PACING_FIELD = "pacing";
        this.PLACEMENT_SETTINGS_ENABLED_FIELD = "enabled";
        this.PLACEMENT_SETTINGS_CAPPING_VALUE_FIELD = "maxImpressions";
        this.PLACEMENT_SETTINGS_PACING_VALUE_FIELD = "numOfSeconds";
        this.PLACEMENT_SETTINGS_CAPPING_UNIT_FIELD = "unit";
        this.VIRTUAL_ITEM_NAME_FIELD = "virtualItemName";
        this.VIRTUAL_ITEM_COUNT_FIELD = "virtualItemCount";
        this.BACKFILL_FIELD = "backFill";
        this.PREMIUM_FIELD = "premium";
        this.UUID_ENABLED_FIELD = "uuidEnabled";
        this.AB_TESTING = "abt";
        this.AD_SOURCE_NAME_FIELD = "adSourceName";
        this.SUB_PROVIDER_ID_FIELD = "spId";
        this.IS_MULTIPLE_INSTANCES_FIELD = "mpis";
        this.mContext = mContext;
        try {
            if (TextUtils.isEmpty((CharSequence)s3)) {
                this.mResponse = new JSONObject();
            }
            else {
                this.mResponse = new JSONObject(s3);
            }
            this.parseProviderSettings();
            this.parseConfigurations();
            this.parseProviderOrder();
            String mAppKey = s;
            if (TextUtils.isEmpty((CharSequence)s)) {
                mAppKey = "";
            }
            this.mAppKey = mAppKey;
            String mUserId = s2;
            if (TextUtils.isEmpty((CharSequence)s2)) {
                mUserId = "";
            }
            this.mUserId = mUserId;
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            this.defaultInit();
        }
    }
    
    public ServerResponseWrapper(final ServerResponseWrapper serverResponseWrapper) {
        this.ERROR_KEY = "error";
        this.DEFAULT_LOG_LEVEL = 3;
        this.DEFAULT_ADAPTERS_SMARTLOAD_AMOUNT = 2;
        this.DEFAULT_ADAPTERS_SMARTLOAD_TIMEOUT = 60;
        this.DEFAULT_BANNER_SMARTLOAD_TIMEOUT = 10000;
        this.DEFAULT_MAX_EVENTS_PER_BATCH = 5000;
        this.PROVIDER_ORDER_FIELD = "providerOrder";
        this.PROVIDER_SETTINGS_FIELD = "providerSettings";
        this.CONFIGURATIONS_FIELD = "configurations";
        this.AD_UNITS_FIELD = "adUnits";
        this.PROVIDER_LOAD_NAME_FIELD = "providerLoadName";
        this.APPLICATION_FIELD = "application";
        this.RV_FIELD = "rewardedVideo";
        this.IS_FIELD = "interstitial";
        this.OW_FIELD = "offerwall";
        this.BN_FIELD = "banner";
        this.INTEGRATION_FIELD = "integration";
        this.LOGGERS_FIELD = "loggers";
        this.SEGMENT_FIELD = "segment";
        this.EVENTS_FIELD = "events";
        this.MAX_NUM_OF_ADAPTERS_TO_LOAD_ON_START_FIELD = "maxNumOfAdaptersToLoadOnStart";
        this.ADAPTER_TIMEOUT_IN_SECS_FIELD = "adapterTimeOutInSeconds";
        this.ADAPTER_TIMEOUT_IN_MILLIS_FIELD = "atim";
        this.DEFAULT_BANNER_LOAD_REFRESH_INTERVAL = "bannerInterval";
        this.SERVER_FIELD = "server";
        this.PUBLISHER_FIELD = "publisher";
        this.CONSOLE_FIELD = "console";
        this.SEND_ULTRA_EVENTS_FIELD = "sendUltraEvents";
        this.SEND_EVENTS_TOGGLE_FIELD = "sendEventsToggle";
        this.SERVER_EVENTS_URL_FIELD = "serverEventsURL";
        this.SERVER_EVENTS_TYPE = "serverEventsType";
        this.BACKUP_THRESHOLD_FIELD = "backupThreshold";
        this.MAX_NUM_OF_EVENTS_FIELD = "maxNumberOfEvents";
        this.MAX_EVENTS_PER_BATCH = "maxEventsPerBatch";
        this.OPT_OUT_EVENTS_FIELD = "optOut";
        this.ALLOW_LOCATION = "allowLocation";
        this.PLACEMENTS_FIELD = "placements";
        this.PLACEMENT_ID_FIELD = "placementId";
        this.PLACEMENT_NAME_FIELD = "placementName";
        this.PLACEMENT_SETTINGS_DELIVERY_FIELD = "delivery";
        this.PLACEMENT_SETTINGS_CAPPING_FIELD = "capping";
        this.PLACEMENT_SETTINGS_PACING_FIELD = "pacing";
        this.PLACEMENT_SETTINGS_ENABLED_FIELD = "enabled";
        this.PLACEMENT_SETTINGS_CAPPING_VALUE_FIELD = "maxImpressions";
        this.PLACEMENT_SETTINGS_PACING_VALUE_FIELD = "numOfSeconds";
        this.PLACEMENT_SETTINGS_CAPPING_UNIT_FIELD = "unit";
        this.VIRTUAL_ITEM_NAME_FIELD = "virtualItemName";
        this.VIRTUAL_ITEM_COUNT_FIELD = "virtualItemCount";
        this.BACKFILL_FIELD = "backFill";
        this.PREMIUM_FIELD = "premium";
        this.UUID_ENABLED_FIELD = "uuidEnabled";
        this.AB_TESTING = "abt";
        this.AD_SOURCE_NAME_FIELD = "adSourceName";
        this.SUB_PROVIDER_ID_FIELD = "spId";
        this.IS_MULTIPLE_INSTANCES_FIELD = "mpis";
        try {
            this.mContext = serverResponseWrapper.getContext();
            this.mResponse = new JSONObject(serverResponseWrapper.mResponse.toString());
            this.mAppKey = serverResponseWrapper.mAppKey;
            this.mUserId = serverResponseWrapper.mUserId;
            this.mProviderOrder = serverResponseWrapper.getProviderOrder();
            this.mProviderSettingsHolder = serverResponseWrapper.getProviderSettingsHolder();
            this.mConfigurations = serverResponseWrapper.getConfigurations();
        }
        catch (Exception ex) {
            this.defaultInit();
        }
    }
    
    private void defaultInit() {
        this.mResponse = new JSONObject();
        this.mAppKey = "";
        this.mUserId = "";
        this.mProviderOrder = new ProviderOrder();
        this.mProviderSettingsHolder = ProviderSettingsHolder.getProviderSettingsHolder();
        this.mConfigurations = new Configurations();
    }
    
    private Context getContext() {
        return this.mContext;
    }
    
    private int getIntConfigValue(final JSONObject jsonObject, final JSONObject jsonObject2, final String s, final int n) {
        int n2 = 0;
        if (jsonObject.has(s)) {
            n2 = jsonObject.optInt(s, 0);
        }
        else if (jsonObject2.has(s)) {
            n2 = jsonObject2.optInt(s, 0);
        }
        int n3;
        if ((n3 = n2) == 0) {
            n3 = n;
        }
        return n3;
    }
    
    private long getLongConfigValue(final JSONObject jsonObject, final JSONObject jsonObject2, final String s, final long n) {
        long n2 = 0L;
        if (jsonObject.has(s)) {
            n2 = jsonObject.optLong(s, 0L);
        }
        else if (jsonObject2.has(s)) {
            n2 = jsonObject2.optLong(s, 0L);
        }
        long n3 = n2;
        if (n2 == 0L) {
            n3 = n;
        }
        return n3;
    }
    
    private PlacementAvailabilitySettings getPlacementAvailabilitySettings(JSONObject optJSONObject) {
        if (optJSONObject == null) {
            return null;
        }
        final PlacementAvailabilitySettings.PlacementAvailabilitySettingsBuilder placementAvailabilitySettingsBuilder = new PlacementAvailabilitySettings.PlacementAvailabilitySettingsBuilder();
        placementAvailabilitySettingsBuilder.delivery(optJSONObject.optBoolean("delivery", true));
        final JSONObject optJSONObject2 = optJSONObject.optJSONObject("capping");
        if (optJSONObject2 != null) {
            final PlacementCappingType placementCappingType = null;
            final String optString = optJSONObject2.optString("unit");
            PlacementCappingType placementCappingType2 = placementCappingType;
            if (!TextUtils.isEmpty((CharSequence)optString)) {
                if (PlacementCappingType.PER_DAY.toString().equals(optString)) {
                    placementCappingType2 = PlacementCappingType.PER_DAY;
                }
                else {
                    placementCappingType2 = placementCappingType;
                    if (PlacementCappingType.PER_HOUR.toString().equals(optString)) {
                        placementCappingType2 = PlacementCappingType.PER_HOUR;
                    }
                }
            }
            final int optInt = optJSONObject2.optInt("maxImpressions", 0);
            placementAvailabilitySettingsBuilder.capping(optJSONObject2.optBoolean("enabled", false) && optInt > 0, placementCappingType2, optInt);
        }
        optJSONObject = optJSONObject.optJSONObject("pacing");
        if (optJSONObject != null) {
            final int optInt2 = optJSONObject.optInt("numOfSeconds", 0);
            placementAvailabilitySettingsBuilder.pacing(optJSONObject.optBoolean("enabled", false) && optInt2 > 0, optInt2);
        }
        return placementAvailabilitySettingsBuilder.build();
    }
    
    private JSONObject getSection(final JSONObject jsonObject, final String s) {
        JSONObject optJSONObject = null;
        if (jsonObject != null) {
            optJSONObject = jsonObject.optJSONObject(s);
        }
        return optJSONObject;
    }
    
    private void parseConfigurations() {
        JSONObject section;
        JSONObject section2;
        JSONObject section3;
        JSONObject section4;
        JSONObject section5;
        JSONObject section6;
        JSONObject section7;
        JSONObject section8;
        JSONObject section9;
        JSONObject section10;
        RewardedVideoConfigurations rewardedVideoConfigurations;
        InterstitialConfigurations interstitialConfigurations;
        OfferwallConfigurations offerwallConfigurations;
        BannerConfigurations bannerConfigurations;
        String optString;
        JSONArray optJSONArray;
        JSONObject section11;
        int intConfigValue;
        int intConfigValue2;
        JSONObject mergeJsons;
        boolean optBoolean;
        boolean optBoolean2;
        String optString2;
        String optString3;
        int optInt;
        int optInt2;
        int optInt3;
        int[] array;
        JSONArray optJSONArray2;
        int[] array2;
        int n;
        RewardedVideoConfigurations rewardedVideoConfigurations2;
        int n2 = 0;
        Placement singleRVPlacement;
        String optString4;
        String optString5;
        JSONArray optJSONArray3;
        JSONObject section12;
        int intConfigValue3;
        int intConfigValue4;
        JSONObject mergeJsons2;
        boolean optBoolean3;
        String optString6;
        String optString7;
        int optInt4;
        int optInt5;
        int optInt6;
        int[] array3;
        JSONArray optJSONArray4;
        int[] array4;
        int n3;
        InterstitialConfigurations interstitialConfigurations2;
        int n4 = 0;
        InterstitialPlacement singleISPlacement;
        String optString8;
        String optString9;
        JSONArray optJSONArray5;
        JSONObject section13;
        int intConfigValue5;
        long longConfigValue;
        int intConfigValue6;
        JSONObject mergeJsons3;
        boolean optBoolean4;
        String optString10;
        String optString11;
        int optInt7;
        int optInt8;
        int optInt9;
        int[] array5;
        JSONArray optJSONArray6;
        int[] array6;
        int n5;
        BannerConfigurations bannerConfigurations2;
        int n6 = 0;
        BannerPlacement singleBNPlacement;
        JSONArray optJSONArray7;
        OfferwallConfigurations offerwallConfigurations2;
        int n7 = 0;
        OfferwallPlacement singleOWPlacement;
        ApplicationLogger applicationLogger;
        ServerSegmetData serverSegmetData;
        ApplicationConfigurations applicationConfigurations;
        Label_0719_Outer:Label_1056_Outer:Label_1130_Outer:
        while (true) {
            while (true) {
            Label_1344:
                while (true) {
                Label_1337:
                    while (true) {
                    Label_1330:
                        while (true) {
                            Label_1323: {
                                try {
                                    section = this.getSection(this.mResponse, "configurations");
                                    section2 = this.getSection(section, "adUnits");
                                    section3 = this.getSection(section, "application");
                                    section4 = this.getSection(section2, "rewardedVideo");
                                    section5 = this.getSection(section2, "interstitial");
                                    section6 = this.getSection(section2, "offerwall");
                                    section7 = this.getSection(section2, "banner");
                                    section8 = this.getSection(section3, "events");
                                    section9 = this.getSection(section3, "loggers");
                                    section10 = this.getSection(section3, "segment");
                                    rewardedVideoConfigurations = null;
                                    interstitialConfigurations = null;
                                    offerwallConfigurations = null;
                                    bannerConfigurations = null;
                                    if (section3 != null) {
                                        IronSourceUtils.saveBooleanToSharedPrefs(this.mContext, "uuidEnabled", section3.optBoolean("uuidEnabled", true));
                                    }
                                    if (section8 != null) {
                                        optString = section8.optString("abt");
                                        if (!TextUtils.isEmpty((CharSequence)optString)) {
                                            IronSourceUtils.setABT(optString);
                                        }
                                    }
                                    if (section4 != null) {
                                        optJSONArray = section4.optJSONArray("placements");
                                        section11 = this.getSection(section4, "events");
                                        intConfigValue = this.getIntConfigValue(section4, section3, "maxNumOfAdaptersToLoadOnStart", 2);
                                        intConfigValue2 = this.getIntConfigValue(section4, section3, "adapterTimeOutInSeconds", 60);
                                        mergeJsons = IronSourceUtils.mergeJsons(section11, section8);
                                        optBoolean = mergeJsons.optBoolean("sendUltraEvents", false);
                                        optBoolean2 = mergeJsons.optBoolean("sendEventsToggle", false);
                                        optString2 = mergeJsons.optString("serverEventsURL", "");
                                        optString3 = mergeJsons.optString("serverEventsType", "");
                                        optInt = mergeJsons.optInt("backupThreshold", -1);
                                        optInt2 = mergeJsons.optInt("maxNumberOfEvents", -1);
                                        optInt3 = mergeJsons.optInt("maxEventsPerBatch", 5000);
                                        array = null;
                                        optJSONArray2 = mergeJsons.optJSONArray("optOut");
                                        if (optJSONArray2 != null) {
                                            array2 = new int[optJSONArray2.length()];
                                            n = 0;
                                            while (true) {
                                                array = array2;
                                                if (n >= optJSONArray2.length()) {
                                                    break;
                                                }
                                                array2[n] = optJSONArray2.optInt(n);
                                                ++n;
                                            }
                                        }
                                        rewardedVideoConfigurations2 = new RewardedVideoConfigurations(intConfigValue, intConfigValue2, new ApplicationEvents(optBoolean, optBoolean2, optString2, optString3, optInt, optInt2, optInt3, array));
                                        if (optJSONArray != null) {
                                            n2 = 0;
                                            if (n2 < optJSONArray.length()) {
                                                singleRVPlacement = this.parseSingleRVPlacement(optJSONArray.optJSONObject(n2));
                                                if (singleRVPlacement != null) {
                                                    rewardedVideoConfigurations2.addRewardedVideoPlacement(singleRVPlacement);
                                                }
                                                break Label_1323;
                                            }
                                        }
                                        optString4 = section4.optString("backFill");
                                        if (!TextUtils.isEmpty((CharSequence)optString4)) {
                                            rewardedVideoConfigurations2.setBackFillProviderName(optString4);
                                        }
                                        optString5 = section4.optString("premium");
                                        rewardedVideoConfigurations = rewardedVideoConfigurations2;
                                        if (!TextUtils.isEmpty((CharSequence)optString5)) {
                                            rewardedVideoConfigurations2.setPremiumProviderName(optString5);
                                            rewardedVideoConfigurations = rewardedVideoConfigurations2;
                                        }
                                    }
                                    if (section5 != null) {
                                        optJSONArray3 = section5.optJSONArray("placements");
                                        section12 = this.getSection(section5, "events");
                                        intConfigValue3 = this.getIntConfigValue(section5, section3, "maxNumOfAdaptersToLoadOnStart", 2);
                                        intConfigValue4 = this.getIntConfigValue(section5, section3, "adapterTimeOutInSeconds", 60);
                                        mergeJsons2 = IronSourceUtils.mergeJsons(section12, section8);
                                        optBoolean3 = mergeJsons2.optBoolean("sendEventsToggle", false);
                                        optString6 = mergeJsons2.optString("serverEventsURL", "");
                                        optString7 = mergeJsons2.optString("serverEventsType", "");
                                        optInt4 = mergeJsons2.optInt("backupThreshold", -1);
                                        optInt5 = mergeJsons2.optInt("maxNumberOfEvents", -1);
                                        optInt6 = mergeJsons2.optInt("maxEventsPerBatch", 5000);
                                        array3 = null;
                                        optJSONArray4 = mergeJsons2.optJSONArray("optOut");
                                        if (optJSONArray4 != null) {
                                            array4 = new int[optJSONArray4.length()];
                                            n3 = 0;
                                            while (true) {
                                                array3 = array4;
                                                if (n3 >= optJSONArray4.length()) {
                                                    break;
                                                }
                                                array4[n3] = optJSONArray4.optInt(n3);
                                                ++n3;
                                            }
                                        }
                                        interstitialConfigurations2 = new InterstitialConfigurations(intConfigValue3, intConfigValue4, new ApplicationEvents(false, optBoolean3, optString6, optString7, optInt4, optInt5, optInt6, array3));
                                        if (optJSONArray3 != null) {
                                            n4 = 0;
                                            if (n4 < optJSONArray3.length()) {
                                                singleISPlacement = this.parseSingleISPlacement(optJSONArray3.optJSONObject(n4));
                                                if (singleISPlacement != null) {
                                                    interstitialConfigurations2.addInterstitialPlacement(singleISPlacement);
                                                }
                                                break Label_1330;
                                            }
                                        }
                                        optString8 = section5.optString("backFill");
                                        if (!TextUtils.isEmpty((CharSequence)optString8)) {
                                            interstitialConfigurations2.setBackFillProviderName(optString8);
                                        }
                                        optString9 = section5.optString("premium");
                                        interstitialConfigurations = interstitialConfigurations2;
                                        if (!TextUtils.isEmpty((CharSequence)optString9)) {
                                            interstitialConfigurations2.setPremiumProviderName(optString9);
                                            interstitialConfigurations = interstitialConfigurations2;
                                        }
                                    }
                                    if (section7 != null) {
                                        optJSONArray5 = section7.optJSONArray("placements");
                                        section13 = this.getSection(section7, "events");
                                        intConfigValue5 = this.getIntConfigValue(section7, section3, "maxNumOfAdaptersToLoadOnStart", 1);
                                        longConfigValue = this.getLongConfigValue(section7, section3, "atim", 10000L);
                                        intConfigValue6 = this.getIntConfigValue(section7, section3, "bannerInterval", 60);
                                        mergeJsons3 = IronSourceUtils.mergeJsons(section13, section8);
                                        optBoolean4 = mergeJsons3.optBoolean("sendEventsToggle", false);
                                        optString10 = mergeJsons3.optString("serverEventsURL", "");
                                        optString11 = mergeJsons3.optString("serverEventsType", "");
                                        optInt7 = mergeJsons3.optInt("backupThreshold", -1);
                                        optInt8 = mergeJsons3.optInt("maxNumberOfEvents", -1);
                                        optInt9 = mergeJsons3.optInt("maxEventsPerBatch", 5000);
                                        array5 = null;
                                        optJSONArray6 = mergeJsons3.optJSONArray("optOut");
                                        if (optJSONArray6 != null) {
                                            array6 = new int[optJSONArray6.length()];
                                            n5 = 0;
                                            while (true) {
                                                array5 = array6;
                                                if (n5 >= optJSONArray6.length()) {
                                                    break;
                                                }
                                                array6[n5] = optJSONArray6.optInt(n5);
                                                ++n5;
                                            }
                                        }
                                        bannerConfigurations2 = (bannerConfigurations = new BannerConfigurations(intConfigValue5, longConfigValue, new ApplicationEvents((boolean)(0 != 0), optBoolean4, optString10, optString11, optInt7, optInt8, optInt9, array5), intConfigValue6));
                                        if (optJSONArray5 != null) {
                                            n6 = 0;
                                            bannerConfigurations = bannerConfigurations2;
                                            if (n6 < optJSONArray5.length()) {
                                                singleBNPlacement = this.parseSingleBNPlacement(optJSONArray5.optJSONObject(n6));
                                                if (singleBNPlacement != null) {
                                                    bannerConfigurations2.addBannerPlacement(singleBNPlacement);
                                                }
                                                break Label_1337;
                                            }
                                        }
                                    }
                                    if (section6 != null) {
                                        optJSONArray7 = section6.optJSONArray("placements");
                                        offerwallConfigurations2 = (offerwallConfigurations = new OfferwallConfigurations());
                                        if (optJSONArray7 != null) {
                                            n7 = 0;
                                            offerwallConfigurations = offerwallConfigurations2;
                                            if (n7 < optJSONArray7.length()) {
                                                singleOWPlacement = this.parseSingleOWPlacement(optJSONArray7.optJSONObject(n7));
                                                if (singleOWPlacement != null) {
                                                    offerwallConfigurations2.addOfferwallPlacement(singleOWPlacement);
                                                }
                                                break Label_1344;
                                            }
                                        }
                                    }
                                    applicationLogger = new ApplicationLogger(section9.optInt("server", 3), section9.optInt("publisher", 3), section9.optInt("console", 3));
                                    serverSegmetData = null;
                                    if (section10 != null) {
                                        serverSegmetData = new ServerSegmetData(section10.optString("name", ""), section10.optString("id", "-1"), section10.optJSONObject("custom"));
                                    }
                                    applicationConfigurations = new ApplicationConfigurations(applicationLogger, serverSegmetData, section3.optBoolean("integration", false));
                                    IronSourceUtils.saveBooleanToSharedPrefs(this.mContext, "GeneralProperties.ALLOW_LOCATION_SHARED_PREFS_KEY", section3.optBoolean("allowLocation", false));
                                    this.mConfigurations = new Configurations(rewardedVideoConfigurations, interstitialConfigurations, offerwallConfigurations, bannerConfigurations, applicationConfigurations);
                                    return;
                                }
                                catch (Exception ex) {
                                    ex.printStackTrace();
                                    return;
                                }
                            }
                            ++n2;
                            continue Label_0719_Outer;
                        }
                        ++n4;
                        continue Label_1056_Outer;
                    }
                    ++n6;
                    continue Label_1130_Outer;
                }
                ++n7;
                continue;
            }
        }
    }
    
    private void parseProviderOrder() {
        JSONArray optJSONArray2 = null;
        JSONArray optJSONArray3 = null;
        Label_0189: {
            while (true) {
                while (true) {
                    int rewardedVideoPriority = 0;
                    Label_0371: {
                        try {
                            final JSONObject section = this.getSection(this.mResponse, "providerOrder");
                            final JSONArray optJSONArray = section.optJSONArray("rewardedVideo");
                            optJSONArray2 = section.optJSONArray("interstitial");
                            optJSONArray3 = section.optJSONArray("banner");
                            this.mProviderOrder = new ProviderOrder();
                            if (optJSONArray == null || this.getConfigurations() == null || this.getConfigurations().getRewardedVideoConfigurations() == null) {
                                break Label_0189;
                            }
                            final String backFillProviderName = this.getConfigurations().getRewardedVideoConfigurations().getBackFillProviderName();
                            final String premiumProviderName = this.getConfigurations().getRewardedVideoConfigurations().getPremiumProviderName();
                            rewardedVideoPriority = 0;
                            if (rewardedVideoPriority >= optJSONArray.length()) {
                                break Label_0189;
                            }
                            final String optString = optJSONArray.optString(rewardedVideoPriority);
                            if (optString.equals(backFillProviderName)) {
                                this.mProviderOrder.setRVBackFillProvider(backFillProviderName);
                                break Label_0371;
                            }
                            if (optString.equals(premiumProviderName)) {
                                this.mProviderOrder.setRVPremiumProvider(premiumProviderName);
                            }
                            this.mProviderOrder.addRewardedVideoProvider(optString);
                            final ProviderSettings providerSettings = ProviderSettingsHolder.getProviderSettingsHolder().getProviderSettings(optString);
                            if (providerSettings != null) {
                                providerSettings.setRewardedVideoPriority(rewardedVideoPriority);
                            }
                            break Label_0371;
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        break;
                    }
                    ++rewardedVideoPriority;
                    continue;
                }
            }
            return;
        }
        if (optJSONArray2 != null && this.getConfigurations() != null && this.getConfigurations().getInterstitialConfigurations() != null) {
            final String backFillProviderName2 = this.getConfigurations().getInterstitialConfigurations().getBackFillProviderName();
            final String premiumProviderName2 = this.getConfigurations().getInterstitialConfigurations().getPremiumProviderName();
            for (int i = 0; i < optJSONArray2.length(); ++i) {
                final String optString2 = optJSONArray2.optString(i);
                if (optString2.equals(backFillProviderName2)) {
                    this.mProviderOrder.setISBackFillProvider(backFillProviderName2);
                }
                else {
                    if (optString2.equals(premiumProviderName2)) {
                        this.mProviderOrder.setISPremiumProvider(premiumProviderName2);
                    }
                    this.mProviderOrder.addInterstitialProvider(optString2);
                    final ProviderSettings providerSettings2 = ProviderSettingsHolder.getProviderSettingsHolder().getProviderSettings(optString2);
                    if (providerSettings2 != null) {
                        providerSettings2.setInterstitialPriority(i);
                    }
                }
            }
        }
        if (optJSONArray3 != null) {
            for (int j = 0; j < optJSONArray3.length(); ++j) {
                final String optString3 = optJSONArray3.optString(j);
                this.mProviderOrder.addBannerProvider(optString3);
                final ProviderSettings providerSettings3 = ProviderSettingsHolder.getProviderSettingsHolder().getProviderSettings(optString3);
                if (providerSettings3 != null) {
                    providerSettings3.setBannerPriority(j);
                }
            }
        }
    }
    
    private void parseProviderSettings() {
        while (true) {
            while (true) {
                String s = null;
                boolean optBoolean = false;
                String optString = null;
                String optString2 = null;
                String optString3 = null;
                JSONObject section3 = null;
                JSONObject mergeJsons = null;
                JSONObject mergeJsons2 = null;
                JSONObject mergeJsons3 = null;
                Label_0287: {
                    try {
                        this.mProviderSettingsHolder = ProviderSettingsHolder.getProviderSettingsHolder();
                        final JSONObject section = this.getSection(this.mResponse, "providerSettings");
                        final Iterator keys = section.keys();
                        while (keys.hasNext()) {
                            s = keys.next();
                            final JSONObject optJSONObject = section.optJSONObject(s);
                            if (optJSONObject != null) {
                                optBoolean = optJSONObject.optBoolean("mpis", false);
                                optString = optJSONObject.optString("spId", "0");
                                optString2 = optJSONObject.optString("adSourceName", (String)null);
                                optString3 = optJSONObject.optString("providerLoadName", s);
                                final JSONObject section2 = this.getSection(optJSONObject, "adUnits");
                                section3 = this.getSection(optJSONObject, "application");
                                final JSONObject section4 = this.getSection(section2, "rewardedVideo");
                                final JSONObject section5 = this.getSection(section2, "interstitial");
                                final JSONObject section6 = this.getSection(section2, "banner");
                                mergeJsons = IronSourceUtils.mergeJsons(section4, section3);
                                mergeJsons2 = IronSourceUtils.mergeJsons(section5, section3);
                                mergeJsons3 = IronSourceUtils.mergeJsons(section6, section3);
                                if (!this.mProviderSettingsHolder.containsProviderSettings(s)) {
                                    break Label_0287;
                                }
                                final ProviderSettings providerSettings = this.mProviderSettingsHolder.getProviderSettings(s);
                                final JSONObject rewardedVideoSettings = providerSettings.getRewardedVideoSettings();
                                final JSONObject interstitialSettings = providerSettings.getInterstitialSettings();
                                final JSONObject bannerSettings = providerSettings.getBannerSettings();
                                providerSettings.setRewardedVideoSettings(IronSourceUtils.mergeJsons(rewardedVideoSettings, mergeJsons));
                                providerSettings.setInterstitialSettings(IronSourceUtils.mergeJsons(interstitialSettings, mergeJsons2));
                                providerSettings.setBannerSettings(IronSourceUtils.mergeJsons(bannerSettings, mergeJsons3));
                                providerSettings.setIsMultipleInstances(optBoolean);
                                providerSettings.setSubProviderId(optString);
                                providerSettings.setAdSourceNameForEvents(optString2);
                            }
                        }
                        break;
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        return;
                    }
                }
                if (this.mProviderSettingsHolder.containsProviderSettings("Mediation") && ("SupersonicAds".toLowerCase().equals(optString3.toLowerCase()) || "RIS".toLowerCase().equals(optString3.toLowerCase()))) {
                    final ProviderSettings providerSettings2 = this.mProviderSettingsHolder.getProviderSettings("Mediation");
                    final ProviderSettings providerSettings3 = new ProviderSettings(s, optString3, section3, IronSourceUtils.mergeJsons(new JSONObject(providerSettings2.getRewardedVideoSettings().toString()), mergeJsons), IronSourceUtils.mergeJsons(new JSONObject(providerSettings2.getInterstitialSettings().toString()), mergeJsons2), IronSourceUtils.mergeJsons(new JSONObject(providerSettings2.getBannerSettings().toString()), mergeJsons3));
                    providerSettings3.setIsMultipleInstances(optBoolean);
                    providerSettings3.setSubProviderId(optString);
                    providerSettings3.setAdSourceNameForEvents(optString2);
                    this.mProviderSettingsHolder.addProviderSettings(providerSettings3);
                    continue;
                }
                final ProviderSettings providerSettings4 = new ProviderSettings(s, optString3, section3, mergeJsons, mergeJsons2, mergeJsons3);
                providerSettings4.setIsMultipleInstances(optBoolean);
                providerSettings4.setSubProviderId(optString);
                providerSettings4.setAdSourceNameForEvents(optString2);
                this.mProviderSettingsHolder.addProviderSettings(providerSettings4);
                continue;
            }
        }
        this.mProviderSettingsHolder.fillSubProvidersDetails();
    }
    
    private BannerPlacement parseSingleBNPlacement(final JSONObject jsonObject) {
        BannerPlacement bannerPlacement2;
        final BannerPlacement bannerPlacement = bannerPlacement2 = null;
        if (jsonObject != null) {
            final int optInt = jsonObject.optInt("placementId", -1);
            final String optString = jsonObject.optString("placementName", "");
            final PlacementAvailabilitySettings placementAvailabilitySettings = this.getPlacementAvailabilitySettings(jsonObject);
            bannerPlacement2 = bannerPlacement;
            if (optInt >= 0) {
                bannerPlacement2 = bannerPlacement;
                if (!TextUtils.isEmpty((CharSequence)optString)) {
                    final BannerPlacement bannerPlacement3 = bannerPlacement2 = new BannerPlacement(optInt, optString, placementAvailabilitySettings);
                    if (placementAvailabilitySettings != null) {
                        CappingManager.addCappingInfo(this.mContext, bannerPlacement3);
                        bannerPlacement2 = bannerPlacement3;
                    }
                }
            }
        }
        return bannerPlacement2;
    }
    
    private InterstitialPlacement parseSingleISPlacement(final JSONObject jsonObject) {
        InterstitialPlacement interstitialPlacement2;
        final InterstitialPlacement interstitialPlacement = interstitialPlacement2 = null;
        if (jsonObject != null) {
            final int optInt = jsonObject.optInt("placementId", -1);
            final String optString = jsonObject.optString("placementName", "");
            final PlacementAvailabilitySettings placementAvailabilitySettings = this.getPlacementAvailabilitySettings(jsonObject);
            interstitialPlacement2 = interstitialPlacement;
            if (optInt >= 0) {
                interstitialPlacement2 = interstitialPlacement;
                if (!TextUtils.isEmpty((CharSequence)optString)) {
                    final InterstitialPlacement interstitialPlacement3 = interstitialPlacement2 = new InterstitialPlacement(optInt, optString, placementAvailabilitySettings);
                    if (placementAvailabilitySettings != null) {
                        CappingManager.addCappingInfo(this.mContext, interstitialPlacement3);
                        interstitialPlacement2 = interstitialPlacement3;
                    }
                }
            }
        }
        return interstitialPlacement2;
    }
    
    private OfferwallPlacement parseSingleOWPlacement(final JSONObject jsonObject) {
        OfferwallPlacement offerwallPlacement2;
        final OfferwallPlacement offerwallPlacement = offerwallPlacement2 = null;
        if (jsonObject != null) {
            final int optInt = jsonObject.optInt("placementId", -1);
            final String optString = jsonObject.optString("placementName", "");
            offerwallPlacement2 = offerwallPlacement;
            if (optInt >= 0) {
                offerwallPlacement2 = offerwallPlacement;
                if (!TextUtils.isEmpty((CharSequence)optString)) {
                    offerwallPlacement2 = new OfferwallPlacement(optInt, optString);
                }
            }
        }
        return offerwallPlacement2;
    }
    
    private Placement parseSingleRVPlacement(final JSONObject jsonObject) {
        Placement placement2;
        final Placement placement = placement2 = null;
        if (jsonObject != null) {
            final int optInt = jsonObject.optInt("placementId", -1);
            final String optString = jsonObject.optString("placementName", "");
            final String optString2 = jsonObject.optString("virtualItemName", "");
            final int optInt2 = jsonObject.optInt("virtualItemCount", -1);
            final PlacementAvailabilitySettings placementAvailabilitySettings = this.getPlacementAvailabilitySettings(jsonObject);
            placement2 = placement;
            if (optInt >= 0) {
                placement2 = placement;
                if (!TextUtils.isEmpty((CharSequence)optString)) {
                    placement2 = placement;
                    if (!TextUtils.isEmpty((CharSequence)optString2)) {
                        placement2 = placement;
                        if (optInt2 > 0) {
                            final Placement placement3 = placement2 = new Placement(optInt, optString, optString2, optInt2, placementAvailabilitySettings);
                            if (placementAvailabilitySettings != null) {
                                CappingManager.addCappingInfo(this.mContext, placement3);
                                placement2 = placement3;
                            }
                        }
                    }
                }
            }
        }
        return placement2;
    }
    
    public Configurations getConfigurations() {
        return this.mConfigurations;
    }
    
    public List<IronSource.AD_UNIT> getInitiatedAdUnits() {
        List<IronSource.AD_UNIT> list;
        if (this.mResponse == null || this.mConfigurations == null) {
            list = null;
        }
        else {
            final ArrayList<IronSource.AD_UNIT> list2 = new ArrayList<IronSource.AD_UNIT>();
            if (this.mConfigurations.getRewardedVideoConfigurations() != null && this.mProviderOrder != null && this.mProviderOrder.getRewardedVideoProviderOrder().size() > 0) {
                list2.add(IronSource.AD_UNIT.REWARDED_VIDEO);
            }
            if (this.mConfigurations.getInterstitialConfigurations() != null && this.mProviderOrder != null && this.mProviderOrder.getInterstitialProviderOrder().size() > 0) {
                list2.add(IronSource.AD_UNIT.INTERSTITIAL);
            }
            if (this.mConfigurations.getOfferwallConfigurations() != null) {
                list2.add(IronSource.AD_UNIT.OFFERWALL);
            }
            list = list2;
            if (this.mConfigurations.getBannerConfigurations() != null) {
                list2.add(IronSource.AD_UNIT.BANNER);
                return list2;
            }
        }
        return list;
    }
    
    public ProviderOrder getProviderOrder() {
        return this.mProviderOrder;
    }
    
    public ProviderSettingsHolder getProviderSettingsHolder() {
        return this.mProviderSettingsHolder;
    }
    
    public String getRVBackFillProvider() {
        try {
            return this.mProviderOrder.getRVBackFillProvider();
        }
        catch (Exception ex) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.INTERNAL, "getRVBackFillProvider", ex);
            return null;
        }
    }
    
    public String getRVPremiumProvider() {
        try {
            return this.mProviderOrder.getRVPremiumProvider();
        }
        catch (Exception ex) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.INTERNAL, "getRVPremiumProvider", ex);
            return null;
        }
    }
    
    public boolean isValidResponse() {
        boolean b;
        if (this.mResponse != null) {
            b = true;
        }
        else {
            b = false;
        }
        boolean b2;
        if (b && !this.mResponse.has("error")) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        boolean b3;
        if (b2 && this.mProviderOrder != null) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        boolean b4;
        if (b3 && this.mProviderSettingsHolder != null) {
            b4 = true;
        }
        else {
            b4 = false;
        }
        return b4 && this.mConfigurations != null;
    }
    
    @Override
    public String toString() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("appKey", (Object)this.mAppKey);
            jsonObject.put("userId", (Object)this.mUserId);
            jsonObject.put("response", (Object)this.mResponse);
            return jsonObject.toString();
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return jsonObject.toString();
        }
    }
}
