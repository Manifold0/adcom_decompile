package com.facebook.ads.internal.p051s;

import android.text.TextUtils;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p042l.C2042a;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.facebook.ads.internal.s.a */
public class C2080a {
    /* renamed from: a */
    private final String f4705a;
    /* renamed from: b */
    private final double f4706b = (((double) System.currentTimeMillis()) / 1000.0d);
    /* renamed from: c */
    private final double f4707c;
    /* renamed from: d */
    private final String f4708d;
    /* renamed from: e */
    private final Map<String, String> f4709e;
    /* renamed from: f */
    private final C2089f f4710f;
    /* renamed from: g */
    private final C2090g f4711g;
    /* renamed from: h */
    private final boolean f4712h;

    /* renamed from: com.facebook.ads.internal.s.a$a */
    public static class C2079a {
        /* renamed from: a */
        private String f4698a;
        /* renamed from: b */
        private double f4699b;
        /* renamed from: c */
        private String f4700c;
        /* renamed from: d */
        private Map<String, String> f4701d;
        /* renamed from: e */
        private C2089f f4702e;
        /* renamed from: f */
        private C2090g f4703f;
        /* renamed from: g */
        private boolean f4704g;

        /* renamed from: a */
        public C2079a m5123a(double d) {
            this.f4699b = d;
            return this;
        }

        /* renamed from: a */
        public C2079a m5124a(C2089f c2089f) {
            this.f4702e = c2089f;
            return this;
        }

        /* renamed from: a */
        public C2079a m5125a(C2090g c2090g) {
            this.f4703f = c2090g;
            return this;
        }

        /* renamed from: a */
        public C2079a m5126a(String str) {
            this.f4698a = str;
            return this;
        }

        /* renamed from: a */
        public C2079a m5127a(Map<String, String> map) {
            this.f4701d = map;
            return this;
        }

        /* renamed from: a */
        public C2079a m5128a(boolean z) {
            this.f4704g = z;
            return this;
        }

        /* renamed from: a */
        public C2080a m5129a() {
            return new C2080a(this.f4698a, this.f4699b, this.f4700c, this.f4701d, this.f4702e, this.f4703f, this.f4704g);
        }

        /* renamed from: b */
        public C2079a m5130b(String str) {
            this.f4700c = str;
            return this;
        }
    }

    public C2080a(String str, double d, String str2, Map<String, String> map, C2089f c2089f, C2090g c2090g, boolean z) {
        this.f4705a = str;
        this.f4707c = d;
        this.f4708d = str2;
        this.f4710f = c2089f;
        this.f4711g = c2090g;
        this.f4712h = z;
        Map hashMap = new HashMap();
        if (!(map == null || map.isEmpty())) {
            hashMap.putAll(map);
        }
        if (m5137f()) {
            hashMap.put("analog", C2581k.m6645a(C2042a.m4944a()));
        }
        this.f4709e = C2080a.m5131a(hashMap);
    }

    /* renamed from: a */
    private static Map<String, String> m5131a(Map<String, String> map) {
        Map<String, String> hashMap = new HashMap();
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (str2 != null) {
                hashMap.put(str, str2);
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    public String m5132a() {
        return this.f4705a;
    }

    /* renamed from: b */
    public double m5133b() {
        return this.f4706b;
    }

    /* renamed from: c */
    public double m5134c() {
        return this.f4707c;
    }

    /* renamed from: d */
    public String m5135d() {
        return this.f4708d;
    }

    /* renamed from: e */
    public Map<String, String> m5136e() {
        return this.f4709e;
    }

    /* renamed from: f */
    final boolean m5137f() {
        return this.f4710f == C2089f.IMMEDIATE;
    }

    /* renamed from: g */
    final boolean m5138g() {
        return !TextUtils.isEmpty(this.f4705a);
    }

    /* renamed from: h */
    public C2089f m5139h() {
        return this.f4710f;
    }

    /* renamed from: i */
    public C2090g m5140i() {
        return this.f4711g;
    }
}
