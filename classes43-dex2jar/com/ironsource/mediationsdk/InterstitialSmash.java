// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk;

import com.ironsource.mediationsdk.utils.ErrorBuilder;
import java.util.TimerTask;
import java.util.Timer;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import android.app.Activity;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialManagerListener;
import com.ironsource.mediationsdk.sdk.InterstitialManagerListener;
import org.json.JSONObject;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialSmashApi;
import com.ironsource.mediationsdk.sdk.InterstitialSmashApi;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialListener;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;

public class InterstitialSmash extends AbstractSmash implements InterstitialSmashListener, RewardedInterstitialListener, InterstitialSmashApi, RewardedInterstitialSmashApi
{
    private JSONObject mInterstitialAdapterConfigs;
    private InterstitialManagerListener mInterstitialManagerListener;
    private RewardedInterstitialManagerListener mRewardedInterstitialManagerListener;
    private int mTimeout;
    
    InterstitialSmash(final ProviderSettings providerSettings, final int mTimeout) {
        super(providerSettings);
        this.mInterstitialAdapterConfigs = providerSettings.getInterstitialSettings();
        this.mMaxAdsPerIteration = this.mInterstitialAdapterConfigs.optInt("maxAdsPerIteration", 99);
        this.mMaxAdsPerSession = this.mInterstitialAdapterConfigs.optInt("maxAdsPerSession", 99);
        this.mMaxAdsPerDay = this.mInterstitialAdapterConfigs.optInt("maxAdsPerDay", 99);
        this.mIsMultipleInstances = providerSettings.isMultipleInstances();
        this.mSpId = providerSettings.getSubProviderId();
        this.mTimeout = mTimeout;
    }
    
    @Override
    void completeIteration() {
        this.mIterationShowCounter = 0;
        this.setMediationState(MEDIATION_STATE.INITIATED);
    }
    
    @Override
    protected String getAdUnitString() {
        return "interstitial";
    }
    
