package com.tapjoy.internal;

import android.support.v4.view.ViewCompat;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public final class hi {
    /* renamed from: a */
    private final byte[] f8112a = new byte[256];
    /* renamed from: b */
    private ByteBuffer f8113b;
    /* renamed from: c */
    private hh f8114c;
    /* renamed from: d */
    private int f8115d = 0;

    /* renamed from: a */
    public final hi m8052a(byte[] bArr) {
        if (bArr != null) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            this.f8113b = null;
            Arrays.fill(this.f8112a, (byte) 0);
            this.f8114c = new hh();
            this.f8115d = 0;
            this.f8113b = wrap.asReadOnlyBuffer();
            this.f8113b.position(0);
            this.f8113b.order(ByteOrder.LITTLE_ENDIAN);
        } else {
            this.f8113b = null;
            this.f8114c.f8100b = 2;
        }
        return this;
    }

    /* renamed from: a */
    public final hh m8051a() {
        if (this.f8113b == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        } else if (m8050h()) {
            return this.f8114c;
        } else {
            m8046d();
            if (!m8050h()) {
                m8044b();
                if (this.f8114c.f8101c < 0) {
                    this.f8114c.f8100b = 1;
                }
            }
            return this.f8114c;
        }
    }

    /* renamed from: b */
    private void m8044b() {
        int i = 0;
        while (i == 0 && !m8050h() && this.f8114c.f8101c <= Integer.MAX_VALUE) {
            int g;
            switch (m8049g()) {
                case 33:
                    switch (m8049g()) {
                        case 1:
                            m8047e();
                            break;
                        case 249:
                            boolean z;
                            this.f8114c.f8102d = new hg();
                            m8049g();
                            g = m8049g();
                            this.f8114c.f8102d.f8094g = (g & 28) >> 2;
                            if (this.f8114c.f8102d.f8094g == 0) {
                                this.f8114c.f8102d.f8094g = 1;
                            }
                            hg hgVar = this.f8114c.f8102d;
                            if ((g & 1) != 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            hgVar.f8093f = z;
                            g = this.f8113b.getShort();
                            if (g < 2) {
                                g = 10;
                            }
                            this.f8114c.f8102d.f8096i = g * 10;
                            this.f8114c.f8102d.f8095h = m8049g();
                            m8049g();
                            break;
                        case 254:
                            m8047e();
                            break;
                        case 255:
                            m8048f();
                            String str = "";
                            for (g = 0; g < 11; g++) {
                                str = str + ((char) this.f8112a[g]);
                            }
                            if (!str.equals("NETSCAPE2.0")) {
                                m8047e();
                                break;
                            } else {
                                m8045c();
                                break;
                            }
                        default:
                            m8047e();
                            break;
                    }
                case 44:
                    boolean z2;
                    if (this.f8114c.f8102d == null) {
                        this.f8114c.f8102d = new hg();
                    }
                    this.f8114c.f8102d.f8088a = this.f8113b.getShort();
                    this.f8114c.f8102d.f8089b = this.f8113b.getShort();
                    this.f8114c.f8102d.f8090c = this.f8113b.getShort();
                    this.f8114c.f8102d.f8091d = this.f8113b.getShort();
                    int g2 = m8049g();
                    g = (g2 & 128) != 0 ? 1 : 0;
                    int pow = (int) Math.pow(2.0d, (double) ((g2 & 7) + 1));
                    hg hgVar2 = this.f8114c.f8102d;
                    if ((g2 & 64) != 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    hgVar2.f8092e = z2;
                    if (g != 0) {
                        this.f8114c.f8102d.f8098k = m8043a(pow);
                    } else {
                        this.f8114c.f8102d.f8098k = null;
                    }
                    this.f8114c.f8102d.f8097j = this.f8113b.position();
                    m8049g();
                    m8047e();
                    if (!m8050h()) {
                        hh hhVar = this.f8114c;
                        hhVar.f8101c++;
                        this.f8114c.f8103e.add(this.f8114c.f8102d);
                        break;
                    }
                    break;
                case 59:
                    i = 1;
                    break;
                default:
                    this.f8114c.f8100b = 1;
                    break;
            }
        }
    }

    /* renamed from: c */
    private void m8045c() {
        do {
            m8048f();
            if (this.f8112a[0] == (byte) 1) {
                this.f8114c.f8111m = (this.f8112a[1] & 255) | ((this.f8112a[2] & 255) << 8);
                if (this.f8114c.f8111m == 0) {
                    this.f8114c.f8111m = -1;
                }
            }
            if (this.f8115d <= 0) {
                return;
            }
        } while (!m8050h());
    }

    /* renamed from: d */
    private void m8046d() {
        int i;
        boolean z = true;
        String str = "";
        for (i = 0; i < 6; i++) {
            str = str + ((char) m8049g());
        }
        if (str.startsWith("GIF")) {
            this.f8114c.f8104f = this.f8113b.getShort();
            this.f8114c.f8105g = this.f8113b.getShort();
            i = m8049g();
            hh hhVar = this.f8114c;
            if ((i & 128) == 0) {
                z = false;
            }
            hhVar.f8106h = z;
            this.f8114c.f8107i = 2 << (i & 7);
            this.f8114c.f8108j = m8049g();
            this.f8114c.f8109k = m8049g();
            if (this.f8114c.f8106h && !m8050h()) {
                this.f8114c.f8099a = m8043a(this.f8114c.f8107i);
                this.f8114c.f8110l = this.f8114c.f8099a[this.f8114c.f8108j];
                return;
            }
            return;
        }
        this.f8114c.f8100b = 1;
    }

    /* renamed from: a */
    private int[] m8043a(int i) {
        int[] iArr;
        BufferUnderflowException e;
        byte[] bArr = new byte[(i * 3)];
        try {
            this.f8113b.get(bArr);
            iArr = new int[256];
            int i2 = 0;
            int i3 = 0;
            while (i3 < i) {
                int i4 = i2 + 1;
                try {
                    int i5 = bArr[i2] & 255;
                    int i6 = i4 + 1;
                    int i7 = bArr[i4] & 255;
                    i2 = i6 + 1;
                    i4 = i3 + 1;
                    iArr[i3] = (((i5 << 16) | ViewCompat.MEASURED_STATE_MASK) | (i7 << 8)) | (bArr[i6] & 255);
                    i3 = i4;
                } catch (BufferUnderflowException e2) {
                    e = e2;
                }
            }
        } catch (BufferUnderflowException e3) {
            BufferUnderflowException bufferUnderflowException = e3;
            iArr = null;
            e = bufferUnderflowException;
            new Object[1][0] = e;
            this.f8114c.f8100b = 1;
            return iArr;
        }
        return iArr;
    }

    /* renamed from: e */
    private void m8047e() {
        int g;
        do {
            try {
                g = m8049g();
                this.f8113b.position(this.f8113b.position() + g);
            } catch (IllegalArgumentException e) {
                return;
            }
        } while (g > 0);
    }

    /* renamed from: f */
    private int m8048f() {
        this.f8115d = m8049g();
        if (this.f8115d <= 0) {
            return 0;
        }
        int i = 0;
        int i2 = 0;
        while (i2 < this.f8115d) {
            try {
                i = this.f8115d - i2;
                this.f8113b.get(this.f8112a, i2, i);
                i2 += i;
            } catch (Exception e) {
                Object[] objArr = new Object[]{Integer.valueOf(i2), Integer.valueOf(i), Integer.valueOf(this.f8115d), e};
                this.f8114c.f8100b = 1;
                return i2;
            }
        }
        return i2;
    }

    /* renamed from: g */
    private int m8049g() {
        int i = 0;
        try {
            return this.f8113b.get() & 255;
        } catch (Exception e) {
            this.f8114c.f8100b = 1;
            return i;
        }
    }

    /* renamed from: h */
    private boolean m8050h() {
        return this.f8114c.f8100b != 0;
    }
}
