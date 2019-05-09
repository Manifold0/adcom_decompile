package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.List;

class dm implements AppLovinNativeAdLoadListener {
    /* renamed from: a */
    final /* synthetic */ AppLovinNativeAdLoadListener f2331a;
    /* renamed from: b */
    final /* synthetic */ df f2332b;

    dm(df dfVar, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.f2332b = dfVar;
        this.f2331a = appLovinNativeAdLoadListener;
    }

    public void onNativeAdsFailedToLoad(int i) {
        this.f2332b.m2582a(this.f2331a, i);
    }

    public void onNativeAdsLoaded(List<AppLovinNativeAd> list) {
        this.f2332b.m2583a(this.f2331a, (List) list);
    }
}
