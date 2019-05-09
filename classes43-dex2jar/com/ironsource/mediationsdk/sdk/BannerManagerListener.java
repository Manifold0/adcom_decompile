// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import android.widget.FrameLayout$LayoutParams;
import android.view.View;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.BannerSmash;

public interface BannerManagerListener
{
    void onBannerAdClicked(final BannerSmash p0);
    
    void onBannerAdLeftApplication(final BannerSmash p0);
    
    void onBannerAdLoadFailed(final IronSourceError p0, final BannerSmash p1);
    
    void onBannerAdLoaded(final BannerSmash p0, final View p1, final FrameLayout$LayoutParams p2);
    
    void onBannerAdReloadFailed(final IronSourceError p0, final BannerSmash p1);
    
    void onBannerAdReloaded(final BannerSmash p0);
    
    void onBannerAdScreenDismissed(final BannerSmash p0);
    
    void onBannerAdScreenPresented(final BannerSmash p0);
}
