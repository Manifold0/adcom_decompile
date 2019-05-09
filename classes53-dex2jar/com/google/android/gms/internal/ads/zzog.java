// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.CustomRenderedAd;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;

@zzadh
public final class zzog extends zzoe
{
    private final OnCustomRenderedAdLoadedListener zzasz;
    
    public zzog(final OnCustomRenderedAdLoadedListener zzasz) {
        this.zzasz = zzasz;
    }
    
    public final void zza(final zzoa zzoa) {
        this.zzasz.onCustomRenderedAdLoaded(new zznz(zzoa));
    }
}
