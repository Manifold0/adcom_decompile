package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.List;

abstract class en extends dx {
    /* renamed from: a */
    protected AppLovinNativeAdLoadListener f2468a;
    /* renamed from: b */
    protected AppLovinNativeAdPrecacheListener f2469b;
    /* renamed from: h */
    private List<NativeAdImpl> f2470h;
    /* renamed from: i */
    private int f2471i = 0;

    en(String str, AppLovinSdkImpl appLovinSdkImpl, List<NativeAdImpl> list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super(str, appLovinSdkImpl);
        this.f2468a = appLovinNativeAdLoadListener;
        this.f2470h = list;
    }

    en(String str, AppLovinSdkImpl appLovinSdkImpl, List<NativeAdImpl> list, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        super(str, appLovinSdkImpl);
        if (list == null) {
            throw new IllegalArgumentException("Slots cannot be null");
        }
        this.f2470h = list;
        this.f2469b = appLovinNativeAdPrecacheListener;
    }

    /* renamed from: a */
    private void m2772a(int i) {
        if (this.f2468a != null) {
            this.f2468a.onNativeAdsFailedToLoad(i);
        }
    }

    /* renamed from: a */
    private void m2773a(List<AppLovinNativeAd> list) {
        if (this.f2468a != null) {
            this.f2468a.onNativeAdsLoaded(list);
        }
    }

    /* renamed from: a */
    protected String m2774a(String str, av avVar, List<String> list) {
        if (!AppLovinSdkUtils.isValidString(str)) {
            this.d.getLogger().mo4172d(m2482a(), "Asked to cache file with null/empty URL, nothing to do.");
            return null;
        } else if (gd.m2948a(str, (List) list)) {
            try {
                String a = avVar.m2299a(this.f, str, null, list, true, true, null);
                if (a != null) {
                    return a;
                }
                this.e.mo4178w(m2482a(), "Unable to cache icon resource " + str);
                return null;
            } catch (Throwable e) {
                this.e.mo4179w(m2482a(), "Unable to cache icon resource " + str, e);
                return null;
            }
        } else {
            this.d.getLogger().mo4172d(m2482a(), "Domain is not whitelisted, skipping precache for URL " + str);
            return null;
        }
    }

    /* renamed from: a */
    protected abstract void mo4162a(NativeAdImpl nativeAdImpl);

    /* renamed from: a */
    protected abstract void mo4163a(NativeAdImpl nativeAdImpl, int i);

    /* renamed from: a */
    protected abstract boolean mo4164a(NativeAdImpl nativeAdImpl, av avVar);

    public void run() {
        for (NativeAdImpl nativeAdImpl : this.f2470h) {
            av fileManager = this.d.getFileManager();
            this.d.getLogger().mo4172d(m2482a(), "Beginning resource caching phase...");
            if (mo4164a(nativeAdImpl, fileManager)) {
                this.f2471i++;
                mo4162a(nativeAdImpl);
            } else {
                this.d.getLogger().mo4173e(m2482a(), "Unable to cache resources");
            }
        }
        try {
            if (this.f2471i == this.f2470h.size()) {
                m2773a(this.f2470h);
            } else if (((Boolean) this.d.get(ea.bv)).booleanValue()) {
                this.d.getLogger().mo4173e(m2482a(), "Mismatch between successful populations and requested size");
                m2772a(-6);
            } else {
                m2773a(this.f2470h);
            }
        } catch (Throwable th) {
            this.d.getLogger().userError(m2482a(), "Encountered exception while notifying publisher code", th);
        }
    }
}
