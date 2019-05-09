// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

class bn implements AppLovinAdDisplayListener
{
    final /* synthetic */ az a;
    
    bn(final az a) {
        this.a = a;
    }
    
    @Override
    public void adDisplayed(final AppLovinAd appLovinAd) {
        if (!this.a.g) {
            this.a.a(appLovinAd);
        }
    }
    
    @Override
    public void adHidden(final AppLovinAd appLovinAd) {
        this.a.b(appLovinAd);
    }
}
