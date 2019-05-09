package com.applovin.impl.adview;

import com.applovin.impl.sdk.ee;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdUpdateListener;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.lang.ref.WeakReference;

/* renamed from: com.applovin.impl.adview.k */
class C1257k implements AppLovinAdLoadListener, AppLovinAdUpdateListener {
    /* renamed from: a */
    private AdViewControllerImpl f1892a;
    /* renamed from: b */
    private WeakReference<AdViewControllerImpl> f1893b;
    /* renamed from: c */
    private final AppLovinAdService f1894c;
    /* renamed from: d */
    private final AppLovinLogger f1895d;

    C1257k(AdViewControllerImpl adViewControllerImpl, AppLovinSdk appLovinSdk) {
        if (adViewControllerImpl == null) {
            throw new IllegalArgumentException("No view specified");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else {
            this.f1895d = appLovinSdk.getLogger();
            this.f1894c = appLovinSdk.getAdService();
            if (new ee(appLovinSdk).ap()) {
                this.f1892a = adViewControllerImpl;
            } else {
                this.f1893b = new WeakReference(adViewControllerImpl);
            }
        }
    }

    /* renamed from: a */
    private AdViewControllerImpl m2104a() {
        return this.f1892a != null ? this.f1892a : (AdViewControllerImpl) this.f1893b.get();
    }

    public void adReceived(AppLovinAd appLovinAd) {
        AdViewControllerImpl a = m2104a();
        if (a != null) {
            a.m1973a(appLovinAd);
        } else {
            this.f1895d.userError("AppLovinAdView", "Ad view has been garbage collected by the time an ad was received");
        }
    }

    public void adUpdated(AppLovinAd appLovinAd) {
        AdViewControllerImpl a = m2104a();
        if (a != null) {
            a.m1973a(appLovinAd);
            return;
        }
        this.f1894c.removeAdUpdateListener(this, appLovinAd.getSize());
        this.f1895d.userError("AppLovinAdView", "Ad view has been garbage collected by the time an ad was updated");
    }

    public void failedToReceiveAd(int i) {
        AdViewControllerImpl a = m2104a();
        if (a != null) {
            a.m1972a(i);
        }
    }

    public String toString() {
        return "[AdViewController listener: " + hashCode() + RequestParameters.RIGHT_BRACKETS;
    }
}
