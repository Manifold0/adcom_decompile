package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinErrorCodes;
import java.util.List;

public class em extends en {
    public em(AppLovinSdkImpl appLovinSdkImpl, List<NativeAdImpl> list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super("TaskCacheNativeAdImages", appLovinSdkImpl, (List) list, appLovinNativeAdLoadListener);
    }

    public em(AppLovinSdkImpl appLovinSdkImpl, List<NativeAdImpl> list, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        super("TaskCacheNativeAdImages", appLovinSdkImpl, (List) list, appLovinNativeAdPrecacheListener);
    }

    /* renamed from: b */
    private boolean m2778b(NativeAdImpl nativeAdImpl) {
        this.e.mo4178w("TaskCacheNativeAdImages", "Unable to cache image resource");
        mo4163a(nativeAdImpl, !ag.m2242a(this.f, this.d) ? AppLovinErrorCodes.NO_NETWORK : AppLovinErrorCodes.UNABLE_TO_PRECACHE_IMAGE_RESOURCES);
        return false;
    }

    /* renamed from: a */
    protected void mo4162a(NativeAdImpl nativeAdImpl) {
        if (this.b != null) {
            this.b.onNativeAdImagesPrecached(nativeAdImpl);
        }
    }

    /* renamed from: a */
    protected void mo4163a(NativeAdImpl nativeAdImpl, int i) {
        if (this.b != null) {
            this.b.onNativeAdImagePrecachingFailed(nativeAdImpl, i);
        }
    }

    /* renamed from: a */
    protected boolean mo4164a(NativeAdImpl nativeAdImpl, av avVar) {
        this.d.getLogger().mo4172d("TaskCacheNativeAdImages", "Beginning slot image caching for ad " + nativeAdImpl.getAdId());
        if (((Boolean) this.d.get(ea.f2387K)).booleanValue()) {
            String a = m2774a(nativeAdImpl.getSourceIconUrl(), avVar, nativeAdImpl.getResourcePrefixes());
            if (a == null) {
                return m2778b(nativeAdImpl);
            }
            nativeAdImpl.setIconUrl(a);
            a = m2774a(nativeAdImpl.getSourceImageUrl(), avVar, nativeAdImpl.getResourcePrefixes());
            if (a == null) {
                return m2778b(nativeAdImpl);
            }
            nativeAdImpl.setImageUrl(a);
        } else {
            this.d.getLogger().mo4172d("TaskCacheNativeAdImages", "Resource caching is disabled, skipping...");
        }
        return true;
    }

    public /* bridge */ /* synthetic */ void run() {
        super.run();
    }
}
