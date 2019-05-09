package com.applovin.impl.adview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.v4.view.ViewCompat;
import com.applovin.sdk.AppLovinSdk;

public class cq extends ak {
    /* renamed from: c */
    private float f1878c = 30.0f;
    /* renamed from: d */
    private float f1879d = 2.0f;
    /* renamed from: e */
    private float f1880e = 10.0f;
    /* renamed from: f */
    private float f1881f = 3.0f;
    /* renamed from: g */
    private float f1882g = 1.0f;

    public cq(AppLovinSdk appLovinSdk, Context context) {
        super(appLovinSdk, context);
    }

    /* renamed from: a */
    protected float m2096a() {
        return this.f1878c * this.f1882g;
    }

    /* renamed from: a */
    public void m2097a(float f) {
        this.f1882g = f;
    }

    /* renamed from: a */
    public void mo4047a(int i) {
        m2097a(((float) i) / this.f1878c);
    }

    /* renamed from: b */
    protected float m2099b() {
        return this.f1880e * this.f1882g;
    }

    /* renamed from: c */
    protected float m2100c() {
        return this.f1881f * this.f1882g;
    }

    /* renamed from: d */
    protected float m2101d() {
        return m2096a() / 2.0f;
    }

    /* renamed from: e */
    protected float m2102e() {
        return this.f1879d * this.f1882g;
    }

    /* renamed from: f */
    protected float m2103f() {
        return m2101d() - m2102e();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float d = m2101d();
        Paint paint = new Paint(1);
        paint.setColor(-1);
        canvas.drawCircle(d, d, d, paint);
        Paint paint2 = new Paint(1);
        paint2.setColor(ViewCompat.MEASURED_STATE_MASK);
        canvas.drawCircle(d, d, m2103f(), paint2);
        Paint paint3 = new Paint(paint);
        paint3.setStyle(Style.STROKE);
        paint3.setStrokeWidth(m2100c());
        float b = m2099b();
        float a = m2096a() - b;
        canvas.drawLine(b, b, a, a, paint3);
        canvas.drawLine(b, a, a, b, paint3);
    }
}
