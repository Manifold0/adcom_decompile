package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdLoadListener;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import org.json.JSONObject;

class fp extends dx {
    /* renamed from: a */
    private final JSONObject f2527a;
    /* renamed from: b */
    private final JSONObject f2528b;
    /* renamed from: h */
    private final AppLovinAdLoadListener f2529h;

    fp(JSONObject jSONObject, JSONObject jSONObject2, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskRenderAppLovinAd", appLovinSdkImpl);
        this.f2527a = jSONObject;
        this.f2528b = jSONObject2;
        this.f2529h = appLovinAdLoadListener;
    }

    /* renamed from: a */
    private fe m2874a(String str) {
        return ParametersKeys.MAIN.equalsIgnoreCase(str) ? fe.MAIN : fe.BACKGROUND;
    }

    public void run() {
        this.e.mo4172d(this.c, "Rendering ad...");
        C1286m c1286m = new C1286m(this.f2527a, this.f2528b, this.d);
        boolean booleanValue = bu.m2387a(this.f2527a, "vs_cache_immediately", Boolean.valueOf(false), this.d).booleanValue();
        boolean booleanValue2 = bu.m2387a(this.f2527a, "vs_load_immediately", Boolean.valueOf(true), this.d).booleanValue();
        String a = bu.m2389a(this.f2527a, "vs_ad_cache_priority", "background", this.d);
        this.e.mo4172d(this.c, "Creating cache task...");
        dx elVar = new el(c1286m, this.f2529h, this.d);
        elVar.m2771a(booleanValue2);
        if (!c1286m.mo4001b() || booleanValue) {
            this.d.getTaskManager().m2854a(elVar);
            return;
        }
        this.d.getTaskManager().m2855a(elVar, m2874a(a));
    }
}
