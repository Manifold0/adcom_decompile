// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import android.os.Looper;
import android.text.TextUtils;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.events.RewardedVideoEventsManager;
import org.json.JSONException;
import com.ironsource.mediationsdk.events.InterstitialEventsManager;
import org.json.JSONObject;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.logger.IronSourceError;
import android.os.Handler;

public class ListenersWrapper implements RewardedVideoListener, InterstitialListener, InternalOfferwallListener, RewardedInterstitialListener, SegmentListener, ISDemandOnlyRewardedVideoListener, ISDemandOnlyInterstitialListener
{
    private CallbackHandlerThread mCallbackHandlerThread;
    private ISDemandOnlyInterstitialListener mISDemandOnlyInterstitialListener;
    private ISDemandOnlyRewardedVideoListener mISDemandOnlyRewardedVideoListener;
    private InterstitialListener mInterstitialListener;
    private OfferwallListener mOfferwallListener;
    private RewardedInterstitialListener mRewardedInterstitialListener;
    private RewardedVideoListener mRewardedVideoListener;
    private SegmentListener mSegementListener;
    
    public ListenersWrapper() {
        (this.mCallbackHandlerThread = new CallbackHandlerThread()).start();
    }
    
    private boolean canSendCallback(final Object o) {
        return o != null && this.mCallbackHandlerThread != null;
    }
    
    private void sendCallback(final Runnable runnable) {
        if (this.mCallbackHandlerThread != null) {
            final Handler callbackHandler = this.mCallbackHandlerThread.getCallbackHandler();
            if (callbackHandler != null) {
                callbackHandler.post(runnable);
            }
        }
    }
    
