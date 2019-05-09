package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import java.util.List;

/* renamed from: com.applovin.impl.sdk.l */
public class C1285l extends ds {
    C1285l(AppLovinSdkImpl appLovinSdkImpl) {
        super(appLovinSdkImpl);
    }

    /* renamed from: a */
    dx mo4130a(C1287n c1287n) {
        dx exVar = new ex(c1287n, this, this.a);
        exVar.m2823a(true);
        return exVar;
    }

    /* renamed from: a */
    C1287n mo4131a(cj cjVar) {
        return ((C1227q) cjVar).mo3997t();
    }

    /* renamed from: a */
    void mo4132a() {
        for (C1287n c1287n : C1287n.m3041b(this.a)) {
            if (!c1287n.m3056e()) {
                mo4147i(c1287n);
            }
        }
    }

    /* renamed from: a */
    public void mo4133a(C1287n c1287n, int i) {
        m2546c(c1287n, i);
    }

    /* renamed from: a */
    void mo4134a(Object obj, cj cjVar) {
        ((AppLovinAdLoadListener) obj).adReceived((AppLovinAd) cjVar);
    }

    /* renamed from: a */
    void mo4135a(Object obj, C1287n c1287n, int i) {
        if (obj instanceof at) {
            ((at) obj).mo4133a(c1287n, i);
        }
        if (obj instanceof AppLovinAdLoadListener) {
            ((AppLovinAdLoadListener) obj).failedToReceiveAd(i);
        }
    }

    public void adReceived(AppLovinAd appLovinAd) {
        m2545c((cj) appLovinAd);
    }

    /* renamed from: b */
    public /* bridge */ /* synthetic */ boolean mo4140b(C1287n c1287n) {
        return super.mo4140b(c1287n);
    }

    /* renamed from: c */
    public /* bridge */ /* synthetic */ cj mo4141c(C1287n c1287n) {
        return super.mo4141c(c1287n);
    }

    /* renamed from: d */
    public /* bridge */ /* synthetic */ cj mo4142d(C1287n c1287n) {
        return super.mo4142d(c1287n);
    }

    /* renamed from: e */
    public /* bridge */ /* synthetic */ cj mo4143e(C1287n c1287n) {
        return super.mo4143e(c1287n);
    }

    /* renamed from: f */
    public /* bridge */ /* synthetic */ boolean mo4144f(C1287n c1287n) {
        return super.mo4144f(c1287n);
    }

    public void failedToReceiveAd(int i) {
    }

    /* renamed from: g */
    public /* bridge */ /* synthetic */ void mo4145g(C1287n c1287n) {
        super.mo4145g(c1287n);
    }

    /* renamed from: h */
    public /* bridge */ /* synthetic */ boolean mo4146h(C1287n c1287n) {
        return super.mo4146h(c1287n);
    }

    /* renamed from: i */
    public /* bridge */ /* synthetic */ void mo4147i(C1287n c1287n) {
        super.mo4147i(c1287n);
    }

    /* renamed from: j */
    public /* bridge */ /* synthetic */ void mo4148j(C1287n c1287n) {
        super.mo4148j(c1287n);
    }

    public void onNativeAdsFailedToLoad(int i) {
    }

    public void onNativeAdsLoaded(List<AppLovinNativeAd> list) {
    }
}
