// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

class k implements Runnable
{
    final /* synthetic */ AppLovinAdClickListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ h c;
    
    k(final h c, final AppLovinAdClickListener a, final AppLovinAd b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void run() {
        this.a.adClicked(this.b);
    }
}
