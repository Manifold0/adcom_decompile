// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzal;

final class zztz
{
    zzal zzbor;
    @Nullable
    zzjj zzbos;
    zzst zzbot;
    long zzbou;
    boolean zzbov;
    private final /* synthetic */ zzty zzbow;
    boolean zzwa;
    
    zztz(final zzty zzbow, final zzss zzss) {
        this.zzbow = zzbow;
        this.zzbor = zzss.zzaw(zzbow.zzye);
        this.zzbot = new zzst();
        final zzst zzbot = this.zzbot;
        final zzal zzbor = this.zzbor;
        zzbor.zza((zzkh)new zzsu(zzbot));
        zzbor.zza((zzla)new zztc(zzbot));
        zzbor.zza((zzod)new zzte(zzbot));
        zzbor.zza((zzke)new zztg(zzbot));
        zzbor.zza((zzahe)new zzti(zzbot));
    }
    
    zztz(final zzty zzty, final zzss zzss, final zzjj zzbos) {
        this(zzty, zzss);
        this.zzbos = zzbos;
    }
    
    final boolean load() {
        if (this.zzwa) {
            return false;
        }
        zzjj zzjj;
        if (this.zzbos != null) {
            zzjj = this.zzbos;
        }
        else {
            zzjj = this.zzbow.zzboo;
        }
        this.zzbov = this.zzbor.zzb(zztw.zzi(zzjj));
        this.zzwa = true;
        this.zzbou = zzbv.zzer().currentTimeMillis();
        return true;
    }
}