    @Override
    public void initInterstitial(final Activity activity, final String s, final String s2) {
        this.startInitTimer();
        if (this.mAdapter != null) {
            this.mAdapter.addInterstitialListener(this);
            if (this.mRewardedInterstitialManagerListener != null) {
                this.mAdapter.setRewardedInterstitialListener(this);
            }
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_API, this.getInstanceName() + ":initInterstitial()", 1);
            this.mAdapter.initInterstitial(activity, s, s2, this.mInterstitialAdapterConfigs, this);
        }
    }
    
    @Override
    public boolean isInterstitialReady() {
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_API, this.getInstanceName() + ":isInterstitialReady()", 1);
            return this.mAdapter.isInterstitialReady(this.mInterstitialAdapterConfigs);
        }
        return false;
    }
    
    @Override
    public void loadInterstitial() {
        this.startLoadTimer();
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_API, this.getInstanceName() + ":loadInterstitial()", 1);
            this.mAdapter.loadInterstitial(this.mInterstitialAdapterConfigs, this);
        }
    }
    
    @Override
    public void onInterstitialAdClicked() {
        if (this.mInterstitialManagerListener != null) {
            this.mInterstitialManagerListener.onInterstitialAdClicked(this);
        }
    }
    
    @Override
    public void onInterstitialAdClosed() {
        if (this.mInterstitialManagerListener != null) {
            this.mInterstitialManagerListener.onInterstitialAdClosed(this);
        }
    }
    
    @Override
    public void onInterstitialAdLoadFailed(final IronSourceError ironSourceError) {
        this.stopLoadTimer();
        if (this.mMediationState == MEDIATION_STATE.LOAD_PENDING && this.mInterstitialManagerListener != null) {
            this.mInterstitialManagerListener.onInterstitialAdLoadFailed(ironSourceError, this);
        }
    }
    
    @Override
    public void onInterstitialAdOpened() {
        if (this.mInterstitialManagerListener != null) {
            this.mInterstitialManagerListener.onInterstitialAdOpened(this);
        }
    }
    
    @Override
    public void onInterstitialAdReady() {
        this.stopLoadTimer();
        if (this.mMediationState == MEDIATION_STATE.LOAD_PENDING && this.mInterstitialManagerListener != null) {
            this.mInterstitialManagerListener.onInterstitialAdReady(this);
        }
    }
    
    @Override
    public void onInterstitialAdRewarded() {
        if (this.mRewardedInterstitialManagerListener != null) {
            this.mRewardedInterstitialManagerListener.onInterstitialAdRewarded(this);
        }
    }
    
    @Override
    public void onInterstitialAdShowFailed(final IronSourceError ironSourceError) {
        if (this.mInterstitialManagerListener != null) {
            this.mInterstitialManagerListener.onInterstitialAdShowFailed(ironSourceError, this);
        }
    }
    
    @Override
    public void onInterstitialAdShowSucceeded() {
        if (this.mInterstitialManagerListener != null) {
            this.mInterstitialManagerListener.onInterstitialAdShowSucceeded(this);
        }
    }
    
    @Override
    public void onInterstitialAdVisible() {
        if (this.mInterstitialManagerListener != null) {
            this.mInterstitialManagerListener.onInterstitialAdVisible(this);
        }
    }
    
    @Override
    public void onInterstitialInitFailed(final IronSourceError ironSourceError) {
        this.stopInitTimer();
        if (this.mMediationState == MEDIATION_STATE.INIT_PENDING) {
            this.setMediationState(MEDIATION_STATE.INIT_FAILED);
            if (this.mInterstitialManagerListener != null) {
                this.mInterstitialManagerListener.onInterstitialInitFailed(ironSourceError, this);
            }
        }
    }
    
    @Override
    public void onInterstitialInitSuccess() {
        this.stopInitTimer();
        if (this.mMediationState == MEDIATION_STATE.INIT_PENDING) {
            this.setMediationState(MEDIATION_STATE.INITIATED);
            if (this.mInterstitialManagerListener != null) {
                this.mInterstitialManagerListener.onInterstitialInitSuccess(this);
            }
        }
    }
    
    @Override
    public void setInterstitialManagerListener(final InterstitialManagerListener mInterstitialManagerListener) {
        this.mInterstitialManagerListener = mInterstitialManagerListener;
    }
    
    @Override
    public void setRewardedInterstitialManagerListener(final RewardedInterstitialManagerListener mRewardedInterstitialManagerListener) {
        this.mRewardedInterstitialManagerListener = mRewardedInterstitialManagerListener;
    }
    
    @Override
    public void showInterstitial() {
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_API, this.getInstanceName() + ":showInterstitial()", 1);
            this.preShow();
            this.mAdapter.showInterstitial(this.mInterstitialAdapterConfigs, this);
        }
    }
    
    @Override
    void startInitTimer() {
        try {
            this.stopInitTimer();
            (this.mInitTimer = new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    this.cancel();
                    if (InterstitialSmash.this.mMediationState == MEDIATION_STATE.INIT_PENDING && InterstitialSmash.this.mInterstitialManagerListener != null) {
                        InterstitialSmash.this.setMediationState(MEDIATION_STATE.INIT_FAILED);
                        InterstitialSmash.this.mInterstitialManagerListener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Timeout", "Interstitial"), InterstitialSmash.this);
                    }
                }
            }, this.mTimeout * 1000);
        }
        catch (Exception ex) {
            this.logException("startInitTimer", ex.getLocalizedMessage());
        }
    }
    
    @Override
    void startLoadTimer() {
        try {
            this.stopLoadTimer();
            (this.mLoadTimer = new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    this.cancel();
                    if (InterstitialSmash.this.mMediationState == MEDIATION_STATE.LOAD_PENDING && InterstitialSmash.this.mInterstitialManagerListener != null) {
                        InterstitialSmash.this.setMediationState(MEDIATION_STATE.NOT_AVAILABLE);
                        InterstitialSmash.this.mInterstitialManagerListener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("Timeout"), InterstitialSmash.this);
                    }
                }
            }, this.mTimeout * 1000);
        }
        catch (Exception ex) {
            this.logException("startLoadTimer", ex.getLocalizedMessage());
        }
    }
}
