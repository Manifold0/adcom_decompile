package com.ironsource.adapters.vungle;

import android.app.Activity;
import android.text.TextUtils;
import com.ironsource.mediationsdk.AbstractAdapter;
import com.ironsource.mediationsdk.IntegrationData;
import com.ironsource.mediationsdk.InterstitialSmash;
import com.ironsource.mediationsdk.RewardedVideoSmash;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.vungle.warren.AdConfig;
import com.vungle.warren.InitCallback;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.PlayAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.Vungle.Consent;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONObject;

class VungleAdapter extends AbstractAdapter {
    private static final String APP_ID = "AppID";
    private static final String CONSENT_MESSAGE_VERSION = "1.0.0";
    private static final String CORE_SDK_VERSION = "6.3.17";
    private static final String PLACEMENT_ID = "PlacementId";
    private static final String VERSION = "4.1.4";
    private EInitState mInitState = EInitState.NOT_INIT;
    private Set<String> mInitiatedAdUnits;
    private Boolean mIsConsent = null;

    /* renamed from: com.ironsource.adapters.vungle.VungleAdapter$4 */
    class C04134 implements InitCallback {
        C04134() {
        }

        public void onSuccess() {
            VungleAdapter.this.log(IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": Succeeded to initialize SDK ", 1);
            synchronized (VungleAdapter.this.mInitState) {
                VungleAdapter.this.setInitState(EInitState.INIT_SUCCESS);
                if (VungleAdapter.this.mIsConsent != null) {
                    Vungle.updateConsentStatus(VungleAdapter.this.mIsConsent.booleanValue() ? Consent.OPTED_IN : Consent.OPTED_OUT, VungleAdapter.CONSENT_MESSAGE_VERSION);
                }
            }
            if (VungleAdapter.this.mInitiatedAdUnits != null) {
                if (VungleAdapter.this.mInitiatedAdUnits.contains(IronSourceConstants.REWARDED_VIDEO_AD_UNIT)) {
                    for (Entry<String, RewardedVideoSmashListener> entry : VungleAdapter.this.mRewardedVideoPlacementToListenerMap.entrySet()) {
                        VungleAdapter.this.loadRewardedVideoAd((String) entry.getKey());
                    }
                }
                if (VungleAdapter.this.mInitiatedAdUnits.contains("Interstitial")) {
                    for (Entry<String, InterstitialSmashListener> entry2 : VungleAdapter.this.mInterstitialPlacementToListenerMap.entrySet()) {
                        if (entry2.getValue() != null) {
                            ((InterstitialSmashListener) entry2.getValue()).onInterstitialInitSuccess();
                        }
                    }
                }
            }
        }

        public void onError(Throwable throwable) {
            VungleAdapter.this.log(IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": Failed to initialize SDK ", 1);
            VungleAdapter.this.setInitState(EInitState.INIT_FAIL);
            if (VungleAdapter.this.mInitiatedAdUnits != null) {
                if (VungleAdapter.this.mInitiatedAdUnits.contains(IronSourceConstants.REWARDED_VIDEO_AD_UNIT)) {
                    for (Entry<String, RewardedVideoSmashListener> entry : VungleAdapter.this.mRewardedVideoPlacementToListenerMap.entrySet()) {
                        if (entry.getValue() != null) {
                            ((RewardedVideoSmashListener) entry.getValue()).onRewardedVideoAvailabilityChanged(false);
                        }
                    }
                }
                if (VungleAdapter.this.mInitiatedAdUnits.contains("Interstitial")) {
                    for (Entry<String, InterstitialSmashListener> entry2 : VungleAdapter.this.mInterstitialPlacementToListenerMap.entrySet()) {
                        if (entry2.getValue() != null) {
                            ((InterstitialSmashListener) entry2.getValue()).onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Vungle failed to init: " + throwable.getMessage(), "Interstitial"));
                        }
                    }
                }
            }
        }

