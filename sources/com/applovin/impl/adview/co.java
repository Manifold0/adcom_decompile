package com.applovin.impl.adview;

import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;

public class co extends WebViewClient {
    /* renamed from: a */
    private final AppLovinSdk f1875a;
    /* renamed from: b */
    private final AppLovinLogger f1876b;
    /* renamed from: c */
    private WeakReference<cp> f1877c;

    public co(AppLovinSdk appLovinSdk) {
        this.f1875a = appLovinSdk;
        this.f1876b = appLovinSdk.getLogger();
    }

    /* renamed from: a */
    void m2094a(WebView webView, String str) {
        this.f1876b.mo4175i("WebViewButtonClient", "Processing click on ad URL \"" + str + "\"");
        if (str != null && (webView instanceof cn)) {
            cn cnVar = (cn) webView;
            Uri parse = Uri.parse(str);
            String scheme = parse.getScheme();
            String host = parse.getHost();
            String path = parse.getPath();
            cp cpVar = (cp) this.f1877c.get();
            if (!AppLovinSdk.URI_SCHEME.equalsIgnoreCase(scheme) || !AppLovinSdk.URI_HOST.equalsIgnoreCase(host) || cpVar == null) {
                return;
            }
            if ("/track_click".equals(path)) {
                cpVar.mo4028a(cnVar);
            } else if ("/close_ad".equals(path)) {
                cpVar.mo4029b(cnVar);
            } else if ("/skip_ad".equals(path)) {
                cpVar.mo4030c(cnVar);
            } else {
                this.f1876b.mo4178w("WebViewButtonClient", "Unknown URL: " + str);
                this.f1876b.mo4178w("WebViewButtonClient", "Path: " + path);
            }
        }
    }

    /* renamed from: a */
    public void m2095a(WeakReference<cp> weakReference) {
        this.f1877c = weakReference;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        m2094a(webView, str);
        return true;
    }
}
