package com.applovin.impl.p016a;

import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.C1287n;
import com.applovin.impl.sdk.C1288o;
import com.applovin.impl.sdk.aa;
import com.applovin.impl.sdk.bu;
import com.applovin.impl.sdk.gd;
import com.applovin.impl.sdk.gf;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.applovin.impl.a.g */
public class C1234g {
    /* renamed from: c */
    private static final List<String> f1614c = Arrays.asList(new String[]{"video/mp4", "video/webm", "video/3gpp", "video/x-matroska"});
    /* renamed from: a */
    protected List<gf> f1615a = new ArrayList();
    /* renamed from: b */
    private final AppLovinSdkImpl f1616b;
    /* renamed from: d */
    private final JSONObject f1617d;
    /* renamed from: e */
    private final JSONObject f1618e;
    /* renamed from: f */
    private final long f1619f = System.currentTimeMillis();

    public C1234g(JSONObject jSONObject, JSONObject jSONObject2, AppLovinSdkImpl appLovinSdkImpl) {
        this.f1616b = appLovinSdkImpl;
        this.f1617d = jSONObject;
        this.f1618e = jSONObject2;
    }

    /* renamed from: a */
    public int m1880a() {
        return this.f1615a.size();
    }

    /* renamed from: b */
    public List<gf> m1881b() {
        return this.f1615a;
    }

    /* renamed from: c */
    public JSONObject m1882c() {
        return this.f1617d;
    }

    /* renamed from: d */
    public JSONObject m1883d() {
        return this.f1618e;
    }

    /* renamed from: e */
    public long m1884e() {
        return this.f1619f;
    }

    /* renamed from: f */
    public C1287n m1885f() {
        String a = bu.m2389a(this.f1618e, "zone_id", null, this.f1616b);
        return C1287n.m3036a(AppLovinAdSize.fromString(bu.m2389a(this.f1618e, "ad_size", null, this.f1616b)), AppLovinAdType.fromString(bu.m2389a(this.f1618e, "ad_type", null, this.f1616b)), C1288o.DIRECT, a, this.f1616b);
    }

    /* renamed from: g */
    public List<String> m1886g() {
        List<String> a = aa.m2193a(bu.m2389a(this.f1617d, "vast_preferred_video_types", null, null));
        return !a.isEmpty() ? a : f1614c;
    }

    /* renamed from: h */
    public int m1887h() {
        return gd.m2932a(this.f1617d);
    }
}
