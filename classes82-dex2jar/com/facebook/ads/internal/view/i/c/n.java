// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i.c;

import android.graphics.Canvas;
import com.facebook.ads.internal.o.f;
import android.graphics.Paint$Style;
import com.facebook.ads.internal.view.i.b.c;
import com.facebook.ads.internal.view.i.b.o;
import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.view.i.a;
import com.facebook.ads.internal.view.i.b.d;
import com.facebook.ads.internal.view.i.b.p;
import android.graphics.Rect;
import android.graphics.Paint;
import com.facebook.ads.internal.view.i.a.b;
import android.view.View;

public class n extends View implements b
{
    private final Paint a;
    private final Rect b;
    private float c;
    private final p d;
    private final d e;
    @Nullable
    private a f;
    
    public n(final Context context) {
        super(context);
        this.d = new p() {
            @Override
            public void a(final o o) {
                if (n.this.f != null) {
                    final int duration = n.this.f.getDuration();
                    if (duration > 0) {
                        n.this.c = n.this.f.getCurrentPositionInMillis() / (float)duration;
                    }
                    else {
                        n.this.c = 0.0f;
                    }
                    n.this.postInvalidate();
                }
            }
        };
        this.e = new d() {
            @Override
            public void a(final c c) {
                if (n.this.f != null) {
                    n.this.c = 0.0f;
                    n.this.postInvalidate();
                }
            }
        };
        (this.a = new Paint()).setStyle(Paint$Style.FILL);
        this.a.setColor(-9528840);
        this.b = new Rect();
    }
    
    public void a(final a f) {
        this.f = f;
        f.getEventBus().a(this.d, this.e);
    }
    
    public void b(final a a) {
        a.getEventBus().b(this.e, this.d);
        this.f = null;
    }
    
    public void draw(final Canvas canvas) {
        this.b.set(0, 0, (int)(this.getWidth() * this.c), this.getHeight());
        canvas.drawRect(this.b, this.a);
        super.draw(canvas);
    }
}
