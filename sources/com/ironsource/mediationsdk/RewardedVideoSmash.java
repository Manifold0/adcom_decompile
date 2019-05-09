package com.ironsource.mediationsdk;

import android.app.Activity;
import com.ironsource.mediationsdk.AbstractSmash.MEDIATION_STATE;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.sdk.RewardedVideoManagerListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashApi;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;

public class RewardedVideoSmash extends AbstractSmash implements RewardedVideoSmashListener, RewardedVideoSmashApi {
    private final String REQUEST_URL_KEY = IronSourceConstants.REQUEST_URL;
    private String mRequestUrl;
    private JSONObject mRewardedVideoAdapterConfigs;
    private RewardedVideoManagerListener mRewardedVideoManagerListener;
    private int mTimeout;

    /* renamed from: com.ironsource.mediationsdk.RewardedVideoSmash$1 */
    class C06901 extends TimerTask {
        C06901() {
        }

        public void run() {
            cancel();
            if (RewardedVideoSmash.this.mRewardedVideoManagerListener != null) {
                RewardedVideoSmash.this.mLoggerManager.log(IronSourceTag.NATIVE, "Timeout for " + RewardedVideoSmash.this.getInstanceName(), 0);
                RewardedVideoSmash.this.setMediationState(MEDIATION_STATE.NOT_AVAILABLE);
                RewardedVideoSmash.this.mRewardedVideoManagerListener.onRewardedVideoAvailabilityChanged(false, RewardedVideoSmash.this);
            }
        }
    }

    RewardedVideoSmash(ProviderSettings adapterConfigs, int timeout) {
        super(adapterConfigs);
        this.mRewardedVideoAdapterConfigs = adapterConfigs.getRewardedVideoSettings();
        this.mMaxAdsPerIteration = this.mRewardedVideoAdapterConfigs.optInt("maxAdsPerIteration", 99);
        this.mMaxAdsPerSession = this.mRewardedVideoAdapterConfigs.optInt("maxAdsPerSession", 99);
        this.mMaxAdsPerDay = this.mRewardedVideoAdapterConfigs.optInt("maxAdsPerDay", 99);
        this.mRequestUrl = this.mRewardedVideoAdapterConfigs.optString(IronSourceConstants.REQUEST_URL);
        this.mTimeout = timeout;
    }

    void completeIteration() {
        this.mIterationShowCounter = 0;
        setMediationState(isRewardedVideoAvailable() ? MEDIATION_STATE.AVAILABLE : MEDIATION_STATE.NOT_AVAILABLE);
    }

    void startInitTimer() {
        try {
            stopInitTimer();
            this.mInitTimer = new Timer();
            this.mInitTimer.schedule(new C06901(), (long) (this.mTimeout * 1000));
        } catch (Exception e) {
            logException("startInitTimer", e.getLocalizedMessage());
        }
    }

    void startLoadTimer() {
    }

    protected String getAdUnitString() {
        return "rewardedvideo";
    }

    public void initRewardedVideo(Activity activity, String appKey, String userId) {
        startInitTimer();
        if (this.mAdapter != null) {
            this.mAdapter.addRewardedVideoListener(this);
            this.mLoggerManager.log(IronSourceTag.ADAPTER_API, getInstanceName() + ":initRewardedVideo()", 1);
            this.mAdapter.initRewardedVideo(activity, appKey, userId, this.mRewardedVideoAdapterConfigs, this);
        }
    }

    public void fetchRewardedVideo() {
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceTag.ADAPTER_API, getInstanceName() + ":fetchRewardedVideo()", 1);
            this.mAdapter.fetchRewardedVideo(this.mRewardedVideoAdapterConfigs);
        }
    }

    public void showRewardedVideo() {
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceTag.ADAPTER_API, getInstanceName() + ":showRewardedVideo()", 1);
            preShow();
            this.mAdapter.showRewardedVideo(this.mRewardedVideoAdapterConfigs, this);
        }
    }

    public boolean isRewardedVideoAvailable() {
        if (this.mAdapter == null) {
            return false;
        }
        this.mLoggerManager.log(IronSourceTag.ADAPTER_API, getInstanceName() + ":isRewardedVideoAvailable()", 1);
        return this.mAdapter.isRewardedVideoAvailable(this.mRewardedVideoAdapterConfigs);
    }

    public void setRewardedVideoManagerListener(RewardedVideoManagerListener listener) {
        this.mRewardedVideoManagerListener = listener;
    }

    public void onRewardedVideoAdShowFailed(IronSourceError error) {
        if (this.mRewardedVideoManagerListener != null) {
            this.mRewardedVideoManagerListener.onRewardedVideoAdShowFailed(error, this);
        }
    }

    public void onRewardedVideoAdOpened() {
        if (this.mRewardedVideoManagerListener != null) {
            this.mRewardedVideoManagerListener.onRewardedVideoAdOpened(this);
        }
    }

    public void onRewardedVideoAdClosed() {
        if (this.mRewardedVideoManagerListener != null) {
            this.mRewardedVideoManagerListener.onRewardedVideoAdClosed(this);
        }
        fetchRewardedVideo();
    }

    public void onRewardedVideoAvailabilityChanged(boolean available) {
        stopInitTimer();
        if (!isMediationAvailable()) {
            return;
        }
        if ((available && this.mMediationState != MEDIATION_STATE.AVAILABLE) || (!available && this.mMediationState != MEDIATION_STATE.NOT_AVAILABLE)) {
            setMediationState(available ? MEDIATION_STATE.AVAILABLE : MEDIATION_STATE.NOT_AVAILABLE);
            if (this.mRewardedVideoManagerListener != null) {
                this.mRewardedVideoManagerListener.onRewardedVideoAvailabilityChanged(available, this);
            }
        }
    }

    public void onRewardedVideoAdStarted() {
        if (this.mRewardedVideoManagerListener != null) {
            this.mRewardedVideoManagerListener.onRewardedVideoAdStarted(this);
        }
    }

    public void onRewardedVideoAdEnded() {
        if (this.mRewardedVideoManagerListener != null) {
            this.mRewardedVideoManagerListener.onRewardedVideoAdEnded(this);
        }
    }

    public void onRewardedVideoAdRewarded() {
        if (this.mRewardedVideoManagerListener != null) {
            this.mRewardedVideoManagerListener.onRewardedVideoAdRewarded(this);
        }
    }

    public void onRewardedVideoAdClicked() {
        if (this.mRewardedVideoManagerListener != null) {
            this.mRewardedVideoManagerListener.onRewardedVideoAdClicked(this);
        }
    }

    public void onRewardedVideoAdVisible() {
        if (this.mRewardedVideoManagerListener != null) {
            this.mRewardedVideoManagerListener.onRewardedVideoAdVisible(this);
        }
    }

    String getRequestUrl() {
        return this.mRequestUrl;
    }
}
