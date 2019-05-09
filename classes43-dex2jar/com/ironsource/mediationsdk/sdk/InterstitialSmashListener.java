// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import com.ironsource.mediationsdk.logger.IronSourceError;

public interface InterstitialSmashListener extends InterstitialListener
{
    void onInterstitialAdVisible();
    
    void onInterstitialInitFailed(final IronSourceError p0);
    
    void onInterstitialInitSuccess();
}
