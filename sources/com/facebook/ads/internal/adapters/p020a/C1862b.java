package com.facebook.ads.internal.adapters.p020a;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.ads.internal.p017t.C1783f;
import com.facebook.ads.internal.p017t.C2114e;
import com.facebook.ads.internal.view.C2254c;
import com.facebook.ads.internal.view.C2331e;
import com.facebook.ads.internal.view.C2506j;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.facebook.ads.internal.adapters.a.b */
public class C1862b extends C1861a {
    public C1862b(C2506j c2506j, List<C2114e> list) {
        super(c2506j, list);
    }

    /* renamed from: a */
    public C2331e m4200a(ViewGroup viewGroup, int i) {
        return new C2331e(new C2254c(viewGroup.getContext()));
    }

    /* renamed from: a */
    public void mo5382a(C2331e c2331e, int i) {
        super.mo5382a(c2331e, i);
        View view = (C2254c) c2331e.m6015a();
        m4197a(view.getImageCardView(), i);
        view.setTitle(((C2114e) this.a.get(i)).m5309a("headline"));
        view.setSubtitle(((C2114e) this.a.get(i)).m5309a("link_description"));
        view.setButtonText(((C2114e) this.a.get(i)).m5309a("call_to_action"));
        C2114e c2114e = (C2114e) this.a.get(i);
        List arrayList = new ArrayList();
        arrayList.add(view);
        c2114e.m5312a(view, (C1783f) view, arrayList);
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        mo5382a((C2331e) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m4200a(viewGroup, i);
    }
}
