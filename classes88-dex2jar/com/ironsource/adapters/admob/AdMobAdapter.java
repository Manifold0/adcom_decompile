// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.adapters.admob;

import com.ironsource.mediationsdk.AdapterUtils;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.ads.MobileAds;
import android.text.TextUtils;
import com.ironsource.mediationsdk.IntegrationData;
import com.google.android.gms.ads.AdSize;
import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.sdk.BannerSmashListener;
import android.os.Bundle;
import com.google.android.gms.ads.AdRequest$Builder;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdListener;
import org.json.JSONObject;
import android.app.Activity;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Iterator;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import com.ironsource.mediationsdk.logger.IronSourceLogger$IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdView;
import java.util.concurrent.ConcurrentHashMap;
import com.ironsource.mediationsdk.AbstractAdapter;

public class AdMobAdapter extends AbstractAdapter
{
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
    
    private AdMobAdapter(final String s) {
        super(s);
        this.IRONSOURCE_REQUEST_AGENT = "ironSource";
        this.mAge = -1;
        this.mIsRewardedVideoReady = false;
        this.mDidInitSdk = false;
        this.AD_UNIT_ID = "adUnitId";
        this.APP_ID = "appId";
        this.mConsent = null;
        this.rewardedVideoAdListener = (RewardedVideoAdListener)new RewardedVideoAdListener() {
            public void onRewarded(final RewardItem rewardItem) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onRewarded", 1);
                if (AdMobAdapter.this.mActiveRewardedVideoSmash != null) {
                    AdMobAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdRewarded();
                }
            }
            
            public void onRewardedVideoAdClosed() {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onRewardedVideoAdClosed", 1);
                if (AdMobAdapter.this.mActiveRewardedVideoSmash != null) {
                    AdMobAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdClosed();
                }
            }
            
            public void onRewardedVideoAdFailedToLoad(final int n) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onRewardedVideoAdFailedToLoad", 1);
                AdMobAdapter.this.mIsRewardedVideoReady = false;
                for (final RewardedVideoSmashListener rewardedVideoSmashListener : AdMobAdapter.this.mAllRewardedVideoSmashes) {
                    if (rewardedVideoSmashListener != null) {
                        rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
                    }
                }
            }
            
            public void onRewardedVideoAdLeftApplication() {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onRewardedVideoAdLeftApplication", 1);
                if (AdMobAdapter.this.mActiveRewardedVideoSmash != null) {
                    AdMobAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdClicked();
                }
            }
            
            public void onRewardedVideoAdLoaded() {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onRewardedVideoAdLoaded", 1);
                AdMobAdapter.this.mIsRewardedVideoReady = true;
                for (final RewardedVideoSmashListener rewardedVideoSmashListener : AdMobAdapter.this.mAllRewardedVideoSmashes) {
                    if (rewardedVideoSmashListener != null) {
                        rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(true);
                    }
                }
            }
            
            public void onRewardedVideoAdOpened() {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onRewardedVideoAdOpened", 1);
                AdMobAdapter.this.mIsRewardedVideoReady = false;
                if (AdMobAdapter.this.mActiveRewardedVideoSmash != null) {
                    AdMobAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdOpened();
                }
                for (final RewardedVideoSmashListener rewardedVideoSmashListener : AdMobAdapter.this.mAllRewardedVideoSmashes) {
                    if (rewardedVideoSmashListener != null) {
                        rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
                    }
                }
            }
            
            public void onRewardedVideoCompleted() {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onRewardedVideoCompleted", 1);
                if (AdMobAdapter.this.mActiveRewardedVideoSmash != null) {
                    AdMobAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdEnded();
                }
            }
            
