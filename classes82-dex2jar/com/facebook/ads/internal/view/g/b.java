// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.g;

import android.widget.LinearLayout$LayoutParams;
import android.os.Build$VERSION;
import android.animation.TimeInterpolator;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.animation.PropertyValuesHolder;
import android.widget.TextView;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable$Orientation;
import com.facebook.ads.internal.view.c.e;
import com.facebook.ads.internal.view.c.d;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.ImageView$ScaleType;
import com.facebook.ads.internal.view.component.f;
import java.util.HashMap;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.support.annotation.Nullable;
import android.animation.ObjectAnimator;
import com.facebook.ads.internal.view.i.c.l;
import android.widget.ImageView;
import com.facebook.ads.internal.view.component.k;
import android.widget.RelativeLayout;
import java.util.Map;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.x.a;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.adapters.b.q;
import com.facebook.ads.internal.view.component.g;

public class b extends g
{
    private static final int b;
    private static final int c;
    private static final int d;
    private static final int e;
    private static final int f;
    private q g;
    private final c h;
    private final a i;
    private final w j;
    private final Map<String, String> k;
    private RelativeLayout l;
    private k m;
    private ImageView n;
    private l o;
    private ObjectAnimator p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    @Nullable
    private com.facebook.ads.internal.f.c v;
    @Nullable
    private com.facebook.ads.internal.f.b.a w;
    
    static {
        b = (int)(48.0f * x.b);
        c = (int)(40.0f * x.b);
        d = (int)(16.0f * x.b);
        e = (int)(56.0f * x.b);
        f = (int)(200.0f * x.b);
    }
    
