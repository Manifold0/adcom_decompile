// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.s;

import android.text.TextUtils;
import java.util.Map;
import com.facebook.ads.internal.w.b.o;
import android.util.Log;
import android.content.Context;
import android.support.annotation.Nullable;
import android.annotation.SuppressLint;
import android.support.annotation.UiThread;

@UiThread
public class d implements c
{
    private static final String a;
    private static double b;
    private static String c;
    private static volatile boolean d;
    @SuppressLint({ "StaticFieldLeak" })
    @Nullable
    private static c i;
    private final b e;
    private final com.facebook.ads.internal.j.d f;
    private final Context g;
    @Nullable
    private e h;
    
    static {
        a = d.class.getSimpleName();
        com.facebook.ads.internal.s.d.d = false;
    }
    
    private d(final Context context) {
        this.g = context.getApplicationContext();
        this.f = new com.facebook.ads.internal.j.d(context);
        (this.e = new b(context, (b.a)new i(context, this.f))).b();
        b(context);
    }
    
    public static c a(final Context context) {
        synchronized (d.class) {
            if (com.facebook.ads.internal.s.d.i == null) {
                com.facebook.ads.internal.s.d.i = new d(context.getApplicationContext());
            }
            return com.facebook.ads.internal.s.d.i;
        }
    }
    
    private void a(final a a) {
        if (!a.g()) {
            Log.e(com.facebook.ads.internal.s.d.a, "Attempting to log an invalid " + a.i() + " event.");
            return;
        }
        if (this.h != null) {
            this.h.a(a);
        }
        this.f.a(a.a(), a.h().c, a.i().toString(), a.b(), a.c(), a.d(), a.e(), new com.facebook.ads.internal.j.a<String>() {
            @Override
            public void a(final int n, final String s) {
                super.a(n, s);
            }
            
            @Override
            public void a(final String s) {
                super.a(s);
                if (com.facebook.ads.internal.r.a.z(com.facebook.ads.internal.s.d.this.g)) {
                    com.facebook.ads.internal.p.a.a(com.facebook.ads.internal.s.d.this.g, a.i().toString(), s);
                }
                if (a.f()) {
                    com.facebook.ads.internal.s.d.this.e.a();
                    return;
                }
                com.facebook.ads.internal.s.d.this.e.b();
            }
        });
    }
    
    private static void b(final Context context) {
        synchronized (d.class) {
            if (!com.facebook.ads.internal.s.d.d) {
                com.facebook.ads.internal.n.a.b(context);
                o.a();
                com.facebook.ads.internal.s.d.b = o.b();
                com.facebook.ads.internal.s.d.c = o.c();
                com.facebook.ads.internal.s.d.d = true;
            }
        }
    }
    
    @Override
    public void a(final String s) {
        new com.facebook.ads.internal.w.e.e(this.g).execute((Object[])new String[] { s });
    }
    
