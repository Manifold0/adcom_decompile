package com.ironsource.adapters.unityads;

import android.app.Activity;
import android.text.TextUtils;
import com.ironsource.mediationsdk.AbstractAdapter;
import com.ironsource.mediationsdk.IntegrationData;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAds.FinishState;
import com.unity3d.ads.UnityAds.PlacementState;
import com.unity3d.ads.UnityAds.UnityAdsError;
import com.unity3d.ads.mediation.IUnityAdsExtendedListener;
import com.unity3d.ads.metadata.MediationMetaData;
import com.unity3d.ads.metadata.MetaData;
import com.unity3d.ads.metadata.PlayerMetaData;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;

class UnityAdsAdapter extends AbstractAdapter implements IUnityAdsListener, IUnityAdsExtendedListener {
    private static final String GitHash = "d62fa2d34";
    private static final String VERSION = "4.1.3";
    private final String CONSENT_GDPR = "gdpr.consent";
    private final String GAME_ID = "sourceId";
    private final String PLACEMENT_ID = "zoneId";
    private Activity mActivity;
    private Boolean mConsentCollectingUserData = null;
    private boolean mDidInit = false;
    private CopyOnWriteArraySet<String> mISZoneReceivedFirstStatus = new CopyOnWriteArraySet();
    private ConcurrentHashMap<String, InterstitialSmashListener> mZoneIdToIsListener = new ConcurrentHashMap();
    private ConcurrentHashMap<String, RewardedVideoSmashListener> mZoneIdToRvListener = new ConcurrentHashMap();

    public static UnityAdsAdapter startAdapter(String providerName) {
        return new UnityAdsAdapter(providerName);
    }

    private UnityAdsAdapter(String providerName) {
        super(providerName);
    }

    public static IntegrationData getIntegrationData(Activity activity) {
        IntegrationData ret = new IntegrationData("UnityAds", VERSION);
        ret.activities = new String[]{"com.unity3d.services.ads.adunit.AdUnitActivity", "com.unity3d.services.ads.adunit.AdUnitTransparentActivity", "com.unity3d.services.ads.adunit.AdUnitTransparentSoftwareActivity", "com.unity3d.services.ads.adunit.AdUnitSoftwareActivity"};
        return ret;
    }

    public static String getAdapterSDKVersion() {
        String sdkVersion = null;
        try {
            sdkVersion = UnityAds.getVersion();
        } catch (Exception e) {
        }
        return sdkVersion;
    }

    public String getVersion() {
        return VERSION;
    }

    public String getCoreSDKVersion() {
        return UnityAds.getVersion();
    }

    public void onResume(Activity activity) {
        this.mActivity = activity;
    }

    protected synchronized void setConsent(boolean consent) {
        if (this.mDidInit) {
            MetaData gdprMetaData = new MetaData(this.mActivity);
            gdprMetaData.set("gdpr.consent", Boolean.valueOf(consent));
            gdprMetaData.commit();
        } else {
            this.mConsentCollectingUserData = Boolean.valueOf(consent);
        }
    }

    private synchronized void initSDK(Activity activity, String game_id) {
        if (!this.mDidInit) {
            this.mActivity = activity;
            MediationMetaData mediationMetaData = new MediationMetaData(activity);
            mediationMetaData.setName("IronSource");
            mediationMetaData.setVersion(VERSION);
            mediationMetaData.commit();
            UnityAds.initialize(activity, game_id, this);
            boolean isDebugEnabled = false;
            try {
                isDebugEnabled = isAdaptersDebugEnabled();
            } catch (NoSuchMethodError e) {
            }
            UnityAds.setDebugMode(isDebugEnabled);
            if (this.mConsentCollectingUserData != null) {
                setConsent(this.mConsentCollectingUserData.booleanValue());
            }
            this.mDidInit = true;
        }
    }

