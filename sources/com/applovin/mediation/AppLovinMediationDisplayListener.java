package com.applovin.mediation;

import java.util.Map;

public interface AppLovinMediationDisplayListener {
    void adClicked(AppLovinMediatedAdInfo appLovinMediatedAdInfo);

    void adDisplayed(AppLovinMediatedAdInfo appLovinMediatedAdInfo);

    void adHidden(AppLovinMediatedAdInfo appLovinMediatedAdInfo);

    void failedToDisplayAd(AppLovinMediatedAdInfo appLovinMediatedAdInfo, AppLovinMediationErrorCode appLovinMediationErrorCode);

    void rewardRejected(AppLovinMediatedAdInfo appLovinMediatedAdInfo, Map<String, String> map);

    void rewardVerified(AppLovinMediatedAdInfo appLovinMediatedAdInfo, Map<String, String> map);
}
