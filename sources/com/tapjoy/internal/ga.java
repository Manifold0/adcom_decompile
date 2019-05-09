package com.tapjoy.internal;

import java.io.File;
import java.util.concurrent.TimeUnit;

public final class ga implements Runnable {
    /* renamed from: a */
    final go f7833a;
    /* renamed from: b */
    ci f7834b;
    /* renamed from: c */
    private final Object f7835c = this.f7833a;
    /* renamed from: d */
    private final Thread f7836d;
    /* renamed from: e */
    private boolean f7837e;

    /* renamed from: com.tapjoy.internal.ga$1 */
    class C29361 extends hp {
        /* renamed from: a */
        final /* synthetic */ ga f7832a;

        C29361(ga gaVar) {
            this.f7832a = gaVar;
        }

        /* renamed from: a */
        protected final boolean mo6293a() {
            return !this.f7832a.f7833a.m7981c();
        }
    }

    public ga(File file) {
        this.f7833a = new go(file);
        new Object[1][0] = Integer.valueOf(this.f7833a.m7979b());
        this.f7836d = new Thread(this, "5Rocks");
        this.f7836d.start();
    }

    public final void run() {
        Object obj;
        Object[] objArr;
        int i = 1;
        while (true) {
            long j = 0;
            while (this.f7834b != null && this.f7833a.m7979b() > 0 && j <= 0) {
                try {
                    if (this.f7833a.m7979b() > 10000) {
                        this.f7833a.m7978a(this.f7833a.m7979b() - 10000);
                    }
                    dy b = this.f7833a.m7980b(0);
                    if (b == null) {
                        break;
                    }
                    ek ekVar = b.f7470w;
                    if (ekVar != null && ekVar.f7642G == null) {
                        gq.f7974c.await(3, TimeUnit.SECONDS);
                    }
                    if (!C2999y.m8234c()) {
                        gq.f7973b.await(3, TimeUnit.SECONDS);
                    }
                    if (this.f7837e || b.f7461n == eb.APP || this.f7833a.m7979b() >= 100 || b.f7463p.longValue() > System.currentTimeMillis()) {
                        j = 0;
                    } else {
                        j = Math.min(Math.max((b.f7463p.longValue() + 60000) - System.currentTimeMillis(), 0), 60000);
                    }
                    if (j <= 0) {
                        cf hnVar = new hn();
                        hnVar.m8077a(b);
                        new Object[1][0] = b;
                        int i2 = 1;
                        while (i2 < 100 && i2 < this.f7833a.m7979b()) {
                            dy b2 = this.f7833a.m7980b(i2);
                            if (b2 == null || !hnVar.m8077a(b2)) {
                                break;
                            }
                            new Object[1][0] = b2;
                            i2++;
                        }
                        try {
                            Object[] objArr2 = new Object[]{Integer.valueOf(hnVar.m8080g()), Integer.valueOf(i + 1)};
                            this.f7834b.mo6207a(hnVar);
                            this.f7833a.m7978a(hnVar.m8080g());
                        } catch (Exception e) {
                            obj = e;
                            i = i2;
                            objArr = new Object[]{Integer.valueOf(hnVar.m8080g()), obj};
                            j = 300000;
                        } catch (InterruptedException e2) {
                            return;
                        }
                        try {
                            new Object[1][0] = Integer.valueOf(hnVar.m8080g());
                            i = 0;
                        } catch (Exception e3) {
                            Exception exception = e3;
                            i = 0;
                            objArr = new Object[]{Integer.valueOf(hnVar.m8080g()), obj};
                            j = 300000;
                        } catch (InterruptedException e22) {
                            return;
                        }
                    }
                } catch (InterruptedException e222) {
                    return;
                } catch (Exception e4) {
                    return;
                }
            }
            this.f7833a.flush();
            if (j > 0) {
                synchronized (this.f7835c) {
                    this.f7837e = false;
                    new Object[1][0] = Long.valueOf(j);
                    this.f7835c.wait(j);
                }
            } else {
                synchronized (this.f7835c) {
                    this.f7837e = false;
                    if (this.f7834b == null || this.f7833a.m7981c()) {
                        this.f7835c.wait();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    final void m7822a(boolean z) {
        synchronized (this.f7835c) {
            this.f7837e = z;
            this.f7835c.notify();
        }
    }

    /* renamed from: a */
    public final void m7821a() {
        if (this.f7834b != null && !this.f7833a.m7981c()) {
            m7822a(true);
        }
    }
}
