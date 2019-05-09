// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.graphics.Rect;
import android.net.Uri;
import com.applovin.impl.a.i;
import com.applovin.impl.a.f;
import com.applovin.impl.a.j;
import com.applovin.impl.a.a;
import com.applovin.impl.sdk.ar;
import android.webkit.WebSettings$PluginState;
import com.applovin.impl.sdk.ge;
import com.applovin.impl.sdk.ab;
import com.applovin.impl.sdk.ee;
import com.applovin.impl.sdk.an;
import com.applovin.impl.sdk.gd;
import com.applovin.sdk.AppLovinSdkUtils;
import android.webkit.WebSettings;
import android.view.View$OnLongClickListener;
import android.view.View$OnTouchListener;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.content.Context;
import com.applovin.sdk.AppLovinAd;
import com.applovin.impl.sdk.g;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinLogger;
import android.webkit.WebView;

class n extends WebView
{
    private final AppLovinLogger a;
    private final AppLovinSdk b;
    private g c;
    private AppLovinAd d;
    private String e;
    private boolean f;
    
    n(final ah webViewClient, final AppLovinSdk b, final Context context) {
        super(context);
        this.d = null;
        this.e = null;
        this.f = false;
        if (b == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        this.b = b;
        this.a = b.getLogger();
        this.setBackgroundColor(0);
        final WebSettings settings = this.getSettings();
        settings.setSupportMultipleWindows(false);
        settings.setJavaScriptEnabled(true);
        this.setWebViewClient((WebViewClient)webViewClient);
        this.setWebChromeClient((WebChromeClient)new m(b));
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
        this.setScrollBarStyle(33554432);
        this.setOnTouchListener((View$OnTouchListener)new o(this));
        this.setOnLongClickListener((View$OnLongClickListener)new z(this));
    }
    
    private String a(final String s, final String s2, final String s3) {
        if (AppLovinSdkUtils.isValidString(s)) {
            return gd.a(s3, s).replace("{SOURCE}", s2);
        }
        return null;
    }
    
    private void a(final an an) {
        try {
            if (new ee(this.b).I() || an.ah()) {
                this.a(new aa(this));
            }
            if (ab.e()) {
                this.a(new com.applovin.impl.adview.ab(this, an));
            }
            if (ab.f() && an.aj()) {
                this.a(new ac(this));
            }
            final ge ak = an.ak();
            if (ak != null) {
                final WebSettings settings = this.getSettings();
                final WebSettings$PluginState b = ak.b();
                if (b != null) {
                    this.a(new ad(this, settings, b));
                }
                final Boolean c = ak.c();
                if (c != null) {
                    this.a(new ae(this, settings, c));
                }
                final Boolean d = ak.d();
                if (d != null) {
                    this.a(new af(this, settings, d));
                }
                final Boolean e = ak.e();
                if (e != null) {
                    this.a(new ag(this, settings, e));
                }
                final Boolean f = ak.f();
                if (f != null) {
                    this.a(new p(this, settings, f));
                }
                final Boolean g = ak.g();
                if (g != null) {
                    this.a(new q(this, settings, g));
                }
                final Boolean h = ak.h();
                if (h != null) {
                    this.a(new r(this, settings, h));
                }
                final Boolean i = ak.i();
                if (i != null) {
                    this.a(new s(this, settings, i));
                }
                final Boolean j = ak.j();
                if (j != null) {
                    this.a(new t(this, settings, j));
                }
                final Boolean k = ak.k();
                if (k != null) {
                    this.a(new u(this, settings, k));
                }
                if (ab.d()) {
                    final Boolean l = ak.l();
                    if (l != null) {
                        this.a(new v(this, settings, l));
                    }
                    final Boolean m = ak.m();
                    if (m != null) {
                        this.a(new w(this, settings, m));
                    }
                }
                if (ab.g()) {
                    final Integer a = ak.a();
                    if (a != null) {
                        this.a(new x(this, settings, a));
                    }
                }
                if (ab.h()) {
                    final Boolean n = ak.n();
                    if (n != null) {
                        this.a(new y(this, settings, n));
                    }
                }
            }
        }
        catch (Throwable t) {
            this.a.e("AdWebView", "Unable to apply WebView settings", t);
        }
    }
    
    private void a(final Runnable runnable) {
        try {
            runnable.run();
        }
        catch (Throwable t) {
            this.a.e("AdWebView", "Unable to apply WebView setting", t);
        }
    }
    
    private void a(final String s, final String s2, String s3, final String s4, final AppLovinSdk appLovinSdk) {
        s3 = this.a(s3, s, s4);
        if (AppLovinSdkUtils.isValidString(s3)) {
            this.a.d("AdWebView", "Rendering webview for VAST ad with resourceContents : " + s3);
            this.loadDataWithBaseURL(s2, s3, "text/html", (String)null, "");
            return;
        }
        s3 = this.a(new ee(appLovinSdk).X(), s, s4);
        if (AppLovinSdkUtils.isValidString(s3)) {
            this.a.d("AdWebView", "Rendering webview for VAST ad with resourceContents : " + s3);
            this.loadDataWithBaseURL(s2, s3, "text/html", (String)null, "");
            return;
        }
        this.a.d("AdWebView", "Rendering webview for VAST ad with resourceURL : " + s);
        this.loadUrl(s);
    }
    
    AppLovinAd a() {
        return this.d;
    }
    
    public void a(final g c) {
        this.c = c;
    }
    
    public void a(final AppLovinAd d, final String e) {
        if (!this.f) {
            this.d = d;
            this.e = e;
            an an;
            try {
                if (d instanceof ar) {
                    this.loadDataWithBaseURL("/", ((ar)d).a(), "text/html", (String)null, "");
                    this.a.d("AdWebView", "Empty ad rendered");
                    return;
                }
                an = (an)d;
                this.a(an);
                if (an.aa()) {
                    this.setVisibility(0);
                }
                if (d instanceof com.applovin.impl.sdk.m) {
                    this.loadDataWithBaseURL(an.ai(), gd.a(e, ((com.applovin.impl.sdk.m)d).a()), "text/html", (String)null, "");
                    this.a.d("AdWebView", "AppLovinAd rendered");
                    return;
                }
            }
            catch (Throwable t) {
                this.a.e("AdWebView", "Unable to render AppLovinAd with placement = \"" + e + "\"", t);
                return;
            }
            if (d instanceof a) {
                final a a = (a)d;
                final f e2 = a.e();
                if (e2 == null) {
                    this.a.d("AdWebView", "No companion ad provided.");
                    return;
                }
                final i b = e2.b();
                final Uri b2 = b.b();
                String string;
                if (b2 != null) {
                    string = b2.toString();
                }
                else {
                    string = "";
                }
                String c = b.c();
                final String j = a.j();
                final ee ee = new ee(this.b);
                if (!AppLovinSdkUtils.isValidString(string) && !AppLovinSdkUtils.isValidString(c)) {
                    this.a.e("AdWebView", "Unable to load companion ad. No resources provided.");
                    return;
                }
                if (b.a() == com.applovin.impl.a.j.b) {
                    this.a.d("AdWebView", "Rendering WebView for static VAST ad");
                    this.loadDataWithBaseURL(an.ai(), this.a(ee.W(), string, e), "text/html", (String)null, "");
                    return;
                }
                if (b.a() == com.applovin.impl.a.j.d) {
                    if (AppLovinSdkUtils.isValidString(c)) {
                        final String a2 = this.a(j, c, e);
                        if (AppLovinSdkUtils.isValidString(a2)) {
                            c = a2;
                        }
                        this.a.d("AdWebView", "Rendering WebView for HTML VAST ad with resourceContents: " + c);
                        this.loadDataWithBaseURL(an.ai(), c, "text/html", (String)null, "");
                        return;
                    }
                    if (AppLovinSdkUtils.isValidString(string)) {
                        this.a.d("AdWebView", "Preparing to load HTML VAST ad resourceUri");
                        this.a(string, an.ai(), j, e, this.b);
                    }
                }
                else {
                    if (b.a() != com.applovin.impl.a.j.c) {
                        this.a.e("AdWebView", "Failed to render VAST companion ad of invalid type");
                        return;
                    }
                    if (AppLovinSdkUtils.isValidString(string)) {
                        this.a.d("AdWebView", "Preparing to load iFrame VAST ad resourceUri");
                        this.a(string, an.ai(), j, e, this.b);
                        return;
                    }
                    if (AppLovinSdkUtils.isValidString(c)) {
                        final String a3 = this.a(j, c, e);
                        if (AppLovinSdkUtils.isValidString(a3)) {
                            c = a3;
                        }
                        this.a.d("AdWebView", "Rendering WebView for iFrame VAST ad with resourceContents: " + c);
                        this.loadDataWithBaseURL(an.ai(), c, "text/html", (String)null, "");
                    }
                }
            }
        }
        else {
            this.a.userError("AdWebView", "Ad can not be loaded in a destroyed webview");
        }
    }
    
    public void a(final String s) {
        this.a(s, null);
    }
    
    public void a(final String s, final Runnable runnable) {
        try {
            this.a.d("AdWebView", "Forwarding \"" + s + "\" to ad template");
            this.loadUrl(s);
        }
        catch (Throwable t) {
            this.a.e("AdWebView", "Unable to forward to template", t);
            if (runnable != null) {
                runnable.run();
            }
        }
    }
    
    String b() {
        return this.e;
    }
    
    public g c() {
        return this.c;
    }
    
    public void computeScroll() {
    }
    
    public void destroy() {
        this.f = true;
        try {
            super.destroy();
            this.a.d("AdWebView", "Web view destroyed");
        }
        catch (Throwable t) {
            if (this.a != null) {
                this.a.e("AdWebView", "destroy() threw exception", t);
            }
        }
    }
    
    protected void onFocusChanged(final boolean b, final int n, final Rect rect) {
        try {
            super.onFocusChanged(b, n, rect);
        }
        catch (Exception ex) {
            this.a.e("AdWebView", "onFocusChanged() threw exception", ex);
        }
    }
    
    protected void onScrollChanged(final int n, final int n2, final int n3, final int n4) {
    }
    
    public void onWindowFocusChanged(final boolean b) {
        try {
            super.onWindowFocusChanged(b);
        }
        catch (Exception ex) {
            this.a.e("AdWebView", "onWindowFocusChanged() threw exception", ex);
        }
    }
    
    protected void onWindowVisibilityChanged(final int n) {
        try {
            super.onWindowVisibilityChanged(n);
        }
        catch (Exception ex) {
            this.a.e("AdWebView", "onWindowVisibilityChanged() threw exception", ex);
        }
    }
    
    public boolean requestFocus(final int n, final Rect rect) {
        try {
            return super.requestFocus(n, rect);
        }
        catch (Exception ex) {
            this.a.e("AdWebView", "requestFocus() threw exception", ex);
            return false;
        }
    }
    
    public void scrollTo(final int n, final int n2) {
    }
}
