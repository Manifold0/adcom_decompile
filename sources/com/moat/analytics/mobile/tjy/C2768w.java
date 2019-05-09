package com.moat.analytics.mobile.tjy;

import android.util.Log;
import android.webkit.WebView;
import com.moat.analytics.mobile.tjy.base.functional.C2749a;
import java.lang.ref.WeakReference;

/* renamed from: com.moat.analytics.mobile.tjy.w */
class C2768w implements ba {
    /* renamed from: a */
    final /* synthetic */ WeakReference f6742a;
    /* renamed from: b */
    final /* synthetic */ ap f6743b;
    /* renamed from: c */
    final /* synthetic */ C2767v f6744c;

    C2768w(C2767v c2767v, WeakReference weakReference, ap apVar) {
        this.f6744c = c2767v;
        this.f6742a = weakReference;
        this.f6743b = apVar;
    }

    /* renamed from: a */
    public C2749a mo6117a() {
        WebView webView = (WebView) this.f6742a.get();
        boolean b = this.f6743b.mo6105b();
        if (webView == null) {
            if (b) {
                Log.e("MoatFactory", "Target ViewGroup is null. Not creating WebAdTracker.");
            }
            return C2749a.m6883a();
        }
        if (b) {
            Log.d("MoatFactory", "Creating WebAdTracker for " + webView.getClass().getSimpleName() + "@" + webView.hashCode());
        }
        return C2749a.m6884a(new bj(webView, this.f6744c.f6741b, this.f6743b));
    }
}
