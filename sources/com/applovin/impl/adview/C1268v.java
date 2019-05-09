package com.applovin.impl.adview;

import android.webkit.WebSettings;

/* renamed from: com.applovin.impl.adview.v */
class C1268v implements Runnable {
    /* renamed from: a */
    final /* synthetic */ WebSettings f1922a;
    /* renamed from: b */
    final /* synthetic */ Boolean f1923b;
    /* renamed from: c */
    final /* synthetic */ C1260n f1924c;

    C1268v(C1260n c1260n, WebSettings webSettings, Boolean bool) {
        this.f1924c = c1260n;
        this.f1922a = webSettings;
        this.f1923b = bool;
    }

    public void run() {
        this.f1922a.setAllowFileAccessFromFileURLs(this.f1923b.booleanValue());
    }
}
