package com.moat.analytics.mobile.vng;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.vng.C0870v.C0867c;
import com.moat.analytics.mobile.vng.C0870v.C0868d;
import com.moat.analytics.mobile.vng.C0870v.C0869e;
import com.moat.analytics.mobile.vng.C0882x.C0815a;
import com.moat.analytics.mobile.vng.p013a.p014a.C0819a;
import com.moat.analytics.mobile.vng.p013a.p015b.C0820a;
import java.lang.ref.WeakReference;
import java.util.Map;

/* renamed from: com.moat.analytics.mobile.vng.n */
class C0854n extends MoatFactory {
    C0854n() {
        if (!m1553a()) {
            C0858p.m1577a(3, "Factory", (Object) this, "Failed to initialize MoatFactory. Please check that you've initialized the Moat SDK correctly.");
            C0858p.m1579a("[ERROR] ", "Failed to initialize MoatFactory, SDK was not started");
            throw new C0849m();
        }
    }

    /* renamed from: a */
    private NativeDisplayTracker m1548a(View view, final Map<String, String> map) {
        C0819a.m1431a(view);
        C0819a.m1431a(map);
        final WeakReference weakReference = new WeakReference(view);
        return (NativeDisplayTracker) C0882x.m1628a(new C0815a<NativeDisplayTracker>(this) {
            /* renamed from: c */
            final /* synthetic */ C0854n f1440c;

            /* renamed from: a */
            public C0820a<NativeDisplayTracker> mo1653a() {
                View view = (View) weakReference.get();
                if (view == null) {
                    C0858p.m1577a(3, "Factory", (Object) this, "Target view is null. Not creating NativeDisplayTracker.");
                    C0858p.m1579a("[ERROR] ", "NativeDisplayTracker creation failed, subject view is null");
                    return C0820a.m1432a();
                } else if (map == null || map.isEmpty()) {
                    C0858p.m1577a(3, "Factory", (Object) this, "adIds is null or empty. NativeDisplayTracker initialization failed.");
                    C0858p.m1579a("[ERROR] ", "NativeDisplayTracker creation failed, adIds is null or empty");
                    return C0820a.m1432a();
                } else {
                    C0858p.m1577a(3, "Factory", (Object) this, "Creating NativeDisplayTracker for " + view.getClass().getSimpleName() + "@" + view.hashCode());
                    C0858p.m1579a("[INFO] ", "Attempting to create NativeDisplayTracker for " + view.getClass().getSimpleName() + "@" + view.hashCode());
                    return C0820a.m1433a(new C0863t(view, map));
                }
            }
        }, NativeDisplayTracker.class);
    }

    /* renamed from: a */
    private NativeVideoTracker m1549a(final String str) {
        return (NativeVideoTracker) C0882x.m1628a(new C0815a<NativeVideoTracker>(this) {
            /* renamed from: b */
            final /* synthetic */ C0854n f1442b;

            /* renamed from: a */
            public C0820a<NativeVideoTracker> mo1653a() {
                if (str == null || str.isEmpty()) {
                    C0858p.m1577a(3, "Factory", (Object) this, "partnerCode is null or empty. NativeVideoTracker initialization failed.");
                    C0858p.m1579a("[ERROR] ", "NativeDisplayTracker creation failed, partnerCode is null or empty");
                    return C0820a.m1432a();
                }
                C0858p.m1577a(3, "Factory", (Object) this, "Creating NativeVideo tracker.");
                C0858p.m1579a("[INFO] ", "Attempting to create NativeVideoTracker");
                return C0820a.m1433a(new C0864u(str));
            }
        }, NativeVideoTracker.class);
    }

