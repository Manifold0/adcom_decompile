package com.facebook.ads.internal.view.component.p058a;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.p025w.p026b.C2600x;

/* renamed from: com.facebook.ads.internal.view.component.a.i */
public class C2288i extends C2259c {
    /* renamed from: c */
    private static final int f5403c = Resources.getSystem().getDisplayMetrics().widthPixels;

    public C2288i(C2279e c2279e, C1876h c1876h) {
        super(c2279e, c1876h, true);
        View relativeLayout = new RelativeLayout(c2279e.m5850a());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(f5403c / 2, -2);
        layoutParams.addRule(12);
        Drawable gradientDrawable = new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{-1778384896, 0});
        gradientDrawable.setCornerRadius(0.0f);
        gradientDrawable.setGradientType(0);
        C2600x.m6681a(relativeLayout, gradientDrawable);
        new LinearLayout.LayoutParams(-2, -2).setMargins(a, 0, 0, 0);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2);
        layoutParams2.setMargins(0, 0, 0, 0);
        layoutParams2.weight = 1.0f;
        if (c2279e.m5857h() != null) {
            LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams3.setMargins(0, 0, 0, 0);
            relativeLayout.addView(c2279e.m5857h(), layoutParams3);
        }
        View d = c2279e.m5853d();
        LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(f5403c / 2, -1);
        layoutParams4.addRule(13);
        layoutParams4.addRule(9);
        addView(d, layoutParams4);
        addView(relativeLayout, layoutParams);
        if (c2279e.m5858i() != null) {
            LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(b, b);
            layoutParams5.addRule(12);
            layoutParams5.setMargins(a, a + c2279e.m5859j(), a, a);
            addView(c2279e.m5858i(), layoutParams5);
        }
    }

    /* renamed from: a */
    public boolean mo5555a() {
        return true;
    }

    public int getExactMediaWidthIfAvailable() {
        return f5403c / 2;
    }
}
