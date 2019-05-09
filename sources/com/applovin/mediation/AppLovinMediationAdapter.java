package com.applovin.mediation;

import android.app.Activity;
import android.content.Context;
import com.applovin.sdk.AppLovinSdk;

public interface AppLovinMediationAdapter {
    String getVersion();

    void initialize(AppLovinMediationAdapterConfig appLovinMediationAdapterConfig, AppLovinSdk appLovinSdk, Activity activity) throws Exception;

    boolean isReady();

    void loadIncentivizedAd(AppLovinMediationAdapterConfig appLovinMediationAdapterConfig, Context context, AppLovinMediationLoadListener appLovinMediationLoadListener);

    void loadInterstitialAd(AppLovinMediationAdapterConfig appLovinMediationAdapterConfig, Context context, AppLovinMediationLoadListener appLovinMediationLoadListener);

    void prepareIncentivizedAd(AppLovinMediationAdapterConfig appLovinMediationAdapterConfig, Context context);

    void prepareInterstitialAd(AppLovinMediationAdapterConfig appLovinMediationAdapterConfig, Context context);

    void processIncentivizedAdLoadTimeout();

    void processInterstitialAdLoadTimeout();

    void showIncentivizedAd(AppLovinMediatedAdInfo appLovinMediatedAdInfo, AppLovinMediationAdapterConfig appLovinMediationAdapterConfig, Activity activity, AppLovinMediationDisplayListener appLovinMediationDisplayListener);

    void showInterstitialAd(AppLovinMediatedAdInfo appLovinMediatedAdInfo, AppLovinMediationAdapterConfig appLovinMediationAdapterConfig, Activity activity, AppLovinMediationDisplayListener appLovinMediationDisplayListener);
}
