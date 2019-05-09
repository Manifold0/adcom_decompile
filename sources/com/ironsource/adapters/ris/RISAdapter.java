package com.ironsource.adapters.ris;

import android.app.Activity;
import android.text.TextUtils;
import com.ironsource.mediationsdk.AbstractAdapter;
import com.ironsource.mediationsdk.AbstractSmash.MEDIATION_STATE;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.mediationsdk.utils.SessionDepthManager;
import com.ironsource.sdk.SSAFactory;
import com.ironsource.sdk.SSAPublisher;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.ironsource.sdk.data.AdUnitsReady;
import com.ironsource.sdk.listeners.OnRewardedVideoListener;
import com.ironsource.sdk.utils.SDKUtils;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class RISAdapter extends AbstractAdapter implements OnRewardedVideoListener {
    private final String AD_VISIBLE_EVENT_NAME = "impressions";
    private final String DYNAMIC_CONTROLLER_CONFIG = RequestParameters.CONTROLLER_CONFIG;
    private final String DYNAMIC_CONTROLLER_DEBUG_MODE = "debugMode";
    private final String DYNAMIC_CONTROLLER_URL = "controllerUrl";
    private boolean hasAdAvailable = false;
    private boolean mConsent;
    private boolean mDidReportInitStatus = false;
    private boolean mDidSetConsent;
    private SSAPublisher mSSAPublisher;

    public static RISAdapter startAdapter(String providerName) {
        return new RISAdapter(providerName);
    }

    private RISAdapter(String providerName) {
        super(providerName);
    }

    public String getVersion() {
        return IronSourceUtils.getSDKVersion();
    }

    public String getCoreSDKVersion() {
        return SDKUtils.getSDKVersion();
    }

    public void onResume(Activity activity) {
        if (this.mSSAPublisher != null) {
            this.mSSAPublisher.onResume(activity);
        }
    }

    public void onPause(Activity activity) {
        if (this.mSSAPublisher != null) {
            this.mSSAPublisher.onPause(activity);
        }
    }

    public void initRewardedVideo(Activity activity, String appKey, String userId, JSONObject config, RewardedVideoSmashListener listener) {
    }

    public void fetchRewardedVideo(JSONObject config) {
    }

    public boolean isRewardedVideoAvailable(JSONObject config) {
        return false;
    }

    public void showRewardedVideo(JSONObject config, RewardedVideoSmashListener listener) {
    }

    public void initInterstitial(final Activity activity, final String appKey, final String userId, JSONObject config, InterstitialSmashListener listener) {
        SDKUtils.setControllerUrl(config.optString("controllerUrl"));
        if (isAdaptersDebugEnabled()) {
            SDKUtils.setDebugMode(3);
        } else {
            SDKUtils.setDebugMode(config.optInt("debugMode", 0));
        }
        SDKUtils.setControllerConfig(config.optString(RequestParameters.CONTROLLER_CONFIG, ""));
        activity.runOnUiThread(new Runnable() {
            public void run() {
                try {
                    RISAdapter.this.mSSAPublisher = SSAFactory.getPublisherInstance(activity);
                    if (RISAdapter.this.mDidSetConsent) {
                        RISAdapter.this.applyConsent(RISAdapter.this.mConsent);
                    }
                    SSAFactory.getPublisherInstance(activity).initRewardedVideo(appKey, userId, RISAdapter.this.getProviderName(), new HashMap(), RISAdapter.this);
                } catch (Exception e) {
                    RISAdapter.this.onRVInitFail(e.getMessage());
                }
            }
        });
    }

    public void loadInterstitial(JSONObject config, InterstitialSmashListener listener) {
        Iterator it;
        InterstitialSmashListener smash;
        if (this.hasAdAvailable) {
            it = this.mAllInterstitialSmashes.iterator();
            while (it.hasNext()) {
                smash = (InterstitialSmashListener) it.next();
                if (smash != null) {
                    smash.onInterstitialAdReady();
                }
            }
            return;
        }
        it = this.mAllInterstitialSmashes.iterator();
        while (it.hasNext()) {
            smash = (InterstitialSmashListener) it.next();
            if (smash != null) {
                smash.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("No Ads to Load"));
            }
        }
    }

    public void showInterstitial(JSONObject config, InterstitialSmashListener listener) {
        this.mActiveInterstitialSmash = listener;
        if (this.mSSAPublisher != null) {
            int sessionDepth = SessionDepthManager.getInstance().getSessionDepth(2);
            JSONObject showParams = new JSONObject();
            try {
                showParams.put("demandSourceName", getProviderName());
                showParams.put(RequestParameters.SESSION_DEPTH, sessionDepth);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.mSSAPublisher.showRewardedVideo(showParams);
        } else if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdShowFailed(new IronSourceError(IronSourceError.ERROR_CODE_NO_ADS_TO_SHOW, "Please call init before calling showRewardedVideo"));
        }
    }

    public boolean isInterstitialReady(JSONObject config) {
        return this.hasAdAvailable;
    }

    public void onRVInitSuccess(AdUnitsReady adUnitsReady) {
        log(IronSourceTag.INTERNAL, getProviderName() + ":onRVInitSuccess()", 1);
        int numOfAdUnits = 0;
        try {
            numOfAdUnits = Integer.parseInt(adUnitsReady.getNumOfAdUnits());
        } catch (NumberFormatException e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, "onRVInitSuccess:parseInt()", e);
        }
        this.hasAdAvailable = numOfAdUnits > 0;
        if (!this.mDidReportInitStatus) {
            this.mDidReportInitStatus = true;
            Iterator it = this.mAllInterstitialSmashes.iterator();
            while (it.hasNext()) {
                InterstitialSmashListener smash = (InterstitialSmashListener) it.next();
                if (smash != null) {
                    smash.onInterstitialInitSuccess();
                }
            }
        }
    }

    public void onRVInitFail(String description) {
        log(IronSourceTag.INTERNAL, getProviderName() + ":onRVInitFail()", 1);
        this.hasAdAvailable = false;
        if (!this.mDidReportInitStatus) {
            this.mDidReportInitStatus = true;
            Iterator it = this.mAllInterstitialSmashes.iterator();
            while (it.hasNext()) {
                InterstitialSmashListener smash = (InterstitialSmashListener) it.next();
                if (smash != null) {
                    smash.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError(description, "Interstitial"));
                }
            }
        }
    }

    public void onRVNoMoreOffers() {
        log(IronSourceTag.INTERNAL, getProviderName() + ":onRVNoMoreOffers()", 1);
        if (!this.mDidReportInitStatus) {
            this.mDidReportInitStatus = true;
            Iterator it = this.mAllInterstitialSmashes.iterator();
            while (it.hasNext()) {
                InterstitialSmashListener smash = (InterstitialSmashListener) it.next();
                if (smash != null) {
                    smash.onInterstitialInitSuccess();
                }
            }
        }
    }

    public void onRVAdCredited(int credits) {
        log(IronSourceTag.INTERNAL, getProviderName() + ":onRVAdCredited()", 1);
        if (this.mRewardedInterstitial != null) {
            this.mRewardedInterstitial.onInterstitialAdRewarded();
        }
    }

    public void onRVAdClosed() {
        log(IronSourceTag.INTERNAL, getProviderName() + ":onRVAdClosed()", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdClosed();
        }
    }

    public void onRVAdOpened() {
        log(IronSourceTag.INTERNAL, getProviderName() + ":onRVAdOpened()", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdShowSucceeded();
            this.mActiveInterstitialSmash.onInterstitialAdOpened();
        }
    }

    public void onRVShowFail(String description) {
        log(IronSourceTag.INTERNAL, getProviderName() + ":onRVShowFail()", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdShowFailed(new IronSourceError(IronSourceError.ERROR_CODE_NO_ADS_TO_SHOW, "Show Failed"));
        }
    }

    public void onRVAdClicked() {
        log(IronSourceTag.INTERNAL, getProviderName() + ":onRVAdClicked()", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdClicked();
        }
    }

    public void onRVEventNotificationReceived(String eventName, JSONObject extData) {
        if (extData != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :onRISEventNotificationReceived: " + eventName + " extData: " + extData.toString(), 1);
            if (!TextUtils.isEmpty(eventName) && "impressions".equals(eventName) && this.mActiveInterstitialSmash != null) {
                this.mActiveInterstitialSmash.onInterstitialAdVisible();
            }
        }
    }

    protected void setMediationState(MEDIATION_STATE state, String adUnit) {
        if (this.mSSAPublisher != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, getProviderName() + " :setMediationState(RIS:(rewardedvideo)):" + adUnit + " , " + getProviderName() + " , " + state.getValue() + ")", 1);
            this.mSSAPublisher.setMediationState("rewardedvideo", getProviderName(), state.getValue());
        }
    }

    protected void setConsent(boolean consent) {
        this.mConsent = consent;
        this.mDidSetConsent = true;
        applyConsent(consent);
    }

    private void applyConsent(boolean consent) {
        if (this.mSSAPublisher != null) {
            JSONObject consentParams = new JSONObject();
            try {
                consentParams.put(RequestParameters.GDPR_CONSENT_STATUS, String.valueOf(consent));
                consentParams.put("demandSourceName", getProviderName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.mSSAPublisher.updateConsentInfo(consentParams);
        }
    }
}
