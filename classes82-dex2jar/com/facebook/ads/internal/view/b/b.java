// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.b;

import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.annotation.TargetApi;
import android.widget.ProgressBar;

@TargetApi(19)
public class b extends ProgressBar
{
    private static final int a;
    private static final int b;
    private Rect c;
    private Paint d;
    
    static {
        a = Color.argb(26, 0, 0, 0);
        b = Color.rgb(88, 144, 255);
    }
    
    public b(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.setIndeterminate(false);
        this.setMax(100);
        this.setProgressDrawable((Drawable)new LayerDrawable(new Drawable[] { (Drawable)new ColorDrawable(0), (Drawable)new ClipDrawable((Drawable)new ColorDrawable(com.facebook.ads.internal.view.b.b.b), 3, 1) }));
        this.c = new Rect();
        (this.d = new Paint()).setStyle(Paint$Style.FILL);
        this.d.setColor(com.facebook.ads.internal.view.b.b.a);
    }
    
    protected void onDraw(final Canvas canvas) {
        synchronized (this) {
            canvas.drawRect(this.c, this.d);
            super.onDraw(canvas);
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        synchronized (this) {
            super.onMeasure(n, n2);
            this.c.set(0, 0, this.getMeasuredWidth(), 2);
        }
    }
    
    public void setProgress(int max) {
        // monitorenter(this)
        Label_0018: {
            if (max != 100) {
                break Label_0018;
            }
            max = 0;
            try {
                while (true) {
                    super.setProgress(max);
                    return;
                    max = Math.max(max, 5);
                    continue;
                }
            }
            finally {
            }
            // monitorexit(this)
        }
    }
}
