package com.applovin.impl.adview;

import android.webkit.WebSettings;

/* renamed from: com.applovin.impl.adview.s */
class C1265s implements Runnable {
    /* renamed from: a */
    final /* synthetic */ WebSettings f1913a;
    /* renamed from: b */
    final /* synthetic */ Boolean f1914b;
    /* renamed from: c */
    final /* synthetic */ C1260n f1915c;

    C1265s(C1260n c1260n, WebSettings webSettings, Boolean bool) {
        this.f1915c = c1260n;
        this.f1913a = webSettings;
        this.f1914b = bool;
    }

    public void run() {
        this.f1913a.setSaveFormData(this.f1914b.booleanValue());
    }
}
