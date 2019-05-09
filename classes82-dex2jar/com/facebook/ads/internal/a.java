// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal;

import com.facebook.ads.internal.w.b.y;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.u.f;
import com.facebook.ads.internal.u.b;
import com.facebook.ads.AdSettings;
import com.facebook.ads.internal.w.b.m;
import com.facebook.ads.internal.protocol.g;
import com.facebook.ads.internal.adapters.AdAdapter;
import java.util.Map;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.internal.adapters.q;
import java.util.HashMap;
import com.facebook.ads.internal.protocol.AdPlacementType;
import java.util.ArrayList;
import com.facebook.ads.internal.adapters.i;
import java.util.List;
import android.os.Handler;
import com.facebook.ads.AdSize;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.adapters.d;
import android.content.Context;
import com.facebook.ads.internal.u.c;

public class a implements com.facebook.ads.internal.u.c.b
{
    private final Context a;
    private final String b;
    private final c c;
    private final d d;
    private final e e;
    private final AdSize f;
    private final int g;
    private boolean h;
    private final Handler i;
    private final Runnable j;
    private final com.facebook.ads.internal.s.c k;
    private a l;
    private com.facebook.ads.internal.m.c m;
    private String n;
    
    static {
        com.facebook.ads.internal.w.b.c.a();
    }
    
    public a(final Context a, final String b, final e e, final AdSize f, final int g) {
        this.a = a;
        this.b = b;
        this.e = e;
        this.f = f;
        this.g = g;
        (this.c = new c(this.a)).a((com.facebook.ads.internal.u.c.b)this);
        this.d = new d();
        this.h = true;
        this.i = new Handler();
        this.j = new b(this);
        this.k = com.facebook.ads.internal.s.d.a(this.a);
        com.facebook.ads.internal.n.a.b(this.a);
    }
    
    private List<i> d() {
        final com.facebook.ads.internal.m.c m = this.m;
        com.facebook.ads.internal.m.a a = m.e();
        final ArrayList list = new ArrayList<i>(m.d());
        while (a != null) {
            final AdAdapter a2 = this.d.a(AdPlacementType.NATIVE);
            if (a2 != null && a2.getPlacementType() == AdPlacementType.NATIVE) {
                final HashMap<String, com.facebook.ads.internal.m.d> hashMap = new HashMap<String, com.facebook.ads.internal.m.d>();
                hashMap.put("data", a.c());
                hashMap.put("definition", m.a());
                final i i = (i)a2;
                i.a(this.a, new q() {
                    @Override
                    public void a(final i i) {
                        list.add(i);
                    }
                    
                    @Override
                    public void a(final i i, final com.facebook.ads.internal.protocol.a a) {
                    }
                    
                    @Override
                    public void b(final i i) {
                    }
                    
                    @Override
                    public void c(final i i) {
                    }
                }, this.k, (Map<String, Object>)hashMap, NativeAdBase.getViewTraversalPredicate());
            }
            a = m.e();
        }
        return (List<i>)list;
    }
    
    public void a() {
        while (true) {
            while (true) {
                try {
                    final g g = new g(this.a, null, null, null);
                    final Context a = this.a;
                    final com.facebook.ads.internal.n.d d = new com.facebook.ads.internal.n.d(this.a, false);
                    final String b = this.b;
                    if (this.f != null) {
                        final m m = new m(this.f.getHeight(), this.f.getWidth());
                        this.c.a(new com.facebook.ads.internal.u.b(a, d, b, m, this.e, null, this.g, AdSettings.isTestMode(this.a), AdSettings.isChildDirected(), g, com.facebook.ads.internal.w.b.q.a(com.facebook.ads.internal.r.a.G(this.a)), this.n));
                        return;
                    }
                }
                catch (com.facebook.ads.internal.protocol.b b2) {
                    this.a(com.facebook.ads.internal.protocol.a.a(b2));
                    return;
                }
                final m m = null;
                continue;
            }
        }
    }
    
    public void a(final a l) {
        this.l = l;
    }
    
    @Override
    public void a(final com.facebook.ads.internal.protocol.a a) {
        if (this.h) {
            this.i.postDelayed(this.j, 1800000L);
        }
        if (this.l != null) {
            this.l.a(a);
        }
    }
    
    @Override
    public void a(final f f) {
        final com.facebook.ads.internal.m.c a = f.a();
        if (a == null) {
            throw new IllegalStateException("no placement in response");
        }
        if (this.h) {
            long c;
            if ((c = a.a().c()) == 0L) {
                c = 1800000L;
            }
            this.i.postDelayed(this.j, c);
        }
        this.m = a;
        final List<i> d = this.d();
        if (this.l != null) {
            if (!d.isEmpty()) {
                this.l.a(d);
                return;
            }
            this.l.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.NO_FILL, ""));
        }
    }
    
    public void a(final String n) {
        this.n = n;
    }
    
    public void b() {
    }
    
    public void c() {
        this.h = false;
        this.i.removeCallbacks(this.j);
    }
    
    public interface a
    {
        void a(final com.facebook.ads.internal.protocol.a p0);
        
        void a(final List<i> p0);
    }
    
    private static final class b extends y<a>
    {
        public b(final a a) {
            super(a);
        }
        
        @Override
        public void run() {
            final a a = this.a();
            if (a == null) {
                return;
            }
            if (com.facebook.ads.internal.w.i.a.a(a.a)) {
                a.a();
                return;
            }
            a.i.postDelayed(a.j, 5000L);
        }
    }
}
