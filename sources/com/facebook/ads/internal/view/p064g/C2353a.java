package com.facebook.ads.internal.view.p064g;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.adapters.p030b.C1887q;
import com.facebook.ads.internal.p025w.p026b.C2598w;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.component.C2295a;
import com.facebook.ads.internal.view.component.C2299f;
import com.facebook.ads.internal.view.component.C2303j;
import com.facebook.ads.internal.view.p019c.C2252d;
import com.facebook.ads.internal.view.p022i.p023b.aa;
import java.util.HashMap;

/* renamed from: com.facebook.ads.internal.view.g.a */
public class C2353a extends LinearLayout {
    /* renamed from: a */
    private static final int f5667a = ((int) (12.0f * C2600x.f6420b));
    /* renamed from: b */
    private static final int f5668b = ((int) (16.0f * C2600x.f6420b));
    /* renamed from: c */
    private final C2303j f5669c;
    /* renamed from: d */
    private final ImageView f5670d;
    /* renamed from: e */
    private final RelativeLayout f5671e;
    /* renamed from: f */
    private final C2295a f5672f;
    /* renamed from: g */
    private final int f5673g;

    public C2353a(Context context, int i, C1876h c1876h, C2085c c2085c, C1789a c1789a, boolean z, boolean z2, C2630a c2630a, C2598w c2598w) {
        super(context);
        this.f5673g = i;
        this.f5670d = new C2299f(context);
        C2600x.m6680a(this.f5670d, 0);
        C2600x.m6679a(this.f5670d);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i);
        layoutParams.addRule(15);
        layoutParams.addRule(9);
        layoutParams.setMargins(0, 0, f5667a, 0);
        if (z2) {
            this.f5670d.setVisibility(8);
        }
        this.f5669c = new C2303j(context, c1876h, true, z, true);
        this.f5669c.setAlignment(GravityCompat.START);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(1, this.f5670d.getId());
        layoutParams2.addRule(15);
        this.f5672f = new C2295a(context, true, false, aa.REWARDED_VIDEO_AD_CLICK.m6205a(), c1876h, c2085c, c1789a, c2630a, c2598w);
        this.f5672f.setVisibility(8);
        this.f5671e = new RelativeLayout(context);
        C2600x.m6679a(this.f5671e);
        this.f5671e.addView(this.f5670d, layoutParams);
        this.f5671e.addView(this.f5669c, layoutParams2);
        addView(this.f5671e, new LinearLayout.LayoutParams(-2, -2));
        Drawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, -15658735});
        gradientDrawable.setCornerRadius(0.0f);
        C2600x.m6681a((View) this, gradientDrawable);
    }

    /* renamed from: a */
    public void m6068a() {
        this.f5672f.setVisibility(0);
    }

    /* renamed from: a */
    public void m6069a(int i) {
        int i2 = -1;
        int i3 = 1;
        C2600x.m6689b(this.f5672f);
        int i4 = i == 1 ? 1 : 0;
        if (i4 == 0) {
            i3 = 0;
        }
        setOrientation(i3);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(i4 != 0 ? -1 : 0, -2);
        layoutParams.weight = 1.0f;
        if (i4 == 0) {
            i2 = -2;
        }
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i2, -2);
        layoutParams2.setMargins(i4 != 0 ? 0 : f5668b, i4 != 0 ? f5668b : 0, 0, 0);
        layoutParams2.gravity = 80;
        this.f5671e.setLayoutParams(layoutParams);
        addView(this.f5672f, layoutParams2);
    }

    public void setInfo(C1887q c1887q) {
        this.f5669c.m5924a(c1887q.m4363g().m4265a(), c1887q.m4363g().m4266b(), null, false, false);
        this.f5672f.m5917a(c1887q.m4364h(), c1887q.mo5384a(), new HashMap());
        new C2252d(this.f5670d).m5772a(this.f5673g, this.f5673g).m5775a(c1887q.m4362f().m4329b());
    }
}
