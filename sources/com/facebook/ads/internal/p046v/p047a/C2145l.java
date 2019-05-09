package com.facebook.ads.internal.p046v.p047a;

/* renamed from: com.facebook.ads.internal.v.a.l */
public abstract class C2145l {
    /* renamed from: a */
    protected String f4967a = "";
    /* renamed from: b */
    protected C2147j f4968b;
    /* renamed from: c */
    protected String f4969c;
    /* renamed from: d */
    protected byte[] f4970d;

    public C2145l(String str, C2152p c2152p) {
        if (str != null) {
            this.f4967a = str;
        }
        if (c2152p != null) {
            this.f4967a += "?" + c2152p.m5509a();
        }
    }

    /* renamed from: a */
    public String m5493a() {
        return this.f4967a;
    }

    /* renamed from: b */
    public C2147j m5494b() {
        return this.f4968b;
    }

    /* renamed from: c */
    public String m5495c() {
        return this.f4969c;
    }

    /* renamed from: d */
    public byte[] m5496d() {
        return this.f4970d;
    }
}
