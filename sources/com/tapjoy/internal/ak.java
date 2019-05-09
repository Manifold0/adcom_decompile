package com.tapjoy.internal;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public final class ak extends Animation {
    /* renamed from: a */
    private final boolean f7175a;
    /* renamed from: b */
    private final float f7176b;
    /* renamed from: c */
    private final float f7177c;
    /* renamed from: d */
    private final int f7178d;
    /* renamed from: e */
    private final float f7179e;
    /* renamed from: f */
    private final int f7180f;
    /* renamed from: g */
    private final float f7181g;
    /* renamed from: h */
    private float f7182h;
    /* renamed from: i */
    private float f7183i;
    /* renamed from: j */
    private Camera f7184j;

    public ak(boolean z, float f, float f2, int i, float f3, int i2, float f4) {
        this.f7175a = z;
        this.f7176b = f;
        this.f7177c = f2;
        this.f7178d = i;
        this.f7179e = f3;
        this.f7180f = i2;
        this.f7181g = f4;
    }

    public final void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        this.f7182h = resolveSize(this.f7178d, this.f7179e, width, parentWidth);
        this.f7183i = resolveSize(this.f7180f, this.f7181g, height, parentHeight);
        this.f7184j = new Camera();
    }

    protected final void applyTransformation(float interpolatedTime, Transformation t) {
        float f = this.f7176b;
        f += (this.f7177c - f) * interpolatedTime;
        Matrix matrix = t.getMatrix();
        Camera camera = this.f7184j;
        camera.save();
        if (this.f7175a) {
            camera.rotateX(f);
        } else {
            camera.rotateY(f);
        }
        camera.getMatrix(matrix);
        camera.restore();
        f = this.f7182h;
        float f2 = this.f7183i;
        matrix.preTranslate(-f, -f2);
        matrix.postTranslate(f, f2);
    }
}
