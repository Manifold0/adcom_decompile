package com.applovin.impl.adview;

import android.content.Context;
import android.widget.VideoView;

public class AppLovinVideoView extends VideoView {
    /* renamed from: a */
    private int f1705a = 0;
    /* renamed from: b */
    private int f1706b = 0;
    /* renamed from: c */
    private float f1707c = 0.0f;

    public AppLovinVideoView(Context context) {
        super(context, null, 0);
    }

    protected void onMeasure(int i, int i2) {
        if (this.f1705a <= 0 || this.f1706b <= 0) {
            super.onMeasure(i, i2);
            return;
        }
        int defaultSize = getDefaultSize(this.f1705a, i);
        int defaultSize2 = getDefaultSize(this.f1706b, i2);
        int i3 = (int) (((float) defaultSize) / this.f1707c);
        if (defaultSize2 <= i3) {
            i3 = defaultSize2;
        }
        defaultSize2 = (int) (((float) i3) * this.f1707c);
        if (defaultSize <= defaultSize2) {
            defaultSize2 = defaultSize;
        }
        setMeasuredDimension(defaultSize2, i3);
    }

    public void setVideoSize(int i, int i2) {
        this.f1705a = i;
        this.f1706b = i2;
        this.f1707c = ((float) this.f1705a) / ((float) this.f1706b);
        try {
            getHolder().setFixedSize(i, i2);
            requestLayout();
            invalidate();
        } catch (Exception e) {
        }
    }
}
