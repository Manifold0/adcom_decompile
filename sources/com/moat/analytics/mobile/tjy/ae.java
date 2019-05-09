package com.moat.analytics.mobile.tjy;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.moat.analytics.mobile.tjy.base.exception.C2747a;

class ae extends WebViewClient {
    /* renamed from: a */
    final /* synthetic */ ad f6622a;

    ae(ad adVar) {
        this.f6622a = adVar;
    }

    public void onPageFinished(WebView webView, String str) {
        if (!this.f6622a.f6619i) {
            try {
                this.f6622a.f6619i = true;
                this.f6622a.f6613c = new bi((View) this.f6622a.f6617g.get(), this.f6622a.f6618h, true, this.f6622a.f6611a, this.f6622a.f6612b);
                this.f6622a.f6613c.mo6121c();
                this.f6622a.m6800a();
            } catch (Exception e) {
                C2747a.m6882a(e);
            }
        }
    }
}
