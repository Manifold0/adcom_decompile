// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads;

import android.os.Bundle;
import com.google.android.gms.ads.reward.zza;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.internal.ads.zzjd;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.internal.Preconditions;
import android.content.Context;
import com.google.android.gms.internal.ads.zzma;

public final class InterstitialAd
{
    private final zzma zzuv;
    
    public InterstitialAd(final Context context) {
        this.zzuv = new zzma(context);
        Preconditions.checkNotNull((Object)context, (Object)"Context cannot be null");
    }
    
    public final AdListener getAdListener() {
        return this.zzuv.getAdListener();
    }
    
    public final String getAdUnitId() {
        return this.zzuv.getAdUnitId();
    }
    
    public final String getMediationAdapterClassName() {
        return this.zzuv.getMediationAdapterClassName();
    }
    
    public final boolean isLoaded() {
        return this.zzuv.isLoaded();
    }
    
    public final boolean isLoading() {
        return this.zzuv.isLoading();
    }
    
    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(final AdRequest adRequest) {
        this.zzuv.zza(adRequest.zzay());
    }
    
    public final void setAdListener(final AdListener adListener) {
        this.zzuv.setAdListener(adListener);
        if (adListener != null && adListener instanceof zzjd) {
            this.zzuv.zza((zzjd)adListener);
        }
        else if (adListener == null) {
            this.zzuv.zza((zzjd)null);
        }
    }
    
    public final void setAdUnitId(final String adUnitId) {
        this.zzuv.setAdUnitId(adUnitId);
    }
    
    public final void setImmersiveMode(final boolean immersiveMode) {
        this.zzuv.setImmersiveMode(immersiveMode);
    }
    
    public final void setRewardedVideoAdListener(final RewardedVideoAdListener rewardedVideoAdListener) {
        this.zzuv.setRewardedVideoAdListener(rewardedVideoAdListener);
    }
    
    public final void show() {
        this.zzuv.show();
    }
    
    public final void zza(final zza zza) {
        this.zzuv.zza(zza);
    }
    
    public final void zza(final boolean b) {
        this.zzuv.zza(true);
    }
    
    public final Bundle zzba() {
        return this.zzuv.zzba();
    }
}
