// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.t;

import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import com.facebook.ads.internal.view.i.a.b;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.facebook.ads.internal.view.i.c.h;
import com.facebook.ads.internal.w.b.x;
import android.util.Log;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.concurrent.atomic.AtomicBoolean;
import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.view.p;
import com.facebook.ads.internal.view.i.d;
import android.view.View;
import com.facebook.ads.internal.x.a;
import com.facebook.ads.internal.view.i.c.g;

public class c
{
    private static final String a;
    private final g b;
    private final com.facebook.ads.internal.x.a c;
    private final com.facebook.ads.internal.x.a.a d;
    private final View e;
    private final d.a f;
    @Nullable
    private p g;
    @Nullable
    private a h;
    private Context i;
    private boolean j;
    private boolean k;
    private boolean l;
    private final AtomicBoolean m;
    private final AtomicBoolean n;
    private l o;
    
    static {
        a = c.class.getSimpleName();
    }
    
    public c(final Context i, final View e) {
        this.f = new d.a() {
            @Override
            public void a() {
                c.this.n.set(true);
                if (c.this.h != null) {
                    c.this.h.a(c.this.m.get());
                }
            }
        };
        this.m = new AtomicBoolean(false);
        this.n = new AtomicBoolean(false);
        this.o = com.facebook.ads.internal.t.l.a;
        this.i = i;
        this.e = e;
        this.b = new g(i);
        this.d = new com.facebook.ads.internal.x.a.a() {
            @Override
            public void a() {
                if (c.this.g == null) {
                    return;
                }
                if (!c.this.l && (c.this.k || (c.this.g != null && c.this.g.getState() != com.facebook.ads.internal.view.i.d.d.g && c.this.o == l.b))) {
                    c.a(c.this, com.facebook.ads.internal.view.i.a.a.c);
                }
                c.this.k = false;
                c.this.l = false;
            }
            
            @Override
            public void b() {
                if (c.this.g == null) {
                    return;
                }
                c.this.g.e();
            }
        };
        this.c = new com.facebook.ads.internal.x.a(this.e, 50, true, this.d);
        this.g();
    }
    
    static /* synthetic */ void a(final c c, final com.facebook.ads.internal.view.i.a.a a) {
        if (c.g != null) {
            c.g.a(a);
        }
        else if (AdInternalSettings.isDebugBuild()) {
            Log.e(c.a, "MediaViewVideo is null; unable to find it.");
        }
    }
    
    private void g() {
        final float b = x.b;
        final int n = (int)(2.0f * b);
        final int n2 = (int)(b * 25.0f);
        final h h = new h(this.i);
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-2, -2);
        layoutParams.addRule(9);
        layoutParams.addRule(12);
        h.setPadding(n, n2, n2, n);
        h.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        for (int i = 0; i < ((ViewGroup)this.e).getChildCount(); ++i) {
            final View child = ((ViewGroup)this.e).getChildAt(0);
            if (child instanceof p) {
                this.g = (p)child;
                break;
            }
        }
        if (this.g == null) {
            if (AdInternalSettings.isDebugBuild()) {
                Log.e(com.facebook.ads.internal.t.c.a, "Unable to find MediaViewVideo child.");
            }
        }
        else {
            this.g.a(this.b);
            this.g.a(h);
        }
        this.c.a(0);
        this.c.b(250);
    }
    
    private void h() {
        if (this.e.getVisibility() == 0 && this.j && this.e.hasWindowFocus()) {
            this.c.a();
            return;
        }
        if (this.g != null && this.g.getState() == com.facebook.ads.internal.view.i.d.d.e) {
            this.l = true;
        }
        this.c.c();
    }
    
    public void a() {
        this.o = com.facebook.ads.internal.t.l.a;
        if (this.g != null) {
            ((d)this.g.getVideoView()).setViewImplInflationListener(null);
        }
    }
    
    public void a(final e e) {
        this.a(e, null);
    }
    
    public void a(final e e, @Nullable final a h) {
        this.k = false;
        this.l = false;
        this.h = h;
        if (this.g != null) {
            ((d)this.g.getVideoView()).setViewImplInflationListener(this.f);
        }
        final g b = this.b;
        String a;
        if (e != null && e.j() != null) {
            a = e.j().a();
        }
        else {
            a = null;
        }
        b.a(a, new com.facebook.ads.internal.view.c.e() {
            @Override
            public void a(final boolean b) {
                com.facebook.ads.internal.t.c.this.m.set(b);
                if (com.facebook.ads.internal.t.c.this.n.get() && com.facebook.ads.internal.t.c.this.h != null) {
                    com.facebook.ads.internal.t.c.this.h.a(b);
                }
            }
        });
        this.o = e.t();
        this.c.a();
    }
    
    public void b() {
        if (this.g != null) {
            this.g.getVideoView().setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
                public boolean onTouch(final View view, final MotionEvent motionEvent) {
                    if (com.facebook.ads.internal.t.c.this.g != null && motionEvent.getAction() == 1) {
                        com.facebook.ads.internal.t.c.this.g.a();
                    }
                    return true;
                }
            });
        }
    }
    
    public void c() {
        this.j = true;
        this.h();
    }
    
    public void d() {
        this.j = false;
        this.h();
    }
    
    public void e() {
        this.h();
    }
    
    public void f() {
        this.h();
    }
    
    public interface a
    {
        void a(final boolean p0);
    }
}
