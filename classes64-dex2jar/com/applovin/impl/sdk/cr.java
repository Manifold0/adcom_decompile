// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.concurrent.TimeUnit;
import com.applovin.mediation.AppLovinMediationErrorCode;
import com.applovin.mediation.AppLovinMediationLoadListener;
import com.applovin.mediation.AppLovinMediationAdapterConfig;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;

class cr implements Runnable
{
    final /* synthetic */ ck a;
    final /* synthetic */ cw b;
    final /* synthetic */ cp c;
    
    cr(final cp c, final ck a, final cw b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void run() {
        this.c.b(this.a);
        final cs cs = new cs(this);
        if (this.a.getType() == AppLovinAdType.REGULAR) {
            if (this.a.getSize() == AppLovinAdSize.INTERSTITIAL) {
                this.c.b.loadInterstitialAd(this.c.e, this.c.c.getApplicationContext(), cs);
            }
            else {
                this.c.d.e("MediationAdapterWrapper", "Failed to load " + this.a + ": " + this.a.getSize() + "> is not a supported ad size");
                this.c.a(AppLovinMediationErrorCode.INTERNAL_AD_SIZE_NOT_SUPPORTED.getErrorCode(), this.b);
            }
        }
        else if (this.a.getType() == AppLovinAdType.INCENTIVIZED) {
            this.c.b.loadIncentivizedAd(this.c.e, this.c.c.getApplicationContext(), cs);
        }
        else {
            this.c.d.e("MediationAdapterWrapper", "Failed to load " + this.a + ": " + this.a.getType() + " is not a supported ad type");
            this.c.a(AppLovinMediationErrorCode.INTERNAL_AD_TYPE_NOT_SUPPORTED.getErrorCode(), this.b);
        }
        if (!this.b.c.get()) {
            if (this.a.g() == 0) {
                this.c.d.d("MediationAdapterWrapper", "Failing ad " + this.a + " since it has 0 timeout");
                this.c.a(-5102, this.b);
            }
            else {
                if (this.a.g() > 0) {
                    this.c.d.d("MediationAdapterWrapper", "Setting timeout " + this.a.g() + " sec. for " + this.a);
                    this.c.c.getTaskManager().a(new cx(this.c, this.b, null), fe.a, TimeUnit.SECONDS.toMillis(this.a.g()));
                    return;
                }
                this.c.d.d("MediationAdapterWrapper", "Negative timeout set for " + this.a + ", not scheduling a timeout");
            }
        }
    }
}
