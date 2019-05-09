// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.internal.ads.zzjd;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzajx;

@zzadh
final class zzao extends zzajx
{
    final /* synthetic */ zzal zzza;
    private final int zzzb;
    
    public zzao(final zzal zzza, final int zzzb) {
        this.zzza = zzza;
        this.zzzb = zzzb;
    }
    
    @Override
    public final void onStop() {
    }
    
    @Override
    public final void zzdn() {
        final boolean zzze = this.zzza.zzvw.zzze;
        final boolean zzdi = this.zzza.zzdi();
        final boolean zza = this.zzza.zzys;
        final float zzb = this.zzza.zzyt;
        int zzzb;
        if (this.zzza.zzvw.zzze) {
            zzzb = this.zzzb;
        }
        else {
            zzzb = -1;
        }
        final zzaq zzaq = new zzaq(zzze, zzdi, zza, zzb, zzzb, this.zzza.zzyu, this.zzza.zzvw.zzacw.zzzl, this.zzza.zzvw.zzacw.zzzm);
        int n;
        if ((n = this.zzza.zzvw.zzacw.zzbyo.getRequestedOrientation()) == -1) {
            n = this.zzza.zzvw.zzacw.orientation;
        }
        zzakk.zzcrm.post((Runnable)new zzap(this, new AdOverlayInfoParcel((zzjd)this.zzza, this.zzza, this.zzza, this.zzza.zzvw.zzacw.zzbyo, n, this.zzza.zzvw.zzacr, this.zzza.zzvw.zzacw.zzcev, zzaq)));
    }
}
