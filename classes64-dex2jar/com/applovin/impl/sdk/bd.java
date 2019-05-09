// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Map;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinSdkUtils;
import android.content.Context;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdClickListener;

class bd implements AppLovinAdClickListener, AppLovinAdDisplayListener, AppLovinAdRewardListener, AppLovinAdVideoPlaybackListener
{
    final /* synthetic */ ax a;
    private final Context b;
    private final AppLovinAdDisplayListener c;
    private final AppLovinAdClickListener d;
    private final AppLovinAdVideoPlaybackListener e;
    private final AppLovinAdRewardListener f;
    
    private bd(final ax a, final Context b, final AppLovinAdRewardListener f, final AppLovinAdVideoPlaybackListener e, final AppLovinAdDisplayListener c, final AppLovinAdClickListener d) {
        this.a = a;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.b = b;
    }
    
    private void a(final an an) {
        final String b = this.a.b();
        if (AppLovinSdkUtils.isValidString(b) && this.a.j) {
            this.a.a(b, this.b);
        }
        else {
            this.a.i.a(true);
            String s;
            int n;
            if (this.a.j) {
                s = "network_timeout";
                n = -500;
            }
            else {
                s = "user_closed_video";
                n = -600;
            }
            dn.a().a(an, s);
            if (this.a.j) {
                this.a.a(b, this.b);
            }
            bv.a(this.f, an, n, this.a.a);
        }
        this.a.a(an);
        bv.b(this.c, an, this.a.a);
        if (!an.ae().getAndSet(true)) {
            this.a.a.getTaskManager().a(new fu(an, this.a.a), fe.b);
        }
    }
    
    private void a(final ck ck) {
        this.a.a.getLogger().d("IncentivizedAdController", "Handling ad hidden for mediated ad...");
        bv.b(this.c, ck, this.a.a);
    }
    
    @Override
    public void adClicked(final AppLovinAd appLovinAd) {
        bv.a(this.d, appLovinAd, this.a.a);
    }
    
    @Override
    public void adDisplayed(final AppLovinAd appLovinAd) {
        bv.a(this.c, appLovinAd, this.a.a);
    }
    
    @Override
    public void adHidden(AppLovinAd a) {
        if (a instanceof aq) {
            a = ((aq)a).a();
        }
        if (a instanceof an) {
            this.a((an)a);
            return;
        }
        if (a instanceof ck) {
            this.a((ck)a);
            return;
        }
        this.a.a.getLogger().e("IncentivizedAdController", "Something is terribly wrong. Received `adHidden` callback for invalid ad of type: " + a);
    }
    
    @Override
    public void userDeclinedToViewAd(final AppLovinAd appLovinAd) {
    }
    
    @Override
    public void userOverQuota(final AppLovinAd appLovinAd, final Map<String, String> map) {
        this.a.a("quota_exceeded");
        bv.b(this.f, appLovinAd, map, this.a.a);
    }
    
    @Override
    public void userRewardRejected(final AppLovinAd appLovinAd, final Map<String, String> map) {
        this.a.a("rejected");
        bv.c(this.f, appLovinAd, map, this.a.a);
    }
    
    @Override
    public void userRewardVerified(final AppLovinAd appLovinAd, final Map<String, String> map) {
        this.a.a("accepted");
        bv.a(this.f, appLovinAd, map, this.a.a);
    }
    
    @Override
    public void validationRequestFailed(final AppLovinAd appLovinAd, final int n) {
        this.a.a("network_timeout");
        bv.a(this.f, appLovinAd, n, this.a.a);
    }
    
    @Override
    public void videoPlaybackBegan(final AppLovinAd appLovinAd) {
        bv.a(this.e, appLovinAd, this.a.a);
    }
    
    @Override
    public void videoPlaybackEnded(final AppLovinAd appLovinAd, final double n, final boolean b) {
        bv.a(this.e, appLovinAd, n, b, this.a.a);
        this.a.j = b;
    }
}
