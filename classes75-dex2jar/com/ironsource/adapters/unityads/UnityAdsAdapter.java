// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.adapters.unityads;

import com.unity3d.ads.metadata.PlayerMetaData;
import com.unity3d.ads.metadata.MetaData;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import android.text.TextUtils;
import com.ironsource.mediationsdk.logger.IronSourceLogger$IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import org.json.JSONObject;
import android.content.Context;
import com.unity3d.ads.metadata.MediationMetaData;
import com.ironsource.mediationsdk.IntegrationData;
import com.unity3d.ads.UnityAds;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import android.app.Activity;
import com.unity3d.ads.mediation.IUnityAdsExtendedListener;
import com.unity3d.ads.IUnityAdsListener;
import com.ironsource.mediationsdk.AbstractAdapter;

class UnityAdsAdapter extends AbstractAdapter implements IUnityAdsListener, IUnityAdsExtendedListener
{
    private static final String GitHash = "d62fa2d34";
    private static final String VERSION = "4.1.3";
    private final String CONSENT_GDPR;
    private final String GAME_ID;
    private final String PLACEMENT_ID;
    private Activity mActivity;
    private Boolean mConsentCollectingUserData;
    private boolean mDidInit;
    private CopyOnWriteArraySet<String> mISZoneReceivedFirstStatus;
    private ConcurrentHashMap<String, InterstitialSmashListener> mZoneIdToIsListener;
    private ConcurrentHashMap<String, RewardedVideoSmashListener> mZoneIdToRvListener;
    
    private UnityAdsAdapter(final String s) {
        super(s);
        this.mDidInit = false;
        this.GAME_ID = "sourceId";
        this.PLACEMENT_ID = "zoneId";
        this.CONSENT_GDPR = "gdpr.consent";
        this.mConsentCollectingUserData = null;
        this.mZoneIdToIsListener = new ConcurrentHashMap<String, InterstitialSmashListener>();
        this.mZoneIdToRvListener = new ConcurrentHashMap<String, RewardedVideoSmashListener>();
        this.mISZoneReceivedFirstStatus = new CopyOnWriteArraySet<String>();
    }
    
    public static String getAdapterSDKVersion() {
        try {
            return UnityAds.getVersion();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static IntegrationData getIntegrationData(final Activity activity) {
        final IntegrationData integrationData = new IntegrationData("UnityAds", "4.1.3");
        integrationData.activities = new String[] { "com.unity3d.services.ads.adunit.AdUnitActivity", "com.unity3d.services.ads.adunit.AdUnitTransparentActivity", "com.unity3d.services.ads.adunit.AdUnitTransparentSoftwareActivity", "com.unity3d.services.ads.adunit.AdUnitSoftwareActivity" };
        return integrationData;
    }
    
    private void initSDK(final Activity mActivity, final String s) {
        synchronized (this) {
            if (this.mDidInit) {
                return;
            }
            this.mActivity = mActivity;
            final MediationMetaData mediationMetaData = new MediationMetaData((Context)mActivity);
            mediationMetaData.setName("IronSource");
            mediationMetaData.setVersion("4.1.3");
            mediationMetaData.commit();
            UnityAds.initialize(mActivity, s, this);
            boolean adaptersDebugEnabled = false;
            try {
                adaptersDebugEnabled = this.isAdaptersDebugEnabled();
                UnityAds.setDebugMode(adaptersDebugEnabled);
                if (this.mConsentCollectingUserData != null) {
                    this.setConsent(this.mConsentCollectingUserData);
                }
                this.mDidInit = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {}
        }
    }
    
    public static UnityAdsAdapter startAdapter(final String s) {
        return new UnityAdsAdapter(s);
    }
    
    public void fetchRewardedVideo(final JSONObject jsonObject) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + " fetchRewardedVideo placementId: <" + jsonObject.optString("zoneId") + ">", 1);
    }
    
    public String getCoreSDKVersion() {
        return UnityAds.getVersion();
    }
    
    public String getVersion() {
        return "4.1.3";
    }
    
    public void initInterstitial(final Activity activity, String optString, String optString2, final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        synchronized (this) {
            optString = jsonObject.optString("sourceId");
            if (TextUtils.isEmpty((CharSequence)optString)) {
                if (interstitialSmashListener != null) {
                    interstitialSmashListener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Missing params", "Interstitial"));
                }
            }
            else {
                optString2 = jsonObject.optString("zoneId");
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + " initInterstitial gameId: <" + optString + "> placementId: <" + optString2 + ">", 1);
                if (!TextUtils.isEmpty((CharSequence)optString2) && interstitialSmashListener != null) {
                    this.mZoneIdToIsListener.put(optString2, interstitialSmashListener);
                }
                this.initSDK(activity, jsonObject.optString("sourceId"));
                if (interstitialSmashListener != null) {
                    interstitialSmashListener.onInterstitialInitSuccess();
                }
            }
        }
    }
    
