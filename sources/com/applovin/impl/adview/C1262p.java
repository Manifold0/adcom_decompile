package com.applovin.impl.adview;

import android.webkit.WebSettings;

/* renamed from: com.applovin.impl.adview.p */
class C1262p implements Runnable {
    /* renamed from: a */
    final /* synthetic */ WebSettings f1904a;
    /* renamed from: b */
    final /* synthetic */ Boolean f1905b;
    /* renamed from: c */
    final /* synthetic */ C1260n f1906c;

    C1262p(C1260n c1260n, WebSettings webSettings, Boolean bool) {
        this.f1906c = c1260n;
        this.f1904a = webSettings;
        this.f1905b = bool;
    }

    public void run() {
        this.f1904a.setAllowContentAccess(this.f1905b.booleanValue());
    }
}