    /* renamed from: a */
    private WebAdTracker m1550a(ViewGroup viewGroup) {
        C0819a.m1431a(viewGroup);
        final WeakReference weakReference = new WeakReference(viewGroup);
        return (WebAdTracker) C0882x.m1628a(new C0815a<WebAdTracker>(this) {
            /* renamed from: b */
            final /* synthetic */ C0854n f1437b;

            /* renamed from: a */
            public C0820a<WebAdTracker> mo1653a() {
                ViewGroup viewGroup = (ViewGroup) weakReference.get();
                if (viewGroup == null) {
                    C0858p.m1577a(3, "Factory", (Object) this, "Target ViewGroup is null. Not creating WebAdTracker.");
                    C0858p.m1579a("[ERROR] ", "WebAdTracker not created, adContainer ViewGroup is null");
                    return C0820a.m1432a();
                }
                C0858p.m1577a(3, "Factory", (Object) this, "Creating WebAdTracker for " + viewGroup.getClass().getSimpleName() + "@" + viewGroup.hashCode());
                C0858p.m1579a("[INFO] ", "Attempting to create WebAdTracker for " + viewGroup.getClass().getSimpleName() + "@" + viewGroup.hashCode());
                C0820a a = ab.m1458a(viewGroup);
                boolean c = a.m1437c();
                C0858p.m1577a(3, "Factory", (Object) this, "WebView " + (c ? "" : "not ") + "found inside of ad container.");
                if (!c) {
                    C0858p.m1579a("[ERROR] ", "WebAdTracker not created, no WebView to track inside of ad container");
                }
                return C0820a.m1433a(new aa((WebView) a.m1436c(null)));
            }
        }, WebAdTracker.class);
    }

    /* renamed from: a */
    private WebAdTracker m1551a(WebView webView) {
        C0819a.m1431a(webView);
        final WeakReference weakReference = new WeakReference(webView);
        return (WebAdTracker) C0882x.m1628a(new C0815a<WebAdTracker>(this) {
            /* renamed from: b */
            final /* synthetic */ C0854n f1435b;

            /* renamed from: a */
            public C0820a<WebAdTracker> mo1653a() {
                WebView webView = (WebView) weakReference.get();
                if (webView == null) {
                    C0858p.m1577a(3, "Factory", (Object) this, "Target ViewGroup is null. Not creating WebAdTracker.");
                    C0858p.m1579a("[ERROR] ", "WebAdTracker not created, webView is null");
                    return C0820a.m1432a();
                }
                C0858p.m1577a(3, "Factory", (Object) this, "Creating WebAdTracker for " + webView.getClass().getSimpleName() + "@" + webView.hashCode());
                C0858p.m1579a("[INFO] ", "Attempting to create WebAdTracker for " + webView.getClass().getSimpleName() + "@" + webView.hashCode());
                return C0820a.m1433a(new aa(webView));
            }
        }, WebAdTracker.class);
    }

    /* renamed from: a */
    private <T> T m1552a(MoatPlugin<T> moatPlugin) {
        return moatPlugin.mo1660a();
    }

    /* renamed from: a */
    private boolean m1553a() {
        return ((C0847k) MoatAnalytics.getInstance()).m1532a();
    }

    public <T> T createCustomTracker(MoatPlugin<T> moatPlugin) {
        try {
            return m1552a((MoatPlugin) moatPlugin);
        } catch (Exception e) {
            C0849m.m1543a(e);
            return moatPlugin.mo1661b();
        }
    }

    public NativeDisplayTracker createNativeDisplayTracker(View view, Map<String, String> map) {
        try {
            return m1548a(view, map);
        } catch (Exception e) {
            C0849m.m1543a(e);
            return new C0867c();
        }
    }

    public NativeVideoTracker createNativeVideoTracker(String str) {
        try {
            return m1549a(str);
        } catch (Exception e) {
            C0849m.m1543a(e);
            return new C0868d();
        }
    }

    public WebAdTracker createWebAdTracker(ViewGroup viewGroup) {
        try {
            return m1550a(viewGroup);
        } catch (Exception e) {
            C0849m.m1543a(e);
            return new C0869e();
        }
    }

    public WebAdTracker createWebAdTracker(WebView webView) {
        try {
            return m1551a(webView);
        } catch (Exception e) {
            C0849m.m1543a(e);
            return new C0869e();
        }
    }
}
