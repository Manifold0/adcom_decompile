package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;

/* renamed from: com.chartboost.sdk.impl.x */
public class C1471x extends bb {
    /* renamed from: a */
    private Paint f3396a;
    /* renamed from: b */
    private Paint f3397b;
    /* renamed from: c */
    private Path f3398c;
    /* renamed from: d */
    private RectF f3399d;
    /* renamed from: e */
    private RectF f3400e;
    /* renamed from: f */
    private int f3401f = 0;
    /* renamed from: g */
    private float f3402g;
    /* renamed from: h */
    private float f3403h;

    public C1471x(Context context) {
        super(context);
        m3697a(context);
    }

    /* renamed from: a */
    private void m3697a(Context context) {
        float f = context.getResources().getDisplayMetrics().density;
        this.f3402g = 4.5f * f;
        this.f3396a = new Paint();
        this.f3396a.setColor(-1);
        this.f3396a.setStyle(Style.STROKE);
        this.f3396a.setStrokeWidth(f * 1.0f);
        this.f3396a.setAntiAlias(true);
        this.f3397b = new Paint();
        this.f3397b.setColor(-855638017);
        this.f3397b.setStyle(Style.FILL);
        this.f3397b.setAntiAlias(true);
        this.f3398c = new Path();
        this.f3400e = new RectF();
        this.f3399d = new RectF();
    }

    /* renamed from: a */
    protected void mo4323a(Canvas canvas) {
        float f = getContext().getResources().getDisplayMetrics().density;
        this.f3399d.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        int min = Math.min(1, Math.round(f * 0.5f));
        this.f3399d.inset((float) min, (float) min);
        this.f3398c.reset();
        this.f3398c.addRoundRect(this.f3399d, this.f3402g, this.f3402g, Direction.CW);
        canvas.save();
        canvas.clipPath(this.f3398c);
        canvas.drawColor(this.f3401f);
        this.f3400e.set(this.f3399d);
        this.f3400e.right = ((this.f3400e.right - this.f3400e.left) * this.f3403h) + this.f3400e.left;
        canvas.drawRect(this.f3400e, this.f3397b);
        canvas.restore();
        canvas.drawRoundRect(this.f3399d, this.f3402g, this.f3402g, this.f3396a);
    }

    /* renamed from: a */
    public void m3699a(int i) {
        this.f3401f = i;
        invalidate();
    }

    /* renamed from: b */
    public void m3702b(int i) {
        this.f3396a.setColor(i);
        invalidate();
    }

    /* renamed from: c */
    public void m3703c(int i) {
        this.f3397b.setColor(i);
        invalidate();
    }

    /* renamed from: a */
    public void m3698a(float f) {
        this.f3403h = f;
        if (getVisibility() != 8) {
            invalidate();
        }
    }

    /* renamed from: b */
    public void m3701b(float f) {
        this.f3402g = f;
    }
}
