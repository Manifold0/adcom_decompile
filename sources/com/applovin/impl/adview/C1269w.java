package com.applovin.impl.adview;

import android.webkit.WebSettings;

/* renamed from: com.applovin.impl.adview.w */
class C1269w implements Runnable {
    /* renamed from: a */
    final /* synthetic */ WebSettings f1925a;
    /* renamed from: b */
    final /* synthetic */ Boolean f1926b;
    /* renamed from: c */
    final /* synthetic */ C1260n f1927c;

    C1269w(C1260n c1260n, WebSettings webSettings, Boolean bool) {
        this.f1927c = c1260n;
        this.f1925a = webSettings;
        this.f1926b = bool;
    }

    public void run() {
        this.f1925a.setAllowUniversalAccessFromFileURLs(this.f1926b.booleanValue());
    }
}
