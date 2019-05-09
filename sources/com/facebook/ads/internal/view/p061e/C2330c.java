package com.facebook.ads.internal.view.p061e;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.p030b.C1883m;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.view.component.C2299f;
import com.facebook.ads.internal.view.p019c.C2252d;

/* renamed from: com.facebook.ads.internal.view.e.c */
public class C2330c extends LinearLayout {
    /* renamed from: a */
    public static final int f5589a = ((int) (C2600x.f6420b * 32.0f));
    /* renamed from: b */
    private static final int f5590b = ((int) (C2600x.f6420b * 8.0f));
    /* renamed from: c */
    private C2299f f5591c;
    /* renamed from: d */
    private TextView f5592d;
    /* renamed from: e */
    private TextView f5593e;

    public C2330c(Context context) {
        super(context);
        m6014a(context);
    }

    /* renamed from: a */
    public void m6013a(int i, int i2) {
        this.f5592d.setTextColor(i);
        this.f5593e.setTextColor(i2);
    }

    /* renamed from: a */
    public void m6014a(Context context) {
        setGravity(16);
        this.f5591c = new C2299f(context);
        this.f5591c.setFullCircleCorners(true);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(f5589a, f5589a);
        layoutParams.setMargins(0, 0, f5590b, 0);
        addView(this.f5591c, layoutParams);
        View linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        this.f5592d = new TextView(context);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        C2600x.m6687a(this.f5592d, true, 16);
        this.f5592d.setEllipsize(TruncateAt.END);
        this.f5592d.setSingleLine(true);
        this.f5593e = new TextView(context);
        C2600x.m6687a(this.f5593e, false, 14);
        linearLayout.addView(this.f5592d);
        linearLayout.addView(this.f5593e);
        addView(linearLayout, layoutParams2);
    }

    public void setPageDetails(C1883m c1883m) {
        C2252d c2252d = new C2252d(this.f5591c);
        c2252d.m5772a(f5589a, f5589a);
        c2252d.m5775a(c1883m.m4329b());
        this.f5592d.setText(c1883m.m4328a());
        this.f5593e.setText(c1883m.m4331d());
    }
}
