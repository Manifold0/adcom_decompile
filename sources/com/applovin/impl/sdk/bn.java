package com.applovin.impl.sdk;

class bn implements Runnable {
    /* renamed from: a */
    final /* synthetic */ bm f2156a;

    bn(bm bmVar) {
        this.f2156a = bmVar;
    }

    public void run() {
        if (this.f2156a.f2155c != null) {
            this.f2156a.f2155c.dismiss();
        }
    }
}
