// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.nativeAds;

public interface AppLovinNativeAdPrecacheListener
{
    void onNativeAdImagePrecachingFailed(final AppLovinNativeAd p0, final int p1);
    
    void onNativeAdImagesPrecached(final AppLovinNativeAd p0);
    
    void onNativeAdVideoPrecachingFailed(final AppLovinNativeAd p0, final int p1);
    
    void onNativeAdVideoPreceached(final AppLovinNativeAd p0);
}
