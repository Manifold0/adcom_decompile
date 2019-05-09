package com.facebook.ads.internal.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.p017t.C1783f;
import com.facebook.ads.internal.p025w.p026b.C2580j;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p050r.C2078a;
import com.tapjoy.TJAdUnitConstants;

/* renamed from: com.facebook.ads.internal.view.c */
public class C2254c extends C1783f {
    /* renamed from: a */
    public static final int f5293a = (((int) C2600x.f6420b) * TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL);
    /* renamed from: b */
    private static final int f5294b = ((int) (C2600x.f6420b * 500.0f));
    /* renamed from: c */
    private static final int f5295c = ((int) (C2600x.f6420b * 4.0f));
    /* renamed from: d */
    private static final int f5296d = ((int) (C2600x.f6420b * 8.0f));
    /* renamed from: e */
    private static final int f5297e = ((int) (C2600x.f6420b * 8.0f));
    /* renamed from: f */
    private static final int f5298f = ((int) (C2600x.f6420b * 4.0f));
    /* renamed from: g */
    private static final int f5299g = ((int) C2600x.f6420b);
    /* renamed from: h */
    private static final int f5300h = ((int) (C2600x.f6420b * 4.0f));
    /* renamed from: i */
    private static final int f5301i = ((int) (((double) C2600x.f6420b) * 0.5d));
    /* renamed from: j */
    private final TextView f5302j;
    /* renamed from: k */
    private final TextView f5303k;
    /* renamed from: l */
    private final TextView f5304l;
    /* renamed from: m */
    private final RelativeLayout f5305m;
    /* renamed from: n */
    private final LinearLayout f5306n;
    /* renamed from: o */
    private final RelativeLayout f5307o;
    /* renamed from: p */
    private final C2548t f5308p;

    public C2254c(Context context) {
        super(context);
        this.f5302j = new TextView(context);
        this.f5303k = new TextView(context);
        this.f5304l = new TextView(context);
        this.f5305m = new RelativeLayout(context);
        this.f5306n = new LinearLayout(context);
        this.f5307o = new RelativeLayout(context);
        this.f5308p = new C2548t(context);
        setLayoutParams(new LayoutParams(-2, -1));
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1);
        gradientDrawable.setCornerRadius((float) f5300h);
        gradientDrawable.setStroke(1, -10459280);
        setBackgroundDrawable(gradientDrawable);
        setPadding(f5301i, f5301i, f5301i, f5301i);
        this.f5306n.setOrientation(1);
        C2600x.m6679a(this.f5306n);
        addView(this.f5306n, new LinearLayout.LayoutParams(-2, -2));
        C2600x.m6679a(this.f5307o);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.weight = 1.0f;
        layoutParams.gravity = 1;
        this.f5306n.addView(this.f5307o, layoutParams);
        this.f5308p.setScaleType(ScaleType.FIT_XY);
        this.f5308p.setRadius(new float[]{(float) f5300h, (float) f5300h, (float) f5300h, (float) f5300h, 0.0f, 0.0f, 0.0f, 0.0f});
        this.f5308p.setAdjustViewBounds(true);
        C2580j.m6643a(this.f5308p, C2580j.INTERNAL_AD_MEDIA);
        C2600x.m6679a(this.f5308p);
        this.f5307o.addView(this.f5308p, new LinearLayout.LayoutParams(-2, -1));
        C2600x.m6679a(this.f5307o);
        this.f5304l.setPadding(f5299g, f5299g, f5299g, f5299g);
        this.f5304l.setTextSize(14.0f);
        C2600x.m6679a(this.f5304l);
        C2600x.m6679a(this.f5305m);
        this.f5302j.setTextSize(14.0f);
        C2600x.m6679a(this.f5302j);
        this.f5302j.setMaxLines(1);
        layoutParams = new LayoutParams(-2, -2);
        layoutParams.setMargins(0, 0, 0, f5298f);
        this.f5305m.addView(this.f5302j, layoutParams);
        this.f5303k.setTextSize(12.0f);
        C2600x.m6679a(this.f5303k);
        this.f5303k.setMaxLines(1);
        layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(3, this.f5302j.getId());
        layoutParams.setMargins(0, 0, 0, f5298f);
        this.f5305m.addView(this.f5303k, layoutParams);
    }

    protected View getAdContentsView() {
        return this.f5308p;
    }

    public TextView getCTAButton() {
        return this.f5304l;
    }

    public ImageView getImageCardView() {
        return this.f5308p;
    }

    protected void onMeasure(int i, int i2) {
        ViewGroup.LayoutParams layoutParams;
        if (MeasureSpec.getSize(i2) >= ((int) C2600x.f6420b) * C2078a.m5108r(getContext()) || MeasureSpec.getMode(i2) == 0) {
            layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(3, this.f5303k.getId());
            C2600x.m6689b(this.f5304l);
            this.f5305m.addView(this.f5304l, layoutParams);
            layoutParams = new LayoutParams(-1, -2);
            this.f5305m.setPadding(f5297e, f5297e, f5297e, f5297e);
            C2600x.m6689b(this.f5305m);
            this.f5306n.addView(this.f5305m, layoutParams);
            this.f5308p.setMaxWidth(f5294b);
            this.f5302j.setTextColor(-10459280);
            this.f5303k.setTextColor(-10459280);
            this.f5304l.setTextColor(-13272859);
            ((LinearLayout.LayoutParams) this.f5307o.getLayoutParams()).gravity = 1;
        } else {
            layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(f5296d, f5295c, f5296d, f5295c);
            C2600x.m6689b(this.f5304l);
            this.f5306n.addView(this.f5304l, layoutParams);
            layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(8, this.f5308p.getId());
            layoutParams.addRule(5, this.f5308p.getId());
            layoutParams.addRule(7, this.f5308p.getId());
            this.f5305m.setPadding(f5297e, 0, f5297e, 0);
            this.f5305m.setBackgroundDrawable(new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{-872415232, 0}));
            C2600x.m6689b(this.f5305m);
            this.f5307o.addView(this.f5305m, layoutParams);
            this.f5302j.setTextColor(-1);
            this.f5303k.setTextColor(-1);
            this.f5304l.setTextColor(-13272859);
        }
        super.onMeasure(i, i2);
    }

    public void setButtonText(String str) {
        if (str == null || str.trim().isEmpty()) {
            this.f5304l.setVisibility(8);
            return;
        }
        CharSequence spannableString = new SpannableString(str);
        spannableString.setSpan(new StyleSpan(1), 0, spannableString.length(), 0);
        this.f5304l.setText(spannableString);
    }

    public void setSubtitle(String str) {
        if (str == null || str.trim().isEmpty()) {
            this.f5303k.setVisibility(8);
        }
        this.f5303k.setText(str);
    }

    public void setTitle(String str) {
        if (str == null || str.trim().isEmpty()) {
            this.f5302j.setVisibility(8);
        }
        this.f5302j.setText(str);
    }
}
