// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.adapters.ris;

import com.ironsource.mediationsdk.utils.SessionDepthManager;
import com.ironsource.mediationsdk.AbstractSmash;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.sdk.data.AdUnitsReady;
import android.text.TextUtils;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import java.util.Iterator;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import java.util.Map;
import java.util.HashMap;
import com.ironsource.sdk.SSAFactory;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import android.app.Activity;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.sdk.utils.SDKUtils;
import org.json.JSONException;
import org.json.JSONObject;
import com.ironsource.sdk.SSAPublisher;
import com.ironsource.sdk.listeners.OnRewardedVideoListener;
import com.ironsource.mediationsdk.AbstractAdapter;

public class RISAdapter extends AbstractAdapter implements OnRewardedVideoListener
{
    private final String AD_VISIBLE_EVENT_NAME;
    private final String DYNAMIC_CONTROLLER_CONFIG;
    private final String DYNAMIC_CONTROLLER_DEBUG_MODE;
    private final String DYNAMIC_CONTROLLER_URL;
    private boolean hasAdAvailable;
    private boolean mConsent;
    private boolean mDidReportInitStatus;
    private boolean mDidSetConsent;
    private SSAPublisher mSSAPublisher;
    
    private RISAdapter(final String s) {
        super(s);
        this.hasAdAvailable = false;
        this.mDidReportInitStatus = false;
        this.DYNAMIC_CONTROLLER_URL = "controllerUrl";
        this.DYNAMIC_CONTROLLER_DEBUG_MODE = "debugMode";
        this.DYNAMIC_CONTROLLER_CONFIG = "controllerConfig";
        this.AD_VISIBLE_EVENT_NAME = "impressions";
    }
    
