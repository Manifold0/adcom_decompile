// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.listeners;

import com.ironsource.sdk.data.AdUnitsReady;
import org.json.JSONObject;

public interface OnRewardedVideoListener extends OnAdProductListener
{
    void onRVAdClicked();
    
    void onRVAdClosed();
    
    void onRVAdCredited(final int p0);
    
    void onRVAdOpened();
    
    void onRVEventNotificationReceived(final String p0, final JSONObject p1);
    
    void onRVInitFail(final String p0);
    
    void onRVInitSuccess(final AdUnitsReady p0);
    
    void onRVNoMoreOffers();
    
    void onRVShowFail(final String p0);
}
