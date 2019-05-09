// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.ads.AdRequest;

final class zzyv implements Runnable
{
    private final /* synthetic */ zzyq zzbvd;
    private final /* synthetic */ AdRequest.ErrorCode zzbve;
    
    zzyv(final zzyq zzbvd, final AdRequest.ErrorCode zzbve) {
        this.zzbvd = zzbvd;
        this.zzbve = zzbve;
    }
    
    @Override
    public final void run() {
        try {
            this.zzbvd.zzbuu.onAdFailedToLoad(zzzc.zza(this.zzbve));
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
}
