package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;
import java.util.Map;

class az implements AppLovinAdRewardListener {
    /* renamed from: a */
    final /* synthetic */ ax f2100a;

    az(ax axVar) {
        this.f2100a = axVar;
    }

    public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
        this.f2100a.f2082a.getLogger().mo4172d("IncentivizedAdController", "User declined to view");
    }

    public void userOverQuota(AppLovinAd appLovinAd, Map<String, String> map) {
        this.f2100a.f2082a.getLogger().mo4172d("IncentivizedAdController", "User over quota: " + map);
    }

    public void userRewardRejected(AppLovinAd appLovinAd, Map<String, String> map) {
        this.f2100a.f2082a.getLogger().mo4172d("IncentivizedAdController", "Reward rejected: " + map);
    }

    public void userRewardVerified(AppLovinAd appLovinAd, Map<String, String> map) {
        this.f2100a.f2082a.getLogger().mo4172d("IncentivizedAdController", "Reward validated: " + map);
    }

    public void validationRequestFailed(AppLovinAd appLovinAd, int i) {
        this.f2100a.f2082a.getLogger().mo4172d("IncentivizedAdController", "Reward validation failed: " + i);
    }
}
