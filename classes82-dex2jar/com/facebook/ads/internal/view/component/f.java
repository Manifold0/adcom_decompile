// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component;

import android.graphics.Path$Direction;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build$VERSION;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.graphics.RectF;
import android.graphics.Path;
import android.widget.ImageView;

public class f extends ImageView
{
    private static final int a;
    private static final float[] b;
    private final Path c;
    private final RectF d;
    private float[] e;
    private boolean f;
    
    static {
        a = (int)(8.0f * x.b);
        b = new float[] { (float)f.a, (float)f.a, (float)f.a, (float)f.a, (float)f.a, (float)f.a, (float)f.a, (float)f.a };
    }
    
    public f(final Context context) {
        super(context);
        this.e = com.facebook.ads.internal.view.component.f.b;
        this.f = false;
        this.c = new Path();
        this.d = new RectF();
        if (Build$VERSION.SDK_INT < 18) {
            this.setLayerType(1, (Paint)null);
        }
    }
    
    private float[] getRadiiForCircularImage() {
        final int n = Math.min(this.getWidth(), this.getHeight()) / 2;
        return new float[] { (float)n, (float)n, (float)n, (float)n, (float)n, (float)n, (float)n, (float)n };
    }
    
    protected void onDraw(final Canvas canvas) {
        this.d.set(0.0f, 0.0f, (float)this.getWidth(), (float)this.getHeight());
        this.c.reset();
        float[] array;
        if (this.f) {
            array = this.getRadiiForCircularImage();
        }
        else {
            array = this.e;
        }
        this.c.addRoundRect(this.d, array, Path$Direction.CW);
        canvas.clipPath(this.c);
        super.onDraw(canvas);
    }
    
    public void setFullCircleCorners(final boolean f) {
        this.f = f;
    }
    
    public void setRadius(int n) {
        n *= (int)x.b;
        this.e = new float[] { (float)n, (float)n, (float)n, (float)n, (float)n, (float)n, (float)n, (float)n };
    }
    
    public void setRadius(final float[] e) {
        this.e = e;
    }
}
