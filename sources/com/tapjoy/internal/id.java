package com.tapjoy.internal;

import java.util.Arrays;

final class id extends hx {
    /* renamed from: f */
    final transient byte[][] f8212f;
    /* renamed from: g */
    final transient int[] f8213g;

    id(hu huVar, int i) {
        int i2 = 0;
        super(null);
        ih.m8186a(huVar.f8183b, 0, (long) i);
        ib ibVar = huVar.f8182a;
        int i3 = 0;
        int i4 = 0;
        while (i4 < i) {
            if (ibVar.f8205c == ibVar.f8204b) {
                throw new AssertionError("s.limit == s.pos");
            }
            i4 += ibVar.f8205c - ibVar.f8204b;
            i3++;
            ibVar = ibVar.f8208f;
        }
        this.f8212f = new byte[i3][];
        this.f8213g = new int[(i3 * 2)];
        ib ibVar2 = huVar.f8182a;
        i4 = 0;
        while (i2 < i) {
            this.f8212f[i4] = ibVar2.f8203a;
            int i5 = (ibVar2.f8205c - ibVar2.f8204b) + i2;
            if (i5 > i) {
                i5 = i;
            }
            this.f8213g[i4] = i5;
            this.f8213g[this.f8212f.length + i4] = ibVar2.f8204b;
            ibVar2.f8206d = true;
            i4++;
            ibVar2 = ibVar2.f8208f;
            i2 = i5;
        }
    }

    /* renamed from: a */
    public final String mo6359a() {
        return m8173e().mo6359a();
    }

    /* renamed from: b */
    public final String mo6362b() {
        return m8173e().mo6362b();
    }

    /* renamed from: a */
    public final hx mo6358a(int i, int i2) {
        return m8173e().mo6358a(i, i2);
    }

    /* renamed from: a */
    public final byte mo6357a(int i) {
        ih.m8186a((long) this.f8213g[this.f8212f.length - 1], (long) i, 1);
        int b = m8172b(i);
        return this.f8212f[b][(i - (b == 0 ? 0 : this.f8213g[b - 1])) + this.f8213g[this.f8212f.length + b]];
    }

    /* renamed from: b */
    private int m8172b(int i) {
        int binarySearch = Arrays.binarySearch(this.f8213g, 0, this.f8212f.length, i + 1);
        return binarySearch >= 0 ? binarySearch : binarySearch ^ -1;
    }

    /* renamed from: c */
    public final int mo6363c() {
        return this.f8213g[this.f8212f.length - 1];
    }

    /* renamed from: d */
    public final byte[] mo6364d() {
        int i = 0;
        Object obj = new byte[this.f8213g[this.f8212f.length - 1]];
        int length = this.f8212f.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.f8213g[length + i];
            int i4 = this.f8213g[i];
            System.arraycopy(this.f8212f[i], i3, obj, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return obj;
    }

    /* renamed from: a */
    final void mo6360a(hu huVar) {
        int i = 0;
        int length = this.f8212f.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.f8213g[length + i];
            int i4 = this.f8213g[i];
            ib ibVar = new ib(this.f8212f[i], i3, (i3 + i4) - i2);
            if (huVar.f8182a == null) {
                ibVar.f8209g = ibVar;
                ibVar.f8208f = ibVar;
                huVar.f8182a = ibVar;
            } else {
                huVar.f8182a.f8209g.m8168a(ibVar);
            }
            i++;
            i2 = i4;
        }
        huVar.f8183b = ((long) i2) + huVar.f8183b;
    }

    /* renamed from: a */
    public final boolean mo6361a(int i, byte[] bArr, int i2, int i3) {
        if (i < 0 || i > mo6363c() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int b = m8172b(i);
        while (i3 > 0) {
            int i4 = b == 0 ? 0 : this.f8213g[b - 1];
            int min = Math.min(i3, ((this.f8213g[b] - i4) + i4) - i);
            if (!ih.m8188a(this.f8212f[b], (i - i4) + this.f8213g[this.f8212f.length + b], bArr, i2, min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            b++;
        }
        return true;
    }

    /* renamed from: e */
    private hx m8173e() {
        return new hx(mo6364d());
    }

    public final boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof hx) || ((hx) o).mo6363c() != mo6363c()) {
            return false;
        }
        boolean z;
        hx hxVar = (hx) o;
        int c = mo6363c();
        if (mo6363c() - c < 0) {
            z = false;
        } else {
            int i = c;
            int i2 = 0;
            int i3 = 0;
            c = m8172b(0);
            while (i > 0) {
                int i4 = c == 0 ? 0 : this.f8213g[c - 1];
                int min = Math.min(i, ((this.f8213g[c] - i4) + i4) - i3);
                if (!hxVar.mo6361a(i2, this.f8212f[c], (i3 - i4) + this.f8213g[this.f8212f.length + c], min)) {
                    z = false;
                    break;
                }
                i3 += min;
                i2 += min;
                i -= min;
                c++;
            }
            z = true;
        }
        if (z) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.d;
        if (i == 0) {
            i = 1;
            int length = this.f8212f.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                byte[] bArr = this.f8212f[i2];
                int i4 = this.f8213g[length + i2];
                int i5 = this.f8213g[i2];
                i3 = (i5 - i3) + i4;
                int i6 = i4;
                i4 = i;
                for (i = i6; i < i3; i++) {
                    i4 = (i4 * 31) + bArr[i];
                }
                i2++;
                i3 = i5;
                i = i4;
            }
            this.d = i;
        }
        return i;
    }

    public final String toString() {
        return m8173e().toString();
    }
}
