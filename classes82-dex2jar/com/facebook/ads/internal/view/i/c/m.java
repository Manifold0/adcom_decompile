// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i.c;

import android.os.Build$VERSION;
import android.graphics.Canvas;
import android.view.View;
import com.facebook.ads.internal.w.b.x;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Style;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.widget.Button;

public class m extends Button
{
    private final Path a;
    private final Path b;
    private final Paint c;
    private final Path d;
    private boolean e;
    
    public m(final Context context) {
        this(context, false);
    }
    
    public m(final Context context, final boolean b) {
        super(context);
        this.e = false;
        this.a = new Path();
        this.b = new Path();
        this.d = new Path();
        this.c = new Paint() {
            {
                this.setStyle(Paint$Style.FILL_AND_STROKE);
                this.setStrokeCap(Paint$Cap.ROUND);
                this.setStrokeWidth(3.0f);
                this.setAntiAlias(true);
                int color;
                if (b) {
                    color = -1;
                }
                else {
                    color = -10066330;
                }
                this.setColor(color);
            }
        };
        this.setClickable(true);
        x.a((View)this, 0);
    }
    
    protected void onDraw(final Canvas canvas) {
        if (canvas.isHardwareAccelerated() && Build$VERSION.SDK_INT < 17) {
            this.setLayerType(1, (Paint)null);
        }
        final float n = Math.max(canvas.getWidth(), canvas.getHeight()) / 100.0f;
        if (this.e) {
            this.d.rewind();
            this.d.moveTo(26.5f * n, 15.5f * n);
            this.d.lineTo(26.5f * n, 84.5f * n);
            this.d.lineTo(90.0f * n, 50.0f * n);
            this.d.lineTo(26.5f * n, n * 15.5f);
            this.d.close();
            canvas.drawPath(this.d, this.c);
        }
        else {
            this.a.rewind();
            this.a.moveTo(29.0f * n, 21.0f * n);
            this.a.lineTo(29.0f * n, 79.0f * n);
            this.a.lineTo(45.0f * n, 79.0f * n);
            this.a.lineTo(45.0f * n, 21.0f * n);
            this.a.lineTo(29.0f * n, 21.0f * n);
            this.a.close();
            this.b.rewind();
            this.b.moveTo(55.0f * n, 21.0f * n);
            this.b.lineTo(55.0f * n, 79.0f * n);
            this.b.lineTo(71.0f * n, 79.0f * n);
            this.b.lineTo(71.0f * n, 21.0f * n);
            this.b.lineTo(55.0f * n, n * 21.0f);
            this.b.close();
            canvas.drawPath(this.a, this.c);
            canvas.drawPath(this.b, this.c);
        }
        super.onDraw(canvas);
    }
    
    public void setChecked(final boolean e) {
        this.e = e;
        this.refreshDrawableState();
        this.invalidate();
    }
}
