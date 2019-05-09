package com.tapjoy.internal;

import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.tapjoy.internal.di.C2861a;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;

public abstract class df implements di {
    /* renamed from: a */
    private final ReentrantLock f7310a = new ReentrantLock();
    /* renamed from: b */
    private final C2859a f7311b = new C2859a();
    /* renamed from: c */
    private final C2859a f7312c = new C2859a();
    /* renamed from: d */
    private C2861a f7313d = C2861a.NEW;
    /* renamed from: e */
    private boolean f7314e = false;

    /* renamed from: com.tapjoy.internal.df$a */
    class C2859a extends de {
        /* renamed from: a */
        final /* synthetic */ df f7323a;

        private C2859a(df dfVar) {
            this.f7323a = dfVar;
        }

        public final /* synthetic */ Object get(long x0, TimeUnit x1) {
            return m7383a(x0, x1);
        }

        /* renamed from: a */
        private C2861a m7383a(long j, TimeUnit timeUnit) {
            try {
                return (C2861a) super.get(j, timeUnit);
            } catch (TimeoutException e) {
                throw new TimeoutException(this.f7323a.toString());
            }
        }
    }

    /* renamed from: a */
    protected abstract void mo6210a();

    /* renamed from: b */
    protected abstract void mo6211b();

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: e */
    public final com.tapjoy.internal.dh mo6208e() {
        /*
        r2 = this;
        r0 = r2.f7310a;
        r0.lock();
        r0 = r2.f7313d;	 Catch:{ Throwable -> 0x001a }
        r1 = com.tapjoy.internal.di.C2861a.NEW;	 Catch:{ Throwable -> 0x001a }
        if (r0 != r1) goto L_0x0012;
    L_0x000b:
        r0 = com.tapjoy.internal.di.C2861a.STARTING;	 Catch:{ Throwable -> 0x001a }
        r2.f7313d = r0;	 Catch:{ Throwable -> 0x001a }
        r2.mo6210a();	 Catch:{ Throwable -> 0x001a }
    L_0x0012:
        r0 = r2.f7310a;
        r0.unlock();
    L_0x0017:
        r0 = r2.f7311b;
        return r0;
    L_0x001a:
        r0 = move-exception;
        r2.m7362a(r0);	 Catch:{ all -> 0x0024 }
        r0 = r2.f7310a;
        r0.unlock();
        goto L_0x0017;
    L_0x0024:
        r0 = move-exception;
        r1 = r2.f7310a;
        r1.unlock();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.df.e():com.tapjoy.internal.dh");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: g */
    private com.tapjoy.internal.dh m7360g() {
        /*
        r2 = this;
        r0 = r2.f7310a;
        r0.lock();
        r0 = r2.f7313d;	 Catch:{ Throwable -> 0x0036 }
        r1 = com.tapjoy.internal.di.C2861a.NEW;	 Catch:{ Throwable -> 0x0036 }
        if (r0 != r1) goto L_0x0025;
    L_0x000b:
        r0 = com.tapjoy.internal.di.C2861a.TERMINATED;	 Catch:{ Throwable -> 0x0036 }
        r2.f7313d = r0;	 Catch:{ Throwable -> 0x0036 }
        r0 = r2.f7311b;	 Catch:{ Throwable -> 0x0036 }
        r1 = com.tapjoy.internal.di.C2861a.TERMINATED;	 Catch:{ Throwable -> 0x0036 }
        r0.m7381a(r1);	 Catch:{ Throwable -> 0x0036 }
        r0 = r2.f7312c;	 Catch:{ Throwable -> 0x0036 }
        r1 = com.tapjoy.internal.di.C2861a.TERMINATED;	 Catch:{ Throwable -> 0x0036 }
        r0.m7381a(r1);	 Catch:{ Throwable -> 0x0036 }
    L_0x001d:
        r0 = r2.f7310a;
        r0.unlock();
    L_0x0022:
        r0 = r2.f7312c;
        return r0;
    L_0x0025:
        r0 = r2.f7313d;	 Catch:{ Throwable -> 0x0036 }
        r1 = com.tapjoy.internal.di.C2861a.STARTING;	 Catch:{ Throwable -> 0x0036 }
        if (r0 != r1) goto L_0x0040;
    L_0x002b:
        r0 = 1;
        r2.f7314e = r0;	 Catch:{ Throwable -> 0x0036 }
        r0 = r2.f7311b;	 Catch:{ Throwable -> 0x0036 }
        r1 = com.tapjoy.internal.di.C2861a.STOPPING;	 Catch:{ Throwable -> 0x0036 }
        r0.m7381a(r1);	 Catch:{ Throwable -> 0x0036 }
        goto L_0x001d;
    L_0x0036:
        r0 = move-exception;
        r2.m7362a(r0);	 Catch:{ all -> 0x004e }
        r0 = r2.f7310a;
        r0.unlock();
        goto L_0x0022;
    L_0x0040:
        r0 = r2.f7313d;	 Catch:{ Throwable -> 0x0036 }
        r1 = com.tapjoy.internal.di.C2861a.RUNNING;	 Catch:{ Throwable -> 0x0036 }
        if (r0 != r1) goto L_0x001d;
    L_0x0046:
        r0 = com.tapjoy.internal.di.C2861a.STOPPING;	 Catch:{ Throwable -> 0x0036 }
        r2.f7313d = r0;	 Catch:{ Throwable -> 0x0036 }
        r2.mo6211b();	 Catch:{ Throwable -> 0x0036 }
        goto L_0x001d;
    L_0x004e:
        r0 = move-exception;
        r1 = r2.f7310a;
        r1.unlock();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.df.g():com.tapjoy.internal.dh");
    }

    /* renamed from: c */
    protected final void m7364c() {
        this.f7310a.lock();
        try {
            if (this.f7313d != C2861a.STARTING) {
                Throwable illegalStateException = new IllegalStateException("Cannot notifyStarted() when the service is " + this.f7313d);
                m7362a(illegalStateException);
                throw illegalStateException;
            }
            this.f7313d = C2861a.RUNNING;
            if (this.f7314e) {
                m7360g();
            } else {
                this.f7311b.m7381a((Object) C2861a.RUNNING);
            }
            this.f7310a.unlock();
        } catch (Throwable th) {
            this.f7310a.unlock();
        }
    }

    /* renamed from: d */
    protected final void m7365d() {
        this.f7310a.lock();
        try {
            if (this.f7313d == C2861a.STOPPING || this.f7313d == C2861a.RUNNING) {
                this.f7313d = C2861a.TERMINATED;
                this.f7312c.m7381a((Object) C2861a.TERMINATED);
                return;
            }
            Throwable illegalStateException = new IllegalStateException("Cannot notifyStopped() when the service is " + this.f7313d);
            m7362a(illegalStateException);
            throw illegalStateException;
        } finally {
            this.f7310a.unlock();
        }
    }

    /* renamed from: a */
    protected final void m7362a(Throwable th) {
        cs.m7336a(th);
        this.f7310a.lock();
        try {
            if (this.f7313d == C2861a.STARTING) {
                this.f7311b.m7382a(th);
                this.f7312c.m7382a(new Exception("Service failed to start.", th));
            } else if (this.f7313d == C2861a.STOPPING) {
                this.f7312c.m7382a(th);
            } else if (this.f7313d == C2861a.RUNNING) {
                this.f7312c.m7382a(new Exception("Service failed while running", th));
            } else if (this.f7313d == C2861a.NEW || this.f7313d == C2861a.TERMINATED) {
                throw new IllegalStateException("Failed while in state:" + this.f7313d, th);
            }
            this.f7313d = C2861a.FAILED;
        } finally {
            this.f7310a.unlock();
        }
    }

    /* renamed from: f */
    public final C2861a mo6209f() {
        this.f7310a.lock();
        try {
            C2861a c2861a;
            if (this.f7314e && this.f7313d == C2861a.STARTING) {
                c2861a = C2861a.STOPPING;
                return c2861a;
            }
            c2861a = this.f7313d;
            this.f7310a.unlock();
            return c2861a;
        } finally {
            this.f7310a.unlock();
        }
    }

    public String toString() {
        return getClass().getSimpleName() + " [" + mo6209f() + RequestParameters.RIGHT_BRACKETS;
    }
}
