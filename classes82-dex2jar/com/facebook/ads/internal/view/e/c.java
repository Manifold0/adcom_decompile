// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.e;

import android.widget.ImageView;
import com.facebook.ads.internal.view.c.d;
import com.facebook.ads.internal.adapters.b.m;
import android.text.TextUtils$TruncateAt;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.LinearLayout$LayoutParams;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.widget.TextView;
import com.facebook.ads.internal.view.component.f;
import android.widget.LinearLayout;

public class c extends LinearLayout
{
    public static final int a;
    private static final int b;
    private f c;
    private TextView d;
    private TextView e;
    
    static {
        a = (int)(x.b * 32.0f);
        b = (int)(x.b * 8.0f);
    }
    
    public c(final Context context) {
        super(context);
        this.a(context);
    }
    
    public void a(final int textColor, final int textColor2) {
        this.d.setTextColor(textColor);
        this.e.setTextColor(textColor2);
    }
    
    public void a(final Context context) {
        this.setGravity(16);
        (this.c = new f(context)).setFullCircleCorners(true);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(com.facebook.ads.internal.view.e.c.a, com.facebook.ads.internal.view.e.c.a);
        linearLayout$LayoutParams.setMargins(0, 0, com.facebook.ads.internal.view.e.c.b, 0);
        this.addView((View)this.c, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        final LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        this.d = new TextView(context);
        final LinearLayout$LayoutParams linearLayout$LayoutParams2 = new LinearLayout$LayoutParams(-1, -2);
        x.a(this.d, true, 16);
        this.d.setEllipsize(TextUtils$TruncateAt.END);
        this.d.setSingleLine(true);
        x.a(this.e = new TextView(context), false, 14);
        linearLayout.addView((View)this.d);
        linearLayout.addView((View)this.e);
        this.addView((View)linearLayout, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
    }
    
    public void setPageDetails(final m m) {
        final d d = new d(this.c);
        d.a(com.facebook.ads.internal.view.e.c.a, com.facebook.ads.internal.view.e.c.a);
        d.a(m.b());
        this.d.setText((CharSequence)m.a());
        this.e.setText((CharSequence)m.d());
    }
}
