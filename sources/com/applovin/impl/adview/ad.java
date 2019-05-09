package com.applovin.impl.adview;

import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;

class ad implements Runnable {
    /* renamed from: a */
    final /* synthetic */ WebSettings f1719a;
    /* renamed from: b */
    final /* synthetic */ PluginState f1720b;
    /* renamed from: c */
    final /* synthetic */ C1260n f1721c;

    ad(C1260n c1260n, WebSettings webSettings, PluginState pluginState) {
        this.f1721c = c1260n;
        this.f1719a = webSettings;
        this.f1720b = pluginState;
    }

    public void run() {
        this.f1719a.setPluginState(this.f1720b);
    }
}
