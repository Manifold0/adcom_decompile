// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.annotation.TargetApi;
import com.applovin.impl.sdk.ee;
import android.webkit.WebResourceRequest;
import android.content.Context;
import java.util.List;
import com.applovin.impl.sdk.AppLovinAdServiceImpl;
import android.webkit.WebView;
import android.view.ViewParent;
import com.applovin.impl.sdk.g;
import com.applovin.adview.AppLovinAdView;
import com.applovin.sdk.AppLovinAd;
import com.applovin.impl.a.f;
import com.applovin.impl.a.a;
import android.graphics.Color;
import com.applovin.impl.sdk.bv;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.sdk.AppLovinSdkUtils;
import android.net.Uri;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import android.webkit.WebViewClient;

class ah extends WebViewClient
{
    private final AppLovinSdk a;
    private final AppLovinLogger b;
    private final AdViewControllerImpl c;
    
    public ah(final AdViewControllerImpl c, final AppLovinSdk a) {
        this.a = a;
        this.b = a.getLogger();
        this.c = c;
    }
    
    private void a(final Uri uri, final n n) {
        Label_0207: {
            try {
                final String queryParameter = uri.getQueryParameter("n");
                if (!AppLovinSdkUtils.isValidString(queryParameter)) {
                    break Label_0207;
                }
                final String queryParameter2 = uri.getQueryParameter("load_type");
                if ("external".equalsIgnoreCase(queryParameter2)) {
                    this.b.d("AdWebViewClient", "Loading new page externally: " + queryParameter);
                    AppLovinSdkUtils.openUri(n.getContext(), Uri.parse(queryParameter), (AppLovinSdkImpl)this.a);
                    bv.c(this.c.getAdViewEventListener(), this.c.getCurrentAd(), this.c.getParentView(), this.a);
                    return;
                }
                if ("internal".equalsIgnoreCase(queryParameter2)) {
                    this.b.d("AdWebViewClient", "Loading new page in WebView: " + queryParameter);
                    n.loadUrl(queryParameter);
                    final String queryParameter3 = uri.getQueryParameter("bg_color");
                    if (AppLovinSdkUtils.isValidString(queryParameter3)) {
                        n.setBackgroundColor(Color.parseColor(queryParameter3));
                    }
                    return;
                }
            }
            catch (Throwable t) {
                this.b.e("AdWebViewClient", "Failed to load new page from query in original uri");
                return;
            }
            this.b.e("AdWebViewClient", "Could not find load type in original uri");
            return;
        }
        this.b.e("AdWebViewClient", "Could not find url to load from query in original uri");
    }
    
    private void a(final a a, final n n) {
        final f e = a.e();
        if (e != null) {
            com.applovin.impl.a.n.a(e.c(), (AppLovinSdkImpl)this.c.getSdk());
            this.a(n, e.a());
        }
    }
    
    private void a(final n n, final Uri uri) {
        final AppLovinAd a = n.a();
        final String b = n.b();
        final AppLovinAdView parentView = this.c.getParentView();
        if (parentView != null && a != null) {
            final g c = n.c();
            if (c != null) {
                c.b();
            }
            this.c.a(a, b, parentView, uri);
            return;
        }
        this.b.e("AdWebViewClient", "Attempting to track click that is null or not an ApplovinAdView instance for clickedUri = " + uri);
    }
    
    private void c(final n n) {
        this.c.expandAd();
    }
    
    private void d(final n n) {
        this.c.contractAd();
    }
    
    void a(final n n) {
        final ViewParent parent = n.getParent();
        if (parent instanceof AppLovinAdView) {
            ((AppLovinAdView)parent).loadNextAd();
        }
    }
    
    boolean a(final WebView webView, String s, final boolean b) {
        this.b.i("AdWebViewClient", "Processing click on ad URL \"" + s + "\"");
        if (s != null && webView instanceof n) {
            final Uri parse = Uri.parse(s);
            final n n = (n)webView;
            final String scheme = parse.getScheme();
            final String host = parse.getHost();
            final String path = parse.getPath();
            final AppLovinAd currentAd = this.c.getCurrentAd();
            if ("applovin".equals(scheme) && "com.applovin.sdk".equals(host)) {
                if ("/adservice/next_ad".equals(path)) {
                    this.a(n);
                }
                else if ("/adservice/close_ad".equals(path)) {
                    this.b(n);
                }
                else if ("/adservice/expand_ad".equals(path)) {
                    this.c(n);
                }
                else if ("/adservice/contract_ad".equals(path)) {
                    this.d(n);
                }
                else {
                    if (AppLovinAdServiceImpl.URI_NO_OP.equals(path)) {
                        return true;
                    }
                    if (AppLovinAdServiceImpl.URI_LOAD_URL.equals(path)) {
                        this.a(parse, n);
                    }
                    else if (AppLovinAdServiceImpl.URI_TRACK_CLICK_IMMEDIATELY.equals(path)) {
                        if (currentAd instanceof a) {
                            this.a((a)currentAd, n);
                        }
                        else {
                            this.a(n, Uri.parse(AppLovinAdServiceImpl.URI_TRACK_CLICK_IMMEDIATELY));
                        }
                    }
                    else if (path != null && path.startsWith("/launch/")) {
                        final List pathSegments = parse.getPathSegments();
                        if (pathSegments != null && pathSegments.size() > 1) {
                            s = pathSegments.get(pathSegments.size() - 1);
                            try {
                                final Context context = webView.getContext();
                                context.startActivity(context.getPackageManager().getLaunchIntentForPackage(s));
                                this.a(n, null);
                            }
                            catch (Exception ex) {
                                this.b.e("AdWebViewClient", "Threw Exception Trying to Launch App for Package: " + s, ex);
                            }
                        }
                    }
                    else {
                        this.b.w("AdWebViewClient", "Unknown URL: " + s);
                        this.b.w("AdWebViewClient", "Path: " + path);
                    }
                }
            }
            else {
                if (!b) {
                    return false;
                }
                if (currentAd instanceof a && ((a)currentAd).i()) {
                    this.a((a)currentAd, n);
                }
                else {
                    this.a(n, parse);
                }
            }
        }
        return true;
    }
    
    void b(final n n) {
        this.c.a();
    }
    
    public void onPageFinished(final WebView webView, final String s) {
        super.onPageFinished(webView, s);
        this.c.onAdHtmlLoaded(webView);
    }
    
    @TargetApi(24)
    public boolean shouldOverrideUrlLoading(final WebView webView, final WebResourceRequest webResourceRequest) {
        final boolean b = !new ee(this.a).aj() || webResourceRequest.hasGesture();
        final Uri url = webResourceRequest.getUrl();
        if (url != null) {
            return this.a(webView, url.toString(), b);
        }
        this.b.e("AdWebViewClient", "No url found for request");
        return false;
    }
    
    public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
        return this.a(webView, s, true);
    }
}
