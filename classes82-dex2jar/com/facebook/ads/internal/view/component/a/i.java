// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component.a;

import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.facebook.ads.internal.w.b.x;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable$Orientation;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.b.h;
import android.content.res.Resources;

public class i extends c
{
    private static final int c;
    
    static {
        c = Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    
    public i(final e e, final h h) {
        super(e, h, true);
        final RelativeLayout relativeLayout = new RelativeLayout(e.a());
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(i.c / 2, -2);
        relativeLayout$LayoutParams.addRule(12);
        final GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable$Orientation.BOTTOM_TOP, new int[] { -1778384896, 0 });
        gradientDrawable.setCornerRadius(0.0f);
        gradientDrawable.setGradientType(0);
        x.a((View)relativeLayout, (Drawable)gradientDrawable);
        new LinearLayout$LayoutParams(-2, -2).setMargins(i.a, 0, 0, 0);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(0, -2);
        linearLayout$LayoutParams.setMargins(0, 0, 0, 0);
        linearLayout$LayoutParams.weight = 1.0f;
        if (e.h() != null) {
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(-1, -2);
            relativeLayout$LayoutParams2.setMargins(0, 0, 0, 0);
            relativeLayout.addView((View)e.h(), (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
        }
        final View d = e.d();
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams3 = new RelativeLayout$LayoutParams(i.c / 2, -1);
        relativeLayout$LayoutParams3.addRule(13);
        relativeLayout$LayoutParams3.addRule(9);
        this.addView(d, (ViewGroup$LayoutParams)relativeLayout$LayoutParams3);
        this.addView((View)relativeLayout, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        if (e.i() != null) {
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams4 = new RelativeLayout$LayoutParams(i.b, i.b);
            relativeLayout$LayoutParams4.addRule(12);
            relativeLayout$LayoutParams4.setMargins(i.a, i.a + e.j(), i.a, i.a);
            this.addView(e.i(), (ViewGroup$LayoutParams)relativeLayout$LayoutParams4);
        }
    }
    
    @Override
    public boolean a() {
        return true;
    }
    
    @Override
    public int getExactMediaWidthIfAvailable() {
        return i.c / 2;
    }
}
