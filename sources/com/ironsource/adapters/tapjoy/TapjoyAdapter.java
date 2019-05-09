package com.ironsource.adapters.tapjoy;

import android.app.Activity;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.ironsource.mediationsdk.AbstractAdapter;
import com.ironsource.mediationsdk.IntegrationData;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.tapjoy.TJActionRequest;
import com.tapjoy.TJConnectListener;
import com.tapjoy.TJError;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJPlacementVideoListener;
import com.tapjoy.Tapjoy;
import com.tapjoy.TapjoyConnectFlag;
import com.tapjoy.TapjoyLog;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

public class TapjoyAdapter extends AbstractAdapter implements TJPlacementListener, TJPlacementVideoListener {
    private static final String VERSION = "4.1.3";
    private final String PLACEMENT_NAME = "placementName";
    private final String SDK_KEY = "sdkKey";
    private EInitState mInitState = EInitState.NOT_INIT;
    private Set<String> mInitiatedAdUnits;
    private ConcurrentHashMap<String, TJPlacement> mInterstitialPlacementToAdMap = new ConcurrentHashMap();
    private ConcurrentHashMap<String, TJPlacement> mRewardedVideoPlacementToAdMap = new ConcurrentHashMap();

    private enum EInitState {
        NOT_INIT,
        INIT_IN_PROGRESS,
        INIT_SUCCESS,
        INIT_FAIL
    }

    public static TapjoyAdapter startAdapter(String providerName) {
        return new TapjoyAdapter(providerName);
    }

    private TapjoyAdapter(String providerName) {
        super(providerName);
    }

    public static IntegrationData getIntegrationData(Activity activity) {
        IntegrationData ret = new IntegrationData("Tapjoy", VERSION);
        ret.activities = new String[]{"com.tapjoy.TJAdUnitActivity", "com.tapjoy.mraid.view.ActionHandler", "com.tapjoy.mraid.view.Browser", "com.tapjoy.TJContentActivity"};
        return ret;
    }

    public String getVersion() {
        return VERSION;
    }

    public String getCoreSDKVersion() {
        return Tapjoy.getVersion();
    }

