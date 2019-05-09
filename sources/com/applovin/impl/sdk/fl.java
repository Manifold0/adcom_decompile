package com.applovin.impl.sdk;

import com.applovin.impl.p016a.C1234g;
import com.applovin.impl.p016a.C1235h;
import com.applovin.impl.p016a.C1241n;
import com.applovin.sdk.AppLovinAdLoadListener;
import org.json.JSONObject;

abstract class fl extends dx {
    /* renamed from: a */
    private final AppLovinAdLoadListener f2523a;
    /* renamed from: b */
    private final fm f2524b;

    fl(C1234g c1234g, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskProcessVastResponse", appLovinSdkImpl);
        if (c1234g == null) {
            throw new IllegalArgumentException("No context specified.");
        }
        this.f2523a = appLovinAdLoadListener;
        this.f2524b = (fm) c1234g;
    }

    /* renamed from: a */
    public static fl m2869a(gf gfVar, C1234g c1234g, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        return new fo(gfVar, c1234g, appLovinAdLoadListener, appLovinSdkImpl);
    }

    /* renamed from: a */
    public static fl m2870a(JSONObject jSONObject, JSONObject jSONObject2, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        return new fn(new fm(jSONObject, jSONObject2, appLovinSdkImpl), appLovinAdLoadListener, appLovinSdkImpl);
    }

    /* renamed from: a */
    void m2871a(C1235h c1235h) {
        this.e.mo4173e(this.c, "Failed to process VAST response due to VAST error code " + c1235h);
        C1241n.m1910a(this.f2524b, this.f2523a, c1235h, -6, this.d);
    }

    /* renamed from: a */
    protected void m2872a(gf gfVar) {
        int a = this.f2524b.m1880a();
        this.e.mo4172d(this.c, "Finished parsing XML at depth " + a);
        this.f2524b.m2873a(gfVar);
        if (C1241n.m1916a(gfVar)) {
            int intValue = ((Integer) this.d.get(ea.dq)).intValue();
            if (a < intValue) {
                this.e.mo4172d(this.c, "VAST response is wrapper. Resolving...");
                this.d.getTaskManager().m2854a(new fw(this.f2524b, this.f2523a, this.d));
                return;
            }
            this.e.mo4173e(this.c, "Reached beyond max wrapper depth of " + intValue);
            m2871a(C1235h.WRAPPER_LIMIT_REACHED);
        } else if (C1241n.m1919b(gfVar)) {
            this.e.mo4172d(this.c, "VAST response is inline. Rendering ad...");
            this.d.getTaskManager().m2854a(new fr(this.f2524b, this.f2523a, this.d));
        } else {
            this.e.mo4173e(this.c, "VAST response is an error");
            m2871a(C1235h.NO_WRAPPER_RESPONSE);
        }
    }
}
