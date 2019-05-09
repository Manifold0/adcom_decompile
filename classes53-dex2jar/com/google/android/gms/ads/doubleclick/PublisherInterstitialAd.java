// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.doubleclick;

import com.google.android.gms.ads.Correlator;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.common.internal.Preconditions;
import android.content.Context;
import com.google.android.gms.internal.ads.zzma;

public final class PublisherInterstitialAd
{
    private final zzma zzuv;
    
    public PublisherInterstitialAd(final Context context) {
        this.zzuv = new zzma(context, this);
        Preconditions.checkNotNull((Object)context, (Object)"Context cannot be null");
    }
    
    public final AdListener getAdListener() {
        return this.zzuv.getAdListener();
    }
    
    public final String getAdUnitId() {
        return this.zzuv.getAdUnitId();
    }
    
    public final AppEventListener getAppEventListener() {
        return this.zzuv.getAppEventListener();
    }
    
    public final String getMediationAdapterClassName() {
        return this.zzuv.getMediationAdapterClassName();
    }
    
    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzuv.getOnCustomRenderedAdLoadedListener();
    }
    
    public final boolean isLoaded() {
        return this.zzuv.isLoaded();
    }
    
    public final boolean isLoading() {
        return this.zzuv.isLoading();
    }
    
    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(final PublisherAdRequest publisherAdRequest) {
        this.zzuv.zza(publisherAdRequest.zzay());
    }
    
    public final void setAdListener(final AdListener adListener) {
        this.zzuv.setAdListener(adListener);
    }
    
    public final void setAdUnitId(final String adUnitId) {
        this.zzuv.setAdUnitId(adUnitId);
    }
    
    public final void setAppEventListener(final AppEventListener appEventListener) {
        this.zzuv.setAppEventListener(appEventListener);
    }
    
    public final void setCorrelator(final Correlator correlator) {
        this.zzuv.setCorrelator(correlator);
    }
    
    public final void setImmersiveMode(final boolean immersiveMode) {
        this.zzuv.setImmersiveMode(immersiveMode);
    }
    
    public final void setOnCustomRenderedAdLoadedListener(final OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzuv.setOnCustomRenderedAdLoadedListener(onCustomRenderedAdLoadedListener);
    }
    
    public final void show() {
        this.zzuv.show();
    }
}
