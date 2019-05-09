package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.C1381h;
import com.chartboost.sdk.Libraries.CBUtility;

@SuppressLint({"ViewConstructor"})
/* renamed from: com.chartboost.sdk.impl.w */
public class C1470w extends C1456z {
    /* renamed from: b */
    private LinearLayout f3393b;
    /* renamed from: c */
    private ay f3394c;
    /* renamed from: d */
    private TextView f3395d;

    public C1470w(Context context, C1469v c1469v) {
        super(context, c1469v);
    }

    /* renamed from: a */
    protected View mo4316a() {
        Context context = getContext();
        int round = Math.round(getContext().getResources().getDisplayMetrics().density * 8.0f);
        this.f3393b = new LinearLayout(context);
        this.f3393b.setOrientation(0);
        this.f3393b.setGravity(17);
        int a = CBUtility.m3108a(36, context);
        this.f3394c = new ay(context);
        this.f3394c.setPadding(round, round, round, round);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
        this.f3394c.setScaleType(ScaleType.FIT_CENTER);
        this.f3395d = new TextView(context);
        this.f3395d.setPadding(round / 2, round, round, round);
        this.f3395d.setTextColor(-15264491);
        this.f3395d.setTextSize(2, 16.0f);
        this.f3395d.setTypeface(null, 1);
        this.f3395d.setGravity(17);
        this.f3393b.addView(this.f3394c, layoutParams);
        this.f3393b.addView(this.f3395d, new LinearLayout.LayoutParams(-2, -1));
        return this.f3393b;
    }

    /* renamed from: a */
    public void m3694a(C1381h c1381h) {
        this.f3394c.m3471a(c1381h);
        this.f3394c.setScaleType(ScaleType.FIT_CENTER);
    }

    /* renamed from: a */
    public void m3695a(String str) {
        this.f3395d.setText(str);
    }

    /* renamed from: b */
    protected int mo4317b() {
        return 48;
    }
}
