// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

@zzadh
public final class zzrz extends zzrd
{
    private final NativeCustomTemplateAd.OnCustomClickListener zzbld;
    
    public zzrz(final NativeCustomTemplateAd.OnCustomClickListener zzbld) {
        this.zzbld = zzbld;
    }
    
    public final void zzb(final zzqs zzqs, final String s) {
        this.zzbld.onCustomClick(zzqv.zza(zzqs), s);
    }
}
