package com.ironsource.sdk.agent;

import android.app.Activity;
import android.content.Context;
import android.content.MutableContextWrapper;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ironsource.sdk.SSAPublisher;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.controller.DemandSourceManager;
import com.ironsource.sdk.controller.IronSourceWebView;
import com.ironsource.sdk.controller.MOATJSAdapter;
import com.ironsource.sdk.controller.PermissionsJSAdapter;
import com.ironsource.sdk.data.AdUnitsReady;
import com.ironsource.sdk.data.DemandSource;
import com.ironsource.sdk.data.SSAEnums.ProductType;
import com.ironsource.sdk.data.SSASession;
import com.ironsource.sdk.data.SSASession.SessionType;
import com.ironsource.sdk.listeners.OnInterstitialListener;
import com.ironsource.sdk.listeners.OnOfferWallListener;
import com.ironsource.sdk.listeners.OnRewardedVideoListener;
import com.ironsource.sdk.listeners.internals.DSAdProductListener;
import com.ironsource.sdk.listeners.internals.DSInterstitialListener;
import com.ironsource.sdk.listeners.internals.DSRewardedVideoListener;
import com.ironsource.sdk.utils.DeviceProperties;
import com.ironsource.sdk.utils.IronSourceAsyncHttpRequestTask;
import com.ironsource.sdk.utils.IronSourceSharedPrefHelper;
import com.ironsource.sdk.utils.Logger;
import com.ironsource.sdk.utils.SDKUtils;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class IronSourceAdsPublisherAgent implements SSAPublisher, DSRewardedVideoListener, DSInterstitialListener, DSAdProductListener {
    private static final String TAG = "IronSourceAdsPublisherAgent";
    private static MutableContextWrapper mutableContextWrapper;
    private static IronSourceAdsPublisherAgent sInstance;
    private DemandSourceManager mDemandSourceManager = new DemandSourceManager();
    private SSASession session;
    private IronSourceWebView wvc;

    /* renamed from: com.ironsource.sdk.agent.IronSourceAdsPublisherAgent$2 */
    class C07102 implements Runnable {
        C07102() {
        }

        public void run() {
            IronSourceAdsPublisherAgent.this.wvc.destroy();
            IronSourceAdsPublisherAgent.this.wvc = null;
        }
    }

    private IronSourceAdsPublisherAgent(final Activity activity, int debugMode) throws Exception {
        IronSourceSharedPrefHelper.getSupersonicPrefHelper(activity);
        Logger.enableLogging(SDKUtils.getDebugMode());
        Logger.m1212i(TAG, "C'tor");
        mutableContextWrapper = new MutableContextWrapper(activity);
        activity.runOnUiThread(new Runnable() {
            public void run() {
                IronSourceAdsPublisherAgent.this.wvc = new IronSourceWebView(IronSourceAdsPublisherAgent.mutableContextWrapper, IronSourceAdsPublisherAgent.this.mDemandSourceManager);
                IronSourceAdsPublisherAgent.this.wvc.addMoatJSInterface(new MOATJSAdapter(activity.getApplication()));
                IronSourceAdsPublisherAgent.this.wvc.addPermissionsJSInterface(new PermissionsJSAdapter(activity.getApplicationContext()));
                IronSourceAdsPublisherAgent.this.wvc.registerConnectionReceiver(activity);
                IronSourceAdsPublisherAgent.this.wvc.setDebugMode(SDKUtils.getDebugMode());
                IronSourceAdsPublisherAgent.this.wvc.downloadController();
            }
        });
        startSession(activity);
    }

    public static synchronized IronSourceAdsPublisherAgent getInstance(Activity activity) throws Exception {
        IronSourceAdsPublisherAgent instance;
        synchronized (IronSourceAdsPublisherAgent.class) {
            instance = getInstance(activity, 0);
        }
        return instance;
    }

    public static synchronized IronSourceAdsPublisherAgent getInstance(Activity activity, int debugMode) throws Exception {
        IronSourceAdsPublisherAgent ironSourceAdsPublisherAgent;
        synchronized (IronSourceAdsPublisherAgent.class) {
            Logger.m1212i(TAG, "getInstance()");
            if (sInstance == null) {
                sInstance = new IronSourceAdsPublisherAgent(activity, debugMode);
            } else {
                mutableContextWrapper.setBaseContext(activity);
            }
            ironSourceAdsPublisherAgent = sInstance;
        }
        return ironSourceAdsPublisherAgent;
    }

    public IronSourceWebView getWebViewController() {
        return this.wvc;
    }

    private OnRewardedVideoListener getAdProductListenerAsRVListener(DemandSource demandSource) {
        if (demandSource == null) {
            return null;
        }
        return (OnRewardedVideoListener) demandSource.getListener();
    }

    private OnInterstitialListener getAdProductListenerAsISListener(DemandSource demandSource) {
        if (demandSource == null) {
            return null;
        }
        return (OnInterstitialListener) demandSource.getListener();
    }

    private void startSession(Context context) {
        this.session = new SSASession(context, SessionType.launched);
    }

    public void resumeSession(Context context) {
        this.session = new SSASession(context, SessionType.backFromBG);
    }

    private void endSession() {
        if (this.session != null) {
            this.session.endSession();
            IronSourceSharedPrefHelper.getSupersonicPrefHelper().addSession(this.session);
            this.session = null;
        }
    }

    public void initRewardedVideo(String applicationKey, String userId, String demandSourceName, Map<String, String> extraParameters, OnRewardedVideoListener listener) {
        this.wvc.initRewardedVideo(applicationKey, userId, this.mDemandSourceManager.createDemandSource(ProductType.RewardedVideo, demandSourceName, extraParameters, listener), this);
    }

    public void showRewardedVideo(JSONObject showParams) {
        this.wvc.showRewardedVideo(showParams);
    }

    public void initOfferWall(String applicationKey, String userId, Map<String, String> extraParameters, OnOfferWallListener listener) {
        this.wvc.initOfferWall(applicationKey, userId, extraParameters, listener);
    }

    public void showOfferWall(Map<String, String> extraParameters) {
        this.wvc.showOfferWall(extraParameters);
    }

    public void getOfferWallCredits(String applicationKey, String userId, OnOfferWallListener listener) {
        this.wvc.getOfferWallCredits(applicationKey, userId, listener);
    }

    public void initInterstitial(String applicationKey, String userId, String demandSourceName, Map<String, String> extraParameters, OnInterstitialListener listener) {
        this.wvc.initInterstitial(applicationKey, userId, this.mDemandSourceManager.createDemandSource(ProductType.Interstitial, demandSourceName, extraParameters, listener), this);
    }

    public void loadInterstitial(JSONObject loadParams) {
        String demandSourceName = null;
        if (loadParams != null) {
            demandSourceName = loadParams.optString("demandSourceName");
        }
        this.wvc.loadInterstitial(demandSourceName);
    }

    public boolean isInterstitialAdAvailable(String demandSourceName) {
        return this.wvc.isInterstitialAdAvailable(demandSourceName);
    }

    public void showInterstitial(JSONObject showParams) {
        this.wvc.showInterstitial(showParams);
    }

    public void onResume(Activity activity) {
        mutableContextWrapper.setBaseContext(activity);
        this.wvc.enterForeground();
        this.wvc.registerConnectionReceiver(activity);
        if (this.session == null) {
            resumeSession(activity);
        }
    }

    public void onPause(Activity activity) {
        try {
            this.wvc.enterBackground();
            this.wvc.unregisterConnectionReceiver(activity);
            endSession();
        } catch (Exception e) {
            e.printStackTrace();
            new IronSourceAsyncHttpRequestTask().execute(new String[]{Constants.NATIVE_EXCEPTION_BASE_URL + e.getStackTrace()[0].getMethodName()});
        }
    }

    public void release(Activity activity) {
        try {
            Logger.m1212i(TAG, "release()");
            DeviceProperties.release();
            this.wvc.unregisterConnectionReceiver(activity);
            if (Looper.getMainLooper().equals(Looper.myLooper())) {
                this.wvc.destroy();
                this.wvc = null;
            } else {
                new Handler(Looper.getMainLooper()).post(new C07102());
            }
        } catch (Exception e) {
        }
        sInstance = null;
        endSession();
    }

    public void onAdProductInitSuccess(ProductType type, String demandSourceName, AdUnitsReady adUnitsReady) {
        DemandSource demandSource = getDemandSourceByName(type, demandSourceName);
        if (demandSource != null) {
            demandSource.setDemandSourceInitState(2);
            if (type == ProductType.RewardedVideo) {
                OnRewardedVideoListener listener = getAdProductListenerAsRVListener(demandSource);
                if (listener != null) {
                    listener.onRVInitSuccess(adUnitsReady);
                }
            } else if (type == ProductType.Interstitial) {
                OnInterstitialListener listener2 = getAdProductListenerAsISListener(demandSource);
                if (listener2 != null) {
                    listener2.onInterstitialInitSuccess();
                }
            }
        }
    }

    public void onAdProductInitFailed(ProductType type, String demandSourceName, String description) {
        DemandSource demandSource = getDemandSourceByName(type, demandSourceName);
        if (demandSource != null) {
            demandSource.setDemandSourceInitState(3);
            if (type == ProductType.RewardedVideo) {
                OnRewardedVideoListener listener = getAdProductListenerAsRVListener(demandSource);
                if (listener != null) {
                    listener.onRVInitFail(description);
                }
            } else if (type == ProductType.Interstitial) {
                OnInterstitialListener listener2 = getAdProductListenerAsISListener(demandSource);
                if (listener2 != null) {
                    listener2.onInterstitialInitFailed(description);
                }
            }
        }
    }

    public void onRVNoMoreOffers(String demandSourceName) {
        DemandSource demandSource = getDemandSourceByName(ProductType.RewardedVideo, demandSourceName);
        if (demandSource != null) {
            OnRewardedVideoListener listener = getAdProductListenerAsRVListener(demandSource);
            if (listener != null) {
                listener.onRVNoMoreOffers();
            }
        }
    }

    public void onRVAdCredited(String demandSourceName, int credits) {
        DemandSource demandSource = getDemandSourceByName(ProductType.RewardedVideo, demandSourceName);
        if (demandSource != null) {
            OnRewardedVideoListener listener = getAdProductListenerAsRVListener(demandSource);
            if (listener != null) {
                listener.onRVAdCredited(credits);
            }
        }
    }

    public void onAdProductClose(ProductType type, String demandSourceName) {
        DemandSource demandSource = getDemandSourceByName(type, demandSourceName);
        if (demandSource == null) {
            return;
        }
        if (type == ProductType.RewardedVideo) {
            OnRewardedVideoListener listener = getAdProductListenerAsRVListener(demandSource);
            if (listener != null) {
                listener.onRVAdClosed();
            }
        } else if (type == ProductType.Interstitial) {
            OnInterstitialListener listener2 = getAdProductListenerAsISListener(demandSource);
            if (listener2 != null) {
                listener2.onInterstitialClose();
            }
        }
    }

    public void onRVShowFail(String demandSourceName, String description) {
        DemandSource demandSource = getDemandSourceByName(ProductType.RewardedVideo, demandSourceName);
        if (demandSource != null) {
            OnRewardedVideoListener listener = getAdProductListenerAsRVListener(demandSource);
            if (listener != null) {
                listener.onRVShowFail(description);
            }
        }
    }

    public void onAdProductClick(ProductType type, String demandSourceName) {
        DemandSource demandSource = getDemandSourceByName(type, demandSourceName);
        if (demandSource == null) {
            return;
        }
        if (type == ProductType.RewardedVideo) {
            OnRewardedVideoListener listener = getAdProductListenerAsRVListener(demandSource);
            if (listener != null) {
                listener.onRVAdClicked();
            }
        } else if (type == ProductType.Interstitial) {
            OnInterstitialListener listener2 = getAdProductListenerAsISListener(demandSource);
            if (listener2 != null) {
                listener2.onInterstitialClick();
            }
        }
    }

    public void onAdProductEventNotificationReceived(ProductType type, String demandSourceName, String eventName, JSONObject extData) {
        DemandSource demandSource = getDemandSourceByName(type, demandSourceName);
        if (demandSource != null) {
            try {
                if (type == ProductType.Interstitial) {
                    OnInterstitialListener listener = getAdProductListenerAsISListener(demandSource);
                    if (listener != null) {
                        extData.put("demandSourceName", demandSourceName);
                        listener.onInterstitialEventNotificationReceived(eventName, extData);
                    }
                } else if (type == ProductType.RewardedVideo) {
                    OnRewardedVideoListener listener2 = getAdProductListenerAsRVListener(demandSource);
                    if (listener2 != null) {
                        extData.put("demandSourceName", demandSourceName);
                        listener2.onRVEventNotificationReceived(eventName, extData);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void onAdProductOpen(ProductType type, String demandSourceName) {
        DemandSource demandSource = getDemandSourceByName(type, demandSourceName);
        if (demandSource == null) {
            return;
        }
        if (type == ProductType.Interstitial) {
            OnInterstitialListener listener = getAdProductListenerAsISListener(demandSource);
            if (listener != null) {
                listener.onInterstitialOpen();
            }
        } else if (type == ProductType.RewardedVideo) {
            OnRewardedVideoListener listener2 = getAdProductListenerAsRVListener(demandSource);
            if (listener2 != null) {
                listener2.onRVAdOpened();
            }
        }
    }

    public void onInterstitialLoadSuccess(String demandSourceName) {
        DemandSource demandSource = getDemandSourceByName(ProductType.Interstitial, demandSourceName);
        if (demandSource != null) {
            OnInterstitialListener listener = getAdProductListenerAsISListener(demandSource);
            if (listener != null) {
                listener.onInterstitialLoadSuccess();
            }
        }
    }

    public void onInterstitialLoadFailed(String demandSourceName, String description) {
        DemandSource demandSource = getDemandSourceByName(ProductType.Interstitial, demandSourceName);
        if (demandSource != null) {
            OnInterstitialListener listener = getAdProductListenerAsISListener(demandSource);
            if (listener != null) {
                listener.onInterstitialLoadFailed(description);
            }
        }
    }

    public void onInterstitialShowSuccess(String demandSourceName) {
        DemandSource demandSource = getDemandSourceByName(ProductType.Interstitial, demandSourceName);
        if (demandSource != null) {
            OnInterstitialListener listener = getAdProductListenerAsISListener(demandSource);
            if (listener != null) {
                listener.onInterstitialShowSuccess();
            }
        }
    }

    public void onInterstitialShowFailed(String demandSourceName, String description) {
        DemandSource demandSource = getDemandSourceByName(ProductType.Interstitial, demandSourceName);
        if (demandSource != null) {
            OnInterstitialListener listener = getAdProductListenerAsISListener(demandSource);
            if (listener != null) {
                listener.onInterstitialShowFailed(description);
            }
        }
    }

    public DemandSource getDemandSourceByName(ProductType type, String demandSourceName) {
        if (TextUtils.isEmpty(demandSourceName)) {
            return null;
        }
        return this.mDemandSourceManager.getDemandSourceByName(type, demandSourceName);
    }

    public void setMediationState(String productType, String demandSourceName, int state) {
        if (!TextUtils.isEmpty(productType) && !TextUtils.isEmpty(demandSourceName)) {
            ProductType product = SDKUtils.getProductType(productType);
            if (product != null) {
                DemandSource demandSource = this.mDemandSourceManager.getDemandSourceByName(product, demandSourceName);
                if (demandSource != null) {
                    demandSource.setMediationState(state);
                }
            }
        }
    }

    public void updateConsentInfo(JSONObject consentParams) {
        if (this.wvc != null) {
            this.wvc.updateConsentInfo(consentParams);
        }
    }
}
