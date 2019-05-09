package com.facebook.ads.internal.p025w.p057e;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* renamed from: com.facebook.ads.internal.w.e.a */
public abstract class C2238a extends WebView {
    /* renamed from: a */
    private static final String f5200a = C2238a.class.getSimpleName();
    /* renamed from: b */
    private boolean f5201b;

    public C2238a(Context context) {
        super(context);
        setWebChromeClient(mo5552a());
        setWebViewClient(mo5553b());
        WebSettings settings = getSettings();
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        } else {
            try {
                WebSettings.class.getMethod("setMixedContentMode", new Class[0]).invoke(settings, new Object[]{Integer.valueOf(0)});
            } catch (Exception e) {
            }
        }
        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);
        if (VERSION.SDK_INT >= 17) {
            getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        if (VERSION.SDK_INT >= 21) {
            try {
                CookieManager.getInstance().setAcceptThirdPartyCookies(this, true);
            } catch (Exception e2) {
                Log.w(f5200a, "Failed to initialize CookieManager.");
            }
        }
    }

    /* renamed from: a */
    protected WebChromeClient mo5552a() {
        return new WebChromeClient();
    }

    /* renamed from: b */
    protected WebViewClient mo5553b() {
        return new WebViewClient();
    }

    /* renamed from: c */
    public boolean m5725c() {
        return this.f5201b;
    }

    public void destroy() {
        this.f5201b = true;
        super.destroy();
    }
}
