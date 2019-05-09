// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component;

import android.text.TextUtils$TruncateAt;
import android.text.TextUtils;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.t.j;
import com.facebook.ads.internal.t.e;
import android.content.Context;
import android.widget.LinearLayout;

public class h extends LinearLayout
{
    public h(final Context context, final e e, final j j) {
        super(context);
        final float b = x.b;
        final LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        linearLayout.setVerticalGravity(16);
        final LinearLayout$LayoutParams layoutParams = new LinearLayout$LayoutParams(-1, -1);
        layoutParams.setMargins(Math.round(15.0f * b), Math.round(15.0f * b), Math.round(15.0f * b), Math.round(b * 15.0f));
        linearLayout.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.addView((View)linearLayout);
        final String a = e.a("headline");
        final TextView textView = new TextView(this.getContext());
        String a2 = a;
        if (TextUtils.isEmpty((CharSequence)a)) {
            a2 = e.a("headline");
        }
        textView.setText((CharSequence)a2);
        j.a(textView);
        textView.setEllipsize(TextUtils$TruncateAt.END);
        textView.setSingleLine(true);
        linearLayout.addView((View)textView);
        final TextView textView2 = new TextView(this.getContext());
        textView2.setText((CharSequence)e.l());
        j.b(textView2);
        textView2.setEllipsize(TextUtils$TruncateAt.END);
        textView2.setMaxLines(2);
        linearLayout.addView((View)textView2);
    }
}
