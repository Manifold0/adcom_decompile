package com.applovin.impl.sdk;

import com.applovin.mediation.AppLovinMediatedAdInfo;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import java.util.Collections;
import java.util.Map;
import org.json.JSONObject;

public class ck extends C1227q {
    /* renamed from: e */
    private final boolean f2216e;
    /* renamed from: f */
    private final AppLovinMediatedAdInfo f2217f;

    public ck(ck ckVar, boolean z, AppLovinMediatedAdInfo appLovinMediatedAdInfo) {
        super(ckVar.a, ckVar.b, ckVar.c);
        this.f2216e = z;
        this.f2217f = appLovinMediatedAdInfo;
    }

    ck(JSONObject jSONObject, JSONObject jSONObject2, AppLovinSdkImpl appLovinSdkImpl) {
        super(jSONObject, jSONObject2, appLovinSdkImpl);
        this.f2216e = false;
        this.f2217f = null;
    }

    /* renamed from: a */
    public boolean mo4000a() {
        return this.f2216e;
    }

    /* renamed from: b */
    String m2423b() {
        return bu.m2389a(this.a, "class", null, this.c);
    }

    /* renamed from: c */
    String m2424c() {
        return bu.m2389a(this.a, "name", null, this.c);
    }

    /* renamed from: d */
    public AppLovinMediatedAdInfo m2425d() {
        return this.f2217f;
    }

    /* renamed from: e */
    public Map<String, String> m2426e() {
        if (this.a.has("config")) {
            try {
                return bu.m2391a(this.a.getJSONObject("config"));
            } catch (Throwable e) {
                this.c.getLogger().mo4174e("MediatedAd", "Failed to retrieve mediation configuration", e);
            }
        }
        return Collections.emptyMap();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        ck ckVar = (ck) obj;
        return this.f2216e != ckVar.f2216e ? false : this.f2217f != null ? this.f2217f.equals(ckVar.f2217f) : ckVar.f2217f == null;
    }

    /* renamed from: f */
    public /* bridge */ /* synthetic */ boolean mo3992f() {
        return super.mo3992f();
    }

    /* renamed from: g */
    public int m2428g() {
        return bu.m2385a(this.a, "timeout_sec", 5, this.c);
    }

    public /* bridge */ /* synthetic */ long getAdIdNumber() {
        return super.getAdIdNumber();
    }

    public /* bridge */ /* synthetic */ String getAdValue(String str) {
        return super.getAdValue(str);
    }

    public /* bridge */ /* synthetic */ AppLovinAdSize getSize() {
        return super.getSize();
    }

    public /* bridge */ /* synthetic */ AppLovinAdType getType() {
        return super.getType();
    }

    public /* bridge */ /* synthetic */ String getZoneId() {
        return super.getZoneId();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f2216e ? 1 : 0) + (super.hashCode() * 31)) * 31;
        if (this.f2217f != null) {
            i = this.f2217f.hashCode();
        }
        return hashCode + i;
    }

    public /* bridge */ /* synthetic */ boolean isVideoAd() {
        return super.isVideoAd();
    }

    /* renamed from: l */
    public /* bridge */ /* synthetic */ long mo3994l() {
        return super.mo3994l();
    }

    /* renamed from: m */
    public /* bridge */ /* synthetic */ C1288o mo3995m() {
        return super.mo3995m();
    }

    /* renamed from: n */
    public /* bridge */ /* synthetic */ String mo3996n() {
        return super.mo3996n();
    }

    /* renamed from: t */
    public /* bridge */ /* synthetic */ C1287n mo3997t() {
        return super.mo3997t();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }
}