        public void onAutoCacheAdAvailable(String placementId) {
            VungleAdapter.this.log(IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": Cache ad is available for placementId " + placementId, 1);
            if (VungleAdapter.this.mInitiatedAdUnits.contains(IronSourceConstants.REWARDED_VIDEO_AD_UNIT)) {
                for (Entry<String, RewardedVideoSmashListener> entry : VungleAdapter.this.mRewardedVideoPlacementToListenerMap.entrySet()) {
                    if (((String) entry.getKey()).equals(placementId) && entry.getValue() != null) {
                        ((RewardedVideoSmashListener) entry.getValue()).onRewardedVideoAvailabilityChanged(true);
                    }
                }
            }
            if (VungleAdapter.this.mInitiatedAdUnits.contains("Interstitial")) {
                for (Entry<String, InterstitialSmashListener> entry2 : VungleAdapter.this.mInterstitialPlacementToListenerMap.entrySet()) {
                    if (((String) entry2.getKey()).equals(placementId) && entry2.getValue() != null) {
                        ((InterstitialSmashListener) entry2.getValue()).onInterstitialAdReady();
                    }
                }
            }
        }
    }

    /* renamed from: com.ironsource.adapters.vungle.VungleAdapter$5 */
    class C04145 implements LoadAdCallback {
        C04145() {
        }

        public void onAdLoad(String placementReferenceId) {
            VungleAdapter.this.log(IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": RewardedVideo Ad loaded for placementReferenceId: " + placementReferenceId, 1);
            if (VungleAdapter.this.mRewardedVideoPlacementToListenerMap.get(placementReferenceId) != null) {
                ((RewardedVideoSmashListener) VungleAdapter.this.mRewardedVideoPlacementToListenerMap.get(placementReferenceId)).onRewardedVideoAvailabilityChanged(true);
            }
        }

        public void onError(String placementReferenceId, Throwable throwable) {
            VungleAdapter.this.log(IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": RewardedVideo Ad failed to load for placementReferenceId: " + placementReferenceId + ", error: " + throwable.getMessage(), 1);
            if (VungleAdapter.this.mRewardedVideoPlacementToListenerMap.get(placementReferenceId) != null) {
                ((RewardedVideoSmashListener) VungleAdapter.this.mRewardedVideoPlacementToListenerMap.get(placementReferenceId)).onRewardedVideoAvailabilityChanged(false);
            }
        }
    }

    private enum EInitState {
        NOT_INIT,
        INIT_IN_PROGRESS,
        INIT_SUCCESS,
        INIT_FAIL
    }

    public static VungleAdapter startAdapter(String providerName) {
        return new VungleAdapter(providerName);
    }

    private VungleAdapter(String providerName) {
        super(providerName);
    }

    public static IntegrationData getIntegrationData(Activity activity) {
        IntegrationData ret = new IntegrationData("Vungle", VERSION);
        ret.validateWriteExternalStorage = true;
        return ret;
    }

    public String getVersion() {
        return VERSION;
    }

    public String getCoreSDKVersion() {
        return "6.3.17";
    }

    protected void setConsent(boolean consent) {
        synchronized (this.mInitState) {
            if (getCurrentInitState() == EInitState.INIT_SUCCESS) {
                Vungle.updateConsentStatus(consent ? Consent.OPTED_IN : Consent.OPTED_OUT, CONSENT_MESSAGE_VERSION);
            } else {
                this.mIsConsent = Boolean.valueOf(consent);
            }
        }
    }

    public void onResume(Activity activity) {
    }

    public void onPause(Activity activity) {
    }

