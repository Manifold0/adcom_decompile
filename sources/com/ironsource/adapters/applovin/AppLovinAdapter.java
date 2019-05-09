package com.ironsource.adapters.applovin;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.FrameLayout.LayoutParams;
import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinPrivacySettings;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkSettings;
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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

class AppLovinAdapter extends AbstractAdapter {
    private static final String GitHash = "4e16816f9";
    private static final String SDK_KEY = "sdkKey";
    private static final String VERSION = "4.3.1";
    private static final String ZONE_ID = "zoneId";
    private Activity mActivity;
    private AppLovinSdk mAppLovinSdk;
    private Boolean mConsentCollectingUserData;
    private Boolean mDidInitSdk;
    private ConcurrentHashMap<String, ALBannerListener> mZoneIdToAppLovinListener;
    private ConcurrentHashMap<String, AppLovinAdView> mZoneIdToBannerAd;
    private ConcurrentHashMap<String, LayoutParams> mZoneIdToBannerLayout;
    private ConcurrentHashMap<String, BannerSmashListener> mZoneIdToBannerSmashListener;
    private ConcurrentHashMap<String, AppLovinAd> mZoneIdToIsAd;
    private ConcurrentHashMap<String, AppLovinInterstitialAdDialog> mZoneIdToIsAdDialog;
    private ConcurrentHashMap<String, InterstitialSmashListener> mZoneIdToIsListener;
    private ConcurrentHashMap<String, AppLovinIncentivizedInterstitial> mZoneIdToRvAd;
    private ConcurrentHashMap<String, RewardedVideoSmashListener> mZoneIdToRvListener;

    /* renamed from: com.ironsource.adapters.applovin.AppLovinAdapter$3 */
    class C13023 implements AppLovinAdRewardListener {
        C13023() {
        }

        public void userRewardVerified(AppLovinAd appLovinAd, Map<String, String> map) {
        }

