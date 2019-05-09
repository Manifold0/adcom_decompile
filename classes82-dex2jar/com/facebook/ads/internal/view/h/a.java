// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.h;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.LinearLayout$LayoutParams;
import android.text.TextUtils$TruncateAt;
import android.widget.TextView;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.internal.t.j;
import com.facebook.ads.internal.t.e;
import android.content.Context;
import android.widget.LinearLayout;

public class a extends LinearLayout
{
    public a(final Context context, final e e, final j j, final AdOptionsView adOptionsView) {
        super(context);
        final TextView textView = new TextView(this.getContext());
        j.a(textView);
        textView.setText((CharSequence)e.a("advertiser_name"));
        textView.setEllipsize(TextUtils$TruncateAt.END);
        textView.setMaxLines(1);
        final TextView textView2 = new TextView(this.getContext());
        j.b(textView2);
        textView2.setText((CharSequence)e.a("social_context"));
        final LinearLayout linearLayout = new LinearLayout(this.getContext());
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(0, -2);
        linearLayout$LayoutParams.weight = 1.0f;
        linearLayout.addView((View)textView, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        linearLayout.addView((View)adOptionsView, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -2));
        this.setOrientation(1);
        final LinearLayout$LayoutParams linearLayout$LayoutParams2 = new LinearLayout$LayoutParams(-1, -2);
        this.addView((View)linearLayout, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
        this.addView((View)textView2, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
    }
}
