// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import android.app.Activity;
import android.view.View;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.Semaphore;
import android.content.Context;

final class q
{
    private UnityPlayer a;
    private Context b;
    private a c;
    private final Semaphore d;
    private final Lock e;
    private p f;
    private int g;
    private boolean h;
    private boolean i;
    
    q(final UnityPlayer a) {
        this.a = null;
        this.b = null;
        this.d = new Semaphore(0);
        this.e = new ReentrantLock();
        this.f = null;
        this.g = 2;
        this.h = false;
        this.i = false;
        this.a = a;
    }
    
    private void d() {
        if (this.f != null) {
            this.a.removeViewFromPlayer((View)this.f);
            this.i = false;
            this.f.destroyPlayer();
            this.f = null;
            if (this.c != null) {
                this.c.a();
            }
        }
    }
    
    public final void a() {
        this.e.lock();
        if (this.f != null) {
            if (this.g == 0) {
                this.f.CancelOnPrepare();
            }
            else if (this.i && !(this.h = this.f.a())) {
                this.f.pause();
            }
        }
        this.e.unlock();
    }
    
    public final boolean a(final Context b, final String s, int g, final int n, final int n2, boolean b2, final long n3, final long n4, final a c) {
        this.e.lock();
        this.c = c;
        this.b = b;
        this.d.drainPermits();
        this.g = 2;
        this.runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                if (q.this.f != null) {
                    com.unity3d.player.g.Log(5, "Video already playing");
                    q.this.g = 2;
                    q.this.d.release();
                }
                else {
                    q.this.f = new p(q.this.b, s, g, n, n2, b2, n3, n4, (p.a)new p.a() {
                        @Override
                        public final void a(final int n) {
                            q.this.e.lock();
                            q.this.g = n;
                            if (n == 3 && q.this.i) {
                                q.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public final void run() {
                                        q.this.d();
                                        q.this.a.resume();
                                    }
                                });
                            }
                            if (n != 0) {
                                q.this.d.release();
                            }
                            q.this.e.unlock();
                        }
                    });
                    if (q.this.f != null) {
                        q.this.a.addView((View)q.this.f);
                    }
                }
            }
        });
        b2 = false;
        while (true) {
            try {
                this.e.unlock();
                this.d.acquire();
                this.e.lock();
                g = this.g;
                if (g != 2) {
                    b2 = true;
                }
                else {
                    b2 = false;
                }
                this.runOnUiThread(new Runnable() {
                    @Override
                    public final void run() {
                        q.this.a.pause();
                    }
                });
                if (b2 && this.g != 3) {
                    this.runOnUiThread(new Runnable() {
                        @Override
                        public final void run() {
                            if (q.this.f != null) {
                                q.this.a.addViewToPlayer((View)q.this.f, true);
                                q.this.i = true;
                                q.this.f.requestFocus();
                            }
                        }
                    });
                }
                else {
                    this.runOnUiThread(new Runnable() {
                        @Override
                        public final void run() {
                            q.this.d();
                            q.this.a.resume();
                        }
                    });
                }
                this.e.unlock();
                return b2;
            }
            catch (InterruptedException ex) {
                continue;
            }
            break;
        }
    }
    
    public final void b() {
        this.e.lock();
        if (this.f != null && this.i && !this.h) {
            this.f.start();
        }
        this.e.unlock();
    }
    
    public final void c() {
        this.e.lock();
        if (this.f != null) {
            this.f.updateVideoLayout();
        }
        this.e.unlock();
    }
    
    protected final void runOnUiThread(final Runnable runnable) {
        if (this.b instanceof Activity) {
            ((Activity)this.b).runOnUiThread(runnable);
            return;
        }
        com.unity3d.player.g.Log(5, "Not running from an Activity; Ignoring execution request...");
    }
    
    public interface a
    {
        void a();
    }
}
