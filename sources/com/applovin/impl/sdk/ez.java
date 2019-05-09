package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.Map;
import org.json.JSONObject;

class ez extends ex {
    /* renamed from: a */
    private final int f2488a;
    /* renamed from: b */
    private final AppLovinNativeAdLoadListener f2489b;

    public ez(String str, int i, AppLovinSdkImpl appLovinSdkImpl, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super(C1287n.m3040b(str, appLovinSdkImpl), null, appLovinSdkImpl);
        this.f2488a = i;
        this.f2489b = appLovinNativeAdLoadListener;
    }

    /* renamed from: a */
    protected dx mo4167a(JSONObject jSONObject) {
        return new fq(jSONObject, this.d, this.f2489b);
    }

    /* renamed from: a */
    protected void mo4168a(int i) {
        if (this.f2489b != null) {
            this.f2489b.onNativeAdsFailedToLoad(i);
        }
    }

    /* renamed from: b */
    protected String mo4169b(Map<String, String> map) {
        return ag.m2243b("nad", map, this.d);
    }

    /* renamed from: c */
    protected String mo4170c(Map<String, String> map) {
        return ag.m2248d("nad", map, this.d);
    }

    /* renamed from: e */
    protected void mo4171e(Map<String, String> map) {
        map.put("slot_count", Integer.toString(this.f2488a));
    }
}