    public synchronized void initRewardedVideo(Activity activity, String appKey, String userId, JSONObject config, RewardedVideoSmashListener listener) {
        if (!TextUtils.isEmpty(config.optString(APP_ID)) && !TextUtils.isEmpty(config.optString(PLACEMENT_ID))) {
            if (!(TextUtils.isEmpty(config.optString(PLACEMENT_ID)) || listener == null)) {
                this.mRewardedVideoPlacementToListenerMap.put(config.optString(PLACEMENT_ID), listener);
            }
            addInitiatedAdUnit(IronSourceConstants.REWARDED_VIDEO_AD_UNIT);
            HashSet<String> allPlacements = new HashSet();
            switch (getCurrentInitState()) {
                case NOT_INIT:
                    if (listener != null) {
                        allPlacements = ((RewardedVideoSmash) listener).getAllSettingsForProvider(PLACEMENT_ID);
                    }
                    initVungleSdk(activity, config.optString(APP_ID), userId, allPlacements);
                    break;
                case INIT_IN_PROGRESS:
                    break;
                case INIT_SUCCESS:
                    if (Vungle.canPlayAd(config.optString(PLACEMENT_ID))) {
                        if (listener != null) {
                            listener.onRewardedVideoAvailabilityChanged(true);
                            break;
                        }
                    }
                    loadRewardedVideoAd(config.optString(PLACEMENT_ID));
                    break;
                    break;
                case INIT_FAIL:
                    if (listener != null) {
                        listener.onRewardedVideoAvailabilityChanged(false);
                        break;
                    }
                    break;
                default:
                    break;
            }
        } else if (listener != null) {
            listener.onRewardedVideoAvailabilityChanged(false);
        }
    }

    public void fetchRewardedVideo(JSONObject config) {
    }

    public void showRewardedVideo(JSONObject config, final RewardedVideoSmashListener listener) {
        AdConfig overrideConfig = new AdConfig();
        if (Vungle.canPlayAd(config.optString(PLACEMENT_ID))) {
            Vungle.setIncentivizedFields(getDynamicUserId(), null, null, null, null);
            Vungle.playAd(config.optString(PLACEMENT_ID), overrideConfig, new PlayAdCallback() {
                public void onAdStart(String placementReferenceId) {
                    VungleAdapter.this.log(IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": RewardedVideo ad started for placementReferenceId: " + placementReferenceId, 1);
                    if (listener != null) {
                        listener.onRewardedVideoAdOpened();
                        listener.onRewardedVideoAdStarted();
                    }
                }

                public void onAdEnd(String placementReferenceId, boolean completed, boolean isCTAClicked) {
                    VungleAdapter.this.log(IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": RewardedVideo ad ended for placementReferenceId: " + placementReferenceId, 1);
                    if (listener != null) {
                        if (isCTAClicked) {
                            listener.onRewardedVideoAdClicked();
                        }
                        listener.onRewardedVideoAdEnded();
                        if (completed) {
                            listener.onRewardedVideoAdRewarded();
                        }
                        listener.onRewardedVideoAdClosed();
                        boolean anotherAdAvailable = Vungle.canPlayAd(placementReferenceId);
                        listener.onRewardedVideoAvailabilityChanged(anotherAdAvailable);
                        if (!anotherAdAvailable) {
                            VungleAdapter.this.loadRewardedVideoAd(placementReferenceId);
                        }
                    }
                }

                public void onError(String placementReferenceId, Throwable throwable) {
                    VungleAdapter.this.log(IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": RewardedVideo ad failed to show for placementReferenceId: " + placementReferenceId + "error: " + throwable.getMessage(), 1);
                    if (listener != null) {
                        listener.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
                        listener.onRewardedVideoAvailabilityChanged(false);
                    }
                }
            });
        }
    }

    public boolean isRewardedVideoAvailable(JSONObject config) {
        return Vungle.isInitialized() && Vungle.canPlayAd(config.optString(PLACEMENT_ID));
    }

    public synchronized void initInterstitial(Activity activity, String appKey, String userId, JSONObject config, InterstitialSmashListener listener) {
        if (!TextUtils.isEmpty(config.optString(APP_ID)) && !TextUtils.isEmpty(config.optString(PLACEMENT_ID))) {
            if (!(TextUtils.isEmpty(config.optString(PLACEMENT_ID)) || listener == null)) {
                this.mInterstitialPlacementToListenerMap.put(config.optString(PLACEMENT_ID), listener);
            }
            addInitiatedAdUnit("Interstitial");
            HashSet<String> allPlacements = new HashSet();
            switch (getCurrentInitState()) {
                case NOT_INIT:
                    if (listener != null) {
                        allPlacements = ((InterstitialSmash) listener).getAllSettingsForProvider(PLACEMENT_ID);
                    }
                    initVungleSdk(activity, config.optString(APP_ID), userId, allPlacements);
                    break;
                case INIT_IN_PROGRESS:
                    break;
                case INIT_SUCCESS:
                    if (listener != null) {
                        listener.onInterstitialInitSuccess();
                        break;
                    }
                    break;
                case INIT_FAIL:
                    if (listener != null) {
                        listener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Init Failed", "Interstitial"));
                        break;
                    }
                    break;
                default:
                    break;
            }
        } else if (listener != null) {
            listener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Missing params", "Interstitial"));
        }
    }

