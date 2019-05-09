// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i.c;

import android.graphics.Path;
import android.graphics.Canvas;
import com.facebook.ads.internal.o.f;
import android.graphics.MaskFilter;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter$Blur;
import android.graphics.Paint$Style;
import com.facebook.ads.internal.view.i.b.c;
import com.facebook.ads.internal.view.i.b.o;
import com.facebook.ads.internal.view.i.b.m;
import android.content.Context;
import com.facebook.ads.internal.view.i.b.d;
import com.facebook.ads.internal.view.i.b.p;
import com.facebook.ads.internal.view.i.b.n;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.view.i.a;
import android.graphics.RectF;
import android.graphics.Paint;
import com.facebook.ads.internal.view.i.a.b;
import android.view.View;

public class j extends View implements b
{
    private final Paint a;
    private final Paint b;
    private final Paint c;
    private a d;
    private final Paint e;
    private final RectF f;
    @Nullable
    private com.facebook.ads.internal.view.i.a g;
    private int h;
    private final AtomicInteger i;
    private final AtomicBoolean j;
    private final n k;
    private final p l;
    private final d m;
    
    public j(final Context context, final int h, final int color) {
        super(context);
        this.d = com.facebook.ads.internal.view.i.c.j.a.a;
        this.i = new AtomicInteger(0);
        this.j = new AtomicBoolean(false);
        this.k = new n() {
            @Override
            public void a(final m m) {
                com.facebook.ads.internal.view.i.c.j.this.j.set(true);
            }
        };
        this.l = new p() {
            @Override
            public void a(final o o) {
                if (com.facebook.ads.internal.view.i.c.j.this.g != null) {
                    final int c = com.facebook.ads.internal.view.i.c.j.this.h;
                    final int duration = com.facebook.ads.internal.view.i.c.j.this.g.getDuration();
                    if (c <= 0) {
                        com.facebook.ads.internal.view.i.c.j.this.i.set(0);
                    }
                    else {
                        final int min = Math.min(duration, c * 1000);
                        if (min == 0) {
                            return;
                        }
                        com.facebook.ads.internal.view.i.c.j.this.i.set((min - com.facebook.ads.internal.view.i.c.j.this.g.getCurrentPositionInMillis()) * 100 / min);
                    }
                    com.facebook.ads.internal.view.i.c.j.this.postInvalidate();
                }
            }
        };
        this.m = new d() {
            @Override
            public void a(final c c) {
                com.facebook.ads.internal.view.i.c.j.this.h = 0;
                com.facebook.ads.internal.view.i.c.j.this.i.set(0);
                com.facebook.ads.internal.view.i.c.j.this.postInvalidate();
            }
        };
        final float density = this.getResources().getDisplayMetrics().density;
        this.h = h;
        (this.b = new Paint()).setStyle(Paint$Style.FILL);
        this.b.setColor(color);
        (this.c = new Paint()).setColor(-1);
        this.c.setAlpha(230);
        this.c.setStyle(Paint$Style.FILL);
        this.c.setStrokeWidth(1.0f * density);
        this.c.setAntiAlias(true);
        (this.a = new Paint()).setColor(-16777216);
        this.a.setStyle(Paint$Style.STROKE);
        this.a.setAlpha(102);
        this.a.setStrokeWidth(1.5f * density);
        this.a.setAntiAlias(true);
        this.setLayerType(1, (Paint)null);
        this.a.setMaskFilter((MaskFilter)new BlurMaskFilter(6.0f, BlurMaskFilter$Blur.NORMAL));
        (this.e = new Paint()).setColor(-10066330);
        this.e.setStyle(Paint$Style.STROKE);
        this.e.setStrokeWidth(density * 2.0f);
        this.e.setAntiAlias(true);
        this.f = new RectF();
    }
    
    public void a(final com.facebook.ads.internal.view.i.a g) {
        this.g = g;
        this.g.getEventBus().a(this.k, this.l, this.m);
    }
    
    public boolean a() {
        return this.g != null && (this.h <= 0 || this.i.get() < 0);
    }
    
    public void b(final com.facebook.ads.internal.view.i.a a) {
        this.g.getEventBus().b(this.m, this.l, this.k);
        this.g = null;
    }
    
    public int getSkipSeconds() {
        return this.h;
    }
    
    protected void onDraw(final Canvas canvas) {
        if (!this.j.get()) {
            super.onDraw(canvas);
            return;
        }
        final int min = Math.min(this.getWidth() - this.getPaddingLeft() - this.getPaddingRight(), this.getHeight() - this.getPaddingTop() - this.getPaddingBottom());
        final int n = min / 2;
        final int n2 = min / 2;
        canvas.drawCircle((float)(this.getPaddingLeft() + n), (float)(this.getPaddingTop() + n2), (float)n, this.a);
        canvas.drawCircle((float)(this.getPaddingLeft() + n), (float)(n2 + this.getPaddingTop()), (float)n, this.c);
        if (this.i.get() <= 0) {
            if (this.d == com.facebook.ads.internal.view.i.c.j.a.b) {
                final int n3 = min / 4;
                final int n4 = min / 3;
                final Path path = new Path();
                path.moveTo((float)(this.getPaddingLeft() + n3), (float)(this.getPaddingTop() + n4));
                path.lineTo((float)(this.getPaddingLeft() + n), (float)(this.getPaddingTop() + n));
                path.lineTo((float)(this.getPaddingLeft() + n3), (float)(n4 * 2 + this.getPaddingTop()));
                canvas.drawPath(path, this.e);
                final Path path2 = new Path();
                path2.moveTo((float)(this.getPaddingLeft() + n), (float)(this.getPaddingTop() + n4));
                path2.lineTo((float)(n3 * 3 + this.getPaddingLeft()), (float)(this.getPaddingTop() + n));
                path2.lineTo((float)(n + this.getPaddingLeft()), (float)(n4 * 2 + this.getPaddingTop()));
                canvas.drawPath(path2, this.e);
            }
            else {
                final int n5 = min / 3;
                final int n6 = min / 3;
                canvas.drawLine((float)(this.getPaddingLeft() + n5), (float)(this.getPaddingTop() + n6), (float)(n5 * 2 + this.getPaddingLeft()), (float)(n6 * 2 + this.getPaddingTop()), this.e);
                canvas.drawLine((float)(n5 * 2 + this.getPaddingLeft()), (float)(this.getPaddingTop() + n6), (float)(this.getPaddingLeft() + n5), (float)(n6 * 2 + this.getPaddingTop()), this.e);
            }
        }
        else {
            this.f.set((float)this.getPaddingLeft(), (float)this.getPaddingTop(), (float)(this.getWidth() - this.getPaddingRight()), (float)(this.getHeight() - this.getPaddingBottom()));
            canvas.drawArc(this.f, -90.0f, -(this.i.get() * 360) / 100.0f, true, this.b);
        }
        super.onDraw(canvas);
    }
    
    public void setButtonMode(final a d) {
        this.d = d;
    }
    
    public enum a
    {
        a, 
        b;
    }
}
