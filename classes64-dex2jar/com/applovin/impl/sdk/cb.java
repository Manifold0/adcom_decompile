// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;

final class cb implements Runnable
{
    final /* synthetic */ AppLovinAdRewardListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ AppLovinSdk c;
    
    cb(final AppLovinAdRewardListener a, final AppLovinAd b, final AppLovinSdk c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    @Override
    public void run() {
        try {
            this.a.userDeclinedToViewAd(b(this.b));
        }
        catch (Throwable t) {
            this.c.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad reward listener about user declining to view ad", t);
        }
    }
}
