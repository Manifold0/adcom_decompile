package com.applovin.impl.sdk;

import android.net.Uri;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.adview.AdViewControllerImpl;
import com.applovin.sdk.AppLovinPostbackListener;

/* renamed from: com.applovin.impl.sdk.r */
class C1290r implements AppLovinPostbackListener {
    /* renamed from: a */
    final /* synthetic */ AdViewControllerImpl f2617a;
    /* renamed from: b */
    final /* synthetic */ Uri f2618b;
    /* renamed from: c */
    final /* synthetic */ an f2619c;
    /* renamed from: d */
    final /* synthetic */ AppLovinAdView f2620d;
    /* renamed from: e */
    final /* synthetic */ AppLovinAdServiceImpl f2621e;

    C1290r(AppLovinAdServiceImpl appLovinAdServiceImpl, AdViewControllerImpl adViewControllerImpl, Uri uri, an anVar, AppLovinAdView appLovinAdView) {
        this.f2621e = appLovinAdServiceImpl;
        this.f2617a = adViewControllerImpl;
        this.f2618b = uri;
        this.f2619c = anVar;
        this.f2620d = appLovinAdView;
    }

    public void onPostbackFailure(String str, int i) {
        this.f2621e.f1937c.post(new C1292t(this));
    }

    public void onPostbackSuccess(String str) {
        this.f2621e.f1937c.post(new C1291s(this));
    }
}
