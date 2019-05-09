package com.applovin.impl.sdk;

import org.json.JSONObject;

class ey extends fs<JSONObject> {
    /* renamed from: a */
    final /* synthetic */ ex f2487a;

    ey(ex exVar, String str, JSONObject jSONObject, String str2, AppLovinSdkImpl appLovinSdkImpl) {
        this.f2487a = exVar;
        super(str, jSONObject, str2, appLovinSdkImpl);
    }

    /* renamed from: a */
    public void mo4128a(int i) {
        this.f2487a.m2809b(i);
    }

    /* renamed from: a */
    public void m2832a(JSONObject jSONObject, int i) {
        if (i == 200) {
            bu.m2397b(jSONObject, "fetch_ad_latency_millis", this.h.m2229a(), this.d);
            bu.m2397b(jSONObject, "fetch_ad_response_size", this.h.m2230b(), this.d);
            this.f2487a.m2810b(jSONObject);
            return;
        }
        this.f2487a.m2809b(i);
    }
}
