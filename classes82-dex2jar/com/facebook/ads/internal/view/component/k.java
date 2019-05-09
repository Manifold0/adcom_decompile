// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component;

import android.view.View$MeasureSpec;
import android.text.TextUtils;
import android.view.View$OnClickListener;
import android.text.TextUtils$TruncateAt;
import com.facebook.ads.internal.w.c.c;
import android.widget.ImageView$ScaleType;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.view.a.b;
import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.adapters.b.e;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;

public class k extends LinearLayout
{
    private static final int a;
    private static final int b;
    private static final int c;
    private static final int d;
    private static final int e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private ImageView j;
    private ImageView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private LinearLayout o;
    private final LinearLayout p;
    private final String q;
    private final e.a r;
    private final a.a s;
    @Nullable
    private b t;
    
    static {
        a = (int)(14.0f * x.b);
        b = (int)(x.b * 8.0f);
        c = (int)(10.0f * x.b);
        d = (int)(x.b * 8.0f);
        e = (int)(17.0f * x.b);
    }
    
    public k(final Context context, final String q, final e.a r, final a.a s) {
        super(context);
        this.setOrientation(1);
        this.q = q;
        this.r = r;
        this.s = s;
        this.f = new TextView(this.getContext());
        final LinearLayout$LayoutParams layoutParams = new LinearLayout$LayoutParams(-1, -2);
        layoutParams.topMargin = com.facebook.ads.internal.view.component.k.b;
        this.f.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.addView((View)this.f);
        (this.p = new LinearLayout(context)).setOrientation(0);
        this.p.setGravity(16);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-2, -2);
        linearLayout$LayoutParams.topMargin = com.facebook.ads.internal.view.component.k.b / 2;
        this.addView((View)this.p, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        final LinearLayout p4 = this.p;
        this.h = new TextView(this.getContext());
        final LinearLayout$LayoutParams layoutParams2 = new LinearLayout$LayoutParams(-2, -2);
        layoutParams2.leftMargin = com.facebook.ads.internal.view.component.k.b / 2;
        this.h.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
        (this.j = new ImageView(this.getContext())).setScaleType(ImageView$ScaleType.FIT_CENTER);
        this.j.setColorFilter(-1);
        this.j.setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.r));
        p4.addView((View)this.j, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(com.facebook.ads.internal.view.component.k.a, com.facebook.ads.internal.view.component.k.a));
        p4.addView((View)this.h);
        this.l = this.a();
        this.p.addView((View)this.l);
        final LinearLayout p5 = this.p;
        (this.i = new TextView(this.getContext())).setEllipsize(TextUtils$TruncateAt.END);
        this.i.setMaxLines(1);
        final LinearLayout$LayoutParams layoutParams3 = new LinearLayout$LayoutParams(-2, -2);
        layoutParams3.leftMargin = com.facebook.ads.internal.view.component.k.b / 2;
        this.i.setLayoutParams((ViewGroup$LayoutParams)layoutParams3);
        (this.k = new ImageView(this.getContext())).setScaleType(ImageView$ScaleType.FIT_CENTER);
        this.k.setColorFilter(-1);
        final ImageView k = this.k;
        com.facebook.ads.internal.w.c.b b;
        if (this.r.equals(com.facebook.ads.internal.adapters.b.e.a.a)) {
            b = com.facebook.ads.internal.w.c.b.t;
        }
        else {
            b = com.facebook.ads.internal.w.c.b.s;
        }
        k.setImageBitmap(com.facebook.ads.internal.w.c.c.a(b));
        p5.addView((View)this.k, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(com.facebook.ads.internal.view.component.k.a, com.facebook.ads.internal.view.component.k.a));
        p5.addView((View)this.i);
        this.n = this.a();
        this.p.addView((View)this.n);
        final LinearLayout p6 = this.p;
        (this.g = new TextView(this.getContext())).setEllipsize(TextUtils$TruncateAt.END);
        this.g.setMaxLines(1);
        this.g.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -2));
        p6.addView((View)this.g);
        this.m = this.a();
        this.p.addView((View)this.m);
        final LinearLayout p7 = this.p;
        (this.o = new LinearLayout(this.getContext())).setOrientation(0);
        this.o.setGravity(16);
        p7.addView((View)this.o, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -1));
        final ImageView imageView = new ImageView(this.getContext());
        imageView.setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.i));
        imageView.setColorFilter(-1);
        this.o.addView((View)imageView, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(com.facebook.ads.internal.view.component.k.a, com.facebook.ads.internal.view.component.k.a));
        final ImageView imageView2 = new ImageView(this.getContext());
        imageView2.setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.f));
        imageView2.setColorFilter(-1);
        final LinearLayout$LayoutParams linearLayout$LayoutParams2 = new LinearLayout$LayoutParams(com.facebook.ads.internal.view.component.k.a, com.facebook.ads.internal.view.component.k.a);
        linearLayout$LayoutParams2.leftMargin = com.facebook.ads.internal.view.component.k.c;
        this.o.addView((View)imageView2, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
        this.o.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                com.facebook.ads.internal.view.component.k.this.s.a(com.facebook.ads.internal.view.component.k.this.q, false, com.facebook.ads.internal.view.component.k.this.t);
            }
        });
        x.a((View)this, (View)this.o, com.facebook.ads.internal.view.component.k.d, com.facebook.ads.internal.view.component.k.e);
    }
    
    private TextView a() {
        final TextView textView = new TextView(this.getContext());
        textView.setText((CharSequence)"·");
        textView.setTextColor(-1);
        x.a(textView, false, 14);
        final LinearLayout$LayoutParams layoutParams = new LinearLayout$LayoutParams(-2, -1);
        layoutParams.leftMargin = com.facebook.ads.internal.view.component.k.b;
        layoutParams.rightMargin = com.facebook.ads.internal.view.component.k.b;
        textView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        return textView;
    }
    
    public void a(final String text, final boolean b, final int n, final int textColor) {
        this.f.setText((CharSequence)text);
        this.f.setTextColor(textColor);
        x.a(this.f, b, n);
        this.f.setMaxLines(2);
        this.f.setEllipsize(TextUtils$TruncateAt.END);
    }
    
    public void a(final boolean b) {
        if (b) {
            if (!TextUtils.isEmpty(this.h.getText())) {
                this.j.setVisibility(0);
                this.h.setVisibility(0);
                this.l.setVisibility(0);
            }
            if (!TextUtils.isEmpty(this.g.getText())) {
                this.g.setVisibility(0);
                this.m.setVisibility(0);
            }
            this.k.setVisibility(8);
            this.i.setVisibility(8);
            this.n.setVisibility(8);
            return;
        }
        if (!TextUtils.isEmpty(this.i.getText())) {
            this.k.setVisibility(0);
            this.i.setVisibility(0);
            this.n.setVisibility(0);
        }
        this.j.setVisibility(8);
        this.h.setVisibility(8);
        this.l.setVisibility(8);
        this.g.setVisibility(8);
        this.m.setVisibility(8);
    }
    
    public void b(final String text, final boolean b, int visibility, final int textColor) {
        final int n = 8;
        this.h.setText((CharSequence)text);
        this.h.setTextColor(textColor);
        x.a(this.h, b, visibility);
        final ImageView j = this.j;
        if (TextUtils.isEmpty((CharSequence)text)) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        j.setVisibility(visibility);
        final TextView h = this.h;
        if (TextUtils.isEmpty((CharSequence)text)) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        h.setVisibility(visibility);
        final TextView l = this.l;
        if (TextUtils.isEmpty((CharSequence)text)) {
            visibility = n;
        }
        else {
            visibility = 0;
        }
        l.setVisibility(visibility);
    }
    
    public void c(final String text, final boolean b, int visibility, final int textColor) {
        final int n = 8;
        this.i.setText((CharSequence)text);
        this.i.setTextColor(textColor);
        x.a(this.i, b, visibility);
        final ImageView k = this.k;
        if (TextUtils.isEmpty((CharSequence)text)) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        k.setVisibility(visibility);
        final TextView i = this.i;
        if (TextUtils.isEmpty((CharSequence)text)) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        i.setVisibility(visibility);
        final TextView n2 = this.n;
        if (TextUtils.isEmpty((CharSequence)text)) {
            visibility = n;
        }
        else {
            visibility = 0;
        }
        n2.setVisibility(visibility);
    }
    
    public void d(final String text, final boolean b, int n, final int textColor) {
        final int n2 = 8;
        this.g.setText((CharSequence)text);
        this.g.setTextColor(textColor);
        x.a(this.g, b, n);
        final TextView g = this.g;
        if (TextUtils.isEmpty((CharSequence)text)) {
            n = 8;
        }
        else {
            n = 0;
        }
        g.setVisibility(n);
        final TextView m = this.m;
        if (TextUtils.isEmpty((CharSequence)text)) {
            n = n2;
        }
        else {
            n = 0;
        }
        m.setVisibility(n);
    }
    
    protected void onLayout(final boolean b, int n, int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        if (!b) {
            return;
        }
        n = View$MeasureSpec.getSize(0);
        this.p.measure(n, n);
        n = this.p.getMeasuredWidth();
        n2 = n - n3;
        if (n2 > 0) {
            this.i.setMaxWidth(this.i.getWidth() - n2);
            this.g.setMaxWidth(this.g.getWidth() - n2);
            return;
        }
        this.i.setMaxWidth(n);
        this.g.setMaxWidth(n);
    }
    
    public void setAdReportingFlowListener(final b t) {
        this.t = t;
    }
}
