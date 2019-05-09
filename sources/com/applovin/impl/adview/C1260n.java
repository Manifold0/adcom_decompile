package com.applovin.impl.adview;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import com.applovin.impl.p016a.C1228a;
import com.applovin.impl.p016a.C1233f;
import com.applovin.impl.p016a.C1236i;
import com.applovin.impl.p016a.C1237j;
import com.applovin.impl.sdk.C1280g;
import com.applovin.impl.sdk.C1286m;
import com.applovin.impl.sdk.ab;
import com.applovin.impl.sdk.an;
import com.applovin.impl.sdk.ar;
import com.applovin.impl.sdk.ee;
import com.applovin.impl.sdk.gd;
import com.applovin.impl.sdk.ge;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.ads.AudienceNetworkActivity;

/* renamed from: com.applovin.impl.adview.n */
class C1260n extends WebView {
    /* renamed from: a */
    private final AppLovinLogger f1897a;
    /* renamed from: b */
    private final AppLovinSdk f1898b;
    /* renamed from: c */
    private C1280g f1899c;
    /* renamed from: d */
    private AppLovinAd f1900d = null;
    /* renamed from: e */
    private String f1901e = null;
    /* renamed from: f */
    private boolean f1902f = false;

    C1260n(ah ahVar, AppLovinSdk appLovinSdk, Context context) {
        super(context);
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        this.f1898b = appLovinSdk;
        this.f1897a = appLovinSdk.getLogger();
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setSupportMultipleWindows(false);
        settings.setJavaScriptEnabled(true);
        setWebViewClient(ahVar);
        setWebChromeClient(new C1259m(appLovinSdk));
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setScrollBarStyle(33554432);
        setOnTouchListener(new C1261o(this));
        setOnLongClickListener(new C1272z(this));
    }

    /* renamed from: a */
    private String m2108a(String str, String str2, String str3) {
        return AppLovinSdkUtils.isValidString(str) ? gd.m2940a(str3, str).replace("{SOURCE}", str2) : null;
    }

    /* renamed from: a */
    private void m2109a(an anVar) {
        try {
            if (new ee(this.f1898b).m2682I() || anVar.ah()) {
                m2110a(new aa(this));
            }
            if (ab.m2205e()) {
                m2110a(new ab(this, anVar));
            }
            if (ab.m2206f() && anVar.aj()) {
                m2110a(new ac(this));
            }
            ge ak = anVar.ak();
            if (ak != null) {
                WebSettings settings = getSettings();
                PluginState b = ak.m2959b();
                if (b != null) {
                    m2110a(new ad(this, settings, b));
                }
                Boolean c = ak.m2960c();
                if (c != null) {
                    m2110a(new ae(this, settings, c));
                }
                c = ak.m2961d();
                if (c != null) {
                    m2110a(new af(this, settings, c));
                }
                c = ak.m2962e();
                if (c != null) {
                    m2110a(new ag(this, settings, c));
                }
                c = ak.m2963f();
                if (c != null) {
                    m2110a(new C1262p(this, settings, c));
                }
                c = ak.m2964g();
                if (c != null) {
                    m2110a(new C1263q(this, settings, c));
                }
                c = ak.m2965h();
                if (c != null) {
                    m2110a(new C1264r(this, settings, c));
                }
                c = ak.m2966i();
                if (c != null) {
                    m2110a(new C1265s(this, settings, c));
                }
                c = ak.m2967j();
                if (c != null) {
                    m2110a(new C1266t(this, settings, c));
                }
                c = ak.m2968k();
                if (c != null) {
                    m2110a(new C1267u(this, settings, c));
                }
                if (ab.m2204d()) {
                    c = ak.m2969l();
                    if (c != null) {
                        m2110a(new C1268v(this, settings, c));
                    }
                    c = ak.m2970m();
                    if (c != null) {
                        m2110a(new C1269w(this, settings, c));
                    }
                }
                if (ab.m2207g()) {
                    Integer a = ak.m2958a();
                    if (a != null) {
                        m2110a(new C1270x(this, settings, a));
                    }
                }
                if (ab.m2208h()) {
                    Boolean n = ak.m2971n();
                    if (n != null) {
                        m2110a(new C1271y(this, settings, n));
                    }
                }
            }
        } catch (Throwable th) {
            this.f1897a.mo4174e("AdWebView", "Unable to apply WebView settings", th);
        }
    }

