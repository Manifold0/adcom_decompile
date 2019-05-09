package com.facebook.ads.internal.p033b;

import android.content.Context;
import android.view.View;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.BannerAdapterListener;
import com.facebook.ads.internal.adapters.C1902e;
import com.facebook.ads.internal.p043m.C2044a;
import com.facebook.ads.internal.p043m.C2046c;
import com.facebook.ads.internal.protocol.C2065a;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.b.b */
public class C1947b extends C1946c {
    public C1947b(Context context, C1943a c1943a) {
        super(context, c1943a);
    }

    /* renamed from: a */
    protected void mo5419a() {
        if (this.d != null) {
            this.c.mo5324a(this.d);
        }
    }

    /* renamed from: a */
    protected void mo5420a(AdAdapter adAdapter, C2046c c2046c, C2044a c2044a, Map<String, Object> map) {
        final C1902e c1902e = (C1902e) adAdapter;
        final Runnable c19441 = new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C1947b f4258b;

            public void run() {
                this.f4258b.m4607a(c1902e);
                this.f4258b.m4622i();
            }
        };
        m4623j().postDelayed(c19441, (long) c2046c.m4970a().m4989j());
        c1902e.m4402a(this.b, this.g, this.h.f4249c, new BannerAdapterListener(this) {
            /* renamed from: b */
            final /* synthetic */ C1947b f4260b;

            public void onBannerAdClicked(C1902e c1902e) {
                this.f4260b.c.mo5323a();
            }

            public void onBannerAdLoaded(C1902e c1902e, View view) {
                if (c1902e == this.f4260b.e) {
                    this.f4260b.m4623j().removeCallbacks(c19441);
                    AdAdapter adAdapter = this.f4260b.f;
                    this.f4260b.f = c1902e;
                    this.f4260b.d = view;
                    if (this.f4260b.a) {
                        this.f4260b.c.mo5324a(view);
                        this.f4260b.m4607a(adAdapter);
                        return;
                    }
                    this.f4260b.c.mo5325a((AdAdapter) c1902e);
                }
            }

            public void onBannerError(C1902e c1902e, AdError adError) {
                if (c1902e == this.f4260b.e) {
                    this.f4260b.m4623j().removeCallbacks(c19441);
                    this.f4260b.m4607a((AdAdapter) c1902e);
                    this.f4260b.m4622i();
                }
            }

            public void onBannerLoggingImpression(C1902e c1902e) {
                this.f4260b.c.mo5327b();
            }
        }, map);
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
