// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView$Adapter;
import android.view.View$MeasureSpec;
import com.facebook.ads.internal.w.b.x;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.util.AttributeSet;
import com.facebook.ads.internal.view.d.a;
import com.facebook.ads.internal.view.d.b;
import android.content.Context;
import com.facebook.ads.internal.view.d.c;

public class j extends c implements c.a
{
    private final HScrollLinearLayoutManager c;
    private a d;
    private int e;
    private int f;
    private int g;
    private int h;
    
    public j(final Context context) {
        super(context);
        this.e = -1;
        this.f = -1;
        this.g = 0;
        this.h = 0;
        this.c = new HScrollLinearLayoutManager(context, new b(), new com.facebook.ads.internal.view.d.a());
        this.a();
    }
    
    public j(final Context context, final AttributeSet set) {
        super(context, set);
        this.e = -1;
        this.f = -1;
        this.g = 0;
        this.h = 0;
        this.c = new HScrollLinearLayoutManager(context, new b(), new com.facebook.ads.internal.view.d.a());
        this.a();
    }
    
    public j(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.e = -1;
        this.f = -1;
        this.g = 0;
        this.h = 0;
        this.c = new HScrollLinearLayoutManager(context, new b(), new com.facebook.ads.internal.view.d.a());
        this.a();
    }
    
    private void a() {
        this.c.setOrientation(0);
        this.setLayoutManager((RecyclerView$LayoutManager)this.c);
        this.setSaveEnabled(false);
        this.setSnapDelegate((c.a)this);
    }
    
    @Override
    public int a(int abs) {
        abs = Math.abs(abs);
        if (abs <= this.a) {
            return 0;
        }
        if (this.g == 0) {
            return 1;
        }
        return abs / this.g + 1;
    }
    
    @Override
    protected void a(final int e, final boolean b) {
        super.a(e, b);
        if (e != this.e || this.f != 0) {
            this.e = e;
            this.f = 0;
            if (this.d != null) {
                this.d.a(this.e, this.f);
            }
        }
    }
    
    public int getChildSpacing() {
        return this.h;
    }
    
    protected void onMeasure(int i, int n) {
        super.onMeasure(i, n);
        final int n2 = this.getPaddingTop() + this.getPaddingBottom();
        if (com.facebook.ads.internal.r.a.q(this.getContext())) {
            i = (int)x.b * com.facebook.ads.internal.r.a.r(this.getContext()) + n2;
        }
        else {
            i = Math.round(this.getMeasuredWidth() / 1.91f);
        }
        switch (View$MeasureSpec.getMode(n)) {
            case 1073741824: {
                i = View$MeasureSpec.getSize(n);
                break;
            }
            case Integer.MIN_VALUE: {
                i = Math.min(View$MeasureSpec.getSize(n), i);
                break;
            }
        }
        final int n3 = i - n2;
        if (com.facebook.ads.internal.r.a.q(this.getContext())) {
            i = Math.min(com.facebook.ads.internal.view.c.a, n3);
        }
        else {
            final int n4 = this.h * 2;
            final int measuredWidth = this.getMeasuredWidth();
            final int paddingLeft = this.getPaddingLeft();
            final int itemCount = this.getAdapter().getItemCount();
            for (n = 0, i = Integer.MAX_VALUE; i > n3; i = (int)((measuredWidth - paddingLeft - n4 - n * n4) / (n + 0.333f))) {
                ++n;
                i = n3;
                if (n >= itemCount) {
                    break;
                }
            }
        }
        this.setMeasuredDimension(this.getMeasuredWidth(), i + n2);
        if (!com.facebook.ads.internal.r.a.q(this.getContext())) {
            this.setChildWidth(i + this.h * 2);
        }
    }
    
    public void setAdapter(@Nullable final RecyclerView$Adapter adapter) {
        final HScrollLinearLayoutManager c = this.c;
        int hashCode;
        if (adapter == null) {
            hashCode = -1;
        }
        else {
            hashCode = adapter.hashCode();
        }
        c.a(hashCode);
        super.setAdapter(adapter);
    }
    
    public void setChildSpacing(final int h) {
        this.h = h;
    }
    
    public void setChildWidth(int measuredWidth) {
        this.g = measuredWidth;
        measuredWidth = this.getMeasuredWidth();
        this.c.b((measuredWidth - this.getPaddingLeft() - this.getPaddingRight() - this.g) / 2);
        this.c.a(this.g / (double)measuredWidth);
    }
    
    public void setCurrentPosition(final int n) {
        this.a(n, false);
    }
    
    public void setOnPageChangedListener(final a d) {
        this.d = d;
    }
    
    public interface a
    {
        void a(final int p0, final int p1);
    }
}
