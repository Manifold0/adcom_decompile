// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import android.app.Activity;

public interface RewardedVideoSmashApi
{
    void fetchRewardedVideo();
    
    void initRewardedVideo(final Activity p0, final String p1, final String p2);
    
    boolean isRewardedVideoAvailable();
    
    void setRewardedVideoManagerListener(final RewardedVideoManagerListener p0);
    
    void showRewardedVideo();
}
