// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.agent;

import android.os.Handler;
import android.os.Looper;
import com.ironsource.sdk.utils.DeviceProperties;
import com.ironsource.sdk.utils.IronSourceAsyncHttpRequestTask;
import com.ironsource.sdk.data.AdUnitsReady;
import org.json.JSONException;
import org.json.JSONObject;
import com.ironsource.sdk.listeners.OnAdProductListener;
import java.util.Map;
import com.ironsource.sdk.listeners.OnOfferWallListener;
import android.text.TextUtils;
import com.ironsource.sdk.data.SSAEnums;
import com.ironsource.sdk.listeners.OnRewardedVideoListener;
import com.ironsource.sdk.listeners.OnInterstitialListener;
import com.ironsource.sdk.data.DemandSource;
import com.ironsource.sdk.controller.PermissionsJSAdapter;
import com.ironsource.sdk.controller.MOATJSAdapter;
import com.ironsource.sdk.utils.Logger;
import com.ironsource.sdk.utils.SDKUtils;
import android.content.Context;
import com.ironsource.sdk.utils.IronSourceSharedPrefHelper;
import android.app.Activity;
import com.ironsource.sdk.controller.IronSourceWebView;
import com.ironsource.sdk.data.SSASession;
import com.ironsource.sdk.controller.DemandSourceManager;
import android.content.MutableContextWrapper;
import com.ironsource.sdk.listeners.internals.DSAdProductListener;
import com.ironsource.sdk.listeners.internals.DSInterstitialListener;
import com.ironsource.sdk.listeners.internals.DSRewardedVideoListener;
import com.ironsource.sdk.SSAPublisher;

public final class IronSourceAdsPublisherAgent implements SSAPublisher, DSRewardedVideoListener, DSInterstitialListener, DSAdProductListener
{
    private static final String TAG = "IronSourceAdsPublisherAgent";
    private static MutableContextWrapper mutableContextWrapper;
    private static IronSourceAdsPublisherAgent sInstance;
    private DemandSourceManager mDemandSourceManager;
    private SSASession session;
    private IronSourceWebView wvc;
    
