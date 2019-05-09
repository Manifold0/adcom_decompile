package com.applovin.impl.sdk;

import android.text.TextUtils;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdkUtils;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.util.Arrays;
import org.json.JSONObject;

/* renamed from: com.applovin.impl.sdk.q */
abstract class C1227q implements cj, AppLovinAd {
    /* renamed from: a */
    protected final JSONObject f1571a;
    /* renamed from: b */
    protected final JSONObject f1572b;
    /* renamed from: c */
    protected final AppLovinSdkImpl f1573c;
    /* renamed from: d */
    protected final Object f1574d;
    /* renamed from: e */
    private C1287n f1575e;
    /* renamed from: f */
    private final long f1576f;
    /* renamed from: g */
    private aq f1577g;

    C1227q(JSONObject jSONObject, JSONObject jSONObject2, AppLovinSdkImpl appLovinSdkImpl) {
        if (jSONObject == null) {
            throw new IllegalArgumentException("No ad object specified");
        } else if (jSONObject2 == null) {
            throw new IllegalArgumentException("No response specified");
        } else if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else {
            this.f1571a = jSONObject;
            this.f1572b = jSONObject2;
            this.f1573c = appLovinSdkImpl;
            this.f1574d = new Object();
            this.f1576f = System.currentTimeMillis();
        }
    }

    /* renamed from: a */
    private String mo4000a() {
        String jSONObject;
        synchronized (this.f1574d) {
            jSONObject = this.f1571a.toString();
        }
        char[] toCharArray = jSONObject.toCharArray();
        Arrays.sort(toCharArray);
        return new String(toCharArray) + getType() + getSize() + mo3996n();
    }

    /* renamed from: a */
    void m1774a(aq aqVar) {
        this.f1577g = aqVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
        r4 = this;
        r1 = 0;
        r0 = r5 instanceof com.applovin.impl.sdk.aq;
        if (r0 == 0) goto L_0x0043;
    L_0x0005:
        r0 = r5;
        r0 = (com.applovin.impl.sdk.aq) r0;
        r0 = r0.m2275b();
        if (r0 == 0) goto L_0x0043;
    L_0x000e:
        if (r4 != r0) goto L_0x0012;
    L_0x0010:
        r0 = 1;
    L_0x0011:
        return r0;
    L_0x0012:
        if (r0 == 0) goto L_0x001e;
    L_0x0014:
        r2 = r4.getClass();
        r3 = r0.getClass();
        if (r2 == r3) goto L_0x0020;
    L_0x001e:
        r0 = r1;
        goto L_0x0011;
    L_0x0020:
        r0 = (com.applovin.impl.sdk.C1227q) r0;
        r2 = r4.f1575e;
        if (r2 == 0) goto L_0x0032;
    L_0x0026:
        r2 = r4.f1575e;
        r3 = r0.f1575e;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0036;
    L_0x0030:
        r0 = r1;
        goto L_0x0011;
    L_0x0032:
        r2 = r0.f1575e;
        if (r2 != 0) goto L_0x0030;
    L_0x0036:
        r0 = r0.mo4000a();
        r1 = r4.mo4000a();
        r0 = r1.equals(r0);
        goto L_0x0011;
    L_0x0043:
        r0 = r5;
        goto L_0x000e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.q.equals(java.lang.Object):boolean");
    }

    /* renamed from: f */
    public boolean mo3992f() {
        this.f1573c.getLogger().mo4173e("AppLovinAdBase", "Attempting to invoke hasVideoUrl() from base ad class");
        return false;
    }

    public long getAdIdNumber() {
        return bu.m2386a(this.f1571a, "ad_id", -1, this.f1573c);
    }

    public String getAdValue(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject a = bu.m2395a(this.f1571a, "ad_values", null, this.f1573c);
        return (a == null || a.length() <= 0) ? null : bu.m2389a(a, str, null, this.f1573c);
    }

    public AppLovinAdSize getSize() {
        return AppLovinAdSize.fromString(bu.m2389a(this.f1572b, "ad_size", null, this.f1573c));
    }

    public AppLovinAdType getType() {
        return AppLovinAdType.fromString(bu.m2389a(this.f1572b, "ad_type", null, this.f1573c));
    }

    public String getZoneId() {
        return mo3997t().m3064m() ? null : bu.m2389a(this.f1572b, "zone_id", null, this.f1573c);
    }

    public int hashCode() {
        return this.f1575e.hashCode() + mo4000a().hashCode();
    }

    public boolean isVideoAd() {
        return this.f1571a.has("is_video_ad") ? bu.m2387a(this.f1571a, "is_video_ad", Boolean.valueOf(false), this.f1573c).booleanValue() : mo3992f();
    }

    /* renamed from: l */
    public long mo3994l() {
        return this.f1576f;
    }

    /* renamed from: m */
    public C1288o mo3995m() {
        return C1288o.m3065a(bu.m2389a(this.f1572b, "type", C1288o.DIRECT.toString(), this.f1573c));
    }

    /* renamed from: n */
    public String mo3996n() {
        String a = bu.m2389a(this.f1571a, "clcode", "", this.f1573c);
        return AppLovinSdkUtils.isValidString(a) ? a : bu.m2389a(this.f1572b, "clcode", "", this.f1573c);
    }

    /* renamed from: o */
    String m1779o() {
        return bu.m2389a(this.f1571a, "pk", "NA", this.f1573c);
    }

    /* renamed from: p */
    String m1780p() {
        return bu.m2389a(this.f1571a, "sk1", null, this.f1573c);
    }

    /* renamed from: q */
    String m1781q() {
        return bu.m2389a(this.f1571a, "sk2", null, this.f1573c);
    }

    /* renamed from: r */
    long m1782r() {
        return bu.m2386a(this.f1572b, "fetch_ad_latency_millis", -1, this.f1573c);
    }

    /* renamed from: s */
    long m1783s() {
        return bu.m2386a(this.f1572b, "fetch_ad_response_size", -1, this.f1573c);
    }

    /* renamed from: t */
    public C1287n mo3997t() {
        if (this.f1575e != null) {
            return this.f1575e;
        }
        this.f1575e = C1287n.m3036a(getSize(), getType(), mo3995m(), bu.m2389a(this.f1572b, "zone_id", null, this.f1573c), this.f1573c);
        return this.f1575e;
    }

    public String toString() {
        String jSONObject;
        synchronized (this.f1574d) {
            jSONObject = this.f1571a.toString();
        }
        return RequestParameters.LEFT_BRACKETS + getClass().getSimpleName() + " #" + getAdIdNumber() + " adType=" + getType() + ", adSize=" + getSize() + ", adObject=" + jSONObject + RequestParameters.RIGHT_BRACKETS;
    }

    /* renamed from: u */
    aq m1785u() {
        return this.f1577g;
    }
}
