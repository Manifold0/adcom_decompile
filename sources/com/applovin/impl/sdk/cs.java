package com.applovin.impl.sdk;

import com.applovin.mediation.AppLovinMediatedAdInfo;
import com.applovin.mediation.AppLovinMediationErrorCode;
import com.applovin.mediation.AppLovinMediationLoadListener;

class cs implements AppLovinMediationLoadListener {
    /* renamed from: a */
    final /* synthetic */ cr f2246a;

    cs(cr crVar) {
        this.f2246a = crVar;
    }

    public void adLoaded(AppLovinMediatedAdInfo appLovinMediatedAdInfo) {
        this.f2246a.f2245c.f2238d.mo4172d("MediationAdapterWrapper", "Successfully loaded " + this.f2246a.f2243a);
        this.f2246a.f2245c.m2461a(appLovinMediatedAdInfo, this.f2246a.f2244b);
    }

    public void failedToLoadAd(AppLovinMediationErrorCode appLovinMediationErrorCode) {
        if (appLovinMediationErrorCode == null) {
            appLovinMediationErrorCode = AppLovinMediationErrorCode.NETWORK_UNSPECIFIED;
        }
        this.f2246a.f2245c.f2238d.mo4173e("MediationAdapterWrapper", "Failed to load " + this.f2246a.f2243a + ": " + appLovinMediationErrorCode);
        this.f2246a.f2245c.m2457a(appLovinMediationErrorCode.getErrorCode(), this.f2246a.f2244b);
    }
}
