// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i;

import com.facebook.ads.internal.view.i.b.m;
import com.facebook.ads.internal.view.i.b.w;
import com.facebook.ads.internal.o.d;
import java.util.Map;
import android.support.annotation.Nullable;
import android.os.Bundle;
import java.util.List;
import java.util.ArrayList;
import android.content.Context;
import com.facebook.ads.internal.view.i.b.n;
import com.facebook.ads.internal.view.i.b.t;
import com.facebook.ads.internal.view.i.b.z;
import com.facebook.ads.internal.view.i.b.y;
import com.facebook.ads.internal.view.i.b.q;
import com.facebook.ads.internal.view.i.b.o;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.b.s;
import com.facebook.ads.internal.o.f;
import com.facebook.ads.internal.view.i.b.x;

public class b extends c
{
    public int a;
    private final x b;
    private final f<s> c;
    private final f<i> d;
    private final f<k> e;
    private final f<o> f;
    private final f<com.facebook.ads.internal.view.i.b.c> g;
    private final f<q> h;
    private final f<y> i;
    private final f<z> j;
    private final f<t> k;
    private final n l;
    private final com.facebook.ads.internal.view.i.a m;
    private boolean n;
    
    public b(final Context context, final com.facebook.ads.internal.s.c c, final com.facebook.ads.internal.view.i.a a, final String s) {
        this(context, c, a, new ArrayList<com.facebook.ads.internal.d.b>(), s);
    }
    
    public b(final Context context, final com.facebook.ads.internal.s.c c, final com.facebook.ads.internal.view.i.a a, final String s, @Nullable final Bundle bundle) {
        this(context, c, a, new ArrayList<com.facebook.ads.internal.d.b>(), s, bundle, null);
    }
    
    public b(final Context context, final com.facebook.ads.internal.s.c c, final com.facebook.ads.internal.view.i.a a, final String s, @Nullable final Map<String, String> map) {
        this(context, c, a, new ArrayList<com.facebook.ads.internal.d.b>(), s, null, map);
    }
    
    public b(final Context context, final com.facebook.ads.internal.s.c c, final com.facebook.ads.internal.view.i.a m, final List<com.facebook.ads.internal.d.b> list, final String s) {
        super(context, c, (a)m, list, s);
        this.b = new x() {
            @Override
            public void a(final w w) {
                com.facebook.ads.internal.view.i.b.this.e();
            }
        };
        this.c = new f<s>() {
            @Override
            public Class<s> a() {
                return s.class;
            }
            
            @Override
            public void a(final s s) {
                com.facebook.ads.internal.view.i.b.this.f();
            }
        };
        this.d = new f<i>() {
            @Override
            public Class<i> a() {
                return i.class;
            }
            
            @Override
            public void a(final i i) {
                com.facebook.ads.internal.view.i.b.this.h();
                com.facebook.ads.internal.view.i.b.this.a(i.a(), false, i.a() < 2000.0);
            }
        };
        this.e = new f<k>() {
            @Override
            public Class<k> a() {
                return k.class;
            }
            
            @Override
            public void a(final k k) {
                if (!com.facebook.ads.internal.view.i.b.this.n) {
                    com.facebook.ads.internal.view.i.b.this.n = true;
                    return;
                }
                com.facebook.ads.internal.view.i.b.this.i();
            }
        };
        this.f = new f<o>() {
            @Override
            public Class<o> a() {
                return o.class;
            }
            
            @Override
            public void a(final o o) {
                final int a = o.a();
                if (com.facebook.ads.internal.view.i.b.this.a > 0 && a == com.facebook.ads.internal.view.i.b.this.m.getDuration() && com.facebook.ads.internal.view.i.b.this.m.getDuration() > com.facebook.ads.internal.view.i.b.this.a) {
                    return;
                }
                com.facebook.ads.internal.view.i.b.this.a(a);
            }
        };
        this.g = new f<com.facebook.ads.internal.view.i.b.c>() {
            @Override
            public Class<com.facebook.ads.internal.view.i.b.c> a() {
                return com.facebook.ads.internal.view.i.b.c.class;
            }
            
            @Override
            public void a(final com.facebook.ads.internal.view.i.b.c c) {
                final int a = c.a();
                final int b = c.b();
                if (com.facebook.ads.internal.view.i.b.this.a > 0 && a == b && b > com.facebook.ads.internal.view.i.b.this.a) {
                    return;
                }
                if (b >= a + 500) {
                    com.facebook.ads.internal.view.i.b.this.b(a);
                    return;
                }
                if (b == 0) {
                    com.facebook.ads.internal.view.i.b.this.b(com.facebook.ads.internal.view.i.b.this.a);
                    return;
                }
                com.facebook.ads.internal.view.i.b.this.b(b);
            }
        };
        this.h = new f<q>() {
            @Override
            public Class<q> a() {
                return q.class;
            }
            
            @Override
            public void a(final q q) {
                com.facebook.ads.internal.view.i.b.this.a(q.a(), q.b());
            }
        };
        this.i = new f<y>() {
            @Override
            public Class<y> a() {
                return y.class;
            }
            
            @Override
            public void a(final y y) {
                com.facebook.ads.internal.view.i.b.this.b();
            }
        };
        this.j = new f<z>() {
            @Override
            public Class<z> a() {
                return z.class;
            }
            
            @Override
            public void a(final z z) {
                com.facebook.ads.internal.view.i.b.this.c();
            }
        };
        this.k = new f<t>() {
            @Override
            public Class<t> a() {
                return t.class;
            }
            
            @Override
            public void a(final t t) {
                com.facebook.ads.internal.view.i.b.this.a(com.facebook.ads.internal.view.i.b.this.j(), com.facebook.ads.internal.view.i.b.this.j());
            }
        };
        this.l = new n() {
            @Override
            public void a(final m m) {
                com.facebook.ads.internal.view.i.b.this.a = com.facebook.ads.internal.view.i.b.this.m.getDuration();
            }
        };
        this.n = false;
        this.m = m;
        this.m.getEventBus().a(this.b, this.f, this.c, this.e, this.d, this.g, this.h, this.i, this.j, this.l, this.k);
    }
    
