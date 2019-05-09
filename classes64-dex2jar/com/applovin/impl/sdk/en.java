// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Iterator;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.nativeAds.AppLovinNativeAd;
import java.util.List;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;

abstract class en extends dx
{
    protected AppLovinNativeAdLoadListener a;
    protected AppLovinNativeAdPrecacheListener b;
    private List<NativeAdImpl> h;
    private int i;
    
    en(final String s, final AppLovinSdkImpl appLovinSdkImpl, final List<NativeAdImpl> h, final AppLovinNativeAdLoadListener a) {
        super(s, appLovinSdkImpl);
        this.i = 0;
        this.a = a;
        this.h = h;
    }
    
    en(final String s, final AppLovinSdkImpl appLovinSdkImpl, final List<NativeAdImpl> h, final AppLovinNativeAdPrecacheListener b) {
        super(s, appLovinSdkImpl);
        this.i = 0;
        if (h == null) {
            throw new IllegalArgumentException("Slots cannot be null");
        }
        this.h = h;
        this.b = b;
    }
    
    private void a(final int n) {
        if (this.a != null) {
            this.a.onNativeAdsFailedToLoad(n);
        }
    }
    
    private void a(final List<AppLovinNativeAd> list) {
        if (this.a != null) {
            this.a.onNativeAdsLoaded(list);
        }
    }
    
    protected String a(final String s, final av av, final List<String> list) {
        String a;
        if (!AppLovinSdkUtils.isValidString(s)) {
            this.d.getLogger().d(this.a(), "Asked to cache file with null/empty URL, nothing to do.");
            a = null;
        }
        else {
            if (!gd.a(s, list)) {
                this.d.getLogger().d(this.a(), "Domain is not whitelisted, skipping precache for URL " + s);
                return null;
            }
            try {
                if ((a = av.a(this.f, s, null, list, true, true, null)) == null) {
                    this.e.w(this.a(), "Unable to cache icon resource " + s);
                    return null;
                }
            }
            catch (Exception ex) {
                this.e.w(this.a(), "Unable to cache icon resource " + s, ex);
                return null;
            }
        }
        return a;
    }
    
    protected abstract void a(final NativeAdImpl p0);
    
    protected abstract void a(final NativeAdImpl p0, final int p1);
    
    protected abstract boolean a(final NativeAdImpl p0, final av p1);
    
    @Override
    public void run() {
        for (final NativeAdImpl nativeAdImpl : this.h) {
            final av fileManager = this.d.getFileManager();
            this.d.getLogger().d(this.a(), "Beginning resource caching phase...");
            if (this.a(nativeAdImpl, fileManager)) {
                ++this.i;
                this.a(nativeAdImpl);
            }
            else {
                this.d.getLogger().e(this.a(), "Unable to cache resources");
            }
        }
        try {
            if (this.i == this.h.size()) {
                this.a((List<AppLovinNativeAd>)this.h);
                return;
            }
            if (this.d.get(ea.bv)) {
                this.d.getLogger().e(this.a(), "Mismatch between successful populations and requested size");
                this.a(-6);
                return;
            }
        }
        catch (Throwable t) {
            this.d.getLogger().userError(this.a(), "Encountered exception while notifying publisher code", t);
            return;
        }
        this.a((List<AppLovinNativeAd>)this.h);
    }
}
