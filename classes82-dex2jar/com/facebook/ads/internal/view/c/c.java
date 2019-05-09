// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.c;

import android.graphics.ColorFilter;
import android.support.annotation.IntRange;
import android.text.StaticLayout;
import android.text.Layout$Alignment;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.graphics.Paint$Style;
import android.graphics.Color;
import com.facebook.ads.internal.x.a;
import java.lang.ref.WeakReference;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.graphics.Path;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

public class c extends Drawable
{
    private final Paint a;
    private final Paint b;
    private final Path c;
    private final TextPaint d;
    private final Paint e;
    private int f;
    private int g;
    private String h;
    private int i;
    private boolean j;
    @Nullable
    private String k;
    private String l;
    private long m;
    private final Handler n;
    @Nullable
    private WeakReference<a> o;
    private final Runnable p;
    
    public c() {
        this.a = new Paint();
        this.b = new Paint();
        this.c = new Path();
        this.d = new TextPaint();
        this.e = new Paint();
        this.n = new Handler();
        this.p = new Runnable() {
            @Override
            public void run() {
                com.facebook.ads.internal.view.c.c.this.c();
                if (com.facebook.ads.internal.view.c.c.this.j) {
                    com.facebook.ads.internal.view.c.c.this.n.postDelayed(com.facebook.ads.internal.view.c.c.this.p, 250L);
                }
            }
        };
        this.a.setColor(Color.argb(127, 36, 36, 36));
        this.a.setStyle(Paint$Style.FILL_AND_STROKE);
        this.b.setAntiAlias(true);
        this.b.setColor(Color.argb(191, 0, 255, 0));
        this.b.setStrokeWidth(20.0f);
        this.b.setStyle(Paint$Style.STROKE);
        this.d.setAntiAlias(true);
        this.d.setColor(-1);
        this.d.setStyle(Paint$Style.FILL_AND_STROKE);
        this.d.setTextSize(30.0f);
        this.e.setColor(Color.argb(212, 0, 0, 0));
        this.e.setStyle(Paint$Style.FILL_AND_STROKE);
        this.b();
    }
    
    private void c() {
        final StringBuilder sb = new StringBuilder();
        if (this.f <= 0) {
            if (!TextUtils.isEmpty((CharSequence)this.k)) {
                sb.append(this.k);
                sb.append("\n");
            }
            if (!TextUtils.isEmpty((CharSequence)this.l)) {
                sb.append(this.l);
                sb.append("\n");
            }
            sb.append("Sdk ");
            sb.append("5.1.0");
            sb.append(", Loaded ");
            if (this.m > 0L) {
                final long max = Math.max(0L, System.currentTimeMillis() - this.m);
                final int n = (int)(max / 3600000L);
                final long n2 = max % 3600000L;
                final int n3 = (int)(n2 / 60000L);
                final int n4 = (int)(n2 % 60000L / 1000L);
                if (n > 0) {
                    sb.append(n);
                    sb.append("h ");
                }
                if (n > 0 || n3 > 0) {
                    sb.append(n3);
                    sb.append("m ");
                }
                sb.append(n4);
                sb.append("s ago");
            }
            else {
                sb.append("Unknown");
            }
        }
        else {
            sb.append("Card ");
            sb.append(this.g + 1);
            sb.append(" of ");
            sb.append(this.f);
        }
        sb.append("\nView: ");
        if (this.o == null || this.o.get() == null) {
            sb.append("Viewability Checker not set");
        }
        else {
            sb.append(this.o.get().d());
        }
        this.h = sb.toString();
        final String[] split = this.h.split("\n");
        final int length = split.length;
        float max2 = -2.14748365E9f;
        for (int i = 0; i < length; ++i) {
            final String s = split[i];
            max2 = Math.max(max2, this.d.measureText(s, 0, s.length()));
        }
        this.i = (int)(0.5f + max2);
        this.invalidateSelf();
    }
    
    public void a(final int f, final int g) {
        this.f = f;
        this.g = g;
        this.c();
    }
    
    public void a(final long m) {
        this.m = m;
        this.c();
    }
    
    public void a(final a a) {
        this.o = new WeakReference<a>(a);
        this.c();
    }
    
    public void a(final String k) {
        this.k = k;
        this.c();
    }
    
    public void a(final boolean j) {
        this.j = j;
        if (this.j) {
            this.n.post(this.p);
        }
        else {
            this.n.removeCallbacks(this.p);
        }
        this.invalidateSelf();
    }
    
    public boolean a() {
        return this.j;
    }
    
    public void b() {
        this.f = 0;
        this.g = -1;
        this.h = "Initializing...";
        this.i = 100;
        this.k = null;
        this.m = -1L;
        this.o = null;
        this.a(false);
    }
    
    public void b(final String l) {
        this.l = l;
        this.c();
    }
    
    public void draw(final Canvas canvas) {
        if (!this.j) {
            return;
        }
        final int width = canvas.getWidth();
        final int height = canvas.getHeight();
        canvas.drawRect(0.0f, 0.0f, (float)width, (float)height, this.a);
        final StaticLayout staticLayout = new StaticLayout((CharSequence)this.h, this.d, this.i, Layout$Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        final float n = width / 2.0f;
        final float n2 = height / 2.0f;
        final float n3 = staticLayout.getWidth() / 2.0f;
        final float n4 = staticLayout.getHeight() / 2.0f;
        canvas.drawRect(n - n3 - 40.0f, n2 - n4 - 40.0f, 40.0f + (n + n3), 40.0f + (n2 + n4), this.e);
        canvas.save();
        canvas.translate(n - n3, n2 - n4);
        staticLayout.draw(canvas);
        canvas.restore();
        this.c.reset();
        this.c.moveTo(0.0f, 0.0f);
        this.c.lineTo((float)width, 0.0f);
        this.c.lineTo((float)width, (float)height);
        this.c.lineTo(0.0f, (float)height);
        this.c.lineTo(0.0f, 0.0f);
        canvas.drawPath(this.c, this.b);
    }
    
    public int getOpacity() {
        return -3;
    }
    
    public void setAlpha(@IntRange(from = 0L, to = 255L) final int n) {
    }
    
    public void setColorFilter(@Nullable final ColorFilter colorFilter) {
    }
}
