package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

@SuppressLint({"ViewConstructor"})
/* renamed from: com.chartboost.sdk.impl.t */
public class C1457t extends C1456z {
    /* renamed from: b */
    private LinearLayout f3327b;
    /* renamed from: c */
    private LinearLayout f3328c;
    /* renamed from: d */
    private ay f3329d;
    /* renamed from: e */
    private az f3330e;
    /* renamed from: f */
    private TextView f3331f;
    /* renamed from: g */
    private TextView f3332g;

    public C1457t(Context context, C1469v c1469v) {
        super(context, c1469v);
    }

    /* renamed from: a */
    protected View mo4316a() {
        Context context = getContext();
        int round = Math.round(getContext().getResources().getDisplayMetrics().density * 6.0f);
        this.f3327b = new LinearLayout(context);
        this.f3327b.setOrientation(0);
        this.f3327b.setGravity(17);
        this.f3328c = new LinearLayout(context);
        this.f3328c.setOrientation(1);
        this.f3328c.setGravity(8388627);
        this.f3329d = new ay(context);
        this.f3329d.setPadding(round, round, round, round);
        if (this.a.f3374J.m3155d()) {
            this.f3329d.m3471a(this.a.f3374J);
        }
        this.f3330e = new az(this, context) {
            /* renamed from: a */
            final /* synthetic */ C1457t f3323a;

            /* renamed from: a */
            protected void mo4279a(MotionEvent motionEvent) {
                this.f3323a.a.m3687q().m3665b(motionEvent.getX(), motionEvent.getY(), (float) super.getWidth(), (float) super.getHeight());
            }
        };
        this.f3330e.setPadding(round, round, round, round);
        if (this.a.f3375K.m3155d()) {
            this.f3330e.m3342a(this.a.f3375K);
        }
        this.f3331f = new TextView(getContext());
        this.f3331f.setTextColor(-15264491);
        this.f3331f.setTypeface(null, 1);
        this.f3331f.setGravity(GravityCompat.START);
        this.f3331f.setPadding(round, round, round, round / 2);
        this.f3332g = new TextView(getContext());
        this.f3332g.setTextColor(-15264491);
        this.f3332g.setTypeface(null, 1);
        this.f3332g.setGravity(GravityCompat.START);
        this.f3332g.setPadding(round, 0, round, round);
        this.f3331f.setTextSize(2, 14.0f);
        this.f3332g.setTextSize(2, 11.0f);
        this.f3328c.addView(this.f3331f);
        this.f3328c.addView(this.f3332g);
        this.f3327b.addView(this.f3329d);
        this.f3327b.addView(this.f3328c, new LayoutParams(0, -2, 1.0f));
        this.f3327b.addView(this.f3330e);
        return this.f3327b;
    }

    /* renamed from: a */
    public void m3644a(String str, String str2) {
        this.f3331f.setText(str);
        this.f3332g.setText(str2);
    }

    /* renamed from: b */
    protected int mo4317b() {
        return 72;
    }
}
