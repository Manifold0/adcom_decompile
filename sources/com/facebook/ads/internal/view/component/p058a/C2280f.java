package com.facebook.ads.internal.view.component.p058a;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.adapters.p030b.C1880l;

/* renamed from: com.facebook.ads.internal.view.component.a.f */
public class C2280f extends C2259c {
    /* renamed from: c */
    private static final int f5380c = Resources.getSystem().getDisplayMetrics().widthPixels;
    /* renamed from: d */
    private final C2290k f5381d;

    public C2280f(C2279e c2279e, boolean z, C1876h c1876h) {
        super(c2279e, c1876h, z);
        this.f5381d = new C2290k(c2279e.m5850a(), c2279e.m5853d());
        this.f5381d.m5906a(c2279e.m5857h(), c2279e.m5858i(), 10, getTitleDescContainer(), z);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12);
        layoutParams.setMargins(a, a, a, a);
        getCtaButton().setLayoutParams(layoutParams);
        View frameLayout = new FrameLayout(c2279e.m5850a());
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(2, getCtaButton().getId());
        frameLayout.setLayoutParams(layoutParams2);
        layoutParams2 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams2.gravity = 17;
        layoutParams2.setMargins(a, 0, a, 0);
        frameLayout.addView(this.f5381d, layoutParams2);
        addView(frameLayout);
        addView(getCtaButton());
    }

    /* renamed from: a */
    public void mo5560a(C1880l c1880l, String str, double d) {
        super.mo5560a(c1880l, str, d);
        if (d > 0.0d) {
            this.f5381d.m5904a((int) (((double) (f5380c - (a * 2))) / d));
        }
    }

    /* renamed from: a */
    public boolean mo5555a() {
        return false;
    }

    /* renamed from: c */
    protected boolean mo5561c() {
        return false;
    }

    /* renamed from: e */
    protected boolean mo5556e() {
        return false;
    }
}
