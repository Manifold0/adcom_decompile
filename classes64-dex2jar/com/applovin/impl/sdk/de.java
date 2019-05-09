// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;

class de implements AppLovinNativeAdPrecacheListener
{
    final /* synthetic */ dd a;
    
    de(final dd a) {
        this.a = a;
    }
    
    @Override
    public void onNativeAdImagePrecachingFailed(final AppLovinNativeAd appLovinNativeAd, final int n) {
        this.a.c(n.j(this.a.a), n);
    }
    
    @Override
    public void onNativeAdImagesPrecached(final AppLovinNativeAd appLovinNativeAd) {
        if (!AppLovinSdkUtils.isValidString(appLovinNativeAd.getVideoUrl())) {
            this.a.c((cj)appLovinNativeAd);
        }
    }
    
    @Override
    public void onNativeAdVideoPrecachingFailed(final AppLovinNativeAd appLovinNativeAd, final int n) {
        this.a.b.w("NativeAdPreloadManager", "Video failed to cache during native ad preload. " + n);
        this.a.c((cj)appLovinNativeAd);
    }
    
    @Override
    public void onNativeAdVideoPreceached(final AppLovinNativeAd appLovinNativeAd) {
        this.a.c((cj)appLovinNativeAd);
    }
}
