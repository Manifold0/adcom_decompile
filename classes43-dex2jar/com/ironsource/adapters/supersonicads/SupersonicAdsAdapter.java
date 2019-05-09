// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.adapters.supersonicads;

import com.ironsource.mediationsdk.utils.SessionDepthManager;
import com.ironsource.mediationsdk.AbstractSmash;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.sdk.data.AdUnitsReady;
import java.util.Iterator;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.sdk.SSAFactory;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.ironsource.mediationsdk.IronSourceObject;
import com.ironsource.sdk.utils.SDKUtils;
import java.util.Map;
import com.ironsource.mediationsdk.IntegrationData;
import android.app.Activity;
import org.json.JSONException;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import android.text.TextUtils;
import java.util.HashMap;
import org.json.JSONObject;
import com.ironsource.sdk.SSAPublisher;
import com.ironsource.mediationsdk.sdk.InternalOfferwallListener;
import com.ironsource.sdk.listeners.OnRewardedVideoListener;
import com.ironsource.sdk.listeners.OnInterstitialListener;
import com.ironsource.sdk.listeners.OnOfferWallListener;
import com.ironsource.mediationsdk.sdk.OfferwallAdapterApi;
import com.ironsource.mediationsdk.AbstractAdapter;

class SupersonicAdsAdapter extends AbstractAdapter implements OfferwallAdapterApi, OnOfferWallListener, OnInterstitialListener, OnRewardedVideoListener
{
    private static final String VERSION = "6.8.0";
    private final String AD_VISIBLE_EVENT_NAME;
    private final String APPLICATION_PRIVATE_KEY;
    private final String APPLICATION_USER_AGE_GROUP;
    private final String APPLICATION_USER_GENDER;
    private final String CAMPAIGN_ID;
    private final String CLIENT_SIDE_CALLBACKS;
    private final String CUSTOM_PARAM_PREFIX;
    private final String CUSTOM_SEGMENT;
    private final String DYNAMIC_CONTROLLER_CONFIG;
    private final String DYNAMIC_CONTROLLER_DEBUG_MODE;
    private final String DYNAMIC_CONTROLLER_URL;
    private final String ITEM_COUNT;
    private final String ITEM_NAME;
    private final String ITEM_SIGNATURE;
    private final String LANGUAGE;
    private final String MAX_VIDEO_LENGTH;
    private final String OW_PLACEMENT_ID;
    private final String SDK_PLUGIN_TYPE;
    private final String TIMESTAMP;
    private boolean mConsent;
    private boolean mDidSetConsent;
    private boolean mDidSetInitParams;
    private boolean mIsRVAvailable;
    private String mMediationSegment;
    private InternalOfferwallListener mOfferwallListener;
    private SSAPublisher mSSAPublisher;
    private String mUserAgeGroup;
    private String mUserGender;
    
    private SupersonicAdsAdapter(final String s) {
        super(s);
        this.TIMESTAMP = "timestamp";
        this.ITEM_SIGNATURE = "itemSignature";
        this.SDK_PLUGIN_TYPE = "SDKPluginType";
        this.OW_PLACEMENT_ID = "placementId";
        this.mIsRVAvailable = false;
        this.mDidSetInitParams = false;
        this.DYNAMIC_CONTROLLER_URL = "controllerUrl";
        this.DYNAMIC_CONTROLLER_DEBUG_MODE = "debugMode";
        this.DYNAMIC_CONTROLLER_CONFIG = "controllerConfig";
        this.APPLICATION_USER_GENDER = "applicationUserGender";
        this.APPLICATION_USER_AGE_GROUP = "applicationUserAgeGroup";
        this.LANGUAGE = "language";
        this.MAX_VIDEO_LENGTH = "maxVideoLength";
        this.CAMPAIGN_ID = "campaignId";
        this.CUSTOM_PARAM_PREFIX = "custom_";
        this.CUSTOM_SEGMENT = "custom_Segment";
        this.ITEM_NAME = "itemName";
        this.ITEM_COUNT = "itemCount";
        this.APPLICATION_PRIVATE_KEY = "privateKey";
        this.CLIENT_SIDE_CALLBACKS = "useClientSideCallbacks";
        this.AD_VISIBLE_EVENT_NAME = "impressions";
    }
    
