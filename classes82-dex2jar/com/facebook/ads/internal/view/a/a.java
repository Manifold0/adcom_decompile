// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.a;

import android.content.Context;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.TextView;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.view.View;
import android.view.View$OnClickListener;
import com.facebook.ads.internal.w.c.c;
import com.facebook.ads.internal.w.c.b;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.ads.internal.w.b.x;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

public class a extends RelativeLayout
{
    private static final int a;
    private static final int b;
    private static final int c;
    private static final int d;
    private static final int e;
    private static final int f;
    private static final int g;
    private static final int h;
    private static final int i;
    private static final int j;
    @Nullable
    private final e k;
    private final int l;
    private final int m;
    private final boolean n;
    
    static {
        a = (int)(x.b * 16.0f);
        b = (int)(8.0f * x.b);
        c = (int)(44.0f * x.b);
        d = (int)(10.0f * x.b);
        e = com.facebook.ads.internal.view.a.a.a - com.facebook.ads.internal.view.a.a.d;
        f = (int)(75.0f * x.b);
        g = (int)(25.0f * x.b);
        h = (int)(45.0f * x.b);
        i = (int)(15.0f * x.b);
        j = (int)(x.b * 16.0f);
    }
    
    private a(final a a) {
        super(a.a);
        this.k = a.b;
        int l;
        if (a.i) {
            l = com.facebook.ads.internal.view.a.a.f;
        }
        else {
            l = com.facebook.ads.internal.view.a.a.h;
        }
        this.l = l;
        int m;
        if (a.i) {
            m = com.facebook.ads.internal.view.a.a.g;
        }
        else {
            m = com.facebook.ads.internal.view.a.a.i;
        }
        this.m = m;
        this.n = a.k;
        this.setClickable(true);
        final LinearLayout linearLayout = new LinearLayout(this.getContext());
        linearLayout.setOrientation(0);
        if (a.h) {
            final ImageView imageView = new ImageView(this.getContext());
            imageView.setPadding(com.facebook.ads.internal.view.a.a.d, com.facebook.ads.internal.view.a.a.d, com.facebook.ads.internal.view.a.a.d, com.facebook.ads.internal.view.a.a.d);
            imageView.setScaleType(ImageView$ScaleType.FIT_CENTER);
            imageView.setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.q));
            imageView.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    if (com.facebook.ads.internal.view.a.a.this.k != null) {
                        if (!com.facebook.ads.internal.view.a.a.this.n) {
                            com.facebook.ads.internal.view.a.a.this.k.a(true);
                            return;
                        }
                        com.facebook.ads.internal.view.a.a.this.k.b();
                    }
                }
            });
            final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(com.facebook.ads.internal.view.a.a.c, com.facebook.ads.internal.view.a.a.c);
            linearLayout$LayoutParams.setMargins(com.facebook.ads.internal.view.a.a.e, com.facebook.ads.internal.view.a.a.e, com.facebook.ads.internal.view.a.a.e, com.facebook.ads.internal.view.a.a.e);
            linearLayout.addView((View)imageView, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        }
        final ImageView imageView2 = new ImageView(this.getContext());
        imageView2.setPadding(this.m, this.m, this.m, this.m);
        imageView2.setImageBitmap(com.facebook.ads.internal.w.c.c.a(a.f));
        imageView2.setColorFilter(-1);
        final LinearLayout$LayoutParams linearLayout$LayoutParams2 = new LinearLayout$LayoutParams(this.l, this.l);
        final GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColor(a.g);
        x.a((View)imageView2, (Drawable)gradientDrawable);
        linearLayout$LayoutParams2.gravity = 17;
        linearLayout$LayoutParams2.setMargins(com.facebook.ads.internal.view.a.a.a, 0, com.facebook.ads.internal.view.a.a.a, com.facebook.ads.internal.view.a.a.a);
        final TextView textView = new TextView(this.getContext());
        x.a(textView, true, 20);
        textView.setTextColor(-14934495);
        textView.setText((CharSequence)a.c);
        textView.setGravity(17);
        final LinearLayout$LayoutParams linearLayout$LayoutParams3 = new LinearLayout$LayoutParams(-1, -2);
        linearLayout$LayoutParams3.setMargins(com.facebook.ads.internal.view.a.a.a, 0, com.facebook.ads.internal.view.a.a.a, com.facebook.ads.internal.view.a.a.a);
        final TextView textView2 = new TextView(this.getContext());
        x.a(textView2, false, 16);
        textView2.setTextColor(-10459280);
        textView2.setText((CharSequence)a.d);
        textView2.setGravity(17);
        final LinearLayout$LayoutParams linearLayout$LayoutParams4 = new LinearLayout$LayoutParams(-1, -2);
        linearLayout$LayoutParams4.setMargins(com.facebook.ads.internal.view.a.a.a, 0, com.facebook.ads.internal.view.a.a.a, com.facebook.ads.internal.view.a.a.a);
        final LinearLayout linearLayout2 = new LinearLayout(this.getContext());
        linearLayout2.setOrientation(1);
        linearLayout2.setGravity(17);
        linearLayout2.addView((View)imageView2, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
        linearLayout2.addView((View)textView, (ViewGroup$LayoutParams)linearLayout$LayoutParams3);
        linearLayout2.addView((View)textView2, (ViewGroup$LayoutParams)linearLayout$LayoutParams4);
        if (a.j) {
            final f f = new f(this.getContext());
            f.a(a.e, com.facebook.ads.internal.w.c.b.p);
            f.setSelected(true);
            linearLayout2.addView((View)f, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -2));
        }
        final View footerView = this.getFooterView();
        x.a((View)linearLayout);
        x.a((View)linearLayout2);
        x.a(footerView);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-1, -2);
        relativeLayout$LayoutParams.addRule(10);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(-1, -1);
        relativeLayout$LayoutParams2.addRule(13);
        relativeLayout$LayoutParams2.addRule(3, ((View)linearLayout).getId());
        relativeLayout$LayoutParams2.addRule(2, footerView.getId());
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams3 = new RelativeLayout$LayoutParams(-1, -2);
        relativeLayout$LayoutParams3.addRule(12);
        relativeLayout$LayoutParams3.setMargins(com.facebook.ads.internal.view.a.a.a, 0, com.facebook.ads.internal.view.a.a.a, com.facebook.ads.internal.view.a.a.a);
        this.addView((View)linearLayout, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        this.addView((View)linearLayout2, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
        this.addView(footerView, (ViewGroup$LayoutParams)relativeLayout$LayoutParams3);
        int visibility;
        if (a.l) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        footerView.setVisibility(visibility);
    }
    
    private View getFooterView() {
        final ImageView imageView = new ImageView(this.getContext());
        imageView.setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.n));
        imageView.setColorFilter(-13272859);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(com.facebook.ads.internal.view.a.a.j, com.facebook.ads.internal.view.a.a.j);
        linearLayout$LayoutParams.gravity = 17;
        final TextView textView = new TextView(this.getContext());
        x.a(textView, false, 16);
        textView.setTextColor(-13272859);
        textView.setPadding(com.facebook.ads.internal.view.a.a.b, com.facebook.ads.internal.view.a.a.b, com.facebook.ads.internal.view.a.a.b, com.facebook.ads.internal.view.a.a.b);
        textView.setText((CharSequence)com.facebook.ads.internal.f.a.h(this.getContext()));
        final LinearLayout$LayoutParams linearLayout$LayoutParams2 = new LinearLayout$LayoutParams(-2, -2);
        linearLayout$LayoutParams2.gravity = 17;
        final LinearLayout linearLayout = new LinearLayout(this.getContext());
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        linearLayout.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (com.facebook.ads.internal.view.a.a.this.k != null) {
                    com.facebook.ads.internal.view.a.a.this.k.c();
                }
            }
        });
        linearLayout.addView((View)imageView, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        linearLayout.addView((View)textView, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
        return (View)linearLayout;
    }
    
    public static class a
    {
        private final Context a;
        private e b;
        private String c;
        private String d;
        private String e;
        private b f;
        private int g;
        private boolean h;
        private boolean i;
        private boolean j;
        private boolean k;
        private boolean l;
        
        public a(final Context a) {
            this.h = true;
            this.i = true;
            this.j = true;
            this.k = true;
            this.l = true;
            this.a = a;
        }
        
        public a a(final int g) {
            this.g = g;
            return this;
        }
        
        public a a(final e b) {
            this.b = b;
            return this;
        }
        
        public a a(final b f) {
            this.f = f;
            return this;
        }
        
        public a a(final String c) {
            this.c = c;
            return this;
        }
        
        public a a(final boolean h) {
            this.h = h;
            return this;
        }
        
        public com.facebook.ads.internal.view.a.a a() {
            return new com.facebook.ads.internal.view.a.a(this, null);
        }
        
        public a b(final String d) {
            this.d = d;
            return this;
        }
        
        public a b(final boolean i) {
            this.i = i;
            return this;
        }
        
        public a c(final String e) {
            this.e = e;
            return this;
        }
        
        public a c(final boolean j) {
            this.j = j;
            return this;
        }
        
        public a d(final boolean k) {
            this.k = k;
            return this;
        }
        
        public a e(final boolean l) {
            this.l = l;
            return this;
        }
    }
}
