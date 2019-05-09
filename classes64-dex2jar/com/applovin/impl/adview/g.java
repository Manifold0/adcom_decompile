// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.impl.sdk.bv;
import com.applovin.adview.AppLovinAdView;

class g implements Runnable
{
    final /* synthetic */ AdViewControllerImpl a;
    
    g(final AdViewControllerImpl a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        AppLovinAd appLovinAd = null;
        if (this.a.t != null || this.a.s != null) {
            if (this.a.t != null) {
                appLovinAd = this.a.t.a();
                this.a.t.dismiss();
                this.a.t = null;
            }
            else if (this.a.s != null) {
                appLovinAd = this.a.s.a();
                this.a.s.dismiss();
                this.a.s = null;
            }
            bv.b(this.a.B, appLovinAd, (AppLovinAdView)this.a.b, this.a.c);
        }
    }
}
