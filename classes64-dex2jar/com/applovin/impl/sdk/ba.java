// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;

class ba implements AppLovinAdLoadListener
{
    final /* synthetic */ ax a;
    private final AppLovinAdLoadListener b;
    
    ba(final ax a, final AppLovinAdLoadListener b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void adReceived(final AppLovinAd appLovinAd) {
        this.a.c = appLovinAd;
        if (this.b != null) {
            AppLovinSdkUtils.runOnUiThread(new bb(this, appLovinAd));
        }
    }
    
    @Override
    public void failedToReceiveAd(final int n) {
        if (this.b != null) {
            AppLovinSdkUtils.runOnUiThread(new bc(this, n));
        }
    }
}
