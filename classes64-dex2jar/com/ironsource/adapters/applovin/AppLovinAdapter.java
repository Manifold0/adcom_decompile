// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.adapters.applovin;

import android.view.View;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import java.util.Map;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinPrivacySettings;
import android.os.Handler;
import android.os.Looper;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdClickListener;
import android.content.Context;
import com.applovin.adview.AppLovinInterstitialAd;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.ironsource.mediationsdk.logger.IronSourceLogger$IronSourceTag;
import android.text.TextUtils;
import org.json.JSONObject;
import com.ironsource.mediationsdk.IntegrationData;
import com.ironsource.mediationsdk.AdapterUtils;
import com.applovin.sdk.AppLovinAdSize;
import com.ironsource.mediationsdk.ISBannerSize;
import java.util.concurrent.CopyOnWriteArrayList;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.ironsource.mediationsdk.sdk.BannerSmashListener;
import android.widget.FrameLayout$LayoutParams;
import com.applovin.adview.AppLovinAdView;
import java.util.concurrent.ConcurrentHashMap;
import com.applovin.sdk.AppLovinSdk;
import android.app.Activity;
import com.ironsource.mediationsdk.AbstractAdapter;

class AppLovinAdapter extends AbstractAdapter
{
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
    private ConcurrentHashMap<String, FrameLayout$LayoutParams> mZoneIdToBannerLayout;
    private ConcurrentHashMap<String, BannerSmashListener> mZoneIdToBannerSmashListener;
    private ConcurrentHashMap<String, AppLovinAd> mZoneIdToIsAd;
    private ConcurrentHashMap<String, AppLovinInterstitialAdDialog> mZoneIdToIsAdDialog;
    private ConcurrentHashMap<String, InterstitialSmashListener> mZoneIdToIsListener;
    private ConcurrentHashMap<String, AppLovinIncentivizedInterstitial> mZoneIdToRvAd;
    private ConcurrentHashMap<String, RewardedVideoSmashListener> mZoneIdToRvListener;
    
    private AppLovinAdapter(final String s) {
        super(s);
        this.mDidInitSdk = false;
        this.mConsentCollectingUserData = null;
        this.mAllBannerSmashes = new CopyOnWriteArrayList();
        this.mZoneIdToAppLovinListener = new ConcurrentHashMap<String, ALBannerListener>();
        this.mZoneIdToBannerSmashListener = new ConcurrentHashMap<String, BannerSmashListener>();
        this.mZoneIdToBannerLayout = new ConcurrentHashMap<String, FrameLayout$LayoutParams>();
        this.mZoneIdToBannerAd = new ConcurrentHashMap<String, AppLovinAdView>();
        this.mZoneIdToIsAd = new ConcurrentHashMap<String, AppLovinAd>();
        this.mZoneIdToIsAdDialog = new ConcurrentHashMap<String, AppLovinInterstitialAdDialog>();
        this.mZoneIdToRvAd = new ConcurrentHashMap<String, AppLovinIncentivizedInterstitial>();
        this.mZoneIdToIsListener = new ConcurrentHashMap<String, InterstitialSmashListener>();
        this.mZoneIdToRvListener = new ConcurrentHashMap<String, RewardedVideoSmashListener>();
    }
    
    private FrameLayout$LayoutParams calcLayoutParams(final ISBannerSize isBannerSize, final AppLovinAdSize appLovinAdSize, final Activity activity) {
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
        return new FrameLayout$LayoutParams(AdapterUtils.dpToPixels(activity, n2), AdapterUtils.dpToPixels(activity, appLovinAdSize.getHeight()), 17);
    }
    