    public void initRewardedVideo(final Activity activity, String optString, String optString2, final JSONObject jsonObject, final RewardedVideoSmashListener rewardedVideoSmashListener) {
        while (true) {
            Label_0131: {
                synchronized (this) {
                    optString = jsonObject.optString("sourceId");
                    optString2 = jsonObject.optString("zoneId");
                    IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + " initRewardedVideo gameId: <" + optString + "> placementId: <" + optString2 + ">", 1);
                    if (rewardedVideoSmashListener == null) {
                        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, this.getProviderName() + " initRewardedVideo: null listener", 3);
                    }
                    else {
                        if (!TextUtils.isEmpty((CharSequence)optString)) {
                            break Label_0131;
                        }
                        rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
                    }
                    return;
                }
            }
            if (!TextUtils.isEmpty((CharSequence)optString2)) {
                this.mZoneIdToRvListener.put(optString2, rewardedVideoSmashListener);
            }
            if (this.mISZoneReceivedFirstStatus.contains(optString2)) {
                rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(UnityAds.isReady(optString2));
            }
            final Activity activity2;
            this.initSDK(activity2, optString);
        }
    }
    
    public boolean isInterstitialReady(final JSONObject jsonObject) {
        final String optString = jsonObject.optString("zoneId");
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + " isInterstitialReady placementId <" + optString + ">", 1);
        return UnityAds.isReady(optString);
    }
    
    public boolean isRewardedVideoAvailable(final JSONObject jsonObject) {
        return UnityAds.isReady(jsonObject.optString("zoneId"));
    }
    
    public void loadInterstitial(final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        final String optString = jsonObject.optString("zoneId");
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + " loadInterstitial placementId <" + optString + ">", 1);
        if (interstitialSmashListener == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + "null listener for placement Id <" + optString + ">", 3);
            return;
        }
        if (!this.mISZoneReceivedFirstStatus.contains(optString)) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + "loadInterstitial, waiting for instance init to complete: placementId: <" + optString + ">", 1);
            return;
        }
        if (UnityAds.isReady(optString)) {
            interstitialSmashListener.onInterstitialAdReady();
            return;
        }
        interstitialSmashListener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("Ad unavailable"));
    }
    
    public void onResume(final Activity mActivity) {
        this.mActivity = mActivity;
    }
    
    public void onUnityAdsClick(final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + " onUnityAdsClick placementId: <" + s + ">", 1);
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final RewardedVideoSmashListener rewardedVideoSmashListener = this.mZoneIdToRvListener.get(s);
            if (rewardedVideoSmashListener != null) {
                rewardedVideoSmashListener.onRewardedVideoAdClicked();
            }
            else {
                final InterstitialSmashListener interstitialSmashListener = this.mZoneIdToIsListener.get(s);
                if (interstitialSmashListener != null) {
                    interstitialSmashListener.onInterstitialAdClicked();
                }
            }
        }
    }
    
    public void onUnityAdsError(final UnityAds.UnityAdsError unityAdsError, final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, this.getProviderName() + " onUnityAdsError(errorType: " + unityAdsError + ", errorMessage: " + s + ")", 1);
    }
    
    public void onUnityAdsFinish(final String s, final UnityAds.FinishState finishState) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + " onUnityAdsFinish placementId: <" + s + "> finishState: " + finishState + ")", 1);
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final RewardedVideoSmashListener rewardedVideoSmashListener = this.mZoneIdToRvListener.get(s);
            if (rewardedVideoSmashListener != null) {
                rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(UnityAds.isReady(s));
                if (finishState.equals(UnityAds.FinishState.COMPLETED)) {
                    rewardedVideoSmashListener.onRewardedVideoAdEnded();
                    rewardedVideoSmashListener.onRewardedVideoAdRewarded();
                }
                rewardedVideoSmashListener.onRewardedVideoAdClosed();
            }
            else {
                final InterstitialSmashListener interstitialSmashListener = this.mZoneIdToIsListener.get(s);
                if (interstitialSmashListener != null) {
                    interstitialSmashListener.onInterstitialAdClosed();
                }
            }
        }
    }
    
    public void onUnityAdsPlacementStateChanged(final String s, final UnityAds.PlacementState placementState, final UnityAds.PlacementState placementState2) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + " onUnityAdsPlacementStateChanged placementId: <" + s + "> from " + placementState + " to " + placementState2, 1);
        if (!placementState2.equals(placementState) && !placementState2.equals(UnityAds.PlacementState.WAITING)) {
            this.mISZoneReceivedFirstStatus.add(s);
            if (!TextUtils.isEmpty((CharSequence)s)) {
                final RewardedVideoSmashListener rewardedVideoSmashListener = this.mZoneIdToRvListener.get(s);
                if (rewardedVideoSmashListener != null) {
                    rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(placementState2.equals(UnityAds.PlacementState.READY));
                    return;
                }
                final InterstitialSmashListener interstitialSmashListener = this.mZoneIdToIsListener.get(s);
                if (interstitialSmashListener != null) {
                    if (placementState2.equals(UnityAds.PlacementState.READY)) {
                        interstitialSmashListener.onInterstitialAdReady();
                        return;
                    }
                    interstitialSmashListener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError(s + " placement state: " + placementState2.toString()));
                }
            }
        }
    }
    
    public void onUnityAdsReady(final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + " onUnityAdsReady placementId <" + s + ">", 1);
    }
    
    public void onUnityAdsStart(final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + " onUnityAdsStart: placementId <" + s + ">", 1);
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final RewardedVideoSmashListener rewardedVideoSmashListener = this.mZoneIdToRvListener.get(s);
            if (rewardedVideoSmashListener != null) {
                rewardedVideoSmashListener.onRewardedVideoAdOpened();
                rewardedVideoSmashListener.onRewardedVideoAdStarted();
            }
            else {
                final InterstitialSmashListener interstitialSmashListener = this.mZoneIdToIsListener.get(s);
                if (interstitialSmashListener != null) {
                    interstitialSmashListener.onInterstitialAdOpened();
                    interstitialSmashListener.onInterstitialAdShowSucceeded();
                }
            }
        }
    }
    
    protected void setConsent(final boolean b) {
        synchronized (this) {
            if (this.mDidInit) {
                final MetaData metaData = new MetaData((Context)this.mActivity);
                metaData.set("gdpr.consent", b);
                metaData.commit();
            }
            else {
                this.mConsentCollectingUserData = b;
            }
        }
    }
    
    public void showInterstitial(final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        final String optString = jsonObject.optString("zoneId");
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + " showInterstitial placementId <" + optString + ">", 1);
        if (UnityAds.isReady(optString)) {
            UnityAds.show(this.mActivity, optString);
        }
        else if (interstitialSmashListener != null) {
            interstitialSmashListener.onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
        }
    }
    
    public void showRewardedVideo(final JSONObject jsonObject, final RewardedVideoSmashListener rewardedVideoSmashListener) {
        synchronized (this) {
            final String optString = jsonObject.optString("zoneId");
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + " showRewardedVideo placementId: <" + optString + ">", 1);
            if (UnityAds.isReady(optString)) {
                if (!TextUtils.isEmpty((CharSequence)this.getDynamicUserId())) {
                    final PlayerMetaData playerMetaData = new PlayerMetaData((Context)this.mActivity);
                    playerMetaData.setServerId(this.getDynamicUserId());
                    playerMetaData.commit();
                }
                UnityAds.show(this.mActivity, optString);
            }
            else if (rewardedVideoSmashListener != null) {
                rewardedVideoSmashListener.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Rewarded Video"));
                rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
            }
        }
    }
}
