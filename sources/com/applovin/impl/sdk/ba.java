package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinSdkUtils;

class ba implements AppLovinAdLoadListener {
    /* renamed from: a */
    final /* synthetic */ ax f2121a;
    /* renamed from: b */
    private final AppLovinAdLoadListener f2122b;

    ba(ax axVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        this.f2121a = axVar;
        this.f2122b = appLovinAdLoadListener;
    }

    public void adReceived(AppLovinAd appLovinAd) {
        this.f2121a.f2084c = appLovinAd;
        if (this.f2122b != null) {
            AppLovinSdkUtils.runOnUiThread(new bb(this, appLovinAd));
        }
    }

    public void failedToReceiveAd(int i) {
        if (this.f2122b != null) {
            AppLovinSdkUtils.runOnUiThread(new bc(this, i));
        }
    }
}
