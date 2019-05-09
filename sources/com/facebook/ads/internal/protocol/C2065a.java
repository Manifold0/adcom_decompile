package com.facebook.ads.internal.protocol;

import android.text.TextUtils;

/* renamed from: com.facebook.ads.internal.protocol.a */
public class C2065a {
    /* renamed from: a */
    private final AdErrorType f4642a;
    /* renamed from: b */
    private final String f4643b;

    public C2065a(int i, String str) {
        this(AdErrorType.adErrorTypeFromCode(i), str);
    }

    public C2065a(AdErrorType adErrorType, String str) {
        if (TextUtils.isEmpty(str)) {
            str = adErrorType.getDefaultErrorMessage();
        }
        this.f4642a = adErrorType;
        this.f4643b = str;
    }

    /* renamed from: a */
    public static C2065a m5036a(AdErrorType adErrorType, String str) {
        return new C2065a(adErrorType, str);
    }

    /* renamed from: a */
    public static C2065a m5037a(C2066b c2066b) {
        return new C2065a(c2066b.m5040a(), c2066b.m5041b());
    }

    /* renamed from: a */
    public AdErrorType m5038a() {
        return this.f4642a;
    }

    /* renamed from: b */
    public String m5039b() {
        return this.f4643b;
    }
}
