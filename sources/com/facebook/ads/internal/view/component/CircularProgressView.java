package com.facebook.ads.internal.view.component;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.support.annotation.Keep;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class CircularProgressView extends View {
    /* renamed from: a */
    private final float f5309a = (3.0f * Resources.getSystem().getDisplayMetrics().density);
    /* renamed from: b */
    private final RectF f5310b = new RectF();
    /* renamed from: c */
    private final Paint f5311c = new Paint(1);
    /* renamed from: d */
    private final Paint f5312d;
    /* renamed from: e */
    private float f5313e = 0.0f;

    public CircularProgressView(Context context) {
        super(context);
        this.f5311c.setStyle(Style.STROKE);
        this.f5311c.setStrokeWidth(this.f5309a);
        this.f5312d = new Paint(1);
        this.f5312d.setStyle(Style.STROKE);
        this.f5312d.setStrokeWidth(this.f5309a);
    }

    /* renamed from: a */
    public void m5780a(int i, int i2) {
        this.f5311c.setColor(i);
        this.f5312d.setColor(i2);
    }

    @Keep
    public float getProgress() {
        return this.f5313e;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(this.f5310b, 0.0f, 360.0f, false, this.f5311c);
        canvas.drawArc(this.f5310b, -90.0f, (this.f5313e * 360.0f) / 100.0f, false, this.f5312d);
    }

    protected void onMeasure(int i, int i2) {
        int min = Math.min(getDefaultSize(getSuggestedMinimumHeight(), i2), getDefaultSize(getSuggestedMinimumWidth(), i));
        setMeasuredDimension(min, min);
        this.f5310b.set(((this.f5309a / 2.0f) + 0.0f) + ((float) getPaddingLeft()), ((this.f5309a / 2.0f) + 0.0f) + ((float) getPaddingTop()), (((float) min) - (this.f5309a / 2.0f)) - ((float) getPaddingRight()), (((float) min) - (this.f5309a / 2.0f)) - ((float) getPaddingBottom()));
    }

    @Keep
    public void setProgress(float f) {
        this.f5313e = Math.min(f, 100.0f);
        postInvalidate();
    }

    public void setProgressWithAnimation(float f) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, NotificationCompat.CATEGORY_PROGRESS, new float[]{f});
        ofFloat.setDuration(400);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.start();
    }
}
