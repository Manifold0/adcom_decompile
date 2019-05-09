package com.facebook.ads.internal.view;

import android.content.Context;
import android.view.View.MeasureSpec;
import com.facebook.ads.internal.view.p022i.C2394a;

/* renamed from: com.facebook.ads.internal.view.y */
public class C2555y extends C2394a {
    public C2555y(Context context) {
        super(context);
    }

    protected void onMeasure(int i, int i2) {
        if (MeasureSpec.getMode(i) == 1073741824) {
            i2 = i;
        } else if (MeasureSpec.getMode(i2) == 1073741824) {
            i = i2;
        }
        super.onMeasure(i, i2);
    }
}
