// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;

class cc implements AppLovinAdLoadListener
{
    final /* synthetic */ String a;
    final /* synthetic */ cb b;
    
    cc(final cb b, final String a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void adReceived(final AppLovinAd appLovinAd) {
        this.b.b(appLovinAd);
        this.b.showAndRender(appLovinAd, this.a);
    }
    
    @Override
    public void failedToReceiveAd(final int n) {
        this.b.a(n);
    }
}
