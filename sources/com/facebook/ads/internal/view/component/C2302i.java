package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.support.v4.graphics.ColorUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.p025w.p026b.C2600x;

/* renamed from: com.facebook.ads.internal.view.component.i */
public class C2302i extends LinearLayout {
    /* renamed from: a */
    private static final int f5453a = ((int) (16.0f * C2600x.f6420b));
    /* renamed from: b */
    private static final int f5454b = ((int) (14.0f * C2600x.f6420b));
    /* renamed from: c */
    private static final int f5455c = ColorUtils.setAlphaComponent(-1, 77);
    /* renamed from: d */
    private final CircularProgressView f5456d;
    /* renamed from: e */
    private final TextView f5457e;

    public C2302i(Context context) {
        super(context);
        setOrientation(0);
        setGravity(16);
        this.f5456d = new CircularProgressView(context);
        this.f5456d.setPadding(f5453a, f5453a, f5453a, f5453a);
        this.f5456d.setProgress(0.0f);
        m5922a(f5455c, -1);
        this.f5457e = new TextView(context);
        m5923a(false, -1, f5454b);
        addView(this.f5456d);
        addView(this.f5457e);
    }

    /* renamed from: a */
    public void m5922a(int i, int i2) {
        this.f5456d.m5780a(i, i2);
    }

    /* renamed from: a */
    public void m5923a(boolean z, int i, int i2) {
        C2600x.m6687a(this.f5457e, z, i2);
        this.f5457e.setTextColor(i);
    }

    public void setProgress(int i) {
        this.f5456d.setProgressWithAnimation((float) i);
    }

    public void setText(String str) {
        this.f5457e.setText(str);
    }
}
