// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component;

import android.graphics.Path$Direction;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build$VERSION;
import android.view.View;
import com.facebook.ads.internal.w.b.x;
import android.content.Context;
import android.graphics.RectF;
import android.graphics.Path;
import android.widget.RelativeLayout;

public class g extends RelativeLayout
{
    protected int a;
    private final Path b;
    private final RectF c;
    
    public g(final Context context) {
        super(context);
        this.a = (int)(4.0f * x.b);
        this.b = new Path();
        this.c = new RectF();
        x.a((View)this, 0);
        if (Build$VERSION.SDK_INT < 18) {
            this.setLayerType(1, (Paint)null);
        }
    }
    
    protected void onDraw(final Canvas canvas) {
        this.c.set(0.0f, 0.0f, (float)this.getWidth(), (float)this.getHeight());
        this.b.reset();
        this.b.addRoundRect(this.c, (float)this.a, (float)this.a, Path$Direction.CW);
        canvas.clipPath(this.b);
        super.onDraw(canvas);
    }
    
    public void setCornerRadius(final int n) {
        this.a = (int)(n * x.b);
    }
}
