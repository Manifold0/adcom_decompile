// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.adapters.vungle;

import com.vungle.warren.PlayAdCallback;
import com.vungle.warren.AdConfig;
import com.ironsource.mediationsdk.RewardedVideoSmash;
import com.ironsource.mediationsdk.InterstitialSmash;
import android.text.TextUtils;
import org.json.JSONObject;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.Vungle$Consent;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import java.util.Iterator;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import java.util.Map;
import com.vungle.warren.InitCallback;
import com.ironsource.mediationsdk.IntegrationData;
import android.app.Activity;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import com.ironsource.mediationsdk.logger.IronSourceLogger$IronSourceTag;
import java.util.Set;
import com.ironsource.mediationsdk.AbstractAdapter;

class VungleAdapter extends AbstractAdapter
{
    private static final String APP_ID = "AppID";
    private static final String CONSENT_MESSAGE_VERSION = "1.0.0";
    private static final String CORE_SDK_VERSION = "6.3.17";
    private static final String PLACEMENT_ID = "PlacementId";
    private static final String VERSION = "4.1.4";
    private EInitState mInitState;
    private Set<String> mInitiatedAdUnits;
    private Boolean mIsConsent;
    
    private VungleAdapter(final String s) {
        super(s);
        this.mInitState = EInitState.NOT_INIT;
        this.mIsConsent = null;
    }
    
    static /* synthetic */ void access$000(final VungleAdapter vungleAdapter, final IronSourceLogger$IronSourceTag ironSourceLogger$IronSourceTag, final String s, final int n) {
        vungleAdapter.log(ironSourceLogger$IronSourceTag, s, n);
    }
    
    static /* synthetic */ void access$100(final VungleAdapter vungleAdapter, final IronSourceLogger$IronSourceTag ironSourceLogger$IronSourceTag, final String s, final int n) {
        vungleAdapter.log(ironSourceLogger$IronSourceTag, s, n);
    }
    
    static /* synthetic */ void access$1600(final VungleAdapter vungleAdapter, final IronSourceLogger$IronSourceTag ironSourceLogger$IronSourceTag, final String s, final int n) {
        vungleAdapter.log(ironSourceLogger$IronSourceTag, s, n);
    }
    
    static /* synthetic */ void access$1900(final VungleAdapter vungleAdapter, final IronSourceLogger$IronSourceTag ironSourceLogger$IronSourceTag, final String s, final int n) {
        vungleAdapter.log(ironSourceLogger$IronSourceTag, s, n);
    }
    
    static /* synthetic */ void access$2200(final VungleAdapter vungleAdapter, final IronSourceLogger$IronSourceTag ironSourceLogger$IronSourceTag, final String s, final int n) {
        vungleAdapter.log(ironSourceLogger$IronSourceTag, s, n);
    }
    
    static /* synthetic */ void access$2500(final VungleAdapter vungleAdapter, final IronSourceLogger$IronSourceTag ironSourceLogger$IronSourceTag, final String s, final int n) {
        vungleAdapter.log(ironSourceLogger$IronSourceTag, s, n);
    }
    
    static /* synthetic */ void access$300(final VungleAdapter vungleAdapter, final IronSourceLogger$IronSourceTag ironSourceLogger$IronSourceTag, final String s, final int n) {
        vungleAdapter.log(ironSourceLogger$IronSourceTag, s, n);
    }
    
    static /* synthetic */ void access$400(final VungleAdapter vungleAdapter, final IronSourceLogger$IronSourceTag ironSourceLogger$IronSourceTag, final String s, final int n) {
        vungleAdapter.log(ironSourceLogger$IronSourceTag, s, n);
    }
    
    static /* synthetic */ void access$500(final VungleAdapter vungleAdapter, final IronSourceLogger$IronSourceTag ironSourceLogger$IronSourceTag, final String s, final int n) {
        vungleAdapter.log(ironSourceLogger$IronSourceTag, s, n);
    }
    
    static /* synthetic */ void access$600(final VungleAdapter vungleAdapter, final IronSourceLogger$IronSourceTag ironSourceLogger$IronSourceTag, final String s, final int n) {
        vungleAdapter.log(ironSourceLogger$IronSourceTag, s, n);
    }
    
    static /* synthetic */ void access$700(final VungleAdapter vungleAdapter, final IronSourceLogger$IronSourceTag ironSourceLogger$IronSourceTag, final String s, final int n) {
        vungleAdapter.log(ironSourceLogger$IronSourceTag, s, n);
    }
    
