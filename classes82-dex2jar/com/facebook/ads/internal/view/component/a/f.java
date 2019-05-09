// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component.a;

import com.facebook.ads.internal.adapters.b.l;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.view.View;
import com.facebook.ads.internal.adapters.b.h;
import android.content.res.Resources;

public class f extends c
{
    private static final int c;
    private final k d;
    
    static {
        c = Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    
    public f(final e e, final boolean b, final h h) {
        super(e, h, b);
        (this.d = new k(e.a(), e.d())).a((View)e.h(), e.i(), 10, this.getTitleDescContainer(), b);
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, -2);
        layoutParams.addRule(12);
        layoutParams.setMargins(f.a, f.a, f.a, f.a);
        this.getCtaButton().setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        final FrameLayout frameLayout = new FrameLayout(e.a());
        final RelativeLayout$LayoutParams layoutParams2 = new RelativeLayout$LayoutParams(-1, -1);
        layoutParams2.addRule(2, this.getCtaButton().getId());
        frameLayout.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
        final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(-1, -2);
        frameLayout$LayoutParams.gravity = 17;
        frameLayout$LayoutParams.setMargins(f.a, 0, f.a, 0);
        frameLayout.addView((View)this.d, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
        this.addView((View)frameLayout);
        this.addView((View)this.getCtaButton());
    }
    
    @Override
    public void a(final l l, final String s, final double n) {
        super.a(l, s, n);
        if (n > 0.0) {
            this.d.a((int)((f.c - f.a * 2) / n));
        }
    }
    
    @Override
    public boolean a() {
        return false;
    }
    
    @Override
    protected boolean c() {
        return false;
    }
    
    @Override
    protected boolean e() {
        return false;
    }
}
