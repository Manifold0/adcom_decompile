package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.internal.p017t.C2114e;
import com.facebook.ads.internal.p017t.C2117j;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.view.C2553w;

/* renamed from: com.facebook.ads.internal.view.component.d */
public class C2297d extends LinearLayout {
    /* renamed from: a */
    private MediaView f5435a;
    /* renamed from: b */
    private C2296b f5436b;
    /* renamed from: c */
    private TextView f5437c;
    /* renamed from: d */
    private LinearLayout f5438d = new LinearLayout(getContext());

    public C2297d(Context context, C2114e c2114e, C2117j c2117j, MediaView mediaView, AdOptionsView adOptionsView, boolean z, int i) {
        super(context);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        setVerticalGravity(16);
        setOrientation(1);
        View linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        linearLayout.setGravity(16);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(Math.round(15.0f * displayMetrics.density), Math.round(15.0f * displayMetrics.density), Math.round(15.0f * displayMetrics.density), Math.round(15.0f * displayMetrics.density));
        linearLayout.setLayoutParams(layoutParams);
        addView(linearLayout);
        layoutParams = new LinearLayout.LayoutParams(-1, 0);
        this.f5438d.setOrientation(0);
        this.f5438d.setGravity(16);
        layoutParams.weight = 3.0f;
        this.f5438d.setLayoutParams(layoutParams);
        linearLayout.addView(this.f5438d);
        this.f5435a = mediaView;
        int i2 = (int) ((3.0d / ((double) ((z ? 1 : 0) + 3))) * ((double) (i - 30)));
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(Math.round(((float) i2) * displayMetrics.density), Math.round(((float) i2) * displayMetrics.density));
        layoutParams2.setMargins(0, 0, Math.round(15.0f * displayMetrics.density), 0);
        this.f5435a.setLayoutParams(layoutParams2);
        this.f5438d.addView(this.f5435a);
        View linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        this.f5438d.addView(linearLayout2);
        this.f5436b = new C2296b(getContext(), c2114e, c2117j, adOptionsView);
        layoutParams2 = new LinearLayout.LayoutParams(-2, -1);
        layoutParams2.setMargins(0, 0, Math.round(15.0f * displayMetrics.density), 0);
        layoutParams2.weight = 0.5f;
        this.f5436b.setLayoutParams(layoutParams2);
        linearLayout2.addView(this.f5436b);
        this.f5437c = new TextView(getContext());
        this.f5437c.setPadding(Math.round(6.0f * displayMetrics.density), Math.round(6.0f * displayMetrics.density), Math.round(6.0f * displayMetrics.density), Math.round(6.0f * displayMetrics.density));
        this.f5437c.setText(c2114e.m5309a("call_to_action"));
        this.f5437c.setTextColor(c2117j.m5374f());
        this.f5437c.setTextSize(14.0f);
        this.f5437c.setTypeface(c2117j.m5358a(), 1);
        this.f5437c.setMaxLines(2);
        this.f5437c.setEllipsize(TruncateAt.END);
        this.f5437c.setGravity(17);
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(c2117j.m5372e());
        gradientDrawable.setCornerRadius(displayMetrics.density * 5.0f);
        gradientDrawable.setStroke(1, c2117j.m5376g());
        C2600x.m6681a(this.f5437c, gradientDrawable);
        LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.weight = 0.25f;
        this.f5437c.setLayoutParams(layoutParams3);
        if (!c2114e.m5332h()) {
            this.f5437c.setVisibility(4);
        }
        linearLayout2.addView(this.f5437c);
        if (z) {
            TextView c2553w = new C2553w(getContext());
            c2553w.setText(c2114e.m5336l());
            c2117j.m5365b(c2553w);
            c2553w.setMinTextSize((float) (c2117j.m5378i() - 1));
            layoutParams3 = new LinearLayout.LayoutParams(-1, 0);
            layoutParams3.weight = 1.0f;
            c2553w.setLayoutParams(layoutParams3);
            c2553w.setGravity(80);
            linearLayout.addView(c2553w);
        }
    }

    public TextView getCallToActionView() {
        return this.f5437c;
    }

    public MediaView getIconView() {
        return this.f5435a;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        TextView titleTextView = this.f5436b.getTitleTextView();
        if (titleTextView.getLayout().getLineEnd(titleTextView.getLineCount() - 1) < this.f5436b.getMinVisibleTitleCharacters()) {
            this.f5438d.removeView(this.f5435a);
            super.onMeasure(i, i2);
        }
    }
}
