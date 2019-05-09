// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.mediation;

import android.content.Context;
import android.app.Activity;
import com.applovin.sdk.AppLovinSdk;

public interface AppLovinMediationAdapter
{
    String getVersion();
    
    void initialize(final AppLovinMediationAdapterConfig p0, final AppLovinSdk p1, final Activity p2) throws Exception;
    
    boolean isReady();
    
    void loadIncentivizedAd(final AppLovinMediationAdapterConfig p0, final Context p1, final AppLovinMediationLoadListener p2);
    
    void loadInterstitialAd(final AppLovinMediationAdapterConfig p0, final Context p1, final AppLovinMediationLoadListener p2);
    
    void prepareIncentivizedAd(final AppLovinMediationAdapterConfig p0, final Context p1);
    
    void prepareInterstitialAd(final AppLovinMediationAdapterConfig p0, final Context p1);
    
    void processIncentivizedAdLoadTimeout();
    
    void processInterstitialAdLoadTimeout();
    
    void showIncentivizedAd(final AppLovinMediatedAdInfo p0, final AppLovinMediationAdapterConfig p1, final Activity p2, final AppLovinMediationDisplayListener p3);
    
    void showInterstitialAd(final AppLovinMediatedAdInfo p0, final AppLovinMediationAdapterConfig p1, final Activity p2, final AppLovinMediationDisplayListener p3);
}
