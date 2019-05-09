// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.util.AttributeSet;
import android.content.Context;
import android.widget.VideoView;

public class AppLovinVideoView extends VideoView
{
    private int a;
    private int b;
    private float c;
    
    public AppLovinVideoView(final Context context) {
        super(context, (AttributeSet)null, 0);
        this.a = 0;
        this.b = 0;
        this.c = 0.0f;
    }
    
    protected void onMeasure(int defaultSize, int n) {
        if (this.a > 0 && this.b > 0) {
            final int defaultSize2 = getDefaultSize(this.a, defaultSize);
            defaultSize = getDefaultSize(this.b, n);
            n = (int)(defaultSize2 / this.c);
            if (defaultSize > n) {
                defaultSize = n;
            }
            n = (int)(defaultSize * this.c);
            if (defaultSize2 <= n) {
                n = defaultSize2;
            }
            this.setMeasuredDimension(n, defaultSize);
            return;
        }
        super.onMeasure(defaultSize, n);
    }
    
    public void setVideoSize(final int a, final int b) {
        this.a = a;
        this.b = b;
        this.c = this.a / (float)this.b;
        try {
            this.getHolder().setFixedSize(a, b);
            this.requestLayout();
            this.invalidate();
        }
        catch (Exception ex) {}
    }
}
