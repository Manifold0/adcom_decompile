package com.moat.analytics.mobile.cha;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.webkit.WebView;

/* renamed from: com.moat.analytics.mobile.cha.v */
final class C1542v extends C1489d implements WebAdTracker {
    C1542v(@Nullable ViewGroup viewGroup) {
        this((WebView) C1544x.m3910(viewGroup, false).orElse(null));
        if (viewGroup == null) {
            String str = "Target ViewGroup is null";
            String str2 = "WebAdTracker initialization not successful, " + str;
            C1487a.m3715(3, "WebAdTracker", this, str2);
            C1487a.m3712("[ERROR] ", str2);
            this.ॱ = new C1518o(str);
        }
        if (this.ˏ == null) {
            str = "No WebView to track inside of ad container";
            str2 = "WebAdTracker initialization not successful, " + str;
            C1487a.m3715(3, "WebAdTracker", this, str2);
            C1487a.m3712("[ERROR] ", str2);
            this.ॱ = new C1518o(str);
        }
    }

    C1542v(@Nullable WebView webView) {
        super(webView, false, false);
        C1487a.m3715(3, "WebAdTracker", this, "Initializing.");
        if (webView == null) {
            String str = "WebView is null";
            String str2 = "WebAdTracker initialization not successful, " + str;
            C1487a.m3715(3, "WebAdTracker", this, str2);
            C1487a.m3712("[ERROR] ", str2);
            this.ॱ = new C1518o(str);
            return;
        }
        try {
            super.m3730(webView);
            C1487a.m3712("[SUCCESS] ", "WebAdTracker created for " + m3721());
        } catch (C1518o e) {
            this.ॱ = e;
        }
    }

    /* renamed from: ˋ */
    final String mo4374() {
        return "WebAdTracker";
    }
}
