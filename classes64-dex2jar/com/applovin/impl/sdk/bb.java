// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;

class bb implements Runnable
{
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ ba b;
    
    bb(final ba b, final AppLovinAd a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void run() {
        this.b.b.adReceived(this.a);
    }
}
