package com.ironsource.adapters.supersonicads;

import android.app.Activity;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.ironsource.mediationsdk.AbstractAdapter;
import com.ironsource.mediationsdk.AbstractSmash.MEDIATION_STATE;
import com.ironsource.mediationsdk.IntegrationData;
import com.ironsource.mediationsdk.IronSourceObject;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.sdk.InternalOfferwallListener;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.ironsource.mediationsdk.sdk.OfferwallAdapterApi;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.mediationsdk.utils.SessionDepthManager;
import com.ironsource.sdk.SSAFactory;
import com.ironsource.sdk.SSAPublisher;
import com.ironsource.sdk.constants.Constants.JSMethods;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.ironsource.sdk.data.AdUnitsReady;
import com.ironsource.sdk.listeners.OnInterstitialListener;
import com.ironsource.sdk.listeners.OnOfferWallListener;
import com.ironsource.sdk.listeners.OnRewardedVideoListener;
import com.ironsource.sdk.utils.SDKUtils;
import com.kongregate.android.internal.browser.C0462b;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class SupersonicAdsAdapter extends AbstractAdapter implements OfferwallAdapterApi, OnOfferWallListener, OnInterstitialListener, OnRewardedVideoListener {
    private static final String VERSION = "6.8.0";
    private final String AD_VISIBLE_EVENT_NAME = "impressions";
    private final String APPLICATION_PRIVATE_KEY = "privateKey";
    private final String APPLICATION_USER_AGE_GROUP = "applicationUserAgeGroup";
    private final String APPLICATION_USER_GENDER = "applicationUserGender";
    private final String CAMPAIGN_ID = "campaignId";
    private final String CLIENT_SIDE_CALLBACKS = ParametersKeys.USE_CLIENT_SIDE_CALLBACKS;
    private final String CUSTOM_PARAM_PREFIX = "custom_";
    private final String CUSTOM_SEGMENT = "custom_Segment";
    private final String DYNAMIC_CONTROLLER_CONFIG = RequestParameters.CONTROLLER_CONFIG;
    private final String DYNAMIC_CONTROLLER_DEBUG_MODE = "debugMode";
    private final String DYNAMIC_CONTROLLER_URL = "controllerUrl";
    private final String ITEM_COUNT = "itemCount";
    private final String ITEM_NAME = "itemName";
    private final String ITEM_SIGNATURE = "itemSignature";
    private final String LANGUAGE = "language";
    private final String MAX_VIDEO_LENGTH = "maxVideoLength";
    private final String OW_PLACEMENT_ID = "placementId";
    private final String SDK_PLUGIN_TYPE = "SDKPluginType";
    private final String TIMESTAMP = "timestamp";
    private boolean mConsent;
    private boolean mDidSetConsent;
    private boolean mDidSetInitParams = false;
    private boolean mIsRVAvailable = false;
    private String mMediationSegment;
    private InternalOfferwallListener mOfferwallListener;
    private SSAPublisher mSSAPublisher;
    private String mUserAgeGroup;
    private String mUserGender;

    public static SupersonicAdsAdapter startAdapter(String providerName) {
        return new SupersonicAdsAdapter(providerName);
    }

    private SupersonicAdsAdapter(String providerName) {
        super(providerName);
    }

    public static IntegrationData getIntegrationData(Activity activity) {
        IntegrationData ret = new IntegrationData(IronSourceConstants.IRONSOURCE_CONFIG_NAME, "6.8.0");
        ret.activities = new String[]{"com.ironsource.sdk.controller.ControllerActivity", "com.ironsource.sdk.controller.InterstitialActivity", "com.ironsource.sdk.controller.OpenUrlActivity"};
        return ret;
    }

    public String getVersion() {
        return "6.8.0";
    }

    public String getCoreSDKVersion() {
        return SDKUtils.getSDKVersion();
    }

    public void onPause(Activity activity) {
        if (this.mSSAPublisher != null) {
            this.mSSAPublisher.onPause(activity);
        }
    }

    public void onResume(Activity activity) {
        if (this.mSSAPublisher != null) {
            this.mSSAPublisher.onResume(activity);
        }
    }

    public void setAge(int age) {
        if (age >= 13 && age <= 17) {
            this.mUserAgeGroup = "1";
        } else if (age >= 18 && age <= 20) {
            this.mUserAgeGroup = "2";
        } else if (age >= 21 && age <= 24) {
            this.mUserAgeGroup = "3";
        } else if (age >= 25 && age <= 34) {
            this.mUserAgeGroup = "4";
        } else if (age >= 35 && age <= 44) {
            this.mUserAgeGroup = "5";
        } else if (age >= 45 && age <= 54) {
            this.mUserAgeGroup = "6";
        } else if (age >= 55 && age <= 64) {
            this.mUserAgeGroup = "7";
        } else if (age <= 65 || age > C0462b.f362a) {
            this.mUserAgeGroup = AppEventsConstants.EVENT_PARAM_VALUE_NO;
        } else {
            this.mUserAgeGroup = "8";
        }
    }

    public void setGender(String gender) {
        this.mUserGender = gender;
    }

    public void setMediationSegment(String segment) {
        this.mMediationSegment = segment;
    }

    public void initRewardedVideo(Activity activity, String appKey, String userId, JSONObject config, RewardedVideoSmashListener listener) {
        if (!this.mDidSetInitParams) {
            setParamsBeforeInit(config);
        }
        final Activity activity2 = activity;
        final JSONObject jSONObject = config;
        final String str = appKey;
        final String str2 = userId;
        activity.runOnUiThread(new Runnable() {
            public void run() {
                try {
                    SupersonicAdsAdapter.this.mSSAPublisher = SSAFactory.getPublisherInstance(activity2);
                    HashMap<String, String> rewardedVideoExtraParams = SupersonicAdsAdapter.this.getRewardedVideoExtraParams(jSONObject);
                    if (SupersonicAdsAdapter.this.mDidSetConsent) {
                        SupersonicAdsAdapter.this.applyConsent(SupersonicAdsAdapter.this.mConsent);
                    }
                    SupersonicAdsAdapter.this.mSSAPublisher.initRewardedVideo(str, str2, SupersonicAdsAdapter.this.getProviderName(), rewardedVideoExtraParams, SupersonicAdsAdapter.this);
                } catch (Exception e) {
                    e.printStackTrace();
                    SupersonicAdsAdapter.this.onRVInitFail(JSMethods.INIT_REWARDED_VIDEO);
                }
            }
        });
    }

    public void fetchRewardedVideo(JSONObject config) {
    }

    public void showRewardedVideo(JSONObject config, RewardedVideoSmashListener listener) {
        this.mActiveRewardedVideoSmash = listener;
        if (this.mSSAPublisher != null) {
            int sessionDepth = SessionDepthManager.getInstance().getSessionDepth(1);
            JSONObject showParams = new JSONObject();
            try {
                showParams.put("demandSourceName", getProviderName());
                showParams.put(RequestParameters.SESSION_DEPTH, sessionDepth);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.mSSAPublisher.showRewardedVideo(showParams);
            return;
        }
        this.mIsRVAvailable = false;
        if (this.mActiveRewardedVideoSmash != null) {
            this.mActiveRewardedVideoSmash.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
        }
        Iterator it = this.mAllRewardedVideoSmashes.iterator();
        while (it.hasNext()) {
            RewardedVideoSmashListener smash = (RewardedVideoSmashListener) it.next();
            if (smash != null) {
                smash.onRewardedVideoAvailabilityChanged(false);
            }
        }
    }

    public synchronized boolean isRewardedVideoAvailable(JSONObject config) {
        return this.mIsRVAvailable;
    }

    public void initInterstitial(final Activity activity, final String appKey, final String userId, JSONObject config, InterstitialSmashListener listener) {
        if (!this.mDidSetInitParams) {
            setParamsBeforeInit(config);
        }
        activity.runOnUiThread(new Runnable() {
            public void run() {
                try {
                    SupersonicAdsAdapter.this.mSSAPublisher = SSAFactory.getPublisherInstance(activity);
                    HashMap<String, String> interstitialExtraParams = SupersonicAdsAdapter.this.getInterstitialExtraParams();
                    if (SupersonicAdsAdapter.this.mDidSetConsent) {
                        SupersonicAdsAdapter.this.applyConsent(SupersonicAdsAdapter.this.mConsent);
                    }
                    SupersonicAdsAdapter.this.mSSAPublisher.initInterstitial(appKey, userId, SupersonicAdsAdapter.this.getProviderName(), interstitialExtraParams, SupersonicAdsAdapter.this);
                } catch (Exception e) {
                    e.printStackTrace();
                    SupersonicAdsAdapter.this.onInterstitialInitFailed(e.getMessage());
                }
            }
        });
    }

    public void loadInterstitial(JSONObject config, InterstitialSmashListener listener) {
        if (this.mSSAPublisher != null) {
            JSONObject loadParams = new JSONObject();
            try {
                loadParams.put("demandSourceName", getProviderName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.mSSAPublisher.loadInterstitial(loadParams);
            return;
        }
        log(IronSourceTag.NATIVE, "Please call initInterstitial before calling loadInterstitial", 2);
        Iterator it = this.mAllInterstitialSmashes.iterator();
        while (it.hasNext()) {
            InterstitialSmashListener smash = (InterstitialSmashListener) it.next();
            if (smash != null) {
                smash.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("Load was called before Init"));
            }
        }
    }

    public void showInterstitial(JSONObject config, InterstitialSmashListener listener) {
        this.mActiveInterstitialSmash = listener;
        if (this.mSSAPublisher != null) {
            int sessionDepth = SessionDepthManager.getInstance().getSessionDepth(2);
            JSONObject showParams = new JSONObject();
            try {
                showParams.put("demandSourceName", getProviderName());
                showParams.put(RequestParameters.SESSION_DEPTH, sessionDepth);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.mSSAPublisher.showInterstitial(showParams);
            return;
        }
        log(IronSourceTag.NATIVE, "Please call loadInterstitial before calling showInterstitial", 2);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
        }
    }

    public boolean isInterstitialReady(JSONObject config) {
        return this.mSSAPublisher != null && this.mSSAPublisher.isInterstitialAdAvailable(getProviderName());
    }

    public void setInternalOfferwallListener(InternalOfferwallListener listener) {
        this.mOfferwallListener = listener;
    }

    public void initOfferwall(Activity activity, String appKey, String userId, JSONObject config) {
        if (!this.mDidSetInitParams) {
            setParamsBeforeInit(config);
        }
        final JSONObject jSONObject = config;
        final Activity activity2 = activity;
        final String str = appKey;
        final String str2 = userId;
        activity.runOnUiThread(new Runnable() {
            public void run() {
                try {
                    Map<String, String> offerwallExtraParams = SupersonicAdsAdapter.this.getOfferwallExtraParams(jSONObject);
                    SupersonicAdsAdapter.this.mSSAPublisher = SSAFactory.getPublisherInstance(activity2);
                    if (SupersonicAdsAdapter.this.mDidSetConsent) {
                        SupersonicAdsAdapter.this.applyConsent(SupersonicAdsAdapter.this.mConsent);
                    }
                    SupersonicAdsAdapter.this.mSSAPublisher.initOfferWall(str, str2, offerwallExtraParams, SupersonicAdsAdapter.this);
                } catch (Exception e) {
                    e.printStackTrace();
                    IronSourceLoggerManager.getLogger().logException(IronSourceTag.ADAPTER_API, SupersonicAdsAdapter.this.getProviderName() + ":initOfferwall(userId:" + str2 + ")", e);
                    SupersonicAdsAdapter.this.mOfferwallListener.onOfferwallAvailable(false, ErrorBuilder.buildInitFailedError("Adapter initialization failure - " + SupersonicAdsAdapter.this.getProviderName() + " - " + e.getMessage(), IronSourceConstants.OFFERWALL_AD_UNIT));
                }
            }
        });
    }

    public void getOfferwallCredits() {
        if (this.mSSAPublisher != null) {
            this.mSSAPublisher.getOfferWallCredits(IronSourceObject.getInstance().getIronSourceAppKey(), IronSourceObject.getInstance().getIronSourceUserId(), this);
            return;
        }
        log(IronSourceTag.NATIVE, "Please call init before calling getOfferwallCredits", 2);
    }

    public void showOfferwall(String placementId, JSONObject config) {
        Map<String, String> offerwallExtraParams = getOfferwallExtraParams(config);
        if (offerwallExtraParams != null) {
            offerwallExtraParams.put("placementId", placementId);
        }
        if (this.mSSAPublisher != null) {
            this.mSSAPublisher.showOfferWall(offerwallExtraParams);
        } else {
            log(IronSourceTag.NATIVE, "Please call init before calling showOfferwall", 2);
        }
    }

    public boolean isOfferwallAvailable() {
        return true;
    }

    private synchronized void setParamsBeforeInit(JSONObject config) {
        this.mDidSetInitParams = true;
        SDKUtils.setControllerUrl(config.optString("controllerUrl"));
        if (isAdaptersDebugEnabled()) {
            SDKUtils.setDebugMode(3);
        } else {
            SDKUtils.setDebugMode(config.optInt("debugMode", 0));
        }
        SDKUtils.setControllerConfig(config.optString(RequestParameters.CONTROLLER_CONFIG, ""));
    }

    private HashMap<String, String> getGenenralExtraParams() {
        HashMap<String, String> params = new HashMap();
        if (!TextUtils.isEmpty(this.mUserAgeGroup)) {
            params.put("applicationUserAgeGroup", this.mUserAgeGroup);
        }
        if (!TextUtils.isEmpty(this.mUserGender)) {
            params.put("applicationUserGender", this.mUserGender);
        }
        String pluginType = getPluginType();
        if (!TextUtils.isEmpty(pluginType)) {
            params.put("SDKPluginType", pluginType);
        }
        return params;
    }

    private HashMap<String, String> getRewardedVideoExtraParams(JSONObject config) {
        HashMap<String, String> rvExtraParams = getGenenralExtraParams();
        String language = config.optString("language");
        if (!TextUtils.isEmpty(language)) {
            rvExtraParams.put("language", language);
        }
        String maxVideoLength = config.optString("maxVideoLength");
        if (!TextUtils.isEmpty(maxVideoLength)) {
            rvExtraParams.put("maxVideoLength", maxVideoLength);
        }
        String campaignId = config.optString("campaignId");
        if (!TextUtils.isEmpty(campaignId)) {
            rvExtraParams.put("campaignId", campaignId);
        }
        if (!TextUtils.isEmpty(this.mMediationSegment)) {
            rvExtraParams.put("custom_Segment", this.mMediationSegment);
        }
        addItemNameCountSignature(rvExtraParams, config);
        Map<String, String> customParams = SupersonicConfig.getConfigObj().getRewardedVideoCustomParams();
        if (!(customParams == null || customParams.isEmpty())) {
            rvExtraParams.putAll(customParams);
        }
        return rvExtraParams;
    }

    private HashMap<String, String> getInterstitialExtraParams() {
        return getGenenralExtraParams();
    }

    private HashMap<String, String> getOfferwallExtraParams(JSONObject config) {
        HashMap<String, String> owExtraParams = getGenenralExtraParams();
        String language = config.optString("language");
        if (!TextUtils.isEmpty(language)) {
            owExtraParams.put("language", language);
        }
        owExtraParams.put(ParametersKeys.USE_CLIENT_SIDE_CALLBACKS, String.valueOf(SupersonicConfig.getConfigObj().getClientSideCallbacks()));
        Map<String, String> customParams = SupersonicConfig.getConfigObj().getOfferwallCustomParams();
        if (!(customParams == null || customParams.isEmpty())) {
            owExtraParams.putAll(customParams);
        }
        addItemNameCountSignature(owExtraParams, config);
        return owExtraParams;
    }

    private void addItemNameCountSignature(HashMap<String, String> params, JSONObject config) {
        try {
            String itemName = config.optString("itemName");
            int itemCount = config.optInt("itemCount", -1);
            String privateKey = config.optString("privateKey");
            boolean shouldAddSignature = true;
            if (TextUtils.isEmpty(itemName)) {
                shouldAddSignature = false;
            } else {
                params.put("itemName", itemName);
            }
            if (TextUtils.isEmpty(privateKey)) {
                shouldAddSignature = false;
            }
            if (itemCount == -1) {
                shouldAddSignature = false;
            } else {
                params.put("itemCount", String.valueOf(itemCount));
            }
            if (shouldAddSignature) {
                int timestamp = IronSourceUtils.getCurrentTimestamp();
                params.put("timestamp", String.valueOf(timestamp));
                params.put("itemSignature", createItemSig(timestamp, itemName, itemCount, privateKey));
            }
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceTag.ADAPTER_API, " addItemNameCountSignature", e);
        }
    }

    private String createItemSig(int timestamp, String itemName, int itemCount, String privateKey) {
        return IronSourceUtils.getMD5(timestamp + itemName + itemCount + privateKey);
    }

    private String createMinimumOfferCommissionSig(double min, String privateKey) {
        return IronSourceUtils.getMD5(min + privateKey);
    }

    private String createUserCreationDateSig(String userid, String uCreationDate, String privateKey) {
        return IronSourceUtils.getMD5(userid + uCreationDate + privateKey);
    }

    public void onRVNoMoreOffers() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onRVNoMoreOffers ", 1);
        this.mIsRVAvailable = false;
        Iterator it = this.mAllRewardedVideoSmashes.iterator();
        while (it.hasNext()) {
            RewardedVideoSmashListener smash = (RewardedVideoSmashListener) it.next();
            if (smash != null) {
                smash.onRewardedVideoAvailabilityChanged(false);
            }
        }
    }

    public void onRVInitSuccess(AdUnitsReady aur) {
        boolean availability = true;
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onRVInitSuccess ", 1);
        int numOfAdUnits = 0;
        try {
            numOfAdUnits = Integer.parseInt(aur.getNumOfAdUnits());
        } catch (NumberFormatException e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, "onRVInitSuccess:parseInt()", e);
        }
        if (numOfAdUnits <= 0) {
            availability = false;
        }
        this.mIsRVAvailable = availability;
        Iterator it = this.mAllRewardedVideoSmashes.iterator();
        while (it.hasNext()) {
            RewardedVideoSmashListener smash = (RewardedVideoSmashListener) it.next();
            if (smash != null) {
                smash.onRewardedVideoAvailabilityChanged(availability);
            }
        }
    }

    public void onRVInitFail(String error) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onRVInitFail ", 1);
        Iterator it = this.mAllRewardedVideoSmashes.iterator();
        while (it.hasNext()) {
            RewardedVideoSmashListener smash = (RewardedVideoSmashListener) it.next();
            if (smash != null) {
                smash.onRewardedVideoAvailabilityChanged(false);
            }
        }
    }

    public void onRVAdClicked() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onRVAdClicked ", 1);
        if (this.mActiveRewardedVideoSmash != null) {
            this.mActiveRewardedVideoSmash.onRewardedVideoAdClicked();
        }
    }

    public void onRVEventNotificationReceived(String eventName, JSONObject extData) {
        if (extData != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onRVEventNotificationReceived: " + eventName + " extData: " + extData.toString(), 1);
        }
        if (!TextUtils.isEmpty(eventName) && "impressions".equals(eventName) && this.mActiveRewardedVideoSmash != null) {
            this.mActiveRewardedVideoSmash.onRewardedVideoAdVisible();
        }
    }

    public void onRVShowFail(String error) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onRVShowFail ", 1);
        if (this.mActiveRewardedVideoSmash != null) {
            this.mActiveRewardedVideoSmash.onRewardedVideoAdShowFailed(new IronSourceError(IronSourceError.ERROR_CODE_NO_ADS_TO_SHOW, error));
        }
    }

    public void onRVAdCredited(int amount) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onRVAdCredited ", 1);
        if (this.mActiveRewardedVideoSmash != null) {
            this.mActiveRewardedVideoSmash.onRewardedVideoAdRewarded();
        }
    }

    public void onRVAdClosed() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onRVAdClosed ", 1);
        if (this.mActiveRewardedVideoSmash != null) {
            this.mActiveRewardedVideoSmash.onRewardedVideoAdClosed();
        }
    }

    public void onRVAdOpened() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onRVAdOpened ", 1);
        if (this.mActiveRewardedVideoSmash != null) {
            this.mActiveRewardedVideoSmash.onRewardedVideoAdOpened();
        }
    }

    public void onInterstitialInitSuccess() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onInterstitialInitSuccess ", 1);
        Iterator it = this.mAllInterstitialSmashes.iterator();
        while (it.hasNext()) {
            InterstitialSmashListener smash = (InterstitialSmashListener) it.next();
            if (smash != null) {
                smash.onInterstitialInitSuccess();
            }
        }
    }

    public void onInterstitialInitFailed(String description) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onInterstitialInitFailed ", 1);
        Iterator it = this.mAllInterstitialSmashes.iterator();
        while (it.hasNext()) {
            InterstitialSmashListener smash = (InterstitialSmashListener) it.next();
            if (smash != null) {
                smash.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError(description, "Interstitial"));
            }
        }
    }

    public void onInterstitialLoadSuccess() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onInterstitialLoadSuccess ", 1);
        Iterator it = this.mAllInterstitialSmashes.iterator();
        while (it.hasNext()) {
            InterstitialSmashListener smash = (InterstitialSmashListener) it.next();
            if (smash != null) {
                smash.onInterstitialAdReady();
            }
        }
    }

    public void onInterstitialLoadFailed(String description) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onInterstitialAdLoadFailed ", 1);
        Iterator it = this.mAllInterstitialSmashes.iterator();
        while (it.hasNext()) {
            InterstitialSmashListener smash = (InterstitialSmashListener) it.next();
            if (smash != null) {
                smash.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError(description));
            }
        }
    }

    public void onInterstitialOpen() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onInterstitialAdOpened ", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdOpened();
        }
    }

    public void onInterstitialClose() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onInterstitialAdClosed ", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdClosed();
        }
    }

    public void onInterstitialShowSuccess() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onInterstitialAdShowSucceeded ", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdShowSucceeded();
        }
    }

    public void onInterstitialShowFailed(String description) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onInterstitialAdShowFailed ", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdShowFailed(ErrorBuilder.buildShowFailedError("Interstitial", description));
        }
    }

    public void onInterstitialClick() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onInterstitialAdClicked ", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdClicked();
        }
    }

    public void onInterstitialEventNotificationReceived(String eventName, JSONObject extData) {
        if (extData != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onInterstitialEventNotificationReceived: " + eventName + " extData: " + extData.toString(), 1);
            if (!TextUtils.isEmpty(eventName) && "impressions".equals(eventName) && this.mActiveInterstitialSmash != null) {
                this.mActiveInterstitialSmash.onInterstitialAdVisible();
            }
        }
    }

    public void onOfferwallInitSuccess() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onOfferwallInitSuccess ", 1);
        if (this.mOfferwallListener != null) {
            this.mOfferwallListener.onOfferwallAvailable(true);
        }
    }

    public void onOfferwallInitFail(String description) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onOfferwallInitFail ", 1);
        if (this.mOfferwallListener != null) {
            this.mOfferwallListener.onOfferwallAvailable(false, ErrorBuilder.buildGenericError(description));
        }
    }

    public void onOfferwallEventNotificationReceived(String eventName, JSONObject extData) {
        if (extData != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onOfferwallEventNotificationReceived ", 1);
        }
    }

    public void onOWShowSuccess(String placementId) {
        if (TextUtils.isEmpty(placementId)) {
            log(IronSourceTag.ADAPTER_API, getProviderName() + ":onOWShowSuccess()", 1);
        } else {
            log(IronSourceTag.ADAPTER_API, getProviderName() + ":onOWShowSuccess(placementId:" + placementId + ")", 1);
        }
        if (this.mOfferwallListener != null) {
            this.mOfferwallListener.onOfferwallOpened();
        }
    }

    public void onOWShowFail(String desc) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onOWShowFail ", 1);
        if (this.mOfferwallListener != null) {
            this.mOfferwallListener.onOfferwallShowFailed(ErrorBuilder.buildGenericError(desc));
        }
    }

    public void onOWGeneric(String arg0, String arg1) {
    }

    public boolean onOWAdCredited(int credits, int totalCredits, boolean totalCreditsFlag) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onOWAdCredited ", 1);
        if (this.mOfferwallListener == null || !this.mOfferwallListener.onOfferwallAdCredited(credits, totalCredits, totalCreditsFlag)) {
            return false;
        }
        return true;
    }

    public void onOWAdClosed() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onOWAdClosed ", 1);
        if (this.mOfferwallListener != null) {
            this.mOfferwallListener.onOfferwallClosed();
        }
    }

    public void onGetOWCreditsFailed(String desc) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onGetOWCreditsFailed ", 1);
        if (this.mOfferwallListener != null) {
            this.mOfferwallListener.onGetOfferwallCreditsFailed(ErrorBuilder.buildGenericError(desc));
        }
    }

    protected void setMediationState(MEDIATION_STATE state, String adUnit) {
        if (this.mSSAPublisher != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :setMediationState(" + adUnit + " , " + getProviderName() + " , " + state.getValue() + ")", 1);
            this.mSSAPublisher.setMediationState(adUnit, getProviderName(), state.getValue());
        }
    }

    protected void setConsent(boolean consent) {
        this.mDidSetConsent = true;
        this.mConsent = consent;
        applyConsent(consent);
    }

    private void applyConsent(boolean consent) {
        if (this.mSSAPublisher != null) {
            JSONObject consentParams = new JSONObject();
            try {
                consentParams.put(RequestParameters.GDPR_CONSENT_STATUS, String.valueOf(consent));
                consentParams.put("demandSourceName", getProviderName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.mSSAPublisher.updateConsentInfo(consentParams);
        }
    }
}
