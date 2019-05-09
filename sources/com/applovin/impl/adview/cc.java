package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;

class cc implements AppLovinAdLoadListener {
    /* renamed from: a */
    final /* synthetic */ String f1847a;
    /* renamed from: b */
    final /* synthetic */ cb f1848b;

    cc(cb cbVar, String str) {
        this.f1848b = cbVar;
        this.f1847a = str;
    }

    public void adReceived(AppLovinAd appLovinAd) {
        this.f1848b.m2059b(appLovinAd);
        this.f1848b.showAndRender(appLovinAd, this.f1847a);
    }

    public void failedToReceiveAd(int i) {
        this.f1848b.m2050a(i);
    }
}
