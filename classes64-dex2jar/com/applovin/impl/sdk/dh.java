// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;

class dh implements AppLovinNativeAdPrecacheListener
{
    final /* synthetic */ AppLovinNativeAdPrecacheListener a;
    final /* synthetic */ df b;
    
    dh(final df b, final AppLovinNativeAdPrecacheListener a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void onNativeAdImagePrecachingFailed(final AppLovinNativeAd appLovinNativeAd, final int n) {
        this.b.a(this.a, appLovinNativeAd, n, false);
    }
    
    @Override
    public void onNativeAdImagesPrecached(final AppLovinNativeAd appLovinNativeAd) {
        this.b.a(this.a, appLovinNativeAd, false);
        this.b.a(appLovinNativeAd, this.a);
    }
    
    @Override
    public void onNativeAdVideoPrecachingFailed(final AppLovinNativeAd appLovinNativeAd, final int n) {
    }
    
    @Override
    public void onNativeAdVideoPreceached(final AppLovinNativeAd appLovinNativeAd) {
    }
}