    @Override
    public void a(final String s, final Map<String, String> map) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        this.a(new a.a().a(s).a(com.facebook.ads.internal.s.d.b).b(com.facebook.ads.internal.s.d.c).a(map).a(com.facebook.ads.internal.s.f.a).a(com.facebook.ads.internal.s.g.e).a(true).a());
    }
    
    @Override
    public void a(final String s, final Map<String, String> map, final String s2, final f f) {
        this.a(new a.a().a(s).a(com.facebook.ads.internal.s.d.b).b(com.facebook.ads.internal.s.d.c).a(map).a(f).a(com.facebook.ads.internal.s.g.a(s2)).a(true).a());
    }
    
    @Override
    public void b(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        this.a(new a.a().a(s).a(com.facebook.ads.internal.s.d.b).b(com.facebook.ads.internal.s.d.c).a(com.facebook.ads.internal.s.f.b).a(com.facebook.ads.internal.s.g.d).a(true).a());
    }
    
    @Override
    public void b(final String s, final Map<String, String> map) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        this.a(new a.a().a(s).a(com.facebook.ads.internal.s.d.b).b(com.facebook.ads.internal.s.d.c).a(map).a(com.facebook.ads.internal.s.f.a).a(com.facebook.ads.internal.s.g.f).a(false).a());
    }
    
    @Override
    public void c(final String s, final Map<String, String> map) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        this.a(new a.a().a(s).a(com.facebook.ads.internal.s.d.b).b(com.facebook.ads.internal.s.d.c).a(map).a(com.facebook.ads.internal.s.f.a).a(com.facebook.ads.internal.s.g.i).a(true).a());
    }
    
    @Override
    public void d(final String s, final Map<String, String> map) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        this.a(new a.a().a(s).a(com.facebook.ads.internal.s.d.b).b(com.facebook.ads.internal.s.d.c).a(map).a(com.facebook.ads.internal.s.f.b).a(com.facebook.ads.internal.s.g.h).a(true).a());
    }
    
    @Override
    public void e(final String s, final Map<String, String> map) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        this.a(new a.a().a(s).a(com.facebook.ads.internal.s.d.b).b(com.facebook.ads.internal.s.d.c).a(map).a(com.facebook.ads.internal.s.f.a).a(com.facebook.ads.internal.s.g.k).a(true).a());
    }
    
    @Override
    public void f(final String s, final Map<String, String> map) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        this.a(new a.a().a(s).a(com.facebook.ads.internal.s.d.b).b(com.facebook.ads.internal.s.d.c).a(map).a(com.facebook.ads.internal.s.f.b).a(com.facebook.ads.internal.s.g.j).a(false).a());
    }
    
    @Override
    public void g(final String s, final Map<String, String> map) {
        this.a(new a.a().a(s).a(com.facebook.ads.internal.s.d.b).b(com.facebook.ads.internal.s.d.c).a(map).a(com.facebook.ads.internal.s.f.b).a(com.facebook.ads.internal.s.g.b).a(false).a());
    }
    
    @Override
    public void h(final String s, final Map<String, String> map) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        this.a(new a.a().a(s).a(com.facebook.ads.internal.s.d.b).b(com.facebook.ads.internal.s.d.c).a(map).a(com.facebook.ads.internal.s.f.a).a(com.facebook.ads.internal.s.g.g).a(true).a());
    }
    
    @Override
    public void i(final String s, final Map<String, String> map) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        this.a(new a.a().a(s).a(com.facebook.ads.internal.s.d.b).b(com.facebook.ads.internal.s.d.c).a(map).a(com.facebook.ads.internal.s.f.b).a(com.facebook.ads.internal.s.g.p).a(true).a());
    }
    
    @Override
    public void j(final String s, final Map<String, String> map) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        this.a(new a.a().a(s).a(com.facebook.ads.internal.s.d.b).b(com.facebook.ads.internal.s.d.c).a(map).a(com.facebook.ads.internal.s.f.b).a(com.facebook.ads.internal.s.g.q).a(true).a());
    }
    
    @Override
    public void k(final String s, final Map<String, String> map) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        this.a(new a.a().a(s).a(com.facebook.ads.internal.s.d.b).b(com.facebook.ads.internal.s.d.c).a(map).a(com.facebook.ads.internal.s.f.b).a(com.facebook.ads.internal.s.g.r).a(true).a());
    }
    
    @Override
    public void l(final String s, final Map<String, String> map) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        this.a(new a.a().a(s).a(com.facebook.ads.internal.s.d.b).b(com.facebook.ads.internal.s.d.c).a(map).a(com.facebook.ads.internal.s.f.b).a(com.facebook.ads.internal.s.g.c).a(true).a());
    }
    
    @Override
    public void m(final String s, final Map<String, String> map) {
        this.a(new a.a().a(s).a(com.facebook.ads.internal.s.d.b).b(com.facebook.ads.internal.s.d.c).a(map).a(com.facebook.ads.internal.s.f.a).a(com.facebook.ads.internal.s.g.l).a(true).a());
    }
    
    @Override
    public void n(final String s, final Map<String, String> map) {
        this.a(new a.a().a(s).a(com.facebook.ads.internal.s.d.b).b(com.facebook.ads.internal.s.d.c).a(map).a(com.facebook.ads.internal.s.f.b).a(com.facebook.ads.internal.s.g.m).a(false).a());
    }
    
    @Override
    public void o(final String s, final Map<String, String> map) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        this.a(new a.a().a(s).a(com.facebook.ads.internal.s.d.b).b(com.facebook.ads.internal.s.d.c).a(map).a(com.facebook.ads.internal.s.f.b).a(com.facebook.ads.internal.s.g.n).a(true).a());
    }
    
    @Override
    public void p(final String s, final Map<String, String> map) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        this.a(new a.a().a(s).a(com.facebook.ads.internal.s.d.b).b(com.facebook.ads.internal.s.d.c).a(map).a(com.facebook.ads.internal.s.f.b).a(com.facebook.ads.internal.s.g.o).a(true).a());
    }
    
    @Override
    public void q(final String s, final Map<String, String> map) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        this.a(new a.a().a(s).a(com.facebook.ads.internal.s.d.b).b(com.facebook.ads.internal.s.d.c).a(map).a(com.facebook.ads.internal.s.f.a).a(com.facebook.ads.internal.s.g.s).a(true).a());
    }
    
    @Override
    public void r(final String s, final Map<String, String> map) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        this.a(new a.a().a(s).a(com.facebook.ads.internal.s.d.b).b(com.facebook.ads.internal.s.d.c).a(map).a(com.facebook.ads.internal.s.f.a).a(com.facebook.ads.internal.s.g.u).a(true).a());
    }
}
