package com.applovin.impl.adview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import com.applovin.sdk.AppLovinSdk;

public class cj extends ak {
    /* renamed from: c */
    private float f1858c = 30.0f;
    /* renamed from: d */
    private float f1859d = 2.0f;
    /* renamed from: e */
    private float f1860e = 8.0f;
    /* renamed from: f */
    private float f1861f = 2.0f;
    /* renamed from: g */
    private float f1862g = 1.0f;

    public cj(AppLovinSdk appLovinSdk, Context context) {
        super(appLovinSdk, context);
    }

    /* renamed from: a */
    protected float m2075a() {
        return this.f1858c * this.f1862g;
    }

    /* renamed from: a */
    public void m2076a(float f) {
        this.f1862g = f;
    }

    /* renamed from: a */
    public void mo4047a(int i) {
        m2076a(((float) i) / this.f1858c);
    }

    /* renamed from: b */
    protected float m2078b() {
        return this.f1860e * this.f1862g;
    }

    /* renamed from: c */
    protected float m2079c() {
        return this.f1861f * this.f1862g;
    }

    /* renamed from: d */
    protected float m2080d() {
        return m2075a() / 2.0f;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float d = m2080d();
        Paint paint = new Paint(1);
        paint.setARGB(80, 0, 0, 0);
        canvas.drawCircle(d, d, d, paint);
        Paint paint2 = new Paint(1);
        paint2.setColor(-1);
        paint2.setStyle(Style.STROKE);
        paint2.setStrokeWidth(m2079c());
        float b = m2078b();
        float a = m2075a() - b;
        canvas.drawLine(b, b, a, a, paint2);
        canvas.drawLine(b, a, a, b, paint2);
    }
}
