// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Map;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;

class az implements AppLovinAdRewardListener
{
    final /* synthetic */ ax a;
    
    az(final ax a) {
        this.a = a;
    }
    
    @Override
    public void userDeclinedToViewAd(final AppLovinAd appLovinAd) {
        this.a.a.getLogger().d("IncentivizedAdController", "User declined to view");
    }
    
    @Override
    public void userOverQuota(final AppLovinAd appLovinAd, final Map<String, String> map) {
        this.a.a.getLogger().d("IncentivizedAdController", "User over quota: " + map);
    }
    
    @Override
    public void userRewardRejected(final AppLovinAd appLovinAd, final Map<String, String> map) {
        this.a.a.getLogger().d("IncentivizedAdController", "Reward rejected: " + map);
    }
    
    @Override
    public void userRewardVerified(final AppLovinAd appLovinAd, final Map<String, String> map) {
        this.a.a.getLogger().d("IncentivizedAdController", "Reward validated: " + map);
    }
    
    @Override
    public void validationRequestFailed(final AppLovinAd appLovinAd, final int n) {
        this.a.a.getLogger().d("IncentivizedAdController", "Reward validation failed: " + n);
    }
}
