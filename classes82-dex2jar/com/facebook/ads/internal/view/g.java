// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.os.Build$VERSION;
import android.graphics.Canvas;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.facebook.ads.internal.w.b.x;
import android.graphics.Paint$Style;
import android.content.Context;
import android.graphics.Paint;
import android.view.View;

public class g extends View
{
    private Paint a;
    private Paint b;
    private Paint c;
    private int d;
    private boolean e;
    
    public g(final Context context) {
        this(context, 60, true);
    }
    
    public g(final Context context, final int d, final boolean e) {
        super(context);
        this.d = d;
        this.e = e;
        if (e) {
            (this.a = new Paint()).setColor(-3355444);
            this.a.setStyle(Paint$Style.STROKE);
            this.a.setStrokeWidth(3.0f);
            this.a.setAntiAlias(true);
            (this.b = new Paint()).setColor(-1287371708);
            this.b.setStyle(Paint$Style.FILL);
            this.b.setAntiAlias(true);
            (this.c = new Paint()).setColor(-1);
            this.c.setStyle(Paint$Style.STROKE);
            this.c.setStrokeWidth(6.0f);
            this.c.setAntiAlias(true);
        }
        final float b = x.b;
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams((int)(this.d * b), (int)(b * this.d));
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        this.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
    
    protected void onDraw(final Canvas canvas) {
        if (this.e) {
            if (canvas.isHardwareAccelerated() && Build$VERSION.SDK_INT < 17) {
                this.setLayerType(1, (Paint)null);
            }
            final int min = Math.min(canvas.getWidth(), canvas.getHeight());
            final int n = min / 2;
            final int n2 = min / 2;
            final int n3 = n * 2 / 3;
            canvas.drawCircle((float)n, (float)n2, (float)n3, this.a);
            canvas.drawCircle((float)n, (float)n2, (float)(n3 - 2), this.b);
            final int n4 = min / 3;
            final int n5 = min / 3;
            canvas.drawLine((float)n4, (float)n5, (float)(n4 * 2), (float)(n5 * 2), this.c);
            canvas.drawLine((float)(n4 * 2), (float)n5, (float)n4, (float)(n5 * 2), this.c);
        }
        super.onDraw(canvas);
    }
}
