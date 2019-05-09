package com.applovin.impl.adview;

import android.webkit.WebSettings;

/* renamed from: com.applovin.impl.adview.r */
class C1264r implements Runnable {
    /* renamed from: a */
    final /* synthetic */ WebSettings f1910a;
    /* renamed from: b */
    final /* synthetic */ Boolean f1911b;
    /* renamed from: c */
    final /* synthetic */ C1260n f1912c;

    C1264r(C1260n c1260n, WebSettings webSettings, Boolean bool) {
        this.f1912c = c1260n;
        this.f1910a = webSettings;
        this.f1911b = bool;
    }

    public void run() {
        this.f1910a.setDisplayZoomControls(this.f1911b.booleanValue());
    }
}
