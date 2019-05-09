// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdLoadListener;
import android.content.Context;
import android.app.Activity;
import com.applovin.sdk.AppLovinSdk;

class ci extends cb
{
    ci(final AppLovinSdk appLovinSdk, final Activity activity) {
        super(appLovinSdk, (Context)activity);
    }
    
    @Override
    protected void a(final AppLovinAdLoadListener appLovinAdLoadListener) {
        this.b.getAdService().loadNextMediatedAd(AppLovinAdSize.INTERSTITIAL, appLovinAdLoadListener);
    }
}
