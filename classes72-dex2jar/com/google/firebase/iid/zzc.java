// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.content.Intent;

final class zzc implements Runnable
{
    private final /* synthetic */ Intent zzm;
    private final /* synthetic */ Intent zzn;
    private final /* synthetic */ zzb zzo;
    
    zzc(final zzb zzo, final Intent zzm, final Intent zzn) {
        this.zzo = zzo;
        this.zzm = zzm;
        this.zzn = zzn;
    }
    
    @Override
    public final void run() {
        this.zzo.zzd(this.zzm);
        this.zzo.zza(this.zzn);
    }
}
