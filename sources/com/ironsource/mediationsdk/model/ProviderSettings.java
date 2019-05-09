package com.ironsource.mediationsdk.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ProviderSettings {
    private String mAdSourceNameForEvents;
    private JSONObject mApplicationSettings;
    private JSONObject mBannerSettings;
    private JSONObject mInterstitialSettings;
    private boolean mIsMultipleInstances;
    private int mProviderBNPriority;
    private int mProviderISPriority;
    private String mProviderInstanceName;
    private String mProviderName;
    private int mProviderRVPriority;
    private String mProviderTypeForReflection;
    private JSONObject mRewardedVideoSettings;
    private String mSubProviderId;

    public ProviderSettings(String providerName) {
        this.mProviderName = providerName;
        this.mProviderInstanceName = providerName;
        this.mProviderTypeForReflection = providerName;
        this.mRewardedVideoSettings = new JSONObject();
        this.mInterstitialSettings = new JSONObject();
        this.mBannerSettings = new JSONObject();
        this.mApplicationSettings = new JSONObject();
        this.mProviderRVPriority = -1;
        this.mProviderISPriority = -1;
        this.mProviderBNPriority = -1;
    }

    public ProviderSettings(String providerName, String providerType, JSONObject applicationSettings, JSONObject rewardedVideoSettings, JSONObject interstitialSettings, JSONObject bannerSettings) {
        this.mProviderName = providerName;
        this.mProviderInstanceName = providerName;
        this.mProviderTypeForReflection = providerType;
        this.mRewardedVideoSettings = rewardedVideoSettings;
        this.mInterstitialSettings = interstitialSettings;
        this.mBannerSettings = bannerSettings;
        this.mApplicationSettings = applicationSettings;
        this.mProviderRVPriority = -1;
        this.mProviderISPriority = -1;
        this.mProviderBNPriority = -1;
    }

    public ProviderSettings(ProviderSettings other) {
        this.mProviderName = other.getProviderName();
        this.mProviderInstanceName = other.getProviderName();
        this.mProviderTypeForReflection = other.getProviderTypeForReflection();
        this.mRewardedVideoSettings = other.getRewardedVideoSettings();
        this.mInterstitialSettings = other.getInterstitialSettings();
        this.mBannerSettings = other.getBannerSettings();
        this.mApplicationSettings = other.getApplicationSettings();
        this.mProviderRVPriority = other.getRewardedVideoPriority();
        this.mProviderISPriority = other.getInterstitialPriority();
        this.mProviderBNPriority = other.getBannerPriority();
    }

    public String getProviderName() {
        return this.mProviderName;
    }

    public JSONObject getRewardedVideoSettings() {
        return this.mRewardedVideoSettings;
    }

    public String getProviderTypeForReflection() {
        return this.mProviderTypeForReflection;
    }

    public void setRewardedVideoSettings(JSONObject rewardedVideoSettings) {
        this.mRewardedVideoSettings = rewardedVideoSettings;
    }

    public void setRewardedVideoSettings(String key, Object value) {
        try {
            this.mRewardedVideoSettings.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getInterstitialSettings() {
        return this.mInterstitialSettings;
    }

    public void setInterstitialSettings(JSONObject interstitialSettings) {
        this.mInterstitialSettings = interstitialSettings;
    }

    public void setInterstitialSettings(String key, Object value) {
        try {
            this.mInterstitialSettings.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getBannerSettings() {
        return this.mBannerSettings;
    }

    public void setBannerSettings(JSONObject bannerSettings) {
        this.mBannerSettings = bannerSettings;
    }

    public void setBannerSettings(String key, Object value) {
        try {
            this.mBannerSettings.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setAdSourceNameForEvents(String adSourceName) {
        this.mAdSourceNameForEvents = adSourceName;
    }

    public String getAdSourceNameForEvents() {
        return this.mAdSourceNameForEvents;
    }

    public void setSubProviderId(String subProviderId) {
        this.mSubProviderId = subProviderId;
    }

    public String getSubProviderId() {
        return this.mSubProviderId;
    }

    public void setIsMultipleInstances(boolean isMultipleInstances) {
        this.mIsMultipleInstances = isMultipleInstances;
    }

    public boolean isMultipleInstances() {
        return this.mIsMultipleInstances;
    }

    public String getProviderInstanceName() {
        return this.mProviderInstanceName;
    }

    public JSONObject getApplicationSettings() {
        return this.mApplicationSettings;
    }

    public void setBannerPriority(int priority) {
        this.mProviderBNPriority = priority;
    }

    public void setInterstitialPriority(int priority) {
        this.mProviderISPriority = priority;
    }

    public void setRewardedVideoPriority(int priority) {
        this.mProviderRVPriority = priority;
    }

    public int getBannerPriority() {
        return this.mProviderBNPriority;
    }

    public int getInterstitialPriority() {
        return this.mProviderISPriority;
    }

    public int getRewardedVideoPriority() {
        return this.mProviderRVPriority;
    }
}
