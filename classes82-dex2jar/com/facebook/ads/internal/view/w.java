// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.view.View$MeasureSpec;
import android.text.TextUtils$TruncateAt;
import com.facebook.ads.internal.w.b.x;
import android.content.Context;
import android.widget.TextView;

public class w extends TextView
{
    private float a;
    private float b;
    
    public w(final Context context) {
        super(context);
        this.b = 8.0f;
        super.setSingleLine();
        super.setMaxLines(1);
        this.a = this.getTextSize() / x.b;
        this.setEllipsize(TextUtils$TruncateAt.END);
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        final int n5 = n3 - n;
        final int measuredHeight = this.getMeasuredHeight();
        final int measuredWidth = this.getMeasuredWidth();
        for (float a = this.a; a >= this.b; a -= 0.5f) {
            super.setTextSize(a);
            this.measure(0, 0);
            if (this.getMeasuredWidth() <= n5) {
                break;
            }
        }
        if (this.getMeasuredWidth() > n5) {
            this.measure(View$MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View$MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
        }
        this.setMeasuredDimension(measuredWidth, measuredHeight);
        super.onLayout(b, n, n2, n3, n4);
    }
    
    public void setMaxLines(final int n) {
    }
    
    public void setMinTextSize(final float b) {
        if (b <= this.a) {
            this.b = b;
        }
    }
    
    public void setSingleLine(final boolean b) {
    }
    
    public void setTextSize(final float a) {
        super.setTextSize(this.a = a);
    }
}
