package com.facebook.ads.internal.view.p064g;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.List;

/* renamed from: com.facebook.ads.internal.view.g.d */
public class C2364d extends Adapter<C2366f> {
    /* renamed from: a */
    private final List<String> f5723a;
    /* renamed from: b */
    private final int f5724b;

    C2364d(List<String> list, int i) {
        this.f5723a = list;
        this.f5724b = i;
    }

    /* renamed from: a */
    public C2366f m6111a(ViewGroup viewGroup, int i) {
        return new C2366f(new C2365e(viewGroup.getContext()));
    }

    /* renamed from: a */
    public void m6112a(C2366f c2366f, int i) {
        String str = (String) this.f5723a.get(i);
        LayoutParams marginLayoutParams = new MarginLayoutParams(-2, -1);
        marginLayoutParams.setMargins(i == 0 ? this.f5724b * 4 : this.f5724b, 0, i >= getItemCount() + -1 ? this.f5724b * 4 : this.f5724b, 0);
        c2366f.m6114a().setLayoutParams(marginLayoutParams);
        c2366f.m6114a().m6113a(str);
    }

    public int getItemCount() {
        return this.f5723a.size();
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m6112a((C2366f) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m6111a(viewGroup, i);
    }
}
