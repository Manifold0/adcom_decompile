package com.facebook.ads.internal.view;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.view.View.MeasureSpec;
import android.widget.TextView;

/* renamed from: com.facebook.ads.internal.view.v */
public class C2552v extends TextView {
    /* renamed from: a */
    private int f6277a;
    /* renamed from: b */
    private float f6278b;
    /* renamed from: c */
    private float f6279c = 8.0f;

    public C2552v(Context context, int i) {
        super(context);
        setMaxLines(i);
        setEllipsize(TruncateAt.END);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.setMaxLines(this.f6277a + 1);
        super.setTextSize(this.f6278b);
        int i5 = i3 - i;
        int i6 = i4 - i2;
        measure(MeasureSpec.makeMeasureSpec(i5, 1073741824), MeasureSpec.makeMeasureSpec(i6, 0));
        if (getMeasuredHeight() > i6) {
            float f = this.f6278b;
            while (f > this.f6279c) {
                f -= 0.5f;
                super.setTextSize(f);
                measure(MeasureSpec.makeMeasureSpec(i5, 1073741824), 0);
                if (getMeasuredHeight() <= i6 && getLineCount() <= this.f6277a) {
                    break;
                }
            }
        }
        super.setMaxLines(this.f6277a);
        setMeasuredDimension(i5, i6);
        super.onLayout(z, i, i2, i3, i4);
    }

    public void setMaxLines(int i) {
        this.f6277a = i;
        super.setMaxLines(i);
    }

    public void setMinTextSize(float f) {
        this.f6279c = f;
    }

    public void setTextSize(float f) {
        this.f6278b = f;
        super.setTextSize(f);
    }
}
