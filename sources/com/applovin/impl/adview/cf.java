package com.applovin.impl.adview;

class cf implements Runnable {
    /* renamed from: a */
    final /* synthetic */ int f1853a;
    /* renamed from: b */
    final /* synthetic */ cb f1854b;

    cf(cb cbVar, int i) {
        this.f1854b = cbVar;
        this.f1853a = i;
    }

    public void run() {
        if (this.f1854b.f1839h != null) {
            this.f1854b.f1839h.failedToReceiveAd(this.f1853a);
        }
    }
}
