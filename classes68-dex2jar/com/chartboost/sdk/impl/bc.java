// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.view.MotionEvent;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.RelativeLayout$LayoutParams;
import android.content.Context;
import com.chartboost.sdk.Model.c;
import com.chartboost.sdk.e;
import android.annotation.SuppressLint;
import android.widget.RelativeLayout;

@SuppressLint({ "ViewConstructor" })
public class bc extends RelativeLayout
{
    private e.a a;
    private ax b;
    private ax c;
    private final c d;
    
    public bc(final Context context, final c d) {
        super(context);
        this.d = d;
        if (d.p.b == 0) {
            this.addView((View)(this.b = new ax(context)), (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
            this.addView((View)(this.c = new ax(context)), (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
            this.c.setVisibility(8);
        }
    }
    
    public void a() {
        if (this.a == null) {
            this.a = this.d.k();
            if (this.a != null) {
                this.addView((View)this.a, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
                this.a.a();
            }
        }
    }
    
    public void b() {
    }
    
    public ax c() {
        return this.b;
    }
    
    public View d() {
        return (View)this.a;
    }
    
    public c e() {
        return this.d;
    }
    
    public boolean f() {
        return this.a != null && this.a.getVisibility() == 0;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        this.performClick();
        return true;
    }
    
    public boolean performClick() {
        super.performClick();
        return true;
    }
}
