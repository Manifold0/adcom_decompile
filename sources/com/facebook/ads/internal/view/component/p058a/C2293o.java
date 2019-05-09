package com.facebook.ads.internal.view.component.p058a;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.adapters.p030b.C1880l;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.view.component.C2295a.C2258a;
import com.facebook.ads.internal.view.p019c.C2252d;

/* renamed from: com.facebook.ads.internal.view.component.a.o */
class C2293o extends FrameLayout {
    /* renamed from: a */
    final C2292n f5422a;

    public C2293o(C2279e c2279e, C1876h c1876h, int i, int i2, C2258a c2258a) {
        super(c2279e.m5850a());
        int i3 = c2279e.m5860k() != 1 ? 1 : 0;
        new C2252d(this, 12).m5772a(i3 != 0 ? C2600x.f6419a.heightPixels : i, i3 != 0 ? i2 : C2600x.f6419a.widthPixels).m5775a(((C1880l) c2279e.m5856g().m4308d().get(0)).m4317c().m4245g());
        View frameLayout = new FrameLayout(c2279e.m5850a());
        addView(frameLayout, new LayoutParams(-1, -1));
        frameLayout.setBackgroundColor(-433903825);
        frameLayout = new FrameLayout(c2279e.m5850a());
        if (i3 == 0) {
            i2 = -1;
        }
        if (i3 != 0) {
            i = -1;
        }
        ViewGroup.LayoutParams layoutParams = new LayoutParams(i2, i);
        layoutParams.gravity = 48;
        addView(frameLayout, layoutParams);
        this.f5422a = new C2292n(c2279e, c1876h, c2258a);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        layoutParams2.gravity = 17;
        frameLayout.addView(this.f5422a, layoutParams2);
    }

    /* renamed from: a */
    public void m5908a(String str, String str2, String str3, boolean z, boolean z2) {
        this.f5422a.m5907a(str, str2, str3, z, z2);
    }
}
