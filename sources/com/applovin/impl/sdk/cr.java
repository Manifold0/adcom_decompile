package com.applovin.impl.sdk;

import com.applovin.mediation.AppLovinMediationErrorCode;
import com.applovin.mediation.AppLovinMediationLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinErrorCodes;
import java.util.concurrent.TimeUnit;

class cr implements Runnable {
    /* renamed from: a */
    final /* synthetic */ ck f2243a;
    /* renamed from: b */
    final /* synthetic */ cw f2244b;
    /* renamed from: c */
    final /* synthetic */ cp f2245c;

    cr(cp cpVar, ck ckVar, cw cwVar) {
        this.f2245c = cpVar;
        this.f2243a = ckVar;
        this.f2244b = cwVar;
    }

    public void run() {
        this.f2245c.m2464b(this.f2243a);
        AppLovinMediationLoadListener csVar = new cs(this);
        if (this.f2243a.getType() == AppLovinAdType.REGULAR) {
            if (this.f2243a.getSize() == AppLovinAdSize.INTERSTITIAL) {
                this.f2245c.f2236b.loadInterstitialAd(this.f2245c.f2239e, this.f2245c.f2237c.getApplicationContext(), csVar);
            } else {
                this.f2245c.f2238d.mo4173e("MediationAdapterWrapper", "Failed to load " + this.f2243a + ": " + this.f2243a.getSize() + "> is not a supported ad size");
                this.f2245c.m2457a(AppLovinMediationErrorCode.INTERNAL_AD_SIZE_NOT_SUPPORTED.getErrorCode(), this.f2244b);
            }
        } else if (this.f2243a.getType() == AppLovinAdType.INCENTIVIZED) {
            this.f2245c.f2236b.loadIncentivizedAd(this.f2245c.f2239e, this.f2245c.f2237c.getApplicationContext(), csVar);
        } else {
            this.f2245c.f2238d.mo4173e("MediationAdapterWrapper", "Failed to load " + this.f2243a + ": " + this.f2243a.getType() + " is not a supported ad type");
            this.f2245c.m2457a(AppLovinMediationErrorCode.INTERNAL_AD_TYPE_NOT_SUPPORTED.getErrorCode(), this.f2244b);
        }
        if (!this.f2244b.f2256c.get()) {
            if (this.f2243a.m2428g() == 0) {
                this.f2245c.f2238d.mo4172d("MediationAdapterWrapper", "Failing ad " + this.f2243a + " since it has 0 timeout");
                this.f2245c.m2457a((int) AppLovinErrorCodes.MEDIATION_ADAPTER_IMMEDIATE_TIMEOUT, this.f2244b);
            } else if (this.f2243a.m2428g() > 0) {
                this.f2245c.f2238d.mo4172d("MediationAdapterWrapper", "Setting timeout " + this.f2243a.m2428g() + " sec. for " + this.f2243a);
                this.f2245c.f2237c.getTaskManager().m2856a(new cx(this.f2245c, this.f2244b, null), fe.MAIN, TimeUnit.SECONDS.toMillis((long) this.f2243a.m2428g()));
            } else {
                this.f2245c.f2238d.mo4172d("MediationAdapterWrapper", "Negative timeout set for " + this.f2243a + ", not scheduling a timeout");
            }
        }
    }
}
