// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.nativeAds;

public interface AppLovinNativeAdService
{
    @Deprecated
    boolean hasPreloadedAdForZoneId(final String p0);
    
    void loadNativeAds(final int p0, final AppLovinNativeAdLoadListener p1);
    
    void loadNativeAds(final int p0, final String p1, final AppLovinNativeAdLoadListener p2);
    
    void precacheResources(final AppLovinNativeAd p0, final AppLovinNativeAdPrecacheListener p1);
    
    @Deprecated
    void preloadAdForZoneId(final String p0);
}
