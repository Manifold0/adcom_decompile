package com.applovin.impl.sdk;

import android.text.TextUtils;
import java.util.Collection;
import java.util.HashSet;

/* renamed from: com.applovin.impl.sdk.b */
class C1274b {
    /* renamed from: a */
    static final C1274b f2101a = C1274b.m2345a("srt");
    /* renamed from: b */
    static final C1274b f2102b = C1274b.m2345a("sft");
    /* renamed from: c */
    static final C1274b f2103c = C1274b.m2345a("sfs");
    /* renamed from: d */
    static final C1274b f2104d = C1274b.m2345a("sadb");
    /* renamed from: e */
    static final C1274b f2105e = C1274b.m2345a("sacb");
    /* renamed from: f */
    static final C1274b f2106f = C1274b.m2345a("stdl");
    /* renamed from: g */
    static final C1274b f2107g = C1274b.m2345a("stdi");
    /* renamed from: h */
    static final C1274b f2108h = C1274b.m2345a("snas");
    /* renamed from: i */
    static final C1274b f2109i = C1274b.m2345a("snat");
    /* renamed from: j */
    static final C1274b f2110j = C1274b.m2345a("stah");
    /* renamed from: k */
    static final C1274b f2111k = C1274b.m2345a("stas");
    /* renamed from: l */
    static final C1274b f2112l = C1274b.m2345a("stac");
    /* renamed from: m */
    static final C1274b f2113m = C1274b.m2345a("stbe");
    /* renamed from: n */
    static final C1274b f2114n = C1274b.m2345a("stbc");
    /* renamed from: o */
    static final C1274b f2115o = C1274b.m2345a("saan");
    /* renamed from: p */
    static final C1274b f2116p = C1274b.m2345a("suvs");
    /* renamed from: q */
    static final C1274b f2117q = C1274b.m2345a("svpv");
    /* renamed from: r */
    static final C1274b f2118r = C1274b.m2345a("stpd");
    /* renamed from: t */
    private static final Collection<String> f2119t = new HashSet(18);
    /* renamed from: s */
    private final String f2120s;

    private C1274b(String str) {
        this.f2120s = str;
    }

    /* renamed from: a */
    private static C1274b m2345a(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("No key name specified");
        } else if (f2119t.contains(str)) {
            throw new IllegalArgumentException("Key has already been used: " + str);
        } else {
            f2119t.add(str);
            return new C1274b(str);
        }
    }

    /* renamed from: a */
    public String m2346a() {
        return this.f2120s;
    }
}
