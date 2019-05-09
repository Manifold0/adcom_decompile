package com.tapjoy.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.nio.charset.Charset;

public final class hu implements hv, hw, Cloneable {
    /* renamed from: c */
    private static final byte[] f8181c = new byte[]{(byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102};
    /* renamed from: a */
    ib f8182a;
    /* renamed from: b */
    long f8183b;

    /* renamed from: b */
    public final /* synthetic */ hv mo6343b(hx hxVar) {
        return m8106a(hxVar);
    }

    /* renamed from: b */
    public final /* synthetic */ hv mo6344b(String str) {
        return m8107a(str);
    }

    public final /* synthetic */ Object clone() {
        return m8130h();
    }

    /* renamed from: d */
    public final /* synthetic */ hv mo6350d(int i) {
        return m8113b(i);
    }

    /* renamed from: e */
    public final /* synthetic */ hv mo6353e(int i) {
        return m8105a(i);
    }

    /* renamed from: f */
    public final /* synthetic */ hv mo6355f(long j) {
        return m8125e(j);
    }

    /* renamed from: a */
    public final hv mo6339a() {
        return this;
    }

    /* renamed from: b */
    public final boolean mo6346b() {
        return this.f8183b == 0;
    }

    /* renamed from: a */
    public final void mo6340a(long j) {
        if (this.f8183b < j) {
            throw new EOFException();
        }
    }

    /* renamed from: c */
    public final byte mo6347c() {
        if (this.f8183b == 0) {
            throw new IllegalStateException("size == 0");
        }
        ib ibVar = this.f8182a;
        int i = ibVar.f8204b;
        int i2 = ibVar.f8205c;
        int i3 = i + 1;
        byte b = ibVar.f8203a[i];
        this.f8183b--;
        if (i3 == i2) {
            this.f8182a = ibVar.m8167a();
            ic.m8171a(ibVar);
        } else {
            ibVar.f8204b = i3;
        }
        return b;
    }

    /* renamed from: d */
    public final int m8121d() {
        if (this.f8183b < 4) {
            throw new IllegalStateException("size < 4: " + this.f8183b);
        }
        ib ibVar = this.f8182a;
        int i = ibVar.f8204b;
        int i2 = ibVar.f8205c;
        if (i2 - i < 4) {
            return ((((mo6347c() & 255) << 24) | ((mo6347c() & 255) << 16)) | ((mo6347c() & 255) << 8)) | (mo6347c() & 255);
        }
        byte[] bArr = ibVar.f8203a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        i = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
        i3 = i4 + 1;
        i |= (bArr[i4] & 255) << 8;
        i4 = i3 + 1;
        i |= bArr[i3] & 255;
        this.f8183b -= 4;
        if (i4 == i2) {
            this.f8182a = ibVar.m8167a();
            ic.m8171a(ibVar);
            return i;
        }
        ibVar.f8204b = i4;
        return i;
    }

    /* renamed from: e */
    public final int mo6352e() {
        return ih.m8184a(m8121d());
    }

    /* renamed from: b */
    public final hx mo6345b(long j) {
        return new hx(m8104g(j));
    }

    /* renamed from: c */
    public final String mo6348c(long j) {
        Charset charset = ih.f8217a;
        ih.m8186a(this.f8183b, 0, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        } else if (j == 0) {
            return "";
        } else {
            ib ibVar = this.f8182a;
            if (((long) ibVar.f8204b) + j > ((long) ibVar.f8205c)) {
                return new String(m8104g(j), charset);
            }
            String str = new String(ibVar.f8203a, ibVar.f8204b, (int) j, charset);
            ibVar.f8204b = (int) (((long) ibVar.f8204b) + j);
            this.f8183b -= j;
            if (ibVar.f8204b != ibVar.f8205c) {
                return str;
            }
            this.f8182a = ibVar.m8167a();
            ic.m8171a(ibVar);
            return str;
        }
    }

    /* renamed from: g */
    public final byte[] m8129g() {
        try {
            return m8104g(this.f8183b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: g */
    private byte[] m8104g(long j) {
        ih.m8186a(this.f8183b, 0, j);
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        byte[] bArr = new byte[((int) j)];
        m8103a(bArr);
        return bArr;
    }

    /* renamed from: a */
    private void m8103a(byte[] bArr) {
        int i = 0;
        while (i < bArr.length) {
            int i2;
            int length = bArr.length - i;
            ih.m8186a((long) bArr.length, (long) i, (long) length);
            ib ibVar = this.f8182a;
            if (ibVar == null) {
                i2 = -1;
            } else {
                i2 = Math.min(length, ibVar.f8205c - ibVar.f8204b);
                System.arraycopy(ibVar.f8203a, ibVar.f8204b, bArr, i, i2);
                ibVar.f8204b += i2;
                this.f8183b -= (long) i2;
                if (ibVar.f8204b == ibVar.f8205c) {
                    this.f8182a = ibVar.m8167a();
                    ic.m8171a(ibVar);
                }
            }
            if (i2 == -1) {
                throw new EOFException();
            }
            i = i2 + i;
        }
    }

    /* renamed from: d */
    public final void mo6351d(long j) {
        while (j > 0) {
            if (this.f8182a == null) {
                throw new EOFException();
            }
            int min = (int) Math.min(j, (long) (this.f8182a.f8205c - this.f8182a.f8204b));
            this.f8183b -= (long) min;
            j -= (long) min;
            ib ibVar = this.f8182a;
            ibVar.f8204b = min + ibVar.f8204b;
            if (this.f8182a.f8204b == this.f8182a.f8205c) {
                ib ibVar2 = this.f8182a;
                this.f8182a = ibVar2.m8167a();
                ic.m8171a(ibVar2);
            }
        }
    }

    /* renamed from: a */
    public final hu m8106a(hx hxVar) {
        if (hxVar == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        hxVar.mo6360a(this);
        return this;
    }

    /* renamed from: a */
    public final hu m8107a(String str) {
        int length = str.length();
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (length < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + length + " < 0");
        } else if (length > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + length + " > " + str.length());
        } else {
            int i = 0;
            while (i < length) {
                char charAt = str.charAt(i);
                int i2;
                if (charAt < '') {
                    ib c = m8119c(1);
                    byte[] bArr = c.f8203a;
                    int i3 = c.f8205c - i;
                    int min = Math.min(length, 8192 - i3);
                    i2 = i + 1;
                    bArr[i + i3] = (byte) charAt;
                    while (i2 < min) {
                        charAt = str.charAt(i2);
                        if (charAt >= '') {
                            break;
                        }
                        i = i2 + 1;
                        bArr[i2 + i3] = (byte) charAt;
                        i2 = i;
                    }
                    i = (i2 + i3) - c.f8205c;
                    c.f8205c += i;
                    this.f8183b += (long) i;
                    i = i2;
                } else if (charAt < 'ࠀ') {
                    m8105a((charAt >> 6) | 192);
                    m8105a((charAt & 63) | 128);
                    i++;
                } else if (charAt < '?' || charAt > '?') {
                    m8105a((charAt >> 12) | 224);
                    m8105a(((charAt >> 6) & 63) | 128);
                    m8105a((charAt & 63) | 128);
                    i++;
                } else {
                    if (i + 1 < length) {
                        i2 = str.charAt(i + 1);
                    } else {
                        i2 = 0;
                    }
                    if (charAt > '?' || i2 < 56320 || i2 > 57343) {
                        m8105a(63);
                        i++;
                    } else {
                        i2 = ((i2 & -56321) | ((charAt & -55297) << 10)) + 65536;
                        m8105a((i2 >> 18) | 240);
                        m8105a(((i2 >> 12) & 63) | 128);
                        m8105a(((i2 >> 6) & 63) | 128);
                        m8105a((i2 & 63) | 128);
                        i += 2;
                    }
                }
            }
            return this;
        }
    }

    /* renamed from: a */
    public final hu m8108a(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        ih.m8186a((long) bArr.length, 0, (long) i2);
        int i3 = i2 + 0;
        while (i < i3) {
            ib c = m8119c(1);
            int min = Math.min(i3 - i, 8192 - c.f8205c);
            System.arraycopy(bArr, i, c.f8203a, c.f8205c, min);
            i += min;
            c.f8205c = min + c.f8205c;
        }
        this.f8183b += (long) i2;
        return this;
    }

    /* renamed from: a */
    public final hu m8105a(int i) {
        ib c = m8119c(1);
        byte[] bArr = c.f8203a;
        int i2 = c.f8205c;
        c.f8205c = i2 + 1;
        bArr[i2] = (byte) i;
        this.f8183b++;
        return this;
    }

    /* renamed from: b */
    public final hu m8113b(int i) {
        int a = ih.m8184a(i);
        ib c = m8119c(4);
        byte[] bArr = c.f8203a;
        int i2 = c.f8205c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((a >>> 24) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) ((a >>> 16) & 255);
        i3 = i2 + 1;
        bArr[i2] = (byte) ((a >>> 8) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) (a & 255);
        c.f8205c = i2;
        this.f8183b += 4;
        return this;
    }

    /* renamed from: e */
    public final hu m8125e(long j) {
        long a = ih.m8185a(j);
        ib c = m8119c(8);
        byte[] bArr = c.f8203a;
        int i = c.f8205c;
        int i2 = i + 1;
        bArr[i] = (byte) ((int) ((a >>> 56) & 255));
        i = i2 + 1;
        bArr[i2] = (byte) ((int) ((a >>> 48) & 255));
        i2 = i + 1;
        bArr[i] = (byte) ((int) ((a >>> 40) & 255));
        i = i2 + 1;
        bArr[i2] = (byte) ((int) ((a >>> 32) & 255));
        i2 = i + 1;
        bArr[i] = (byte) ((int) ((a >>> 24) & 255));
        i = i2 + 1;
        bArr[i2] = (byte) ((int) ((a >>> 16) & 255));
        i2 = i + 1;
        bArr[i] = (byte) ((int) ((a >>> 8) & 255));
        i = i2 + 1;
        bArr[i2] = (byte) ((int) (a & 255));
        c.f8205c = i;
        this.f8183b += 8;
        return this;
    }

    /* renamed from: c */
    final ib m8119c(int i) {
        if (i <= 0 || i > 8192) {
            throw new IllegalArgumentException();
        } else if (this.f8182a == null) {
            this.f8182a = ic.m8170a();
            ib ibVar = this.f8182a;
            ib ibVar2 = this.f8182a;
            r0 = this.f8182a;
            ibVar2.f8209g = r0;
            ibVar.f8208f = r0;
            return r0;
        } else {
            r0 = this.f8182a.f8209g;
            if (r0.f8205c + i > 8192 || !r0.f8207e) {
                return r0.m8168a(ic.m8170a());
            }
            return r0;
        }
    }

    /* renamed from: a */
    public final void mo6341a(hu huVar, long j) {
        if (huVar == null) {
            throw new IllegalArgumentException("source == null");
        } else if (huVar == this) {
            throw new IllegalArgumentException("source == this");
        } else {
            ih.m8186a(huVar.f8183b, 0, j);
            while (j > 0) {
                ib ibVar;
                ib ibVar2;
                if (j < ((long) (huVar.f8182a.f8205c - huVar.f8182a.f8204b))) {
                    ibVar = this.f8182a != null ? this.f8182a.f8209g : null;
                    if (ibVar != null && ibVar.f8207e) {
                        if ((((long) ibVar.f8205c) + j) - ((long) (ibVar.f8206d ? 0 : ibVar.f8204b)) <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                            huVar.f8182a.m8169a(ibVar, (int) j);
                            huVar.f8183b -= j;
                            this.f8183b += j;
                            return;
                        }
                    }
                    ibVar = huVar.f8182a;
                    int i = (int) j;
                    if (i <= 0 || i > ibVar.f8205c - ibVar.f8204b) {
                        throw new IllegalArgumentException();
                    }
                    if (i >= 1024) {
                        ibVar2 = new ib(ibVar);
                    } else {
                        ibVar2 = ic.m8170a();
                        System.arraycopy(ibVar.f8203a, ibVar.f8204b, ibVar2.f8203a, 0, i);
                    }
                    ibVar2.f8205c = ibVar2.f8204b + i;
                    ibVar.f8204b = i + ibVar.f8204b;
                    ibVar.f8209g.m8168a(ibVar2);
                    huVar.f8182a = ibVar2;
                }
                ibVar2 = huVar.f8182a;
                long j2 = (long) (ibVar2.f8205c - ibVar2.f8204b);
                huVar.f8182a = ibVar2.m8167a();
                if (this.f8182a == null) {
                    this.f8182a = ibVar2;
                    ibVar2 = this.f8182a;
                    ibVar = this.f8182a;
                    ib ibVar3 = this.f8182a;
                    ibVar.f8209g = ibVar3;
                    ibVar2.f8208f = ibVar3;
                } else {
                    ibVar = this.f8182a.f8209g.m8168a(ibVar2);
                    if (ibVar.f8209g == ibVar) {
                        throw new IllegalStateException();
                    } else if (ibVar.f8209g.f8207e) {
                        int i2 = ibVar.f8205c - ibVar.f8204b;
                        if (i2 <= (ibVar.f8209g.f8206d ? 0 : ibVar.f8209g.f8204b) + (8192 - ibVar.f8209g.f8205c)) {
                            ibVar.m8169a(ibVar.f8209g, i2);
                            ibVar.m8167a();
                            ic.m8171a(ibVar);
                        }
                    }
                }
                huVar.f8183b -= j2;
                this.f8183b += j2;
                j -= j2;
            }
        }
    }

    /* renamed from: b */
    public final long mo6342b(hu huVar, long j) {
        if (huVar == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.f8183b == 0) {
            return -1;
        } else {
            if (j > this.f8183b) {
                j = this.f8183b;
            }
            huVar.mo6341a(this, j);
            return j;
        }
    }

    public final void flush() {
    }

    public final void close() {
    }

    public final boolean equals(Object o) {
        long j = 0;
        if (this == o) {
            return true;
        }
        if (!(o instanceof hu)) {
            return false;
        }
        hu huVar = (hu) o;
        if (this.f8183b != huVar.f8183b) {
            return false;
        }
        if (this.f8183b == 0) {
            return true;
        }
        ib ibVar = this.f8182a;
        ib ibVar2 = huVar.f8182a;
        int i = ibVar.f8204b;
        int i2 = ibVar2.f8204b;
        while (j < this.f8183b) {
            long min = (long) Math.min(ibVar.f8205c - i, ibVar2.f8205c - i2);
            int i3 = 0;
            while (((long) i3) < min) {
                int i4 = i + 1;
                byte b = ibVar.f8203a[i];
                i = i2 + 1;
                if (b != ibVar2.f8203a[i2]) {
                    return false;
                }
                i3++;
                i2 = i;
                i = i4;
            }
            if (i == ibVar.f8205c) {
                ibVar = ibVar.f8208f;
                i = ibVar.f8204b;
            }
            if (i2 == ibVar2.f8205c) {
                ibVar2 = ibVar2.f8208f;
                i2 = ibVar2.f8204b;
            }
            j += min;
        }
        return true;
    }

    public final int hashCode() {
        ib ibVar = this.f8182a;
        if (ibVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = ibVar.f8204b;
            while (i2 < ibVar.f8205c) {
                int i3 = ibVar.f8203a[i2] + (i * 31);
                i2++;
                i = i3;
            }
            ibVar = ibVar.f8208f;
        } while (ibVar != this.f8182a);
        return i;
    }

    /* renamed from: h */
    public final hu m8130h() {
        hu huVar = new hu();
        if (this.f8183b == 0) {
            return huVar;
        }
        huVar.f8182a = new ib(this.f8182a);
        ib ibVar = huVar.f8182a;
        ib ibVar2 = huVar.f8182a;
        ib ibVar3 = huVar.f8182a;
        ibVar2.f8209g = ibVar3;
        ibVar.f8208f = ibVar3;
        for (ibVar = this.f8182a.f8208f; ibVar != this.f8182a; ibVar = ibVar.f8208f) {
            huVar.f8182a.f8209g.m8168a(new ib(ibVar));
        }
        huVar.f8183b = this.f8183b;
        return huVar;
    }

    /* renamed from: f */
    public final long mo6354f() {
        if (this.f8183b < 8) {
            throw new IllegalStateException("size < 8: " + this.f8183b);
        }
        long d;
        ib ibVar = this.f8182a;
        int i = ibVar.f8204b;
        int i2 = ibVar.f8205c;
        if (i2 - i < 8) {
            d = ((((long) m8121d()) & 4294967295L) << 32) | (((long) m8121d()) & 4294967295L);
        } else {
            byte[] bArr = ibVar.f8203a;
            int i3 = i + 1;
            long j = (((long) bArr[i]) & 255) << 56;
            i = i3 + 1;
            long j2 = ((((long) bArr[i3]) & 255) << 48) | j;
            int i4 = i + 1;
            i = i4 + 1;
            j2 = (j2 | ((((long) bArr[i]) & 255) << 40)) | ((((long) bArr[i4]) & 255) << 32);
            i4 = i + 1;
            i = i4 + 1;
            j2 = (j2 | ((((long) bArr[i]) & 255) << 24)) | ((((long) bArr[i4]) & 255) << 16);
            i4 = i + 1;
            int i5 = i4 + 1;
            d = (((long) bArr[i4]) & 255) | (j2 | ((((long) bArr[i]) & 255) << 8));
            this.f8183b -= 8;
            if (i5 == i2) {
                this.f8182a = ibVar.m8167a();
                ic.m8171a(ibVar);
            } else {
                ibVar.f8204b = i5;
            }
        }
        return ih.m8185a(d);
    }

    public final String toString() {
        if (this.f8183b > 2147483647L) {
            throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.f8183b);
        }
        hx hxVar;
        int i = (int) this.f8183b;
        if (i == 0) {
            hxVar = hx.f8185b;
        } else {
            hxVar = new id(this, i);
        }
        return hxVar.toString();
    }
}
