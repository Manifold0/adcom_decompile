// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.graphics.Path$Direction;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.content.Context;
import android.graphics.RectF;
import android.graphics.Path;
import android.graphics.Paint;

public class x extends bb
{
    private Paint a;
    private Paint b;
    private Path c;
    private RectF d;
    private RectF e;
    private int f;
    private float g;
    private float h;
    
    public x(final Context context) {
        super(context);
        this.f = 0;
        this.a(context);
    }
    
    private void a(final Context context) {
        final float density = context.getResources().getDisplayMetrics().density;
        this.g = 4.5f * density;
        (this.a = new Paint()).setColor(-1);
        this.a.setStyle(Paint$Style.STROKE);
        this.a.setStrokeWidth(density * 1.0f);
        this.a.setAntiAlias(true);
        (this.b = new Paint()).setColor(-855638017);
        this.b.setStyle(Paint$Style.FILL);
        this.b.setAntiAlias(true);
        this.c = new Path();
        this.e = new RectF();
        this.d = new RectF();
    }
    
    public void a(final float h) {
        this.h = h;
        if (this.getVisibility() != 8) {
            this.invalidate();
        }
    }
    
    public void a(final int f) {
        this.f = f;
        this.invalidate();
    }
    
    @Override
    protected void a(final Canvas canvas) {
        final float density = this.getContext().getResources().getDisplayMetrics().density;
        this.d.set(0.0f, 0.0f, (float)this.getWidth(), (float)this.getHeight());
        final int min = Math.min(1, Math.round(density * 0.5f));
        this.d.inset((float)min, (float)min);
        this.c.reset();
        this.c.addRoundRect(this.d, this.g, this.g, Path$Direction.CW);
        canvas.save();
        canvas.clipPath(this.c);
        canvas.drawColor(this.f);
        this.e.set(this.d);
        this.e.right = (this.e.right - this.e.left) * this.h + this.e.left;
        canvas.drawRect(this.e, this.b);
        canvas.restore();
        canvas.drawRoundRect(this.d, this.g, this.g, this.a);
    }
    
    public void b(final float g) {
        this.g = g;
    }
    
    public void b(final int color) {
        this.a.setColor(color);
        this.invalidate();
    }
    
    public void c(final int color) {
        this.b.setColor(color);
        this.invalidate();
    }
}