    private AppLovinAdSize calculateBannerSize(final ISBannerSize isBannerSize, final boolean b) {
        final String description = isBannerSize.getDescription();
        switch (description) {
            case "BANNER":
            case "LARGE": {
                return AppLovinAdSize.BANNER;
            }
            case "RECTANGLE": {
                return AppLovinAdSize.MREC;
            }
            case "SMART": {
                if (b) {
                    return AppLovinAdSize.LEADER;
                }
                return AppLovinAdSize.BANNER;
            }
            case "CUSTOM": {
                if (isBannerSize.getHeight() >= 40 && isBannerSize.getHeight() <= 60) {
                    return AppLovinAdSize.BANNER;
                }
                break;
            }
        }
        return null;
    }
    
    public static String getAdapterSDKVersion() {
        return "8.0.1";
    }
    
    private String getErrorString(final int n) {
        switch (n) {
            default: {
                return "Unknown error";
            }
            case -102: {
                return "Ad fetch timeout";
            }
            case -300: {
                return "No ad pre-loaded";
            }
            case -500: {
                return "Server timeout";
            }
            case -400: {
                return "Unknown server error";
            }
            case -600: {
                return "User closed video before reward";
            }
            case 204: {
                return "No fill";
            }
            case -103: {
                return "No network available";
            }
            case -6: {
                return "Unable to render ad";
            }
            case -1: {
                return "Unspecified error";
            }
        }
    }
    
    public static IntegrationData getIntegrationData(final Activity activity) {
        final IntegrationData integrationData = new IntegrationData("AppLovin", "4.3.1");
        integrationData.activities = new String[] { "com.applovin.adview.AppLovinInterstitialActivity", "com.applovin.adview.AppLovinConfirmationActivity" };
        return integrationData;
    }
    
    private String getZoneId(final AppLovinAd appLovinAd) {
        if (appLovinAd.getZoneId() != null) {
            return appLovinAd.getZoneId();
        }
        return "";
    }
    
    private String getZoneId(final JSONObject jsonObject) {
        if (!TextUtils.isEmpty((CharSequence)jsonObject.optString("zoneId"))) {
            return jsonObject.optString("zoneId");
        }
        return "";
    }
    
    private void initSdk(final Activity p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/ironsource/adapters/applovin/AppLovinAdapter.mDidInitSdk:Ljava/lang/Boolean;
        //     6: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //     9: ifne            82
        //    12: new             Lcom/applovin/sdk/AppLovinSdkSettings;
        //    15: dup            
        //    16: invokespecial   com/applovin/sdk/AppLovinSdkSettings.<init>:()V
        //    19: astore          5
        //    21: iconst_0       
        //    22: istore_3       
        //    23: aload_0        
        //    24: invokevirtual   com/ironsource/adapters/applovin/AppLovinAdapter.isAdaptersDebugEnabled:()Z
        //    27: istore          4
        //    29: iload           4
        //    31: istore_3       
        //    32: aload           5
        //    34: iload_3        
        //    35: invokevirtual   com/applovin/sdk/AppLovinSdkSettings.setVerboseLogging:(Z)V
        //    38: aload_0        
        //    39: aload_2        
        //    40: aload           5
        //    42: aload_1        
        //    43: invokestatic    com/applovin/sdk/AppLovinSdk.getInstance:(Ljava/lang/String;Lcom/applovin/sdk/AppLovinSdkSettings;Landroid/content/Context;)Lcom/applovin/sdk/AppLovinSdk;
        //    46: putfield        com/ironsource/adapters/applovin/AppLovinAdapter.mAppLovinSdk:Lcom/applovin/sdk/AppLovinSdk;
        //    49: aload_0        
        //    50: getfield        com/ironsource/adapters/applovin/AppLovinAdapter.mAppLovinSdk:Lcom/applovin/sdk/AppLovinSdk;
        //    53: invokevirtual   com/applovin/sdk/AppLovinSdk.initializeSdk:()V
        //    56: aload_0        
        //    57: iconst_1       
        //    58: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    61: putfield        com/ironsource/adapters/applovin/AppLovinAdapter.mDidInitSdk:Ljava/lang/Boolean;
        //    64: aload_0        
        //    65: getfield        com/ironsource/adapters/applovin/AppLovinAdapter.mConsentCollectingUserData:Ljava/lang/Boolean;
        //    68: ifnull          82
        //    71: aload_0        
        //    72: aload_0        
        //    73: getfield        com/ironsource/adapters/applovin/AppLovinAdapter.mConsentCollectingUserData:Ljava/lang/Boolean;
        //    76: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    79: invokevirtual   com/ironsource/adapters/applovin/AppLovinAdapter.setConsent:(Z)V
        //    82: aload_0        
        //    83: monitorexit    
        //    84: return         
        //    85: astore_1       
        //    86: aload_0        
        //    87: monitorexit    
        //    88: aload_1        
        //    89: athrow         
        //    90: astore          6
        //    92: goto            32
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                         
        //  -----  -----  -----  -----  -----------------------------
        //  2      21     85     90     Any
        //  23     29     90     95     Ljava/lang/NoSuchMethodError;
        //  23     29     85     90     Any
        //  32     82     85     90     Any
        //  82     84     85     90     Any
        //  86     88     85     90     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0032:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static AppLovinAdapter startAdapter(final String s) {
        return new AppLovinAdapter(s);
    }
    
