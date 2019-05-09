// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.utils;

public class SessionDepthManager
{
    public static final int BANNER = 3;
    public static final int INTERSTITIAL = 2;
    public static final int NONE = -1;
    public static final int OFFERWALL = 0;
    public static final int REWARDEDVIDEO = 1;
    private static SessionDepthManager mInstance;
    private int mBannerDepth;
    private int mInterstitialDepth;
    private int mOfferwallDepth;
    private int mRewardedVideoDepth;
    
    public SessionDepthManager() {
        this.mRewardedVideoDepth = 1;
        this.mInterstitialDepth = 1;
        this.mOfferwallDepth = 1;
        this.mBannerDepth = 1;
    }
    
    public static SessionDepthManager getInstance() {
        synchronized (SessionDepthManager.class) {
            if (SessionDepthManager.mInstance == null) {
                SessionDepthManager.mInstance = new SessionDepthManager();
            }
            return SessionDepthManager.mInstance;
        }
    }
    
    public int getSessionDepth(int n) {
        // monitorenter(this)
    Label_0046_Outer:
        while (true) {
        Label_0062_Outer:
            while (true) {
                while (true) {
                    switch (n) {
                        default: {
                            n = -1;
                            break;
                        }
                        case 0: {
                            try {
                                n = this.mOfferwallDepth;
                                break;
                                n = this.mInterstitialDepth;
                                break;
                                n = this.mRewardedVideoDepth;
                                break;
                                n = this.mBannerDepth;
                                break;
                            }
                            finally {
                            }
                            // monitorexit(this)
                            break;
                        }
                        case 1: {
                            continue Label_0062_Outer;
                        }
                        case 2: {
                            continue Label_0046_Outer;
                        }
                        case 3: {
                            continue;
                        }
                    }
                    break;
                }
                break;
            }
            break;
        }
        // monitorexit(this)
        return n;
    }
    
    public void increaseSessionDepth(final int n) {
        // monitorenter(this)
        switch (n) {
            case 0: {
                try {
                    ++this.mOfferwallDepth;
                    break;
                }
                finally {
                }
                // monitorexit(this)
            }
            case 1: {
                ++this.mRewardedVideoDepth;
                break;
            }
            case 2: {
                ++this.mInterstitialDepth;
                break;
            }
            case 3: {
                ++this.mBannerDepth;
                break;
            }
        }
    }
    // monitorexit(this)
}
