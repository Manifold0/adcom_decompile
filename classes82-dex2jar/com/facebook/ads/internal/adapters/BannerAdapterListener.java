// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters;

import com.facebook.ads.AdError;
import android.view.View;

public interface BannerAdapterListener
{
    void onBannerAdClicked(final e p0);
    
    void onBannerAdLoaded(final e p0, final View p1);
    
    void onBannerError(final e p0, final AdError p1);
    
    void onBannerLoggingImpression(final e p0);
}
