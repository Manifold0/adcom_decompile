package com.facebook.ads.internal.adapters.p020a;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.ads.internal.p017t.C1783f;
import com.facebook.ads.internal.p017t.C2114e;
import com.facebook.ads.internal.view.C2331e;
import com.facebook.ads.internal.view.C2506j;
import com.facebook.ads.internal.view.C2507k;
import com.facebook.ads.internal.view.C2554x;
import java.util.List;

/* renamed from: com.facebook.ads.internal.adapters.a.c */
public class C1863c extends C1861a {
    public C1863c(C2506j c2506j, List<C2114e> list) {
        super(c2506j, list);
    }

    /* renamed from: a */
    public C2331e m4202a(ViewGroup viewGroup, int i) {
        return new C2331e(new C2507k(viewGroup.getContext()));
    }

    /* renamed from: a */
    public void mo5382a(C2331e c2331e, int i) {
        super.mo5382a(c2331e, i);
        View view = (C2507k) c2331e.m6015a();
        C2554x c2554x = (C2554x) view.getImageCardView();
        c2554x.setImageDrawable(null);
        m4197a((ImageView) c2554x, i);
        ((C2114e) this.a.get(i)).m5311a(view, (C1783f) view);
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        mo5382a((C2331e) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m4202a(viewGroup, i);
    }
}
