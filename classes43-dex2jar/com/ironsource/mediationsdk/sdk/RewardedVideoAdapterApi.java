// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import android.app.Activity;
import org.json.JSONObject;

public interface RewardedVideoAdapterApi
{
    void addRewardedVideoListener(final RewardedVideoSmashListener p0);
    
    void fetchRewardedVideo(final JSONObject p0);
    
    void initRewardedVideo(final Activity p0, final String p1, final String p2, final JSONObject p3, final RewardedVideoSmashListener p4);
    
    boolean isRewardedVideoAvailable(final JSONObject p0);
    
    void removeRewardedVideoListener(final RewardedVideoSmashListener p0);
    
    void showRewardedVideo(final JSONObject p0, final RewardedVideoSmashListener p1);
}
