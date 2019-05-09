package com.ironsource.adapters.chartboost;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.chartboost.sdk.CBLocation;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Chartboost.CBFramework;
import com.chartboost.sdk.Chartboost.CBMediation;
import com.chartboost.sdk.Chartboost.CBPIDataUseConsent;
import com.chartboost.sdk.ChartboostDelegate;
import com.chartboost.sdk.Libraries.CBLogging.Level;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.ironsource.mediationsdk.AbstractAdapter;
import com.ironsource.mediationsdk.IntegrationData;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

class ChartboostAdapter extends AbstractAdapter {
    private static final String GitHash = "63e9f72bf";
    private static final String VERSION = "4.1.8";
    private final String AD_LOCATION = "adLocation";
    private final String APP_ID = "appID";
    private final String APP_SIGNATURE = "appSignature";
    private Activity mActivity;
    private Boolean mAlreadyCalledInit = Boolean.valueOf(false);
    private Boolean mConsentCollectingUserData = null;
    private CbDelegate mDelegate;
    private boolean mDidInitSuccessfully = false;
    private ConcurrentHashMap<String, InterstitialSmashListener> mLocationToIsListener = new ConcurrentHashMap();
    private ConcurrentHashMap<String, RewardedVideoSmashListener> mLocationToRvListener = new ConcurrentHashMap();

    private class CbDelegate extends ChartboostDelegate {
        private CbDelegate() {
        }

        public void didDisplayRewardedVideo(String location) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didDisplayRewardedVideo placementId: <" + location + ">", 1);
            if (ChartboostAdapter.this.mLocationToRvListener.get(location) != null) {
                ((RewardedVideoSmashListener) ChartboostAdapter.this.mLocationToRvListener.get(location)).onRewardedVideoAdOpened();
            }
        }

