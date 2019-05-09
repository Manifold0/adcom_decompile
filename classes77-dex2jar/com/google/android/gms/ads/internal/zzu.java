// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzqo;
import com.google.android.gms.internal.ads.zzoq;

final class zzu implements Runnable
{
    private final /* synthetic */ zzq zzwt;
    private final /* synthetic */ zzoq zzww;
    
    zzu(final zzq zzwt, final zzoq zzww) {
        this.zzwt = zzwt;
        this.zzww = zzww;
    }
    
    @Override
    public final void run() {
        try {
            if (this.zzwt.zzvw.zzadf != null) {
                this.zzwt.zzvw.zzadf.zza((zzqo)this.zzww);
                this.zzwt.zzb(this.zzww.zzka());
            }
        }
        catch (RemoteException ex) {
            zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
}
