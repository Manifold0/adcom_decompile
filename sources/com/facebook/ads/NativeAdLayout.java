package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.view.p055a.C2195c;

public class NativeAdLayout extends FrameLayout {
    /* renamed from: a */
    private View f3715a;
    /* renamed from: b */
    private int f3716b = 0;
    /* renamed from: c */
    private int f3717c = 0;

    public NativeAdLayout(Context context) {
        super(context);
    }

    public NativeAdLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NativeAdLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void clearAdReportingLayout() {
        C2600x.m6683a((ViewGroup) this);
        removeView(this.f3715a);
        this.f3715a = null;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f3717c > 0 && getMeasuredWidth() > this.f3717c) {
            setMeasuredDimension(this.f3717c, getMeasuredHeight());
        } else if (getMeasuredWidth() < this.f3716b) {
            setMeasuredDimension(this.f3716b, getMeasuredHeight());
        }
    }

    public void setAdReportingLayout(C2195c c2195c) {
        this.f3715a = c2195c;
        this.f3715a.setLayoutParams(new LayoutParams(-1, -1));
        C2600x.m6683a((ViewGroup) this);
        addView(this.f3715a);
    }

    public void setMaxWidth(int i) {
        this.f3717c = i;
    }

    public void setMinWidth(int i) {
        this.f3716b = i;
    }
}
