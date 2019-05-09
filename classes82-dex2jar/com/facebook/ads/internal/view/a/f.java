// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.a;

import com.facebook.ads.internal.w.c.c;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.w.c.b;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.LinearLayout$LayoutParams;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class f extends LinearLayout
{
    private static final int a;
    private static final int b;
    private static final int c;
    private static final int d;
    private boolean e;
    private final ImageView f;
    private final TextView g;
    
    static {
        a = (int)(x.b * 16.0f);
        b = (int)(x.b * 12.0f);
        c = (int)(x.b * 12.0f);
        d = (int)(x.b * 16.0f);
    }
    
    public f(final Context context) {
        super(context);
        this.e = false;
        this.setOrientation(0);
        this.setPadding(com.facebook.ads.internal.view.a.f.a, com.facebook.ads.internal.view.a.f.b, com.facebook.ads.internal.view.a.f.a, com.facebook.ads.internal.view.a.f.b);
        this.f = new ImageView(this.getContext());
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(com.facebook.ads.internal.view.a.f.d, com.facebook.ads.internal.view.a.f.d);
        linearLayout$LayoutParams.gravity = 17;
        this.g = new TextView(this.getContext());
        final LinearLayout$LayoutParams linearLayout$LayoutParams2 = new LinearLayout$LayoutParams(-2, -2);
        this.addView((View)this.f, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        this.addView((View)this.g, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
        this.b();
    }
    
    private void b() {
        final GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        int color;
        if (this.e) {
            color = -13272859;
        }
        else {
            color = -1315344;
        }
        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(50.0f);
        x.a((View)this, (Drawable)gradientDrawable);
        x.a(this.g, false, 14);
        int n;
        if (this.e) {
            n = -1;
        }
        else {
            n = -10459280;
        }
        this.g.setTextColor(n);
        this.f.setColorFilter(n);
    }
    
    public void a() {
        this.setSelected(!this.e);
    }
    
    public void a(final String text, @Nullable final b b) {
        this.g.setText((CharSequence)text);
        if (b != null) {
            this.f.setImageBitmap(com.facebook.ads.internal.w.c.c.a(b));
            this.f.setVisibility(0);
            this.g.setPadding(com.facebook.ads.internal.view.a.f.c, 0, 0, 0);
        }
        else {
            this.f.setVisibility(8);
            this.g.setPadding(0, 0, 0, 0);
        }
        this.b();
    }
    
    public void setSelected(final boolean e) {
        this.e = e;
        this.b();
    }
}
