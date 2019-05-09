// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.view.MotionEvent;
import android.view.View;
import com.chartboost.sdk.e;
import android.graphics.Typeface;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.ImageView$ScaleType;
import android.content.Context;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.widget.LinearLayout;

@SuppressLint({ "ViewConstructor" })
public class y extends LinearLayout
{
    final v a;
    final az b;
    private LinearLayout c;
    private ay d;
    private TextView e;
    private int f;
    
    public y(final Context context, final v a) {
        super(context);
        this.f = Integer.MIN_VALUE;
        this.a = a;
        final int round = Math.round(context.getResources().getDisplayMetrics().density * 8.0f);
        this.setOrientation(1);
        this.setGravity(17);
        (this.c = new LinearLayout(context)).setGravity(17);
        this.c.setOrientation(0);
        this.c.setPadding(round, round, round, round);
        (this.d = new ay(context)).setScaleType(ImageView$ScaleType.FIT_CENTER);
        this.d.setPadding(0, 0, round, 0);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-2, -2);
        a.a((ViewGroup$LayoutParams)linearLayout$LayoutParams, a.H, 1.0f);
        (this.e = new TextView(this.getContext())).setTextColor(-1);
        this.e.setTypeface((Typeface)null, 1);
        this.e.setGravity(17);
        final TextView e = this.e;
        float n;
        if (com.chartboost.sdk.e.a(context)) {
            n = 26.0f;
        }
        else {
            n = 16.0f;
        }
        e.setTextSize(2, n);
        this.c.addView((View)this.d, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        this.c.addView((View)this.e, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -2));
        (this.b = new az(this.getContext()) {
            @Override
            protected void a(final MotionEvent motionEvent) {
                y.this.b.setEnabled(false);
                y.this.a.q().g();
            }
        }).setContentDescription((CharSequence)"CBWatch");
        this.b.setPadding(0, 0, 0, round);
        this.b.a(ImageView$ScaleType.FIT_CENTER);
        this.b.setPadding(round, round, round, round);
        final LinearLayout$LayoutParams linearLayout$LayoutParams2 = new LinearLayout$LayoutParams(-2, -2);
        a.a((ViewGroup$LayoutParams)linearLayout$LayoutParams2, a.G, 1.0f);
        this.d.a(a.H);
        this.b.a(a.G);
        this.addView((View)this.c, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -2));
        this.addView((View)this.b, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
        this.a();
    }
    
    public void a() {
        this.a(this.a.s());
    }
    
    public void a(final String text, final int f) {
        this.e.setText((CharSequence)text);
        this.f = f;
        this.a(this.a.s());
    }
    
    public void a(final boolean b) {
        int f;
        if (b) {
            f = -16777216;
        }
        else {
            f = this.f;
        }
        this.setBackgroundColor(f);
    }
}
