package com.applovin.impl.sdk;

import com.applovin.mediation.AppLovinMediationAdapter;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinErrorCodes;

class cx extends dx {
    /* renamed from: a */
    final /* synthetic */ cp f2262a;
    /* renamed from: b */
    private final cw f2263b;

    private cx(cp cpVar, cw cwVar) {
        this.f2262a = cpVar;
        super("TaskTimeoutMediatedAd", cpVar.f2237c);
        if (cwVar == null) {
            throw new IllegalArgumentException("No loadState specified");
        }
        this.f2263b = cwVar;
    }

    public void run() {
        this.e.mo4178w(this.c, "Timing out " + this.f2263b.f2254a + "...");
        AppLovinMediationAdapter d = this.f2262a.m2475d();
        if (this.f2263b.f2254a.getType().equals(AppLovinAdType.INCENTIVIZED)) {
            d.processIncentivizedAdLoadTimeout();
        } else {
            d.processInterstitialAdLoadTimeout();
        }
        this.f2262a.m2457a((int) AppLovinErrorCodes.MEDIATION_ADAPTER_TIMEOUT, this.f2263b);
    }
}
