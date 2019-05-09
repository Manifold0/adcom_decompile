// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.TextureView;
import android.support.annotation.NonNull;
import com.facebook.ads.internal.w.b.x;
import java.util.Iterator;
import com.facebook.ads.internal.view.i.c.g;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.b.q;
import com.facebook.ads.internal.view.i.b.o;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.facebook.ads.internal.settings.AdInternalSettings;
import android.annotation.TargetApi;
import android.util.AttributeSet;
import com.facebook.ads.internal.view.i.b.u;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import android.content.Context;
import android.view.View$OnTouchListener;
import com.facebook.ads.internal.o.f;
import android.os.Handler;
import com.facebook.ads.internal.view.i.a.b;
import java.util.List;
import com.facebook.ads.internal.view.i.b.y;
import com.facebook.ads.internal.view.i.b.z;
import com.facebook.ads.internal.view.i.b.w;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.t;
import com.facebook.ads.internal.view.i.b.s;
import com.facebook.ads.internal.view.i.b.m;
import com.facebook.ads.internal.view.i.d.e;
import android.widget.RelativeLayout;

public class a extends RelativeLayout implements c.a, e
{
    private static final m b;
    private static final com.facebook.ads.internal.view.i.b.e c;
    private static final s d;
    private static final t e;
    private static final k f;
    private static final w g;
    private static final z h;
    private static final y i;
    protected final com.facebook.ads.internal.view.i.d.c a;
    private d j;
    private final List<com.facebook.ads.internal.view.i.a.b> k;
    private final Handler l;
    private final Handler m;
    private final com.facebook.ads.internal.o.e<f, com.facebook.ads.internal.o.d> n;
    private boolean o;
    private boolean p;
    private boolean q;
    private int r;
    private final View$OnTouchListener s;
    
    static {
        b = new m();
        c = new com.facebook.ads.internal.view.i.b.e();
        d = new s();
        e = new t();
        f = new k();
        g = new w();
        h = new z();
        i = new y();
    }
    