        public void userOverQuota(AppLovinAd appLovinAd, Map<String, String> map) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "userOverQuota", 1);
        }

        public void userRewardRejected(AppLovinAd appLovinAd, Map<String, String> map) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "userRewardRejected", 1);
        }

        public void validationRequestFailed(AppLovinAd appLovinAd, int errorCode) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "validationRequestFailed " + AppLovinAdapter.this.getErrorString(errorCode) + "(" + errorCode + ")", 1);
        }

        public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "userDeclinedToViewAd", 1);
            String zoneId = AppLovinAdapter.this.getZoneId(appLovinAd);
            if (AppLovinAdapter.this.mZoneIdToRvListener.containsKey(zoneId)) {
                ((RewardedVideoSmashListener) AppLovinAdapter.this.mZoneIdToRvListener.get(zoneId)).onRewardedVideoAdClosed();
            }
        }
    }

    /* renamed from: com.ironsource.adapters.applovin.AppLovinAdapter$4 */
    class C13034 implements AppLovinAdVideoPlaybackListener {
        C13034() {
        }

        public void videoPlaybackBegan(AppLovinAd appLovinAd) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "videoPlaybackBegan", 1);
            String zoneId = AppLovinAdapter.this.getZoneId(appLovinAd);
            if (AppLovinAdapter.this.mZoneIdToRvListener.containsKey(zoneId)) {
                ((RewardedVideoSmashListener) AppLovinAdapter.this.mZoneIdToRvListener.get(zoneId)).onRewardedVideoAdStarted();
            }
        }

        public void videoPlaybackEnded(AppLovinAd appLovinAd, double percentViewed, boolean isFullyWatched) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "videoPlaybackEnded ; isFullyWatched: " + isFullyWatched, 1);
            String zoneId = AppLovinAdapter.this.getZoneId(appLovinAd);
            if (AppLovinAdapter.this.mZoneIdToRvListener.containsKey(zoneId)) {
                ((RewardedVideoSmashListener) AppLovinAdapter.this.mZoneIdToRvListener.get(zoneId)).onRewardedVideoAdEnded();
                if (isFullyWatched) {
                    ((RewardedVideoSmashListener) AppLovinAdapter.this.mZoneIdToRvListener.get(zoneId)).onRewardedVideoAdRewarded();
                }
            }
        }
    }

    /* renamed from: com.ironsource.adapters.applovin.AppLovinAdapter$5 */
    class C13055 implements AppLovinAdDisplayListener {
        C13055() {
        }

        public void adDisplayed(AppLovinAd appLovinAd) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "adDisplayed", 1);
            String zoneId = AppLovinAdapter.this.getZoneId(appLovinAd);
            if (AppLovinAdapter.this.mZoneIdToRvListener.containsKey(zoneId)) {
                ((RewardedVideoSmashListener) AppLovinAdapter.this.mZoneIdToRvListener.get(zoneId)).onRewardedVideoAdOpened();
            }
        }

        public void adHidden(AppLovinAd appLovinAd) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "adHidden", 1);
            final String zoneId = AppLovinAdapter.this.getZoneId(appLovinAd);
            if (AppLovinAdapter.this.mZoneIdToRvListener.containsKey(zoneId)) {
                ((RewardedVideoSmashListener) AppLovinAdapter.this.mZoneIdToRvListener.get(zoneId)).onRewardedVideoAvailabilityChanged(false);
                ((RewardedVideoSmashListener) AppLovinAdapter.this.mZoneIdToRvListener.get(zoneId)).onRewardedVideoAdClosed();
            }
            if (AppLovinAdapter.this.mZoneIdToRvAd.containsKey(zoneId)) {
                ((AppLovinIncentivizedInterstitial) AppLovinAdapter.this.mZoneIdToRvAd.get(zoneId)).preload(new AppLovinAdLoadListener() {
                    public void adReceived(AppLovinAd appLovinAd) {
                        if (AppLovinAdapter.this.mZoneIdToRvListener.containsKey(zoneId)) {
                            ((RewardedVideoSmashListener) AppLovinAdapter.this.mZoneIdToRvListener.get(zoneId)).onRewardedVideoAvailabilityChanged(true);
                        }
                    }

                    public void failedToReceiveAd(int i) {
                        if (AppLovinAdapter.this.mZoneIdToRvListener.containsKey(zoneId)) {
                            ((RewardedVideoSmashListener) AppLovinAdapter.this.mZoneIdToRvListener.get(zoneId)).onRewardedVideoAvailabilityChanged(false);
                        }
                    }
                });
            }
        }
    }

    /* renamed from: com.ironsource.adapters.applovin.AppLovinAdapter$6 */
    class C13066 implements AppLovinAdClickListener {
        C13066() {
        }

        public void adClicked(AppLovinAd appLovinAd) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "adClicked", 1);
            String zoneId = AppLovinAdapter.this.getZoneId(appLovinAd);
            if (AppLovinAdapter.this.mZoneIdToRvListener.containsKey(zoneId)) {
                ((RewardedVideoSmashListener) AppLovinAdapter.this.mZoneIdToRvListener.get(zoneId)).onRewardedVideoAdClicked();
            }
        }
    }

    private class ALBannerListener implements AppLovinAdLoadListener, AppLovinAdDisplayListener, AppLovinAdClickListener {
        private String mZoneId;

        ALBannerListener(String zoneId) {
            this.mZoneId = zoneId;
        }

        public void adClicked(AppLovinAd appLovinAd) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, AppLovinAdapter.this.getProviderName() + " Banner adClicked:  <" + this.mZoneId + ">", 1);
            BannerSmashListener listener = (BannerSmashListener) AppLovinAdapter.this.mZoneIdToBannerSmashListener.get(this.mZoneId);
            if (listener != null) {
                listener.onBannerAdClicked();
            }
        }

        public void adDisplayed(AppLovinAd appLovinAd) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, AppLovinAdapter.this.getProviderName() + " Banner adDisplayed: <" + this.mZoneId + ">", 1);
        }

        public void adHidden(AppLovinAd appLovinAd) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, AppLovinAdapter.this.getProviderName() + " Banner adHidden: <" + this.mZoneId + ">", 1);
        }

        public void adReceived(AppLovinAd appLovinAd) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, AppLovinAdapter.this.getProviderName() + " Banner adReceived: <" + this.mZoneId + ">", 1);
            final AppLovinAdView adView = (AppLovinAdView) AppLovinAdapter.this.mZoneIdToBannerAd.get(this.mZoneId);
            final LayoutParams layoutParams = (LayoutParams) AppLovinAdapter.this.mZoneIdToBannerLayout.get(this.mZoneId);
            final BannerSmashListener listener = (BannerSmashListener) AppLovinAdapter.this.mZoneIdToBannerSmashListener.get(this.mZoneId);
            if (adView == null || listener == null || layoutParams == null) {
                IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, AppLovinAdapter.this.getProviderName() + " adReceived: null parameter", 3);
                return;
            }
            final AppLovinAd appLovinAd2 = appLovinAd;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    adView.renderAd(appLovinAd2);
                    listener.onBannerAdLoaded(adView, layoutParams);
                }
            });
        }

        public void failedToReceiveAd(int errorCode) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, AppLovinAdapter.this.getProviderName() + " Banner failedToReceiveAd", 1);
            BannerSmashListener listener = (BannerSmashListener) AppLovinAdapter.this.mZoneIdToBannerSmashListener.get(this.mZoneId);
            if (listener != null) {
                listener.onBannerAdLoadFailed(ErrorBuilder.buildLoadFailedError(AppLovinAdapter.this.getErrorString(errorCode) + "(" + errorCode + ")"));
            }
        }
    }

    public static AppLovinAdapter startAdapter(String providerName) {
        return new AppLovinAdapter(providerName);
    }

    private AppLovinAdapter(String providerName) {
        super(providerName);
        this.mDidInitSdk = Boolean.valueOf(false);
        this.mConsentCollectingUserData = null;
        this.mAllBannerSmashes = new CopyOnWriteArrayList();
        this.mZoneIdToAppLovinListener = new ConcurrentHashMap();
        this.mZoneIdToBannerSmashListener = new ConcurrentHashMap();
        this.mZoneIdToBannerLayout = new ConcurrentHashMap();
        this.mZoneIdToBannerAd = new ConcurrentHashMap();
        this.mZoneIdToIsAd = new ConcurrentHashMap();
        this.mZoneIdToIsAdDialog = new ConcurrentHashMap();
        this.mZoneIdToRvAd = new ConcurrentHashMap();
        this.mZoneIdToIsListener = new ConcurrentHashMap();
        this.mZoneIdToRvListener = new ConcurrentHashMap();
    }

    public static IntegrationData getIntegrationData(Activity activity) {
        IntegrationData ret = new IntegrationData("AppLovin", VERSION);
        ret.activities = new String[]{"com.applovin.adview.AppLovinInterstitialActivity", "com.applovin.adview.AppLovinConfirmationActivity"};
        return ret;
    }

    public static String getAdapterSDKVersion() {
        String sdkVersion = null;
        try {
            return AppLovinSdk.VERSION;
        } catch (Exception e) {
            return sdkVersion;
        }
    }

    public String getVersion() {
        return VERSION;
    }

    public String getCoreSDKVersion() {
        return AppLovinSdk.VERSION;
    }

    public void onResume(Activity activity) {
        this.mActivity = activity;
    }

    protected void setConsent(boolean consent) {
        synchronized (this) {
            if (this.mDidInitSdk.booleanValue()) {
                AppLovinPrivacySettings.setHasUserConsent(consent, this.mActivity);
            } else {
                this.mConsentCollectingUserData = Boolean.valueOf(consent);
            }
        }
    }

    private void initSdk(Activity activity, String sdkKey) {
        synchronized (this) {
            if (!this.mDidInitSdk.booleanValue()) {
                AppLovinSdkSettings appLovinSdkSettings = new AppLovinSdkSettings();
                boolean isDebugEnabled = false;
                try {
                    isDebugEnabled = isAdaptersDebugEnabled();
                } catch (NoSuchMethodError e) {
                }
                appLovinSdkSettings.setVerboseLogging(isDebugEnabled);
                this.mAppLovinSdk = AppLovinSdk.getInstance(sdkKey, appLovinSdkSettings, activity);
                this.mAppLovinSdk.initializeSdk();
                this.mDidInitSdk = Boolean.valueOf(true);
                if (this.mConsentCollectingUserData != null) {
                    setConsent(this.mConsentCollectingUserData.booleanValue());
                }
            }
        }
    }

    public void initRewardedVideo(Activity activity, String appKey, String userId, JSONObject config, RewardedVideoSmashListener listener) {
        final String sdkKey = config.optString(SDK_KEY);
        if (!TextUtils.isEmpty(sdkKey)) {
            final String zoneId = getZoneId(config);
            this.mActivity = activity;
            if (listener != null) {
                this.mZoneIdToRvListener.put(zoneId, listener);
            }
            final Activity activity2 = activity;
            final String str = userId;
            final RewardedVideoSmashListener rewardedVideoSmashListener = listener;
            activity.runOnUiThread(new Runnable() {

                /* renamed from: com.ironsource.adapters.applovin.AppLovinAdapter$1$1 */
                class C12991 implements AppLovinAdLoadListener {
                    C12991() {
                    }

                    public void adReceived(AppLovinAd appLovinAd) {
                        if (rewardedVideoSmashListener != null) {
                            rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(true);
                        }
                    }

                    public void failedToReceiveAd(int i) {
                        if (rewardedVideoSmashListener != null) {
                            rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
                        }
                    }
                }

                public void run() {
                    AppLovinIncentivizedInterstitial ad;
                    AppLovinAdapter.this.initSdk(activity2, sdkKey);
                    if (TextUtils.isEmpty(zoneId)) {
                        ad = AppLovinIncentivizedInterstitial.create(AppLovinAdapter.this.mAppLovinSdk);
                    } else {
                        ad = AppLovinIncentivizedInterstitial.create(zoneId, AppLovinAdapter.this.mAppLovinSdk);
                    }
                    AppLovinAdapter.this.mZoneIdToRvAd.put(zoneId, ad);
                    ad.setUserIdentifier(str);
                    ad.preload(new C12991());
                }
            });
        } else if (listener != null) {
            listener.onRewardedVideoAvailabilityChanged(false);
        }
    }

    public void fetchRewardedVideo(JSONObject config) {
        log(IronSourceTag.ADAPTER_API, getProviderName() + ": in fetchRewardedVideo", 0);
        final String zoneId = getZoneId(config);
        if (this.mZoneIdToRvAd.containsKey(zoneId)) {
            ((AppLovinIncentivizedInterstitial) this.mZoneIdToRvAd.get(zoneId)).preload(new AppLovinAdLoadListener() {
                public void adReceived(AppLovinAd appLovinAd) {
                    if (AppLovinAdapter.this.mZoneIdToRvListener.containsKey(zoneId)) {
                        ((RewardedVideoSmashListener) AppLovinAdapter.this.mZoneIdToRvListener.get(zoneId)).onRewardedVideoAvailabilityChanged(true);
                    }
                }

                public void failedToReceiveAd(int i) {
                    if (AppLovinAdapter.this.mZoneIdToRvListener.containsKey(zoneId)) {
                        ((RewardedVideoSmashListener) AppLovinAdapter.this.mZoneIdToRvListener.get(zoneId)).onRewardedVideoAvailabilityChanged(false);
                    }
                }
            });
        }
    }

    public void showRewardedVideo(JSONObject config, RewardedVideoSmashListener listener) {
        String zoneId = getZoneId(config);
        if (listener == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "AppLovin showRewardedVideo error: null listener", 3);
        } else if (this.mZoneIdToRvAd.containsKey(zoneId) && ((AppLovinIncentivizedInterstitial) this.mZoneIdToRvAd.get(zoneId)).isAdReadyToDisplay()) {
            if (!TextUtils.isEmpty(getDynamicUserId())) {
                ((AppLovinIncentivizedInterstitial) this.mZoneIdToRvAd.get(zoneId)).setUserIdentifier(getDynamicUserId());
            }
            ((AppLovinIncentivizedInterstitial) this.mZoneIdToRvAd.get(zoneId)).show(this.mActivity, new C13023(), new C13034(), new C13055(), new C13066());
        } else {
            listener.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
            listener.onRewardedVideoAvailabilityChanged(false);
        }
    }

    public synchronized boolean isRewardedVideoAvailable(JSONObject config) {
        boolean z;
        String zoneId = getZoneId(config);
        z = this.mZoneIdToRvAd.containsKey(zoneId) && ((AppLovinIncentivizedInterstitial) this.mZoneIdToRvAd.get(zoneId)).isAdReadyToDisplay();
        return z;
    }

    public void initInterstitial(Activity activity, String appKey, String userId, JSONObject config, InterstitialSmashListener listener) {
        String sdkKey = config.optString(SDK_KEY);
        if (TextUtils.isEmpty(sdkKey)) {
            log(IronSourceTag.INTERNAL, getProviderName() + " initInterstitial empty sdkKey", 3);
            if (listener != null) {
                listener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("initInterstitial empty sdkKey", "Interstitial"));
                return;
            }
            return;
        }
        final String zoneId = getZoneId(config);
        this.mActivity = activity;
        initSdk(activity, sdkKey);
        AppLovinInterstitialAdDialog interstitialAd = AppLovinInterstitialAd.create(this.mAppLovinSdk, activity);
        this.mZoneIdToIsAdDialog.put(zoneId, interstitialAd);
        if (listener != null) {
            this.mZoneIdToIsListener.put(zoneId, listener);
        }
        interstitialAd.setAdClickListener(new AppLovinAdClickListener() {
            public void adClicked(AppLovinAd appLovinAd) {
                if (AppLovinAdapter.this.mZoneIdToIsListener.containsKey(zoneId)) {
                    ((InterstitialSmashListener) AppLovinAdapter.this.mZoneIdToIsListener.get(zoneId)).onInterstitialAdClicked();
                }
            }
        });
        interstitialAd.setAdDisplayListener(new AppLovinAdDisplayListener() {
            public void adDisplayed(AppLovinAd appLovinAd) {
                if (AppLovinAdapter.this.mZoneIdToIsListener.containsKey(zoneId)) {
                    ((InterstitialSmashListener) AppLovinAdapter.this.mZoneIdToIsListener.get(zoneId)).onInterstitialAdOpened();
                    ((InterstitialSmashListener) AppLovinAdapter.this.mZoneIdToIsListener.get(zoneId)).onInterstitialAdShowSucceeded();
                }
            }

            public void adHidden(AppLovinAd appLovinAd) {
                if (AppLovinAdapter.this.mZoneIdToIsListener.containsKey(zoneId)) {
                    ((InterstitialSmashListener) AppLovinAdapter.this.mZoneIdToIsListener.get(zoneId)).onInterstitialAdClosed();
                }
            }
        });
        if (listener != null) {
            listener.onInterstitialInitSuccess();
        }
    }

    public void loadInterstitial(JSONObject config, final InterstitialSmashListener listener) {
        final String zoneId = getZoneId(config);
        if (TextUtils.isEmpty(zoneId)) {
            this.mAppLovinSdk.getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                public void adReceived(AppLovinAd appLovinAd) {
                    IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "Interstitial adReceived", 1);
                    if (listener != null) {
                        listener.onInterstitialAdReady();
                    }
                    AppLovinAdapter.this.mZoneIdToIsAd.put(zoneId, appLovinAd);
                }

                public void failedToReceiveAd(int errorCode) {
                    if (listener != null) {
                        listener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError(AppLovinAdapter.this.getErrorString(errorCode) + "( " + errorCode + " )"));
                    }
                }
            });
        } else {
            this.mAppLovinSdk.getAdService().loadNextAdForZoneId(zoneId, new AppLovinAdLoadListener() {
                public void adReceived(AppLovinAd appLovinAd) {
                    IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "Interstitial adReceived zoneId=" + AppLovinAdapter.this.getZoneId(appLovinAd), 1);
                    if (listener != null) {
                        listener.onInterstitialAdReady();
                    }
                    AppLovinAdapter.this.mZoneIdToIsAd.put(zoneId, appLovinAd);
                }

                public void failedToReceiveAd(int errorCode) {
                    if (listener != null) {
                        listener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError(AppLovinAdapter.this.getErrorString(errorCode) + "( " + errorCode + " )"));
                    }
                }
            });
        }
    }

    public void showInterstitial(JSONObject config, InterstitialSmashListener listener) {
        String zoneId = getZoneId(config);
        if (this.mZoneIdToIsAd.containsKey(zoneId) && this.mZoneIdToIsAdDialog.containsKey(zoneId)) {
            ((AppLovinInterstitialAdDialog) this.mZoneIdToIsAdDialog.get(zoneId)).showAndRender((AppLovinAd) this.mZoneIdToIsAd.get(zoneId));
        } else if (listener != null) {
            listener.onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
        }
    }

    public boolean isInterstitialReady(JSONObject config) {
        return this.mZoneIdToIsAdDialog.containsKey(getZoneId(config));
    }

    public void initBanners(final Activity activity, String appKey, String userId, JSONObject config, final BannerSmashListener listener) {
        if (listener == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " initBanners listener == null", 3);
        }
        if (config == null || activity == null) {
            listener.onBannerInitFailed(ErrorBuilder.buildInitFailedError("null parameters", IronSourceConstants.BANNER_AD_UNIT));
            return;
        }
        final String sdkKey = config.optString(SDK_KEY);
        if (TextUtils.isEmpty(sdkKey)) {
            listener.onBannerInitFailed(ErrorBuilder.buildInitFailedError("Missing params", IronSourceConstants.BANNER_AD_UNIT));
            return;
        }
        this.mActivity = activity;
        activity.runOnUiThread(new Runnable() {
            public void run() {
                AppLovinAdapter.this.initSdk(activity, sdkKey);
                listener.onBannerInitSuccess();
            }
        });
    }

    public void loadBanner(IronSourceBannerLayout banner, JSONObject config, BannerSmashListener listener) {
        if (listener == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " loadBanner listener == null", 3);
        } else if (banner == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " loadBanner banner == null", 3);
        } else {
            final AppLovinAdSize bannerSize = calculateBannerSize(banner.getSize(), AdapterUtils.isLargeScreen(banner.getActivity()));
            if (bannerSize == null) {
                listener.onBannerAdLoadFailed(ErrorBuilder.unsupportedBannerSize(getProviderName()));
                return;
            }
            final String zoneId = getZoneId(config);
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " loadBanner: " + getProviderName() + ", zoneID <" + zoneId + ">", 1);
            final IronSourceBannerLayout ironSourceBannerLayout = banner;
            final BannerSmashListener bannerSmashListener = listener;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    try {
                        LayoutParams layoutParams;
                        AppLovinAdView adView;
                        if (TextUtils.isEmpty(zoneId)) {
                            layoutParams = AppLovinAdapter.this.calcLayoutParams(ironSourceBannerLayout.getSize(), bannerSize, ironSourceBannerLayout.getActivity());
                            adView = new AppLovinAdView(AppLovinAdapter.this.mAppLovinSdk, bannerSize, ironSourceBannerLayout.getActivity());
                        } else {
                            layoutParams = AppLovinAdapter.this.calcLayoutParams(ISBannerSize.BANNER, AppLovinAdSize.BANNER, ironSourceBannerLayout.getActivity());
                            adView = new AppLovinAdView(AppLovinAdapter.this.mAppLovinSdk, AppLovinAdSize.BANNER, ironSourceBannerLayout.getActivity());
                        }
                        ALBannerListener applovinListener = new ALBannerListener(zoneId);
                        adView.setAdLoadListener(applovinListener);
                        adView.setAdClickListener(applovinListener);
                        adView.setAdDisplayListener(applovinListener);
                        AppLovinAdapter.this.mZoneIdToBannerSmashListener.put(zoneId, bannerSmashListener);
                        AppLovinAdapter.this.mZoneIdToBannerAd.put(zoneId, adView);
                        AppLovinAdapter.this.mZoneIdToBannerLayout.put(zoneId, layoutParams);
                        AppLovinAdapter.this.mZoneIdToAppLovinListener.put(zoneId, applovinListener);
                        if (TextUtils.isEmpty(zoneId)) {
                            adView.loadNextAd();
                        } else {
                            AppLovinAdapter.this.mAppLovinSdk.getAdService().loadNextAdForZoneId(zoneId, applovinListener);
                        }
                    } catch (Exception e) {
                        bannerSmashListener.onBannerAdLoadFailed(ErrorBuilder.buildLoadFailedError(AppLovinAdapter.this.getProviderName() + " loadBanner exception " + e.getMessage()));
                    }
                }
            });
        }
    }

    private AppLovinAdSize calculateBannerSize(ISBannerSize size, boolean isLargeScreen) {
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
            case 1:
                return AppLovinAdSize.BANNER;
            case 2:
                return AppLovinAdSize.MREC;
            case 3:
                return isLargeScreen ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
            case 4:
                if (size.getHeight() >= 40 && size.getHeight() <= 60) {
                    return AppLovinAdSize.BANNER;
                }
        }
        return null;
    }

    private LayoutParams calcLayoutParams(ISBannerSize isSize, AppLovinAdSize appLovinSize, Activity activity) {
        int widthDp = FetchService.ACTION_LOGGING;
        if (isSize.getDescription().equals("RECTANGLE")) {
            widthDp = Strategy.TTL_SECONDS_DEFAULT;
        } else if (isSize.getDescription().equals("SMART") && AdapterUtils.isLargeScreen(activity)) {
            widthDp = 728;
        }
        return new LayoutParams(AdapterUtils.dpToPixels(activity, widthDp), AdapterUtils.dpToPixels(activity, appLovinSize.getHeight()), 17);
    }

    public void destroyBanner(JSONObject config) {
        String zoneId = getZoneId(config);
        AppLovinAdView adView = (AppLovinAdView) this.mZoneIdToBannerAd.get(zoneId);
        if (adView != null) {
            adView.destroy();
        }
        if (this.mZoneIdToBannerAd != null) {
            this.mZoneIdToBannerAd.remove(zoneId);
        }
    }

    public void reloadBanner(JSONObject config) {
        final String zoneId = getZoneId(config);
        final AppLovinAdView adView = (AppLovinAdView) this.mZoneIdToBannerAd.get(zoneId);
        final ALBannerListener applovinListener = (ALBannerListener) this.mZoneIdToAppLovinListener.get(zoneId);
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + "Banner reloadBanner: <" + zoneId + ">", 1);
        if (adView == null || applovinListener == null) {
            log(IronSourceTag.ADAPTER_API, getProviderName() + ":reloadBanner() failed, null parameters", 2);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (TextUtils.isEmpty(zoneId)) {
                        adView.loadNextAd();
                    } else {
                        AppLovinAdapter.this.mAppLovinSdk.getAdService().loadNextAdForZoneId(zoneId, applovinListener);
                    }
                }
            });
        }
    }

    private String getErrorString(int errorCode) {
        switch (errorCode) {
            case AppLovinErrorCodes.INCENTIVIZED_USER_CLOSED_VIDEO /*-600*/:
                return "User closed video before reward";
            case AppLovinErrorCodes.INCENTIVIZED_SERVER_TIMEOUT /*-500*/:
                return "Server timeout";
            case AppLovinErrorCodes.INCENTIVIZED_UNKNOWN_SERVER_ERROR /*-400*/:
                return "Unknown server error";
            case AppLovinErrorCodes.INCENTIVIZED_NO_AD_PRELOADED /*-300*/:
                return "No ad pre-loaded";
            case AppLovinErrorCodes.NO_NETWORK /*-103*/:
                return "No network available";
            case -102:
                return "Ad fetch timeout";
            case AppLovinErrorCodes.UNABLE_TO_RENDER_AD /*-6*/:
                return "Unable to render ad";
            case -1:
                return "Unspecified error";
            case AppLovinErrorCodes.NO_FILL /*204*/:
                return "No fill";
            default:
                return "Unknown error";
        }
    }

    private String getZoneId(JSONObject config) {
        return !TextUtils.isEmpty(config.optString(ZONE_ID)) ? config.optString(ZONE_ID) : "";
    }

    private String getZoneId(AppLovinAd ad) {
        return ad.getZoneId() != null ? ad.getZoneId() : "";
    }
}
