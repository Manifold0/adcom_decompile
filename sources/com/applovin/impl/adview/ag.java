package com.applovin.impl.adview;

import android.webkit.WebSettings;

class ag implements Runnable {
    /* renamed from: a */
    final /* synthetic */ WebSettings f1728a;
    /* renamed from: b */
    final /* synthetic */ Boolean f1729b;
    /* renamed from: c */
    final /* synthetic */ C1260n f1730c;

    ag(C1260n c1260n, WebSettings webSettings, Boolean bool) {
        this.f1730c = c1260n;
        this.f1728a = webSettings;
        this.f1729b = bool;
    }

    public void run() {
        this.f1728a.setUseWideViewPort(this.f1729b.booleanValue());
    }
}
