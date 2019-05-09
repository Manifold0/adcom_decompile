package com.tapjoy.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class ao extends ViewGroup {
    public ao(Context context) {
        super(context);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i = 0;
        int childCount = getChildCount();
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                i = Math.max(i, childAt.getMeasuredWidth());
                i2 = Math.max(i2, childAt.getMeasuredHeight());
            }
        }
        setMeasuredDimension(resolveSize(Math.max(i + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), widthMeasureSpec), resolveSize(Math.max(i2 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), heightMeasureSpec));
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                childAt.layout(paddingLeft, paddingTop, childAt.getMeasuredWidth() + paddingLeft, childAt.getMeasuredHeight() + paddingTop);
            }
        }
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new LayoutParams(p);
    }
}
