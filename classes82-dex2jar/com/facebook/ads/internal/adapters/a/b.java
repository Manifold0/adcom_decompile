// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters.a;

import android.support.v7.widget.RecyclerView$ViewHolder;
import android.view.View;
import java.util.ArrayList;
import com.facebook.ads.internal.t.f;
import com.facebook.ads.internal.view.c;
import android.view.ViewGroup;
import com.facebook.ads.internal.t.e;
import java.util.List;
import com.facebook.ads.internal.view.j;

public class b extends a
{
    public b(final j j, final List<e> list) {
        super(j, list);
    }
    
    public com.facebook.ads.internal.view.e a(final ViewGroup viewGroup, final int n) {
        return new com.facebook.ads.internal.view.e(new c(viewGroup.getContext()));
    }
    
    @Override
    public void a(final com.facebook.ads.internal.view.e e, final int n) {
        super.a(e, n);
        final c c = (c)e.a();
        this.a(c.getImageCardView(), n);
        c.setTitle(this.a.get(n).a("headline"));
        c.setSubtitle(this.a.get(n).a("link_description"));
        c.setButtonText(this.a.get(n).a("call_to_action"));
        final e e2 = this.a.get(n);
        final ArrayList<c> list = new ArrayList<c>();
        list.add(c);
        e2.a((View)c, c, (List<View>)list);
    }
}
