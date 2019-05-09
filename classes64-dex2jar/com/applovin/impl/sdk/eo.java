// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.List;

public class eo extends en
{
    public eo(final AppLovinSdkImpl appLovinSdkImpl, final List<NativeAdImpl> list, final AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super("TaskCacheNativeAdVideos", appLovinSdkImpl, list, appLovinNativeAdLoadListener);
    }
    
    public eo(final AppLovinSdkImpl appLovinSdkImpl, final List<NativeAdImpl> list, final AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        super("TaskCacheNativeAdVideos", appLovinSdkImpl, list, appLovinNativeAdPrecacheListener);
    }
    
    private boolean b(final NativeAdImpl nativeAdImpl) {
        this.e.w("TaskCacheNativeAdVideos", "Unable to cache video resource " + nativeAdImpl.getSourceVideoUrl());
        int n;
        if (!ag.a(this.f, this.d)) {
            n = -103;
        }
        else {
            n = -202;
        }
        this.a(nativeAdImpl, n);
        return false;
    }
    
    @Override
    protected void a(final NativeAdImpl nativeAdImpl) {
        if (this.b != null) {
            this.b.onNativeAdVideoPreceached(nativeAdImpl);
        }
    }
    
    @Override
    protected void a(final NativeAdImpl nativeAdImpl, final int n) {
        if (this.b != null) {
            this.b.onNativeAdVideoPrecachingFailed(nativeAdImpl, n);
        }
    }
    
    @Override
    protected boolean a(final NativeAdImpl nativeAdImpl, final av av) {
        if (!AppLovinSdkUtils.isValidString(nativeAdImpl.getSourceVideoUrl())) {
            this.d.getLogger().d("TaskCacheNativeAdVideos", "No video attached to ad, nothing to cache...");
            return true;
        }
        this.d.getLogger().d("TaskCacheNativeAdVideos", "Beginning slot video caching for ad " + nativeAdImpl.getAdId());
        if (this.d.get(ea.K)) {
            final String a = this.a(nativeAdImpl.getSourceVideoUrl(), av, nativeAdImpl.getResourcePrefixes());
            if (a == null) {
                return this.b(nativeAdImpl);
            }
            nativeAdImpl.setVideoUrl(a);
        }
        else {
            this.d.getLogger().d("TaskCacheNativeAdVideos", "Resource caching is disabled, skipping...");
        }
        return true;
    }
}
