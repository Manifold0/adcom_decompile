// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.view.Window;
import android.os.Bundle;
import com.applovin.impl.sdk.g;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.RelativeLayout$LayoutParams;
import com.applovin.impl.sdk.ee;
import android.view.View$OnClickListener;
import com.applovin.sdk.AppLovinSdkUtils;
import android.content.Context;
import android.widget.RelativeLayout;
import com.applovin.impl.sdk.m;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import android.app.Activity;
import android.app.Dialog;

class ar extends Dialog implements aq
{
    private final Activity a;
    private final AppLovinSdk b;
    private final AppLovinLogger c;
    private final n d;
    private final m e;
    private final String f;
    private RelativeLayout g;
    private ak h;
    
    ar(final m e, final String f, final n d, final Activity a, final AppLovinSdk b) {
        super((Context)a, 16973840);
        if (e == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        if (d == null) {
            throw new IllegalArgumentException("No main view specified");
        }
        if (b == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        if (a == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        this.b = b;
        this.c = b.getLogger();
        this.a = a;
        this.d = d;
        this.e = e;
        this.f = f;
        this.requestWindowFeature(1);
        this.setCancelable(false);
    }
    
    private int a(final int n) {
        return AppLovinSdkUtils.dpToPx((Context)this.a, n);
    }
    
    private void a(final al al) {
        final int n = 9;
        if (this.h != null) {
            this.c.w("ExpandedAdDialog", "Attempting to create duplicate close button");
            return;
        }
        (this.h = ak.a(this.b, this.getContext(), al)).setVisibility(8);
        this.h.setOnClickListener((View$OnClickListener)new av(this));
        this.h.setClickable(false);
        final ee ee = new ee(this.b);
        final int a = this.a(ee.R());
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(a, a);
        relativeLayout$LayoutParams.addRule(10);
        int n2;
        if (ee.U()) {
            n2 = 9;
        }
        else {
            n2 = 11;
        }
        relativeLayout$LayoutParams.addRule(n2);
        this.h.a(a);
        final int a2 = this.a(ee.T());
        final int a3 = this.a(ee.S());
        relativeLayout$LayoutParams.setMargins(a3, a2, a3, 0);
        this.g.addView((View)this.h, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        this.h.bringToFront();
        final int a4 = this.a(ee.V());
        final View view = new View((Context)this.a);
        view.setBackgroundColor(0);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(a + a4, a + a4);
        relativeLayout$LayoutParams2.addRule(10);
        int n3;
        if (ee.U()) {
            n3 = n;
        }
        else {
            n3 = 11;
        }
        relativeLayout$LayoutParams2.addRule(n3);
        relativeLayout$LayoutParams2.setMargins(a3 - this.a(5), a2 - this.a(5), a3 - this.a(5), 0);
        view.setOnClickListener((View$OnClickListener)new aw(this));
        this.g.addView(view, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
        view.bringToFront();
    }
    
    private void b() {
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.d.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        (this.g = new RelativeLayout((Context)this.a)).setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
        this.g.setBackgroundColor(-1157627904);
        this.g.addView((View)this.d);
        if (!this.e.j()) {
            this.a(this.e.k());
            this.d();
        }
        this.setContentView((View)this.g);
    }
    
    private void c() {
        this.d.a("javascript:al_onCloseTapped();", new as(this));
    }
    
    private void d() {
        this.a.runOnUiThread((Runnable)new ax(this));
    }
    
    public m a() {
        return this.e;
    }
    
    public void dismiss() {
        final g c = this.d.c();
        if (c != null) {
            c.e();
        }
        this.a.runOnUiThread((Runnable)new au(this));
    }
    
    public void onBackPressed() {
        this.d.a("javascript:al_onBackPressed();", new at(this));
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.b();
    }
    
    protected void onStart() {
        super.onStart();
        try {
            final Window window = this.getWindow();
            if (window == null) {
                this.c.e("ExpandedAdDialog", "Unable to turn on hardware acceleration - window is null");
                return;
            }
            window.setFlags(this.a.getWindow().getAttributes().flags, this.a.getWindow().getAttributes().flags);
            if (this.e.F()) {
                window.addFlags(16777216);
            }
        }
        catch (Throwable t) {
            this.c.e("ExpandedAdDialog", "Setting window flags failed.", t);
        }
    }
}
