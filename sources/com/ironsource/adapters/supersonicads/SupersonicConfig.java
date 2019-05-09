package com.ironsource.adapters.supersonicads;

import android.text.TextUtils;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.model.ProviderSettingsHolder;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SupersonicConfig {
    private static SupersonicConfig mInstance;
    private final String APPLICATION_PRIVATE_KEY = "privateKey";
    private final String CAMPAIGN_ID = "campaignId";
    private final String CLIENT_SIDE_CALLBACKS = ParametersKeys.USE_CLIENT_SIDE_CALLBACKS;
    private final String CUSTOM_PARAM_PREFIX = "custom_";
    private final String DYNAMIC_CONTROLLER_DEBUG_MODE = "debugMode";
    private final String DYNAMIC_CONTROLLER_URL = "controllerUrl";
    private final String ITEM_COUNT = "itemCount";
    private final String ITEM_NAME = "itemName";
    private final String LANGUAGE = "language";
    private final String MAX_VIDEO_LENGTH = "maxVideoLength";
    private Map<String, String> mOfferwallCustomParams;
    ProviderSettings mProviderSettings = new ProviderSettings(ProviderSettingsHolder.getProviderSettingsHolder().getProviderSettings("Mediation"));
    private Map<String, String> mRewardedVideoCustomParams;

    public static SupersonicConfig getConfigObj() {
        if (mInstance == null) {
            mInstance = new SupersonicConfig();
        }
        return mInstance;
    }

    private SupersonicConfig() {
    }

    public void setClientSideCallbacks(boolean status) {
        this.mProviderSettings.setRewardedVideoSettings(ParametersKeys.USE_CLIENT_SIDE_CALLBACKS, String.valueOf(status));
    }

    public void setCustomControllerUrl(String url) {
        this.mProviderSettings.setRewardedVideoSettings("controllerUrl", url);
        this.mProviderSettings.setInterstitialSettings("controllerUrl", url);
        this.mProviderSettings.setBannerSettings("controllerUrl", url);
    }

    public void setDebugMode(int debugMode) {
        this.mProviderSettings.setRewardedVideoSettings("debugMode", Integer.valueOf(debugMode));
        this.mProviderSettings.setInterstitialSettings("debugMode", Integer.valueOf(debugMode));
        this.mProviderSettings.setBannerSettings("debugMode", Integer.valueOf(debugMode));
    }

    public void setCampaignId(String id) {
        this.mProviderSettings.setRewardedVideoSettings("campaignId", id);
    }

    public void setLanguage(String language) {
        this.mProviderSettings.setRewardedVideoSettings("language", language);
        this.mProviderSettings.setInterstitialSettings("language", language);
    }

    public void setRewardedVideoCustomParams(Map<String, String> rvCustomParams) {
        this.mRewardedVideoCustomParams = convertCustomParams(rvCustomParams);
    }

    public void setOfferwallCustomParams(Map<String, String> owCustomParams) {
        this.mOfferwallCustomParams = convertCustomParams(owCustomParams);
    }

    private Map<String, String> convertCustomParams(Map<String, String> customParams) {
        Map<String, String> result = new HashMap();
        if (customParams != null) {
            try {
                Set<String> keys = customParams.keySet();
                if (keys != null) {
                    for (String k : keys) {
                        if (!TextUtils.isEmpty(k)) {
                            String value = (String) customParams.get(k);
                            if (!TextUtils.isEmpty(value)) {
                                result.put("custom_" + k, value);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, ":convertCustomParams()", e);
            }
        }
        return result;
    }

    public boolean getClientSideCallbacks() {
        if (this.mProviderSettings == null || this.mProviderSettings.getRewardedVideoSettings() == null || !this.mProviderSettings.getRewardedVideoSettings().has(ParametersKeys.USE_CLIENT_SIDE_CALLBACKS)) {
            return false;
        }
        return this.mProviderSettings.getRewardedVideoSettings().optBoolean(ParametersKeys.USE_CLIENT_SIDE_CALLBACKS, false);
    }

    Map<String, String> getOfferwallCustomParams() {
        return this.mOfferwallCustomParams;
    }

    Map<String, String> getRewardedVideoCustomParams() {
        return this.mRewardedVideoCustomParams;
    }
}
