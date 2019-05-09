package com.tapjoy.internal;

public final class fl {
    /* renamed from: a */
    public static final fl f7783a = new fl(0, 0, 0, 0.0d);
    /* renamed from: b */
    public final long f7784b;
    /* renamed from: c */
    public final long f7785c;
    /* renamed from: d */
    public final double f7786d;
    /* renamed from: e */
    public long f7787e;
    /* renamed from: f */
    private final long f7788f;

    public fl(long j, long j2, long j3, double d) {
        this.f7788f = j;
        this.f7784b = j2;
        this.f7785c = j3;
        this.f7786d = d;
        this.f7787e = j;
    }

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        fl flVar = (fl) o;
        if (this.f7788f == flVar.f7788f && this.f7784b == flVar.f7784b && this.f7785c == flVar.f7785c && this.f7786d == flVar.f7786d && this.f7787e == flVar.f7787e) {
            return true;
        }
        return false;
    }
}
