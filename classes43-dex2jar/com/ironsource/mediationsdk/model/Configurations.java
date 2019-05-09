// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.model;

public class Configurations
{
    private ApplicationConfigurations mApplicationConfig;
    private BannerConfigurations mBannerConfig;
    private InterstitialConfigurations mInterstitialConfig;
    private OfferwallConfigurations mOfferwallConfig;
    private RewardedVideoConfigurations mRewardedVideoConfig;
    
    public Configurations() {
    }
    
    public Configurations(final RewardedVideoConfigurations mRewardedVideoConfig, final InterstitialConfigurations mInterstitialConfig, final OfferwallConfigurations mOfferwallConfig, final BannerConfigurations mBannerConfig, final ApplicationConfigurations mApplicationConfig) {
        if (mRewardedVideoConfig != null) {
            this.mRewardedVideoConfig = mRewardedVideoConfig;
        }
        if (mInterstitialConfig != null) {
            this.mInterstitialConfig = mInterstitialConfig;
        }
        if (mOfferwallConfig != null) {
            this.mOfferwallConfig = mOfferwallConfig;
        }
        if (mBannerConfig != null) {
            this.mBannerConfig = mBannerConfig;
        }
        this.mApplicationConfig = mApplicationConfig;
    }
    
    public ApplicationConfigurations getApplicationConfigurations() {
        return this.mApplicationConfig;
    }
    
    public BannerConfigurations getBannerConfigurations() {
        return this.mBannerConfig;
    }
    
    public InterstitialConfigurations getInterstitialConfigurations() {
        return this.mInterstitialConfig;
    }
    
    public OfferwallConfigurations getOfferwallConfigurations() {
        return this.mOfferwallConfig;
    }
    
    public RewardedVideoConfigurations getRewardedVideoConfigurations() {
        return this.mRewardedVideoConfig;
    }
}
