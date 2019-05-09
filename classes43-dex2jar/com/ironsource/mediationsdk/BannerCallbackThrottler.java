// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk;

import android.os.Handler;
import android.os.Looper;
import com.ironsource.mediationsdk.logger.IronSourceError;

public class BannerCallbackThrottler
{
    private static final long MAX_CALL_RATE_SEC = 15L;
    private static BannerCallbackThrottler sInstance;
    private boolean mIsWaitingForInvocation;
    private long mLastInvoked;
    
    private BannerCallbackThrottler() {
        this.mLastInvoked = 0L;
        this.mIsWaitingForInvocation = false;
    }
    
    public static BannerCallbackThrottler getInstance() {
        synchronized (BannerCallbackThrottler.class) {
            if (BannerCallbackThrottler.sInstance == null) {
                BannerCallbackThrottler.sInstance = new BannerCallbackThrottler();
            }
            return BannerCallbackThrottler.sInstance;
        }
    }
    
    private void invokeCallback(final IronSourceBannerLayout ironSourceBannerLayout, final IronSourceError ironSourceError) {
        this.mLastInvoked = System.currentTimeMillis();
        this.mIsWaitingForInvocation = false;
        ironSourceBannerLayout.sendBannerAdLoadFailed(ironSourceError);
    }
    
    public boolean hasPendingInvocation() {
        synchronized (this) {
            return this.mIsWaitingForInvocation;
        }
    }
    
    public void sendBannerAdLoadFailed(final IronSourceBannerLayout ironSourceBannerLayout, final IronSourceError ironSourceError) {
        final long n;
        synchronized (this) {
            if (this.mIsWaitingForInvocation) {
                return;
            }
            n = System.currentTimeMillis() - this.mLastInvoked;
            if (n > 15000L) {
                this.invokeCallback(ironSourceBannerLayout, ironSourceError);
                return;
            }
        }
        this.mIsWaitingForInvocation = true;
        final IronSourceBannerLayout ironSourceBannerLayout2;
        new Handler(Looper.getMainLooper()).postDelayed((Runnable)new Runnable() {
            @Override
            public void run() {
                BannerCallbackThrottler.this.invokeCallback(ironSourceBannerLayout2, ironSourceError);
            }
        }, 15000L - n);
    }
    // monitorexit(this)
}
