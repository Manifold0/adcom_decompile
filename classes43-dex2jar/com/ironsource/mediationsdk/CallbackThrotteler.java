// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ironsource.mediationsdk.logger.IronSourceError;
import java.util.HashMap;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import java.util.Map;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyInterstitialListener;

public class CallbackThrotteler
{
    private static final long MAX_CALL_RATE_SEC = 15L;
    private static final String MEDIATION = "mediation";
    private ISDemandOnlyInterstitialListener mDemandOnlyListener;
    private Map<String, Boolean> mIsWaitingForInvocation;
    private Map<String, Long> mLastInvoked;
    private InterstitialListener mListener;
    
    public CallbackThrotteler() {
        this.mListener = null;
        this.mDemandOnlyListener = null;
        this.mLastInvoked = new HashMap<String, Long>();
        this.mIsWaitingForInvocation = new HashMap<String, Boolean>();
    }
    
    private boolean hasPendingInvocationInternal(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && this.mIsWaitingForInvocation.containsKey(s) && this.mIsWaitingForInvocation.get(s);
    }
    
    private void invokeCallback(final String s, final IronSourceError ironSourceError) {
        this.mLastInvoked.put(s, System.currentTimeMillis());
        if (s.equalsIgnoreCase("mediation")) {
            if (this.mListener != null) {
                this.mListener.onInterstitialAdLoadFailed(ironSourceError);
            }
        }
        else if (this.mDemandOnlyListener != null) {
            this.mDemandOnlyListener.onInterstitialAdLoadFailed(s, ironSourceError);
        }
    }
    
    private void onInterstitialAdLoadFailedInternal(final String s, final IronSourceError ironSourceError) {
        if (this.hasPendingInvocationInternal(s)) {
            return;
        }
        if (!this.mLastInvoked.containsKey(s)) {
            this.invokeCallback(s, ironSourceError);
            return;
        }
        final long n = System.currentTimeMillis() - this.mLastInvoked.get(s);
        if (n > 15000L) {
            this.invokeCallback(s, ironSourceError);
            return;
        }
        this.mIsWaitingForInvocation.put(s, true);
        new Handler(Looper.getMainLooper()).postDelayed((Runnable)new Runnable() {
            @Override
            public void run() {
                CallbackThrotteler.this.invokeCallback(s, ironSourceError);
                CallbackThrotteler.this.mIsWaitingForInvocation.put(s, false);
            }
        }, 15000L - n);
    }
    
    public boolean hasPendingInvocation() {
        synchronized (this) {
            return this.hasPendingInvocationInternal("mediation");
        }
    }
    
    public boolean hasPendingInvocation(final String s) {
        synchronized (this) {
            return this.hasPendingInvocationInternal(s);
        }
    }
    
    public void onInterstitialAdLoadFailed(final IronSourceError ironSourceError) {
        synchronized (this) {
            this.onInterstitialAdLoadFailedInternal("mediation", ironSourceError);
        }
    }
    
    public void onInterstitialAdLoadFailed(final String s, final IronSourceError ironSourceError) {
        synchronized (this) {
            this.onInterstitialAdLoadFailedInternal(s, ironSourceError);
        }
    }
    
    public void setISDemandOnlyInterstitialListener(final ISDemandOnlyInterstitialListener mDemandOnlyListener) {
        this.mDemandOnlyListener = mDemandOnlyListener;
    }
    
    public void setInterstitialListener(final InterstitialListener mListener) {
        this.mListener = mListener;
    }
}
