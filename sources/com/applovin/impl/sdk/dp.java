package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinPostbackListener;

class dp implements AppLovinPostbackListener {
    /* renamed from: a */
    final /* synthetic */ dq f2343a;
    /* renamed from: b */
    final /* synthetic */ C1277do f2344b;

    dp(C1277do c1277do, dq dqVar) {
        this.f2344b = c1277do;
        this.f2343a = dqVar;
    }

    public void onPostbackFailure(String str, int i) {
        this.f2344b.f2338b.mo4175i("PersistentPostbackManager", "Failed to submit postback with errorCode " + i + ". Will retry later...  Postback: " + this.f2343a);
        this.f2344b.m2606e(this.f2343a);
    }

    public void onPostbackSuccess(String str) {
        this.f2344b.m2605d(this.f2343a);
        this.f2344b.f2338b.mo4172d("PersistentPostbackManager", "Successfully submitted postback: " + this.f2343a);
        this.f2344b.m2613b();
    }
}
