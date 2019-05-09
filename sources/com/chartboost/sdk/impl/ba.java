package com.chartboost.sdk.impl;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public final class ba extends Animation {
    /* renamed from: a */
    private final float f3115a;
    /* renamed from: b */
    private final float f3116b;
    /* renamed from: c */
    private final float f3117c;
    /* renamed from: d */
    private final float f3118d;
    /* renamed from: e */
    private boolean f3119e = true;
    /* renamed from: f */
    private Camera f3120f;

    public ba(float f, float f2, float f3, float f4, boolean z) {
        this.f3115a = f;
        this.f3116b = f2;
        this.f3117c = f3;
        this.f3118d = f4;
        this.f3119e = z;
    }

    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        this.f3120f = new Camera();
    }

    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float f = this.f3115a + ((this.f3116b - this.f3115a) * interpolatedTime);
        Camera camera = this.f3120f;
        Matrix matrix = t.getMatrix();
        camera.save();
        if (this.f3119e) {
            camera.rotateY(f);
        } else {
            camera.rotateX(f);
        }
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-this.f3117c, -this.f3118d);
        matrix.postTranslate(this.f3117c, this.f3118d);
    }
}
