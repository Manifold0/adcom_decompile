// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk;

import java.util.TimerTask;
import java.util.Timer;
import com.ironsource.mediationsdk.logger.IronSourceError;
import android.app.Activity;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.sdk.RewardedVideoManagerListener;
import org.json.JSONObject;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashApi;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;

public class RewardedVideoSmash extends AbstractSmash implements RewardedVideoSmashListener, RewardedVideoSmashApi
{
    private final String REQUEST_URL_KEY;
    private String mRequestUrl;
    private JSONObject mRewardedVideoAdapterConfigs;
    private RewardedVideoManagerListener mRewardedVideoManagerListener;
    private int mTimeout;
    
    RewardedVideoSmash(final ProviderSettings providerSettings, final int mTimeout) {
        super(providerSettings);
        this.REQUEST_URL_KEY = "requestUrl";
        this.mRewardedVideoAdapterConfigs = providerSettings.getRewardedVideoSettings();
        this.mMaxAdsPerIteration = this.mRewardedVideoAdapterConfigs.optInt("maxAdsPerIteration", 99);
        this.mMaxAdsPerSession = this.mRewardedVideoAdapterConfigs.optInt("maxAdsPerSession", 99);
        this.mMaxAdsPerDay = this.mRewardedVideoAdapterConfigs.optInt("maxAdsPerDay", 99);
        this.mRequestUrl = this.mRewardedVideoAdapterConfigs.optString("requestUrl");
        this.mTimeout = mTimeout;
    }
    
    @Override
    void completeIteration() {
        this.mIterationShowCounter = 0;
        MEDIATION_STATE mediationState;
        if (this.isRewardedVideoAvailable()) {
            mediationState = MEDIATION_STATE.AVAILABLE;
        }
        else {
            mediationState = MEDIATION_STATE.NOT_AVAILABLE;
        }
        this.setMediationState(mediationState);
    }
    
    @Override
    public void fetchRewardedVideo() {
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_API, this.getInstanceName() + ":fetchRewardedVideo()", 1);
            this.mAdapter.fetchRewardedVideo(this.mRewardedVideoAdapterConfigs);
        }
    }
    
    @Override
    protected String getAdUnitString() {
        return "rewardedvideo";
    }
    
    String getRequestUrl() {
        return this.mRequestUrl;
    }
    
    @Override
    public void initRewardedVideo(final Activity activity, final String s, final String s2) {
        this.startInitTimer();
        if (this.mAdapter != null) {
            this.mAdapter.addRewardedVideoListener(this);
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_API, this.getInstanceName() + ":initRewardedVideo()", 1);
            this.mAdapter.initRewardedVideo(activity, s, s2, this.mRewardedVideoAdapterConfigs, this);
        }
    }
    
    @Override
    public boolean isRewardedVideoAvailable() {
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_API, this.getInstanceName() + ":isRewardedVideoAvailable()", 1);
            return this.mAdapter.isRewardedVideoAvailable(this.mRewardedVideoAdapterConfigs);
        }
        return false;
    }
    
    @Override
    public void onRewardedVideoAdClicked() {
        if (this.mRewardedVideoManagerListener != null) {
            this.mRewardedVideoManagerListener.onRewardedVideoAdClicked(this);
        }
    }
    
    @Override
    public void onRewardedVideoAdClosed() {
        if (this.mRewardedVideoManagerListener != null) {
            this.mRewardedVideoManagerListener.onRewardedVideoAdClosed(this);
        }
        this.fetchRewardedVideo();
    }
    
    @Override
    public void onRewardedVideoAdEnded() {
        if (this.mRewardedVideoManagerListener != null) {
            this.mRewardedVideoManagerListener.onRewardedVideoAdEnded(this);
        }
    }
    
    @Override
    public void onRewardedVideoAdOpened() {
        if (this.mRewardedVideoManagerListener != null) {
            this.mRewardedVideoManagerListener.onRewardedVideoAdOpened(this);
        }
    }
    
    @Override
    public void onRewardedVideoAdRewarded() {
        if (this.mRewardedVideoManagerListener != null) {
            this.mRewardedVideoManagerListener.onRewardedVideoAdRewarded(this);
        }
    }
    
    @Override
    public void onRewardedVideoAdShowFailed(final IronSourceError ironSourceError) {
        if (this.mRewardedVideoManagerListener != null) {
            this.mRewardedVideoManagerListener.onRewardedVideoAdShowFailed(ironSourceError, this);
        }
    }
    
    @Override
    public void onRewardedVideoAdStarted() {
        if (this.mRewardedVideoManagerListener != null) {
            this.mRewardedVideoManagerListener.onRewardedVideoAdStarted(this);
        }
    }
    
    @Override
    public void onRewardedVideoAdVisible() {
        if (this.mRewardedVideoManagerListener != null) {
            this.mRewardedVideoManagerListener.onRewardedVideoAdVisible(this);
        }
    }
    
    @Override
    public void onRewardedVideoAvailabilityChanged(final boolean b) {
        this.stopInitTimer();
        if (this.isMediationAvailable() && ((b && this.mMediationState != MEDIATION_STATE.AVAILABLE) || (!b && this.mMediationState != MEDIATION_STATE.NOT_AVAILABLE))) {
            MEDIATION_STATE mediationState;
            if (b) {
                mediationState = MEDIATION_STATE.AVAILABLE;
            }
            else {
                mediationState = MEDIATION_STATE.NOT_AVAILABLE;
            }
            this.setMediationState(mediationState);
            if (this.mRewardedVideoManagerListener != null) {
                this.mRewardedVideoManagerListener.onRewardedVideoAvailabilityChanged(b, this);
            }
        }
    }
    
    @Override
    public void setRewardedVideoManagerListener(final RewardedVideoManagerListener mRewardedVideoManagerListener) {
        this.mRewardedVideoManagerListener = mRewardedVideoManagerListener;
    }
    
    @Override
    public void showRewardedVideo() {
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_API, this.getInstanceName() + ":showRewardedVideo()", 1);
            this.preShow();
            this.mAdapter.showRewardedVideo(this.mRewardedVideoAdapterConfigs, this);
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
                    if (RewardedVideoSmash.this.mRewardedVideoManagerListener != null) {
                        RewardedVideoSmash.this.mLoggerManager.log(IronSourceLogger.IronSourceTag.NATIVE, "Timeout for " + RewardedVideoSmash.this.getInstanceName(), 0);
                        RewardedVideoSmash.this.setMediationState(MEDIATION_STATE.NOT_AVAILABLE);
                        RewardedVideoSmash.this.mRewardedVideoManagerListener.onRewardedVideoAvailabilityChanged(false, RewardedVideoSmash.this);
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
    }
}
