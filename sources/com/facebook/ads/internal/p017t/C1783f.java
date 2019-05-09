package com.facebook.ads.internal.p017t;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/* renamed from: com.facebook.ads.internal.t.f */
public abstract class C1783f extends RelativeLayout {
    public C1783f(Context context) {
        super(context);
    }

    public C1783f(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public C1783f(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @RequiresApi(api = 21)
    public C1783f(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    protected abstract View getAdContentsView();
}
