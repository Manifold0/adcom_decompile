package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

class cz implements AppLovinAdDisplayListener {
    /* renamed from: a */
    final /* synthetic */ ck f2269a;
    /* renamed from: b */
    final /* synthetic */ MediationServiceImpl f2270b;

    cz(MediationServiceImpl mediationServiceImpl, ck ckVar) {
        this.f2270b = mediationServiceImpl;
        this.f2269a = ckVar;
    }

    public void adDisplayed(AppLovinAd appLovinAd) {
        this.f2270b.m2178b(this.f2269a);
    }

    public void adHidden(AppLovinAd appLovinAd) {
    }
}
