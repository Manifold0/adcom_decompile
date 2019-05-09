package com.ironsource.mediationsdk.utils;

public class SessionDepthManager {
    public static final int BANNER = 3;
    public static final int INTERSTITIAL = 2;
    public static final int NONE = -1;
    public static final int OFFERWALL = 0;
    public static final int REWARDEDVIDEO = 1;
    private static SessionDepthManager mInstance;
    private int mBannerDepth = 1;
    private int mInterstitialDepth = 1;
    private int mOfferwallDepth = 1;
    private int mRewardedVideoDepth = 1;

    public static synchronized SessionDepthManager getInstance() {
        SessionDepthManager sessionDepthManager;
        synchronized (SessionDepthManager.class) {
            if (mInstance == null) {
                mInstance = new SessionDepthManager();
            }
            sessionDepthManager = mInstance;
        }
        return sessionDepthManager;
    }

    public synchronized void increaseSessionDepth(int adUnit) {
        switch (adUnit) {
            case 0:
                this.mOfferwallDepth++;
                break;
            case 1:
                this.mRewardedVideoDepth++;
                break;
            case 2:
                this.mInterstitialDepth++;
                break;
            case 3:
                this.mBannerDepth++;
                break;
        }
    }

    public synchronized int getSessionDepth(int adUnit) {
        int i;
        switch (adUnit) {
            case 0:
                i = this.mOfferwallDepth;
                break;
            case 1:
                i = this.mRewardedVideoDepth;
                break;
            case 2:
                i = this.mInterstitialDepth;
                break;
            case 3:
                i = this.mBannerDepth;
                break;
            default:
                i = -1;
                break;
        }
        return i;
    }
}
