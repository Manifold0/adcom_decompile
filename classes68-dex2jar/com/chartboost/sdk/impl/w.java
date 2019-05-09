// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import com.chartboost.sdk.Libraries.h;
import android.view.ViewGroup$LayoutParams;
import android.graphics.Typeface;
import android.widget.ImageView$ScaleType;
import android.widget.LinearLayout$LayoutParams;
import com.chartboost.sdk.Libraries.CBUtility;
import android.view.View;
import android.content.Context;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.annotation.SuppressLint;

@SuppressLint({ "ViewConstructor" })
public class w extends z
{
    private LinearLayout b;
    private ay c;
    private TextView d;
    
    public w(final Context context, final v v) {
        super(context, v);
    }
    
    @Override
    protected View a() {
        final Context context = this.getContext();
        final int round = Math.round(this.getContext().getResources().getDisplayMetrics().density * 8.0f);
        (this.b = new LinearLayout(context)).setOrientation(0);
        this.b.setGravity(17);
        final int a = CBUtility.a(36, context);
        (this.c = new ay(context)).setPadding(round, round, round, round);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(a, a);
        this.c.setScaleType(ImageView$ScaleType.FIT_CENTER);
        (this.d = new TextView(context)).setPadding(round / 2, round, round, round);
        this.d.setTextColor(-15264491);
        this.d.setTextSize(2, 16.0f);
        this.d.setTypeface((Typeface)null, 1);
        this.d.setGravity(17);
        this.b.addView((View)this.c, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        this.b.addView((View)this.d, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -1));
        return (View)this.b;
    }
    
    public void a(final h h) {
        this.c.a(h);
        this.c.setScaleType(ImageView$ScaleType.FIT_CENTER);
    }
    
    public void a(final String text) {
        this.d.setText((CharSequence)text);
    }
    
    @Override
    protected int b() {
        return 48;
    }
}
