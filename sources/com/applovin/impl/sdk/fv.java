package com.applovin.impl.sdk;

import org.json.JSONObject;

class fv implements af<JSONObject> {
    /* renamed from: a */
    final /* synthetic */ fu f2538a;

    fv(fu fuVar) {
        this.f2538a = fuVar;
    }

    /* renamed from: a */
    public void mo4128a(int i) {
        this.f2538a.e.mo4173e("TaskReportReward", "Failed to report reward for ad: " + this.f2538a.f2537a.getAdIdNumber() + " - error code: " + i);
    }

    /* renamed from: a */
    public void m2887a(JSONObject jSONObject, int i) {
        this.f2538a.e.mo4172d("TaskReportReward", "Reported reward successfully for ad: " + this.f2538a.f2537a.getAdIdNumber());
    }
}
