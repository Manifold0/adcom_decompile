package com.facebook.ads.internal.p033b;

import android.content.Context;
import android.view.View;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.C1911n;
import com.facebook.ads.internal.adapters.C1912f;
import com.facebook.ads.internal.p043m.C2044a;
import com.facebook.ads.internal.p043m.C2046c;
import com.facebook.ads.internal.protocol.C2065a;
import com.facebook.ads.p018a.C1800a;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.b.d */
public class C1952d extends C1946c {

    /* renamed from: com.facebook.ads.internal.b.d$1 */
    class C19511 implements C1800a {
        /* renamed from: a */
        final /* synthetic */ C1952d f4282a;

        C19511(C1952d c1952d) {
            this.f4282a = c1952d;
        }

        /* renamed from: a */
        public void mo5343a(C1911n c1911n) {
            this.f4282a.f = c1911n;
            this.f4282a.a = false;
            this.f4282a.c.mo5325a((AdAdapter) c1911n);
        }

        /* renamed from: a */
        public void mo5344a(C1911n c1911n, View view) {
            this.f4282a.c.mo5324a(view);
        }

        /* renamed from: a */
        public void mo5345a(C1911n c1911n, AdError adError) {
            this.f4282a.c.mo5326a(new C2065a(adError.getErrorCode(), adError.getErrorMessage()));
        }

        /* renamed from: b */
        public void mo5346b(C1911n c1911n) {
            this.f4282a.c.mo5323a();
        }

        /* renamed from: c */
        public void mo5347c(C1911n c1911n) {
            this.f4282a.c.mo5327b();
        }

        /* renamed from: d */
        public void mo5348d(C1911n c1911n) {
            this.f4282a.c.mo5342c();
        }
    }

    public C1952d(Context context, C1943a c1943a) {
        super(context, c1943a);
    }

    /* renamed from: a */
    protected void mo5419a() {
        ((C1911n) this.f).mo5397e();
    }

    /* renamed from: a */
    protected void mo5420a(AdAdapter adAdapter, C2046c c2046c, C2044a c2044a, Map<String, Object> map) {
        ((C1912f) adAdapter).m4428a(this.b, new C19511(this), (Map) map, this.g, this.h.f4250d);
    }

    /* renamed from: a */
    protected void mo5421a(String str) {
        C2065a a = C1954e.m4637a(this.b, Integer.valueOf(0));
        if (a != null) {
            mo5379a(a);
        } else {
            super.mo5421a(str);
        }
    }
}
