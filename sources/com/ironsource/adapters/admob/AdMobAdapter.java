package com.ironsource.adapters.admob;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.ironsource.mediationsdk.AbstractAdapter;
import com.ironsource.mediationsdk.AdapterUtils;
import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IntegrationData;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.sdk.BannerSmashListener;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceConstants.Gender;
import com.vungle.warren.model.Cookie;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

public class AdMobAdapter extends AbstractAdapter {
    private static final String CORE_SDK_VERSION = "15.0.0";
    private static final String VERSION = "4.3.0";
    private final String AD_UNIT_ID;
    private final String APP_ID;
    private final String IRONSOURCE_REQUEST_AGENT;
    private ConcurrentHashMap<String, AdView> mAdIdToBannerAd;
    private ConcurrentHashMap<String, InterstitialAd> mAdIdToIsAd;
    private int mAge;
    private Boolean mConsent;
    private Boolean mDidInitSdk;
    private int mGender;
    private ConcurrentHashMap<String, Boolean> mInterstitialAdsAvailability;
    private boolean mIsRewardedVideoReady;
    private RewardedVideoAd mRewardedVideoAd;
    private RewardedVideoAdListener rewardedVideoAdListener;

    /* renamed from: com.ironsource.adapters.admob.AdMobAdapter$5 */
    class C27225 implements Runnable {
        C27225() {
        }

        public void run() {
            if (AdMobAdapter.this.mRewardedVideoAd.isLoaded()) {
                AdMobAdapter.this.mRewardedVideoAd.show();
                return;
            }
            if (AdMobAdapter.this.mActiveRewardedVideoSmash != null) {
                AdMobAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
            }
            Iterator it = AdMobAdapter.this.mAllRewardedVideoSmashes.iterator();
            while (it.hasNext()) {
                RewardedVideoSmashListener smash = (RewardedVideoSmashListener) it.next();
                if (smash != null) {
                    smash.onRewardedVideoAvailabilityChanged(false);
                }
            }
        }
    }

    /* renamed from: com.ironsource.adapters.admob.AdMobAdapter$6 */
    class C27236 implements RewardedVideoAdListener {
        C27236() {
        }

        public void onRewardedVideoAdLoaded() {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onRewardedVideoAdLoaded", 1);
            AdMobAdapter.this.mIsRewardedVideoReady = true;
            Iterator it = AdMobAdapter.this.mAllRewardedVideoSmashes.iterator();
            while (it.hasNext()) {
                RewardedVideoSmashListener smash = (RewardedVideoSmashListener) it.next();
                if (smash != null) {
                    smash.onRewardedVideoAvailabilityChanged(true);
                }
            }
        }

        public void onRewardedVideoAdOpened() {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onRewardedVideoAdOpened", 1);
            AdMobAdapter.this.mIsRewardedVideoReady = false;
            if (AdMobAdapter.this.mActiveRewardedVideoSmash != null) {
                AdMobAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdOpened();
            }
            Iterator it = AdMobAdapter.this.mAllRewardedVideoSmashes.iterator();
            while (it.hasNext()) {
                RewardedVideoSmashListener smash = (RewardedVideoSmashListener) it.next();
                if (smash != null) {
                    smash.onRewardedVideoAvailabilityChanged(false);
                }
            }
        }

        public void onRewardedVideoStarted() {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onRewardedVideoStarted", 1);
            if (AdMobAdapter.this.mActiveRewardedVideoSmash != null) {
                AdMobAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdStarted();
            }
        }

        public void onRewardedVideoCompleted() {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onRewardedVideoCompleted", 1);
            if (AdMobAdapter.this.mActiveRewardedVideoSmash != null) {
                AdMobAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdEnded();
            }
        }

        public void onRewardedVideoAdClosed() {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onRewardedVideoAdClosed", 1);
            if (AdMobAdapter.this.mActiveRewardedVideoSmash != null) {
                AdMobAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdClosed();
            }
        }

        public void onRewarded(RewardItem rewardItem) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onRewarded", 1);
            if (AdMobAdapter.this.mActiveRewardedVideoSmash != null) {
                AdMobAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdRewarded();
            }
        }

        public void onRewardedVideoAdLeftApplication() {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onRewardedVideoAdLeftApplication", 1);
            if (AdMobAdapter.this.mActiveRewardedVideoSmash != null) {
                AdMobAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdClicked();
            }
        }

