package com.facebook.ads.internal.adapters.p030b;

import java.io.Serializable;

/* renamed from: com.facebook.ads.internal.adapters.b.i */
public class C1877i implements Serializable {
    private static final long serialVersionUID = -4041915335826065133L;
    /* renamed from: a */
    private final String f3963a;
    /* renamed from: b */
    private final String f3964b;

    C1877i(String str, String str2) {
        this.f3963a = C1877i.m4294a(str);
        this.f3964b = C1877i.m4294a(str2);
    }

    /* renamed from: a */
    private static String m4294a(String str) {
        return "null".equalsIgnoreCase(str) ? "" : str;
    }

    /* renamed from: a */
    public String m4295a() {
        return this.f3963a;
    }

    /* renamed from: b */
    public String m4296b() {
        return this.f3964b;
    }
}
