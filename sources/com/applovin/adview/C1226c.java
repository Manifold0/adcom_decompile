package com.applovin.adview;

import android.app.Activity;
import com.applovin.impl.adview.MediatedInterstitialAdDialogCreatorImpl;
import com.applovin.sdk.AppLovinSdk;

/* renamed from: com.applovin.adview.c */
final class C1226c implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinSdk f1569a;
    /* renamed from: b */
    final /* synthetic */ Activity f1570b;

    C1226c(AppLovinSdk appLovinSdk, Activity activity) {
        this.f1569a = appLovinSdk;
        this.f1570b = activity;
    }

    public void run() {
        new MediatedInterstitialAdDialogCreatorImpl().createInterstitialAdDialog(this.f1569a, this.f1570b).show();
    }
}
