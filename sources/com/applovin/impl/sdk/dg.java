package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.List;

class dg implements AppLovinNativeAdLoadListener {
    /* renamed from: a */
    final /* synthetic */ AppLovinNativeAdLoadListener f2318a;
    /* renamed from: b */
    final /* synthetic */ df f2319b;

    dg(df dfVar, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.f2319b = dfVar;
        this.f2318a = appLovinNativeAdLoadListener;
    }

    public void onNativeAdsFailedToLoad(int i) {
        this.f2319b.m2582a(this.f2318a, i);
    }

    public void onNativeAdsLoaded(List<AppLovinNativeAd> list) {
        this.f2319b.m2590a((List) list, this.f2318a);
    }
}
