package com.facebook.ads.internal.view.p061e.p062a;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseBooleanArray;
import android.view.ViewGroup;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.p024h.C2011b;
import com.facebook.ads.internal.p025w.p026b.C2598w;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.component.p058a.C2279e.C2278a;
import com.facebook.ads.internal.view.component.p058a.p059a.C2272c;
import java.util.List;

/* renamed from: com.facebook.ads.internal.view.e.a.c */
public class C2318c extends Adapter<C2327f> {
    /* renamed from: a */
    private final C2085c f5518a;
    @Nullable
    /* renamed from: b */
    private final C2011b f5519b;
    /* renamed from: c */
    private final C2630a f5520c;
    /* renamed from: d */
    private final C2598w f5521d;
    /* renamed from: e */
    private final C1876h f5522e;
    @Nullable
    /* renamed from: f */
    private C1789a f5523f;
    /* renamed from: g */
    private int f5524g;
    /* renamed from: h */
    private int f5525h;
    /* renamed from: i */
    private String f5526i;
    /* renamed from: j */
    private int f5527j;
    /* renamed from: k */
    private int f5528k;
    /* renamed from: l */
    private List<C2316b> f5529l;
    /* renamed from: m */
    private final C2315a f5530m;
    /* renamed from: n */
    private final SparseBooleanArray f5531n = new SparseBooleanArray();

    /* renamed from: com.facebook.ads.internal.view.e.a.c$a */
    public interface C2317a {
        /* renamed from: a */
        void mo5579a(int i);
    }

    C2318c(List<C2316b> list, C2085c c2085c, C2011b c2011b, C2630a c2630a, C2598w c2598w, C1789a c1789a, C1876h c1876h, String str, int i, int i2, int i3, int i4, C2315a c2315a) {
        this.f5518a = c2085c;
        this.f5519b = c2011b;
        this.f5520c = c2630a;
        this.f5521d = c2598w;
        this.f5523f = c1789a;
        this.f5529l = list;
        this.f5525h = i;
        this.f5522e = c1876h;
        this.f5527j = i4;
        this.f5526i = str;
        this.f5524g = i3;
        this.f5528k = i2;
        this.f5530m = c2315a;
    }

    /* renamed from: a */
    public C2327f m5975a(ViewGroup viewGroup, int i) {
        return new C2327f(C2272c.m5826a(new C2278a(viewGroup.getContext(), this.f5518a, this.f5523f, null, null, this.f5520c, this.f5521d).m5848a(), this.f5527j, this.f5522e, this.f5526i, this.f5530m), this.f5531n, this.f5520c, this.f5525h, this.f5524g, this.f5528k, this.f5529l.size());
    }

    /* renamed from: a */
    public void m5976a(C2327f c2327f, int i) {
        c2327f.m6009a((C2316b) this.f5529l.get(i), this.f5518a, this.f5519b, this.f5521d, this.f5526i);
    }

    public int getItemCount() {
        return this.f5529l.size();
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m5976a((C2327f) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m5975a(viewGroup, i);
    }
}
