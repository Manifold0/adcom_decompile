package com.applovin.impl.adview;

import android.webkit.WebSettings;

/* renamed from: com.applovin.impl.adview.x */
class C1270x implements Runnable {
    /* renamed from: a */
    final /* synthetic */ WebSettings f1928a;
    /* renamed from: b */
    final /* synthetic */ Integer f1929b;
    /* renamed from: c */
    final /* synthetic */ C1260n f1930c;

    C1270x(C1260n c1260n, WebSettings webSettings, Integer num) {
        this.f1930c = c1260n;
        this.f1928a = webSettings;
        this.f1929b = num;
    }

    public void run() {
        this.f1928a.setMixedContentMode(this.f1929b.intValue());
    }
}
