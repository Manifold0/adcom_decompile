// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Collection;
import java.util.ArrayList;
import com.applovin.nativeAds.AppLovinNativeAd;
import java.util.List;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;

class dk implements AppLovinNativeAdLoadListener
{
    final /* synthetic */ dj a;
    
    dk(final dj a) {
        this.a = a;
    }
    
    @Override
    public void onNativeAdsFailedToLoad(final int n) {
        if (this.a.b != null) {
            this.a.b.onNativeAdsFailedToLoad(n);
        }
    }
    
    @Override
    public void onNativeAdsLoaded(final List<AppLovinNativeAd> list) {
        if (this.a.b != null) {
            final ArrayList<Object> list2 = new ArrayList<Object>();
            list2.addAll(this.a.a);
            list2.addAll(this.a.c);
            this.a.b.onNativeAdsLoaded((List<AppLovinNativeAd>)list2);
        }
    }
}
