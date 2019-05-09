package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

class bn implements AppLovinAdDisplayListener {
    /* renamed from: a */
    final /* synthetic */ az f1812a;

    bn(az azVar) {
        this.f1812a = azVar;
    }

    public void adDisplayed(AppLovinAd appLovinAd) {
        if (!this.f1812a.f1537g) {
            this.f1812a.m1699a(appLovinAd);
        }
    }

    public void adHidden(AppLovinAd appLovinAd) {
        this.f1812a.m1707b(appLovinAd);
    }
}
