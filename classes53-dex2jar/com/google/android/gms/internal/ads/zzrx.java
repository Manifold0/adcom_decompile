// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeAppInstallAd;

@zzadh
public final class zzrx extends zzqx
{
    private final NativeAppInstallAd.OnAppInstallAdLoadedListener zzblb;
    
    public zzrx(final NativeAppInstallAd.OnAppInstallAdLoadedListener zzblb) {
        this.zzblb = zzblb;
    }
    
    public final void zza(final zzqk zzqk) {
        this.zzblb.onAppInstallAdLoaded(new zzqn(zzqk));
    }
}
