package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.ArrayList;
import java.util.List;

class dk implements AppLovinNativeAdLoadListener {
    /* renamed from: a */
    final /* synthetic */ dj f2328a;

    dk(dj djVar) {
        this.f2328a = djVar;
    }

    public void onNativeAdsFailedToLoad(int i) {
        if (this.f2328a.f2325b != null) {
            this.f2328a.f2325b.onNativeAdsFailedToLoad(i);
        }
    }

    public void onNativeAdsLoaded(List<AppLovinNativeAd> list) {
        if (this.f2328a.f2325b != null) {
            List arrayList = new ArrayList();
            arrayList.addAll(this.f2328a.f2324a);
            arrayList.addAll(this.f2328a.f2326c);
            this.f2328a.f2325b.onNativeAdsLoaded(arrayList);
        }
    }
}
