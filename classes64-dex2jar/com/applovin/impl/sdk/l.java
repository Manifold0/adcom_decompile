// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import java.util.List;
import java.util.LinkedHashSet;
import com.applovin.sdk.AppLovinAd;
import java.util.Iterator;
import com.applovin.sdk.AppLovinAdLoadListener;

public class l extends ds
{
    l(final AppLovinSdkImpl appLovinSdkImpl) {
        super(appLovinSdkImpl);
    }
    
    @Override
    dx a(final n n) {
        final ex ex = new ex(n, this, this.a);
        ex.a(true);
        return ex;
    }
    
    @Override
    n a(final cj cj) {
        return ((q)cj).t();
    }
    
    @Override
    void a() {
        for (final n n : n.b(this.a)) {
            if (!n.e()) {
                this.i(n);
            }
        }
    }
    
    @Override
    public void a(final n n, final int n2) {
        this.c(n, n2);
    }
    
    @Override
    void a(final Object o, final cj cj) {
        ((AppLovinAdLoadListener)o).adReceived((AppLovinAd)cj);
    }
    
    @Override
    void a(final Object o, final n n, final int n2) {
        if (o instanceof at) {
            ((at)o).a(n, n2);
        }
        if (o instanceof AppLovinAdLoadListener) {
            ((AppLovinAdLoadListener)o).failedToReceiveAd(n2);
        }
    }
    
    @Override
    public void adReceived(final AppLovinAd appLovinAd) {
        this.c((cj)appLovinAd);
    }
    
    @Override
    public void failedToReceiveAd(final int n) {
    }
    
    @Override
    public void onNativeAdsFailedToLoad(final int n) {
    }
    
    @Override
    public void onNativeAdsLoaded(final List<AppLovinNativeAd> list) {
    }
}
