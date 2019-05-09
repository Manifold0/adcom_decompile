package com.applovin.impl.adview;

import android.webkit.WebView;

class ac implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C1260n f1718a;

    ac(C1260n c1260n) {
        this.f1718a = c1260n;
    }

    public void run() {
        WebView.setWebContentsDebuggingEnabled(true);
    }
}
