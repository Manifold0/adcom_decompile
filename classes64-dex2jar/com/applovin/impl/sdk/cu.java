// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.mediation.AppLovinMediationDisplayListener;
import com.applovin.mediation.AppLovinMediationAdapterConfig;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import android.app.Activity;

class cu implements Runnable
{
    final /* synthetic */ h a;
    final /* synthetic */ ck b;
    final /* synthetic */ Activity c;
    final /* synthetic */ cp d;
    
    cu(final cp d, final h a, final ck b, final Activity c) {
        this.d = d;
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    @Override
    public void run() {
        final cv cv = new cv(this);
        if (this.b.getType() == AppLovinAdType.REGULAR) {
            if (this.b.getSize() == AppLovinAdSize.INTERSTITIAL) {
                this.d.b.showInterstitialAd(this.b.d(), this.d.e, this.c, cv);
                return;
            }
            this.d.d.e("MediationAdapterWrapper", "Failed to display " + this.b + ": " + this.b.getSize() + " is not a supported ad size");
            throw new IllegalArgumentException("Unsupported ad size");
        }
        else {
            if (this.b.getType() == AppLovinAdType.INCENTIVIZED) {
                this.d.b.showIncentivizedAd(this.b.d(), this.d.e, this.c, cv);
                return;
            }
            this.d.d.e("MediationAdapterWrapper", "Failed to display " + this.b + ": " + this.b.getType() + " is not a supported ad type");
            throw new IllegalArgumentException("Unsupported ad type");
        }
    }
}
