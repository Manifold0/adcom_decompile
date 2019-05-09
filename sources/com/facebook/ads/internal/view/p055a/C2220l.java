package com.facebook.ads.internal.view.p055a;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.facebook.ads.internal.p025w.p026b.C2600x;

/* renamed from: com.facebook.ads.internal.view.a.l */
public class C2220l extends ViewGroup {
    /* renamed from: a */
    private static final int f5153a = ((int) (8.0f * C2600x.f6420b));
    /* renamed from: b */
    private int f5154b;

    public C2220l(Context context) {
        super(context);
        setMotionEventSplittingEnabled(false);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i6 = 0; i6 < getChildCount(); i6++) {
            View childAt = getChildAt(i6);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            if (paddingLeft + measuredWidth > i5) {
                paddingLeft = getPaddingLeft();
                paddingTop += this.f5154b;
            }
            childAt.layout(paddingLeft, paddingTop, paddingLeft + measuredWidth, measuredHeight + paddingTop);
            paddingLeft += f5153a + measuredWidth;
        }
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 0;
        int size = (MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        int size2 = (MeasureSpec.getSize(i2) - getPaddingTop()) - getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE);
        size2 = getChildCount() > 0 ? 1 : 0;
        int i4 = paddingLeft;
        paddingLeft = 0;
        while (i3 < getChildCount()) {
            View childAt = getChildAt(i3);
            childAt.measure(MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), makeMeasureSpec);
            int measuredWidth = childAt.getMeasuredWidth();
            paddingLeft = Math.max(paddingLeft, childAt.getMeasuredHeight() + f5153a);
            if (i4 + measuredWidth > size) {
                size2++;
                i4 = getPaddingLeft();
            }
            i4 += f5153a + measuredWidth;
            i3++;
        }
        this.f5154b = paddingLeft;
        setMeasuredDimension(size, (size2 * this.f5154b) + f5153a);
    }
}
