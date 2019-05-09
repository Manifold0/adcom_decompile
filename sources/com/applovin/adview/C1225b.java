package com.applovin.adview;

import android.content.Context;
import com.applovin.impl.adview.InterstitialAdDialogCreatorImpl;
import com.applovin.sdk.AppLovinSdk;

/* renamed from: com.applovin.adview.b */
final class C1225b implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinSdk f1566a;
    /* renamed from: b */
    final /* synthetic */ Context f1567b;
    /* renamed from: c */
    final /* synthetic */ String f1568c;

    C1225b(AppLovinSdk appLovinSdk, Context context, String str) {
        this.f1566a = appLovinSdk;
        this.f1567b = context;
        this.f1568c = str;
    }

    public void run() {
        new InterstitialAdDialogCreatorImpl().createInterstitialAdDialog(this.f1566a, this.f1567b).show(this.f1568c);
    }
}
