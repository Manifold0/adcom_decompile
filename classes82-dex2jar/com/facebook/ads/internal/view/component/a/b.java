// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component.a;

import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.facebook.ads.internal.w.b.x;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable$Orientation;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.b.h;

public class b extends c
{
    public b(final e e, final h h, final boolean b) {
        int orientation = 1;
        super(e, h, true);
        final RelativeLayout relativeLayout = new RelativeLayout(e.a());
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-1, -2);
        relativeLayout$LayoutParams.addRule(12);
        final GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable$Orientation.BOTTOM_TOP, new int[] { -1778384896, 0 });
        gradientDrawable.setCornerRadius(0.0f);
        gradientDrawable.setGradientType(0);
        x.a((View)relativeLayout, (Drawable)gradientDrawable);
        final LinearLayout linearLayout = new LinearLayout(e.a());
        if (b) {
            orientation = 0;
        }
        linearLayout.setOrientation(orientation);
        linearLayout.setGravity(80);
        x.a((View)linearLayout);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(-1, -2);
        int a;
        if (e.h() == null) {
            a = b.a;
        }
        else {
            a = b.a / 2;
        }
        relativeLayout$LayoutParams2.setMargins(b.a, 0, b.a, a);
        int n;
        if (b) {
            n = -2;
        }
        else {
            n = -1;
        }
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(n, -2);
        int a2;
        if (b) {
            a2 = b.a;
        }
        else {
            a2 = 0;
        }
        int a3;
        if (b) {
            a3 = 0;
        }
        else {
            a3 = b.a;
        }
        linearLayout$LayoutParams.setMargins(a2, a3, 0, 0);
        int n2;
        if (b) {
            n2 = 0;
        }
        else {
            n2 = -1;
        }
        final LinearLayout$LayoutParams linearLayout$LayoutParams2 = new LinearLayout$LayoutParams(n2, -2);
        linearLayout$LayoutParams2.setMargins(0, 0, 0, 0);
        linearLayout$LayoutParams2.weight = 1.0f;
        linearLayout.addView((View)this.getTitleDescContainer(), (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
        linearLayout.addView((View)this.getCtaButton(), (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        relativeLayout.addView((View)linearLayout, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
        if (e.h() != null) {
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams3 = new RelativeLayout$LayoutParams(-1, -2);
            relativeLayout$LayoutParams3.setMargins(0, 0, 0, 0);
            relativeLayout$LayoutParams3.addRule(3, linearLayout.getId());
            relativeLayout.addView((View)e.h(), (ViewGroup$LayoutParams)relativeLayout$LayoutParams3);
        }
        this.addView(e.d(), (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
        this.addView((View)relativeLayout, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        if (e.i() != null) {
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams4 = new RelativeLayout$LayoutParams(b.b, b.b);
            relativeLayout$LayoutParams4.addRule(10);
            relativeLayout$LayoutParams4.addRule(11);
            relativeLayout$LayoutParams4.setMargins(b.a, b.a + e.j(), b.a, b.a);
            this.addView(e.i(), (ViewGroup$LayoutParams)relativeLayout$LayoutParams4);
        }
    }
    
    @Override
    public boolean a() {
        return true;
    }
}
