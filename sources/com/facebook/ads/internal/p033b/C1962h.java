package com.facebook.ads.internal.p033b;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.AdError;
import com.facebook.ads.RewardData;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.C1930s;
import com.facebook.ads.internal.adapters.C1931k;
import com.facebook.ads.internal.adapters.C1940t;
import com.facebook.ads.internal.p043m.C2044a;
import com.facebook.ads.internal.p043m.C2046c;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.C2065a;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.b.h */
public class C1962h extends C1946c {

    /* renamed from: com.facebook.ads.internal.b.h$1 */
    class C19611 implements C1940t {
        /* renamed from: a */
        final /* synthetic */ C1962h f4299a;

        C19611(C1962h c1962h) {
            this.f4299a = c1962h;
        }

        /* renamed from: a */
        public void mo5430a() {
            this.f4299a.c.mo5451h();
        }

        /* renamed from: a */
        public void mo5431a(C1930s c1930s) {
            this.f4299a.f = c1930s;
            this.f4299a.c.mo5325a((AdAdapter) c1930s);
        }

        /* renamed from: a */
        public void mo5432a(C1930s c1930s, AdError adError) {
            this.f4299a.c.mo5326a(new C2065a(AdErrorType.INTERNAL_ERROR, null));
            this.f4299a.m4607a((AdAdapter) c1930s);
            this.f4299a.m4622i();
        }

        /* renamed from: b */
        public void mo5433b() {
            this.f4299a.c.mo5454k();
        }

        /* renamed from: b */
        public void mo5434b(C1930s c1930s) {
            this.f4299a.c.mo5323a();
        }

        /* renamed from: c */
        public void mo5435c(C1930s c1930s) {
            this.f4299a.c.mo5327b();
        }

        /* renamed from: d */
        public void mo5436d(C1930s c1930s) {
            this.f4299a.c.mo5450g();
        }

        /* renamed from: e */
        public void mo5437e(C1930s c1930s) {
            this.f4299a.c.mo5452i();
        }

        /* renamed from: f */
        public void mo5438f(C1930s c1930s) {
            this.f4299a.c.mo5453j();
        }
    }

    public C1962h(Context context, C1943a c1943a) {
        super(context, c1943a);
    }

    /* renamed from: a */
    protected void mo5419a() {
        C1930s c1930s = (C1930s) this.f;
        c1930s.m4535a(this.h.f4253g);
        c1930s.mo5413b();
    }

    /* renamed from: a */
    public void m4660a(RewardData rewardData) {
        if (this.f == null) {
            throw new IllegalStateException("no adapter ready to set reward on");
        } else if (this.f.getPlacementType() != AdPlacementType.REWARDED_VIDEO) {
            throw new IllegalStateException("can only set on rewarded video ads");
        } else {
            ((C1930s) this.f).m4536a(rewardData);
        }
    }

    /* renamed from: a */
    protected void mo5420a(AdAdapter adAdapter, C2046c c2046c, C2044a c2044a, Map<String, Object> map) {
        ((C1931k) adAdapter).m4546a(this.b, new C19611(this), map, this.h.f4252f, this.h.f4251e);
    }

    @Nullable
    /* renamed from: c */
    C2065a mo5429c() {
        return (!this.h.f4252f || m4617d()) ? null : new C2065a(AdErrorType.CLEAR_TEXT_SUPPORT_NOT_ALLOWED, "");
    }
}
