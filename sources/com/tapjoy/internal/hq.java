package com.tapjoy.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

public final class hq extends View {
    /* renamed from: a */
    public boolean f8145a = false;
    /* renamed from: b */
    private Bitmap f8146b = null;
    /* renamed from: c */
    private Rect f8147c = null;
    /* renamed from: d */
    private Rect f8148d = null;
    /* renamed from: e */
    private Rect f8149e = null;
    /* renamed from: f */
    private Rect f8150f = new Rect();

    public hq(Context context) {
        super(context);
    }

    public final void setImageBitmap(Bitmap bitmap) {
        this.f8146b = bitmap;
        int width = this.f8146b.getWidth();
        int height = this.f8146b.getHeight();
        this.f8148d = new Rect(0, 0, width / 2, height);
        this.f8147c = new Rect(width / 2, 0, width, height);
        m8083a();
    }

    /* renamed from: a */
    final void m8083a() {
        if (this.f8145a) {
            this.f8149e = this.f8147c;
        } else {
            this.f8149e = this.f8148d;
        }
    }

    public final void onDraw(Canvas canvas) {
        if (this.f8149e != null && this.f8146b != null) {
            getDrawingRect(this.f8150f);
            canvas.drawBitmap(this.f8146b, this.f8149e, this.f8150f, null);
        }
    }
}
