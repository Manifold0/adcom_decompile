package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chartboost.sdk.C1403e;

@SuppressLint({"ViewConstructor"})
/* renamed from: com.chartboost.sdk.impl.y */
public class C1473y extends LinearLayout {
    /* renamed from: a */
    final C1469v f3405a;
    /* renamed from: b */
    final az f3406b;
    /* renamed from: c */
    private LinearLayout f3407c;
    /* renamed from: d */
    private ay f3408d;
    /* renamed from: e */
    private TextView f3409e;
    /* renamed from: f */
    private int f3410f = Integer.MIN_VALUE;

    public C1473y(Context context, C1469v c1469v) {
        super(context);
        this.f3405a = c1469v;
        int round = Math.round(context.getResources().getDisplayMetrics().density * 8.0f);
        setOrientation(1);
        setGravity(17);
        this.f3407c = new LinearLayout(context);
        this.f3407c.setGravity(17);
        this.f3407c.setOrientation(0);
        this.f3407c.setPadding(round, round, round, round);
        this.f3408d = new ay(context);
        this.f3408d.setScaleType(ScaleType.FIT_CENTER);
        this.f3408d.setPadding(0, 0, round, 0);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        c1469v.m3653a(layoutParams, c1469v.f3372H, 1.0f);
        this.f3409e = new TextView(getContext());
        this.f3409e.setTextColor(-1);
        this.f3409e.setTypeface(null, 1);
        this.f3409e.setGravity(17);
        this.f3409e.setTextSize(2, C1403e.m3295a(context) ? 26.0f : 16.0f);
        this.f3407c.addView(this.f3408d, layoutParams);
        this.f3407c.addView(this.f3409e, new LinearLayout.LayoutParams(-2, -2));
        this.f3406b = new az(this, getContext()) {
            /* renamed from: a */
            final /* synthetic */ C1473y f3404a;

            /* renamed from: a */
            protected void mo4279a(MotionEvent motionEvent) {
                this.f3404a.f3406b.setEnabled(false);
                this.f3404a.f3405a.m3687q().m3673g();
            }
        };
        this.f3406b.setContentDescription("CBWatch");
        this.f3406b.setPadding(0, 0, 0, round);
        this.f3406b.m3341a(ScaleType.FIT_CENTER);
        this.f3406b.setPadding(round, round, round, round);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        c1469v.m3653a(layoutParams2, c1469v.f3371G, 1.0f);
        this.f3408d.m3471a(c1469v.f3372H);
        this.f3406b.m3342a(c1469v.f3371G);
        addView(this.f3407c, new LinearLayout.LayoutParams(-2, -2));
        addView(this.f3406b, layoutParams2);
        m3705a();
    }

    /* renamed from: a */
    public void m3707a(boolean z) {
        setBackgroundColor(z ? ViewCompat.MEASURED_STATE_MASK : this.f3410f);
    }

    /* renamed from: a */
    public void m3706a(String str, int i) {
        this.f3409e.setText(str);
        this.f3410f = i;
        m3707a(this.f3405a.m3689s());
    }

    /* renamed from: a */
    public void m3705a() {
        m3707a(this.f3405a.m3689s());
    }
}
