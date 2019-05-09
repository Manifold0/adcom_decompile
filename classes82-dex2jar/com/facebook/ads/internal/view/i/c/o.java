// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i.c;

import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.animation.TimeInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.util.AttributeSet;
import com.facebook.ads.internal.view.i.b.c;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.l;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.b.j;
import com.facebook.ads.internal.o.d;
import com.facebook.ads.internal.view.i.b.p;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.o.f;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.view.i.a;
import android.widget.ProgressBar;
import java.util.concurrent.atomic.AtomicInteger;
import android.animation.ObjectAnimator;
import com.facebook.ads.internal.view.i.a.b;
import android.widget.RelativeLayout;

public class o extends RelativeLayout implements b
{
    private static final int a;
    private ObjectAnimator b;
    private AtomicInteger c;
    private ProgressBar d;
    @Nullable
    private a e;
    private f f;
    private f g;
    private f h;
    private f i;
    
    static {
        a = (int)(6.0f * x.b);
    }
    
    public o(final Context context) {
        this(context, o.a, -12549889);
    }
    
    public o(final Context context, final int n, final int progressBarColor) {
        super(context);
        this.f = new p() {
            @Override
            public void a(final com.facebook.ads.internal.view.i.b.o o) {
                if (o.this.e != null) {
                    o.a(o.this, o.this.e.getDuration(), o.this.e.getCurrentPositionInMillis());
                }
            }
        };
        this.g = new j() {
            @Override
            public void a(final i i) {
                o.this.b();
            }
        };
        this.h = new l() {
            @Override
            public void a(final k k) {
                if (o.this.e != null) {
                    o.a(o.this, o.this.e.getDuration(), o.this.e.getCurrentPositionInMillis());
                }
            }
        };
        this.i = new com.facebook.ads.internal.view.i.b.d() {
            @Override
            public void a(final c c) {
                if (o.this.e != null) {
                    o.c(o.this);
                }
            }
        };
        this.c = new AtomicInteger(-1);
        (this.d = new ProgressBar(context, (AttributeSet)null, 16842872)).setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, n));
        this.setProgressBarColor(progressBarColor);
        this.d.setMax(10000);
        this.addView((View)this.d);
    }
    
    static /* synthetic */ void a(final o o, final int n, final int n2) {
        o.b();
        if (o.c.get() >= n2 || n <= n2) {
            return;
        }
        (o.b = ObjectAnimator.ofInt((Object)o.d, "progress", new int[] { n2 * 10000 / n, Math.min(n2 + 250, n) * 10000 / n })).setDuration((long)Math.min(250, n - n2));
        o.b.setInterpolator((TimeInterpolator)new LinearInterpolator());
        o.b.start();
        o.c.set(n2);
    }
    
    private void b() {
        if (this.b != null) {
            this.b.cancel();
            this.b.setTarget((Object)null);
            this.b = null;
            this.d.clearAnimation();
        }
    }
    
    static /* synthetic */ void c(final o o) {
        o.b();
        (o.b = ObjectAnimator.ofInt((Object)o.d, "progress", new int[] { 0, 0 })).setDuration(0L);
        o.b.setInterpolator((TimeInterpolator)new LinearInterpolator());
        o.b.start();
        o.c.set(0);
    }
    
    public void a() {
        this.b();
        this.d = null;
        this.e = null;
    }
    
    public void a(final a e) {
        this.e = e;
        e.getEventBus().a(this.g, this.h, this.f, this.i);
    }
    
    public void b(final a a) {
        a.getEventBus().b(this.f, this.h, this.g, this.i);
        this.e = null;
    }
    
    public void setProgressBarColor(final int n) {
        final LayerDrawable progressDrawable = new LayerDrawable(new Drawable[] { (Drawable)new ColorDrawable(0), (Drawable)new ColorDrawable(0), (Drawable)new ScaleDrawable((Drawable)new ColorDrawable(n), 8388611, 1.0f, -1.0f) });
        progressDrawable.setId(0, 16908288);
        progressDrawable.setId(1, 16908303);
        progressDrawable.setId(2, 16908301);
        this.d.setProgressDrawable((Drawable)progressDrawable);
    }
}
