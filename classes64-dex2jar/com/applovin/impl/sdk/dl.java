// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import java.util.List;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;

class dl implements AppLovinNativeAdLoadListener
{
    final /* synthetic */ AppLovinNativeAdLoadListener a;
    final /* synthetic */ df b;
    
    dl(final df b, final AppLovinNativeAdLoadListener a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void onNativeAdsFailedToLoad(final int n) {
        if (this.a != null) {
            this.a.onNativeAdsFailedToLoad(n);
        }
    }
    
    @Override
    public void onNativeAdsLoaded(final List<AppLovinNativeAd> list) {
        if (this.a != null) {
            this.a.onNativeAdsLoaded(list);
        }
    }
}
