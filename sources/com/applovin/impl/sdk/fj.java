package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import org.json.JSONArray;
import org.json.JSONObject;

class fj extends dx {
    /* renamed from: a */
    private final JSONObject f2517a;
    /* renamed from: b */
    private final C1287n f2518b;
    /* renamed from: h */
    private final AppLovinAdLoadListener f2519h;

    fj(JSONObject jSONObject, C1287n c1287n, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskProcessAdWaterfall", appLovinSdkImpl);
        if (jSONObject == null) {
            throw new IllegalArgumentException("No response specified");
        } else if (c1287n == null) {
            throw new IllegalArgumentException("No zone specified");
        } else {
            this.f2517a = jSONObject;
            this.f2518b = c1287n;
            this.f2519h = appLovinAdLoadListener;
        }
    }

    /* renamed from: a */
    private void m2860a(int i) {
        gd.m2943a(this.f2519h, this.f2518b, i, this.d);
    }

    /* renamed from: a */
    private void m2863a(AppLovinAd appLovinAd) {
        try {
            if (this.f2519h != null) {
                this.f2519h.adReceived(appLovinAd);
            }
        } catch (Throwable th) {
            this.e.mo4174e(this.c, "Unable process a ad received notification", th);
        }
    }

    /* renamed from: b */
    private void m2865b() {
        m2860a(-6);
    }

    public void run() {
        try {
            this.e.mo4172d(this.c, "Processing ad response...");
            JSONArray jSONArray = this.f2517a.getJSONArray("ads");
            int length = jSONArray.length();
            if (length > 0) {
                this.e.mo4172d(this.c, "Loading the first out of " + length + " ads...");
                this.d.getTaskManager().m2854a(new fk(this, 0, jSONArray));
                return;
            }
            this.e.mo4178w(this.c, "No ads were returned from the server");
            m2860a((int) AppLovinErrorCodes.NO_FILL);
        } catch (Throwable th) {
            this.e.mo4174e(this.c, "Encountered error while processing ad response", th);
            m2865b();
        }
    }
}
