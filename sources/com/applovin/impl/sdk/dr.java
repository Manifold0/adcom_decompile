package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinPostbackListener;

class dr implements AppLovinPostbackListener {
    /* renamed from: a */
    final /* synthetic */ AppLovinPostbackListener f2349a;
    /* renamed from: b */
    final /* synthetic */ PostbackServiceImpl f2350b;

    dr(PostbackServiceImpl postbackServiceImpl, AppLovinPostbackListener appLovinPostbackListener) {
        this.f2350b = postbackServiceImpl;
        this.f2349a = appLovinPostbackListener;
    }

    public void onPostbackFailure(String str, int i) {
        this.f2350b.f2008a.getLogger().mo4173e("PostbackService", "Failed to dispatch postback. Error code: " + i + " URL: " + str);
        if (this.f2349a != null) {
            this.f2349a.onPostbackFailure(str, i);
        }
    }

    public void onPostbackSuccess(String str) {
        this.f2350b.f2008a.getLogger().mo4172d("PostbackService", "Successfully dispatched postback to URL: " + str);
        if (this.f2349a != null) {
            this.f2349a.onPostbackSuccess(str);
        }
    }
}
