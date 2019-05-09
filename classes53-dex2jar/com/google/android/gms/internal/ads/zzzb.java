// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

final class zzzb implements Runnable
{
    private final /* synthetic */ zzyq zzbvd;
    
    zzzb(final zzyq zzbvd) {
        this.zzbvd = zzbvd;
    }
    
    @Override
    public final void run() {
        try {
            this.zzbvd.zzbuu.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
}
