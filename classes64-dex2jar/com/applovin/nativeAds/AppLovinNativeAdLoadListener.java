// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.nativeAds;

import java.util.List;

public interface AppLovinNativeAdLoadListener
{
    void onNativeAdsFailedToLoad(final int p0);
    
    void onNativeAdsLoaded(final List<AppLovinNativeAd> p0);
}
