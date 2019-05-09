package com.ironsource.mediationsdk.sdk;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.events.InterstitialEventsManager;
import com.ironsource.mediationsdk.events.RewardedVideoEventsManager;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class ListenersWrapper implements RewardedVideoListener, InterstitialListener, InternalOfferwallListener, RewardedInterstitialListener, SegmentListener, ISDemandOnlyRewardedVideoListener, ISDemandOnlyInterstitialListener {
    private CallbackHandlerThread mCallbackHandlerThread = new CallbackHandlerThread();
    private ISDemandOnlyInterstitialListener mISDemandOnlyInterstitialListener;
    private ISDemandOnlyRewardedVideoListener mISDemandOnlyRewardedVideoListener;
    private InterstitialListener mInterstitialListener;
    private OfferwallListener mOfferwallListener;
    private RewardedInterstitialListener mRewardedInterstitialListener;
    private RewardedVideoListener mRewardedVideoListener;
    private SegmentListener mSegementListener;

    /* renamed from: com.ironsource.mediationsdk.sdk.ListenersWrapper$2 */
    class C06982 implements Runnable {
        C06982() {
        }

        public void run() {
            ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdOpened();
        }
    }

    /* renamed from: com.ironsource.mediationsdk.sdk.ListenersWrapper$3 */
    class C06993 implements Runnable {
        C06993() {
        }

        public void run() {
            ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdClosed();
        }
    }

    /* renamed from: com.ironsource.mediationsdk.sdk.ListenersWrapper$5 */
    class C07015 implements Runnable {
        C07015() {
        }

        public void run() {
            ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdStarted();
        }
    }

    /* renamed from: com.ironsource.mediationsdk.sdk.ListenersWrapper$6 */
    class C07026 implements Runnable {
        C07026() {
        }

        public void run() {
            ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdEnded();
        }
    }

    private class CallbackHandlerThread extends Thread {
        private Handler mCallbackHandler;

        private CallbackHandlerThread() {
        }

        public void run() {
            Looper.prepare();
            this.mCallbackHandler = new Handler();
            Looper.loop();
        }

        public Handler getCallbackHandler() {
            return this.mCallbackHandler;
        }
    }

    public ListenersWrapper() {
        this.mCallbackHandlerThread.start();
    }

    private boolean canSendCallback(Object productListener) {
        return (productListener == null || this.mCallbackHandlerThread == null) ? false : true;
    }

    private void sendCallback(Runnable callbackRunnable) {
        if (this.mCallbackHandlerThread != null) {
            Handler callbackHandler = this.mCallbackHandlerThread.getCallbackHandler();
            if (callbackHandler != null) {
                callbackHandler.post(callbackRunnable);
            }
        }
    }

    public void setRewardedVideoListener(RewardedVideoListener rewardedVideoListener) {
        this.mRewardedVideoListener = rewardedVideoListener;
    }

    public void setISDemandOnlyRewardedVideoListener(ISDemandOnlyRewardedVideoListener demandOnlyRewardedVideoListener) {
        this.mISDemandOnlyRewardedVideoListener = demandOnlyRewardedVideoListener;
    }

    public void setInterstitialListener(InterstitialListener interstitialListener) {
        this.mInterstitialListener = interstitialListener;
    }

    public void setISDemandOnlyInterstitialListener(ISDemandOnlyInterstitialListener demandOnlyInterstitialListener) {
        this.mISDemandOnlyInterstitialListener = demandOnlyInterstitialListener;
    }

    public void setOfferwallListener(OfferwallListener offerwallListener) {
        this.mOfferwallListener = offerwallListener;
    }

    public void setRewardedInterstitialListener(RewardedInterstitialListener rewardedInterstitialListener) {
        this.mRewardedInterstitialListener = rewardedInterstitialListener;
    }

    public void setSegmentListener(SegmentListener segmentListener) {
        this.mSegementListener = segmentListener;
    }

    public void onSegmentReceived(final String segment) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onSegmentReceived(" + segment + ")", 1);
        if (canSendCallback(this.mSegementListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    if (!TextUtils.isEmpty(segment)) {
                        ListenersWrapper.this.mSegementListener.onSegmentReceived(segment);
                    }
                }
            });
        }
    }

    public void onRewardedVideoAdOpened() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onRewardedVideoAdOpened()", 1);
        if (canSendCallback(this.mRewardedVideoListener)) {
            sendCallback(new C06982());
        }
    }

    public void onRewardedVideoAdClosed() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onRewardedVideoAdClosed()", 1);
        if (canSendCallback(this.mRewardedVideoListener)) {
            sendCallback(new C06993());
        }
    }

    public void onRewardedVideoAvailabilityChanged(final boolean available) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onRewardedVideoAvailabilityChanged(available:" + available + ")", 1);
        JSONObject data = IronSourceUtils.getMediationAdditionalData(false);
        try {
            data.put("status", String.valueOf(available));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(7, data));
        if (canSendCallback(this.mRewardedVideoListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAvailabilityChanged(available);
                }
            });
        }
    }

    public void onRewardedVideoAdStarted() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onRewardedVideoAdStarted()", 1);
        if (canSendCallback(this.mRewardedVideoListener)) {
            sendCallback(new C07015());
        }
    }

    public void onRewardedVideoAdEnded() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onRewardedVideoAdEnded()", 1);
        if (canSendCallback(this.mRewardedVideoListener)) {
            sendCallback(new C07026());
        }
    }

    public void onRewardedVideoAdRewarded(final Placement placement) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onRewardedVideoAdRewarded(" + placement.toString() + ")", 1);
        if (canSendCallback(this.mRewardedVideoListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdRewarded(placement);
                }
            });
        }
    }

    public void onRewardedVideoAdClicked(final Placement placement) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onRewardedVideoAdClicked(" + placement.getPlacementName() + ")", 1);
        if (canSendCallback(this.mRewardedVideoListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdClicked(placement);
                }
            });
        }
    }

    public void onRewardedVideoAdShowFailed(final IronSourceError error) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onRewardedVideoAdShowFailed(" + error.toString() + ")", 1);
        JSONObject data = IronSourceUtils.getMediationAdditionalData(false);
        try {
            data.put("status", "false");
            if (error.getErrorCode() == IronSourceError.ERROR_REACHED_CAP_LIMIT_PER_PLACEMENT) {
                data.put("reason", 1);
            }
            data.put(IronSourceConstants.ERROR_CODE_KEY, error.getErrorCode());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(17, data));
        if (canSendCallback(this.mRewardedVideoListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdShowFailed(error);
                }
            });
        }
    }

    public void onInterstitialAdReady() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onInterstitialAdReady()", 1);
        if (canSendCallback(this.mInterstitialListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mInterstitialListener.onInterstitialAdReady();
                }
            });
        }
    }

    public void onInterstitialAdLoadFailed(final IronSourceError error) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onInterstitialAdLoadFailed(" + error + ")", 1);
        if (canSendCallback(this.mInterstitialListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mInterstitialListener.onInterstitialAdLoadFailed(error);
                }
            });
        }
    }

    public void onInterstitialAdOpened() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onInterstitialAdOpened()", 1);
        if (canSendCallback(this.mInterstitialListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mInterstitialListener.onInterstitialAdOpened();
                }
            });
        }
    }

    public void onInterstitialAdShowSucceeded() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onInterstitialAdShowSucceeded()", 1);
        if (canSendCallback(this.mInterstitialListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mInterstitialListener.onInterstitialAdShowSucceeded();
                }
            });
        }
    }

    public void onInterstitialAdShowFailed(final IronSourceError error) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onInterstitialAdShowFailed(" + error + ")", 1);
        JSONObject data = IronSourceUtils.getMediationAdditionalData(false);
        try {
            if (error.getErrorCode() == IronSourceError.ERROR_REACHED_CAP_LIMIT_PER_PLACEMENT) {
                data.put("reason", 1);
            }
            data.put(IronSourceConstants.ERROR_CODE_KEY, error.getErrorCode());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        InterstitialEventsManager.getInstance().log(new EventData(29, data));
        if (canSendCallback(this.mInterstitialListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mInterstitialListener.onInterstitialAdShowFailed(error);
                }
            });
        }
    }

    public void onInterstitialAdClicked() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onInterstitialAdClicked()", 1);
        if (canSendCallback(this.mInterstitialListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mInterstitialListener.onInterstitialAdClicked();
                }
            });
        }
    }

    public void onInterstitialAdClosed() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onInterstitialAdClosed()", 1);
        if (canSendCallback(this.mInterstitialListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mInterstitialListener.onInterstitialAdClosed();
                }
            });
        }
    }

    public void onInterstitialAdRewarded() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onInterstitialAdRewarded()", 1);
        if (canSendCallback(this.mRewardedInterstitialListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mRewardedInterstitialListener.onInterstitialAdRewarded();
                }
            });
        }
    }

    public void onOfferwallOpened() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onOfferwallOpened()", 1);
        if (canSendCallback(this.mOfferwallListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mOfferwallListener.onOfferwallOpened();
                }
            });
        }
    }

    public void onOfferwallShowFailed(final IronSourceError error) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onOfferwallShowFailed(" + error + ")", 1);
        if (canSendCallback(this.mOfferwallListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mOfferwallListener.onOfferwallShowFailed(error);
                }
            });
        }
    }

    public boolean onOfferwallAdCredited(int credits, int totalCredits, boolean totalCreditsFlag) {
        boolean result = false;
        if (this.mOfferwallListener != null) {
            result = this.mOfferwallListener.onOfferwallAdCredited(credits, totalCredits, totalCreditsFlag);
        }
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onOfferwallAdCredited(credits:" + credits + ", " + "totalCredits:" + totalCredits + ", " + "totalCreditsFlag:" + totalCreditsFlag + "):" + result, 1);
        return result;
    }

    public void onGetOfferwallCreditsFailed(final IronSourceError error) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onGetOfferwallCreditsFailed(" + error + ")", 1);
        if (canSendCallback(this.mOfferwallListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mOfferwallListener.onGetOfferwallCreditsFailed(error);
                }
            });
        }
    }

    public void onOfferwallClosed() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onOfferwallClosed()", 1);
        if (canSendCallback(this.mOfferwallListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mOfferwallListener.onOfferwallClosed();
                }
            });
        }
    }

    public void onOfferwallAvailable(boolean isAvailable) {
        onOfferwallAvailable(isAvailable, null);
    }

    public void onOfferwallAvailable(final boolean isAvailable, IronSourceError error) {
        String logString = "onOfferwallAvailable(isAvailable: " + isAvailable + ")";
        if (error != null) {
            logString = logString + ", error: " + error.getErrorMessage();
        }
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, logString, 1);
        JSONObject data = IronSourceUtils.getMediationAdditionalData(false);
        try {
            data.put("status", String.valueOf(isAvailable));
            if (error != null) {
                data.put(IronSourceConstants.ERROR_CODE_KEY, error.getErrorCode());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(IronSourceConstants.OFFERWALL_AVAILABLE, data));
        if (canSendCallback(this.mOfferwallListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mOfferwallListener.onOfferwallAvailable(isAvailable);
                }
            });
        }
    }

    public void onRewardedVideoAdOpened(final String instanceId) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onRewardedVideoAdOpened(" + instanceId + ")", 1);
        if (canSendCallback(this.mISDemandOnlyRewardedVideoListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyRewardedVideoListener.onRewardedVideoAdOpened(instanceId);
                }
            });
        }
    }

    public void onRewardedVideoAdClosed(final String instanceId) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onRewardedVideoAdClosed(" + instanceId + ")", 1);
        if (canSendCallback(this.mISDemandOnlyRewardedVideoListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyRewardedVideoListener.onRewardedVideoAdClosed(instanceId);
                }
            });
        }
    }

    public void onRewardedVideoAvailabilityChanged(final String instanceId, final boolean available) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onRewardedVideoAvailabilityChanged(" + instanceId + ", " + available + ")", 1);
        if (canSendCallback(this.mISDemandOnlyRewardedVideoListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyRewardedVideoListener.onRewardedVideoAvailabilityChanged(instanceId, available);
                }
            });
        }
    }

    public void onRewardedVideoAdRewarded(final String instanceId, final Placement placement) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onRewardedVideoAdRewarded(" + instanceId + ", " + placement.toString() + ")", 1);
        if (canSendCallback(this.mISDemandOnlyRewardedVideoListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyRewardedVideoListener.onRewardedVideoAdRewarded(instanceId, placement);
                }
            });
        }
    }

    public void onRewardedVideoAdShowFailed(final String instanceId, final IronSourceError error) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onRewardedVideoAdShowFailed(" + instanceId + ", " + error.toString() + ")", 1);
        JSONObject data = IronSourceUtils.getMediationAdditionalData(true);
        try {
            data.put("status", "false");
            if (error.getErrorCode() == IronSourceError.ERROR_REACHED_CAP_LIMIT_PER_PLACEMENT) {
                data.put("reason", 1);
            }
            data.put(IronSourceConstants.ERROR_CODE_KEY, error.getErrorCode());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(17, data));
        if (canSendCallback(this.mISDemandOnlyRewardedVideoListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyRewardedVideoListener.onRewardedVideoAdShowFailed(instanceId, error);
                }
            });
        }
    }

    public void onRewardedVideoAdClicked(final String instanceId, final Placement placement) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onRewardedVideoAdClicked(" + instanceId + ", " + placement.getPlacementName() + ")", 1);
        if (canSendCallback(this.mISDemandOnlyRewardedVideoListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyRewardedVideoListener.onRewardedVideoAdClicked(instanceId, placement);
                }
            });
        }
    }

    public void onInterstitialAdReady(final String instanceId) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onInterstitialAdReady(" + instanceId + ")", 1);
        if (canSendCallback(this.mISDemandOnlyInterstitialListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyInterstitialListener.onInterstitialAdReady(instanceId);
                }
            });
        }
    }

    public void onInterstitialAdLoadFailed(final String instanceId, final IronSourceError error) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onInterstitialAdLoadFailed(" + instanceId + ", " + error + ")", 1);
        if (canSendCallback(this.mISDemandOnlyInterstitialListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyInterstitialListener.onInterstitialAdLoadFailed(instanceId, error);
                }
            });
        }
    }

    public void onInterstitialAdOpened(final String instanceId) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onInterstitialAdOpened(" + instanceId + ")", 1);
        if (canSendCallback(this.mISDemandOnlyInterstitialListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyInterstitialListener.onInterstitialAdOpened(instanceId);
                }
            });
        }
    }

    public void onInterstitialAdClosed(final String instanceId) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onInterstitialAdClosed(" + instanceId + ")", 1);
        if (canSendCallback(this.mISDemandOnlyInterstitialListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyInterstitialListener.onInterstitialAdClosed(instanceId);
                }
            });
        }
    }

    public void onInterstitialAdShowSucceeded(final String instanceId) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onInterstitialAdShowSucceeded(" + instanceId + ")", 1);
        if (canSendCallback(this.mISDemandOnlyInterstitialListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyInterstitialListener.onInterstitialAdShowSucceeded(instanceId);
                }
            });
        }
    }

    public void onInterstitialAdShowFailed(final String instanceId, final IronSourceError error) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onInterstitialAdShowFailed(" + instanceId + ", " + error + ")", 1);
        JSONObject data = IronSourceUtils.getMediationAdditionalData(true);
        try {
            if (error.getErrorCode() == IronSourceError.ERROR_REACHED_CAP_LIMIT_PER_PLACEMENT) {
                data.put("reason", 1);
            }
            data.put(IronSourceConstants.ERROR_CODE_KEY, error.getErrorCode());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        InterstitialEventsManager.getInstance().log(new EventData(29, data));
        if (canSendCallback(this.mISDemandOnlyInterstitialListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyInterstitialListener.onInterstitialAdShowFailed(instanceId, error);
                }
            });
        }
    }

    public void onInterstitialAdClicked(final String instanceId) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onInterstitialAdClicked(" + instanceId + ")", 1);
        if (canSendCallback(this.mISDemandOnlyInterstitialListener)) {
            sendCallback(new Runnable() {
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyInterstitialListener.onInterstitialAdClicked(instanceId);
                }
            });
        }
    }
}
