package com.applovin.impl.adview;

import android.webkit.WebSettings;

/* renamed from: com.applovin.impl.adview.q */
class C1263q implements Runnable {
    /* renamed from: a */
    final /* synthetic */ WebSettings f1907a;
    /* renamed from: b */
    final /* synthetic */ Boolean f1908b;
    /* renamed from: c */
    final /* synthetic */ C1260n f1909c;

    C1263q(C1260n c1260n, WebSettings webSettings, Boolean bool) {
        this.f1909c = c1260n;
        this.f1907a = webSettings;
        this.f1908b = bool;
    }

    public void run() {
        this.f1907a.setBuiltInZoomControls(this.f1908b.booleanValue());
    }
}
