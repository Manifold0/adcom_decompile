// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.RewardedVideoSmash;

public interface RewardedVideoManagerListener
{
    void onRewardedVideoAdClicked(final RewardedVideoSmash p0);
    
    void onRewardedVideoAdClosed(final RewardedVideoSmash p0);
    
    void onRewardedVideoAdEnded(final RewardedVideoSmash p0);
    
    void onRewardedVideoAdOpened(final RewardedVideoSmash p0);
    
    void onRewardedVideoAdRewarded(final RewardedVideoSmash p0);
    
    void onRewardedVideoAdShowFailed(final IronSourceError p0, final RewardedVideoSmash p1);
    
    void onRewardedVideoAdStarted(final RewardedVideoSmash p0);
    
    void onRewardedVideoAdVisible(final RewardedVideoSmash p0);
    
    void onRewardedVideoAvailabilityChanged(final boolean p0, final RewardedVideoSmash p1);
}
