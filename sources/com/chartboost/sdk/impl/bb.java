package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public abstract class bb extends View {
    /* renamed from: a */
    private Bitmap f3121a = null;
    /* renamed from: b */
    private Canvas f3122b = null;

    /* renamed from: a */
    protected abstract void mo4323a(Canvas canvas);

    public bb(Context context) {
        super(context);
        m3475a(context);
    }

    /* renamed from: a */
    private void m3475a(Context context) {
        try {
            getClass().getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class}).invoke(this, new Object[]{Integer.valueOf(1), null});
        } catch (Exception e) {
        }
    }

    /* renamed from: b */
    private boolean m3476b(Canvas canvas) {
        try {
            return ((Boolean) Canvas.class.getMethod("isHardwareAccelerated", new Class[0]).invoke(canvas, new Object[0])).booleanValue();
        } catch (Exception e) {
            return false;
        }
    }

    protected final void onDraw(Canvas canvas) {
        Canvas canvas2;
        boolean b = m3476b(canvas);
        if (b) {
            if (!(this.f3121a != null && this.f3121a.getWidth() == canvas.getWidth() && this.f3121a.getHeight() == canvas.getHeight())) {
                if (!(this.f3121a == null || this.f3121a.isRecycled())) {
                    this.f3121a.recycle();
                }
                try {
                    this.f3121a = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Config.ARGB_8888);
                    this.f3122b = new Canvas(this.f3121a);
                } catch (Throwable th) {
                    return;
                }
            }
            this.f3121a.eraseColor(0);
            canvas2 = canvas;
            canvas = this.f3122b;
        } else {
            canvas2 = null;
        }
        mo4323a(canvas);
        if (b) {
            canvas2.drawBitmap(this.f3121a, 0.0f, 0.0f, null);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!(this.f3121a == null || this.f3121a.isRecycled())) {
            this.f3121a.recycle();
        }
        this.f3121a = null;
    }
}
