// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import com.ironsource.mediationsdk.logger.IronSourceError;

public interface ISDemandOnlyInterstitialListener
{
    void onInterstitialAdClicked(final String p0);
    
    void onInterstitialAdClosed(final String p0);
    
    void onInterstitialAdLoadFailed(final String p0, final IronSourceError p1);
    
    void onInterstitialAdOpened(final String p0);
    
    void onInterstitialAdReady(final String p0);
    
    void onInterstitialAdShowFailed(final String p0, final IronSourceError p1);
    
    void onInterstitialAdShowSucceeded(final String p0);
}
