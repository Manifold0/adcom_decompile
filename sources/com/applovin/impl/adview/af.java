package com.applovin.impl.adview;

import android.webkit.WebSettings;

class af implements Runnable {
    /* renamed from: a */
    final /* synthetic */ WebSettings f1725a;
    /* renamed from: b */
    final /* synthetic */ Boolean f1726b;
    /* renamed from: c */
    final /* synthetic */ C1260n f1727c;

    af(C1260n c1260n, WebSettings webSettings, Boolean bool) {
        this.f1727c = c1260n;
        this.f1725a = webSettings;
        this.f1726b = bool;
    }

    public void run() {
        this.f1725a.setLoadWithOverviewMode(this.f1726b.booleanValue());
    }
}
