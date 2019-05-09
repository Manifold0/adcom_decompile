// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.adview.AppLovinAdView;
import com.applovin.sdk.AppLovinAd;
import com.applovin.adview.AppLovinAdViewEventListener;

final class ch implements Runnable
{
    final /* synthetic */ AppLovinAdViewEventListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ AppLovinAdView c;
    final /* synthetic */ AppLovinSdk d;
    
    ch(final AppLovinAdViewEventListener a, final AppLovinAd b, final AppLovinAdView c, final AppLovinSdk d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    @Override
    public void run() {
        try {
            this.a.adClosedFullscreen(b(this.b), this.c);
        }
        catch (Throwable t) {
            this.d.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad event listener about fullscreen closed event", t);
        }
    }
}
