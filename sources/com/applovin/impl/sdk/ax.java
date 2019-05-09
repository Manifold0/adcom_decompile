package com.applovin.impl.sdk;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.SoftReference;

public class ax {
    /* renamed from: a */
    protected final AppLovinSdkImpl f2082a;
    /* renamed from: b */
    protected final AppLovinAdServiceImpl f2083b;
    /* renamed from: c */
    private AppLovinAd f2084c;
    /* renamed from: d */
    private String f2085d;
    /* renamed from: e */
    private String f2086e;
    /* renamed from: f */
    private SoftReference<AppLovinAdLoadListener> f2087f;
    /* renamed from: g */
    private final Object f2088g = new Object();
    /* renamed from: h */
    private volatile String f2089h;
    /* renamed from: i */
    private fy f2090i;
    /* renamed from: j */
    private volatile boolean f2091j = false;
    /* renamed from: k */
    private SoftReference<AppLovinInterstitialAdDialog> f2092k;

    public ax(String str, AppLovinSdk appLovinSdk) {
        this.f2082a = (AppLovinSdkImpl) appLovinSdk;
        this.f2083b = (AppLovinAdServiceImpl) appLovinSdk.getAdService();
        this.f2086e = str;
    }

    /* renamed from: a */
    private void m2320a(an anVar, AppLovinAdRewardListener appLovinAdRewardListener) {
        this.f2090i = new fy(anVar, appLovinAdRewardListener, this.f2082a);
        this.f2082a.getTaskManager().m2855a(this.f2090i, fe.BACKGROUND);
    }

    /* renamed from: a */
    private void m2324a(ck ckVar, Activity activity, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        C1281h c1281h = new C1281h();
        c1281h.m2993a(appLovinAdClickListener);
        c1281h.m2994a(appLovinAdDisplayListener);
        c1281h.m2995a(appLovinAdRewardListener);
        this.f2082a.getMediationService().showAd(ckVar, this.f2085d, activity, c1281h);
        m2326a((AppLovinAd) ckVar);
    }

