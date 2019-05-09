package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.internal.p017t.C2114e;
import com.facebook.ads.internal.p017t.C2117j;
import com.facebook.ads.internal.view.C2552v;
import com.facebook.ads.internal.view.C2553w;

/* renamed from: com.facebook.ads.internal.view.component.b */
public class C2296b extends LinearLayout {
    /* renamed from: a */
    private C2552v f5433a = new C2552v(getContext(), 2);
    /* renamed from: b */
    private int f5434b;

    public C2296b(Context context, C2114e c2114e, C2117j c2117j, AdOptionsView adOptionsView) {
        int i = 21;
        super(context);
        setOrientation(1);
        setVerticalGravity(16);
        this.f5433a.setMinTextSize((float) (c2117j.m5377h() - 2));
        this.f5433a.setText(c2114e.m5309a("headline"));
        c2117j.m5361a(this.f5433a);
        View linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.weight = 1.0f;
        linearLayout.addView(this.f5433a, layoutParams);
        linearLayout.addView(adOptionsView, new LinearLayout.LayoutParams(-2, -2));
        if (c2114e.m5309a("headline") != null) {
            i = Math.min(c2114e.m5309a("headline").length(), 21);
        }
        this.f5434b = i;
        addView(linearLayout, new LinearLayout.LayoutParams(-1, -2));
        View linearLayout2 = new LinearLayout(context);
        TextView c2553w = new C2553w(context);
        c2553w.setText(c2114e.m5309a("social_context"));
        c2117j.m5365b(c2553w);
        linearLayout2.addView(c2553w);
        addView(linearLayout2);
    }

    public int getMinVisibleTitleCharacters() {
        return this.f5434b;
    }

    public TextView getTitleTextView() {
        return this.f5433a;
    }
}
