// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ProviderSettings
{
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
    
    public ProviderSettings(final ProviderSettings providerSettings) {
        this.mProviderName = providerSettings.getProviderName();
        this.mProviderInstanceName = providerSettings.getProviderName();
        this.mProviderTypeForReflection = providerSettings.getProviderTypeForReflection();
        this.mRewardedVideoSettings = providerSettings.getRewardedVideoSettings();
        this.mInterstitialSettings = providerSettings.getInterstitialSettings();
        this.mBannerSettings = providerSettings.getBannerSettings();
        this.mApplicationSettings = providerSettings.getApplicationSettings();
        this.mProviderRVPriority = providerSettings.getRewardedVideoPriority();
        this.mProviderISPriority = providerSettings.getInterstitialPriority();
        this.mProviderBNPriority = providerSettings.getBannerPriority();
    }
    
    public ProviderSettings(final String mProviderTypeForReflection) {
        this.mProviderName = mProviderTypeForReflection;
        this.mProviderInstanceName = mProviderTypeForReflection;
        this.mProviderTypeForReflection = mProviderTypeForReflection;
        this.mRewardedVideoSettings = new JSONObject();
        this.mInterstitialSettings = new JSONObject();
        this.mBannerSettings = new JSONObject();
        this.mApplicationSettings = new JSONObject();
        this.mProviderRVPriority = -1;
        this.mProviderISPriority = -1;
        this.mProviderBNPriority = -1;
    }
    
    public ProviderSettings(final String s, final String mProviderTypeForReflection, final JSONObject mApplicationSettings, final JSONObject mRewardedVideoSettings, final JSONObject mInterstitialSettings, final JSONObject mBannerSettings) {
        this.mProviderName = s;
        this.mProviderInstanceName = s;
        this.mProviderTypeForReflection = mProviderTypeForReflection;
        this.mRewardedVideoSettings = mRewardedVideoSettings;
        this.mInterstitialSettings = mInterstitialSettings;
        this.mBannerSettings = mBannerSettings;
        this.mApplicationSettings = mApplicationSettings;
        this.mProviderRVPriority = -1;
        this.mProviderISPriority = -1;
        this.mProviderBNPriority = -1;
    }
    
    public String getAdSourceNameForEvents() {
        return this.mAdSourceNameForEvents;
    }
    
    public JSONObject getApplicationSettings() {
        return this.mApplicationSettings;
    }
    
    public int getBannerPriority() {
        return this.mProviderBNPriority;
    }
    
    public JSONObject getBannerSettings() {
        return this.mBannerSettings;
    }
    
    public int getInterstitialPriority() {
        return this.mProviderISPriority;
    }
    
    public JSONObject getInterstitialSettings() {
        return this.mInterstitialSettings;
    }
    
    public String getProviderInstanceName() {
        return this.mProviderInstanceName;
    }
    
    public String getProviderName() {
        return this.mProviderName;
    }
    
    public String getProviderTypeForReflection() {
        return this.mProviderTypeForReflection;
    }
    
    public int getRewardedVideoPriority() {
        return this.mProviderRVPriority;
    }
    
    public JSONObject getRewardedVideoSettings() {
        return this.mRewardedVideoSettings;
    }
    
    public String getSubProviderId() {
        return this.mSubProviderId;
    }
    
    public boolean isMultipleInstances() {
        return this.mIsMultipleInstances;
    }
    
    public void setAdSourceNameForEvents(final String mAdSourceNameForEvents) {
        this.mAdSourceNameForEvents = mAdSourceNameForEvents;
    }
    
    public void setBannerPriority(final int mProviderBNPriority) {
        this.mProviderBNPriority = mProviderBNPriority;
    }
    
    public void setBannerSettings(final String s, final Object o) {
        try {
            this.mBannerSettings.put(s, o);
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setBannerSettings(final JSONObject mBannerSettings) {
        this.mBannerSettings = mBannerSettings;
    }
    
    public void setInterstitialPriority(final int mProviderISPriority) {
        this.mProviderISPriority = mProviderISPriority;
    }
    
    public void setInterstitialSettings(final String s, final Object o) {
        try {
            this.mInterstitialSettings.put(s, o);
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setInterstitialSettings(final JSONObject mInterstitialSettings) {
        this.mInterstitialSettings = mInterstitialSettings;
    }
    
    public void setIsMultipleInstances(final boolean mIsMultipleInstances) {
        this.mIsMultipleInstances = mIsMultipleInstances;
    }
    
    public void setRewardedVideoPriority(final int mProviderRVPriority) {
        this.mProviderRVPriority = mProviderRVPriority;
    }
    
    public void setRewardedVideoSettings(final String s, final Object o) {
        try {
            this.mRewardedVideoSettings.put(s, o);
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setRewardedVideoSettings(final JSONObject mRewardedVideoSettings) {
        this.mRewardedVideoSettings = mRewardedVideoSettings;
    }
    
    public void setSubProviderId(final String mSubProviderId) {
        this.mSubProviderId = mSubProviderId;
    }
}
