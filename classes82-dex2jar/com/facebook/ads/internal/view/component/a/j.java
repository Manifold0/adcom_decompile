// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component.a;

import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.adapters.b.l;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.view.View;
import com.facebook.ads.internal.adapters.b.h;
import android.content.res.Resources;

class j extends c
{
    private static final int c;
    private final k d;
    private int e;
    
    static {
        c = Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    
    public j(final e e, final h h) {
        super(e, h, true);
        (this.d = new k(e.a(), e.d())).a((View)e.h(), e.i());
        final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(-1, -2);
        frameLayout$LayoutParams.gravity = 17;
        this.addView((View)this.d, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
    }
    
    @Override
    public void a(final l l, final String s, final double n) {
        super.a(l, s, n);
        if (n > 0.0) {
            int e;
            if (x.a.heightPixels - (e = (int)((j.c - j.a * 2) / n)) < n.a) {
                e = x.a.heightPixels - n.a;
            }
            this.d.a(e);
            this.e = e;
        }
    }
    
    @Override
    public boolean a() {
        return true;
    }
    
    @Override
    public int getExactMediaHeightIfAvailable() {
        return this.e;
    }
}
