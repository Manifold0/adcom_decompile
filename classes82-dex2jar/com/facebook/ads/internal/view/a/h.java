// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.a;

import com.facebook.ads.internal.f.a;
import android.widget.TextView;
import java.util.Iterator;
import android.view.View$OnClickListener;
import android.view.ViewGroup;
import com.facebook.ads.internal.f.b;
import android.widget.FrameLayout$LayoutParams;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.ImageView$ScaleType;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class h extends c
{
    private static final int c;
    private static final int d;
    private static final int e;
    private final LinearLayout f;
    private final ImageView g;
    private final HorizontalScrollView h;
    private final LinearLayout i;
    
    static {
        c = (int)(4.0f * x.b);
        d = (int)(10.0f * x.b);
        e = (int)(44.0f * x.b);
    }
    
    public h(final Context context, final com.facebook.ads.internal.s.c c, final String s, final int n, final int n2) {
        super(context, c, s);
        (this.g = new ImageView(this.getContext())).setPadding(com.facebook.ads.internal.view.a.h.d, com.facebook.ads.internal.view.a.h.d, com.facebook.ads.internal.view.a.h.d, com.facebook.ads.internal.view.a.h.d);
        this.g.setScaleType(ImageView$ScaleType.FIT_CENTER);
        this.g.setColorFilter(-10459280);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(com.facebook.ads.internal.view.a.h.e, com.facebook.ads.internal.view.a.h.e);
        linearLayout$LayoutParams.gravity = 16;
        (this.i = new LinearLayout(this.getContext())).setOrientation(0);
        final LinearLayout$LayoutParams layoutParams = new LinearLayout$LayoutParams(-1, -2);
        layoutParams.gravity = 17;
        (this.h = new HorizontalScrollView(this.getContext())).setHorizontalScrollBarEnabled(false);
        this.h.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.h.addView((View)this.i, (ViewGroup$LayoutParams)layoutParams);
        (this.f = new LinearLayout(this.getContext())).setOrientation(0);
        x.a((View)this.f, -218103809);
        this.f.setMotionEventSplittingEnabled(false);
        this.f.addView((View)this.g, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        this.f.addView((View)this.h, (ViewGroup$LayoutParams)layoutParams);
        this.addView((View)this.f, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(n, n2));
    }
    
    public void a(final com.facebook.ads.internal.f.c c, final b.a a) {
        x.a((ViewGroup)this.f);
        this.g.setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.m));
        this.g.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                com.facebook.ads.internal.view.a.h.this.b.a();
            }
        });
        this.i.removeAllViews();
        this.h.fullScroll(17);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-2, -2);
        linearLayout$LayoutParams.setMargins(0, com.facebook.ads.internal.view.a.h.c, com.facebook.ads.internal.view.a.h.c, com.facebook.ads.internal.view.a.h.c);
        for (final com.facebook.ads.internal.f.c c2 : c.d()) {
            final f f = new f(this.getContext());
            f.a(c2.b(), null);
            f.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    f.a();
                    com.facebook.ads.internal.view.a.h.this.b.a(c2);
                }
            });
            this.i.addView((View)f, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        }
    }
    
    public void b(final com.facebook.ads.internal.f.c c, final b.a a) {
        this.g.setOnClickListener((View$OnClickListener)null);
        final TextView textView = new TextView(this.getContext());
        x.a(textView, true, 14);
        textView.setText((CharSequence)a.k(this.getContext()));
        textView.setGravity(17);
        x.a((ViewGroup)this.f);
        this.f.removeAllViews();
        this.f.addView((View)textView, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, -1));
        textView.setClickable(true);
    }
    
    @Override
    void c() {
        x.c((View)this);
        x.b((View)this);
    }
    
    public void d() {
        this.g.setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.q));
        this.g.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                com.facebook.ads.internal.view.a.h.this.b.a();
            }
        });
        final f f = new f(this.getContext());
        f.a(com.facebook.ads.internal.f.a.b(this.getContext()), com.facebook.ads.internal.w.c.b.j);
        f.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                f.a();
                com.facebook.ads.internal.view.a.h.this.b.a(com.facebook.ads.internal.f.b.a.b);
            }
        });
        final f f2 = new f(this.getContext());
        f2.a(com.facebook.ads.internal.f.a.e(this.getContext()), com.facebook.ads.internal.w.c.b.k);
        f2.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                f2.a();
                com.facebook.ads.internal.view.a.h.this.b.a(com.facebook.ads.internal.f.b.a.a);
            }
        });
        final f f3 = new f(this.getContext());
        f3.a(com.facebook.ads.internal.f.a.l(this.getContext()), com.facebook.ads.internal.w.c.b.f);
        f3.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                f3.a();
                com.facebook.ads.internal.view.a.h.this.b.d();
            }
        });
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-2, -2);
        linearLayout$LayoutParams.setMargins(0, com.facebook.ads.internal.view.a.h.c, com.facebook.ads.internal.view.a.h.c, com.facebook.ads.internal.view.a.h.c);
        x.a((ViewGroup)this.f);
        this.i.removeAllViews();
        this.i.addView((View)f, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        this.i.addView((View)f2, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        this.i.addView((View)f3, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
    }
    
    @Override
    boolean e() {
        return true;
    }
}
