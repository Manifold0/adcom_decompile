// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.UnifiedNativeAd;

@zzadh
public final class zzsd extends zzrm
{
    private final UnifiedNativeAd.OnUnifiedNativeAdLoadedListener zzblj;
    
    public zzsd(final UnifiedNativeAd.OnUnifiedNativeAdLoadedListener zzblj) {
        this.zzblj = zzblj;
    }
    
    public final void zza(final zzrr zzrr) {
        this.zzblj.onUnifiedNativeAdLoaded(new zzru(zzrr));
    }
}