    static /* synthetic */ void access$800(final VungleAdapter vungleAdapter, final IronSourceLogger$IronSourceTag ironSourceLogger$IronSourceTag, final String s, final int n) {
        vungleAdapter.log(ironSourceLogger$IronSourceTag, s, n);
    }
    
    static /* synthetic */ void access$900(final VungleAdapter vungleAdapter, final IronSourceLogger$IronSourceTag ironSourceLogger$IronSourceTag, final String s, final int n) {
        vungleAdapter.log(ironSourceLogger$IronSourceTag, s, n);
    }
    
    private void addInitiatedAdUnit(final String s) {
        if (this.mInitiatedAdUnits == null) {
            this.mInitiatedAdUnits = new HashSet<String>();
        }
        this.mInitiatedAdUnits.add(s);
    }
    
    private EInitState getCurrentInitState() {
        synchronized (this) {
            return this.mInitState;
        }
    }
    
    public static IntegrationData getIntegrationData(final Activity activity) {
        final IntegrationData integrationData = new IntegrationData("Vungle", "4.1.4");
        integrationData.validateWriteExternalStorage = true;
        return integrationData;
    }
    
    private void initVungleSdk(final Activity activity, final String s, final String s2, final HashSet<String> set) {
        this.setInitState(EInitState.INIT_IN_PROGRESS);
        Vungle.init(s, activity.getApplicationContext(), (InitCallback)new InitCallback() {
            public void onAutoCacheAdAvailable(final String s) {
                VungleAdapter.access$1900(VungleAdapter.this, IronSourceLogger$IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": Cache ad is available for placementId " + s, 1);
                if (VungleAdapter.this.mInitiatedAdUnits.contains("Rewarded Video")) {
                    for (final Map.Entry<String, V> entry : VungleAdapter.this.mRewardedVideoPlacementToListenerMap.entrySet()) {
                        if (entry.getKey().equals(s) && entry.getValue() != null) {
                            ((RewardedVideoSmashListener)entry.getValue()).onRewardedVideoAvailabilityChanged(true);
                        }
                    }
                }
                if (VungleAdapter.this.mInitiatedAdUnits.contains("Interstitial")) {
                    for (final Map.Entry<String, V> entry2 : VungleAdapter.this.mInterstitialPlacementToListenerMap.entrySet()) {
                        if (entry2.getKey().equals(s) && entry2.getValue() != null) {
                            ((InterstitialSmashListener)entry2.getValue()).onInterstitialAdReady();
                        }
                    }
                }
            }
            
            public void onError(final Throwable t) {
                VungleAdapter.access$1600(VungleAdapter.this, IronSourceLogger$IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": Failed to initialize SDK ", 1);
                VungleAdapter.this.setInitState(EInitState.INIT_FAIL);
                if (VungleAdapter.this.mInitiatedAdUnits != null) {
                    if (VungleAdapter.this.mInitiatedAdUnits.contains("Rewarded Video")) {
                        for (final Map.Entry<K, RewardedVideoSmashListener> entry : VungleAdapter.this.mRewardedVideoPlacementToListenerMap.entrySet()) {
                            if (entry.getValue() != null) {
                                entry.getValue().onRewardedVideoAvailabilityChanged(false);
                            }
                        }
                    }
                    if (VungleAdapter.this.mInitiatedAdUnits.contains("Interstitial")) {
                        for (final Map.Entry<K, InterstitialSmashListener> entry2 : VungleAdapter.this.mInterstitialPlacementToListenerMap.entrySet()) {
                            if (entry2.getValue() != null) {
                                entry2.getValue().onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Vungle failed to init: " + t.getMessage(), "Interstitial"));
                            }
                        }
                    }
                }
            }
            
            public void onSuccess() {
                VungleAdapter.access$900(VungleAdapter.this, IronSourceLogger$IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": Succeeded to initialize SDK ", 1);
                Object access$1000 = VungleAdapter.this.mInitState;
                synchronized (access$1000) {
                    VungleAdapter.this.setInitState(EInitState.INIT_SUCCESS);
                    if (VungleAdapter.this.mIsConsent != null) {
                        Vungle$Consent vungle$Consent;
                        if (VungleAdapter.this.mIsConsent) {
                            vungle$Consent = Vungle$Consent.OPTED_IN;
                        }
                        else {
                            vungle$Consent = Vungle$Consent.OPTED_OUT;
                        }
                        Vungle.updateConsentStatus(vungle$Consent, "1.0.0");
                    }
                    // monitorexit(access$1000)
                    if (VungleAdapter.this.mInitiatedAdUnits == null) {
                        return;
                    }
                    if (VungleAdapter.this.mInitiatedAdUnits.contains("Rewarded Video")) {
                        final Iterator<Map.Entry<?, ?>> iterator = VungleAdapter.this.mRewardedVideoPlacementToListenerMap.entrySet().iterator();
                        while (iterator.hasNext()) {
                            access$1000 = iterator.next();
                            VungleAdapter.this.loadRewardedVideoAd(((Map.Entry<String, V>)access$1000).getKey());
                        }
                    }
                }
                if (VungleAdapter.this.mInitiatedAdUnits.contains("Interstitial")) {
                    for (final Map.Entry<K, InterstitialSmashListener> entry : VungleAdapter.this.mInterstitialPlacementToListenerMap.entrySet()) {
                        if (entry.getValue() != null) {
                            entry.getValue().onInterstitialInitSuccess();
                        }
                    }
                }
            }
        });
    }
    
