// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component.a.a;

import com.facebook.ads.internal.view.component.j;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.graphics.drawable.ColorDrawable;
import android.widget.LinearLayout;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.content.Context;
import com.facebook.ads.internal.view.e.a.a;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.view.component.a.e;
import com.facebook.ads.internal.w.b.x;

public class d extends b
{
    private static final int c;
    private static final int d;
    
    static {
        c = (int)(20.0f * x.b);
        d = (int)(16.0f * x.b);
    }
    
    d(final com.facebook.ads.internal.view.component.a.e e, final h h, final String s, final com.facebook.ads.internal.view.e.a.a a) {
        super(e, h, false, s, a);
    }
    
    @Override
    protected void a(final Context context) {
        final j titleDescContainer = this.getTitleDescContainer();
        titleDescContainer.setAlignment(3);
        titleDescContainer.setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -2));
        titleDescContainer.setPadding(0, 0, 0, com.facebook.ads.internal.view.component.a.a.d.c);
        this.getCtaButton().setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -2));
        final LinearLayout linearLayout = new LinearLayout(context);
        x.a((View)linearLayout, (Drawable)new ColorDrawable(-1));
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, -2);
        layoutParams.addRule(3, this.getMediaContainer().getId());
        linearLayout.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(com.facebook.ads.internal.view.component.a.a.d.d, com.facebook.ads.internal.view.component.a.a.d.d, com.facebook.ads.internal.view.component.a.a.d.d, com.facebook.ads.internal.view.component.a.a.d.d);
        linearLayout.addView((View)titleDescContainer);
        linearLayout.addView((View)this.getCtaButton());
        this.addView((View)this.getMediaContainer());
        this.addView((View)linearLayout);
    }
    
    @Override
    protected boolean b() {
        return false;
    }
}
