// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.graphics.Matrix;
import android.view.animation.Transformation;
import android.graphics.Camera;
import android.view.animation.Animation;

public final class ba extends Animation
{
    private final float a;
    private final float b;
    private final float c;
    private final float d;
    private boolean e;
    private Camera f;
    
    public ba(final float a, final float b, final float c, final float d, final boolean e) {
        this.e = true;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    protected void applyTransformation(float n, final Transformation transformation) {
        n = this.a + (this.b - this.a) * n;
        final Camera f = this.f;
        final Matrix matrix = transformation.getMatrix();
        f.save();
        if (this.e) {
            f.rotateY(n);
        }
        else {
            f.rotateX(n);
        }
        f.getMatrix(matrix);
        f.restore();
        matrix.preTranslate(-this.c, -this.d);
        matrix.postTranslate(this.c, this.d);
    }
    
    public void initialize(final int n, final int n2, final int n3, final int n4) {
        super.initialize(n, n2, n3, n4);
        this.f = new Camera();
    }
}
