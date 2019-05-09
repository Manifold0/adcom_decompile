package com.ironsource.mediationsdk;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyInterstitialListener;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import java.util.HashMap;
import java.util.Map;

public class CallbackThrotteler {
    private static final long MAX_CALL_RATE_SEC = 15;
    private static final String MEDIATION = "mediation";
    private ISDemandOnlyInterstitialListener mDemandOnlyListener = null;
    private Map<String, Boolean> mIsWaitingForInvocation = new HashMap();
    private Map<String, Long> mLastInvoked = new HashMap();
    private InterstitialListener mListener = null;

    public void setInterstitialListener(InterstitialListener listener) {
        this.mListener = listener;
    }

    public void setISDemandOnlyInterstitialListener(ISDemandOnlyInterstitialListener listener) {
        this.mDemandOnlyListener = listener;
    }

    public void onInterstitialAdLoadFailed(IronSourceError error) {
        synchronized (this) {
            onInterstitialAdLoadFailedInternal(MEDIATION, error);
        }
    }

    public void onInterstitialAdLoadFailed(String instanceId, IronSourceError error) {
        synchronized (this) {
            onInterstitialAdLoadFailedInternal(instanceId, error);
        }
    }

    public boolean hasPendingInvocation(String instanceId) {
        boolean hasPendingInvocationInternal;
        synchronized (this) {
            hasPendingInvocationInternal = hasPendingInvocationInternal(instanceId);
        }
        return hasPendingInvocationInternal;
    }

    public boolean hasPendingInvocation() {
        boolean hasPendingInvocationInternal;
        synchronized (this) {
            hasPendingInvocationInternal = hasPendingInvocationInternal(MEDIATION);
        }
        return hasPendingInvocationInternal;
    }

    private boolean hasPendingInvocationInternal(String instanceId) {
        if (!TextUtils.isEmpty(instanceId) && this.mIsWaitingForInvocation.containsKey(instanceId)) {
            return ((Boolean) this.mIsWaitingForInvocation.get(instanceId)).booleanValue();
        }
        return false;
    }

    private void invokeCallback(String instanceId, IronSourceError error) {
        this.mLastInvoked.put(instanceId, Long.valueOf(System.currentTimeMillis()));
        if (instanceId.equalsIgnoreCase(MEDIATION)) {
            if (this.mListener != null) {
                this.mListener.onInterstitialAdLoadFailed(error);
            }
        } else if (this.mDemandOnlyListener != null) {
            this.mDemandOnlyListener.onInterstitialAdLoadFailed(instanceId, error);
        }
    }

    private void onInterstitialAdLoadFailedInternal(final String instanceId, final IronSourceError error) {
        if (!hasPendingInvocationInternal(instanceId)) {
            if (this.mLastInvoked.containsKey(instanceId)) {
                long timeSinceLastCallMs = System.currentTimeMillis() - ((Long) this.mLastInvoked.get(instanceId)).longValue();
                if (timeSinceLastCallMs > 15000) {
                    invokeCallback(instanceId, error);
                    return;
                }
                this.mIsWaitingForInvocation.put(instanceId, Boolean.valueOf(true));
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        CallbackThrotteler.this.invokeCallback(instanceId, error);
                        CallbackThrotteler.this.mIsWaitingForInvocation.put(instanceId, Boolean.valueOf(false));
                    }
                }, 15000 - timeSinceLastCallMs);
                return;
            }
            invokeCallback(instanceId, error);
        }
    }
}