    public a(final Context context) {
        super(context);
        this.k = new ArrayList<com.facebook.ads.internal.view.i.a.b>();
        this.l = new Handler();
        this.m = new Handler();
        this.n = new com.facebook.ads.internal.o.e<f, com.facebook.ads.internal.o.d>();
        this.q = false;
        this.r = 200;
        this.s = (View$OnTouchListener)new View$OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                com.facebook.ads.internal.view.i.a.this.n.a(new u(view, motionEvent));
                return false;
            }
        };
        if (com.facebook.ads.internal.r.a.a(context)) {
            this.a = new com.facebook.ads.internal.view.i.d.a(context);
        }
        else {
            this.a = new com.facebook.ads.internal.view.i.d.b(context);
        }
        this.a();
    }
    
    public a(final Context context, final AttributeSet set) {
        super(context, set);
        this.k = new ArrayList<com.facebook.ads.internal.view.i.a.b>();
        this.l = new Handler();
        this.m = new Handler();
        this.n = new com.facebook.ads.internal.o.e<f, com.facebook.ads.internal.o.d>();
        this.q = false;
        this.r = 200;
        this.s = (View$OnTouchListener)new View$OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                com.facebook.ads.internal.view.i.a.this.n.a(new u(view, motionEvent));
                return false;
            }
        };
        if (com.facebook.ads.internal.r.a.a(context)) {
            this.a = new com.facebook.ads.internal.view.i.d.a(context, set);
        }
        else {
            this.a = new com.facebook.ads.internal.view.i.d.b(context, set);
        }
        this.a();
    }
    
    public a(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.k = new ArrayList<com.facebook.ads.internal.view.i.a.b>();
        this.l = new Handler();
        this.m = new Handler();
        this.n = new com.facebook.ads.internal.o.e<f, com.facebook.ads.internal.o.d>();
        this.q = false;
        this.r = 200;
        this.s = (View$OnTouchListener)new View$OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                com.facebook.ads.internal.view.i.a.this.n.a(new u(view, motionEvent));
                return false;
            }
        };
        if (com.facebook.ads.internal.r.a.a(context)) {
            this.a = new com.facebook.ads.internal.view.i.d.a(context, set, n);
        }
        else {
            this.a = new com.facebook.ads.internal.view.i.d.b(context, set, n);
        }
        this.a();
    }
    
    @TargetApi(21)
    public a(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
        this.k = new ArrayList<com.facebook.ads.internal.view.i.a.b>();
        this.l = new Handler();
        this.m = new Handler();
        this.n = new com.facebook.ads.internal.o.e<f, com.facebook.ads.internal.o.d>();
        this.q = false;
        this.r = 200;
        this.s = (View$OnTouchListener)new View$OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                com.facebook.ads.internal.view.i.a.this.n.a(new u(view, motionEvent));
                return false;
            }
        };
        if (com.facebook.ads.internal.r.a.a(context)) {
            this.a = new com.facebook.ads.internal.view.i.d.a(context, set, n, n2);
        }
        else {
            this.a = new com.facebook.ads.internal.view.i.d.b(context, set, n, n2);
        }
        this.a();
    }
    
    private void a() {
        if (this.h() && this.a instanceof com.facebook.ads.internal.view.i.d.a) {
            ((com.facebook.ads.internal.view.i.d.a)this.a).setTestMode(AdInternalSettings.isTestMode(this.getContext()));
        }
        this.a.setRequestedVolume(1.0f);
        this.a.setVideoStateChangeListener(this);
        this.j = new d(this.getContext(), this.a);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-1, -1);
        relativeLayout$LayoutParams.addRule(13);
        this.addView((View)this.j, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        this.setOnTouchListener(this.s);
    }
    
    private void b() {
        this.l.postDelayed((Runnable)new Runnable() {
            @Override
            public void run() {
                if (!com.facebook.ads.internal.view.i.a.this.o) {
                    com.facebook.ads.internal.view.i.a.this.n.a(new o(com.facebook.ads.internal.view.i.a.this.getCurrentPositionInMillis()));
                    com.facebook.ads.internal.view.i.a.this.l.postDelayed((Runnable)this, (long)com.facebook.ads.internal.view.i.a.this.r);
                }
            }
        }, (long)this.r);
    }
    
    public void a(final int n) {
        this.l.removeCallbacksAndMessages((Object)null);
        this.a.a(n);
    }
    
    public void a(final int n, final int n2) {
        this.m.post((Runnable)new Runnable() {
            @Override
            public void run() {
                com.facebook.ads.internal.view.i.a.this.n.a(new q(n, n2));
            }
        });
        this.b();
    }
    
    public void a(final com.facebook.ads.internal.view.i.a.a a) {
        if (this.o && this.a.getState() == com.facebook.ads.internal.view.i.d.d.g) {
            this.o = false;
        }
        this.a.a(a);
    }
    
    public void a(final com.facebook.ads.internal.view.i.a.b b) {
        this.k.add(b);
    }
    
    public void a(final com.facebook.ads.internal.view.i.d.d d) {
        this.m.post((Runnable)new Runnable() {
            final /* synthetic */ int b = com.facebook.ads.internal.view.i.a.this.getCurrentPositionInMillis();
            final /* synthetic */ int c = com.facebook.ads.internal.view.i.a.this.getDuration();
            
            @Override
            public void run() {
                if (d == com.facebook.ads.internal.view.i.d.d.c) {
                    com.facebook.ads.internal.view.i.a.this.n.a(com.facebook.ads.internal.view.i.a.b);
                }
                else {
                    if (d == com.facebook.ads.internal.view.i.d.d.h) {
                        com.facebook.ads.internal.view.i.a.this.o = true;
                        com.facebook.ads.internal.view.i.a.this.n.a(com.facebook.ads.internal.view.i.a.c);
                        return;
                    }
                    if (d == com.facebook.ads.internal.view.i.d.d.g) {
                        com.facebook.ads.internal.view.i.a.this.o = true;
                        com.facebook.ads.internal.view.i.a.this.l.removeCallbacksAndMessages((Object)null);
                        com.facebook.ads.internal.view.i.a.this.n.a(new com.facebook.ads.internal.view.i.b.c(this.b, this.c));
                        return;
                    }
                    if (d == com.facebook.ads.internal.view.i.d.d.d) {
                        com.facebook.ads.internal.view.i.a.this.n.a(com.facebook.ads.internal.view.i.a.f);
                        com.facebook.ads.internal.view.i.a.this.l.removeCallbacksAndMessages((Object)null);
                        com.facebook.ads.internal.view.i.a.this.b();
                        return;
                    }
                    if (d == com.facebook.ads.internal.view.i.d.d.e) {
                        com.facebook.ads.internal.view.i.a.this.n.a(new i(this.b));
                        com.facebook.ads.internal.view.i.a.this.l.removeCallbacksAndMessages((Object)null);
                        return;
                    }
                    if (d == com.facebook.ads.internal.view.i.d.d.a) {
                        com.facebook.ads.internal.view.i.a.this.n.a(com.facebook.ads.internal.view.i.a.e);
                        com.facebook.ads.internal.view.i.a.this.l.removeCallbacksAndMessages((Object)null);
                    }
                }
            }
        });
    }
    
    public void a(final boolean q) {
        if (this.m()) {
            return;
        }
        this.a.a(q);
        this.q = q;
    }
    
    public void c() {
        for (final com.facebook.ads.internal.view.i.a.b b : this.k) {
            if (b instanceof com.facebook.ads.internal.view.i.a.c) {
                final com.facebook.ads.internal.view.i.a.c c = (com.facebook.ads.internal.view.i.a.c)b;
                if (c.getParent() == null) {
                    if (c instanceof g) {
                        this.j.a(c);
                    }
                    else {
                        this.addView((View)c);
                    }
                }
            }
            b.a(this);
        }
    }
    
    public void d() {
        for (final com.facebook.ads.internal.view.i.a.b b : this.k) {
            if (b instanceof com.facebook.ads.internal.view.i.a.c) {
                final com.facebook.ads.internal.view.i.a.c c = (com.facebook.ads.internal.view.i.a.c)b;
                if (c instanceof g) {
                    this.j.b(c);
                }
                else {
                    x.b((View)c);
                }
            }
            b.b(this);
        }
    }
    
    public void e() {
        if (this.m()) {
            return;
        }
        this.a.a();
    }
    
    public void f() {
        this.m.post((Runnable)new Runnable() {
            @Override
            public void run() {
                com.facebook.ads.internal.view.i.a.this.getEventBus().a(com.facebook.ads.internal.view.i.a.d);
            }
        });
        this.a.b();
    }
    
    public void g() {
        this.a.c();
    }
    
    public int getCurrentPositionInMillis() {
        return this.a.getCurrentPosition();
    }
    
    public int getDuration() {
        return this.a.getDuration();
    }
    
    @NonNull
    public com.facebook.ads.internal.o.e<f, com.facebook.ads.internal.o.d> getEventBus() {
        return this.n;
    }
    
    public long getInitialBufferTime() {
        return this.a.getInitialBufferTime();
    }
    
    public com.facebook.ads.internal.view.i.d.d getState() {
        return this.a.getState();
    }
    
    protected Handler getStateHandler() {
        return this.m;
    }
    
    public TextureView getTextureView() {
        return (TextureView)this.a;
    }
    
    public int getVideoHeight() {
        return this.a.getVideoHeight();
    }
    
    public int getVideoProgressReportIntervalMs() {
        return this.r;
    }
    
    public com.facebook.ads.internal.view.i.a.a getVideoStartReason() {
        return this.a.getStartReason();
    }
    
    public View getVideoView() {
        return (View)this.j;
    }
    
    public int getVideoWidth() {
        return this.a.getVideoWidth();
    }
    
    public View getView() {
        return (View)this;
    }
    
    public float getVolume() {
        return this.a.getVolume();
    }
    
    public boolean h() {
        return com.facebook.ads.internal.r.a.a(this.getContext());
    }
    
    public boolean i() {
        return this.p;
    }
    
    public boolean j() {
        return this.getState() == com.facebook.ads.internal.view.i.d.d.d;
    }
    
    public boolean k() {
        return this.a.d();
    }
    
    public void l() {
        this.a.setVideoStateChangeListener(null);
        this.a.e();
    }
    
    public boolean m() {
        return this.getState() == com.facebook.ads.internal.view.i.d.d.e;
    }
    
    public boolean n() {
        return this.m() && this.q;
    }
    
    protected void onAttachedToWindow() {
        this.n.a(com.facebook.ads.internal.view.i.a.i);
        super.onAttachedToWindow();
    }
    
    protected void onDetachedFromWindow() {
        this.n.a(com.facebook.ads.internal.view.i.a.h);
        super.onDetachedFromWindow();
    }
    
    public void setControlsAnchorView(final View controlsAnchorView) {
        if (this.a != null) {
            this.a.setControlsAnchorView(controlsAnchorView);
        }
    }
    
    public void setIsFullScreen(final boolean b) {
        this.p = b;
        this.a.setFullScreen(b);
    }
    
    public void setLayoutParams(final ViewGroup$LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
    }
    
    public void setVideoMPD(@Nullable final String videoMPD) {
        this.a.setVideoMPD(videoMPD);
    }
    
    public void setVideoProgressReportIntervalMs(final int r) {
        this.r = r;
    }
    
    public void setVideoURI(@Nullable final Uri uri) {
        if (uri == null) {
            this.d();
        }
        else {
            this.c();
            this.a.setup(uri);
        }
        this.o = false;
    }
    
    public void setVideoURI(@Nullable final String s) {
        Uri parse;
        if (s != null) {
            parse = Uri.parse(s);
        }
        else {
            parse = null;
        }
        this.setVideoURI(parse);
    }
    
    public void setVolume(final float requestedVolume) {
        this.a.setRequestedVolume(requestedVolume);
        this.getEventBus().a(com.facebook.ads.internal.view.i.a.g);
    }
}