    /* renamed from: a */
    private void m2110a(Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable th) {
            this.f1897a.mo4174e("AdWebView", "Unable to apply WebView setting", th);
        }
    }

    /* renamed from: a */
    private void m2111a(String str, String str2, String str3, String str4, AppLovinSdk appLovinSdk) {
        String a = m2108a(str3, str, str4);
        if (AppLovinSdkUtils.isValidString(a)) {
            this.f1897a.mo4172d("AdWebView", "Rendering webview for VAST ad with resourceContents : " + a);
            loadDataWithBaseURL(str2, a, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, null, "");
            return;
        }
        a = m2108a(new ee(appLovinSdk).m2697X(), str, str4);
        if (AppLovinSdkUtils.isValidString(a)) {
            this.f1897a.mo4172d("AdWebView", "Rendering webview for VAST ad with resourceContents : " + a);
            loadDataWithBaseURL(str2, a, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, null, "");
            return;
        }
        this.f1897a.mo4172d("AdWebView", "Rendering webview for VAST ad with resourceURL : " + str);
        loadUrl(str);
    }

    /* renamed from: a */
    AppLovinAd m2112a() {
        return this.f1900d;
    }

    /* renamed from: a */
    public void m2113a(C1280g c1280g) {
        this.f1899c = c1280g;
    }

    /* renamed from: a */
    public void m2114a(AppLovinAd appLovinAd, String str) {
        if (this.f1902f) {
            this.f1897a.userError("AdWebView", "Ad can not be loaded in a destroyed webview");
            return;
        }
        this.f1900d = appLovinAd;
        this.f1901e = str;
        try {
            if (appLovinAd instanceof ar) {
                loadDataWithBaseURL("/", ((ar) appLovinAd).m2281a(), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, null, "");
                this.f1897a.mo4172d("AdWebView", "Empty ad rendered");
                return;
            }
            an anVar = (an) appLovinAd;
            m2109a(anVar);
            if (anVar.aa()) {
                setVisibility(0);
            }
            if (appLovinAd instanceof C1286m) {
                loadDataWithBaseURL(anVar.ai(), gd.m2940a(str, ((C1286m) appLovinAd).mo4000a()), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, null, "");
                this.f1897a.mo4172d("AdWebView", "AppLovinAd rendered");
            } else if (appLovinAd instanceof C1228a) {
                C1228a c1228a = (C1228a) appLovinAd;
                C1233f e = c1228a.m1844e();
                if (e != null) {
                    C1236i b = e.m1877b();
                    Uri b2 = b.m1894b();
                    String uri = b2 != null ? b2.toString() : "";
                    String c = b.m1895c();
                    String j = c1228a.m1849j();
                    ee eeVar = new ee(this.f1898b);
                    if (!AppLovinSdkUtils.isValidString(uri) && !AppLovinSdkUtils.isValidString(c)) {
                        this.f1897a.mo4173e("AdWebView", "Unable to load companion ad. No resources provided.");
                        return;
                    } else if (b.m1891a() == C1237j.STATIC) {
                        this.f1897a.mo4172d("AdWebView", "Rendering WebView for static VAST ad");
                        loadDataWithBaseURL(anVar.ai(), m2108a(eeVar.m2696W(), uri, str), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, null, "");
                        return;
                    } else if (b.m1891a() == C1237j.HTML) {
                        if (AppLovinSdkUtils.isValidString(c)) {
                            r3 = m2108a(j, c, str);
                            if (!AppLovinSdkUtils.isValidString(r3)) {
                                r3 = c;
                            }
                            this.f1897a.mo4172d("AdWebView", "Rendering WebView for HTML VAST ad with resourceContents: " + r3);
                            loadDataWithBaseURL(anVar.ai(), r3, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, null, "");
                            return;
                        } else if (AppLovinSdkUtils.isValidString(uri)) {
                            this.f1897a.mo4172d("AdWebView", "Preparing to load HTML VAST ad resourceUri");
                            m2111a(uri, anVar.ai(), j, str, this.f1898b);
                            return;
                        } else {
                            return;
                        }
                    } else if (b.m1891a() != C1237j.IFRAME) {
                        this.f1897a.mo4173e("AdWebView", "Failed to render VAST companion ad of invalid type");
                        return;
                    } else if (AppLovinSdkUtils.isValidString(uri)) {
                        this.f1897a.mo4172d("AdWebView", "Preparing to load iFrame VAST ad resourceUri");
                        m2111a(uri, anVar.ai(), j, str, this.f1898b);
                        return;
                    } else if (AppLovinSdkUtils.isValidString(c)) {
                        r3 = m2108a(j, c, str);
                        if (!AppLovinSdkUtils.isValidString(r3)) {
                            r3 = c;
                        }
                        this.f1897a.mo4172d("AdWebView", "Rendering WebView for iFrame VAST ad with resourceContents: " + r3);
                        loadDataWithBaseURL(anVar.ai(), r3, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, null, "");
                        return;
                    } else {
                        return;
                    }
                }
                this.f1897a.mo4172d("AdWebView", "No companion ad provided.");
            }
        } catch (Throwable th) {
            this.f1897a.mo4174e("AdWebView", "Unable to render AppLovinAd with placement = \"" + str + "\"", th);
        }
    }

    /* renamed from: a */
    public void m2115a(String str) {
        m2116a(str, null);
    }

    /* renamed from: a */
    public void m2116a(String str, Runnable runnable) {
        try {
            this.f1897a.mo4172d("AdWebView", "Forwarding \"" + str + "\" to ad template");
            loadUrl(str);
        } catch (Throwable th) {
            this.f1897a.mo4174e("AdWebView", "Unable to forward to template", th);
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    /* renamed from: b */
    String m2117b() {
        return this.f1901e;
    }

    /* renamed from: c */
    public C1280g m2118c() {
        return this.f1899c;
    }

    public void computeScroll() {
    }

    public void destroy() {
        this.f1902f = true;
        try {
            super.destroy();
            this.f1897a.mo4172d("AdWebView", "Web view destroyed");
        } catch (Throwable th) {
            if (this.f1897a != null) {
                this.f1897a.mo4174e("AdWebView", "destroy() threw exception", th);
            }
        }
    }

    protected void onFocusChanged(boolean z, int i, Rect rect) {
        try {
            super.onFocusChanged(z, i, rect);
        } catch (Throwable e) {
            this.f1897a.mo4174e("AdWebView", "onFocusChanged() threw exception", e);
        }
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
    }

    public void onWindowFocusChanged(boolean z) {
        try {
            super.onWindowFocusChanged(z);
        } catch (Throwable e) {
            this.f1897a.mo4174e("AdWebView", "onWindowFocusChanged() threw exception", e);
        }
    }

    protected void onWindowVisibilityChanged(int i) {
        try {
            super.onWindowVisibilityChanged(i);
        } catch (Throwable e) {
            this.f1897a.mo4174e("AdWebView", "onWindowVisibilityChanged() threw exception", e);
        }
    }

    public boolean requestFocus(int i, Rect rect) {
        try {
            return super.requestFocus(i, rect);
        } catch (Throwable e) {
            this.f1897a.mo4174e("AdWebView", "requestFocus() threw exception", e);
            return false;
        }
    }

    public void scrollTo(int i, int i2) {
    }
}
