// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.adapters.tapjoy;

import com.tapjoy.TJError;
import com.tapjoy.TJActionRequest;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.logger.IronSourceLogger$IronSourceTag;
import android.text.TextUtils;
import android.content.Context;
import java.util.Iterator;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import java.util.Map;
import com.tapjoy.TJConnectListener;
import com.tapjoy.TapjoyLog;
import com.tapjoy.Tapjoy;
import java.util.Hashtable;
import org.json.JSONObject;
import com.ironsource.mediationsdk.IntegrationData;
import android.app.Activity;
import java.util.HashSet;
import com.tapjoy.TJPlacement;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Set;
import com.tapjoy.TJPlacementVideoListener;
import com.tapjoy.TJPlacementListener;
import com.ironsource.mediationsdk.AbstractAdapter;

public class TapjoyAdapter extends AbstractAdapter implements TJPlacementListener, TJPlacementVideoListener
{
    private static final String VERSION = "4.1.3";
    private final String PLACEMENT_NAME;
    private final String SDK_KEY;
    private EInitState mInitState;
    private Set<String> mInitiatedAdUnits;
    private ConcurrentHashMap<String, TJPlacement> mInterstitialPlacementToAdMap;
    private ConcurrentHashMap<String, TJPlacement> mRewardedVideoPlacementToAdMap;
    
    private TapjoyAdapter(final String s) {
        super(s);
        this.mInitState = EInitState.NOT_INIT;
        this.SDK_KEY = "sdkKey";
        this.PLACEMENT_NAME = "placementName";
        this.mRewardedVideoPlacementToAdMap = new ConcurrentHashMap<String, TJPlacement>();
        this.mInterstitialPlacementToAdMap = new ConcurrentHashMap<String, TJPlacement>();
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
        final IntegrationData integrationData = new IntegrationData("Tapjoy", "4.1.3");
        integrationData.activities = new String[] { "com.tapjoy.TJAdUnitActivity", "com.tapjoy.mraid.view.ActionHandler", "com.tapjoy.mraid.view.Browser", "com.tapjoy.TJContentActivity" };
        return integrationData;
    }
    
