// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.b;

import android.support.annotation.Nullable;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.t.d;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.internal.adapters.q;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.i;
import java.util.Iterator;
import com.facebook.ads.internal.w.e.e;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import android.content.Context;

public class g extends c
{
    public g(final Context context, final com.facebook.ads.internal.b.a a) {
        super(context, a);
    }
    
    static /* synthetic */ Map a(final g g, final long n) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("delay", String.valueOf(System.currentTimeMillis() - n));
        return hashMap;
    }
    
    static /* synthetic */ void a(final g g, final List list, final Map map) {
        if (list != null && !list.isEmpty()) {
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                new e(g.b, map).execute((Object[])new String[] { iterator.next() });
            }
        }
    }
    
    @Override
    protected void a() {
        final i i = (i)this.f;
        if (!i.A()) {
            throw new IllegalStateException("ad is not ready or already displayed");
        }
        this.c.a(i);
    }
    
    @Override
    protected void a(final AdAdapter adAdapter, final com.facebook.ads.internal.m.c c, final com.facebook.ads.internal.m.a a, final Map<String, Object> map) {
        final i i = (i)adAdapter;
        final long currentTimeMillis = System.currentTimeMillis();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                g.this.a(i);
                final Map a = g.a(g.this, currentTimeMillis);
                a.put("error", "-1");
                a.put("msg", "timeout");
                g.a(g.this, a.a(com.facebook.ads.internal.m.e.a), a);
                g.this.i();
            }
        };
        this.j().postDelayed((Runnable)runnable, (long)c.a().j());
        i.a(this.b, new q() {
            boolean a = false;
            boolean b = false;
            boolean c = false;
            
            @Override
            public void a(final i f) {
                if (f == com.facebook.ads.internal.b.g.this.e) {
                    com.facebook.ads.internal.b.g.this.j().removeCallbacks(runnable);
                    com.facebook.ads.internal.b.g.this.f = f;
                    com.facebook.ads.internal.b.g.this.c.a((AdAdapter)f);
                    if (!this.a) {
                        this.a = true;
                        com.facebook.ads.internal.b.g.a(com.facebook.ads.internal.b.g.this, a.a(com.facebook.ads.internal.m.e.a), com.facebook.ads.internal.b.g.a(com.facebook.ads.internal.b.g.this, currentTimeMillis));
                    }
                }
            }
            
            @Override
            public void a(final i i, final com.facebook.ads.internal.protocol.a a) {
                if (i != com.facebook.ads.internal.b.g.this.e) {
                    return;
                }
                com.facebook.ads.internal.b.g.this.j().removeCallbacks(runnable);
                com.facebook.ads.internal.b.g.this.a(i);
                if (!this.a) {
                    this.a = true;
                    final Map a2 = com.facebook.ads.internal.b.g.a(com.facebook.ads.internal.b.g.this, currentTimeMillis);
                    a2.put("error", String.valueOf(a.a().getErrorCode()));
                    a2.put("msg", String.valueOf(a.b()));
                    com.facebook.ads.internal.b.g.a(com.facebook.ads.internal.b.g.this, a.a(com.facebook.ads.internal.m.e.a), a2);
                }
                com.facebook.ads.internal.b.g.this.i();
            }
            
            @Override
            public void b(final i i) {
                if (!this.b) {
                    this.b = true;
                    com.facebook.ads.internal.b.g.a(com.facebook.ads.internal.b.g.this, a.a(com.facebook.ads.internal.m.e.b), null);
                }
            }
            
            @Override
            public void c(final i i) {
                if (!this.c) {
                    this.c = true;
                    com.facebook.ads.internal.b.g.a(com.facebook.ads.internal.b.g.this, a.a(com.facebook.ads.internal.m.e.c), null);
                }
                if (com.facebook.ads.internal.b.g.this.c != null) {
                    com.facebook.ads.internal.b.g.this.c.a();
                }
            }
        }, this.g, map, NativeAdBase.getViewTraversalPredicate());
    }
    
    @Override
    protected void a(final String s) {
        final com.facebook.ads.internal.protocol.a a = com.facebook.ads.internal.b.e.a(this.b, 0, 1);
        if (a != null) {
            this.a(a);
            return;
        }
        super.a(s);
    }
    
    @Nullable
    @Override
    com.facebook.ads.internal.protocol.a c() {
        if (this.h.h == null || this.h.h == com.facebook.ads.internal.t.d.a || this.d()) {
            return null;
        }
        return new com.facebook.ads.internal.protocol.a(AdErrorType.CLEAR_TEXT_SUPPORT_NOT_ALLOWED, "");
    }
}
