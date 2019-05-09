// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;

class ce implements Runnable
{
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ cb b;
    
    ce(final cb b, final AppLovinAd a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void run() {
        if (this.b.h != null) {
            this.b.h.adReceived(this.a);
        }
    }
}