        public void didCacheRewardedVideo(String location) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didCacheRewardedVideo placementId: <" + location + ">", 1);
            if (ChartboostAdapter.this.mLocationToRvListener.get(location) != null) {
                ((RewardedVideoSmashListener) ChartboostAdapter.this.mLocationToRvListener.get(location)).onRewardedVideoAvailabilityChanged(true);
            }
        }

        public void didFailToLoadRewardedVideo(String location, CBImpressionError error) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, "didFailToLoadRewardedVideo placementId: <" + location + "> error: " + error.toString(), 1);
            if (ChartboostAdapter.this.mLocationToRvListener.get(location) != null) {
                ((RewardedVideoSmashListener) ChartboostAdapter.this.mLocationToRvListener.get(location)).onRewardedVideoAvailabilityChanged(Chartboost.hasRewardedVideo(location));
            }
        }

        public void didDismissRewardedVideo(String location) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didDismissRewardedVideo placementId: <" + location + ">", 1);
            if (ChartboostAdapter.this.mLocationToRvListener.get(location) != null) {
                ((RewardedVideoSmashListener) ChartboostAdapter.this.mLocationToRvListener.get(location)).onRewardedVideoAdClosed();
            }
        }

        public void didCloseRewardedVideo(String location) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didCloseRewardedVideo placementId: <" + location + ">", 1);
        }

        public void didClickRewardedVideo(String location) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didClickRewardedVideo placementId: <" + location + ">", 1);
            if (ChartboostAdapter.this.mLocationToRvListener.get(location) != null) {
                ((RewardedVideoSmashListener) ChartboostAdapter.this.mLocationToRvListener.get(location)).onRewardedVideoAdClicked();
            }
        }

        public void didCompleteRewardedVideo(String location, int reward) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didCompleteRewardedVideo placementId: <" + location + ">", 1);
            if (ChartboostAdapter.this.mLocationToRvListener.get(location) != null) {
                ((RewardedVideoSmashListener) ChartboostAdapter.this.mLocationToRvListener.get(location)).onRewardedVideoAdRewarded();
            }
        }

        public void willDisplayVideo(String location) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " willDisplayVideo placementId: <" + location + ">", 1);
        }

        public void didCacheInterstitial(String location) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didCacheInterstitial placementId: <" + location + ">", 1);
            if (ChartboostAdapter.this.mLocationToIsListener.get(location) != null) {
                ((InterstitialSmashListener) ChartboostAdapter.this.mLocationToIsListener.get(location)).onInterstitialAdReady();
            }
        }

        public void didFailToLoadInterstitial(String location, CBImpressionError error) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didFailToLoadInterstitial placementId: <" + location + ">  error: " + error.toString(), 1);
            if (ChartboostAdapter.this.mLocationToIsListener.get(location) != null) {
                ((InterstitialSmashListener) ChartboostAdapter.this.mLocationToIsListener.get(location)).onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError(error.toString()));
            }
        }

        public void didDismissInterstitial(String location) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didDismissInterstitial placementId: <" + location + ">", 1);
            if (ChartboostAdapter.this.mLocationToIsListener.get(location) != null) {
                ((InterstitialSmashListener) ChartboostAdapter.this.mLocationToIsListener.get(location)).onInterstitialAdClosed();
            }
        }

        public void didCloseInterstitial(String location) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didCloseInterstitial placementId: <" + location + ">", 1);
        }

        public void didClickInterstitial(String location) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, ChartboostAdapter.this.getProviderName() + " didClickInterstitial placementId: <" + location + ">", 1);
            if (ChartboostAdapter.this.mLocationToIsListener.get(location) != null) {
                ((InterstitialSmashListener) ChartboostAdapter.this.mLocationToIsListener.get(location)).onInterstitialAdClicked();
            }
        }

        public void didDisplayInterstitial(String location) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didDisplayInterstitial placementId: <" + location + ">", 1);
            if (ChartboostAdapter.this.mLocationToIsListener.get(location) != null) {
                ((InterstitialSmashListener) ChartboostAdapter.this.mLocationToIsListener.get(location)).onInterstitialAdOpened();
                ((InterstitialSmashListener) ChartboostAdapter.this.mLocationToIsListener.get(location)).onInterstitialAdShowSucceeded();
            }
        }

        public void didInitialize() {
            synchronized (this) {
                IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didInitialize", 1);
                ChartboostAdapter.this.mDidInitSuccessfully = true;
                for (String key : ChartboostAdapter.this.mLocationToRvListener.keySet()) {
                    Chartboost.cacheRewardedVideo(key);
                }
                for (String key2 : ChartboostAdapter.this.mLocationToIsListener.keySet()) {
                    ((InterstitialSmashListener) ChartboostAdapter.this.mLocationToIsListener.get(key2)).onInterstitialInitSuccess();
                }
            }
        }
    }

    public static ChartboostAdapter startAdapter(String providerName) {
        return new ChartboostAdapter(providerName);
    }

    private ChartboostAdapter(String providerName) {
        super(providerName);
    }

    public static IntegrationData getIntegrationData(Activity activity) {
        IntegrationData ret = new IntegrationData("Chartboost", VERSION);
        ret.activities = new String[]{"com.chartboost.sdk.CBImpressionActivity"};
        return ret;
    }

    public static String getAdapterSDKVersion() {
        String sdkVersion = null;
        try {
            sdkVersion = Chartboost.getSDKVersion();
        } catch (Exception e) {
        }
        return sdkVersion;
    }

    public String getVersion() {
        return VERSION;
    }

    public String getCoreSDKVersion() {
        return Chartboost.getSDKVersion();
    }

    protected synchronized void setConsent(boolean consent) {
        if (!this.mAlreadyCalledInit.booleanValue()) {
            this.mConsentCollectingUserData = Boolean.valueOf(consent);
        } else if (consent) {
            Chartboost.setPIDataUseConsent(this.mActivity, CBPIDataUseConsent.YES_BEHAVIORAL);
        } else {
            Chartboost.setPIDataUseConsent(this.mActivity, CBPIDataUseConsent.NO_BEHAVIORAL);
        }
    }

    public void onResume(Activity activity) {
        if (activity != null) {
            this.mActivity = activity;
            Chartboost.onStart(activity);
            Chartboost.onResume(activity);
        }
    }

    public void onPause(Activity activity) {
        if (activity != null) {
            Chartboost.onPause(activity);
            Chartboost.onStop(activity);
        }
    }

    private void init(Activity activity, String userId, String type, String appId, String appSignature) {
        final Activity activity2 = activity;
        final String str = appId;
        final String str2 = appSignature;
        final String str3 = userId;
        activity.runOnUiThread(new Runnable() {
            public void run() {
                synchronized (this) {
                    if (!ChartboostAdapter.this.mAlreadyCalledInit.booleanValue()) {
                        ChartboostAdapter.this.mAlreadyCalledInit = Boolean.valueOf(true);
                        ChartboostAdapter.this.mActivity = activity2;
                        ChartboostAdapter.this.mDelegate = new CbDelegate();
                        if (ChartboostAdapter.this.mConsentCollectingUserData != null) {
                            ChartboostAdapter.this.setConsent(ChartboostAdapter.this.mConsentCollectingUserData.booleanValue());
                        }
                        Chartboost.setDelegate(ChartboostAdapter.this.mDelegate);
                        Chartboost.startWithAppId(activity2, str, str2);
                        boolean isDebugEnabled = false;
                        try {
                            isDebugEnabled = ChartboostAdapter.this.isAdaptersDebugEnabled();
                        } catch (NoSuchMethodError e) {
                        }
                        if (isDebugEnabled) {
                            Chartboost.setLoggingLevel(Level.ALL);
                        } else {
                            Chartboost.setLoggingLevel(Level.NONE);
                        }
                        if ("Unity".equals(ChartboostAdapter.this.getPluginType()) && !TextUtils.isEmpty(ChartboostAdapter.this.getPluginFrameworkVersion())) {
                            Chartboost.setFramework(CBFramework.CBFrameworkUnity, ChartboostAdapter.this.getPluginFrameworkVersion());
                        }
                        Chartboost.setMediation(CBMediation.CBMediationironSource, ChartboostAdapter.VERSION);
                        Chartboost.setCustomId(str3);
                        Chartboost.setAutoCacheAds(true);
                        Chartboost.onCreate(activity2);
                        Chartboost.onStart(activity2);
                        Chartboost.onResume(activity2);
                    }
                }
            }
        });
    }

    public synchronized void initRewardedVideo(Activity activity, String appKey, String userId, JSONObject config, RewardedVideoSmashListener listener) {
        if (!TextUtils.isEmpty(config.optString("appID")) && !TextUtils.isEmpty(config.optString("appSignature"))) {
            String locationId = getLocationId(config);
            this.mLocationToRvListener.put(locationId, listener);
            init(activity, userId, IronSourceConstants.REWARDED_VIDEO_EVENT_TYPE, config.optString("appID"), config.optString("appSignature"));
            if (this.mDidInitSuccessfully) {
                Chartboost.cacheRewardedVideo(locationId);
            }
        } else if (listener != null) {
            listener.onRewardedVideoAvailabilityChanged(false);
        }
    }

    public void fetchRewardedVideo(JSONObject config) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, getProviderName() + " fetchRewardedVideo placementId: <" + getLocationId(config) + ">: Automatic network", 1);
    }

    public void showRewardedVideo(JSONObject config, RewardedVideoSmashListener listener) {
        String locationId = getLocationId(config);
        IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, getProviderName() + " showRewardedVideo placementId: <" + locationId + ">", 1);
        if (Chartboost.hasRewardedVideo(locationId)) {
            Chartboost.showRewardedVideo(locationId);
            return;
        }
        Chartboost.cacheRewardedVideo(locationId);
        if (this.mLocationToRvListener.containsKey(locationId)) {
            ((RewardedVideoSmashListener) this.mLocationToRvListener.get(locationId)).onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
        }
    }

    public boolean isRewardedVideoAvailable(JSONObject config) {
        return Chartboost.hasRewardedVideo(getLocationId(config));
    }

    public synchronized void initInterstitial(Activity activity, String appKey, String userId, JSONObject config, InterstitialSmashListener listener) {
        if (!TextUtils.isEmpty(config.optString("appID")) && !TextUtils.isEmpty(config.optString("appSignature"))) {
            this.mLocationToIsListener.put(getLocationId(config), listener);
            init(activity, userId, IronSourceConstants.INTERSTITIAL_EVENT_TYPE, config.optString("appID"), config.optString("appSignature"));
            if (this.mDidInitSuccessfully) {
                listener.onInterstitialInitSuccess();
            }
        } else if (listener != null) {
            listener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Missing params", "Interstitial"));
        }
    }

    public void loadInterstitial(JSONObject config, InterstitialSmashListener listener) {
        Handler h = new Handler(Looper.getMainLooper());
        final String locationId = getLocationId(config);
        h.post(new Runnable() {
            public void run() {
                Chartboost.cacheInterstitial(locationId);
            }
        });
    }

    public void showInterstitial(JSONObject config, InterstitialSmashListener listener) {
        String locationId = getLocationId(config);
        IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, getProviderName() + " showInterstitial placementId: <" + locationId + ">", 1);
        if (Chartboost.hasInterstitial(locationId)) {
            Chartboost.showInterstitial(locationId);
        } else if (this.mLocationToIsListener.containsKey(locationId)) {
            ((InterstitialSmashListener) this.mLocationToIsListener.get(locationId)).onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
        }
    }

    public boolean isInterstitialReady(JSONObject config) {
        return Chartboost.hasInterstitial(getLocationId(config));
    }

    private String getLocationId(JSONObject config) {
        String locationId = config.optString("adLocation");
        if (TextUtils.isEmpty(locationId)) {
            return CBLocation.LOCATION_DEFAULT;
        }
        return locationId;
    }
}