    protected void setConsent(boolean consent) {
        Tapjoy.setUserConsent(consent ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
    }

    private void initSDK(final Activity activity, final String userId, JSONObject config) {
        setInitState(EInitState.INIT_IN_PROGRESS);
        Hashtable<String, Object> connectFlags = new Hashtable();
        boolean isDebugEnabled = false;
        try {
            isDebugEnabled = isAdaptersDebugEnabled();
        } catch (NoSuchMethodError e) {
        }
        if (isDebugEnabled) {
            connectFlags.put(TapjoyConnectFlag.ENABLE_LOGGING, "true");
            Tapjoy.setDebugEnabled(true);
            TapjoyLog.setDebugEnabled(true);
        } else {
            connectFlags.put(TapjoyConnectFlag.ENABLE_LOGGING, "false");
            Tapjoy.setDebugEnabled(false);
            TapjoyLog.setDebugEnabled(false);
        }
        Tapjoy.connect(activity.getApplicationContext(), config.optString("sdkKey"), connectFlags, new TJConnectListener() {
            public void onConnectSuccess() {
                TapjoyAdapter.this.setInitState(EInitState.INIT_SUCCESS);
                Tapjoy.setUserID(userId);
                if (TapjoyAdapter.this.mInitiatedAdUnits != null) {
                    if (TapjoyAdapter.this.mInitiatedAdUnits.contains(IronSourceConstants.REWARDED_VIDEO_AD_UNIT)) {
                        for (Entry<String, RewardedVideoSmashListener> entry : TapjoyAdapter.this.mRewardedVideoPlacementToListenerMap.entrySet()) {
                            if (Tapjoy.isConnected()) {
                                TJPlacement rvPlacement = new TJPlacement(activity, (String) entry.getKey(), TapjoyAdapter.this);
                                rvPlacement.setMediationName("supersonic");
                                rvPlacement.setAdapterVersion(TapjoyAdapter.VERSION);
                                rvPlacement.setVideoListener(TapjoyAdapter.this);
                                if (!TextUtils.isEmpty((CharSequence) entry.getKey())) {
                                    TapjoyAdapter.this.mRewardedVideoPlacementToAdMap.put(entry.getKey(), rvPlacement);
                                }
                                rvPlacement.requestContent();
                            } else if (entry.getValue() != null) {
                                ((RewardedVideoSmashListener) entry.getValue()).onRewardedVideoAvailabilityChanged(false);
                            }
                        }
                    }
                    if (TapjoyAdapter.this.mInitiatedAdUnits.contains("Interstitial")) {
                        for (Entry<String, InterstitialSmashListener> entry2 : TapjoyAdapter.this.mInterstitialPlacementToListenerMap.entrySet()) {
                            if (Tapjoy.isConnected()) {
                                TJPlacement isPlacement = new TJPlacement(activity, (String) entry2.getKey(), TapjoyAdapter.this);
                                isPlacement.setMediationName("supersonic");
                                isPlacement.setAdapterVersion(TapjoyAdapter.VERSION);
                                if (!TextUtils.isEmpty((CharSequence) entry2.getKey())) {
                                    TapjoyAdapter.this.mInterstitialPlacementToAdMap.put(entry2.getKey(), isPlacement);
                                }
                                if (entry2.getValue() != null) {
                                    ((InterstitialSmashListener) entry2.getValue()).onInterstitialInitSuccess();
                                }
                            } else if (entry2.getValue() != null) {
                                ((InterstitialSmashListener) entry2.getValue()).onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Not Connected", "Interstitial"));
                            }
                        }
                    }
                }
            }

            public void onConnectFailure() {
                if (TapjoyAdapter.this.mInitiatedAdUnits != null) {
                    if (TapjoyAdapter.this.mInitiatedAdUnits.contains(IronSourceConstants.REWARDED_VIDEO_AD_UNIT)) {
                        for (Entry<String, RewardedVideoSmashListener> entry : TapjoyAdapter.this.mRewardedVideoPlacementToListenerMap.entrySet()) {
                            if (entry.getValue() != null) {
                                ((RewardedVideoSmashListener) entry.getValue()).onRewardedVideoAvailabilityChanged(false);
                            }
                        }
                    }
                    if (TapjoyAdapter.this.mInitiatedAdUnits.contains("Interstitial")) {
                        for (Entry<String, InterstitialSmashListener> entry2 : TapjoyAdapter.this.mInterstitialPlacementToListenerMap.entrySet()) {
                            if (entry2.getValue() != null) {
                                ((InterstitialSmashListener) entry2.getValue()).onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Not Connected", "Interstitial"));
                            }
                        }
                    }
                }
            }
        });
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

    public void initRewardedVideo(Activity activity, String appKey, String userId, JSONObject config, RewardedVideoSmashListener listener) {
        if (!TextUtils.isEmpty(config.optString("sdkKey")) && !TextUtils.isEmpty(config.optString("placementName"))) {
            if (!(TextUtils.isEmpty(config.optString("placementName")) || listener == null)) {
                this.mRewardedVideoPlacementToListenerMap.put(config.optString("placementName"), listener);
            }
            addInitiatedAdUnit(IronSourceConstants.REWARDED_VIDEO_AD_UNIT);
            switch (getCurrentInitState()) {
                case NOT_INIT:
                    initSDK(activity, userId, config);
                    return;
                case INIT_IN_PROGRESS:
                    return;
                case INIT_SUCCESS:
                    if (Tapjoy.isConnected()) {
                        if (!TextUtils.isEmpty(config.optString("placementName"))) {
                            TJPlacement rvPlacement = new TJPlacement(activity, config.optString("placementName"), this);
                            rvPlacement.setMediationName("supersonic");
                            rvPlacement.setAdapterVersion(VERSION);
                            rvPlacement.setVideoListener(this);
                            this.mRewardedVideoPlacementToAdMap.put(config.optString("placementName"), rvPlacement);
                            rvPlacement.requestContent();
                            return;
                        }
                        return;
                    } else if (listener != null) {
                        listener.onRewardedVideoAvailabilityChanged(false);
                        return;
                    } else {
                        return;
                    }
                case INIT_FAIL:
                    if (listener != null) {
                        listener.onRewardedVideoAvailabilityChanged(false);
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else if (listener != null) {
            listener.onRewardedVideoAvailabilityChanged(false);
        }
    }

    public void fetchRewardedVideo(JSONObject config) {
        if (this.mRewardedVideoPlacementToAdMap.get(config.optString("placementName")) != null) {
            ((TJPlacement) this.mRewardedVideoPlacementToAdMap.get(config.optString("placementName"))).requestContent();
        }
    }

    public void showRewardedVideo(JSONObject config, RewardedVideoSmashListener listener) {
        if (this.mRewardedVideoPlacementToAdMap.get(config.optString("placementName")) != null && ((TJPlacement) this.mRewardedVideoPlacementToAdMap.get(config.optString("placementName"))).isContentReady()) {
            ((TJPlacement) this.mRewardedVideoPlacementToAdMap.get(config.optString("placementName"))).showContent();
        } else if (listener != null) {
            listener.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
            listener.onRewardedVideoAvailabilityChanged(false);
        }
    }

    public boolean isRewardedVideoAvailable(JSONObject config) {
        return this.mRewardedVideoPlacementToAdMap.get(config.optString("placementName")) != null && ((TJPlacement) this.mRewardedVideoPlacementToAdMap.get(config.optString("placementName"))).isContentReady();
    }

    public void initInterstitial(Activity activity, String appKey, String userId, JSONObject config, InterstitialSmashListener listener) {
        if (!TextUtils.isEmpty(config.optString("sdkKey")) && !TextUtils.isEmpty(config.optString("placementName"))) {
            if (!(TextUtils.isEmpty(config.optString("placementName")) || listener == null)) {
                this.mInterstitialPlacementToListenerMap.put(config.optString("placementName"), listener);
            }
            addInitiatedAdUnit("Interstitial");
            switch (getCurrentInitState()) {
                case NOT_INIT:
                    initSDK(activity, userId, config);
                    return;
                case INIT_IN_PROGRESS:
                    return;
                case INIT_SUCCESS:
                    if (Tapjoy.isConnected()) {
                        if (!TextUtils.isEmpty(config.optString("placementName"))) {
                            TJPlacement isPlacement = new TJPlacement(activity, config.optString("placementName"), this);
                            isPlacement.setMediationName("supersonic");
                            isPlacement.setAdapterVersion(VERSION);
                            this.mInterstitialPlacementToAdMap.put(config.optString("placementName"), isPlacement);
                            if (listener != null) {
                                listener.onInterstitialInitSuccess();
                            }
                        }
                    } else if (listener != null) {
                        listener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Init Failed", "Interstitial"));
                    }
                    if (listener != null) {
                        listener.onInterstitialInitSuccess();
                        return;
                    }
                    return;
                case INIT_FAIL:
                    if (listener != null) {
                        listener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Init Failed", "Interstitial"));
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else if (listener != null) {
            listener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Missing params", "Interstitial"));
        }
    }

    public void loadInterstitial(JSONObject config, InterstitialSmashListener listener) {
        if (this.mInterstitialPlacementToAdMap.get(config.optString("placementName")) != null) {
            ((TJPlacement) this.mInterstitialPlacementToAdMap.get(config.optString("placementName"))).requestContent();
        } else if (listener != null) {
            listener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("Load error"));
        }
    }

    public void showInterstitial(JSONObject config, InterstitialSmashListener listener) {
        if (this.mInterstitialPlacementToAdMap.get(config.optString("placementName")) != null && ((TJPlacement) this.mInterstitialPlacementToAdMap.get(config.optString("placementName"))).isContentReady()) {
            ((TJPlacement) this.mInterstitialPlacementToAdMap.get(config.optString("placementName"))).showContent();
        } else if (listener != null) {
            listener.onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
        }
    }

    public boolean isInterstitialReady(JSONObject config) {
        return this.mInterstitialPlacementToAdMap.get(config.optString("placementName")) != null && ((TJPlacement) this.mInterstitialPlacementToAdMap.get(config.optString("placementName"))).isContentReady();
    }

    public void onRequestSuccess(TJPlacement tjPlacement) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onRequestSuccess(tjPlacement: " + tjPlacement.getName(), 1);
        if (!tjPlacement.isContentAvailable()) {
            if (this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()) != null) {
                ((RewardedVideoSmashListener) this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName())).onRewardedVideoAvailabilityChanged(false);
            }
            if (this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName()) != null) {
                ((InterstitialSmashListener) this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName())).onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("No Content Available"));
            }
        }
    }

    public void onRequestFailure(TJPlacement tjPlacement, TJError tjError) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onRequestFailure(tjPlacement: " + tjPlacement.getName() + ", tjError: " + tjError.message + ")", 1);
        if (this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()) != null) {
            ((RewardedVideoSmashListener) this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName())).onRewardedVideoAvailabilityChanged(false);
        }
        if (this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName()) != null) {
            ((InterstitialSmashListener) this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName())).onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError(tjError.message));
        }
    }

    public void onContentReady(TJPlacement tjPlacement) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onContentReady(tjPlacement: " + tjPlacement.getName() + ")", 1);
        if (this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()) != null) {
            ((RewardedVideoSmashListener) this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName())).onRewardedVideoAvailabilityChanged(true);
        }
        if (this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName()) != null) {
            ((InterstitialSmashListener) this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName())).onInterstitialAdReady();
        }
    }

    public void onContentShow(TJPlacement tjPlacement) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onContentShow(tjPlacement: " + tjPlacement.getName() + ")", 1);
        if (this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()) != null) {
            ((RewardedVideoSmashListener) this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName())).onRewardedVideoAdOpened();
            ((RewardedVideoSmashListener) this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName())).onRewardedVideoAvailabilityChanged(false);
        }
        if (this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName()) != null) {
            ((InterstitialSmashListener) this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName())).onInterstitialAdOpened();
            ((InterstitialSmashListener) this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName())).onInterstitialAdShowSucceeded();
        }
    }

    public void onContentDismiss(TJPlacement tjPlacement) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onContentDismiss(tjPlacement: " + tjPlacement.getName() + ")", 1);
        if (this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()) != null) {
            ((RewardedVideoSmashListener) this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName())).onRewardedVideoAdClosed();
        }
        if (this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName()) != null) {
            ((InterstitialSmashListener) this.mInterstitialPlacementToListenerMap.get(tjPlacement.getName())).onInterstitialAdClosed();
        }
    }

    public void onPurchaseRequest(TJPlacement tjPlacement, TJActionRequest tjActionRequest, String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onPurchaseRequest(tjPlacement: " + tjPlacement.getName(), 1);
    }

    public void onRewardRequest(TJPlacement tjPlacement, TJActionRequest request, String itemId, int quantity) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onRewardRequest(tjPlacement: " + tjPlacement.getName(), 1);
    }

    public void onVideoStart(TJPlacement tjPlacement) {
        log(IronSourceTag.INTERNAL, getProviderName() + ":onRewardedVideoAdStarted()", 1);
        if (this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()) != null) {
            ((RewardedVideoSmashListener) this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName())).onRewardedVideoAdStarted();
        }
    }

    public void onVideoError(TJPlacement tjPlacement, String s) {
        log(IronSourceTag.INTERNAL, getProviderName() + ":onVideoError(error: " + s + ")", 1);
    }

    public void onVideoComplete(TJPlacement tjPlacement) {
        log(IronSourceTag.INTERNAL, getProviderName() + ":onVideoComplete()", 1);
        if (this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName()) != null) {
            ((RewardedVideoSmashListener) this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName())).onRewardedVideoAdEnded();
            ((RewardedVideoSmashListener) this.mRewardedVideoPlacementToListenerMap.get(tjPlacement.getName())).onRewardedVideoAdRewarded();
        }
    }
}
