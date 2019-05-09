// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.reward;

import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.AdRequest;
import android.content.Context;

public interface RewardedVideoAd
{
    @Deprecated
    void destroy();
    
    void destroy(final Context p0);
    
    String getMediationAdapterClassName();
    
    RewardedVideoAdListener getRewardedVideoAdListener();
    
    String getUserId();
    
    boolean isLoaded();
    
    void loadAd(final String p0, final AdRequest p1);
    
    void loadAd(final String p0, final PublisherAdRequest p1);
    
    @Deprecated
    void pause();
    
    void pause(final Context p0);
    
    @Deprecated
    void resume();
    
    void resume(final Context p0);
    
    void setImmersiveMode(final boolean p0);
    
    void setRewardedVideoAdListener(final RewardedVideoAdListener p0);
    
    void setUserId(final String p0);
    
    void show();
}