    private void initSDK(final Activity activity, final String s, final JSONObject jsonObject) {
        this.setInitState(EInitState.INIT_IN_PROGRESS);
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        int adaptersDebugEnabled = 0;
        while (true) {
            try {
                adaptersDebugEnabled = (this.isAdaptersDebugEnabled() ? 1 : 0);
                if (adaptersDebugEnabled != 0) {
                    hashtable.put("TJC_OPTION_ENABLE_LOGGING", "true");
                    Tapjoy.setDebugEnabled(true);
                    TapjoyLog.setDebugEnabled(true);
                }
                else {
                    hashtable.put("TJC_OPTION_ENABLE_LOGGING", "false");
                    Tapjoy.setDebugEnabled(false);
                    TapjoyLog.setDebugEnabled(false);
                }
                Tapjoy.connect(activity.getApplicationContext(), jsonObject.optString("sdkKey"), hashtable, new TJConnectListener() {
                    @Override
                    public void onConnectFailure() {
                        if (TapjoyAdapter.this.mInitiatedAdUnits != null) {
                            if (TapjoyAdapter.this.mInitiatedAdUnits.contains("Rewarded Video")) {
                                for (final Map.Entry<K, RewardedVideoSmashListener> entry : TapjoyAdapter.this.mRewardedVideoPlacementToListenerMap.entrySet()) {
                                    if (entry.getValue() != null) {
                                        entry.getValue().onRewardedVideoAvailabilityChanged(false);
                                    }
                                }
                            }
                            if (TapjoyAdapter.this.mInitiatedAdUnits.contains("Interstitial")) {
                                for (final Map.Entry<K, InterstitialSmashListener> entry2 : TapjoyAdapter.this.mInterstitialPlacementToListenerMap.entrySet()) {
                                    if (entry2.getValue() != null) {
                                        entry2.getValue().onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Not Connected", "Interstitial"));
                                    }
                                }
                            }
                        }
                    }
                    
                    @Override
                    public void onConnectSuccess() {
                        TapjoyAdapter.this.setInitState(EInitState.INIT_SUCCESS);
                        Tapjoy.setUserID(s);
                        if (TapjoyAdapter.this.mInitiatedAdUnits != null) {
                            if (TapjoyAdapter.this.mInitiatedAdUnits.contains("Rewarded Video")) {
                                for (final Map.Entry<K, RewardedVideoSmashListener> entry : TapjoyAdapter.this.mRewardedVideoPlacementToListenerMap.entrySet()) {
                                    if (!Tapjoy.isConnected()) {
                                        if (entry.getValue() == null) {
                                            continue;
                                        }
                                        entry.getValue().onRewardedVideoAvailabilityChanged(false);
                                    }
                                    else {
                                        final TJPlacement tjPlacement = new TJPlacement((Context)activity, (String)entry.getKey(), TapjoyAdapter.this);
                                        tjPlacement.setMediationName("supersonic");
                                        tjPlacement.setAdapterVersion("4.1.3");
                                        tjPlacement.setVideoListener(TapjoyAdapter.this);
                                        if (!TextUtils.isEmpty((CharSequence)entry.getKey())) {
                                            TapjoyAdapter.this.mRewardedVideoPlacementToAdMap.put(entry.getKey(), tjPlacement);
                                        }
                                        tjPlacement.requestContent();
                                    }
                                }
                            }
                            if (TapjoyAdapter.this.mInitiatedAdUnits.contains("Interstitial")) {
                                for (final Map.Entry<K, InterstitialSmashListener> entry2 : TapjoyAdapter.this.mInterstitialPlacementToListenerMap.entrySet()) {
                                    if (!Tapjoy.isConnected()) {
                                        if (entry2.getValue() == null) {
                                            continue;
                                        }
                                        entry2.getValue().onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Not Connected", "Interstitial"));
                                    }
                                    else {
                                        final TJPlacement tjPlacement2 = new TJPlacement((Context)activity, entry2.getKey(), TapjoyAdapter.this);
                                        tjPlacement2.setMediationName("supersonic");
                                        tjPlacement2.setAdapterVersion("4.1.3");
                                        if (!TextUtils.isEmpty((CharSequence)entry2.getKey())) {
                                            TapjoyAdapter.this.mInterstitialPlacementToAdMap.put(entry2.getKey(), tjPlacement2);
                                        }
                                        if (entry2.getValue() == null) {
                                            continue;
                                        }
                                        entry2.getValue().onInterstitialInitSuccess();
                                    }
                                }
                            }
                        }
                    }
                });
            }
            catch (NoSuchMethodError noSuchMethodError) {
                continue;
            }
            break;
        }
    }
    
