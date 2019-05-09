// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzqo;
import com.google.android.gms.internal.ads.zzoq;

final class zzbi implements Runnable
{
    private final /* synthetic */ zzbc zzaaf;
    private final /* synthetic */ zzoq zzww;
    
    zzbi(final zzbc zzaaf, final zzoq zzww) {
        this.zzaaf = zzaaf;
        this.zzww = zzww;
    }
    
    @Override
    public final void run() {
        try {
            if (this.zzaaf.zzvw.zzadf != null) {
                this.zzaaf.zzvw.zzadf.zza((zzqo)this.zzww);
                this.zzaaf.zzb(this.zzww.zzka());
            }
        }
        catch (RemoteException ex) {
            zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
}
