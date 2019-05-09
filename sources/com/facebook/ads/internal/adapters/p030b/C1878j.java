package com.facebook.ads.internal.adapters.p030b;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/* renamed from: com.facebook.ads.internal.adapters.b.j */
public class C1878j implements Serializable {
    private static final long serialVersionUID = -2102939945352398575L;
    /* renamed from: a */
    private final byte[] f3965a;
    /* renamed from: b */
    private final String f3966b;
    /* renamed from: c */
    private final List<String> f3967c;
    /* renamed from: d */
    private String f3968d;

    C1878j(byte[] bArr, String str, List<String> list) {
        this.f3965a = bArr;
        this.f3966b = str;
        this.f3967c = list;
    }

    /* renamed from: a */
    public String m4297a() {
        return this.f3968d;
    }

    /* renamed from: a */
    void m4298a(String str) {
        this.f3968d = str;
    }

    /* renamed from: b */
    public byte[] m4299b() {
        return this.f3965a;
    }

    /* renamed from: c */
    public String m4300c() {
        return this.f3966b;
    }

    /* renamed from: d */
    public List<String> m4301d() {
        return Collections.unmodifiableList(this.f3967c);
    }
}
