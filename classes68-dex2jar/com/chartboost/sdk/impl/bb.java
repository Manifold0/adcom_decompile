// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.graphics.Bitmap$Config;
import android.graphics.Paint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.view.View;

public abstract class bb extends View
{
    private Bitmap a;
    private Canvas b;
    
    public bb(final Context context) {
        super(context);
        this.a = null;
        this.b = null;
        this.a(context);
    }
    
    private void a(final Context context) {
        try {
            this.getClass().getMethod("setLayerType", Integer.TYPE, Paint.class).invoke(this, 1, null);
        }
        catch (Exception ex) {}
    }
    
    private boolean b(final Canvas canvas) {
        try {
            return (boolean)Canvas.class.getMethod("isHardwareAccelerated", (Class<?>[])new Class[0]).invoke(canvas, new Object[0]);
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    protected abstract void a(final Canvas p0);
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null && !this.a.isRecycled()) {
            this.a.recycle();
        }
        this.a = null;
    }
    
    protected final void onDraw(final Canvas canvas) {
        final boolean b = this.b(canvas);
        while (true) {
            Label_0142: {
                if (!b) {
                    break Label_0142;
                }
                Label_0102: {
                    if (this.a != null && this.a.getWidth() == canvas.getWidth() && this.a.getHeight() == canvas.getHeight()) {
                        break Label_0102;
                    }
                    if (this.a != null && !this.a.isRecycled()) {
                        this.a.recycle();
                    }
                    try {
                        this.a = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap$Config.ARGB_8888);
                        this.b = new Canvas(this.a);
                        this.a.eraseColor(0);
                        final Canvas b2 = this.b;
                        final Canvas canvas2 = canvas;
                        this.a(b2);
                        if (b) {
                            canvas2.drawBitmap(this.a, 0.0f, 0.0f, (Paint)null);
                        }
                        return;
                    }
                    catch (Throwable t) {
                        return;
                    }
                }
            }
            final Canvas canvas2 = null;
            final Canvas b2 = canvas;
            continue;
        }
    }
}
