package com.ironsource.mediationsdk;

import android.app.Activity;
import com.ironsource.mediationsdk.AbstractSmash.MEDIATION_STATE;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.sdk.InterstitialManagerListener;
import com.ironsource.mediationsdk.sdk.InterstitialSmashApi;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialListener;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialManagerListener;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialSmashApi;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;

public class InterstitialSmash extends AbstractSmash implements InterstitialSmashListener, RewardedInterstitialListener, InterstitialSmashApi, RewardedInterstitialSmashApi {
    private JSONObject mInterstitialAdapterConfigs;
    private InterstitialManagerListener mInterstitialManagerListener;
    private RewardedInterstitialManagerListener mRewardedInterstitialManagerListener;
    private int mTimeout;

    /* renamed from: com.ironsource.mediationsdk.InterstitialSmash$1 */
    class C06811 extends TimerTask {
        C06811() {
        }

        public void run() {
            cancel();
            if (InterstitialSmash.this.mMediationState == MEDIATION_STATE.INIT_PENDING && InterstitialSmash.this.mInterstitialManagerListener != null) {
                InterstitialSmash.this.setMediationState(MEDIATION_STATE.INIT_FAILED);
                InterstitialSmash.this.mInterstitialManagerListener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Timeout", "Interstitial"), InterstitialSmash.this);
            }
        }
    }

    /* renamed from: com.ironsource.mediationsdk.InterstitialSmash$2 */
    class C06822 extends TimerTask {
        C06822() {
        }

