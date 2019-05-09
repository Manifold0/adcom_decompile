// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters;

import com.facebook.ads.AdError;

public interface InterstitialAdapterListener
{
    void onInterstitialActivityDestroyed();
    
    void onInterstitialAdClicked(final g p0, final String p1, final boolean p2);
    
    void onInterstitialAdDismissed(final g p0);
    
    void onInterstitialAdDisplayed(final g p0);
    
    void onInterstitialAdLoaded(final g p0);
    
    void onInterstitialError(final g p0, final AdError p1);
    
    void onInterstitialLoggingImpression(final g p0);
}
