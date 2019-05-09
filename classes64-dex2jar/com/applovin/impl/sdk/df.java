// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Arrays;
import android.text.TextUtils;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import java.util.ArrayList;
import java.util.List;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdService;

class df implements AppLovinNativeAdService
{
    private final AppLovinSdkImpl a;
    
    df(final AppLovinSdkImpl a) {
        this.a = a;
    }
    
    private List<NativeAdImpl> a(final AppLovinNativeAd appLovinNativeAd) {
        final ArrayList<NativeAdImpl> list = new ArrayList<NativeAdImpl>(1);
        list.add((NativeAdImpl)appLovinNativeAd);
        return list;
    }
    
    private void a(final AppLovinNativeAd appLovinNativeAd, final AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        if (appLovinNativeAd.isVideoPrecached()) {
            appLovinNativeAdPrecacheListener.onNativeAdVideoPreceached(appLovinNativeAd);
            return;
        }
        this.a.getTaskManager().a(new eo(this.a, this.a(appLovinNativeAd), new di(this, appLovinNativeAdPrecacheListener)), fe.a);
    }
    
    private void a(final AppLovinNativeAdLoadListener appLovinNativeAdLoadListener, final int n) {
        if (appLovinNativeAdLoadListener == null) {
            return;
        }
        try {
            appLovinNativeAdLoadListener.onNativeAdsFailedToLoad(n);
        }
        catch (Exception ex) {
            this.a.getLogger().userError("NativeAdService", "Encountered exception whilst notifying user callback", ex);
        }
    }
    
    private void a(final AppLovinNativeAdLoadListener appLovinNativeAdLoadListener, final List<AppLovinNativeAd> list) {
        if (appLovinNativeAdLoadListener == null) {
            return;
        }
        try {
            appLovinNativeAdLoadListener.onNativeAdsLoaded(list);
        }
        catch (Exception ex) {
            this.a.getLogger().userError("NativeAdService", "Encountered exception whilst notifying user callback", ex);
        }
    }
    
    private void a(final AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener, final AppLovinNativeAd appLovinNativeAd, final int n, final boolean b) {
        if (appLovinNativeAdPrecacheListener != null) {
            Label_0018: {
                if (!b) {
                    break Label_0018;
                }
                try {
                    appLovinNativeAdPrecacheListener.onNativeAdVideoPrecachingFailed(appLovinNativeAd, n);
                    return;
                    appLovinNativeAdPrecacheListener.onNativeAdImagePrecachingFailed(appLovinNativeAd, n);
                }
                catch (Exception ex) {
                    this.a.getLogger().userError("NativeAdService", "Encountered exception whilst notifying user callback", ex);
                }
            }
        }
    }
    
    private void a(final AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener, final AppLovinNativeAd appLovinNativeAd, final boolean b) {
        if (appLovinNativeAdPrecacheListener != null) {
            Label_0016: {
                if (!b) {
                    break Label_0016;
                }
                try {
                    appLovinNativeAdPrecacheListener.onNativeAdVideoPreceached(appLovinNativeAd);
                    return;
                    appLovinNativeAdPrecacheListener.onNativeAdImagesPrecached(appLovinNativeAd);
                }
                catch (Exception ex) {
                    this.a.getLogger().userError("NativeAdService", "Encountered exception whilst notifying user callback", ex);
                }
            }
        }
    }
    
    private void a(final String s, final int n, final AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.a.getTaskManager().a(new ez(s, n, this.a, new dg(this, appLovinNativeAdLoadListener)), fe.a);
    }
    
    private void b(final List<NativeAdImpl> list, final AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.a.getTaskManager().a(new em(this.a, list, new dl(this, appLovinNativeAdLoadListener)), fe.a);
    }
    
    private void c(final List<NativeAdImpl> list, final AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.a.getTaskManager().a(new eo(this.a, list, new dm(this, appLovinNativeAdLoadListener)), fe.a);
    }
    
    void a(final n n) {
        this.a.c().i(n);
        int h;
        final int n2 = h = n.h();
        if (n2 == 0) {
            h = n2;
            if (this.a.c().b(n)) {
                h = 1;
            }
        }
        this.a.c().b(n, h);
    }
    
    public void a(final List<AppLovinNativeAd> list, final AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        final int intValue = this.a.get(ea.bE);
        if (intValue <= 0) {
            if (appLovinNativeAdLoadListener != null) {
                appLovinNativeAdLoadListener.onNativeAdsLoaded(list);
            }
        }
        else {
            final List<AppLovinNativeAd> list2 = list;
            final int size = list2.size();
            if (size != 0) {
                final int min = Math.min(intValue, size);
                final List<NativeAdImpl> subList = list2.subList(0, min);
                this.b(subList, new dj(this, subList, appLovinNativeAdLoadListener, list2.subList(min, size)));
                return;
            }
            if (appLovinNativeAdLoadListener != null) {
                appLovinNativeAdLoadListener.onNativeAdsFailedToLoad(-700);
            }
        }
    }
    
    @Override
    public boolean hasPreloadedAdForZoneId(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            this.a.getLogger().userError("NativeAdService", "Unable to check if ad is preloaded - invalid zone id.");
            return false;
        }
        return this.a.d().h(n.a(s, this.a));
    }
    
    @Override
    public void loadNativeAds(final int n, final AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.loadNativeAds(n, null, appLovinNativeAdLoadListener);
    }
    
    @Override
    public void loadNativeAds(final int n, final String s, final AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        if (n <= 0) {
            this.a.getLogger().userError("NativeAdService", "Requested invalid number of native ads: " + n);
            return;
        }
        this.a.f();
        if (n == 1) {
            final n b = n.b(s, this.a);
            final AppLovinNativeAd appLovinNativeAd = (AppLovinNativeAd)this.a.d().e(b);
            if (appLovinNativeAd != null) {
                this.a.getLogger().d("NativeAdService", "Preloaded native ad found");
                this.a(appLovinNativeAdLoadListener, Arrays.asList(appLovinNativeAd));
            }
            else {
                this.a(s, 1, appLovinNativeAdLoadListener);
            }
            if (this.a.get(ea.bu)) {
                this.a.d().j(b);
            }
            return;
        }
        this.a(s, n, appLovinNativeAdLoadListener);
    }
    
    @Override
    public void precacheResources(final AppLovinNativeAd appLovinNativeAd, final AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        this.a.f();
        if (appLovinNativeAd.isImagePrecached()) {
            appLovinNativeAdPrecacheListener.onNativeAdImagesPrecached(appLovinNativeAd);
            this.a(appLovinNativeAd, appLovinNativeAdPrecacheListener);
            return;
        }
        this.a.getTaskManager().a(new em(this.a, this.a(appLovinNativeAd), new dh(this, appLovinNativeAdPrecacheListener)), fe.a);
    }
    
    @Override
    public void preloadAdForZoneId(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            this.a.getLogger().userError("NativeAdService", "Unable to preload zone for invalid zone id.");
            return;
        }
        this.a.f();
        final n a = n.a(s, this.a);
        this.a.c().i(a);
        this.a.c().j(a);
    }
}
