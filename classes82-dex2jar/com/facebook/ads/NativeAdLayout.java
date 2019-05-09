// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import android.view.ViewGroup$LayoutParams;
import com.facebook.ads.internal.view.a.c;
import android.view.ViewGroup;
import com.facebook.ads.internal.w.b.x;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

public class NativeAdLayout extends FrameLayout
{
    private View a;
    private int b;
    private int c;
    
    public NativeAdLayout(final Context context) {
        super(context);
        this.b = 0;
        this.c = 0;
    }
    
    public NativeAdLayout(final Context context, @Nullable final AttributeSet set) {
        super(context, set);
        this.b = 0;
        this.c = 0;
    }
    
    public NativeAdLayout(final Context context, @Nullable final AttributeSet set, final int n) {
        super(context, set, n);
        this.b = 0;
        this.c = 0;
    }
    
    public void clearAdReportingLayout() {
        x.a((ViewGroup)this);
        this.removeView(this.a);
        this.a = null;
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        if (this.c > 0 && this.getMeasuredWidth() > this.c) {
            this.setMeasuredDimension(this.c, this.getMeasuredHeight());
        }
        else if (this.getMeasuredWidth() < this.b) {
            this.setMeasuredDimension(this.b, this.getMeasuredHeight());
        }
    }
    
    public void setAdReportingLayout(final c a) {
        (this.a = (View)a).setLayoutParams(new ViewGroup$LayoutParams(-1, -1));
        x.a((ViewGroup)this);
        this.addView(this.a);
    }
    
    public void setMaxWidth(final int c) {
        this.c = c;
    }
    
    public void setMinWidth(final int b) {
        this.b = b;
    }
}
