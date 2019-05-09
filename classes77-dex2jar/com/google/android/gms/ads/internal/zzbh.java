// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzrr;
import com.google.android.gms.internal.ads.zzov;

final class zzbh implements Runnable
{
    private final /* synthetic */ zzbc zzaaf;
    private final /* synthetic */ zzov zzwu;
    
    zzbh(final zzbc zzaaf, final zzov zzwu) {
        this.zzaaf = zzaaf;
        this.zzwu = zzwu;
    }
    
    @Override
    public final void run() {
        try {
            if (this.zzaaf.zzvw.zzadg != null) {
                this.zzaaf.zzvw.zzadg.zza((zzrr)this.zzwu);
                this.zzaaf.zzb(this.zzwu.zzka());
            }
        }
        catch (RemoteException ex) {
            zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
}
