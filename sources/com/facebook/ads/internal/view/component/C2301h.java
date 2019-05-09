package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.p017t.C2114e;
import com.facebook.ads.internal.p017t.C2117j;
import com.facebook.ads.internal.p025w.p026b.C2600x;

/* renamed from: com.facebook.ads.internal.view.component.h */
public class C2301h extends LinearLayout {
    public C2301h(Context context, C2114e c2114e, C2117j c2117j) {
        super(context);
        float f = C2600x.f6420b;
        View linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        linearLayout.setVerticalGravity(16);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(Math.round(15.0f * f), Math.round(15.0f * f), Math.round(15.0f * f), Math.round(f * 15.0f));
        linearLayout.setLayoutParams(layoutParams);
        addView(linearLayout);
        CharSequence a = c2114e.m5309a("headline");
        TextView textView = new TextView(getContext());
        if (TextUtils.isEmpty(a)) {
            a = c2114e.m5309a("headline");
        }
        textView.setText(a);
        c2117j.m5361a(textView);
        textView.setEllipsize(TruncateAt.END);
        textView.setSingleLine(true);
        linearLayout.addView(textView);
        TextView textView2 = new TextView(getContext());
        textView2.setText(c2114e.m5336l());
        c2117j.m5365b(textView2);
        textView2.setEllipsize(TruncateAt.END);
        textView2.setMaxLines(2);
        linearLayout.addView(textView2);
    }
}
