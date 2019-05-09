package com.applovin.impl.adview;

import android.webkit.WebSettings;

/* renamed from: com.applovin.impl.adview.y */
class C1271y implements Runnable {
    /* renamed from: a */
    final /* synthetic */ WebSettings f1931a;
    /* renamed from: b */
    final /* synthetic */ Boolean f1932b;
    /* renamed from: c */
    final /* synthetic */ C1260n f1933c;

    C1271y(C1260n c1260n, WebSettings webSettings, Boolean bool) {
        this.f1933c = c1260n;
        this.f1931a = webSettings;
        this.f1932b = bool;
    }

    public void run() {
        this.f1931a.setOffscreenPreRaster(this.f1932b.booleanValue());
    }
}
