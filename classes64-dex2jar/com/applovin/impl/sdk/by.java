// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdk;
import java.util.Map;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;

final class by implements Runnable
{
    final /* synthetic */ AppLovinAdRewardListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ Map c;
    final /* synthetic */ AppLovinSdk d;
    
    by(final AppLovinAdRewardListener a, final AppLovinAd b, final Map c, final AppLovinSdk d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    @Override
    public void run() {
        try {
            this.a.userOverQuota(b(this.b), this.c);
        }
        catch (Throwable t) {
            this.d.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad reward listener about exceeding quota", t);
        }
    }
}
