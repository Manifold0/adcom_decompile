// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;

@zzadh
public final class zzaqe extends zzajx
{
    final zzapw zzcyg;
    final zzaqh zzdav;
    private final String zzdaw;
    
    zzaqe(final zzapw zzcyg, final zzaqh zzdav, final String zzdaw) {
        this.zzcyg = zzcyg;
        this.zzdav = zzdav;
        this.zzdaw = zzdaw;
        zzbv.zzff().zza(this);
    }
    
    @Override
    public final void onStop() {
        this.zzdav.abort();
    }
    
    @Override
    public final void zzdn() {
        try {
            this.zzdav.zzdp(this.zzdaw);
        }
        finally {
            zzakk.zzcrm.post((Runnable)new zzaqf(this));
        }
    }
}