    private void loadRewardedVideoAd(final String s) {
        this.log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + ": loadRewardedVideoAd placementId " + s, 1);
        Vungle.loadAd(s, (LoadAdCallback)new LoadAdCallback() {
            public void onAdLoad(final String s) {
                VungleAdapter.access$2200(VungleAdapter.this, IronSourceLogger$IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": RewardedVideo Ad loaded for placementReferenceId: " + s, 1);
                if (VungleAdapter.this.mRewardedVideoPlacementToListenerMap.get(s) != null) {
                    VungleAdapter.this.mRewardedVideoPlacementToListenerMap.get(s).onRewardedVideoAvailabilityChanged(true);
                }
            }
            
            public void onError(final String s, final Throwable t) {
                VungleAdapter.access$2500(VungleAdapter.this, IronSourceLogger$IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": RewardedVideo Ad failed to load for placementReferenceId: " + s + ", error: " + t.getMessage(), 1);
                if (VungleAdapter.this.mRewardedVideoPlacementToListenerMap.get(s) != null) {
                    VungleAdapter.this.mRewardedVideoPlacementToListenerMap.get(s).onRewardedVideoAvailabilityChanged(false);
                }
            }
        });
    }
    
    private void setInitState(final EInitState mInitState) {
        synchronized (this) {
            this.log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + ":init state changed from " + this.mInitState + " to " + mInitState + ")", 1);
            this.mInitState = mInitState;
        }
    }
    
    public static VungleAdapter startAdapter(final String s) {
        return new VungleAdapter(s);
    }
    
    public void fetchRewardedVideo(final JSONObject jsonObject) {
    }
    
    public String getCoreSDKVersion() {
        return "6.3.17";
    }
    
    public String getVersion() {
        return "4.1.4";
    }
    
    public void initInterstitial(final Activity activity, final String s, final String s2, final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        while (true) {
            Label_0193: {
                Label_0178: {
                    synchronized (this) {
                        if (TextUtils.isEmpty((CharSequence)jsonObject.optString("AppID")) || TextUtils.isEmpty((CharSequence)jsonObject.optString("PlacementId"))) {
                            if (interstitialSmashListener != null) {
                                interstitialSmashListener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Missing params", "Interstitial"));
                            }
                        }
                        else {
                            if (!TextUtils.isEmpty((CharSequence)jsonObject.optString("PlacementId")) && interstitialSmashListener != null) {
                                this.mInterstitialPlacementToListenerMap.put(jsonObject.optString("PlacementId"), interstitialSmashListener);
                            }
                            this.addInitiatedAdUnit("Interstitial");
                            HashSet<String> allSettingsForProvider = new HashSet<String>();
                            switch (this.getCurrentInitState()) {
                                case NOT_INIT: {
                                    if (interstitialSmashListener != null) {
                                        allSettingsForProvider = (HashSet<String>)((InterstitialSmash)interstitialSmashListener).getAllSettingsForProvider("PlacementId");
                                    }
                                    this.initVungleSdk(activity, jsonObject.optString("AppID"), s2, allSettingsForProvider);
                                }
                                case INIT_IN_PROGRESS: {
                                    break;
                                }
                                case INIT_SUCCESS: {
                                    break Label_0178;
                                }
                                case INIT_FAIL: {
                                    break Label_0193;
                                }
                                default: {}
                            }
                        }
                        return;
                    }
                }
                if (interstitialSmashListener != null) {
                    interstitialSmashListener.onInterstitialInitSuccess();
                    return;
                }
                return;
            }
            if (interstitialSmashListener != null) {
                interstitialSmashListener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Init Failed", "Interstitial"));
            }
        }
    }
    
    public void initRewardedVideo(final Activity activity, final String s, final String s2, final JSONObject jsonObject, final RewardedVideoSmashListener rewardedVideoSmashListener) {
        while (true) {
            Label_0217: {
                Label_0174: {
                    synchronized (this) {
                        if (TextUtils.isEmpty((CharSequence)jsonObject.optString("AppID")) || TextUtils.isEmpty((CharSequence)jsonObject.optString("PlacementId"))) {
                            if (rewardedVideoSmashListener != null) {
                                rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
                            }
                        }
                        else {
                            if (!TextUtils.isEmpty((CharSequence)jsonObject.optString("PlacementId")) && rewardedVideoSmashListener != null) {
                                this.mRewardedVideoPlacementToListenerMap.put(jsonObject.optString("PlacementId"), rewardedVideoSmashListener);
                            }
                            this.addInitiatedAdUnit("Rewarded Video");
                            HashSet<String> allSettingsForProvider = new HashSet<String>();
                            switch (this.getCurrentInitState()) {
                                case NOT_INIT: {
                                    if (rewardedVideoSmashListener != null) {
                                        allSettingsForProvider = (HashSet<String>)((RewardedVideoSmash)rewardedVideoSmashListener).getAllSettingsForProvider("PlacementId");
                                    }
                                    this.initVungleSdk(activity, jsonObject.optString("AppID"), s2, allSettingsForProvider);
                                }
                                case INIT_IN_PROGRESS: {
                                    break;
                                }
                                case INIT_SUCCESS: {
                                    break Label_0174;
                                }
                                case INIT_FAIL: {
                                    break Label_0217;
                                }
                                default: {}
                            }
                        }
                        return;
                    }
                }
                if (!Vungle.canPlayAd(jsonObject.optString("PlacementId"))) {
                    this.loadRewardedVideoAd(jsonObject.optString("PlacementId"));
                    return;
                }
                if (rewardedVideoSmashListener != null) {
                    rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(true);
                    return;
                }
                return;
            }
            if (rewardedVideoSmashListener != null) {
                rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
            }
        }
    }
    
    public boolean isInterstitialReady(final JSONObject jsonObject) {
        return Vungle.isInitialized() && Vungle.canPlayAd(jsonObject.optString("PlacementId"));
    }
    
    public boolean isRewardedVideoAvailable(final JSONObject jsonObject) {
        return Vungle.isInitialized() && Vungle.canPlayAd(jsonObject.optString("PlacementId"));
    }
    
    public void loadInterstitial(final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        if (Vungle.isInitialized()) {
            final String optString = jsonObject.optString("PlacementId");
            if (!Vungle.canPlayAd(optString)) {
                Vungle.loadAd(optString, (LoadAdCallback)new LoadAdCallback() {
                    public void onAdLoad(final String s) {
                        VungleAdapter.access$400(VungleAdapter.this, IronSourceLogger$IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": Interstitial loaded for placementReferenceId: " + s, 1);
                        if (interstitialSmashListener != null) {
                            interstitialSmashListener.onInterstitialAdReady();
                        }
                    }
                    
                    public void onError(final String s, final Throwable t) {
                        VungleAdapter.access$500(VungleAdapter.this, IronSourceLogger$IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": Interstitial failed to load for placementReferenceId: " + s + " ,error: " + t.getMessage(), 1);
                        if (interstitialSmashListener != null) {
                            interstitialSmashListener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("Error loading Ad: " + t.getMessage()));
                        }
                    }
                });
                return;
            }
            if (interstitialSmashListener != null) {
                interstitialSmashListener.onInterstitialAdReady();
            }
        }
    }
    
    public void onPause(final Activity activity) {
    }
    
    public void onResume(final Activity activity) {
    }
    
    protected void setConsent(final boolean b) {
        synchronized (this.mInitState) {
            if (this.getCurrentInitState() == EInitState.INIT_SUCCESS) {
                Vungle$Consent vungle$Consent;
                if (b) {
                    vungle$Consent = Vungle$Consent.OPTED_IN;
                }
                else {
                    vungle$Consent = Vungle$Consent.OPTED_OUT;
                }
                Vungle.updateConsentStatus(vungle$Consent, "1.0.0");
            }
            else {
                this.mIsConsent = b;
            }
        }
    }
    
    public void showInterstitial(final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        if (Vungle.canPlayAd(jsonObject.optString("PlacementId"))) {
            Vungle.playAd(jsonObject.optString("PlacementId"), new AdConfig(), (PlayAdCallback)new PlayAdCallback() {
                public void onAdEnd(final String s, final boolean b, final boolean b2) {
                    VungleAdapter.access$700(VungleAdapter.this, IronSourceLogger$IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": Interstitial ad ended for placementReferenceId: " + s, 1);
                    if (interstitialSmashListener != null) {
                        if (b2) {
                            interstitialSmashListener.onInterstitialAdClicked();
                        }
                        interstitialSmashListener.onInterstitialAdClosed();
                    }
                }
                
                public void onAdStart(final String s) {
                    VungleAdapter.access$600(VungleAdapter.this, IronSourceLogger$IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": Interstitial ad started for placementReferenceId: " + s, 1);
                    if (interstitialSmashListener != null) {
                        interstitialSmashListener.onInterstitialAdOpened();
                        interstitialSmashListener.onInterstitialAdShowSucceeded();
                    }
                }
                
                public void onError(final String s, final Throwable t) {
                    VungleAdapter.access$800(VungleAdapter.this, IronSourceLogger$IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": Interstitial ad failed to show for placementReferenceId: " + s + "error: " + t.getMessage(), 1);
                    if (interstitialSmashListener != null) {
                        interstitialSmashListener.onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
                    }
                }
            });
        }
        else if (interstitialSmashListener != null) {
            interstitialSmashListener.onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
        }
    }
    
    public void showRewardedVideo(final JSONObject jsonObject, final RewardedVideoSmashListener rewardedVideoSmashListener) {
        final AdConfig adConfig = new AdConfig();
        if (Vungle.canPlayAd(jsonObject.optString("PlacementId"))) {
            Vungle.setIncentivizedFields(this.getDynamicUserId(), (String)null, (String)null, (String)null, (String)null);
            Vungle.playAd(jsonObject.optString("PlacementId"), adConfig, (PlayAdCallback)new PlayAdCallback() {
                public void onAdEnd(final String s, final boolean b, final boolean b2) {
                    VungleAdapter.access$100(VungleAdapter.this, IronSourceLogger$IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": RewardedVideo ad ended for placementReferenceId: " + s, 1);
                    if (rewardedVideoSmashListener != null) {
                        if (b2) {
                            rewardedVideoSmashListener.onRewardedVideoAdClicked();
                        }
                        rewardedVideoSmashListener.onRewardedVideoAdEnded();
                        if (b) {
                            rewardedVideoSmashListener.onRewardedVideoAdRewarded();
                        }
                        rewardedVideoSmashListener.onRewardedVideoAdClosed();
                        final boolean canPlayAd = Vungle.canPlayAd(s);
                        rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(canPlayAd);
                        if (!canPlayAd) {
                            VungleAdapter.this.loadRewardedVideoAd(s);
                        }
                    }
                }
                
                public void onAdStart(final String s) {
                    VungleAdapter.access$000(VungleAdapter.this, IronSourceLogger$IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": RewardedVideo ad started for placementReferenceId: " + s, 1);
                    if (rewardedVideoSmashListener != null) {
                        rewardedVideoSmashListener.onRewardedVideoAdOpened();
                        rewardedVideoSmashListener.onRewardedVideoAdStarted();
                    }
                }
                
                public void onError(final String s, final Throwable t) {
                    VungleAdapter.access$300(VungleAdapter.this, IronSourceLogger$IronSourceTag.ADAPTER_API, VungleAdapter.this.getProviderName() + ": RewardedVideo ad failed to show for placementReferenceId: " + s + "error: " + t.getMessage(), 1);
                    if (rewardedVideoSmashListener != null) {
                        rewardedVideoSmashListener.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Rewarded Video"));
                        rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
                    }
                }
            });
        }
    }
    
    private enum EInitState
    {
        INIT_FAIL, 
        INIT_IN_PROGRESS, 
        INIT_SUCCESS, 
        NOT_INIT;
    }
}
