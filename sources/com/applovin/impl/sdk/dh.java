package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;

class dh implements AppLovinNativeAdPrecacheListener {
    /* renamed from: a */
    final /* synthetic */ AppLovinNativeAdPrecacheListener f2320a;
    /* renamed from: b */
    final /* synthetic */ df f2321b;

    dh(df dfVar, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        this.f2321b = dfVar;
        this.f2320a = appLovinNativeAdPrecacheListener;
    }

    public void onNativeAdImagePrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
        this.f2321b.m2584a(this.f2320a, appLovinNativeAd, i, false);
    }

    public void onNativeAdImagesPrecached(AppLovinNativeAd appLovinNativeAd) {
        this.f2321b.m2585a(this.f2320a, appLovinNativeAd, false);
        this.f2321b.m2581a(appLovinNativeAd, this.f2320a);
    }

    public void onNativeAdVideoPrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
    }

    public void onNativeAdVideoPreceached(AppLovinNativeAd appLovinNativeAd) {
    }
}
