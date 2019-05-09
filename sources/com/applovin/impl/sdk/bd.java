package com.applovin.impl.sdk;

import android.content.Context;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Map;

class bd implements AppLovinAdClickListener, AppLovinAdDisplayListener, AppLovinAdRewardListener, AppLovinAdVideoPlaybackListener {
    /* renamed from: a */
    final /* synthetic */ ax f2127a;
    /* renamed from: b */
    private final Context f2128b;
    /* renamed from: c */
    private final AppLovinAdDisplayListener f2129c;
    /* renamed from: d */
    private final AppLovinAdClickListener f2130d;
    /* renamed from: e */
    private final AppLovinAdVideoPlaybackListener f2131e;
    /* renamed from: f */
    private final AppLovinAdRewardListener f2132f;

    private bd(ax axVar, Context context, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        this.f2127a = axVar;
        this.f2129c = appLovinAdDisplayListener;
        this.f2130d = appLovinAdClickListener;
        this.f2131e = appLovinAdVideoPlaybackListener;
        this.f2132f = appLovinAdRewardListener;
        this.f2128b = context;
    }

    /* renamed from: a */
    private void m2348a(an anVar) {
        String b = this.f2127a.m2341b();
        if (AppLovinSdkUtils.isValidString(b) && this.f2127a.f2091j) {
            this.f2127a.m2339a(b, this.f2128b);
        } else {
            String str;
            int i;
            this.f2127a.f2090i.m2900a(true);
            if (this.f2127a.f2091j) {
                str = "network_timeout";
                i = AppLovinErrorCodes.INCENTIVIZED_SERVER_TIMEOUT;
            } else {
                str = "user_closed_video";
                i = AppLovinErrorCodes.INCENTIVIZED_USER_CLOSED_VIDEO;
            }
            dn.m2591a().m2593a(anVar, str);
            if (this.f2127a.f2091j) {
                this.f2127a.m2339a(b, this.f2128b);
            }
            bv.m2402a(this.f2132f, (AppLovinAd) anVar, i, this.f2127a.f2082a);
        }
        this.f2127a.m2326a((AppLovinAd) anVar);
        bv.m2409b(this.f2129c, anVar, this.f2127a.f2082a);
        if (!anVar.ae().getAndSet(true)) {
            this.f2127a.f2082a.getTaskManager().m2855a(new fu(anVar, this.f2127a.f2082a), fe.BACKGROUND);
        }
    }

    /* renamed from: a */
    private void m2349a(ck ckVar) {
        this.f2127a.f2082a.getLogger().mo4172d("IncentivizedAdController", "Handling ad hidden for mediated ad...");
        bv.m2409b(this.f2129c, ckVar, this.f2127a.f2082a);
    }

    public void adClicked(AppLovinAd appLovinAd) {
        bv.m2400a(this.f2130d, appLovinAd, this.f2127a.f2082a);
    }

    public void adDisplayed(AppLovinAd appLovinAd) {
        bv.m2401a(this.f2129c, appLovinAd, this.f2127a.f2082a);
    }

    public void adHidden(AppLovinAd appLovinAd) {
        if (appLovinAd instanceof aq) {
            Object a = ((aq) appLovinAd).mo4000a();
        } else {
            AppLovinAd appLovinAd2 = appLovinAd;
        }
        if (a instanceof an) {
            m2348a((an) a);
        } else if (a instanceof ck) {
            m2349a((ck) a);
        } else {
            this.f2127a.f2082a.getLogger().mo4173e("IncentivizedAdController", "Something is terribly wrong. Received `adHidden` callback for invalid ad of type: " + a);
        }
    }

    public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
    }

    public void userOverQuota(AppLovinAd appLovinAd, Map<String, String> map) {
        this.f2127a.m2338a("quota_exceeded");
        bv.m2410b(this.f2132f, appLovinAd, (Map) map, this.f2127a.f2082a);
    }

    public void userRewardRejected(AppLovinAd appLovinAd, Map<String, String> map) {
        this.f2127a.m2338a("rejected");
        bv.m2412c(this.f2132f, appLovinAd, (Map) map, this.f2127a.f2082a);
    }

    public void userRewardVerified(AppLovinAd appLovinAd, Map<String, String> map) {
        this.f2127a.m2338a("accepted");
        bv.m2404a(this.f2132f, appLovinAd, (Map) map, this.f2127a.f2082a);
    }

    public void validationRequestFailed(AppLovinAd appLovinAd, int i) {
        this.f2127a.m2338a("network_timeout");
        bv.m2402a(this.f2132f, appLovinAd, i, this.f2127a.f2082a);
    }

    public void videoPlaybackBegan(AppLovinAd appLovinAd) {
        bv.m2406a(this.f2131e, appLovinAd, this.f2127a.f2082a);
    }

    public void videoPlaybackEnded(AppLovinAd appLovinAd, double d, boolean z) {
        bv.m2405a(this.f2131e, appLovinAd, d, z, this.f2127a.f2082a);
        this.f2127a.f2091j = z;
    }
}
