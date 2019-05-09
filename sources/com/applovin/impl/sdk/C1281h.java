package com.applovin.impl.sdk;

import android.os.Handler;
import android.os.Looper;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import java.lang.ref.WeakReference;
import java.util.Map;

/* renamed from: com.applovin.impl.sdk.h */
public class C1281h {
    /* renamed from: a */
    private static final Handler f2582a = new Handler(Looper.getMainLooper());
    /* renamed from: b */
    private WeakReference<AppLovinAdDisplayListener> f2583b = new WeakReference(null);
    /* renamed from: c */
    private WeakReference<AppLovinAdClickListener> f2584c = new WeakReference(null);
    /* renamed from: d */
    private WeakReference<AppLovinAdRewardListener> f2585d = new WeakReference(null);
    /* renamed from: e */
    private AppLovinAdDisplayListener f2586e;
    /* renamed from: f */
    private AppLovinAdClickListener f2587f;

    /* renamed from: a */
    void m2992a(AppLovinAd appLovinAd) {
        AppLovinAdDisplayListener appLovinAdDisplayListener = (AppLovinAdDisplayListener) this.f2583b.get();
        if (appLovinAdDisplayListener != null) {
            f2582a.post(new C1282i(this, appLovinAdDisplayListener, appLovinAd));
        }
        if (this.f2586e != null) {
            this.f2586e.adDisplayed(appLovinAd);
        }
    }

    /* renamed from: a */
    public void m2993a(AppLovinAdClickListener appLovinAdClickListener) {
        this.f2584c = new WeakReference(appLovinAdClickListener);
    }

    /* renamed from: a */
    public void m2994a(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.f2583b = new WeakReference(appLovinAdDisplayListener);
    }

    /* renamed from: a */
    public void m2995a(AppLovinAdRewardListener appLovinAdRewardListener) {
        this.f2585d = new WeakReference(appLovinAdRewardListener);
    }

    /* renamed from: a */
    void m2996a(Map<String, String> map, ck ckVar) {
        AppLovinAdRewardListener appLovinAdRewardListener = (AppLovinAdRewardListener) this.f2585d.get();
        if (appLovinAdRewardListener != null) {
            appLovinAdRewardListener.userRewardVerified(ckVar, map);
        }
    }

    /* renamed from: b */
    void m2997b(AppLovinAd appLovinAd) {
        AppLovinAdDisplayListener appLovinAdDisplayListener = (AppLovinAdDisplayListener) this.f2583b.get();
        if (appLovinAdDisplayListener != null) {
            f2582a.post(new C1283j(this, appLovinAdDisplayListener, appLovinAd));
        }
        if (this.f2586e != null) {
            this.f2586e.adHidden(appLovinAd);
        }
    }

    /* renamed from: b */
    void m2998b(AppLovinAdClickListener appLovinAdClickListener) {
        this.f2587f = appLovinAdClickListener;
    }

    /* renamed from: b */
    void m2999b(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.f2586e = appLovinAdDisplayListener;
    }

    /* renamed from: b */
    void m3000b(Map<String, String> map, ck ckVar) {
        AppLovinAdRewardListener appLovinAdRewardListener = (AppLovinAdRewardListener) this.f2585d.get();
        if (appLovinAdRewardListener != null) {
            appLovinAdRewardListener.userRewardRejected(ckVar, map);
        }
    }

    /* renamed from: c */
    void m3001c(AppLovinAd appLovinAd) {
        AppLovinAdClickListener appLovinAdClickListener = (AppLovinAdClickListener) this.f2584c.get();
        if (appLovinAdClickListener != null) {
            f2582a.post(new C1284k(this, appLovinAdClickListener, appLovinAd));
        }
        if (this.f2587f != null) {
            this.f2587f.adClicked(appLovinAd);
        }
    }
}
