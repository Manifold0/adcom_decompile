package com.facebook.ads.internal.p025w.p069c;

import android.support.annotation.NonNull;
import java.io.BufferedInputStream;
import java.io.InputStream;

/* renamed from: com.facebook.ads.internal.w.c.e */
public class C2607e extends BufferedInputStream {
    /* renamed from: a */
    private int f6459a;
    /* renamed from: b */
    private boolean f6460b;
    /* renamed from: c */
    private int f6461c = Integer.MAX_VALUE;

    public C2607e(InputStream inputStream) {
        super(inputStream);
    }

    /* renamed from: a */
    public boolean m6703a() {
        return this.f6460b;
    }

    public synchronized void mark(int i) {
        this.f6461c = i;
        super.mark(i);
    }

    public int read() {
        if (this.f6459a + 1 > this.f6461c) {
            this.f6460b = true;
            return -1;
        }
        this.f6459a++;
        return super.read();
    }

    public int read(@NonNull byte[] bArr) {
        if (this.f6459a + bArr.length <= this.f6461c) {
            return super.read(bArr);
        }
        this.f6460b = true;
        return -1;
    }

    public synchronized int read(byte[] bArr, int i, int i2) {
        int i3;
        if (this.f6459a + i2 > this.f6461c) {
            this.f6460b = true;
            i3 = -1;
        } else {
            i3 = super.read(bArr, i, i2);
            this.f6459a += i3;
        }
        return i3;
    }

    public synchronized void reset() {
        this.f6461c = Integer.MAX_VALUE;
        super.reset();
    }

    public synchronized long skip(long j) {
        long j2;
        if (((long) this.f6459a) + j > ((long) this.f6461c)) {
            this.f6460b = true;
            j2 = 0;
        } else {
            this.f6459a = (int) (((long) this.f6459a) + j);
            j2 = super.skip(j);
        }
        return j2;
    }
}
