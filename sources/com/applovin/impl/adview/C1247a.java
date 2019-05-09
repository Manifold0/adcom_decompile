package com.applovin.impl.adview;

/* renamed from: com.applovin.impl.adview.a */
class C1247a implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AdViewControllerImpl f1714a;

    C1247a(AdViewControllerImpl adViewControllerImpl) {
        this.f1714a = adViewControllerImpl;
    }

    public void run() {
        if (this.f1714a.f1697s != null) {
            this.f1714a.f1683e.mo4172d("AppLovinAdView", "Detaching expanded ad: " + this.f1714a.f1697s.m2037a());
            this.f1714a.f1698t = this.f1714a.f1697s;
            this.f1714a.f1697s = null;
            this.f1714a.m1947a(this.f1714a.f1684f);
        }
    }
}
