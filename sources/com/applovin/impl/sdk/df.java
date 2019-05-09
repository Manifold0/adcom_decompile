package com.applovin.impl.sdk;

import android.text.TextUtils;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.nativeAds.AppLovinNativeAdService;
import com.applovin.sdk.AppLovinErrorCodes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class df implements AppLovinNativeAdService {
    /* renamed from: a */
    private final AppLovinSdkImpl f2317a;

    df(AppLovinSdkImpl appLovinSdkImpl) {
        this.f2317a = appLovinSdkImpl;
    }

    /* renamed from: a */
    private List<NativeAdImpl> m2574a(AppLovinNativeAd appLovinNativeAd) {
        List<NativeAdImpl> arrayList = new ArrayList(1);
        arrayList.add((NativeAdImpl) appLovinNativeAd);
        return arrayList;
    }

    /* renamed from: a */
    private void m2581a(AppLovinNativeAd appLovinNativeAd, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        if (appLovinNativeAd.isVideoPrecached()) {
            appLovinNativeAdPrecacheListener.onNativeAdVideoPreceached(appLovinNativeAd);
            return;
        }
        this.f2317a.getTaskManager().m2855a(new eo(this.f2317a, m2574a(appLovinNativeAd), new di(this, appLovinNativeAdPrecacheListener)), fe.MAIN);
    }

    /* renamed from: a */
    private void m2582a(AppLovinNativeAdLoadListener appLovinNativeAdLoadListener, int i) {
        if (appLovinNativeAdLoadListener != null) {
            try {
                appLovinNativeAdLoadListener.onNativeAdsFailedToLoad(i);
            } catch (Throwable e) {
                this.f2317a.getLogger().userError("NativeAdService", "Encountered exception whilst notifying user callback", e);
            }
        }
    }

    /* renamed from: a */
    private void m2583a(AppLovinNativeAdLoadListener appLovinNativeAdLoadListener, List<AppLovinNativeAd> list) {
        if (appLovinNativeAdLoadListener != null) {
            try {
                appLovinNativeAdLoadListener.onNativeAdsLoaded(list);
            } catch (Throwable e) {
                this.f2317a.getLogger().userError("NativeAdService", "Encountered exception whilst notifying user callback", e);
            }
        }
    }

    /* renamed from: a */
    private void m2584a(AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener, AppLovinNativeAd appLovinNativeAd, int i, boolean z) {
        if (appLovinNativeAdPrecacheListener == null) {
            return;
        }
        if (z) {
            try {
                appLovinNativeAdPrecacheListener.onNativeAdVideoPrecachingFailed(appLovinNativeAd, i);
                return;
            } catch (Throwable e) {
                this.f2317a.getLogger().userError("NativeAdService", "Encountered exception whilst notifying user callback", e);
                return;
            }
        }
        appLovinNativeAdPrecacheListener.onNativeAdImagePrecachingFailed(appLovinNativeAd, i);
    }

    /* renamed from: a */
    private void m2585a(AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener, AppLovinNativeAd appLovinNativeAd, boolean z) {
        if (appLovinNativeAdPrecacheListener == null) {
            return;
        }
        if (z) {
            try {
                appLovinNativeAdPrecacheListener.onNativeAdVideoPreceached(appLovinNativeAd);
                return;
            } catch (Throwable e) {
                this.f2317a.getLogger().userError("NativeAdService", "Encountered exception whilst notifying user callback", e);
                return;
            }
        }
        appLovinNativeAdPrecacheListener.onNativeAdImagesPrecached(appLovinNativeAd);
    }

    /* renamed from: a */
    private void m2586a(String str, int i, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.f2317a.getTaskManager().m2855a(new ez(str, i, this.f2317a, new dg(this, appLovinNativeAdLoadListener)), fe.MAIN);
    }

    /* renamed from: b */
    private void m2587b(List<NativeAdImpl> list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.f2317a.getTaskManager().m2855a(new em(this.f2317a, (List) list, new dl(this, appLovinNativeAdLoadListener)), fe.MAIN);
    }

    /* renamed from: c */
    private void m2588c(List<NativeAdImpl> list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.f2317a.getTaskManager().m2855a(new eo(this.f2317a, (List) list, new dm(this, appLovinNativeAdLoadListener)), fe.MAIN);
    }

    /* renamed from: a */
    void m2589a(C1287n c1287n) {
        this.f2317a.m2142c().mo4147i(c1287n);
        int h = c1287n.m3059h();
        if (h == 0 && this.f2317a.m2142c().mo4140b(c1287n)) {
            h = 1;
        }
        this.f2317a.m2142c().mo4138b(c1287n, h);
    }

    /* renamed from: a */
    public void m2590a(List<AppLovinNativeAd> list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        int intValue = ((Integer) this.f2317a.get(ea.bE)).intValue();
        if (intValue > 0) {
            List list2 = list;
            int size = list2.size();
            if (size != 0) {
                intValue = Math.min(intValue, size);
                List subList = list2.subList(0, intValue);
                m2587b(subList, new dj(this, subList, appLovinNativeAdLoadListener, list2.subList(intValue, size)));
            } else if (appLovinNativeAdLoadListener != null) {
                appLovinNativeAdLoadListener.onNativeAdsFailedToLoad(AppLovinErrorCodes.UNABLE_TO_PREPARE_NATIVE_AD);
            }
        } else if (appLovinNativeAdLoadListener != null) {
            appLovinNativeAdLoadListener.onNativeAdsLoaded(list);
        }
    }

    public boolean hasPreloadedAdForZoneId(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f2317a.getLogger().userError("NativeAdService", "Unable to check if ad is preloaded - invalid zone id.");
            return false;
        }
        return this.f2317a.m2143d().mo4146h(C1287n.m3037a(str, this.f2317a));
    }

    public void loadNativeAds(int i, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        loadNativeAds(i, null, appLovinNativeAdLoadListener);
    }

    public void loadNativeAds(int i, String str, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        if (i > 0) {
            this.f2317a.m2145f();
            if (i == 1) {
                C1287n b = C1287n.m3040b(str, this.f2317a);
                if (((AppLovinNativeAd) this.f2317a.m2143d().mo4143e(b)) != null) {
                    this.f2317a.getLogger().mo4172d("NativeAdService", "Preloaded native ad found");
                    m2583a(appLovinNativeAdLoadListener, Arrays.asList(new AppLovinNativeAd[]{r0}));
                } else {
                    m2586a(str, 1, appLovinNativeAdLoadListener);
                }
                if (((Boolean) this.f2317a.get(ea.bu)).booleanValue()) {
                    this.f2317a.m2143d().mo4148j(b);
                    return;
                }
                return;
            }
            m2586a(str, i, appLovinNativeAdLoadListener);
            return;
        }
        this.f2317a.getLogger().userError("NativeAdService", "Requested invalid number of native ads: " + i);
    }

    public void precacheResources(AppLovinNativeAd appLovinNativeAd, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        this.f2317a.m2145f();
        if (appLovinNativeAd.isImagePrecached()) {
            appLovinNativeAdPrecacheListener.onNativeAdImagesPrecached(appLovinNativeAd);
            m2581a(appLovinNativeAd, appLovinNativeAdPrecacheListener);
            return;
        }
        this.f2317a.getTaskManager().m2855a(new em(this.f2317a, m2574a(appLovinNativeAd), new dh(this, appLovinNativeAdPrecacheListener)), fe.MAIN);
    }

    public void preloadAdForZoneId(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f2317a.getLogger().userError("NativeAdService", "Unable to preload zone for invalid zone id.");
            return;
        }
        this.f2317a.m2145f();
        C1287n a = C1287n.m3037a(str, this.f2317a);
        this.f2317a.m2142c().mo4147i(a);
        this.f2317a.m2142c().mo4148j(a);
    }
}
