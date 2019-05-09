package com.facebook.ads.internal.view.p055a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p069c.C2603b;
import com.facebook.ads.internal.p025w.p069c.C2604c;

/* renamed from: com.facebook.ads.internal.view.a.f */
public class C2197f extends LinearLayout {
    /* renamed from: a */
    private static final int f5092a = ((int) (C2600x.f6420b * 16.0f));
    /* renamed from: b */
    private static final int f5093b = ((int) (C2600x.f6420b * 12.0f));
    /* renamed from: c */
    private static final int f5094c = ((int) (C2600x.f6420b * 12.0f));
    /* renamed from: d */
    private static final int f5095d = ((int) (C2600x.f6420b * 16.0f));
    /* renamed from: e */
    private boolean f5096e = false;
    /* renamed from: f */
    private final ImageView f5097f;
    /* renamed from: g */
    private final TextView f5098g;

    public C2197f(Context context) {
        super(context);
        setOrientation(0);
        setPadding(f5092a, f5093b, f5092a, f5093b);
        this.f5097f = new ImageView(getContext());
        LayoutParams layoutParams = new LinearLayout.LayoutParams(f5095d, f5095d);
        layoutParams.gravity = 17;
        this.f5098g = new TextView(getContext());
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        addView(this.f5097f, layoutParams);
        addView(this.f5098g, layoutParams2);
        m5674b();
    }

    /* renamed from: b */
    private void m5674b() {
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(this.f5096e ? -13272859 : -1315344);
        gradientDrawable.setCornerRadius(50.0f);
        C2600x.m6681a((View) this, gradientDrawable);
        C2600x.m6687a(this.f5098g, false, 14);
        int i = this.f5096e ? -1 : -10459280;
        this.f5098g.setTextColor(i);
        this.f5097f.setColorFilter(i);
    }

    /* renamed from: a */
    public void m5675a() {
        setSelected(!this.f5096e);
    }

    /* renamed from: a */
    public void m5676a(String str, @Nullable C2603b c2603b) {
        this.f5098g.setText(str);
        if (c2603b != null) {
            this.f5097f.setImageBitmap(C2604c.m6697a(c2603b));
            this.f5097f.setVisibility(0);
            this.f5098g.setPadding(f5094c, 0, 0, 0);
        } else {
            this.f5097f.setVisibility(8);
            this.f5098g.setPadding(0, 0, 0, 0);
        }
        m5674b();
    }

    public void setSelected(boolean z) {
        this.f5096e = z;
        m5674b();
    }
}
