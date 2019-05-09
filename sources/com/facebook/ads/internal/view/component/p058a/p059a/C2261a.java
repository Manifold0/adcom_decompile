package com.facebook.ads.internal.view.component.p058a.p059a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.view.component.p058a.C2279e;
import com.facebook.ads.internal.view.p061e.p062a.C2315a;

/* renamed from: com.facebook.ads.internal.view.component.a.a.a */
public class C2261a extends C2260b {
    /* renamed from: c */
    private static final int f5347c = ((int) (12.0f * C2600x.f6420b));

    C2261a(C2279e c2279e, C1876h c1876h, String str, C2315a c2315a) {
        super(c2279e, c1876h, true, str, c2315a);
    }

    /* renamed from: a */
    protected void mo5557a(Context context) {
        View titleDescContainer = getTitleDescContainer();
        titleDescContainer.setAlignment(3);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(8, getMediaContainer().getId());
        titleDescContainer.setLayoutParams(layoutParams);
        titleDescContainer.setPadding(f5347c, f5347c, f5347c, f5347c);
        Drawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, -15658735});
        gradientDrawable.setCornerRadius(0.0f);
        C2600x.m6681a(titleDescContainer, gradientDrawable);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, getMediaContainer().getId());
        getCtaButton().setLayoutParams(layoutParams);
        addView(getMediaContainer());
        addView(titleDescContainer);
        addView(getCtaButton());
    }

    /* renamed from: d */
    protected boolean mo5558d() {
        return false;
    }

    /* renamed from: e */
    protected boolean mo5556e() {
        return false;
    }
}