    @Override
    public void onGetOfferwallCreditsFailed(final IronSourceError ironSourceError) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onGetOfferwallCreditsFailed(" + ironSourceError + ")", 1);
        if (this.canSendCallback(this.mOfferwallListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mOfferwallListener.onGetOfferwallCreditsFailed(ironSourceError);
                }
            });
        }
    }
    
    @Override
    public void onInterstitialAdClicked() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdClicked()", 1);
        if (this.canSendCallback(this.mInterstitialListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mInterstitialListener.onInterstitialAdClicked();
                }
            });
        }
    }
    
    @Override
    public void onInterstitialAdClicked(final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdClicked(" + s + ")", 1);
        if (this.canSendCallback(this.mISDemandOnlyInterstitialListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyInterstitialListener.onInterstitialAdClicked(s);
                }
            });
        }
    }
    
    @Override
    public void onInterstitialAdClosed() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdClosed()", 1);
        if (this.canSendCallback(this.mInterstitialListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mInterstitialListener.onInterstitialAdClosed();
                }
            });
        }
    }
    
    @Override
    public void onInterstitialAdClosed(final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdClosed(" + s + ")", 1);
        if (this.canSendCallback(this.mISDemandOnlyInterstitialListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyInterstitialListener.onInterstitialAdClosed(s);
                }
            });
        }
    }
    
    @Override
    public void onInterstitialAdLoadFailed(final IronSourceError ironSourceError) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdLoadFailed(" + ironSourceError + ")", 1);
        if (this.canSendCallback(this.mInterstitialListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mInterstitialListener.onInterstitialAdLoadFailed(ironSourceError);
                }
            });
        }
    }
    
    @Override
    public void onInterstitialAdLoadFailed(final String s, final IronSourceError ironSourceError) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdLoadFailed(" + s + ", " + ironSourceError + ")", 1);
        if (this.canSendCallback(this.mISDemandOnlyInterstitialListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyInterstitialListener.onInterstitialAdLoadFailed(s, ironSourceError);
                }
            });
        }
    }
    
    @Override
    public void onInterstitialAdOpened() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdOpened()", 1);
        if (this.canSendCallback(this.mInterstitialListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mInterstitialListener.onInterstitialAdOpened();
                }
            });
        }
    }
    
    @Override
    public void onInterstitialAdOpened(final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdOpened(" + s + ")", 1);
        if (this.canSendCallback(this.mISDemandOnlyInterstitialListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyInterstitialListener.onInterstitialAdOpened(s);
                }
            });
        }
    }
    
    @Override
    public void onInterstitialAdReady() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdReady()", 1);
        if (this.canSendCallback(this.mInterstitialListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mInterstitialListener.onInterstitialAdReady();
                }
            });
        }
    }
    
    @Override
    public void onInterstitialAdReady(final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdReady(" + s + ")", 1);
        if (this.canSendCallback(this.mISDemandOnlyInterstitialListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyInterstitialListener.onInterstitialAdReady(s);
                }
            });
        }
    }
    
    @Override
    public void onInterstitialAdRewarded() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdRewarded()", 1);
        if (this.canSendCallback(this.mRewardedInterstitialListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mRewardedInterstitialListener.onInterstitialAdRewarded();
                }
            });
        }
    }
    
    @Override
    public void onInterstitialAdShowFailed(final IronSourceError ironSourceError) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdShowFailed(" + ironSourceError + ")", 1);
        Object mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
        while (true) {
            try {
                if (ironSourceError.getErrorCode() == 524) {
                    ((JSONObject)mediationAdditionalData).put("reason", 1);
                }
                ((JSONObject)mediationAdditionalData).put("errorCode", ironSourceError.getErrorCode());
                mediationAdditionalData = new EventData(29, (JSONObject)mediationAdditionalData);
                InterstitialEventsManager.getInstance().log((EventData)mediationAdditionalData);
                if (this.canSendCallback(this.mInterstitialListener)) {
                    this.sendCallback(new Runnable() {
                        @Override
                        public void run() {
                            ListenersWrapper.this.mInterstitialListener.onInterstitialAdShowFailed(ironSourceError);
                        }
                    });
                }
            }
            catch (JSONException ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    @Override
    public void onInterstitialAdShowFailed(final String s, final IronSourceError ironSourceError) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdShowFailed(" + s + ", " + ironSourceError + ")", 1);
        Object mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(true);
        while (true) {
            try {
                if (ironSourceError.getErrorCode() == 524) {
                    ((JSONObject)mediationAdditionalData).put("reason", 1);
                }
                ((JSONObject)mediationAdditionalData).put("errorCode", ironSourceError.getErrorCode());
                mediationAdditionalData = new EventData(29, (JSONObject)mediationAdditionalData);
                InterstitialEventsManager.getInstance().log((EventData)mediationAdditionalData);
                if (this.canSendCallback(this.mISDemandOnlyInterstitialListener)) {
                    this.sendCallback(new Runnable() {
                        @Override
                        public void run() {
                            ListenersWrapper.this.mISDemandOnlyInterstitialListener.onInterstitialAdShowFailed(s, ironSourceError);
                        }
                    });
                }
            }
            catch (JSONException ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    @Override
    public void onInterstitialAdShowSucceeded() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdShowSucceeded()", 1);
        if (this.canSendCallback(this.mInterstitialListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mInterstitialListener.onInterstitialAdShowSucceeded();
                }
            });
        }
    }
    
    @Override
    public void onInterstitialAdShowSucceeded(final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdShowSucceeded(" + s + ")", 1);
        if (this.canSendCallback(this.mISDemandOnlyInterstitialListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyInterstitialListener.onInterstitialAdShowSucceeded(s);
                }
            });
        }
    }
    
    @Override
    public boolean onOfferwallAdCredited(final int n, final int n2, final boolean b) {
        boolean onOfferwallAdCredited = false;
        if (this.mOfferwallListener != null) {
            onOfferwallAdCredited = this.mOfferwallListener.onOfferwallAdCredited(n, n2, b);
        }
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onOfferwallAdCredited(credits:" + n + ", " + "totalCredits:" + n2 + ", " + "totalCreditsFlag:" + b + "):" + onOfferwallAdCredited, 1);
        return onOfferwallAdCredited;
    }
    
    @Override
    public void onOfferwallAvailable(final boolean b) {
        this.onOfferwallAvailable(b, null);
    }
    
    @Override
    public void onOfferwallAvailable(final boolean b, final IronSourceError ironSourceError) {
        String s = "onOfferwallAvailable(isAvailable: " + b + ")";
        if (ironSourceError != null) {
            s = s + ", error: " + ironSourceError.getErrorMessage();
        }
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, s, 1);
        final JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
        while (true) {
            try {
                mediationAdditionalData.put("status", (Object)String.valueOf(b));
                if (ironSourceError != null) {
                    mediationAdditionalData.put("errorCode", ironSourceError.getErrorCode());
                }
                RewardedVideoEventsManager.getInstance().log(new EventData(302, mediationAdditionalData));
                if (this.canSendCallback(this.mOfferwallListener)) {
                    this.sendCallback(new Runnable() {
                        @Override
                        public void run() {
                            ListenersWrapper.this.mOfferwallListener.onOfferwallAvailable(b);
                        }
                    });
                }
            }
            catch (JSONException ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    @Override
    public void onOfferwallClosed() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onOfferwallClosed()", 1);
        if (this.canSendCallback(this.mOfferwallListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mOfferwallListener.onOfferwallClosed();
                }
            });
        }
    }
    
    @Override
    public void onOfferwallOpened() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onOfferwallOpened()", 1);
        if (this.canSendCallback(this.mOfferwallListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mOfferwallListener.onOfferwallOpened();
                }
            });
        }
    }
    
    @Override
    public void onOfferwallShowFailed(final IronSourceError ironSourceError) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onOfferwallShowFailed(" + ironSourceError + ")", 1);
        if (this.canSendCallback(this.mOfferwallListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mOfferwallListener.onOfferwallShowFailed(ironSourceError);
                }
            });
        }
    }
    
    @Override
    public void onRewardedVideoAdClicked(final Placement placement) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAdClicked(" + placement.getPlacementName() + ")", 1);
        if (this.canSendCallback(this.mRewardedVideoListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdClicked(placement);
                }
            });
        }
    }
    
    @Override
    public void onRewardedVideoAdClicked(final String s, final Placement placement) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAdClicked(" + s + ", " + placement.getPlacementName() + ")", 1);
        if (this.canSendCallback(this.mISDemandOnlyRewardedVideoListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyRewardedVideoListener.onRewardedVideoAdClicked(s, placement);
                }
            });
        }
    }
    
    @Override
    public void onRewardedVideoAdClosed() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAdClosed()", 1);
        if (this.canSendCallback(this.mRewardedVideoListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdClosed();
                }
            });
        }
    }
    
    @Override
    public void onRewardedVideoAdClosed(final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAdClosed(" + s + ")", 1);
        if (this.canSendCallback(this.mISDemandOnlyRewardedVideoListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyRewardedVideoListener.onRewardedVideoAdClosed(s);
                }
            });
        }
    }
    
    @Override
    public void onRewardedVideoAdEnded() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAdEnded()", 1);
        if (this.canSendCallback(this.mRewardedVideoListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdEnded();
                }
            });
        }
    }
    
    @Override
    public void onRewardedVideoAdOpened() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAdOpened()", 1);
        if (this.canSendCallback(this.mRewardedVideoListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdOpened();
                }
            });
        }
    }
    
    @Override
    public void onRewardedVideoAdOpened(final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAdOpened(" + s + ")", 1);
        if (this.canSendCallback(this.mISDemandOnlyRewardedVideoListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyRewardedVideoListener.onRewardedVideoAdOpened(s);
                }
            });
        }
    }
    
    @Override
    public void onRewardedVideoAdRewarded(final Placement placement) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAdRewarded(" + placement.toString() + ")", 1);
        if (this.canSendCallback(this.mRewardedVideoListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdRewarded(placement);
                }
            });
        }
    }
    
    @Override
    public void onRewardedVideoAdRewarded(final String s, final Placement placement) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAdRewarded(" + s + ", " + placement.toString() + ")", 1);
        if (this.canSendCallback(this.mISDemandOnlyRewardedVideoListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyRewardedVideoListener.onRewardedVideoAdRewarded(s, placement);
                }
            });
        }
    }
    
    @Override
    public void onRewardedVideoAdShowFailed(final IronSourceError ironSourceError) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAdShowFailed(" + ironSourceError.toString() + ")", 1);
        Object mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
        while (true) {
            try {
                ((JSONObject)mediationAdditionalData).put("status", (Object)"false");
                if (ironSourceError.getErrorCode() == 524) {
                    ((JSONObject)mediationAdditionalData).put("reason", 1);
                }
                ((JSONObject)mediationAdditionalData).put("errorCode", ironSourceError.getErrorCode());
                mediationAdditionalData = new EventData(17, (JSONObject)mediationAdditionalData);
                RewardedVideoEventsManager.getInstance().log((EventData)mediationAdditionalData);
                if (this.canSendCallback(this.mRewardedVideoListener)) {
                    this.sendCallback(new Runnable() {
                        @Override
                        public void run() {
                            ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdShowFailed(ironSourceError);
                        }
                    });
                }
            }
            catch (JSONException ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    @Override
    public void onRewardedVideoAdShowFailed(final String s, final IronSourceError ironSourceError) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAdShowFailed(" + s + ", " + ironSourceError.toString() + ")", 1);
        Object mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(true);
        while (true) {
            try {
                ((JSONObject)mediationAdditionalData).put("status", (Object)"false");
                if (ironSourceError.getErrorCode() == 524) {
                    ((JSONObject)mediationAdditionalData).put("reason", 1);
                }
                ((JSONObject)mediationAdditionalData).put("errorCode", ironSourceError.getErrorCode());
                mediationAdditionalData = new EventData(17, (JSONObject)mediationAdditionalData);
                RewardedVideoEventsManager.getInstance().log((EventData)mediationAdditionalData);
                if (this.canSendCallback(this.mISDemandOnlyRewardedVideoListener)) {
                    this.sendCallback(new Runnable() {
                        @Override
                        public void run() {
                            ListenersWrapper.this.mISDemandOnlyRewardedVideoListener.onRewardedVideoAdShowFailed(s, ironSourceError);
                        }
                    });
                }
            }
            catch (JSONException ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    @Override
    public void onRewardedVideoAdStarted() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAdStarted()", 1);
        if (this.canSendCallback(this.mRewardedVideoListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdStarted();
                }
            });
        }
    }
    
    @Override
    public void onRewardedVideoAvailabilityChanged(final String s, final boolean b) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAvailabilityChanged(" + s + ", " + b + ")", 1);
        if (this.canSendCallback(this.mISDemandOnlyRewardedVideoListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    ListenersWrapper.this.mISDemandOnlyRewardedVideoListener.onRewardedVideoAvailabilityChanged(s, b);
                }
            });
        }
    }
    
    @Override
    public void onRewardedVideoAvailabilityChanged(final boolean b) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAvailabilityChanged(available:" + b + ")", 1);
        Object mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
        while (true) {
            try {
                ((JSONObject)mediationAdditionalData).put("status", (Object)String.valueOf(b));
                mediationAdditionalData = new EventData(7, (JSONObject)mediationAdditionalData);
                RewardedVideoEventsManager.getInstance().log((EventData)mediationAdditionalData);
                if (this.canSendCallback(this.mRewardedVideoListener)) {
                    this.sendCallback(new Runnable() {
                        @Override
                        public void run() {
                            ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAvailabilityChanged(b);
                        }
                    });
                }
            }
            catch (JSONException ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    @Override
    public void onSegmentReceived(final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onSegmentReceived(" + s + ")", 1);
        if (this.canSendCallback(this.mSegementListener)) {
            this.sendCallback(new Runnable() {
                @Override
                public void run() {
                    if (!TextUtils.isEmpty((CharSequence)s)) {
                        ListenersWrapper.this.mSegementListener.onSegmentReceived(s);
                    }
                }
            });
        }
    }
    
    public void setISDemandOnlyInterstitialListener(final ISDemandOnlyInterstitialListener misDemandOnlyInterstitialListener) {
        this.mISDemandOnlyInterstitialListener = misDemandOnlyInterstitialListener;
    }
    
    public void setISDemandOnlyRewardedVideoListener(final ISDemandOnlyRewardedVideoListener misDemandOnlyRewardedVideoListener) {
        this.mISDemandOnlyRewardedVideoListener = misDemandOnlyRewardedVideoListener;
    }
    
    public void setInterstitialListener(final InterstitialListener mInterstitialListener) {
        this.mInterstitialListener = mInterstitialListener;
    }
    
    public void setOfferwallListener(final OfferwallListener mOfferwallListener) {
        this.mOfferwallListener = mOfferwallListener;
    }
    
    public void setRewardedInterstitialListener(final RewardedInterstitialListener mRewardedInterstitialListener) {
        this.mRewardedInterstitialListener = mRewardedInterstitialListener;
    }
    
    public void setRewardedVideoListener(final RewardedVideoListener mRewardedVideoListener) {
        this.mRewardedVideoListener = mRewardedVideoListener;
    }
    
    public void setSegmentListener(final SegmentListener mSegementListener) {
        this.mSegementListener = mSegementListener;
    }
    
    private class CallbackHandlerThread extends Thread
    {
        private Handler mCallbackHandler;
        
        public Handler getCallbackHandler() {
            return this.mCallbackHandler;
        }
        
        @Override
        public void run() {
            Looper.prepare();
            this.mCallbackHandler = new Handler();
            Looper.loop();
        }
    }
}
