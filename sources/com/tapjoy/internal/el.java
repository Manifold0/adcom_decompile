package com.tapjoy.internal;

import android.os.SystemClock;

public final class el {
    /* renamed from: a */
    public static final el f7662a = new el(-1);
    /* renamed from: b */
    public final long f7663b;
    /* renamed from: c */
    public long f7664c;

    public el(long j) {
        this.f7663b = j;
        this.f7664c = SystemClock.elapsedRealtime();
    }

    public el() {
        this.f7663b = 3600000;
        try {
            this.f7664c = SystemClock.elapsedRealtime() - 3600000;
        } catch (NullPointerException e) {
            this.f7664c = -1;
        }
    }

    /* renamed from: a */
    public final boolean m7649a() {
        try {
            return SystemClock.elapsedRealtime() - this.f7664c > this.f7663b;
        } catch (NullPointerException e) {
            return true;
        }
    }

    /* renamed from: a */
    public final boolean m7650a(long j) {
        try {
            return (SystemClock.elapsedRealtime() - this.f7664c) + j > this.f7663b;
        } catch (NullPointerException e) {
            return true;
        }
    }
}
