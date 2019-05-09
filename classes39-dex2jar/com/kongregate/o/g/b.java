// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.g;

import java.util.Map;
import com.kongregate.android.internal.util.p;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import com.kongregate.android.internal.sdk.l;
import android.support.v4.content.LocalBroadcastManager;
import com.kongregate.android.internal.util.j;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import com.kongregate.o.c.e;
import com.kongregate.o.c.d;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.Random;
import org.json.JSONObject;
import com.kongregate.o.i.a;
import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;

public class b
{
    private static b b;
    public final a a;
    private final ScheduledExecutorService c;
    private final Context d;
    private boolean e;
    private final com.kongregate.o.i.a f;
    
    static {
        com.kongregate.o.g.b.b = null;
    }
    
    private b(final Context d) {
        this.e = false;
        this.a = (a)new a() {
            @Override
            public void a(final c c, final JSONObject jsonObject) {
            }
        };
        final String string = "KongregateAPI-httpRequestQueue-" + new Random().nextInt() + "-";
        this.d = d;
        this.f = new com.kongregate.o.i.a(this.d);
        this.c = new e(1, new com.kongregate.o.c.b(string), new ThreadPoolExecutor.DiscardPolicy(), com.kongregate.o.c.d.g());
    }
    
    public static b a() {
        synchronized (b.class) {
            if (com.kongregate.o.g.b.b == null) {
                throw new IllegalStateException("must initialize before first use");
            }
        }
        // monitorexit(b.class)
        return com.kongregate.o.g.b.b;
    }
    
    public static b a(final Context context, final String s, final long n, final String s2) {
        synchronized (b.class) {
            com.kongregate.o.g.a.a();
            com.kongregate.o.g.a.b().b(s);
            com.kongregate.o.g.a.b().a(n, s2);
            if (com.kongregate.o.g.b.b == null) {
                com.kongregate.o.g.b.b = new b(context);
            }
            return com.kongregate.o.g.b.b;
        }
    }
    
    private c a(final c c, final a a) {
        if (a == null) {
            throw new IllegalArgumentException("No handler specified. You may use NullResultHandler");
        }
        if (!c.f()) {
            j.c("HTTP request error: " + c.e());
            if (!this.e) {
                this.e = true;
                LocalBroadcastManager.getInstance(this.d).sendBroadcast(l.a("KONG_API_EVENT_SERVICE_UNAVAILABLE"));
            }
            a.a(c);
            return c;
        }
        this.e = false;
        final JSONObject c2 = c.c();
        if (c2 != null && c2.optBoolean("success", (boolean)Boolean.TRUE)) {
            a.a(c, c2);
            return c;
        }
        if ("session".equals(c2.optString("error", ""))) {
            j.c("session error. clear active user");
            com.kongregate.o.j.a.a().c();
        }
        a.b(c, c2);
        return c;
    }
    
    public Future<c> a(final String s, final a a) {
        return this.c.submit((Callable<c>)new Callable<c>() {
            public c a() throws Exception {
                if (!com.kongregate.o.g.b.this.f.b()) {
                    return com.kongregate.o.g.b.this.a(new c(p.d), a);
                }
                if (s.startsWith("https")) {
                    return com.kongregate.o.g.b.this.a(com.kongregate.o.g.a.b().f(s), a);
                }
                return com.kongregate.o.g.b.this.a(com.kongregate.o.g.a.b().e(s), a);
            }
        });
    }
    
    public Future<c> a(final String s, final Map<String, Object> map, final a a) {
        return this.c.submit((Callable<c>)new Callable<c>() {
            public c a() throws Exception {
                if (!com.kongregate.o.g.b.this.f.b()) {
                    return com.kongregate.o.g.b.this.a(new c(p.d), a);
                }
                if (s.startsWith("https")) {
                    return com.kongregate.o.g.b.this.a(com.kongregate.o.g.a.b().a(s, map), a);
                }
                return com.kongregate.o.g.b.this.a(com.kongregate.o.g.a.b().b(s, map), a);
            }
        });
    }
    
    public Future<c> a(final String s, final Map<String, Object> map, final String s2, final Map<String, String> map2, final a a) {
        return this.c.submit((Callable<c>)new Callable<c>() {
            public c a() throws Exception {
                if (!com.kongregate.o.g.b.this.f.b()) {
                    return com.kongregate.o.g.b.this.a(new c(p.d), a);
                }
                if (s.startsWith("https")) {
                    return com.kongregate.o.g.b.this.a(com.kongregate.o.g.a.b().a(s, map, s2, map2), a);
                }
                return com.kongregate.o.g.b.this.a(com.kongregate.o.g.a.b().b(s, map, s2, map2), a);
            }
        });
    }
    
    public boolean b() {
        return this.f.b();
    }
    
    public abstract static class a
    {
        public void a(final c c) {
            j.d("HTTP error: " + c.e());
        }
        
        public abstract void a(final c p0, final JSONObject p1);
        
        public void b(final c c, final JSONObject jsonObject) {
            j.d("Response error: " + c.b());
        }
    }
}