    private IronSourceAdsPublisherAgent(final Activity activity, final int n) throws Exception {
        IronSourceSharedPrefHelper.getSupersonicPrefHelper((Context)activity);
        this.mDemandSourceManager = new DemandSourceManager();
        Logger.enableLogging(SDKUtils.getDebugMode());
        Logger.i("IronSourceAdsPublisherAgent", "C'tor");
        IronSourceAdsPublisherAgent.mutableContextWrapper = new MutableContextWrapper((Context)activity);
        activity.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                IronSourceAdsPublisherAgent.this.wvc = new IronSourceWebView((Context)IronSourceAdsPublisherAgent.mutableContextWrapper, IronSourceAdsPublisherAgent.this.mDemandSourceManager);
                IronSourceAdsPublisherAgent.this.wvc.addMoatJSInterface(new MOATJSAdapter(activity.getApplication()));
                IronSourceAdsPublisherAgent.this.wvc.addPermissionsJSInterface(new PermissionsJSAdapter(activity.getApplicationContext()));
                IronSourceAdsPublisherAgent.this.wvc.registerConnectionReceiver((Context)activity);
                IronSourceAdsPublisherAgent.this.wvc.setDebugMode(SDKUtils.getDebugMode());
                IronSourceAdsPublisherAgent.this.wvc.downloadController();
            }
        });
        this.startSession((Context)activity);
    }
    
    private void endSession() {
        if (this.session != null) {
            this.session.endSession();
            IronSourceSharedPrefHelper.getSupersonicPrefHelper().addSession(this.session);
            this.session = null;
        }
    }
    
    private OnInterstitialListener getAdProductListenerAsISListener(final DemandSource demandSource) {
        if (demandSource == null) {
            return null;
        }
        return (OnInterstitialListener)demandSource.getListener();
    }
    
    private OnRewardedVideoListener getAdProductListenerAsRVListener(final DemandSource demandSource) {
        if (demandSource == null) {
            return null;
        }
        return (OnRewardedVideoListener)demandSource.getListener();
    }
    
    public static IronSourceAdsPublisherAgent getInstance(final Activity activity) throws Exception {
        synchronized (IronSourceAdsPublisherAgent.class) {
            return getInstance(activity, 0);
        }
    }
    
    public static IronSourceAdsPublisherAgent getInstance(final Activity baseContext, final int n) throws Exception {
        synchronized (IronSourceAdsPublisherAgent.class) {
            Logger.i("IronSourceAdsPublisherAgent", "getInstance()");
            if (IronSourceAdsPublisherAgent.sInstance == null) {
                IronSourceAdsPublisherAgent.sInstance = new IronSourceAdsPublisherAgent(baseContext, n);
            }
            else {
                IronSourceAdsPublisherAgent.mutableContextWrapper.setBaseContext((Context)baseContext);
            }
            return IronSourceAdsPublisherAgent.sInstance;
        }
    }
    
    private void startSession(final Context context) {
        this.session = new SSASession(context, SSASession.SessionType.launched);
    }
    
    public DemandSource getDemandSourceByName(final SSAEnums.ProductType productType, final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return null;
        }
        return this.mDemandSourceManager.getDemandSourceByName(productType, s);
    }
    
    @Override
    public void getOfferWallCredits(final String s, final String s2, final OnOfferWallListener onOfferWallListener) {
        this.wvc.getOfferWallCredits(s, s2, onOfferWallListener);
    }
    
    public IronSourceWebView getWebViewController() {
        return this.wvc;
    }
    
    @Override
    public void initInterstitial(final String s, final String s2, final String s3, final Map<String, String> map, final OnInterstitialListener onInterstitialListener) {
        this.wvc.initInterstitial(s, s2, this.mDemandSourceManager.createDemandSource(SSAEnums.ProductType.Interstitial, s3, map, onInterstitialListener), this);
    }
    
    @Override
    public void initOfferWall(final String s, final String s2, final Map<String, String> map, final OnOfferWallListener onOfferWallListener) {
        this.wvc.initOfferWall(s, s2, map, onOfferWallListener);
    }
    
    @Override
    public void initRewardedVideo(final String s, final String s2, final String s3, final Map<String, String> map, final OnRewardedVideoListener onRewardedVideoListener) {
        this.wvc.initRewardedVideo(s, s2, this.mDemandSourceManager.createDemandSource(SSAEnums.ProductType.RewardedVideo, s3, map, onRewardedVideoListener), this);
    }
    
    @Override
    public boolean isInterstitialAdAvailable(final String s) {
        return this.wvc.isInterstitialAdAvailable(s);
    }
    
    @Override
    public void loadInterstitial(final JSONObject jsonObject) {
        String optString = null;
        if (jsonObject != null) {
            optString = jsonObject.optString("demandSourceName");
        }
        this.wvc.loadInterstitial(optString);
    }
    
    @Override
    public void onAdProductClick(final SSAEnums.ProductType productType, final String s) {
        final DemandSource demandSourceByName = this.getDemandSourceByName(productType, s);
        if (demandSourceByName != null) {
            if (productType == SSAEnums.ProductType.RewardedVideo) {
                final OnRewardedVideoListener adProductListenerAsRVListener = this.getAdProductListenerAsRVListener(demandSourceByName);
                if (adProductListenerAsRVListener != null) {
                    adProductListenerAsRVListener.onRVAdClicked();
                }
            }
            else if (productType == SSAEnums.ProductType.Interstitial) {
                final OnInterstitialListener adProductListenerAsISListener = this.getAdProductListenerAsISListener(demandSourceByName);
                if (adProductListenerAsISListener != null) {
                    adProductListenerAsISListener.onInterstitialClick();
                }
            }
        }
    }
    
    @Override
    public void onAdProductClose(final SSAEnums.ProductType productType, final String s) {
        final DemandSource demandSourceByName = this.getDemandSourceByName(productType, s);
        if (demandSourceByName != null) {
            if (productType == SSAEnums.ProductType.RewardedVideo) {
                final OnRewardedVideoListener adProductListenerAsRVListener = this.getAdProductListenerAsRVListener(demandSourceByName);
                if (adProductListenerAsRVListener != null) {
                    adProductListenerAsRVListener.onRVAdClosed();
                }
            }
            else if (productType == SSAEnums.ProductType.Interstitial) {
                final OnInterstitialListener adProductListenerAsISListener = this.getAdProductListenerAsISListener(demandSourceByName);
                if (adProductListenerAsISListener != null) {
                    adProductListenerAsISListener.onInterstitialClose();
                }
            }
        }
    }
    
    @Override
    public void onAdProductEventNotificationReceived(final SSAEnums.ProductType productType, final String s, final String s2, final JSONObject jsonObject) {
        final DemandSource demandSourceByName = this.getDemandSourceByName(productType, s);
        if (demandSourceByName != null) {
            try {
                if (productType == SSAEnums.ProductType.Interstitial) {
                    final OnInterstitialListener adProductListenerAsISListener = this.getAdProductListenerAsISListener(demandSourceByName);
                    if (adProductListenerAsISListener != null) {
                        jsonObject.put("demandSourceName", (Object)s);
                        adProductListenerAsISListener.onInterstitialEventNotificationReceived(s2, jsonObject);
                    }
                }
                else if (productType == SSAEnums.ProductType.RewardedVideo) {
                    final OnRewardedVideoListener adProductListenerAsRVListener = this.getAdProductListenerAsRVListener(demandSourceByName);
                    if (adProductListenerAsRVListener != null) {
                        jsonObject.put("demandSourceName", (Object)s);
                        adProductListenerAsRVListener.onRVEventNotificationReceived(s2, jsonObject);
                    }
                }
            }
            catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public void onAdProductInitFailed(final SSAEnums.ProductType productType, final String s, final String s2) {
        final DemandSource demandSourceByName = this.getDemandSourceByName(productType, s);
        if (demandSourceByName != null) {
            demandSourceByName.setDemandSourceInitState(3);
            if (productType == SSAEnums.ProductType.RewardedVideo) {
                final OnRewardedVideoListener adProductListenerAsRVListener = this.getAdProductListenerAsRVListener(demandSourceByName);
                if (adProductListenerAsRVListener != null) {
                    adProductListenerAsRVListener.onRVInitFail(s2);
                }
            }
            else if (productType == SSAEnums.ProductType.Interstitial) {
                final OnInterstitialListener adProductListenerAsISListener = this.getAdProductListenerAsISListener(demandSourceByName);
                if (adProductListenerAsISListener != null) {
                    adProductListenerAsISListener.onInterstitialInitFailed(s2);
                }
            }
        }
    }
    
    @Override
    public void onAdProductInitSuccess(final SSAEnums.ProductType productType, final String s, final AdUnitsReady adUnitsReady) {
        final DemandSource demandSourceByName = this.getDemandSourceByName(productType, s);
        if (demandSourceByName != null) {
            demandSourceByName.setDemandSourceInitState(2);
            if (productType == SSAEnums.ProductType.RewardedVideo) {
                final OnRewardedVideoListener adProductListenerAsRVListener = this.getAdProductListenerAsRVListener(demandSourceByName);
                if (adProductListenerAsRVListener != null) {
                    adProductListenerAsRVListener.onRVInitSuccess(adUnitsReady);
                }
            }
            else if (productType == SSAEnums.ProductType.Interstitial) {
                final OnInterstitialListener adProductListenerAsISListener = this.getAdProductListenerAsISListener(demandSourceByName);
                if (adProductListenerAsISListener != null) {
                    adProductListenerAsISListener.onInterstitialInitSuccess();
                }
            }
        }
    }
    
    @Override
    public void onAdProductOpen(final SSAEnums.ProductType productType, final String s) {
        final DemandSource demandSourceByName = this.getDemandSourceByName(productType, s);
        if (demandSourceByName != null) {
            if (productType == SSAEnums.ProductType.Interstitial) {
                final OnInterstitialListener adProductListenerAsISListener = this.getAdProductListenerAsISListener(demandSourceByName);
                if (adProductListenerAsISListener != null) {
                    adProductListenerAsISListener.onInterstitialOpen();
                }
            }
            else if (productType == SSAEnums.ProductType.RewardedVideo) {
                final OnRewardedVideoListener adProductListenerAsRVListener = this.getAdProductListenerAsRVListener(demandSourceByName);
                if (adProductListenerAsRVListener != null) {
                    adProductListenerAsRVListener.onRVAdOpened();
                }
            }
        }
    }
    
    @Override
    public void onInterstitialLoadFailed(final String s, final String s2) {
        final DemandSource demandSourceByName = this.getDemandSourceByName(SSAEnums.ProductType.Interstitial, s);
        if (demandSourceByName != null) {
            final OnInterstitialListener adProductListenerAsISListener = this.getAdProductListenerAsISListener(demandSourceByName);
            if (adProductListenerAsISListener != null) {
                adProductListenerAsISListener.onInterstitialLoadFailed(s2);
            }
        }
    }
    
    @Override
    public void onInterstitialLoadSuccess(final String s) {
        final DemandSource demandSourceByName = this.getDemandSourceByName(SSAEnums.ProductType.Interstitial, s);
        if (demandSourceByName != null) {
            final OnInterstitialListener adProductListenerAsISListener = this.getAdProductListenerAsISListener(demandSourceByName);
            if (adProductListenerAsISListener != null) {
                adProductListenerAsISListener.onInterstitialLoadSuccess();
            }
        }
    }
    
    @Override
    public void onInterstitialShowFailed(final String s, final String s2) {
        final DemandSource demandSourceByName = this.getDemandSourceByName(SSAEnums.ProductType.Interstitial, s);
        if (demandSourceByName != null) {
            final OnInterstitialListener adProductListenerAsISListener = this.getAdProductListenerAsISListener(demandSourceByName);
            if (adProductListenerAsISListener != null) {
                adProductListenerAsISListener.onInterstitialShowFailed(s2);
            }
        }
    }
    
    @Override
    public void onInterstitialShowSuccess(final String s) {
        final DemandSource demandSourceByName = this.getDemandSourceByName(SSAEnums.ProductType.Interstitial, s);
        if (demandSourceByName != null) {
            final OnInterstitialListener adProductListenerAsISListener = this.getAdProductListenerAsISListener(demandSourceByName);
            if (adProductListenerAsISListener != null) {
                adProductListenerAsISListener.onInterstitialShowSuccess();
            }
        }
    }
    
    @Override
    public void onPause(final Activity activity) {
        try {
            this.wvc.enterBackground();
            this.wvc.unregisterConnectionReceiver((Context)activity);
            this.endSession();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            new IronSourceAsyncHttpRequestTask().execute((Object[])new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + ex.getStackTrace()[0].getMethodName() });
        }
    }
    
    @Override
    public void onRVAdCredited(final String s, final int n) {
        final DemandSource demandSourceByName = this.getDemandSourceByName(SSAEnums.ProductType.RewardedVideo, s);
        if (demandSourceByName != null) {
            final OnRewardedVideoListener adProductListenerAsRVListener = this.getAdProductListenerAsRVListener(demandSourceByName);
            if (adProductListenerAsRVListener != null) {
                adProductListenerAsRVListener.onRVAdCredited(n);
            }
        }
    }
    
    @Override
    public void onRVNoMoreOffers(final String s) {
        final DemandSource demandSourceByName = this.getDemandSourceByName(SSAEnums.ProductType.RewardedVideo, s);
        if (demandSourceByName != null) {
            final OnRewardedVideoListener adProductListenerAsRVListener = this.getAdProductListenerAsRVListener(demandSourceByName);
            if (adProductListenerAsRVListener != null) {
                adProductListenerAsRVListener.onRVNoMoreOffers();
            }
        }
    }
    
    @Override
    public void onRVShowFail(final String s, final String s2) {
        final DemandSource demandSourceByName = this.getDemandSourceByName(SSAEnums.ProductType.RewardedVideo, s);
        if (demandSourceByName != null) {
            final OnRewardedVideoListener adProductListenerAsRVListener = this.getAdProductListenerAsRVListener(demandSourceByName);
            if (adProductListenerAsRVListener != null) {
                adProductListenerAsRVListener.onRVShowFail(s2);
            }
        }
    }
    
    @Override
    public void onResume(final Activity baseContext) {
        IronSourceAdsPublisherAgent.mutableContextWrapper.setBaseContext((Context)baseContext);
        this.wvc.enterForeground();
        this.wvc.registerConnectionReceiver((Context)baseContext);
        if (this.session == null) {
            this.resumeSession((Context)baseContext);
        }
    }
    
    @Override
    public void release(final Activity activity) {
        while (true) {
            try {
                Logger.i("IronSourceAdsPublisherAgent", "release()");
                DeviceProperties.release();
                this.wvc.unregisterConnectionReceiver((Context)activity);
                if (Looper.getMainLooper().equals(Looper.myLooper())) {
                    this.wvc.destroy();
                    this.wvc = null;
                }
                else {
                    new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            IronSourceAdsPublisherAgent.this.wvc.destroy();
                            IronSourceAdsPublisherAgent.this.wvc = null;
                        }
                    });
                }
                IronSourceAdsPublisherAgent.sInstance = null;
                this.endSession();
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
    }
    
    public void resumeSession(final Context context) {
        this.session = new SSASession(context, SSASession.SessionType.backFromBG);
    }
    
    @Override
    public void setMediationState(final String s, final String s2, final int mediationState) {
        if (!TextUtils.isEmpty((CharSequence)s) && !TextUtils.isEmpty((CharSequence)s2)) {
            final SSAEnums.ProductType productType = SDKUtils.getProductType(s);
            if (productType != null) {
                final DemandSource demandSourceByName = this.mDemandSourceManager.getDemandSourceByName(productType, s2);
                if (demandSourceByName != null) {
                    demandSourceByName.setMediationState(mediationState);
                }
            }
        }
    }
    
    @Override
    public void showInterstitial(final JSONObject jsonObject) {
        this.wvc.showInterstitial(jsonObject);
    }
    
    @Override
    public void showOfferWall(final Map<String, String> map) {
        this.wvc.showOfferWall(map);
    }
    
    @Override
    public void showRewardedVideo(final JSONObject jsonObject) {
        this.wvc.showRewardedVideo(jsonObject);
    }
    
    @Override
    public void updateConsentInfo(final JSONObject jsonObject) {
        if (this.wvc != null) {
            this.wvc.updateConsentInfo(jsonObject);
        }
    }
}
