// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import com.ironsource.mediationsdk.logger.IronSourceError;

public interface RewardedVideoSmashListener
{
    void onRewardedVideoAdClicked();
    
    void onRewardedVideoAdClosed();
    
    void onRewardedVideoAdEnded();
    
    void onRewardedVideoAdOpened();
    
    void onRewardedVideoAdRewarded();
    
    void onRewardedVideoAdShowFailed(final IronSourceError p0);
    
    void onRewardedVideoAdStarted();
    
    void onRewardedVideoAdVisible();
    
    void onRewardedVideoAvailabilityChanged(final boolean p0);
}
