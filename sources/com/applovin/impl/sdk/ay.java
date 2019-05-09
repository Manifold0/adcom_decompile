package com.applovin.impl.sdk;

import android.content.Context;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import java.lang.ref.SoftReference;

class ay implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C1227q f2093a;
    /* renamed from: b */
    final /* synthetic */ Context f2094b;
    /* renamed from: c */
    final /* synthetic */ AppLovinAdRewardListener f2095c;
    /* renamed from: d */
    final /* synthetic */ AppLovinAdVideoPlaybackListener f2096d;
    /* renamed from: e */
    final /* synthetic */ AppLovinAdDisplayListener f2097e;
    /* renamed from: f */
    final /* synthetic */ AppLovinAdClickListener f2098f;
    /* renamed from: g */
    final /* synthetic */ ax f2099g;

    ay(ax axVar, C1227q c1227q, Context context, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        this.f2099g = axVar;
        this.f2093a = c1227q;
        this.f2094b = context;
        this.f2095c = appLovinAdRewardListener;
        this.f2096d = appLovinAdVideoPlaybackListener;
        this.f2097e = appLovinAdDisplayListener;
        this.f2098f = appLovinAdClickListener;
    }

    public void run() {
        AppLovinAd a = gd.m2936a(this.f2093a, this.f2099g.f2082a);
        if (a != null) {
            AppLovinInterstitialAdDialog create = AppLovinInterstitialAd.create(this.f2099g.f2082a, this.f2094b);
            AppLovinAdRewardListener bdVar = new bd(this.f2099g, this.f2094b, this.f2095c, this.f2096d, this.f2097e, this.f2098f, null);
            create.setAdDisplayListener(bdVar);
            create.setAdVideoPlaybackListener(bdVar);
            create.setAdClickListener(bdVar);
            create.showAndRender(a, this.f2099g.f2085d);
            this.f2099g.f2092k = new SoftReference(create);
            if (a instanceof an) {
                this.f2099g.m2320a((an) a, bdVar);
                return;
            }
            return;
        }
        this.f2099g.m2328a(this.f2093a, this.f2096d, this.f2097e);
    }
}
