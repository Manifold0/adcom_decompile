package com.tapjoy.internal;

public final class dp {
    /* renamed from: a */
    final hv f7377a;

    /* renamed from: a */
    static int m7465a(int i, dk dkVar) {
        return (i << 3) | dkVar.f7360e;
    }

    /* renamed from: a */
    static int m7464a(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        if ((-268435456 & i) == 0) {
            return 4;
        }
        return 5;
    }

    /* renamed from: a */
    static int m7466a(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if ((-16384 & j) == 0) {
            return 2;
        }
        if ((-2097152 & j) == 0) {
            return 3;
        }
        if ((-268435456 & j) == 0) {
            return 4;
        }
        if ((-34359738368L & j) == 0) {
            return 5;
        }
        if ((-4398046511104L & j) == 0) {
            return 6;
        }
        if ((-562949953421312L & j) == 0) {
            return 7;
        }
        if ((-72057594037927936L & j) == 0) {
            return 8;
        }
        if ((Long.MIN_VALUE & j) == 0) {
            return 9;
        }
        return 10;
    }

    /* renamed from: b */
    static int m7467b(int i) {
        return (i << 1) ^ (i >> 31);
    }

    /* renamed from: b */
    static long m7468b(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public dp(hv hvVar) {
        this.f7377a = hvVar;
    }

    /* renamed from: a */
    public final void m7469a(hx hxVar) {
        this.f7377a.mo6343b(hxVar);
    }

    /* renamed from: c */
    public final void m7470c(int i) {
        while ((i & -128) != 0) {
            this.f7377a.mo6353e((i & 127) | 128);
            i >>>= 7;
        }
        this.f7377a.mo6353e(i);
    }

    /* renamed from: c */
    public final void m7471c(long j) {
        while ((-128 & j) != 0) {
            this.f7377a.mo6353e((((int) j) & 127) | 128);
            j >>>= 7;
        }
        this.f7377a.mo6353e((int) j);
    }

    /* renamed from: d */
    public final void m7472d(int i) {
        this.f7377a.mo6350d(i);
    }

    /* renamed from: d */
    public final void m7473d(long j) {
        this.f7377a.mo6355f(j);
    }
}
