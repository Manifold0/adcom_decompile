package com.applovin.impl.sdk;

import org.json.JSONObject;

public class fi extends dx {
    /* renamed from: a */
    private final JSONObject f2515a;
    /* renamed from: b */
    private final JSONObject f2516b;

    fi(JSONObject jSONObject, JSONObject jSONObject2, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskLoadAdapterAd", appLovinSdkImpl);
        if (jSONObject == null) {
            throw new IllegalArgumentException("No ad object specified");
        } else if (jSONObject2 == null) {
            throw new IllegalArgumentException("No response specified");
        } else {
            this.f2515a = jSONObject;
            this.f2516b = jSONObject2;
        }
    }

    public void run() {
        try {
            this.d.getMediationService().m2182a(new ck(this.f2515a, this.f2516b, this.d));
        } catch (Throwable th) {
            this.e.mo4174e(this.c, "Unable to prepare adapter ad", th);
        }
    }
}
