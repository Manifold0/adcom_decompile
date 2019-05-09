// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.a;

import android.net.Uri;
import com.facebook.ads.internal.w.e.g;
import android.text.TextUtils;
import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.view.a;
import android.widget.FrameLayout;

public abstract class c extends FrameLayout
{
    boolean a;
    protected final e b;
    private final com.facebook.ads.internal.s.c c;
    private final String d;
    @Nullable
    private final a e;
    @Nullable
    private final a.a f;
    @Nullable
    private b g;
    private int h;
    private com.facebook.ads.internal.f.b i;
    private com.facebook.ads.internal.f.b.a j;
    private com.facebook.ads.internal.f.c k;
    
    public c(final Context context, final com.facebook.ads.internal.s.c c, final String s) {
        this(context, c, s, null, null);
    }
    
    public c(final Context context, final com.facebook.ads.internal.s.c c, final String d, @Nullable final a e, @Nullable final a.a f) {
        super(context);
        this.h = 0;
        this.j = com.facebook.ads.internal.f.b.a.c;
        this.k = null;
        this.b = new e() {
            @Override
            public void a() {
                if (com.facebook.ads.internal.view.a.c.this.k == null) {
                    this.a(false);
                    return;
                }
                com.facebook.ads.internal.view.a.c.this.h--;
                if (com.facebook.ads.internal.view.a.c.this.k.e() == null) {
                    com.facebook.ads.internal.view.a.c.this.g();
                    return;
                }
                com.facebook.ads.internal.view.a.c.a(com.facebook.ads.internal.view.a.c.this, com.facebook.ads.internal.view.a.c.this.k.e());
            }
            
            @Override
            public void a(final com.facebook.ads.internal.f.b.a a) {
                com.facebook.ads.internal.view.a.c.this.h++;
                com.facebook.ads.internal.view.a.c.this.j = a;
                com.facebook.ads.internal.f.c c;
                if (com.facebook.ads.internal.view.a.c.this.j == com.facebook.ads.internal.f.b.a.b) {
                    c = com.facebook.ads.internal.f.a.d(com.facebook.ads.internal.view.a.c.this.getContext());
                }
                else {
                    c = com.facebook.ads.internal.f.a.g(com.facebook.ads.internal.view.a.c.this.getContext());
                }
                com.facebook.ads.internal.view.a.c.a(com.facebook.ads.internal.view.a.c.this, c);
            }
            
            @Override
            public void a(final com.facebook.ads.internal.f.c c) {
                c.this.h++;
                c.this.i.a(c.a());
                if (c.d().isEmpty()) {
                    c.b(c.this, c);
                    if (c.this.g != null) {
                        c.this.g.a(c, c.this.j);
                    }
                    return;
                }
                c.a(c.this, c);
            }
            
            @Override
            public void a(final boolean b) {
                com.facebook.ads.internal.view.a.c.this.c();
                if (com.facebook.ads.internal.view.a.c.this.e != null) {
                    com.facebook.ads.internal.view.a.c.this.e.b(true);
                }
                if (com.facebook.ads.internal.view.a.c.this.g != null) {
                    com.facebook.ads.internal.view.a.c.this.g.a(b);
                }
                if (!b) {
                    com.facebook.ads.internal.view.a.c.this.f();
                }
            }
            
            @Override
            public void b() {
                if (com.facebook.ads.internal.view.a.c.this.f != null) {
                    com.facebook.ads.internal.view.a.c.this.f.a("com.facebook.ads.adreporting.FINISH_AD_REPORTING_FLOW");
                }
            }
            
            @Override
            public void c() {
                if (!TextUtils.isEmpty((CharSequence)com.facebook.ads.internal.f.a.n(com.facebook.ads.internal.view.a.c.this.getContext()))) {
                    com.facebook.ads.internal.w.e.g.a(new g(), com.facebook.ads.internal.view.a.c.this.getContext(), Uri.parse(com.facebook.ads.internal.f.a.n(com.facebook.ads.internal.view.a.c.this.getContext())), com.facebook.ads.internal.view.a.c.this.d);
                }
                com.facebook.ads.internal.view.a.c.this.i.c();
            }
            
            @Override
            public void d() {
                com.facebook.ads.internal.view.a.c.this.c();
                if (com.facebook.ads.internal.view.a.c.this.e != null) {
                    com.facebook.ads.internal.view.a.c.this.e.b(true);
                }
                if (!TextUtils.isEmpty((CharSequence)com.facebook.ads.internal.f.a.m(com.facebook.ads.internal.view.a.c.this.getContext()))) {
                    com.facebook.ads.internal.w.e.g.a(new g(), com.facebook.ads.internal.view.a.c.this.getContext(), Uri.parse(com.facebook.ads.internal.f.a.m(com.facebook.ads.internal.view.a.c.this.getContext())), com.facebook.ads.internal.view.a.c.this.d);
                }
                com.facebook.ads.internal.view.a.c.this.i.b();
                com.facebook.ads.internal.view.a.c.this.f();
            }
        };
        this.c = c;
        this.e = e;
        this.f = f;
        this.d = d;
    }
    
    static /* synthetic */ void a(final c c, final com.facebook.ads.internal.f.c k) {
        c.k = k;
        c.i.a(c.j, c.h);
        c.a(k, c.j);
    }
    
    static /* synthetic */ void b(final c c, final com.facebook.ads.internal.f.c c2) {
        c.i.a(c.j);
        c.b(c2, c.j);
        if (c.e()) {
            c.f();
        }
    }
    
    private void f() {
        if (this.i.e()) {
            this.c.n(this.d, this.i.d());
            this.i.f();
        }
    }
    
    private void g() {
        this.k = null;
        this.i.a();
        this.d();
    }
    
    public void a() {
        this.i = new com.facebook.ads.internal.f.b();
        if (this.e != null) {
            this.e.a_(true);
        }
        this.g();
        if (this.g != null) {
            this.g.a();
        }
    }
    
    abstract void a(final com.facebook.ads.internal.f.c p0, final com.facebook.ads.internal.f.b.a p1);
    
    public void a(final boolean a) {
        this.a = a;
    }
    
    public void b() {
        this.f();
    }
    
    abstract void b(final com.facebook.ads.internal.f.c p0, final com.facebook.ads.internal.f.b.a p1);
    
    abstract void c();
    
    abstract void d();
    
    abstract boolean e();
    
    public void setAdReportingFlowListener(@Nullable final b g) {
        this.g = g;
    }
}
