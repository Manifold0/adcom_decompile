// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.text.style.StyleSpan;
import android.text.SpannableString;
import android.graphics.drawable.GradientDrawable$Orientation;
import com.facebook.ads.internal.r.a;
import android.view.View$MeasureSpec;
import android.widget.ImageView;
import com.facebook.ads.internal.w.b.j;
import android.widget.ImageView$ScaleType;
import android.widget.LinearLayout$LayoutParams;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.internal.t.f;

public class c extends f
{
    public static final int a;
    private static final int b;
    private static final int c;
    private static final int d;
    private static final int e;
    private static final int f;
    private static final int g;
    private static final int h;
    private static final int i;
    private final TextView j;
    private final TextView k;
    private final TextView l;
    private final RelativeLayout m;
    private final LinearLayout n;
    private final RelativeLayout o;
    private final t p;
    
    static {
        a = (int)x.b * 500;
        b = (int)(x.b * 500.0f);
        c = (int)(x.b * 4.0f);
        d = (int)(x.b * 8.0f);
        e = (int)(x.b * 8.0f);
        f = (int)(x.b * 4.0f);
        g = (int)x.b;
        h = (int)(x.b * 4.0f);
        i = (int)(x.b * 0.5);
    }
    
    public c(final Context context) {
        super(context);
        this.j = new TextView(context);
        this.k = new TextView(context);
        this.l = new TextView(context);
        this.m = new RelativeLayout(context);
        this.n = new LinearLayout(context);
        this.o = new RelativeLayout(context);
        this.p = new t(context);
        this.setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-2, -1));
        final GradientDrawable backgroundDrawable = new GradientDrawable();
        backgroundDrawable.setColor(-1);
        backgroundDrawable.setCornerRadius((float)com.facebook.ads.internal.view.c.h);
        backgroundDrawable.setStroke(1, -10459280);
        this.setBackgroundDrawable((Drawable)backgroundDrawable);
        this.setPadding(com.facebook.ads.internal.view.c.i, com.facebook.ads.internal.view.c.i, com.facebook.ads.internal.view.c.i, com.facebook.ads.internal.view.c.i);
        this.n.setOrientation(1);
        x.a((View)this.n);
        this.addView((View)this.n, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -2));
        x.a((View)this.o);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-2, -2);
        linearLayout$LayoutParams.weight = 1.0f;
        linearLayout$LayoutParams.gravity = 1;
        this.n.addView((View)this.o, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        this.p.setScaleType(ImageView$ScaleType.FIT_XY);
        this.p.setRadius(new float[] { (float)com.facebook.ads.internal.view.c.h, (float)com.facebook.ads.internal.view.c.h, (float)com.facebook.ads.internal.view.c.h, (float)com.facebook.ads.internal.view.c.h, 0.0f, 0.0f, 0.0f, 0.0f });
        this.p.setAdjustViewBounds(true);
        com.facebook.ads.internal.w.b.j.a((View)this.p, com.facebook.ads.internal.w.b.j.n);
        x.a((View)this.p);
        this.o.addView((View)this.p, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -1));
        x.a((View)this.o);
        this.l.setPadding(com.facebook.ads.internal.view.c.g, com.facebook.ads.internal.view.c.g, com.facebook.ads.internal.view.c.g, com.facebook.ads.internal.view.c.g);
        this.l.setTextSize(14.0f);
        x.a((View)this.l);
        x.a((View)this.m);
        this.j.setTextSize(14.0f);
        x.a((View)this.j);
        this.j.setMaxLines(1);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-2, -2);
        relativeLayout$LayoutParams.setMargins(0, 0, 0, com.facebook.ads.internal.view.c.f);
        this.m.addView((View)this.j, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        this.k.setTextSize(12.0f);
        x.a((View)this.k);
        this.k.setMaxLines(1);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(-2, -2);
        relativeLayout$LayoutParams2.addRule(3, this.j.getId());
        relativeLayout$LayoutParams2.setMargins(0, 0, 0, com.facebook.ads.internal.view.c.f);
        this.m.addView((View)this.k, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
    }
    
    @Override
    protected View getAdContentsView() {
        return (View)this.p;
    }
    
    public TextView getCTAButton() {
        return this.l;
    }
    
    public ImageView getImageCardView() {
        return this.p;
    }
    
    protected void onMeasure(final int n, final int n2) {
        if (View$MeasureSpec.getSize(n2) >= (int)x.b * com.facebook.ads.internal.r.a.r(this.getContext()) || View$MeasureSpec.getMode(n2) == 0) {
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-2, -2);
            relativeLayout$LayoutParams.addRule(3, this.k.getId());
            x.b((View)this.l);
            this.m.addView((View)this.l, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(-1, -2);
            this.m.setPadding(com.facebook.ads.internal.view.c.e, com.facebook.ads.internal.view.c.e, com.facebook.ads.internal.view.c.e, com.facebook.ads.internal.view.c.e);
            x.b((View)this.m);
            this.n.addView((View)this.m, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
            this.p.setMaxWidth(com.facebook.ads.internal.view.c.b);
            this.j.setTextColor(-10459280);
            this.k.setTextColor(-10459280);
            this.l.setTextColor(-13272859);
            ((LinearLayout$LayoutParams)this.o.getLayoutParams()).gravity = 1;
        }
        else {
            final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-2, -2);
            linearLayout$LayoutParams.setMargins(com.facebook.ads.internal.view.c.d, com.facebook.ads.internal.view.c.c, com.facebook.ads.internal.view.c.d, com.facebook.ads.internal.view.c.c);
            x.b((View)this.l);
            this.n.addView((View)this.l, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams3 = new RelativeLayout$LayoutParams(-2, -2);
            relativeLayout$LayoutParams3.addRule(8, this.p.getId());
            relativeLayout$LayoutParams3.addRule(5, this.p.getId());
            relativeLayout$LayoutParams3.addRule(7, this.p.getId());
            this.m.setPadding(com.facebook.ads.internal.view.c.e, 0, com.facebook.ads.internal.view.c.e, 0);
            this.m.setBackgroundDrawable((Drawable)new GradientDrawable(GradientDrawable$Orientation.BOTTOM_TOP, new int[] { -872415232, 0 }));
            x.b((View)this.m);
            this.o.addView((View)this.m, (ViewGroup$LayoutParams)relativeLayout$LayoutParams3);
            this.j.setTextColor(-1);
            this.k.setTextColor(-1);
            this.l.setTextColor(-13272859);
        }
        super.onMeasure(n, n2);
    }
    
    public void setButtonText(final String s) {
        if (s == null || s.trim().isEmpty()) {
            this.l.setVisibility(8);
            return;
        }
        final SpannableString text = new SpannableString((CharSequence)s);
        text.setSpan((Object)new StyleSpan(1), 0, text.length(), 0);
        this.l.setText((CharSequence)text);
    }
    
    public void setSubtitle(final String text) {
        if (text == null || text.trim().isEmpty()) {
            this.k.setVisibility(8);
        }
        this.k.setText((CharSequence)text);
    }
    
    public void setTitle(final String text) {
        if (text == null || text.trim().isEmpty()) {
            this.j.setVisibility(8);
        }
        this.j.setText((CharSequence)text);
    }
}
