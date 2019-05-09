// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzakb;
import java.lang.ref.WeakReference;

final class zzbm implements Runnable
{
    private final /* synthetic */ WeakReference zzaas;
    private final /* synthetic */ zzbl zzaat;
    
    zzbm(final zzbl zzaat, final WeakReference zzaas) {
        this.zzaat = zzaat;
        this.zzaas = zzaas;
    }
    
    @Override
    public final void run() {
        zzbl.zza(this.zzaat, false);
        final zza zza = (zza)this.zzaas.get();
        if (zza != null) {
            final zzjj zzb = this.zzaat.zzaao;
            if (!zza.zzc(zzb)) {
                zzakb.zzdj("Ad is not visible. Not refreshing ad.");
                zza.zzvv.zzg(zzb);
                return;
            }
            zza.zzb(zzb);
        }
    }
}
