// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.g;

import com.facebook.ads.internal.w.e.e;
import com.facebook.ads.internal.adapters.b.o;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.lang.ref.WeakReference;
import com.facebook.ads.internal.a.b;
import android.net.Uri;
import android.util.Log;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.ImageView;
import com.facebook.ads.internal.view.c.d;
import com.facebook.ads.internal.view.component.f;
import com.facebook.ads.internal.view.component.j;
import android.view.View;
import android.util.Pair;
import com.facebook.ads.internal.adapters.b.n;
import android.text.TextUtils;
import java.util.Map;
import java.util.HashMap;
import com.facebook.ads.internal.view.i.b.aa;
import com.facebook.ads.internal.w.b.p;
import com.facebook.ads.internal.w.b.x;
import android.support.annotation.Nullable;
import java.util.concurrent.Executor;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.adapters.b.q;
import android.content.Context;
import com.facebook.ads.internal.view.component.a;

public class c
{
    private static final String a;
    private static final int b;
    private static final int c;
    private static final int d;
    private com.facebook.ads.internal.view.component.a e;
    private final Context f;
    private final com.facebook.ads.internal.s.c g;
    private final q h;
    private final String i;
    private final h j;
    private final com.facebook.ads.internal.x.a k;
    private final w l;
    private Executor m;
    @Nullable
    private com.facebook.ads.internal.view.a.a n;
    @Nullable
    private com.facebook.ads.internal.view.c.a o;
    @Nullable
    private com.facebook.ads.internal.view.c.a.b p;
    
    static {
        a = c.class.getSimpleName();
        b = (int)(4.0f * x.b);
        c = (int)(72.0f * x.b);
        d = (int)(8.0f * x.b);
    }
    
    public c(final Context f, final com.facebook.ads.internal.s.c g, final q h, final com.facebook.ads.internal.view.a.a n, final com.facebook.ads.internal.x.a k, final w l) {
        this.m = com.facebook.ads.internal.w.b.p.a;
        this.f = f;
        this.g = g;
        this.h = h;
        this.n = n;
        this.i = com.facebook.ads.internal.o.c.a(this.h.k().b());
        this.j = this.h.i().a();
        this.k = k;
        this.l = l;
    }
    
    static /* synthetic */ void a(final c c) {
        if (c.n != null) {
            c.n.a(aa.c.a());
        }
    }
    
    private com.facebook.ads.internal.view.component.a g() {
        if (this.e != null) {
            return this.e;
        }
        (this.e = new com.facebook.ads.internal.view.component.a(this.f, true, false, aa.e.a(), this.j, this.g, this.n, this.k, this.l)).a(this.h.h(), this.h.a(), new HashMap<String, String>());
        return this.e;
    }
    
    public boolean a() {
        return this.b() == com.facebook.ads.internal.view.g.c.a.b;
    }
    
    public a b() {
        final n j = this.h.j().j();
        if (j != null && j.i()) {
            return com.facebook.ads.internal.view.g.c.a.c;
        }
        if (!this.h.k().d().isEmpty()) {
            return com.facebook.ads.internal.view.g.c.a.a;
        }
        if (!TextUtils.isEmpty((CharSequence)this.i)) {
            return com.facebook.ads.internal.view.g.c.a.b;
        }
        return com.facebook.ads.internal.view.g.c.a.d;
    }
    
