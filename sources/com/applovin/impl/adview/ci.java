package com.applovin.impl.adview;

import android.app.Activity;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;

class ci extends cb {
    ci(AppLovinSdk appLovinSdk, Activity activity) {
        super(appLovinSdk, activity);
    }

    /* renamed from: a */
    protected void mo4048a(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.b.getAdService().loadNextMediatedAd(AppLovinAdSize.INTERSTITIAL, appLovinAdLoadListener);
    }
}