    private void setInitState(final EInitState mInitState) {
        synchronized (this) {
            this.log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + ":init state changed from " + this.mInitState + " to " + mInitState + ")", 1);
            this.mInitState = mInitState;
        }
    }
    
    public static TapjoyAdapter startAdapter(final String s) {
        return new TapjoyAdapter(s);
    }
    
    public void fetchRewardedVideo(final JSONObject jsonObject) {
        if (this.mRewardedVideoPlacementToAdMap.get(jsonObject.optString("placementName")) != null) {
            this.mRewardedVideoPlacementToAdMap.get(jsonObject.optString("placementName")).requestContent();
        }
    }
    
    public String getCoreSDKVersion() {
        return Tapjoy.getVersion();
    }
    
    public String getVersion() {
        return "4.1.3";
    }
    
    public void initInterstitial(final Activity activity, final String s, final String s2, final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        if (TextUtils.isEmpty((CharSequence)jsonObject.optString("sdkKey")) || TextUtils.isEmpty((CharSequence)jsonObject.optString("placementName"))) {
            if (interstitialSmashListener != null) {
                interstitialSmashListener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Missing params", "Interstitial"));
            }
        }
        else {
            if (!TextUtils.isEmpty((CharSequence)jsonObject.optString("placementName")) && interstitialSmashListener != null) {
                this.mInterstitialPlacementToListenerMap.put(jsonObject.optString("placementName"), interstitialSmashListener);
            }
            this.addInitiatedAdUnit("Interstitial");
            switch (this.getCurrentInitState()) {
                case INIT_IN_PROGRESS: {
                    break;
                }
                default: {}
                case NOT_INIT: {
                    this.initSDK(activity, s2, jsonObject);
                }
                case INIT_SUCCESS: {
                    if (!Tapjoy.isConnected()) {
                        if (interstitialSmashListener != null) {
                            interstitialSmashListener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Init Failed", "Interstitial"));
                        }
                    }
                    else if (!TextUtils.isEmpty((CharSequence)jsonObject.optString("placementName"))) {
                        final TJPlacement tjPlacement = new TJPlacement((Context)activity, jsonObject.optString("placementName"), this);
                        tjPlacement.setMediationName("supersonic");
                        tjPlacement.setAdapterVersion("4.1.3");
                        this.mInterstitialPlacementToAdMap.put(jsonObject.optString("placementName"), tjPlacement);
                        if (interstitialSmashListener != null) {
                            interstitialSmashListener.onInterstitialInitSuccess();
                        }
                    }
                    if (interstitialSmashListener != null) {
                        interstitialSmashListener.onInterstitialInitSuccess();
                        return;
                    }
                    break;
                }
                case INIT_FAIL: {
                    if (interstitialSmashListener != null) {
                        interstitialSmashListener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Init Failed", "Interstitial"));
                        return;
                    }
                    break;
                }
            }
        }
    }
    
    public void initRewardedVideo(final Activity activity, final String s, final String s2, final JSONObject jsonObject, final RewardedVideoSmashListener rewardedVideoSmashListener) {
        if (TextUtils.isEmpty((CharSequence)jsonObject.optString("sdkKey")) || TextUtils.isEmpty((CharSequence)jsonObject.optString("placementName"))) {
            if (rewardedVideoSmashListener != null) {
                rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
            }
        }
        else {
            if (!TextUtils.isEmpty((CharSequence)jsonObject.optString("placementName")) && rewardedVideoSmashListener != null) {
                this.mRewardedVideoPlacementToListenerMap.put(jsonObject.optString("placementName"), rewardedVideoSmashListener);
            }
            this.addInitiatedAdUnit("Rewarded Video");
            switch (this.getCurrentInitState()) {
                case INIT_IN_PROGRESS: {
                    break;
                }
                default: {}
                case NOT_INIT: {
                    this.initSDK(activity, s2, jsonObject);
                }
                case INIT_SUCCESS: {
                    if (!Tapjoy.isConnected()) {
                        if (rewardedVideoSmashListener != null) {
                            rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
                            return;
                        }
                        break;
                    }
                    else {
                        if (!TextUtils.isEmpty((CharSequence)jsonObject.optString("placementName"))) {
                            final TJPlacement tjPlacement = new TJPlacement((Context)activity, jsonObject.optString("placementName"), this);
                            tjPlacement.setMediationName("supersonic");
                            tjPlacement.setAdapterVersion("4.1.3");
                            tjPlacement.setVideoListener(this);
                            this.mRewardedVideoPlacementToAdMap.put(jsonObject.optString("placementName"), tjPlacement);
                            tjPlacement.requestContent();
                            return;
                        }
                        break;
                    }
                    break;
                }
                case INIT_FAIL: {
                    if (rewardedVideoSmashListener != null) {
                        rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
                        return;
                    }
                    break;
                }
            }
        }
    }
    
    public boolean isInterstitialReady(final JSONObject jsonObject) {
        return this.mInterstitialPlacementToAdMap.get(jsonObject.optString("placementName")) != null && this.mInterstitialPlacementToAdMap.get(jsonObject.optString("placementName")).isContentReady();
    }
    
    public boolean isRewardedVideoAvailable(final JSONObject jsonObject) {
        return this.mRewardedVideoPlacementToAdMap.get(jsonObject.optString("placementName")) != null && this.mRewardedVideoPlacementToAdMap.get(jsonObject.optString("placementName")).isContentReady();
    }
    
    public void loadInterstitial(final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        if (this.mInterstitialPlacementToAdMap.get(jsonObject.optString("placementName")) != null) {
            this.mInterstitialPlacementToAdMap.get(jsonObject.optString("placementName")).requestContent();
        }
        else if (interstitialSmashListener != null) {
            interstitialSmashListener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("Load error"));
        }
    }
    
    public void onContentDismiss(final TJPlacement tjPlacement) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onContentDismiss(tjPlacement: " + tjPlacement.getName() + ")", 1);
        if (this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()) != null) {
            this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()).onRewardedVideoAdClosed();
        }
        if (this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName()) != null) {
            this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName()).onInterstitialAdClosed();
        }
    }
    
    public void onContentReady(final TJPlacement tjPlacement) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onContentReady(tjPlacement: " + tjPlacement.getName() + ")", 1);
        if (this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()) != null) {
            this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()).onRewardedVideoAvailabilityChanged(true);
        }
        if (this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName()) != null) {
            this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName()).onInterstitialAdReady();
        }
    }
    
    public void onContentShow(final TJPlacement tjPlacement) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onContentShow(tjPlacement: " + tjPlacement.getName() + ")", 1);
        if (this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()) != null) {
            this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()).onRewardedVideoAdOpened();
            this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()).onRewardedVideoAvailabilityChanged(false);
        }
        if (this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName()) != null) {
            this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName()).onInterstitialAdOpened();
            this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName()).onInterstitialAdShowSucceeded();
        }
    }
    
    public void onPurchaseRequest(final TJPlacement tjPlacement, final TJActionRequest tjActionRequest, final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onPurchaseRequest(tjPlacement: " + tjPlacement.getName(), 1);
    }
    
    public void onRequestFailure(final TJPlacement tjPlacement, final TJError tjError) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onRequestFailure(tjPlacement: " + tjPlacement.getName() + ", tjError: " + tjError.message + ")", 1);
        if (this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()) != null) {
            this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()).onRewardedVideoAvailabilityChanged(false);
        }
        if (this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName()) != null) {
            this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName()).onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError(tjError.message));
        }
    }
    
    public void onRequestSuccess(final TJPlacement tjPlacement) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onRequestSuccess(tjPlacement: " + tjPlacement.getName(), 1);
        if (!tjPlacement.isContentAvailable()) {
            if (this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()) != null) {
                this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()).onRewardedVideoAvailabilityChanged(false);
            }
            if (this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName()) != null) {
                this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName()).onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("No Content Available"));
            }
        }
    }
    
    public void onRewardRequest(final TJPlacement tjPlacement, final TJActionRequest tjActionRequest, final String s, final int n) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, "onRewardRequest(tjPlacement: " + tjPlacement.getName(), 1);
    }
    
    public void onVideoComplete(final TJPlacement tjPlacement) {
        this.log(IronSourceLogger$IronSourceTag.INTERNAL, this.getProviderName() + ":onVideoComplete()", 1);
        if (this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()) != null) {
            this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()).onRewardedVideoAdEnded();
            this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()).onRewardedVideoAdRewarded();
        }
    }
    
    public void onVideoError(final TJPlacement tjPlacement, final String s) {
        this.log(IronSourceLogger$IronSourceTag.INTERNAL, this.getProviderName() + ":onVideoError(error: " + s + ")", 1);
    }
    
    public void onVideoStart(final TJPlacement tjPlacement) {
        this.log(IronSourceLogger$IronSourceTag.INTERNAL, this.getProviderName() + ":onRewardedVideoAdStarted()", 1);
        if (this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()) != null) {
            this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()).onRewardedVideoAdStarted();
        }
    }
    
    protected void setConsent(final boolean b) {
        String userConsent;
        if (b) {
            userConsent = "1";
        }
        else {
            userConsent = "0";
        }
        Tapjoy.setUserConsent(userConsent);
    }
    
    public void showInterstitial(final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        if (this.mInterstitialPlacementToAdMap.get(jsonObject.optString("placementName")) != null && this.mInterstitialPlacementToAdMap.get(jsonObject.optString("placementName")).isContentReady()) {
            this.mInterstitialPlacementToAdMap.get(jsonObject.optString("placementName")).showContent();
        }
        else if (interstitialSmashListener != null) {
            interstitialSmashListener.onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
        }
    }
    
    public void showRewardedVideo(final JSONObject jsonObject, final RewardedVideoSmashListener rewardedVideoSmashListener) {
        if (this.mRewardedVideoPlacementToAdMap.get(jsonObject.optString("placementName")) != null && this.mRewardedVideoPlacementToAdMap.get(jsonObject.optString("placementName")).isContentReady()) {
            this.mRewardedVideoPlacementToAdMap.get(jsonObject.optString("placementName")).showContent();
        }
        else if (rewardedVideoSmashListener != null) {
            rewardedVideoSmashListener.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Rewarded Video"));
            rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
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
