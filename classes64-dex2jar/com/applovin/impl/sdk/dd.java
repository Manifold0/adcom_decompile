// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import java.util.List;
import com.applovin.sdk.AppLovinAd;
import java.util.LinkedHashSet;
import java.util.Arrays;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;

public class dd extends ds
{
    public dd(final AppLovinSdkImpl appLovinSdkImpl) {
        super(appLovinSdkImpl);
    }
    
    @Override
    dx a(final n n) {
        return new ez(null, 1, this.a, this);
    }
    
    @Override
    n a(final cj cj) {
        return ((NativeAdImpl)cj).getAdZone();
    }
    
    @Override
    void a() {
        this.i(n.j(this.a));
    }
    
    @Override
    public void a(final n n, final int n2) {
    }
    
    @Override
    void a(final Object o, final cj cj) {
        ((AppLovinNativeAdLoadListener)o).onNativeAdsLoaded(Arrays.asList((AppLovinNativeAd)cj));
    }
    
    @Override
    void a(final Object o, final n n, final int n2) {
        ((AppLovinNativeAdLoadListener)o).onNativeAdsFailedToLoad(n2);
    }
    
    @Override
    public void adReceived(final AppLovinAd appLovinAd) {
    }
    
    @Override
    public void failedToReceiveAd(final int n) {
    }
    
    @Override
    public void onNativeAdsFailedToLoad(final int n) {
        this.c(n.j(this.a), n);
    }
    
    @Override
    public void onNativeAdsLoaded(final List<AppLovinNativeAd> list) {
        final AppLovinNativeAd appLovinNativeAd = list.get(0);
        if (this.a.get(ea.ce)) {
            this.a.getNativeAdService().precacheResources(appLovinNativeAd, new de(this));
            return;
        }
        this.c((cj)appLovinNativeAd);
    }
}
