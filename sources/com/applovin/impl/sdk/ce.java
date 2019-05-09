package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;

final class ce implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdVideoPlaybackListener f2196a;
    /* renamed from: b */
    final /* synthetic */ AppLovinAd f2197b;
    /* renamed from: c */
    final /* synthetic */ AppLovinSdk f2198c;

    ce(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        this.f2196a = appLovinAdVideoPlaybackListener;
        this.f2197b = appLovinAd;
        this.f2198c = appLovinSdk;
    }

    public void run() {
        try {
            this.f2196a.videoPlaybackBegan(bv.m2407b(this.f2197b));
        } catch (Throwable th) {
            this.f2198c.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad event listener about ad playback began", th);
        }
    }
}
