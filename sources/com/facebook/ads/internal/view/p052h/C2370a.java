package com.facebook.ads.internal.view.p052h;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.internal.p017t.C2114e;
import com.facebook.ads.internal.p017t.C2117j;

/* renamed from: com.facebook.ads.internal.view.h.a */
public class C2370a extends LinearLayout {
    public C2370a(Context context, C2114e c2114e, C2117j c2117j, AdOptionsView adOptionsView) {
        super(context);
        TextView textView = new TextView(getContext());
        c2117j.m5361a(textView);
        textView.setText(c2114e.m5309a("advertiser_name"));
        textView.setEllipsize(TruncateAt.END);
        textView.setMaxLines(1);
        TextView textView2 = new TextView(getContext());
        c2117j.m5365b(textView2);
        textView2.setText(c2114e.m5309a("social_context"));
        View linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.weight = 1.0f;
        linearLayout.addView(textView, layoutParams);
        linearLayout.addView(adOptionsView, new LinearLayout.LayoutParams(-2, -2));
        setOrientation(1);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        addView(linearLayout, layoutParams2);
        addView(textView2, layoutParams2);
    }
}
