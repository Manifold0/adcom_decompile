// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.InterstitialSmash;

public interface InterstitialManagerListener
{
    void onInterstitialAdClicked(final InterstitialSmash p0);
    
    void onInterstitialAdClosed(final InterstitialSmash p0);
    
    void onInterstitialAdLoadFailed(final IronSourceError p0, final InterstitialSmash p1);
    
    void onInterstitialAdOpened(final InterstitialSmash p0);
    
    void onInterstitialAdReady(final InterstitialSmash p0);
    
    void onInterstitialAdShowFailed(final IronSourceError p0, final InterstitialSmash p1);
    
    void onInterstitialAdShowSucceeded(final InterstitialSmash p0);
    
    void onInterstitialAdVisible(final InterstitialSmash p0);
    
    void onInterstitialInitFailed(final IronSourceError p0, final InterstitialSmash p1);
    
    void onInterstitialInitSuccess(final InterstitialSmash p0);
}
