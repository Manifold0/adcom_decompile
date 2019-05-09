// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters.a;

import android.support.v7.widget.RecyclerView$ViewHolder;
import com.facebook.ads.internal.t.f;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import com.facebook.ads.internal.t.g;
import com.facebook.ads.internal.view.c.d;
import android.widget.ImageView;
import com.facebook.ads.internal.view.j;
import com.facebook.ads.internal.w.b.x;
import android.support.annotation.Nullable;
import java.util.List;
import com.facebook.ads.internal.view.e;
import android.support.v7.widget.RecyclerView$Adapter;

public abstract class a extends RecyclerView$Adapter<e>
{
    private static final int c;
    final List<com.facebook.ads.internal.t.e> a;
    private final int b;
    @Nullable
    private a d;
    private final a.a e;
    
    static {
        c = (int)(4.0f * x.b);
    }
    
    a(final j j, final List<com.facebook.ads.internal.t.e> a) {
        this.e = new a.a() {
            @Override
            public void a() {
                if (com.facebook.ads.internal.adapters.a.a.this.d != null) {
                    com.facebook.ads.internal.adapters.a.a.this.d.a();
                }
            }
        };
        this.b = j.getChildSpacing();
        this.a = a;
    }
    
    void a(final ImageView imageView, final int n) {
        final com.facebook.ads.internal.t.e e = this.a.get(n);
        final g j = e.j();
        if (j != null) {
            final d a = new d(imageView).a();
            a.a(new com.facebook.ads.internal.view.c.e() {
                @Override
                public void a(final boolean b) {
                    if (n == 0) {
                        e.a(com.facebook.ads.internal.adapters.a.a.this.e);
                    }
                    e.a(b, true);
                }
            });
            a.a(j.a());
        }
    }
    
    public void a(final a d) {
        this.d = d;
    }
    
    public void a(final e e, int b) {
        final f a = e.a();
        final ViewGroup$MarginLayoutParams layoutParams = new ViewGroup$MarginLayoutParams(-2, -1);
        int b2;
        if (b == 0) {
            b2 = this.b * 2;
        }
        else {
            b2 = this.b;
        }
        if (b >= this.a.size() - 1) {
            b = this.b * 2;
        }
        else {
            b = this.b;
        }
        layoutParams.setMargins(b2, 0, b, 0);
        a.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
    
    public int getItemCount() {
        return this.a.size();
    }
    
    public interface a
    {
        void a();
    }
}