    public synchronized void initRewardedVideo(Activity activity, String appKey, String userId, JSONObject config, RewardedVideoSmashListener listener) {
        String gameId = config.optString("sourceId");
        String placementId = config.optString("zoneId");
        IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, getProviderName() + " initRewardedVideo gameId: <" + gameId + "> placementId: <" + placementId + ">", 1);
        if (listener == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " initRewardedVideo: null listener", 3);
        } else if (TextUtils.isEmpty(gameId)) {
            listener.onRewardedVideoAvailabilityChanged(false);
        } else {
            if (!TextUtils.isEmpty(placementId)) {
                this.mZoneIdToRvListener.put(placementId, listener);
            }
            if (this.mISZoneReceivedFirstStatus.contains(placementId)) {
                listener.onRewardedVideoAvailabilityChanged(UnityAds.isReady(placementId));
            }
            initSDK(activity, gameId);
        }
    }

    public void fetchRewardedVideo(JSONObject config) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, getProviderName() + " fetchRewardedVideo placementId: <" + config.optString("zoneId") + ">", 1);
    }

    public synchronized void showRewardedVideo(JSONObject config, RewardedVideoSmashListener listener) {
        String placementId = config.optString("zoneId");
        IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, getProviderName() + " showRewardedVideo placementId: <" + placementId + ">", 1);
        if (UnityAds.isReady(placementId)) {
            if (!TextUtils.isEmpty(getDynamicUserId())) {
                PlayerMetaData playerMetaData = new PlayerMetaData(this.mActivity);
                playerMetaData.setServerId(getDynamicUserId());
                playerMetaData.commit();
            }
            UnityAds.show(this.mActivity, placementId);
        } else if (listener != null) {
            listener.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
            listener.onRewardedVideoAvailabilityChanged(false);
        }
    }

    public boolean isRewardedVideoAvailable(JSONObject config) {
        return UnityAds.isReady(config.optString("zoneId"));
    }

    public synchronized void initInterstitial(Activity activity, String appKey, String userId, JSONObject config, InterstitialSmashListener listener) {
        String gameId = config.optString("sourceId");
        if (!TextUtils.isEmpty(gameId)) {
            String placementId = config.optString("zoneId");
            IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, getProviderName() + " initInterstitial gameId: <" + gameId + "> placementId: <" + placementId + ">", 1);
            if (!(TextUtils.isEmpty(placementId) || listener == null)) {
                this.mZoneIdToIsListener.put(placementId, listener);
            }
            initSDK(activity, config.optString("sourceId"));
            if (listener != null) {
                listener.onInterstitialInitSuccess();
            }
        } else if (listener != null) {
            listener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Missing params", "Interstitial"));
        }
    }

    public void loadInterstitial(JSONObject config, InterstitialSmashListener listener) {
        String placementId = config.optString("zoneId");
        IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, getProviderName() + " loadInterstitial placementId <" + placementId + ">", 1);
        if (listener == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, getProviderName() + "null listener for placement Id <" + placementId + ">", 3);
        } else if (!this.mISZoneReceivedFirstStatus.contains(placementId)) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, getProviderName() + "loadInterstitial, waiting for instance init to complete: placementId: <" + placementId + ">", 1);
        } else if (UnityAds.isReady(placementId)) {
            listener.onInterstitialAdReady();
        } else {
            listener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("Ad unavailable"));
        }
    }

    public void showInterstitial(JSONObject config, InterstitialSmashListener listener) {
        String placementId = config.optString("zoneId");
        IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, getProviderName() + " showInterstitial placementId <" + placementId + ">", 1);
        if (UnityAds.isReady(placementId)) {
            UnityAds.show(this.mActivity, placementId);
        } else if (listener != null) {
            listener.onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
        }
    }

    public boolean isInterstitialReady(JSONObject config) {
        String placementId = config.optString("zoneId");
        IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, getProviderName() + " isInterstitialReady placementId <" + placementId + ">", 1);
        return UnityAds.isReady(placementId);
    }

    public void onUnityAdsReady(String placementId) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, getProviderName() + " onUnityAdsReady placementId <" + placementId + ">", 1);
    }

    public void onUnityAdsStart(String placementId) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, getProviderName() + " onUnityAdsStart: placementId <" + placementId + ">", 1);
        if (!TextUtils.isEmpty(placementId)) {
            RewardedVideoSmashListener RVlistener = (RewardedVideoSmashListener) this.mZoneIdToRvListener.get(placementId);
            if (RVlistener != null) {
                RVlistener.onRewardedVideoAdOpened();
                RVlistener.onRewardedVideoAdStarted();
                return;
            }
            InterstitialSmashListener ISListener = (InterstitialSmashListener) this.mZoneIdToIsListener.get(placementId);
            if (ISListener != null) {
                ISListener.onInterstitialAdOpened();
                ISListener.onInterstitialAdShowSucceeded();
            }
        }
    }

    public void onUnityAdsFinish(String placementId, FinishState finishState) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, getProviderName() + " onUnityAdsFinish placementId: <" + placementId + "> finishState: " + finishState + ")", 1);
        if (!TextUtils.isEmpty(placementId)) {
            RewardedVideoSmashListener RVlistener = (RewardedVideoSmashListener) this.mZoneIdToRvListener.get(placementId);
            if (RVlistener != null) {
                RVlistener.onRewardedVideoAvailabilityChanged(UnityAds.isReady(placementId));
                if (finishState.equals(FinishState.COMPLETED)) {
                    RVlistener.onRewardedVideoAdEnded();
                    RVlistener.onRewardedVideoAdRewarded();
                }
                RVlistener.onRewardedVideoAdClosed();
                return;
            }
            InterstitialSmashListener ISListener = (InterstitialSmashListener) this.mZoneIdToIsListener.get(placementId);
            if (ISListener != null) {
                ISListener.onInterstitialAdClosed();
            }
        }
    }

    public void onUnityAdsError(UnityAdsError unityAdsError, String errorMessage) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " onUnityAdsError(errorType: " + unityAdsError + ", errorMessage: " + errorMessage + ")", 1);
    }

    public void onUnityAdsClick(String placementId) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, getProviderName() + " onUnityAdsClick placementId: <" + placementId + ">", 1);
        if (!TextUtils.isEmpty(placementId)) {
            RewardedVideoSmashListener RVlistener = (RewardedVideoSmashListener) this.mZoneIdToRvListener.get(placementId);
            if (RVlistener != null) {
                RVlistener.onRewardedVideoAdClicked();
                return;
            }
            InterstitialSmashListener ISListener = (InterstitialSmashListener) this.mZoneIdToIsListener.get(placementId);
            if (ISListener != null) {
                ISListener.onInterstitialAdClicked();
            }
        }
    }

    public void onUnityAdsPlacementStateChanged(String placementId, PlacementState oldState, PlacementState newState) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, getProviderName() + " onUnityAdsPlacementStateChanged placementId: <" + placementId + "> from " + oldState + " to " + newState, 1);
        if (!newState.equals(oldState) && !newState.equals(PlacementState.WAITING)) {
            this.mISZoneReceivedFirstStatus.add(placementId);
            if (!TextUtils.isEmpty(placementId)) {
                RewardedVideoSmashListener RVlistener = (RewardedVideoSmashListener) this.mZoneIdToRvListener.get(placementId);
                if (RVlistener != null) {
                    RVlistener.onRewardedVideoAvailabilityChanged(newState.equals(PlacementState.READY));
                    return;
                }
                InterstitialSmashListener ISListener = (InterstitialSmashListener) this.mZoneIdToIsListener.get(placementId);
                if (ISListener == null) {
                    return;
                }
                if (newState.equals(PlacementState.READY)) {
                    ISListener.onInterstitialAdReady();
                } else {
                    ISListener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError(placementId + " placement state: " + newState.toString()));
                }
            }
        }
    }
}
