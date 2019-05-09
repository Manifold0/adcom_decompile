package com.facebook.ads.internal.view.p061e.p062a;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.facebook.ads.internal.p024h.C2011b;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p025w.p026b.C2598w;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.p029x.C2630a.C1858a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.component.p058a.p059a.C2260b;
import com.facebook.ads.internal.view.component.p058a.p059a.C2260b.C2267a;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.view.e.a.f */
class C2327f extends ViewHolder {
    /* renamed from: a */
    private final C2260b f5573a;
    /* renamed from: b */
    private final SparseBooleanArray f5574b;
    /* renamed from: c */
    private final int f5575c;
    /* renamed from: d */
    private final int f5576d;
    /* renamed from: e */
    private final int f5577e;
    /* renamed from: f */
    private final int f5578f;
    @Nullable
    /* renamed from: g */
    private C2630a f5579g;
    /* renamed from: h */
    private C1858a f5580h;
    /* renamed from: i */
    private C2630a f5581i;

    C2327f(C2260b c2260b, SparseBooleanArray sparseBooleanArray, C2630a c2630a, int i, int i2, int i3, int i4) {
        super(c2260b);
        this.f5573a = c2260b;
        this.f5574b = sparseBooleanArray;
        this.f5581i = c2630a;
        this.f5575c = i;
        this.f5576d = i2;
        this.f5577e = i3;
        this.f5578f = i4;
    }

    /* renamed from: a */
    void m6009a(final C2316b c2316b, C2085c c2085c, C2011b c2011b, C2598w c2598w, String str) {
        int b = c2316b.m5972b();
        this.f5573a.setTag(-1593835536, Integer.valueOf(b));
        LayoutParams marginLayoutParams = new MarginLayoutParams(this.f5575c, -2);
        marginLayoutParams.setMargins(b == 0 ? this.f5576d : this.f5577e, 0, b >= this.f5578f + -1 ? this.f5576d : this.f5577e, 0);
        String g = c2316b.m5973c().m4317c().m4245g();
        String a = c2316b.m5973c().m4317c().m4238a();
        this.f5573a.setIsVideo(!TextUtils.isEmpty(a));
        if (this.f5573a.m5801f()) {
            this.f5573a.setVideoPlaceholderUrl(g);
            C2260b c2260b = this.f5573a;
            String str2 = "";
            if (!(c2011b == null || a == null)) {
                str2 = c2011b.m4846c(a);
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = a;
            }
            c2260b.setVideoUrl(str2);
        } else {
            this.f5573a.setImageUrl(g);
        }
        this.f5573a.setLayoutParams(marginLayoutParams);
        this.f5573a.m5797a(c2316b.m5973c().m4315a().m4265a(), c2316b.m5973c().m4315a().m4267c());
        this.f5573a.m5796a(c2316b.m5973c().m4316b(), c2316b.m5971a());
        this.f5573a.m5798a(c2316b.m5971a());
        if (!this.f5574b.get(c2316b.m5972b())) {
            if (this.f5579g != null) {
                this.f5579g.m6774c();
                this.f5579g = null;
            }
            final Map a2 = c2316b.m5971a();
            final String str3 = str;
            final C2316b c2316b2 = c2316b;
            final C2598w c2598w2 = c2598w;
            final C2085c c2085c2 = c2085c;
            this.f5580h = new C1858a(this) {
                /* renamed from: f */
                final /* synthetic */ C2327f f5570f;

                /* renamed from: a */
                public void mo5381a() {
                    if (!this.f5570f.f5581i.m6773b() && !TextUtils.isEmpty(str3) && !this.f5570f.f5574b.get(c2316b2.m5972b())) {
                        if (this.f5570f.f5579g != null) {
                            this.f5570f.f5579g.m6771a(a2);
                        }
                        a2.put("touch", C2581k.m6645a(c2598w2.m6676e()));
                        c2085c2.mo5470a(str3, a2);
                        this.f5570f.f5574b.put(c2316b2.m5972b(), true);
                    }
                }
            };
            this.f5579g = new C2630a(this.f5573a, 10, this.f5580h);
            this.f5579g.m6770a(100);
            this.f5579g.m6772b(100);
            this.f5573a.setOnAssetsLoadedListener(new C2267a(this) {
                /* renamed from: b */
                final /* synthetic */ C2327f f5572b;

                /* renamed from: a */
                public void mo5582a() {
                    if (c2316b.m5972b() == 0) {
                        this.f5572b.f5581i.m6769a();
                    }
                    this.f5572b.f5579g.m6769a();
                }
            });
        }
    }
}
