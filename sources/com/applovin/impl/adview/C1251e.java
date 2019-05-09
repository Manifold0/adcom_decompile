package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinLogger;

/* renamed from: com.applovin.impl.adview.e */
class C1251e implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAd f1884a;
    /* renamed from: b */
    final /* synthetic */ AdViewControllerImpl f1885b;

    C1251e(AdViewControllerImpl adViewControllerImpl, AppLovinAd appLovinAd) {
        this.f1885b = adViewControllerImpl;
        this.f1884a = appLovinAd;
    }

    public void run() {
        try {
            if (this.f1885b.f1704z != null) {
                this.f1885b.f1704z.adReceived(this.f1884a);
            }
        } catch (Throwable th) {
            this.f1885b.f1683e.userError(AppLovinLogger.SDK_TAG, "Exception while running ad load callback: " + th.getMessage());
        }
    }
}
