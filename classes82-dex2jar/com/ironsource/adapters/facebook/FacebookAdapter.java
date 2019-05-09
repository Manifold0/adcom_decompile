// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.adapters.facebook;

import com.facebook.ads.RewardData;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import org.json.JSONObject;
import android.content.Context;
import android.text.TextUtils;
import android.os.Handler;
import android.os.Looper;
import com.ironsource.mediationsdk.IntegrationData;
import android.view.View;
import com.ironsource.mediationsdk.sdk.BannerSmashListener;
import com.facebook.ads.AdSize;
import com.ironsource.mediationsdk.AdapterUtils;
import com.facebook.ads.AdListener;
import android.widget.FrameLayout$LayoutParams;
import com.ironsource.mediationsdk.ISBannerSize;
import com.facebook.ads.AdSettings;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.facebook.ads.AdError;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.ironsource.mediationsdk.logger.IronSourceLogger$IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.facebook.ads.Ad;
import java.util.concurrent.CopyOnWriteArrayList;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.AdView;
import java.util.concurrent.ConcurrentHashMap;
import android.app.Activity;
import com.ironsource.mediationsdk.AbstractAdapter;

public class FacebookAdapter extends AbstractAdapter
{
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
    
    private FacebookAdapter(final String s) {
        super(s);
        this.PLACEMENT_ID = "placementId";
        this.mAllBannerSmashes = new CopyOnWriteArrayList();
        this.mRewardedVideoPlacementToAdMap = new ConcurrentHashMap<String, RewardedVideoAd>();
        this.mInterstitialPlacementToAdMap = new ConcurrentHashMap<String, InterstitialAd>();
        this.mBannerPlacementToAdMap = new ConcurrentHashMap<String, AdView>();
        this.mInterstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onAdClicked(final Ad ad) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "IS Ad, onAdClicked", 1);
                if (FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId()) != null) {
                    FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId()).onInterstitialAdClicked();
                }
            }
            
            @Override
            public void onAdLoaded(final Ad ad) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "IS Ad, onAdLoaded", 1);
                if (FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId()) != null) {
                    FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId()).onInterstitialAdReady();
                }
            }
            
            @Override
            public void onError(final Ad ad, final AdError adError) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "IS Ad, onError; error: " + adError.getErrorMessage(), 1);
                if (FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId()) != null) {
                    FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId()).onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError(adError.getErrorMessage()));
                }
            }
            
            @Override
            public void onInterstitialDismissed(final Ad ad) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onInterstitialDismissed", 1);
                if (FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId()) != null) {
                    FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId()).onInterstitialAdClosed();
                }
            }
            
            @Override
            public void onInterstitialDisplayed(final Ad ad) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onInterstitialDisplayed", 1);
                if (FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId()) != null) {
                    FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId()).onInterstitialAdOpened();
                }
            }
            
            @Override
            public void onLoggingImpression(final Ad ad) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "IS Ad, onLoggingImpression", 1);
                if (FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId()) != null) {
                    FacebookAdapter.this.mInterstitialPlacementToListenerMap.get(ad.getPlacementId()).onInterstitialAdShowSucceeded();
                }
            }
        };
        this.mRewardedVideoAdListener = new RewardedVideoAdListener() {
            @Override
            public void onAdClicked(final Ad ad) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "RV Ad, onAdClicked", 1);
                if (FacebookAdapter.this.mRewardedVideoPlacementToListenerMap.get(ad.getPlacementId()) != null) {
                    FacebookAdapter.this.mRewardedVideoPlacementToListenerMap.get(ad.getPlacementId()).onRewardedVideoAdClicked();
                }
            }
            
            @Override
            public void onAdLoaded(final Ad ad) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "RV Ad, onAdLoaded", 1);
                if (FacebookAdapter.this.mRewardedVideoPlacementToListenerMap.get(ad.getPlacementId()) != null) {
                    FacebookAdapter.this.mRewardedVideoPlacementToListenerMap.get(ad.getPlacementId()).onRewardedVideoAvailabilityChanged(true);
                }
            }
            
            @Override
            public void onError(final Ad ad, final AdError adError) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "RV Ad, onError; error: " + adError.getErrorMessage(), 1);
                if (FacebookAdapter.this.mRewardedVideoPlacementToListenerMap.get(ad.getPlacementId()) != null) {
                    FacebookAdapter.this.mRewardedVideoPlacementToListenerMap.get(ad.getPlacementId()).onRewardedVideoAvailabilityChanged(false);
                }
            }
            
            @Override
            public void onLoggingImpression(final Ad ad) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "RV Ad, onLoggingImpression", 1);
                if (FacebookAdapter.this.mActiveRewardedVideoSmash != null) {
                    FacebookAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdOpened();
                    FacebookAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdStarted();
                }
            }
            
            @Override
            public void onRewardedVideoClosed() {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onRewardedVideoClosed", 1);
                if (FacebookAdapter.this.mActiveRewardedVideoSmash != null) {
                    FacebookAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdClosed();
                }
            }
            
            @Override
            public void onRewardedVideoCompleted() {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onRewardedVideoCompleted", 1);
                if (FacebookAdapter.this.mActiveRewardedVideoSmash != null) {
                    FacebookAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdEnded();
                    FacebookAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdRewarded();
                }
            }
        };
        AdSettings.setMediationService("Supersonic");
    }
    
    static /* synthetic */ String access$2700(final FacebookAdapter facebookAdapter) {
        return facebookAdapter.getDynamicUserId();
    }
    
    static /* synthetic */ String access$2800(final FacebookAdapter facebookAdapter) {
        return facebookAdapter.getDynamicUserId();
    }
    
    static /* synthetic */ void access$4200(final FacebookAdapter facebookAdapter, final IronSourceLogger$IronSourceTag ironSourceLogger$IronSourceTag, final String s, final int n) {
        facebookAdapter.log(ironSourceLogger$IronSourceTag, s, n);
    }
    
    private FrameLayout$LayoutParams calcLayoutParams(final ISBannerSize isBannerSize, final Activity activity) {
        final int n = 320;
        int n2;
        if (isBannerSize.getDescription().equals("RECTANGLE")) {
            n2 = 300;
        }
        else {
            n2 = n;
            if (isBannerSize.getDescription().equals("SMART")) {
                n2 = n;
                if (AdapterUtils.isLargeScreen(activity)) {
                    n2 = 728;
                }
            }
        }
        final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(AdapterUtils.dpToPixels(activity, n2), -2);
        frameLayout$LayoutParams.gravity = 17;
        return frameLayout$LayoutParams;
    }
    
    private AdSize calculateBannerSize(final ISBannerSize isBannerSize, final boolean b) {
        final String description = isBannerSize.getDescription();
        switch (description) {
            case "BANNER": {
                return AdSize.BANNER_HEIGHT_50;
            }
            case "LARGE": {
                return AdSize.BANNER_HEIGHT_90;
            }
            case "RECTANGLE": {
                return AdSize.RECTANGLE_HEIGHT_250;
            }
            case "SMART": {
                if (b) {
                    return AdSize.BANNER_HEIGHT_90;
                }
                return AdSize.BANNER_HEIGHT_50;
            }
            case "CUSTOM": {
                if (isBannerSize.getHeight() == 50) {
                    return AdSize.BANNER_HEIGHT_50;
                }
                if (isBannerSize.getHeight() == 90) {
                    return AdSize.BANNER_HEIGHT_90;
                }
                if (isBannerSize.getHeight() == 250) {
                    return AdSize.RECTANGLE_HEIGHT_250;
                }
                break;
            }
        }
        return null;
    }
    
    private AdListener createBannerAdListener(final FrameLayout$LayoutParams frameLayout$LayoutParams) {
        return new AdListener() {
            @Override
            public void onAdClicked(final Ad ad) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "Banner Ad, onAdClicked", 1);
                if (FacebookAdapter.this.mBannerPlacementToListenerMap.containsKey(ad.getPlacementId())) {
                    FacebookAdapter.this.mBannerPlacementToListenerMap.get(ad.getPlacementId()).onBannerAdClicked();
                }
            }
            
            @Override
            public void onAdLoaded(final Ad ad) {
                if (FacebookAdapter.this.mBannerPlacementToListenerMap.containsKey(ad.getPlacementId())) {
                    IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "Banner Ad, onAdLoaded", 1);
                    if (FacebookAdapter.this.mBannerPlacementToAdMap.get(ad.getPlacementId()) != null) {
                        FacebookAdapter.this.mBannerPlacementToListenerMap.get(ad.getPlacementId()).onBannerAdLoaded((View)FacebookAdapter.this.mBannerPlacementToAdMap.get(ad.getPlacementId()), frameLayout$LayoutParams);
                    }
                }
            }
            
            @Override
            public void onError(final Ad ad, final AdError adError) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "Banner Ad, onError; error: " + adError.getErrorMessage(), 1);
                if (FacebookAdapter.this.mBannerPlacementToListenerMap.containsKey(ad.getPlacementId())) {
                    FacebookAdapter.this.mBannerPlacementToListenerMap.get(ad.getPlacementId()).onBannerAdLoadFailed(ErrorBuilder.buildLoadFailedError(adError.getErrorMessage()));
                }
            }
            
            @Override
            public void onLoggingImpression(final Ad ad) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "Banner Ad, onLoggingImpression", 1);
            }
        };
    }
    
    public static IntegrationData getIntegrationData(final Activity activity) {
        final IntegrationData integrationData = new IntegrationData("Facebook", "4.3.1");
        integrationData.activities = new String[] { "com.facebook.ads.AudienceNetworkActivity" };
        return integrationData;
    }
    
    private void loadRewardedVideo(final String s) {
        if (this.mActivity != null) {
            new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        if (!TextUtils.isEmpty((CharSequence)s)) {
                            final RewardedVideoAd rewardedVideoAd = new RewardedVideoAd((Context)FacebookAdapter.this.mActivity, s);
                            rewardedVideoAd.setAdListener(FacebookAdapter.this.mRewardedVideoAdListener);
                            rewardedVideoAd.loadAd();
                            FacebookAdapter.this.mRewardedVideoPlacementToAdMap.put(s, rewardedVideoAd);
                        }
                    }
                    catch (Exception ex) {
                        if (FacebookAdapter.this.mRewardedVideoPlacementToListenerMap.get(s) != null) {
                            FacebookAdapter.this.mRewardedVideoPlacementToListenerMap.get(s).onRewardedVideoAvailabilityChanged(false);
                        }
                    }
                }
            });
        }
    }
    
    public static FacebookAdapter startAdapter(final String s) {
        return new FacebookAdapter(s);
    }
    
    public void destroyBanner(final JSONObject jsonObject) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    if (FacebookAdapter.this.mBannerPlacementToAdMap.get(jsonObject.optString("placementId")) != null) {
                        FacebookAdapter.this.mBannerPlacementToAdMap.get(jsonObject.optString("placementId")).destroy();
                        FacebookAdapter.this.mBannerPlacementToAdMap.remove(jsonObject.optString("placementId"));
                    }
                }
                catch (Exception ex) {
                    FacebookAdapter.access$4200(FacebookAdapter.this, IronSourceLogger$IronSourceTag.ADAPTER_API, FacebookAdapter.this.getProviderName() + ":destroyBanner() failed with an exception: " + ex, 2);
                }
            }
        });
    }
    
    public void fetchRewardedVideo(final JSONObject jsonObject) {
        this.loadRewardedVideo(jsonObject.optString("placementId"));
    }
    
    public String getCoreSDKVersion() {
        return "5.1.0";
    }
    
    public String getVersion() {
        return "4.3.1";
    }
    
    public void initBanners(final Activity mActivity, final String s, final String s2, final JSONObject jsonObject, final BannerSmashListener bannerSmashListener) {
        this.mActivity = mActivity;
        if (bannerSmashListener != null) {
            bannerSmashListener.onBannerInitSuccess();
        }
    }
    
    public void initInterstitial(final Activity mActivity, final String s, final String s2, final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        if (TextUtils.isEmpty((CharSequence)jsonObject.optString("placementId"))) {
            if (interstitialSmashListener != null) {
                interstitialSmashListener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Missing params", "Interstitial"));
            }
        }
        else {
            this.mActivity = mActivity;
            if (!TextUtils.isEmpty((CharSequence)jsonObject.optString("placementId")) && interstitialSmashListener != null) {
                this.mInterstitialPlacementToListenerMap.put(jsonObject.optString("placementId"), interstitialSmashListener);
                interstitialSmashListener.onInterstitialInitSuccess();
            }
        }
    }
    
    public void initRewardedVideo(final Activity mActivity, final String s, final String s2, final JSONObject jsonObject, final RewardedVideoSmashListener rewardedVideoSmashListener) {
        if (TextUtils.isEmpty((CharSequence)jsonObject.optString("placementId"))) {
            if (rewardedVideoSmashListener != null) {
                rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
            }
            return;
        }
        this.mActivity = mActivity;
        if (!TextUtils.isEmpty((CharSequence)jsonObject.optString("placementId")) && rewardedVideoSmashListener != null) {
            this.mRewardedVideoPlacementToListenerMap.put(jsonObject.optString("placementId"), rewardedVideoSmashListener);
        }
        this.loadRewardedVideo(jsonObject.optString("placementId"));
    }
    
    public boolean isInterstitialReady(final JSONObject jsonObject) {
        return this.mInterstitialPlacementToAdMap.get(jsonObject.optString("placementId")) != null && this.mInterstitialPlacementToAdMap.get(jsonObject.optString("placementId")).isAdLoaded();
    }
    
    public boolean isRewardedVideoAvailable(final JSONObject jsonObject) {
        return this.mRewardedVideoPlacementToAdMap.get(jsonObject.optString("placementId")) != null && this.mRewardedVideoPlacementToAdMap.get(jsonObject.optString("placementId")).isAdLoaded();
    }
    
    public void loadBanner(final IronSourceBannerLayout ironSourceBannerLayout, final JSONObject jsonObject, final BannerSmashListener bannerSmashListener) {
        if (bannerSmashListener == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "Facebook loadBanner listener == null", 3);
            return;
        }
        if (ironSourceBannerLayout == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "Facebook loadBanner banner == null", 3);
            return;
        }
        final String optString = jsonObject.optString("placementId");
        if (TextUtils.isEmpty((CharSequence)optString)) {
            bannerSmashListener.onBannerAdLoadFailed(ErrorBuilder.buildLoadFailedError("FacebookAdapter loadBanner placementId is empty"));
            return;
        }
        final AdSize calculateBannerSize = this.calculateBannerSize(ironSourceBannerLayout.getSize(), AdapterUtils.isLargeScreen(ironSourceBannerLayout.getActivity()));
        if (calculateBannerSize == null) {
            bannerSmashListener.onBannerAdLoadFailed(ErrorBuilder.unsupportedBannerSize("Facebook"));
            return;
        }
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    final AdView adView = new AdView((Context)ironSourceBannerLayout.getActivity(), optString, calculateBannerSize);
                    adView.setAdListener(FacebookAdapter.this.createBannerAdListener(FacebookAdapter.this.calcLayoutParams(ironSourceBannerLayout.getSize(), ironSourceBannerLayout.getActivity())));
                    FacebookAdapter.this.mBannerPlacementToAdMap.put(optString, adView);
                    FacebookAdapter.this.mBannerPlacementToListenerMap.put(optString, bannerSmashListener);
                    adView.loadAd();
                }
                catch (Exception ex) {
                    bannerSmashListener.onBannerAdLoadFailed(ErrorBuilder.buildLoadFailedError("FacebookAdapter loadBanner exception " + ex.getMessage()));
                }
            }
        });
    }
    
    public void loadInterstitial(final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    if (!TextUtils.isEmpty((CharSequence)jsonObject.optString("placementId"))) {
                        final InterstitialAd interstitialAd = new InterstitialAd((Context)FacebookAdapter.this.mActivity, jsonObject.optString("placementId"));
                        interstitialAd.setAdListener(FacebookAdapter.this.mInterstitialAdListener);
                        interstitialAd.loadAd();
                        FacebookAdapter.this.mInterstitialPlacementToAdMap.put(jsonObject.optString("placementId"), interstitialAd);
                        return;
                    }
                    interstitialSmashListener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("invalid placement"));
                }
                catch (Exception ex) {
                    if (interstitialSmashListener != null) {
                        interstitialSmashListener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError(ex.getLocalizedMessage()));
                    }
                }
            }
        });
    }
    
    public void onPause(final Activity activity) {
        this.log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + ":onPause()", 1);
    }
    
    public void onResume(final Activity mActivity) {
        this.mActivity = mActivity;
        this.log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + ":onResume()", 1);
    }
    
    public void reloadBanner(final JSONObject jsonObject) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (FacebookAdapter.this.mBannerPlacementToAdMap.get(jsonObject.optString("placementId")) != null) {
                    FacebookAdapter.this.mBannerPlacementToAdMap.get(jsonObject.optString("placementId")).loadAd();
                }
            }
        });
    }
    
    public void showInterstitial(final JSONObject jsonObject, final InterstitialSmashListener mActiveInterstitialSmash) {
        this.mActiveInterstitialSmash = mActiveInterstitialSmash;
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    if (FacebookAdapter.this.mInterstitialPlacementToAdMap.get(jsonObject.optString("placementId")) != null && FacebookAdapter.this.mInterstitialPlacementToAdMap.get(jsonObject.optString("placementId")).isAdLoaded()) {
                        FacebookAdapter.this.mInterstitialPlacementToAdMap.get(jsonObject.optString("placementId")).show();
                        return;
                    }
                    if (mActiveInterstitialSmash != null) {
                        mActiveInterstitialSmash.onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
                    }
                }
                catch (Exception ex) {
                    IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "Facebook show failed - " + ex.getMessage(), 3);
                    if (mActiveInterstitialSmash != null) {
                        mActiveInterstitialSmash.onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
                    }
                }
            }
        });
    }
    
    public void showRewardedVideo(final JSONObject jsonObject, final RewardedVideoSmashListener mActiveRewardedVideoSmash) {
        this.mActiveRewardedVideoSmash = mActiveRewardedVideoSmash;
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    final RewardedVideoAd rewardedVideoAd = FacebookAdapter.this.mRewardedVideoPlacementToAdMap.get(jsonObject.optString("placementId"));
                    if (rewardedVideoAd != null && rewardedVideoAd.isAdLoaded()) {
                        if (!TextUtils.isEmpty((CharSequence)FacebookAdapter.access$2700(FacebookAdapter.this))) {
                            rewardedVideoAd.setRewardData(new RewardData(FacebookAdapter.access$2800(FacebookAdapter.this), "1"));
                        }
                        rewardedVideoAd.show();
                    }
                    else if (mActiveRewardedVideoSmash != null) {
                        mActiveRewardedVideoSmash.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Rewarded Video"));
                    }
                    if (mActiveRewardedVideoSmash != null) {
                        mActiveRewardedVideoSmash.onRewardedVideoAvailabilityChanged(false);
                    }
                }
                catch (Exception ex) {
                    IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "Facebook show failed - " + ex.getMessage(), 3);
                    if (mActiveRewardedVideoSmash != null) {
                        mActiveRewardedVideoSmash.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Rewarded Video"));
                        mActiveRewardedVideoSmash.onRewardedVideoAvailabilityChanged(false);
                    }
                }
            }
        });
    }
}
