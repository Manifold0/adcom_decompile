package com.moat.analytics.mobile.tjy;

import android.util.Log;
import com.moat.analytics.mobile.tjy.base.functional.C2749a;

/* renamed from: com.moat.analytics.mobile.tjy.z */
class C2771z implements ba {
    /* renamed from: a */
    final /* synthetic */ ap f6752a;
    /* renamed from: b */
    final /* synthetic */ String f6753b;
    /* renamed from: c */
    final /* synthetic */ C2767v f6754c;

    C2771z(C2767v c2767v, ap apVar, String str) {
        this.f6754c = c2767v;
        this.f6752a = apVar;
        this.f6753b = str;
    }

    /* renamed from: a */
    public C2749a mo6117a() {
        if (this.f6752a.mo6105b()) {
            Log.d("MoatFactory", "Creating NativeVideo tracker.");
        }
        return C2749a.m6884a(new ah(this.f6753b, this.f6754c.f6741b, this.f6752a));
    }
}
