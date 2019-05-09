package com.moat.analytics.mobile.vng;

import android.support.annotation.Nullable;
import android.webkit.WebView;

class aa extends C0822b implements WebAdTracker {
    aa(@Nullable WebView webView) {
        super(webView, false, false);
        C0858p.m1577a(3, "WebAdTracker", (Object) this, "In initialization method.");
        super.m1448a(webView);
        C0858p.m1579a("[SUCCESS] ", mo1662a() + " created for " + m1453e());
    }

    /* renamed from: a */
    String mo1662a() {
        return "WebAdTracker";
    }
}