    private void addItemNameCountSignature(final HashMap<String, String> hashMap, final JSONObject jsonObject) {
        String optString;
        int optInt = 0;
        String optString2;
        int n;
        int currentTimestamp;
        Label_0048_Outer:Label_0111_Outer:
        while (true) {
            while (true) {
                while (true) {
                    Label_0126: {
                        try {
                            optString = jsonObject.optString("itemName");
                            optInt = jsonObject.optInt("itemCount", -1);
                            optString2 = jsonObject.optString("privateKey");
                            n = 1;
                            if (TextUtils.isEmpty((CharSequence)optString)) {
                                n = 0;
                            }
                            else {
                                hashMap.put("itemName", optString);
                            }
                            if (TextUtils.isEmpty((CharSequence)optString2)) {
                                n = 0;
                            }
                            break Label_0126;
                            while (true) {
                                currentTimestamp = IronSourceUtils.getCurrentTimestamp();
                                hashMap.put("timestamp", String.valueOf(currentTimestamp));
                                hashMap.put("itemSignature", this.createItemSig(currentTimestamp, optString, optInt, optString2));
                                return;
                                continue Label_0048_Outer;
                            }
                        }
                        // iftrue(Label_0137:, n == 0)
                        catch (Exception ex) {
                            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.ADAPTER_API, " addItemNameCountSignature", ex);
                            return;
                        }
                        hashMap.put("itemCount", String.valueOf(optInt));
                        continue Label_0111_Outer;
                    }
                    if (optInt == -1) {
                        n = 0;
                        continue Label_0111_Outer;
                    }
                    break;
                }
                continue;
            }
        }
        Label_0137:;
    }
    
    private void applyConsent(final boolean b) {
        if (this.mSSAPublisher == null) {
            return;
        }
        final JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                jsonObject.put("gdprConsentStatus", (Object)String.valueOf(b));
                jsonObject.put("demandSourceName", (Object)this.getProviderName());
                this.mSSAPublisher.updateConsentInfo(jsonObject);
            }
            catch (JSONException ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    private String createItemSig(final int n, final String s, final int n2, final String s2) {
        return IronSourceUtils.getMD5(n + s + n2 + s2);
    }
    
    private String createMinimumOfferCommissionSig(final double n, final String s) {
        return IronSourceUtils.getMD5(n + s);
    }
    
    private String createUserCreationDateSig(final String s, final String s2, final String s3) {
        return IronSourceUtils.getMD5(s + s2 + s3);
    }
    
    private HashMap<String, String> getGenenralExtraParams() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        if (!TextUtils.isEmpty((CharSequence)this.mUserAgeGroup)) {
            hashMap.put("applicationUserAgeGroup", this.mUserAgeGroup);
        }
        if (!TextUtils.isEmpty((CharSequence)this.mUserGender)) {
            hashMap.put("applicationUserGender", this.mUserGender);
        }
        final String pluginType = this.getPluginType();
        if (!TextUtils.isEmpty((CharSequence)pluginType)) {
            hashMap.put("SDKPluginType", pluginType);
        }
        return hashMap;
    }
    
    public static IntegrationData getIntegrationData(final Activity activity) {
        final IntegrationData integrationData = new IntegrationData("SupersonicAds", "6.8.0");
        integrationData.activities = new String[] { "com.ironsource.sdk.controller.ControllerActivity", "com.ironsource.sdk.controller.InterstitialActivity", "com.ironsource.sdk.controller.OpenUrlActivity" };
        return integrationData;
    }
    
    private HashMap<String, String> getInterstitialExtraParams() {
        return this.getGenenralExtraParams();
    }
    
    private HashMap<String, String> getOfferwallExtraParams(final JSONObject jsonObject) {
        final HashMap<String, String> genenralExtraParams = this.getGenenralExtraParams();
        final String optString = jsonObject.optString("language");
        if (!TextUtils.isEmpty((CharSequence)optString)) {
            genenralExtraParams.put("language", optString);
        }
        genenralExtraParams.put("useClientSideCallbacks", String.valueOf(SupersonicConfig.getConfigObj().getClientSideCallbacks()));
        final Map<String, String> offerwallCustomParams = SupersonicConfig.getConfigObj().getOfferwallCustomParams();
        if (offerwallCustomParams != null && !offerwallCustomParams.isEmpty()) {
            genenralExtraParams.putAll(offerwallCustomParams);
        }
        this.addItemNameCountSignature(genenralExtraParams, jsonObject);
        return genenralExtraParams;
    }
    
    private HashMap<String, String> getRewardedVideoExtraParams(final JSONObject jsonObject) {
        final HashMap<String, String> genenralExtraParams = this.getGenenralExtraParams();
        final String optString = jsonObject.optString("language");
        if (!TextUtils.isEmpty((CharSequence)optString)) {
            genenralExtraParams.put("language", optString);
        }
        final String optString2 = jsonObject.optString("maxVideoLength");
        if (!TextUtils.isEmpty((CharSequence)optString2)) {
            genenralExtraParams.put("maxVideoLength", optString2);
        }
        final String optString3 = jsonObject.optString("campaignId");
        if (!TextUtils.isEmpty((CharSequence)optString3)) {
            genenralExtraParams.put("campaignId", optString3);
        }
        if (!TextUtils.isEmpty((CharSequence)this.mMediationSegment)) {
            genenralExtraParams.put("custom_Segment", this.mMediationSegment);
        }
        this.addItemNameCountSignature(genenralExtraParams, jsonObject);
        final Map<String, String> rewardedVideoCustomParams = SupersonicConfig.getConfigObj().getRewardedVideoCustomParams();
        if (rewardedVideoCustomParams != null && !rewardedVideoCustomParams.isEmpty()) {
            genenralExtraParams.putAll(rewardedVideoCustomParams);
        }
        return genenralExtraParams;
    }
    
    private void setParamsBeforeInit(final JSONObject jsonObject) {
        synchronized (this) {
            this.mDidSetInitParams = true;
            SDKUtils.setControllerUrl(jsonObject.optString("controllerUrl"));
            if (this.isAdaptersDebugEnabled()) {
                SDKUtils.setDebugMode(3);
            }
            else {
                SDKUtils.setDebugMode(jsonObject.optInt("debugMode", 0));
            }
            SDKUtils.setControllerConfig(jsonObject.optString("controllerConfig", ""));
        }
    }
    
    public static SupersonicAdsAdapter startAdapter(final String s) {
        return new SupersonicAdsAdapter(s);
    }
    
    @Override
    public void fetchRewardedVideo(final JSONObject jsonObject) {
    }
    
    @Override
    public String getCoreSDKVersion() {
        return SDKUtils.getSDKVersion();
    }
    
    @Override
    public void getOfferwallCredits() {
        if (this.mSSAPublisher != null) {
            this.mSSAPublisher.getOfferWallCredits(IronSourceObject.getInstance().getIronSourceAppKey(), IronSourceObject.getInstance().getIronSourceUserId(), this);
            return;
        }
        this.log(IronSourceLogger.IronSourceTag.NATIVE, "Please call init before calling getOfferwallCredits", 2);
    }
    
    @Override
    public String getVersion() {
        return "6.8.0";
    }
    
    @Override
    public void initInterstitial(final Activity activity, final String s, final String s2, final JSONObject paramsBeforeInit, final InterstitialSmashListener interstitialSmashListener) {
        if (!this.mDidSetInitParams) {
            this.setParamsBeforeInit(paramsBeforeInit);
        }
        activity.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    SupersonicAdsAdapter.this.mSSAPublisher = SSAFactory.getPublisherInstance(activity);
                    final HashMap access$500 = SupersonicAdsAdapter.this.getInterstitialExtraParams();
                    if (SupersonicAdsAdapter.this.mDidSetConsent) {
                        SupersonicAdsAdapter.this.applyConsent(SupersonicAdsAdapter.this.mConsent);
                    }
                    SupersonicAdsAdapter.this.mSSAPublisher.initInterstitial(s, s2, SupersonicAdsAdapter.this.getProviderName(), access$500, SupersonicAdsAdapter.this);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    SupersonicAdsAdapter.this.onInterstitialInitFailed(ex.getMessage());
                }
            }
        });
    }
    
    @Override
    public void initOfferwall(final Activity activity, final String s, final String s2, final JSONObject paramsBeforeInit) {
        if (!this.mDidSetInitParams) {
            this.setParamsBeforeInit(paramsBeforeInit);
        }
        activity.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    final HashMap access$600 = SupersonicAdsAdapter.this.getOfferwallExtraParams(paramsBeforeInit);
                    SupersonicAdsAdapter.this.mSSAPublisher = SSAFactory.getPublisherInstance(activity);
                    if (SupersonicAdsAdapter.this.mDidSetConsent) {
                        SupersonicAdsAdapter.this.applyConsent(SupersonicAdsAdapter.this.mConsent);
                    }
                    SupersonicAdsAdapter.this.mSSAPublisher.initOfferWall(s, s2, access$600, SupersonicAdsAdapter.this);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.ADAPTER_API, SupersonicAdsAdapter.this.getProviderName() + ":initOfferwall(userId:" + s2 + ")", ex);
                    SupersonicAdsAdapter.this.mOfferwallListener.onOfferwallAvailable(false, ErrorBuilder.buildInitFailedError("Adapter initialization failure - " + SupersonicAdsAdapter.this.getProviderName() + " - " + ex.getMessage(), "Offerwall"));
                }
            }
        });
    }
    
    @Override
    public void initRewardedVideo(final Activity activity, final String s, final String s2, final JSONObject paramsBeforeInit, final RewardedVideoSmashListener rewardedVideoSmashListener) {
        if (!this.mDidSetInitParams) {
            this.setParamsBeforeInit(paramsBeforeInit);
        }
        activity.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    SupersonicAdsAdapter.this.mSSAPublisher = SSAFactory.getPublisherInstance(activity);
                    final HashMap access$100 = SupersonicAdsAdapter.this.getRewardedVideoExtraParams(paramsBeforeInit);
                    if (SupersonicAdsAdapter.this.mDidSetConsent) {
                        SupersonicAdsAdapter.this.applyConsent(SupersonicAdsAdapter.this.mConsent);
                    }
                    SupersonicAdsAdapter.this.mSSAPublisher.initRewardedVideo(s, s2, SupersonicAdsAdapter.this.getProviderName(), access$100, SupersonicAdsAdapter.this);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    SupersonicAdsAdapter.this.onRVInitFail("initRewardedVideo");
                }
            }
        });
    }
    
    @Override
    public boolean isInterstitialReady(final JSONObject jsonObject) {
        return this.mSSAPublisher != null && this.mSSAPublisher.isInterstitialAdAvailable(this.getProviderName());
    }
    
    @Override
    public boolean isOfferwallAvailable() {
        return true;
    }
    
    @Override
    public boolean isRewardedVideoAvailable(final JSONObject jsonObject) {
        synchronized (this) {
            return this.mIsRVAvailable;
        }
    }
    
    @Override
    public void loadInterstitial(JSONObject iterator, InterstitialSmashListener interstitialSmashListener) {
        Label_0045: {
            if (this.mSSAPublisher == null) {
                break Label_0045;
            }
            iterator = new JSONObject();
            while (true) {
                try {
                    ((JSONObject)iterator).put("demandSourceName", (Object)this.getProviderName());
                    this.mSSAPublisher.loadInterstitial((JSONObject)iterator);
                    return;
                }
                catch (JSONException ex) {
                    ex.printStackTrace();
                    continue;
                }
                break;
            }
        }
        this.log(IronSourceLogger.IronSourceTag.NATIVE, "Please call initInterstitial before calling loadInterstitial", 2);
        iterator = this.mAllInterstitialSmashes.iterator();
        while (((Iterator)iterator).hasNext()) {
            interstitialSmashListener = ((Iterator<InterstitialSmashListener>)iterator).next();
            if (interstitialSmashListener != null) {
                interstitialSmashListener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("Load was called before Init"));
            }
        }
    }
    
    @Override
    public void onGetOWCreditsFailed(final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onGetOWCreditsFailed ", 1);
        if (this.mOfferwallListener != null) {
            this.mOfferwallListener.onGetOfferwallCreditsFailed(ErrorBuilder.buildGenericError(s));
        }
    }
    
    @Override
    public void onInterstitialClick() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onInterstitialAdClicked ", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdClicked();
        }
    }
    
    @Override
    public void onInterstitialClose() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onInterstitialAdClosed ", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdClosed();
        }
    }
    
    @Override
    public void onInterstitialEventNotificationReceived(final String s, final JSONObject jsonObject) {
        if (jsonObject != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onInterstitialEventNotificationReceived: " + s + " extData: " + jsonObject.toString(), 1);
            if (!TextUtils.isEmpty((CharSequence)s) && "impressions".equals(s) && this.mActiveInterstitialSmash != null) {
                this.mActiveInterstitialSmash.onInterstitialAdVisible();
            }
        }
    }
    
    @Override
    public void onInterstitialInitFailed(final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onInterstitialInitFailed ", 1);
        for (final InterstitialSmashListener interstitialSmashListener : this.mAllInterstitialSmashes) {
            if (interstitialSmashListener != null) {
                interstitialSmashListener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError(s, "Interstitial"));
            }
        }
    }
    
    @Override
    public void onInterstitialInitSuccess() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onInterstitialInitSuccess ", 1);
        for (final InterstitialSmashListener interstitialSmashListener : this.mAllInterstitialSmashes) {
            if (interstitialSmashListener != null) {
                interstitialSmashListener.onInterstitialInitSuccess();
            }
        }
    }
    
    @Override
    public void onInterstitialLoadFailed(final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onInterstitialAdLoadFailed ", 1);
        for (final InterstitialSmashListener interstitialSmashListener : this.mAllInterstitialSmashes) {
            if (interstitialSmashListener != null) {
                interstitialSmashListener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError(s));
            }
        }
    }
    
    @Override
    public void onInterstitialLoadSuccess() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onInterstitialLoadSuccess ", 1);
        for (final InterstitialSmashListener interstitialSmashListener : this.mAllInterstitialSmashes) {
            if (interstitialSmashListener != null) {
                interstitialSmashListener.onInterstitialAdReady();
            }
        }
    }
    
    @Override
    public void onInterstitialOpen() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onInterstitialAdOpened ", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdOpened();
        }
    }
    
    @Override
    public void onInterstitialShowFailed(final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onInterstitialAdShowFailed ", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdShowFailed(ErrorBuilder.buildShowFailedError("Interstitial", s));
        }
    }
    
    @Override
    public void onInterstitialShowSuccess() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onInterstitialAdShowSucceeded ", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdShowSucceeded();
        }
    }
    
    @Override
    public void onOWAdClosed() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onOWAdClosed ", 1);
        if (this.mOfferwallListener != null) {
            this.mOfferwallListener.onOfferwallClosed();
        }
    }
    
    @Override
    public boolean onOWAdCredited(final int n, final int n2, final boolean b) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onOWAdCredited ", 1);
        return this.mOfferwallListener != null && this.mOfferwallListener.onOfferwallAdCredited(n, n2, b);
    }
    
    @Override
    public void onOWGeneric(final String s, final String s2) {
    }
    
    @Override
    public void onOWShowFail(final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onOWShowFail ", 1);
        if (this.mOfferwallListener != null) {
            this.mOfferwallListener.onOfferwallShowFailed(ErrorBuilder.buildGenericError(s));
        }
    }
    
    @Override
    public void onOWShowSuccess(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            this.log(IronSourceLogger.IronSourceTag.ADAPTER_API, this.getProviderName() + ":onOWShowSuccess()", 1);
        }
        else {
            this.log(IronSourceLogger.IronSourceTag.ADAPTER_API, this.getProviderName() + ":onOWShowSuccess(placementId:" + s + ")", 1);
        }
        if (this.mOfferwallListener != null) {
            this.mOfferwallListener.onOfferwallOpened();
        }
    }
    
    @Override
    public void onOfferwallEventNotificationReceived(final String s, final JSONObject jsonObject) {
        if (jsonObject != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onOfferwallEventNotificationReceived ", 1);
        }
    }
    
    @Override
    public void onOfferwallInitFail(final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onOfferwallInitFail ", 1);
        if (this.mOfferwallListener != null) {
            this.mOfferwallListener.onOfferwallAvailable(false, ErrorBuilder.buildGenericError(s));
        }
    }
    
    @Override
    public void onOfferwallInitSuccess() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onOfferwallInitSuccess ", 1);
        if (this.mOfferwallListener != null) {
            this.mOfferwallListener.onOfferwallAvailable(true);
        }
    }
    
    @Override
    public void onPause(final Activity activity) {
        if (this.mSSAPublisher != null) {
            this.mSSAPublisher.onPause(activity);
        }
    }
    
    @Override
    public void onRVAdClicked() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onRVAdClicked ", 1);
        if (this.mActiveRewardedVideoSmash != null) {
            this.mActiveRewardedVideoSmash.onRewardedVideoAdClicked();
        }
    }
    
    @Override
    public void onRVAdClosed() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onRVAdClosed ", 1);
        if (this.mActiveRewardedVideoSmash != null) {
            this.mActiveRewardedVideoSmash.onRewardedVideoAdClosed();
        }
    }
    
    @Override
    public void onRVAdCredited(final int n) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onRVAdCredited ", 1);
        if (this.mActiveRewardedVideoSmash != null) {
            this.mActiveRewardedVideoSmash.onRewardedVideoAdRewarded();
        }
    }
    
    @Override
    public void onRVAdOpened() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onRVAdOpened ", 1);
        if (this.mActiveRewardedVideoSmash != null) {
            this.mActiveRewardedVideoSmash.onRewardedVideoAdOpened();
        }
    }
    
    @Override
    public void onRVEventNotificationReceived(final String s, final JSONObject jsonObject) {
        if (jsonObject != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onRVEventNotificationReceived: " + s + " extData: " + jsonObject.toString(), 1);
        }
        if (!TextUtils.isEmpty((CharSequence)s) && "impressions".equals(s) && this.mActiveRewardedVideoSmash != null) {
            this.mActiveRewardedVideoSmash.onRewardedVideoAdVisible();
        }
    }
    
    @Override
    public void onRVInitFail(final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onRVInitFail ", 1);
        for (final RewardedVideoSmashListener rewardedVideoSmashListener : this.mAllRewardedVideoSmashes) {
            if (rewardedVideoSmashListener != null) {
                rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
            }
        }
    }
    
    @Override
    public void onRVInitSuccess(final AdUnitsReady adUnitsReady) {
    Label_0052_Outer:
        while (true) {
            boolean mIsRVAvailable = true;
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onRVInitSuccess ", 1);
            int int1 = 0;
            while (true) {
                while (true) {
                    try {
                        int1 = Integer.parseInt(adUnitsReady.getNumOfAdUnits());
                        if (int1 > 0) {
                            this.mIsRVAvailable = mIsRVAvailable;
                            for (final RewardedVideoSmashListener rewardedVideoSmashListener : this.mAllRewardedVideoSmashes) {
                                if (rewardedVideoSmashListener != null) {
                                    rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(mIsRVAvailable);
                                }
                            }
                            break;
                        }
                    }
                    catch (NumberFormatException ex) {
                        IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "onRVInitSuccess:parseInt()", ex);
                        continue Label_0052_Outer;
                    }
                    break;
                }
                mIsRVAvailable = false;
                continue;
            }
        }
    }
    
    @Override
    public void onRVNoMoreOffers() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onRVNoMoreOffers ", 1);
        this.mIsRVAvailable = false;
        for (final RewardedVideoSmashListener rewardedVideoSmashListener : this.mAllRewardedVideoSmashes) {
            if (rewardedVideoSmashListener != null) {
                rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
            }
        }
    }
    
    @Override
    public void onRVShowFail(final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onRVShowFail ", 1);
        if (this.mActiveRewardedVideoSmash != null) {
            this.mActiveRewardedVideoSmash.onRewardedVideoAdShowFailed(new IronSourceError(509, s));
        }
    }
    
    @Override
    public void onResume(final Activity activity) {
        if (this.mSSAPublisher != null) {
            this.mSSAPublisher.onResume(activity);
        }
    }
    
    @Override
    public void setAge(final int n) {
        if (n >= 13 && n <= 17) {
            this.mUserAgeGroup = "1";
            return;
        }
        if (n >= 18 && n <= 20) {
            this.mUserAgeGroup = "2";
            return;
        }
        if (n >= 21 && n <= 24) {
            this.mUserAgeGroup = "3";
            return;
        }
        if (n >= 25 && n <= 34) {
            this.mUserAgeGroup = "4";
            return;
        }
        if (n >= 35 && n <= 44) {
            this.mUserAgeGroup = "5";
            return;
        }
        if (n >= 45 && n <= 54) {
            this.mUserAgeGroup = "6";
            return;
        }
        if (n >= 55 && n <= 64) {
            this.mUserAgeGroup = "7";
            return;
        }
        if (n > 65 && n <= 120) {
            this.mUserAgeGroup = "8";
            return;
        }
        this.mUserAgeGroup = "0";
    }
    
    @Override
    protected void setConsent(final boolean mConsent) {
        this.mDidSetConsent = true;
        this.applyConsent(this.mConsent = mConsent);
    }
    
    @Override
    public void setGender(final String mUserGender) {
        this.mUserGender = mUserGender;
    }
    
    @Override
    public void setInternalOfferwallListener(final InternalOfferwallListener mOfferwallListener) {
        this.mOfferwallListener = mOfferwallListener;
    }
    
    @Override
    public void setMediationSegment(final String mMediationSegment) {
        this.mMediationSegment = mMediationSegment;
    }
    
    @Override
    protected void setMediationState(final AbstractSmash.MEDIATION_STATE mediation_STATE, final String s) {
        if (this.mSSAPublisher != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :setMediationState(" + s + " , " + this.getProviderName() + " , " + mediation_STATE.getValue() + ")", 1);
            this.mSSAPublisher.setMediationState(s, this.getProviderName(), mediation_STATE.getValue());
        }
    }
    
    @Override
    public void showInterstitial(JSONObject jsonObject, final InterstitialSmashListener mActiveInterstitialSmash) {
        this.mActiveInterstitialSmash = mActiveInterstitialSmash;
        Label_0067: {
            if (this.mSSAPublisher == null) {
                break Label_0067;
            }
            final int sessionDepth = SessionDepthManager.getInstance().getSessionDepth(2);
            jsonObject = new JSONObject();
            while (true) {
                try {
                    jsonObject.put("demandSourceName", (Object)this.getProviderName());
                    jsonObject.put("sessionDepth", sessionDepth);
                    this.mSSAPublisher.showInterstitial(jsonObject);
                    return;
                }
                catch (JSONException ex) {
                    ex.printStackTrace();
                    continue;
                }
                break;
            }
        }
        this.log(IronSourceLogger.IronSourceTag.NATIVE, "Please call loadInterstitial before calling showInterstitial", 2);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
        }
    }
    
    @Override
    public void showOfferwall(final String s, final JSONObject jsonObject) {
        final HashMap<String, String> offerwallExtraParams = this.getOfferwallExtraParams(jsonObject);
        if (offerwallExtraParams != null) {
            offerwallExtraParams.put("placementId", s);
        }
        if (this.mSSAPublisher != null) {
            this.mSSAPublisher.showOfferWall(offerwallExtraParams);
            return;
        }
        this.log(IronSourceLogger.IronSourceTag.NATIVE, "Please call init before calling showOfferwall", 2);
    }
    
    @Override
    public void showRewardedVideo(JSONObject iterator, RewardedVideoSmashListener mActiveRewardedVideoSmash) {
        this.mActiveRewardedVideoSmash = mActiveRewardedVideoSmash;
        Label_0067: {
            if (this.mSSAPublisher == null) {
                break Label_0067;
            }
            final int sessionDepth = SessionDepthManager.getInstance().getSessionDepth(1);
            iterator = new JSONObject();
            while (true) {
                try {
                    ((JSONObject)iterator).put("demandSourceName", (Object)this.getProviderName());
                    ((JSONObject)iterator).put("sessionDepth", sessionDepth);
                    this.mSSAPublisher.showRewardedVideo((JSONObject)iterator);
                    return;
                }
                catch (JSONException ex) {
                    ex.printStackTrace();
                    continue;
                }
                break;
            }
        }
        this.mIsRVAvailable = false;
        if (this.mActiveRewardedVideoSmash != null) {
            this.mActiveRewardedVideoSmash.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Rewarded Video"));
        }
        iterator = this.mAllRewardedVideoSmashes.iterator();
        while (((Iterator)iterator).hasNext()) {
            mActiveRewardedVideoSmash = ((Iterator<RewardedVideoSmashListener>)iterator).next();
            if (mActiveRewardedVideoSmash != null) {
                mActiveRewardedVideoSmash.onRewardedVideoAvailabilityChanged(false);
            }
        }
    }
}
