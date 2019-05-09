package com.applovin.impl.adview;

import com.applovin.impl.sdk.fe;
import com.applovin.impl.sdk.fu;

class bm implements Runnable {
    /* renamed from: a */
    final /* synthetic */ az f1811a;

    bm(az azVar) {
        this.f1811a = azVar;
    }

    public void run() {
        if (this.f1811a.currentAd != null && !this.f1811a.currentAd.ae().getAndSet(true)) {
            this.f1811a.sdk.getTaskManager().m2855a(new fu(this.f1811a.currentAd, this.f1811a.sdk), fe.BACKGROUND);
        }
    }
}
