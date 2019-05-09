package com.facebook.ads.internal.p033b;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.C1837q;
import com.facebook.ads.internal.adapters.C1924i;
import com.facebook.ads.internal.p017t.C2104d;
import com.facebook.ads.internal.p025w.p057e.C2613e;
import com.facebook.ads.internal.p043m.C2044a;
import com.facebook.ads.internal.p043m.C2046c;
import com.facebook.ads.internal.p043m.C2048e;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.C2065a;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.b.g */
public class C1960g extends C1946c {
    public C1960g(Context context, C1943a c1943a) {
        super(context, c1943a);
    }

    /* renamed from: a */
    static /* synthetic */ Map m4644a(C1960g c1960g, long j) {
        Map hashMap = new HashMap();
        hashMap.put("delay", String.valueOf(System.currentTimeMillis() - j));
        return hashMap;
    }

    /* renamed from: a */
    static /* synthetic */ void m4645a(C1960g c1960g, List list, Map map) {
        if (list != null && !list.isEmpty()) {
            for (String str : list) {
                new C2613e(c1960g.b, map).execute(new String[]{str});
            }
        }
    }

    /* renamed from: a */
    protected void mo5419a() {
        C1924i c1924i = (C1924i) this.f;
        if (c1924i.m4489A()) {
            this.c.mo5501a(c1924i);
            return;
        }
        throw new IllegalStateException("ad is not ready or already displayed");
    }

    /* renamed from: a */
    protected void mo5420a(AdAdapter adAdapter, C2046c c2046c, C2044a c2044a, Map<String, Object> map) {
        final C1924i c1924i = (C1924i) adAdapter;
        final long currentTimeMillis = System.currentTimeMillis();
        final C2044a c2044a2 = c2044a;
        Runnable c19581 = new Runnable(this) {
            /* renamed from: d */
            final /* synthetic */ C1960g f4291d;

            public void run() {
                this.f4291d.m4607a(c1924i);
                Map a = C1960g.m4644a(this.f4291d, currentTimeMillis);
                a.put("error", "-1");
                a.put(NotificationCompat.CATEGORY_MESSAGE, "timeout");
                C1960g.m4645a(this.f4291d, c2044a2.m4964a(C2048e.REQUEST), a);
                this.f4291d.m4622i();
            }
        };
        m4623j().postDelayed(c19581, (long) c2046c.m4970a().m4989j());
        final Runnable runnable = c19581;
        final long j = currentTimeMillis;
        final C2044a c2044a3 = c2044a;
        c1924i.m4494a(this.b, new C1837q(this) {
            /* renamed from: a */
            boolean f4292a = false;
            /* renamed from: b */
            boolean f4293b = false;
            /* renamed from: c */
            boolean f4294c = false;
            /* renamed from: g */
            final /* synthetic */ C1960g f4298g;

            /* renamed from: a */
            public void mo5372a(C1924i c1924i) {
                if (c1924i == this.f4298g.e) {
                    this.f4298g.m4623j().removeCallbacks(runnable);
                    this.f4298g.f = c1924i;
                    this.f4298g.c.mo5325a((AdAdapter) c1924i);
                    if (!this.f4292a) {
                        this.f4292a = true;
                        C1960g.m4645a(this.f4298g, c2044a3.m4964a(C2048e.REQUEST), C1960g.m4644a(this.f4298g, j));
                    }
                }
            }

            /* renamed from: a */
            public void mo5373a(C1924i c1924i, C2065a c2065a) {
                if (c1924i == this.f4298g.e) {
                    this.f4298g.m4623j().removeCallbacks(runnable);
                    this.f4298g.m4607a((AdAdapter) c1924i);
                    if (!this.f4292a) {
                        this.f4292a = true;
                        Map a = C1960g.m4644a(this.f4298g, j);
                        a.put("error", String.valueOf(c2065a.m5038a().getErrorCode()));
                        a.put(NotificationCompat.CATEGORY_MESSAGE, String.valueOf(c2065a.m5039b()));
                        C1960g.m4645a(this.f4298g, c2044a3.m4964a(C2048e.REQUEST), a);
                    }
                    this.f4298g.m4622i();
                }
            }

            /* renamed from: b */
            public void mo5374b(C1924i c1924i) {
                if (!this.f4293b) {
                    this.f4293b = true;
                    C1960g.m4645a(this.f4298g, c2044a3.m4964a(C2048e.IMPRESSION), null);
                }
            }

            /* renamed from: c */
            public void mo5375c(C1924i c1924i) {
                if (!this.f4294c) {
                    this.f4294c = true;
                    C1960g.m4645a(this.f4298g, c2044a3.m4964a(C2048e.CLICK), null);
                }
                if (this.f4298g.c != null) {
                    this.f4298g.c.mo5323a();
                }
            }
        }, this.g, map, NativeAdBase.getViewTraversalPredicate());
    }

    /* renamed from: a */
    protected void mo5421a(String str) {
        C2065a a = C1954e.m4637a(this.b, Integer.valueOf(0), Integer.valueOf(1));
        if (a != null) {
            mo5379a(a);
        } else {
            super.mo5421a(str);
        }
    }

    @Nullable
    /* renamed from: c */
    C2065a mo5429c() {
        return (this.h.f4254h == null || this.h.f4254h == C2104d.NONE || m4617d()) ? null : new C2065a(AdErrorType.CLEAR_TEXT_SUPPORT_NOT_ALLOWED, "");
    }
}
