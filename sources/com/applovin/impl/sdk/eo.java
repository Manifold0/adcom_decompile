package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.List;

public class eo extends en {
    public eo(AppLovinSdkImpl appLovinSdkImpl, List<NativeAdImpl> list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super("TaskCacheNativeAdVideos", appLovinSdkImpl, (List) list, appLovinNativeAdLoadListener);
    }

    public eo(AppLovinSdkImpl appLovinSdkImpl, List<NativeAdImpl> list, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        super("TaskCacheNativeAdVideos", appLovinSdkImpl, (List) list, appLovinNativeAdPrecacheListener);
    }

    /* renamed from: b */
    private boolean m2782b(NativeAdImpl nativeAdImpl) {
        this.e.mo4178w("TaskCacheNativeAdVideos", "Unable to cache video resource " + nativeAdImpl.getSourceVideoUrl());
        mo4163a(nativeAdImpl, !ag.m2242a(this.f, this.d) ? AppLovinErrorCodes.NO_NETWORK : AppLovinErrorCodes.UNABLE_TO_PRECACHE_VIDEO_RESOURCES);
        return false;
    }

    /* renamed from: a */
    protected void mo4162a(NativeAdImpl nativeAdImpl) {
        if (this.b != null) {
            this.b.onNativeAdVideoPreceached(nativeAdImpl);
        }
    }

    /* renamed from: a */
    protected void mo4163a(NativeAdImpl nativeAdImpl, int i) {
        if (this.b != null) {
            this.b.onNativeAdVideoPrecachingFailed(nativeAdImpl, i);
        }
    }

    /* renamed from: a */
    protected boolean mo4164a(NativeAdImpl nativeAdImpl, av avVar) {
        if (AppLovinSdkUtils.isValidString(nativeAdImpl.getSourceVideoUrl())) {
            this.d.getLogger().mo4172d("TaskCacheNativeAdVideos", "Beginning slot video caching for ad " + nativeAdImpl.getAdId());
            if (((Boolean) this.d.get(ea.f2387K)).booleanValue()) {
                String a = m2774a(nativeAdImpl.getSourceVideoUrl(), avVar, nativeAdImpl.getResourcePrefixes());
                if (a == null) {
                    return m2782b(nativeAdImpl);
                }
                nativeAdImpl.setVideoUrl(a);
            } else {
                this.d.getLogger().mo4172d("TaskCacheNativeAdVideos", "Resource caching is disabled, skipping...");
            }
            return true;
        }
        this.d.getLogger().mo4172d("TaskCacheNativeAdVideos", "No video attached to ad, nothing to cache...");
        return true;
    }

    public /* bridge */ /* synthetic */ void run() {
        super.run();
    }
}
