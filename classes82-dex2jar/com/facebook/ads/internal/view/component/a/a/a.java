// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component.a.a;

import com.facebook.ads.internal.view.component.j;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable$Orientation;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.content.Context;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.view.component.a.e;
import com.facebook.ads.internal.w.b.x;

public class a extends b
{
    private static final int c;
    
    static {
        c = (int)(12.0f * x.b);
    }
    
    a(final com.facebook.ads.internal.view.component.a.e e, final h h, final String s, final com.facebook.ads.internal.view.e.a.a a) {
        super(e, h, true, s, a);
    }
    
    @Override
    protected void a(final Context context) {
        final j titleDescContainer = this.getTitleDescContainer();
        titleDescContainer.setAlignment(3);
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, -2);
        layoutParams.addRule(8, this.getMediaContainer().getId());
        titleDescContainer.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        titleDescContainer.setPadding(a.c, a.c, a.c, a.c);
        final GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable$Orientation.TOP_BOTTOM, new int[] { 0, -15658735 });
        gradientDrawable.setCornerRadius(0.0f);
        x.a((View)titleDescContainer, (Drawable)gradientDrawable);
        final RelativeLayout$LayoutParams layoutParams2 = new RelativeLayout$LayoutParams(-1, -2);
        layoutParams2.addRule(3, this.getMediaContainer().getId());
        this.getCtaButton().setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
        this.addView((View)this.getMediaContainer());
        this.addView((View)titleDescContainer);
        this.addView((View)this.getCtaButton());
    }
    
    @Override
    protected boolean d() {
        return false;
    }
    
    @Override
    protected boolean e() {
        return false;
    }
}
