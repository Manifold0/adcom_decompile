// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.annotation.SuppressLint;

@SuppressLint({ "ViewConstructor" })
public class t extends z
{
    private LinearLayout b;
    private LinearLayout c;
    private ay d;
    private az e;
    private TextView f;
    private TextView g;
    
    public t(final Context context, final v v) {
        super(context, v);
    }
    
    @Override
    protected View a() {
        final Context context = this.getContext();
        final int round = Math.round(this.getContext().getResources().getDisplayMetrics().density * 6.0f);
        (this.b = new LinearLayout(context)).setOrientation(0);
        this.b.setGravity(17);
        (this.c = new LinearLayout(context)).setOrientation(1);
        this.c.setGravity(8388627);
        (this.d = new ay(context)).setPadding(round, round, round, round);
        if (this.a.J.d()) {
            this.d.a(this.a.J);
        }
        (this.e = new az(context) {
            @Override
            protected void a(final MotionEvent motionEvent) {
                t.this.a.q().b(motionEvent.getX(), motionEvent.getY(), (float)super.getWidth(), (float)super.getHeight());
            }
        }).setPadding(round, round, round, round);
        if (this.a.K.d()) {
            this.e.a(this.a.K);
        }
        (this.f = new TextView(this.getContext())).setTextColor(-15264491);
        this.f.setTypeface((Typeface)null, 1);
        this.f.setGravity(8388611);
        this.f.setPadding(round, round, round, round / 2);
        (this.g = new TextView(this.getContext())).setTextColor(-15264491);
        this.g.setTypeface((Typeface)null, 1);
        this.g.setGravity(8388611);
        this.g.setPadding(round, 0, round, round);
        this.f.setTextSize(2, 14.0f);
        this.g.setTextSize(2, 11.0f);
        this.c.addView((View)this.f);
        this.c.addView((View)this.g);
        this.b.addView((View)this.d);
        this.b.addView((View)this.c, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(0, -2, 1.0f));
        this.b.addView((View)this.e);
        return (View)this.b;
    }
    
    public void a(final String text, final String text2) {
        this.f.setText((CharSequence)text);
        this.g.setText((CharSequence)text2);
    }
    
    @Override
    protected int b() {
        return 72;
    }
}
