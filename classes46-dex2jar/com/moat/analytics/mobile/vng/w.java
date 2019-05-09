// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import com.moat.analytics.mobile.vng.a.b.a;
import java.util.Iterator;
import android.os.Looper;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import android.os.Handler;
import java.util.Queue;

class w
{
    private static w g;
    private static final Queue<c> h;
    volatile d a;
    volatile boolean b;
    volatile boolean c;
    volatile int d;
    private long e;
    private long f;
    private Handler i;
    private final AtomicBoolean j;
    private volatile long k;
    private final AtomicInteger l;
    private final AtomicBoolean m;
    
    static {
        h = new ConcurrentLinkedQueue<c>();
    }
    
    private w() {
        this.e = 1800000L;
        this.f = 60000L;
        this.a = w.d.a;
        this.b = false;
        this.c = false;
        this.d = 200;
        this.j = new AtomicBoolean(false);
        this.k = 0L;
        this.l = new AtomicInteger(0);
        this.m = new AtomicBoolean(false);
        try {
            this.i = new Handler(Looper.getMainLooper());
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.vng.m.a(ex);
        }
    }
    
    static w a() {
        synchronized (w.class) {
            if (w.g == null) {
                w.g = new w();
            }
            return w.g;
        }
    }
    
    private void a(final long n) {
        if (!this.m.compareAndSet(false, true)) {
            return;
        }
        p.a(3, "OnOff", this, "Performing status check.");
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                final Handler handler;
                handler.postDelayed((Runnable)new a("VNG", handler = new Handler(), (e)new e() {
                    @Override
                    public void a(final l l) {
                        while (true) {
                            while (true) {
                                c c = null;
                                Label_0188: {
                                    synchronized (w.h) {
                                        final boolean a = ((k)MoatAnalytics.getInstance()).a;
                                        if (w.this.a != l.d() || (w.this.a == w.d.a && a)) {
                                            w.this.a = l.d();
                                            if (w.this.a == w.d.a && a) {
                                                w.this.a = w.d.b;
                                            }
                                            if (w.this.a == w.d.b) {
                                                p.a(3, "OnOff", this, "Moat enabled - Version 2.2.0");
                                            }
                                            final Iterator iterator = w.h.iterator();
                                            while (iterator.hasNext()) {
                                                c = iterator.next();
                                                if (w.this.a != w.d.b) {
                                                    break Label_0188;
                                                }
                                                c.b.b();
                                            }
                                            break;
                                        }
                                        break;
                                    }
                                }
                                c.b.c();
                                continue;
                            }
                        }
                        while (!w.h.isEmpty()) {
                            w.h.remove();
                        }
                    }
                    // monitorexit(queue)
                }), n);
                Looper.loop();
            }
        }.start();
    }
    
    private void d() {
        synchronized (w.h) {
            final long currentTimeMillis = System.currentTimeMillis();
            final Iterator<c> iterator = (Iterator<c>)w.h.iterator();
            while (iterator.hasNext()) {
                if (currentTimeMillis - iterator.next().a >= 60000L) {
                    iterator.remove();
                }
            }
        }
        if (w.h.size() >= 15) {
            for (int i = 0; i < 5; ++i) {
                w.h.remove();
            }
        }
    }
    // monitorexit(queue)
    
    private void e() {
        if (!this.j.compareAndSet(false, true)) {
            return;
        }
        this.i.postDelayed((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    if (w.h.size() > 0) {
                        w.this.d();
                        w.this.i.postDelayed((Runnable)this, 60000L);
                        return;
                    }
                    w.this.j.compareAndSet(true, false);
                    w.this.i.removeCallbacks((Runnable)this);
                }
                catch (Exception ex) {
                    com.moat.analytics.mobile.vng.m.a(ex);
                }
            }
        }, 60000L);
    }
    
    void a(final b b) {
        if (this.a == w.d.b) {
            b.b();
            return;
        }
        this.d();
        w.h.add(new c(System.currentTimeMillis(), b));
        this.e();
    }
    
    void b() {
        if (System.currentTimeMillis() - this.k > this.e) {
            this.a(0L);
        }
    }
    
    private class a implements Runnable
    {
        private final Handler b;
        private final String c;
        private final e d;
        
        private a(final String s, final Handler b, final e d) {
            this.d = d;
            this.b = b;
            this.c = "https://z.moatads.com/" + s + "/android/" + "3f2ae9c1894282b5e0222f0d06bbf457191f816f".substring(0, 7) + "/status.json";
        }
        
        private void a() {
            final String b = this.b();
            final l l = new l(b);
            w.this.b = l.a();
            w.this.c = l.b();
            w.this.d = l.c();
            new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        w.a.this.d.a(l);
                    }
                    catch (Exception ex) {
                        com.moat.analytics.mobile.vng.m.a(ex);
                    }
                }
            });
            w.this.k = System.currentTimeMillis();
            w.this.m.compareAndSet(true, false);
            if (b == null) {
                if (w.this.l.incrementAndGet() < 10) {
                    w.this.a(w.this.f);
                }
                return;
            }
            w.this.l.set(0);
        }
        
        private String b() {
            final com.moat.analytics.mobile.vng.a.b.a<String> a = q.a(this.c + "?ts=" + System.currentTimeMillis() + "&v=" + "2.2.0");
            try {
                return a.b();
            }
            catch (Exception ex) {
                return null;
            }
        }
        
        @Override
        public void run() {
            while (true) {
                try {
                    this.a();
                    this.b.removeCallbacks((Runnable)this);
                    final Looper myLooper = Looper.myLooper();
                    if (myLooper != null) {
                        myLooper.quit();
                    }
                }
                catch (Exception ex) {
                    com.moat.analytics.mobile.vng.m.a(ex);
                    continue;
                }
                break;
            }
        }
    }
    
    interface b
    {
        void b();
        
        void c();
    }
    
    private class c
    {
        final Long a;
        final b b;
        
        c(final Long a, final b b) {
            this.a = a;
            this.b = b;
        }
    }
    
    enum d
    {
        a, 
        b;
    }
    
    interface e
    {
        void a(final l p0);
    }
}