    public void destroyBanner(final JSONObject jsonObject) {
        final String zoneId = this.getZoneId(jsonObject);
        final AppLovinAdView appLovinAdView = this.mZoneIdToBannerAd.get(zoneId);
        if (appLovinAdView != null) {
            appLovinAdView.destroy();
        }
        if (this.mZoneIdToBannerAd != null) {
            this.mZoneIdToBannerAd.remove(zoneId);
        }
    }
    
    public void fetchRewardedVideo(final JSONObject jsonObject) {
        this.log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + ": in fetchRewardedVideo", 0);
        final String zoneId = this.getZoneId(jsonObject);
        if (this.mZoneIdToRvAd.containsKey(zoneId)) {
            this.mZoneIdToRvAd.get(zoneId).preload(new AppLovinAdLoadListener() {
                @Override
                public void adReceived(final AppLovinAd appLovinAd) {
                    if (AppLovinAdapter.this.mZoneIdToRvListener.containsKey(zoneId)) {
                        AppLovinAdapter.this.mZoneIdToRvListener.get(zoneId).onRewardedVideoAvailabilityChanged(true);
                    }
                }
                
                @Override
                public void failedToReceiveAd(final int n) {
                    if (AppLovinAdapter.this.mZoneIdToRvListener.containsKey(zoneId)) {
                        AppLovinAdapter.this.mZoneIdToRvListener.get(zoneId).onRewardedVideoAvailabilityChanged(false);
                    }
                }
            });
        }
    }
    
    public String getCoreSDKVersion() {
        return "8.0.1";
    }
    
    public String getVersion() {
        return "4.3.1";
    }
    
    public void initBanners(final Activity mActivity, String optString, final String s, final JSONObject jsonObject, final BannerSmashListener bannerSmashListener) {
        if (bannerSmashListener == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, this.getProviderName() + " initBanners listener == null", 3);
        }
        if (jsonObject == null || mActivity == null) {
            bannerSmashListener.onBannerInitFailed(ErrorBuilder.buildInitFailedError("null parameters", "Banner"));
            return;
        }
        optString = jsonObject.optString("sdkKey");
        if (TextUtils.isEmpty((CharSequence)optString)) {
            bannerSmashListener.onBannerInitFailed(ErrorBuilder.buildInitFailedError("Missing params", "Banner"));
            return;
        }
        (this.mActivity = mActivity).runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                AppLovinAdapter.this.initSdk(mActivity, optString);
                bannerSmashListener.onBannerInitSuccess();
            }
        });
    }
    
    public void initInterstitial(final Activity mActivity, String optString, String zoneId, final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        optString = jsonObject.optString("sdkKey");
        if (TextUtils.isEmpty((CharSequence)optString)) {
            this.log(IronSourceLogger$IronSourceTag.INTERNAL, this.getProviderName() + " initInterstitial empty sdkKey", 3);
            if (interstitialSmashListener != null) {
                interstitialSmashListener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("initInterstitial empty sdkKey", "Interstitial"));
            }
        }
        else {
            zoneId = this.getZoneId(jsonObject);
            this.initSdk(this.mActivity = mActivity, optString);
            final AppLovinInterstitialAdDialog create = AppLovinInterstitialAd.create(this.mAppLovinSdk, (Context)mActivity);
            this.mZoneIdToIsAdDialog.put(zoneId, create);
            if (interstitialSmashListener != null) {
                this.mZoneIdToIsListener.put(zoneId, interstitialSmashListener);
            }
            create.setAdClickListener(new AppLovinAdClickListener() {
                @Override
                public void adClicked(final AppLovinAd appLovinAd) {
                    if (AppLovinAdapter.this.mZoneIdToIsListener.containsKey(zoneId)) {
                        AppLovinAdapter.this.mZoneIdToIsListener.get(zoneId).onInterstitialAdClicked();
                    }
                }
            });
            create.setAdDisplayListener(new AppLovinAdDisplayListener() {
                @Override
                public void adDisplayed(final AppLovinAd appLovinAd) {
                    if (AppLovinAdapter.this.mZoneIdToIsListener.containsKey(zoneId)) {
                        AppLovinAdapter.this.mZoneIdToIsListener.get(zoneId).onInterstitialAdOpened();
                        AppLovinAdapter.this.mZoneIdToIsListener.get(zoneId).onInterstitialAdShowSucceeded();
                    }
                }
                
                @Override
                public void adHidden(final AppLovinAd appLovinAd) {
                    if (AppLovinAdapter.this.mZoneIdToIsListener.containsKey(zoneId)) {
                        AppLovinAdapter.this.mZoneIdToIsListener.get(zoneId).onInterstitialAdClosed();
                    }
                }
            });
            if (interstitialSmashListener != null) {
                interstitialSmashListener.onInterstitialInitSuccess();
            }
        }
    }
    
    public void initRewardedVideo(final Activity mActivity, String optString, final String s, final JSONObject jsonObject, final RewardedVideoSmashListener rewardedVideoSmashListener) {
        optString = jsonObject.optString("sdkKey");
        if (TextUtils.isEmpty((CharSequence)optString)) {
            if (rewardedVideoSmashListener != null) {
                rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
            }
            return;
        }
        final String zoneId = this.getZoneId(jsonObject);
        this.mActivity = mActivity;
        if (rewardedVideoSmashListener != null) {
            this.mZoneIdToRvListener.put(zoneId, rewardedVideoSmashListener);
        }
        mActivity.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                AppLovinAdapter.this.initSdk(mActivity, optString);
                AppLovinIncentivizedInterstitial appLovinIncentivizedInterstitial;
                if (!TextUtils.isEmpty((CharSequence)zoneId)) {
                    appLovinIncentivizedInterstitial = AppLovinIncentivizedInterstitial.create(zoneId, AppLovinAdapter.this.mAppLovinSdk);
                }
                else {
                    appLovinIncentivizedInterstitial = AppLovinIncentivizedInterstitial.create(AppLovinAdapter.this.mAppLovinSdk);
                }
                AppLovinAdapter.this.mZoneIdToRvAd.put(zoneId, appLovinIncentivizedInterstitial);
                appLovinIncentivizedInterstitial.setUserIdentifier(s);
                appLovinIncentivizedInterstitial.preload(new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(final AppLovinAd appLovinAd) {
                        if (rewardedVideoSmashListener != null) {
                            rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(true);
                        }
                    }
                    
                    @Override
                    public void failedToReceiveAd(final int n) {
                        if (rewardedVideoSmashListener != null) {
                            rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
                        }
                    }
                });
            }
        });
    }
    
    public boolean isInterstitialReady(final JSONObject jsonObject) {
        return this.mZoneIdToIsAdDialog.containsKey(this.getZoneId(jsonObject));
    }
    
    public boolean isRewardedVideoAvailable(final JSONObject jsonObject) {
        synchronized (this) {
            final String zoneId = this.getZoneId(jsonObject);
            return this.mZoneIdToRvAd.containsKey(zoneId) && this.mZoneIdToRvAd.get(zoneId).isAdReadyToDisplay();
        }
    }
    
    public void loadBanner(final IronSourceBannerLayout ironSourceBannerLayout, final JSONObject jsonObject, final BannerSmashListener bannerSmashListener) {
        if (bannerSmashListener == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, this.getProviderName() + " loadBanner listener == null", 3);
            return;
        }
        if (ironSourceBannerLayout == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, this.getProviderName() + " loadBanner banner == null", 3);
            return;
        }
        final AppLovinAdSize calculateBannerSize = this.calculateBannerSize(ironSourceBannerLayout.getSize(), AdapterUtils.isLargeScreen(ironSourceBannerLayout.getActivity()));
        if (calculateBannerSize == null) {
            bannerSmashListener.onBannerAdLoadFailed(ErrorBuilder.unsupportedBannerSize(this.getProviderName()));
            return;
        }
        final String zoneId = this.getZoneId(jsonObject);
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, this.getProviderName() + " loadBanner: " + this.getProviderName() + ", zoneID <" + zoneId + ">", 1);
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    FrameLayout$LayoutParams frameLayout$LayoutParams;
                    AppLovinAdView appLovinAdView;
                    if (TextUtils.isEmpty((CharSequence)zoneId)) {
                        frameLayout$LayoutParams = AppLovinAdapter.this.calcLayoutParams(ironSourceBannerLayout.getSize(), calculateBannerSize, ironSourceBannerLayout.getActivity());
                        appLovinAdView = new AppLovinAdView(AppLovinAdapter.this.mAppLovinSdk, calculateBannerSize, (Context)ironSourceBannerLayout.getActivity());
                    }
                    else {
                        frameLayout$LayoutParams = AppLovinAdapter.this.calcLayoutParams(ISBannerSize.BANNER, AppLovinAdSize.BANNER, ironSourceBannerLayout.getActivity());
                        appLovinAdView = new AppLovinAdView(AppLovinAdapter.this.mAppLovinSdk, AppLovinAdSize.BANNER, (Context)ironSourceBannerLayout.getActivity());
                    }
                    final ALBannerListener adDisplayListener = new ALBannerListener(zoneId);
                    appLovinAdView.setAdLoadListener(adDisplayListener);
                    appLovinAdView.setAdClickListener(adDisplayListener);
                    appLovinAdView.setAdDisplayListener(adDisplayListener);
                    AppLovinAdapter.this.mZoneIdToBannerSmashListener.put(zoneId, bannerSmashListener);
                    AppLovinAdapter.this.mZoneIdToBannerAd.put(zoneId, appLovinAdView);
                    AppLovinAdapter.this.mZoneIdToBannerLayout.put(zoneId, frameLayout$LayoutParams);
                    AppLovinAdapter.this.mZoneIdToAppLovinListener.put(zoneId, adDisplayListener);
                    if (TextUtils.isEmpty((CharSequence)zoneId)) {
                        appLovinAdView.loadNextAd();
                        return;
                    }
                    AppLovinAdapter.this.mAppLovinSdk.getAdService().loadNextAdForZoneId(zoneId, adDisplayListener);
                }
                catch (Exception ex) {
                    bannerSmashListener.onBannerAdLoadFailed(ErrorBuilder.buildLoadFailedError(AppLovinAdapter.this.getProviderName() + " loadBanner exception " + ex.getMessage()));
                }
            }
        });
    }
    
    public void loadInterstitial(final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        final String zoneId = this.getZoneId(jsonObject);
        if (!TextUtils.isEmpty((CharSequence)zoneId)) {
            this.mAppLovinSdk.getAdService().loadNextAdForZoneId(zoneId, new AppLovinAdLoadListener() {
                @Override
                public void adReceived(final AppLovinAd appLovinAd) {
                    IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "Interstitial adReceived zoneId=" + AppLovinAdapter.this.getZoneId(appLovinAd), 1);
                    if (interstitialSmashListener != null) {
                        interstitialSmashListener.onInterstitialAdReady();
                    }
                    AppLovinAdapter.this.mZoneIdToIsAd.put(zoneId, appLovinAd);
                }
                
                @Override
                public void failedToReceiveAd(final int n) {
                    if (interstitialSmashListener != null) {
                        interstitialSmashListener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError(AppLovinAdapter.this.getErrorString(n) + "( " + n + " )"));
                    }
                }
            });
            return;
        }
        this.mAppLovinSdk.getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
            @Override
            public void adReceived(final AppLovinAd appLovinAd) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "Interstitial adReceived", 1);
                if (interstitialSmashListener != null) {
                    interstitialSmashListener.onInterstitialAdReady();
                }
                AppLovinAdapter.this.mZoneIdToIsAd.put(zoneId, appLovinAd);
            }
            
            @Override
            public void failedToReceiveAd(final int n) {
                if (interstitialSmashListener != null) {
                    interstitialSmashListener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError(AppLovinAdapter.this.getErrorString(n) + "( " + n + " )"));
                }
            }
        });
    }
    
    public void onResume(final Activity mActivity) {
        this.mActivity = mActivity;
    }
    
    public void reloadBanner(final JSONObject jsonObject) {
        final String zoneId = this.getZoneId(jsonObject);
        final AppLovinAdView appLovinAdView = this.mZoneIdToBannerAd.get(zoneId);
        final ALBannerListener alBannerListener = this.mZoneIdToAppLovinListener.get(zoneId);
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, this.getProviderName() + "Banner reloadBanner: <" + zoneId + ">", 1);
        if (appLovinAdView == null || alBannerListener == null) {
            this.log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + ":reloadBanner() failed, null parameters", 2);
            return;
        }
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty((CharSequence)zoneId)) {
                    appLovinAdView.loadNextAd();
                    return;
                }
                AppLovinAdapter.this.mAppLovinSdk.getAdService().loadNextAdForZoneId(zoneId, alBannerListener);
            }
        });
    }
    
    protected void setConsent(final boolean b) {
        synchronized (this) {
            if (this.mDidInitSdk) {
                AppLovinPrivacySettings.setHasUserConsent(b, (Context)this.mActivity);
            }
            else {
                this.mConsentCollectingUserData = b;
            }
        }
    }
    
    public void showInterstitial(final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        final String zoneId = this.getZoneId(jsonObject);
        if (!this.mZoneIdToIsAd.containsKey(zoneId) || !this.mZoneIdToIsAdDialog.containsKey(zoneId)) {
            if (interstitialSmashListener != null) {
                interstitialSmashListener.onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
            }
            return;
        }
        this.mZoneIdToIsAdDialog.get(zoneId).showAndRender(this.mZoneIdToIsAd.get(zoneId));
    }
    
    public void showRewardedVideo(final JSONObject jsonObject, final RewardedVideoSmashListener rewardedVideoSmashListener) {
        final String zoneId = this.getZoneId(jsonObject);
        if (rewardedVideoSmashListener == null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "AppLovin showRewardedVideo error: null listener", 3);
            return;
        }
        if (!this.mZoneIdToRvAd.containsKey(zoneId) || !this.mZoneIdToRvAd.get(zoneId).isAdReadyToDisplay()) {
            rewardedVideoSmashListener.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Rewarded Video"));
            rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
            return;
        }
        if (!TextUtils.isEmpty((CharSequence)this.getDynamicUserId())) {
            this.mZoneIdToRvAd.get(zoneId).setUserIdentifier(this.getDynamicUserId());
        }
        this.mZoneIdToRvAd.get(zoneId).show((Context)this.mActivity, new AppLovinAdRewardListener() {
            @Override
            public void userDeclinedToViewAd(final AppLovinAd appLovinAd) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "userDeclinedToViewAd", 1);
                final String access$500 = AppLovinAdapter.this.getZoneId(appLovinAd);
                if (AppLovinAdapter.this.mZoneIdToRvListener.containsKey(access$500)) {
                    ((RewardedVideoSmashListener)AppLovinAdapter.this.mZoneIdToRvListener.get(access$500)).onRewardedVideoAdClosed();
                }
            }
            
            @Override
            public void userOverQuota(final AppLovinAd appLovinAd, final Map<String, String> map) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "userOverQuota", 1);
            }
            
            @Override
            public void userRewardRejected(final AppLovinAd appLovinAd, final Map<String, String> map) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "userRewardRejected", 1);
            }
            
            @Override
            public void userRewardVerified(final AppLovinAd appLovinAd, final Map<String, String> map) {
            }
            
            @Override
            public void validationRequestFailed(final AppLovinAd appLovinAd, final int n) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "validationRequestFailed " + AppLovinAdapter.this.getErrorString(n) + "(" + n + ")", 1);
            }
        }, new AppLovinAdVideoPlaybackListener() {
            @Override
            public void videoPlaybackBegan(final AppLovinAd appLovinAd) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "videoPlaybackBegan", 1);
                final String access$500 = AppLovinAdapter.this.getZoneId(appLovinAd);
                if (AppLovinAdapter.this.mZoneIdToRvListener.containsKey(access$500)) {
                    ((RewardedVideoSmashListener)AppLovinAdapter.this.mZoneIdToRvListener.get(access$500)).onRewardedVideoAdStarted();
                }
            }
            
            @Override
            public void videoPlaybackEnded(final AppLovinAd appLovinAd, final double n, final boolean b) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "videoPlaybackEnded ; isFullyWatched: " + b, 1);
                final String access$500 = AppLovinAdapter.this.getZoneId(appLovinAd);
                if (AppLovinAdapter.this.mZoneIdToRvListener.containsKey(access$500)) {
                    ((RewardedVideoSmashListener)AppLovinAdapter.this.mZoneIdToRvListener.get(access$500)).onRewardedVideoAdEnded();
                    if (b) {
                        ((RewardedVideoSmashListener)AppLovinAdapter.this.mZoneIdToRvListener.get(access$500)).onRewardedVideoAdRewarded();
                    }
                }
            }
        }, new AppLovinAdDisplayListener() {
            @Override
            public void adDisplayed(final AppLovinAd appLovinAd) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "adDisplayed", 1);
                final String access$500 = AppLovinAdapter.this.getZoneId(appLovinAd);
                if (AppLovinAdapter.this.mZoneIdToRvListener.containsKey(access$500)) {
                    ((RewardedVideoSmashListener)AppLovinAdapter.this.mZoneIdToRvListener.get(access$500)).onRewardedVideoAdOpened();
                }
            }
            
            @Override
            public void adHidden(final AppLovinAd appLovinAd) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "adHidden", 1);
                final String access$500 = AppLovinAdapter.this.getZoneId(appLovinAd);
                if (AppLovinAdapter.this.mZoneIdToRvListener.containsKey(access$500)) {
                    ((RewardedVideoSmashListener)AppLovinAdapter.this.mZoneIdToRvListener.get(access$500)).onRewardedVideoAvailabilityChanged(false);
                    ((RewardedVideoSmashListener)AppLovinAdapter.this.mZoneIdToRvListener.get(access$500)).onRewardedVideoAdClosed();
                }
                if (AppLovinAdapter.this.mZoneIdToRvAd.containsKey(access$500)) {
                    ((AppLovinIncentivizedInterstitial)AppLovinAdapter.this.mZoneIdToRvAd.get(access$500)).preload(new AppLovinAdLoadListener() {
                        @Override
                        public void adReceived(final AppLovinAd appLovinAd) {
                            if (AppLovinAdapter.this.mZoneIdToRvListener.containsKey(access$500)) {
                                AppLovinAdapter.this.mZoneIdToRvListener.get(access$500).onRewardedVideoAvailabilityChanged(true);
                            }
                        }
                        
                        @Override
                        public void failedToReceiveAd(final int n) {
                            if (AppLovinAdapter.this.mZoneIdToRvListener.containsKey(access$500)) {
                                AppLovinAdapter.this.mZoneIdToRvListener.get(access$500).onRewardedVideoAvailabilityChanged(false);
                            }
                        }
                    });
                }
            }
        }, new AppLovinAdClickListener() {
            @Override
            public void adClicked(final AppLovinAd appLovinAd) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "adClicked", 1);
                final String access$500 = AppLovinAdapter.this.getZoneId(appLovinAd);
                if (AppLovinAdapter.this.mZoneIdToRvListener.containsKey(access$500)) {
                    ((RewardedVideoSmashListener)AppLovinAdapter.this.mZoneIdToRvListener.get(access$500)).onRewardedVideoAdClicked();
                }
            }
        });
    }
    
    private class ALBannerListener implements AppLovinAdLoadListener, AppLovinAdDisplayListener, AppLovinAdClickListener
    {
        private String mZoneId;
        
        ALBannerListener(final String mZoneId) {
            this.mZoneId = mZoneId;
        }
        
        @Override
        public void adClicked(final AppLovinAd appLovinAd) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, AppLovinAdapter.this.getProviderName() + " Banner adClicked:  <" + this.mZoneId + ">", 1);
            final BannerSmashListener bannerSmashListener = AppLovinAdapter.this.mZoneIdToBannerSmashListener.get(this.mZoneId);
            if (bannerSmashListener != null) {
                bannerSmashListener.onBannerAdClicked();
            }
        }
        
        @Override
        public void adDisplayed(final AppLovinAd appLovinAd) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, AppLovinAdapter.this.getProviderName() + " Banner adDisplayed: <" + this.mZoneId + ">", 1);
        }
        
        @Override
        public void adHidden(final AppLovinAd appLovinAd) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, AppLovinAdapter.this.getProviderName() + " Banner adHidden: <" + this.mZoneId + ">", 1);
        }
        
        @Override
        public void adReceived(final AppLovinAd appLovinAd) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, AppLovinAdapter.this.getProviderName() + " Banner adReceived: <" + this.mZoneId + ">", 1);
            final AppLovinAdView appLovinAdView = AppLovinAdapter.this.mZoneIdToBannerAd.get(this.mZoneId);
            final FrameLayout$LayoutParams frameLayout$LayoutParams = AppLovinAdapter.this.mZoneIdToBannerLayout.get(this.mZoneId);
            final BannerSmashListener bannerSmashListener = AppLovinAdapter.this.mZoneIdToBannerSmashListener.get(this.mZoneId);
            if (appLovinAdView != null && bannerSmashListener != null && frameLayout$LayoutParams != null) {
                new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        appLovinAdView.renderAd(appLovinAd);
                        bannerSmashListener.onBannerAdLoaded((View)appLovinAdView, frameLayout$LayoutParams);
                    }
                });
                return;
            }
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, AppLovinAdapter.this.getProviderName() + " adReceived: null parameter", 3);
        }
        
        @Override
        public void failedToReceiveAd(final int n) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, AppLovinAdapter.this.getProviderName() + " Banner failedToReceiveAd", 1);
            final BannerSmashListener bannerSmashListener = AppLovinAdapter.this.mZoneIdToBannerSmashListener.get(this.mZoneId);
            if (bannerSmashListener != null) {
                bannerSmashListener.onBannerAdLoadFailed(ErrorBuilder.buildLoadFailedError(AppLovinAdapter.this.getErrorString(n) + "(" + n + ")"));
            }
        }
    }
}
