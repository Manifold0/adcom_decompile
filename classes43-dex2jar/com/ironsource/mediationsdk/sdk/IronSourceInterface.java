// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import java.util.Map;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.model.InterstitialPlacement;
import android.content.Context;
import com.ironsource.mediationsdk.logger.LoggingApi;

public interface IronSourceInterface extends BaseApi, RewardedVideoApi, InterstitialApi, OfferwallApi, LoggingApi, RewardedInterstitialApi
{
    void clearRewardedVideoServerParameters();
    
    String getAdvertiserId(final Context p0);
    
    InterstitialPlacement getInterstitialPlacementInfo(final String p0);
    
    Placement getRewardedVideoPlacementInfo(final String p0);
    
    void removeInterstitialListener();
    
    void removeOfferwallListener();
    
    void removeRewardedVideoListener();
    
    void setAdaptersDebug(final boolean p0);
    
    boolean setDynamicUserId(final String p0);
    
    void setMediationType(final String p0);
    
    void setRewardedVideoServerParameters(final Map<String, String> p0);
    
    void shouldTrackNetworkState(final Context p0, final boolean p1);
}
