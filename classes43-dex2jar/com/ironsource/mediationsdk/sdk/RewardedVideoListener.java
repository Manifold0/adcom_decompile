// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.model.Placement;

public interface RewardedVideoListener
{
    void onRewardedVideoAdClicked(final Placement p0);
    
    void onRewardedVideoAdClosed();
    
    void onRewardedVideoAdEnded();
    
    void onRewardedVideoAdOpened();
    
    void onRewardedVideoAdRewarded(final Placement p0);
    
    void onRewardedVideoAdShowFailed(final IronSourceError p0);
    
    void onRewardedVideoAdStarted();
    
    void onRewardedVideoAvailabilityChanged(final boolean p0);
}
