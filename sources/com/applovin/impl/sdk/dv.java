package com.applovin.impl.sdk;

import org.json.JSONObject;

abstract class dv extends dx {
    protected dv(String str, AppLovinSdkImpl appLovinSdkImpl) {
        super(str, appLovinSdkImpl);
    }

    /* renamed from: a */
    protected void m2630a(String str, JSONObject jSONObject, af afVar) {
        fs dwVar = new dw(this, "POST", new JSONObject(), "Repeat" + this.c, this.d, afVar);
        dwVar.m2497a(ag.m2237a(str, null, this.d));
        dwVar.m2502b(ag.m2247c(str, null, this.d));
        dwVar.m2498a(jSONObject);
        dwVar.m2503c(((Integer) this.d.get(ea.f2413j)).intValue());
        dwVar.m2495a(ea.f2416m);
        dwVar.m2501b(ea.f2420q);
        dwVar.run();
    }
}
