package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.p025w.p026b.C2600x;

/* renamed from: com.facebook.ads.internal.view.component.j */
public class C2303j extends LinearLayout {
    /* renamed from: a */
    private static final float f5458a = Resources.getSystem().getDisplayMetrics().density;
    /* renamed from: b */
    private static final int f5459b = ((int) (6.0f * f5458a));
    /* renamed from: c */
    private static final int f5460c = ((int) (8.0f * f5458a));
    /* renamed from: d */
    private final TextView f5461d;
    /* renamed from: e */
    private final TextView f5462e;
    /* renamed from: f */
    private final TextView f5463f;

    public C2303j(Context context, C1876h c1876h, boolean z, int i, int i2, int i3) {
        super(context);
        setOrientation(1);
        this.f5461d = new TextView(context);
        C2600x.m6687a(this.f5461d, true, i);
        this.f5461d.setTextColor(c1876h.m4289c(z));
        this.f5461d.setEllipsize(TruncateAt.END);
        this.f5461d.setLineSpacing((float) f5459b, 1.0f);
        this.f5463f = new TextView(context);
        this.f5463f.setTextColor(c1876h.m4286a(z));
        this.f5462e = new TextView(context);
        C2600x.m6687a(this.f5462e, false, i2);
        this.f5462e.setTextColor(c1876h.m4288b(z));
        this.f5462e.setEllipsize(TruncateAt.END);
        this.f5462e.setLineSpacing((float) f5459b, 1.0f);
        addView(this.f5461d, new LayoutParams(-1, -2));
        addView(this.f5463f, new LayoutParams(-1, -2));
        this.f5463f.setVisibility(8);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.setMargins(0, i3, 0, 0);
        addView(this.f5462e, layoutParams);
    }

    public C2303j(Context context, C1876h c1876h, boolean z, boolean z2, boolean z3) {
        this(context, c1876h, z, z2 ? 18 : 22, z2 ? 14 : 16, z3 ? f5460c / 2 : f5460c);
    }

    /* renamed from: a */
    public void m5924a(String str, String str2, @Nullable String str3, boolean z, boolean z2) {
        CharSequence charSequence;
        int i = 0;
        int i2 = 1;
        int i3 = !TextUtils.isEmpty(str) ? 1 : 0;
        if (!TextUtils.isEmpty(str2)) {
            i = 1;
        }
        TextView textView = this.f5461d;
        if (i3 == 0) {
            Object obj = str2;
        }
        textView.setText(r7);
        if (str3 != null) {
            this.f5463f.setText(str3);
        }
        textView = this.f5462e;
        if (i3 == 0) {
            charSequence = "";
        }
        textView.setText(charSequence);
        if (i3 == 0 || r2 == 0) {
            TextView textView2 = this.f5461d;
            i3 = z ? 2 : z2 ? 4 : 3;
            textView2.setMaxLines(i3);
            return;
        }
        this.f5461d.setMaxLines(z ? 1 : 2);
        TextView textView3 = this.f5462e;
        if (!z) {
            i2 = z2 ? 3 : 2;
        }
        textView3.setMaxLines(i2);
    }

    /* renamed from: a */
    public void m5925a(boolean z, int i) {
        if (z) {
            this.f5463f.setGravity(i);
            this.f5463f.setVisibility(0);
            return;
        }
        this.f5463f.setVisibility(8);
    }

    public TextView getDescriptionTextView() {
        return this.f5462e;
    }

    public TextView getTitleTextView() {
        return this.f5461d;
    }

    public void setAlignment(int i) {
        this.f5461d.setGravity(i);
        this.f5462e.setGravity(i);
    }

    public void setDescriptionGravity(int i) {
        this.f5462e.setGravity(i);
    }

    public void setTitleGravity(int i) {
        this.f5461d.setGravity(i);
    }
}
