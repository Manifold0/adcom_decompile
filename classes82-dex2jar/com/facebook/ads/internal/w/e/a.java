// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.e;

import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.CookieManager;
import android.os.Build$VERSION;
import android.content.Context;
import android.webkit.WebView;

public abstract class a extends WebView
{
    private static final String a;
    private boolean b;
    
    static {
        a = a.class.getSimpleName();
    }
    
    public a(final Context context) {
        super(context);
        this.setWebChromeClient(this.a());
        this.setWebViewClient(this.b());
        final WebSettings settings = this.getSettings();
        Label_0108: {
            if (Build$VERSION.SDK_INT < 21) {
                break Label_0108;
            }
            settings.setMixedContentMode(0);
            while (true) {
                this.getSettings().setJavaScriptEnabled(true);
                this.getSettings().setDomStorageEnabled(true);
                if (Build$VERSION.SDK_INT >= 17) {
                    this.getSettings().setMediaPlaybackRequiresUserGesture(false);
                }
                this.setHorizontalScrollBarEnabled(false);
                this.setHorizontalScrollbarOverlay(false);
                this.setVerticalScrollBarEnabled(false);
                this.setVerticalScrollbarOverlay(false);
                if (Build$VERSION.SDK_INT < 21) {
                    return;
                }
                try {
                    CookieManager.getInstance().setAcceptThirdPartyCookies((WebView)this, true);
                    return;
                    try {
                        WebSettings.class.getMethod("setMixedContentMode", (Class<?>[])new Class[0]).invoke(settings, 0);
                    }
                    catch (Exception ex) {}
                    continue;
                }
                catch (Exception ex2) {
                    Log.w(com.facebook.ads.internal.w.e.a.a, "Failed to initialize CookieManager.");
                }
                break;
            }
        }
    }
    
    protected WebChromeClient a() {
        return new WebChromeClient();
    }
    
    protected WebViewClient b() {
        return new WebViewClient();
    }
    
    public boolean c() {
        return this.b;
    }
    
    public void destroy() {
        this.b = true;
        super.destroy();
    }
}
