package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import org.json.JSONObject;

public class fc extends dx {
    /* renamed from: a */
    private final JSONObject f2492a;
    /* renamed from: b */
    private final JSONObject f2493b;
    /* renamed from: h */
    private final AppLovinAdLoadListener f2494h;

    fc(JSONObject jSONObject, JSONObject jSONObject2, AppLovinSdkImpl appLovinSdkImpl, AppLovinAdLoadListener appLovinAdLoadListener) {
        super("TaskLoadAdapterAd", appLovinSdkImpl);
        if (jSONObject == null) {
            throw new IllegalArgumentException("No ad object specified");
        } else if (jSONObject2 == null) {
            throw new IllegalArgumentException("No response specified");
        } else {
            this.f2492a = jSONObject;
            this.f2493b = jSONObject2;
            this.f2494h = appLovinAdLoadListener;
        }
    }

    public void run() {
        try {
            this.d.getMediationService().m2183a(new ck(this.f2492a, this.f2493b, this.d), this.f2494h);
        } catch (Throwable th) {
            this.e.mo4174e(this.c, "Unable to process adapter ad", th);
            if (this.f2494h != null) {
                this.f2494h.failedToReceiveAd(AppLovinErrorCodes.MEDIATION_ADAPTER_LOAD_FAILED);
            }
        }
    }
}
