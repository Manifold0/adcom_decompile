// 
// Decompiled by Procyon v0.5.34
// 

package com.google.ads.mediation;

import com.google.android.gms.ads.reward.zza;

final class zzb extends zza
{
    private final /* synthetic */ AbstractAdViewAdapter zzhd;
    
    zzb(final AbstractAdViewAdapter zzhd) {
        this.zzhd = zzhd;
    }
    
    @Override
    public final void zzt() {
        if (this.zzhd.zzha != null && this.zzhd.zzhb != null) {
            this.zzhd.zzhb.zzc(this.zzhd.zzha.zzba());
        }
    }
}
