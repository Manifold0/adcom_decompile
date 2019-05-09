package com.applovin.impl.sdk;

import com.applovin.mediation.AppLovinMediatedAdInfo;
import com.applovin.mediation.AppLovinMediationDisplayListener;
import com.applovin.mediation.AppLovinMediationErrorCode;
import java.util.Map;

class cv implements AppLovinMediationDisplayListener {
    /* renamed from: a */
    final /* synthetic */ cu f2253a;

    cv(cu cuVar) {
        this.f2253a = cuVar;
    }

    public void adClicked(AppLovinMediatedAdInfo appLovinMediatedAdInfo) {
        this.f2253a.f2249a.m3001c(this.f2253a.f2250b);
    }

    public void adDisplayed(AppLovinMediatedAdInfo appLovinMediatedAdInfo) {
        this.f2253a.f2249a.m2992a(this.f2253a.f2250b);
    }

    public void adHidden(AppLovinMediatedAdInfo appLovinMediatedAdInfo) {
        this.f2253a.f2249a.m2997b(this.f2253a.f2250b);
    }

    public void failedToDisplayAd(AppLovinMediatedAdInfo appLovinMediatedAdInfo, AppLovinMediationErrorCode appLovinMediationErrorCode) {
        this.f2253a.f2249a.m2997b(this.f2253a.f2250b);
    }

    public void rewardRejected(AppLovinMediatedAdInfo appLovinMediatedAdInfo, Map<String, String> map) {
        this.f2253a.f2249a.m3000b(map, this.f2253a.f2250b);
    }

    public void rewardVerified(AppLovinMediatedAdInfo appLovinMediatedAdInfo, Map<String, String> map) {
        this.f2253a.f2249a.m2996a(map, this.f2253a.f2250b);
    }
}
