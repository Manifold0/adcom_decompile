package com.facebook.ads.internal.view;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import com.facebook.ads.internal.view.p060d.C2306a;
import com.facebook.ads.internal.view.p060d.C2307b;

public class HScrollLinearLayoutManager extends LinearLayoutManager {
    /* renamed from: a */
    private final C2307b f5041a;
    /* renamed from: b */
    private final C2306a f5042b;
    /* renamed from: c */
    private final Context f5043c;
    /* renamed from: d */
    private int[] f5044d;
    /* renamed from: e */
    private int f5045e = 0;
    /* renamed from: f */
    private float f5046f = 50.0f;
    /* renamed from: g */
    private C2187a f5047g;
    /* renamed from: h */
    private int f5048h;

    /* renamed from: com.facebook.ads.internal.view.HScrollLinearLayoutManager$a */
    private class C2187a extends LinearSmoothScroller {
        /* renamed from: a */
        final /* synthetic */ HScrollLinearLayoutManager f5040a;

        public C2187a(HScrollLinearLayoutManager hScrollLinearLayoutManager, Context context) {
            this.f5040a = hScrollLinearLayoutManager;
            super(context);
        }

        public int calculateDxToMakeVisible(View view, int i) {
            LayoutManager layoutManager = getLayoutManager();
            if (!layoutManager.canScrollHorizontally()) {
                return 0;
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            return calculateDtToFit(layoutManager.getDecoratedLeft(view) - layoutParams.leftMargin, layoutManager.getDecoratedRight(view) + layoutParams.rightMargin, layoutManager.getPaddingLeft(), layoutManager.getWidth() - layoutManager.getPaddingRight(), i) + this.f5040a.f5045e;
        }

        protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return this.f5040a.f5046f / ((float) displayMetrics.densityDpi);
        }

        public PointF computeScrollVectorForPosition(int i) {
            return this.f5040a.computeScrollVectorForPosition(i);
        }

        protected int getHorizontalSnapPreference() {
            return -1;
        }
    }

    public HScrollLinearLayoutManager(Context context, C2307b c2307b, C2306a c2306a) {
        super(context);
        this.f5043c = context;
        this.f5041a = c2307b;
        this.f5042b = c2306a;
        this.f5048h = -1;
        this.f5047g = new C2187a(this, this.f5043c);
    }

    /* renamed from: a */
    public void m5601a(double d) {
        if (d <= 0.0d) {
            d = 1.0d;
        }
        this.f5046f = (float) (50.0d / d);
        this.f5047g = new C2187a(this, this.f5043c);
    }

    /* renamed from: a */
    void m5602a(int i) {
        this.f5048h = i;
    }

    /* renamed from: b */
    public void m5603b(int i) {
        this.f5045e = i;
    }

    public void onMeasure(Recycler recycler, State state, int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        if ((mode == 1073741824 && getOrientation() == 1) || (mode2 == 1073741824 && getOrientation() == 0)) {
            super.onMeasure(recycler, state, i, i2);
            return;
        }
        int[] a;
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (this.f5042b.m5937b(this.f5048h)) {
            a = this.f5042b.m5936a(this.f5048h);
        } else {
            int[] iArr = new int[]{0, 0};
            if (state.getItemCount() >= 1) {
                int childCount = getChildCount() > 0 ? 1 : getChildCount();
                for (int i3 = 0; i3 < childCount; i3++) {
                    this.f5044d = this.f5041a.m5938a(findViewByPosition(i3), MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
                    if (getOrientation() == 0) {
                        iArr[0] = iArr[0] + this.f5044d[0];
                        if (i3 == 0) {
                            iArr[1] = (this.f5044d[1] + getPaddingTop()) + getPaddingBottom();
                        }
                    } else {
                        iArr[1] = iArr[1] + this.f5044d[1];
                        if (i3 == 0) {
                            iArr[0] = (this.f5044d[0] + getPaddingLeft()) + getPaddingRight();
                        }
                    }
                }
                if (this.f5048h != -1) {
                    this.f5042b.m5935a(this.f5048h, iArr);
                }
            }
            a = iArr;
        }
        if (mode == 1073741824) {
            a[0] = size;
        }
        if (mode2 == 1073741824) {
            a[1] = size2;
        }
        setMeasuredDimension(a[0], a[1]);
    }

    public void scrollToPosition(int i) {
        super.scrollToPositionWithOffset(i, this.f5045e);
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, State state, int i) {
        this.f5047g.setTargetPosition(i);
        startSmoothScroll(this.f5047g);
    }
}
