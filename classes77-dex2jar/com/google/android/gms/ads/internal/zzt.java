// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzqk;
import com.google.android.gms.internal.ads.zzoo;

final class zzt implements Runnable
{
    private final /* synthetic */ zzq zzwt;
    private final /* synthetic */ zzoo zzwv;
    
    zzt(final zzq zzwt, final zzoo zzwv) {
        this.zzwt = zzwt;
        this.zzwv = zzwv;
    }
    
    @Override
    public final void run() {
        try {
            if (this.zzwt.zzvw.zzade != null) {
                this.zzwt.zzvw.zzade.zza((zzqk)this.zzwv);
                this.zzwt.zzb(this.zzwv.zzka());
            }
        }
        catch (RemoteException ex) {
            zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
}
