// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.view.View$MeasureSpec;
import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;

public class x extends ImageView
{
    @Nullable
    private ImageView$ScaleType a;
    
    public x(final Context context) {
        super(context);
    }
    
    protected void onMeasure(int n, final int n2) {
        final int size = View$MeasureSpec.getSize(n2);
        final int size2 = View$MeasureSpec.getSize(n);
        if (View$MeasureSpec.getMode(n) == 1073741824 && View$MeasureSpec.getMode(n2) == 1073741824) {
            n = Math.min(size2, size);
            this.setMeasuredDimension(n, n);
        }
        else if (View$MeasureSpec.getMode(n) == 1073741824) {
            n = size2;
            if (size > 0) {
                n = Math.min(size2, size);
            }
            this.setMeasuredDimension(n, n);
        }
        else if (View$MeasureSpec.getMode(n2) == 1073741824) {
            if (size2 > 0) {
                n = Math.min(size2, size);
            }
            else {
                n = size;
            }
            this.setMeasuredDimension(n, n);
        }
        else {
            super.onMeasure(n, n2);
        }
        if (this.a != null) {
            super.setScaleType(this.a);
        }
    }
    
    public void setScaleType(final ImageView$ScaleType a) {
        this.a = a;
    }
}
