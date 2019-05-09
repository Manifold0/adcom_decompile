package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;

class bb implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAd f2123a;
    /* renamed from: b */
    final /* synthetic */ ba f2124b;

    bb(ba baVar, AppLovinAd appLovinAd) {
        this.f2124b = baVar;
        this.f2123a = appLovinAd;
    }

    public void run() {
        this.f2124b.f2122b.adReceived(this.f2123a);
    }
}
