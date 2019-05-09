// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import java.util.List;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;

class dj implements AppLovinNativeAdLoadListener
{
    final /* synthetic */ List a;
    final /* synthetic */ AppLovinNativeAdLoadListener b;
    final /* synthetic */ List c;
    final /* synthetic */ df d;
    
    dj(final df d, final List a, final AppLovinNativeAdLoadListener b, final List c) {
        this.d = d;
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    @Override
    public void onNativeAdsFailedToLoad(final int n) {
        if (this.b != null) {
            this.b.onNativeAdsFailedToLoad(n);
        }
    }
    
    @Override
    public void onNativeAdsLoaded(final List<AppLovinNativeAd> list) {
        this.d.c(this.a, new dk(this));
    }
}