            public void onRewardedVideoStarted() {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onRewardedVideoStarted", 1);
                if (AdMobAdapter.this.mActiveRewardedVideoSmash != null) {
                    AdMobAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdStarted();
                }
            }
        };
        this.mAllBannerSmashes = new CopyOnWriteArrayList();
        this.mAdIdToIsAd = new ConcurrentHashMap<String, InterstitialAd>();
        this.mInterstitialAdsAvailability = new ConcurrentHashMap<String, Boolean>();
        this.mAdIdToBannerAd = new ConcurrentHashMap<String, AdView>();
    }
    
    static /* synthetic */ void access$5700(final AdMobAdapter adMobAdapter, final IronSourceLogger$IronSourceTag ironSourceLogger$IronSourceTag, final String s, final int n) {
        adMobAdapter.log(ironSourceLogger$IronSourceTag, s, n);
    }
    
    private AdRequest createAdRequest() {
        final AdRequest$Builder adRequest$Builder = new AdRequest$Builder();
        adRequest$Builder.setGender(this.mGender);
        adRequest$Builder.setRequestAgent("ironSource");
        if (this.mAge > -1) {
            adRequest$Builder.tagForChildDirectedTreatment(this.mAge < 13);
        }
        if (this.mConsent != null && !this.mConsent) {
            final Bundle bundle = new Bundle();
            bundle.putString("npa", "1");
            adRequest$Builder.addNetworkExtrasBundle((Class)com.google.ads.mediation.admob.AdMobAdapter.class, bundle);
        }
        return adRequest$Builder.build();
    }
    
    private AdListener createBannerAdListener(final AdView adView, final String s) {
        return new AdListener() {
            public void onAdClosed() {
                super.onAdClosed();
                if (AdMobAdapter.this.mBannerPlacementToListenerMap.containsKey(s)) {
                    AdMobAdapter.this.mBannerPlacementToListenerMap.get(s).onBannerAdScreenDismissed();
                }
            }
            
            public void onAdFailedToLoad(final int n) {
                super.onAdFailedToLoad(n);
                if (AdMobAdapter.this.mBannerPlacementToListenerMap.containsKey(s)) {
                    AdMobAdapter.this.mBannerPlacementToListenerMap.get(s).onBannerAdLoadFailed(ErrorBuilder.buildLoadFailedError(AdMobAdapter.this.getErrorString(n) + "( " + n + " )"));
                }
            }
            
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                if (AdMobAdapter.this.mBannerPlacementToListenerMap.containsKey(s)) {
                    AdMobAdapter.this.mBannerPlacementToListenerMap.get(s).onBannerAdClicked();
                    AdMobAdapter.this.mBannerPlacementToListenerMap.get(s).onBannerAdLeftApplication();
                }
            }
            
            public void onAdLoaded() {
                super.onAdLoaded();
                if (AdMobAdapter.this.mBannerPlacementToListenerMap.containsKey(s)) {
                    final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(-2, -2);
                    frameLayout$LayoutParams.gravity = 17;
                    AdMobAdapter.this.mBannerPlacementToListenerMap.get(s).onBannerAdLoaded((View)adView, frameLayout$LayoutParams);
                }
            }
            
            public void onAdOpened() {
                super.onAdOpened();
                if (AdMobAdapter.this.mBannerPlacementToListenerMap.containsKey(s)) {
                    AdMobAdapter.this.mBannerPlacementToListenerMap.get(s).onBannerAdScreenPresented();
                }
            }
        };
    }
    
    private AdListener createInterstitialAdListener(final JSONObject jsonObject, final String s) {
        return new AdListener() {
            public void onAdClosed() {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onAdClosed " + s, 1);
                if (AdMobAdapter.this.mInterstitialPlacementToListenerMap.containsKey(s)) {
                    AdMobAdapter.this.mInterstitialPlacementToListenerMap.get(s).onInterstitialAdClosed();
                }
                AdMobAdapter.this.setInterstitialAdAvailability(jsonObject);
            }
            
            public void onAdFailedToLoad(final int n) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onAdFailedToLoad " + s, 1);
                if (AdMobAdapter.this.mInterstitialPlacementToListenerMap.containsKey(s)) {
                    AdMobAdapter.this.mInterstitialPlacementToListenerMap.get(s).onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError(AdMobAdapter.this.getErrorString(n) + "( " + n + " )"));
                }
                AdMobAdapter.this.setInterstitialAdAvailability(jsonObject);
            }
            
            public void onAdLeftApplication() {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onAdLeftApplication " + s, 1);
                if (AdMobAdapter.this.mInterstitialPlacementToListenerMap.containsKey(s)) {
                    AdMobAdapter.this.mInterstitialPlacementToListenerMap.get(s).onInterstitialAdClicked();
                }
            }
            
            public void onAdLoaded() {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onAdLoaded " + s, 1);
                if (AdMobAdapter.this.mInterstitialPlacementToListenerMap.containsKey(s)) {
                    AdMobAdapter.this.mInterstitialPlacementToListenerMap.get(s).onInterstitialAdReady();
                }
                AdMobAdapter.this.setInterstitialAdAvailability(jsonObject);
            }
            
            public void onAdOpened() {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onAdOpened " + s, 1);
                if (AdMobAdapter.this.mInterstitialPlacementToListenerMap.containsKey(s)) {
                    AdMobAdapter.this.mInterstitialPlacementToListenerMap.get(s).onInterstitialAdOpened();
                    AdMobAdapter.this.mInterstitialPlacementToListenerMap.get(s).onInterstitialAdShowSucceeded();
                }
            }
        };
    }
    
    private AdSize getAdSize(final ISBannerSize isBannerSize, final boolean b) {
        final String description = isBannerSize.getDescription();
        switch (description) {
            default: {
                return null;
            }
            case "BANNER": {
                return AdSize.BANNER;
            }
            case "LARGE": {
                return AdSize.LARGE_BANNER;
            }
            case "RECTANGLE": {
                return AdSize.MEDIUM_RECTANGLE;
            }
            case "SMART": {
                if (b) {
                    return AdSize.LEADERBOARD;
                }
                return AdSize.BANNER;
            }
            case "CUSTOM": {
                return new AdSize(isBannerSize.getWidth(), isBannerSize.getHeight());
            }
        }
    }
    
    private String getErrorString(final int n) {
        switch (n) {
            default: {
                return "Unknown error";
            }
            case 0: {
                return "Internal error";
            }
            case 1: {
                return "The ad request was invalid";
            }
            case 2: {
                return "The ad request was unsuccessful due to network connectivity";
            }
            case 3: {
                return "The ad request was successful, but no ad was returned due to lack of ad inventory";
            }
        }
    }
    
    public static IntegrationData getIntegrationData(final Activity activity) {
        final IntegrationData integrationData = new IntegrationData("AdMob", "4.3.0");
        integrationData.activities = new String[] { "com.google.android.gms.ads.AdActivity" };
        return integrationData;
    }
    
    private InterstitialAd getInterstitialAd(final JSONObject jsonObject) {
        final String optString = jsonObject.optString("adUnitId");
        if (TextUtils.isEmpty((CharSequence)optString) || !this.mAdIdToIsAd.containsKey(optString)) {
            return null;
        }
        return this.mAdIdToIsAd.get(optString);
    }
    
    private void initSDK(final Activity activity, final String s) {
        synchronized (this) {
            if (!this.mDidInitSdk) {
                this.mDidInitSdk = true;
                if (TextUtils.isEmpty((CharSequence)s)) {
                    MobileAds.initialize(activity.getApplicationContext());
                }
                else {
                    MobileAds.initialize(activity.getApplicationContext(), s);
                }
            }
        }
    }
    
    private void loadRewardedVideoAd(final JSONObject jsonObject) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                final String optString = jsonObject.optString("adUnitId");
                final AdRequest access$400 = AdMobAdapter.this.createAdRequest();
                if (AdMobAdapter.this.mRewardedVideoAd != null) {
                    AdMobAdapter.this.mRewardedVideoAd.loadAd(optString, access$400);
                }
            }
        });
    }
    
    private void setInterstitialAdAvailability(final JSONObject jsonObject) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                final InterstitialAd access$4000 = AdMobAdapter.this.getInterstitialAd(jsonObject);
                final String optString = jsonObject.optString("adUnitId");
                if (access$4000 != null && optString != null) {
                    IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "setInterstitialAdAvailability for " + optString + ", avilbility " + access$4000.isLoaded(), 1);
                    AdMobAdapter.this.mInterstitialAdsAvailability.put(optString, access$4000.isLoaded());
                }
            }
        });
    }
    
    public static AdMobAdapter startAdapter(final String s) {
        return new AdMobAdapter(s);
    }
    
    public void destroyBanner(final JSONObject jsonObject) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    final String optString = jsonObject.optString("adUnitId");
                    if (AdMobAdapter.this.mAdIdToBannerAd.containsKey(optString)) {
                        ((AdView)AdMobAdapter.this.mAdIdToBannerAd.get(optString)).destroy();
                        AdMobAdapter.this.mAdIdToBannerAd.remove(optString);
                    }
                }
                catch (Exception ex) {
                    AdMobAdapter.access$5700(AdMobAdapter.this, IronSourceLogger$IronSourceTag.ADAPTER_API, "AdMob destroyBanner() exception: " + ex, 3);
                }
            }
        });
    }
    
    public void fetchRewardedVideo(final JSONObject jsonObject) {
        this.loadRewardedVideoAd(jsonObject);
    }
    
    public String getCoreSDKVersion() {
        return "15.0.0";
    }
    
    public String getVersion() {
        return "4.3.0";
    }
    
    public void initBanners(final Activity activity, final String s, final String s2, final JSONObject jsonObject, final BannerSmashListener bannerSmashListener) {
        if (bannerSmashListener == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "Admob banner init failed: BannerSmashListener is empty", 2);
            return;
        }
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                AdMobAdapter.this.initSDK(activity, jsonObject.optString("appId"));
                bannerSmashListener.onBannerInitSuccess();
            }
        });
    }
    
    public void initInterstitial(final Activity activity, String optString, final String s, final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        if (interstitialSmashListener == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "Admob interstitial init failed: InterstitialSmashListener is empty", 2);
            return;
        }
        if (TextUtils.isEmpty((CharSequence)jsonObject.optString("appId"))) {
            interstitialSmashListener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Missing params: 'appId' ", "Interstitial"));
            return;
        }
        optString = jsonObject.optString("adUnitId");
        if (TextUtils.isEmpty((CharSequence)optString)) {
            interstitialSmashListener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Missing params: 'adUnitId' ", "Interstitial"));
            return;
        }
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                AdMobAdapter.this.initSDK(activity, jsonObject.optString("appId"));
                final InterstitialAd interstitialAd = new InterstitialAd((Context)activity);
                interstitialAd.setAdUnitId(optString);
                AdMobAdapter.this.mAdIdToIsAd.put(optString, interstitialAd);
                interstitialAd.setAdListener(AdMobAdapter.this.createInterstitialAdListener(jsonObject, optString));
                AdMobAdapter.this.mInterstitialPlacementToListenerMap.put(optString, interstitialSmashListener);
                interstitialSmashListener.onInterstitialInitSuccess();
            }
        });
    }
    
    public void initRewardedVideo(final Activity activity, final String s, final String s2, final JSONObject jsonObject, final RewardedVideoSmashListener rewardedVideoSmashListener) {
        if (rewardedVideoSmashListener == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "Admob RV init failed: RewardedVideoSmashListener is empty", 2);
            return;
        }
        if (TextUtils.isEmpty((CharSequence)jsonObject.optString("appId"))) {
            rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
            return;
        }
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                AdMobAdapter.this.initSDK(activity, jsonObject.optString("appId"));
                AdMobAdapter.this.mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance((Context)activity);
                AdMobAdapter.this.mRewardedVideoAd.setRewardedVideoAdListener(AdMobAdapter.this.rewardedVideoAdListener);
                AdMobAdapter.this.loadRewardedVideoAd(jsonObject);
            }
        });
    }
    
    public final boolean isInterstitialReady(final JSONObject jsonObject) {
        final String optString = jsonObject.optString("adUnitId");
        return this.mInterstitialAdsAvailability.get(optString) != null && this.mInterstitialAdsAvailability.get(optString);
    }
    
    public boolean isRewardedVideoAvailable(final JSONObject jsonObject) {
        return this.mRewardedVideoAd != null && this.mIsRewardedVideoReady;
    }
    
    public void loadBanner(final IronSourceBannerLayout ironSourceBannerLayout, final JSONObject jsonObject, final BannerSmashListener bannerSmashListener) {
        if (bannerSmashListener == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "AdMob loadBanner listener == null", 3);
            return;
        }
        if (ironSourceBannerLayout == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "AdMob loadBanner banner == null", 3);
            return;
        }
        final String optString = jsonObject.optString("adUnitId");
        if (TextUtils.isEmpty((CharSequence)optString)) {
            bannerSmashListener.onBannerAdLoadFailed(ErrorBuilder.buildLoadFailedError("AdMobAdapter loadBanner adUnitId is empty"));
            return;
        }
        final AdSize adSize = this.getAdSize(ironSourceBannerLayout.getSize(), AdapterUtils.isLargeScreen(ironSourceBannerLayout.getActivity()));
        if (adSize == null) {
            bannerSmashListener.onBannerAdLoadFailed(ErrorBuilder.unsupportedBannerSize("AdMob"));
            return;
        }
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    final AdView adView = new AdView((Context)ironSourceBannerLayout.getActivity());
                    adView.setAdSize(adSize);
                    adView.setAdUnitId(optString);
                    adView.setAdListener(AdMobAdapter.this.createBannerAdListener(adView, optString));
                    AdMobAdapter.this.mAdIdToBannerAd.put(optString, adView);
                    AdMobAdapter.this.mBannerPlacementToListenerMap.put(optString, bannerSmashListener);
                    adView.loadAd(AdMobAdapter.this.createAdRequest());
                }
                catch (Exception ex) {
                    bannerSmashListener.onBannerAdLoadFailed(ErrorBuilder.buildLoadFailedError("AdMobAdapter loadBanner exception " + ex.getMessage()));
                }
            }
        });
    }
    
    public void loadInterstitial(final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                final InterstitialAd access$4000 = AdMobAdapter.this.getInterstitialAd(jsonObject);
                if (access$4000 == null) {
                    if (interstitialSmashListener != null) {
                        interstitialSmashListener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("invalid adUnitId"));
                    }
                }
                else if (access$4000.isLoaded()) {
                    final String optString = jsonObject.optString("adUnitId");
                    if (AdMobAdapter.this.mInterstitialPlacementToListenerMap.containsKey(optString)) {
                        ((InterstitialSmashListener)AdMobAdapter.this.mInterstitialPlacementToListenerMap.get(optString)).onInterstitialAdReady();
                    }
                }
                else if (!access$4000.isLoading()) {
                    access$4000.loadAd(AdMobAdapter.this.createAdRequest());
                }
            }
        });
    }
    
    public void onPause(final Activity activity) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (AdMobAdapter.this.mRewardedVideoAd != null) {
                    AdMobAdapter.this.mRewardedVideoAd.pause((Context)activity);
                }
            }
        });
    }
    
    public void onResume(final Activity activity) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (AdMobAdapter.this.mRewardedVideoAd != null) {
                    AdMobAdapter.this.mRewardedVideoAd.resume((Context)activity);
                }
            }
        });
    }
    
    public void reloadBanner(final JSONObject jsonObject) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (AdMobAdapter.this.mAdIdToBannerAd.get(jsonObject.optString("adUnitId")) != null) {
                    AdMobAdapter.this.mAdIdToBannerAd.get(jsonObject.optString("adUnitId")).loadAd(AdMobAdapter.this.createAdRequest());
                }
            }
        });
    }
    
    public void setAge(final int mAge) {
        this.mAge = mAge;
    }
    
    protected void setConsent(final boolean b) {
        this.mConsent = b;
    }
    
    public void setGender(final String s) {
        switch (s) {
            default: {
                this.mGender = 0;
            }
            case "female": {
                this.mGender = 2;
            }
            case "male": {
                this.mGender = 1;
            }
        }
    }
    
    public void showInterstitial(final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                final InterstitialAd access$4000 = AdMobAdapter.this.getInterstitialAd(jsonObject);
                if (access$4000 == null || !access$4000.isLoaded()) {
                    if (interstitialSmashListener != null) {
                        interstitialSmashListener.onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
                    }
                    return;
                }
                access$4000.show();
            }
        });
    }
    
    public void showRewardedVideo(final JSONObject jsonObject, final RewardedVideoSmashListener mActiveRewardedVideoSmash) {
        this.mActiveRewardedVideoSmash = mActiveRewardedVideoSmash;
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (AdMobAdapter.this.mRewardedVideoAd.isLoaded()) {
                    AdMobAdapter.this.mRewardedVideoAd.show();
                }
                else {
                    if (AdMobAdapter.this.mActiveRewardedVideoSmash != null) {
                        AdMobAdapter.this.mActiveRewardedVideoSmash.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Rewarded Video"));
                    }
                    for (final RewardedVideoSmashListener rewardedVideoSmashListener : AdMobAdapter.this.mAllRewardedVideoSmashes) {
                        if (rewardedVideoSmashListener != null) {
                            rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
                        }
                    }
                }
            }
        });
    }
}
