// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component.a;

import android.widget.ImageView;
import com.facebook.ads.internal.view.c.d;
import android.graphics.Typeface;
import java.util.Map;
import java.util.HashMap;
import com.facebook.ads.internal.adapters.b.l;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.LinearLayout$LayoutParams;
import com.facebook.ads.internal.view.component.a;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.w.b.x;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.view.component.f;
import com.facebook.ads.internal.view.component.j;
import android.widget.LinearLayout;

public class n extends LinearLayout
{
    public static final int a;
    private static final int b;
    private static final int c;
    private static final int d;
    private static final int e;
    private static final int f;
    private final j g;
    private final f h;
    @Nullable
    private m i;
    
    static {
        a = (int)(x.b * 275.0f);
        b = (int)(x.b * 56.0f);
        c = (int)(x.b * 4.0f);
        d = (int)(x.b * 8.0f);
        e = (int)(x.b * 16.0f);
        f = (int)(x.b * 20.0f);
    }
    
    public n(final e e, h h, final a.a a) {
        super(e.a());
        this.setOrientation(1);
        this.setGravity(17);
        (this.h = new f(e.a())).setFullCircleCorners(true);
        this.setupIconView(e);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(n.b, n.b);
        this.addView((View)this.h, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        linearLayout$LayoutParams.bottomMargin = n.c;
        x.a((View)(this.g = new j(this.getContext(), h, true, true, false)));
        this.g.setTitleGravity(17);
        this.g.setDescriptionGravity(17);
        this.g.a(true, 17);
        final LinearLayout$LayoutParams linearLayout$LayoutParams2 = new LinearLayout$LayoutParams(-1, -2);
        linearLayout$LayoutParams2.setMargins(n.e, 0, n.e, n.c);
        this.addView((View)this.g, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
        x.a((View)this.g);
        final LinearLayout$LayoutParams linearLayout$LayoutParams3 = new LinearLayout$LayoutParams(-2, -2);
        linearLayout$LayoutParams3.topMargin = n.f;
        linearLayout$LayoutParams3.bottomMargin = n.c;
        if (e.k() == 1) {
            this.addView((View)(this.i = new m(e, e.g().d().get(0).b().b(), h, a)), (ViewGroup$LayoutParams)linearLayout$LayoutParams3);
            return;
        }
        h = new h();
        h.a(654311423);
        final a a2 = new a(e.a(), true, false, "com.facebook.ads.interstitial.clicked", h, e.b(), e.c(), e.e(), e.f());
        a2.a(e.g().d().get(0).b(), e.g().c(), new HashMap<String, String>(), a);
        a2.setPadding(n.d, n.c, n.d, n.c);
        a2.setBackgroundColor(0);
        a2.setTextColor(-1);
        a2.setTypeface(Typeface.defaultFromStyle(1));
        this.addView((View)a2, (ViewGroup$LayoutParams)linearLayout$LayoutParams3);
    }
    
    private void setupIconView(final e e) {
        final d d = new d(this.h);
        d.a(n.b, n.b);
        d.a(e.g().a().b());
    }
    
    public void a(final String s, final String s2, final String s3, final boolean b, final boolean b2) {
        this.g.a(s, s2, s3, b, b2);
    }
    
    @Nullable
    public m getSwipeUpCtaButton() {
        return this.i;
    }
}
