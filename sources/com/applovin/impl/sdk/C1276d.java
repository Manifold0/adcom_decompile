package com.applovin.impl.sdk;

import org.json.JSONObject;

/* renamed from: com.applovin.impl.sdk.d */
class C1276d extends fs<JSONObject> {
    /* renamed from: a */
    final /* synthetic */ C1275c f2284a;

    C1276d(C1275c c1275c, String str, JSONObject jSONObject, String str2, AppLovinSdkImpl appLovinSdkImpl) {
        this.f2284a = c1275c;
        super(str, jSONObject, str2, appLovinSdkImpl);
    }

    /* renamed from: a */
    public void mo4128a(int i) {
        this.e.mo4173e("AdEventStatsManager", "Failed to submitted ad stats: " + i);
    }

    /* renamed from: a */
    public void m2506a(JSONObject jSONObject, int i) {
        this.e.mo4172d("AdEventStatsManager", "Ad stats submitted: " + i);
    }
}
