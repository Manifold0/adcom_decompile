// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component.a;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable$Orientation;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import com.facebook.ads.internal.view.component.j;
import android.support.annotation.Nullable;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.facebook.ads.internal.w.b.x;
import android.content.Context;
import com.facebook.ads.internal.view.component.g;
import android.view.View;
import android.widget.RelativeLayout;

final class k extends RelativeLayout
{
    private final View a;
    private final g b;
    
    public k(final Context context, final View a) {
        super(context);
        this.a = a;
        x.a((View)(this.b = new g(context)));
    }
    
    public void a(final int n) {
        this.a.setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, n));
    }
    
    public void a(@Nullable final View view, @Nullable final View view2) {
        this.a(view, view2, 8, null, false);
    }
    
    public void a(@Nullable final View view, @Nullable final View view2, final int n, @Nullable final j j, final boolean b) {
        this.b.addView(this.a, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -2));
        if (view2 != null) {
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(c.b, c.b);
            relativeLayout$LayoutParams.addRule(n, this.a.getId());
            relativeLayout$LayoutParams.addRule(7, this.a.getId());
            relativeLayout$LayoutParams.setMargins(c.a, c.a, c.a, c.a);
            this.b.addView(view2, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        }
        final LinearLayout linearLayout = new LinearLayout(this.getContext());
        linearLayout.setOrientation(1);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(-1, -2);
        relativeLayout$LayoutParams2.addRule(8, this.a.getId());
        if (j != null) {
            if (b) {
                final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-1, -2);
                j.setAlignment(3);
                linearLayout$LayoutParams.setMargins(c.a / 2, c.a / 2, c.a / 2, c.a / 2);
                linearLayout.addView((View)j, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
                final GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable$Orientation.BOTTOM_TOP, new int[] { -1778384896, 0 });
                gradientDrawable.setCornerRadius(0.0f);
                gradientDrawable.setGradientType(0);
                x.a((View)linearLayout, (Drawable)gradientDrawable);
            }
            else {
                final RelativeLayout$LayoutParams relativeLayout$LayoutParams3 = new RelativeLayout$LayoutParams(-1, -2);
                relativeLayout$LayoutParams3.addRule(3, this.b.getId());
                relativeLayout$LayoutParams3.setMargins(0, c.a, 0, 0);
                j.setAlignment(17);
                this.addView((View)j, (ViewGroup$LayoutParams)relativeLayout$LayoutParams3);
            }
        }
        if (view != null) {
            linearLayout.addView(view, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -2));
        }
        this.b.addView((View)linearLayout, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
        this.addView((View)this.b, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -2));
    }
}
