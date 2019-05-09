// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzrr;
import com.google.android.gms.internal.ads.zzov;

final class zzs implements Runnable
{
    private final /* synthetic */ zzq zzwt;
    private final /* synthetic */ zzov zzwu;
    
    zzs(final zzq zzwt, final zzov zzwu) {
        this.zzwt = zzwt;
        this.zzwu = zzwu;
    }
    
    @Override
    public final void run() {
        try {
            if (this.zzwt.zzvw.zzadg != null) {
                this.zzwt.zzvw.zzadg.zza((zzrr)this.zzwu);
                this.zzwt.zzb(this.zzwu.zzka());
            }
        }
        catch (RemoteException ex) {
            zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
}
