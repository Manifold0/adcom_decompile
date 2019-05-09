package com.facebook.ads.internal.protocol;

/* renamed from: com.facebook.ads.internal.protocol.b */
public class C2066b extends Exception {
    /* renamed from: a */
    private final AdErrorType f4644a;
    /* renamed from: b */
    private final String f4645b;

    public C2066b(AdErrorType adErrorType, String str) {
        this(adErrorType, str, null);
    }

    public C2066b(AdErrorType adErrorType, String str, Throwable th) {
        super(str, th);
        this.f4644a = adErrorType;
        this.f4645b = str;
    }

    /* renamed from: a */
    public AdErrorType m5040a() {
        return this.f4644a;
    }

    /* renamed from: b */
    public String m5041b() {
        return this.f4645b;
    }
}
