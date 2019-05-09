// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.d;

import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView$LayoutParams;
import android.view.View;

public class b
{
    public int[] a(final View view, int measuredWidth, int leftMargin) {
        final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(measuredWidth, view.getPaddingLeft() + view.getPaddingRight(), recyclerView$LayoutParams.width), ViewGroup.getChildMeasureSpec(leftMargin, view.getPaddingTop() + view.getPaddingBottom(), recyclerView$LayoutParams.height));
        measuredWidth = view.getMeasuredWidth();
        leftMargin = recyclerView$LayoutParams.leftMargin;
        return new int[] { measuredWidth + leftMargin + recyclerView$LayoutParams.rightMargin, recyclerView$LayoutParams.topMargin + (view.getMeasuredHeight() + recyclerView$LayoutParams.bottomMargin) };
    }
}
