// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.graphics.Matrix;
import android.view.animation.Transformation;
import android.graphics.Camera;
import android.view.animation.Animation;

public final class ak extends Animation
{
    private final boolean a;
    private final float b;
    private final float c;
    private final int d;
    private final float e;
    private final int f;
    private final float g;
    private float h;
    private float i;
    private Camera j;
    
    public ak(final boolean a, final float b, final float c, final int d, final float e, final int f, final float g) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
    }
    
    protected final void applyTransformation(float h, final Transformation transformation) {
        final float b = this.b;
        h = b + (this.c - b) * h;
        final Matrix matrix = transformation.getMatrix();
        final Camera j = this.j;
        j.save();
        if (this.a) {
            j.rotateX(h);
        }
        else {
            j.rotateY(h);
        }
        j.getMatrix(matrix);
        j.restore();
        h = this.h;
        final float i = this.i;
        matrix.preTranslate(-h, -i);
        matrix.postTranslate(h, i);
    }
    
    public final void initialize(final int n, final int n2, final int n3, final int n4) {
        super.initialize(n, n2, n3, n4);
        this.h = this.resolveSize(this.d, this.e, n, n3);
        this.i = this.resolveSize(this.f, this.g, n2, n4);
        this.j = new Camera();
    }
}
