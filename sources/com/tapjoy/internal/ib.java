package com.tapjoy.internal;

final class ib {
    /* renamed from: a */
    final byte[] f8203a;
    /* renamed from: b */
    int f8204b;
    /* renamed from: c */
    int f8205c;
    /* renamed from: d */
    boolean f8206d;
    /* renamed from: e */
    boolean f8207e;
    /* renamed from: f */
    ib f8208f;
    /* renamed from: g */
    ib f8209g;

    ib() {
        this.f8203a = new byte[8192];
        this.f8207e = true;
        this.f8206d = false;
    }

    ib(ib ibVar) {
        this(ibVar.f8203a, ibVar.f8204b, ibVar.f8205c);
        ibVar.f8206d = true;
    }

    ib(byte[] bArr, int i, int i2) {
        this.f8203a = bArr;
        this.f8204b = i;
        this.f8205c = i2;
        this.f8207e = false;
        this.f8206d = true;
    }

    /* renamed from: a */
    public final ib m8167a() {
        ib ibVar = this.f8208f != this ? this.f8208f : null;
        this.f8209g.f8208f = this.f8208f;
        this.f8208f.f8209g = this.f8209g;
        this.f8208f = null;
        this.f8209g = null;
        return ibVar;
    }

    /* renamed from: a */
    public final ib m8168a(ib ibVar) {
        ibVar.f8209g = this;
        ibVar.f8208f = this.f8208f;
        this.f8208f.f8209g = ibVar;
        this.f8208f = ibVar;
        return ibVar;
    }

    /* renamed from: a */
    public final void m8169a(ib ibVar, int i) {
        if (ibVar.f8207e) {
            if (ibVar.f8205c + i > 8192) {
                if (ibVar.f8206d) {
                    throw new IllegalArgumentException();
                } else if ((ibVar.f8205c + i) - ibVar.f8204b > 8192) {
                    throw new IllegalArgumentException();
                } else {
                    System.arraycopy(ibVar.f8203a, ibVar.f8204b, ibVar.f8203a, 0, ibVar.f8205c - ibVar.f8204b);
                    ibVar.f8205c -= ibVar.f8204b;
                    ibVar.f8204b = 0;
                }
            }
            System.arraycopy(this.f8203a, this.f8204b, ibVar.f8203a, ibVar.f8205c, i);
            ibVar.f8205c += i;
            this.f8204b += i;
            return;
        }
        throw new IllegalArgumentException();
    }
}
