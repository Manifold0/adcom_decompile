package com.moat.analytics.mobile.iro;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.webkit.WebView;

/* renamed from: com.moat.analytics.mobile.iro.x */
final class C0806x extends C0757c implements WebAdTracker {
    C0806x(@Nullable ViewGroup viewGroup) {
        this((WebView) C0804v.m1404(viewGroup, false).orElse(null));
        if (viewGroup == null) {
            String str = "Target ViewGroup is null";
            String str2 = "WebAdTracker initialization not successful, " + str;
            C0756b.m1234(3, "WebAdTracker", this, str2);
            C0756b.m1232("[ERROR] ", str2);
            this.ˋ = new C0785o(str);
        }
        if (this.ॱ == null) {
            str = "No WebView to track inside of ad container";
            str2 = "WebAdTracker initialization not successful, " + str;
            C0756b.m1234(3, "WebAdTracker", this, str2);
            C0756b.m1232("[ERROR] ", str2);
            this.ˋ = new C0785o(str);
        }
    }

    C0806x(@Nullable WebView webView) {
        super(webView, false, false);
        C0756b.m1234(3, "WebAdTracker", this, "Initializing.");
        if (webView == null) {
            String str = "WebView is null";
            String str2 = "WebAdTracker initialization not successful, " + str;
            C0756b.m1234(3, "WebAdTracker", this, str2);
            C0756b.m1232("[ERROR] ", str2);
            this.ˋ = new C0785o(str);
            return;
        }
        try {
            super.m1244(webView);
            C0756b.m1232("[SUCCESS] ", "WebAdTracker created for " + m1241());
        } catch (C0785o e) {
            this.ˋ = e;
        }
    }

    /* renamed from: ˊ */
    final String mo1647() {
        return "WebAdTracker";
    }
}
