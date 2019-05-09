package com.ironsource.mediationsdk;

import android.os.Handler;
import android.os.Looper;
import com.ironsource.mediationsdk.logger.IronSourceError;

public class BannerCallbackThrottler {
    private static final long MAX_CALL_RATE_SEC = 15;
    private static BannerCallbackThrottler sInstance;
    private boolean mIsWaitingForInvocation = false;
    private long mLastInvoked = 0;

    public static synchronized BannerCallbackThrottler getInstance() {
        BannerCallbackThrottler bannerCallbackThrottler;
        synchronized (BannerCallbackThrottler.class) {
            if (sInstance == null) {
                sInstance = new BannerCallbackThrottler();
            }
            bannerCallbackThrottler = sInstance;
        }
        return bannerCallbackThrottler;
    }

    private BannerCallbackThrottler() {
    }

    public void sendBannerAdLoadFailed(final IronSourceBannerLayout banner, final IronSourceError error) {
        synchronized (this) {
            if (this.mIsWaitingForInvocation) {
                return;
            }
            long timeSinceLastCallMs = System.currentTimeMillis() - this.mLastInvoked;
            if (timeSinceLastCallMs > 15000) {
                invokeCallback(banner, error);
                return;
            }
            this.mIsWaitingForInvocation = true;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    BannerCallbackThrottler.this.invokeCallback(banner, error);
                }
            }, 15000 - timeSinceLastCallMs);
        }
    }

    public boolean hasPendingInvocation() {
        boolean z;
        synchronized (this) {
            z = this.mIsWaitingForInvocation;
        }
        return z;
    }

    private void invokeCallback(IronSourceBannerLayout banner, IronSourceError error) {
        this.mLastInvoked = System.currentTimeMillis();
        this.mIsWaitingForInvocation = false;
        banner.sendBannerAdLoadFailed(error);
    }
}
