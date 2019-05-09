package com.applovin.impl.adview;

/* renamed from: com.applovin.impl.adview.f */
class C1252f implements Runnable {
    /* renamed from: a */
    final /* synthetic */ int f1886a;
    /* renamed from: b */
    final /* synthetic */ AdViewControllerImpl f1887b;

    C1252f(AdViewControllerImpl adViewControllerImpl, int i) {
        this.f1887b = adViewControllerImpl;
        this.f1886a = i;
    }

    public void run() {
        try {
            if (this.f1887b.f1704z != null) {
                this.f1887b.f1704z.failedToReceiveAd(this.f1886a);
            }
        } catch (Throwable th) {
            this.f1887b.f1683e.userError("AppLovinAdView", "Exception while running app load  callback", th);
        }
    }
}
