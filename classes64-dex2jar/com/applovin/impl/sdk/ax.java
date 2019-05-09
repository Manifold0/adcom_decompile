// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.text.TextUtils;
import com.applovin.sdk.AppLovinAdType;
import android.content.Context;
import com.applovin.sdk.AppLovinAdClickListener;
import android.app.Activity;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAdLoadListener;
import java.lang.ref.SoftReference;
import com.applovin.sdk.AppLovinAd;

public class ax
{
    protected final AppLovinSdkImpl a;
    protected final AppLovinAdServiceImpl b;
    private AppLovinAd c;
    private String d;
    private String e;
    private SoftReference<AppLovinAdLoadListener> f;
    private final Object g;
    private volatile String h;
    private fy i;
    private volatile boolean j;
    private SoftReference<AppLovinInterstitialAdDialog> k;
    
    public ax(final String e, final AppLovinSdk appLovinSdk) {
        this.g = new Object();
        this.j = false;
        this.a = (AppLovinSdkImpl)appLovinSdk;
        this.b = (AppLovinAdServiceImpl)appLovinSdk.getAdService();
        this.e = e;
    }
    
    private void a(final an an, final AppLovinAdRewardListener appLovinAdRewardListener) {
        this.i = new fy(an, appLovinAdRewardListener, this.a);
        this.a.getTaskManager().a(this.i, fe.b);
    }
    
    private void a(final ck ck, final Activity activity, final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAdDisplayListener appLovinAdDisplayListener, final AppLovinAdClickListener appLovinAdClickListener) {
        final h h = new h();
        h.a(appLovinAdClickListener);
        h.a(appLovinAdDisplayListener);
        h.a(appLovinAdRewardListener);
        this.a.getMediationService().showAd(ck, this.d, activity, h);
        this.a(ck);
    }
    
