package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

class da implements AppLovinAdClickListener {
    /* renamed from: a */
    final /* synthetic */ ck f2285a;
    /* renamed from: b */
    final /* synthetic */ MediationServiceImpl f2286b;

    da(MediationServiceImpl mediationServiceImpl, ck ckVar) {
        this.f2286b = mediationServiceImpl;
        this.f2285a = ckVar;
    }

    public void adClicked(AppLovinAd appLovinAd) {
        this.f2286b.m2180c(this.f2285a);
    }
}
