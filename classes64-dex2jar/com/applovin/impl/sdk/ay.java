// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.adview.AppLovinInterstitialAdDialog;
import java.lang.ref.SoftReference;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import android.content.Context;

class ay implements Runnable
{
    final /* synthetic */ q a;
    final /* synthetic */ Context b;
    final /* synthetic */ AppLovinAdRewardListener c;
    final /* synthetic */ AppLovinAdVideoPlaybackListener d;
    final /* synthetic */ AppLovinAdDisplayListener e;
    final /* synthetic */ AppLovinAdClickListener f;
    final /* synthetic */ ax g;
    
    ay(final ax g, final q a, final Context b, final AppLovinAdRewardListener c, final AppLovinAdVideoPlaybackListener d, final AppLovinAdDisplayListener e, final AppLovinAdClickListener f) {
        this.g = g;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    @Override
    public void run() {
        final AppLovinAd a = gd.a(this.a, (AppLovinSdk)this.g.a);
        if (a != null) {
            final AppLovinInterstitialAdDialog create = AppLovinInterstitialAd.create(this.g.a, this.b);
            final bd adClickListener = new bd(this.g, this.b, this.c, this.d, this.e, this.f, null);
            create.setAdDisplayListener(adClickListener);
            create.setAdVideoPlaybackListener(adClickListener);
            create.setAdClickListener(adClickListener);
            create.showAndRender(a, this.g.d);
            this.g.k = (SoftReference<AppLovinInterstitialAdDialog>)new SoftReference(create);
            if (a instanceof an) {
                this.g.a((an)a, adClickListener);
            }
            return;
        }
        this.g.a(this.a, this.d, this.e);
    }
}
