// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.view.View$MeasureSpec;
import android.text.TextUtils$TruncateAt;
import android.content.Context;
import android.widget.TextView;

public class v extends TextView
{
    private int a;
    private float b;
    private float c;
    
    public v(final Context context, final int maxLines) {
        super(context);
        this.c = 8.0f;
        this.setMaxLines(maxLines);
        this.setEllipsize(TextUtils$TruncateAt.END);
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.setMaxLines(this.a + 1);
        super.setTextSize(this.b);
        final int n5 = n3 - n;
        final int n6 = n4 - n2;
        this.measure(View$MeasureSpec.makeMeasureSpec(n5, 1073741824), View$MeasureSpec.makeMeasureSpec(n6, 0));
        if (this.getMeasuredHeight() > n6) {
            float b2 = this.b;
            while (b2 > this.c) {
                final float textSize = b2 - 0.5f;
                super.setTextSize(textSize);
                this.measure(View$MeasureSpec.makeMeasureSpec(n5, 1073741824), 0);
                b2 = textSize;
                if (this.getMeasuredHeight() <= n6) {
                    b2 = textSize;
                    if (this.getLineCount() <= this.a) {
                        break;
                    }
                    continue;
                }
            }
        }
        super.setMaxLines(this.a);
        this.setMeasuredDimension(n5, n6);
        super.onLayout(b, n, n2, n3, n4);
    }
    
    public void setMaxLines(final int a) {
        super.setMaxLines(this.a = a);
    }
    
    public void setMinTextSize(final float c) {
        this.c = c;
    }
    
    public void setTextSize(final float b) {
        super.setTextSize(this.b = b);
    }
}
