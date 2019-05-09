package com.tapjoy.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.support.v4.app.FragmentTransaction;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class hf {
    /* renamed from: d */
    private static final String f8063d = hf.class.getSimpleName();
    /* renamed from: a */
    int f8064a;
    /* renamed from: b */
    int f8065b;
    /* renamed from: c */
    hh f8066c;
    /* renamed from: e */
    private int[] f8067e;
    /* renamed from: f */
    private final int[] f8068f;
    /* renamed from: g */
    private ByteBuffer f8069g;
    /* renamed from: h */
    private byte[] f8070h;
    /* renamed from: i */
    private byte[] f8071i;
    /* renamed from: j */
    private int f8072j;
    /* renamed from: k */
    private int f8073k;
    /* renamed from: l */
    private hi f8074l;
    /* renamed from: m */
    private short[] f8075m;
    /* renamed from: n */
    private byte[] f8076n;
    /* renamed from: o */
    private byte[] f8077o;
    /* renamed from: p */
    private byte[] f8078p;
    /* renamed from: q */
    private int[] f8079q;
    /* renamed from: r */
    private C2973a f8080r;
    /* renamed from: s */
    private Bitmap f8081s;
    /* renamed from: t */
    private boolean f8082t;
    /* renamed from: u */
    private int f8083u;
    /* renamed from: v */
    private int f8084v;
    /* renamed from: w */
    private int f8085w;
    /* renamed from: x */
    private int f8086x;
    /* renamed from: y */
    private boolean f8087y;

    /* renamed from: com.tapjoy.internal.hf$a */
    interface C2973a {
        /* renamed from: a */
        Bitmap mo6330a(int i, int i2, Config config);

        /* renamed from: a */
        byte[] mo6331a(int i);

        /* renamed from: b */
        int[] mo6332b(int i);
    }

    hf(C2973a c2973a, hh hhVar, ByteBuffer byteBuffer) {
        this(c2973a, hhVar, byteBuffer, (byte) 0);
    }

    private hf(C2973a c2973a, hh hhVar, ByteBuffer byteBuffer, byte b) {
        this(c2973a);
        m8037b(hhVar, byteBuffer);
    }

    private hf(C2973a c2973a) {
        this.f8068f = new int[256];
        this.f8072j = 0;
        this.f8073k = 0;
        this.f8080r = c2973a;
        this.f8066c = new hh();
    }

    hf() {
        this(new hk());
    }

    /* renamed from: a */
    final synchronized Bitmap m8042a() {
        Bitmap bitmap;
        if (this.f8066c.f8101c <= 0 || this.f8064a < 0) {
            Object[] objArr = new Object[]{Integer.valueOf(this.f8066c.f8101c), Integer.valueOf(this.f8064a)};
            this.f8083u = 1;
        }
        if (this.f8083u == 1 || this.f8083u == 2) {
            new Object[1][0] = Integer.valueOf(this.f8083u);
            bitmap = null;
        } else {
            hg hgVar;
            this.f8083u = 0;
            hg hgVar2 = (hg) this.f8066c.f8103e.get(this.f8064a);
            int i = this.f8064a - 1;
            if (i >= 0) {
                hgVar = (hg) this.f8066c.f8103e.get(i);
            } else {
                hgVar = null;
            }
            this.f8067e = hgVar2.f8098k != null ? hgVar2.f8098k : this.f8066c.f8099a;
            if (this.f8067e == null) {
                new Object[1][0] = Integer.valueOf(this.f8064a);
                this.f8083u = 1;
                bitmap = null;
            } else {
                int i2;
                int i3;
                int i4;
                int i5;
                int i6;
                int i7;
                if (hgVar2.f8093f) {
                    System.arraycopy(this.f8067e, 0, this.f8068f, 0, this.f8067e.length);
                    this.f8067e = this.f8068f;
                    this.f8067e[hgVar2.f8095h] = 0;
                }
                int[] iArr = this.f8079q;
                if (hgVar == null) {
                    Arrays.fill(iArr, 0);
                }
                if (hgVar != null && hgVar.f8094g > 0) {
                    if (hgVar.f8094g == 2) {
                        i2 = 0;
                        if (!hgVar2.f8093f) {
                            i2 = this.f8066c.f8110l;
                            if (hgVar2.f8098k != null && this.f8066c.f8108j == hgVar2.f8095h) {
                                i2 = 0;
                            }
                        } else if (this.f8064a == 0) {
                            this.f8087y = true;
                        }
                        m8035a(iArr, hgVar, i2);
                    } else if (hgVar.f8094g == 3) {
                        if (this.f8081s == null) {
                            m8035a(iArr, hgVar, 0);
                        } else {
                            i3 = hgVar.f8089b / this.f8084v;
                            i4 = hgVar.f8088a / this.f8084v;
                            this.f8081s.getPixels(iArr, (this.f8086x * i3) + i4, this.f8086x, i4, i3, hgVar.f8090c / this.f8084v, hgVar.f8091d / this.f8084v);
                        }
                    }
                }
                this.f8072j = 0;
                this.f8073k = 0;
                if (hgVar2 != null) {
                    this.f8069g.position(hgVar2.f8097j);
                }
                if (hgVar2 == null) {
                    i5 = this.f8066c.f8104f * this.f8066c.f8105g;
                } else {
                    i5 = hgVar2.f8090c * hgVar2.f8091d;
                }
                if (this.f8078p == null || this.f8078p.length < i5) {
                    this.f8078p = this.f8080r.mo6331a(i5);
                }
                if (this.f8075m == null) {
                    this.f8075m = new short[4096];
                }
                if (this.f8076n == null) {
                    this.f8076n = new byte[4096];
                }
                if (this.f8077o == null) {
                    this.f8077o = new byte[FragmentTransaction.TRANSIT_FRAGMENT_OPEN];
                }
                int c = m8038c();
                int i8 = 1 << c;
                int i9 = i8 + 1;
                int i10 = i8 + 2;
                int i11 = -1;
                i2 = c + 1;
                int i12 = (1 << i2) - 1;
                for (i4 = 0; i4 < i8; i4++) {
                    this.f8075m[i4] = (short) 0;
                    this.f8076n[i4] = (byte) i4;
                }
                int i13 = 0;
                int i14 = 0;
                int i15 = 0;
                int i16 = 0;
                int i17 = 0;
                i3 = i12;
                int i18 = i10;
                i12 = 0;
                i10 = 0;
                i4 = i2;
                i2 = 0;
                while (i13 < i5) {
                    if (i10 == 0) {
                        i10 = m8039d();
                        if (i10 <= 0) {
                            this.f8083u = 3;
                            break;
                        }
                        i6 = 0;
                        i7 = i10;
                    } else {
                        i6 = i12;
                        i7 = i10;
                    }
                    i12 = ((this.f8070h[i6] & 255) << i17) + i16;
                    i10 = i17 + 8;
                    i16 = i6 + 1;
                    i17 = i7 - 1;
                    i7 = i10;
                    i10 = i18;
                    i18 = i15;
                    int i19 = i2;
                    i2 = i4;
                    i4 = i19;
                    int i20 = i3;
                    i3 = i12;
                    i12 = i20;
                    while (i7 >= i2) {
                        i15 = i3 & i12;
                        i6 = i3 >> i2;
                        i7 -= i2;
                        if (i15 != i8) {
                            if (i15 <= i10) {
                                if (i15 == i9) {
                                    i15 = i18;
                                    i3 = i12;
                                    i12 = i16;
                                    i18 = i10;
                                    i10 = i17;
                                    i16 = i6;
                                    i17 = i7;
                                    i19 = i4;
                                    i4 = i2;
                                    i2 = i19;
                                    break;
                                } else if (i11 == -1) {
                                    i3 = i14 + 1;
                                    this.f8077o[i14] = this.f8076n[i15];
                                    i14 = i3;
                                    i18 = i15;
                                    i11 = i15;
                                    i3 = i6;
                                } else {
                                    if (i15 >= i10) {
                                        i3 = i14 + 1;
                                        this.f8077o[i14] = (byte) i18;
                                        i14 = i3;
                                        i18 = i11;
                                    } else {
                                        i18 = i15;
                                    }
                                    while (i18 >= i8) {
                                        i3 = i14 + 1;
                                        this.f8077o[i14] = this.f8076n[i18];
                                        short s = this.f8075m[i18];
                                        i14 = i3;
                                    }
                                    i18 = this.f8076n[i18] & 255;
                                    i3 = i14 + 1;
                                    this.f8077o[i14] = (byte) i18;
                                    if (i10 < 4096) {
                                        this.f8075m[i10] = (short) i11;
                                        this.f8076n[i10] = (byte) i18;
                                        i10++;
                                        if ((i10 & i12) == 0 && i10 < 4096) {
                                            i2++;
                                            i12 += i10;
                                        }
                                    }
                                    i14 = i13;
                                    i13 = i3;
                                    while (i13 > 0) {
                                        i3 = i4 + 1;
                                        i13--;
                                        this.f8078p[i4] = this.f8077o[i13];
                                        i14++;
                                        i4 = i3;
                                    }
                                    i3 = i6;
                                    i11 = i15;
                                    i19 = i14;
                                    i14 = i13;
                                    i13 = i19;
                                }
                            } else {
                                this.f8083u = 3;
                                i15 = i18;
                                i3 = i12;
                                i12 = i16;
                                i18 = i10;
                                i10 = i17;
                                i16 = i6;
                                i17 = i7;
                                i19 = i4;
                                i4 = i2;
                                i2 = i19;
                                break;
                            }
                        }
                        i2 = c + 1;
                        i12 = (1 << i2) - 1;
                        i10 = i8 + 2;
                        i3 = i6;
                        i11 = -1;
                    }
                    i15 = i18;
                    i18 = i10;
                    i10 = i17;
                    i17 = i7;
                    i19 = i4;
                    i4 = i2;
                    i2 = i19;
                    i20 = i3;
                    i3 = i12;
                    i12 = i16;
                    i16 = i20;
                }
                while (i2 < i5) {
                    this.f8078p[i2] = (byte) 0;
                    i2++;
                }
                i5 = hgVar2.f8091d / this.f8084v;
                c = hgVar2.f8089b / this.f8084v;
                i8 = hgVar2.f8090c / this.f8084v;
                i9 = hgVar2.f8088a / this.f8084v;
                i4 = 1;
                i10 = 8;
                i12 = 0;
                Object obj = this.f8064a == 0 ? 1 : null;
                i18 = 0;
                while (i18 < i5) {
                    if (hgVar2.f8092e) {
                        if (i12 >= i5) {
                            i4++;
                            switch (i4) {
                                case 2:
                                    i12 = 4;
                                    break;
                                case 3:
                                    i12 = 2;
                                    i10 = 4;
                                    break;
                                case 4:
                                    i12 = 1;
                                    i10 = 2;
                                    break;
                            }
                        }
                        i11 = i12 + i10;
                        i15 = i10;
                        i6 = i4;
                    } else {
                        i11 = i12;
                        i15 = i10;
                        i6 = i4;
                        i12 = i18;
                    }
                    i12 += c;
                    if (i12 < this.f8085w) {
                        i4 = i12 * this.f8086x;
                        i10 = i4 + i9;
                        i12 = i10 + i8;
                        if (this.f8086x + i4 < i12) {
                            i14 = this.f8086x + i4;
                        } else {
                            i14 = i12;
                        }
                        i12 = (this.f8084v * i18) * hgVar2.f8090c;
                        int i21 = i12 + ((i14 - i10) * this.f8084v);
                        i7 = i12;
                        for (i16 = i10; i16 < i14; i16++) {
                            if (this.f8084v == 1) {
                                i12 = this.f8067e[this.f8078p[i7] & 255];
                            } else {
                                int i22;
                                int i23 = hgVar2.f8090c;
                                i13 = 0;
                                i3 = 0;
                                i4 = 0;
                                i10 = 0;
                                i12 = 0;
                                i17 = i7;
                                while (i17 < this.f8084v + i7 && i17 < this.f8078p.length && i17 < i21) {
                                    i22 = this.f8067e[this.f8078p[i17] & 255];
                                    if (i22 != 0) {
                                        i13 += (i22 >> 24) & 255;
                                        i3 += (i22 >> 16) & 255;
                                        i4 += (i22 >> 8) & 255;
                                        i10 += i22 & 255;
                                        i12++;
                                    }
                                    i17++;
                                }
                                i17 = i7 + i23;
                                while (i17 < (i7 + i23) + this.f8084v && i17 < this.f8078p.length && i17 < i21) {
                                    i22 = this.f8067e[this.f8078p[i17] & 255];
                                    if (i22 != 0) {
                                        i13 += (i22 >> 24) & 255;
                                        i3 += (i22 >> 16) & 255;
                                        i4 += (i22 >> 8) & 255;
                                        i10 += i22 & 255;
                                        i12++;
                                    }
                                    i17++;
                                }
                                if (i12 == 0) {
                                    i12 = 0;
                                } else {
                                    i12 = (i10 / i12) | (((i4 / i12) << 8) | (((i3 / i12) << 16) | ((i13 / i12) << 24)));
                                }
                            }
                            if (i12 != 0) {
                                iArr[i16] = i12;
                            } else if (!(this.f8087y || obj == null)) {
                                this.f8087y = true;
                            }
                            i7 = this.f8084v + i7;
                        }
                    }
                    i18++;
                    i12 = i11;
                    i10 = i15;
                    i4 = i6;
                }
                if (this.f8082t && (hgVar2.f8094g == 0 || hgVar2.f8094g == 1)) {
                    if (this.f8081s == null) {
                        this.f8081s = m8040e();
                    }
                    this.f8081s.setPixels(iArr, 0, this.f8086x, 0, 0, this.f8086x, this.f8085w);
                }
                bitmap = m8040e();
                bitmap.setPixels(iArr, 0, this.f8086x, 0, 0, this.f8086x, this.f8085w);
            }
        }
        return bitmap;
    }

    /* renamed from: a */
    private synchronized void m8034a(hh hhVar, byte[] bArr) {
        m8033a(hhVar, ByteBuffer.wrap(bArr));
    }

    /* renamed from: a */
    private synchronized void m8033a(hh hhVar, ByteBuffer byteBuffer) {
        m8037b(hhVar, byteBuffer);
    }

    /* renamed from: b */
    private synchronized void m8037b(hh hhVar, ByteBuffer byteBuffer) {
        int highestOneBit = Integer.highestOneBit(1);
        this.f8083u = 0;
        this.f8066c = hhVar;
        this.f8087y = false;
        this.f8064a = -1;
        this.f8065b = 0;
        this.f8069g = byteBuffer.asReadOnlyBuffer();
        this.f8069g.position(0);
        this.f8069g.order(ByteOrder.LITTLE_ENDIAN);
        this.f8082t = false;
        for (hg hgVar : hhVar.f8103e) {
            if (hgVar.f8094g == 3) {
                this.f8082t = true;
                break;
            }
        }
        this.f8084v = highestOneBit;
        this.f8086x = hhVar.f8104f / highestOneBit;
        this.f8085w = hhVar.f8105g / highestOneBit;
        this.f8078p = this.f8080r.mo6331a(hhVar.f8104f * hhVar.f8105g);
        this.f8079q = this.f8080r.mo6332b(this.f8086x * this.f8085w);
    }

    /* renamed from: a */
    final synchronized int m8041a(byte[] bArr) {
        if (this.f8074l == null) {
            this.f8074l = new hi();
        }
        this.f8066c = this.f8074l.m8052a(bArr).m8051a();
        if (bArr != null) {
            m8034a(this.f8066c, bArr);
        }
        return this.f8083u;
    }

    /* renamed from: a */
    private void m8035a(int[] iArr, hg hgVar, int i) {
        int i2 = hgVar.f8090c / this.f8084v;
        int i3 = hgVar.f8088a / this.f8084v;
        int i4 = ((hgVar.f8089b / this.f8084v) * this.f8086x) + i3;
        i3 = i4 + ((hgVar.f8091d / this.f8084v) * this.f8086x);
        while (i4 < i3) {
            int i5 = i4 + i2;
            for (int i6 = i4; i6 < i5; i6++) {
                iArr[i6] = i;
            }
            i4 += this.f8086x;
        }
    }

    /* renamed from: b */
    private void m8036b() {
        if (this.f8072j <= this.f8073k) {
            if (this.f8071i == null) {
                this.f8071i = this.f8080r.mo6331a(16384);
            }
            this.f8073k = 0;
            this.f8072j = Math.min(this.f8069g.remaining(), 16384);
            this.f8069g.get(this.f8071i, 0, this.f8072j);
        }
    }

    /* renamed from: c */
    private int m8038c() {
        try {
            m8036b();
            byte[] bArr = this.f8071i;
            int i = this.f8073k;
            this.f8073k = i + 1;
            return bArr[i] & 255;
        } catch (Exception e) {
            this.f8083u = 1;
            return 0;
        }
    }

    /* renamed from: d */
    private int m8039d() {
        int c = m8038c();
        if (c > 0) {
            try {
                if (this.f8070h == null) {
                    this.f8070h = this.f8080r.mo6331a(255);
                }
                int i = this.f8072j - this.f8073k;
                if (i >= c) {
                    System.arraycopy(this.f8071i, this.f8073k, this.f8070h, 0, c);
                    this.f8073k += c;
                } else if (this.f8069g.remaining() + i >= c) {
                    System.arraycopy(this.f8071i, this.f8073k, this.f8070h, 0, i);
                    this.f8073k = this.f8072j;
                    m8036b();
                    int i2 = c - i;
                    System.arraycopy(this.f8071i, 0, this.f8070h, i, i2);
                    this.f8073k += i2;
                } else {
                    this.f8083u = 1;
                }
            } catch (Exception e) {
                new Object[1][0] = e;
                this.f8083u = 1;
            }
        }
        return c;
    }

    /* renamed from: e */
    private Bitmap m8040e() {
        Bitmap a = this.f8080r.mo6330a(this.f8086x, this.f8085w, this.f8087y ? Config.ARGB_4444 : Config.RGB_565);
        if (VERSION.SDK_INT >= 12) {
            a.setHasAlpha(true);
        }
        return a;
    }
}
