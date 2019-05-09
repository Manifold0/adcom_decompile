// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

class i implements Runnable
{
    final /* synthetic */ AppLovinAdDisplayListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ h c;
    
    i(final h c, final AppLovinAdDisplayListener a, final AppLovinAd b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void run() {
        this.a.adDisplayed(this.b);
    }
}
