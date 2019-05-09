package com.facebook.ads.internal.p046v.p053b.p054a;

import java.io.File;

/* renamed from: com.facebook.ads.internal.v.b.a.g */
public class C2163g extends C2161e {
    /* renamed from: a */
    private final long f4988a;

    public C2163g(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("Max size must be positive number!");
        }
        this.f4988a = j;
    }

    /* renamed from: a */
    public /* bridge */ /* synthetic */ void mo5526a(File file) {
        super.mo5526a(file);
    }

    /* renamed from: a */
    protected boolean mo5528a(File file, long j, int i) {
        return j <= this.f4988a;
    }
}
