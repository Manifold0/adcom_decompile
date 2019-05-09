package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.List;

class dj implements AppLovinNativeAdLoadListener {
    /* renamed from: a */
    final /* synthetic */ List f2324a;
    /* renamed from: b */
    final /* synthetic */ AppLovinNativeAdLoadListener f2325b;
    /* renamed from: c */
    final /* synthetic */ List f2326c;
    /* renamed from: d */
    final /* synthetic */ df f2327d;

    dj(df dfVar, List list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener, List list2) {
        this.f2327d = dfVar;
        this.f2324a = list;
        this.f2325b = appLovinNativeAdLoadListener;
        this.f2326c = list2;
    }

    public void onNativeAdsFailedToLoad(int i) {
        if (this.f2325b != null) {
            this.f2325b.onNativeAdsFailedToLoad(i);
        }
    }

    public void onNativeAdsLoaded(List<AppLovinNativeAd> list) {
        this.f2327d.m2588c(this.f2324a, new dk(this));
    }
}
