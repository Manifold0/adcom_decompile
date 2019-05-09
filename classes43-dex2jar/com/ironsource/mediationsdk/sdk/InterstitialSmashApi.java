// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import android.app.Activity;

public interface InterstitialSmashApi
{
    void initInterstitial(final Activity p0, final String p1, final String p2);
    
    boolean isInterstitialReady();
    
    void loadInterstitial();
    
    void setInterstitialManagerListener(final InterstitialManagerListener p0);
    
    void showInterstitial();
}
