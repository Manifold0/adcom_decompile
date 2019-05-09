// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import android.app.Activity;

public interface InterstitialApi
{
    void initInterstitial(final Activity p0, final String p1, final String p2);
    
    boolean isInterstitialReady();
    
    void loadInterstitial();
    
    void setInterstitialListener(final InterstitialListener p0);
    
    void showInterstitial(final String p0);
}
