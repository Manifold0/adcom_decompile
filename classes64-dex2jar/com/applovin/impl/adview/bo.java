// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.impl.sdk.bv;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

class bo implements AppLovinAdClickListener
{
    final /* synthetic */ az a;
    
    bo(final az a) {
        this.a = a;
    }
    
    @Override
    public void adClicked(final AppLovinAd appLovinAd) {
        bv.a(this.a.b.e(), appLovinAd, this.a.sdk);
    }
}