    public b(final Context context, final com.facebook.ads.internal.s.c c, final com.facebook.ads.internal.view.i.a m, final List<com.facebook.ads.internal.d.b> list, final String s, @Nullable final Bundle bundle, @Nullable final Map<String, String> map) {
        super(context, c, (a)m, list, s, bundle, map);
        this.b = new x() {
            @Override
            public void a(final w w) {
                com.facebook.ads.internal.view.i.b.this.e();
            }
        };
        this.c = new f<s>() {
            @Override
            public Class<s> a() {
                return s.class;
            }
            
            @Override
            public void a(final s s) {
                com.facebook.ads.internal.view.i.b.this.f();
            }
        };
        this.d = new f<i>() {
            @Override
            public Class<i> a() {
                return i.class;
            }
            
            @Override
            public void a(final i i) {
                com.facebook.ads.internal.view.i.b.this.h();
                com.facebook.ads.internal.view.i.b.this.a(i.a(), false, i.a() < 2000.0);
            }
        };
        this.e = new f<k>() {
            @Override
            public Class<k> a() {
                return k.class;
            }
            
            @Override
            public void a(final k k) {
                if (!com.facebook.ads.internal.view.i.b.this.n) {
                    com.facebook.ads.internal.view.i.b.this.n = true;
                    return;
                }
                com.facebook.ads.internal.view.i.b.this.i();
            }
        };
        this.f = new f<o>() {
            @Override
            public Class<o> a() {
                return o.class;
            }
            
            @Override
            public void a(final o o) {
                final int a = o.a();
                if (com.facebook.ads.internal.view.i.b.this.a > 0 && a == com.facebook.ads.internal.view.i.b.this.m.getDuration() && com.facebook.ads.internal.view.i.b.this.m.getDuration() > com.facebook.ads.internal.view.i.b.this.a) {
                    return;
                }
                com.facebook.ads.internal.view.i.b.this.a(a);
            }
        };
        this.g = new f<com.facebook.ads.internal.view.i.b.c>() {
            @Override
            public Class<com.facebook.ads.internal.view.i.b.c> a() {
                return com.facebook.ads.internal.view.i.b.c.class;
            }
            
            @Override
            public void a(final com.facebook.ads.internal.view.i.b.c c) {
                final int a = c.a();
                final int b = c.b();
                if (com.facebook.ads.internal.view.i.b.this.a > 0 && a == b && b > com.facebook.ads.internal.view.i.b.this.a) {
                    return;
                }
                if (b >= a + 500) {
                    com.facebook.ads.internal.view.i.b.this.b(a);
                    return;
                }
                if (b == 0) {
                    com.facebook.ads.internal.view.i.b.this.b(com.facebook.ads.internal.view.i.b.this.a);
                    return;
                }
                com.facebook.ads.internal.view.i.b.this.b(b);
            }
        };
        this.h = new f<q>() {
            @Override
            public Class<q> a() {
                return q.class;
            }
            
            @Override
            public void a(final q q) {
                com.facebook.ads.internal.view.i.b.this.a(q.a(), q.b());
            }
        };
        this.i = new f<y>() {
            @Override
            public Class<y> a() {
                return y.class;
            }
            
            @Override
            public void a(final y y) {
                com.facebook.ads.internal.view.i.b.this.b();
            }
        };
        this.j = new f<z>() {
            @Override
            public Class<z> a() {
                return z.class;
            }
            
            @Override
            public void a(final z z) {
                com.facebook.ads.internal.view.i.b.this.c();
            }
        };
        this.k = new f<t>() {
            @Override
            public Class<t> a() {
                return t.class;
            }
            
            @Override
            public void a(final t t) {
                com.facebook.ads.internal.view.i.b.this.a(com.facebook.ads.internal.view.i.b.this.j(), com.facebook.ads.internal.view.i.b.this.j());
            }
        };
        this.l = new n() {
            @Override
            public void a(final m m) {
                com.facebook.ads.internal.view.i.b.this.a = com.facebook.ads.internal.view.i.b.this.m.getDuration();
            }
        };
        this.n = false;
        this.m = m;
        this.m.getEventBus().a(this.b, this.f, this.c, this.e, this.d, this.g, this.h, this.i, this.j, this.k);
    }
    
    public void a() {
        this.m.getStateHandler().post((Runnable)new Runnable() {
            @Override
            public void run() {
                com.facebook.ads.internal.view.i.b.this.m.getEventBus().b(com.facebook.ads.internal.view.i.b.this.b, com.facebook.ads.internal.view.i.b.this.f, com.facebook.ads.internal.view.i.b.this.c, com.facebook.ads.internal.view.i.b.this.e, com.facebook.ads.internal.view.i.b.this.d, com.facebook.ads.internal.view.i.b.this.g, com.facebook.ads.internal.view.i.b.this.h, com.facebook.ads.internal.view.i.b.this.i, com.facebook.ads.internal.view.i.b.this.j, com.facebook.ads.internal.view.i.b.this.l, com.facebook.ads.internal.view.i.b.this.k);
            }
        });
    }
}
