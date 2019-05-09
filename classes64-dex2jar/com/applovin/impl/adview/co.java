// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.net.Uri;
import android.webkit.WebView;
import java.lang.ref.WeakReference;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import android.webkit.WebViewClient;

public class co extends WebViewClient
{
    private final AppLovinSdk a;
    private final AppLovinLogger b;
    private WeakReference<cp> c;
    
    public co(final AppLovinSdk a) {
        this.a = a;
        this.b = a.getLogger();
    }
    
    void a(final WebView webView, final String s) {
        this.b.i("WebViewButtonClient", "Processing click on ad URL \"" + s + "\"");
        if (s != null && webView instanceof cn) {
            final cn cn = (cn)webView;
            final Uri parse = Uri.parse(s);
            final String scheme = parse.getScheme();
            final String host = parse.getHost();
            final String path = parse.getPath();
            final cp cp = this.c.get();
            if ("applovin".equalsIgnoreCase(scheme) && "com.applovin.sdk".equalsIgnoreCase(host) && cp != null) {
                if ("/track_click".equals(path)) {
                    cp.a(cn);
                }
                else {
                    if ("/close_ad".equals(path)) {
                        cp.b(cn);
                        return;
                    }
                    if ("/skip_ad".equals(path)) {
                        cp.c(cn);
                        return;
                    }
                    this.b.w("WebViewButtonClient", "Unknown URL: " + s);
                    this.b.w("WebViewButtonClient", "Path: " + path);
                }
            }
        }
    }
    
    public void a(final WeakReference<cp> c) {
        this.c = c;
    }
    
    public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
        this.a(webView, s);
        return true;
    }
}
