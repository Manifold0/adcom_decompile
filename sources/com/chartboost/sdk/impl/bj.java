package com.chartboost.sdk.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class bj extends OutputStream {
    /* renamed from: a */
    private static final byte[] f3198a = new byte[0];
    /* renamed from: b */
    private final List<byte[]> f3199b;
    /* renamed from: c */
    private int f3200c;
    /* renamed from: d */
    private int f3201d;
    /* renamed from: e */
    private byte[] f3202e;
    /* renamed from: f */
    private int f3203f;

    public bj() {
        this(1024);
    }

    public bj(int i) {
        this.f3199b = new ArrayList();
        if (i < 0) {
            throw new IllegalArgumentException("Negative initial size: " + i);
        }
        synchronized (this) {
            m3533a(i);
        }
    }

    /* renamed from: a */
    private void m3533a(int i) {
        if (this.f3200c < this.f3199b.size() - 1) {
            this.f3201d += this.f3202e.length;
            this.f3200c++;
            this.f3202e = (byte[]) this.f3199b.get(this.f3200c);
            return;
        }
        if (this.f3202e == null) {
            this.f3201d = 0;
        } else {
            i = Math.max(this.f3202e.length << 1, i - this.f3201d);
            this.f3201d += this.f3202e.length;
        }
        this.f3200c++;
        this.f3202e = new byte[i];
        this.f3199b.add(this.f3202e);
    }

    public void write(byte[] b, int off, int len) {
        if (off < 0 || off > b.length || len < 0 || off + len > b.length || off + len < 0) {
            throw new IndexOutOfBoundsException();
        } else if (len != 0) {
            synchronized (this) {
                int i = this.f3203f + len;
                int i2 = this.f3203f - this.f3201d;
                int i3 = len;
                while (i3 > 0) {
                    int min = Math.min(i3, this.f3202e.length - i2);
                    System.arraycopy(b, (off + len) - i3, this.f3202e, i2, min);
                    i3 -= min;
                    if (i3 > 0) {
                        m3533a(i);
                        i2 = 0;
                    }
                }
                this.f3203f = i;
            }
        }
    }

    public synchronized void write(int b) {
        int i = this.f3203f - this.f3201d;
        if (i == this.f3202e.length) {
            m3533a(this.f3203f + 1);
            i = 0;
        }
        this.f3202e[i] = (byte) b;
        this.f3203f++;
    }

    public void close() throws IOException {
    }

    /* renamed from: a */
    public synchronized byte[] m3534a() {
        byte[] bArr;
        int i = this.f3203f;
        if (i == 0) {
            bArr = f3198a;
        } else {
            Object obj = new byte[i];
            int i2 = i;
            i = 0;
            for (byte[] bArr2 : this.f3199b) {
                int min = Math.min(bArr2.length, i2);
                System.arraycopy(bArr2, 0, obj, i, min);
                int i3 = i + min;
                i = i2 - min;
                if (i == 0) {
                    break;
                }
                i2 = i;
                i = i3;
            }
            Object obj2 = obj;
        }
        return bArr2;
    }

    public String toString() {
        return new String(m3534a());
    }
}
