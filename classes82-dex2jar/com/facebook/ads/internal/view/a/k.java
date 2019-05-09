// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.a;

import com.facebook.ads.internal.f.a;
import android.view.ViewGroup;
import android.view.View$OnClickListener;
import com.facebook.ads.internal.f.b;
import android.widget.FrameLayout$LayoutParams;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class k extends c
{
    private static final int c;
    private static final int d;
    private static final int e;
    private final ScrollView f;
    private final LinearLayout g;
    private final ImageView h;
    
    static {
        c = (int)(8.0f * x.b);
        d = (int)(10.0f * x.b);
        e = (int)(44.0f * x.b);
    }
    
    public k(final Context context, final com.facebook.ads.internal.s.c c, final String s, final int n, final int n2) {
        super(context, c, s);
        (this.h = new ImageView(this.getContext())).setPadding(k.d, k.d, k.d, k.d);
        this.h.setColorFilter(-10459280);
        final LinearLayout$LayoutParams layoutParams = new LinearLayout$LayoutParams(k.e, k.e);
        layoutParams.gravity = 3;
        this.h.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        (this.f = new ScrollView(this.getContext())).setFillViewport(true);
        x.a((View)this.f, -218103809);
        (this.g = new LinearLayout(this.getContext())).setOrientation(1);
        this.g.setPadding(k.c, k.c, k.c, k.c);
        this.f.addView((View)this.g, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        this.addView((View)this.f, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(n, n2));
    }
    
    @Override
    void a(final com.facebook.ads.internal.f.c c, final b.a a) {
        int n;
        if (a == com.facebook.ads.internal.f.b.a.a) {
            n = 1;
        }
        else {
            n = 0;
        }
        final Context context = this.getContext();
        final e b = this.b;
        com.facebook.ads.internal.w.c.b b2;
        if (n != 0) {
            b2 = com.facebook.ads.internal.w.c.b.k;
        }
        else {
            b2 = com.facebook.ads.internal.w.c.b.j;
        }
        final j j = new j(context, c, b, b2);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-2, 0);
        linearLayout$LayoutParams.gravity = 17;
        linearLayout$LayoutParams.weight = 1.0f;
        this.h.setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.m));
        this.h.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                k.this.b.a();
            }
        });
        x.a((ViewGroup)this.g);
        this.f.fullScroll(33);
        this.g.removeAllViews();
        this.g.addView((View)this.h);
        this.g.addView((View)j, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
    }
    
    @Override
    void b(final com.facebook.ads.internal.f.c c, final b.a a) {
        this.h.setOnClickListener((View$OnClickListener)null);
        String s;
        com.facebook.ads.internal.w.c.b b;
        int n;
        if (a == com.facebook.ads.internal.f.b.a.a) {
            s = a.j(this.getContext());
            b = com.facebook.ads.internal.w.c.b.k;
            n = -552389;
        }
        else {
            s = a.b(this.getContext());
            b = com.facebook.ads.internal.w.c.b.j;
            n = -13272859;
        }
        final com.facebook.ads.internal.view.a.a a2 = new com.facebook.ads.internal.view.a.a.a(this.getContext()).a(this.b).a(s).b(a.k(this.getContext())).c(c.b()).a(false).a(b).a(n).b(false).c(false).a();
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-1, 0);
        linearLayout$LayoutParams.gravity = 17;
        linearLayout$LayoutParams.weight = 1.0f;
        x.a((ViewGroup)this.g);
        this.f.fullScroll(33);
        this.g.removeAllViews();
        this.g.addView((View)a2, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
    }
    
    @Override
    void c() {
        x.c((View)this);
        x.b((View)this);
    }
    
    @Override
    void d() {
        this.h.setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.q));
        this.h.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                k.this.b.a();
            }
        });
        final f f = new f(this.getContext());
        f.a(com.facebook.ads.internal.f.a.b(this.getContext()), com.facebook.ads.internal.w.c.b.j);
        f.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                f.a();
                k.this.b.a(com.facebook.ads.internal.f.b.a.b);
            }
        });
        final f f2 = new f(this.getContext());
        f2.a(com.facebook.ads.internal.f.a.e(this.getContext()), com.facebook.ads.internal.w.c.b.k);
        f2.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                f2.a();
                k.this.b.a(com.facebook.ads.internal.f.b.a.a);
            }
        });
        final f f3 = new f(this.getContext());
        f3.a(com.facebook.ads.internal.f.a.l(this.getContext()), com.facebook.ads.internal.w.c.b.f);
        f3.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                f3.a();
                k.this.b.d();
            }
        });
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-2, -2);
        linearLayout$LayoutParams.setMargins(k.c, k.c, k.c, k.c);
        linearLayout$LayoutParams.gravity = 17;
        final LinearLayout linearLayout = new LinearLayout(this.getContext());
        linearLayout.setOrientation(1);
        final LinearLayout$LayoutParams linearLayout$LayoutParams2 = new LinearLayout$LayoutParams(-2, 0);
        linearLayout$LayoutParams2.gravity = 17;
        linearLayout$LayoutParams2.weight = 1.0f;
        x.a((ViewGroup)this.g);
        this.g.removeAllViews();
        this.g.addView((View)this.h);
        this.g.addView((View)linearLayout, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
        linearLayout.addView((View)f, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        linearLayout.addView((View)f2, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        linearLayout.addView((View)f3, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
    }
    
    @Override
    boolean e() {
        return true;
    }
}
