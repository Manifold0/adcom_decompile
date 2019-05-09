// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.e;

import android.webkit.WebView;
import java.util.Locale;
import android.text.TextUtils;
import com.facebook.ads.internal.settings.AdInternalSettings;

public class b
{
    public static String a() {
        final String urlPrefix = AdInternalSettings.getUrlPrefix();
        if (TextUtils.isEmpty((CharSequence)urlPrefix)) {
            return "https://www.facebook.com/";
        }
        return String.format(Locale.US, "https://www.%s.facebook.com", urlPrefix);
    }
    
    public static void a(final WebView webView) {
        webView.loadUrl("about:blank");
        webView.clearCache(true);
    }
}
