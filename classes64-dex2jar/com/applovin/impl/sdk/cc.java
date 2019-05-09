// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

final class cc implements Runnable
{
    final /* synthetic */ AppLovinAdDisplayListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ AppLovinSdk c;
    
    cc(final AppLovinAdDisplayListener a, final AppLovinAd b, final AppLovinSdk c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    @Override
    public void run() {
        try {
            this.a.adHidden(b(this.b));
        }
        catch (Throwable t) {
            this.c.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being hidden", t);
        }
    }
}
