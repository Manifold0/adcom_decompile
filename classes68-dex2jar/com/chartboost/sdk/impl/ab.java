// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.media.MediaPlayer;
import android.view.animation.Animation;
import android.view.animation.AlphaAnimation;
import android.net.Uri;
import android.graphics.Point;
import com.chartboost.sdk.Libraries.h;
import org.json.JSONObject;
import com.chartboost.sdk.Libraries.CBUtility;
import android.widget.LinearLayout$LayoutParams;
import android.widget.ImageView$ScaleType;
import com.chartboost.sdk.Libraries.e;
import android.view.MotionEvent;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.chartboost.sdk.g;
import android.view.View;
import java.util.Locale;
import android.content.Context;
import android.os.Handler;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnCompletionListener;
import android.widget.RelativeLayout;

@SuppressLint({ "ViewConstructor" })
public class ab extends RelativeLayout implements MediaPlayer$OnCompletionListener, MediaPlayer$OnErrorListener, MediaPlayer$OnPreparedListener
{
    private static final CharSequence k;
    final RelativeLayout a;
    final aa b;
    final aa c;
    final az d;
    final TextView e;
    final x f;
    final av g;
    final v h;
    final Handler i;
    final Runnable j;
    private boolean l;
    private boolean m;
    private final Runnable n;
    private final Runnable o;
    
    static {
        k = "00:00";
    }
    
