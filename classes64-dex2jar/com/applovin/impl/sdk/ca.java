// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;

final class ca implements Runnable
{
    final /* synthetic */ AppLovinAdRewardListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ int c;
    final /* synthetic */ AppLovinSdk d;
    
    ca(final AppLovinAdRewardListener a, final AppLovinAd b, final int c, final AppLovinSdk d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    @Override
    public void run() {
        try {
            this.a.validationRequestFailed(b(this.b), this.c);
        }
        catch (Throwable t) {
            this.d.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad reward listener about reward validation request failing", t);
        }
    }
}
