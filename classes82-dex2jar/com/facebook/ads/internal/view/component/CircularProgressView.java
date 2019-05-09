// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component;

import android.animation.TimeInterpolator;
import android.view.animation.LinearInterpolator;
import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.support.annotation.Keep;
import android.graphics.Paint$Style;
import android.content.res.Resources;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class CircularProgressView extends View
{
    private final float a;
    private final RectF b;
    private final Paint c;
    private final Paint d;
    private float e;
    
    public CircularProgressView(final Context context) {
        super(context);
        this.a = 3.0f * Resources.getSystem().getDisplayMetrics().density;
        this.e = 0.0f;
        this.b = new RectF();
        (this.c = new Paint(1)).setStyle(Paint$Style.STROKE);
        this.c.setStrokeWidth(this.a);
        (this.d = new Paint(1)).setStyle(Paint$Style.STROKE);
        this.d.setStrokeWidth(this.a);
    }
    
    public void a(final int color, final int color2) {
        this.c.setColor(color);
        this.d.setColor(color2);
    }
    
    @Keep
    public float getProgress() {
        return this.e;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(this.b, 0.0f, 360.0f, false, this.c);
        canvas.drawArc(this.b, -90.0f, this.e * 360.0f / 100.0f, false, this.d);
    }
    
    protected void onMeasure(int min, final int n) {
        min = Math.min(getDefaultSize(this.getSuggestedMinimumHeight(), n), getDefaultSize(this.getSuggestedMinimumWidth(), min));
        this.setMeasuredDimension(min, min);
        this.b.set(this.a / 2.0f + 0.0f + this.getPaddingLeft(), this.a / 2.0f + 0.0f + this.getPaddingTop(), min - this.a / 2.0f - this.getPaddingRight(), min - this.a / 2.0f - this.getPaddingBottom());
    }
    
    @Keep
    public void setProgress(final float n) {
        this.e = Math.min(n, 100.0f);
        this.postInvalidate();
    }
    
    public void setProgressWithAnimation(final float n) {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this, "progress", new float[] { n });
        ofFloat.setDuration(400L);
        ofFloat.setInterpolator((TimeInterpolator)new LinearInterpolator());
        ofFloat.start();
    }
}
