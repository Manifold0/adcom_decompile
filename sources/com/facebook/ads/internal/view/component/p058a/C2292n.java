package com.facebook.ads.internal.view.component.p058a;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.adapters.p030b.C1880l;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.view.component.C2295a;
import com.facebook.ads.internal.view.component.C2295a.C2258a;
import com.facebook.ads.internal.view.component.C2299f;
import com.facebook.ads.internal.view.component.C2303j;
import com.facebook.ads.internal.view.p019c.C2252d;
import java.util.HashMap;

/* renamed from: com.facebook.ads.internal.view.component.a.n */
public class C2292n extends LinearLayout {
    /* renamed from: a */
    public static final int f5413a = ((int) (C2600x.f6420b * 275.0f));
    /* renamed from: b */
    private static final int f5414b = ((int) (C2600x.f6420b * 56.0f));
    /* renamed from: c */
    private static final int f5415c = ((int) (C2600x.f6420b * 4.0f));
    /* renamed from: d */
    private static final int f5416d = ((int) (C2600x.f6420b * 8.0f));
    /* renamed from: e */
    private static final int f5417e = ((int) (C2600x.f6420b * 16.0f));
    /* renamed from: f */
    private static final int f5418f = ((int) (C2600x.f6420b * 20.0f));
    /* renamed from: g */
    private final C2303j f5419g;
    /* renamed from: h */
    private final C2299f f5420h;
    @Nullable
    /* renamed from: i */
    private C2291m f5421i;

    public C2292n(C2279e c2279e, C1876h c1876h, C2258a c2258a) {
        super(c2279e.m5850a());
        setOrientation(1);
        setGravity(17);
        this.f5420h = new C2299f(c2279e.m5850a());
        this.f5420h.setFullCircleCorners(true);
        setupIconView(c2279e);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(f5414b, f5414b);
        addView(this.f5420h, layoutParams);
        layoutParams.bottomMargin = f5415c;
        this.f5419g = new C2303j(getContext(), c1876h, true, true, false);
        C2600x.m6679a(this.f5419g);
        this.f5419g.setTitleGravity(17);
        this.f5419g.setDescriptionGravity(17);
        this.f5419g.m5925a(true, 17);
        layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(f5417e, 0, f5417e, f5415c);
        addView(this.f5419g, layoutParams);
        C2600x.m6679a(this.f5419g);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.topMargin = f5418f;
        layoutParams2.bottomMargin = f5415c;
        if (c2279e.m5860k() == 1) {
            this.f5421i = new C2291m(c2279e, ((C1880l) c2279e.m5856g().m4308d().get(0)).m4316b().m4296b(), c1876h, c2258a);
            addView(this.f5421i, layoutParams2);
            return;
        }
        C1876h c1876h2 = new C1876h();
        c1876h2.m4287a(654311423);
        View c2295a = new C2295a(c2279e.m5850a(), true, false, "com.facebook.ads.interstitial.clicked", c1876h2, c2279e.m5851b(), c2279e.m5852c(), c2279e.m5854e(), c2279e.m5855f());
        c2295a.m5918a(((C1880l) c2279e.m5856g().m4308d().get(0)).m4316b(), c2279e.m5856g().m4307c(), new HashMap(), c2258a);
        c2295a.setPadding(f5416d, f5415c, f5416d, f5415c);
        c2295a.setBackgroundColor(0);
        c2295a.setTextColor(-1);
        c2295a.setTypeface(Typeface.defaultFromStyle(1));
        addView(c2295a, layoutParams2);
    }

    private void setupIconView(C2279e c2279e) {
        C2252d c2252d = new C2252d(this.f5420h);
        c2252d.m5772a(f5414b, f5414b);
        c2252d.m5775a(c2279e.m5856g().m4303a().m4329b());
    }

    /* renamed from: a */
    public void m5907a(String str, String str2, String str3, boolean z, boolean z2) {
        this.f5419g.m5924a(str, str2, str3, z, z2);
    }

    @Nullable
    public C2291m getSwipeUpCtaButton() {
        return this.f5421i;
    }
}
