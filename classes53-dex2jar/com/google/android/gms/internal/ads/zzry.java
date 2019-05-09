// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeContentAd;

@zzadh
public final class zzry extends zzra
{
    private final NativeContentAd.OnContentAdLoadedListener zzblc;
    
    public zzry(final NativeContentAd.OnContentAdLoadedListener zzblc) {
        this.zzblc = zzblc;
    }
    
    public final void zza(final zzqo zzqo) {
        this.zzblc.onContentAdLoaded(new zzqr(zzqo));
    }
}