    /* renamed from: a */
    private void m2325a(C1227q c1227q, Context context, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        if (!c1227q.getType().equals(AppLovinAdType.INCENTIVIZED)) {
            this.f2082a.getLogger().mo4173e("IncentivizedAdController", "Failed to render an ad of type " + c1227q.getType() + " in an Incentivized Ad interstitial.");
            m2328a((AppLovinAd) c1227q, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
        } else if (gd.m2947a((AppLovinAd) c1227q, this.f2082a)) {
            if (c1227q.mo3995m() == C1288o.DIRECT) {
                if (!gd.m2946a(c1227q instanceof aq ? (an) this.f2082a.m2142c().mo4141c(c1227q.mo3997t()) : (an) c1227q, context, this.f2082a)) {
                    this.f2082a.getLogger().userError("IncentivizedAdController", "Failed to render an ad: video cache has been removed.");
                    m2328a((AppLovinAd) c1227q, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
                    return;
                }
            }
            Runnable ayVar = new ay(this, c1227q, context, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
            boolean booleanValue = ((Boolean) this.f2082a.get(ea.ap)).booleanValue();
            if (booleanValue && (context instanceof Activity) && !((Activity) context).isFinishing()) {
                bg.m2356a().m2370a(this.f2082a).m2369a((Activity) context).m2371a(this).m2372a(appLovinAdRewardListener).m2373a(ayVar).m2368a().m2362a((AppLovinAd) c1227q);
                return;
            }
            if (booleanValue) {
                this.f2082a.getLogger().userError("IncentivizedAdController", "Unable to show Incentivized Ad prompt. Must pass in an active Activity context.");
            }
            ayVar.run();
        } else {
            m2328a((AppLovinAd) c1227q, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
        }
    }

    /* renamed from: a */
    private void m2326a(AppLovinAd appLovinAd) {
        if (this.f2084c != null) {
            if (this.f2084c instanceof aq) {
                if (appLovinAd == ((aq) this.f2084c).mo4000a()) {
                    this.f2084c = null;
                }
            } else if (appLovinAd == this.f2084c) {
                this.f2084c = null;
            }
        }
        this.f2085d = null;
    }

    /* renamed from: a */
    private void m2327a(AppLovinAd appLovinAd, Context context, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        C1227q c1227q = appLovinAd != null ? (C1227q) appLovinAd : (C1227q) this.f2084c;
        if (c1227q != null) {
            if ((c1227q.mo3995m() == C1288o.INDIRECT ? 1 : null) != null) {
                AppLovinAd a = gd.m2936a((AppLovinAd) c1227q, this.f2082a);
                if (!(a instanceof ck)) {
                    this.f2082a.getLogger().userError("IncentivizedAdController", "Skipping mediated incentivized video playback: an unknown ad was pre-loaded: '" + a + "'");
                    m2333e();
                    return;
                } else if (context instanceof Activity) {
                    m2324a((ck) a, (Activity) context, appLovinAdRewardListener, appLovinAdDisplayListener, appLovinAdClickListener);
                    return;
                } else {
                    this.f2082a.getLogger().userError("IncentivizedAdController", "Skipping incentivized video playback: Activity required.");
                    m2328a((AppLovinAd) c1227q, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
                    return;
                }
            }
            m2325a(c1227q, context, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
            return;
        }
        this.f2082a.getLogger().userError("IncentivizedAdController", "Skipping incentivized video playback: user attempted to play an incentivized video before one was preloaded.");
        m2333e();
    }

    /* renamed from: a */
    private void m2328a(AppLovinAd appLovinAd, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener) {
        bv.m2405a(appLovinAdVideoPlaybackListener, appLovinAd, 0.0d, false, this.f2082a);
        bv.m2409b(appLovinAdDisplayListener, appLovinAd, this.f2082a);
    }

    /* renamed from: e */
    private void m2333e() {
        if (this.f2087f != null) {
            AppLovinAdLoadListener appLovinAdLoadListener = (AppLovinAdLoadListener) this.f2087f.get();
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.failedToReceiveAd(AppLovinErrorCodes.INCENTIVIZED_NO_AD_PRELOADED);
            }
        }
    }

    /* renamed from: f */
    private AppLovinAdRewardListener m2334f() {
        return new az(this);
    }

    /* renamed from: a */
    public void m2335a(AppLovinAd appLovinAd, Context context, String str, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        AppLovinAdRewardListener f = appLovinAdRewardListener == null ? m2334f() : appLovinAdRewardListener;
        this.f2085d = str;
        m2327a(appLovinAd, context, f, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
    }

    /* renamed from: a */
    void m2336a(AppLovinAd appLovinAd, AppLovinAdRewardListener appLovinAdRewardListener) {
        bv.m2403a(appLovinAdRewardListener, appLovinAd, this.f2082a);
    }

    /* renamed from: a */
    public void m2337a(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.f2082a.getLogger().mo4172d("IncentivizedAdController", "User requested preload of incentivized ad...");
        this.f2087f = new SoftReference(appLovinAdLoadListener);
        if (m2340a()) {
            this.f2082a.getLogger().userError("IncentivizedAdController", "Attempted to call preloadAndNotify: while an ad was already loaded or currently being played. Do not call preloadAndNotify: again until the last ad has been closed (adHidden).");
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.adReceived(this.f2084c);
                return;
            }
            return;
        }
        mo4114b(new ba(this, appLovinAdLoadListener));
    }

    /* renamed from: a */
    void m2338a(String str) {
        synchronized (this.f2088g) {
            this.f2089h = str;
        }
    }

    /* renamed from: a */
    void m2339a(String str, Context context) {
        if (str != null && ((Boolean) this.f2082a.get(ea.aq)).booleanValue()) {
            new be(this.f2082a, context, str).m2352a();
        }
    }

    /* renamed from: a */
    public boolean m2340a() {
        return this.f2084c != null;
    }

    /* renamed from: b */
    String m2341b() {
        String str;
        synchronized (this.f2088g) {
            str = this.f2089h;
        }
        return str;
    }

    /* renamed from: b */
    void mo4114b(AppLovinAdLoadListener appLovinAdLoadListener) {
        if (TextUtils.isEmpty(this.f2086e)) {
            this.f2083b.m2135a(appLovinAdLoadListener);
        } else {
            this.f2083b.loadNextAdForZoneId(this.f2086e, appLovinAdLoadListener);
        }
    }

    /* renamed from: c */
    public String m2343c() {
        return this.f2086e;
    }

    /* renamed from: d */
    public void m2344d() {
        if (this.f2092k != null) {
            AppLovinInterstitialAdDialog appLovinInterstitialAdDialog = (AppLovinInterstitialAdDialog) this.f2092k.get();
            if (appLovinInterstitialAdDialog != null) {
                appLovinInterstitialAdDialog.dismiss();
            }
        }
    }
}
