package com.facebook.ads.internal.view.component.p058a;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.p025w.p026b.C2600x;

/* renamed from: com.facebook.ads.internal.view.component.a.b */
public class C2275b extends C2259c {
    public C2275b(C2279e c2279e, C1876h c1876h, boolean z) {
        int i = 1;
        super(c2279e, c1876h, true);
        View relativeLayout = new RelativeLayout(c2279e.m5850a());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12);
        Drawable gradientDrawable = new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{-1778384896, 0});
        gradientDrawable.setCornerRadius(0.0f);
        gradientDrawable.setGradientType(0);
        C2600x.m6681a(relativeLayout, gradientDrawable);
        View linearLayout = new LinearLayout(c2279e.m5850a());
        if (z) {
            i = 0;
        }
        linearLayout.setOrientation(i);
        linearLayout.setGravity(80);
        C2600x.m6679a(linearLayout);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(a, 0, a, c2279e.m5857h() == null ? a : a / 2);
        LayoutParams layoutParams3 = new LinearLayout.LayoutParams(z ? -2 : -1, -2);
        layoutParams3.setMargins(z ? a : 0, z ? 0 : a, 0, 0);
        LayoutParams layoutParams4 = new LinearLayout.LayoutParams(z ? 0 : -1, -2);
        layoutParams4.setMargins(0, 0, 0, 0);
        layoutParams4.weight = 1.0f;
        linearLayout.addView(getTitleDescContainer(), layoutParams4);
        linearLayout.addView(getCtaButton(), layoutParams3);
        relativeLayout.addView(linearLayout, layoutParams2);
        if (c2279e.m5857h() != null) {
            LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams5.setMargins(0, 0, 0, 0);
            layoutParams5.addRule(3, linearLayout.getId());
            relativeLayout.addView(c2279e.m5857h(), layoutParams5);
        }
        addView(c2279e.m5853d(), new RelativeLayout.LayoutParams(-1, -1));
        addView(relativeLayout, layoutParams);
        if (c2279e.m5858i() != null) {
            layoutParams5 = new RelativeLayout.LayoutParams(b, b);
            layoutParams5.addRule(10);
            layoutParams5.addRule(11);
            layoutParams5.setMargins(a, a + c2279e.m5859j(), a, a);
            addView(c2279e.m5858i(), layoutParams5);
        }
    }

    /* renamed from: a */
    public boolean mo5555a() {
        return true;
    }
}
