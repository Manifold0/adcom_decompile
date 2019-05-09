package com.applovin.impl.sdk;

import org.json.JSONObject;

class et extends fs<JSONObject> {
    /* renamed from: a */
    final /* synthetic */ es f2481a;

    et(es esVar, String str, JSONObject jSONObject, String str2, AppLovinSdkImpl appLovinSdkImpl) {
        this.f2481a = esVar;
        super(str, jSONObject, str2, appLovinSdkImpl);
    }

    /* renamed from: a */
    public void mo4128a(int i) {
        this.f2481a.f2476h.onPostbackFailure(this.f2481a.f2474a, i);
    }

    /* renamed from: a */
    public void m2797a(JSONObject jSONObject, int i) {
        this.f2481a.f2476h.onPostbackSuccess(this.f2481a.f2474a);
    }
}
