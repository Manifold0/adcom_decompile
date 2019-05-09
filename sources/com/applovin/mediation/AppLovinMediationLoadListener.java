package com.applovin.mediation;

public interface AppLovinMediationLoadListener {
    void adLoaded(AppLovinMediatedAdInfo appLovinMediatedAdInfo);

    void failedToLoadAd(AppLovinMediationErrorCode appLovinMediationErrorCode);
}
