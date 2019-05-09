// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import android.widget.FrameLayout$LayoutParams;
import android.view.View;
import com.ironsource.mediationsdk.logger.IronSourceError;

public interface BannerSmashListener
{
    void onBannerAdClicked();
    
    void onBannerAdLeftApplication();
    
    void onBannerAdLoadFailed(final IronSourceError p0);
    
    void onBannerAdLoaded(final View p0, final FrameLayout$LayoutParams p1);
    
    void onBannerAdScreenDismissed();
    
    void onBannerAdScreenPresented();
    
    void onBannerInitFailed(final IronSourceError p0);
    
    void onBannerInitSuccess();
}
