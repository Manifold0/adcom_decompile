// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.List;

public class em extends en
{
    public em(final AppLovinSdkImpl appLovinSdkImpl, final List<NativeAdImpl> list, final AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super("TaskCacheNativeAdImages", appLovinSdkImpl, list, appLovinNativeAdLoadListener);
    }
    
    public em(final AppLovinSdkImpl appLovinSdkImpl, final List<NativeAdImpl> list, final AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        super("TaskCacheNativeAdImages", appLovinSdkImpl, list, appLovinNativeAdPrecacheListener);
    }
    
    private boolean b(final NativeAdImpl nativeAdImpl) {
        this.e.w("TaskCacheNativeAdImages", "Unable to cache image resource");
        int n;
        if (!ag.a(this.f, this.d)) {
            n = -103;
        }
        else {
            n = -201;
        }
        this.a(nativeAdImpl, n);
        return false;
    }
    
    @Override
    protected void a(final NativeAdImpl nativeAdImpl) {
        if (this.b != null) {
            this.b.onNativeAdImagesPrecached(nativeAdImpl);
        }
    }
    
    @Override
    protected void a(final NativeAdImpl nativeAdImpl, final int n) {
        if (this.b != null) {
            this.b.onNativeAdImagePrecachingFailed(nativeAdImpl, n);
        }
    }
    
    @Override
    protected boolean a(final NativeAdImpl nativeAdImpl, final av av) {
        this.d.getLogger().d("TaskCacheNativeAdImages", "Beginning slot image caching for ad " + nativeAdImpl.getAdId());
        if (this.d.get(ea.K)) {
            final String a = this.a(nativeAdImpl.getSourceIconUrl(), av, nativeAdImpl.getResourcePrefixes());
            if (a == null) {
                return this.b(nativeAdImpl);
            }
            nativeAdImpl.setIconUrl(a);
            final String a2 = this.a(nativeAdImpl.getSourceImageUrl(), av, nativeAdImpl.getResourcePrefixes());
            if (a2 == null) {
                return this.b(nativeAdImpl);
            }
            nativeAdImpl.setImageUrl(a2);
        }
        else {
            this.d.getLogger().d("TaskCacheNativeAdImages", "Resource caching is disabled, skipping...");
        }
        return true;
    }
}
