package com.applovin.impl.sdk;

import org.json.JSONObject;

class ev extends fs<JSONObject> {
    /* renamed from: a */
    final /* synthetic */ eu f2482a;

    ev(eu euVar, String str, JSONObject jSONObject, String str2, AppLovinSdkImpl appLovinSdkImpl) {
        this.f2482a = euVar;
        super(str, jSONObject, str2, appLovinSdkImpl);
    }

    /* renamed from: a */
    public void mo4128a(int i) {
        this.e.mo4173e(m2482a(), "Unable to fetch basic SDK settings: server returned " + i);
        this.d.m2140a(false);
    }

    /* renamed from: a */
    public void m2803a(JSONObject jSONObject, int i) {
        ag.m2240a(jSONObject, this.d);
        this.e.mo4175i(m2482a(), "Executing initialize SDK...");
        this.d.getTaskManager().m2854a(new fb(this.d));
        ag.m2246b(jSONObject, this.d);
        this.e.mo4175i(m2482a(), "Finished executing initialize SDK");
    }
}
