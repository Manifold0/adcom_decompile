package com.moat.analytics.mobile.tjy;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.tjy.base.asserts.C2746a;
import com.moat.analytics.mobile.tjy.base.exception.C2747a;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.moat.analytics.mobile.tjy.v */
class C2767v extends MoatFactory {
    /* renamed from: c */
    private static final AtomicReference f6739c = new AtomicReference();
    /* renamed from: a */
    private final bl f6740a = new bm();
    /* renamed from: b */
    private final C2742a f6741b;

    C2767v(Activity activity) {
        if (f6739c.get() == null) {
            Object asVar;
            an anVar = new an();
            try {
                asVar = new as(ab.instance);
            } catch (Exception e) {
                C2747a.m6882a(e);
                an anVar2 = anVar;
            }
            f6739c.compareAndSet(null, asVar);
        }
        this.f6741b = new C2751c(activity, (ap) f6739c.get());
        this.f6741b.mo6126b();
    }

    /* renamed from: a */
    private NativeDisplayTracker m6956a(View view, String str) {
        C2746a.m6881a(view);
        ap apVar = (ap) f6739c.get();
        return (NativeDisplayTracker) ay.m6867a(apVar, new C2770y(this, new WeakReference(view), apVar, str), new ag());
    }

    /* renamed from: a */
    private NativeVideoTracker m6957a(String str) {
        ap apVar = (ap) f6739c.get();
        return (NativeVideoTracker) ay.m6867a(apVar, new C2771z(this, apVar, str), new ai());
    }

    /* renamed from: a */
    private WebAdTracker m6958a(ViewGroup viewGroup) {
        C2746a.m6881a(viewGroup);
        ap apVar = (ap) f6739c.get();
        return (WebAdTracker) ay.m6867a(apVar, new C2769x(this, new WeakReference(viewGroup), apVar), new bk());
    }

    /* renamed from: a */
    private WebAdTracker m6959a(WebView webView) {
        C2746a.m6881a(webView);
        ap apVar = (ap) f6739c.get();
        return (WebAdTracker) ay.m6867a(apVar, new C2768w(this, new WeakReference(webView), apVar), new bk());
    }

    /* renamed from: a */
    public Object m6962a(ac acVar) {
        return acVar.mo6079a(this.f6741b, (ap) f6739c.get());
    }

    public Object createCustomTracker(ac acVar) {
        try {
            return m6962a(acVar);
        } catch (Exception e) {
            C2747a.m6882a(e);
            return acVar.mo6078a();
        }
    }

    public NativeDisplayTracker createNativeDisplayTracker(View view, String str) {
        try {
            return m6956a(view, str);
        } catch (Exception e) {
            C2747a.m6882a(e);
            return new al();
        }
    }

    public NativeVideoTracker createNativeVideoTracker(String str) {
        try {
            return m6957a(str);
        } catch (Exception e) {
            C2747a.m6882a(e);
            return new am();
        }
    }

    public WebAdTracker createWebAdTracker(ViewGroup viewGroup) {
        try {
            return m6958a(viewGroup);
        } catch (Exception e) {
            C2747a.m6882a(e);
            return new ao();
        }
    }

    public WebAdTracker createWebAdTracker(WebView webView) {
        try {
            return m6959a(webView);
        } catch (Exception e) {
            C2747a.m6882a(e);
            return new ao();
        }
    }

    public WebAdTracker createWebDisplayTracker(ViewGroup viewGroup) {
        return createWebAdTracker(viewGroup);
    }

    public WebAdTracker createWebDisplayTracker(WebView webView) {
        return createWebAdTracker(webView);
    }
}
