package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

/* renamed from: com.applovin.impl.sdk.k */
class C1284k implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdClickListener f2594a;
    /* renamed from: b */
    final /* synthetic */ AppLovinAd f2595b;
    /* renamed from: c */
    final /* synthetic */ C1281h f2596c;

    C1284k(C1281h c1281h, AppLovinAdClickListener appLovinAdClickListener, AppLovinAd appLovinAd) {
        this.f2596c = c1281h;
        this.f2594a = appLovinAdClickListener;
        this.f2595b = appLovinAd;
    }

    public void run() {
        this.f2594a.adClicked(this.f2595b);
    }
}