        public void onRewardedVideoAdFailedToLoad(int errorCode) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onRewardedVideoAdFailedToLoad", 1);
            AdMobAdapter.this.mIsRewardedVideoReady = false;
            Iterator it = AdMobAdapter.this.mAllRewardedVideoSmashes.iterator();
            while (it.hasNext()) {
                RewardedVideoSmashListener smash = (RewardedVideoSmashListener) it.next();
                if (smash != null) {
                    smash.onRewardedVideoAvailabilityChanged(false);
                }
            }
        }
    }

    public static AdMobAdapter startAdapter(String providerName) {
        return new AdMobAdapter(providerName);
    }

    private AdMobAdapter(String providerName) {
        super(providerName);
        this.IRONSOURCE_REQUEST_AGENT = "ironSource";
        this.mAge = -1;
        this.mIsRewardedVideoReady = false;
        this.mDidInitSdk = Boolean.valueOf(false);
        this.AD_UNIT_ID = "adUnitId";
        this.APP_ID = Cookie.APP_ID;
        this.mConsent = null;
        this.rewardedVideoAdListener = new C27236();
        this.mAllBannerSmashes = new CopyOnWriteArrayList();
        this.mAdIdToIsAd = new ConcurrentHashMap();
        this.mInterstitialAdsAvailability = new ConcurrentHashMap();
        this.mAdIdToBannerAd = new ConcurrentHashMap();
    }

    public static IntegrationData getIntegrationData(Activity activity) {
        IntegrationData ret = new IntegrationData("AdMob", VERSION);
        ret.activities = new String[]{AdActivity.CLASS_NAME};
        return ret;
    }

    public String getVersion() {
        return VERSION;
    }

    public String getCoreSDKVersion() {
        return CORE_SDK_VERSION;
    }

    public void onResume(final Activity activity) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                if (AdMobAdapter.this.mRewardedVideoAd != null) {
                    AdMobAdapter.this.mRewardedVideoAd.resume(activity);
                }
            }
        });
    }

    public void onPause(final Activity activity) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                if (AdMobAdapter.this.mRewardedVideoAd != null) {
                    AdMobAdapter.this.mRewardedVideoAd.pause(activity);
                }
            }
        });
    }

    public void setAge(int age) {
        this.mAge = age;
    }

    public void setGender(String gender) {
        int i = -1;
        switch (gender.hashCode()) {
            case -1278174388:
                if (gender.equals(Gender.FEMALE)) {
                    i = 0;
                    break;
                }
                break;
            case 3343885:
                if (gender.equals(Gender.MALE)) {
                    i = 1;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                this.mGender = 2;
                return;
            case 1:
                this.mGender = 1;
                return;
            default:
                this.mGender = 0;
                return;
        }
    }

    private synchronized void initSDK(Activity activity, String appKey) {
        if (!this.mDidInitSdk.booleanValue()) {
            this.mDidInitSdk = Boolean.valueOf(true);
            if (TextUtils.isEmpty(appKey)) {
                MobileAds.initialize(activity.getApplicationContext());
            } else {
                MobileAds.initialize(activity.getApplicationContext(), appKey);
            }
        }
    }

    public void initRewardedVideo(final Activity activity, String appKey, String userId, final JSONObject config, RewardedVideoSmashListener listener) {
        if (listener == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "Admob RV init failed: RewardedVideoSmashListener is empty", 2);
        } else if (TextUtils.isEmpty(config.optString(Cookie.APP_ID))) {
            listener.onRewardedVideoAvailabilityChanged(false);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    AdMobAdapter.this.initSDK(activity, config.optString(Cookie.APP_ID));
                    AdMobAdapter.this.mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(activity);
                    AdMobAdapter.this.mRewardedVideoAd.setRewardedVideoAdListener(AdMobAdapter.this.rewardedVideoAdListener);
                    AdMobAdapter.this.loadRewardedVideoAd(config);
                }
            });
        }
    }

    public void fetchRewardedVideo(JSONObject config) {
        loadRewardedVideoAd(config);
    }

    private void loadRewardedVideoAd(final JSONObject config) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                String RVAdUnitId = config.optString("adUnitId");
                AdRequest adRequest = AdMobAdapter.this.createAdRequest();
                if (AdMobAdapter.this.mRewardedVideoAd != null) {
                    AdMobAdapter.this.mRewardedVideoAd.loadAd(RVAdUnitId, adRequest);
                }
            }
        });
    }

    public boolean isRewardedVideoAvailable(JSONObject config) {
        return this.mRewardedVideoAd != null && this.mIsRewardedVideoReady;
    }

    public void showRewardedVideo(JSONObject config, RewardedVideoSmashListener listener) {
        this.mActiveRewardedVideoSmash = listener;
        new Handler(Looper.getMainLooper()).post(new C27225());
    }

    public void initInterstitial(Activity activity, String appKey, String userId, JSONObject config, InterstitialSmashListener listener) {
        if (listener == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "Admob interstitial init failed: InterstitialSmashListener is empty", 2);
        } else if (TextUtils.isEmpty(config.optString(Cookie.APP_ID))) {
            listener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Missing params: 'appId' ", "Interstitial"));
        } else {
            final String adUnitId = config.optString("adUnitId");
            if (TextUtils.isEmpty(adUnitId)) {
                listener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Missing params: 'adUnitId' ", "Interstitial"));
                return;
            }
            final Activity activity2 = activity;
            final JSONObject jSONObject = config;
            final InterstitialSmashListener interstitialSmashListener = listener;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    AdMobAdapter.this.initSDK(activity2, jSONObject.optString(Cookie.APP_ID));
                    InterstitialAd ad = new InterstitialAd(activity2);
                    ad.setAdUnitId(adUnitId);
                    AdMobAdapter.this.mAdIdToIsAd.put(adUnitId, ad);
                    ad.setAdListener(AdMobAdapter.this.createInterstitialAdListener(jSONObject, adUnitId));
                    AdMobAdapter.this.mInterstitialPlacementToListenerMap.put(adUnitId, interstitialSmashListener);
                    interstitialSmashListener.onInterstitialInitSuccess();
                }
            });
        }
    }

    private AdListener createInterstitialAdListener(final JSONObject config, final String adUnitId) {
        return new AdListener() {
            public void onAdClosed() {
                IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onAdClosed " + adUnitId, 1);
                if (AdMobAdapter.this.mInterstitialPlacementToListenerMap.containsKey(adUnitId)) {
                    ((InterstitialSmashListener) AdMobAdapter.this.mInterstitialPlacementToListenerMap.get(adUnitId)).onInterstitialAdClosed();
                }
                AdMobAdapter.this.setInterstitialAdAvailability(config);
            }

            public void onAdFailedToLoad(int errorCode) {
                IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onAdFailedToLoad " + adUnitId, 1);
                if (AdMobAdapter.this.mInterstitialPlacementToListenerMap.containsKey(adUnitId)) {
                    ((InterstitialSmashListener) AdMobAdapter.this.mInterstitialPlacementToListenerMap.get(adUnitId)).onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError(AdMobAdapter.this.getErrorString(errorCode) + "( " + errorCode + " )"));
                }
                AdMobAdapter.this.setInterstitialAdAvailability(config);
            }

            public void onAdLeftApplication() {
                IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onAdLeftApplication " + adUnitId, 1);
                if (AdMobAdapter.this.mInterstitialPlacementToListenerMap.containsKey(adUnitId)) {
                    ((InterstitialSmashListener) AdMobAdapter.this.mInterstitialPlacementToListenerMap.get(adUnitId)).onInterstitialAdClicked();
                }
            }

            public void onAdOpened() {
                IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onAdOpened " + adUnitId, 1);
                if (AdMobAdapter.this.mInterstitialPlacementToListenerMap.containsKey(adUnitId)) {
                    ((InterstitialSmashListener) AdMobAdapter.this.mInterstitialPlacementToListenerMap.get(adUnitId)).onInterstitialAdOpened();
                    ((InterstitialSmashListener) AdMobAdapter.this.mInterstitialPlacementToListenerMap.get(adUnitId)).onInterstitialAdShowSucceeded();
                }
            }

            public void onAdLoaded() {
                IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onAdLoaded " + adUnitId, 1);
                if (AdMobAdapter.this.mInterstitialPlacementToListenerMap.containsKey(adUnitId)) {
                    ((InterstitialSmashListener) AdMobAdapter.this.mInterstitialPlacementToListenerMap.get(adUnitId)).onInterstitialAdReady();
                }
                AdMobAdapter.this.setInterstitialAdAvailability(config);
            }
        };
    }

    public void loadInterstitial(final JSONObject config, final InterstitialSmashListener listener) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                InterstitialAd ad = AdMobAdapter.this.getInterstitialAd(config);
                if (ad == null) {
                    if (listener != null) {
                        listener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("invalid adUnitId"));
                    }
                } else if (ad.isLoaded()) {
                    String adUnitId = config.optString("adUnitId");
                    if (AdMobAdapter.this.mInterstitialPlacementToListenerMap.containsKey(adUnitId)) {
                        ((InterstitialSmashListener) AdMobAdapter.this.mInterstitialPlacementToListenerMap.get(adUnitId)).onInterstitialAdReady();
                    }
                } else if (!ad.isLoading()) {
                    ad.loadAd(AdMobAdapter.this.createAdRequest());
                }
            }
        });
    }

    public void showInterstitial(final JSONObject config, final InterstitialSmashListener listener) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                InterstitialAd ad = AdMobAdapter.this.getInterstitialAd(config);
                if (ad != null && ad.isLoaded()) {
                    ad.show();
                } else if (listener != null) {
                    listener.onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
                }
            }
        });
    }

    public final boolean isInterstitialReady(JSONObject config) {
        String adUnitId = config.optString("adUnitId");
        if (this.mInterstitialAdsAvailability.get(adUnitId) != null) {
            return ((Boolean) this.mInterstitialAdsAvailability.get(adUnitId)).booleanValue();
        }
        return false;
    }

    public void initBanners(final Activity activity, String appKey, String userId, final JSONObject config, final BannerSmashListener listener) {
        if (listener == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "Admob banner init failed: BannerSmashListener is empty", 2);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    AdMobAdapter.this.initSDK(activity, config.optString(Cookie.APP_ID));
                    listener.onBannerInitSuccess();
                }
            });
        }
    }

    private AdSize getAdSize(ISBannerSize size, boolean isLargeScreen) {
        String description = size.getDescription();
        Object obj = -1;
        switch (description.hashCode()) {
            case -387072689:
                if (description.equals("RECTANGLE")) {
                    obj = 2;
                    break;
                }
                break;
            case 72205083:
                if (description.equals("LARGE")) {
                    obj = 1;
                    break;
                }
                break;
            case 79011241:
                if (description.equals("SMART")) {
                    obj = 3;
                    break;
                }
                break;
            case 1951953708:
                if (description.equals("BANNER")) {
                    obj = null;
                    break;
                }
                break;
            case 1999208305:
                if (description.equals("CUSTOM")) {
                    obj = 4;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return AdSize.BANNER;
            case 1:
                return AdSize.LARGE_BANNER;
            case 2:
                return AdSize.MEDIUM_RECTANGLE;
            case 3:
                return isLargeScreen ? AdSize.LEADERBOARD : AdSize.BANNER;
            case 4:
                return new AdSize(size.getWidth(), size.getHeight());
            default:
                return null;
        }
    }

    private AdListener createBannerAdListener(final AdView adView, final String adUnitId) {
        return new AdListener() {
            public void onAdClosed() {
                super.onAdClosed();
                if (AdMobAdapter.this.mBannerPlacementToListenerMap.containsKey(adUnitId)) {
                    ((BannerSmashListener) AdMobAdapter.this.mBannerPlacementToListenerMap.get(adUnitId)).onBannerAdScreenDismissed();
                }
            }

            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                if (AdMobAdapter.this.mBannerPlacementToListenerMap.containsKey(adUnitId)) {
                    ((BannerSmashListener) AdMobAdapter.this.mBannerPlacementToListenerMap.get(adUnitId)).onBannerAdLoadFailed(ErrorBuilder.buildLoadFailedError(AdMobAdapter.this.getErrorString(errorCode) + "( " + errorCode + " )"));
                }
            }

            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                if (AdMobAdapter.this.mBannerPlacementToListenerMap.containsKey(adUnitId)) {
                    ((BannerSmashListener) AdMobAdapter.this.mBannerPlacementToListenerMap.get(adUnitId)).onBannerAdClicked();
                    ((BannerSmashListener) AdMobAdapter.this.mBannerPlacementToListenerMap.get(adUnitId)).onBannerAdLeftApplication();
                }
            }

            public void onAdOpened() {
                super.onAdOpened();
                if (AdMobAdapter.this.mBannerPlacementToListenerMap.containsKey(adUnitId)) {
                    ((BannerSmashListener) AdMobAdapter.this.mBannerPlacementToListenerMap.get(adUnitId)).onBannerAdScreenPresented();
                }
            }

            public void onAdLoaded() {
                super.onAdLoaded();
                if (AdMobAdapter.this.mBannerPlacementToListenerMap.containsKey(adUnitId)) {
                    LayoutParams layoutParams = new LayoutParams(-2, -2);
                    layoutParams.gravity = 17;
                    ((BannerSmashListener) AdMobAdapter.this.mBannerPlacementToListenerMap.get(adUnitId)).onBannerAdLoaded(adView, layoutParams);
                }
            }
        };
    }

    public void loadBanner(IronSourceBannerLayout banner, JSONObject config, BannerSmashListener listener) {
        if (listener == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "AdMob loadBanner listener == null", 3);
        } else if (banner == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "AdMob loadBanner banner == null", 3);
        } else {
            final String adUnitId = config.optString("adUnitId");
            if (TextUtils.isEmpty(adUnitId)) {
                listener.onBannerAdLoadFailed(ErrorBuilder.buildLoadFailedError("AdMobAdapter loadBanner adUnitId is empty"));
                return;
            }
            final AdSize size = getAdSize(banner.getSize(), AdapterUtils.isLargeScreen(banner.getActivity()));
            if (size == null) {
                listener.onBannerAdLoadFailed(ErrorBuilder.unsupportedBannerSize("AdMob"));
                return;
            }
            final IronSourceBannerLayout ironSourceBannerLayout = banner;
            final BannerSmashListener bannerSmashListener = listener;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    try {
                        AdView adView = new AdView(ironSourceBannerLayout.getActivity());
                        adView.setAdSize(size);
                        adView.setAdUnitId(adUnitId);
                        adView.setAdListener(AdMobAdapter.this.createBannerAdListener(adView, adUnitId));
                        AdMobAdapter.this.mAdIdToBannerAd.put(adUnitId, adView);
                        AdMobAdapter.this.mBannerPlacementToListenerMap.put(adUnitId, bannerSmashListener);
                        adView.loadAd(AdMobAdapter.this.createAdRequest());
                    } catch (Exception e) {
                        bannerSmashListener.onBannerAdLoadFailed(ErrorBuilder.buildLoadFailedError("AdMobAdapter loadBanner exception " + e.getMessage()));
                    }
                }
            });
        }
    }

    public void destroyBanner(final JSONObject config) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                try {
                    String adUnitId = config.optString("adUnitId");
                    if (AdMobAdapter.this.mAdIdToBannerAd.containsKey(adUnitId)) {
                        ((AdView) AdMobAdapter.this.mAdIdToBannerAd.get(adUnitId)).destroy();
                        AdMobAdapter.this.mAdIdToBannerAd.remove(adUnitId);
                    }
                } catch (Exception e) {
                    AdMobAdapter.this.log(IronSourceTag.ADAPTER_API, "AdMob destroyBanner() exception: " + e, 3);
                }
            }
        });
    }

    public void reloadBanner(final JSONObject config) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                if (AdMobAdapter.this.mAdIdToBannerAd.get(config.optString("adUnitId")) != null) {
                    ((AdView) AdMobAdapter.this.mAdIdToBannerAd.get(config.optString("adUnitId"))).loadAd(AdMobAdapter.this.createAdRequest());
                }
            }
        });
    }

    private AdRequest createAdRequest() {
        Builder builder = new Builder();
        builder.setGender(this.mGender);
        builder.setRequestAgent("ironSource");
        if (this.mAge > -1) {
            builder.tagForChildDirectedTreatment(this.mAge < 13);
        }
        if (!(this.mConsent == null || this.mConsent.booleanValue())) {
            Bundle extras = new Bundle();
            extras.putString("npa", "1");
            builder.addNetworkExtrasBundle(com.google.ads.mediation.admob.AdMobAdapter.class, extras);
        }
        return builder.build();
    }

    private String getErrorString(int errorCode) {
        switch (errorCode) {
            case 0:
                return "Internal error";
            case 1:
                return "The ad request was invalid";
            case 2:
                return "The ad request was unsuccessful due to network connectivity";
            case 3:
                return "The ad request was successful, but no ad was returned due to lack of ad inventory";
            default:
                return "Unknown error";
        }
    }

    private InterstitialAd getInterstitialAd(JSONObject config) {
        String adUnitId = config.optString("adUnitId");
        if (TextUtils.isEmpty(adUnitId) || !this.mAdIdToIsAd.containsKey(adUnitId)) {
            return null;
        }
        return (InterstitialAd) this.mAdIdToIsAd.get(adUnitId);
    }

    private void setInterstitialAdAvailability(final JSONObject config) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                InterstitialAd ad = AdMobAdapter.this.getInterstitialAd(config);
                String adUnitId = config.optString("adUnitId");
                if (ad != null && adUnitId != null) {
                    IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "setInterstitialAdAvailability for " + adUnitId + ", avilbility " + ad.isLoaded(), 1);
                    AdMobAdapter.this.mInterstitialAdsAvailability.put(adUnitId, Boolean.valueOf(ad.isLoaded()));
                }
            }
        });
    }

    protected void setConsent(boolean consent) {
        this.mConsent = Boolean.valueOf(consent);
    }
}
