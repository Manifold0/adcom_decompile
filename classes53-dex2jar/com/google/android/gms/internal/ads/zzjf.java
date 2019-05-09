// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.AdListener;

@zzadh
public final class zzjf extends zzki
{
    private final AdListener zzapu;
    
    public zzjf(final AdListener zzapu) {
        this.zzapu = zzapu;
    }
    
    public final AdListener getAdListener() {
        return this.zzapu;
    }
    
    public final void onAdClicked() {
        this.zzapu.onAdClicked();
    }
    
    public final void onAdClosed() {
        this.zzapu.onAdClosed();
    }
    
    public final void onAdFailedToLoad(final int n) {
        this.zzapu.onAdFailedToLoad(n);
    }
    
    public final void onAdImpression() {
        this.zzapu.onAdImpression();
    }
    
    public final void onAdLeftApplication() {
        this.zzapu.onAdLeftApplication();
    }
    
    public final void onAdLoaded() {
        this.zzapu.onAdLoaded();
    }
    
    public final void onAdOpened() {
        this.zzapu.onAdOpened();
    }
}