    private void a(final q q, final Context context, final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, final AppLovinAdDisplayListener appLovinAdDisplayListener, final AppLovinAdClickListener appLovinAdClickListener) {
        if (!q.getType().equals(AppLovinAdType.INCENTIVIZED)) {
            this.a.getLogger().e("IncentivizedAdController", "Failed to render an ad of type " + q.getType() + " in an Incentivized Ad interstitial.");
            this.a(q, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
            return;
        }
        if (!gd.a(q, this.a)) {
            this.a(q, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
            return;
        }
        if (q.m() == o.b) {
            an an;
            if (q instanceof aq) {
                an = (an)this.a.c().c(q.t());
            }
            else {
                an = (an)q;
            }
            if (!gd.a(an, context, this.a)) {
                this.a.getLogger().userError("IncentivizedAdController", "Failed to render an ad: video cache has been removed.");
                this.a(q, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
                return;
            }
        }
        final ay ay = new ay(this, q, context, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
        final boolean booleanValue = this.a.get(ea.ap);
        if (booleanValue && context instanceof Activity && !((Activity)context).isFinishing()) {
            bg.a().a(this.a).a((Activity)context).a(this).a(appLovinAdRewardListener).a(ay).a().a(q);
            return;
        }
        if (booleanValue) {
            this.a.getLogger().userError("IncentivizedAdController", "Unable to show Incentivized Ad prompt. Must pass in an active Activity context.");
        }
        ay.run();
    }
    
    private void a(final AppLovinAd appLovinAd) {
        if (this.c != null) {
            if (this.c instanceof aq) {
                if (appLovinAd == ((aq)this.c).a()) {
                    this.c = null;
                }
            }
            else if (appLovinAd == this.c) {
                this.c = null;
            }
        }
        this.d = null;
    }
    
    private void a(final AppLovinAd appLovinAd, final Context context, final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, final AppLovinAdDisplayListener appLovinAdDisplayListener, final AppLovinAdClickListener appLovinAdClickListener) {
        q q;
        if (appLovinAd != null) {
            q = (q)appLovinAd;
        }
        else {
            q = (q)this.c;
        }
        if (q == null) {
            this.a.getLogger().userError("IncentivizedAdController", "Skipping incentivized video playback: user attempted to play an incentivized video before one was preloaded.");
            this.e();
            return;
        }
        int n;
        if (q.m() == o.c) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n == 0) {
            this.a(q, context, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
            return;
        }
        final AppLovinAd a = gd.a(q, (AppLovinSdk)this.a);
        if (!(a instanceof ck)) {
            this.a.getLogger().userError("IncentivizedAdController", "Skipping mediated incentivized video playback: an unknown ad was pre-loaded: '" + a + "'");
            this.e();
            return;
        }
        if (context instanceof Activity) {
            this.a((ck)a, (Activity)context, appLovinAdRewardListener, appLovinAdDisplayListener, appLovinAdClickListener);
            return;
        }
        this.a.getLogger().userError("IncentivizedAdController", "Skipping incentivized video playback: Activity required.");
        this.a(q, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
    }
    
    private void a(final AppLovinAd appLovinAd, final AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, final AppLovinAdDisplayListener appLovinAdDisplayListener) {
        bv.a(appLovinAdVideoPlaybackListener, appLovinAd, 0.0, false, this.a);
        bv.b(appLovinAdDisplayListener, appLovinAd, this.a);
    }
    
    private void e() {
        if (this.f != null) {
            final AppLovinAdLoadListener appLovinAdLoadListener = this.f.get();
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.failedToReceiveAd(-300);
            }
        }
    }
    
    private AppLovinAdRewardListener f() {
        return new az(this);
    }
    
    public void a(final AppLovinAd appLovinAd, final Context context, final String d, AppLovinAdRewardListener f, final AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, final AppLovinAdDisplayListener appLovinAdDisplayListener, final AppLovinAdClickListener appLovinAdClickListener) {
        if (f == null) {
            f = this.f();
        }
        this.d = d;
        this.a(appLovinAd, context, f, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
    }
    
    void a(final AppLovinAd appLovinAd, final AppLovinAdRewardListener appLovinAdRewardListener) {
        bv.a(appLovinAdRewardListener, appLovinAd, this.a);
    }
    
    public void a(final AppLovinAdLoadListener appLovinAdLoadListener) {
        this.a.getLogger().d("IncentivizedAdController", "User requested preload of incentivized ad...");
        this.f = new SoftReference<AppLovinAdLoadListener>(appLovinAdLoadListener);
        if (this.a()) {
            this.a.getLogger().userError("IncentivizedAdController", "Attempted to call preloadAndNotify: while an ad was already loaded or currently being played. Do not call preloadAndNotify: again until the last ad has been closed (adHidden).");
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.adReceived(this.c);
            }
            return;
        }
        this.b(new ba(this, appLovinAdLoadListener));
    }
    
    void a(final String h) {
        synchronized (this.g) {
            this.h = h;
        }
    }
    
    void a(final String s, final Context context) {
        if (s != null && this.a.get(ea.aq)) {
            new be(this.a, context, s).a();
        }
    }
    
    public boolean a() {
        return this.c != null;
    }
    
    String b() {
        synchronized (this.g) {
            return this.h;
        }
    }
    
    void b(final AppLovinAdLoadListener appLovinAdLoadListener) {
        if (!TextUtils.isEmpty((CharSequence)this.e)) {
            this.b.loadNextAdForZoneId(this.e, appLovinAdLoadListener);
            return;
        }
        this.b.a(appLovinAdLoadListener);
    }
    
    public String c() {
        return this.e;
    }
    
    public void d() {
        if (this.k != null) {
            final AppLovinInterstitialAdDialog appLovinInterstitialAdDialog = this.k.get();
            if (appLovinInterstitialAdDialog != null) {
                appLovinInterstitialAdDialog.dismiss();
            }
        }
    }
}
