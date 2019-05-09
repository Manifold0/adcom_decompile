package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdUpdateListener;
import java.util.Collection;
import java.util.HashSet;

/* renamed from: com.applovin.impl.sdk.u */
class C1293u implements AppLovinAdLoadListener {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdServiceImpl f2624a;
    /* renamed from: b */
    private final C1294v f2625b;

    private C1293u(AppLovinAdServiceImpl appLovinAdServiceImpl, C1294v c1294v) {
        this.f2624a = appLovinAdServiceImpl;
        this.f2625b = c1294v;
    }

    public void adReceived(AppLovinAd appLovinAd) {
        C1287n t = ((C1227q) appLovinAd).mo3997t();
        if (!(appLovinAd instanceof aq) && t.m3063l()) {
            this.f2624a.f1935a.m2142c().adReceived(appLovinAd);
            appLovinAd = new aq(t, this.f2624a.f1935a);
        }
        synchronized (this.f2625b.f2626a) {
            if (t.m3060i()) {
                long j = t.m3061j();
                if (j > 0) {
                    this.f2625b.f2628c = (j * 1000) + System.currentTimeMillis();
                } else if (j == 0) {
                    this.f2625b.f2628c = Long.MAX_VALUE;
                }
                this.f2625b.f2627b = appLovinAd;
            } else {
                this.f2625b.f2627b = null;
                this.f2625b.f2628c = 0;
            }
            Collection<AppLovinAdLoadListener> hashSet = new HashSet(this.f2625b.f2631f);
            this.f2625b.f2631f.clear();
            Collection<AppLovinAdUpdateListener> hashSet2 = new HashSet(this.f2625b.f2630e);
            this.f2625b.f2629d = false;
        }
        this.f2624a.m2132b(t);
        for (AppLovinAdLoadListener adReceived : hashSet) {
            try {
                adReceived.adReceived(appLovinAd);
            } catch (Throwable th) {
                this.f2624a.f1936b.mo4174e("AppLovinAdService", "Unable to notify listener about a newly loaded ad", th);
            }
        }
        for (AppLovinAdUpdateListener adUpdated : hashSet2) {
            try {
                adUpdated.adUpdated(appLovinAd);
            } catch (Throwable th2) {
                this.f2624a.f1936b.mo4174e("AppLovinAdService", "Unable to notify listener about an updated loaded ad", th2);
            }
        }
    }

    public void failedToReceiveAd(int i) {
        synchronized (this.f2625b.f2626a) {
            Collection<AppLovinAdLoadListener> hashSet = new HashSet(this.f2625b.f2631f);
            this.f2625b.f2631f.clear();
            this.f2625b.f2629d = false;
        }
        for (AppLovinAdLoadListener failedToReceiveAd : hashSet) {
            try {
                failedToReceiveAd.failedToReceiveAd(i);
            } catch (Throwable th) {
                this.f2624a.f1936b.mo4174e("AppLovinAdService", "Unable to notify listener about ad load failure", th);
            }
        }
    }
}