    public void loadInterstitial(JSONObject config, final InterstitialSmashListener listener) {
        if (Vungle.isInitialized()) {
            String placementAd = config.optString(PLACEMENT_ID);
            if (!Vungle.canPlayAd(placementAd)) {
                Vungle.loadAd(placementAd, new LoadAdCallback() {
                    public void onAdLoad(String placementReferenceId) {
                        VungleAdapter.this.log(IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": Interstitial loaded for placementReferenceId: " + placementReferenceId, 1);
                        if (listener != null) {
                            listener.onInterstitialAdReady();
                        }
                    }

                    public void onError(String placementReferenceId, Throwable throwable) {
                        VungleAdapter.this.log(IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": Interstitial failed to load for placementReferenceId: " + placementReferenceId + " ,error: " + throwable.getMessage(), 1);
                        if (listener != null) {
                            listener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("Error loading Ad: " + throwable.getMessage()));
                        }
                    }
                });
            } else if (listener != null) {
                listener.onInterstitialAdReady();
            }
        }
    }

    public void showInterstitial(JSONObject config, final InterstitialSmashListener listener) {
        if (Vungle.canPlayAd(config.optString(PLACEMENT_ID))) {
            Vungle.playAd(config.optString(PLACEMENT_ID), new AdConfig(), new PlayAdCallback() {
                public void onAdStart(String placementReferenceId) {
                    VungleAdapter.this.log(IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": Interstitial ad started for placementReferenceId: " + placementReferenceId, 1);
                    if (listener != null) {
                        listener.onInterstitialAdOpened();
                        listener.onInterstitialAdShowSucceeded();
                    }
                }

                public void onAdEnd(String placementReferenceId, boolean completed, boolean isCTAClicked) {
                    VungleAdapter.this.log(IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": Interstitial ad ended for placementReferenceId: " + placementReferenceId, 1);
                    if (listener != null) {
                        if (isCTAClicked) {
                            listener.onInterstitialAdClicked();
                        }
                        listener.onInterstitialAdClosed();
                    }
                }

                public void onError(String placementReferenceId, Throwable throwable) {
                    VungleAdapter.this.log(IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": Interstitial ad failed to show for placementReferenceId: " + placementReferenceId + "error: " + throwable.getMessage(), 1);
                    if (listener != null) {
                        listener.onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
                    }
                }
            });
        } else if (listener != null) {
            listener.onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
        }
    }

    public boolean isInterstitialReady(JSONObject config) {
        return Vungle.isInitialized() && Vungle.canPlayAd(config.optString(PLACEMENT_ID));
    }

    private synchronized void setInitState(EInitState state) {
        log(IronSourceTag.ADAPTER_API, getProviderName() + ":init state changed from " + this.mInitState + " to " + state + ")", 1);
        this.mInitState = state;
    }

    private synchronized EInitState getCurrentInitState() {
        return this.mInitState;
    }

    private void addInitiatedAdUnit(String adUnit) {
        if (this.mInitiatedAdUnits == null) {
            this.mInitiatedAdUnits = new HashSet();
        }
        this.mInitiatedAdUnits.add(adUnit);
    }

    private void initVungleSdk(Activity activity, String appId, String userId, HashSet<String> hashSet) {
        setInitState(EInitState.INIT_IN_PROGRESS);
        Vungle.init(appId, activity.getApplicationContext(), new C04134());
    }

    private void loadRewardedVideoAd(String placementReferenceId) {
        log(IronSourceTag.ADAPTER_API, getProviderName() + ": loadRewardedVideoAd placementId " + placementReferenceId, 1);
        Vungle.loadAd(placementReferenceId, new C04145());
    }
}
