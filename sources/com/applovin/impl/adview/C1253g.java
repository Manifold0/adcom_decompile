package com.applovin.impl.adview;

import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.sdk.C1286m;
import com.applovin.impl.sdk.bv;
import com.applovin.sdk.AppLovinAd;

/* renamed from: com.applovin.impl.adview.g */
class C1253g implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AdViewControllerImpl f1888a;

    C1253g(AdViewControllerImpl adViewControllerImpl) {
        this.f1888a = adViewControllerImpl;
    }

    public void run() {
        AppLovinAd appLovinAd = null;
        if (this.f1888a.f1698t != null || this.f1888a.f1697s != null) {
            C1286m a;
            if (this.f1888a.f1698t != null) {
                a = this.f1888a.f1698t.m2037a();
                this.f1888a.f1698t.dismiss();
                this.f1888a.f1698t = null;
                appLovinAd = a;
            } else if (this.f1888a.f1697s != null) {
                a = this.f1888a.f1697s.m2037a();
                this.f1888a.f1697s.dismiss();
                this.f1888a.f1697s = null;
                Object obj = a;
            }
            bv.m2408b(this.f1888a.f1676B, appLovinAd, (AppLovinAdView) this.f1888a.f1680b, this.f1888a.f1681c);
        }
    }
}
