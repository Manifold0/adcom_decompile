package com.applovin.impl.adview;

import android.webkit.WebSettings;

/* renamed from: com.applovin.impl.adview.u */
class C1267u implements Runnable {
    /* renamed from: a */
    final /* synthetic */ WebSettings f1919a;
    /* renamed from: b */
    final /* synthetic */ Boolean f1920b;
    /* renamed from: c */
    final /* synthetic */ C1260n f1921c;

    C1267u(C1260n c1260n, WebSettings webSettings, Boolean bool) {
        this.f1921c = c1260n;
        this.f1919a = webSettings;
        this.f1920b = bool;
    }

    public void run() {
        this.f1919a.setNeedInitialFocus(this.f1920b.booleanValue());
    }
}
