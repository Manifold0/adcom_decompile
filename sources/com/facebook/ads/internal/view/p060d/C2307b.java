package com.facebook.ads.internal.view.p060d;

import android.support.v7.widget.RecyclerView.LayoutParams;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: com.facebook.ads.internal.view.d.b */
public class C2307b {
    /* renamed from: a */
    public int[] m5938a(View view, int i, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i, view.getPaddingLeft() + view.getPaddingRight(), layoutParams.width), ViewGroup.getChildMeasureSpec(i2, view.getPaddingTop() + view.getPaddingBottom(), layoutParams.height));
        int[] iArr = new int[2];
        iArr[0] = (view.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin;
        iArr[1] = layoutParams.topMargin + (view.getMeasuredHeight() + layoutParams.bottomMargin);
        return iArr;
    }
}
