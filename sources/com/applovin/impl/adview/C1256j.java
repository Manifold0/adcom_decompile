package com.applovin.impl.adview;

import com.applovin.impl.sdk.C1280g;
import com.applovin.impl.sdk.ar;
import com.applovin.sdk.AppLovinAdSize;

/* renamed from: com.applovin.impl.adview.j */
class C1256j implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AdViewControllerImpl f1891a;

    private C1256j(AdViewControllerImpl adViewControllerImpl) {
        this.f1891a = adViewControllerImpl;
    }

    public void run() {
        if (this.f1891a.f1694p == null) {
            return;
        }
        if (this.f1891a.f1690l != null) {
            this.f1891a.f1683e.mo4172d("AppLovinAdView", "Rendering advertisement ad for #" + this.f1891a.f1694p.getAdIdNumber() + " over placement: \"" + this.f1891a.f1686h + "\"...");
            AdViewControllerImpl.m1953b(this.f1891a.f1690l, this.f1891a.f1694p.getSize());
            this.f1891a.f1690l.m2114a(this.f1891a.f1694p, this.f1891a.f1686h);
            if (this.f1891a.f1694p.getSize() != AppLovinAdSize.INTERSTITIAL && !this.f1891a.f1702x && !(this.f1891a.f1694p instanceof ar)) {
                this.f1891a.f1687i = new C1280g(this.f1891a.f1694p, this.f1891a.f1681c);
                this.f1891a.f1687i.m2909a();
                this.f1891a.f1690l.m2113a(this.f1891a.f1687i);
                return;
            }
            return;
        }
        this.f1891a.f1681c.getLogger().userError("AppLovinAdView", "Unable to render advertisement for ad #" + this.f1891a.f1694p.getAdIdNumber() + ". Please make sure you are not calling AppLovinAdView.destroy() prematurely.");
    }
}
