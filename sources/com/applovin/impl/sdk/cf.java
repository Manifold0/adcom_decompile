package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;

final class cf implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdVideoPlaybackListener f2199a;
    /* renamed from: b */
    final /* synthetic */ AppLovinAd f2200b;
    /* renamed from: c */
    final /* synthetic */ double f2201c;
    /* renamed from: d */
    final /* synthetic */ boolean f2202d;
    /* renamed from: e */
    final /* synthetic */ AppLovinSdk f2203e;

    cf(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAd appLovinAd, double d, boolean z, AppLovinSdk appLovinSdk) {
        this.f2199a = appLovinAdVideoPlaybackListener;
        this.f2200b = appLovinAd;
        this.f2201c = d;
        this.f2202d = z;
        this.f2203e = appLovinSdk;
    }

    public void run() {
        try {
            this.f2199a.videoPlaybackEnded(bv.m2407b(this.f2200b), this.f2201c, this.f2202d);
        } catch (Throwable th) {
            this.f2203e.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad event listener about ad playback ended", th);
        }
    }
}
