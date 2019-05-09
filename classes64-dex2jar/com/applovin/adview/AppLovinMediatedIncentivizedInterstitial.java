// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.adview;

import com.applovin.impl.sdk.cl;
import com.applovin.impl.sdk.ax;
import com.applovin.sdk.AppLovinSdk;
import android.content.Context;

public class AppLovinMediatedIncentivizedInterstitial extends AppLovinIncentivizedInterstitial
{
    public AppLovinMediatedIncentivizedInterstitial(final Context context) {
        super(context);
    }
    
    public AppLovinMediatedIncentivizedInterstitial(final AppLovinSdk appLovinSdk) {
        super(appLovinSdk);
    }
    
    public static AppLovinMediatedIncentivizedInterstitial create(final Context context) {
        return create(AppLovinSdk.getInstance(context));
    }
    
    public static AppLovinMediatedIncentivizedInterstitial create(final AppLovinSdk appLovinSdk) {
        return new AppLovinMediatedIncentivizedInterstitial(appLovinSdk);
    }
    
    @Override
    protected ax createIncentivizedAdController(final String s, final AppLovinSdk appLovinSdk) {
        return new cl(appLovinSdk);
    }
}