    public b(final Context context, final q g, final c h, final a i, final w j, final com.facebook.ads.internal.view.a.a a) {
        super(context);
        this.k = new HashMap<String, String>();
        this.t = false;
        this.g = g;
        this.h = h;
        this.i = i;
        this.j = j;
        this.n = new f(context);
        (this.o = new l(context, true)).setClickable(false);
        this.n.setScaleType(ImageView$ScaleType.CENTER_CROP);
        this.addView((View)this.n, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
        new d(this.n).a().a(new e() {
            @Override
            public void a(final boolean b) {
                b.this.r = b;
                b.this.e();
            }
        }).a(this.g.j().g());
        final String a2 = g.a();
        final RelativeLayout relativeLayout = new RelativeLayout(this.getContext());
        relativeLayout.setPadding(com.facebook.ads.internal.view.g.b.d, com.facebook.ads.internal.view.g.b.d, com.facebook.ads.internal.view.g.b.d, com.facebook.ads.internal.view.g.b.d);
        relativeLayout.setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
        this.addView((View)relativeLayout);
        final GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable$Orientation.TOP_BOTTOM, new int[] { 0, -872415232 });
        gradientDrawable.setCornerRadius(0.0f);
        x.a((View)relativeLayout, (Drawable)gradientDrawable);
        x.a((View)(this.l = new RelativeLayout(this.getContext())));
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-2, -2);
        relativeLayout$LayoutParams.addRule(12);
        relativeLayout.addView((View)this.l, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        (this.m = new k(this.getContext(), a2, this.g.g().f(), a)).a(this.g.g().a(), true, 22, -1);
        this.m.b(this.g.g().d(), false, 14, -1);
        this.m.c(this.g.g().g(), false, 14, -1);
        this.m.d(this.g.g().e(), false, 14, -1);
        this.l.addView((View)this.m, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -2));
        final f f = new f(this.getContext());
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(com.facebook.ads.internal.view.g.b.b, com.facebook.ads.internal.view.g.b.b);
        layoutParams.addRule(2, this.l.getId());
        f.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        f.setFullCircleCorners(this.g.g().f().equals(com.facebook.ads.internal.adapters.b.e.a.b));
        relativeLayout.addView((View)f);
        new d(f).a(com.facebook.ads.internal.view.g.b.b, com.facebook.ads.internal.view.g.b.b).a(this.g.f().b());
        this.c(this.s);
    }
    
    private void b(final com.facebook.ads.internal.f.c c, final com.facebook.ads.internal.f.b.a a) {
        int n;
        if (this.getWidth() >= com.facebook.ads.internal.view.g.b.f && this.getHeight() >= com.facebook.ads.internal.view.g.b.f) {
            n = 1;
        }
        else {
            n = 0;
        }
        Object o;
        if (n != 0) {
            String s;
            com.facebook.ads.internal.w.c.b b;
            int n2;
            if (a == com.facebook.ads.internal.f.b.a.a) {
                s = com.facebook.ads.internal.f.a.j(this.getContext());
                b = com.facebook.ads.internal.w.c.b.k;
                n2 = -552389;
            }
            else {
                s = com.facebook.ads.internal.f.a.i(this.getContext());
                b = com.facebook.ads.internal.w.c.b.j;
                n2 = -13272859;
            }
            o = new com.facebook.ads.internal.view.a.a.a(this.getContext()).a(s).b(com.facebook.ads.internal.f.a.k(this.getContext())).c(c.b()).a(false).a(b).a(n2).b(false).c(false).e(false).a();
        }
        else {
            o = this.getAdHiddenViewTextOnly();
        }
        x.a((View)o, -1);
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, -1);
        layoutParams.addRule(13);
        ((View)o).setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.removeAllViews();
        this.addView((View)o);
    }
    
    private void c(final boolean b) {
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = (RelativeLayout$LayoutParams)this.m.getLayoutParams();
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(b.c, b.c);
        if (b) {
            relativeLayout$LayoutParams.rightMargin = 0;
            relativeLayout$LayoutParams2.topMargin = b.d;
            relativeLayout$LayoutParams2.rightMargin = b.d;
            relativeLayout$LayoutParams2.addRule(11);
            relativeLayout$LayoutParams2.addRule(10);
            this.addView((View)this.o, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
            return;
        }
        relativeLayout$LayoutParams.rightMargin = b.e;
        relativeLayout$LayoutParams2.addRule(15);
        relativeLayout$LayoutParams2.addRule(11);
        this.l.addView((View)this.o, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
    }
    
    private void e() {
        if (this.q && this.r) {
            this.i.a(this.k);
            this.k.put("touch", com.facebook.ads.internal.w.b.k.a(this.j.e()));
            this.k.put("is_cyoa", Boolean.TRUE.toString());
            this.h.o(this.g.a(), this.k);
        }
    }
    
    private View getAdHiddenViewTextOnly() {
        final TextView textView = new TextView(this.getContext());
        x.a(textView, true, 14);
        textView.setText((CharSequence)com.facebook.ads.internal.f.a.k(this.getContext()));
        textView.setGravity(17);
        return (View)textView;
    }
    
    public void a(final int n) {
        this.k.put("ad_intro_position", String.valueOf(n));
    }
    
    public void a(final com.facebook.ads.internal.f.c v, final com.facebook.ads.internal.f.b.a w) {
        this.t = true;
        this.b(this.v = v, this.w = w);
    }
    
    public void a(final boolean b) {
        this.m.a(b);
    }
    
    public void a(final boolean b, final int n) {
        float n2 = 0.99f;
        if (this.p != null) {
            this.p.cancel();
        }
        float n3;
        if (b) {
            n3 = 1.01f;
        }
        else {
            n3 = 0.99f;
        }
        if (!b) {
            n2 = 1.01f;
        }
        (this.p = ObjectAnimator.ofPropertyValuesHolder((Object)this, new PropertyValuesHolder[] { PropertyValuesHolder.ofFloat("scaleX", new float[] { n3, n2 }), PropertyValuesHolder.ofFloat("scaleY", new float[] { n3, n2 }) })).setInterpolator((TimeInterpolator)new FastOutLinearInInterpolator());
        this.p.setDuration((long)n);
        this.p.setRepeatCount(-1);
        this.p.setRepeatMode(2);
        this.p.start();
        this.u = false;
    }
    
    public boolean a() {
        return this.t;
    }
    
    public void b() {
        if (this.p != null && !this.u) {
            if (Build$VERSION.SDK_INT < 19) {
                this.p.cancel();
                return;
            }
            this.p.pause();
        }
    }
    
    public void b(final boolean b) {
        final int n = 0;
        final LinearLayout$LayoutParams linearLayout$LayoutParams = (LinearLayout$LayoutParams)this.getLayoutParams();
        int width;
        if (b) {
            width = -1;
        }
        else {
            width = 0;
        }
        linearLayout$LayoutParams.width = width;
        int height;
        if (b) {
            height = n;
        }
        else {
            height = -1;
        }
        linearLayout$LayoutParams.height = height;
    }
    
    public void c() {
        if (this.p != null && !this.u) {
            if (Build$VERSION.SDK_INT < 19) {
                this.p.start();
                return;
            }
            this.p.resume();
        }
    }
    
    public void d() {
        if (this.p != null) {
            this.p.cancel();
        }
        this.u = true;
    }
    
    public q getAdDataBundle() {
        return this.g;
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        if (b && this.a()) {
            this.removeAllViews();
            this.b(this.v, this.w);
        }
    }
    
    public void setAdReportingFlowListener(final com.facebook.ads.internal.view.a.b adReportingFlowListener) {
        this.m.setAdReportingFlowListener(adReportingFlowListener);
    }
    
    public void setShouldPlayButtonOnTop(final boolean s) {
        if (s != this.s) {
            this.s = s;
            x.b((View)this.o);
            this.c(this.s);
        }
    }
    
    public void setViewability(final boolean q) {
        this.q = q;
        this.e();
    }
}
