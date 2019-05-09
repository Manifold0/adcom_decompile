// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.e.a;

import android.support.v7.widget.RecyclerView$ViewHolder;
import android.view.View;
import com.facebook.ads.internal.adapters.b.k;
import com.facebook.ads.internal.view.component.a.e;
import android.view.ViewGroup;
import android.util.SparseBooleanArray;
import java.util.List;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.x.a;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.h.b;
import android.support.v7.widget.RecyclerView$Adapter;

public class c extends RecyclerView$Adapter<f>
{
    private final com.facebook.ads.internal.s.c a;
    @Nullable
    private final b b;
    private final com.facebook.ads.internal.x.a c;
    private final w d;
    private final h e;
    @Nullable
    private com.facebook.ads.internal.view.a.a f;
    private int g;
    private int h;
    private String i;
    private int j;
    private int k;
    private List<com.facebook.ads.internal.view.e.a.b> l;
    private final com.facebook.ads.internal.view.e.a.a m;
    private final SparseBooleanArray n;
    
    c(final List<com.facebook.ads.internal.view.e.a.b> l, final com.facebook.ads.internal.s.c a, final b b, final com.facebook.ads.internal.x.a c, final w d, final com.facebook.ads.internal.view.a.a f, final h e, final String i, final int h, final int k, final int g, final int j, final com.facebook.ads.internal.view.e.a.a m) {
        this.n = new SparseBooleanArray();
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.f = f;
        this.l = l;
        this.h = h;
        this.e = e;
        this.j = j;
        this.i = i;
        this.g = g;
        this.k = k;
        this.m = m;
    }
    
    public f a(final ViewGroup viewGroup, final int n) {
        return new f(com.facebook.ads.internal.view.component.a.a.c.a(new e.a(viewGroup.getContext(), this.a, this.f, null, null, this.c, this.d).a(), this.j, this.e, this.i, this.m), this.n, this.c, this.h, this.g, this.k, this.l.size());
    }
    
    public void a(final f f, final int n) {
        f.a(this.l.get(n), this.a, this.b, this.d, this.i);
    }
    
    public int getItemCount() {
        return this.l.size();
    }
    
    public interface a
    {
        void a(final int p0);
    }
}
