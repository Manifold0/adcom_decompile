package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinSdk;

final class cd implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdClickListener f2193a;
    /* renamed from: b */
    final /* synthetic */ AppLovinAd f2194b;
    /* renamed from: c */
    final /* synthetic */ AppLovinSdk f2195c;

    cd(AppLovinAdClickListener appLovinAdClickListener, AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        this.f2193a = appLovinAdClickListener;
        this.f2194b = appLovinAd;
        this.f2195c = appLovinSdk;
    }

    public void run() {
        try {
            this.f2193a.adClicked(bv.m2407b(this.f2194b));
        } catch (Throwable th) {
            this.f2195c.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being clicked", th);
        }
    }
}