    private void applyConsent(final boolean b) {
        if (this.mSSAPublisher == null) {
            return;
        }
        final JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                jsonObject.put("gdprConsentStatus", (Object)String.valueOf(b));
                jsonObject.put("demandSourceName", (Object)this.getProviderName());
                this.mSSAPublisher.updateConsentInfo(jsonObject);
            }
            catch (JSONException ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    public static RISAdapter startAdapter(final String s) {
        return new RISAdapter(s);
    }
    
    @Override
    public void fetchRewardedVideo(final JSONObject jsonObject) {
    }
    
    @Override
    public String getCoreSDKVersion() {
        return SDKUtils.getSDKVersion();
    }
    
    @Override
    public String getVersion() {
        return IronSourceUtils.getSDKVersion();
    }
    
    @Override
    public void initInterstitial(final Activity activity, final String s, final String s2, final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        SDKUtils.setControllerUrl(jsonObject.optString("controllerUrl"));
        if (this.isAdaptersDebugEnabled()) {
            SDKUtils.setDebugMode(3);
        }
        else {
            SDKUtils.setDebugMode(jsonObject.optInt("debugMode", 0));
        }
        SDKUtils.setControllerConfig(jsonObject.optString("controllerConfig", ""));
        activity.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    RISAdapter.this.mSSAPublisher = SSAFactory.getPublisherInstance(activity);
                    if (RISAdapter.this.mDidSetConsent) {
                        RISAdapter.this.applyConsent(RISAdapter.this.mConsent);
                    }
                    SSAFactory.getPublisherInstance(activity).initRewardedVideo(s, s2, RISAdapter.this.getProviderName(), new HashMap<String, String>(), RISAdapter.this);
                }
                catch (Exception ex) {
                    RISAdapter.this.onRVInitFail(ex.getMessage());
                }
            }
        });
    }
    
    @Override
    public void initRewardedVideo(final Activity activity, final String s, final String s2, final JSONObject jsonObject, final RewardedVideoSmashListener rewardedVideoSmashListener) {
    }
    
    @Override
    public boolean isInterstitialReady(final JSONObject jsonObject) {
        return this.hasAdAvailable;
    }
    
    @Override
    public boolean isRewardedVideoAvailable(final JSONObject jsonObject) {
        return false;
    }
    
    @Override
    public void loadInterstitial(final JSONObject jsonObject, InterstitialSmashListener interstitialSmashListener) {
        if (this.hasAdAvailable) {
            final Iterator<InterstitialSmashListener> iterator = this.mAllInterstitialSmashes.iterator();
            while (iterator.hasNext()) {
                interstitialSmashListener = iterator.next();
                if (interstitialSmashListener != null) {
                    interstitialSmashListener.onInterstitialAdReady();
                }
            }
        }
        else {
            final Iterator<InterstitialSmashListener> iterator2 = this.mAllInterstitialSmashes.iterator();
            while (iterator2.hasNext()) {
                interstitialSmashListener = iterator2.next();
                if (interstitialSmashListener != null) {
                    interstitialSmashListener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("No Ads to Load"));
                }
            }
        }
    }
    
    @Override
    public void onPause(final Activity activity) {
        if (this.mSSAPublisher != null) {
            this.mSSAPublisher.onPause(activity);
        }
    }
    
    @Override
    public void onRVAdClicked() {
        this.log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + ":onRVAdClicked()", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdClicked();
        }
    }
    
    @Override
    public void onRVAdClosed() {
        this.log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + ":onRVAdClosed()", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdClosed();
        }
    }
    
    @Override
    public void onRVAdCredited(final int n) {
        this.log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + ":onRVAdCredited()", 1);
        if (this.mRewardedInterstitial != null) {
            this.mRewardedInterstitial.onInterstitialAdRewarded();
        }
    }
    
    @Override
    public void onRVAdOpened() {
        this.log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + ":onRVAdOpened()", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdShowSucceeded();
            this.mActiveInterstitialSmash.onInterstitialAdOpened();
        }
    }
    
    @Override
    public void onRVEventNotificationReceived(final String s, final JSONObject jsonObject) {
        if (jsonObject != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :onRISEventNotificationReceived: " + s + " extData: " + jsonObject.toString(), 1);
            if (!TextUtils.isEmpty((CharSequence)s) && "impressions".equals(s) && this.mActiveInterstitialSmash != null) {
                this.mActiveInterstitialSmash.onInterstitialAdVisible();
            }
        }
    }
    
    @Override
    public void onRVInitFail(final String s) {
        this.log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + ":onRVInitFail()", 1);
        this.hasAdAvailable = false;
        if (!this.mDidReportInitStatus) {
            this.mDidReportInitStatus = true;
            for (final InterstitialSmashListener interstitialSmashListener : this.mAllInterstitialSmashes) {
                if (interstitialSmashListener != null) {
                    interstitialSmashListener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError(s, "Interstitial"));
                }
            }
        }
    }
    
    @Override
    public void onRVInitSuccess(final AdUnitsReady adUnitsReady) {
    Label_0050_Outer:
        while (true) {
            this.log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + ":onRVInitSuccess()", 1);
            int int1 = 0;
            while (true) {
                while (true) {
                    try {
                        int1 = Integer.parseInt(adUnitsReady.getNumOfAdUnits());
                        if (int1 > 0) {
                            final boolean hasAdAvailable = true;
                            this.hasAdAvailable = hasAdAvailable;
                            if (!this.mDidReportInitStatus) {
                                this.mDidReportInitStatus = true;
                                for (final InterstitialSmashListener interstitialSmashListener : this.mAllInterstitialSmashes) {
                                    if (interstitialSmashListener != null) {
                                        interstitialSmashListener.onInterstitialInitSuccess();
                                    }
                                }
                                break;
                            }
                            break;
                        }
                    }
                    catch (NumberFormatException ex) {
                        IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "onRVInitSuccess:parseInt()", ex);
                        continue Label_0050_Outer;
                    }
                    break;
                }
                final boolean hasAdAvailable = false;
                continue;
            }
        }
    }
    
    @Override
    public void onRVNoMoreOffers() {
        this.log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + ":onRVNoMoreOffers()", 1);
        if (!this.mDidReportInitStatus) {
            this.mDidReportInitStatus = true;
            for (final InterstitialSmashListener interstitialSmashListener : this.mAllInterstitialSmashes) {
                if (interstitialSmashListener != null) {
                    interstitialSmashListener.onInterstitialInitSuccess();
                }
            }
        }
    }
    
    @Override
    public void onRVShowFail(final String s) {
        this.log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + ":onRVShowFail()", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdShowFailed(new IronSourceError(509, "Show Failed"));
        }
    }
    
    @Override
    public void onResume(final Activity activity) {
        if (this.mSSAPublisher != null) {
            this.mSSAPublisher.onResume(activity);
        }
    }
    
    @Override
    protected void setConsent(final boolean mConsent) {
        this.mConsent = mConsent;
        this.mDidSetConsent = true;
        this.applyConsent(mConsent);
    }
    
    @Override
    protected void setMediationState(final AbstractSmash.MEDIATION_STATE mediation_STATE, final String s) {
        if (this.mSSAPublisher != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, this.getProviderName() + " :setMediationState(RIS:(rewardedvideo)):" + s + " , " + this.getProviderName() + " , " + mediation_STATE.getValue() + ")", 1);
            this.mSSAPublisher.setMediationState("rewardedvideo", this.getProviderName(), mediation_STATE.getValue());
        }
    }
    
    @Override
    public void showInterstitial(JSONObject jsonObject, final InterstitialSmashListener mActiveInterstitialSmash) {
        this.mActiveInterstitialSmash = mActiveInterstitialSmash;
        Label_0067: {
            if (this.mSSAPublisher == null) {
                break Label_0067;
            }
            final int sessionDepth = SessionDepthManager.getInstance().getSessionDepth(2);
            jsonObject = new JSONObject();
            while (true) {
                try {
                    jsonObject.put("demandSourceName", (Object)this.getProviderName());
                    jsonObject.put("sessionDepth", sessionDepth);
                    this.mSSAPublisher.showRewardedVideo(jsonObject);
                    return;
                }
                catch (JSONException ex) {
                    ex.printStackTrace();
                    continue;
                }
                break;
            }
        }
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdShowFailed(new IronSourceError(509, "Please call init before calling showRewardedVideo"));
        }
    }
    
    @Override
    public void showRewardedVideo(final JSONObject jsonObject, final RewardedVideoSmashListener rewardedVideoSmashListener) {
    }
}
