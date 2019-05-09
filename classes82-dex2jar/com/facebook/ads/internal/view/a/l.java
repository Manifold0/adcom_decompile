// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.a;

import android.view.View$MeasureSpec;
import android.view.View;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.view.ViewGroup;

public class l extends ViewGroup
{
    private static final int a;
    private int b;
    
    static {
        a = (int)(8.0f * x.b);
    }
    
    public l(final Context context) {
        super(context);
        this.setMotionEventSplittingEnabled(false);
    }
    
    protected void onLayout(final boolean b, final int n, int paddingLeft, final int n2, int i) {
        paddingLeft = this.getPaddingLeft();
        int paddingTop = this.getPaddingTop();
        View child;
        int measuredWidth;
        int measuredHeight;
        int n3;
        int paddingLeft2;
        for (i = 0; i < this.getChildCount(); ++i, paddingTop = n3) {
            child = this.getChildAt(i);
            measuredWidth = child.getMeasuredWidth();
            measuredHeight = child.getMeasuredHeight();
            n3 = paddingTop;
            paddingLeft2 = paddingLeft;
            if (paddingLeft + measuredWidth > n2 - n) {
                paddingLeft2 = this.getPaddingLeft();
                n3 = paddingTop + this.b;
            }
            child.layout(paddingLeft2, n3, paddingLeft2 + measuredWidth, measuredHeight + n3);
            paddingLeft = paddingLeft2 + (l.a + measuredWidth);
        }
    }
    
    protected void onMeasure(int paddingLeft, int size) {
        int i = 0;
        final int n = View$MeasureSpec.getSize(paddingLeft) - this.getPaddingLeft() - this.getPaddingRight();
        size = View$MeasureSpec.getSize(size);
        final int paddingTop = this.getPaddingTop();
        final int paddingBottom = this.getPaddingBottom();
        paddingLeft = this.getPaddingLeft();
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(size - paddingTop - paddingBottom, Integer.MIN_VALUE);
        if (this.getChildCount() > 0) {
            size = 1;
        }
        else {
            size = 0;
        }
        int b = 0;
        while (i < this.getChildCount()) {
            final View child = this.getChildAt(i);
            child.measure(View$MeasureSpec.makeMeasureSpec(n, Integer.MIN_VALUE), measureSpec);
            final int measuredWidth = child.getMeasuredWidth();
            final int max = Math.max(b, child.getMeasuredHeight() + l.a);
            int n2 = size;
            int paddingLeft2 = paddingLeft;
            if (paddingLeft + measuredWidth > n) {
                n2 = size + 1;
                paddingLeft2 = this.getPaddingLeft();
            }
            paddingLeft = paddingLeft2 + (l.a + measuredWidth);
            ++i;
            size = n2;
            b = max;
        }
        this.b = b;
        this.setMeasuredDimension(n, size * this.b + l.a);
    }
}
