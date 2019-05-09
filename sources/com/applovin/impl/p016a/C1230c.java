package com.applovin.impl.p016a;

import com.applovin.impl.sdk.AppLovinSdkImpl;
import java.util.Set;
import org.json.JSONObject;

/* renamed from: com.applovin.impl.a.c */
public class C1230c {
    /* renamed from: a */
    private JSONObject f1587a;
    /* renamed from: b */
    private JSONObject f1588b;
    /* renamed from: c */
    private AppLovinSdkImpl f1589c;
    /* renamed from: d */
    private long f1590d;
    /* renamed from: e */
    private String f1591e;
    /* renamed from: f */
    private String f1592f;
    /* renamed from: g */
    private C1238k f1593g;
    /* renamed from: h */
    private C1242o f1594h;
    /* renamed from: i */
    private C1233f f1595i;
    /* renamed from: j */
    private Set<C1239l> f1596j;
    /* renamed from: k */
    private Set<C1239l> f1597k;

    private C1230c() {
    }

    /* renamed from: a */
    public C1228a m1863a() {
        return new C1228a();
    }

    /* renamed from: a */
    public C1230c m1864a(long j) {
        this.f1590d = j;
        return this;
    }

    /* renamed from: a */
    public C1230c m1865a(C1233f c1233f) {
        this.f1595i = c1233f;
        return this;
    }

    /* renamed from: a */
    public C1230c m1866a(C1238k c1238k) {
        this.f1593g = c1238k;
        return this;
    }

    /* renamed from: a */
    public C1230c m1867a(C1242o c1242o) {
        this.f1594h = c1242o;
        return this;
    }

    /* renamed from: a */
    public C1230c m1868a(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        this.f1589c = appLovinSdkImpl;
        return this;
    }

    /* renamed from: a */
    public C1230c m1869a(String str) {
        this.f1591e = str;
        return this;
    }

    /* renamed from: a */
    public C1230c m1870a(Set<C1239l> set) {
        this.f1596j = set;
        return this;
    }

    /* renamed from: a */
    public C1230c m1871a(JSONObject jSONObject) {
        if (jSONObject == null) {
            throw new IllegalArgumentException("No ad object specified.");
        }
        this.f1587a = jSONObject;
        return this;
    }

    /* renamed from: b */
    public C1230c m1872b(String str) {
        this.f1592f = str;
        return this;
    }

    /* renamed from: b */
    public C1230c m1873b(Set<C1239l> set) {
        this.f1597k = set;
        return this;
    }

    /* renamed from: b */
    public C1230c m1874b(JSONObject jSONObject) {
        if (jSONObject == null) {
            throw new IllegalArgumentException("No full ad response specified.");
        }
        this.f1588b = jSONObject;
        return this;
    }
}
