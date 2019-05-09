// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.graphics.Paint;
import android.graphics.Canvas;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.Bitmap;
import android.view.View;

public final class hq extends View
{
    public boolean a;
    private Bitmap b;
    private Rect c;
    private Rect d;
    private Rect e;
    private Rect f;
    
    public hq(final Context context) {
        super(context);
        this.a = false;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = new Rect();
    }
    
    final void a() {
        if (this.a) {
            this.e = this.c;
            return;
        }
        this.e = this.d;
    }
    
    public final void onDraw(final Canvas canvas) {
        if (this.e != null && this.b != null) {
            this.getDrawingRect(this.f);
            canvas.drawBitmap(this.b, this.e, this.f, (Paint)null);
        }
    }
    
    public final void setImageBitmap(final Bitmap b) {
        this.b = b;
        final int width = this.b.getWidth();
        final int height = this.b.getHeight();
        this.d = new Rect(0, 0, width / 2, height);
        this.c = new Rect(width / 2, 0, width, height);
        this.a();
    }
}
