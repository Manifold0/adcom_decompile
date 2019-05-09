// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzqk;
import com.google.android.gms.internal.ads.zzoo;

final class zzbg implements Runnable
{
    private final /* synthetic */ zzbc zzaaf;
    private final /* synthetic */ zzoo zzwv;
    
    zzbg(final zzbc zzaaf, final zzoo zzwv) {
        this.zzaaf = zzaaf;
        this.zzwv = zzwv;
    }
    
    @Override
    public final void run() {
        try {
            if (this.zzaaf.zzvw.zzade != null) {
                this.zzaaf.zzvw.zzade.zza((zzqk)this.zzwv);
                this.zzaaf.zzb(this.zzwv.zzka());
            }
        }
        catch (RemoteException ex) {
            zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
}
