// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewGroup;

public class ao extends ViewGroup
{
    public ao(final Context context) {
        super(context);
    }
    
    public ViewGroup$LayoutParams generateLayoutParams(final AttributeSet set) {
        return new ViewGroup$LayoutParams(this.getContext(), set);
    }
    
    protected ViewGroup$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return new ViewGroup$LayoutParams(viewGroup$LayoutParams);
    }
    
    protected void onLayout(final boolean b, int i, int paddingLeft, int paddingTop, int childCount) {
        paddingLeft = this.getPaddingLeft();
        paddingTop = this.getPaddingTop();
        View child;
        for (childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
                child.layout(paddingLeft, paddingTop, child.getMeasuredWidth() + paddingLeft, child.getMeasuredHeight() + paddingTop);
            }
        }
    }
    
    public void onMeasure(final int n, final int n2) {
        int n3 = 0;
        final int childCount = this.getChildCount();
        this.measureChildren(n, n2);
        int i = 0;
        int n4 = 0;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            int max = n3;
            int max2 = n4;
            if (child.getVisibility() != 8) {
                max = Math.max(n3, child.getMeasuredWidth());
                max2 = Math.max(n4, child.getMeasuredHeight());
            }
            ++i;
            n3 = max;
            n4 = max2;
        }
        this.setMeasuredDimension(resolveSize(Math.max(n3 + (this.getPaddingLeft() + this.getPaddingRight()), this.getSuggestedMinimumWidth()), n), resolveSize(Math.max(n4 + (this.getPaddingTop() + this.getPaddingBottom()), this.getSuggestedMinimumHeight()), n2));
    }
}
