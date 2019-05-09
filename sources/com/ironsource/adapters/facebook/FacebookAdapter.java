package com.ironsource.adapters.facebook;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.RewardData;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.google.android.gms.nearby.messages.Strategy;
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
import com.tonyodev.fetch.FetchService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

public class FacebookAdapter extends AbstractAdapter {
    private static final String CORE_SDK_VERSION = "5.1.0";
    private static final String GitHash = "a0b026c30";
    private static final String MEDIATION_SERVICE_NAME = "Supersonic";
    private static final String VERSION = "4.3.1";
    private final String PLACEMENT_ID;
    private Activity mActivity;
    private ConcurrentHashMap<String, AdView> mBannerPlacementToAdMap;
    private InterstitialAdListener mInterstitialAdListener;
    private ConcurrentHashMap<String, InterstitialAd> mInterstitialPlacementToAdMap;
    private RewardedVideoAdListener mRewardedVideoAdListener;
    private ConcurrentHashMap<String, RewardedVideoAd> mRewardedVideoPlacementToAdMap;

    /* renamed from: com.ironsource.adapters.facebook.FacebookAdapter$1 */
    class C26341 implements InterstitialAdListener {
        C26341() {
        }

        public void onInterstitialDisplayed(Ad ad) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onInterstitialDisplayed", 1);
            if (FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId()) != null) {
                ((InterstitialSmashListener) FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId())).onInterstitialAdOpened();
            }
        }

        public void onInterstitialDismissed(Ad ad) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onInterstitialDismissed", 1);
            if (FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId()) != null) {
                ((InterstitialSmashListener) FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId())).onInterstitialAdClosed();
            }
        }

        public void onError(Ad ad, AdError adError) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "IS Ad, onError; error: " + adError.getErrorMessage(), 1);
            if (FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId()) != null) {
                ((InterstitialSmashListener) FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId())).onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError(adError.getErrorMessage()));
            }
        }

        public void onAdLoaded(Ad ad) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "IS Ad, onAdLoaded", 1);
            if (FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId()) != null) {
                ((InterstitialSmashListener) FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId())).onInterstitialAdReady();
            }
        }

        public void onAdClicked(Ad ad) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "IS Ad, onAdClicked", 1);
            if (FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId()) != null) {
                ((InterstitialSmashListener) FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId())).onInterstitialAdClicked();
            }
        }

        public void onLoggingImpression(Ad ad) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "IS Ad, onLoggingImpression", 1);
            if (FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId()) != null) {
                ((InterstitialSmashListener) FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId())).onInterstitialAdShowSucceeded();
            }
        }
    }

    /* renamed from: com.ironsource.adapters.facebook.FacebookAdapter$2 */
    class C26352 implements RewardedVideoAdListener {
        C26352() {
        }

        public void onRewardedVideoCompleted() {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onRewardedVideoCompleted", 1);
            if (FacebookAdapter.this.mActiveRewardedVideoSmash != null) {
                FacebookAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdEnded();
                FacebookAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdRewarded();
            }
        }

        public void onLoggingImpression(Ad ad) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "RV Ad, onLoggingImpression", 1);
            if (FacebookAdapter.this.mActiveRewardedVideoSmash != null) {
                FacebookAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdOpened();
                FacebookAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdStarted();
            }
        }

        public void onRewardedVideoClosed() {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onRewardedVideoClosed", 1);
            if (FacebookAdapter.this.mActiveRewardedVideoSmash != null) {
                FacebookAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdClosed();
            }
        }

        public void onError(Ad ad, AdError adError) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "RV Ad, onError; error: " + adError.getErrorMessage(), 1);
            if (FacebookAdapter.this.mRewardedVideoPlacementToListenerMap.get(ad.getPlacementId()) != null) {
                ((RewardedVideoSmashListener) FacebookAdapter.this.mRewardedVideoPlacementToListenerMap.get(ad.getPlacementId())).onRewardedVideoAvailabilityChanged(false);
            }
        }

        public void onAdLoaded(Ad ad) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "RV Ad, onAdLoaded", 1);
            if (FacebookAdapter.this.mRewardedVideoPlacementToListenerMap.get(ad.getPlacementId()) != null) {
                ((RewardedVideoSmashListener) FacebookAdapter.this.mRewardedVideoPlacementToListenerMap.get(ad.getPlacementId())).onRewardedVideoAvailabilityChanged(true);
            }
        }

        public void onAdClicked(Ad ad) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "RV Ad, onAdClicked", 1);
            if (FacebookAdapter.this.mRewardedVideoPlacementToListenerMap.get(ad.getPlacementId()) != null) {
                ((RewardedVideoSmashListener) FacebookAdapter.this.mRewardedVideoPlacementToListenerMap.get(ad.getPlacementId())).onRewardedVideoAdClicked();
            }
        }
    }

    public static FacebookAdapter startAdapter(String providerName) {
        return new FacebookAdapter(providerName);
    }

    public static IntegrationData getIntegrationData(Activity activity) {
        IntegrationData ret = new IntegrationData("Facebook", VERSION);
        ret.activities = new String[]{"com.facebook.ads.AudienceNetworkActivity"};
        return ret;
    }

    private FacebookAdapter(String providerName) {
        super(providerName);
        this.PLACEMENT_ID = "placementId";
        this.mAllBannerSmashes = new CopyOnWriteArrayList();
        this.mRewardedVideoPlacementToAdMap = new ConcurrentHashMap();
        this.mInterstitialPlacementToAdMap = new ConcurrentHashMap();
        this.mBannerPlacementToAdMap = new ConcurrentHashMap();
        this.mInterstitialAdListener = new C26341();
        this.mRewardedVideoAdListener = new C26352();
        AdSettings.setMediationService(MEDIATION_SERVICE_NAME);
    }

    public String getVersion() {
        return VERSION;
    }

    public String getCoreSDKVersion() {
        return "5.1.0";
    }

    public void initRewardedVideo(Activity activity, String appKey, String userId, JSONObject config, RewardedVideoSmashListener listener) {
        if (!TextUtils.isEmpty(config.optString("placementId"))) {
            this.mActivity = activity;
            if (!(TextUtils.isEmpty(config.optString("placementId")) || listener == null)) {
                this.mRewardedVideoPlacementToListenerMap.put(config.optString("placementId"), listener);
            }
            loadRewardedVideo(config.optString("placementId"));
        } else if (listener != null) {
            listener.onRewardedVideoAvailabilityChanged(false);
        }
    }

    public void fetchRewardedVideo(JSONObject config) {
        loadRewardedVideo(config.optString("placementId"));
    }

    public boolean isRewardedVideoAvailable(JSONObject config) {
        return this.mRewardedVideoPlacementToAdMap.get(config.optString("placementId")) != null && ((RewardedVideoAd) this.mRewardedVideoPlacementToAdMap.get(config.optString("placementId"))).isAdLoaded();
    }

    public void showRewardedVideo(final JSONObject config, final RewardedVideoSmashListener listener) {
        this.mActiveRewardedVideoSmash = listener;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                try {
                    RewardedVideoAd videoAd = (RewardedVideoAd) FacebookAdapter.this.mRewardedVideoPlacementToAdMap.get(config.optString("placementId"));
                    if (videoAd != null && videoAd.isAdLoaded()) {
                        if (!TextUtils.isEmpty(FacebookAdapter.this.getDynamicUserId())) {
                            videoAd.setRewardData(new RewardData(FacebookAdapter.this.getDynamicUserId(), "1"));
                        }
                        videoAd.show();
                    } else if (listener != null) {
                        listener.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
                    }
                    if (listener != null) {
                        listener.onRewardedVideoAvailabilityChanged(false);
                    }
                } catch (Exception ex) {
                    IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "Facebook show failed - " + ex.getMessage(), 3);
                    if (listener != null) {
                        listener.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
                        listener.onRewardedVideoAvailabilityChanged(false);
                    }
                }
            }
        });
    }

    public void onResume(Activity activity) {
        this.mActivity = activity;
        log(IronSourceTag.ADAPTER_API, getProviderName() + ":onResume()", 1);
    }

    public void onPause(Activity activity) {
        log(IronSourceTag.ADAPTER_API, getProviderName() + ":onPause()", 1);
    }

    public void initInterstitial(Activity activity, String appKey, String userId, JSONObject config, InterstitialSmashListener listener) {
        if (!TextUtils.isEmpty(config.optString("placementId"))) {
            this.mActivity = activity;
            if (!TextUtils.isEmpty(config.optString("placementId")) && listener != null) {
                this.mInterstitialPlacementToListenerMap.put(config.optString("placementId"), listener);
                listener.onInterstitialInitSuccess();
            }
        } else if (listener != null) {
            listener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Missing params", "Interstitial"));
        }
    }

    public void loadInterstitial(final JSONObject config, final InterstitialSmashListener listener) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                try {
                    if (TextUtils.isEmpty(config.optString("placementId"))) {
                        listener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("invalid placement"));
                        return;
                    }
                    InterstitialAd interstitialAd = new InterstitialAd(FacebookAdapter.this.mActivity, config.optString("placementId"));
                    interstitialAd.setAdListener(FacebookAdapter.this.mInterstitialAdListener);
                    interstitialAd.loadAd();
                    FacebookAdapter.this.mInterstitialPlacementToAdMap.put(config.optString("placementId"), interstitialAd);
                } catch (Exception e) {
                    if (listener != null) {
                        listener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError(e.getLocalizedMessage()));
                    }
                }
            }
        });
    }

    public void showInterstitial(final JSONObject config, final InterstitialSmashListener listener) {
        this.mActiveInterstitialSmash = listener;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                try {
                    if (FacebookAdapter.this.mInterstitialPlacementToAdMap.get(config.optString("placementId")) != null && ((InterstitialAd) FacebookAdapter.this.mInterstitialPlacementToAdMap.get(config.optString("placementId"))).isAdLoaded()) {
                        ((InterstitialAd) FacebookAdapter.this.mInterstitialPlacementToAdMap.get(config.optString("placementId"))).show();
                    } else if (listener != null) {
                        listener.onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
                    }
                } catch (Exception ex) {
                    IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "Facebook show failed - " + ex.getMessage(), 3);
                    if (listener != null) {
                        listener.onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
                    }
                }
            }
        });
    }

    public boolean isInterstitialReady(JSONObject config) {
        return this.mInterstitialPlacementToAdMap.get(config.optString("placementId")) != null && ((InterstitialAd) this.mInterstitialPlacementToAdMap.get(config.optString("placementId"))).isAdLoaded();
    }

    public void initBanners(Activity activity, String appKey, String userId, JSONObject config, BannerSmashListener listener) {
        this.mActivity = activity;
        if (listener != null) {
            listener.onBannerInitSuccess();
        }
    }

    private AdListener createBannerAdListener(final LayoutParams layoutParams) {
        return new AdListener() {
            public void onError(Ad ad, AdError adError) {
                IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "Banner Ad, onError; error: " + adError.getErrorMessage(), 1);
                if (FacebookAdapter.this.mBannerPlacementToListenerMap.containsKey(ad.getPlacementId())) {
                    ((BannerSmashListener) FacebookAdapter.this.mBannerPlacementToListenerMap.get(ad.getPlacementId())).onBannerAdLoadFailed(ErrorBuilder.buildLoadFailedError(adError.getErrorMessage()));
                }
            }

            public void onAdLoaded(Ad ad) {
                if (FacebookAdapter.this.mBannerPlacementToListenerMap.containsKey(ad.getPlacementId())) {
                    IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "Banner Ad, onAdLoaded", 1);
                    if (FacebookAdapter.this.mBannerPlacementToAdMap.get(ad.getPlacementId()) != null) {
                        ((BannerSmashListener) FacebookAdapter.this.mBannerPlacementToListenerMap.get(ad.getPlacementId())).onBannerAdLoaded((View) FacebookAdapter.this.mBannerPlacementToAdMap.get(ad.getPlacementId()), layoutParams);
                    }
                }
            }

            public void onAdClicked(Ad ad) {
                IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "Banner Ad, onAdClicked", 1);
                if (FacebookAdapter.this.mBannerPlacementToListenerMap.containsKey(ad.getPlacementId())) {
                    ((BannerSmashListener) FacebookAdapter.this.mBannerPlacementToListenerMap.get(ad.getPlacementId())).onBannerAdClicked();
                }
            }

            public void onLoggingImpression(Ad ad) {
                IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "Banner Ad, onLoggingImpression", 1);
            }
        };
    }

    public void loadBanner(IronSourceBannerLayout banner, JSONObject config, BannerSmashListener listener) {
        if (listener == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "Facebook loadBanner listener == null", 3);
        } else if (banner == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "Facebook loadBanner banner == null", 3);
        } else {
            final String placementId = config.optString("placementId");
            if (TextUtils.isEmpty(placementId)) {
                listener.onBannerAdLoadFailed(ErrorBuilder.buildLoadFailedError("FacebookAdapter loadBanner placementId is empty"));
                return;
            }
            final AdSize adSize = calculateBannerSize(banner.getSize(), AdapterUtils.isLargeScreen(banner.getActivity()));
            if (adSize == null) {
                listener.onBannerAdLoadFailed(ErrorBuilder.unsupportedBannerSize("Facebook"));
                return;
            }
            final IronSourceBannerLayout ironSourceBannerLayout = banner;
            final BannerSmashListener bannerSmashListener = listener;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    try {
                        AdView adView = new AdView(ironSourceBannerLayout.getActivity(), placementId, adSize);
                        adView.setAdListener(FacebookAdapter.this.createBannerAdListener(FacebookAdapter.this.calcLayoutParams(ironSourceBannerLayout.getSize(), ironSourceBannerLayout.getActivity())));
                        FacebookAdapter.this.mBannerPlacementToAdMap.put(placementId, adView);
                        FacebookAdapter.this.mBannerPlacementToListenerMap.put(placementId, bannerSmashListener);
                        adView.loadAd();
                    } catch (Exception e) {
                        bannerSmashListener.onBannerAdLoadFailed(ErrorBuilder.buildLoadFailedError("FacebookAdapter loadBanner exception " + e.getMessage()));
                    }
                }
            });
        }
    }

    public void destroyBanner(final JSONObject config) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                try {
                    if (FacebookAdapter.this.mBannerPlacementToAdMap.get(config.optString("placementId")) != null) {
                        ((AdView) FacebookAdapter.this.mBannerPlacementToAdMap.get(config.optString("placementId"))).destroy();
                        FacebookAdapter.this.mBannerPlacementToAdMap.remove(config.optString("placementId"));
                    }
                } catch (Exception e) {
                    FacebookAdapter.this.log(IronSourceTag.ADAPTER_API, FacebookAdapter.this.getProviderName() + ":destroyBanner() failed with an exception: " + e, 2);
                }
            }
        });
    }

    public void reloadBanner(final JSONObject config) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                if (FacebookAdapter.this.mBannerPlacementToAdMap.get(config.optString("placementId")) != null) {
                    ((AdView) FacebookAdapter.this.mBannerPlacementToAdMap.get(config.optString("placementId"))).loadAd();
                }
            }
        });
    }

    private void loadRewardedVideo(final String placement) {
        if (this.mActivity != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    try {
                        if (!TextUtils.isEmpty(placement)) {
                            RewardedVideoAd rewardedVideoAd = new RewardedVideoAd(FacebookAdapter.this.mActivity, placement);
                            rewardedVideoAd.setAdListener(FacebookAdapter.this.mRewardedVideoAdListener);
                            rewardedVideoAd.loadAd();
                            FacebookAdapter.this.mRewardedVideoPlacementToAdMap.put(placement, rewardedVideoAd);
                        }
                    } catch (Exception e) {
                        if (FacebookAdapter.this.mRewardedVideoPlacementToListenerMap.get(placement) != null) {
                            ((RewardedVideoSmashListener) FacebookAdapter.this.mRewardedVideoPlacementToListenerMap.get(placement)).onRewardedVideoAvailabilityChanged(false);
                        }
                    }
                }
            });
        }
    }

    private AdSize calculateBannerSize(ISBannerSize size, boolean isLargeScreen) {
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
                return AdSize.BANNER_HEIGHT_50;
            case 1:
                return AdSize.BANNER_HEIGHT_90;
            case 2:
                return AdSize.RECTANGLE_HEIGHT_250;
            case 3:
                return isLargeScreen ? AdSize.BANNER_HEIGHT_90 : AdSize.BANNER_HEIGHT_50;
            case 4:
                if (size.getHeight() == 50) {
                    return AdSize.BANNER_HEIGHT_50;
                }
                if (size.getHeight() == 90) {
                    return AdSize.BANNER_HEIGHT_90;
                }
                if (size.getHeight() == 250) {
                    return AdSize.RECTANGLE_HEIGHT_250;
                }
                break;
        }
        return null;
    }

    private LayoutParams calcLayoutParams(ISBannerSize size, Activity activity) {
        int widthDp = FetchService.ACTION_LOGGING;
        if (size.getDescription().equals("RECTANGLE")) {
            widthDp = Strategy.TTL_SECONDS_DEFAULT;
        } else if (size.getDescription().equals("SMART") && AdapterUtils.isLargeScreen(activity)) {
            widthDp = 728;
        }
        LayoutParams layoutParams = new LayoutParams(AdapterUtils.dpToPixels(activity, widthDp), -2);
        layoutParams.gravity = 17;
        return layoutParams;
    }
}
