// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.e.a;

import com.facebook.ads.internal.w.b.k;
import java.util.Map;
import android.view.ViewGroup$LayoutParams;
import android.text.TextUtils;
import android.view.ViewGroup$MarginLayoutParams;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.s.c;
import android.view.View;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.x.a;
import android.util.SparseBooleanArray;
import com.facebook.ads.internal.view.component.a.a.b;
import android.support.v7.widget.RecyclerView$ViewHolder;

class f extends RecyclerView$ViewHolder
{
    private final b a;
    private final SparseBooleanArray b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    @Nullable
    private a g;
    private a.a h;
    private a i;
    
    f(final b a, final SparseBooleanArray b, final a i, final int c, final int d, final int e, final int f) {
        super((View)a);
        this.a = a;
        this.b = b;
        this.i = i;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    void a(final com.facebook.ads.internal.view.e.a.b b, final c c, final com.facebook.ads.internal.h.b b2, final w w, final String s) {
        final int b3 = b.b();
        this.a.setTag(-1593835536, (Object)b3);
        final ViewGroup$MarginLayoutParams layoutParams = new ViewGroup$MarginLayoutParams(this.c, -2);
        int n;
        if (b3 == 0) {
            n = this.d;
        }
        else {
            n = this.e;
        }
        int n2;
        if (b3 >= this.f - 1) {
            n2 = this.d;
        }
        else {
            n2 = this.e;
        }
        layoutParams.setMargins(n, 0, n2, 0);
        final String g = b.c().c().g();
        final String a = b.c().c().a();
        this.a.setIsVideo(!TextUtils.isEmpty((CharSequence)a));
        if (this.a.f()) {
            this.a.setVideoPlaceholderUrl(g);
            final b a2 = this.a;
            String c2;
            final String s2 = c2 = "";
            if (b2 != null) {
                c2 = s2;
                if (a != null) {
                    c2 = b2.c(a);
                }
            }
            if (TextUtils.isEmpty((CharSequence)c2)) {
                c2 = a;
            }
            a2.setVideoUrl(c2);
        }
        else {
            this.a.setImageUrl(g);
        }
        this.a.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.a.a(b.c().a().a(), b.c().a().c());
        this.a.a(b.c().b(), b.a());
        this.a.a(b.a());
        if (this.b.get(b.b())) {
            return;
        }
        if (this.g != null) {
            this.g.c();
            this.g = null;
        }
        this.h = new a.a() {
            final /* synthetic */ Map c = b.a();
            
            @Override
            public void a() {
                if (!com.facebook.ads.internal.view.e.a.f.this.i.b() && !TextUtils.isEmpty((CharSequence)s) && !com.facebook.ads.internal.view.e.a.f.this.b.get(b.b())) {
                    if (com.facebook.ads.internal.view.e.a.f.this.g != null) {
                        com.facebook.ads.internal.view.e.a.f.this.g.a(this.c);
                    }
                    this.c.put("touch", com.facebook.ads.internal.w.b.k.a(w.e()));
                    c.a(s, this.c);
                    com.facebook.ads.internal.view.e.a.f.this.b.put(b.b(), true);
                }
            }
        };
        (this.g = new a((View)this.a, 10, this.h)).a(100);
        this.g.b(100);
        this.a.setOnAssetsLoadedListener((b.a)new b.a() {
            @Override
            public void a() {
                if (b.b() == 0) {
                    f.this.i.a();
                }
                f.this.g.a();
            }
        });
    }
}
