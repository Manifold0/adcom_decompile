package com.facebook.ads.internal.view.component.p058a;

import android.content.res.Resources;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.adapters.p030b.C1880l;
import com.facebook.ads.internal.p025w.p026b.C2600x;

/* renamed from: com.facebook.ads.internal.view.component.a.j */
class C2289j extends C2259c {
    /* renamed from: c */
    private static final int f5404c = Resources.getSystem().getDisplayMetrics().widthPixels;
    /* renamed from: d */
    private final C2290k f5405d;
    /* renamed from: e */
    private int f5406e;

    public C2289j(C2279e c2279e, C1876h c1876h) {
        super(c2279e, c1876h, true);
        this.f5405d = new C2290k(c2279e.m5850a(), c2279e.m5853d());
        this.f5405d.m5905a(c2279e.m5857h(), c2279e.m5858i());
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 17;
        addView(this.f5405d, layoutParams);
    }

    /* renamed from: a */
    public void mo5560a(C1880l c1880l, String str, double d) {
        super.mo5560a(c1880l, str, d);
        if (d > 0.0d) {
            int i = (int) (((double) (f5404c - (a * 2))) / d);
            if (C2600x.f6419a.heightPixels - i < C2292n.f5413a) {
                i = C2600x.f6419a.heightPixels - C2292n.f5413a;
            }
            this.f5405d.m5904a(i);
            this.f5406e = i;
        }
    }

    /* renamed from: a */
    public boolean mo5555a() {
        return true;
    }

    public int getExactMediaHeightIfAvailable() {
        return this.f5406e;
    }
}
