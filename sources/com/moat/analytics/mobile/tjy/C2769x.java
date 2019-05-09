package com.moat.analytics.mobile.tjy;

import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.tjy.base.functional.C2749a;
import java.lang.ref.WeakReference;

/* renamed from: com.moat.analytics.mobile.tjy.x */
class C2769x implements ba {
    /* renamed from: a */
    final /* synthetic */ WeakReference f6745a;
    /* renamed from: b */
    final /* synthetic */ ap f6746b;
    /* renamed from: c */
    final /* synthetic */ C2767v f6747c;

    C2769x(C2767v c2767v, WeakReference weakReference, ap apVar) {
        this.f6747c = c2767v;
        this.f6745a = weakReference;
        this.f6746b = apVar;
    }

    /* renamed from: a */
    public C2749a mo6117a() {
        ViewGroup viewGroup = (ViewGroup) this.f6745a.get();
        boolean b = this.f6746b.mo6105b();
        if (viewGroup == null) {
            if (b) {
                Log.e("MoatFactory", "Target ViewGroup is null. Not creating WebAdTracker.");
            }
            return C2749a.m6883a();
        }
        if (b) {
            Log.d("MoatFactory", "Creating WebAdTracker for " + viewGroup.getClass().getSimpleName() + "@" + viewGroup.hashCode());
        }
        C2749a a = this.f6747c.f6740a.mo6124a(viewGroup);
        boolean c = a.m6888c();
        if (b) {
            Log.e("MoatFactory", "WebView " + (c ? "" : "not ") + "found inside of ad container.");
        }
        return C2749a.m6884a(new bj((WebView) a.m6887c(null), this.f6747c.f6741b, this.f6746b));
    }
}
