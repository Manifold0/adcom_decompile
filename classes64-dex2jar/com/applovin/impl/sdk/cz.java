// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

class cz implements AppLovinAdDisplayListener
{
    final /* synthetic */ ck a;
    final /* synthetic */ MediationServiceImpl b;
    
    cz(final MediationServiceImpl b, final ck a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void adDisplayed(final AppLovinAd appLovinAd) {
        this.b.b(this.a);
    }
    
    @Override
    public void adHidden(final AppLovinAd appLovinAd) {
    }
}
