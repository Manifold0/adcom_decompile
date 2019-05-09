package com.facebook.ads.internal.p025w.p057e;

import android.text.TextUtils;
import android.webkit.WebView;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.Locale;

/* renamed from: com.facebook.ads.internal.w.e.b */
public class C2610b {
    /* renamed from: a */
    public static String m6707a() {
        if (TextUtils.isEmpty(AdInternalSettings.getUrlPrefix())) {
            return "https://www.facebook.com/";
        }
        return String.format(Locale.US, "https://www.%s.facebook.com", new Object[]{AdInternalSettings.getUrlPrefix()});
    }

    /* renamed from: a */
    public static void m6708a(WebView webView) {
        webView.loadUrl("about:blank");
        webView.clearCache(true);
    }
}
