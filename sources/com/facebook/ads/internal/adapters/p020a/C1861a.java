package com.facebook.ads.internal.adapters.p020a;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import com.facebook.ads.internal.p017t.C1783f;
import com.facebook.ads.internal.p017t.C2114e;
import com.facebook.ads.internal.p017t.C2115g;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p029x.C2630a.C1858a;
import com.facebook.ads.internal.view.C2331e;
import com.facebook.ads.internal.view.C2506j;
import com.facebook.ads.internal.view.p019c.C1802e;
import com.facebook.ads.internal.view.p019c.C2252d;
import java.util.List;

/* renamed from: com.facebook.ads.internal.adapters.a.a */
public abstract class C1861a extends Adapter<C2331e> {
    /* renamed from: c */
    private static final int f3891c = ((int) (4.0f * C2600x.f6420b));
    /* renamed from: a */
    final List<C2114e> f3892a;
    /* renamed from: b */
    private final int f3893b;
    @Nullable
    /* renamed from: d */
    private C1804a f3894d;
    /* renamed from: e */
    private final C1858a f3895e = new C18591(this);

    /* renamed from: com.facebook.ads.internal.adapters.a.a$a */
    public interface C1804a {
        /* renamed from: a */
        void mo5350a();
    }

    /* renamed from: com.facebook.ads.internal.adapters.a.a$1 */
    class C18591 extends C1858a {
        /* renamed from: a */
        final /* synthetic */ C1861a f3887a;

        C18591(C1861a c1861a) {
            this.f3887a = c1861a;
        }

        /* renamed from: a */
        public void mo5381a() {
            if (this.f3887a.f3894d != null) {
                this.f3887a.f3894d.mo5350a();
            }
        }
    }

    C1861a(C2506j c2506j, List<C2114e> list) {
        this.f3893b = c2506j.getChildSpacing();
        this.f3892a = list;
    }

    /* renamed from: a */
    void m4197a(ImageView imageView, final int i) {
        final C2114e c2114e = (C2114e) this.f3892a.get(i);
        C2115g j = c2114e.m5334j();
        if (j != null) {
            C2252d a = new C2252d(imageView).m5771a();
            a.m5773a(new C1802e(this) {
                /* renamed from: c */
                final /* synthetic */ C1861a f3890c;

                /* renamed from: a */
                public void mo5349a(boolean z) {
                    if (i == 0) {
                        c2114e.m5319a(this.f3890c.f3895e);
                    }
                    c2114e.m5321a(z, true);
                }
            });
            a.m5775a(j.m5352a());
        }
    }

    /* renamed from: a */
    public void m4198a(C1804a c1804a) {
        this.f3894d = c1804a;
    }

    /* renamed from: a */
    public void mo5382a(C2331e c2331e, int i) {
        C1783f a = c2331e.m6015a();
        LayoutParams marginLayoutParams = new MarginLayoutParams(-2, -1);
        marginLayoutParams.setMargins(i == 0 ? this.f3893b * 2 : this.f3893b, 0, i >= this.f3892a.size() + -1 ? this.f3893b * 2 : this.f3893b, 0);
        a.setLayoutParams(marginLayoutParams);
    }

    public int getItemCount() {
        return this.f3892a.size();
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        mo5382a((C2331e) viewHolder, i);
    }
}
