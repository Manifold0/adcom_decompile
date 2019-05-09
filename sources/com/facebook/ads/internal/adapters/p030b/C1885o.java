package com.facebook.ads.internal.adapters.p030b;

import java.io.Serializable;

/* renamed from: com.facebook.ads.internal.adapters.b.o */
public class C1885o implements Serializable {
    private static final long serialVersionUID = 1;
    /* renamed from: a */
    private final String f4003a;
    /* renamed from: b */
    private final C1883m f4004b;
    /* renamed from: c */
    private final C1873e f4005c;
    /* renamed from: d */
    private final C1877i f4006d;
    /* renamed from: e */
    private final C1865b f4007e;
    /* renamed from: f */
    private final C1869d f4008f;
    /* renamed from: g */
    private final String f4009g;

    private C1885o(String str, String str2, C1883m c1883m, C1873e c1873e, C1877i c1877i, C1865b c1865b, C1869d c1869d) {
        this.f4009g = str;
        this.f4003a = str2;
        this.f4004b = c1883m;
        this.f4005c = c1873e;
        this.f4006d = c1877i;
        this.f4007e = c1865b;
        this.f4008f = c1869d;
    }

    /* renamed from: a */
    public static C1885o m4345a(C1879k c1879k) {
        C1880l c1880l = (C1880l) c1879k.m4308d().get(0);
        return new C1885o(c1879k.m4309e(), c1879k.m4307c(), c1879k.m4303a(), c1880l.m4315a(), c1880l.m4316b(), c1879k.m4306b(), c1880l.m4317c());
    }

    /* renamed from: a */
    public static C1885o m4346a(C1887q c1887q) {
        return new C1885o(c1887q.m4361e(), c1887q.mo5384a(), c1887q.m4362f(), c1887q.m4363g(), c1887q.m4364h(), c1887q.m4365i(), c1887q.m4366j());
    }

    /* renamed from: a */
    public String m4347a() {
        return this.f4009g;
    }

    /* renamed from: b */
    public C1883m m4348b() {
        return this.f4004b;
    }

    /* renamed from: c */
    public C1873e m4349c() {
        return this.f4005c;
    }

    /* renamed from: d */
    public C1877i m4350d() {
        return this.f4006d;
    }

    /* renamed from: e */
    public C1865b m4351e() {
        return this.f4007e;
    }

    /* renamed from: f */
    public C1869d m4352f() {
        return this.f4008f;
    }

    /* renamed from: g */
    public String m4353g() {
        return this.f4003a;
    }
}
