// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

final class cd implements Runnable
{
    final /* synthetic */ AppLovinAdClickListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ AppLovinSdk c;
    
    cd(final AppLovinAdClickListener a, final AppLovinAd b, final AppLovinSdk c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    @Override
    public void run() {
        try {
            this.a.adClicked(b(this.b));
        }
        catch (Throwable t) {
            this.c.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being clicked", t);
        }
    }
}
