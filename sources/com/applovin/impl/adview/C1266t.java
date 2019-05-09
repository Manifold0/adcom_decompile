package com.applovin.impl.adview;

import android.webkit.WebSettings;

/* renamed from: com.applovin.impl.adview.t */
class C1266t implements Runnable {
    /* renamed from: a */
    final /* synthetic */ WebSettings f1916a;
    /* renamed from: b */
    final /* synthetic */ Boolean f1917b;
    /* renamed from: c */
    final /* synthetic */ C1260n f1918c;

    C1266t(C1260n c1260n, WebSettings webSettings, Boolean bool) {
        this.f1918c = c1260n;
        this.f1916a = webSettings;
        this.f1917b = bool;
    }

    public void run() {
        this.f1916a.setGeolocationEnabled(this.f1917b.booleanValue());
    }
}
