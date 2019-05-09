// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component;

import android.util.DisplayMetrics;
import com.facebook.ads.internal.view.w;
import android.graphics.drawable.Drawable;
import com.facebook.ads.internal.w.b.x;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils$TruncateAt;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.internal.t.j;
import com.facebook.ads.internal.t.e;
import android.content.Context;
import android.widget.TextView;
import com.facebook.ads.MediaView;
import android.widget.LinearLayout;

public class d extends LinearLayout
{
    private MediaView a;
    private b b;
    private TextView c;
    private LinearLayout d;
    
    public d(final Context context, final e e, final j j, final MediaView a, final AdOptionsView adOptionsView, final boolean b, int n) {
        super(context);
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.setVerticalGravity(16);
        this.setOrientation(1);
        final LinearLayout linearLayout = new LinearLayout(this.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setGravity(16);
        final LinearLayout$LayoutParams layoutParams = new LinearLayout$LayoutParams(-1, -1);
        layoutParams.setMargins(Math.round(15.0f * displayMetrics.density), Math.round(15.0f * displayMetrics.density), Math.round(15.0f * displayMetrics.density), Math.round(15.0f * displayMetrics.density));
        linearLayout.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.addView((View)linearLayout);
        this.d = new LinearLayout(this.getContext());
        final LinearLayout$LayoutParams layoutParams2 = new LinearLayout$LayoutParams(-1, 0);
        this.d.setOrientation(0);
        this.d.setGravity(16);
        layoutParams2.weight = 3.0f;
        this.d.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
        linearLayout.addView((View)this.d);
        this.a = a;
        int n2;
        if (b) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        n = (int)(3.0 / (n2 + 3) * (n - 30));
        final LinearLayout$LayoutParams layoutParams3 = new LinearLayout$LayoutParams(Math.round(n * displayMetrics.density), Math.round(n * displayMetrics.density));
        layoutParams3.setMargins(0, 0, Math.round(15.0f * displayMetrics.density), 0);
        this.a.setLayoutParams((ViewGroup$LayoutParams)layoutParams3);
        this.d.addView((View)this.a);
        final LinearLayout linearLayout2 = new LinearLayout(this.getContext());
        linearLayout2.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, -1));
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        this.d.addView((View)linearLayout2);
        this.b = new b(this.getContext(), e, j, adOptionsView);
        final LinearLayout$LayoutParams layoutParams4 = new LinearLayout$LayoutParams(-2, -1);
        layoutParams4.setMargins(0, 0, Math.round(15.0f * displayMetrics.density), 0);
        layoutParams4.weight = 0.5f;
        this.b.setLayoutParams((ViewGroup$LayoutParams)layoutParams4);
        linearLayout2.addView((View)this.b);
        (this.c = new TextView(this.getContext())).setPadding(Math.round(6.0f * displayMetrics.density), Math.round(6.0f * displayMetrics.density), Math.round(6.0f * displayMetrics.density), Math.round(6.0f * displayMetrics.density));
        this.c.setText((CharSequence)e.a("call_to_action"));
        this.c.setTextColor(j.f());
        this.c.setTextSize(14.0f);
        this.c.setTypeface(j.a(), 1);
        this.c.setMaxLines(2);
        this.c.setEllipsize(TextUtils$TruncateAt.END);
        this.c.setGravity(17);
        final GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(j.e());
        gradientDrawable.setCornerRadius(displayMetrics.density * 5.0f);
        gradientDrawable.setStroke(1, j.g());
        x.a((View)this.c, (Drawable)gradientDrawable);
        final LinearLayout$LayoutParams layoutParams5 = new LinearLayout$LayoutParams(-2, -2);
        layoutParams5.weight = 0.25f;
        this.c.setLayoutParams((ViewGroup$LayoutParams)layoutParams5);
        if (!e.h()) {
            this.c.setVisibility(4);
        }
        linearLayout2.addView((View)this.c);
        if (b) {
            final w w = new w(this.getContext());
            w.setText((CharSequence)e.l());
            j.b(w);
            w.setMinTextSize((float)(j.i() - 1));
            final LinearLayout$LayoutParams layoutParams6 = new LinearLayout$LayoutParams(-1, 0);
            layoutParams6.weight = 1.0f;
            w.setLayoutParams((ViewGroup$LayoutParams)layoutParams6);
            w.setGravity(80);
            linearLayout.addView((View)w);
        }
    }
    
    public TextView getCallToActionView() {
        return this.c;
    }
    
    public MediaView getIconView() {
        return this.a;
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        final TextView titleTextView = this.b.getTitleTextView();
        if (titleTextView.getLayout().getLineEnd(titleTextView.getLineCount() - 1) < this.b.getMinVisibleTitleCharacters()) {
            this.d.removeView((View)this.a);
            super.onMeasure(n, n2);
        }
    }
}
