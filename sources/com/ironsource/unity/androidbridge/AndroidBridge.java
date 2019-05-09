package com.ironsource.unity.androidbridge;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.facebook.internal.NativeProtocol;
import com.ironsource.adapters.supersonicads.SupersonicConfig;
import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSource.AD_UNIT;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.IronSourceSegment;
import com.ironsource.mediationsdk.config.ConfigFile;
import com.ironsource.mediationsdk.integration.IntegrationHelper;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.sdk.BannerListener;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyInterstitialListener;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyRewardedVideoListener;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import com.ironsource.mediationsdk.sdk.OfferwallListener;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;
import com.ironsource.mediationsdk.sdk.SegmentListener;
import com.ironsource.sdk.constants.Constants.JSMethods;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.unity3d.player.UnityPlayer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class AndroidBridge implements InterstitialListener, ISDemandOnlyInterstitialListener, OfferwallListener, RewardedVideoListener, ISDemandOnlyRewardedVideoListener, RewardedInterstitialListener, SegmentListener {
    private static final AndroidBridge mInstance = new AndroidBridge();
    private final int BANNER_POSITION_TOP = 1;
    private final String ERROR_CODE = NativeProtocol.BRIDGE_ARG_ERROR_CODE;
    private final String ERROR_DESCRIPTION = NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION;
    private final String IRONSOURCE_EVENT_GAMEOBJECT = "IronSourceEvents";
    private final String PLACEMENT_NAME = "placement_name";
    private final String REWARD_AMOUNT = "reward_amount";
    private final String REWARD_NAME = "reward_name";
    private IronSourceBannerLayout mBanner;
    private FrameLayout mBannerContainer;
    private boolean mIsBannerLoaded;
    private boolean mIsInitBannerCalled;
    private Handler mUIHandler;

    /* renamed from: com.ironsource.unity.androidbridge.AndroidBridge$1 */
    class C01871 implements Callable<String> {
        C01871() {
        }

        public String call() throws Exception {
            return IronSource.getAdvertiserId(AndroidBridge.this.getUnityActivity());
        }
    }

    /* renamed from: com.ironsource.unity.androidbridge.AndroidBridge$3 */
    class C01923 implements Runnable {
        C01923() {
        }

        public void run() {
            synchronized (AndroidBridge.mInstance) {
                try {
                    AndroidBridge.this.mBannerContainer.removeAllViews();
                    if (AndroidBridge.this.mBanner != null) {
                        IronSource.destroyBanner(AndroidBridge.this.mBanner);
                        AndroidBridge.this.mBanner = null;
                    }
                } catch (Exception e) {
                }
                AndroidBridge.this.mIsBannerLoaded = false;
            }
        }
    }

    /* renamed from: com.ironsource.unity.androidbridge.AndroidBridge$4 */
    class C01934 implements Runnable {
        C01934() {
        }

        public void run() {
            synchronized (AndroidBridge.mInstance) {
                try {
                    if (AndroidBridge.this.mBanner != null) {
                        AndroidBridge.this.mBanner.setVisibility(0);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    /* renamed from: com.ironsource.unity.androidbridge.AndroidBridge$5 */
    class C01945 implements Runnable {
        C01945() {
        }

        public void run() {
            synchronized (AndroidBridge.mInstance) {
                try {
                    if (AndroidBridge.this.mBanner != null) {
                        AndroidBridge.this.mBanner.setVisibility(8);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public static synchronized AndroidBridge getInstance() {
        AndroidBridge androidBridge;
        synchronized (AndroidBridge.class) {
            androidBridge = mInstance;
        }
        return androidBridge;
    }

    private AndroidBridge() {
        IronSource.setRewardedVideoListener(this);
        IronSource.setInterstitialListener(this);
        IronSource.setRewardedInterstitialListener(this);
        IronSource.setOfferwallListener(this);
        IronSource.setISDemandOnlyInterstitialListener(this);
        IronSource.setISDemandOnlyRewardedVideoListener(this);
        this.mUIHandler = new Handler(Looper.getMainLooper());
        this.mBannerContainer = null;
        this.mBanner = null;
        this.mIsBannerLoaded = false;
        this.mIsInitBannerCalled = false;
    }

    public Activity getUnityActivity() {
        return UnityPlayer.currentActivity;
    }

    private void sendUnityEvent(String event) {
        sendUnityEvent(event, "");
    }

    private void sendUnityEvent(String event, String params) {
        try {
            if (UnityPlayer.currentActivity != null) {
                String paramsStr;
                if (TextUtils.isEmpty(params)) {
                    paramsStr = "";
                } else {
                    paramsStr = params;
                }
                UnityPlayer.UnitySendMessage("IronSourceEvents", event, paramsStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPluginData(String pluginType, String pluginVersion, String pluginFrameworkVersion) {
        ConfigFile.getConfigFile().setPluginData(pluginType, pluginVersion, pluginFrameworkVersion);
    }

    public String getAdvertiserId() {
        String result = "";
        FutureTask<String> f = new FutureTask(new C01871());
        f.run();
        try {
            return (String) f.get(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    public void validateIntegration() {
        IntegrationHelper.validateIntegration(getUnityActivity());
    }

    public void shouldTrackNetworkState(boolean track) {
        IronSource.shouldTrackNetworkState(getUnityActivity(), track);
    }

    public boolean setDynamicUserId(String dynamicUserId) {
        return IronSource.setDynamicUserId(dynamicUserId);
    }

    public void setAdaptersDebug(boolean enabled) {
        IronSource.setAdaptersDebug(enabled);
    }

    public void onResume() {
        IronSource.onResume(getUnityActivity());
    }

    public void onPause() {
        IronSource.onPause(getUnityActivity());
    }

    public void setAge(int age) {
        IronSource.setAge(age);
    }

    public void setGender(String gender) {
        IronSource.setGender(gender);
    }

    public void setMediationSegment(String segment) {
        IronSource.setMediationSegment(segment);
    }

    public void setUserId(String userId) {
        IronSource.setUserId(userId);
    }

    public void init(String appKey) {
        this.mIsInitBannerCalled = true;
        IronSource.init(getUnityActivity(), appKey);
    }

    public void init(String appKey, String[] adUnits) {
        List<AD_UNIT> adUnitsToInit = new ArrayList();
        for (String adUnit : adUnits) {
            if (AD_UNIT.REWARDED_VIDEO.toString().equalsIgnoreCase(adUnit)) {
                adUnitsToInit.add(AD_UNIT.REWARDED_VIDEO);
            } else if (AD_UNIT.INTERSTITIAL.toString().equalsIgnoreCase(adUnit)) {
                adUnitsToInit.add(AD_UNIT.INTERSTITIAL);
            } else if (AD_UNIT.OFFERWALL.toString().equalsIgnoreCase(adUnit)) {
                adUnitsToInit.add(AD_UNIT.OFFERWALL);
            } else if (AD_UNIT.BANNER.toString().equalsIgnoreCase(adUnit)) {
                this.mIsInitBannerCalled = true;
                adUnitsToInit.add(AD_UNIT.BANNER);
            }
        }
        IronSource.init(getUnityActivity(), appKey, (AD_UNIT[]) adUnitsToInit.toArray(new AD_UNIT[adUnitsToInit.size()]));
    }

    public void initISDemandOnly(String appKey, String[] adUnits) {
        List<AD_UNIT> adUnitsToInit = new ArrayList();
        for (String adUnit : adUnits) {
            if (AD_UNIT.REWARDED_VIDEO.toString().equalsIgnoreCase(adUnit)) {
                adUnitsToInit.add(AD_UNIT.REWARDED_VIDEO);
            } else if (AD_UNIT.INTERSTITIAL.toString().equalsIgnoreCase(adUnit)) {
                adUnitsToInit.add(AD_UNIT.INTERSTITIAL);
            } else if (AD_UNIT.OFFERWALL.toString().equalsIgnoreCase(adUnit)) {
                adUnitsToInit.add(AD_UNIT.OFFERWALL);
            } else if (AD_UNIT.BANNER.toString().equalsIgnoreCase(adUnit)) {
                adUnitsToInit.add(AD_UNIT.BANNER);
            }
        }
        IronSource.initISDemandOnly(getUnityActivity(), appKey, (AD_UNIT[]) adUnitsToInit.toArray(new AD_UNIT[adUnitsToInit.size()]));
    }

    public void showRewardedVideo() {
        IronSource.showRewardedVideo();
    }

    public void showRewardedVideo(String placementName) {
        IronSource.showRewardedVideo(placementName);
    }

    public boolean isRewardedVideoAvailable() {
        return IronSource.isRewardedVideoAvailable();
    }

    public boolean isRewardedVideoPlacementCapped(String placementName) {
        return IronSource.isRewardedVideoPlacementCapped(placementName);
    }

    public String getPlacementInfo(String placementName) {
        String result = null;
        Placement p = IronSource.getRewardedVideoPlacementInfo(placementName);
        HashMap<String, Object> map = new HashMap();
        try {
            map.put("placement_name", p.getPlacementName());
            map.put("reward_name", p.getRewardName());
            map.put("reward_amount", Integer.valueOf(p.getRewardAmount()));
            result = new JSONObject(map).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void setRewardedVideoServerParams(String paramsJson) {
        IronSource.setRewardedVideoServerParameters(getHashMapFromJsonString(paramsJson));
    }

    public void clearRewardedVideoServerParams() {
        IronSource.clearRewardedVideoServerParameters();
    }

    public void showISDemandOnlyRewardedVideo(String instanceId) {
        IronSource.showISDemandOnlyRewardedVideo(instanceId);
    }

    public void showISDemandOnlyRewardedVideo(String instanceId, String placementName) {
        IronSource.showISDemandOnlyRewardedVideo(instanceId, placementName);
    }

    public boolean isISDemandOnlyRewardedVideoAvailable(String instanceId) {
        return IronSource.isISDemandOnlyRewardedVideoAvailable(instanceId);
    }

    public void loadInterstitial() {
        IronSource.loadInterstitial();
    }

    public void showInterstitial() {
        IronSource.showInterstitial();
    }

    public void showInterstitial(String placementName) {
        IronSource.showInterstitial(placementName);
    }

    public boolean isInterstitialReady() {
        return IronSource.isInterstitialReady();
    }

    public boolean isInterstitialPlacementCapped(String placementName) {
        return IronSource.isInterstitialPlacementCapped(placementName);
    }

    public void loadISDemandOnlyInterstitial(String instanceId) {
        IronSource.loadISDemandOnlyInterstitial(instanceId);
    }

    public void showISDemandOnlyInterstitial(String instanceId) {
        IronSource.showISDemandOnlyInterstitial(instanceId);
    }

    public void showISDemandOnlyInterstitial(String instanceId, String placementName) {
        IronSource.showISDemandOnlyInterstitial(instanceId, placementName);
    }

    public boolean isISDemandOnlyInterstitialReady(String instanceId) {
        return IronSource.isISDemandOnlyInterstitialReady(instanceId);
    }

    public void showOfferwall() {
        IronSource.showOfferwall();
    }

    public void showOfferwall(String placementName) {
        IronSource.showOfferwall(placementName);
    }

    public boolean isOfferwallAvailable() {
        return IronSource.isOfferwallAvailable();
    }

    public void getOfferwallCredits() {
        IronSource.getOfferwallCredits();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadBanner(java.lang.String r3, int r4, int r5, int r6, java.lang.String r7) {
        /*
        r2 = this;
        r1 = mInstance;
        monitor-enter(r1);
        r0 = r2.mIsInitBannerCalled;	 Catch:{ all -> 0x0015 }
        if (r0 == 0) goto L_0x000b;
    L_0x0007:
        r0 = r2.mIsBannerLoaded;	 Catch:{ all -> 0x0015 }
        if (r0 == 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r1);	 Catch:{ all -> 0x0015 }
    L_0x000c:
        return;
    L_0x000d:
        r0 = 1;
        r2.mIsBannerLoaded = r0;	 Catch:{ all -> 0x0015 }
        r2.loadAndShowBanner(r3, r4, r5, r6, r7);	 Catch:{ all -> 0x0015 }
        monitor-exit(r1);	 Catch:{ all -> 0x0015 }
        goto L_0x000c;
    L_0x0015:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0015 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ironsource.unity.androidbridge.AndroidBridge.loadBanner(java.lang.String, int, int, int, java.lang.String):void");
    }

    private void loadAndShowBanner(String description, int width, int height, int position, String placementName) {
        final String str = description;
        final int i = width;
        final int i2 = height;
        final int i3 = position;
        final String str2 = placementName;
        this.mUIHandler.post(new Runnable() {

            /* renamed from: com.ironsource.unity.androidbridge.AndroidBridge$2$1 */
            class C01901 implements BannerListener {

                /* renamed from: com.ironsource.unity.androidbridge.AndroidBridge$2$1$1 */
                class C01881 implements Runnable {
                    C01881() {
                    }

                    public void run() {
                        synchronized (AndroidBridge.mInstance) {
                            AndroidBridge.this.mBannerContainer.requestLayout();
                        }
                    }
                }

                /* renamed from: com.ironsource.unity.androidbridge.AndroidBridge$2$1$2 */
                class C01892 implements Runnable {
                    C01892() {
                    }

                    public void run() {
                        synchronized (AndroidBridge.mInstance) {
                            AndroidBridge.this.mBannerContainer.removeAllViews();
                            if (AndroidBridge.this.mBanner != null) {
                                AndroidBridge.this.mBanner = null;
                            }
                            AndroidBridge.this.mIsBannerLoaded = false;
                        }
                    }
                }

                C01901() {
                }

                public void onBannerAdLoaded() {
                    AndroidBridge.this.mUIHandler.post(new C01881());
                    AndroidBridge.this.sendUnityEvent("onBannerAdLoaded");
                }

                public void onBannerAdLoadFailed(IronSourceError ironSourceError) {
                    AndroidBridge.this.mUIHandler.post(new C01892());
                    AndroidBridge.this.sendUnityEvent("onBannerAdLoadFailed", AndroidBridge.this.parseErrorToEvent(ironSourceError));
                }

                public void onBannerAdClicked() {
                    AndroidBridge.this.sendUnityEvent("onBannerAdClicked");
                }

                public void onBannerAdScreenPresented() {
                    AndroidBridge.this.sendUnityEvent("onBannerAdScreenPresented");
                }

                public void onBannerAdScreenDismissed() {
                    AndroidBridge.this.sendUnityEvent("onBannerAdScreenDismissed");
                }

                public void onBannerAdLeftApplication() {
                    AndroidBridge.this.sendUnityEvent("onBannerAdLeftApplication");
                }
            }

            public void run() {
                synchronized (AndroidBridge.mInstance) {
                    try {
                        if (AndroidBridge.this.mBannerContainer == null) {
                            AndroidBridge.this.mBannerContainer = new FrameLayout(UnityPlayer.currentActivity);
                            UnityPlayer.currentActivity.addContentView(AndroidBridge.this.mBannerContainer, new LayoutParams(-1, -1));
                        }
                        AndroidBridge.this.mBanner = IronSource.createBanner(AndroidBridge.this.getUnityActivity(), AndroidBridge.this.getBannerSize(str, i, i2));
                        LayoutParams layoutParams = new LayoutParams(-1, -2);
                        layoutParams.gravity = i3 == 1 ? 48 : 80;
                        AndroidBridge.this.mBannerContainer.addView(AndroidBridge.this.mBanner, layoutParams);
                        AndroidBridge.this.mBanner.setBannerListener(new C01901());
                        if (str2 != null) {
                            IronSource.loadBanner(AndroidBridge.this.mBanner, str2);
                        } else {
                            IronSource.loadBanner(AndroidBridge.this.mBanner);
                        }
                    } catch (Throwable ex) {
                        AndroidBridge.this.sendUnityEvent("onBannerAdLoadFailed", AndroidBridge.this.parseErrorToEvent(IronSourceError.ERROR_CODE_NO_ADS_TO_SHOW, ex.getMessage()));
                    }
                }
            }
        });
    }

    private ISBannerSize getBannerSize(String description, int width, int height) {
        if (description.equals("CUSTOM")) {
            return new ISBannerSize(width, height);
        }
        return new ISBannerSize(description);
    }

    public void destroyBanner() {
        synchronized (mInstance) {
            this.mUIHandler.post(new C01923());
        }
    }

    public void displayBanner() {
        synchronized (mInstance) {
            this.mUIHandler.post(new C01934());
        }
    }

    public void hideBanner() {
        synchronized (mInstance) {
            this.mUIHandler.post(new C01945());
        }
    }

    public boolean isBannerPlacementCapped(String placementName) {
        return IronSource.isBannerPlacementCapped(placementName);
    }

    public void setSegment(String segmentJson) {
        try {
            IronSource.setSegmentListener(this);
            JSONObject json = new JSONObject(segmentJson);
            IronSourceSegment ironSegment = new IronSourceSegment();
            Iterator<String> iter = json.keys();
            while (iter.hasNext()) {
                String key = (String) iter.next();
                if (key.equals(IronSourceSegment.AGE)) {
                    ironSegment.setAge(json.optInt(key));
                } else if (key.equals("gender")) {
                    ironSegment.setGender(json.optString(key));
                } else if (key.equals("level")) {
                    ironSegment.setLevel(json.optInt(key));
                } else if (key.equals("isPaying")) {
                    ironSegment.setIsPaying(json.optInt(key) != 0);
                } else if (key.equals("userCreationDate")) {
                    ironSegment.setUserCreationDate(json.optLong(key));
                } else if (key.equals("segmentName")) {
                    ironSegment.setSegmentName(json.optString(key));
                } else if (key.equals(IronSourceSegment.IAPT)) {
                    ironSegment.setIAPTotal(json.optDouble(key));
                } else {
                    ironSegment.setCustom(key, json.optString(key));
                }
            }
            IronSource.setSegment(ironSegment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setConsent(boolean consent) {
        IronSource.setConsent(consent);
    }

    public void onRewardedVideoAdOpened() {
        sendUnityEvent("onRewardedVideoAdOpened", "");
    }

    public void onRewardedVideoAdClosed() {
        sendUnityEvent("onRewardedVideoAdClosed", "");
    }

    public void onRewardedVideoAvailabilityChanged(boolean available) {
        sendUnityEvent("onRewardedVideoAvailabilityChanged", String.valueOf(available));
    }

    public void onRewardedVideoAdStarted() {
        sendUnityEvent("onRewardedVideoAdStarted", "");
    }

    public void onRewardedVideoAdEnded() {
        sendUnityEvent("onRewardedVideoAdEnded", "");
    }

    public void onRewardedVideoAdRewarded(Placement placement) {
        HashMap<String, Object> map = new HashMap();
        try {
            map.put("placement_id", String.valueOf(placement.getPlacementId()));
            map.put("placement_name", placement.getPlacementName());
            map.put("placement_reward_amount", String.valueOf(placement.getRewardAmount()));
            map.put("placement_reward_name", placement.getRewardName());
            sendUnityEvent("onRewardedVideoAdRewarded", new JSONObject(map).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onRewardedVideoAdShowFailed(IronSourceError ironSourceError) {
        if (ironSourceError != null) {
            sendUnityEvent("onRewardedVideoAdShowFailed", parseErrorToEvent(ironSourceError.getErrorCode(), ironSourceError.getErrorMessage()));
        } else {
            sendUnityEvent("onRewardedVideoAdShowFailed", "");
        }
    }

    public void onRewardedVideoAdClicked(Placement placement) {
        HashMap<String, Object> map = new HashMap();
        try {
            map.put("placement_id", String.valueOf(placement.getPlacementId()));
            map.put("placement_name", placement.getPlacementName());
            map.put("placement_reward_amount", String.valueOf(placement.getRewardAmount()));
            map.put("placement_reward_name", placement.getRewardName());
            sendUnityEvent("onRewardedVideoAdClicked", new JSONObject(map).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onRewardedVideoAvailabilityChanged(String instanceId, boolean available) {
        sendUnityEvent("onRewardedVideoAvailabilityChangedDemandOnly", Arrays.toString(new String[]{instanceId, String.valueOf(available)}));
    }

    public void onRewardedVideoAdOpened(String instanceId) {
        sendUnityEvent("onRewardedVideoAdOpenedDemandOnly", instanceId);
    }

    public void onRewardedVideoAdClosed(String instanceId) {
        sendUnityEvent("onRewardedVideoAdClosedDemandOnly", instanceId);
    }

    public void onRewardedVideoAdRewarded(String instanceId, Placement placement) {
        HashMap<String, Object> map = new HashMap();
        try {
            map.put("placement_id", String.valueOf(placement.getPlacementId()));
            map.put("placement_name", placement.getPlacementName());
            map.put("placement_reward_amount", String.valueOf(placement.getRewardAmount()));
            map.put("placement_reward_name", placement.getRewardName());
            String argsJson = new JSONObject(map).toString();
            sendUnityEvent("onRewardedVideoAdRewardedDemandOnly", Arrays.toString(new String[]{instanceId, argsJson}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onRewardedVideoAdShowFailed(String instanceId, IronSourceError ironSourceError) {
        String[] params;
        if (ironSourceError != null) {
            int errorCode = ironSourceError.getErrorCode();
            String errorDescription = ironSourceError.getErrorMessage();
            params = new String[]{instanceId, parseErrorToEvent(errorCode, errorDescription)};
        } else {
            params = new String[]{instanceId, ""};
        }
        sendUnityEvent("onRewardedVideoAdShowFailedDemandOnly", Arrays.toString(params));
    }

    public void onRewardedVideoAdClicked(String instanceId, Placement placement) {
        HashMap<String, Object> map = new HashMap();
        try {
            map.put("placement_id", String.valueOf(placement.getPlacementId()));
            map.put("placement_name", placement.getPlacementName());
            map.put("placement_reward_amount", String.valueOf(placement.getRewardAmount()));
            map.put("placement_reward_name", placement.getRewardName());
            String argsJson = new JSONObject(map).toString();
            sendUnityEvent("onRewardedVideoAdClickedDemandOnly", Arrays.toString(new String[]{instanceId, argsJson}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onInterstitialAdReady() {
        sendUnityEvent("onInterstitialAdReady", "");
    }

    public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {
        if (ironSourceError != null) {
            sendUnityEvent("onInterstitialAdLoadFailed", parseErrorToEvent(ironSourceError.getErrorCode(), ironSourceError.getErrorMessage()));
        } else {
            sendUnityEvent("onInterstitialAdLoadFailed", "");
        }
    }

    public void onInterstitialAdOpened() {
        sendUnityEvent("onInterstitialAdOpened", "");
    }

    public void onInterstitialAdClosed() {
        sendUnityEvent("onInterstitialAdClosed", "");
    }

    public void onInterstitialAdShowSucceeded() {
        sendUnityEvent("onInterstitialAdShowSucceeded", "");
    }

    public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {
        if (ironSourceError != null) {
            sendUnityEvent("onInterstitialAdShowFailed", parseErrorToEvent(ironSourceError.getErrorCode(), ironSourceError.getErrorMessage()));
        } else {
            sendUnityEvent("onInterstitialAdShowFailed", "");
        }
    }

    public void onInterstitialAdClicked() {
        sendUnityEvent(JSMethods.ON_INTERSTITIAL_AD_CLICKED, "");
    }

    public void onInterstitialAdReady(String instanceId) {
        sendUnityEvent("onInterstitialAdReadyDemandOnly", instanceId);
    }

    public void onInterstitialAdLoadFailed(String instanceId, IronSourceError ironSourceError) {
        String[] params;
        if (ironSourceError != null) {
            int errorCode = ironSourceError.getErrorCode();
            String errorDescription = ironSourceError.getErrorMessage();
            params = new String[]{instanceId, parseErrorToEvent(errorCode, errorDescription)};
        } else {
            params = new String[]{instanceId, ""};
        }
        sendUnityEvent("onInterstitialAdLoadFailedDemandOnly", Arrays.toString(params));
    }

    public void onInterstitialAdOpened(String instanceId) {
        sendUnityEvent("onInterstitialAdOpenedDemandOnly", instanceId);
    }

    public void onInterstitialAdClosed(String instanceId) {
        sendUnityEvent("onInterstitialAdClosedDemandOnly", instanceId);
    }

    public void onInterstitialAdShowSucceeded(String instanceId) {
        sendUnityEvent("onInterstitialAdShowSucceededDemandOnly", instanceId);
    }

    public void onInterstitialAdShowFailed(String instanceId, IronSourceError ironSourceError) {
        String[] params;
        if (ironSourceError != null) {
            int errorCode = ironSourceError.getErrorCode();
            String errorDescription = ironSourceError.getErrorMessage();
            params = new String[]{instanceId, parseErrorToEvent(errorCode, errorDescription)};
        } else {
            params = new String[]{instanceId, ""};
        }
        sendUnityEvent("onInterstitialAdShowFailedDemandOnly", Arrays.toString(params));
    }

    public void onInterstitialAdClicked(String instanceId) {
        sendUnityEvent("onInterstitialAdClickedDemandOnly", instanceId);
    }

    public void onInterstitialAdRewarded() {
        sendUnityEvent("onInterstitialAdRewarded", "");
    }

    public void onOfferwallAvailable(boolean available) {
        sendUnityEvent("onOfferwallAvailable", String.valueOf(available));
    }

    public void onOfferwallOpened() {
        sendUnityEvent("onOfferwallOpened", "");
    }

    public void onOfferwallShowFailed(IronSourceError ironSourceError) {
        if (ironSourceError != null) {
            sendUnityEvent("onOfferwallShowFailed", parseErrorToEvent(ironSourceError.getErrorCode(), ironSourceError.getErrorMessage()));
        } else {
            sendUnityEvent("onOfferwallShowFailed", "");
        }
    }

    public boolean onOfferwallAdCredited(int credits, int totalCredits, boolean totalCreditsFlag) {
        HashMap<String, Object> map = new HashMap();
        try {
            map.put(ParametersKeys.CREDITS, String.valueOf(credits));
            map.put("totalCredits", String.valueOf(totalCredits));
            map.put("totalCreditsFlag", String.valueOf(totalCreditsFlag));
            sendUnityEvent("onOfferwallAdCredited", new JSONObject(map).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void onGetOfferwallCreditsFailed(IronSourceError ironSourceError) {
        if (ironSourceError != null) {
            sendUnityEvent("onGetOfferwallCreditsFailed", parseErrorToEvent(ironSourceError.getErrorCode(), ironSourceError.getErrorMessage()));
        } else {
            sendUnityEvent("onGetOfferwallCreditsFailed", "");
        }
    }

    public void onOfferwallClosed() {
        sendUnityEvent("onOfferwallClosed", "");
    }

    public void setLanguage(String language) {
        SupersonicConfig.getConfigObj().setLanguage(language);
    }

    public void setClientSideCallbacks(boolean status) {
        SupersonicConfig.getConfigObj().setClientSideCallbacks(status);
    }

    public void setRewardedVideoCustomParams(String paramsJson) {
        SupersonicConfig.getConfigObj().setRewardedVideoCustomParams(getHashMapFromJsonString(paramsJson));
    }

    public void setOfferwallCustomParams(String paramsJson) {
        SupersonicConfig.getConfigObj().setOfferwallCustomParams(getHashMapFromJsonString(paramsJson));
    }

    public HashMap<String, String> getHashMapFromJsonString(String jsonStr) {
        HashMap<String, String> map = new HashMap();
        try {
            JSONObject json = new JSONObject(jsonStr);
            Iterator<String> iter = json.keys();
            while (iter.hasNext()) {
                String key = (String) iter.next();
                map.put(key, json.getString(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }

    private String parseErrorToEvent(IronSourceError error) {
        return error == null ? "" : parseErrorToEvent(error.getErrorCode(), error.getErrorMessage());
    }

    public String parseErrorToEvent(int code, String msg) {
        HashMap<String, Object> map = new HashMap();
        String result = "";
        try {
            map.put(NativeProtocol.BRIDGE_ARG_ERROR_CODE, String.valueOf(code));
            map.put(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION, msg);
            result = new JSONObject(map).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void onSegmentReceived(String segment) {
        sendUnityEvent("onSegmentReceived", segment);
    }
}
