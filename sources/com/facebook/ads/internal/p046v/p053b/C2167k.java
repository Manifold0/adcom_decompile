package com.facebook.ads.internal.p046v.p053b;

import android.util.Log;
import java.lang.Thread.State;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.facebook.ads.internal.v.b.k */
class C2167k {
    /* renamed from: a */
    private final C2178n f4997a;
    /* renamed from: b */
    private final C2154a f4998b;
    /* renamed from: c */
    private final Object f4999c = new Object();
    /* renamed from: d */
    private final Object f5000d = new Object();
    /* renamed from: e */
    private final AtomicInteger f5001e;
    /* renamed from: f */
    private volatile Thread f5002f;
    /* renamed from: g */
    private volatile boolean f5003g;
    /* renamed from: h */
    private volatile int f5004h = -1;

    /* renamed from: com.facebook.ads.internal.v.b.k$a */
    private class C2184a implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2167k f5039a;

        private C2184a(C2167k c2167k) {
            this.f5039a = c2167k;
        }

        public void run() {
            C2167k.m5541a(this.f5039a);
        }
    }

    public C2167k(C2178n c2178n, C2154a c2154a) {
        this.f4997a = (C2178n) C2182j.m5591a(c2178n);
        this.f4998b = (C2154a) C2182j.m5591a(c2154a);
        this.f5001e = new AtomicInteger();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    static /* synthetic */ void m5541a(com.facebook.ads.internal.p046v.p053b.C2167k r8) {
        /*
        r3 = -1;
        r1 = 0;
        r0 = r8.f4998b;	 Catch:{ Throwable -> 0x008a, all -> 0x0087 }
        r1 = r0.mo5520a();	 Catch:{ Throwable -> 0x008a, all -> 0x0087 }
        r0 = r8.f4997a;	 Catch:{ Throwable -> 0x008a, all -> 0x0087 }
        r0.mo5533a(r1);	 Catch:{ Throwable -> 0x008a, all -> 0x0087 }
        r0 = r8.f4997a;	 Catch:{ Throwable -> 0x008a, all -> 0x0087 }
        r2 = r0.mo5531a();	 Catch:{ Throwable -> 0x008a, all -> 0x0087 }
        r0 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x003f }
    L_0x0017:
        r4 = r8.f4997a;	 Catch:{ Throwable -> 0x003f }
        r4 = r4.mo5532a(r0);	 Catch:{ Throwable -> 0x003f }
        if (r4 == r3) goto L_0x005e;
    L_0x001f:
        r5 = r8.f5000d;	 Catch:{ Throwable -> 0x003f }
        monitor-enter(r5);	 Catch:{ Throwable -> 0x003f }
        r6 = r8.m5544c();	 Catch:{ all -> 0x0051 }
        if (r6 == 0) goto L_0x0032;
    L_0x0028:
        monitor-exit(r5);	 Catch:{ all -> 0x0051 }
        r8.m5545d();
        r0 = (long) r1;
        r2 = (long) r2;
        r8.m5543b(r0, r2);
    L_0x0031:
        return;
    L_0x0032:
        r6 = r8.f4998b;	 Catch:{ all -> 0x0051 }
        r6.mo5522a(r0, r4);	 Catch:{ all -> 0x0051 }
        monitor-exit(r5);	 Catch:{ all -> 0x0051 }
        r1 = r1 + r4;
        r4 = (long) r1;
        r6 = (long) r2;
        r8.m5543b(r4, r6);	 Catch:{ Throwable -> 0x003f }
        goto L_0x0017;
    L_0x003f:
        r0 = move-exception;
    L_0x0040:
        r3 = r8.f5001e;	 Catch:{ all -> 0x0054 }
        r3.incrementAndGet();	 Catch:{ all -> 0x0054 }
        r8.m5550a(r0);	 Catch:{ all -> 0x0054 }
        r8.m5545d();
        r0 = (long) r1;
        r2 = (long) r2;
        r8.m5543b(r0, r2);
        goto L_0x0031;
    L_0x0051:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0051 }
        throw r0;	 Catch:{ Throwable -> 0x003f }
    L_0x0054:
        r0 = move-exception;
    L_0x0055:
        r8.m5545d();
        r4 = (long) r1;
        r2 = (long) r2;
        r8.m5543b(r4, r2);
        throw r0;
    L_0x005e:
        r3 = r8.f5000d;	 Catch:{ Throwable -> 0x003f }
        monitor-enter(r3);	 Catch:{ Throwable -> 0x003f }
        r0 = r8.m5544c();	 Catch:{ all -> 0x0084 }
        if (r0 != 0) goto L_0x007a;
    L_0x0067:
        r0 = r8.f4998b;	 Catch:{ all -> 0x0084 }
        r0 = r0.mo5520a();	 Catch:{ all -> 0x0084 }
        r4 = r8.f4997a;	 Catch:{ all -> 0x0084 }
        r4 = r4.mo5531a();	 Catch:{ all -> 0x0084 }
        if (r0 != r4) goto L_0x007a;
    L_0x0075:
        r0 = r8.f4998b;	 Catch:{ all -> 0x0084 }
        r0.mo5524c();	 Catch:{ all -> 0x0084 }
    L_0x007a:
        monitor-exit(r3);	 Catch:{ all -> 0x0084 }
        r8.m5545d();
        r0 = (long) r1;
        r2 = (long) r2;
        r8.m5543b(r0, r2);
        goto L_0x0031;
    L_0x0084:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0084 }
        throw r0;	 Catch:{ Throwable -> 0x003f }
    L_0x0087:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0055;
    L_0x008a:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0040;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.v.b.k.a(com.facebook.ads.internal.v.b.k):void");
    }

    /* renamed from: b */
    private synchronized void m5542b() {
        Object obj = (this.f5002f == null || this.f5002f.getState() == State.TERMINATED) ? null : 1;
        if (!(this.f5003g || this.f4998b.mo5525d() || obj != null)) {
            this.f5002f = new Thread(new C2184a(), "Source reader for " + this.f4997a);
            this.f5002f.start();
        }
    }

    /* renamed from: b */
    private void m5543b(long j, long j2) {
        m5549a(j, j2);
        synchronized (this.f4999c) {
            this.f4999c.notifyAll();
        }
    }

    /* renamed from: c */
    private boolean m5544c() {
        return Thread.currentThread().isInterrupted() || this.f5003g;
    }

    /* renamed from: d */
    private void m5545d() {
        Throwable e;
        try {
            this.f4997a.mo5534b();
            return;
        } catch (C2180l e2) {
            e = e2;
        } catch (IllegalArgumentException e3) {
            e = e3;
        }
        m5550a(new C2180l("Error closing source " + this.f4997a, e));
    }

    /* renamed from: a */
    public int m5546a(byte[] bArr, long j, int i) {
        String str = "Buffer must be not null!";
        if (bArr == null) {
            throw new NullPointerException(str);
        }
        int i2;
        C2182j.m5592a(j >= 0, "Data offset must be positive!");
        boolean z = i >= 0 && i <= bArr.length;
        C2182j.m5592a(z, "Length must be in range [0..buffer.length]");
        while (!this.f4998b.mo5525d() && ((long) this.f4998b.mo5520a()) < ((long) i) + j && !this.f5003g) {
            m5542b();
            synchronized (this.f4999c) {
                try {
                    this.f4999c.wait(1000);
                } catch (Throwable e) {
                    throw new C2180l("Waiting source data is interrupted!", e);
                }
            }
            i2 = this.f5001e.get();
            if (i2 >= 1) {
                this.f5001e.set(0);
                throw new C2180l("Error reading source " + i2 + " times");
            }
        }
        i2 = this.f4998b.mo5521a(bArr, j, i);
        if (this.f4998b.mo5525d() && this.f5004h != 100) {
            this.f5004h = 100;
            mo5529a(100);
        }
        return i2;
    }

    /* renamed from: a */
    public void m5547a() {
        synchronized (this.f5000d) {
            Log.d("ProxyCache", "Shutdown proxy for " + this.f4997a);
            try {
                this.f5003g = true;
                if (this.f5002f != null) {
                    this.f5002f.interrupt();
                }
                this.f4998b.mo5523b();
            } catch (Throwable e) {
                m5550a(e);
            }
        }
    }

    /* renamed from: a */
    protected void mo5529a(int i) {
    }

    /* renamed from: a */
    protected void m5549a(long j, long j2) {
        Object obj = 1;
        int i = ((j2 > 0 ? 1 : (j2 == 0 ? 0 : -1)) == 0 ? 1 : null) != null ? 100 : (int) ((100 * j) / j2);
        Object obj2 = i != this.f5004h ? 1 : null;
        if (j2 < 0) {
            obj = null;
        }
        if (!(obj == null || obj2 == null)) {
            mo5529a(i);
        }
        this.f5004h = i;
    }

    /* renamed from: a */
    protected final void m5550a(Throwable th) {
        if (th instanceof C2181i) {
            Log.d("ProxyCache", "ProxyCache is interrupted");
        } else {
            Log.e("ProxyCache", "ProxyCache error", th);
        }
    }
}
