package com.applovin.impl.sdk;

import com.applovin.impl.p016a.C1234g;
import org.json.JSONObject;

final class fm extends C1234g {
    fm(JSONObject jSONObject, JSONObject jSONObject2, AppLovinSdkImpl appLovinSdkImpl) {
        super(jSONObject, jSONObject2, appLovinSdkImpl);
    }

    /* renamed from: a */
    void m2873a(gf gfVar) {
        if (gfVar == null) {
            throw new IllegalArgumentException("No aggregated vast response specified");
        }
        this.a.add(gfVar);
    }
}