        public void run() {
            cancel();
            if (InterstitialSmash.this.mMediationState == MEDIATION_STATE.LOAD_PENDING && InterstitialSmash.this.mInterstitialManagerListener != null) {
                InterstitialSmash.this.setMediationState(MEDIATION_STATE.NOT_AVAILABLE);
                InterstitialSmash.this.mInterstitialManagerListener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("Timeout"), InterstitialSmash.this);
            }
        }
    }

    InterstitialSmash(ProviderSettings adapterConfigs, int timeout) {
        super(adapterConfigs);
        this.mInterstitialAdapterConfigs = adapterConfigs.getInterstitialSettings();
        this.mMaxAdsPerIteration = this.mInterstitialAdapterConfigs.optInt("maxAdsPerIteration", 99);
        this.mMaxAdsPerSession = this.mInterstitialAdapterConfigs.optInt("maxAdsPerSession", 99);
        this.mMaxAdsPerDay = this.mInterstitialAdapterConfigs.optInt("maxAdsPerDay", 99);
        this.mIsMultipleInstances = adapterConfigs.isMultipleInstances();
        this.mSpId = adapterConfigs.getSubProviderId();
        this.mTimeout = timeout;
    }

    void completeIteration() {
        this.mIterationShowCounter = 0;
        setMediationState(MEDIATION_STATE.INITIATED);
    }

    void startInitTimer() {
        try {
            stopInitTimer();
            this.mInitTimer = new Timer();
            this.mInitTimer.schedule(new C06811(), (long) (this.mTimeout * 1000));
        } catch (Exception e) {
            logException("startInitTimer", e.getLocalizedMessage());
        }
    }

    void startLoadTimer() {
        try {
            stopLoadTimer();
            this.mLoadTimer = new Timer();
            this.mLoadTimer.schedule(new C06822(), (long) (this.mTimeout * 1000));
        } catch (Exception e) {
            logException("startLoadTimer", e.getLocalizedMessage());
        }
    }

    protected String getAdUnitString() {
        return "interstitial";
    }

    public void initInterstitial(Activity activity, String appKey, String userId) {
        startInitTimer();
        if (this.mAdapter != null) {
            this.mAdapter.addInterstitialListener(this);
            if (this.mRewardedInterstitialManagerListener != null) {
                this.mAdapter.setRewardedInterstitialListener(this);
            }
            this.mLoggerManager.log(IronSourceTag.ADAPTER_API, getInstanceName() + ":initInterstitial()", 1);
            this.mAdapter.initInterstitial(activity, appKey, userId, this.mInterstitialAdapterConfigs, this);
        }
    }

    public void loadInterstitial() {
        startLoadTimer();
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceTag.ADAPTER_API, getInstanceName() + ":loadInterstitial()", 1);
            this.mAdapter.loadInterstitial(this.mInterstitialAdapterConfigs, this);
        }
    }

    public void showInterstitial() {
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceTag.ADAPTER_API, getInstanceName() + ":showInterstitial()", 1);
            preShow();
            this.mAdapter.showInterstitial(this.mInterstitialAdapterConfigs, this);
        }
    }

    public boolean isInterstitialReady() {
        if (this.mAdapter == null) {
            return false;
        }
        this.mLoggerManager.log(IronSourceTag.ADAPTER_API, getInstanceName() + ":isInterstitialReady()", 1);
        return this.mAdapter.isInterstitialReady(this.mInterstitialAdapterConfigs);
    }

    public void setInterstitialManagerListener(InterstitialManagerListener listener) {
        this.mInterstitialManagerListener = listener;
    }

    public void setRewardedInterstitialManagerListener(RewardedInterstitialManagerListener listener) {
        this.mRewardedInterstitialManagerListener = listener;
    }

    public void onInterstitialInitSuccess() {
        stopInitTimer();
        if (this.mMediationState == MEDIATION_STATE.INIT_PENDING) {
            setMediationState(MEDIATION_STATE.INITIATED);
            if (this.mInterstitialManagerListener != null) {
                this.mInterstitialManagerListener.onInterstitialInitSuccess(this);
            }
        }
    }

    public void onInterstitialInitFailed(IronSourceError error) {
        stopInitTimer();
        if (this.mMediationState == MEDIATION_STATE.INIT_PENDING) {
            setMediationState(MEDIATION_STATE.INIT_FAILED);
            if (this.mInterstitialManagerListener != null) {
                this.mInterstitialManagerListener.onInterstitialInitFailed(error, this);
            }
        }
    }

    public void onInterstitialAdReady() {
        stopLoadTimer();
        if (this.mMediationState == MEDIATION_STATE.LOAD_PENDING && this.mInterstitialManagerListener != null) {
            this.mInterstitialManagerListener.onInterstitialAdReady(this);
        }
    }

    public void onInterstitialAdLoadFailed(IronSourceError error) {
        stopLoadTimer();
        if (this.mMediationState == MEDIATION_STATE.LOAD_PENDING && this.mInterstitialManagerListener != null) {
            this.mInterstitialManagerListener.onInterstitialAdLoadFailed(error, this);
        }
    }

    public void onInterstitialAdOpened() {
        if (this.mInterstitialManagerListener != null) {
            this.mInterstitialManagerListener.onInterstitialAdOpened(this);
        }
    }

    public void onInterstitialAdClosed() {
        if (this.mInterstitialManagerListener != null) {
            this.mInterstitialManagerListener.onInterstitialAdClosed(this);
        }
    }

    public void onInterstitialAdShowSucceeded() {
        if (this.mInterstitialManagerListener != null) {
            this.mInterstitialManagerListener.onInterstitialAdShowSucceeded(this);
        }
    }

    public void onInterstitialAdShowFailed(IronSourceError error) {
        if (this.mInterstitialManagerListener != null) {
            this.mInterstitialManagerListener.onInterstitialAdShowFailed(error, this);
        }
    }

    public void onInterstitialAdClicked() {
        if (this.mInterstitialManagerListener != null) {
            this.mInterstitialManagerListener.onInterstitialAdClicked(this);
        }
    }

    public void onInterstitialAdVisible() {
        if (this.mInterstitialManagerListener != null) {
            this.mInterstitialManagerListener.onInterstitialAdVisible(this);
        }
    }

    public void onInterstitialAdRewarded() {
        if (this.mRewardedInterstitialManagerListener != null) {
            this.mRewardedInterstitialManagerListener.onInterstitialAdRewarded(this);
        }
    }
}
