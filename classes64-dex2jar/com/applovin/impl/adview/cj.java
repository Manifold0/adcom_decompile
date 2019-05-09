// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.content.Context;
import com.applovin.sdk.AppLovinSdk;

public class cj extends ak
{
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    
    public cj(final AppLovinSdk appLovinSdk, final Context context) {
        super(appLovinSdk, context);
        this.c = 30.0f;
        this.d = 2.0f;
        this.e = 8.0f;
        this.f = 2.0f;
        this.g = 1.0f;
    }
    
    protected float a() {
        return this.c * this.g;
    }
    
    public void a(final float g) {
        this.g = g;
    }
    
    @Override
    public void a(final int n) {
        this.a(n / this.c);
    }
    
    protected float b() {
        return this.e * this.g;
    }
    
    protected float c() {
        return this.f * this.g;
    }
    
    protected float d() {
        return this.a() / 2.0f;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        final float d = this.d();
        final Paint paint = new Paint(1);
        paint.setARGB(80, 0, 0, 0);
        canvas.drawCircle(d, d, d, paint);
        final Paint paint2 = new Paint(1);
        paint2.setColor(-1);
        paint2.setStyle(Paint$Style.STROKE);
        paint2.setStrokeWidth(this.c());
        final float b = this.b();
        final float n = this.a() - b;
        canvas.drawLine(b, b, n, n, paint2);
        canvas.drawLine(b, n, n, b, paint2);
    }
}
