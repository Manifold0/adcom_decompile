// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.RecyclerView$LayoutParams;
import android.view.View;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView$SmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.View$MeasureSpec;
import android.support.v7.widget.RecyclerView$State;
import android.support.v7.widget.RecyclerView$Recycler;
import android.content.Context;
import com.facebook.ads.internal.view.d.a;
import com.facebook.ads.internal.view.d.b;
import android.support.v7.widget.LinearLayoutManager;

public class HScrollLinearLayoutManager extends LinearLayoutManager
{
    private final b a;
    private final com.facebook.ads.internal.view.d.a b;
    private final Context c;
    private int[] d;
    private int e;
    private float f;
    private a g;
    private int h;
    
    public HScrollLinearLayoutManager(final Context c, final b a, final com.facebook.ads.internal.view.d.a b) {
        super(c);
        this.e = 0;
        this.f = 50.0f;
        this.c = c;
        this.a = a;
        this.b = b;
        this.h = -1;
        this.g = new a(this.c);
    }
    
    public void a(final double n) {
        double n2 = n;
        if (n <= 0.0) {
            n2 = 1.0;
        }
        this.f = (float)(50.0 / n2);
        this.g = new a(this.c);
    }
    
    void a(final int h) {
        this.h = h;
    }
    
    public void b(final int e) {
        this.e = e;
    }
    
    public void onMeasure(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, int childCount, int i) {
        final int mode = View$MeasureSpec.getMode(childCount);
        final int mode2 = View$MeasureSpec.getMode(i);
        if ((mode == 1073741824 && this.getOrientation() == 1) || (mode2 == 1073741824 && this.getOrientation() == 0)) {
            super.onMeasure(recyclerView$Recycler, recyclerView$State, childCount, i);
            return;
        }
        final int size = View$MeasureSpec.getSize(childCount);
        final int size2 = View$MeasureSpec.getSize(i);
        int[] a;
        if (this.b.b(this.h)) {
            a = this.b.a(this.h);
        }
        else {
            final int[] array;
            a = (array = new int[2]);
            array[1] = (array[0] = 0);
            if (recyclerView$State.getItemCount() >= 1) {
                if (this.getChildCount() > 0) {
                    childCount = 1;
                }
                else {
                    childCount = this.getChildCount();
                }
                for (i = 0; i < childCount; ++i) {
                    this.d = this.a.a(this.findViewByPosition(i), View$MeasureSpec.makeMeasureSpec(0, 0), View$MeasureSpec.makeMeasureSpec(0, 0));
                    if (this.getOrientation() == 0) {
                        a[0] += this.d[0];
                        if (i == 0) {
                            a[1] = this.d[1] + this.getPaddingTop() + this.getPaddingBottom();
                        }
                    }
                    else {
                        a[1] += this.d[1];
                        if (i == 0) {
                            a[0] = this.d[0] + this.getPaddingLeft() + this.getPaddingRight();
                        }
                    }
                }
                if (this.h != -1) {
                    this.b.a(this.h, a);
                }
            }
        }
        if (mode == 1073741824) {
            a[0] = size;
        }
        if (mode2 == 1073741824) {
            a[1] = size2;
        }
        this.setMeasuredDimension(a[0], a[1]);
    }
    
    public void scrollToPosition(final int n) {
        super.scrollToPositionWithOffset(n, this.e);
    }
    
    public void smoothScrollToPosition(final RecyclerView recyclerView, final RecyclerView$State recyclerView$State, final int targetPosition) {
        this.g.setTargetPosition(targetPosition);
        this.startSmoothScroll((RecyclerView$SmoothScroller)this.g);
    }
    
    private class a extends LinearSmoothScroller
    {
        public a(final Context context) {
            super(context);
        }
        
        public int calculateDxToMakeVisible(final View view, final int n) {
            final RecyclerView$LayoutManager layoutManager = this.getLayoutManager();
            if (!layoutManager.canScrollHorizontally()) {
                return 0;
            }
            final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)view.getLayoutParams();
            return this.calculateDtToFit(layoutManager.getDecoratedLeft(view) - recyclerView$LayoutParams.leftMargin, layoutManager.getDecoratedRight(view) + recyclerView$LayoutParams.rightMargin, layoutManager.getPaddingLeft(), layoutManager.getWidth() - layoutManager.getPaddingRight(), n) + HScrollLinearLayoutManager.this.e;
        }
        
        protected float calculateSpeedPerPixel(final DisplayMetrics displayMetrics) {
            return HScrollLinearLayoutManager.this.f / displayMetrics.densityDpi;
        }
        
        public PointF computeScrollVectorForPosition(final int n) {
            return HScrollLinearLayoutManager.this.computeScrollVectorForPosition(n);
        }
        
        protected int getHorizontalSnapPreference() {
            return -1;
        }
    }
}