    public Pair<a, View> c() {
        final a b = this.b();
        switch (c$3.a[b.ordinal()]) {
            default: {
                final j j = new j(this.f, this.j, true, false, false);
                j.a(this.h.g().a(), this.h.g().c(), null, false, true);
                j.setAlignment(17);
                final com.facebook.ads.internal.view.component.a g = this.g();
                final f f = new f(this.f);
                x.a((View)f, 0);
                f.setRadius(50);
                new d(f).a().a(this.h.f().b());
                final LinearLayout linearLayout = new LinearLayout(this.f);
                linearLayout.setOrientation(1);
                linearLayout.setGravity(17);
                linearLayout.addView((View)f, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(com.facebook.ads.internal.view.g.c.c, com.facebook.ads.internal.view.g.c.c));
                final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-2, -2);
                linearLayout$LayoutParams.setMargins(0, com.facebook.ads.internal.view.g.c.d, 0, com.facebook.ads.internal.view.g.c.d);
                linearLayout.addView((View)j, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
                linearLayout.addView((View)g, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
                return (Pair<a, View>)new Pair((Object)b, (Object)linearLayout);
            }
            case 1: {
                this.p = new com.facebook.ads.internal.view.c.a.c() {
                    @Override
                    public void a() {
                        if (c.this.o != null && !TextUtils.isEmpty((CharSequence)c.this.h.k().c())) {
                            c.this.o.post((Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    if (c.this.o == null || c.this.o.c()) {
                                        Log.w(c.a, "Webview already destroyed, cannot activate");
                                        return;
                                    }
                                    c.this.o.loadUrl("javascript:" + c.this.h.k().c());
                                }
                            });
                        }
                    }
                    
                    @Override
                    public void a(final String s, final Map<String, String> map) {
                        final Uri parse = Uri.parse(s);
                        if ("fbad".equals(parse.getScheme()) && parse.getAuthority().equals("close")) {
                            c.a(c.this);
                        }
                        else {
                            if ("fbad".equals(parse.getScheme()) && com.facebook.ads.internal.a.c.a(parse.getAuthority()) && c.this.n != null) {
                                c.this.n.a(aa.e.a());
                            }
                            final b a = com.facebook.ads.internal.a.c.a(c.this.f, c.this.g, c.this.h.a(), parse, map);
                            if (a != null) {
                                try {
                                    a.a();
                                }
                                catch (Exception ex) {
                                    Log.e(c.a, "Error executing action", (Throwable)ex);
                                }
                            }
                        }
                    }
                };
                (this.o = new com.facebook.ads.internal.view.c.a(this.f, new WeakReference<com.facebook.ads.internal.view.c.a.b>(this.p), 1)).loadDataWithBaseURL(com.facebook.ads.internal.w.e.b.a(), this.i, "text/html", "utf-8", (String)null);
                return (Pair<a, View>)new Pair((Object)b, (Object)this.o);
            }
            case 2: {
                final RecyclerView recyclerView = new RecyclerView(this.f);
                recyclerView.setLayoutManager((RecyclerView$LayoutManager)new LinearLayoutManager(this.f, 0, false));
                recyclerView.setAdapter((RecyclerView$Adapter)new com.facebook.ads.internal.view.g.d(this.h.k().d(), com.facebook.ads.internal.view.g.c.b));
                return (Pair<a, View>)new Pair((Object)b, (Object)recyclerView);
            }
            case 3: {
                return (Pair<a, View>)new Pair((Object)b, (Object)new com.facebook.ads.internal.view.f.b(this.f, com.facebook.ads.internal.adapters.b.o.a(this.h), this.g, this.n, (com.facebook.ads.internal.view.f.b.c)new b(this), false, false));
            }
        }
    }
    
    public void d() {
        final String a = this.h.k().a();
        if (!TextUtils.isEmpty((CharSequence)a)) {
            final e e = new e(this.f, new HashMap<String, String>());
            e.a((e.a)new e.a() {
                @Override
                public void a() {
                    if (c.this.n != null) {
                        c.this.n.a(aa.i.a());
                    }
                }
                
                @Override
                public void a(final com.facebook.ads.internal.w.e.f f) {
                    if (c.this.n == null) {
                        return;
                    }
                    if (f != null && f.a()) {
                        c.this.n.a(aa.h.a());
                        return;
                    }
                    c.this.n.a(aa.i.a());
                }
            });
            e.executeOnExecutor(this.m, (Object[])new String[] { a });
        }
    }
    
    public void e() {
        if (this.o != null) {
            this.o.destroy();
            this.o = null;
            this.p = null;
        }
    }
    
    public enum a
    {
        a, 
        b, 
        c, 
        d;
    }
    
    private static class b implements com.facebook.ads.internal.view.f.b.c
    {
        final WeakReference<c> a;
        
        private b(final c c) {
            this.a = new WeakReference<c>(c);
        }
        
        @Override
        public void a() {
        }
        
        @Override
        public void a(final com.facebook.ads.internal.x.a a, final w w) {
        }
        
        @Override
        public void a(final boolean b) {
            if (this.a.get() != null) {
                this.a.get().g().performClick();
            }
        }
        
        @Override
        public void b() {
        }
        
        @Override
        public void c() {
            if (this.a.get() != null) {
                c.a(this.a.get());
            }
        }
        
        @Override
        public void d() {
            this.c();
        }
    }
}
