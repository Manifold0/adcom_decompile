// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.a;

import android.widget.LinearLayout;
import android.widget.LinearLayout$LayoutParams;
import com.facebook.ads.internal.f.b;
import android.view.ViewGroup;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.ChangeBounds;
import android.transition.TransitionSet;
import android.os.Build$VERSION;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.RelativeLayout$LayoutParams;
import com.facebook.ads.internal.view.a;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.widget.RelativeLayout;

public class g extends c
{
    private static final int c;
    private final RelativeLayout d;
    
    static {
        c = (int)(8.0f * x.b);
    }
    
    g(final Context context, final com.facebook.ads.internal.s.c c, final String s, final a a, final a.a a2) {
        super(context, c, s, a, a2);
        this.addView((View)(this.d = new RelativeLayout(this.getContext())), (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
        x.a((View)this.d, -1728053248);
        this.d.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                g.this.b.a(false);
            }
        });
    }
    
    private static RelativeLayout$LayoutParams b(final boolean b) {
        int n;
        if (b) {
            n = -1;
        }
        else {
            n = -2;
        }
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-1, n);
        relativeLayout$LayoutParams.addRule(12);
        return relativeLayout$LayoutParams;
    }
    
    private void f() {
        if (Build$VERSION.SDK_INT >= 21) {
            final TransitionSet set = new TransitionSet();
            set.setOrdering(0);
            set.addTransition((Transition)new ChangeBounds()).addTransition((Transition)new Explode());
            x.a((ViewGroup)this, (Transition)set);
            return;
        }
        x.a((ViewGroup)this);
    }
    
    public void a(final com.facebook.ads.internal.f.c c, final b.a a) {
        boolean b;
        if (a == com.facebook.ads.internal.f.b.a.a) {
            b = true;
        }
        else {
            b = false;
        }
        final Context context = this.getContext();
        final e b2 = this.b;
        String s;
        if (b) {
            s = com.facebook.ads.internal.f.a.e(this.getContext());
        }
        else {
            s = com.facebook.ads.internal.f.a.b(this.getContext());
        }
        com.facebook.ads.internal.w.c.b b3;
        if (b) {
            b3 = com.facebook.ads.internal.w.c.b.k;
        }
        else {
            b3 = com.facebook.ads.internal.w.c.b.j;
        }
        final j j = new j(context, c, b2, s, b3);
        j.setClickable(true);
        x.a((View)j, -1);
        j.setPadding(g.c * 2, g.c, g.c * 2, g.c);
        this.f();
        this.d.removeAllViews();
        this.d.addView((View)j, (ViewGroup$LayoutParams)b(false));
    }
    
    public void b(final com.facebook.ads.internal.f.c c, final b.a a) {
        if (a == com.facebook.ads.internal.f.b.a.c) {
            return;
        }
        boolean b;
        if (a == com.facebook.ads.internal.f.b.a.a) {
            b = true;
        }
        else {
            b = false;
        }
        final com.facebook.ads.internal.view.a.a.a a2 = new com.facebook.ads.internal.view.a.a.a(this.getContext()).a(this.b);
        String s;
        if (b) {
            s = com.facebook.ads.internal.f.a.j(this.getContext());
        }
        else {
            s = com.facebook.ads.internal.f.a.i(this.getContext());
        }
        final com.facebook.ads.internal.view.a.a.a c2 = a2.a(s).b(com.facebook.ads.internal.f.a.k(this.getContext())).c(c.b());
        com.facebook.ads.internal.w.c.b b2;
        if (b) {
            b2 = com.facebook.ads.internal.w.c.b.k;
        }
        else {
            b2 = com.facebook.ads.internal.w.c.b.j;
        }
        final com.facebook.ads.internal.view.a.a.a a3 = c2.a(b2);
        int n;
        if (b) {
            n = -552389;
        }
        else {
            n = -13272859;
        }
        final com.facebook.ads.internal.view.a.a a4 = a3.a(n).d(this.a).a();
        x.a((View)a4, -1);
        x.a((ViewGroup)this);
        this.d.removeAllViews();
        this.d.addView((View)a4, (ViewGroup$LayoutParams)b(true));
    }
    
    public void c() {
        x.c((View)this);
        this.d.removeAllViews();
        x.b((View)this);
    }
    
    public void d() {
        final com.facebook.ads.internal.f.c d = com.facebook.ads.internal.f.a.d(this.getContext());
        final i i = new i(this.getContext());
        i.a(com.facebook.ads.internal.w.c.b.j, com.facebook.ads.internal.f.a.b(this.getContext()), com.facebook.ads.internal.f.a.c(this.getContext()));
        i.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                g.this.b.a(com.facebook.ads.internal.f.b.a.b);
            }
        });
        final com.facebook.ads.internal.f.c g = com.facebook.ads.internal.f.a.g(this.getContext());
        final i j = new i(this.getContext());
        j.a(com.facebook.ads.internal.w.c.b.k, com.facebook.ads.internal.f.a.e(this.getContext()), com.facebook.ads.internal.f.a.f(this.getContext()));
        j.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                g.this.b.a(com.facebook.ads.internal.f.b.a.a);
            }
        });
        final i k = new i(this.getContext());
        k.a(com.facebook.ads.internal.w.c.b.f, com.facebook.ads.internal.f.a.l(this.getContext()), "");
        k.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                g.this.b.d();
            }
        });
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-1, -2);
        final LinearLayout linearLayout = new LinearLayout(this.getContext());
        linearLayout.setClickable(true);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(com.facebook.ads.internal.view.a.g.c * 2, com.facebook.ads.internal.view.a.g.c, com.facebook.ads.internal.view.a.g.c * 2, com.facebook.ads.internal.view.a.g.c);
        x.a((View)linearLayout, -1);
        if (!d.d().isEmpty()) {
            linearLayout.addView((View)i, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        }
        if (!g.d().isEmpty()) {
            linearLayout.addView((View)j, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        }
        linearLayout.addView((View)k, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        this.f();
        this.d.removeAllViews();
        this.d.addView((View)linearLayout, (ViewGroup$LayoutParams)b(false));
    }
    
    @Override
    boolean e() {
        return false;
    }
}
