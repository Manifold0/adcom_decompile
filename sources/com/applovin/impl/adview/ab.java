package com.applovin.impl.adview;

import com.applovin.impl.sdk.an;

class ab implements Runnable {
    /* renamed from: a */
    final /* synthetic */ an f1716a;
    /* renamed from: b */
    final /* synthetic */ C1260n f1717b;

    ab(C1260n c1260n, an anVar) {
        this.f1717b = c1260n;
        this.f1716a = anVar;
    }

    public void run() {
        this.f1717b.getSettings().setMediaPlaybackRequiresUserGesture(this.f1716a.ag());
    }
}
