// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

class da implements AppLovinAdClickListener
{
    final /* synthetic */ ck a;
    final /* synthetic */ MediationServiceImpl b;
    
    da(final MediationServiceImpl b, final ck a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void adClicked(final AppLovinAd appLovinAd) {
        this.b.c(this.a);
    }
}
