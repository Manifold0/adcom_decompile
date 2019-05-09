// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.mediation.AppLovinMediationAdapterConfig;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;

class ct implements Runnable
{
    final /* synthetic */ ck a;
    final /* synthetic */ cp b;
    
    ct(final cp b, final ck a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void run() {
        this.b.b(this.a);
        if (this.a.getType() == AppLovinAdType.REGULAR) {
            if (this.a.getSize() == AppLovinAdSize.INTERSTITIAL) {
                this.b.b.prepareInterstitialAd(this.b.e, this.b.c.getApplicationContext());
            }
        }
        else if (this.a.getType() == AppLovinAdType.INCENTIVIZED) {
            this.b.b.prepareIncentivizedAd(this.b.e, this.b.c.getApplicationContext());
        }
    }
}
