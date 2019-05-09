// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import com.ironsource.mediationsdk.logger.IronSourceError;

public interface BannerListener
{
    void onBannerAdClicked();
    
    void onBannerAdLeftApplication();
    
    void onBannerAdLoadFailed(final IronSourceError p0);
    
    void onBannerAdLoaded();
    
    void onBannerAdScreenDismissed();
    
    void onBannerAdScreenPresented();
}
