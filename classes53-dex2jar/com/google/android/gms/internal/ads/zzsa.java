// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

@zzadh
public final class zzsa extends zzrg
{
    private final NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener zzble;
    
    public zzsa(final NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener zzble) {
        this.zzble = zzble;
    }
    
    public final void zzb(final zzqs zzqs) {
        this.zzble.onCustomTemplateAdLoaded(zzqv.zza(zzqs));
    }
}
