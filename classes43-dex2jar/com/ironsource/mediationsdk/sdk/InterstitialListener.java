// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import com.ironsource.mediationsdk.logger.IronSourceError;

public interface InterstitialListener
{
    void onInterstitialAdClicked();
    
    void onInterstitialAdClosed();
    
    void onInterstitialAdLoadFailed(final IronSourceError p0);
    
    void onInterstitialAdOpened();
    
    void onInterstitialAdReady();
    
    void onInterstitialAdShowFailed(final IronSourceError p0);
    
    void onInterstitialAdShowSucceeded();
}
