// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component.a;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import com.facebook.ads.internal.adapters.b.l;
import com.facebook.ads.internal.w.b.x;
import android.view.ViewGroup;
import com.facebook.ads.internal.view.c.d;
import com.facebook.ads.internal.view.component.a;
import com.facebook.ads.internal.adapters.b.h;
import android.widget.FrameLayout;

class o extends FrameLayout
{
    final n a;
    
    public o(final e e, final h h, int n, int n2, final a.a a) {
        super(e.a());
        boolean b;
        if (e.k() != 1) {
            b = true;
        }
        else {
            b = false;
        }
        final d d = new d((ViewGroup)this, 12);
        int heightPixels;
        if (b) {
            heightPixels = x.a.heightPixels;
        }
        else {
            heightPixels = n;
        }
        int widthPixels;
        if (b) {
            widthPixels = n2;
        }
        else {
            widthPixels = x.a.widthPixels;
        }
        d.a(heightPixels, widthPixels).a(e.g().d().get(0).c().g());
        final FrameLayout frameLayout = new FrameLayout(e.a());
        this.addView((View)frameLayout, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        frameLayout.setBackgroundColor(-433903825);
        final FrameLayout frameLayout2 = new FrameLayout(e.a());
        if (!b) {
            n2 = -1;
        }
        if (b) {
            n = -1;
        }
        final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(n2, n);
        frameLayout$LayoutParams.gravity = 48;
        this.addView((View)frameLayout2, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
        this.a = new n(e, h, a);
        final FrameLayout$LayoutParams frameLayout$LayoutParams2 = new FrameLayout$LayoutParams(-1, -2);
        frameLayout$LayoutParams2.gravity = 17;
        frameLayout2.addView((View)this.a, (ViewGroup$LayoutParams)frameLayout$LayoutParams2);
    }
    
    public void a(final String s, final String s2, final String s3, final boolean b, final boolean b2) {
        this.a.a(s, s2, s3, b, b2);
    }
}
