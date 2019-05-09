package com.tapjoy.internal;

final class hz implements hv {
    /* renamed from: a */
    public final hu f8194a = new hu();
    /* renamed from: b */
    public final ie f8195b;
    /* renamed from: c */
    boolean f8196c;

    hz(ie ieVar) {
        if (ieVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        this.f8195b = ieVar;
    }

    /* renamed from: a */
    public final void mo6341a(hu huVar, long j) {
        if (this.f8196c) {
            throw new IllegalStateException("closed");
        }
        this.f8194a.mo6341a(huVar, j);
        m8146b();
    }

    /* renamed from: b */
    public final hv mo6343b(hx hxVar) {
        if (this.f8196c) {
            throw new IllegalStateException("closed");
        }
        this.f8194a.m8106a(hxVar);
        return m8146b();
    }

    /* renamed from: b */
    public final hv mo6344b(String str) {
        if (this.f8196c) {
            throw new IllegalStateException("closed");
        }
        this.f8194a.m8107a(str);
        return m8146b();
    }

    /* renamed from: e */
    public final hv mo6353e(int i) {
        if (this.f8196c) {
            throw new IllegalStateException("closed");
        }
        this.f8194a.m8105a(i);
        return m8146b();
    }

    /* renamed from: d */
    public final hv mo6350d(int i) {
        if (this.f8196c) {
            throw new IllegalStateException("closed");
        }
        this.f8194a.m8113b(i);
        return m8146b();
    }

    /* renamed from: f */
    public final hv mo6355f(long j) {
        if (this.f8196c) {
            throw new IllegalStateException("closed");
        }
        this.f8194a.m8125e(j);
        return m8146b();
    }

    /* renamed from: b */
    private hv m8146b() {
        if (this.f8196c) {
            throw new IllegalStateException("closed");
        }
        hu huVar = this.f8194a;
        long j = huVar.f8183b;
        if (j == 0) {
            j = 0;
        } else {
            ib ibVar = huVar.f8182a.f8209g;
            if (ibVar.f8205c < 8192 && ibVar.f8207e) {
                j -= (long) (ibVar.f8205c - ibVar.f8204b);
            }
        }
        if (j > 0) {
            this.f8195b.mo6341a(this.f8194a, j);
        }
        return this;
    }

    /* renamed from: a */
    public final hv mo6339a() {
        if (this.f8196c) {
            throw new IllegalStateException("closed");
        }
        long j = this.f8194a.f8183b;
        if (j > 0) {
            this.f8195b.mo6341a(this.f8194a, j);
        }
        return this;
    }

    public final void flush() {
        if (this.f8196c) {
            throw new IllegalStateException("closed");
        }
        if (this.f8194a.f8183b > 0) {
            this.f8195b.mo6341a(this.f8194a, this.f8194a.f8183b);
        }
        this.f8195b.flush();
    }

    public final void close() {
        if (!this.f8196c) {
            Throwable th = null;
            try {
                if (this.f8194a.f8183b > 0) {
                    this.f8195b.mo6341a(this.f8194a, this.f8194a.f8183b);
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.f8195b.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            this.f8196c = true;
            if (th != null) {
                ih.m8187a(th);
            }
        }
    }

    public final String toString() {
        return "buffer(" + this.f8195b + ")";
    }
}
