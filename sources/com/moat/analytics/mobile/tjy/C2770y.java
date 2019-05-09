package com.moat.analytics.mobile.tjy;

import android.util.Log;
import android.view.View;
import com.moat.analytics.mobile.tjy.base.functional.C2749a;
import java.lang.ref.WeakReference;

/* renamed from: com.moat.analytics.mobile.tjy.y */
class C2770y implements ba {
    /* renamed from: a */
    final /* synthetic */ WeakReference f6748a;
    /* renamed from: b */
    final /* synthetic */ ap f6749b;
    /* renamed from: c */
    final /* synthetic */ String f6750c;
    /* renamed from: d */
    final /* synthetic */ C2767v f6751d;

    C2770y(C2767v c2767v, WeakReference weakReference, ap apVar, String str) {
        this.f6751d = c2767v;
        this.f6748a = weakReference;
        this.f6749b = apVar;
        this.f6750c = str;
    }

    /* renamed from: a */
    public C2749a mo6117a() {
        View view = (View) this.f6748a.get();
        if (view == null) {
            if (this.f6749b.mo6105b()) {
                Log.e("MoatFactory", "Target view is null. Not creating NativeDisplayTracker.");
            }
            return C2749a.m6883a();
        }
        if (this.f6749b.mo6105b()) {
            Log.d("MoatFactory", "Creating NativeDisplayTracker for " + view.getClass().getSimpleName() + "@" + view.hashCode());
        }
        return C2749a.m6884a(new af(view, this.f6750c, this.f6751d.f6741b, this.f6749b));
    }
}
