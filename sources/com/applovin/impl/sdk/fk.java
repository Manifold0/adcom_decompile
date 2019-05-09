package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdk;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class fk extends dx implements AppLovinAdLoadListener {
    /* renamed from: a */
    final /* synthetic */ fj f2520a;
    /* renamed from: b */
    private final JSONArray f2521b;
    /* renamed from: h */
    private final int f2522h;

    fk(fj fjVar, int i, JSONArray jSONArray) {
        this.f2520a = fjVar;
        super("TaskProcessNextWaterfallAd", fjVar.d);
        if (jSONArray == null) {
            throw new IllegalArgumentException("No ad objects array specified");
        } else if (i < 0 || i >= jSONArray.length()) {
            throw new IllegalArgumentException("Invalid ad index specified: " + i);
        } else {
            this.f2521b = jSONArray;
            this.f2522h = i;
        }
    }

    /* renamed from: a */
    private void m2866a(int i) throws JSONException {
        if ("adapter".equals(m2867b(i))) {
            this.d.getTaskManager().m2855a(new fi(this.f2521b.getJSONObject(i), this.f2520a.f2517a, this.d), fe.BACKGROUND);
        }
    }

    /* renamed from: b */
    private String m2867b(int i) {
        if (i < 0 || i >= this.f2521b.length()) {
            return "undefined";
        }
        try {
            return bu.m2389a(this.f2521b.getJSONObject(i), "type", "undefined", this.d);
        } catch (JSONException e) {
            this.e.mo4173e(this.c, "Unable to parse next ad from the ad response");
            return "undefined";
        }
    }

    /* renamed from: b */
    private void m2868b() throws JSONException {
        JSONObject jSONObject = this.f2521b.getJSONObject(this.f2522h);
        String b = m2867b(this.f2522h);
        if (AppLovinSdk.URI_SCHEME.equalsIgnoreCase(b)) {
            this.e.mo4172d(this.c, "Starting task for AppLovin ad...");
            this.d.getTaskManager().m2854a(new fp(jSONObject, this.f2520a.f2517a, this, this.d));
        } else if ("vast".equalsIgnoreCase(b)) {
            this.e.mo4172d(this.c, "Starting task for VAST ad...");
            this.d.getTaskManager().m2854a(fl.m2870a(jSONObject, this.f2520a.f2517a, (AppLovinAdLoadListener) this, this.d));
        } else if ("adapter".equalsIgnoreCase(b)) {
            this.e.mo4172d(this.c, "Starting task for adapter ad...");
            this.d.getTaskManager().m2854a(new fc(jSONObject, this.f2520a.f2517a, this.d, this));
        } else {
            this.e.mo4178w(this.c, "Unable to process ad of unknown type: " + b);
            failedToReceiveAd(AppLovinErrorCodes.INVALID_RESPONSE);
        }
    }

    public void adReceived(AppLovinAd appLovinAd) {
        this.f2520a.m2863a(appLovinAd);
    }

    public void failedToReceiveAd(int i) {
        if (this.f2522h < this.f2521b.length() - 1) {
            this.e.mo4175i(this.c, "Attempting to load next ad (" + this.f2522h + ") after failure...");
            this.d.getTaskManager().m2855a(new fk(this.f2520a, this.f2522h + 1, this.f2521b), fe.BACKGROUND);
            return;
        }
        this.f2520a.m2865b();
    }

    public void run() {
        try {
            int i;
            if (this.f2522h == 0) {
                int intValue = ((Integer) this.d.get(ea.dB)).intValue();
                i = 1;
                while (i <= intValue && i < this.f2521b.length()) {
                    m2866a(i);
                    i++;
                }
            } else {
                i = ((Integer) this.d.get(ea.dB)).intValue() + this.f2522h;
                if (i < this.f2521b.length()) {
                    m2866a(i);
                }
            }
            m2868b();
        } catch (Throwable th) {
            this.e.mo4174e(this.c, "Encountered error while processing ad number " + this.f2522h, th);
            this.f2520a.m2865b();
        }
    }
}
