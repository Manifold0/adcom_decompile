// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters.a;

import android.support.v7.widget.RecyclerView$ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.graphics.drawable.Drawable;
import com.facebook.ads.internal.view.x;
import com.facebook.ads.internal.t.f;
import com.facebook.ads.internal.view.k;
import android.view.ViewGroup;
import com.facebook.ads.internal.t.e;
import java.util.List;
import com.facebook.ads.internal.view.j;

public class c extends a
{
    public c(final j j, final List<e> list) {
        super(j, list);
    }
    
    public com.facebook.ads.internal.view.e a(final ViewGroup viewGroup, final int n) {
        return new com.facebook.ads.internal.view.e(new k(viewGroup.getContext()));
    }
    
    @Override
    public void a(final com.facebook.ads.internal.view.e e, final int n) {
        super.a(e, n);
        final k k = (k)e.a();
        final x x = (x)k.getImageCardView();
        x.setImageDrawable((Drawable)null);
        this.a(x, n);
        this.a.get(n).a((View)k, k);
    }
}
