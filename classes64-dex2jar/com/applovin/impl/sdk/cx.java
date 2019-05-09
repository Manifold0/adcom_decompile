// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.mediation.AppLovinMediationAdapter;
import com.applovin.sdk.AppLovinAdType;

class cx extends dx
{
    final /* synthetic */ cp a;
    private final cw b;
    
    private cx(final cp a, final cw b) {
        this.a = a;
        super("TaskTimeoutMediatedAd", a.c);
        if (b == null) {
            throw new IllegalArgumentException("No loadState specified");
        }
        this.b = b;
    }
    
    @Override
    public void run() {
        this.e.w(this.c, "Timing out " + this.b.a + "...");
        final AppLovinMediationAdapter d = this.a.d();
        if (this.b.a.getType().equals(AppLovinAdType.INCENTIVIZED)) {
            d.processIncentivizedAdLoadTimeout();
        }
        else {
            d.processInterstitialAdLoadTimeout();
        }
        this.a.a(-5101, this.b);
    }
}
