package com.applovin.impl.sdk;

import android.app.Activity;
import com.applovin.mediation.AppLovinMediationDisplayListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;

class cu implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C1281h f2249a;
    /* renamed from: b */
    final /* synthetic */ ck f2250b;
    /* renamed from: c */
    final /* synthetic */ Activity f2251c;
    /* renamed from: d */
    final /* synthetic */ cp f2252d;

    cu(cp cpVar, C1281h c1281h, ck ckVar, Activity activity) {
        this.f2252d = cpVar;
        this.f2249a = c1281h;
        this.f2250b = ckVar;
        this.f2251c = activity;
    }

    public void run() {
        AppLovinMediationDisplayListener cvVar = new cv(this);
        if (this.f2250b.getType() == AppLovinAdType.REGULAR) {
            if (this.f2250b.getSize() == AppLovinAdSize.INTERSTITIAL) {
                this.f2252d.f2236b.showInterstitialAd(this.f2250b.m2425d(), this.f2252d.f2239e, this.f2251c, cvVar);
            } else {
                this.f2252d.f2238d.mo4173e("MediationAdapterWrapper", "Failed to display " + this.f2250b + ": " + this.f2250b.getSize() + " is not a supported ad size");
                throw new IllegalArgumentException("Unsupported ad size");
            }
        } else if (this.f2250b.getType() == AppLovinAdType.INCENTIVIZED) {
            this.f2252d.f2236b.showIncentivizedAd(this.f2250b.m2425d(), this.f2252d.f2239e, this.f2251c, cvVar);
        } else {
            this.f2252d.f2238d.mo4173e("MediationAdapterWrapper", "Failed to display " + this.f2250b + ": " + this.f2250b.getType() + " is not a supported ad type");
            throw new IllegalArgumentException("Unsupported ad type");
        }
    }
}
