package com.facebook.ads.internal.view.component.p058a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.view.component.C2300g;
import com.facebook.ads.internal.view.component.C2303j;

/* renamed from: com.facebook.ads.internal.view.component.a.k */
final class C2290k extends RelativeLayout {
    /* renamed from: a */
    private final View f5407a;
    /* renamed from: b */
    private final C2300g f5408b;

    public C2290k(Context context, View view) {
        super(context);
        this.f5407a = view;
        this.f5408b = new C2300g(context);
        C2600x.m6679a(this.f5408b);
    }

    /* renamed from: a */
    public void m5904a(int i) {
        this.f5407a.setLayoutParams(new LayoutParams(-1, i));
    }

    /* renamed from: a */
    public void m5905a(@Nullable View view, @Nullable View view2) {
        m5906a(view, view2, 8, null, false);
    }

    /* renamed from: a */
    public void m5906a(@Nullable View view, @Nullable View view2, int i, @Nullable C2303j c2303j, boolean z) {
        this.f5408b.addView(this.f5407a, new LayoutParams(-1, -2));
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams = new LayoutParams(C2259c.f5323b, C2259c.f5323b);
            layoutParams.addRule(i, this.f5407a.getId());
            layoutParams.addRule(7, this.f5407a.getId());
            layoutParams.setMargins(C2259c.f5322a, C2259c.f5322a, C2259c.f5322a, C2259c.f5322a);
            this.f5408b.addView(view2, layoutParams);
        }
        View linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        layoutParams2.addRule(8, this.f5407a.getId());
        if (c2303j != null) {
            ViewGroup.LayoutParams layoutParams3;
            if (z) {
                layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
                c2303j.setAlignment(3);
                layoutParams3.setMargins(C2259c.f5322a / 2, C2259c.f5322a / 2, C2259c.f5322a / 2, C2259c.f5322a / 2);
                linearLayout.addView(c2303j, layoutParams3);
                Drawable gradientDrawable = new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{-1778384896, 0});
                gradientDrawable.setCornerRadius(0.0f);
                gradientDrawable.setGradientType(0);
                C2600x.m6681a(linearLayout, gradientDrawable);
            } else {
                layoutParams3 = new LayoutParams(-1, -2);
                layoutParams3.addRule(3, this.f5408b.getId());
                layoutParams3.setMargins(0, C2259c.f5322a, 0, 0);
                c2303j.setAlignment(17);
                addView(c2303j, layoutParams3);
            }
        }
        if (view != null) {
            linearLayout.addView(view, new LayoutParams(-1, -2));
        }
        this.f5408b.addView(linearLayout, layoutParams2);
        addView(this.f5408b, new LayoutParams(-1, -2));
    }
}
