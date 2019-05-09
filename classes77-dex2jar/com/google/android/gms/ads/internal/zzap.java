// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;

final class zzap implements Runnable
{
    private final /* synthetic */ AdOverlayInfoParcel zzzc;
    private final /* synthetic */ zzao zzzd;
    
    zzap(final zzao zzzd, final AdOverlayInfoParcel zzzc) {
        this.zzzd = zzzd;
        this.zzzc = zzzc;
    }
    
    @Override
    public final void run() {
        zzbv.zzei();
        zzl.zza(this.zzzd.zzza.zzvw.zzrt, this.zzzc, true);
    }
}
