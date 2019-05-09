package com.facebook.ads.internal.view.p056b;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.adjust.sdk.Constants;
import com.facebook.ads.internal.p025w.p069c.C2603b;
import com.facebook.ads.internal.p025w.p069c.C2604c;

@TargetApi(19)
/* renamed from: com.facebook.ads.internal.view.b.e */
public class C2235e extends LinearLayout {
    /* renamed from: a */
    private TextView f5193a = new TextView(getContext());
    /* renamed from: b */
    private TextView f5194b;
    /* renamed from: c */
    private Drawable f5195c;

    public C2235e(Context context) {
        super(context);
        float f = getResources().getDisplayMetrics().density;
        setOrientation(1);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        this.f5193a.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.f5193a.setTextSize(2, 20.0f);
        this.f5193a.setEllipsize(TruncateAt.END);
        this.f5193a.setSingleLine(true);
        this.f5193a.setVisibility(8);
        addView(this.f5193a, layoutParams);
        this.f5194b = new TextView(getContext());
        layoutParams = new LinearLayout.LayoutParams(-1, -2);
        this.f5194b.setAlpha(0.5f);
        this.f5194b.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.f5194b.setTextSize(2, 15.0f);
        this.f5194b.setCompoundDrawablePadding((int) (f * 5.0f));
        this.f5194b.setEllipsize(TruncateAt.END);
        this.f5194b.setSingleLine(true);
        this.f5194b.setVisibility(8);
        addView(this.f5194b, layoutParams);
    }

    private Drawable getPadlockDrawable() {
        if (this.f5195c == null) {
            this.f5195c = new BitmapDrawable(getContext().getResources(), C2604c.m6697a(C2603b.BROWSER_PADLOCK));
        }
        return this.f5195c;
    }

    public void setSubtitle(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f5194b.setText(null);
            this.f5194b.setVisibility(8);
            return;
        }
        Uri parse = Uri.parse(str);
        this.f5194b.setText(parse.getHost());
        this.f5194b.setCompoundDrawablesRelativeWithIntrinsicBounds(Constants.SCHEME.equals(parse.getScheme()) ? getPadlockDrawable() : null, null, null, null);
        this.f5194b.setVisibility(0);
    }

    public void setTitle(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f5193a.setText(null);
            this.f5193a.setVisibility(8);
            return;
        }
        this.f5193a.setText(str);
        this.f5193a.setVisibility(0);
    }
}
