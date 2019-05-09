package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.sdk.AppLovinAd;
import java.util.Arrays;
import java.util.List;

public class dd extends ds {
    public dd(AppLovinSdkImpl appLovinSdkImpl) {
        super(appLovinSdkImpl);
    }

    /* renamed from: a */
    dx mo4130a(C1287n c1287n) {
        return new ez(null, 1, this.a, this);
    }

    /* renamed from: a */
    C1287n mo4131a(cj cjVar) {
        return ((NativeAdImpl) cjVar).getAdZone();
    }

    /* renamed from: a */
    void mo4132a() {
        mo4147i(C1287n.m3049j(this.a));
    }

    /* renamed from: a */
    public void mo4133a(C1287n c1287n, int i) {
    }

    /* renamed from: a */
    void mo4134a(Object obj, cj cjVar) {
        AppLovinNativeAdLoadListener appLovinNativeAdLoadListener = (AppLovinNativeAdLoadListener) obj;
        appLovinNativeAdLoadListener.onNativeAdsLoaded(Arrays.asList(new AppLovinNativeAd[]{(AppLovinNativeAd) cjVar}));
    }

    /* renamed from: a */
    void mo4135a(Object obj, C1287n c1287n, int i) {
        ((AppLovinNativeAdLoadListener) obj).onNativeAdsFailedToLoad(i);
    }

    public void adReceived(AppLovinAd appLovinAd) {
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
        m2546c(C1287n.m3049j(this.a), i);
    }

    public void onNativeAdsLoaded(List<AppLovinNativeAd> list) {
        AppLovinNativeAd appLovinNativeAd = (AppLovinNativeAd) list.get(0);
        if (((Boolean) this.a.get(ea.ce)).booleanValue()) {
            this.a.getNativeAdService().precacheResources(appLovinNativeAd, new de(this));
        } else {
            m2545c((cj) appLovinNativeAd);
        }
    }
}