    public ab(final Context context, final v h) {
        super(context);
        this.l = false;
        this.m = false;
        this.n = new Runnable() {
            @Override
            public void run() {
                ab.this.a(false);
            }
        };
        this.o = new Runnable() {
            @Override
            public void run() {
                if (ab.this.b != null) {
                    ab.this.b.setVisibility(8);
                }
                if (ab.this.h.M) {
                    ab.this.f.setVisibility(8);
                }
                ab.this.c.setVisibility(8);
                if (ab.this.d != null) {
                    ab.this.d.setEnabled(false);
                }
            }
        };
        this.j = new Runnable() {
            private int b = 0;
            
            @Override
            public void run() {
                final v.a q = ab.this.h.q();
                if (q != null) {
                    if (ab.this.g.a().e()) {
                        final int d = ab.this.g.a().d();
                        if (d > 0) {
                            ab.this.h.v = d;
                            if (ab.this.h.v / 1000.0f > 0.0f && !ab.this.h.t()) {
                                ab.this.h.r();
                                ab.this.h.a(true);
                            }
                        }
                        final float n = d / (float)ab.this.g.a().c();
                        if (ab.this.h.M) {
                            ab.this.f.a(n);
                        }
                        final int b = d / 1000;
                        if (this.b != b) {
                            this.b = b;
                            ab.this.e.setText((CharSequence)String.format(Locale.US, "%02d:%02d", b / 60, b % 60));
                        }
                    }
                    if (q.f()) {
                        final az d2 = q.d(true);
                        if (d2.getVisibility() == 8) {
                            ab.this.h.a(true, (View)d2);
                            d2.setEnabled(true);
                        }
                    }
                    ab.this.i.removeCallbacks(ab.this.j);
                    ab.this.i.postDelayed(ab.this.j, 16L);
                }
            }
        };
        this.h = h;
        this.i = h.a;
        final JSONObject g = h.g();
        final float density = context.getResources().getDisplayMetrics().density;
        final int round = Math.round(density * 10.0f);
        final g a = com.chartboost.sdk.g.a();
        this.g = a.a(new av(context));
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-1, -2);
        relativeLayout$LayoutParams.addRule(13);
        this.addView((View)this.g, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        this.a = a.a(new RelativeLayout(context));
        if (g != null && !g.isNull("video-click-button")) {
            (this.b = a.a(new aa(context))).setVisibility(8);
            (this.d = new az(context) {
                @Override
                protected void a(final MotionEvent motionEvent) {
                    ab.this.h.b(e.a(e.a("x", motionEvent.getX()), e.a("y", motionEvent.getY()), e.a("w", ab.this.d.getWidth()), e.a("h", ab.this.d.getHeight())));
                }
            }).a(ImageView$ScaleType.FIT_CENTER);
            final h i = h.I;
            final Point b = h.b("video-click-button");
            final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-2, -2);
            linearLayout$LayoutParams.leftMargin = Math.round(b.x / i.f());
            linearLayout$LayoutParams.topMargin = Math.round(b.y / i.f());
            h.a((ViewGroup$LayoutParams)linearLayout$LayoutParams, i, 1.0f);
            this.d.a(i);
            this.b.addView((View)this.d, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(-1, Math.round(linearLayout$LayoutParams.height + 10.0f * density));
            relativeLayout$LayoutParams2.addRule(10);
            this.a.addView((View)this.b, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
        }
        else {
            this.b = null;
            this.d = null;
        }
        (this.c = a.a(new aa(context))).setVisibility(8);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams3 = new RelativeLayout$LayoutParams(-1, Math.round(32.5f * density));
        relativeLayout$LayoutParams3.addRule(12);
        this.a.addView((View)this.c, (ViewGroup$LayoutParams)relativeLayout$LayoutParams3);
        this.c.setGravity(16);
        this.c.setPadding(round, round, round, round);
        (this.e = a.a(new TextView(context))).setTextColor(-1);
        this.e.setTextSize(2, 11.0f);
        this.e.setText(ab.k);
        this.e.setPadding(0, 0, round, 0);
        this.e.setSingleLine();
        this.e.measure(0, 0);
        final int measuredWidth = this.e.getMeasuredWidth();
        this.e.setGravity(17);
        this.c.addView((View)this.e, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(measuredWidth, -1));
        (this.f = a.a(new x(context))).setVisibility(8);
        final LinearLayout$LayoutParams linearLayout$LayoutParams2 = new LinearLayout$LayoutParams(-1, Math.round(10.0f * density));
        linearLayout$LayoutParams2.setMargins(0, CBUtility.a(1, context), 0, 0);
        this.c.addView((View)this.f, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams4 = new RelativeLayout$LayoutParams(-1, -1);
        relativeLayout$LayoutParams4.addRule(6, this.g.getId());
        relativeLayout$LayoutParams4.addRule(8, this.g.getId());
        relativeLayout$LayoutParams4.addRule(5, this.g.getId());
        relativeLayout$LayoutParams4.addRule(7, this.g.getId());
        this.addView((View)this.a, (ViewGroup$LayoutParams)relativeLayout$LayoutParams4);
        this.a();
    }
    
    public void a() {
        this.c(CBUtility.a(CBUtility.a()));
    }
    
    public void a(final int n) {
        if (this.b != null) {
            this.b.setBackgroundColor(n);
        }
        this.c.setBackgroundColor(n);
    }
    
    public void a(final String s) {
        this.g.a().a((MediaPlayer$OnCompletionListener)this);
        this.g.a().a((MediaPlayer$OnErrorListener)this);
        this.g.a().a((MediaPlayer$OnPreparedListener)this);
        this.g.a().a(Uri.parse(s));
    }
    
    void a(final boolean b) {
        this.a(!this.l, b);
    }
    
    protected void a(final boolean l, final boolean b) {
        this.i.removeCallbacks(this.n);
        this.i.removeCallbacks(this.o);
        if (!this.h.y || !this.h.p() || l == this.l) {
            return;
        }
        this.l = l;
        AlphaAnimation alphaAnimation;
        if (this.l) {
            alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        }
        else {
            alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        }
        long duration;
        if (b) {
            duration = 100L;
        }
        else {
            duration = 200L;
        }
        ((Animation)alphaAnimation).setDuration(duration);
        ((Animation)alphaAnimation).setFillAfter(true);
        if (!this.m && this.b != null) {
            this.b.setVisibility(0);
            this.b.startAnimation((Animation)alphaAnimation);
            if (this.d != null) {
                this.d.setEnabled(true);
            }
        }
        if (this.h.M) {
            this.f.setVisibility(0);
        }
        this.c.setVisibility(0);
        this.c.startAnimation((Animation)alphaAnimation);
        if (this.l) {
            this.i.postDelayed(this.n, 3000L);
            return;
        }
        this.i.postDelayed(this.o, ((Animation)alphaAnimation).getDuration());
    }
    
    public av.a b() {
        return this.g.a();
    }
    
    public void b(final boolean l) {
        this.i.removeCallbacks(this.n);
        this.i.removeCallbacks(this.o);
        if (l) {
            if (!this.m && this.b != null) {
                this.b.setVisibility(0);
            }
            if (this.h.M) {
                this.f.setVisibility(0);
            }
            this.c.setVisibility(0);
            if (this.d != null) {
                this.d.setEnabled(true);
            }
        }
        else {
            if (this.b != null) {
                this.b.clearAnimation();
                this.b.setVisibility(8);
            }
            this.c.clearAnimation();
            if (this.h.M) {
                this.f.setVisibility(8);
            }
            this.c.setVisibility(8);
            if (this.d != null) {
                this.d.setEnabled(false);
            }
        }
        this.l = l;
    }
    
    public x c() {
        return this.f;
    }
    
    public void c(final boolean b) {
        int backgroundColor;
        if (b) {
            backgroundColor = -16777216;
        }
        else {
            backgroundColor = 0;
        }
        this.setBackgroundColor(backgroundColor);
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, -1);
        if (!b) {
            layoutParams.addRule(6, this.g.getId());
            layoutParams.addRule(8, this.g.getId());
            layoutParams.addRule(5, this.g.getId());
            layoutParams.addRule(7, this.g.getId());
        }
        this.a.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        if (this.b != null) {
            this.b.setGravity(8388627);
            this.b.requestLayout();
        }
    }
    
    public void d() {
        if (this.b != null) {
            this.b.setVisibility(8);
        }
        this.m = true;
        if (this.d != null) {
            this.d.setEnabled(false);
        }
    }
    
    public void d(final boolean b) {
        final TextView e = this.e;
        int visibility;
        if (b) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        e.setVisibility(visibility);
    }
    
    public void e() {
        this.i.postDelayed((Runnable)new Runnable() {
            @Override
            public void run() {
                ab.this.g.setVisibility(0);
            }
        }, 500L);
        this.g.a().a();
        this.i.removeCallbacks(this.j);
        this.i.postDelayed(this.j, 16L);
    }
    
    public void f() {
        if (this.g.a().e()) {
            this.h.v = this.g.a().d();
            this.g.a().b();
        }
        if (this.h.q().e.getVisibility() == 0) {
            this.h.q().e.postInvalidate();
        }
        this.i.removeCallbacks(this.j);
    }
    
    public void g() {
        if (this.g.a().e()) {
            this.h.v = this.g.a().d();
        }
        this.g.a().b();
        this.i.removeCallbacks(this.j);
    }
    
    public void h() {
        this.g.setVisibility(8);
        this.invalidate();
    }
    
    public void onCompletion(final MediaPlayer mediaPlayer) {
        this.h.v = this.g.a().c();
        if (this.h.q() != null) {
            this.h.q().e();
        }
    }
    
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.i.removeCallbacks(this.j);
    }
    
    public boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
        this.h.v();
        return false;
    }
    
    public void onPrepared(final MediaPlayer mediaPlayer) {
        this.h.w = this.g.a().c();
        ((com.chartboost.sdk.e.a)this.h.q()).a(true);
    }
    
    @SuppressLint({ "ClickableViewAccessibility" })
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (this.g.a().e() && motionEvent.getActionMasked() == 0) {
            if (this.h != null) {
                this.a(true);
            }
            return true;
        }
        return false;
    }
    
    public void setEnabled(final boolean b) {
        super.setEnabled(b);
        if (this.d != null) {
            this.d.setEnabled(b);
        }
        if (b) {
            this.b(false);
        }
    }
}
