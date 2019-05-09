package com.applovin.impl.sdk;

class fx extends fs<gf> {
    /* renamed from: a */
    final /* synthetic */ fw f2541a;

    fx(fw fwVar, String str, gf gfVar, String str2, AppLovinSdkImpl appLovinSdkImpl) {
        this.f2541a = fwVar;
        super(str, gfVar, str2, appLovinSdkImpl);
    }

    /* renamed from: a */
    public void mo4128a(int i) {
        this.e.mo4173e(this.c, "Unable to resolve VAST wrapper. Server returned " + i);
        this.f2541a.m2889a(i);
    }

    /* renamed from: a */
    public void m2893a(gf gfVar, int i) {
        this.d.getTaskManager().m2854a(fl.m2869a(gfVar, this.f2541a.f2539a, this.f2541a.f2540b, this.d));
    }
}
