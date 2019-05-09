package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.chartboost.sdk.C1403e.C1402a;
import com.chartboost.sdk.Model.C1388c;

@SuppressLint({"ViewConstructor"})
public class bc extends RelativeLayout {
    /* renamed from: a */
    private C1402a f3123a;
    /* renamed from: b */
    private ax f3124b;
    /* renamed from: c */
    private ax f3125c;
    /* renamed from: d */
    private final C1388c f3126d;

    public bc(Context context, C1388c c1388c) {
        super(context);
        this.f3126d = c1388c;
        if (c1388c.f2770p.f2730b == 0) {
            this.f3124b = new ax(context);
            addView(this.f3124b, new LayoutParams(-1, -1));
            this.f3125c = new ax(context);
            addView(this.f3125c, new LayoutParams(-1, -1));
            this.f3125c.setVisibility(8);
        }
    }

    /* renamed from: a */
    public void m3478a() {
        if (this.f3123a == null) {
            this.f3123a = this.f3126d.m3185k();
            if (this.f3123a != null) {
                addView(this.f3123a, new LayoutParams(-1, -1));
                this.f3123a.m3288a();
            }
        }
    }

    /* renamed from: b */
    public void m3479b() {
    }

    public boolean onTouchEvent(MotionEvent ev) {
        performClick();
        return true;
    }

    /* renamed from: c */
    public ax m3480c() {
        return this.f3124b;
    }

    /* renamed from: d */
    public View m3481d() {
        return this.f3123a;
    }

    /* renamed from: e */
    public C1388c m3482e() {
        return this.f3126d;
    }

    /* renamed from: f */
    public boolean m3483f() {
        return this.f3123a != null && this.f3123a.getVisibility() == 0;
    }

    public boolean performClick() {
        super.performClick();
        return true;
    }
}
