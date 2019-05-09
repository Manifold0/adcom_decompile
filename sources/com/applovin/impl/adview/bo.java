package com.applovin.impl.adview;

import com.applovin.impl.sdk.bv;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

class bo implements AppLovinAdClickListener {
    /* renamed from: a */
    final /* synthetic */ az f1813a;

    bo(az azVar) {
        this.f1813a = azVar;
    }

    public void adClicked(AppLovinAd appLovinAd) {
        bv.m2400a(this.f1813a.f1532b.m2068e(), appLovinAd, this.f1813a.sdk);
    }
}
