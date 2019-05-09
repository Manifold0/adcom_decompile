// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.adapters.supersonicads;

import java.util.Iterator;
import java.util.Set;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import android.text.TextUtils;
import java.util.HashMap;
import com.ironsource.mediationsdk.model.ProviderSettingsHolder;
import com.ironsource.mediationsdk.model.ProviderSettings;
import java.util.Map;

public class SupersonicConfig
{
    private static SupersonicConfig mInstance;
    private final String APPLICATION_PRIVATE_KEY;
    private final String CAMPAIGN_ID;
    private final String CLIENT_SIDE_CALLBACKS;
    private final String CUSTOM_PARAM_PREFIX;
    private final String DYNAMIC_CONTROLLER_DEBUG_MODE;
    private final String DYNAMIC_CONTROLLER_URL;
    private final String ITEM_COUNT;
    private final String ITEM_NAME;
    private final String LANGUAGE;
    private final String MAX_VIDEO_LENGTH;
    private Map<String, String> mOfferwallCustomParams;
    ProviderSettings mProviderSettings;
    private Map<String, String> mRewardedVideoCustomParams;
    
    private SupersonicConfig() {
        this.CUSTOM_PARAM_PREFIX = "custom_";
        this.CLIENT_SIDE_CALLBACKS = "useClientSideCallbacks";
        this.MAX_VIDEO_LENGTH = "maxVideoLength";
        this.DYNAMIC_CONTROLLER_URL = "controllerUrl";
        this.DYNAMIC_CONTROLLER_DEBUG_MODE = "debugMode";
        this.CAMPAIGN_ID = "campaignId";
        this.LANGUAGE = "language";
        this.APPLICATION_PRIVATE_KEY = "privateKey";
        this.ITEM_NAME = "itemName";
        this.ITEM_COUNT = "itemCount";
        this.mProviderSettings = new ProviderSettings(ProviderSettingsHolder.getProviderSettingsHolder().getProviderSettings("Mediation"));
    }
    
    private Map<String, String> convertCustomParams(final Map<String, String> map) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        if (map != null) {
            try {
                final Set<String> keySet = map.keySet();
                if (keySet != null) {
                    for (final String s : keySet) {
                        if (!TextUtils.isEmpty((CharSequence)s)) {
                            final String s2 = map.get(s);
                            if (TextUtils.isEmpty((CharSequence)s2)) {
                                continue;
                            }
                            hashMap.put("custom_" + s, s2);
                        }
                    }
                }
            }
            catch (Exception ex) {
                IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, ":convertCustomParams()", ex);
            }
        }
        return hashMap;
    }
    
    public static SupersonicConfig getConfigObj() {
        if (SupersonicConfig.mInstance == null) {
            SupersonicConfig.mInstance = new SupersonicConfig();
        }
        return SupersonicConfig.mInstance;
    }
    
    public boolean getClientSideCallbacks() {
        boolean optBoolean;
        final boolean b = optBoolean = false;
        if (this.mProviderSettings != null) {
            optBoolean = b;
            if (this.mProviderSettings.getRewardedVideoSettings() != null) {
                optBoolean = b;
                if (this.mProviderSettings.getRewardedVideoSettings().has("useClientSideCallbacks")) {
                    optBoolean = this.mProviderSettings.getRewardedVideoSettings().optBoolean("useClientSideCallbacks", false);
                }
            }
        }
        return optBoolean;
    }
    
    Map<String, String> getOfferwallCustomParams() {
        return this.mOfferwallCustomParams;
    }
    
    Map<String, String> getRewardedVideoCustomParams() {
        return this.mRewardedVideoCustomParams;
    }
    
    public void setCampaignId(final String s) {
        this.mProviderSettings.setRewardedVideoSettings("campaignId", s);
    }
    
    public void setClientSideCallbacks(final boolean b) {
        this.mProviderSettings.setRewardedVideoSettings("useClientSideCallbacks", String.valueOf(b));
    }
    
    public void setCustomControllerUrl(final String s) {
        this.mProviderSettings.setRewardedVideoSettings("controllerUrl", s);
        this.mProviderSettings.setInterstitialSettings("controllerUrl", s);
        this.mProviderSettings.setBannerSettings("controllerUrl", s);
    }
    
    public void setDebugMode(final int n) {
        this.mProviderSettings.setRewardedVideoSettings("debugMode", n);
        this.mProviderSettings.setInterstitialSettings("debugMode", n);
        this.mProviderSettings.setBannerSettings("debugMode", n);
    }
    
    public void setLanguage(final String s) {
        this.mProviderSettings.setRewardedVideoSettings("language", s);
        this.mProviderSettings.setInterstitialSettings("language", s);
    }
    
    public void setOfferwallCustomParams(final Map<String, String> map) {
        this.mOfferwallCustomParams = this.convertCustomParams(map);
    }
    
    public void setRewardedVideoCustomParams(final Map<String, String> map) {
        this.mRewardedVideoCustomParams = this.convertCustomParams(map);
    }
}
