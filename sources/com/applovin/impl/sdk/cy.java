package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;

class cy implements AppLovinAdLoadListener {
    /* renamed from: a */
    final /* synthetic */ long f2264a;
    /* renamed from: b */
    final /* synthetic */ cp f2265b;
    /* renamed from: c */
    final /* synthetic */ ck f2266c;
    /* renamed from: d */
    final /* synthetic */ AppLovinAdLoadListener f2267d;
    /* renamed from: e */
    final /* synthetic */ MediationServiceImpl f2268e;

    cy(MediationServiceImpl mediationServiceImpl, long j, cp cpVar, ck ckVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        this.f2268e = mediationServiceImpl;
        this.f2264a = j;
        this.f2265b = cpVar;
        this.f2266c = ckVar;
        this.f2267d = appLovinAdLoadListener;
    }

    public void adReceived(AppLovinAd appLovinAd) {
        long currentTimeMillis = System.currentTimeMillis() - this.f2264a;
        synchronized (this.f2268e.f1982d) {
            this.f2268e.f1984f = this.f2265b.m2467a();
            this.f2268e.f1983e = currentTimeMillis;
        }
        C1280g.m2907a(this.f2266c, this.f2268e.f1979a);
        C1280g.m2905a(currentTimeMillis, this.f2266c, this.f2268e.f1979a);
        if (((Boolean) this.f2268e.f1979a.get(ea.dC)).booleanValue()) {
            this.f2268e.f1981c.m2447a(this.f2265b);
        }
        this.f2268e.m2174a(appLovinAd, this.f2267d);
    }

    public void failedToReceiveAd(int i) {
        this.f2268e.m2173a(this.f2266c, i, this.f2267d);
    }
}
