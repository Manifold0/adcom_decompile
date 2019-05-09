package com.facebook.ads.internal.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

/* renamed from: com.facebook.ads.internal.view.x */
public class C2554x extends ImageView {
    @Nullable
    /* renamed from: a */
    private ScaleType f6282a;

    public C2554x(Context context) {
        super(context);
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i2);
        int size2 = MeasureSpec.getSize(i);
        if (MeasureSpec.getMode(i) == 1073741824 && MeasureSpec.getMode(i2) == 1073741824) {
            size2 = Math.min(size2, size);
            setMeasuredDimension(size2, size2);
        } else if (MeasureSpec.getMode(i) == 1073741824) {
            if (size > 0) {
                size2 = Math.min(size2, size);
            }
            setMeasuredDimension(size2, size2);
        } else if (MeasureSpec.getMode(i2) == 1073741824) {
            size2 = size2 > 0 ? Math.min(size2, size) : size;
            setMeasuredDimension(size2, size2);
        } else {
            super.onMeasure(i, i2);
        }
        if (this.f6282a != null) {
            super.setScaleType(this.f6282a);
        }
    }

    public void setScaleType(ScaleType scaleType) {
        this.f6282a = scaleType;
    }
}
