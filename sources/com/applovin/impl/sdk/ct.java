package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;

class ct implements Runnable {
    /* renamed from: a */
    final /* synthetic */ ck f2247a;
    /* renamed from: b */
    final /* synthetic */ cp f2248b;

    ct(cp cpVar, ck ckVar) {
        this.f2248b = cpVar;
        this.f2247a = ckVar;
    }

    public void run() {
        this.f2248b.m2464b(this.f2247a);
        if (this.f2247a.getType() == AppLovinAdType.REGULAR) {
            if (this.f2247a.getSize() == AppLovinAdSize.INTERSTITIAL) {
                this.f2248b.f2236b.prepareInterstitialAd(this.f2248b.f2239e, this.f2248b.f2237c.getApplicationContext());
            }
        } else if (this.f2247a.getType() == AppLovinAdType.INCENTIVIZED) {
            this.f2248b.f2236b.prepareIncentivizedAd(this.f2248b.f2239e, this.f2248b.f2237c.getApplicationContext());
        }
    }
}
