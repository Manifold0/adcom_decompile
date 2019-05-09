package com.applovin.impl.sdk;

class bc implements Runnable {
    /* renamed from: a */
    final /* synthetic */ int f2125a;
    /* renamed from: b */
    final /* synthetic */ ba f2126b;

    bc(ba baVar, int i) {
        this.f2126b = baVar;
        this.f2125a = i;
    }

    public void run() {
        this.f2126b.f2122b.failedToReceiveAd(this.f2125a);
    }
}
