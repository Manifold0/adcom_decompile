// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.model.Placement;

public interface ISDemandOnlyRewardedVideoListener
{
    void onRewardedVideoAdClicked(final String p0, final Placement p1);
    
    void onRewardedVideoAdClosed(final String p0);
    
    void onRewardedVideoAdOpened(final String p0);
    
    void onRewardedVideoAdRewarded(final String p0, final Placement p1);
    
    void onRewardedVideoAdShowFailed(final String p0, final IronSourceError p1);
    
    void onRewardedVideoAvailabilityChanged(final String p0, final boolean p1);
}
