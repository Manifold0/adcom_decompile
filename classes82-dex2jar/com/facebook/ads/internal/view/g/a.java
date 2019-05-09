// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.g;

import com.facebook.ads.internal.view.c.d;
import java.util.Map;
import java.util.HashMap;
import com.facebook.ads.internal.adapters.b.q;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable$Orientation;
import android.widget.LinearLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import com.facebook.ads.internal.view.i.b.aa;
import android.widget.RelativeLayout$LayoutParams;
import android.view.View;
import com.facebook.ads.internal.view.component.f;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.adapters.b.h;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.widget.RelativeLayout;
import android.widget.ImageView;
import com.facebook.ads.internal.view.component.j;
import android.widget.LinearLayout;

public class a extends LinearLayout
{
    private static final int a;
    private static final int b;
    private final j c;
    private final ImageView d;
    private final RelativeLayout e;
    private final com.facebook.ads.internal.view.component.a f;
    private final int g;
    
    static {
        a = (int)(12.0f * x.b);
        b = (int)(16.0f * x.b);
    }
    
    public a(final Context context, final int g, final h h, final c c, final com.facebook.ads.internal.view.a.a a, final boolean b, final boolean b2, final com.facebook.ads.internal.x.a a2, final w w) {
        super(context);
        this.g = g;
        x.a((View)(this.d = new f(context)), 0);
        x.a((View)this.d);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(g, g);
        relativeLayout$LayoutParams.addRule(15);
        relativeLayout$LayoutParams.addRule(9);
        relativeLayout$LayoutParams.setMargins(0, 0, com.facebook.ads.internal.view.g.a.a, 0);
        if (b2) {
            this.d.setVisibility(8);
        }
        (this.c = new j(context, h, true, b, true)).setAlignment(8388611);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(-2, -2);
        relativeLayout$LayoutParams2.addRule(1, this.d.getId());
        relativeLayout$LayoutParams2.addRule(15);
        (this.f = new com.facebook.ads.internal.view.component.a(context, true, false, aa.e.a(), h, c, a, a2, w)).setVisibility(8);
        x.a((View)(this.e = new RelativeLayout(context)));
        this.e.addView((View)this.d, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        this.e.addView((View)this.c, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
        this.addView((View)this.e, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -2));
        final GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable$Orientation.TOP_BOTTOM, new int[] { 0, -15658735 });
        gradientDrawable.setCornerRadius(0.0f);
        x.a((View)this, (Drawable)gradientDrawable);
    }
    
    public void a() {
        this.f.setVisibility(0);
    }
    
    public void a(int b) {
        final int n = -1;
        int orientation = 1;
        x.b((View)this.f);
        if (b == 1) {
            b = 1;
        }
        else {
            b = 0;
        }
        if (b == 0) {
            orientation = 0;
        }
        this.setOrientation(orientation);
        int n2;
        if (b != 0) {
            n2 = -1;
        }
        else {
            n2 = 0;
        }
        final LinearLayout$LayoutParams layoutParams = new LinearLayout$LayoutParams(n2, -2);
        layoutParams.weight = 1.0f;
        int n3;
        if (b != 0) {
            n3 = n;
        }
        else {
            n3 = -2;
        }
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(n3, -2);
        int b2;
        if (b != 0) {
            b2 = 0;
        }
        else {
            b2 = com.facebook.ads.internal.view.g.a.b;
        }
        if (b != 0) {
            b = com.facebook.ads.internal.view.g.a.b;
        }
        else {
            b = 0;
        }
        linearLayout$LayoutParams.setMargins(b2, b, 0, 0);
        linearLayout$LayoutParams.gravity = 80;
        this.e.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.addView((View)this.f, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
    }
    
    public void setInfo(final q q) {
        this.c.a(q.g().a(), q.g().b(), null, false, false);
        this.f.a(q.h(), q.a(), new HashMap<String, String>());
        new d(this.d).a(this.g, this.g).a(q.f().b());
    }
}
