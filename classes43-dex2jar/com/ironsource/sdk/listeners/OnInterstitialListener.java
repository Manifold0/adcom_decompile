// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.listeners;

import org.json.JSONObject;

public interface OnInterstitialListener extends OnAdProductListener
{
    void onInterstitialClick();
    
    void onInterstitialClose();
    
    void onInterstitialEventNotificationReceived(final String p0, final JSONObject p1);
    
    void onInterstitialInitFailed(final String p0);
    
    void onInterstitialInitSuccess();
    
    void onInterstitialLoadFailed(final String p0);
    
    void onInterstitialLoadSuccess();
    
    void onInterstitialOpen();
    
    void onInterstitialShowFailed(final String p0);
    
    void onInterstitialShowSuccess();
}
