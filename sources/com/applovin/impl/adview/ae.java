package com.applovin.impl.adview;

import android.webkit.WebSettings;

class ae implements Runnable {
    /* renamed from: a */
    final /* synthetic */ WebSettings f1722a;
    /* renamed from: b */
    final /* synthetic */ Boolean f1723b;
    /* renamed from: c */
    final /* synthetic */ C1260n f1724c;

    ae(C1260n c1260n, WebSettings webSettings, Boolean bool) {
        this.f1724c = c1260n;
        this.f1722a = webSettings;
        this.f1723b = bool;
    }

    public void run() {
        this.f1722a.setAllowFileAccess(this.f1723b.booleanValue());
    }
}
