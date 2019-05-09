// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import java.util.List;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;

class dm implements AppLovinNativeAdLoadListener
{
    final /* synthetic */ AppLovinNativeAdLoadListener a;
    final /* synthetic */ df b;
    
    dm(final df b, final AppLovinNativeAdLoadListener a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void onNativeAdsFailedToLoad(final int n) {
        this.b.a(this.a, n);
    }
    
    @Override
    public void onNativeAdsLoaded(final List<AppLovinNativeAd> list) {
        this.b.a(this.a, list);
    }
}
