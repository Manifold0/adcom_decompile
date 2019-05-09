package com.applovin.impl.adview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.ViewParent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.p016a.C1228a;
import com.applovin.impl.p016a.C1233f;
import com.applovin.impl.p016a.C1241n;
import com.applovin.impl.sdk.AppLovinAdServiceImpl;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.C1280g;
import com.applovin.impl.sdk.bv;
import com.applovin.impl.sdk.ee;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.kongregate.android.internal.browser.C0451a;
import com.tapjoy.TapjoyConstants;
import java.util.List;

class ah extends WebViewClient {
    /* renamed from: a */
    private final AppLovinSdk f1731a;
    /* renamed from: b */
    private final AppLovinLogger f1732b;
    /* renamed from: c */
    private final AdViewControllerImpl f1733c;

    public ah(AdViewControllerImpl adViewControllerImpl, AppLovinSdk appLovinSdk) {
        this.f1731a = appLovinSdk;
        this.f1732b = appLovinSdk.getLogger();
        this.f1733c = adViewControllerImpl;
    }

    /* renamed from: a */
    private void m1975a(Uri uri, C1260n c1260n) {
        try {
            String queryParameter = uri.getQueryParameter("n");
            if (AppLovinSdkUtils.isValidString(queryParameter)) {
                String queryParameter2 = uri.getQueryParameter("load_type");
                if (C0451a.f314d.equalsIgnoreCase(queryParameter2)) {
                    this.f1732b.mo4172d("AdWebViewClient", "Loading new page externally: " + queryParameter);
                    AppLovinSdkUtils.openUri(c1260n.getContext(), Uri.parse(queryParameter), (AppLovinSdkImpl) this.f1731a);
                    bv.m2411c(this.f1733c.getAdViewEventListener(), this.f1733c.getCurrentAd(), this.f1733c.getParentView(), this.f1731a);
                    return;
                } else if (TapjoyConstants.LOG_LEVEL_INTERNAL.equalsIgnoreCase(queryParameter2)) {
                    this.f1732b.mo4172d("AdWebViewClient", "Loading new page in WebView: " + queryParameter);
                    c1260n.loadUrl(queryParameter);
                    queryParameter = uri.getQueryParameter("bg_color");
                    if (AppLovinSdkUtils.isValidString(queryParameter)) {
                        c1260n.setBackgroundColor(Color.parseColor(queryParameter));
                        return;
                    }
                    return;
                } else {
                    this.f1732b.mo4173e("AdWebViewClient", "Could not find load type in original uri");
                    return;
                }
            }
            this.f1732b.mo4173e("AdWebViewClient", "Could not find url to load from query in original uri");
        } catch (Throwable th) {
            this.f1732b.mo4173e("AdWebViewClient", "Failed to load new page from query in original uri");
        }
    }

    /* renamed from: a */
    private void m1976a(C1228a c1228a, C1260n c1260n) {
        C1233f e = c1228a.m1844e();
        if (e != null) {
            C1241n.m1915a(e.m1878c(), (AppLovinSdkImpl) this.f1733c.getSdk());
            m1977a(c1260n, e.m1876a());
        }
    }

    /* renamed from: a */
    private void m1977a(C1260n c1260n, Uri uri) {
        AppLovinAd a = c1260n.m2112a();
        String b = c1260n.m2117b();
        AppLovinAdView parentView = this.f1733c.getParentView();
        if (parentView == null || a == null) {
            this.f1732b.mo4173e("AdWebViewClient", "Attempting to track click that is null or not an ApplovinAdView instance for clickedUri = " + uri);
            return;
        }
        C1280g c = c1260n.m2118c();
        if (c != null) {
            c.m2911b();
        }
        this.f1733c.m1974a(a, b, parentView, uri);
    }

    /* renamed from: c */
    private void m1978c(C1260n c1260n) {
        this.f1733c.expandAd();
    }

    /* renamed from: d */
    private void m1979d(C1260n c1260n) {
        this.f1733c.contractAd();
    }

    /* renamed from: a */
    void m1980a(C1260n c1260n) {
        ViewParent parent = c1260n.getParent();
        if (parent instanceof AppLovinAdView) {
            ((AppLovinAdView) parent).loadNextAd();
        }
    }

    /* renamed from: a */
    boolean m1981a(WebView webView, String str, boolean z) {
        this.f1732b.mo4175i("AdWebViewClient", "Processing click on ad URL \"" + str + "\"");
        if (str != null && (webView instanceof C1260n)) {
            Uri parse = Uri.parse(str);
            C1260n c1260n = (C1260n) webView;
            String scheme = parse.getScheme();
            String host = parse.getHost();
            String path = parse.getPath();
            AppLovinAd currentAd = this.f1733c.getCurrentAd();
            if (AppLovinSdk.URI_SCHEME.equals(scheme) && AppLovinSdk.URI_HOST.equals(host)) {
                if (AppLovinAdService.URI_NEXT_AD.equals(path)) {
                    m1980a(c1260n);
                } else if (AppLovinAdService.URI_CLOSE_AD.equals(path)) {
                    m1982b(c1260n);
                } else if (AppLovinAdService.URI_EXPAND_AD.equals(path)) {
                    m1978c(c1260n);
                } else if (AppLovinAdService.URI_CONTRACT_AD.equals(path)) {
                    m1979d(c1260n);
                } else if (AppLovinAdServiceImpl.URI_NO_OP.equals(path)) {
                    return true;
                } else {
                    if (AppLovinAdServiceImpl.URI_LOAD_URL.equals(path)) {
                        m1975a(parse, c1260n);
                    } else if (AppLovinAdServiceImpl.URI_TRACK_CLICK_IMMEDIATELY.equals(path)) {
                        if (currentAd instanceof C1228a) {
                            m1976a((C1228a) currentAd, c1260n);
                        } else {
                            m1977a(c1260n, Uri.parse(AppLovinAdServiceImpl.URI_TRACK_CLICK_IMMEDIATELY));
                        }
                    } else if (path == null || !path.startsWith("/launch/")) {
                        this.f1732b.mo4178w("AdWebViewClient", "Unknown URL: " + str);
                        this.f1732b.mo4178w("AdWebViewClient", "Path: " + path);
                    } else {
                        List pathSegments = parse.getPathSegments();
                        if (pathSegments != null && pathSegments.size() > 1) {
                            String str2 = (String) pathSegments.get(pathSegments.size() - 1);
                            try {
                                Context context = webView.getContext();
                                context.startActivity(context.getPackageManager().getLaunchIntentForPackage(str2));
                                m1977a(c1260n, null);
                            } catch (Throwable e) {
                                this.f1732b.mo4174e("AdWebViewClient", "Threw Exception Trying to Launch App for Package: " + str2, e);
                            }
                        }
                    }
                }
            } else if (!z) {
                return false;
            } else {
                if ((currentAd instanceof C1228a) && ((C1228a) currentAd).m1848i()) {
                    m1976a((C1228a) currentAd, c1260n);
                } else {
                    m1977a(c1260n, parse);
                }
            }
        }
        return true;
    }

    /* renamed from: b */
    void m1982b(C1260n c1260n) {
        this.f1733c.m1971a();
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.f1733c.onAdHtmlLoaded(webView);
    }

    @TargetApi(24)
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        boolean hasGesture = new ee(this.f1731a).aj() ? webResourceRequest.hasGesture() : true;
        Uri url = webResourceRequest.getUrl();
        if (url != null) {
            return m1981a(webView, url.toString(), hasGesture);
        }
        this.f1732b.mo4173e("AdWebViewClient", "No url found for request");
        return false;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return m1981a(webView, str, true);
    }
}
