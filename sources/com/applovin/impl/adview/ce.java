package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;

class ce implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAd f1851a;
    /* renamed from: b */
    final /* synthetic */ cb f1852b;

    ce(cb cbVar, AppLovinAd appLovinAd) {
        this.f1852b = cbVar;
        this.f1851a = appLovinAd;
    }

    public void run() {
        if (this.f1852b.f1839h != null) {
            this.f1852b.f1839h.adReceived(this.f1851a);
        }
    }
}
